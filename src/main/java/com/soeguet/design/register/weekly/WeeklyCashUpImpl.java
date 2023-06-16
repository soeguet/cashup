package com.soeguet.design.register.weekly;

import static com.soeguet.design.register.Cashrolls.FIFTY_TWENTY_TEN_CENT;
import static com.soeguet.design.register.Cashrolls.FIVE_TWO_ONE_CENT;
import static com.soeguet.design.register.Cashrolls.TWO_AND_ONE_EURO;

import com.soeguet.config.Settings;
import com.soeguet.design.register.PdfCreate;
import com.soeguet.design.register.weekly.properties.WeeklyPropertiesFrameImpl;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

public class WeeklyCashUpImpl extends WeeklyCashUp {

  private final Settings settings;
  private Process process;

  public WeeklyCashUpImpl() {
    settings = Settings.getInstance();
    dateBtnActionPerformed(null);
  }

  @Override
  protected void settingsBtnActionPerformed(ActionEvent e) {
    JDialog propertiesFrame = new WeeklyPropertiesFrameImpl();
    propertiesFrame.pack();
    propertiesFrame.setSize(propertiesFrame.getWidth() + 100, propertiesFrame.getHeight());
    propertiesFrame.setLocationRelativeTo(this);
    propertiesFrame.setVisible(true);
  }

  @Override
  protected void exitBtnActionPerformed(ActionEvent e) {
    System.exit(0);
  }

  @Override
  protected void pdfBtnActionPerformed(ActionEvent e) {
    this.setName("WeeklyCash");
    new PdfCreate(this);
  }

  @Override
  protected void calculateBtnActionPerformed(ActionEvent e) {

    double coinsIntermediateValue = getIntermediateValue();
    form_intermediateValueNotesLabel.setText(decimalFormat.format(coinsIntermediateValue) + " €");

    double notesIntermediateValue = getNotesIntermediateValue();
    form_intermediateValueNotesLabel.setText(decimalFormat.format(notesIntermediateValue) + " €");

    double extraCash = getExtraCash();
    form_extraChangeIntermediateLabel.setText(decimalFormat.format(extraCash) + " €");

    double totalValue = coinsIntermediateValue + notesIntermediateValue + extraCash;
    form_totalValueLabel.setText(decimalFormat.format(totalValue) + " €");

    String debitValueText = form_debitTextField.getText().replace(".", "");
    debitValueText = debitValueText.replace(",", ".");

    try {
      double differenceValue = totalValue - Double.parseDouble(debitValueText);
      differenceValue = roundTwoDecimalPlaces(differenceValue);
      form_differenceValueLabel.setText(decimalFormat.format(differenceValue) + " €");
    } catch (NumberFormatException ex) {
      form_debitTextField.setText("0,00");
      throw new RuntimeException(ex.getCause() + "\n" + ex.getMessage());
    }
  }

  @Override
  public void resetBtnActionPerformed(ActionEvent e) {
    for (JSpinner spinner : resetArray) {
      try {
        spinner.setValue(0);
      } catch (IllegalArgumentException ex) {
        throw new RuntimeException(ex);
      }
    }
    form_debitTextField.setText("0");
    calculateBtnActionPerformed(e);
  }

  @Override
  protected void debitBtnNewActionPerformed(ActionEvent e) {
    runPythonScriptDebitValueFetching();
    pleaseStandByModal();
  }

  private void runPythonScriptDebitValueFetching() {
    new Thread(
            () -> {
              try {
                process =
                    new ProcessBuilder("python3", settings.getDebitValuePathWeekly())
                        .redirectErrorStream(true)
                        .start();

                try (BufferedReader in =
                    new BufferedReader(new InputStreamReader(process.getInputStream()))) {

                  String result = in.lines().collect(Collectors.joining(System.lineSeparator()));
                  int exitCode = process.waitFor();

                  if (exitCode == 0) {
                    form_debitTextField.setText(result);
                    dialog.dispose();
                  }
                }
              } catch (IOException | InterruptedException e) {
                e.printStackTrace();
              }
            })
        .start();
  }

  private void pleaseStandByModal() {
    new Thread(
            () -> {
              jOptionPane =
                  new JOptionPane(
                      "debit value fetching ongoing, please wait",
                      JOptionPane.WARNING_MESSAGE,
                      JOptionPane.OK_CANCEL_OPTION);
              dialog = jOptionPane.createDialog(this, "caution");
              dialog.setModalityType(Dialog.ModalityType.TOOLKIT_MODAL);
              dialog.setVisible(true);

              if (jOptionPane.getValue().equals(2)) {
                process.destroy();
              }
            })
        .start();
  }

