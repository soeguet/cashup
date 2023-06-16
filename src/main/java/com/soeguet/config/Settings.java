package com.soeguet.config;

import java.util.prefs.Preferences;

public class Settings {

  private static Settings instance;

  private final Preferences preferences;

  private Settings() {
    preferences = Preferences.userNodeForPackage(Settings.class);
  }

  public static Settings getInstance() {
    if (instance == null) {
      instance = new Settings();
    }
    return instance;
  }

  // PDF Path
  public String getPdfPathDaily() {
    return preferences.get("pdfPath", System.getProperty("user.dir"));
  }

  public void setPdfPathDaily(String pdfPath) {
    preferences.put("pdfPath", pdfPath);
  }

  public String getPdfPathWeekly() {
    return preferences.get("pdfPathWeekly", System.getProperty("user.dir"));
  }

  public void setPdfPathWeekly(String pdfPath) {
    preferences.put("pdfPathWeekly", pdfPath);
  }

  // Debit Path
  public String getDebitValuePathDaily() {
    return preferences.get("debitPathDaily", System.getProperty("user.dir"));
  }

  public void setDebitValuePathDaily(String pdfPath) {
    preferences.put("debitPathDaily", pdfPath);
  }

  public String getDebitValuePathWeekly() {
    return preferences.get("debitPathWeekly", System.getProperty("user.dir"));
  }

  public void setDebitValuePathWeekly(String pdfPath) {
    preferences.put("debitPathWeekly", pdfPath);
  }

  // Register Path
  public String getRegisterPathDaily() {
    return preferences.get("registerPathDaily", System.getProperty("user.dir"));
  }

  public void setRegisterPathDaily(String pdfPath) {
    preferences.put("registerPathDaily", pdfPath);
  }

  public String getRegisterPathWeekly() {
    return preferences.get("registerPathWeekly", System.getProperty("user.dir"));
  }

  public void setRegisterPathWeekly(String pdfPath) {
    preferences.put("registerPathWeekly", pdfPath);
  }
}
