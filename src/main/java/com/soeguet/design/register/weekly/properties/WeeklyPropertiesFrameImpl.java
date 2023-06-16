package com.soeguet.design.register.weekly.properties;

import com.soeguet.config.Settings;

import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class WeeklyPropertiesFrameImpl extends WeeklyPropertiesFrame {

  private final Settings settings;

  public WeeklyPropertiesFrameImpl() {
    super();
    settings = Settings.getInstance();
    initialLoadUpValues();
  }

  private void initialLoadUpValues() {
    form_debitValueTextField.setText(settings.getDebitValuePathDaily());
    form_pdfPathTextField.setText(settings.getPdfPathDaily());
    form_registerPathTextField.setText(settings.getRegisterPathDaily());
  }

  @Override
  protected void pdfPathTextFieldMouseClicked(MouseEvent e) {
    choosePathForAction(form_pdfPathTextField, "dir");
  }

  @Override
  protected void debitValueTextFieldMouseClicked(MouseEvent e) {
    choosePathForAction(form_debitValueTextField, "py");
  }

  @Override
  protected void registerPathTextFieldMouseClicked(MouseEvent e) {
    choosePathForAction(form_registerPathTextField, "py");
  }

  private void choosePathForAction(JFormattedTextField textField, String... fileTypes) {

    JFileChooser chooser = new JFileChooser();
    chooser.setCurrentDirectory(new File(textField.getText()));

    if (fileTypes.length > 0 && !fileTypes[0].contains("dir")) {
      FileNameExtensionFilter filter = new FileNameExtensionFilter(".py", fileTypes);
      chooser.setFileFilter(filter);
    } else {
      chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

    int returnVal = chooser.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      textField.setText(chooser.getSelectedFile().getAbsolutePath());
    }
  }

  @Override
  protected void cancelButtonMouseClicked(MouseEvent e) {
    e.consume();
    dispose();
  }

  @Override
  protected void okButtonMouseClicked(MouseEvent e) {
    e.consume();
    saveSettings();
    dispose();
  }

  private void saveSettings() {

    settings.setRegisterPathWeekly(form_registerPathTextField.getText());
    settings.setPdfPathWeekly(form_pdfPathTextField.getText());
    settings.setDebitValuePathWeekly(form_debitValueTextField.getText());
  }
}