  @Override
  protected void dateBtnActionPerformed(ActionEvent e) {
    form_dateSpinner.setModel(new SpinnerDateModel());
    JSpinner.DateEditor editor = new JSpinner.DateEditor(form_dateSpinner, "dd.MM.yy, HH:mm");
    form_dateSpinner.setEditor(editor);
  }

  private double roundTwoDecimalPlaces(double value) {
    return Math.round(value * 100.00) / 100.00;
  }

  private double getExtraCash() {

    double extraGeld = 0;

    extraGeld += Integer.parseInt(form_fullRegisterSpinner.getValue().toString()) * 500.00;

    extraGeld +=
        getCashExtraRolls(
            TWO_AND_ONE_EURO, 2.00, form_rollTwoEuroSpinner, form_rollTwoEuroSpinner2);
    extraGeld +=
        getCashExtraRolls(
            TWO_AND_ONE_EURO, 1.00, form_rollOneEuroSpinner, form_rollOneEuroSpinner2);
    extraGeld +=
        getCashExtraRolls(
            FIFTY_TWENTY_TEN_CENT, 0.50, form_rollFiftyCentSpinner, form_rollFiftyCentSpinner2);
    extraGeld +=
        getCashExtraRolls(
            FIFTY_TWENTY_TEN_CENT, 0.20, form_rollTwentyCentSpinner, form_rollTwentyCentSpinner2);
    extraGeld +=
        getCashExtraRolls(
            FIFTY_TWENTY_TEN_CENT, 0.10, form_rollTenCentSpinner, form_rollTenCentSpinner2);
    extraGeld +=
        getCashExtraRolls(
            FIVE_TWO_ONE_CENT, 0.05, form_rollFiveCentSpinner, form_rollFiveCentSpinner2);
    extraGeld +=
        getCashExtraRolls(
            FIVE_TWO_ONE_CENT, 0.02, form_rollTwoCentSpinner, form_rollTwoCentSpinner2);
    extraGeld +=
        getCashExtraRolls(
            FIVE_TWO_ONE_CENT, 0.01, form_rollOneCentSpinner, form_rollOneCentSpinner2);

    extraGeld += getGeldExtraBoxen(form_boxTwoEuroSpinner, 5, TWO_AND_ONE_EURO, 2.00);
    extraGeld += getGeldExtraBoxen(form_boxOneEuroSpinner, 5, TWO_AND_ONE_EURO, 1.00);
    extraGeld += getGeldExtraBoxen(form_boxFiftyCentSpinner, 3, FIFTY_TWENTY_TEN_CENT, 0.50);
    extraGeld += getGeldExtraBoxen(form_boxTwentyCentSpinner, 3, FIFTY_TWENTY_TEN_CENT, 0.20);
    extraGeld += getGeldExtraBoxen(form_boxTenCentSpinner, 3, FIFTY_TWENTY_TEN_CENT, 0.10);
    extraGeld += getGeldExtraBoxen(form_boxFiveCentSpinner, 3, FIVE_TWO_ONE_CENT, 0.05);
    extraGeld += getGeldExtraBoxen(form_boxTwoCentSpinner, 5, FIVE_TWO_ONE_CENT, 0.02);
    extraGeld += getGeldExtraBoxen(form_boxOneCentSpinner, 5, FIVE_TWO_ONE_CENT, 0.01);

    return extraGeld;
  }

  public double getCashExtraRolls(int coinsAmount, double rowValue, JSpinner... spinner) {

    int value = 0;

    for (JSpinner spin : spinner) {

      value += (int) spin.getValue();
    }

    return value * coinsAmount * rowValue;
  }

  public double getGeldExtraBoxen(
      JSpinner spinner, int boxAmount, int coinAmount, double rowValue) {

    return Integer.parseInt(spinner.getValue().toString()) * boxAmount * coinAmount * rowValue;
  }

  private double getNotesIntermediateValue() {

    double notesIntermediateValue = 0;
    notesIntermediateValue +=
        calculateRowValue(
            200.0,
            twoHundredEuroRow,
            form_twohundredEuroLabelAmount,
            form_twohundredEuroLabelValue);
    notesIntermediateValue +=
        calculateRowValue(
            100.0,
            oneHundredEuroRow,
            form_onehundredEuroLabelAmount,
            form_onehundredEuroLabelValue);
    notesIntermediateValue +=
        calculateRowValue(50.0, fiftyEuroRow, form_fiftyEuroLabelAmount, form_fiftyEuroLabelValue);
    notesIntermediateValue +=
        calculateRowValue(
            20.0, twentyEuroRow, form_twentyEuroLabelAmount, form_twentyEuroLabelValue);
    notesIntermediateValue +=
        calculateRowValue(10.0, tenEuroRow, form_tenEuroLabelAmount, form_tenEuroLabelValue);
    notesIntermediateValue +=
        calculateRowValue(5.0, fiveEuroRow, form_fiveEuroLabelAmount, form_fiftyEuroLabelValue);

    notesIntermediateValue = roundTwoDecimalPlaces(notesIntermediateValue);
    return notesIntermediateValue;
  }

