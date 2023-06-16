package com.soeguet.design.register.daily;

import com.soeguet.config.Settings;
import com.soeguet.design.register.PdfCreate;
import com.soeguet.design.register.daily.properties.DailyPropertiesFrameImpl;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

public class DailyCashImpl extends DailyCash {

  private final DecimalFormat decimalFormat = new DecimalFormat();
  private Process process;
  private final Settings settings;

  public DailyCashImpl() {
    settings = Settings.getInstance();
    dateBtnActionPerformed(null);
  }

  @Override
  protected void settingsMenuItemAction(ActionEvent e) {
    JDialog propertiesFrame = new DailyPropertiesFrameImpl();
    propertiesFrame.pack();
    propertiesFrame.setSize(propertiesFrame.getWidth() + 100, propertiesFrame.getHeight());
    propertiesFrame.setLocationRelativeTo(this);
    propertiesFrame.setVisible(true);
  }

  @Override
  protected void exitMenuItemAction(ActionEvent e) {
    dispose();
  }

  @Override
  protected void calculateBtnActionPerformed(ActionEvent e) {

    double coinsIntermediateValue = calculateAllCoins();
    form_intermediateValueOverviewCoins.setText(
        decimalFormat.format(coinsIntermediateValue) + " €");

    double billsIntermediateValue = calculateAllNotes();
    form_intermedateValueOverviewNotes.setText(decimalFormat.format(billsIntermediateValue) + " €");

    double totalValue = coinsIntermediateValue + billsIntermediateValue;
    form_totalValueLabel.setText(decimalFormat.format(totalValue) + " €");

    String debitValueString = form_debitTextField.getText().replace(".", "");
    debitValueString = debitValueString.replace(",", ".");

    try {
      double deltaValue = totalValue - Double.parseDouble(debitValueString);
      deltaValue = Math.round(deltaValue * 100.00) / 100.00;
      form_differenceValueLabel.setText(decimalFormat.format(deltaValue) + " €");
    } catch (NumberFormatException ex) {

      form_debitTextField.setText("0,00");
      throw new RuntimeException(ex.getCause() + "\n" + ex.getMessage());
    }
  }

  private double calculateAllNotes() {
    double billsIntermediateValue = 0;
    billsIntermediateValue +=
        calculateHelper(
            200.0,
            twohundredEuroRow,
            form_twohundredEuroLabelAmount,
            form_twohundredEuroLabelValue);
    billsIntermediateValue +=
        calculateHelper(
            100.0,
            onehundredEuroRow,
            form_onehundredEuroLabelAmount,
            form_onehundredEuroLabelValue);
    billsIntermediateValue +=
        calculateHelper(50.0, fiftyEuroRow, form_fiftyEuroLabelAmount, form_fiftyEuroLabelValue);
    billsIntermediateValue +=
        calculateHelper(20.0, twentyEuroRow, form_twentyEuroLabelAmount, form_twentyEuroLabelValue);
    billsIntermediateValue +=
        calculateHelper(10.0, tenEuroRow, form_tenEuroLabelAmount, form_tenEuroLabelValue);
    billsIntermediateValue +=
        calculateHelper(5.0, fiveEuroRow, form_fiveEuroLabelAmount, form_fiveEuroLabelValue);

    billsIntermediateValue = Math.round(billsIntermediateValue * 100.00) / 100.00;
    return billsIntermediateValue;
  }

  private double calculateAllCoins() {
    double coinsIntermediateValue = 0;
    coinsIntermediateValue +=
        calculateHelper(2.00, twoEuroRow, form_twoEuroLabelAmount, form_twoEuroLabelValue);
    coinsIntermediateValue +=
        calculateHelper(1.00, oneEuroRow, form_oneEuroLabelAmount, form_oneEuroLabelValue);
    coinsIntermediateValue +=
        calculateHelper(0.5, fiftyCentRow, form_fiftyCentLabelAmount, form_fiftyCentLabelValue);
    coinsIntermediateValue +=
        calculateHelper(0.2, twentyCentRow, form_twentyCentLabelAmount, form_twentyCentLabelValue);
    coinsIntermediateValue +=
        calculateHelper(0.1, tenCentRow, form_tenCentLabelAmount, form_tenCentLabelValue);
    coinsIntermediateValue +=
        calculateHelper(0.05, fiveCentRow, form_fiveCentLabelAmount, form_fiveCentLabelValue);
    coinsIntermediateValue +=
        calculateHelper(0.02, twoCentRow, form_twoCentLabelAmount, form_twoCentLabelValue);
    coinsIntermediateValue +=
        calculateHelper(0.01, oneCentRow, form_oneCentLabelAmount, form_oneCentLabelValue);

    coinsIntermediateValue = Math.round(coinsIntermediateValue * 100.00) / 100.00;
    return coinsIntermediateValue;
  }

  @Override
  protected void pdfBtnActionPerformed(ActionEvent e) {
    this.setName("DailyCash");
    new PdfCreate(this);
  }

  @Override
  protected void resetBtnActionPerformed(ActionEvent e) {
    for (JSpinner spinner : resetArray) {
      spinner.setValue(0);
    }
    form_debitTextField.setText("0");
    calculateBtnActionPerformed(e);
  }

  @Override
  protected void debitBtnActionPerformed(ActionEvent e) {

    new Thread(
            () -> {
              var options = new String[] {"go!", "cancel"};

              var answer =
                  JOptionPane.showOptionDialog(
                      this,
                      "do you want me to fetch the debit value? \n"
                          + "the script you chose is located at: \n\n"
                          + settings.getDebitValuePathDaily(),
                      "debit value fetching",
                      JOptionPane.DEFAULT_OPTION,
                      JOptionPane.PLAIN_MESSAGE,
                      null,
                      options,
                      options[0]);

              if (answer == 0) {
                if (!new File(settings.getDebitValuePathDaily()).exists()) {
                  JOptionPane.showConfirmDialog(
                      this,
                      "cannot find python script",
                      "error",
                      JOptionPane.DEFAULT_OPTION,
                      JOptionPane.WARNING_MESSAGE);
                  return;
                }
                runPythonScriptDebitValueFetching();
                pleaseStandByModal();
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

  @Override
  protected void registerBtnActionPerformed(ActionEvent e) {
    new Thread(this::registerScript).start();
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

  private void createNewDialog() {
    jOptionPane =
        new JOptionPane(
            "the register entry is being made",
            JOptionPane.WARNING_MESSAGE,
            JOptionPane.OK_CANCEL_OPTION);
    dialog = jOptionPane.createDialog(this, "register entry");

    new Thread(() -> dialog.setVisible(true)).start();
  }

  private double calculateHelper(
      double value, JSpinner[] spinnerRow, JLabel amountCoinRows, JLabel valueCoinRows) {

    int valueVar = Arrays.stream(spinnerRow).mapToInt(spinner -> (int) spinner.getValue()).sum();

    amountCoinRows.setText(String.valueOf(valueVar));

    double intermediateValue = value * valueVar;
    intermediateValue = Math.round(intermediateValue * 100.00) / 100.00;
    valueCoinRows.setText(decimalFormat.format(intermediateValue));

    return value * valueVar;
  }

  private void runPythonScriptDebitValueFetching() {
    new Thread(
            () -> {
              try {
                process =
                    new ProcessBuilder("python3", settings.getDebitValuePathDaily())
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
}
