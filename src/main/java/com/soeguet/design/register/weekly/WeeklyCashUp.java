package com.soeguet.design.register.weekly;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.stream.Stream;
import javax.swing.*;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public abstract class WeeklyCashUp extends JInternalFrame {

    protected String saveFileRoute = "G:\\Meine Ablage\\_PROGRAMMIEREN\\desktop_module\\build\\distributions\\Kassensturz\\bin\\";
    protected JSpinner[] twoEuroRow, OneEuroRow, fiftyCentRow, twentyCentRow, tenCentRow,
			fiveCentRow, twoCentRow, oneCentRow, twoHundredEuroRow, oneHundredEuroRow, fiftyEuroRow,
			twentyEuroRow, tenEuroRow, fiveEuroRow;
    protected final DecimalFormat decimalFormat;
    protected JOptionPane jOptionPane;
    protected JDialog dialog;
    protected boolean debitQueryCancel;
    protected String posOrNeg;
    protected JSpinner[] resetArray;

    public WeeklyCashUp() {

        decimalFormat = new DecimalFormat();
        // JFormDesigner Init
        initComponents();
        customInit();

        prepareHighlighting();
    }

    private void prepareHighlighting() {
        highlightSelectedValueRow(new JSpinner[]{form_rollTwoEuroSpinner, form_rollTwoEuroSpinner2}, new JLabel[]{form_twoEuroRollValue, form_twoEuroRollAmount}, form_rollPanel,"rolls");
        highlightSelectedValueRow(new JSpinner[]{form_rollOneEuroSpinner, form_rollOneEuroSpinner2}, new JLabel[]{form_oneEuroRollValue, form_oneEuroRollAmount}, form_rollPanel,"rolls");
        highlightSelectedValueRow(new JSpinner[]{form_rollFiftyCentSpinner, form_rollFiftyCentSpinner2}, new JLabel[]{form_fiftyCentRollValue, form_fiftyCentRollAmount}, form_rollPanel,"rolls");
        highlightSelectedValueRow(new JSpinner[]{form_rollTwentyCentSpinner, form_rollTwentyCentSpinner2}, new JLabel[]{form_twentyCentRollValue, form_twentyCentRollAmount}, form_rollPanel,"rolls");
        highlightSelectedValueRow(new JSpinner[]{form_rollTenCentSpinner, form_rollTenCentSpinner2}, new JLabel[]{form_tenCentRollValue, form_tenCentRollAmount}, form_rollPanel,"rolls");
        highlightSelectedValueRow(new JSpinner[]{form_rollFiveCentSpinner, form_rollFiveCentSpinner2}, new JLabel[]{form_fiveCentRollValue, form_fiveCentRollAmount}, form_rollPanel,"rolls");
        highlightSelectedValueRow(new JSpinner[]{form_rollTwoCentSpinner, form_rollTwoCentSpinner2}, new JLabel[]{form_twoCentRollValue, form_twoCentRollAmount}, form_rollPanel,"rolls");
        highlightSelectedValueRow(new JSpinner[]{form_rollOneCentSpinner, form_rollOneCentSpinner2}, new JLabel[]{form_oneCentRollValue, form_oneCentRollAmount}, form_rollPanel,"rolls");

        highlightSelectedValueRow(new JSpinner[]{form_boxTwoEuroSpinner}, new JLabel[]{form_twoEuroBoxValue, form_twoEuroBoxAmount}, form_boxPanel,"boxes");
        highlightSelectedValueRow(new JSpinner[]{form_boxOneEuroSpinner}, new JLabel[]{form_oneEuroBoxValue, form_oneEuroBoxAmount}, form_boxPanel,"boxes");
        highlightSelectedValueRow(new JSpinner[]{form_boxFiftyCentSpinner}, new JLabel[]{form_fiftyCentBoxValue, form_fiftyCentBoxAmount}, form_boxPanel,"boxes");
        highlightSelectedValueRow(new JSpinner[]{form_boxTwentyCentSpinner}, new JLabel[]{form_twentyCentBoxValue, form_twentyCentBoxAmount}, form_boxPanel,"boxes");
        highlightSelectedValueRow(new JSpinner[]{form_boxTenCentSpinner}, new JLabel[]{form_tenCentBoxValue, form_tenCentBoxAmount}, form_boxPanel,"boxes");
        highlightSelectedValueRow(new JSpinner[]{form_boxFiveCentSpinner}, new JLabel[]{form_fiveCentBoxValue, form_fiveCentBoxAmount}, form_boxPanel,"boxes");
        highlightSelectedValueRow(new JSpinner[]{form_boxTwoCentSpinner}, new JLabel[]{form_twoCentBoxValue, form_twoCentBoxAmount}, form_boxPanel,"boxes");
        highlightSelectedValueRow(new JSpinner[]{form_boxOneCentSpinner}, new JLabel[]{form_oneCentBoxValue, form_oneCentBoxAmount}, form_boxPanel,"boxes");

        highlightSelectedValueRow(new JSpinner[]{form_fullRegisterSpinner}, new JLabel[]{form_fullRegisterLabel}, form_registerPanel,"register");

        highlightSelectedValueRow(twoHundredEuroRow, new JLabel[]{form_twoHundredEuroLabel,form_twohundredEuroLabelAmount,form_twohundredEuroLabelX,form_twohundredEuroLabelValue,form_twohundredEuroLabelSymbol}, form_cashPanel,"cash");
        highlightSelectedValueRow(oneHundredEuroRow, new JLabel[]{form_oneHundredEuroLabel,form_onehundredEuroLabelAmount,form_onehundredEuroLabelX,form_onehundredEuroLabelValue,form_onehundredEuroLabelSymbol}, form_cashPanel,"cash");
        highlightSelectedValueRow(fiftyEuroRow, new JLabel[]{form_fiftyEuroLabel,form_fiftyEuroLabelAmount,form_fiftyEuroLabelX,form_fiftyEuroLabelValue,form_fiftyEuroLabelSymbol}, form_cashPanel,"cash");
        highlightSelectedValueRow(twentyEuroRow, new JLabel[]{form_twentyEuroLabel,form_twentyEuroLabelAmount,form_twentyEuroLabelX,form_twentyEuroLabelValue,form_twentyEuroLabelSymbol}, form_cashPanel,"cash");
        highlightSelectedValueRow(tenEuroRow, new JLabel[]{form_tenEuroLabel,form_tenEuroLabelAmount,form_tenEuroLabelX,form_tenEuroLabelValue,form_tenEuroLabelSymbol}, form_cashPanel,"cash");
        highlightSelectedValueRow(fiveEuroRow, new JLabel[]{form_fiveEuroLabel,form_fiveEuroLabelAmount,form_fiveEuroLabelX,form_fiveEuroLabelValue,form_fiveEuroLabelSymbol}, form_cashPanel,"cash");

        highlightSelectedValueRow(twoEuroRow, new JLabel[]{form_twoEuroLabel,form_twoEuroLabelAmount,form_twoEuroLabelX,form_twoEuroLabelValue,form_twoEuroLabelSymbol}, form_cashPanel,"cash");
        highlightSelectedValueRow(OneEuroRow, new JLabel[]{form_oneEuroLabel,form_oneEuroLabelAmount,form_oneEuroLabelX,form_oneEuroLabelValue,form_oneEuroLabelSymbol}, form_cashPanel,"cash");
        highlightSelectedValueRow(fiftyCentRow, new JLabel[]{form_fiftyCentLabel,form_fiftyCentLabelAmount,form_fiftyCentLabelX,form_fiftyCentLabelValue,form_fiftyCentLabelSymbol}, form_cashPanel,"cash");
        highlightSelectedValueRow(twentyCentRow, new JLabel[]{form_twentyCentLabel,form_twentyCentLabelAmount,form_twentyCentLabelX,form_twentyCentLabelValue,form_twentyCentLabelSymbol}, form_cashPanel,"cash");
        highlightSelectedValueRow(tenCentRow, new JLabel[]{form_tenCentLabel,form_tenCentLabelAmount,form_tenCentLabelX,form_tenCentLabelValue,form_tenCentLabelSymbol}, form_cashPanel,"cash");
        highlightSelectedValueRow(fiveCentRow, new JLabel[]{form_fiveCentLabel,form_fiveCentLabelAmount,form_fiveCentLabelX,form_fiveCentLabelValue,form_fiveCentLabelSymbol}, form_cashPanel,"cash");
        highlightSelectedValueRow(twoCentRow, new JLabel[]{form_twoCentLabel,form_twoCentLabelAmount,form_twoCentLabelX,form_twoCentLabelValue,form_twoCentLabelSymbol}, form_cashPanel,"cash");
        highlightSelectedValueRow(oneCentRow, new JLabel[]{form_oneCentLabel,form_oneCentLabelAmount,form_oneCentLabelX,form_oneCentLabelValue,form_oneCentLabelSymbol}, form_cashPanel,"cash");
    }

    private void highlightSelectedValueRow(JSpinner[] spinnerList, JLabel[] labelList, JPanel panelComponent, String borderTitle) {

        Arrays.stream(spinnerList).forEach(spinner -> {
            Component editorComponent = spinner.getEditor().getComponent(0);
            editorComponent.addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent e) {

                    JTextField textField = (JTextField) e.getSource();
                    textField.setText(textField.getText());
                    textField.selectAll();

                    Stream.of(labelList, spinnerList).forEach(component -> {
                        for (JComponent comp : component) {

                            if (comp instanceof JSpinner) {


                                comp.setBackground(new Color(0, 47, 111, 100));
                            } else {

                                comp.setForeground(new Color(110, 181, 255));
                                comp.setFont(new Font(comp.getFont().getName(), Font.BOLD, comp.getFont().getSize()));
                            }
                        }
                    });

                    if (panelComponent != null) {

                        panelComponent.setBorder(new TitledBorder(new LineBorder(new Color(110, 181, 255)), borderTitle, TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {

                    Stream.of(labelList, spinnerList).forEach(component -> {
                        for (JComponent comp : component) {

                            if (comp instanceof JSpinner) {

                                comp.setBackground(new Color(70, 73, 75));
                            } else {

                                comp.setForeground(new Color(187, 187, 187));
                                comp.setFont(new Font(comp.getFont().getName(), Font.PLAIN, comp.getFont().getSize()));
                            }
                        }
                    });


                    if (panelComponent != null) {

                        panelComponent.setBorder(new TitledBorder(new LineBorder(new Color(70,73,75)), borderTitle, TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
                    }
                }
            });
        });
    }


    private void customInit() {

        resetArray = new JSpinner[]{form_fullRegisterSpinner, form_rollTwoCentSpinner, form_rollOneEuroSpinner,
                form_rollFiftyCentSpinner, form_rollTwentyCentSpinner, form_rollTenCentSpinner, form_rollFiveCentSpinner,
                form_rollTwoCentSpinner, form_rollOneCentSpinner, form_boxTwoEuroSpinner, form_boxOneEuroSpinner,
                form_boxFiftyCentSpinner, form_boxTwentyCentSpinner, form_boxTenCentSpinner, form_boxFiveCentSpinner,
                form_boxTwoCentSpinner, form_boxOneCentSpinner, form_spinner2_1, form_spinner1_1, form_spinner050_1, form_spinner020_1,
                form_spinner010_1, form_spinner005_1, form_spinner002_1, form_spinner001_1, form_spinner200_1, form_spinner100_1, form_spinner50_1,
                form_spinner20_1, form_spinner10_1, form_spinner5_1, form_spinner2_2, form_spinner1_2, form_spinner050_2, form_spinner020_2, form_spinner010_2,
                form_spinner005_2, form_spinner002_2, form_spinner001_2, form_spinner200_2, form_spinner100_2, form_spinner50_2, form_spinner20_2,
                form_spinner10_2, form_spinner5_2, form_spinner2_3, form_spinner1_3, form_spinner050_3, form_spinner020_3, form_spinner010_3, form_spinner005_3,
                form_spinner002_3, form_spinner001_3, form_spinner200_3, form_spinner100_3, form_spinner50_3, form_spinner20_3, form_spinner10_3,
                form_spinner5_3, form_spinner2_4, form_spinner1_4, form_spinner050_4, form_spinner020_4, form_spinner010_4, form_spinner005_4,
                form_spinner002_4, form_spinner001_4, form_spinner200_4, form_spinner100_4, form_spinner50_4, form_spinner20_4, form_spinner10_4,
                form_spinner5_4, form_spinner2_5, form_spinner1_5, form_spinner050_5, form_spinner020_5, form_spinner010_5, form_spinner005_5,
                form_spinner002_5, form_spinner001_5, form_spinner200_5, form_spinner100_5, form_spinner50_5, form_spinner20_5, form_spinner10_5,
                form_spinner5_5,
                form_rollTwoEuroSpinner2, form_rollOneEuroSpinner2, form_rollFiftyCentSpinner2, form_rollTwentyCentSpinner2, form_rollTenCentSpinner2,
                form_rollFiveCentSpinner2, form_rollTwoCentSpinner2, form_rollOneCentSpinner2};

        twoEuroRow = new JSpinner[]{form_spinner2_1, form_spinner2_2, form_spinner2_3, form_spinner2_4, form_spinner2_5};
        OneEuroRow = new JSpinner[]{form_spinner1_1, form_spinner1_2, form_spinner1_3, form_spinner1_4, form_spinner1_5};
        fiftyCentRow = new JSpinner[]{form_spinner050_1, form_spinner050_2, form_spinner050_3, form_spinner050_4, form_spinner050_5};
        twentyCentRow = new JSpinner[]{form_spinner020_1, form_spinner020_2, form_spinner020_3, form_spinner020_4, form_spinner020_5};
        tenCentRow = new JSpinner[]{form_spinner010_1, form_spinner010_2, form_spinner010_3, form_spinner010_4, form_spinner010_5};
        fiveCentRow = new JSpinner[]{form_spinner005_1, form_spinner005_2, form_spinner005_3, form_spinner005_4, form_spinner005_5};
        twoCentRow = new JSpinner[]{form_spinner002_1, form_spinner002_2, form_spinner002_3, form_spinner002_4, form_spinner002_5};
        oneCentRow = new JSpinner[]{form_spinner001_1, form_spinner001_2, form_spinner001_3, form_spinner001_4, form_spinner001_5};

        twoHundredEuroRow = new JSpinner[]{form_spinner200_1, form_spinner200_2, form_spinner200_3, form_spinner200_4, form_spinner200_5};
        oneHundredEuroRow = new JSpinner[]{form_spinner100_1, form_spinner100_2, form_spinner100_3, form_spinner100_4, form_spinner100_5};
        fiftyEuroRow = new JSpinner[]{form_spinner50_1, form_spinner50_2, form_spinner50_3, form_spinner50_4, form_spinner50_5};
        twentyEuroRow = new JSpinner[]{form_spinner20_1, form_spinner20_2, form_spinner20_3, form_spinner20_4, form_spinner20_5};
        tenEuroRow = new JSpinner[]{form_spinner10_1, form_spinner10_2, form_spinner10_3, form_spinner10_4, form_spinner10_5};
        fiveEuroRow = new JSpinner[]{form_spinner5_1, form_spinner5_2, form_spinner5_3, form_spinner5_4, form_spinner5_5};
    }

    protected abstract void pdfBtnActionPerformed(ActionEvent e);

    protected abstract void calculateBtnActionPerformed(ActionEvent e);

    protected abstract void resetBtnActionPerformed(ActionEvent e);

    protected abstract void debitBtnNewActionPerformed(ActionEvent e);

    protected abstract void registerBtnActionPerformed(ActionEvent e);

	protected abstract void settingsBtnActionPerformed(ActionEvent e);

	protected abstract void exitBtnActionPerformed(ActionEvent e);

    protected abstract void dateBtnActionPerformed(ActionEvent e);

    private void initComponents() {

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner non-commercial license
		this.form_menuBar1 = new JMenuBar();
		this.form_menu1 = new JMenu();
		this.form_settingsBtn = new JMenuItem();
		this.form_exitBtn = new JMenuItem();
		this.form_bottomPanel = new JPanel();
		this.form_panel4 = new JPanel();
		this.form_extraChangeIntermediateLabel = new JLabel();
		this.form_intermediateValueCoinsLabel = new JLabel();
		this.form_intermediateValueNotesLabel = new JLabel();
		this.form_panel6 = new JPanel();
		this.form_differenceValueLabel = new JLabel();
		this.form_totalValueLabel = new JLabel();
		this.form_kassensturzErweiterungPanel = new JPanel();
		this.form_rollPanel = new JPanel();
		this.form_panel37 = new JPanel();
		this.form_twoEuroRollValue = new JLabel();
		this.form_oneEuroRollValue = new JLabel();
		this.form_fiftyCentRollValue = new JLabel();
		this.form_twentyCentRollValue = new JLabel();
		this.form_tenCentRollValue = new JLabel();
		this.form_fiveCentRollValue = new JLabel();
		this.form_twoCentRollValue = new JLabel();
		this.form_oneCentRollValue = new JLabel();
		this.form_panel36 = new JPanel();
		this.form_twoEuroRollAmount = new JLabel();
		this.form_oneEuroRollAmount = new JLabel();
		this.form_fiftyCentRollAmount = new JLabel();
		this.form_twentyCentRollAmount = new JLabel();
		this.form_tenCentRollAmount = new JLabel();
		this.form_fiveCentRollAmount = new JLabel();
		this.form_twoCentRollAmount = new JLabel();
		this.form_oneCentRollAmount = new JLabel();
		this.form_panel20 = new JPanel();
		this.form_rollTwoEuroSpinner = new JSpinner();
		this.form_rollOneEuroSpinner = new JSpinner();
		this.form_rollFiftyCentSpinner = new JSpinner();
		this.form_rollTwentyCentSpinner = new JSpinner();
		this.form_rollTenCentSpinner = new JSpinner();
		this.form_rollFiveCentSpinner = new JSpinner();
		this.form_rollTwoCentSpinner = new JSpinner();
		this.form_rollOneCentSpinner = new JSpinner();
		this.form_panel34 = new JPanel();
		this.form_rollTwoEuroSpinner2 = new JSpinner();
		this.form_rollOneEuroSpinner2 = new JSpinner();
		this.form_rollFiftyCentSpinner2 = new JSpinner();
		this.form_rollTwentyCentSpinner2 = new JSpinner();
		this.form_rollTenCentSpinner2 = new JSpinner();
		this.form_rollFiveCentSpinner2 = new JSpinner();
		this.form_rollTwoCentSpinner2 = new JSpinner();
		this.form_rollOneCentSpinner2 = new JSpinner();
		this.form_boxPanel = new JPanel();
		this.form_panel38 = new JPanel();
		this.form_twoEuroBoxValue = new JLabel();
		this.form_oneEuroBoxValue = new JLabel();
		this.form_fiftyCentBoxValue = new JLabel();
		this.form_twentyCentBoxValue = new JLabel();
		this.form_tenCentBoxValue = new JLabel();
		this.form_fiveCentBoxValue = new JLabel();
		this.form_twoCentBoxValue = new JLabel();
		this.form_oneCentBoxValue = new JLabel();
		this.form_panel39 = new JPanel();
		this.form_twoEuroBoxAmount = new JLabel();
		this.form_oneEuroBoxAmount = new JLabel();
		this.form_fiftyCentBoxAmount = new JLabel();
		this.form_twentyCentBoxAmount = new JLabel();
		this.form_tenCentBoxAmount = new JLabel();
		this.form_fiveCentBoxAmount = new JLabel();
		this.form_twoCentBoxAmount = new JLabel();
		this.form_oneCentBoxAmount = new JLabel();
		this.form_panel40 = new JPanel();
		this.form_boxTwoEuroSpinner = new JSpinner();
		this.form_boxOneEuroSpinner = new JSpinner();
		this.form_boxFiftyCentSpinner = new JSpinner();
		this.form_boxTwentyCentSpinner = new JSpinner();
		this.form_boxTenCentSpinner = new JSpinner();
		this.form_boxFiveCentSpinner = new JSpinner();
		this.form_boxTwoCentSpinner = new JSpinner();
		this.form_boxOneCentSpinner = new JSpinner();
		this.form_cashPanel = new JPanel();
		this.form_valuePanel = new JPanel();
		this.form_twoEuroLabel = new JLabel();
		this.form_oneEuroLabel = new JLabel();
		this.form_fiftyCentLabel = new JLabel();
		this.form_twentyCentLabel = new JLabel();
		this.form_tenCentLabel = new JLabel();
		this.form_fiveCentLabel = new JLabel();
		this.form_twoCentLabel = new JLabel();
		this.form_oneCentLabel = new JLabel();
		this.form_vSpacer1 = new JPanel(null);
		this.form_twoHundredEuroLabel = new JLabel();
		this.form_oneHundredEuroLabel = new JLabel();
		this.form_fiftyEuroLabel = new JLabel();
		this.form_twentyEuroLabel = new JLabel();
		this.form_tenEuroLabel = new JLabel();
		this.form_fiveEuroLabel = new JLabel();
		this.form_firstSpinnerNormal = new JPanel();
		this.form_spinner2_1 = new JSpinner();
		this.form_spinner1_1 = new JSpinner();
		this.form_spinner050_1 = new JSpinner();
		this.form_spinner020_1 = new JSpinner();
		this.form_spinner010_1 = new JSpinner();
		this.form_spinner005_1 = new JSpinner();
		this.form_spinner002_1 = new JSpinner();
		this.form_spinner001_1 = new JSpinner();
		this.form_vSpacer2 = new JPanel(null);
		this.form_spinner200_1 = new JSpinner();
		this.form_spinner100_1 = new JSpinner();
		this.form_spinner50_1 = new JSpinner();
		this.form_spinner20_1 = new JSpinner();
		this.form_spinner10_1 = new JSpinner();
		this.form_spinner5_1 = new JSpinner();
		this.form_secondSpinnerNormal = new JPanel();
		this.form_spinner200_2 = new JSpinner();
		this.form_spinner100_2 = new JSpinner();
		this.form_spinner50_2 = new JSpinner();
		this.form_spinner20_2 = new JSpinner();
		this.form_spinner10_2 = new JSpinner();
		this.form_spinner5_2 = new JSpinner();
		this.form_spinner2_2 = new JSpinner();
		this.form_spinner1_2 = new JSpinner();
		this.form_spinner050_2 = new JSpinner();
		this.form_spinner020_2 = new JSpinner();
		this.form_spinner010_2 = new JSpinner();
		this.form_spinner005_2 = new JSpinner();
		this.form_spinner002_2 = new JSpinner();
		this.form_spinner001_2 = new JSpinner();
		this.form_vSpacer3 = new JPanel(null);
		this.form_thirdSpinnerNormal = new JPanel();
		this.form_spinner2_3 = new JSpinner();
		this.form_spinner1_3 = new JSpinner();
		this.form_spinner050_3 = new JSpinner();
		this.form_spinner020_3 = new JSpinner();
		this.form_spinner010_3 = new JSpinner();
		this.form_spinner005_3 = new JSpinner();
		this.form_spinner002_3 = new JSpinner();
		this.form_spinner001_3 = new JSpinner();
		this.form_vSpacer4 = new JPanel(null);
		this.form_spinner200_3 = new JSpinner();
		this.form_spinner100_3 = new JSpinner();
		this.form_spinner50_3 = new JSpinner();
		this.form_spinner20_3 = new JSpinner();
		this.form_spinner10_3 = new JSpinner();
		this.form_spinner5_3 = new JSpinner();
		this.form_fourthSpinnerNormal = new JPanel();
		this.form_spinner2_4 = new JSpinner();
		this.form_spinner1_4 = new JSpinner();
		this.form_spinner050_4 = new JSpinner();
		this.form_spinner020_4 = new JSpinner();
		this.form_spinner010_4 = new JSpinner();
		this.form_spinner005_4 = new JSpinner();
		this.form_spinner002_4 = new JSpinner();
		this.form_spinner001_4 = new JSpinner();
		this.form_vSpacer5 = new JPanel(null);
		this.form_spinner200_4 = new JSpinner();
		this.form_spinner100_4 = new JSpinner();
		this.form_spinner50_4 = new JSpinner();
		this.form_spinner20_4 = new JSpinner();
		this.form_spinner10_4 = new JSpinner();
		this.form_spinner5_4 = new JSpinner();
		this.form_fifthSpinnerNormal = new JPanel();
		this.form_spinner2_5 = new JSpinner();
		this.form_spinner1_5 = new JSpinner();
		this.form_spinner050_5 = new JSpinner();
		this.form_spinner020_5 = new JSpinner();
		this.form_spinner010_5 = new JSpinner();
		this.form_spinner005_5 = new JSpinner();
		this.form_spinner002_5 = new JSpinner();
		this.form_spinner001_5 = new JSpinner();
		this.form_vSpacer6 = new JPanel(null);
		this.form_spinner200_5 = new JSpinner();
		this.form_spinner100_5 = new JSpinner();
		this.form_spinner50_5 = new JSpinner();
		this.form_spinner20_5 = new JSpinner();
		this.form_spinner10_5 = new JSpinner();
		this.form_spinner5_5 = new JSpinner();
		this.form_amountPanelNormal = new JPanel();
		this.form_panel44 = new JPanel();
		this.form_twoEuroLabelAmount = new JLabel();
		this.form_oneEuroLabelAmount = new JLabel();
		this.form_fiftyCentLabelAmount = new JLabel();
		this.form_twentyCentLabelAmount = new JLabel();
		this.form_tenCentLabelAmount = new JLabel();
		this.form_fiveCentLabelAmount = new JLabel();
		this.form_twoCentLabelAmount = new JLabel();
		this.form_oneCentLabelAmount = new JLabel();
		this.form_vSpacer7 = new JPanel(null);
		this.form_twohundredEuroLabelAmount = new JLabel();
		this.form_onehundredEuroLabelAmount = new JLabel();
		this.form_fiftyEuroLabelAmount = new JLabel();
		this.form_twentyEuroLabelAmount = new JLabel();
		this.form_tenEuroLabelAmount = new JLabel();
		this.form_fiveEuroLabelAmount = new JLabel();
		this.form_panel45 = new JPanel();
		this.form_twoEuroLabelX = new JLabel();
		this.form_oneEuroLabelX = new JLabel();
		this.form_fiftyCentLabelX = new JLabel();
		this.form_twentyCentLabelX = new JLabel();
		this.form_tenCentLabelX = new JLabel();
		this.form_fiveCentLabelX = new JLabel();
		this.form_twoCentLabelX = new JLabel();
		this.form_oneCentLabelX = new JLabel();
		this.form_vSpacer8 = new JPanel(null);
		this.form_twohundredEuroLabelX = new JLabel();
		this.form_onehundredEuroLabelX = new JLabel();
		this.form_fiftyEuroLabelX = new JLabel();
		this.form_twentyEuroLabelX = new JLabel();
		this.form_tenEuroLabelX = new JLabel();
		this.form_fiveEuroLabelX = new JLabel();
		this.form_valueRowPanelNormal = new JPanel();
		this.form_panel46 = new JPanel();
		this.form_twoEuroLabelValue = new JLabel();
		this.form_oneEuroLabelValue = new JLabel();
		this.form_fiftyCentLabelValue = new JLabel();
		this.form_twentyCentLabelValue = new JLabel();
		this.form_tenCentLabelValue = new JLabel();
		this.form_fiveCentLabelValue = new JLabel();
		this.form_twoCentLabelValue = new JLabel();
		this.form_oneCentLabelValue = new JLabel();
		this.form_vSpacer9 = new JPanel(null);
		this.form_twohundredEuroLabelValue = new JLabel();
		this.form_onehundredEuroLabelValue = new JLabel();
		this.form_fiftyEuroLabelValue = new JLabel();
		this.form_twentyEuroLabelValue = new JLabel();
		this.form_tenEuroLabelValue = new JLabel();
		this.form_fiveEuroLabelValue = new JLabel();
		this.form_panel47 = new JPanel();
		this.form_twoEuroLabelSymbol = new JLabel();
		this.form_oneEuroLabelSymbol = new JLabel();
		this.form_fiftyCentLabelSymbol = new JLabel();
		this.form_twentyCentLabelSymbol = new JLabel();
		this.form_tenCentLabelSymbol = new JLabel();
		this.form_fiveCentLabelSymbol = new JLabel();
		this.form_twoCentLabelSymbol = new JLabel();
		this.form_oneCentLabelSymbol = new JLabel();
		this.form_vSpacer10 = new JPanel(null);
		this.form_twohundredEuroLabelSymbol = new JLabel();
		this.form_onehundredEuroLabelSymbol = new JLabel();
		this.form_fiftyEuroLabelSymbol = new JLabel();
		this.form_twentyEuroLabelSymbol = new JLabel();
		this.form_tenEuroLabelSymbol = new JLabel();
		this.form_fiveEuroLabelSymbol = new JLabel();
		this.form_topPanel = new JPanel();
		this.form_registerPanel = new JPanel();
		this.form_fullRegisterSpinner = new JSpinner();
		this.form_fullRegisterLabel = new JLabel();
		this.form_buttonPanel = new JPanel();
		this.form_resetBtn = new JButton();
		this.form_separator1 = new JSeparator();
		this.form_registerBtn = new JButton();
		this.form_pdfBtn = new JButton();
		this.form_ausrechnenBtn2 = new JButton();
		this.form_separator2 = new JSeparator();
		this.form_debitBtnNew = new JButton();
		this.form_separator3 = new JSeparator();
		this.form_dateBtn = new JButton();
		this.form_datePanel = new JPanel();
		this.form_dateSpinner = new JSpinner();
		this.form_debitPanel = new JPanel();
		this.form_debitTextField = new JTextField();
		this.form_label69 = new JLabel();

		//======== this ========
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setResizable(true);
		setTitle("weekly");
		setVisible(true);
		setMinimumSize(null);
		setPreferredSize(null);
		setMaximumSize(null);
		setName("this");
		Container contentPane = getContentPane();
		contentPane.setLayout(new MigLayout(
			null,
			// columns
			"[grow,fill]" +
			"[856:855,grow,fill]",
			// rows
			"[grow,fill]" +
			"[grow,fill]" +
			"[grow,fill]"));

		//======== form_menuBar1 ========
		{
			this.form_menuBar1.setName("menuBar1");

			//======== form_menu1 ========
			{
				this.form_menu1.setText("file");
				this.form_menu1.setName("menu1");

				//---- form_settingsBtn ----
				this.form_settingsBtn.setText("settings");
				this.form_settingsBtn.setName("settingsBtn");
				this.form_settingsBtn.addActionListener(e -> settingsBtnActionPerformed(e));
				this.form_menu1.add(this.form_settingsBtn);
				this.form_menu1.addSeparator();

				//---- form_exitBtn ----
				this.form_exitBtn.setText("exit");
				this.form_exitBtn.setName("exitBtn");
				this.form_exitBtn.addActionListener(e -> exitBtnActionPerformed(e));
				this.form_menu1.add(this.form_exitBtn);
			}
			this.form_menuBar1.add(this.form_menu1);
		}
		setJMenuBar(this.form_menuBar1);

		//======== form_bottomPanel ========
		{
			this.form_bottomPanel.setMinimumSize(null);
			this.form_bottomPanel.setPreferredSize(null);
			this.form_bottomPanel.setMaximumSize(null);
			this.form_bottomPanel.setName("bottomPanel");
			this.form_bottomPanel.setLayout(new MigLayout(
				"insets 0,hidemode 3,gap 0 0",
				// columns
				"[grow,fill]" +
				"[grow,fill]",
				// rows
				"[grow,fill]"));

			//======== form_panel4 ========
			{
				this.form_panel4.setFocusable(false);
				this.form_panel4.setEnabled(false);
				this.form_panel4.setMinimumSize(null);
				this.form_panel4.setPreferredSize(null);
				this.form_panel4.setMaximumSize(null);
				this.form_panel4.setName("panel4");
				this.form_panel4.setLayout(new MigLayout(
					"fill,align center center,wrap 1",
					// columns
					"[]",
					// rows
					"[]"));

				//---- form_extraChangeIntermediateLabel ----
				this.form_extraChangeIntermediateLabel.setText("0 \u20ac");
				this.form_extraChangeIntermediateLabel.setBorder(new TitledBorder("change total"));
				this.form_extraChangeIntermediateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_extraChangeIntermediateLabel.setFocusable(false);
				this.form_extraChangeIntermediateLabel.setInheritsPopupMenu(false);
				this.form_extraChangeIntermediateLabel.setRequestFocusEnabled(false);
				this.form_extraChangeIntermediateLabel.setVerifyInputWhenFocusTarget(false);
				this.form_extraChangeIntermediateLabel.setEnabled(false);
				this.form_extraChangeIntermediateLabel.setFont(this.form_extraChangeIntermediateLabel.getFont().deriveFont(this.form_extraChangeIntermediateLabel.getFont().getSize() + 3f));
				this.form_extraChangeIntermediateLabel.setMinimumSize(null);
				this.form_extraChangeIntermediateLabel.setPreferredSize(null);
				this.form_extraChangeIntermediateLabel.setMaximumSize(null);
				this.form_extraChangeIntermediateLabel.setName("extraChangeIntermediateLabel");
				this.form_panel4.add(this.form_extraChangeIntermediateLabel, "cell 0 0,grow");

				//---- form_intermediateValueCoinsLabel ----
				this.form_intermediateValueCoinsLabel.setText("0,00 \u20ac");
				this.form_intermediateValueCoinsLabel.setBorder(new TitledBorder("coins"));
				this.form_intermediateValueCoinsLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
				this.form_intermediateValueCoinsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_intermediateValueCoinsLabel.setFocusable(false);
				this.form_intermediateValueCoinsLabel.setFont(this.form_intermediateValueCoinsLabel.getFont().deriveFont(this.form_intermediateValueCoinsLabel.getFont().getSize() + 3f));
				this.form_intermediateValueCoinsLabel.setEnabled(false);
				this.form_intermediateValueCoinsLabel.setVerifyInputWhenFocusTarget(false);
				this.form_intermediateValueCoinsLabel.setRequestFocusEnabled(false);
				this.form_intermediateValueCoinsLabel.setInheritsPopupMenu(false);
				this.form_intermediateValueCoinsLabel.setMinimumSize(null);
				this.form_intermediateValueCoinsLabel.setMaximumSize(null);
				this.form_intermediateValueCoinsLabel.setPreferredSize(null);
				this.form_intermediateValueCoinsLabel.setName("intermediateValueCoinsLabel");
				this.form_panel4.add(this.form_intermediateValueCoinsLabel, "cell 0 1,grow");

				//---- form_intermediateValueNotesLabel ----
				this.form_intermediateValueNotesLabel.setText("0,00 \u20ac");
				this.form_intermediateValueNotesLabel.setBorder(new TitledBorder("notes"));
				this.form_intermediateValueNotesLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
				this.form_intermediateValueNotesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_intermediateValueNotesLabel.setFocusable(false);
				this.form_intermediateValueNotesLabel.setFont(this.form_intermediateValueNotesLabel.getFont().deriveFont(this.form_intermediateValueNotesLabel.getFont().getSize() + 3f));
				this.form_intermediateValueNotesLabel.setEnabled(false);
				this.form_intermediateValueNotesLabel.setVerifyInputWhenFocusTarget(false);
				this.form_intermediateValueNotesLabel.setRequestFocusEnabled(false);
				this.form_intermediateValueNotesLabel.setInheritsPopupMenu(false);
				this.form_intermediateValueNotesLabel.setMaximumSize(null);
				this.form_intermediateValueNotesLabel.setMinimumSize(null);
				this.form_intermediateValueNotesLabel.setPreferredSize(null);
				this.form_intermediateValueNotesLabel.setName("intermediateValueNotesLabel");
				this.form_panel4.add(this.form_intermediateValueNotesLabel, "cell 0 2,grow");
			}
			this.form_bottomPanel.add(this.form_panel4, "cell 0 0");

			//======== form_panel6 ========
			{
				this.form_panel6.setFocusable(false);
				this.form_panel6.setEnabled(false);
				this.form_panel6.setPreferredSize(null);
				this.form_panel6.setMinimumSize(null);
				this.form_panel6.setMaximumSize(null);
				this.form_panel6.setName("panel6");
				this.form_panel6.setLayout(new MigLayout(
					"insets 0,hidemode 3,gap 0 0",
					// columns
					"[grow,fill]",
					// rows
					"[grow,fill]"));

				//---- form_differenceValueLabel ----
				this.form_differenceValueLabel.setText("0,00 \u20ac");
				this.form_differenceValueLabel.setBorder(new TitledBorder("DELTA"));
				this.form_differenceValueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_differenceValueLabel.setFont(this.form_differenceValueLabel.getFont().deriveFont(this.form_differenceValueLabel.getFont().getSize() + 10f));
				this.form_differenceValueLabel.setFocusable(false);
				this.form_differenceValueLabel.setInheritsPopupMenu(false);
				this.form_differenceValueLabel.setRequestFocusEnabled(false);
				this.form_differenceValueLabel.setVerifyInputWhenFocusTarget(false);
				this.form_differenceValueLabel.setEnabled(false);
				this.form_differenceValueLabel.setPreferredSize(null);
				this.form_differenceValueLabel.setMinimumSize(null);
				this.form_differenceValueLabel.setMaximumSize(null);
				this.form_differenceValueLabel.setName("differenceValueLabel");
				this.form_panel6.add(this.form_differenceValueLabel, "cell 0 0");

				//---- form_totalValueLabel ----
				this.form_totalValueLabel.setText("0,00 \u20ac");
				this.form_totalValueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_totalValueLabel.setBorder(new TitledBorder("TOTAL"));
				this.form_totalValueLabel.setFont(this.form_totalValueLabel.getFont().deriveFont(this.form_totalValueLabel.getFont().getSize() + 10f));
				this.form_totalValueLabel.setFocusable(false);
				this.form_totalValueLabel.setEnabled(false);
				this.form_totalValueLabel.setInheritsPopupMenu(false);
				this.form_totalValueLabel.setRequestFocusEnabled(false);
				this.form_totalValueLabel.setVerifyInputWhenFocusTarget(false);
				this.form_totalValueLabel.setPreferredSize(null);
				this.form_totalValueLabel.setMinimumSize(null);
				this.form_totalValueLabel.setMaximumSize(null);
				this.form_totalValueLabel.setName("totalValueLabel");
				this.form_panel6.add(this.form_totalValueLabel, "cell 0 1");
			}
			this.form_bottomPanel.add(this.form_panel6, "cell 1 0");
		}
		contentPane.add(this.form_bottomPanel, "cell 1 2");

		//======== form_kassensturzErweiterungPanel ========
		{
			this.form_kassensturzErweiterungPanel.setPreferredSize(null);
			this.form_kassensturzErweiterungPanel.setFont(this.form_kassensturzErweiterungPanel.getFont().deriveFont(this.form_kassensturzErweiterungPanel.getFont().getSize() + 3f));
			this.form_kassensturzErweiterungPanel.setMaximumSize(null);
			this.form_kassensturzErweiterungPanel.setMinimumSize(null);
			this.form_kassensturzErweiterungPanel.setEnabled(false);
			this.form_kassensturzErweiterungPanel.setBorder(null);
			this.form_kassensturzErweiterungPanel.setName("kassensturzErweiterungPanel");
			this.form_kassensturzErweiterungPanel.setLayout(new MigLayout(
				"fill",
				// columns
				"[fill]",
				// rows
				"[fill]" +
				"[grow,fill]"));

			//======== form_rollPanel ========
			{
				this.form_rollPanel.setBorder(new TitledBorder("rolls"));
				this.form_rollPanel.setName("rollPanel");
				this.form_rollPanel.setLayout(new MigLayout(
					"fill",
					// columns
					"[]" +
					"[]" +
					"[fill]" +
					"[]",
					// rows
					"[center]"));

				//======== form_panel37 ========
				{
					this.form_panel37.setName("panel37");
					this.form_panel37.setLayout(new GridBagLayout());
					((GridBagLayout)this.form_panel37.getLayout()).columnWidths = new int[] {0, 0};
					((GridBagLayout)this.form_panel37.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
					((GridBagLayout)this.form_panel37.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
					((GridBagLayout)this.form_panel37.getLayout()).rowWeights = new double[] {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0E-4};

					//---- form_twoEuroRollValue ----
					this.form_twoEuroRollValue.setText("2,00 \u20ac");
					this.form_twoEuroRollValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_twoEuroRollValue.setFont(this.form_twoEuroRollValue.getFont().deriveFont(this.form_twoEuroRollValue.getFont().getSize() + 3f));
					this.form_twoEuroRollValue.setName("twoEuroRollValue");
					this.form_panel37.add(this.form_twoEuroRollValue, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
						new Insets(0, 0, 0, 0), 0, 0));

					//---- form_oneEuroRollValue ----
					this.form_oneEuroRollValue.setText("1,00 \u20ac");
					this.form_oneEuroRollValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_oneEuroRollValue.setFont(this.form_oneEuroRollValue.getFont().deriveFont(this.form_oneEuroRollValue.getFont().getSize() + 3f));
					this.form_oneEuroRollValue.setName("oneEuroRollValue");
					this.form_panel37.add(this.form_oneEuroRollValue, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
						new Insets(0, 0, 0, 0), 0, 0));

					//---- form_fiftyCentRollValue ----
					this.form_fiftyCentRollValue.setText("0,50 \u20ac");
					this.form_fiftyCentRollValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_fiftyCentRollValue.setFont(this.form_fiftyCentRollValue.getFont().deriveFont(this.form_fiftyCentRollValue.getFont().getSize() + 3f));
					this.form_fiftyCentRollValue.setName("fiftyCentRollValue");
					this.form_panel37.add(this.form_fiftyCentRollValue, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
						new Insets(0, 0, 0, 0), 0, 0));

					//---- form_twentyCentRollValue ----
					this.form_twentyCentRollValue.setText("0,20 \u20ac");
					this.form_twentyCentRollValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_twentyCentRollValue.setFont(this.form_twentyCentRollValue.getFont().deriveFont(this.form_twentyCentRollValue.getFont().getSize() + 3f));
					this.form_twentyCentRollValue.setName("twentyCentRollValue");
					this.form_panel37.add(this.form_twentyCentRollValue, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
						new Insets(0, 0, 0, 0), 0, 0));

					//---- form_tenCentRollValue ----
					this.form_tenCentRollValue.setText("0,10 \u20ac");
					this.form_tenCentRollValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_tenCentRollValue.setFont(this.form_tenCentRollValue.getFont().deriveFont(this.form_tenCentRollValue.getFont().getSize() + 3f));
					this.form_tenCentRollValue.setName("tenCentRollValue");
					this.form_panel37.add(this.form_tenCentRollValue, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
						new Insets(0, 0, 0, 0), 0, 0));

					//---- form_fiveCentRollValue ----
					this.form_fiveCentRollValue.setText("0,05 \u20ac");
					this.form_fiveCentRollValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_fiveCentRollValue.setFont(this.form_fiveCentRollValue.getFont().deriveFont(this.form_fiveCentRollValue.getFont().getSize() + 3f));
					this.form_fiveCentRollValue.setName("fiveCentRollValue");
					this.form_panel37.add(this.form_fiveCentRollValue, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
						new Insets(0, 0, 0, 0), 0, 0));

					//---- form_twoCentRollValue ----
					this.form_twoCentRollValue.setText("0,02 \u20ac");
					this.form_twoCentRollValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_twoCentRollValue.setFont(this.form_twoCentRollValue.getFont().deriveFont(this.form_twoCentRollValue.getFont().getSize() + 3f));
					this.form_twoCentRollValue.setName("twoCentRollValue");
					this.form_panel37.add(this.form_twoCentRollValue, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
						new Insets(0, 0, 0, 0), 0, 0));

					//---- form_oneCentRollValue ----
					this.form_oneCentRollValue.setText("0,01 \u20ac");
					this.form_oneCentRollValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_oneCentRollValue.setFont(this.form_oneCentRollValue.getFont().deriveFont(this.form_oneCentRollValue.getFont().getSize() + 3f));
					this.form_oneCentRollValue.setName("oneCentRollValue");
					this.form_panel37.add(this.form_oneCentRollValue, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
						new Insets(0, 0, 0, 0), 0, 0));
				}
				this.form_rollPanel.add(this.form_panel37, "cell 0 0,grow");

				//======== form_panel36 ========
				{
					this.form_panel36.setName("panel36");
					this.form_panel36.setLayout(new GridBagLayout());
					((GridBagLayout)this.form_panel36.getLayout()).columnWidths = new int[] {0, 0};
					((GridBagLayout)this.form_panel36.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
					((GridBagLayout)this.form_panel36.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
					((GridBagLayout)this.form_panel36.getLayout()).rowWeights = new double[] {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0E-4};

					//---- form_twoEuroRollAmount ----
					this.form_twoEuroRollAmount.setText("25x");
					this.form_twoEuroRollAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_twoEuroRollAmount.setFont(this.form_twoEuroRollAmount.getFont().deriveFont(this.form_twoEuroRollAmount.getFont().getSize() + 3f));
					this.form_twoEuroRollAmount.setName("twoEuroRollAmount");
					this.form_panel36.add(this.form_twoEuroRollAmount, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
						new Insets(0, 0, 0, 0), 0, 0));

					//---- form_oneEuroRollAmount ----
					this.form_oneEuroRollAmount.setText("25x");
					this.form_oneEuroRollAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_oneEuroRollAmount.setFont(this.form_oneEuroRollAmount.getFont().deriveFont(this.form_oneEuroRollAmount.getFont().getSize() + 3f));
					this.form_oneEuroRollAmount.setName("oneEuroRollAmount");
					this.form_panel36.add(this.form_oneEuroRollAmount, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
						new Insets(0, 0, 0, 0), 0, 0));

					//---- form_fiftyCentRollAmount ----
					this.form_fiftyCentRollAmount.setText("40x");
					this.form_fiftyCentRollAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_fiftyCentRollAmount.setFont(this.form_fiftyCentRollAmount.getFont().deriveFont(this.form_fiftyCentRollAmount.getFont().getSize() + 3f));
					this.form_fiftyCentRollAmount.setName("fiftyCentRollAmount");
					this.form_panel36.add(this.form_fiftyCentRollAmount, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
						new Insets(0, 0, 0, 0), 0, 0));

					//---- form_twentyCentRollAmount ----
					this.form_twentyCentRollAmount.setText("40x");
					this.form_twentyCentRollAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_twentyCentRollAmount.setFont(this.form_twentyCentRollAmount.getFont().deriveFont(this.form_twentyCentRollAmount.getFont().getSize() + 3f));
					this.form_twentyCentRollAmount.setName("twentyCentRollAmount");
					this.form_panel36.add(this.form_twentyCentRollAmount, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
						new Insets(0, 0, 0, 0), 0, 0));

					//---- form_tenCentRollAmount ----
					this.form_tenCentRollAmount.setText("40x");
					this.form_tenCentRollAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_tenCentRollAmount.setFont(this.form_tenCentRollAmount.getFont().deriveFont(this.form_tenCentRollAmount.getFont().getSize() + 3f));
					this.form_tenCentRollAmount.setName("tenCentRollAmount");
					this.form_panel36.add(this.form_tenCentRollAmount, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
						new Insets(0, 0, 0, 0), 0, 0));

					//---- form_fiveCentRollAmount ----
					this.form_fiveCentRollAmount.setText("50x");
					this.form_fiveCentRollAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_fiveCentRollAmount.setFont(this.form_fiveCentRollAmount.getFont().deriveFont(this.form_fiveCentRollAmount.getFont().getSize() + 3f));
					this.form_fiveCentRollAmount.setName("fiveCentRollAmount");
					this.form_panel36.add(this.form_fiveCentRollAmount, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
						new Insets(0, 0, 0, 0), 0, 0));

					//---- form_twoCentRollAmount ----
					this.form_twoCentRollAmount.setText("50x");
					this.form_twoCentRollAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_twoCentRollAmount.setFont(this.form_twoCentRollAmount.getFont().deriveFont(this.form_twoCentRollAmount.getFont().getSize() + 3f));
					this.form_twoCentRollAmount.setName("twoCentRollAmount");
					this.form_panel36.add(this.form_twoCentRollAmount, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
						new Insets(0, 0, 0, 0), 0, 0));

					//---- form_oneCentRollAmount ----
					this.form_oneCentRollAmount.setText("50x");
					this.form_oneCentRollAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_oneCentRollAmount.setFont(this.form_oneCentRollAmount.getFont().deriveFont(this.form_oneCentRollAmount.getFont().getSize() + 3f));
					this.form_oneCentRollAmount.setName("oneCentRollAmount");
					this.form_panel36.add(this.form_oneCentRollAmount, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
						new Insets(0, 0, 0, 0), 0, 0));
				}
				this.form_rollPanel.add(this.form_panel36, "cell 1 0,grow");

				//======== form_panel20 ========
				{
					this.form_panel20.setMinimumSize(null);
					this.form_panel20.setPreferredSize(null);
					this.form_panel20.setName("panel20");
					this.form_panel20.setLayout(new MigLayout(
						"fill,alignx center",
						// columns
						"[fill]",
						// rows
						null));

					//---- form_rollTwoEuroSpinner ----
					this.form_rollTwoEuroSpinner.setFont(this.form_rollTwoEuroSpinner.getFont().deriveFont(this.form_rollTwoEuroSpinner.getFont().getSize() + 3f));
					this.form_rollTwoEuroSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
					this.form_rollTwoEuroSpinner.setPreferredSize(new Dimension(68, 34));
					this.form_rollTwoEuroSpinner.setMinimumSize(new Dimension(68, 34));
					this.form_rollTwoEuroSpinner.setName("rollTwoEuroSpinner");
					this.form_panel20.add(this.form_rollTwoEuroSpinner, "cell 0 0");

					//---- form_rollOneEuroSpinner ----
					this.form_rollOneEuroSpinner.setFont(this.form_rollOneEuroSpinner.getFont().deriveFont(this.form_rollOneEuroSpinner.getFont().getSize() + 3f));
					this.form_rollOneEuroSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
					this.form_rollOneEuroSpinner.setPreferredSize(new Dimension(68, 34));
					this.form_rollOneEuroSpinner.setMinimumSize(new Dimension(68, 34));
					this.form_rollOneEuroSpinner.setName("rollOneEuroSpinner");
					this.form_panel20.add(this.form_rollOneEuroSpinner, "cell 0 1");

					//---- form_rollFiftyCentSpinner ----
					this.form_rollFiftyCentSpinner.setFont(this.form_rollFiftyCentSpinner.getFont().deriveFont(this.form_rollFiftyCentSpinner.getFont().getSize() + 3f));
					this.form_rollFiftyCentSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
					this.form_rollFiftyCentSpinner.setPreferredSize(new Dimension(68, 34));
					this.form_rollFiftyCentSpinner.setMinimumSize(new Dimension(68, 34));
					this.form_rollFiftyCentSpinner.setName("rollFiftyCentSpinner");
					this.form_panel20.add(this.form_rollFiftyCentSpinner, "cell 0 2");

					//---- form_rollTwentyCentSpinner ----
					this.form_rollTwentyCentSpinner.setFont(this.form_rollTwentyCentSpinner.getFont().deriveFont(this.form_rollTwentyCentSpinner.getFont().getSize() + 3f));
					this.form_rollTwentyCentSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
					this.form_rollTwentyCentSpinner.setPreferredSize(new Dimension(68, 34));
					this.form_rollTwentyCentSpinner.setMinimumSize(new Dimension(68, 34));
					this.form_rollTwentyCentSpinner.setName("rollTwentyCentSpinner");
					this.form_panel20.add(this.form_rollTwentyCentSpinner, "cell 0 3");

					//---- form_rollTenCentSpinner ----
					this.form_rollTenCentSpinner.setFont(this.form_rollTenCentSpinner.getFont().deriveFont(this.form_rollTenCentSpinner.getFont().getSize() + 3f));
					this.form_rollTenCentSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
					this.form_rollTenCentSpinner.setPreferredSize(new Dimension(68, 34));
					this.form_rollTenCentSpinner.setMinimumSize(new Dimension(68, 34));
					this.form_rollTenCentSpinner.setName("rollTenCentSpinner");
					this.form_panel20.add(this.form_rollTenCentSpinner, "cell 0 4");

					//---- form_rollFiveCentSpinner ----
					this.form_rollFiveCentSpinner.setFont(this.form_rollFiveCentSpinner.getFont().deriveFont(this.form_rollFiveCentSpinner.getFont().getSize() + 3f));
					this.form_rollFiveCentSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
					this.form_rollFiveCentSpinner.setPreferredSize(new Dimension(68, 34));
					this.form_rollFiveCentSpinner.setMinimumSize(new Dimension(68, 34));
					this.form_rollFiveCentSpinner.setName("rollFiveCentSpinner");
					this.form_panel20.add(this.form_rollFiveCentSpinner, "cell 0 5");

					//---- form_rollTwoCentSpinner ----
					this.form_rollTwoCentSpinner.setFont(this.form_rollTwoCentSpinner.getFont().deriveFont(this.form_rollTwoCentSpinner.getFont().getSize() + 3f));
					this.form_rollTwoCentSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
					this.form_rollTwoCentSpinner.setPreferredSize(new Dimension(68, 34));
					this.form_rollTwoCentSpinner.setMinimumSize(new Dimension(68, 34));
					this.form_rollTwoCentSpinner.setName("rollTwoCentSpinner");
					this.form_panel20.add(this.form_rollTwoCentSpinner, "cell 0 6");

					//---- form_rollOneCentSpinner ----
					this.form_rollOneCentSpinner.setFont(this.form_rollOneCentSpinner.getFont().deriveFont(this.form_rollOneCentSpinner.getFont().getSize() + 3f));
					this.form_rollOneCentSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
					this.form_rollOneCentSpinner.setPreferredSize(new Dimension(68, 34));
					this.form_rollOneCentSpinner.setMinimumSize(new Dimension(68, 34));
					this.form_rollOneCentSpinner.setName("rollOneCentSpinner");
					this.form_panel20.add(this.form_rollOneCentSpinner, "cell 0 7");
				}
				this.form_rollPanel.add(this.form_panel20, "cell 2 0,aligny top,growy 0");

				//======== form_panel34 ========
				{
					this.form_panel34.setMinimumSize(null);
					this.form_panel34.setPreferredSize(null);
					this.form_panel34.setName("panel34");
					this.form_panel34.setLayout(new MigLayout(
						"fill,alignx center",
						// columns
						"[grow,fill]",
						// rows
						null));

					//---- form_rollTwoEuroSpinner2 ----
					this.form_rollTwoEuroSpinner2.setFont(this.form_rollTwoEuroSpinner2.getFont().deriveFont(this.form_rollTwoEuroSpinner2.getFont().getSize() + 3f));
					this.form_rollTwoEuroSpinner2.setModel(new SpinnerNumberModel(0, 0, null, 1));
					this.form_rollTwoEuroSpinner2.setMinimumSize(new Dimension(68, 34));
					this.form_rollTwoEuroSpinner2.setPreferredSize(new Dimension(68, 34));
					this.form_rollTwoEuroSpinner2.setName("rollTwoEuroSpinner2");
					this.form_panel34.add(this.form_rollTwoEuroSpinner2, "cell 0 0");

					//---- form_rollOneEuroSpinner2 ----
					this.form_rollOneEuroSpinner2.setFont(this.form_rollOneEuroSpinner2.getFont().deriveFont(this.form_rollOneEuroSpinner2.getFont().getSize() + 3f));
					this.form_rollOneEuroSpinner2.setModel(new SpinnerNumberModel(0, 0, null, 1));
					this.form_rollOneEuroSpinner2.setMinimumSize(new Dimension(68, 34));
					this.form_rollOneEuroSpinner2.setPreferredSize(new Dimension(68, 34));
					this.form_rollOneEuroSpinner2.setName("rollOneEuroSpinner2");
					this.form_panel34.add(this.form_rollOneEuroSpinner2, "cell 0 1");

					//---- form_rollFiftyCentSpinner2 ----
					this.form_rollFiftyCentSpinner2.setFont(this.form_rollFiftyCentSpinner2.getFont().deriveFont(this.form_rollFiftyCentSpinner2.getFont().getSize() + 3f));
					this.form_rollFiftyCentSpinner2.setModel(new SpinnerNumberModel(0, 0, null, 1));
					this.form_rollFiftyCentSpinner2.setMinimumSize(new Dimension(68, 34));
					this.form_rollFiftyCentSpinner2.setPreferredSize(new Dimension(68, 34));
					this.form_rollFiftyCentSpinner2.setName("rollFiftyCentSpinner2");
					this.form_panel34.add(this.form_rollFiftyCentSpinner2, "cell 0 2");

					//---- form_rollTwentyCentSpinner2 ----
					this.form_rollTwentyCentSpinner2.setFont(this.form_rollTwentyCentSpinner2.getFont().deriveFont(this.form_rollTwentyCentSpinner2.getFont().getSize() + 3f));
					this.form_rollTwentyCentSpinner2.setModel(new SpinnerNumberModel(0, 0, null, 1));
					this.form_rollTwentyCentSpinner2.setMinimumSize(new Dimension(68, 34));
					this.form_rollTwentyCentSpinner2.setPreferredSize(new Dimension(68, 34));
					this.form_rollTwentyCentSpinner2.setName("rollTwentyCentSpinner2");
					this.form_panel34.add(this.form_rollTwentyCentSpinner2, "cell 0 3");

					//---- form_rollTenCentSpinner2 ----
					this.form_rollTenCentSpinner2.setFont(this.form_rollTenCentSpinner2.getFont().deriveFont(this.form_rollTenCentSpinner2.getFont().getSize() + 3f));
					this.form_rollTenCentSpinner2.setModel(new SpinnerNumberModel(0, 0, null, 1));
					this.form_rollTenCentSpinner2.setMinimumSize(new Dimension(68, 34));
					this.form_rollTenCentSpinner2.setPreferredSize(new Dimension(68, 34));
					this.form_rollTenCentSpinner2.setName("rollTenCentSpinner2");
					this.form_panel34.add(this.form_rollTenCentSpinner2, "cell 0 4,growx");

					//---- form_rollFiveCentSpinner2 ----
					this.form_rollFiveCentSpinner2.setFont(this.form_rollFiveCentSpinner2.getFont().deriveFont(this.form_rollFiveCentSpinner2.getFont().getSize() + 3f));
					this.form_rollFiveCentSpinner2.setModel(new SpinnerNumberModel(0, 0, null, 1));
					this.form_rollFiveCentSpinner2.setMinimumSize(new Dimension(68, 34));
					this.form_rollFiveCentSpinner2.setPreferredSize(new Dimension(68, 34));
					this.form_rollFiveCentSpinner2.setName("rollFiveCentSpinner2");
					this.form_panel34.add(this.form_rollFiveCentSpinner2, "cell 0 5");

					//---- form_rollTwoCentSpinner2 ----
					this.form_rollTwoCentSpinner2.setFont(this.form_rollTwoCentSpinner2.getFont().deriveFont(this.form_rollTwoCentSpinner2.getFont().getSize() + 3f));
					this.form_rollTwoCentSpinner2.setModel(new SpinnerNumberModel(0, 0, null, 1));
					this.form_rollTwoCentSpinner2.setMinimumSize(new Dimension(68, 34));
					this.form_rollTwoCentSpinner2.setPreferredSize(new Dimension(68, 34));
					this.form_rollTwoCentSpinner2.setName("rollTwoCentSpinner2");
					this.form_panel34.add(this.form_rollTwoCentSpinner2, "cell 0 6");

					//---- form_rollOneCentSpinner2 ----
					this.form_rollOneCentSpinner2.setFont(this.form_rollOneCentSpinner2.getFont().deriveFont(this.form_rollOneCentSpinner2.getFont().getSize() + 3f));
					this.form_rollOneCentSpinner2.setModel(new SpinnerNumberModel(0, 0, null, 1));
					this.form_rollOneCentSpinner2.setMinimumSize(new Dimension(68, 34));
					this.form_rollOneCentSpinner2.setPreferredSize(new Dimension(68, 34));
					this.form_rollOneCentSpinner2.setName("rollOneCentSpinner2");
					this.form_panel34.add(this.form_rollOneCentSpinner2, "cell 0 7");
				}
				this.form_rollPanel.add(this.form_panel34, "cell 3 0,grow");
			}
			this.form_kassensturzErweiterungPanel.add(this.form_rollPanel, "cell 0 0,grow");

			//======== form_boxPanel ========
			{
				this.form_boxPanel.setBorder(new TitledBorder("boxes"));
				this.form_boxPanel.setName("boxPanel");
				this.form_boxPanel.setLayout(new MigLayout(
					"fill",
					// columns
					"[grow,trailing]" +
					"[grow]" +
					"[grow]",
					// rows
					"[center]"));

				//======== form_panel38 ========
				{
					this.form_panel38.setName("panel38");
					this.form_panel38.setLayout(new MigLayout(
						"fill",
						// columns
						"[fill]",
						// rows
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]"));

					//---- form_twoEuroBoxValue ----
					this.form_twoEuroBoxValue.setText("2,00 \u20ac");
					this.form_twoEuroBoxValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_twoEuroBoxValue.setFont(this.form_twoEuroBoxValue.getFont().deriveFont(this.form_twoEuroBoxValue.getFont().getSize() + 3f));
					this.form_twoEuroBoxValue.setName("twoEuroBoxValue");
					this.form_panel38.add(this.form_twoEuroBoxValue, "cell 0 0");

					//---- form_oneEuroBoxValue ----
					this.form_oneEuroBoxValue.setText("1,00 \u20ac");
					this.form_oneEuroBoxValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_oneEuroBoxValue.setFont(this.form_oneEuroBoxValue.getFont().deriveFont(this.form_oneEuroBoxValue.getFont().getSize() + 3f));
					this.form_oneEuroBoxValue.setName("oneEuroBoxValue");
					this.form_panel38.add(this.form_oneEuroBoxValue, "cell 0 1");

					//---- form_fiftyCentBoxValue ----
					this.form_fiftyCentBoxValue.setText("0,50 \u20ac");
					this.form_fiftyCentBoxValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_fiftyCentBoxValue.setFont(this.form_fiftyCentBoxValue.getFont().deriveFont(this.form_fiftyCentBoxValue.getFont().getSize() + 3f));
					this.form_fiftyCentBoxValue.setName("fiftyCentBoxValue");
					this.form_panel38.add(this.form_fiftyCentBoxValue, "cell 0 2");

					//---- form_twentyCentBoxValue ----
					this.form_twentyCentBoxValue.setText("0,20 \u20ac");
					this.form_twentyCentBoxValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_twentyCentBoxValue.setFont(this.form_twentyCentBoxValue.getFont().deriveFont(this.form_twentyCentBoxValue.getFont().getSize() + 3f));
					this.form_twentyCentBoxValue.setName("twentyCentBoxValue");
					this.form_panel38.add(this.form_twentyCentBoxValue, "cell 0 3");

					//---- form_tenCentBoxValue ----
					this.form_tenCentBoxValue.setText("0,10 \u20ac");
					this.form_tenCentBoxValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_tenCentBoxValue.setFont(this.form_tenCentBoxValue.getFont().deriveFont(this.form_tenCentBoxValue.getFont().getSize() + 3f));
					this.form_tenCentBoxValue.setName("tenCentBoxValue");
					this.form_panel38.add(this.form_tenCentBoxValue, "cell 0 4");

					//---- form_fiveCentBoxValue ----
					this.form_fiveCentBoxValue.setText("0,05 \u20ac");
					this.form_fiveCentBoxValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_fiveCentBoxValue.setFont(this.form_fiveCentBoxValue.getFont().deriveFont(this.form_fiveCentBoxValue.getFont().getSize() + 3f));
					this.form_fiveCentBoxValue.setName("fiveCentBoxValue");
					this.form_panel38.add(this.form_fiveCentBoxValue, "cell 0 5");

					//---- form_twoCentBoxValue ----
					this.form_twoCentBoxValue.setText("0,02 \u20ac");
					this.form_twoCentBoxValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_twoCentBoxValue.setFont(this.form_twoCentBoxValue.getFont().deriveFont(this.form_twoCentBoxValue.getFont().getSize() + 3f));
					this.form_twoCentBoxValue.setName("twoCentBoxValue");
					this.form_panel38.add(this.form_twoCentBoxValue, "cell 0 6");

					//---- form_oneCentBoxValue ----
					this.form_oneCentBoxValue.setText("0,01 \u20ac");
					this.form_oneCentBoxValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_oneCentBoxValue.setFont(this.form_oneCentBoxValue.getFont().deriveFont(this.form_oneCentBoxValue.getFont().getSize() + 3f));
					this.form_oneCentBoxValue.setName("oneCentBoxValue");
					this.form_panel38.add(this.form_oneCentBoxValue, "cell 0 7");
				}
				this.form_boxPanel.add(this.form_panel38, "cell 0 0,grow");

				//======== form_panel39 ========
				{
					this.form_panel39.setName("panel39");
					this.form_panel39.setLayout(new MigLayout(
						"fill",
						// columns
						"[center]",
						// rows
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]"));

					//---- form_twoEuroBoxAmount ----
					this.form_twoEuroBoxAmount.setText("5 rolls");
					this.form_twoEuroBoxAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_twoEuroBoxAmount.setFont(this.form_twoEuroBoxAmount.getFont().deriveFont(this.form_twoEuroBoxAmount.getFont().getSize() + 3f));
					this.form_twoEuroBoxAmount.setName("twoEuroBoxAmount");
					this.form_panel39.add(this.form_twoEuroBoxAmount, "cell 0 0");

					//---- form_oneEuroBoxAmount ----
					this.form_oneEuroBoxAmount.setText("5 rolls");
					this.form_oneEuroBoxAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_oneEuroBoxAmount.setFont(this.form_oneEuroBoxAmount.getFont().deriveFont(this.form_oneEuroBoxAmount.getFont().getSize() + 3f));
					this.form_oneEuroBoxAmount.setName("oneEuroBoxAmount");
					this.form_panel39.add(this.form_oneEuroBoxAmount, "cell 0 1");

					//---- form_fiftyCentBoxAmount ----
					this.form_fiftyCentBoxAmount.setText("3 rolls");
					this.form_fiftyCentBoxAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_fiftyCentBoxAmount.setFont(this.form_fiftyCentBoxAmount.getFont().deriveFont(this.form_fiftyCentBoxAmount.getFont().getSize() + 3f));
					this.form_fiftyCentBoxAmount.setName("fiftyCentBoxAmount");
					this.form_panel39.add(this.form_fiftyCentBoxAmount, "cell 0 2");

					//---- form_twentyCentBoxAmount ----
					this.form_twentyCentBoxAmount.setText("3 rolls");
					this.form_twentyCentBoxAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_twentyCentBoxAmount.setFont(this.form_twentyCentBoxAmount.getFont().deriveFont(this.form_twentyCentBoxAmount.getFont().getSize() + 3f));
					this.form_twentyCentBoxAmount.setName("twentyCentBoxAmount");
					this.form_panel39.add(this.form_twentyCentBoxAmount, "cell 0 3");

					//---- form_tenCentBoxAmount ----
					this.form_tenCentBoxAmount.setText("3 rolls");
					this.form_tenCentBoxAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_tenCentBoxAmount.setFont(this.form_tenCentBoxAmount.getFont().deriveFont(this.form_tenCentBoxAmount.getFont().getSize() + 3f));
					this.form_tenCentBoxAmount.setName("tenCentBoxAmount");
					this.form_panel39.add(this.form_tenCentBoxAmount, "cell 0 4");

					//---- form_fiveCentBoxAmount ----
					this.form_fiveCentBoxAmount.setText("3 rolls");
					this.form_fiveCentBoxAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_fiveCentBoxAmount.setFont(this.form_fiveCentBoxAmount.getFont().deriveFont(this.form_fiveCentBoxAmount.getFont().getSize() + 3f));
					this.form_fiveCentBoxAmount.setName("fiveCentBoxAmount");
					this.form_panel39.add(this.form_fiveCentBoxAmount, "cell 0 5");

					//---- form_twoCentBoxAmount ----
					this.form_twoCentBoxAmount.setText("5 rolls");
					this.form_twoCentBoxAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_twoCentBoxAmount.setFont(this.form_twoCentBoxAmount.getFont().deriveFont(this.form_twoCentBoxAmount.getFont().getSize() + 3f));
					this.form_twoCentBoxAmount.setName("twoCentBoxAmount");
					this.form_panel39.add(this.form_twoCentBoxAmount, "cell 0 6");

					//---- form_oneCentBoxAmount ----
					this.form_oneCentBoxAmount.setText("5 rolls");
					this.form_oneCentBoxAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_oneCentBoxAmount.setFont(this.form_oneCentBoxAmount.getFont().deriveFont(this.form_oneCentBoxAmount.getFont().getSize() + 3f));
					this.form_oneCentBoxAmount.setName("oneCentBoxAmount");
					this.form_panel39.add(this.form_oneCentBoxAmount, "cell 0 7");
				}
				this.form_boxPanel.add(this.form_panel39, "cell 1 0,grow");

				//======== form_panel40 ========
				{
					this.form_panel40.setName("panel40");
					this.form_panel40.setLayout(new MigLayout(
						"fill,alignx center",
						// columns
						"[grow,fill]",
						// rows
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]"));

					//---- form_boxTwoEuroSpinner ----
					this.form_boxTwoEuroSpinner.setFont(this.form_boxTwoEuroSpinner.getFont().deriveFont(this.form_boxTwoEuroSpinner.getFont().getSize() + 3f));
					this.form_boxTwoEuroSpinner.setModel(new SpinnerNumberModel(2, 0, 2, 1));
					this.form_boxTwoEuroSpinner.setPreferredSize(new Dimension(68, 34));
					this.form_boxTwoEuroSpinner.setMinimumSize(new Dimension(68, 34));
					this.form_boxTwoEuroSpinner.setName("boxTwoEuroSpinner");
					this.form_panel40.add(this.form_boxTwoEuroSpinner, "cell 0 0,aligny center,growy 0");

					//---- form_boxOneEuroSpinner ----
					this.form_boxOneEuroSpinner.setFont(this.form_boxOneEuroSpinner.getFont().deriveFont(this.form_boxOneEuroSpinner.getFont().getSize() + 3f));
					this.form_boxOneEuroSpinner.setModel(new SpinnerNumberModel(2, 0, 2, 1));
					this.form_boxOneEuroSpinner.setPreferredSize(new Dimension(68, 34));
					this.form_boxOneEuroSpinner.setMinimumSize(new Dimension(68, 34));
					this.form_boxOneEuroSpinner.setName("boxOneEuroSpinner");
					this.form_panel40.add(this.form_boxOneEuroSpinner, "cell 0 1,aligny center,growy 0");

					//---- form_boxFiftyCentSpinner ----
					this.form_boxFiftyCentSpinner.setFont(this.form_boxFiftyCentSpinner.getFont().deriveFont(this.form_boxFiftyCentSpinner.getFont().getSize() + 3f));
					this.form_boxFiftyCentSpinner.setModel(new SpinnerNumberModel(2, 0, 2, 1));
					this.form_boxFiftyCentSpinner.setPreferredSize(new Dimension(68, 34));
					this.form_boxFiftyCentSpinner.setMinimumSize(new Dimension(68, 34));
					this.form_boxFiftyCentSpinner.setName("boxFiftyCentSpinner");
					this.form_panel40.add(this.form_boxFiftyCentSpinner, "cell 0 2,aligny center,growy 0");

					//---- form_boxTwentyCentSpinner ----
					this.form_boxTwentyCentSpinner.setFont(this.form_boxTwentyCentSpinner.getFont().deriveFont(this.form_boxTwentyCentSpinner.getFont().getSize() + 3f));
					this.form_boxTwentyCentSpinner.setModel(new SpinnerNumberModel(2, 0, 2, 1));
					this.form_boxTwentyCentSpinner.setPreferredSize(new Dimension(68, 34));
					this.form_boxTwentyCentSpinner.setMinimumSize(new Dimension(68, 34));
					this.form_boxTwentyCentSpinner.setName("boxTwentyCentSpinner");
					this.form_panel40.add(this.form_boxTwentyCentSpinner, "cell 0 3,aligny center,growy 0");

					//---- form_boxTenCentSpinner ----
					this.form_boxTenCentSpinner.setFont(this.form_boxTenCentSpinner.getFont().deriveFont(this.form_boxTenCentSpinner.getFont().getSize() + 3f));
					this.form_boxTenCentSpinner.setModel(new SpinnerNumberModel(2, 0, 2, 1));
					this.form_boxTenCentSpinner.setPreferredSize(new Dimension(68, 34));
					this.form_boxTenCentSpinner.setMinimumSize(new Dimension(68, 34));
					this.form_boxTenCentSpinner.setName("boxTenCentSpinner");
					this.form_panel40.add(this.form_boxTenCentSpinner, "cell 0 4,aligny center,growy 0");

					//---- form_boxFiveCentSpinner ----
					this.form_boxFiveCentSpinner.setFont(this.form_boxFiveCentSpinner.getFont().deriveFont(this.form_boxFiveCentSpinner.getFont().getSize() + 3f));
					this.form_boxFiveCentSpinner.setModel(new SpinnerNumberModel(2, 0, 2, 1));
					this.form_boxFiveCentSpinner.setPreferredSize(new Dimension(68, 34));
					this.form_boxFiveCentSpinner.setMinimumSize(new Dimension(68, 34));
					this.form_boxFiveCentSpinner.setName("boxFiveCentSpinner");
					this.form_panel40.add(this.form_boxFiveCentSpinner, "cell 0 5,aligny center,growy 0");

					//---- form_boxTwoCentSpinner ----
					this.form_boxTwoCentSpinner.setFont(this.form_boxTwoCentSpinner.getFont().deriveFont(this.form_boxTwoCentSpinner.getFont().getSize() + 3f));
					this.form_boxTwoCentSpinner.setModel(new SpinnerNumberModel(2, 0, 2, 1));
					this.form_boxTwoCentSpinner.setPreferredSize(new Dimension(68, 34));
					this.form_boxTwoCentSpinner.setMinimumSize(new Dimension(68, 34));
					this.form_boxTwoCentSpinner.setName("boxTwoCentSpinner");
					this.form_panel40.add(this.form_boxTwoCentSpinner, "cell 0 6,aligny center,growy 0");

					//---- form_boxOneCentSpinner ----
					this.form_boxOneCentSpinner.setFont(this.form_boxOneCentSpinner.getFont().deriveFont(this.form_boxOneCentSpinner.getFont().getSize() + 3f));
					this.form_boxOneCentSpinner.setModel(new SpinnerNumberModel(2, 0, 2, 1));
					this.form_boxOneCentSpinner.setPreferredSize(new Dimension(68, 34));
					this.form_boxOneCentSpinner.setMinimumSize(new Dimension(68, 34));
					this.form_boxOneCentSpinner.setName("boxOneCentSpinner");
					this.form_panel40.add(this.form_boxOneCentSpinner, "cell 0 7,aligny center,growy 0");
				}
				this.form_boxPanel.add(this.form_panel40, "cell 2 0,grow");
			}
			this.form_kassensturzErweiterungPanel.add(this.form_boxPanel, "cell 0 1,grow");
		}
		contentPane.add(this.form_kassensturzErweiterungPanel, "cell 0 1 1 2,grow");

		//======== form_cashPanel ========
		{
			this.form_cashPanel.setBorder(new TitledBorder("cash"));
			this.form_cashPanel.setEnabled(false);
			this.form_cashPanel.setMaximumSize(null);
			this.form_cashPanel.setMinimumSize(null);
			this.form_cashPanel.setPreferredSize(null);
			this.form_cashPanel.setName("cashPanel");
			this.form_cashPanel.setLayout(new MigLayout(
				"fill,insets 0,hidemode 3,gap 0 0",
				// columns
				"[grow,fill]" +
				"[grow,fill]" +
				"[grow,fill]" +
				"[grow,fill]" +
				"[grow,fill]" +
				"[grow,fill]",
				// rows
				"[grow,fill]"));

			//======== form_valuePanel ========
			{
				this.form_valuePanel.setPreferredSize(new Dimension(100, 500));
				this.form_valuePanel.setName("valuePanel");
				this.form_valuePanel.setLayout(new MigLayout(
					"fill",
					// columns
					null,
					// rows
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]"));

				//---- form_twoEuroLabel ----
				this.form_twoEuroLabel.setText("2,00 \u20ac");
				this.form_twoEuroLabel.setPreferredSize(new Dimension(100, 33));
				this.form_twoEuroLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_twoEuroLabel.setFont(this.form_twoEuroLabel.getFont().deriveFont(this.form_twoEuroLabel.getFont().getSize() + 3f));
				this.form_twoEuroLabel.setFocusable(false);
				this.form_twoEuroLabel.setInheritsPopupMenu(false);
				this.form_twoEuroLabel.setRequestFocusEnabled(false);
				this.form_twoEuroLabel.setVerifyInputWhenFocusTarget(false);
				this.form_twoEuroLabel.setMinimumSize(new Dimension(100, 33));
				this.form_twoEuroLabel.setMaximumSize(new Dimension(100, 33));
				this.form_twoEuroLabel.setName("twoEuroLabel");
				this.form_valuePanel.add(this.form_twoEuroLabel, "cell 0 0");

				//---- form_oneEuroLabel ----
				this.form_oneEuroLabel.setText("1,00 \u20ac");
				this.form_oneEuroLabel.setPreferredSize(new Dimension(100, 33));
				this.form_oneEuroLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_oneEuroLabel.setFont(this.form_oneEuroLabel.getFont().deriveFont(this.form_oneEuroLabel.getFont().getSize() + 3f));
				this.form_oneEuroLabel.setFocusable(false);
				this.form_oneEuroLabel.setInheritsPopupMenu(false);
				this.form_oneEuroLabel.setRequestFocusEnabled(false);
				this.form_oneEuroLabel.setVerifyInputWhenFocusTarget(false);
				this.form_oneEuroLabel.setMinimumSize(new Dimension(100, 33));
				this.form_oneEuroLabel.setMaximumSize(new Dimension(100, 33));
				this.form_oneEuroLabel.setName("oneEuroLabel");
				this.form_valuePanel.add(this.form_oneEuroLabel, "cell 0 1");

				//---- form_fiftyCentLabel ----
				this.form_fiftyCentLabel.setText("0,50 \u20ac");
				this.form_fiftyCentLabel.setPreferredSize(new Dimension(100, 33));
				this.form_fiftyCentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_fiftyCentLabel.setFont(this.form_fiftyCentLabel.getFont().deriveFont(this.form_fiftyCentLabel.getFont().getSize() + 3f));
				this.form_fiftyCentLabel.setFocusable(false);
				this.form_fiftyCentLabel.setInheritsPopupMenu(false);
				this.form_fiftyCentLabel.setRequestFocusEnabled(false);
				this.form_fiftyCentLabel.setVerifyInputWhenFocusTarget(false);
				this.form_fiftyCentLabel.setMinimumSize(new Dimension(100, 33));
				this.form_fiftyCentLabel.setMaximumSize(new Dimension(100, 33));
				this.form_fiftyCentLabel.setName("fiftyCentLabel");
				this.form_valuePanel.add(this.form_fiftyCentLabel, "cell 0 2");

				//---- form_twentyCentLabel ----
				this.form_twentyCentLabel.setText("0,20 \u20ac");
				this.form_twentyCentLabel.setPreferredSize(new Dimension(100, 33));
				this.form_twentyCentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_twentyCentLabel.setFont(this.form_twentyCentLabel.getFont().deriveFont(this.form_twentyCentLabel.getFont().getSize() + 3f));
				this.form_twentyCentLabel.setFocusable(false);
				this.form_twentyCentLabel.setInheritsPopupMenu(false);
				this.form_twentyCentLabel.setRequestFocusEnabled(false);
				this.form_twentyCentLabel.setVerifyInputWhenFocusTarget(false);
				this.form_twentyCentLabel.setMinimumSize(new Dimension(100, 33));
				this.form_twentyCentLabel.setMaximumSize(new Dimension(100, 33));
				this.form_twentyCentLabel.setName("twentyCentLabel");
				this.form_valuePanel.add(this.form_twentyCentLabel, "cell 0 3");

				//---- form_tenCentLabel ----
				this.form_tenCentLabel.setText("0,10 \u20ac");
				this.form_tenCentLabel.setPreferredSize(new Dimension(100, 33));
				this.form_tenCentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_tenCentLabel.setFont(this.form_tenCentLabel.getFont().deriveFont(this.form_tenCentLabel.getFont().getSize() + 3f));
				this.form_tenCentLabel.setFocusable(false);
				this.form_tenCentLabel.setInheritsPopupMenu(false);
				this.form_tenCentLabel.setRequestFocusEnabled(false);
				this.form_tenCentLabel.setVerifyInputWhenFocusTarget(false);
				this.form_tenCentLabel.setMinimumSize(new Dimension(100, 33));
				this.form_tenCentLabel.setMaximumSize(new Dimension(100, 33));
				this.form_tenCentLabel.setName("tenCentLabel");
				this.form_valuePanel.add(this.form_tenCentLabel, "cell 0 4");

				//---- form_fiveCentLabel ----
				this.form_fiveCentLabel.setText("0,05 \u20ac");
				this.form_fiveCentLabel.setPreferredSize(new Dimension(100, 33));
				this.form_fiveCentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_fiveCentLabel.setFont(this.form_fiveCentLabel.getFont().deriveFont(this.form_fiveCentLabel.getFont().getSize() + 3f));
				this.form_fiveCentLabel.setFocusable(false);
				this.form_fiveCentLabel.setInheritsPopupMenu(false);
				this.form_fiveCentLabel.setRequestFocusEnabled(false);
				this.form_fiveCentLabel.setVerifyInputWhenFocusTarget(false);
				this.form_fiveCentLabel.setMinimumSize(new Dimension(100, 33));
				this.form_fiveCentLabel.setMaximumSize(new Dimension(100, 33));
				this.form_fiveCentLabel.setName("fiveCentLabel");
				this.form_valuePanel.add(this.form_fiveCentLabel, "cell 0 5");

				//---- form_twoCentLabel ----
				this.form_twoCentLabel.setText("0,02 \u20ac");
				this.form_twoCentLabel.setPreferredSize(new Dimension(100, 33));
				this.form_twoCentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_twoCentLabel.setFont(this.form_twoCentLabel.getFont().deriveFont(this.form_twoCentLabel.getFont().getSize() + 3f));
				this.form_twoCentLabel.setFocusable(false);
				this.form_twoCentLabel.setInheritsPopupMenu(false);
				this.form_twoCentLabel.setRequestFocusEnabled(false);
				this.form_twoCentLabel.setVerifyInputWhenFocusTarget(false);
				this.form_twoCentLabel.setMinimumSize(new Dimension(100, 33));
				this.form_twoCentLabel.setMaximumSize(new Dimension(100, 33));
				this.form_twoCentLabel.setName("twoCentLabel");
				this.form_valuePanel.add(this.form_twoCentLabel, "cell 0 6");

				//---- form_oneCentLabel ----
				this.form_oneCentLabel.setText("0,01 \u20ac");
				this.form_oneCentLabel.setPreferredSize(new Dimension(100, 33));
				this.form_oneCentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_oneCentLabel.setFont(this.form_oneCentLabel.getFont().deriveFont(this.form_oneCentLabel.getFont().getSize() + 3f));
				this.form_oneCentLabel.setFocusable(false);
				this.form_oneCentLabel.setInheritsPopupMenu(false);
				this.form_oneCentLabel.setRequestFocusEnabled(false);
				this.form_oneCentLabel.setVerifyInputWhenFocusTarget(false);
				this.form_oneCentLabel.setMaximumSize(new Dimension(100, 33));
				this.form_oneCentLabel.setMinimumSize(new Dimension(100, 33));
				this.form_oneCentLabel.setName("oneCentLabel");
				this.form_valuePanel.add(this.form_oneCentLabel, "cell 0 7");

				//---- form_vSpacer1 ----
				this.form_vSpacer1.setName("vSpacer1");
				this.form_valuePanel.add(this.form_vSpacer1, "cell 0 8");

				//---- form_twoHundredEuroLabel ----
				this.form_twoHundredEuroLabel.setText("200,00 \u20ac");
				this.form_twoHundredEuroLabel.setPreferredSize(new Dimension(100, 33));
				this.form_twoHundredEuroLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_twoHundredEuroLabel.setFont(this.form_twoHundredEuroLabel.getFont().deriveFont(this.form_twoHundredEuroLabel.getFont().getSize() + 3f));
				this.form_twoHundredEuroLabel.setFocusable(false);
				this.form_twoHundredEuroLabel.setInheritsPopupMenu(false);
				this.form_twoHundredEuroLabel.setRequestFocusEnabled(false);
				this.form_twoHundredEuroLabel.setVerifyInputWhenFocusTarget(false);
				this.form_twoHundredEuroLabel.setMaximumSize(new Dimension(100, 33));
				this.form_twoHundredEuroLabel.setMinimumSize(new Dimension(100, 33));
				this.form_twoHundredEuroLabel.setName("twoHundredEuroLabel");
				this.form_valuePanel.add(this.form_twoHundredEuroLabel, "cell 0 9");

				//---- form_oneHundredEuroLabel ----
				this.form_oneHundredEuroLabel.setText("100,00 \u20ac");
				this.form_oneHundredEuroLabel.setPreferredSize(new Dimension(100, 33));
				this.form_oneHundredEuroLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_oneHundredEuroLabel.setFont(this.form_oneHundredEuroLabel.getFont().deriveFont(this.form_oneHundredEuroLabel.getFont().getSize() + 3f));
				this.form_oneHundredEuroLabel.setFocusable(false);
				this.form_oneHundredEuroLabel.setInheritsPopupMenu(false);
				this.form_oneHundredEuroLabel.setRequestFocusEnabled(false);
				this.form_oneHundredEuroLabel.setVerifyInputWhenFocusTarget(false);
				this.form_oneHundredEuroLabel.setMaximumSize(new Dimension(100, 33));
				this.form_oneHundredEuroLabel.setMinimumSize(new Dimension(100, 33));
				this.form_oneHundredEuroLabel.setName("oneHundredEuroLabel");
				this.form_valuePanel.add(this.form_oneHundredEuroLabel, "cell 0 10");

				//---- form_fiftyEuroLabel ----
				this.form_fiftyEuroLabel.setText("50,00 \u20ac");
				this.form_fiftyEuroLabel.setPreferredSize(new Dimension(100, 33));
				this.form_fiftyEuroLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_fiftyEuroLabel.setFont(this.form_fiftyEuroLabel.getFont().deriveFont(this.form_fiftyEuroLabel.getFont().getSize() + 3f));
				this.form_fiftyEuroLabel.setFocusable(false);
				this.form_fiftyEuroLabel.setInheritsPopupMenu(false);
				this.form_fiftyEuroLabel.setRequestFocusEnabled(false);
				this.form_fiftyEuroLabel.setVerifyInputWhenFocusTarget(false);
				this.form_fiftyEuroLabel.setMaximumSize(new Dimension(100, 33));
				this.form_fiftyEuroLabel.setMinimumSize(new Dimension(100, 33));
				this.form_fiftyEuroLabel.setName("fiftyEuroLabel");
				this.form_valuePanel.add(this.form_fiftyEuroLabel, "cell 0 11");

				//---- form_twentyEuroLabel ----
				this.form_twentyEuroLabel.setText("20,00 \u20ac");
				this.form_twentyEuroLabel.setPreferredSize(new Dimension(100, 33));
				this.form_twentyEuroLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_twentyEuroLabel.setFont(this.form_twentyEuroLabel.getFont().deriveFont(this.form_twentyEuroLabel.getFont().getSize() + 3f));
				this.form_twentyEuroLabel.setFocusable(false);
				this.form_twentyEuroLabel.setInheritsPopupMenu(false);
				this.form_twentyEuroLabel.setRequestFocusEnabled(false);
				this.form_twentyEuroLabel.setVerifyInputWhenFocusTarget(false);
				this.form_twentyEuroLabel.setMaximumSize(new Dimension(100, 33));
				this.form_twentyEuroLabel.setMinimumSize(new Dimension(100, 33));
				this.form_twentyEuroLabel.setName("twentyEuroLabel");
				this.form_valuePanel.add(this.form_twentyEuroLabel, "cell 0 12");

				//---- form_tenEuroLabel ----
				this.form_tenEuroLabel.setText("10,00 \u20ac");
				this.form_tenEuroLabel.setPreferredSize(new Dimension(100, 33));
				this.form_tenEuroLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_tenEuroLabel.setFont(this.form_tenEuroLabel.getFont().deriveFont(this.form_tenEuroLabel.getFont().getSize() + 3f));
				this.form_tenEuroLabel.setFocusable(false);
				this.form_tenEuroLabel.setInheritsPopupMenu(false);
				this.form_tenEuroLabel.setRequestFocusEnabled(false);
				this.form_tenEuroLabel.setVerifyInputWhenFocusTarget(false);
				this.form_tenEuroLabel.setMaximumSize(new Dimension(100, 33));
				this.form_tenEuroLabel.setMinimumSize(new Dimension(100, 33));
				this.form_tenEuroLabel.setName("tenEuroLabel");
				this.form_valuePanel.add(this.form_tenEuroLabel, "cell 0 13");

				//---- form_fiveEuroLabel ----
				this.form_fiveEuroLabel.setText("5,00 \u20ac");
				this.form_fiveEuroLabel.setPreferredSize(new Dimension(100, 33));
				this.form_fiveEuroLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_fiveEuroLabel.setFont(this.form_fiveEuroLabel.getFont().deriveFont(this.form_fiveEuroLabel.getFont().getSize() + 3f));
				this.form_fiveEuroLabel.setFocusable(false);
				this.form_fiveEuroLabel.setInheritsPopupMenu(false);
				this.form_fiveEuroLabel.setRequestFocusEnabled(false);
				this.form_fiveEuroLabel.setVerifyInputWhenFocusTarget(false);
				this.form_fiveEuroLabel.setMaximumSize(new Dimension(100, 33));
				this.form_fiveEuroLabel.setMinimumSize(new Dimension(100, 33));
				this.form_fiveEuroLabel.setName("fiveEuroLabel");
				this.form_valuePanel.add(this.form_fiveEuroLabel, "cell 0 14");
			}
			this.form_cashPanel.add(this.form_valuePanel, "cell 0 0");

			//======== form_firstSpinnerNormal ========
			{
				this.form_firstSpinnerNormal.setPreferredSize(new Dimension(100, 500));
				this.form_firstSpinnerNormal.setName("firstSpinnerNormal");
				this.form_firstSpinnerNormal.setLayout(new MigLayout(
					"fill,align center center",
					// columns
					"[fill]",
					// rows
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]"));

				//---- form_spinner2_1 ----
				this.form_spinner2_1.setPreferredSize(new Dimension(64, 30));
				this.form_spinner2_1.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner2_1.setFont(this.form_spinner2_1.getFont().deriveFont(this.form_spinner2_1.getFont().getSize() + 3f));
				this.form_spinner2_1.setName("spinner2_1");
				this.form_firstSpinnerNormal.add(this.form_spinner2_1, "cell 0 0");

				//---- form_spinner1_1 ----
				this.form_spinner1_1.setPreferredSize(new Dimension(64, 30));
				this.form_spinner1_1.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner1_1.setFont(this.form_spinner1_1.getFont().deriveFont(this.form_spinner1_1.getFont().getSize() + 3f));
				this.form_spinner1_1.setName("spinner1_1");
				this.form_firstSpinnerNormal.add(this.form_spinner1_1, "cell 0 1");

				//---- form_spinner050_1 ----
				this.form_spinner050_1.setPreferredSize(new Dimension(64, 30));
				this.form_spinner050_1.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner050_1.setFont(this.form_spinner050_1.getFont().deriveFont(this.form_spinner050_1.getFont().getSize() + 3f));
				this.form_spinner050_1.setName("spinner050_1");
				this.form_firstSpinnerNormal.add(this.form_spinner050_1, "cell 0 2");

				//---- form_spinner020_1 ----
				this.form_spinner020_1.setPreferredSize(new Dimension(64, 30));
				this.form_spinner020_1.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner020_1.setFont(this.form_spinner020_1.getFont().deriveFont(this.form_spinner020_1.getFont().getSize() + 3f));
				this.form_spinner020_1.setName("spinner020_1");
				this.form_firstSpinnerNormal.add(this.form_spinner020_1, "cell 0 3");

				//---- form_spinner010_1 ----
				this.form_spinner010_1.setPreferredSize(new Dimension(64, 30));
				this.form_spinner010_1.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner010_1.setFont(this.form_spinner010_1.getFont().deriveFont(this.form_spinner010_1.getFont().getSize() + 3f));
				this.form_spinner010_1.setName("spinner010_1");
				this.form_firstSpinnerNormal.add(this.form_spinner010_1, "cell 0 4");

				//---- form_spinner005_1 ----
				this.form_spinner005_1.setPreferredSize(new Dimension(64, 30));
				this.form_spinner005_1.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner005_1.setFont(this.form_spinner005_1.getFont().deriveFont(this.form_spinner005_1.getFont().getSize() + 3f));
				this.form_spinner005_1.setName("spinner005_1");
				this.form_firstSpinnerNormal.add(this.form_spinner005_1, "cell 0 5");

				//---- form_spinner002_1 ----
				this.form_spinner002_1.setPreferredSize(new Dimension(64, 30));
				this.form_spinner002_1.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner002_1.setFont(this.form_spinner002_1.getFont().deriveFont(this.form_spinner002_1.getFont().getSize() + 3f));
				this.form_spinner002_1.setName("spinner002_1");
				this.form_firstSpinnerNormal.add(this.form_spinner002_1, "cell 0 6");

				//---- form_spinner001_1 ----
				this.form_spinner001_1.setPreferredSize(new Dimension(64, 30));
				this.form_spinner001_1.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner001_1.setFont(this.form_spinner001_1.getFont().deriveFont(this.form_spinner001_1.getFont().getSize() + 3f));
				this.form_spinner001_1.setName("spinner001_1");
				this.form_firstSpinnerNormal.add(this.form_spinner001_1, "cell 0 7");

				//---- form_vSpacer2 ----
				this.form_vSpacer2.setName("vSpacer2");
				this.form_firstSpinnerNormal.add(this.form_vSpacer2, "cell 0 8");

				//---- form_spinner200_1 ----
				this.form_spinner200_1.setPreferredSize(new Dimension(64, 30));
				this.form_spinner200_1.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner200_1.setFont(this.form_spinner200_1.getFont().deriveFont(this.form_spinner200_1.getFont().getSize() + 3f));
				this.form_spinner200_1.setName("spinner200_1");
				this.form_firstSpinnerNormal.add(this.form_spinner200_1, "cell 0 9");

				//---- form_spinner100_1 ----
				this.form_spinner100_1.setPreferredSize(new Dimension(64, 30));
				this.form_spinner100_1.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner100_1.setFont(this.form_spinner100_1.getFont().deriveFont(this.form_spinner100_1.getFont().getSize() + 3f));
				this.form_spinner100_1.setName("spinner100_1");
				this.form_firstSpinnerNormal.add(this.form_spinner100_1, "cell 0 10");

				//---- form_spinner50_1 ----
				this.form_spinner50_1.setPreferredSize(new Dimension(64, 30));
				this.form_spinner50_1.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner50_1.setFont(this.form_spinner50_1.getFont().deriveFont(this.form_spinner50_1.getFont().getSize() + 3f));
				this.form_spinner50_1.setName("spinner50_1");
				this.form_firstSpinnerNormal.add(this.form_spinner50_1, "cell 0 11");

				//---- form_spinner20_1 ----
				this.form_spinner20_1.setPreferredSize(new Dimension(64, 30));
				this.form_spinner20_1.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner20_1.setFont(this.form_spinner20_1.getFont().deriveFont(this.form_spinner20_1.getFont().getSize() + 3f));
				this.form_spinner20_1.setName("spinner20_1");
				this.form_firstSpinnerNormal.add(this.form_spinner20_1, "cell 0 12");

				//---- form_spinner10_1 ----
				this.form_spinner10_1.setPreferredSize(new Dimension(64, 30));
				this.form_spinner10_1.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner10_1.setFont(this.form_spinner10_1.getFont().deriveFont(this.form_spinner10_1.getFont().getSize() + 3f));
				this.form_spinner10_1.setName("spinner10_1");
				this.form_firstSpinnerNormal.add(this.form_spinner10_1, "cell 0 13");

				//---- form_spinner5_1 ----
				this.form_spinner5_1.setPreferredSize(new Dimension(64, 30));
				this.form_spinner5_1.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner5_1.setFont(this.form_spinner5_1.getFont().deriveFont(this.form_spinner5_1.getFont().getSize() + 3f));
				this.form_spinner5_1.setName("spinner5_1");
				this.form_firstSpinnerNormal.add(this.form_spinner5_1, "cell 0 14");
			}
			this.form_cashPanel.add(this.form_firstSpinnerNormal, "cell 1 0");

			//======== form_secondSpinnerNormal ========
			{
				this.form_secondSpinnerNormal.setPreferredSize(new Dimension(100, 500));
				this.form_secondSpinnerNormal.setFont(this.form_secondSpinnerNormal.getFont().deriveFont(this.form_secondSpinnerNormal.getFont().getSize() + 3f));
				this.form_secondSpinnerNormal.setName("secondSpinnerNormal");
				this.form_secondSpinnerNormal.setLayout(new MigLayout(
					"fill,align center center",
					// columns
					"[fill]",
					// rows
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]"));

				//---- form_spinner200_2 ----
				this.form_spinner200_2.setPreferredSize(new Dimension(64, 30));
				this.form_spinner200_2.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner200_2.setFont(this.form_spinner200_2.getFont().deriveFont(this.form_spinner200_2.getFont().getSize() + 3f));
				this.form_spinner200_2.setName("spinner200_2");
				this.form_secondSpinnerNormal.add(this.form_spinner200_2, "cell 0 9");

				//---- form_spinner100_2 ----
				this.form_spinner100_2.setPreferredSize(new Dimension(64, 30));
				this.form_spinner100_2.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner100_2.setFont(this.form_spinner100_2.getFont().deriveFont(this.form_spinner100_2.getFont().getSize() + 3f));
				this.form_spinner100_2.setName("spinner100_2");
				this.form_secondSpinnerNormal.add(this.form_spinner100_2, "cell 0 10");

				//---- form_spinner50_2 ----
				this.form_spinner50_2.setPreferredSize(new Dimension(64, 30));
				this.form_spinner50_2.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner50_2.setFont(this.form_spinner50_2.getFont().deriveFont(this.form_spinner50_2.getFont().getSize() + 3f));
				this.form_spinner50_2.setName("spinner50_2");
				this.form_secondSpinnerNormal.add(this.form_spinner50_2, "cell 0 11");

				//---- form_spinner20_2 ----
				this.form_spinner20_2.setPreferredSize(new Dimension(64, 30));
				this.form_spinner20_2.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner20_2.setFont(this.form_spinner20_2.getFont().deriveFont(this.form_spinner20_2.getFont().getSize() + 3f));
				this.form_spinner20_2.setName("spinner20_2");
				this.form_secondSpinnerNormal.add(this.form_spinner20_2, "cell 0 12");

				//---- form_spinner10_2 ----
				this.form_spinner10_2.setPreferredSize(new Dimension(64, 30));
				this.form_spinner10_2.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner10_2.setFont(this.form_spinner10_2.getFont().deriveFont(this.form_spinner10_2.getFont().getSize() + 3f));
				this.form_spinner10_2.setName("spinner10_2");
				this.form_secondSpinnerNormal.add(this.form_spinner10_2, "cell 0 13");

				//---- form_spinner5_2 ----
				this.form_spinner5_2.setPreferredSize(new Dimension(64, 30));
				this.form_spinner5_2.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner5_2.setFont(this.form_spinner5_2.getFont().deriveFont(this.form_spinner5_2.getFont().getSize() + 3f));
				this.form_spinner5_2.setName("spinner5_2");
				this.form_secondSpinnerNormal.add(this.form_spinner5_2, "cell 0 14");

				//---- form_spinner2_2 ----
				this.form_spinner2_2.setPreferredSize(new Dimension(64, 30));
				this.form_spinner2_2.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner2_2.setFont(this.form_spinner2_2.getFont().deriveFont(this.form_spinner2_2.getFont().getSize() + 3f));
				this.form_spinner2_2.setName("spinner2_2");
				this.form_secondSpinnerNormal.add(this.form_spinner2_2, "cell 0 0");

				//---- form_spinner1_2 ----
				this.form_spinner1_2.setPreferredSize(new Dimension(64, 30));
				this.form_spinner1_2.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner1_2.setFont(this.form_spinner1_2.getFont().deriveFont(this.form_spinner1_2.getFont().getSize() + 3f));
				this.form_spinner1_2.setName("spinner1_2");
				this.form_secondSpinnerNormal.add(this.form_spinner1_2, "cell 0 1");

				//---- form_spinner050_2 ----
				this.form_spinner050_2.setPreferredSize(new Dimension(64, 30));
				this.form_spinner050_2.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner050_2.setFont(this.form_spinner050_2.getFont().deriveFont(this.form_spinner050_2.getFont().getSize() + 3f));
				this.form_spinner050_2.setName("spinner050_2");
				this.form_secondSpinnerNormal.add(this.form_spinner050_2, "cell 0 2");

				//---- form_spinner020_2 ----
				this.form_spinner020_2.setPreferredSize(new Dimension(64, 30));
				this.form_spinner020_2.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner020_2.setFont(this.form_spinner020_2.getFont().deriveFont(this.form_spinner020_2.getFont().getSize() + 3f));
				this.form_spinner020_2.setName("spinner020_2");
				this.form_secondSpinnerNormal.add(this.form_spinner020_2, "cell 0 3");

				//---- form_spinner010_2 ----
				this.form_spinner010_2.setPreferredSize(new Dimension(64, 30));
				this.form_spinner010_2.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner010_2.setFont(this.form_spinner010_2.getFont().deriveFont(this.form_spinner010_2.getFont().getSize() + 3f));
				this.form_spinner010_2.setName("spinner010_2");
				this.form_secondSpinnerNormal.add(this.form_spinner010_2, "cell 0 4");

				//---- form_spinner005_2 ----
				this.form_spinner005_2.setPreferredSize(new Dimension(64, 30));
				this.form_spinner005_2.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner005_2.setFont(this.form_spinner005_2.getFont().deriveFont(this.form_spinner005_2.getFont().getSize() + 3f));
				this.form_spinner005_2.setName("spinner005_2");
				this.form_secondSpinnerNormal.add(this.form_spinner005_2, "cell 0 5");

				//---- form_spinner002_2 ----
				this.form_spinner002_2.setPreferredSize(new Dimension(64, 30));
				this.form_spinner002_2.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner002_2.setFont(this.form_spinner002_2.getFont().deriveFont(this.form_spinner002_2.getFont().getSize() + 3f));
				this.form_spinner002_2.setName("spinner002_2");
				this.form_secondSpinnerNormal.add(this.form_spinner002_2, "cell 0 6");

				//---- form_spinner001_2 ----
				this.form_spinner001_2.setPreferredSize(new Dimension(64, 30));
				this.form_spinner001_2.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner001_2.setFont(this.form_spinner001_2.getFont().deriveFont(this.form_spinner001_2.getFont().getSize() + 3f));
				this.form_spinner001_2.setName("spinner001_2");
				this.form_secondSpinnerNormal.add(this.form_spinner001_2, "cell 0 7");

				//---- form_vSpacer3 ----
				this.form_vSpacer3.setName("vSpacer3");
				this.form_secondSpinnerNormal.add(this.form_vSpacer3, "cell 0 8");
			}
			this.form_cashPanel.add(this.form_secondSpinnerNormal, "cell 2 0");

			//======== form_thirdSpinnerNormal ========
			{
				this.form_thirdSpinnerNormal.setPreferredSize(new Dimension(100, 500));
				this.form_thirdSpinnerNormal.setFont(this.form_thirdSpinnerNormal.getFont().deriveFont(this.form_thirdSpinnerNormal.getFont().getSize() + 3f));
				this.form_thirdSpinnerNormal.setName("thirdSpinnerNormal");
				this.form_thirdSpinnerNormal.setLayout(new MigLayout(
					"fill,align center center",
					// columns
					"[fill]",
					// rows
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]"));

				//---- form_spinner2_3 ----
				this.form_spinner2_3.setPreferredSize(new Dimension(64, 30));
				this.form_spinner2_3.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner2_3.setFont(this.form_spinner2_3.getFont().deriveFont(this.form_spinner2_3.getFont().getSize() + 3f));
				this.form_spinner2_3.setName("spinner2_3");
				this.form_thirdSpinnerNormal.add(this.form_spinner2_3, "cell 0 0");

				//---- form_spinner1_3 ----
				this.form_spinner1_3.setPreferredSize(new Dimension(64, 30));
				this.form_spinner1_3.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner1_3.setFont(this.form_spinner1_3.getFont().deriveFont(this.form_spinner1_3.getFont().getSize() + 3f));
				this.form_spinner1_3.setName("spinner1_3");
				this.form_thirdSpinnerNormal.add(this.form_spinner1_3, "cell 0 1");

				//---- form_spinner050_3 ----
				this.form_spinner050_3.setPreferredSize(new Dimension(64, 30));
				this.form_spinner050_3.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner050_3.setFont(this.form_spinner050_3.getFont().deriveFont(this.form_spinner050_3.getFont().getSize() + 3f));
				this.form_spinner050_3.setName("spinner050_3");
				this.form_thirdSpinnerNormal.add(this.form_spinner050_3, "cell 0 2");

				//---- form_spinner020_3 ----
				this.form_spinner020_3.setPreferredSize(new Dimension(64, 30));
				this.form_spinner020_3.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner020_3.setFont(this.form_spinner020_3.getFont().deriveFont(this.form_spinner020_3.getFont().getSize() + 3f));
				this.form_spinner020_3.setName("spinner020_3");
				this.form_thirdSpinnerNormal.add(this.form_spinner020_3, "cell 0 3");

				//---- form_spinner010_3 ----
				this.form_spinner010_3.setPreferredSize(new Dimension(64, 30));
				this.form_spinner010_3.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner010_3.setFont(this.form_spinner010_3.getFont().deriveFont(this.form_spinner010_3.getFont().getSize() + 3f));
				this.form_spinner010_3.setName("spinner010_3");
				this.form_thirdSpinnerNormal.add(this.form_spinner010_3, "cell 0 4");

				//---- form_spinner005_3 ----
				this.form_spinner005_3.setPreferredSize(new Dimension(64, 30));
				this.form_spinner005_3.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner005_3.setFont(this.form_spinner005_3.getFont().deriveFont(this.form_spinner005_3.getFont().getSize() + 3f));
				this.form_spinner005_3.setName("spinner005_3");
				this.form_thirdSpinnerNormal.add(this.form_spinner005_3, "cell 0 5");

				//---- form_spinner002_3 ----
				this.form_spinner002_3.setPreferredSize(new Dimension(64, 30));
				this.form_spinner002_3.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner002_3.setFont(this.form_spinner002_3.getFont().deriveFont(this.form_spinner002_3.getFont().getSize() + 3f));
				this.form_spinner002_3.setName("spinner002_3");
				this.form_thirdSpinnerNormal.add(this.form_spinner002_3, "cell 0 6");

				//---- form_spinner001_3 ----
				this.form_spinner001_3.setPreferredSize(new Dimension(64, 30));
				this.form_spinner001_3.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner001_3.setFont(this.form_spinner001_3.getFont().deriveFont(this.form_spinner001_3.getFont().getSize() + 3f));
				this.form_spinner001_3.setName("spinner001_3");
				this.form_thirdSpinnerNormal.add(this.form_spinner001_3, "cell 0 7");

				//---- form_vSpacer4 ----
				this.form_vSpacer4.setName("vSpacer4");
				this.form_thirdSpinnerNormal.add(this.form_vSpacer4, "cell 0 8");

				//---- form_spinner200_3 ----
				this.form_spinner200_3.setPreferredSize(new Dimension(64, 30));
				this.form_spinner200_3.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner200_3.setFont(this.form_spinner200_3.getFont().deriveFont(this.form_spinner200_3.getFont().getSize() + 3f));
				this.form_spinner200_3.setName("spinner200_3");
				this.form_thirdSpinnerNormal.add(this.form_spinner200_3, "cell 0 9");

				//---- form_spinner100_3 ----
				this.form_spinner100_3.setPreferredSize(new Dimension(64, 30));
				this.form_spinner100_3.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner100_3.setFont(this.form_spinner100_3.getFont().deriveFont(this.form_spinner100_3.getFont().getSize() + 3f));
				this.form_spinner100_3.setName("spinner100_3");
				this.form_thirdSpinnerNormal.add(this.form_spinner100_3, "cell 0 10");

				//---- form_spinner50_3 ----
				this.form_spinner50_3.setPreferredSize(new Dimension(64, 30));
				this.form_spinner50_3.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner50_3.setFont(this.form_spinner50_3.getFont().deriveFont(this.form_spinner50_3.getFont().getSize() + 3f));
				this.form_spinner50_3.setName("spinner50_3");
				this.form_thirdSpinnerNormal.add(this.form_spinner50_3, "cell 0 11");

				//---- form_spinner20_3 ----
				this.form_spinner20_3.setPreferredSize(new Dimension(64, 30));
				this.form_spinner20_3.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner20_3.setFont(this.form_spinner20_3.getFont().deriveFont(this.form_spinner20_3.getFont().getSize() + 3f));
				this.form_spinner20_3.setName("spinner20_3");
				this.form_thirdSpinnerNormal.add(this.form_spinner20_3, "cell 0 12");

				//---- form_spinner10_3 ----
				this.form_spinner10_3.setPreferredSize(new Dimension(64, 30));
				this.form_spinner10_3.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner10_3.setFont(this.form_spinner10_3.getFont().deriveFont(this.form_spinner10_3.getFont().getSize() + 3f));
				this.form_spinner10_3.setName("spinner10_3");
				this.form_thirdSpinnerNormal.add(this.form_spinner10_3, "cell 0 13");

				//---- form_spinner5_3 ----
				this.form_spinner5_3.setPreferredSize(new Dimension(64, 30));
				this.form_spinner5_3.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner5_3.setFont(this.form_spinner5_3.getFont().deriveFont(this.form_spinner5_3.getFont().getSize() + 3f));
				this.form_spinner5_3.setName("spinner5_3");
				this.form_thirdSpinnerNormal.add(this.form_spinner5_3, "cell 0 14");
			}
			this.form_cashPanel.add(this.form_thirdSpinnerNormal, "cell 3 0");

			//======== form_fourthSpinnerNormal ========
			{
				this.form_fourthSpinnerNormal.setPreferredSize(new Dimension(100, 500));
				this.form_fourthSpinnerNormal.setFont(this.form_fourthSpinnerNormal.getFont().deriveFont(this.form_fourthSpinnerNormal.getFont().getSize() + 3f));
				this.form_fourthSpinnerNormal.setName("fourthSpinnerNormal");
				this.form_fourthSpinnerNormal.setLayout(new MigLayout(
					"fill,align center center",
					// columns
					"[fill]",
					// rows
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]"));

				//---- form_spinner2_4 ----
				this.form_spinner2_4.setPreferredSize(new Dimension(64, 30));
				this.form_spinner2_4.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner2_4.setFont(this.form_spinner2_4.getFont().deriveFont(this.form_spinner2_4.getFont().getSize() + 3f));
				this.form_spinner2_4.setName("spinner2_4");
				this.form_fourthSpinnerNormal.add(this.form_spinner2_4, "cell 0 0");

				//---- form_spinner1_4 ----
				this.form_spinner1_4.setPreferredSize(new Dimension(64, 30));
				this.form_spinner1_4.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner1_4.setFont(this.form_spinner1_4.getFont().deriveFont(this.form_spinner1_4.getFont().getSize() + 3f));
				this.form_spinner1_4.setName("spinner1_4");
				this.form_fourthSpinnerNormal.add(this.form_spinner1_4, "cell 0 1");

				//---- form_spinner050_4 ----
				this.form_spinner050_4.setPreferredSize(new Dimension(64, 30));
				this.form_spinner050_4.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner050_4.setFont(this.form_spinner050_4.getFont().deriveFont(this.form_spinner050_4.getFont().getSize() + 3f));
				this.form_spinner050_4.setName("spinner050_4");
				this.form_fourthSpinnerNormal.add(this.form_spinner050_4, "cell 0 2");

				//---- form_spinner020_4 ----
				this.form_spinner020_4.setPreferredSize(new Dimension(64, 30));
				this.form_spinner020_4.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner020_4.setFont(this.form_spinner020_4.getFont().deriveFont(this.form_spinner020_4.getFont().getSize() + 3f));
				this.form_spinner020_4.setName("spinner020_4");
				this.form_fourthSpinnerNormal.add(this.form_spinner020_4, "cell 0 3");

				//---- form_spinner010_4 ----
				this.form_spinner010_4.setPreferredSize(new Dimension(64, 30));
				this.form_spinner010_4.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner010_4.setFont(this.form_spinner010_4.getFont().deriveFont(this.form_spinner010_4.getFont().getSize() + 3f));
				this.form_spinner010_4.setName("spinner010_4");
				this.form_fourthSpinnerNormal.add(this.form_spinner010_4, "cell 0 4");

				//---- form_spinner005_4 ----
				this.form_spinner005_4.setPreferredSize(new Dimension(64, 30));
				this.form_spinner005_4.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner005_4.setFont(this.form_spinner005_4.getFont().deriveFont(this.form_spinner005_4.getFont().getSize() + 3f));
				this.form_spinner005_4.setName("spinner005_4");
				this.form_fourthSpinnerNormal.add(this.form_spinner005_4, "cell 0 5");

				//---- form_spinner002_4 ----
				this.form_spinner002_4.setPreferredSize(new Dimension(64, 30));
				this.form_spinner002_4.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner002_4.setFont(this.form_spinner002_4.getFont().deriveFont(this.form_spinner002_4.getFont().getSize() + 3f));
				this.form_spinner002_4.setName("spinner002_4");
				this.form_fourthSpinnerNormal.add(this.form_spinner002_4, "cell 0 6");

				//---- form_spinner001_4 ----
				this.form_spinner001_4.setPreferredSize(new Dimension(64, 30));
				this.form_spinner001_4.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner001_4.setFont(this.form_spinner001_4.getFont().deriveFont(this.form_spinner001_4.getFont().getSize() + 3f));
				this.form_spinner001_4.setName("spinner001_4");
				this.form_fourthSpinnerNormal.add(this.form_spinner001_4, "cell 0 7");

				//---- form_vSpacer5 ----
				this.form_vSpacer5.setName("vSpacer5");
				this.form_fourthSpinnerNormal.add(this.form_vSpacer5, "cell 0 8");

				//---- form_spinner200_4 ----
				this.form_spinner200_4.setPreferredSize(new Dimension(64, 30));
				this.form_spinner200_4.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner200_4.setFont(this.form_spinner200_4.getFont().deriveFont(this.form_spinner200_4.getFont().getSize() + 3f));
				this.form_spinner200_4.setName("spinner200_4");
				this.form_fourthSpinnerNormal.add(this.form_spinner200_4, "cell 0 9");

				//---- form_spinner100_4 ----
				this.form_spinner100_4.setPreferredSize(new Dimension(64, 30));
				this.form_spinner100_4.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner100_4.setFont(this.form_spinner100_4.getFont().deriveFont(this.form_spinner100_4.getFont().getSize() + 3f));
				this.form_spinner100_4.setName("spinner100_4");
				this.form_fourthSpinnerNormal.add(this.form_spinner100_4, "cell 0 10");

				//---- form_spinner50_4 ----
				this.form_spinner50_4.setPreferredSize(new Dimension(64, 30));
				this.form_spinner50_4.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner50_4.setFont(this.form_spinner50_4.getFont().deriveFont(this.form_spinner50_4.getFont().getSize() + 3f));
				this.form_spinner50_4.setName("spinner50_4");
				this.form_fourthSpinnerNormal.add(this.form_spinner50_4, "cell 0 11");

				//---- form_spinner20_4 ----
				this.form_spinner20_4.setPreferredSize(new Dimension(64, 30));
				this.form_spinner20_4.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner20_4.setFont(this.form_spinner20_4.getFont().deriveFont(this.form_spinner20_4.getFont().getSize() + 3f));
				this.form_spinner20_4.setName("spinner20_4");
				this.form_fourthSpinnerNormal.add(this.form_spinner20_4, "cell 0 12");

				//---- form_spinner10_4 ----
				this.form_spinner10_4.setPreferredSize(new Dimension(64, 30));
				this.form_spinner10_4.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner10_4.setFont(this.form_spinner10_4.getFont().deriveFont(this.form_spinner10_4.getFont().getSize() + 3f));
				this.form_spinner10_4.setName("spinner10_4");
				this.form_fourthSpinnerNormal.add(this.form_spinner10_4, "cell 0 13");

				//---- form_spinner5_4 ----
				this.form_spinner5_4.setPreferredSize(new Dimension(64, 30));
				this.form_spinner5_4.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner5_4.setFont(this.form_spinner5_4.getFont().deriveFont(this.form_spinner5_4.getFont().getSize() + 3f));
				this.form_spinner5_4.setName("spinner5_4");
				this.form_fourthSpinnerNormal.add(this.form_spinner5_4, "cell 0 14");
			}
			this.form_cashPanel.add(this.form_fourthSpinnerNormal, "cell 4 0");

			//======== form_fifthSpinnerNormal ========
			{
				this.form_fifthSpinnerNormal.setPreferredSize(new Dimension(100, 500));
				this.form_fifthSpinnerNormal.setFont(this.form_fifthSpinnerNormal.getFont().deriveFont(this.form_fifthSpinnerNormal.getFont().getSize() + 3f));
				this.form_fifthSpinnerNormal.setName("fifthSpinnerNormal");
				this.form_fifthSpinnerNormal.setLayout(new MigLayout(
					"fill,align center center",
					// columns
					"[fill]",
					// rows
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]"));

				//---- form_spinner2_5 ----
				this.form_spinner2_5.setPreferredSize(new Dimension(64, 30));
				this.form_spinner2_5.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner2_5.setFont(this.form_spinner2_5.getFont().deriveFont(this.form_spinner2_5.getFont().getSize() + 3f));
				this.form_spinner2_5.setName("spinner2_5");
				this.form_fifthSpinnerNormal.add(this.form_spinner2_5, "cell 0 0");

				//---- form_spinner1_5 ----
				this.form_spinner1_5.setPreferredSize(new Dimension(64, 30));
				this.form_spinner1_5.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner1_5.setFont(this.form_spinner1_5.getFont().deriveFont(this.form_spinner1_5.getFont().getSize() + 3f));
				this.form_spinner1_5.setName("spinner1_5");
				this.form_fifthSpinnerNormal.add(this.form_spinner1_5, "cell 0 1");

				//---- form_spinner050_5 ----
				this.form_spinner050_5.setPreferredSize(new Dimension(64, 30));
				this.form_spinner050_5.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner050_5.setFont(this.form_spinner050_5.getFont().deriveFont(this.form_spinner050_5.getFont().getSize() + 3f));
				this.form_spinner050_5.setName("spinner050_5");
				this.form_fifthSpinnerNormal.add(this.form_spinner050_5, "cell 0 2");

				//---- form_spinner020_5 ----
				this.form_spinner020_5.setPreferredSize(new Dimension(64, 30));
				this.form_spinner020_5.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner020_5.setFont(this.form_spinner020_5.getFont().deriveFont(this.form_spinner020_5.getFont().getSize() + 3f));
				this.form_spinner020_5.setName("spinner020_5");
				this.form_fifthSpinnerNormal.add(this.form_spinner020_5, "cell 0 3");

				//---- form_spinner010_5 ----
				this.form_spinner010_5.setPreferredSize(new Dimension(64, 30));
				this.form_spinner010_5.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner010_5.setFont(this.form_spinner010_5.getFont().deriveFont(this.form_spinner010_5.getFont().getSize() + 3f));
				this.form_spinner010_5.setName("spinner010_5");
				this.form_fifthSpinnerNormal.add(this.form_spinner010_5, "cell 0 4");

				//---- form_spinner005_5 ----
				this.form_spinner005_5.setPreferredSize(new Dimension(64, 30));
				this.form_spinner005_5.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner005_5.setFont(this.form_spinner005_5.getFont().deriveFont(this.form_spinner005_5.getFont().getSize() + 3f));
				this.form_spinner005_5.setName("spinner005_5");
				this.form_fifthSpinnerNormal.add(this.form_spinner005_5, "cell 0 5");

				//---- form_spinner002_5 ----
				this.form_spinner002_5.setPreferredSize(new Dimension(64, 30));
				this.form_spinner002_5.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner002_5.setFont(this.form_spinner002_5.getFont().deriveFont(this.form_spinner002_5.getFont().getSize() + 3f));
				this.form_spinner002_5.setName("spinner002_5");
				this.form_fifthSpinnerNormal.add(this.form_spinner002_5, "cell 0 6");

				//---- form_spinner001_5 ----
				this.form_spinner001_5.setPreferredSize(new Dimension(64, 30));
				this.form_spinner001_5.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner001_5.setFont(this.form_spinner001_5.getFont().deriveFont(this.form_spinner001_5.getFont().getSize() + 3f));
				this.form_spinner001_5.setName("spinner001_5");
				this.form_fifthSpinnerNormal.add(this.form_spinner001_5, "cell 0 7");

				//---- form_vSpacer6 ----
				this.form_vSpacer6.setName("vSpacer6");
				this.form_fifthSpinnerNormal.add(this.form_vSpacer6, "cell 0 8");

				//---- form_spinner200_5 ----
				this.form_spinner200_5.setPreferredSize(new Dimension(64, 30));
				this.form_spinner200_5.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner200_5.setFont(this.form_spinner200_5.getFont().deriveFont(this.form_spinner200_5.getFont().getSize() + 3f));
				this.form_spinner200_5.setName("spinner200_5");
				this.form_fifthSpinnerNormal.add(this.form_spinner200_5, "cell 0 9");

				//---- form_spinner100_5 ----
				this.form_spinner100_5.setPreferredSize(new Dimension(64, 30));
				this.form_spinner100_5.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner100_5.setFont(this.form_spinner100_5.getFont().deriveFont(this.form_spinner100_5.getFont().getSize() + 3f));
				this.form_spinner100_5.setName("spinner100_5");
				this.form_fifthSpinnerNormal.add(this.form_spinner100_5, "cell 0 10");

				//---- form_spinner50_5 ----
				this.form_spinner50_5.setPreferredSize(new Dimension(64, 30));
				this.form_spinner50_5.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner50_5.setFont(this.form_spinner50_5.getFont().deriveFont(this.form_spinner50_5.getFont().getSize() + 3f));
				this.form_spinner50_5.setName("spinner50_5");
				this.form_fifthSpinnerNormal.add(this.form_spinner50_5, "cell 0 11");

				//---- form_spinner20_5 ----
				this.form_spinner20_5.setPreferredSize(new Dimension(64, 30));
				this.form_spinner20_5.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner20_5.setFont(this.form_spinner20_5.getFont().deriveFont(this.form_spinner20_5.getFont().getSize() + 3f));
				this.form_spinner20_5.setName("spinner20_5");
				this.form_fifthSpinnerNormal.add(this.form_spinner20_5, "cell 0 12");

				//---- form_spinner10_5 ----
				this.form_spinner10_5.setPreferredSize(new Dimension(64, 30));
				this.form_spinner10_5.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner10_5.setFont(this.form_spinner10_5.getFont().deriveFont(this.form_spinner10_5.getFont().getSize() + 3f));
				this.form_spinner10_5.setName("spinner10_5");
				this.form_fifthSpinnerNormal.add(this.form_spinner10_5, "cell 0 13");

				//---- form_spinner5_5 ----
				this.form_spinner5_5.setPreferredSize(new Dimension(64, 30));
				this.form_spinner5_5.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner5_5.setFont(this.form_spinner5_5.getFont().deriveFont(this.form_spinner5_5.getFont().getSize() + 3f));
				this.form_spinner5_5.setName("spinner5_5");
				this.form_fifthSpinnerNormal.add(this.form_spinner5_5, "cell 0 14");
			}
			this.form_cashPanel.add(this.form_fifthSpinnerNormal, "cell 5 0");

			//======== form_amountPanelNormal ========
			{
				this.form_amountPanelNormal.setPreferredSize(new Dimension(100, 450));
				this.form_amountPanelNormal.setFont(this.form_amountPanelNormal.getFont().deriveFont(this.form_amountPanelNormal.getFont().getSize() + 3f));
				this.form_amountPanelNormal.setMinimumSize(new Dimension(118, 450));
				this.form_amountPanelNormal.setName("amountPanelNormal");
				this.form_amountPanelNormal.setLayout(new MigLayout(
					"fill,align center center",
					// columns
					"[grow,fill]" +
					"[fill]",
					// rows
					null));

				//======== form_panel44 ========
				{
					this.form_panel44.setName("panel44");
					this.form_panel44.setLayout(new MigLayout(
						"fill,align center center",
						// columns
						"[grow,fill]",
						// rows
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]"));

					//---- form_twoEuroLabelAmount ----
					this.form_twoEuroLabelAmount.setText("0");
					this.form_twoEuroLabelAmount.setPreferredSize(new Dimension(56, 30));
					this.form_twoEuroLabelAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_twoEuroLabelAmount.setFont(this.form_twoEuroLabelAmount.getFont().deriveFont(this.form_twoEuroLabelAmount.getFont().getSize() + 3f));
					this.form_twoEuroLabelAmount.setMaximumSize(null);
					this.form_twoEuroLabelAmount.setName("twoEuroLabelAmount");
					this.form_panel44.add(this.form_twoEuroLabelAmount, "cell 0 0,grow");

					//---- form_oneEuroLabelAmount ----
					this.form_oneEuroLabelAmount.setText("0");
					this.form_oneEuroLabelAmount.setPreferredSize(new Dimension(56, 30));
					this.form_oneEuroLabelAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_oneEuroLabelAmount.setMaximumSize(null);
					this.form_oneEuroLabelAmount.setFont(this.form_oneEuroLabelAmount.getFont().deriveFont(this.form_oneEuroLabelAmount.getFont().getSize() + 3f));
					this.form_oneEuroLabelAmount.setName("oneEuroLabelAmount");
					this.form_panel44.add(this.form_oneEuroLabelAmount, "cell 0 1,grow");

					//---- form_fiftyCentLabelAmount ----
					this.form_fiftyCentLabelAmount.setText("0");
					this.form_fiftyCentLabelAmount.setPreferredSize(new Dimension(56, 30));
					this.form_fiftyCentLabelAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_fiftyCentLabelAmount.setMaximumSize(null);
					this.form_fiftyCentLabelAmount.setFont(this.form_fiftyCentLabelAmount.getFont().deriveFont(this.form_fiftyCentLabelAmount.getFont().getSize() + 3f));
					this.form_fiftyCentLabelAmount.setName("fiftyCentLabelAmount");
					this.form_panel44.add(this.form_fiftyCentLabelAmount, "cell 0 2,grow");

					//---- form_twentyCentLabelAmount ----
					this.form_twentyCentLabelAmount.setText("0");
					this.form_twentyCentLabelAmount.setPreferredSize(new Dimension(56, 30));
					this.form_twentyCentLabelAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_twentyCentLabelAmount.setMaximumSize(null);
					this.form_twentyCentLabelAmount.setFont(this.form_twentyCentLabelAmount.getFont().deriveFont(this.form_twentyCentLabelAmount.getFont().getSize() + 3f));
					this.form_twentyCentLabelAmount.setName("twentyCentLabelAmount");
					this.form_panel44.add(this.form_twentyCentLabelAmount, "cell 0 3,grow");

					//---- form_tenCentLabelAmount ----
					this.form_tenCentLabelAmount.setText("0");
					this.form_tenCentLabelAmount.setPreferredSize(new Dimension(56, 30));
					this.form_tenCentLabelAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_tenCentLabelAmount.setMaximumSize(null);
					this.form_tenCentLabelAmount.setFont(this.form_tenCentLabelAmount.getFont().deriveFont(this.form_tenCentLabelAmount.getFont().getSize() + 3f));
					this.form_tenCentLabelAmount.setName("tenCentLabelAmount");
					this.form_panel44.add(this.form_tenCentLabelAmount, "cell 0 4,grow");

					//---- form_fiveCentLabelAmount ----
					this.form_fiveCentLabelAmount.setText("0");
					this.form_fiveCentLabelAmount.setPreferredSize(new Dimension(56, 30));
					this.form_fiveCentLabelAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_fiveCentLabelAmount.setMaximumSize(null);
					this.form_fiveCentLabelAmount.setFont(this.form_fiveCentLabelAmount.getFont().deriveFont(this.form_fiveCentLabelAmount.getFont().getSize() + 3f));
					this.form_fiveCentLabelAmount.setName("fiveCentLabelAmount");
					this.form_panel44.add(this.form_fiveCentLabelAmount, "cell 0 5,grow");

					//---- form_twoCentLabelAmount ----
					this.form_twoCentLabelAmount.setText("0");
					this.form_twoCentLabelAmount.setPreferredSize(new Dimension(56, 30));
					this.form_twoCentLabelAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_twoCentLabelAmount.setMaximumSize(null);
					this.form_twoCentLabelAmount.setFont(this.form_twoCentLabelAmount.getFont().deriveFont(this.form_twoCentLabelAmount.getFont().getSize() + 3f));
					this.form_twoCentLabelAmount.setName("twoCentLabelAmount");
					this.form_panel44.add(this.form_twoCentLabelAmount, "cell 0 6,grow");

					//---- form_oneCentLabelAmount ----
					this.form_oneCentLabelAmount.setText("0");
					this.form_oneCentLabelAmount.setPreferredSize(new Dimension(56, 30));
					this.form_oneCentLabelAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_oneCentLabelAmount.setMaximumSize(null);
					this.form_oneCentLabelAmount.setFont(this.form_oneCentLabelAmount.getFont().deriveFont(this.form_oneCentLabelAmount.getFont().getSize() + 3f));
					this.form_oneCentLabelAmount.setName("oneCentLabelAmount");
					this.form_panel44.add(this.form_oneCentLabelAmount, "cell 0 7,grow");

					//---- form_vSpacer7 ----
					this.form_vSpacer7.setName("vSpacer7");
					this.form_panel44.add(this.form_vSpacer7, "cell 0 8");

					//---- form_twohundredEuroLabelAmount ----
					this.form_twohundredEuroLabelAmount.setText("0");
					this.form_twohundredEuroLabelAmount.setPreferredSize(new Dimension(56, 30));
					this.form_twohundredEuroLabelAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_twohundredEuroLabelAmount.setMaximumSize(null);
					this.form_twohundredEuroLabelAmount.setFont(this.form_twohundredEuroLabelAmount.getFont().deriveFont(this.form_twohundredEuroLabelAmount.getFont().getSize() + 3f));
					this.form_twohundredEuroLabelAmount.setName("twohundredEuroLabelAmount");
					this.form_panel44.add(this.form_twohundredEuroLabelAmount, "cell 0 9,grow");

					//---- form_onehundredEuroLabelAmount ----
					this.form_onehundredEuroLabelAmount.setText("0");
					this.form_onehundredEuroLabelAmount.setPreferredSize(new Dimension(56, 30));
					this.form_onehundredEuroLabelAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_onehundredEuroLabelAmount.setMaximumSize(null);
					this.form_onehundredEuroLabelAmount.setFont(this.form_onehundredEuroLabelAmount.getFont().deriveFont(this.form_onehundredEuroLabelAmount.getFont().getSize() + 3f));
					this.form_onehundredEuroLabelAmount.setName("onehundredEuroLabelAmount");
					this.form_panel44.add(this.form_onehundredEuroLabelAmount, "cell 0 10,grow");

					//---- form_fiftyEuroLabelAmount ----
					this.form_fiftyEuroLabelAmount.setText("0");
					this.form_fiftyEuroLabelAmount.setPreferredSize(new Dimension(56, 30));
					this.form_fiftyEuroLabelAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_fiftyEuroLabelAmount.setMaximumSize(null);
					this.form_fiftyEuroLabelAmount.setFont(this.form_fiftyEuroLabelAmount.getFont().deriveFont(this.form_fiftyEuroLabelAmount.getFont().getSize() + 3f));
					this.form_fiftyEuroLabelAmount.setName("fiftyEuroLabelAmount");
					this.form_panel44.add(this.form_fiftyEuroLabelAmount, "cell 0 11,grow");

					//---- form_twentyEuroLabelAmount ----
					this.form_twentyEuroLabelAmount.setText("0");
					this.form_twentyEuroLabelAmount.setPreferredSize(new Dimension(56, 30));
					this.form_twentyEuroLabelAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_twentyEuroLabelAmount.setMaximumSize(null);
					this.form_twentyEuroLabelAmount.setFont(this.form_twentyEuroLabelAmount.getFont().deriveFont(this.form_twentyEuroLabelAmount.getFont().getSize() + 3f));
					this.form_twentyEuroLabelAmount.setName("twentyEuroLabelAmount");
					this.form_panel44.add(this.form_twentyEuroLabelAmount, "cell 0 12,grow");

					//---- form_tenEuroLabelAmount ----
					this.form_tenEuroLabelAmount.setText("0");
					this.form_tenEuroLabelAmount.setPreferredSize(new Dimension(56, 30));
					this.form_tenEuroLabelAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_tenEuroLabelAmount.setMaximumSize(null);
					this.form_tenEuroLabelAmount.setFont(this.form_tenEuroLabelAmount.getFont().deriveFont(this.form_tenEuroLabelAmount.getFont().getSize() + 3f));
					this.form_tenEuroLabelAmount.setName("tenEuroLabelAmount");
					this.form_panel44.add(this.form_tenEuroLabelAmount, "cell 0 13,grow");

					//---- form_fiveEuroLabelAmount ----
					this.form_fiveEuroLabelAmount.setText("0");
					this.form_fiveEuroLabelAmount.setPreferredSize(new Dimension(56, 30));
					this.form_fiveEuroLabelAmount.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_fiveEuroLabelAmount.setMaximumSize(null);
					this.form_fiveEuroLabelAmount.setFont(this.form_fiveEuroLabelAmount.getFont().deriveFont(this.form_fiveEuroLabelAmount.getFont().getSize() + 3f));
					this.form_fiveEuroLabelAmount.setName("fiveEuroLabelAmount");
					this.form_panel44.add(this.form_fiveEuroLabelAmount, "cell 0 14,grow");
				}
				this.form_amountPanelNormal.add(this.form_panel44, "cell 0 0,grow");

				//======== form_panel45 ========
				{
					this.form_panel45.setMinimumSize(new Dimension(30, 525));
					this.form_panel45.setPreferredSize(new Dimension(30, 525));
					this.form_panel45.setName("panel45");
					this.form_panel45.setLayout(new MigLayout(
						"fill,align center center",
						// columns
						null,
						// rows
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]"));

					//---- form_twoEuroLabelX ----
					this.form_twoEuroLabelX.setText(" x");
					this.form_twoEuroLabelX.setPreferredSize(new Dimension(8, 30));
					this.form_twoEuroLabelX.setHorizontalAlignment(SwingConstants.LEFT);
					this.form_twoEuroLabelX.setMaximumSize(null);
					this.form_twoEuroLabelX.setFont(this.form_twoEuroLabelX.getFont().deriveFont(this.form_twoEuroLabelX.getFont().getSize() + 3f));
					this.form_twoEuroLabelX.setName("twoEuroLabelX");
					this.form_panel45.add(this.form_twoEuroLabelX, "cell 0 0,growx");

					//---- form_oneEuroLabelX ----
					this.form_oneEuroLabelX.setText(" x");
					this.form_oneEuroLabelX.setPreferredSize(new Dimension(8, 30));
					this.form_oneEuroLabelX.setHorizontalAlignment(SwingConstants.LEFT);
					this.form_oneEuroLabelX.setMaximumSize(null);
					this.form_oneEuroLabelX.setFont(this.form_oneEuroLabelX.getFont().deriveFont(this.form_oneEuroLabelX.getFont().getSize() + 3f));
					this.form_oneEuroLabelX.setName("oneEuroLabelX");
					this.form_panel45.add(this.form_oneEuroLabelX, "cell 0 1,growx");

					//---- form_fiftyCentLabelX ----
					this.form_fiftyCentLabelX.setText(" x");
					this.form_fiftyCentLabelX.setPreferredSize(new Dimension(8, 30));
					this.form_fiftyCentLabelX.setHorizontalAlignment(SwingConstants.LEFT);
					this.form_fiftyCentLabelX.setMaximumSize(null);
					this.form_fiftyCentLabelX.setFont(this.form_fiftyCentLabelX.getFont().deriveFont(this.form_fiftyCentLabelX.getFont().getSize() + 3f));
					this.form_fiftyCentLabelX.setName("fiftyCentLabelX");
					this.form_panel45.add(this.form_fiftyCentLabelX, "cell 0 2,growx");

					//---- form_twentyCentLabelX ----
					this.form_twentyCentLabelX.setText(" x");
					this.form_twentyCentLabelX.setPreferredSize(new Dimension(8, 30));
					this.form_twentyCentLabelX.setHorizontalAlignment(SwingConstants.LEFT);
					this.form_twentyCentLabelX.setMaximumSize(null);
					this.form_twentyCentLabelX.setFont(this.form_twentyCentLabelX.getFont().deriveFont(this.form_twentyCentLabelX.getFont().getSize() + 3f));
					this.form_twentyCentLabelX.setName("twentyCentLabelX");
					this.form_panel45.add(this.form_twentyCentLabelX, "cell 0 3,growx");

					//---- form_tenCentLabelX ----
					this.form_tenCentLabelX.setText(" x");
					this.form_tenCentLabelX.setPreferredSize(new Dimension(8, 30));
					this.form_tenCentLabelX.setHorizontalAlignment(SwingConstants.LEFT);
					this.form_tenCentLabelX.setMaximumSize(null);
					this.form_tenCentLabelX.setFont(this.form_tenCentLabelX.getFont().deriveFont(this.form_tenCentLabelX.getFont().getSize() + 3f));
					this.form_tenCentLabelX.setName("tenCentLabelX");
					this.form_panel45.add(this.form_tenCentLabelX, "cell 0 4,growx");

					//---- form_fiveCentLabelX ----
					this.form_fiveCentLabelX.setText(" x");
					this.form_fiveCentLabelX.setPreferredSize(new Dimension(8, 30));
					this.form_fiveCentLabelX.setHorizontalAlignment(SwingConstants.LEFT);
					this.form_fiveCentLabelX.setMaximumSize(null);
					this.form_fiveCentLabelX.setFont(this.form_fiveCentLabelX.getFont().deriveFont(this.form_fiveCentLabelX.getFont().getSize() + 3f));
					this.form_fiveCentLabelX.setName("fiveCentLabelX");
					this.form_panel45.add(this.form_fiveCentLabelX, "cell 0 5,growx");

					//---- form_twoCentLabelX ----
					this.form_twoCentLabelX.setText(" x");
					this.form_twoCentLabelX.setPreferredSize(new Dimension(8, 30));
					this.form_twoCentLabelX.setHorizontalAlignment(SwingConstants.LEFT);
					this.form_twoCentLabelX.setMaximumSize(null);
					this.form_twoCentLabelX.setFont(this.form_twoCentLabelX.getFont().deriveFont(this.form_twoCentLabelX.getFont().getSize() + 3f));
					this.form_twoCentLabelX.setName("twoCentLabelX");
					this.form_panel45.add(this.form_twoCentLabelX, "cell 0 6,growx");

					//---- form_oneCentLabelX ----
					this.form_oneCentLabelX.setText(" x");
					this.form_oneCentLabelX.setPreferredSize(new Dimension(8, 30));
					this.form_oneCentLabelX.setHorizontalAlignment(SwingConstants.LEFT);
					this.form_oneCentLabelX.setMaximumSize(null);
					this.form_oneCentLabelX.setFont(this.form_oneCentLabelX.getFont().deriveFont(this.form_oneCentLabelX.getFont().getSize() + 3f));
					this.form_oneCentLabelX.setName("oneCentLabelX");
					this.form_panel45.add(this.form_oneCentLabelX, "cell 0 7,growx");

					//---- form_vSpacer8 ----
					this.form_vSpacer8.setName("vSpacer8");
					this.form_panel45.add(this.form_vSpacer8, "cell 0 8");

					//---- form_twohundredEuroLabelX ----
					this.form_twohundredEuroLabelX.setText(" x");
					this.form_twohundredEuroLabelX.setPreferredSize(new Dimension(8, 30));
					this.form_twohundredEuroLabelX.setHorizontalAlignment(SwingConstants.LEFT);
					this.form_twohundredEuroLabelX.setMaximumSize(null);
					this.form_twohundredEuroLabelX.setFont(this.form_twohundredEuroLabelX.getFont().deriveFont(this.form_twohundredEuroLabelX.getFont().getSize() + 3f));
					this.form_twohundredEuroLabelX.setHorizontalTextPosition(SwingConstants.LEFT);
					this.form_twohundredEuroLabelX.setName("twohundredEuroLabelX");
					this.form_panel45.add(this.form_twohundredEuroLabelX, "cell 0 9,growx");

					//---- form_onehundredEuroLabelX ----
					this.form_onehundredEuroLabelX.setText(" x");
					this.form_onehundredEuroLabelX.setPreferredSize(new Dimension(8, 30));
					this.form_onehundredEuroLabelX.setHorizontalAlignment(SwingConstants.LEFT);
					this.form_onehundredEuroLabelX.setMaximumSize(null);
					this.form_onehundredEuroLabelX.setFont(this.form_onehundredEuroLabelX.getFont().deriveFont(this.form_onehundredEuroLabelX.getFont().getSize() + 3f));
					this.form_onehundredEuroLabelX.setHorizontalTextPosition(SwingConstants.LEFT);
					this.form_onehundredEuroLabelX.setName("onehundredEuroLabelX");
					this.form_panel45.add(this.form_onehundredEuroLabelX, "cell 0 10,growx");

					//---- form_fiftyEuroLabelX ----
					this.form_fiftyEuroLabelX.setText(" x");
					this.form_fiftyEuroLabelX.setPreferredSize(new Dimension(8, 30));
					this.form_fiftyEuroLabelX.setHorizontalAlignment(SwingConstants.LEFT);
					this.form_fiftyEuroLabelX.setMaximumSize(null);
					this.form_fiftyEuroLabelX.setFont(this.form_fiftyEuroLabelX.getFont().deriveFont(this.form_fiftyEuroLabelX.getFont().getSize() + 3f));
					this.form_fiftyEuroLabelX.setHorizontalTextPosition(SwingConstants.LEFT);
					this.form_fiftyEuroLabelX.setName("fiftyEuroLabelX");
					this.form_panel45.add(this.form_fiftyEuroLabelX, "cell 0 11,growx");

					//---- form_twentyEuroLabelX ----
					this.form_twentyEuroLabelX.setText(" x");
					this.form_twentyEuroLabelX.setPreferredSize(new Dimension(8, 30));
					this.form_twentyEuroLabelX.setHorizontalAlignment(SwingConstants.LEFT);
					this.form_twentyEuroLabelX.setMaximumSize(null);
					this.form_twentyEuroLabelX.setFont(this.form_twentyEuroLabelX.getFont().deriveFont(this.form_twentyEuroLabelX.getFont().getSize() + 3f));
					this.form_twentyEuroLabelX.setHorizontalTextPosition(SwingConstants.LEFT);
					this.form_twentyEuroLabelX.setName("twentyEuroLabelX");
					this.form_panel45.add(this.form_twentyEuroLabelX, "cell 0 12,growx");

					//---- form_tenEuroLabelX ----
					this.form_tenEuroLabelX.setText(" x");
					this.form_tenEuroLabelX.setPreferredSize(new Dimension(8, 30));
					this.form_tenEuroLabelX.setHorizontalAlignment(SwingConstants.LEFT);
					this.form_tenEuroLabelX.setMaximumSize(null);
					this.form_tenEuroLabelX.setFont(this.form_tenEuroLabelX.getFont().deriveFont(this.form_tenEuroLabelX.getFont().getSize() + 3f));
					this.form_tenEuroLabelX.setHorizontalTextPosition(SwingConstants.LEFT);
					this.form_tenEuroLabelX.setName("tenEuroLabelX");
					this.form_panel45.add(this.form_tenEuroLabelX, "cell 0 13,growx");

					//---- form_fiveEuroLabelX ----
					this.form_fiveEuroLabelX.setText(" x");
					this.form_fiveEuroLabelX.setPreferredSize(new Dimension(8, 30));
					this.form_fiveEuroLabelX.setHorizontalAlignment(SwingConstants.LEFT);
					this.form_fiveEuroLabelX.setMaximumSize(null);
					this.form_fiveEuroLabelX.setFont(this.form_fiveEuroLabelX.getFont().deriveFont(this.form_fiveEuroLabelX.getFont().getSize() + 3f));
					this.form_fiveEuroLabelX.setHorizontalTextPosition(SwingConstants.LEFT);
					this.form_fiveEuroLabelX.setName("fiveEuroLabelX");
					this.form_panel45.add(this.form_fiveEuroLabelX, "cell 0 14,growx");
				}
				this.form_amountPanelNormal.add(this.form_panel45, "cell 1 0,grow");
			}
			this.form_cashPanel.add(this.form_amountPanelNormal, "cell 6 0,grow");

			//======== form_valueRowPanelNormal ========
			{
				this.form_valueRowPanelNormal.setPreferredSize(new Dimension(100, 500));
				this.form_valueRowPanelNormal.setFont(this.form_valueRowPanelNormal.getFont().deriveFont(this.form_valueRowPanelNormal.getFont().getSize() + 3f));
				this.form_valueRowPanelNormal.setName("valueRowPanelNormal");
				this.form_valueRowPanelNormal.setLayout(new MigLayout(
					"fill,align center center",
					// columns
					"[]" +
					"[]",
					// rows
					null));

				//======== form_panel46 ========
				{
					this.form_panel46.setMinimumSize(null);
					this.form_panel46.setPreferredSize(null);
					this.form_panel46.setName("panel46");
					this.form_panel46.setLayout(new MigLayout(
						"fill,align center center",
						// columns
						"[grow]",
						// rows
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]"));

					//---- form_twoEuroLabelValue ----
					this.form_twoEuroLabelValue.setText("0");
					this.form_twoEuroLabelValue.setPreferredSize(new Dimension(56, 30));
					this.form_twoEuroLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_twoEuroLabelValue.setMaximumSize(null);
					this.form_twoEuroLabelValue.setFont(this.form_twoEuroLabelValue.getFont().deriveFont(this.form_twoEuroLabelValue.getFont().getSize() + 3f));
					this.form_twoEuroLabelValue.setName("twoEuroLabelValue");
					this.form_panel46.add(this.form_twoEuroLabelValue, "cell 0 0");

					//---- form_oneEuroLabelValue ----
					this.form_oneEuroLabelValue.setText("0");
					this.form_oneEuroLabelValue.setPreferredSize(new Dimension(56, 30));
					this.form_oneEuroLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_oneEuroLabelValue.setMaximumSize(null);
					this.form_oneEuroLabelValue.setFont(this.form_oneEuroLabelValue.getFont().deriveFont(this.form_oneEuroLabelValue.getFont().getSize() + 3f));
					this.form_oneEuroLabelValue.setName("oneEuroLabelValue");
					this.form_panel46.add(this.form_oneEuroLabelValue, "cell 0 1");

					//---- form_fiftyCentLabelValue ----
					this.form_fiftyCentLabelValue.setText("0");
					this.form_fiftyCentLabelValue.setPreferredSize(new Dimension(56, 30));
					this.form_fiftyCentLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_fiftyCentLabelValue.setMaximumSize(null);
					this.form_fiftyCentLabelValue.setFont(this.form_fiftyCentLabelValue.getFont().deriveFont(this.form_fiftyCentLabelValue.getFont().getSize() + 3f));
					this.form_fiftyCentLabelValue.setName("fiftyCentLabelValue");
					this.form_panel46.add(this.form_fiftyCentLabelValue, "cell 0 2");

					//---- form_twentyCentLabelValue ----
					this.form_twentyCentLabelValue.setText("0");
					this.form_twentyCentLabelValue.setPreferredSize(new Dimension(56, 30));
					this.form_twentyCentLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_twentyCentLabelValue.setMaximumSize(null);
					this.form_twentyCentLabelValue.setFont(this.form_twentyCentLabelValue.getFont().deriveFont(this.form_twentyCentLabelValue.getFont().getSize() + 3f));
					this.form_twentyCentLabelValue.setName("twentyCentLabelValue");
					this.form_panel46.add(this.form_twentyCentLabelValue, "cell 0 3");

					//---- form_tenCentLabelValue ----
					this.form_tenCentLabelValue.setText("0");
					this.form_tenCentLabelValue.setPreferredSize(new Dimension(56, 30));
					this.form_tenCentLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_tenCentLabelValue.setMaximumSize(null);
					this.form_tenCentLabelValue.setFont(this.form_tenCentLabelValue.getFont().deriveFont(this.form_tenCentLabelValue.getFont().getSize() + 3f));
					this.form_tenCentLabelValue.setName("tenCentLabelValue");
					this.form_panel46.add(this.form_tenCentLabelValue, "cell 0 4");

					//---- form_fiveCentLabelValue ----
					this.form_fiveCentLabelValue.setText("0");
					this.form_fiveCentLabelValue.setPreferredSize(new Dimension(56, 30));
					this.form_fiveCentLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_fiveCentLabelValue.setMaximumSize(null);
					this.form_fiveCentLabelValue.setFont(this.form_fiveCentLabelValue.getFont().deriveFont(this.form_fiveCentLabelValue.getFont().getSize() + 3f));
					this.form_fiveCentLabelValue.setName("fiveCentLabelValue");
					this.form_panel46.add(this.form_fiveCentLabelValue, "cell 0 5");

					//---- form_twoCentLabelValue ----
					this.form_twoCentLabelValue.setText("0");
					this.form_twoCentLabelValue.setPreferredSize(new Dimension(56, 30));
					this.form_twoCentLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_twoCentLabelValue.setMaximumSize(null);
					this.form_twoCentLabelValue.setFont(this.form_twoCentLabelValue.getFont().deriveFont(this.form_twoCentLabelValue.getFont().getSize() + 3f));
					this.form_twoCentLabelValue.setName("twoCentLabelValue");
					this.form_panel46.add(this.form_twoCentLabelValue, "cell 0 6");

					//---- form_oneCentLabelValue ----
					this.form_oneCentLabelValue.setText("0");
					this.form_oneCentLabelValue.setPreferredSize(new Dimension(56, 30));
					this.form_oneCentLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_oneCentLabelValue.setMaximumSize(null);
					this.form_oneCentLabelValue.setFont(this.form_oneCentLabelValue.getFont().deriveFont(this.form_oneCentLabelValue.getFont().getSize() + 3f));
					this.form_oneCentLabelValue.setName("oneCentLabelValue");
					this.form_panel46.add(this.form_oneCentLabelValue, "cell 0 7");

					//---- form_vSpacer9 ----
					this.form_vSpacer9.setName("vSpacer9");
					this.form_panel46.add(this.form_vSpacer9, "cell 0 8");

					//---- form_twohundredEuroLabelValue ----
					this.form_twohundredEuroLabelValue.setText("0");
					this.form_twohundredEuroLabelValue.setPreferredSize(new Dimension(56, 30));
					this.form_twohundredEuroLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_twohundredEuroLabelValue.setMaximumSize(null);
					this.form_twohundredEuroLabelValue.setFont(this.form_twohundredEuroLabelValue.getFont().deriveFont(this.form_twohundredEuroLabelValue.getFont().getSize() + 3f));
					this.form_twohundredEuroLabelValue.setName("twohundredEuroLabelValue");
					this.form_panel46.add(this.form_twohundredEuroLabelValue, "cell 0 9");

					//---- form_onehundredEuroLabelValue ----
					this.form_onehundredEuroLabelValue.setText("0");
					this.form_onehundredEuroLabelValue.setPreferredSize(new Dimension(56, 30));
					this.form_onehundredEuroLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_onehundredEuroLabelValue.setMaximumSize(null);
					this.form_onehundredEuroLabelValue.setFont(this.form_onehundredEuroLabelValue.getFont().deriveFont(this.form_onehundredEuroLabelValue.getFont().getSize() + 3f));
					this.form_onehundredEuroLabelValue.setName("onehundredEuroLabelValue");
					this.form_panel46.add(this.form_onehundredEuroLabelValue, "cell 0 10");

					//---- form_fiftyEuroLabelValue ----
					this.form_fiftyEuroLabelValue.setText("0");
					this.form_fiftyEuroLabelValue.setPreferredSize(new Dimension(56, 30));
					this.form_fiftyEuroLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_fiftyEuroLabelValue.setMaximumSize(null);
					this.form_fiftyEuroLabelValue.setFont(this.form_fiftyEuroLabelValue.getFont().deriveFont(this.form_fiftyEuroLabelValue.getFont().getSize() + 3f));
					this.form_fiftyEuroLabelValue.setName("fiftyEuroLabelValue");
					this.form_panel46.add(this.form_fiftyEuroLabelValue, "cell 0 11");

					//---- form_twentyEuroLabelValue ----
					this.form_twentyEuroLabelValue.setText("0");
					this.form_twentyEuroLabelValue.setPreferredSize(new Dimension(56, 30));
					this.form_twentyEuroLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_twentyEuroLabelValue.setMaximumSize(null);
					this.form_twentyEuroLabelValue.setFont(this.form_twentyEuroLabelValue.getFont().deriveFont(this.form_twentyEuroLabelValue.getFont().getSize() + 3f));
					this.form_twentyEuroLabelValue.setName("twentyEuroLabelValue");
					this.form_panel46.add(this.form_twentyEuroLabelValue, "cell 0 12");

					//---- form_tenEuroLabelValue ----
					this.form_tenEuroLabelValue.setText("0");
					this.form_tenEuroLabelValue.setPreferredSize(new Dimension(56, 30));
					this.form_tenEuroLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_tenEuroLabelValue.setMaximumSize(null);
					this.form_tenEuroLabelValue.setFont(this.form_tenEuroLabelValue.getFont().deriveFont(this.form_tenEuroLabelValue.getFont().getSize() + 3f));
					this.form_tenEuroLabelValue.setName("tenEuroLabelValue");
					this.form_panel46.add(this.form_tenEuroLabelValue, "cell 0 13");

					//---- form_fiveEuroLabelValue ----
					this.form_fiveEuroLabelValue.setText("0");
					this.form_fiveEuroLabelValue.setPreferredSize(new Dimension(56, 30));
					this.form_fiveEuroLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
					this.form_fiveEuroLabelValue.setMaximumSize(null);
					this.form_fiveEuroLabelValue.setFont(this.form_fiveEuroLabelValue.getFont().deriveFont(this.form_fiveEuroLabelValue.getFont().getSize() + 3f));
					this.form_fiveEuroLabelValue.setName("fiveEuroLabelValue");
					this.form_panel46.add(this.form_fiveEuroLabelValue, "cell 0 14");
				}
				this.form_valueRowPanelNormal.add(this.form_panel46, "cell 0 0,grow");

				//======== form_panel47 ========
				{
					this.form_panel47.setName("panel47");
					this.form_panel47.setLayout(new MigLayout(
						"fill,align center center",
						// columns
						"[fill]",
						// rows
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]"));

					//---- form_twoEuroLabelSymbol ----
					this.form_twoEuroLabelSymbol.setText(" \u20ac");
					this.form_twoEuroLabelSymbol.setPreferredSize(new Dimension(12, 30));
					this.form_twoEuroLabelSymbol.setHorizontalAlignment(SwingConstants.LEFT);
					this.form_twoEuroLabelSymbol.setMaximumSize(null);
					this.form_twoEuroLabelSymbol.setFont(this.form_twoEuroLabelSymbol.getFont().deriveFont(this.form_twoEuroLabelSymbol.getFont().getSize() + 3f));
					this.form_twoEuroLabelSymbol.setName("twoEuroLabelSymbol");
					this.form_panel47.add(this.form_twoEuroLabelSymbol, "cell 0 0");

					//---- form_oneEuroLabelSymbol ----
					this.form_oneEuroLabelSymbol.setText(" \u20ac");
					this.form_oneEuroLabelSymbol.setPreferredSize(new Dimension(12, 30));
					this.form_oneEuroLabelSymbol.setHorizontalAlignment(SwingConstants.LEFT);
					this.form_oneEuroLabelSymbol.setMaximumSize(null);
					this.form_oneEuroLabelSymbol.setFont(this.form_oneEuroLabelSymbol.getFont().deriveFont(this.form_oneEuroLabelSymbol.getFont().getSize() + 3f));
					this.form_oneEuroLabelSymbol.setName("oneEuroLabelSymbol");
					this.form_panel47.add(this.form_oneEuroLabelSymbol, "cell 0 1");

					//---- form_fiftyCentLabelSymbol ----
					this.form_fiftyCentLabelSymbol.setText(" \u20ac");
					this.form_fiftyCentLabelSymbol.setPreferredSize(new Dimension(12, 30));
					this.form_fiftyCentLabelSymbol.setHorizontalAlignment(SwingConstants.LEFT);
					this.form_fiftyCentLabelSymbol.setMaximumSize(null);
					this.form_fiftyCentLabelSymbol.setFont(this.form_fiftyCentLabelSymbol.getFont().deriveFont(this.form_fiftyCentLabelSymbol.getFont().getSize() + 3f));
					this.form_fiftyCentLabelSymbol.setName("fiftyCentLabelSymbol");
					this.form_panel47.add(this.form_fiftyCentLabelSymbol, "cell 0 2");

					//---- form_twentyCentLabelSymbol ----
					this.form_twentyCentLabelSymbol.setText(" \u20ac");
					this.form_twentyCentLabelSymbol.setPreferredSize(new Dimension(12, 30));
					this.form_twentyCentLabelSymbol.setHorizontalAlignment(SwingConstants.LEFT);
					this.form_twentyCentLabelSymbol.setMaximumSize(null);
					this.form_twentyCentLabelSymbol.setFont(this.form_twentyCentLabelSymbol.getFont().deriveFont(this.form_twentyCentLabelSymbol.getFont().getSize() + 3f));
					this.form_twentyCentLabelSymbol.setName("twentyCentLabelSymbol");
					this.form_panel47.add(this.form_twentyCentLabelSymbol, "cell 0 3");

					//---- form_tenCentLabelSymbol ----
					this.form_tenCentLabelSymbol.setText(" \u20ac");
					this.form_tenCentLabelSymbol.setPreferredSize(new Dimension(12, 30));
					this.form_tenCentLabelSymbol.setHorizontalAlignment(SwingConstants.LEFT);
					this.form_tenCentLabelSymbol.setMaximumSize(null);
					this.form_tenCentLabelSymbol.setFont(this.form_tenCentLabelSymbol.getFont().deriveFont(this.form_tenCentLabelSymbol.getFont().getSize() + 3f));
					this.form_tenCentLabelSymbol.setName("tenCentLabelSymbol");
					this.form_panel47.add(this.form_tenCentLabelSymbol, "cell 0 4");

					//---- form_fiveCentLabelSymbol ----
					this.form_fiveCentLabelSymbol.setText(" \u20ac");
					this.form_fiveCentLabelSymbol.setPreferredSize(new Dimension(12, 30));
					this.form_fiveCentLabelSymbol.setHorizontalAlignment(SwingConstants.LEFT);
					this.form_fiveCentLabelSymbol.setMaximumSize(null);
					this.form_fiveCentLabelSymbol.setFont(this.form_fiveCentLabelSymbol.getFont().deriveFont(this.form_fiveCentLabelSymbol.getFont().getSize() + 3f));
					this.form_fiveCentLabelSymbol.setName("fiveCentLabelSymbol");
					this.form_panel47.add(this.form_fiveCentLabelSymbol, "cell 0 5");

					//---- form_twoCentLabelSymbol ----
					this.form_twoCentLabelSymbol.setText(" \u20ac");
					this.form_twoCentLabelSymbol.setPreferredSize(new Dimension(12, 30));
					this.form_twoCentLabelSymbol.setHorizontalAlignment(SwingConstants.LEFT);
					this.form_twoCentLabelSymbol.setMaximumSize(null);
					this.form_twoCentLabelSymbol.setFont(this.form_twoCentLabelSymbol.getFont().deriveFont(this.form_twoCentLabelSymbol.getFont().getSize() + 3f));
					this.form_twoCentLabelSymbol.setName("twoCentLabelSymbol");
					this.form_panel47.add(this.form_twoCentLabelSymbol, "cell 0 6");

					//---- form_oneCentLabelSymbol ----
					this.form_oneCentLabelSymbol.setText(" \u20ac");
					this.form_oneCentLabelSymbol.setPreferredSize(new Dimension(12, 30));
					this.form_oneCentLabelSymbol.setHorizontalAlignment(SwingConstants.LEFT);
					this.form_oneCentLabelSymbol.setMaximumSize(null);
					this.form_oneCentLabelSymbol.setFont(this.form_oneCentLabelSymbol.getFont().deriveFont(this.form_oneCentLabelSymbol.getFont().getSize() + 3f));
					this.form_oneCentLabelSymbol.setName("oneCentLabelSymbol");
					this.form_panel47.add(this.form_oneCentLabelSymbol, "cell 0 7");

					//---- form_vSpacer10 ----
					this.form_vSpacer10.setName("vSpacer10");
					this.form_panel47.add(this.form_vSpacer10, "cell 0 8");

					//---- form_twohundredEuroLabelSymbol ----
					this.form_twohundredEuroLabelSymbol.setText(" \u20ac");
					this.form_twohundredEuroLabelSymbol.setPreferredSize(new Dimension(12, 30));
					this.form_twohundredEuroLabelSymbol.setHorizontalAlignment(SwingConstants.LEFT);
					this.form_twohundredEuroLabelSymbol.setMaximumSize(null);
					this.form_twohundredEuroLabelSymbol.setFont(this.form_twohundredEuroLabelSymbol.getFont().deriveFont(this.form_twohundredEuroLabelSymbol.getFont().getSize() + 3f));
					this.form_twohundredEuroLabelSymbol.setName("twohundredEuroLabelSymbol");
					this.form_panel47.add(this.form_twohundredEuroLabelSymbol, "cell 0 9");

					//---- form_onehundredEuroLabelSymbol ----
					this.form_onehundredEuroLabelSymbol.setText(" \u20ac");
					this.form_onehundredEuroLabelSymbol.setPreferredSize(new Dimension(12, 30));
					this.form_onehundredEuroLabelSymbol.setHorizontalAlignment(SwingConstants.LEFT);
					this.form_onehundredEuroLabelSymbol.setMaximumSize(null);
					this.form_onehundredEuroLabelSymbol.setFont(this.form_onehundredEuroLabelSymbol.getFont().deriveFont(this.form_onehundredEuroLabelSymbol.getFont().getSize() + 3f));
					this.form_onehundredEuroLabelSymbol.setName("onehundredEuroLabelSymbol");
					this.form_panel47.add(this.form_onehundredEuroLabelSymbol, "cell 0 10");

					//---- form_fiftyEuroLabelSymbol ----
					this.form_fiftyEuroLabelSymbol.setText(" \u20ac");
					this.form_fiftyEuroLabelSymbol.setPreferredSize(new Dimension(12, 30));
					this.form_fiftyEuroLabelSymbol.setHorizontalAlignment(SwingConstants.LEFT);
					this.form_fiftyEuroLabelSymbol.setMaximumSize(null);
					this.form_fiftyEuroLabelSymbol.setFont(this.form_fiftyEuroLabelSymbol.getFont().deriveFont(this.form_fiftyEuroLabelSymbol.getFont().getSize() + 3f));
					this.form_fiftyEuroLabelSymbol.setName("fiftyEuroLabelSymbol");
					this.form_panel47.add(this.form_fiftyEuroLabelSymbol, "cell 0 11");

					//---- form_twentyEuroLabelSymbol ----
					this.form_twentyEuroLabelSymbol.setText(" \u20ac");
					this.form_twentyEuroLabelSymbol.setPreferredSize(new Dimension(12, 30));
					this.form_twentyEuroLabelSymbol.setHorizontalAlignment(SwingConstants.LEFT);
					this.form_twentyEuroLabelSymbol.setMaximumSize(null);
					this.form_twentyEuroLabelSymbol.setFont(this.form_twentyEuroLabelSymbol.getFont().deriveFont(this.form_twentyEuroLabelSymbol.getFont().getSize() + 3f));
					this.form_twentyEuroLabelSymbol.setName("twentyEuroLabelSymbol");
					this.form_panel47.add(this.form_twentyEuroLabelSymbol, "cell 0 12");

					//---- form_tenEuroLabelSymbol ----
					this.form_tenEuroLabelSymbol.setText(" \u20ac");
					this.form_tenEuroLabelSymbol.setPreferredSize(new Dimension(12, 30));
					this.form_tenEuroLabelSymbol.setHorizontalAlignment(SwingConstants.LEFT);
					this.form_tenEuroLabelSymbol.setMaximumSize(null);
					this.form_tenEuroLabelSymbol.setFont(this.form_tenEuroLabelSymbol.getFont().deriveFont(this.form_tenEuroLabelSymbol.getFont().getSize() + 3f));
					this.form_tenEuroLabelSymbol.setName("tenEuroLabelSymbol");
					this.form_panel47.add(this.form_tenEuroLabelSymbol, "cell 0 13");

					//---- form_fiveEuroLabelSymbol ----
					this.form_fiveEuroLabelSymbol.setText(" \u20ac");
					this.form_fiveEuroLabelSymbol.setPreferredSize(new Dimension(12, 30));
					this.form_fiveEuroLabelSymbol.setHorizontalAlignment(SwingConstants.LEFT);
					this.form_fiveEuroLabelSymbol.setMaximumSize(null);
					this.form_fiveEuroLabelSymbol.setFont(this.form_fiveEuroLabelSymbol.getFont().deriveFont(this.form_fiveEuroLabelSymbol.getFont().getSize() + 3f));
					this.form_fiveEuroLabelSymbol.setName("fiveEuroLabelSymbol");
					this.form_panel47.add(this.form_fiveEuroLabelSymbol, "cell 0 14");
				}
				this.form_valueRowPanelNormal.add(this.form_panel47, "cell 1 0,grow");
			}
			this.form_cashPanel.add(this.form_valueRowPanelNormal, "cell 7 0,grow");
		}
		contentPane.add(this.form_cashPanel, "cell 1 1");

		//======== form_topPanel ========
		{
			this.form_topPanel.setFocusable(false);
			this.form_topPanel.setEnabled(false);
			this.form_topPanel.setRequestFocusEnabled(false);
			this.form_topPanel.setVerifyInputWhenFocusTarget(false);
			this.form_topPanel.setAutoscrolls(true);
			this.form_topPanel.setMinimumSize(null);
			this.form_topPanel.setPreferredSize(null);
			this.form_topPanel.setMaximumSize(null);
			this.form_topPanel.setName("topPanel");
			this.form_topPanel.setLayout(new MigLayout(
				"fill,insets 0,hidemode 3,gap 5 5",
				// columns
				"[left]" +
				"[left]" +
				"[200:200,left]" +
				"[left]",
				// rows
				"[fill]"));

			//======== form_registerPanel ========
			{
				this.form_registerPanel.setBorder(new TitledBorder("register"));
				this.form_registerPanel.setEnabled(false);
				this.form_registerPanel.setMinimumSize(null);
				this.form_registerPanel.setName("buttonPanel");
				this.form_registerPanel.setPreferredSize(null);
				this.form_registerPanel.setMaximumSize(null);
				this.form_registerPanel.setLayout(new MigLayout(
					"fill,align center center",
					// columns
					"[]" +
					"[]",
					// rows
					"[grow,fill]"));

				//---- form_fullRegisterSpinner ----
				this.form_fullRegisterSpinner.setModel(new SpinnerNumberModel(2, 0, 2, 1));
				this.form_fullRegisterSpinner.setMinimumSize(new Dimension(100, 30));
				this.form_fullRegisterSpinner.setPreferredSize(new Dimension(100, 30));
				this.form_fullRegisterSpinner.setName("fullRegisterSpinner");
				this.form_registerPanel.add(this.form_fullRegisterSpinner, "cell 0 0,align trailing center,grow 0 0");

				//---- form_fullRegisterLabel ----
				this.form_fullRegisterLabel.setText("full registers");
				this.form_fullRegisterLabel.setHorizontalAlignment(SwingConstants.CENTER);
				this.form_fullRegisterLabel.setEnabled(false);
				this.form_fullRegisterLabel.setFont(this.form_fullRegisterLabel.getFont().deriveFont(this.form_fullRegisterLabel.getFont().getSize() + 3f));
				this.form_fullRegisterLabel.setName("fullRegisterLabel");
				this.form_registerPanel.add(this.form_fullRegisterLabel, "cell 1 0");
			}
			this.form_topPanel.add(this.form_registerPanel, "cell 0 0");

			//======== form_buttonPanel ========
			{
				this.form_buttonPanel.setBorder(new TitledBorder("action"));
				this.form_buttonPanel.setPreferredSize(null);
				this.form_buttonPanel.setFocusable(false);
				this.form_buttonPanel.setEnabled(false);
				this.form_buttonPanel.setMinimumSize(new Dimension(700, 0));
				this.form_buttonPanel.setMaximumSize(null);
				this.form_buttonPanel.setName("buttonPanel");
				this.form_buttonPanel.setLayout(new FlowLayout());

				//---- form_resetBtn ----
				this.form_resetBtn.setText("RESET");
				this.form_resetBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				this.form_resetBtn.setFont(this.form_resetBtn.getFont().deriveFont(this.form_resetBtn.getFont().getSize() + 3f));
				this.form_resetBtn.setName("resetBtn");
				this.form_resetBtn.addActionListener(e -> resetBtnActionPerformed(e));
				this.form_buttonPanel.add(this.form_resetBtn);

				//---- form_separator1 ----
				this.form_separator1.setOrientation(SwingConstants.VERTICAL);
				this.form_separator1.setPreferredSize(new Dimension(3, 10));
				this.form_separator1.setRequestFocusEnabled(false);
				this.form_separator1.setBackground(Color.white);
				this.form_separator1.setName("separator1");
				this.form_buttonPanel.add(this.form_separator1);

				//---- form_registerBtn ----
				this.form_registerBtn.setText("register");
				this.form_registerBtn.setFont(this.form_registerBtn.getFont().deriveFont(this.form_registerBtn.getFont().getSize() + 3f));
				this.form_registerBtn.setName("registerBtn");
				this.form_registerBtn.addActionListener(e -> registerBtnActionPerformed(e));
				this.form_buttonPanel.add(this.form_registerBtn);

				//---- form_pdfBtn ----
				this.form_pdfBtn.setText("pdf");
				this.form_pdfBtn.setFont(this.form_pdfBtn.getFont().deriveFont(this.form_pdfBtn.getFont().getSize() + 3f));
				this.form_pdfBtn.setName("pdfBtn");
				this.form_pdfBtn.addActionListener(e -> pdfBtnActionPerformed(e));
				this.form_buttonPanel.add(this.form_pdfBtn);

				//---- form_ausrechnenBtn2 ----
				this.form_ausrechnenBtn2.setText("calculate");
				this.form_ausrechnenBtn2.setFont(this.form_ausrechnenBtn2.getFont().deriveFont(this.form_ausrechnenBtn2.getFont().getSize() + 3f));
				this.form_ausrechnenBtn2.setDoubleBuffered(true);
				this.form_ausrechnenBtn2.setOpaque(true);
				this.form_ausrechnenBtn2.setSelected(true);
				this.form_ausrechnenBtn2.setBackground(new Color(0x6699ff));
				this.form_ausrechnenBtn2.setName("ausrechnenBtn2");
				this.form_ausrechnenBtn2.addActionListener(e -> calculateBtnActionPerformed(e));
				this.form_buttonPanel.add(this.form_ausrechnenBtn2);

				//---- form_separator2 ----
				this.form_separator2.setOrientation(SwingConstants.VERTICAL);
				this.form_separator2.setPreferredSize(new Dimension(3, 10));
				this.form_separator2.setRequestFocusEnabled(false);
				this.form_separator2.setBackground(Color.white);
				this.form_separator2.setName("separator2");
				this.form_buttonPanel.add(this.form_separator2);

				//---- form_debitBtnNew ----
				this.form_debitBtnNew.setText("debit");
				this.form_debitBtnNew.setFont(this.form_debitBtnNew.getFont().deriveFont(this.form_debitBtnNew.getFont().getSize() + 3f));
				this.form_debitBtnNew.setName("debitBtnNew");
				this.form_debitBtnNew.addActionListener(e -> debitBtnNewActionPerformed(e));
				this.form_buttonPanel.add(this.form_debitBtnNew);

				//---- form_separator3 ----
				this.form_separator3.setOrientation(SwingConstants.VERTICAL);
				this.form_separator3.setPreferredSize(new Dimension(3, 10));
				this.form_separator3.setRequestFocusEnabled(false);
				this.form_separator3.setBackground(Color.white);
				this.form_separator3.setName("separator3");
				this.form_buttonPanel.add(this.form_separator3);

				//---- form_dateBtn ----
				this.form_dateBtn.setText("date");
				this.form_dateBtn.setFont(this.form_dateBtn.getFont().deriveFont(this.form_dateBtn.getFont().getSize() + 3f));
				this.form_dateBtn.setName("dateBtn");
				this.form_dateBtn.addActionListener(e -> dateBtnActionPerformed(e));
				this.form_buttonPanel.add(this.form_dateBtn);
			}
			this.form_topPanel.add(this.form_buttonPanel, "cell 1 0,growx");

			//======== form_datePanel ========
			{
				this.form_datePanel.setBorder(new TitledBorder("date"));
				this.form_datePanel.setPreferredSize(new Dimension(200, 0));
				this.form_datePanel.setEnabled(false);
				this.form_datePanel.setFocusable(false);
				this.form_datePanel.setMinimumSize(null);
				this.form_datePanel.setMaximumSize(null);
				this.form_datePanel.setName("datePanel");
				this.form_datePanel.setLayout(new FlowLayout());

				//---- form_dateSpinner ----
				this.form_dateSpinner.setDoubleBuffered(true);
				this.form_dateSpinner.setModel(new SpinnerDateModel(new java.util.Date((System.currentTimeMillis()/60000)*60000), null, null, java.util.Calendar.MINUTE));
				this.form_dateSpinner.setMinimumSize(new Dimension(180, 30));
				this.form_dateSpinner.setPreferredSize(new Dimension(180, 30));
				this.form_dateSpinner.setFont(this.form_dateSpinner.getFont().deriveFont(this.form_dateSpinner.getFont().getSize() + 3f));
				this.form_dateSpinner.setName("dateSpinner");
				this.form_datePanel.add(this.form_dateSpinner);
			}
			this.form_topPanel.add(this.form_datePanel, "cell 2 0");

			//======== form_debitPanel ========
			{
				this.form_debitPanel.setBorder(new TitledBorder("debit"));
				this.form_debitPanel.setRequestFocusEnabled(false);
				this.form_debitPanel.setVerifyInputWhenFocusTarget(false);
				this.form_debitPanel.setPreferredSize(null);
				this.form_debitPanel.setMinimumSize(null);
				this.form_debitPanel.setEnabled(false);
				this.form_debitPanel.setMaximumSize(null);
				this.form_debitPanel.setName("debitPanel");
				this.form_debitPanel.setLayout(new FlowLayout());

				//---- form_debitTextField ----
				this.form_debitTextField.setMinimumSize(new Dimension(200, 30));
				this.form_debitTextField.setPreferredSize(new Dimension(200, 30));
				this.form_debitTextField.setOpaque(true);
				this.form_debitTextField.setFont(this.form_debitTextField.getFont().deriveFont(this.form_debitTextField.getFont().getSize() + 3f));
				this.form_debitTextField.setText("0");
				this.form_debitTextField.setName("debitTextField");
				this.form_debitPanel.add(this.form_debitTextField);

				//---- form_label69 ----
				this.form_label69.setText(" \u20ac");
				this.form_label69.setFocusable(false);
				this.form_label69.setRequestFocusEnabled(false);
				this.form_label69.setVerifyInputWhenFocusTarget(false);
				this.form_label69.setInheritsPopupMenu(false);
				this.form_label69.setMinimumSize(new Dimension(12, 30));
				this.form_label69.setPreferredSize(new Dimension(12, 30));
				this.form_label69.setMaximumSize(new Dimension(12, 30));
				this.form_label69.setName("label69");
				this.form_debitPanel.add(this.form_label69);
			}
			this.form_topPanel.add(this.form_debitPanel, "cell 3 0");
		}
		contentPane.add(this.form_topPanel, "cell 0 0 2 1,grow");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner non-commercial license
	protected JMenuBar form_menuBar1;
	protected JMenu form_menu1;
	protected JMenuItem form_settingsBtn;
	protected JMenuItem form_exitBtn;
	protected JPanel form_bottomPanel;
	protected JPanel form_panel4;
	protected JLabel form_extraChangeIntermediateLabel;
	protected JLabel form_intermediateValueCoinsLabel;
	protected JLabel form_intermediateValueNotesLabel;
	protected JPanel form_panel6;
	protected JLabel form_differenceValueLabel;
	protected JLabel form_totalValueLabel;
	protected JPanel form_kassensturzErweiterungPanel;
	protected JPanel form_rollPanel;
	protected JPanel form_panel37;
	protected JLabel form_twoEuroRollValue;
	protected JLabel form_oneEuroRollValue;
	protected JLabel form_fiftyCentRollValue;
	protected JLabel form_twentyCentRollValue;
	protected JLabel form_tenCentRollValue;
	protected JLabel form_fiveCentRollValue;
	protected JLabel form_twoCentRollValue;
	protected JLabel form_oneCentRollValue;
	protected JPanel form_panel36;
	protected JLabel form_twoEuroRollAmount;
	protected JLabel form_oneEuroRollAmount;
	protected JLabel form_fiftyCentRollAmount;
	protected JLabel form_twentyCentRollAmount;
	protected JLabel form_tenCentRollAmount;
	protected JLabel form_fiveCentRollAmount;
	protected JLabel form_twoCentRollAmount;
	protected JLabel form_oneCentRollAmount;
	protected JPanel form_panel20;
	protected JSpinner form_rollTwoEuroSpinner;
	protected JSpinner form_rollOneEuroSpinner;
	protected JSpinner form_rollFiftyCentSpinner;
	protected JSpinner form_rollTwentyCentSpinner;
	protected JSpinner form_rollTenCentSpinner;
	protected JSpinner form_rollFiveCentSpinner;
	protected JSpinner form_rollTwoCentSpinner;
	protected JSpinner form_rollOneCentSpinner;
	protected JPanel form_panel34;
	protected JSpinner form_rollTwoEuroSpinner2;
	protected JSpinner form_rollOneEuroSpinner2;
	protected JSpinner form_rollFiftyCentSpinner2;
	protected JSpinner form_rollTwentyCentSpinner2;
	protected JSpinner form_rollTenCentSpinner2;
	protected JSpinner form_rollFiveCentSpinner2;
	protected JSpinner form_rollTwoCentSpinner2;
	protected JSpinner form_rollOneCentSpinner2;
	protected JPanel form_boxPanel;
	protected JPanel form_panel38;
	protected JLabel form_twoEuroBoxValue;
	protected JLabel form_oneEuroBoxValue;
	protected JLabel form_fiftyCentBoxValue;
	protected JLabel form_twentyCentBoxValue;
	protected JLabel form_tenCentBoxValue;
	protected JLabel form_fiveCentBoxValue;
	protected JLabel form_twoCentBoxValue;
	protected JLabel form_oneCentBoxValue;
	protected JPanel form_panel39;
	protected JLabel form_twoEuroBoxAmount;
	protected JLabel form_oneEuroBoxAmount;
	protected JLabel form_fiftyCentBoxAmount;
	protected JLabel form_twentyCentBoxAmount;
	protected JLabel form_tenCentBoxAmount;
	protected JLabel form_fiveCentBoxAmount;
	protected JLabel form_twoCentBoxAmount;
	protected JLabel form_oneCentBoxAmount;
	protected JPanel form_panel40;
	protected JSpinner form_boxTwoEuroSpinner;
	protected JSpinner form_boxOneEuroSpinner;
	protected JSpinner form_boxFiftyCentSpinner;
	protected JSpinner form_boxTwentyCentSpinner;
	protected JSpinner form_boxTenCentSpinner;
	protected JSpinner form_boxFiveCentSpinner;
	protected JSpinner form_boxTwoCentSpinner;
	protected JSpinner form_boxOneCentSpinner;
	protected JPanel form_cashPanel;
	protected JPanel form_valuePanel;
	protected JLabel form_twoEuroLabel;
	protected JLabel form_oneEuroLabel;
	protected JLabel form_fiftyCentLabel;
	protected JLabel form_twentyCentLabel;
	protected JLabel form_tenCentLabel;
	protected JLabel form_fiveCentLabel;
	protected JLabel form_twoCentLabel;
	protected JLabel form_oneCentLabel;
	protected JPanel form_vSpacer1;
	protected JLabel form_twoHundredEuroLabel;
	protected JLabel form_oneHundredEuroLabel;
	protected JLabel form_fiftyEuroLabel;
	protected JLabel form_twentyEuroLabel;
	protected JLabel form_tenEuroLabel;
	protected JLabel form_fiveEuroLabel;
	protected JPanel form_firstSpinnerNormal;
	protected JSpinner form_spinner2_1;
	protected JSpinner form_spinner1_1;
	protected JSpinner form_spinner050_1;
	protected JSpinner form_spinner020_1;
	protected JSpinner form_spinner010_1;
	protected JSpinner form_spinner005_1;
	protected JSpinner form_spinner002_1;
	protected JSpinner form_spinner001_1;
	protected JPanel form_vSpacer2;
	protected JSpinner form_spinner200_1;
	protected JSpinner form_spinner100_1;
	protected JSpinner form_spinner50_1;
	protected JSpinner form_spinner20_1;
	protected JSpinner form_spinner10_1;
	protected JSpinner form_spinner5_1;
	protected JPanel form_secondSpinnerNormal;
	protected JSpinner form_spinner200_2;
	protected JSpinner form_spinner100_2;
	protected JSpinner form_spinner50_2;
	protected JSpinner form_spinner20_2;
	protected JSpinner form_spinner10_2;
	protected JSpinner form_spinner5_2;
	protected JSpinner form_spinner2_2;
	protected JSpinner form_spinner1_2;
	protected JSpinner form_spinner050_2;
	protected JSpinner form_spinner020_2;
	protected JSpinner form_spinner010_2;
	protected JSpinner form_spinner005_2;
	protected JSpinner form_spinner002_2;
	protected JSpinner form_spinner001_2;
	protected JPanel form_vSpacer3;
	protected JPanel form_thirdSpinnerNormal;
	protected JSpinner form_spinner2_3;
	protected JSpinner form_spinner1_3;
	protected JSpinner form_spinner050_3;
	protected JSpinner form_spinner020_3;
	protected JSpinner form_spinner010_3;
	protected JSpinner form_spinner005_3;
	protected JSpinner form_spinner002_3;
	protected JSpinner form_spinner001_3;
	protected JPanel form_vSpacer4;
	protected JSpinner form_spinner200_3;
	protected JSpinner form_spinner100_3;
	protected JSpinner form_spinner50_3;
	protected JSpinner form_spinner20_3;
	protected JSpinner form_spinner10_3;
	protected JSpinner form_spinner5_3;
	protected JPanel form_fourthSpinnerNormal;
	protected JSpinner form_spinner2_4;
	protected JSpinner form_spinner1_4;
	protected JSpinner form_spinner050_4;
	protected JSpinner form_spinner020_4;
	protected JSpinner form_spinner010_4;
	protected JSpinner form_spinner005_4;
	protected JSpinner form_spinner002_4;
	protected JSpinner form_spinner001_4;
	protected JPanel form_vSpacer5;
	protected JSpinner form_spinner200_4;
	protected JSpinner form_spinner100_4;
	protected JSpinner form_spinner50_4;
	protected JSpinner form_spinner20_4;
	protected JSpinner form_spinner10_4;
	protected JSpinner form_spinner5_4;
	protected JPanel form_fifthSpinnerNormal;
	protected JSpinner form_spinner2_5;
	protected JSpinner form_spinner1_5;
	protected JSpinner form_spinner050_5;
	protected JSpinner form_spinner020_5;
	protected JSpinner form_spinner010_5;
	protected JSpinner form_spinner005_5;
	protected JSpinner form_spinner002_5;
	protected JSpinner form_spinner001_5;
	protected JPanel form_vSpacer6;
	protected JSpinner form_spinner200_5;
	protected JSpinner form_spinner100_5;
	protected JSpinner form_spinner50_5;
	protected JSpinner form_spinner20_5;
	protected JSpinner form_spinner10_5;
	protected JSpinner form_spinner5_5;
	protected JPanel form_amountPanelNormal;
	protected JPanel form_panel44;
	protected JLabel form_twoEuroLabelAmount;
	protected JLabel form_oneEuroLabelAmount;
	protected JLabel form_fiftyCentLabelAmount;
	protected JLabel form_twentyCentLabelAmount;
	protected JLabel form_tenCentLabelAmount;
	protected JLabel form_fiveCentLabelAmount;
	protected JLabel form_twoCentLabelAmount;
	protected JLabel form_oneCentLabelAmount;
	protected JPanel form_vSpacer7;
	protected JLabel form_twohundredEuroLabelAmount;
	protected JLabel form_onehundredEuroLabelAmount;
	protected JLabel form_fiftyEuroLabelAmount;
	protected JLabel form_twentyEuroLabelAmount;
	protected JLabel form_tenEuroLabelAmount;
	protected JLabel form_fiveEuroLabelAmount;
	protected JPanel form_panel45;
	protected JLabel form_twoEuroLabelX;
	protected JLabel form_oneEuroLabelX;
	protected JLabel form_fiftyCentLabelX;
	protected JLabel form_twentyCentLabelX;
	protected JLabel form_tenCentLabelX;
	protected JLabel form_fiveCentLabelX;
	protected JLabel form_twoCentLabelX;
	protected JLabel form_oneCentLabelX;
	protected JPanel form_vSpacer8;
	protected JLabel form_twohundredEuroLabelX;
	protected JLabel form_onehundredEuroLabelX;
	protected JLabel form_fiftyEuroLabelX;
	protected JLabel form_twentyEuroLabelX;
	protected JLabel form_tenEuroLabelX;
	protected JLabel form_fiveEuroLabelX;
	protected JPanel form_valueRowPanelNormal;
	protected JPanel form_panel46;
	protected JLabel form_twoEuroLabelValue;
	protected JLabel form_oneEuroLabelValue;
	protected JLabel form_fiftyCentLabelValue;
	protected JLabel form_twentyCentLabelValue;
	protected JLabel form_tenCentLabelValue;
	protected JLabel form_fiveCentLabelValue;
	protected JLabel form_twoCentLabelValue;
	protected JLabel form_oneCentLabelValue;
	protected JPanel form_vSpacer9;
	protected JLabel form_twohundredEuroLabelValue;
	protected JLabel form_onehundredEuroLabelValue;
	protected JLabel form_fiftyEuroLabelValue;
	protected JLabel form_twentyEuroLabelValue;
	protected JLabel form_tenEuroLabelValue;
	protected JLabel form_fiveEuroLabelValue;
	protected JPanel form_panel47;
	protected JLabel form_twoEuroLabelSymbol;
	protected JLabel form_oneEuroLabelSymbol;
	protected JLabel form_fiftyCentLabelSymbol;
	protected JLabel form_twentyCentLabelSymbol;
	protected JLabel form_tenCentLabelSymbol;
	protected JLabel form_fiveCentLabelSymbol;
	protected JLabel form_twoCentLabelSymbol;
	protected JLabel form_oneCentLabelSymbol;
	protected JPanel form_vSpacer10;
	protected JLabel form_twohundredEuroLabelSymbol;
	protected JLabel form_onehundredEuroLabelSymbol;
	protected JLabel form_fiftyEuroLabelSymbol;
	protected JLabel form_twentyEuroLabelSymbol;
	protected JLabel form_tenEuroLabelSymbol;
	protected JLabel form_fiveEuroLabelSymbol;
	protected JPanel form_topPanel;
	protected JPanel form_registerPanel;
	protected JSpinner form_fullRegisterSpinner;
	protected JLabel form_fullRegisterLabel;
	protected JPanel form_buttonPanel;
	protected JButton form_resetBtn;
	protected JSeparator form_separator1;
	protected JButton form_registerBtn;
	protected JButton form_pdfBtn;
	protected JButton form_ausrechnenBtn2;
	protected JSeparator form_separator2;
	protected JButton form_debitBtnNew;
	protected JSeparator form_separator3;
	protected JButton form_dateBtn;
	protected JPanel form_datePanel;
	protected JSpinner form_dateSpinner;
	protected JPanel form_debitPanel;
	protected JTextField form_debitTextField;
	protected JLabel form_label69;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