  private double getIntermediateValue() {

    double coinsIntermediateValue = 0;
    coinsIntermediateValue +=
        calculateRowValue(2.00, twoEuroRow, form_twoEuroLabelAmount, form_twoEuroLabelValue);
    coinsIntermediateValue +=
        calculateRowValue(1.00, OneEuroRow, form_oneEuroLabelAmount, form_oneEuroLabelValue);
    coinsIntermediateValue +=
        calculateRowValue(0.5, fiftyCentRow, form_fiftyCentLabelAmount, form_fiftyCentLabelValue);
    coinsIntermediateValue +=
        calculateRowValue(
            0.2, twentyCentRow, form_twentyCentLabelAmount, form_twentyCentLabelValue);
    coinsIntermediateValue +=
        calculateRowValue(0.1, tenCentRow, form_tenCentLabelAmount, form_tenCentLabelValue);
    coinsIntermediateValue +=
        calculateRowValue(0.05, fiveCentRow, form_fiveCentLabelAmount, form_fiveCentLabelValue);
    coinsIntermediateValue +=
        calculateRowValue(0.02, twoCentRow, form_twoCentLabelAmount, form_twoCentLabelValue);
    coinsIntermediateValue +=
        calculateRowValue(0.01, oneCentRow, form_oneCentLabelAmount, form_oneCentLabelValue);
    coinsIntermediateValue = roundTwoDecimalPlaces(coinsIntermediateValue);
    return coinsIntermediateValue;
  }

  public double calculateRowValue(
      double value, JSpinner[] spinnerRow, JLabel amountCoinsRow, JLabel valueCoinsRow) {

    int rowAmount = 0;

    for (JSpinner spinner : spinnerRow) {

      rowAmount += (int) spinner.getValue();
    }

    if (amountCoinsRow != null) {

      amountCoinsRow.setText(String.valueOf(rowAmount));
    }

    double intermediateValue = value * rowAmount;
    intermediateValue = roundTwoDecimalPlaces(intermediateValue);

    if (valueCoinsRow != null) {

      valueCoinsRow.setText(decimalFormat.format(intermediateValue));
    }

    return value * rowAmount;
  }

  private void createNewDialog() {
    jOptionPane =
        new JOptionPane(
            "the register entry is being made",
            JOptionPane.WARNING_MESSAGE,
            JOptionPane.OK_CANCEL_OPTION);
    dialog = jOptionPane.createDialog(this, "register entry");

    new Thread(() -> dialog.setVisible(true)).start();
  }

  private void registerScript() {
    int response =
        JOptionPane.showConfirmDialog(
            this,
            "would you like to begin the register entry?  \n"
                + "the script you chose is located at: \n\n"
                + settings.getRegisterPathDaily(),
            "register entry start",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

    if (response == 0) {

      if (!new File(settings.getRegisterPathDaily()).exists()) {
        JOptionPane.showMessageDialog(
            this, "cannot find python script", "error", JOptionPane.ERROR_MESSAGE);
        return;
      }

      createNewDialog();

      try {
        String value =
            form_differenceValueLabel
                .getText()
                .replace(" €", "")
                .replace(".", "")
                .replace(",", ".");

        process =
            new ProcessBuilder(
                    "python3",
                    settings.getRegisterPathDaily(),
                    String.valueOf(Math.abs(Double.parseDouble(value))).replace(".", ","),
                    Double.parseDouble(value) > 0 ? "positiv" : "negativ",
                    LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                    LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")))
                .redirectErrorStream(true)
                .start();

        waitForProcessCompletion();

      } catch (IOException e) {
        JOptionPane.showMessageDialog(
            this, "the python script cannot be found!", "error!", JOptionPane.ERROR_MESSAGE);
        throw new RuntimeException(e);
      }
    }
  }

  private void waitForProcessCompletion() {
    try {
      int exitCode = process.waitFor();
      if (exitCode == 0) {
        dialog.dispose();
      }
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void registerBtnActionPerformed(ActionEvent e) {
    new Thread(this::registerScript).start();
  }
}
