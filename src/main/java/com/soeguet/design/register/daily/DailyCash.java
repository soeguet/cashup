package com.soeguet.design.register.daily;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

/**
 * @author unknown
 */
public abstract class DailyCash extends JInternalFrame {

    protected JSpinner[] twoEuroRow;
    protected JSpinner[] oneEuroRow;
    protected JSpinner[] fiftyCentRow;
    protected JSpinner[] twentyCentRow;
    protected JSpinner[] tenCentRow;
    protected JSpinner[] fiveCentRow;
    protected JSpinner[] twoCentRow;
    protected JSpinner[] oneCentRow;

    protected JSpinner[] twohundredEuroRow;
    protected JSpinner[] onehundredEuroRow;
    protected JSpinner[] fiftyEuroRow;
    protected JSpinner[] twentyEuroRow;
    protected JSpinner[] tenEuroRow;
    protected JSpinner[] fiveEuroRow;

    protected JOptionPane jOptionPane;
    protected JDialog dialog;
    protected boolean sollabfrageAbbrechen;
    protected String posneg;

    protected JSpinner[] resetArray;

    protected JLabel[] twoEuroRowLabel;
    protected JLabel[] oneEuroRowLabel;
    protected JLabel[] fiftyCentRowLabel;
    protected JLabel[] twentyCentRowLabel;
    protected JLabel[] tenCentRowLabel;
    protected JLabel[] fiveCentRowLabel;
    protected JLabel[] twoCentRowLabel;
    protected JLabel[] oneCentRowLabel;
    protected JLabel[] twohundredEuroRowLabel;
    protected JLabel[] onehundredEuroRowLabel;
    protected JLabel[] fiftyEuroRowLabel;
    protected JLabel[] twentyEuroRowLabel;
    protected JLabel[] tenEuroRowLabel;
    protected JLabel[] fiveEuroRowLabel;

    public DailyCash() {
        initComponents();
        eigenerInit();

		focuListenerPreperation();
	}

	//bundle method to set up focus listener for each JSpinner
	private void focuListenerPreperation() {
		addFocusListenerToSpinners(twoEuroRow, twoEuroRowLabel);
		addFocusListenerToSpinners(oneEuroRow, oneEuroRowLabel);
		addFocusListenerToSpinners(fiftyCentRow, fiftyCentRowLabel);
		addFocusListenerToSpinners(twentyCentRow, twentyCentRowLabel);
		addFocusListenerToSpinners(tenCentRow, tenCentRowLabel);
		addFocusListenerToSpinners(fiveCentRow, fiveCentRowLabel);
		addFocusListenerToSpinners(twoCentRow, twoCentRowLabel);
		addFocusListenerToSpinners(oneCentRow, oneCentRowLabel);

		addFocusListenerToSpinners(twohundredEuroRow, twohundredEuroRowLabel);
		addFocusListenerToSpinners(onehundredEuroRow, onehundredEuroRowLabel);
		addFocusListenerToSpinners(fiftyEuroRow, fiftyEuroRowLabel);
		addFocusListenerToSpinners(twentyEuroRow, twentyEuroRowLabel);
		addFocusListenerToSpinners(tenEuroRow, tenEuroRowLabel);
		addFocusListenerToSpinners(fiveEuroRow, fiveEuroRowLabel);
	}

	private void addFocusListenerToSpinners(JSpinner[] spinnerList, JLabel[] labelList) {

        Arrays.stream(spinnerList).forEach(spinner -> {
            Component editorComponent = spinner.getEditor().getComponent(0); // Zugriff auf das Editor-Element des JSpinners
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

                }
            });
        });
    }

    protected void allesMarkieren() {

        for (JSpinner spinner : resetArray) {

            JSpinner.DefaultEditor editorSpinner = (JSpinner.DefaultEditor) spinner.getEditor();
            JTextField jTextField = editorSpinner.getTextField();
            jTextField.setText("0");

            jTextField.addFocusListener(new FocusAdapter() {

                @Override
                public void focusGained(FocusEvent e) {

                    jTextField.selectAll();

                    Thread thread = new Thread(() -> {

                        JTextField tf = (JTextField) e.getSource();
                        tf.selectAll();
                    });
                    thread.start();
                }
            });
        }
    }

    protected abstract void calculateBtnActionPerformed(ActionEvent e);

    protected void eigenerInit() {

        twoEuroRowLabel = new JLabel[]{form_twoEuroLabel, form_twoEuroLabelAmount, form_twoEuroLabelX, form_twoEuroLabelSymbol, form_twoEuroLabelValue};
        oneEuroRowLabel = new JLabel[]{form_oneEuroLabel, form_oneEuroLabelAmount, form_oneEuroLabelX, form_oneEuroLabelSymbol, form_oneEuroLabelValue};
        fiftyCentRowLabel = new JLabel[]{form_fiftyCentLabel, form_fiftyCentLabelAmount, form_fiftyCentLabelX, form_fiftyCentLabelSymbol, form_fiftyCentLabelValue};
        twentyCentRowLabel = new JLabel[]{form_twentyCentLabel, form_twentyCentLabelAmount, form_twentyCentLabelX, form_twentyCentLabelSymbol, form_twentyCentLabelValue};
        tenCentRowLabel = new JLabel[]{form_tenCentLabel, form_tenCentLabelAmount, form_tenCentLabelX, form_tenCentLabelSymbol, form_tenCentLabelValue};
        fiveCentRowLabel = new JLabel[]{form_fiveCentLabel, form_fiveCentLabelAmount, form_fiveCentLabelX, form_fiveCentLabelSymbol, form_fiveCentLabelValue};
        twoCentRowLabel = new JLabel[]{form_twoCentLabel, form_twoCentLabelAmount, form_twoCentLabelX, form_twoCentLabelSymbol, form_twoCentLabelValue};
        oneCentRowLabel = new JLabel[]{form_oneCentLabel, form_oneCentLabelAmount, form_oneCentLabelX, form_oneCentLabelSymbol, form_oneCentLabelValue};

        twohundredEuroRowLabel = new JLabel[]{form_twohundredEuroLabel, form_twohundredEuroLabelAmount, form_twohundredEuroLabelX, form_twohundredEuroLabelSymbol, form_twohundredEuroLabelValue};
        onehundredEuroRowLabel = new JLabel[]{form_onehundredEuroLabel, form_onehundredEuroLabelAmount, form_onehundredEuroLabelX, form_onehundredEuroLabelSymbol, form_onehundredEuroLabelValue};
        fiftyEuroRowLabel = new JLabel[]{form_fiftyEuroLabel, form_fiftyEuroLabelAmount, form_fiftyEuroLabelX, form_fiftyEuroLabelSymbol, form_fiftyEuroLabelValue};
        twentyEuroRowLabel = new JLabel[]{form_twentyEuroLabel, form_twentyEuroLabelAmount, form_twentyEuroLabelX, form_twentyEuroLabelSymbol, form_twentyEuroLabelValue};
        tenEuroRowLabel = new JLabel[]{form_tenEuroLabel, form_tenEuroLabelAmount, form_tenEuroLabelX, form_tenEuroLabelSymbol, form_tenEuroLabelValue};
        fiveEuroRowLabel = new JLabel[]{form_fiveEuroLabel, form_fiveEuroLabelAmount, form_fiveEuroLabelX, form_fiveEuroLabelSymbol, form_fiveEuroLabelValue};

        resetArray = new JSpinner[]{form_spinner2_1, form_spinner1_1, form_spinner050_1, form_spinner020_1,
                form_spinner010_1, form_spinner005_1,
                form_spinner002_1, form_spinner001_1, form_spinner200_1, form_spinner100_1, form_spinner50_1,
                form_spinner20_1, form_spinner10_1,
                form_spinner5_1, form_spinner2_2, form_spinner1_2, form_spinner050_2, form_spinner020_2,
                form_spinner010_2, form_spinner005_2,
                form_spinner002_2, form_spinner001_2, form_spinner200_2, form_spinner100_2, form_spinner50_2,
                form_spinner20_2, form_spinner10_2,
                form_spinner5_2, form_spinner2_3, form_spinner1_3, form_spinner050_3, form_spinner020_3,
                form_spinner010_3, form_spinner005_3,
                form_spinner002_3, form_spinner001_3, form_spinner200_3, form_spinner100_3, form_spinner50_3,
                form_spinner20_3, form_spinner10_3,
                form_spinner5_3, form_spinner2_4, form_spinner1_4, form_spinner050_4, form_spinner020_4,
                form_spinner010_4, form_spinner005_4,
                form_spinner002_4, form_spinner001_4, form_spinner200_4, form_spinner100_4, form_spinner50_4,
                form_spinner20_4, form_spinner10_4,
                form_spinner5_4, form_spinner2_5, form_spinner1_5, form_spinner050_5, form_spinner020_5,
                form_spinner010_5, form_spinner005_5,
                form_spinner002_5, form_spinner001_5, form_spinner200_5, form_spinner100_5, form_spinner50_5,
                form_spinner20_5, form_spinner10_5,
                form_spinner5_5};

        twoEuroRow = new JSpinner[]{form_spinner2_1, form_spinner2_2, form_spinner2_3, form_spinner2_4,
                form_spinner2_5};
        oneEuroRow = new JSpinner[]{form_spinner1_1, form_spinner1_2, form_spinner1_3, form_spinner1_4,
                form_spinner1_5};
        fiftyCentRow = new JSpinner[]{form_spinner050_1, form_spinner050_2, form_spinner050_3, form_spinner050_4,
                form_spinner050_5};
        twentyCentRow = new JSpinner[]{form_spinner020_1, form_spinner020_2, form_spinner020_3, form_spinner020_4,
                form_spinner020_5};
        tenCentRow = new JSpinner[]{form_spinner010_1, form_spinner010_2, form_spinner010_3, form_spinner010_4,
                form_spinner010_5};
        fiveCentRow = new JSpinner[]{form_spinner005_1, form_spinner005_2, form_spinner005_3, form_spinner005_4,
                form_spinner005_5};
        twoCentRow = new JSpinner[]{form_spinner002_1, form_spinner002_2, form_spinner002_3, form_spinner002_4,
                form_spinner002_5};
        oneCentRow = new JSpinner[]{form_spinner001_1, form_spinner001_2, form_spinner001_3, form_spinner001_4,
                form_spinner001_5};

        twohundredEuroRow = new JSpinner[]{form_spinner200_1, form_spinner200_2, form_spinner200_3,
                form_spinner200_4, form_spinner200_5};
        onehundredEuroRow = new JSpinner[]{form_spinner100_1, form_spinner100_2, form_spinner100_3,
                form_spinner100_4, form_spinner100_5};
        fiftyEuroRow = new JSpinner[]{form_spinner50_1, form_spinner50_2, form_spinner50_3, form_spinner50_4,
                form_spinner50_5};
        twentyEuroRow = new JSpinner[]{form_spinner20_1, form_spinner20_2, form_spinner20_3, form_spinner20_4,
                form_spinner20_5};
        tenEuroRow = new JSpinner[]{form_spinner10_1, form_spinner10_2, form_spinner10_3, form_spinner10_4,
                form_spinner10_5};
        fiveEuroRow = new JSpinner[]{form_spinner5_1, form_spinner5_2, form_spinner5_3, form_spinner5_4,
                form_spinner5_5};
    }

    protected abstract void registerBtnActionPerformed(ActionEvent e);

    protected abstract void pdfBtnActionPerformed(ActionEvent e);

    protected abstract void resetBtnActionPerformed(ActionEvent e);

    protected abstract void debitBtnActionPerformed(ActionEvent e);

	protected abstract void settingsMenuItemAction(ActionEvent e);

	protected abstract void exitMenuItemAction(ActionEvent e);

    protected abstract void dateBtnActionPerformed(ActionEvent e);

    protected void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner non-commercial license
		this.form_menuBar1 = new JMenuBar();
		this.form_menu1 = new JMenu();
		this.form_settingsMenuItem = new JMenuItem();
		this.form_exitMenuItem = new JMenuItem();
		this.form_topPanel = new JPanel();
		this.form_buttonPanel = new JPanel();
		this.form_resetBtn = new JButton();
		this.form_separator1 = new JSeparator();
		this.form_registerBtn = new JButton();
		this.form_pdfBtn = new JButton();
		this.form_calculateBtn = new JButton();
		this.form_separator2 = new JSeparator();
		this.form_debitBtn = new JButton();
		this.form_separator3 = new JSeparator();
		this.form_dateBtn = new JButton();
		this.form_datePanel = new JPanel();
		this.form_dateSpinner = new JSpinner();
		this.form_debitPanel = new JPanel();
		this.form_debitTextField = new JTextField();
		this.form_label69 = new JLabel();
		this.form_middlePanel = new JPanel();
		this.form_wertPanel = new JPanel();
		this.form_twoEuroLabel = new JLabel();
		this.form_oneEuroLabel = new JLabel();
		this.form_fiftyCentLabel = new JLabel();
		this.form_twentyCentLabel = new JLabel();
		this.form_tenCentLabel = new JLabel();
		this.form_fiveCentLabel = new JLabel();
		this.form_twoCentLabel = new JLabel();
		this.form_oneCentLabel = new JLabel();
		this.form_vSpacer4 = new JPanel(null);
		this.form_twohundredEuroLabel = new JLabel();
		this.form_onehundredEuroLabel = new JLabel();
		this.form_fiftyEuroLabel = new JLabel();
		this.form_twentyEuroLabel = new JLabel();
		this.form_tenEuroLabel = new JLabel();
		this.form_fiveEuroLabel = new JLabel();
		this.form_ersteSpinnerNormal = new JPanel();
		this.form_spinner2_1 = new JSpinner();
		this.form_spinner1_1 = new JSpinner();
		this.form_spinner050_1 = new JSpinner();
		this.form_spinner020_1 = new JSpinner();
		this.form_spinner010_1 = new JSpinner();
		this.form_spinner005_1 = new JSpinner();
		this.form_spinner002_1 = new JSpinner();
		this.form_spinner001_1 = new JSpinner();
		this.form_vSpacer3 = new JPanel(null);
		this.form_spinner200_1 = new JSpinner();
		this.form_spinner100_1 = new JSpinner();
		this.form_spinner50_1 = new JSpinner();
		this.form_spinner20_1 = new JSpinner();
		this.form_spinner10_1 = new JSpinner();
		this.form_spinner5_1 = new JSpinner();
		this.form_zweiteSpinnerNormal = new JPanel();
		this.form_spinner2_2 = new JSpinner();
		this.form_spinner1_2 = new JSpinner();
		this.form_spinner050_2 = new JSpinner();
		this.form_spinner020_2 = new JSpinner();
		this.form_spinner010_2 = new JSpinner();
		this.form_spinner005_2 = new JSpinner();
		this.form_spinner002_2 = new JSpinner();
		this.form_spinner001_2 = new JSpinner();
		this.form_vSpacer2 = new JPanel(null);
		this.form_spinner200_2 = new JSpinner();
		this.form_spinner100_2 = new JSpinner();
		this.form_spinner50_2 = new JSpinner();
		this.form_spinner20_2 = new JSpinner();
		this.form_spinner10_2 = new JSpinner();
		this.form_spinner5_2 = new JSpinner();
		this.form_dritteSpinnerNormal = new JPanel();
		this.form_spinner2_3 = new JSpinner();
		this.form_spinner1_3 = new JSpinner();
		this.form_spinner050_3 = new JSpinner();
		this.form_spinner020_3 = new JSpinner();
		this.form_spinner010_3 = new JSpinner();
		this.form_spinner005_3 = new JSpinner();
		this.form_spinner002_3 = new JSpinner();
		this.form_spinner001_3 = new JSpinner();
		this.form_vSpacer5 = new JPanel(null);
		this.form_spinner200_3 = new JSpinner();
		this.form_spinner100_3 = new JSpinner();
		this.form_spinner50_3 = new JSpinner();
		this.form_spinner20_3 = new JSpinner();
		this.form_spinner10_3 = new JSpinner();
		this.form_spinner5_3 = new JSpinner();
		this.form_vierteSpinnerNormal = new JPanel();
		this.form_spinner2_4 = new JSpinner();
		this.form_spinner1_4 = new JSpinner();
		this.form_spinner050_4 = new JSpinner();
		this.form_spinner020_4 = new JSpinner();
		this.form_spinner010_4 = new JSpinner();
		this.form_spinner005_4 = new JSpinner();
		this.form_spinner002_4 = new JSpinner();
		this.form_spinner001_4 = new JSpinner();
		this.form_vSpacer6 = new JPanel(null);
		this.form_spinner200_4 = new JSpinner();
		this.form_spinner100_4 = new JSpinner();
		this.form_spinner50_4 = new JSpinner();
		this.form_spinner20_4 = new JSpinner();
		this.form_spinner10_4 = new JSpinner();
		this.form_spinner5_4 = new JSpinner();
		this.form_fuenfteSpinnerNormal = new JPanel();
		this.form_spinner2_5 = new JSpinner();
		this.form_spinner1_5 = new JSpinner();
		this.form_spinner050_5 = new JSpinner();
		this.form_spinner020_5 = new JSpinner();
		this.form_spinner010_5 = new JSpinner();
		this.form_spinner005_5 = new JSpinner();
		this.form_spinner002_5 = new JSpinner();
		this.form_spinner001_5 = new JSpinner();
		this.form_vSpacer7 = new JPanel(null);
		this.form_spinner200_5 = new JSpinner();
		this.form_spinner100_5 = new JSpinner();
		this.form_spinner50_5 = new JSpinner();
		this.form_spinner20_5 = new JSpinner();
		this.form_spinner10_5 = new JSpinner();
		this.form_spinner5_5 = new JSpinner();
		this.form_anzahlPanelNormal = new JPanel();
		this.form_twoEuroLabelAmount = new JLabel();
		this.form_twoEuroLabelX = new JLabel();
		this.form_oneEuroLabelAmount = new JLabel();
		this.form_oneEuroLabelX = new JLabel();
		this.form_fiftyCentLabelAmount = new JLabel();
		this.form_fiftyCentLabelX = new JLabel();
		this.form_twentyCentLabelAmount = new JLabel();
		this.form_twentyCentLabelX = new JLabel();
		this.form_tenCentLabelAmount = new JLabel();
		this.form_tenCentLabelX = new JLabel();
		this.form_fiveCentLabelAmount = new JLabel();
		this.form_fiveCentLabelX = new JLabel();
		this.form_twoCentLabelAmount = new JLabel();
		this.form_twoCentLabelX = new JLabel();
		this.form_oneCentLabelAmount = new JLabel();
		this.form_oneCentLabelX = new JLabel();
		this.form_vSpacer8 = new JPanel(null);
		this.form_twohundredEuroLabelAmount = new JLabel();
		this.form_twohundredEuroLabelX = new JLabel();
		this.form_onehundredEuroLabelAmount = new JLabel();
		this.form_onehundredEuroLabelX = new JLabel();
		this.form_fiftyEuroLabelAmount = new JLabel();
		this.form_fiftyEuroLabelX = new JLabel();
		this.form_twentyEuroLabelAmount = new JLabel();
		this.form_twentyEuroLabelX = new JLabel();
		this.form_tenEuroLabelAmount = new JLabel();
		this.form_tenEuroLabelX = new JLabel();
		this.form_fiveEuroLabelAmount = new JLabel();
		this.form_fiveEuroLabelX = new JLabel();
		this.form_zweilenwertPanelNormal = new JPanel();
		this.form_twoEuroLabelValue = new JLabel();
		this.form_twoEuroLabelSymbol = new JLabel();
		this.form_oneEuroLabelValue = new JLabel();
		this.form_oneEuroLabelSymbol = new JLabel();
		this.form_fiftyCentLabelValue = new JLabel();
		this.form_fiftyCentLabelSymbol = new JLabel();
		this.form_twentyCentLabelValue = new JLabel();
		this.form_twentyCentLabelSymbol = new JLabel();
		this.form_tenCentLabelValue = new JLabel();
		this.form_tenCentLabelSymbol = new JLabel();
		this.form_fiveCentLabelValue = new JLabel();
		this.form_fiveCentLabelSymbol = new JLabel();
		this.form_twoCentLabelValue = new JLabel();
		this.form_twoCentLabelSymbol = new JLabel();
		this.form_oneCentLabelValue = new JLabel();
		this.form_oneCentLabelSymbol = new JLabel();
		this.form_vSpacer9 = new JPanel(null);
		this.form_twohundredEuroLabelValue = new JLabel();
		this.form_twohundredEuroLabelSymbol = new JLabel();
		this.form_onehundredEuroLabelValue = new JLabel();
		this.form_onehundredEuroLabelSymbol = new JLabel();
		this.form_fiftyEuroLabelValue = new JLabel();
		this.form_fiftyEuroLabelSymbol = new JLabel();
		this.form_twentyEuroLabelValue = new JLabel();
		this.form_twentyEuroLabelSymbol = new JLabel();
		this.form_tenEuroLabelValue = new JLabel();
		this.form_tenEuroLabelSymbol = new JLabel();
		this.form_fiveEuroLabelValue = new JLabel();
		this.form_fiveEuroLabelSymbol = new JLabel();
		this.form_bottomPanel = new JPanel();
		this.form_panel4 = new JPanel();
		this.form_intermediateValueOverviewCoins = new JLabel();
		this.form_intermedateValueOverviewNotes = new JLabel();
		this.form_panel6 = new JPanel();
		this.form_totalValueLabel = new JLabel();
		this.form_panel5 = new JPanel();
		this.form_differenceValueLabel = new JLabel();

		//======== this ========
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setResizable(true);
		setTitle("daily");
		setVisible(true);
		setMinimumSize(new Dimension(1200, 760));
		setPreferredSize(new Dimension(1200, 760));
		setName("this");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== form_menuBar1 ========
		{
			this.form_menuBar1.setName("menuBar1");

			//======== form_menu1 ========
			{
				this.form_menu1.setText("file");
				this.form_menu1.setName("menu1");

				//---- form_settingsMenuItem ----
				this.form_settingsMenuItem.setText("settings");
				this.form_settingsMenuItem.setName("settingsMenuItem");
				this.form_settingsMenuItem.addActionListener(e -> settingsMenuItemAction(e));
				this.form_menu1.add(this.form_settingsMenuItem);
				this.form_menu1.addSeparator();

				//---- form_exitMenuItem ----
				this.form_exitMenuItem.setText("exit");
				this.form_exitMenuItem.setName("exitMenuItem");
				this.form_exitMenuItem.addActionListener(e -> exitMenuItemAction(e));
				this.form_menu1.add(this.form_exitMenuItem);
			}
			this.form_menuBar1.add(this.form_menu1);
		}
		setJMenuBar(this.form_menuBar1);

		//======== form_topPanel ========
		{
			this.form_topPanel.setFocusable(false);
			this.form_topPanel.setEnabled(false);
			this.form_topPanel.setRequestFocusEnabled(false);
			this.form_topPanel.setVerifyInputWhenFocusTarget(false);
			this.form_topPanel.setAutoscrolls(true);
			this.form_topPanel.setName("topPanel");
			this.form_topPanel.setLayout(new FlowLayout());
			((FlowLayout)this.form_topPanel.getLayout()).setAlignOnBaseline(true);

			//======== form_buttonPanel ========
			{
				this.form_buttonPanel.setBorder(new TitledBorder("actions"));
				this.form_buttonPanel.setPreferredSize(new Dimension(700, 70));
				this.form_buttonPanel.setFocusable(false);
				this.form_buttonPanel.setEnabled(false);
				this.form_buttonPanel.setMinimumSize(new Dimension(700, 70));
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

				//---- form_calculateBtn ----
				this.form_calculateBtn.setText("calculate");
				this.form_calculateBtn.setFont(this.form_calculateBtn.getFont().deriveFont(this.form_calculateBtn.getFont().getSize() + 3f));
				this.form_calculateBtn.setDoubleBuffered(true);
				this.form_calculateBtn.setOpaque(true);
				this.form_calculateBtn.setSelected(true);
				this.form_calculateBtn.setBackground(new Color(0x0055ff));
				this.form_calculateBtn.setName("calculateBtn");
				this.form_calculateBtn.addActionListener(e -> calculateBtnActionPerformed(e));
				this.form_buttonPanel.add(this.form_calculateBtn);

				//---- form_separator2 ----
				this.form_separator2.setOrientation(SwingConstants.VERTICAL);
				this.form_separator2.setPreferredSize(new Dimension(3, 10));
				this.form_separator2.setRequestFocusEnabled(false);
				this.form_separator2.setBackground(Color.white);
				this.form_separator2.setName("separator2");
				this.form_buttonPanel.add(this.form_separator2);

				//---- form_debitBtn ----
				this.form_debitBtn.setText("debit");
				this.form_debitBtn.setFont(this.form_debitBtn.getFont().deriveFont(this.form_debitBtn.getFont().getSize() + 3f));
				this.form_debitBtn.setToolTipText("start python script. this script is intended to open the cash register programm and search for the amount, which is to be in the cash register");
				this.form_debitBtn.setName("debitBtn");
				this.form_debitBtn.addActionListener(e -> debitBtnActionPerformed(e));
				this.form_buttonPanel.add(this.form_debitBtn);

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
			this.form_topPanel.add(this.form_buttonPanel);

			//======== form_datePanel ========
			{
				this.form_datePanel.setBorder(new TitledBorder("date"));
				this.form_datePanel.setPreferredSize(new Dimension(195, 70));
				this.form_datePanel.setEnabled(false);
				this.form_datePanel.setFocusable(false);
				this.form_datePanel.setMinimumSize(new Dimension(195, 70));
				this.form_datePanel.setName("datePanel");
				this.form_datePanel.setLayout(new FlowLayout());

				//---- form_dateSpinner ----
				this.form_dateSpinner.setDoubleBuffered(true);
				this.form_dateSpinner.setModel(new SpinnerDateModel(new java.util.Date((System.currentTimeMillis()/60000)*60000), null, null, java.util.Calendar.MINUTE));
				this.form_dateSpinner.setMinimumSize(new Dimension(175, 30));
				this.form_dateSpinner.setPreferredSize(new Dimension(175, 30));
				this.form_dateSpinner.setFont(this.form_dateSpinner.getFont().deriveFont(this.form_dateSpinner.getFont().getSize() + 3f));
				this.form_dateSpinner.setName("dateSpinner");
				this.form_datePanel.add(this.form_dateSpinner);
			}
			this.form_topPanel.add(this.form_datePanel);

			//======== form_debitPanel ========
			{
				this.form_debitPanel.setBorder(new TitledBorder("debit"));
				this.form_debitPanel.setRequestFocusEnabled(false);
				this.form_debitPanel.setVerifyInputWhenFocusTarget(false);
				this.form_debitPanel.setPreferredSize(new Dimension(250, 70));
				this.form_debitPanel.setMinimumSize(new Dimension(250, 70));
				this.form_debitPanel.setEnabled(false);
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
			this.form_topPanel.add(this.form_debitPanel);
		}
		contentPane.add(this.form_topPanel, BorderLayout.NORTH);

		//======== form_middlePanel ========
		{
			this.form_middlePanel.setName("middlePanel");
			this.form_middlePanel.setLayout(new GridLayout());

			//======== form_wertPanel ========
			{
				this.form_wertPanel.setPreferredSize(new Dimension(56, 30));
				this.form_wertPanel.setName("wertPanel");
				this.form_wertPanel.setLayout(new MigLayout(
					"fill,alignx trailing",
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
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]"));

				//---- form_twoEuroLabel ----
				this.form_twoEuroLabel.setText("2,00 \u20ac");
				this.form_twoEuroLabel.setPreferredSize(new Dimension(56, 30));
				this.form_twoEuroLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_twoEuroLabel.setFont(this.form_twoEuroLabel.getFont().deriveFont(this.form_twoEuroLabel.getFont().getStyle() & ~Font.BOLD, this.form_twoEuroLabel.getFont().getSize() + 3f));
				this.form_twoEuroLabel.setFocusable(false);
				this.form_twoEuroLabel.setInheritsPopupMenu(false);
				this.form_twoEuroLabel.setRequestFocusEnabled(false);
				this.form_twoEuroLabel.setVerifyInputWhenFocusTarget(false);
				this.form_twoEuroLabel.setMaximumSize(null);
				this.form_twoEuroLabel.setMinimumSize(new Dimension(44, 30));
				this.form_twoEuroLabel.setName("twoEuroLabel");
				this.form_wertPanel.add(this.form_twoEuroLabel, "cell 0 0,growx,gapx null 20");

				//---- form_oneEuroLabel ----
				this.form_oneEuroLabel.setText("1,00 \u20ac");
				this.form_oneEuroLabel.setPreferredSize(new Dimension(56, 30));
				this.form_oneEuroLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_oneEuroLabel.setFont(this.form_oneEuroLabel.getFont().deriveFont(this.form_oneEuroLabel.getFont().getSize() + 3f));
				this.form_oneEuroLabel.setFocusable(false);
				this.form_oneEuroLabel.setInheritsPopupMenu(false);
				this.form_oneEuroLabel.setRequestFocusEnabled(false);
				this.form_oneEuroLabel.setVerifyInputWhenFocusTarget(false);
				this.form_oneEuroLabel.setMaximumSize(null);
				this.form_oneEuroLabel.setMinimumSize(new Dimension(44, 30));
				this.form_oneEuroLabel.setName("oneEuroLabel");
				this.form_wertPanel.add(this.form_oneEuroLabel, "cell 0 1,growx,gapx null 20");

				//---- form_fiftyCentLabel ----
				this.form_fiftyCentLabel.setText("0,50 \u20ac");
				this.form_fiftyCentLabel.setPreferredSize(new Dimension(56, 30));
				this.form_fiftyCentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_fiftyCentLabel.setFont(this.form_fiftyCentLabel.getFont().deriveFont(this.form_fiftyCentLabel.getFont().getSize() + 3f));
				this.form_fiftyCentLabel.setFocusable(false);
				this.form_fiftyCentLabel.setInheritsPopupMenu(false);
				this.form_fiftyCentLabel.setRequestFocusEnabled(false);
				this.form_fiftyCentLabel.setVerifyInputWhenFocusTarget(false);
				this.form_fiftyCentLabel.setMaximumSize(null);
				this.form_fiftyCentLabel.setMinimumSize(new Dimension(44, 30));
				this.form_fiftyCentLabel.setName("fiftyCentLabel");
				this.form_wertPanel.add(this.form_fiftyCentLabel, "cell 0 2,growx,gapx null 20");

				//---- form_twentyCentLabel ----
				this.form_twentyCentLabel.setText("0,20 \u20ac");
				this.form_twentyCentLabel.setPreferredSize(new Dimension(56, 30));
				this.form_twentyCentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_twentyCentLabel.setFont(this.form_twentyCentLabel.getFont().deriveFont(this.form_twentyCentLabel.getFont().getSize() + 3f));
				this.form_twentyCentLabel.setFocusable(false);
				this.form_twentyCentLabel.setInheritsPopupMenu(false);
				this.form_twentyCentLabel.setRequestFocusEnabled(false);
				this.form_twentyCentLabel.setVerifyInputWhenFocusTarget(false);
				this.form_twentyCentLabel.setMaximumSize(null);
				this.form_twentyCentLabel.setMinimumSize(new Dimension(44, 30));
				this.form_twentyCentLabel.setName("twentyCentLabel");
				this.form_wertPanel.add(this.form_twentyCentLabel, "cell 0 3,growx,gapx null 20");

				//---- form_tenCentLabel ----
				this.form_tenCentLabel.setText("0,10 \u20ac");
				this.form_tenCentLabel.setPreferredSize(new Dimension(56, 30));
				this.form_tenCentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_tenCentLabel.setFont(this.form_tenCentLabel.getFont().deriveFont(this.form_tenCentLabel.getFont().getSize() + 3f));
				this.form_tenCentLabel.setFocusable(false);
				this.form_tenCentLabel.setInheritsPopupMenu(false);
				this.form_tenCentLabel.setRequestFocusEnabled(false);
				this.form_tenCentLabel.setVerifyInputWhenFocusTarget(false);
				this.form_tenCentLabel.setMaximumSize(null);
				this.form_tenCentLabel.setMinimumSize(new Dimension(44, 30));
				this.form_tenCentLabel.setName("tenCentLabel");
				this.form_wertPanel.add(this.form_tenCentLabel, "cell 0 4,growx,gapx null 20");

				//---- form_fiveCentLabel ----
				this.form_fiveCentLabel.setText("0,05 \u20ac");
				this.form_fiveCentLabel.setPreferredSize(new Dimension(56, 30));
				this.form_fiveCentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_fiveCentLabel.setFont(this.form_fiveCentLabel.getFont().deriveFont(this.form_fiveCentLabel.getFont().getSize() + 3f));
				this.form_fiveCentLabel.setFocusable(false);
				this.form_fiveCentLabel.setInheritsPopupMenu(false);
				this.form_fiveCentLabel.setRequestFocusEnabled(false);
				this.form_fiveCentLabel.setVerifyInputWhenFocusTarget(false);
				this.form_fiveCentLabel.setMaximumSize(null);
				this.form_fiveCentLabel.setMinimumSize(new Dimension(44, 30));
				this.form_fiveCentLabel.setName("fiveCentLabel");
				this.form_wertPanel.add(this.form_fiveCentLabel, "cell 0 5,growx,gapx null 20");

				//---- form_twoCentLabel ----
				this.form_twoCentLabel.setText("0,02 \u20ac");
				this.form_twoCentLabel.setPreferredSize(new Dimension(56, 30));
				this.form_twoCentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_twoCentLabel.setFont(this.form_twoCentLabel.getFont().deriveFont(this.form_twoCentLabel.getFont().getSize() + 3f));
				this.form_twoCentLabel.setFocusable(false);
				this.form_twoCentLabel.setInheritsPopupMenu(false);
				this.form_twoCentLabel.setRequestFocusEnabled(false);
				this.form_twoCentLabel.setVerifyInputWhenFocusTarget(false);
				this.form_twoCentLabel.setMaximumSize(null);
				this.form_twoCentLabel.setMinimumSize(new Dimension(44, 30));
				this.form_twoCentLabel.setName("twoCentLabel");
				this.form_wertPanel.add(this.form_twoCentLabel, "cell 0 6,growx,gapx null 20");

				//---- form_oneCentLabel ----
				this.form_oneCentLabel.setText("0,01 \u20ac");
				this.form_oneCentLabel.setPreferredSize(new Dimension(56, 30));
				this.form_oneCentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_oneCentLabel.setFont(this.form_oneCentLabel.getFont().deriveFont(this.form_oneCentLabel.getFont().getSize() + 3f));
				this.form_oneCentLabel.setFocusable(false);
				this.form_oneCentLabel.setInheritsPopupMenu(false);
				this.form_oneCentLabel.setRequestFocusEnabled(false);
				this.form_oneCentLabel.setVerifyInputWhenFocusTarget(false);
				this.form_oneCentLabel.setMaximumSize(null);
				this.form_oneCentLabel.setMinimumSize(new Dimension(44, 30));
				this.form_oneCentLabel.setName("oneCentLabel");
				this.form_wertPanel.add(this.form_oneCentLabel, "cell 0 7,growx,gapx null 20");

				//---- form_vSpacer4 ----
				this.form_vSpacer4.setName("vSpacer4");
				this.form_wertPanel.add(this.form_vSpacer4, "cell 0 8");

				//---- form_twohundredEuroLabel ----
				this.form_twohundredEuroLabel.setText("200,00 \u20ac");
				this.form_twohundredEuroLabel.setPreferredSize(new Dimension(56, 30));
				this.form_twohundredEuroLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_twohundredEuroLabel.setFont(this.form_twohundredEuroLabel.getFont().deriveFont(this.form_twohundredEuroLabel.getFont().getSize() + 3f));
				this.form_twohundredEuroLabel.setFocusable(false);
				this.form_twohundredEuroLabel.setInheritsPopupMenu(false);
				this.form_twohundredEuroLabel.setRequestFocusEnabled(false);
				this.form_twohundredEuroLabel.setVerifyInputWhenFocusTarget(false);
				this.form_twohundredEuroLabel.setMaximumSize(null);
				this.form_twohundredEuroLabel.setMinimumSize(new Dimension(44, 30));
				this.form_twohundredEuroLabel.setName("twohundredEuroLabel");
				this.form_wertPanel.add(this.form_twohundredEuroLabel, "cell 0 9,growx,gapx null 20");

				//---- form_onehundredEuroLabel ----
				this.form_onehundredEuroLabel.setText("100,00 \u20ac");
				this.form_onehundredEuroLabel.setPreferredSize(new Dimension(56, 30));
				this.form_onehundredEuroLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_onehundredEuroLabel.setFont(this.form_onehundredEuroLabel.getFont().deriveFont(this.form_onehundredEuroLabel.getFont().getSize() + 3f));
				this.form_onehundredEuroLabel.setFocusable(false);
				this.form_onehundredEuroLabel.setInheritsPopupMenu(false);
				this.form_onehundredEuroLabel.setRequestFocusEnabled(false);
				this.form_onehundredEuroLabel.setVerifyInputWhenFocusTarget(false);
				this.form_onehundredEuroLabel.setMaximumSize(null);
				this.form_onehundredEuroLabel.setMinimumSize(new Dimension(44, 30));
				this.form_onehundredEuroLabel.setName("onehundredEuroLabel");
				this.form_wertPanel.add(this.form_onehundredEuroLabel, "cell 0 10,growx,gapx null 20");

				//---- form_fiftyEuroLabel ----
				this.form_fiftyEuroLabel.setText("50,00 \u20ac");
				this.form_fiftyEuroLabel.setPreferredSize(new Dimension(56, 30));
				this.form_fiftyEuroLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_fiftyEuroLabel.setFont(this.form_fiftyEuroLabel.getFont().deriveFont(this.form_fiftyEuroLabel.getFont().getSize() + 3f));
				this.form_fiftyEuroLabel.setFocusable(false);
				this.form_fiftyEuroLabel.setInheritsPopupMenu(false);
				this.form_fiftyEuroLabel.setRequestFocusEnabled(false);
				this.form_fiftyEuroLabel.setVerifyInputWhenFocusTarget(false);
				this.form_fiftyEuroLabel.setMaximumSize(null);
				this.form_fiftyEuroLabel.setMinimumSize(new Dimension(44, 30));
				this.form_fiftyEuroLabel.setName("fiftyEuroLabel");
				this.form_wertPanel.add(this.form_fiftyEuroLabel, "cell 0 11,growx,gapx null 20");

				//---- form_twentyEuroLabel ----
				this.form_twentyEuroLabel.setText("20,00 \u20ac");
				this.form_twentyEuroLabel.setPreferredSize(new Dimension(56, 30));
				this.form_twentyEuroLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_twentyEuroLabel.setFont(this.form_twentyEuroLabel.getFont().deriveFont(this.form_twentyEuroLabel.getFont().getSize() + 3f));
				this.form_twentyEuroLabel.setFocusable(false);
				this.form_twentyEuroLabel.setInheritsPopupMenu(false);
				this.form_twentyEuroLabel.setRequestFocusEnabled(false);
				this.form_twentyEuroLabel.setVerifyInputWhenFocusTarget(false);
				this.form_twentyEuroLabel.setMaximumSize(null);
				this.form_twentyEuroLabel.setMinimumSize(new Dimension(44, 30));
				this.form_twentyEuroLabel.setName("twentyEuroLabel");
				this.form_wertPanel.add(this.form_twentyEuroLabel, "cell 0 12,growx,gapx null 20");

				//---- form_tenEuroLabel ----
				this.form_tenEuroLabel.setText("10,00 \u20ac");
				this.form_tenEuroLabel.setPreferredSize(new Dimension(56, 30));
				this.form_tenEuroLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_tenEuroLabel.setFont(this.form_tenEuroLabel.getFont().deriveFont(this.form_tenEuroLabel.getFont().getSize() + 3f));
				this.form_tenEuroLabel.setFocusable(false);
				this.form_tenEuroLabel.setInheritsPopupMenu(false);
				this.form_tenEuroLabel.setRequestFocusEnabled(false);
				this.form_tenEuroLabel.setVerifyInputWhenFocusTarget(false);
				this.form_tenEuroLabel.setMaximumSize(null);
				this.form_tenEuroLabel.setMinimumSize(new Dimension(44, 30));
				this.form_tenEuroLabel.setName("tenEuroLabel");
				this.form_wertPanel.add(this.form_tenEuroLabel, "cell 0 13,growx,gapx null 20");

				//---- form_fiveEuroLabel ----
				this.form_fiveEuroLabel.setText("5,00 \u20ac");
				this.form_fiveEuroLabel.setPreferredSize(new Dimension(56, 30));
				this.form_fiveEuroLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_fiveEuroLabel.setFont(this.form_fiveEuroLabel.getFont().deriveFont(this.form_fiveEuroLabel.getFont().getSize() + 3f));
				this.form_fiveEuroLabel.setFocusable(false);
				this.form_fiveEuroLabel.setInheritsPopupMenu(false);
				this.form_fiveEuroLabel.setRequestFocusEnabled(false);
				this.form_fiveEuroLabel.setVerifyInputWhenFocusTarget(false);
				this.form_fiveEuroLabel.setMaximumSize(null);
				this.form_fiveEuroLabel.setMinimumSize(new Dimension(44, 30));
				this.form_fiveEuroLabel.setName("fiveEuroLabel");
				this.form_wertPanel.add(this.form_fiveEuroLabel, "cell 0 14,growx,gapx null 20");
			}
			this.form_middlePanel.add(this.form_wertPanel);

			//======== form_ersteSpinnerNormal ========
			{
				this.form_ersteSpinnerNormal.setPreferredSize(new Dimension(100, 500));
				this.form_ersteSpinnerNormal.setName("ersteSpinnerNormal");
				this.form_ersteSpinnerNormal.setLayout(new MigLayout(
					"fill,alignx center",
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
				this.form_spinner2_1.setMaximumSize(null);
				this.form_spinner2_1.setMinimumSize(new Dimension(88, 30));
				this.form_spinner2_1.setToolTipText("twoEuro");
				this.form_spinner2_1.setName("spinner2_1");
				this.form_ersteSpinnerNormal.add(this.form_spinner2_1, "cell 0 0,growx");

				//---- form_spinner1_1 ----
				this.form_spinner1_1.setPreferredSize(new Dimension(64, 30));
				this.form_spinner1_1.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner1_1.setFont(this.form_spinner1_1.getFont().deriveFont(this.form_spinner1_1.getFont().getSize() + 3f));
				this.form_spinner1_1.setMaximumSize(null);
				this.form_spinner1_1.setMinimumSize(new Dimension(88, 30));
				this.form_spinner1_1.setName("spinner1_1");
				this.form_ersteSpinnerNormal.add(this.form_spinner1_1, "cell 0 1,growx");

				//---- form_spinner050_1 ----
				this.form_spinner050_1.setPreferredSize(new Dimension(64, 30));
				this.form_spinner050_1.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner050_1.setFont(this.form_spinner050_1.getFont().deriveFont(this.form_spinner050_1.getFont().getSize() + 3f));
				this.form_spinner050_1.setMaximumSize(null);
				this.form_spinner050_1.setMinimumSize(new Dimension(88, 30));
				this.form_spinner050_1.setName("spinner050_1");
				this.form_ersteSpinnerNormal.add(this.form_spinner050_1, "cell 0 2,growx");

				//---- form_spinner020_1 ----
				this.form_spinner020_1.setPreferredSize(new Dimension(64, 30));
				this.form_spinner020_1.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner020_1.setFont(this.form_spinner020_1.getFont().deriveFont(this.form_spinner020_1.getFont().getSize() + 3f));
				this.form_spinner020_1.setMaximumSize(null);
				this.form_spinner020_1.setMinimumSize(new Dimension(88, 30));
				this.form_spinner020_1.setName("spinner020_1");
				this.form_ersteSpinnerNormal.add(this.form_spinner020_1, "cell 0 3,growx");

				//---- form_spinner010_1 ----
				this.form_spinner010_1.setPreferredSize(new Dimension(64, 30));
				this.form_spinner010_1.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner010_1.setFont(this.form_spinner010_1.getFont().deriveFont(this.form_spinner010_1.getFont().getSize() + 3f));
				this.form_spinner010_1.setMaximumSize(null);
				this.form_spinner010_1.setMinimumSize(new Dimension(88, 30));
				this.form_spinner010_1.setName("spinner010_1");
				this.form_ersteSpinnerNormal.add(this.form_spinner010_1, "cell 0 4,growx");

				//---- form_spinner005_1 ----
				this.form_spinner005_1.setPreferredSize(new Dimension(64, 30));
				this.form_spinner005_1.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner005_1.setFont(this.form_spinner005_1.getFont().deriveFont(this.form_spinner005_1.getFont().getSize() + 3f));
				this.form_spinner005_1.setMaximumSize(null);
				this.form_spinner005_1.setMinimumSize(new Dimension(88, 30));
				this.form_spinner005_1.setName("spinner005_1");
				this.form_ersteSpinnerNormal.add(this.form_spinner005_1, "cell 0 5,growx");

				//---- form_spinner002_1 ----
				this.form_spinner002_1.setPreferredSize(new Dimension(64, 30));
				this.form_spinner002_1.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner002_1.setFont(this.form_spinner002_1.getFont().deriveFont(this.form_spinner002_1.getFont().getSize() + 3f));
				this.form_spinner002_1.setMaximumSize(null);
				this.form_spinner002_1.setMinimumSize(new Dimension(88, 30));
				this.form_spinner002_1.setName("spinner002_1");
				this.form_ersteSpinnerNormal.add(this.form_spinner002_1, "cell 0 6,growx");

				//---- form_spinner001_1 ----
				this.form_spinner001_1.setPreferredSize(new Dimension(64, 30));
				this.form_spinner001_1.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner001_1.setFont(this.form_spinner001_1.getFont().deriveFont(this.form_spinner001_1.getFont().getSize() + 3f));
				this.form_spinner001_1.setMaximumSize(null);
				this.form_spinner001_1.setMinimumSize(new Dimension(88, 30));
				this.form_spinner001_1.setName("spinner001_1");
				this.form_ersteSpinnerNormal.add(this.form_spinner001_1, "cell 0 7,growx");

				//---- form_vSpacer3 ----
				this.form_vSpacer3.setName("vSpacer3");
				this.form_ersteSpinnerNormal.add(this.form_vSpacer3, "cell 0 8");

				//---- form_spinner200_1 ----
				this.form_spinner200_1.setPreferredSize(new Dimension(64, 30));
				this.form_spinner200_1.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner200_1.setFont(this.form_spinner200_1.getFont().deriveFont(this.form_spinner200_1.getFont().getSize() + 3f));
				this.form_spinner200_1.setMaximumSize(null);
				this.form_spinner200_1.setMinimumSize(new Dimension(88, 30));
				this.form_spinner200_1.setName("spinner200_1");
				this.form_ersteSpinnerNormal.add(this.form_spinner200_1, "cell 0 9,growx");

				//---- form_spinner100_1 ----
				this.form_spinner100_1.setPreferredSize(new Dimension(64, 30));
				this.form_spinner100_1.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner100_1.setFont(this.form_spinner100_1.getFont().deriveFont(this.form_spinner100_1.getFont().getSize() + 3f));
				this.form_spinner100_1.setMaximumSize(null);
				this.form_spinner100_1.setMinimumSize(new Dimension(88, 30));
				this.form_spinner100_1.setName("spinner100_1");
				this.form_ersteSpinnerNormal.add(this.form_spinner100_1, "cell 0 10,growx");

				//---- form_spinner50_1 ----
				this.form_spinner50_1.setPreferredSize(new Dimension(64, 30));
				this.form_spinner50_1.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner50_1.setFont(this.form_spinner50_1.getFont().deriveFont(this.form_spinner50_1.getFont().getSize() + 3f));
				this.form_spinner50_1.setMaximumSize(null);
				this.form_spinner50_1.setMinimumSize(new Dimension(88, 30));
				this.form_spinner50_1.setName("spinner50_1");
				this.form_ersteSpinnerNormal.add(this.form_spinner50_1, "cell 0 11,growx");

				//---- form_spinner20_1 ----
				this.form_spinner20_1.setPreferredSize(new Dimension(64, 30));
				this.form_spinner20_1.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner20_1.setFont(this.form_spinner20_1.getFont().deriveFont(this.form_spinner20_1.getFont().getSize() + 3f));
				this.form_spinner20_1.setMaximumSize(null);
				this.form_spinner20_1.setMinimumSize(new Dimension(88, 30));
				this.form_spinner20_1.setName("spinner20_1");
				this.form_ersteSpinnerNormal.add(this.form_spinner20_1, "cell 0 12,growx");

				//---- form_spinner10_1 ----
				this.form_spinner10_1.setPreferredSize(new Dimension(64, 30));
				this.form_spinner10_1.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner10_1.setFont(this.form_spinner10_1.getFont().deriveFont(this.form_spinner10_1.getFont().getSize() + 3f));
				this.form_spinner10_1.setMaximumSize(null);
				this.form_spinner10_1.setMinimumSize(new Dimension(88, 30));
				this.form_spinner10_1.setName("spinner10_1");
				this.form_ersteSpinnerNormal.add(this.form_spinner10_1, "cell 0 13,growx");

				//---- form_spinner5_1 ----
				this.form_spinner5_1.setPreferredSize(new Dimension(64, 30));
				this.form_spinner5_1.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner5_1.setFont(this.form_spinner5_1.getFont().deriveFont(this.form_spinner5_1.getFont().getSize() + 3f));
				this.form_spinner5_1.setMaximumSize(null);
				this.form_spinner5_1.setMinimumSize(new Dimension(88, 30));
				this.form_spinner5_1.setName("spinner5_1");
				this.form_ersteSpinnerNormal.add(this.form_spinner5_1, "cell 0 14,growx");
			}
			this.form_middlePanel.add(this.form_ersteSpinnerNormal);

			//======== form_zweiteSpinnerNormal ========
			{
				this.form_zweiteSpinnerNormal.setPreferredSize(new Dimension(100, 500));
				this.form_zweiteSpinnerNormal.setFont(this.form_zweiteSpinnerNormal.getFont().deriveFont(this.form_zweiteSpinnerNormal.getFont().getSize() + 3f));
				this.form_zweiteSpinnerNormal.setName("zweiteSpinnerNormal");
				this.form_zweiteSpinnerNormal.setLayout(new MigLayout(
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
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]"));

				//---- form_spinner2_2 ----
				this.form_spinner2_2.setPreferredSize(new Dimension(64, 30));
				this.form_spinner2_2.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner2_2.setFont(this.form_spinner2_2.getFont().deriveFont(this.form_spinner2_2.getFont().getSize() + 3f));
				this.form_spinner2_2.setMinimumSize(new Dimension(88, 30));
				this.form_spinner2_2.setMaximumSize(null);
				this.form_spinner2_2.setToolTipText("twoEuro");
				this.form_spinner2_2.setName("spinner2_2");
				this.form_zweiteSpinnerNormal.add(this.form_spinner2_2, "cell 0 0,growx");

				//---- form_spinner1_2 ----
				this.form_spinner1_2.setPreferredSize(new Dimension(64, 30));
				this.form_spinner1_2.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner1_2.setFont(this.form_spinner1_2.getFont().deriveFont(this.form_spinner1_2.getFont().getSize() + 3f));
				this.form_spinner1_2.setMinimumSize(new Dimension(88, 30));
				this.form_spinner1_2.setMaximumSize(null);
				this.form_spinner1_2.setName("spinner1_2");
				this.form_zweiteSpinnerNormal.add(this.form_spinner1_2, "cell 0 1,growx");

				//---- form_spinner050_2 ----
				this.form_spinner050_2.setPreferredSize(new Dimension(64, 30));
				this.form_spinner050_2.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner050_2.setFont(this.form_spinner050_2.getFont().deriveFont(this.form_spinner050_2.getFont().getSize() + 3f));
				this.form_spinner050_2.setMinimumSize(new Dimension(88, 30));
				this.form_spinner050_2.setMaximumSize(null);
				this.form_spinner050_2.setName("spinner050_2");
				this.form_zweiteSpinnerNormal.add(this.form_spinner050_2, "cell 0 2,growx");

				//---- form_spinner020_2 ----
				this.form_spinner020_2.setPreferredSize(new Dimension(64, 30));
				this.form_spinner020_2.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner020_2.setFont(this.form_spinner020_2.getFont().deriveFont(this.form_spinner020_2.getFont().getSize() + 3f));
				this.form_spinner020_2.setMinimumSize(new Dimension(88, 30));
				this.form_spinner020_2.setMaximumSize(null);
				this.form_spinner020_2.setName("spinner020_2");
				this.form_zweiteSpinnerNormal.add(this.form_spinner020_2, "cell 0 3,growx");

				//---- form_spinner010_2 ----
				this.form_spinner010_2.setPreferredSize(new Dimension(64, 30));
				this.form_spinner010_2.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner010_2.setFont(this.form_spinner010_2.getFont().deriveFont(this.form_spinner010_2.getFont().getSize() + 3f));
				this.form_spinner010_2.setMinimumSize(new Dimension(88, 30));
				this.form_spinner010_2.setMaximumSize(null);
				this.form_spinner010_2.setName("spinner010_2");
				this.form_zweiteSpinnerNormal.add(this.form_spinner010_2, "cell 0 4,growx");

				//---- form_spinner005_2 ----
				this.form_spinner005_2.setPreferredSize(new Dimension(64, 30));
				this.form_spinner005_2.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner005_2.setFont(this.form_spinner005_2.getFont().deriveFont(this.form_spinner005_2.getFont().getSize() + 3f));
				this.form_spinner005_2.setMinimumSize(new Dimension(88, 30));
				this.form_spinner005_2.setMaximumSize(null);
				this.form_spinner005_2.setName("spinner005_2");
				this.form_zweiteSpinnerNormal.add(this.form_spinner005_2, "cell 0 5,growx");

				//---- form_spinner002_2 ----
				this.form_spinner002_2.setPreferredSize(new Dimension(64, 30));
				this.form_spinner002_2.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner002_2.setFont(this.form_spinner002_2.getFont().deriveFont(this.form_spinner002_2.getFont().getSize() + 3f));
				this.form_spinner002_2.setMinimumSize(new Dimension(88, 30));
				this.form_spinner002_2.setMaximumSize(null);
				this.form_spinner002_2.setName("spinner002_2");
				this.form_zweiteSpinnerNormal.add(this.form_spinner002_2, "cell 0 6,growx");

				//---- form_spinner001_2 ----
				this.form_spinner001_2.setPreferredSize(new Dimension(64, 30));
				this.form_spinner001_2.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner001_2.setFont(this.form_spinner001_2.getFont().deriveFont(this.form_spinner001_2.getFont().getSize() + 3f));
				this.form_spinner001_2.setMinimumSize(new Dimension(88, 30));
				this.form_spinner001_2.setMaximumSize(null);
				this.form_spinner001_2.setName("spinner001_2");
				this.form_zweiteSpinnerNormal.add(this.form_spinner001_2, "cell 0 7,growx");

				//---- form_vSpacer2 ----
				this.form_vSpacer2.setName("vSpacer2");
				this.form_zweiteSpinnerNormal.add(this.form_vSpacer2, "cell 0 8");

				//---- form_spinner200_2 ----
				this.form_spinner200_2.setPreferredSize(new Dimension(64, 30));
				this.form_spinner200_2.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner200_2.setFont(this.form_spinner200_2.getFont().deriveFont(this.form_spinner200_2.getFont().getSize() + 3f));
				this.form_spinner200_2.setMinimumSize(new Dimension(88, 30));
				this.form_spinner200_2.setMaximumSize(null);
				this.form_spinner200_2.setName("spinner200_2");
				this.form_zweiteSpinnerNormal.add(this.form_spinner200_2, "cell 0 9,growx");

				//---- form_spinner100_2 ----
				this.form_spinner100_2.setPreferredSize(new Dimension(64, 30));
				this.form_spinner100_2.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner100_2.setFont(this.form_spinner100_2.getFont().deriveFont(this.form_spinner100_2.getFont().getSize() + 3f));
				this.form_spinner100_2.setMinimumSize(new Dimension(88, 30));
				this.form_spinner100_2.setMaximumSize(null);
				this.form_spinner100_2.setName("spinner100_2");
				this.form_zweiteSpinnerNormal.add(this.form_spinner100_2, "cell 0 10,growx");

				//---- form_spinner50_2 ----
				this.form_spinner50_2.setPreferredSize(new Dimension(64, 30));
				this.form_spinner50_2.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner50_2.setFont(this.form_spinner50_2.getFont().deriveFont(this.form_spinner50_2.getFont().getSize() + 3f));
				this.form_spinner50_2.setMinimumSize(new Dimension(88, 30));
				this.form_spinner50_2.setMaximumSize(null);
				this.form_spinner50_2.setName("spinner50_2");
				this.form_zweiteSpinnerNormal.add(this.form_spinner50_2, "cell 0 11,growx");

				//---- form_spinner20_2 ----
				this.form_spinner20_2.setPreferredSize(new Dimension(64, 30));
				this.form_spinner20_2.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner20_2.setFont(this.form_spinner20_2.getFont().deriveFont(this.form_spinner20_2.getFont().getSize() + 3f));
				this.form_spinner20_2.setMinimumSize(new Dimension(88, 30));
				this.form_spinner20_2.setMaximumSize(null);
				this.form_spinner20_2.setName("spinner20_2");
				this.form_zweiteSpinnerNormal.add(this.form_spinner20_2, "cell 0 12,growx");

				//---- form_spinner10_2 ----
				this.form_spinner10_2.setPreferredSize(new Dimension(64, 30));
				this.form_spinner10_2.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner10_2.setFont(this.form_spinner10_2.getFont().deriveFont(this.form_spinner10_2.getFont().getSize() + 3f));
				this.form_spinner10_2.setMinimumSize(new Dimension(88, 30));
				this.form_spinner10_2.setMaximumSize(null);
				this.form_spinner10_2.setName("spinner10_2");
				this.form_zweiteSpinnerNormal.add(this.form_spinner10_2, "cell 0 13,growx");

				//---- form_spinner5_2 ----
				this.form_spinner5_2.setPreferredSize(new Dimension(64, 30));
				this.form_spinner5_2.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner5_2.setFont(this.form_spinner5_2.getFont().deriveFont(this.form_spinner5_2.getFont().getSize() + 3f));
				this.form_spinner5_2.setMinimumSize(new Dimension(88, 30));
				this.form_spinner5_2.setMaximumSize(null);
				this.form_spinner5_2.setName("spinner5_2");
				this.form_zweiteSpinnerNormal.add(this.form_spinner5_2, "cell 0 14,growx");
			}
			this.form_middlePanel.add(this.form_zweiteSpinnerNormal);

			//======== form_dritteSpinnerNormal ========
			{
				this.form_dritteSpinnerNormal.setPreferredSize(new Dimension(100, 500));
				this.form_dritteSpinnerNormal.setFont(this.form_dritteSpinnerNormal.getFont().deriveFont(this.form_dritteSpinnerNormal.getFont().getSize() + 3f));
				this.form_dritteSpinnerNormal.setName("dritteSpinnerNormal");
				this.form_dritteSpinnerNormal.setLayout(new MigLayout(
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
				this.form_spinner2_3.setMaximumSize(null);
				this.form_spinner2_3.setMinimumSize(new Dimension(88, 30));
				this.form_spinner2_3.setName("spinner2_3");
				this.form_dritteSpinnerNormal.add(this.form_spinner2_3, "cell 0 0,growx");

				//---- form_spinner1_3 ----
				this.form_spinner1_3.setPreferredSize(new Dimension(64, 30));
				this.form_spinner1_3.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner1_3.setFont(this.form_spinner1_3.getFont().deriveFont(this.form_spinner1_3.getFont().getSize() + 3f));
				this.form_spinner1_3.setMaximumSize(null);
				this.form_spinner1_3.setMinimumSize(new Dimension(88, 30));
				this.form_spinner1_3.setName("spinner1_3");
				this.form_dritteSpinnerNormal.add(this.form_spinner1_3, "cell 0 1,growx");

				//---- form_spinner050_3 ----
				this.form_spinner050_3.setPreferredSize(new Dimension(64, 30));
				this.form_spinner050_3.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner050_3.setFont(this.form_spinner050_3.getFont().deriveFont(this.form_spinner050_3.getFont().getSize() + 3f));
				this.form_spinner050_3.setMaximumSize(null);
				this.form_spinner050_3.setMinimumSize(new Dimension(88, 30));
				this.form_spinner050_3.setName("spinner050_3");
				this.form_dritteSpinnerNormal.add(this.form_spinner050_3, "cell 0 2,growx");

				//---- form_spinner020_3 ----
				this.form_spinner020_3.setPreferredSize(new Dimension(64, 30));
				this.form_spinner020_3.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner020_3.setFont(this.form_spinner020_3.getFont().deriveFont(this.form_spinner020_3.getFont().getSize() + 3f));
				this.form_spinner020_3.setMaximumSize(null);
				this.form_spinner020_3.setMinimumSize(new Dimension(88, 30));
				this.form_spinner020_3.setName("spinner020_3");
				this.form_dritteSpinnerNormal.add(this.form_spinner020_3, "cell 0 3,growx");

				//---- form_spinner010_3 ----
				this.form_spinner010_3.setPreferredSize(new Dimension(64, 30));
				this.form_spinner010_3.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner010_3.setFont(this.form_spinner010_3.getFont().deriveFont(this.form_spinner010_3.getFont().getSize() + 3f));
				this.form_spinner010_3.setMaximumSize(null);
				this.form_spinner010_3.setMinimumSize(new Dimension(88, 30));
				this.form_spinner010_3.setName("spinner010_3");
				this.form_dritteSpinnerNormal.add(this.form_spinner010_3, "cell 0 4,growx");

				//---- form_spinner005_3 ----
				this.form_spinner005_3.setPreferredSize(new Dimension(64, 30));
				this.form_spinner005_3.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner005_3.setFont(this.form_spinner005_3.getFont().deriveFont(this.form_spinner005_3.getFont().getSize() + 3f));
				this.form_spinner005_3.setMaximumSize(null);
				this.form_spinner005_3.setMinimumSize(new Dimension(88, 30));
				this.form_spinner005_3.setName("spinner005_3");
				this.form_dritteSpinnerNormal.add(this.form_spinner005_3, "cell 0 5,growx");

				//---- form_spinner002_3 ----
				this.form_spinner002_3.setPreferredSize(new Dimension(64, 30));
				this.form_spinner002_3.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner002_3.setFont(this.form_spinner002_3.getFont().deriveFont(this.form_spinner002_3.getFont().getSize() + 3f));
				this.form_spinner002_3.setMaximumSize(null);
				this.form_spinner002_3.setMinimumSize(new Dimension(88, 30));
				this.form_spinner002_3.setName("spinner002_3");
				this.form_dritteSpinnerNormal.add(this.form_spinner002_3, "cell 0 6,growx");

				//---- form_spinner001_3 ----
				this.form_spinner001_3.setPreferredSize(new Dimension(64, 30));
				this.form_spinner001_3.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner001_3.setFont(this.form_spinner001_3.getFont().deriveFont(this.form_spinner001_3.getFont().getSize() + 3f));
				this.form_spinner001_3.setMaximumSize(null);
				this.form_spinner001_3.setMinimumSize(new Dimension(88, 30));
				this.form_spinner001_3.setName("spinner001_3");
				this.form_dritteSpinnerNormal.add(this.form_spinner001_3, "cell 0 7,growx");

				//---- form_vSpacer5 ----
				this.form_vSpacer5.setName("vSpacer5");
				this.form_dritteSpinnerNormal.add(this.form_vSpacer5, "cell 0 8");

				//---- form_spinner200_3 ----
				this.form_spinner200_3.setPreferredSize(new Dimension(64, 30));
				this.form_spinner200_3.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner200_3.setFont(this.form_spinner200_3.getFont().deriveFont(this.form_spinner200_3.getFont().getSize() + 3f));
				this.form_spinner200_3.setMaximumSize(null);
				this.form_spinner200_3.setMinimumSize(new Dimension(88, 30));
				this.form_spinner200_3.setName("spinner200_3");
				this.form_dritteSpinnerNormal.add(this.form_spinner200_3, "cell 0 9,growx");

				//---- form_spinner100_3 ----
				this.form_spinner100_3.setPreferredSize(new Dimension(64, 30));
				this.form_spinner100_3.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner100_3.setFont(this.form_spinner100_3.getFont().deriveFont(this.form_spinner100_3.getFont().getSize() + 3f));
				this.form_spinner100_3.setMaximumSize(null);
				this.form_spinner100_3.setMinimumSize(new Dimension(88, 30));
				this.form_spinner100_3.setName("spinner100_3");
				this.form_dritteSpinnerNormal.add(this.form_spinner100_3, "cell 0 10,growx");

				//---- form_spinner50_3 ----
				this.form_spinner50_3.setPreferredSize(new Dimension(64, 30));
				this.form_spinner50_3.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner50_3.setFont(this.form_spinner50_3.getFont().deriveFont(this.form_spinner50_3.getFont().getSize() + 3f));
				this.form_spinner50_3.setMaximumSize(null);
				this.form_spinner50_3.setMinimumSize(new Dimension(88, 30));
				this.form_spinner50_3.setName("spinner50_3");
				this.form_dritteSpinnerNormal.add(this.form_spinner50_3, "cell 0 11,growx");

				//---- form_spinner20_3 ----
				this.form_spinner20_3.setPreferredSize(new Dimension(64, 30));
				this.form_spinner20_3.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner20_3.setFont(this.form_spinner20_3.getFont().deriveFont(this.form_spinner20_3.getFont().getSize() + 3f));
				this.form_spinner20_3.setMaximumSize(null);
				this.form_spinner20_3.setMinimumSize(new Dimension(88, 30));
				this.form_spinner20_3.setName("spinner20_3");
				this.form_dritteSpinnerNormal.add(this.form_spinner20_3, "cell 0 12,growx");

				//---- form_spinner10_3 ----
				this.form_spinner10_3.setPreferredSize(new Dimension(64, 30));
				this.form_spinner10_3.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner10_3.setFont(this.form_spinner10_3.getFont().deriveFont(this.form_spinner10_3.getFont().getSize() + 3f));
				this.form_spinner10_3.setMaximumSize(null);
				this.form_spinner10_3.setMinimumSize(new Dimension(88, 30));
				this.form_spinner10_3.setName("spinner10_3");
				this.form_dritteSpinnerNormal.add(this.form_spinner10_3, "cell 0 13,growx");

				//---- form_spinner5_3 ----
				this.form_spinner5_3.setPreferredSize(new Dimension(64, 30));
				this.form_spinner5_3.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner5_3.setFont(this.form_spinner5_3.getFont().deriveFont(this.form_spinner5_3.getFont().getSize() + 3f));
				this.form_spinner5_3.setMaximumSize(null);
				this.form_spinner5_3.setMinimumSize(new Dimension(88, 30));
				this.form_spinner5_3.setName("spinner5_3");
				this.form_dritteSpinnerNormal.add(this.form_spinner5_3, "cell 0 14,growx");
			}
			this.form_middlePanel.add(this.form_dritteSpinnerNormal);

			//======== form_vierteSpinnerNormal ========
			{
				this.form_vierteSpinnerNormal.setPreferredSize(new Dimension(100, 500));
				this.form_vierteSpinnerNormal.setFont(this.form_vierteSpinnerNormal.getFont().deriveFont(this.form_vierteSpinnerNormal.getFont().getSize() + 3f));
				this.form_vierteSpinnerNormal.setName("vierteSpinnerNormal");
				this.form_vierteSpinnerNormal.setLayout(new MigLayout(
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
				this.form_spinner2_4.setMinimumSize(new Dimension(88, 30));
				this.form_spinner2_4.setMaximumSize(null);
				this.form_spinner2_4.setName("spinner2_4");
				this.form_vierteSpinnerNormal.add(this.form_spinner2_4, "cell 0 0,growx");

				//---- form_spinner1_4 ----
				this.form_spinner1_4.setPreferredSize(new Dimension(64, 30));
				this.form_spinner1_4.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner1_4.setFont(this.form_spinner1_4.getFont().deriveFont(this.form_spinner1_4.getFont().getSize() + 3f));
				this.form_spinner1_4.setMinimumSize(new Dimension(88, 30));
				this.form_spinner1_4.setMaximumSize(null);
				this.form_spinner1_4.setName("spinner1_4");
				this.form_vierteSpinnerNormal.add(this.form_spinner1_4, "cell 0 1,growx");

				//---- form_spinner050_4 ----
				this.form_spinner050_4.setPreferredSize(new Dimension(64, 30));
				this.form_spinner050_4.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner050_4.setFont(this.form_spinner050_4.getFont().deriveFont(this.form_spinner050_4.getFont().getSize() + 3f));
				this.form_spinner050_4.setMinimumSize(new Dimension(88, 30));
				this.form_spinner050_4.setMaximumSize(null);
				this.form_spinner050_4.setName("spinner050_4");
				this.form_vierteSpinnerNormal.add(this.form_spinner050_4, "cell 0 2,growx");

				//---- form_spinner020_4 ----
				this.form_spinner020_4.setPreferredSize(new Dimension(64, 30));
				this.form_spinner020_4.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner020_4.setFont(this.form_spinner020_4.getFont().deriveFont(this.form_spinner020_4.getFont().getSize() + 3f));
				this.form_spinner020_4.setMinimumSize(new Dimension(88, 30));
				this.form_spinner020_4.setMaximumSize(null);
				this.form_spinner020_4.setName("spinner020_4");
				this.form_vierteSpinnerNormal.add(this.form_spinner020_4, "cell 0 3,growx");

				//---- form_spinner010_4 ----
				this.form_spinner010_4.setPreferredSize(new Dimension(64, 30));
				this.form_spinner010_4.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner010_4.setFont(this.form_spinner010_4.getFont().deriveFont(this.form_spinner010_4.getFont().getSize() + 3f));
				this.form_spinner010_4.setMinimumSize(new Dimension(88, 30));
				this.form_spinner010_4.setMaximumSize(null);
				this.form_spinner010_4.setName("spinner010_4");
				this.form_vierteSpinnerNormal.add(this.form_spinner010_4, "cell 0 4,growx");

				//---- form_spinner005_4 ----
				this.form_spinner005_4.setPreferredSize(new Dimension(64, 30));
				this.form_spinner005_4.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner005_4.setFont(this.form_spinner005_4.getFont().deriveFont(this.form_spinner005_4.getFont().getSize() + 3f));
				this.form_spinner005_4.setMinimumSize(new Dimension(88, 30));
				this.form_spinner005_4.setMaximumSize(null);
				this.form_spinner005_4.setName("spinner005_4");
				this.form_vierteSpinnerNormal.add(this.form_spinner005_4, "cell 0 5,growx");

				//---- form_spinner002_4 ----
				this.form_spinner002_4.setPreferredSize(new Dimension(64, 30));
				this.form_spinner002_4.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner002_4.setFont(this.form_spinner002_4.getFont().deriveFont(this.form_spinner002_4.getFont().getSize() + 3f));
				this.form_spinner002_4.setMinimumSize(new Dimension(88, 30));
				this.form_spinner002_4.setMaximumSize(null);
				this.form_spinner002_4.setName("spinner002_4");
				this.form_vierteSpinnerNormal.add(this.form_spinner002_4, "cell 0 6,growx");

				//---- form_spinner001_4 ----
				this.form_spinner001_4.setPreferredSize(new Dimension(64, 30));
				this.form_spinner001_4.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner001_4.setFont(this.form_spinner001_4.getFont().deriveFont(this.form_spinner001_4.getFont().getSize() + 3f));
				this.form_spinner001_4.setMinimumSize(new Dimension(88, 30));
				this.form_spinner001_4.setMaximumSize(null);
				this.form_spinner001_4.setName("spinner001_4");
				this.form_vierteSpinnerNormal.add(this.form_spinner001_4, "cell 0 7,growx");

				//---- form_vSpacer6 ----
				this.form_vSpacer6.setName("vSpacer6");
				this.form_vierteSpinnerNormal.add(this.form_vSpacer6, "cell 0 8");

				//---- form_spinner200_4 ----
				this.form_spinner200_4.setPreferredSize(new Dimension(64, 30));
				this.form_spinner200_4.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner200_4.setFont(this.form_spinner200_4.getFont().deriveFont(this.form_spinner200_4.getFont().getSize() + 3f));
				this.form_spinner200_4.setMinimumSize(new Dimension(88, 30));
				this.form_spinner200_4.setMaximumSize(null);
				this.form_spinner200_4.setName("spinner200_4");
				this.form_vierteSpinnerNormal.add(this.form_spinner200_4, "cell 0 9,growx");

				//---- form_spinner100_4 ----
				this.form_spinner100_4.setPreferredSize(new Dimension(64, 30));
				this.form_spinner100_4.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner100_4.setFont(this.form_spinner100_4.getFont().deriveFont(this.form_spinner100_4.getFont().getSize() + 3f));
				this.form_spinner100_4.setMinimumSize(new Dimension(88, 30));
				this.form_spinner100_4.setMaximumSize(null);
				this.form_spinner100_4.setName("spinner100_4");
				this.form_vierteSpinnerNormal.add(this.form_spinner100_4, "cell 0 10,growx");

				//---- form_spinner50_4 ----
				this.form_spinner50_4.setPreferredSize(new Dimension(64, 30));
				this.form_spinner50_4.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner50_4.setFont(this.form_spinner50_4.getFont().deriveFont(this.form_spinner50_4.getFont().getSize() + 3f));
				this.form_spinner50_4.setMinimumSize(new Dimension(88, 30));
				this.form_spinner50_4.setMaximumSize(null);
				this.form_spinner50_4.setName("spinner50_4");
				this.form_vierteSpinnerNormal.add(this.form_spinner50_4, "cell 0 11,growx");

				//---- form_spinner20_4 ----
				this.form_spinner20_4.setPreferredSize(new Dimension(64, 30));
				this.form_spinner20_4.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner20_4.setFont(this.form_spinner20_4.getFont().deriveFont(this.form_spinner20_4.getFont().getSize() + 3f));
				this.form_spinner20_4.setMinimumSize(new Dimension(88, 30));
				this.form_spinner20_4.setMaximumSize(null);
				this.form_spinner20_4.setName("spinner20_4");
				this.form_vierteSpinnerNormal.add(this.form_spinner20_4, "cell 0 12,growx");

				//---- form_spinner10_4 ----
				this.form_spinner10_4.setPreferredSize(new Dimension(64, 30));
				this.form_spinner10_4.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner10_4.setFont(this.form_spinner10_4.getFont().deriveFont(this.form_spinner10_4.getFont().getSize() + 3f));
				this.form_spinner10_4.setMinimumSize(new Dimension(88, 30));
				this.form_spinner10_4.setMaximumSize(null);
				this.form_spinner10_4.setName("spinner10_4");
				this.form_vierteSpinnerNormal.add(this.form_spinner10_4, "cell 0 13,growx");

				//---- form_spinner5_4 ----
				this.form_spinner5_4.setPreferredSize(new Dimension(64, 30));
				this.form_spinner5_4.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner5_4.setFont(this.form_spinner5_4.getFont().deriveFont(this.form_spinner5_4.getFont().getSize() + 3f));
				this.form_spinner5_4.setMinimumSize(new Dimension(88, 30));
				this.form_spinner5_4.setMaximumSize(null);
				this.form_spinner5_4.setName("spinner5_4");
				this.form_vierteSpinnerNormal.add(this.form_spinner5_4, "cell 0 14,growx");
			}
			this.form_middlePanel.add(this.form_vierteSpinnerNormal);

			//======== form_fuenfteSpinnerNormal ========
			{
				this.form_fuenfteSpinnerNormal.setPreferredSize(new Dimension(100, 500));
				this.form_fuenfteSpinnerNormal.setFont(this.form_fuenfteSpinnerNormal.getFont().deriveFont(this.form_fuenfteSpinnerNormal.getFont().getSize() + 3f));
				this.form_fuenfteSpinnerNormal.setName("fuenfteSpinnerNormal");
				this.form_fuenfteSpinnerNormal.setLayout(new MigLayout(
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
				this.form_spinner2_5.setMinimumSize(new Dimension(88, 30));
				this.form_spinner2_5.setMaximumSize(null);
				this.form_spinner2_5.setName("spinner2_5");
				this.form_fuenfteSpinnerNormal.add(this.form_spinner2_5, "cell 0 0,growx");

				//---- form_spinner1_5 ----
				this.form_spinner1_5.setPreferredSize(new Dimension(64, 30));
				this.form_spinner1_5.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner1_5.setFont(this.form_spinner1_5.getFont().deriveFont(this.form_spinner1_5.getFont().getSize() + 3f));
				this.form_spinner1_5.setMinimumSize(new Dimension(88, 30));
				this.form_spinner1_5.setMaximumSize(null);
				this.form_spinner1_5.setName("spinner1_5");
				this.form_fuenfteSpinnerNormal.add(this.form_spinner1_5, "cell 0 1,growx");

				//---- form_spinner050_5 ----
				this.form_spinner050_5.setPreferredSize(new Dimension(64, 30));
				this.form_spinner050_5.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner050_5.setFont(this.form_spinner050_5.getFont().deriveFont(this.form_spinner050_5.getFont().getSize() + 3f));
				this.form_spinner050_5.setMinimumSize(new Dimension(88, 30));
				this.form_spinner050_5.setMaximumSize(null);
				this.form_spinner050_5.setName("spinner050_5");
				this.form_fuenfteSpinnerNormal.add(this.form_spinner050_5, "cell 0 2,growx");

				//---- form_spinner020_5 ----
				this.form_spinner020_5.setPreferredSize(new Dimension(64, 30));
				this.form_spinner020_5.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner020_5.setFont(this.form_spinner020_5.getFont().deriveFont(this.form_spinner020_5.getFont().getSize() + 3f));
				this.form_spinner020_5.setMinimumSize(new Dimension(88, 30));
				this.form_spinner020_5.setMaximumSize(null);
				this.form_spinner020_5.setName("spinner020_5");
				this.form_fuenfteSpinnerNormal.add(this.form_spinner020_5, "cell 0 3,growx");

				//---- form_spinner010_5 ----
				this.form_spinner010_5.setPreferredSize(new Dimension(64, 30));
				this.form_spinner010_5.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner010_5.setFont(this.form_spinner010_5.getFont().deriveFont(this.form_spinner010_5.getFont().getSize() + 3f));
				this.form_spinner010_5.setMinimumSize(new Dimension(88, 30));
				this.form_spinner010_5.setMaximumSize(null);
				this.form_spinner010_5.setName("spinner010_5");
				this.form_fuenfteSpinnerNormal.add(this.form_spinner010_5, "cell 0 4,growx");

				//---- form_spinner005_5 ----
				this.form_spinner005_5.setPreferredSize(new Dimension(64, 30));
				this.form_spinner005_5.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner005_5.setFont(this.form_spinner005_5.getFont().deriveFont(this.form_spinner005_5.getFont().getSize() + 3f));
				this.form_spinner005_5.setMinimumSize(new Dimension(88, 30));
				this.form_spinner005_5.setMaximumSize(null);
				this.form_spinner005_5.setName("spinner005_5");
				this.form_fuenfteSpinnerNormal.add(this.form_spinner005_5, "cell 0 5,growx");

				//---- form_spinner002_5 ----
				this.form_spinner002_5.setPreferredSize(new Dimension(64, 30));
				this.form_spinner002_5.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner002_5.setFont(this.form_spinner002_5.getFont().deriveFont(this.form_spinner002_5.getFont().getSize() + 3f));
				this.form_spinner002_5.setMinimumSize(new Dimension(88, 30));
				this.form_spinner002_5.setMaximumSize(null);
				this.form_spinner002_5.setName("spinner002_5");
				this.form_fuenfteSpinnerNormal.add(this.form_spinner002_5, "cell 0 6,growx");

				//---- form_spinner001_5 ----
				this.form_spinner001_5.setPreferredSize(new Dimension(64, 30));
				this.form_spinner001_5.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner001_5.setFont(this.form_spinner001_5.getFont().deriveFont(this.form_spinner001_5.getFont().getSize() + 3f));
				this.form_spinner001_5.setMinimumSize(new Dimension(88, 30));
				this.form_spinner001_5.setMaximumSize(null);
				this.form_spinner001_5.setName("spinner001_5");
				this.form_fuenfteSpinnerNormal.add(this.form_spinner001_5, "cell 0 7,growx");

				//---- form_vSpacer7 ----
				this.form_vSpacer7.setName("vSpacer7");
				this.form_fuenfteSpinnerNormal.add(this.form_vSpacer7, "cell 0 8");

				//---- form_spinner200_5 ----
				this.form_spinner200_5.setPreferredSize(new Dimension(64, 30));
				this.form_spinner200_5.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner200_5.setFont(this.form_spinner200_5.getFont().deriveFont(this.form_spinner200_5.getFont().getSize() + 3f));
				this.form_spinner200_5.setMinimumSize(new Dimension(88, 30));
				this.form_spinner200_5.setMaximumSize(null);
				this.form_spinner200_5.setName("spinner200_5");
				this.form_fuenfteSpinnerNormal.add(this.form_spinner200_5, "cell 0 9,growx");

				//---- form_spinner100_5 ----
				this.form_spinner100_5.setPreferredSize(new Dimension(64, 30));
				this.form_spinner100_5.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner100_5.setFont(this.form_spinner100_5.getFont().deriveFont(this.form_spinner100_5.getFont().getSize() + 3f));
				this.form_spinner100_5.setMinimumSize(new Dimension(88, 30));
				this.form_spinner100_5.setMaximumSize(null);
				this.form_spinner100_5.setName("spinner100_5");
				this.form_fuenfteSpinnerNormal.add(this.form_spinner100_5, "cell 0 10,growx");

				//---- form_spinner50_5 ----
				this.form_spinner50_5.setPreferredSize(new Dimension(64, 30));
				this.form_spinner50_5.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner50_5.setFont(this.form_spinner50_5.getFont().deriveFont(this.form_spinner50_5.getFont().getSize() + 3f));
				this.form_spinner50_5.setMinimumSize(new Dimension(88, 30));
				this.form_spinner50_5.setMaximumSize(null);
				this.form_spinner50_5.setName("spinner50_5");
				this.form_fuenfteSpinnerNormal.add(this.form_spinner50_5, "cell 0 11,growx");

				//---- form_spinner20_5 ----
				this.form_spinner20_5.setPreferredSize(new Dimension(64, 30));
				this.form_spinner20_5.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner20_5.setFont(this.form_spinner20_5.getFont().deriveFont(this.form_spinner20_5.getFont().getSize() + 3f));
				this.form_spinner20_5.setMinimumSize(new Dimension(88, 30));
				this.form_spinner20_5.setMaximumSize(null);
				this.form_spinner20_5.setName("spinner20_5");
				this.form_fuenfteSpinnerNormal.add(this.form_spinner20_5, "cell 0 12,growx");

				//---- form_spinner10_5 ----
				this.form_spinner10_5.setPreferredSize(new Dimension(64, 30));
				this.form_spinner10_5.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner10_5.setFont(this.form_spinner10_5.getFont().deriveFont(this.form_spinner10_5.getFont().getSize() + 3f));
				this.form_spinner10_5.setMinimumSize(new Dimension(88, 30));
				this.form_spinner10_5.setMaximumSize(null);
				this.form_spinner10_5.setName("spinner10_5");
				this.form_fuenfteSpinnerNormal.add(this.form_spinner10_5, "cell 0 13,growx");

				//---- form_spinner5_5 ----
				this.form_spinner5_5.setPreferredSize(new Dimension(64, 30));
				this.form_spinner5_5.setModel(new SpinnerNumberModel(0, 0, 999, 1));
				this.form_spinner5_5.setFont(this.form_spinner5_5.getFont().deriveFont(this.form_spinner5_5.getFont().getSize() + 3f));
				this.form_spinner5_5.setMinimumSize(new Dimension(88, 30));
				this.form_spinner5_5.setMaximumSize(null);
				this.form_spinner5_5.setName("spinner5_5");
				this.form_fuenfteSpinnerNormal.add(this.form_spinner5_5, "cell 0 14,growx");
			}
			this.form_middlePanel.add(this.form_fuenfteSpinnerNormal);

			//======== form_anzahlPanelNormal ========
			{
				this.form_anzahlPanelNormal.setPreferredSize(new Dimension(100, 450));
				this.form_anzahlPanelNormal.setFont(this.form_anzahlPanelNormal.getFont().deriveFont(this.form_anzahlPanelNormal.getFont().getSize() + 3f));
				this.form_anzahlPanelNormal.setName("anzahlPanelNormal");
				this.form_anzahlPanelNormal.setLayout(new MigLayout(
					"fill",
					// columns
					"[grow]" +
					"[]",
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
				this.form_twoEuroLabelAmount.setMinimumSize(new Dimension(9, 30));
				this.form_twoEuroLabelAmount.setName("twoEuroLabelAmount");
				this.form_anzahlPanelNormal.add(this.form_twoEuroLabelAmount, "cell 0 0,growx");

				//---- form_twoEuroLabelX ----
				this.form_twoEuroLabelX.setText("x");
				this.form_twoEuroLabelX.setPreferredSize(new Dimension(15, 30));
				this.form_twoEuroLabelX.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_twoEuroLabelX.setFont(this.form_twoEuroLabelX.getFont().deriveFont(this.form_twoEuroLabelX.getFont().getSize() + 3f));
				this.form_twoEuroLabelX.setMaximumSize(null);
				this.form_twoEuroLabelX.setMinimumSize(new Dimension(9, 30));
				this.form_twoEuroLabelX.setName("twoEuroLabelX");
				this.form_anzahlPanelNormal.add(this.form_twoEuroLabelX, "cell 1 0,growx,gapx 5");

				//---- form_oneEuroLabelAmount ----
				this.form_oneEuroLabelAmount.setText("0");
				this.form_oneEuroLabelAmount.setPreferredSize(new Dimension(56, 30));
				this.form_oneEuroLabelAmount.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_oneEuroLabelAmount.setFont(this.form_oneEuroLabelAmount.getFont().deriveFont(this.form_oneEuroLabelAmount.getFont().getSize() + 3f));
				this.form_oneEuroLabelAmount.setMaximumSize(null);
				this.form_oneEuroLabelAmount.setMinimumSize(new Dimension(9, 30));
				this.form_oneEuroLabelAmount.setName("oneEuroLabelAmount");
				this.form_anzahlPanelNormal.add(this.form_oneEuroLabelAmount, "cell 0 1,growx");

				//---- form_oneEuroLabelX ----
				this.form_oneEuroLabelX.setText("x");
				this.form_oneEuroLabelX.setPreferredSize(new Dimension(15, 30));
				this.form_oneEuroLabelX.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_oneEuroLabelX.setFont(this.form_oneEuroLabelX.getFont().deriveFont(this.form_oneEuroLabelX.getFont().getSize() + 3f));
				this.form_oneEuroLabelX.setMaximumSize(null);
				this.form_oneEuroLabelX.setMinimumSize(new Dimension(9, 30));
				this.form_oneEuroLabelX.setName("oneEuroLabelX");
				this.form_anzahlPanelNormal.add(this.form_oneEuroLabelX, "cell 1 1,growx,gapx 5");

				//---- form_fiftyCentLabelAmount ----
				this.form_fiftyCentLabelAmount.setText("0");
				this.form_fiftyCentLabelAmount.setPreferredSize(new Dimension(56, 30));
				this.form_fiftyCentLabelAmount.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_fiftyCentLabelAmount.setFont(this.form_fiftyCentLabelAmount.getFont().deriveFont(this.form_fiftyCentLabelAmount.getFont().getSize() + 3f));
				this.form_fiftyCentLabelAmount.setMaximumSize(null);
				this.form_fiftyCentLabelAmount.setMinimumSize(new Dimension(9, 30));
				this.form_fiftyCentLabelAmount.setName("fiftyCentLabelAmount");
				this.form_anzahlPanelNormal.add(this.form_fiftyCentLabelAmount, "cell 0 2,growx");

				//---- form_fiftyCentLabelX ----
				this.form_fiftyCentLabelX.setText("x");
				this.form_fiftyCentLabelX.setPreferredSize(new Dimension(15, 30));
				this.form_fiftyCentLabelX.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_fiftyCentLabelX.setFont(this.form_fiftyCentLabelX.getFont().deriveFont(this.form_fiftyCentLabelX.getFont().getSize() + 3f));
				this.form_fiftyCentLabelX.setMaximumSize(null);
				this.form_fiftyCentLabelX.setMinimumSize(new Dimension(9, 30));
				this.form_fiftyCentLabelX.setName("fiftyCentLabelX");
				this.form_anzahlPanelNormal.add(this.form_fiftyCentLabelX, "cell 1 2,growx,gapx 5");

				//---- form_twentyCentLabelAmount ----
				this.form_twentyCentLabelAmount.setText("0");
				this.form_twentyCentLabelAmount.setPreferredSize(new Dimension(56, 30));
				this.form_twentyCentLabelAmount.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_twentyCentLabelAmount.setFont(this.form_twentyCentLabelAmount.getFont().deriveFont(this.form_twentyCentLabelAmount.getFont().getSize() + 3f));
				this.form_twentyCentLabelAmount.setMaximumSize(null);
				this.form_twentyCentLabelAmount.setMinimumSize(new Dimension(9, 30));
				this.form_twentyCentLabelAmount.setName("twentyCentLabelAmount");
				this.form_anzahlPanelNormal.add(this.form_twentyCentLabelAmount, "cell 0 3,growx");

				//---- form_twentyCentLabelX ----
				this.form_twentyCentLabelX.setText("x");
				this.form_twentyCentLabelX.setPreferredSize(new Dimension(15, 30));
				this.form_twentyCentLabelX.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_twentyCentLabelX.setFont(this.form_twentyCentLabelX.getFont().deriveFont(this.form_twentyCentLabelX.getFont().getSize() + 3f));
				this.form_twentyCentLabelX.setMaximumSize(null);
				this.form_twentyCentLabelX.setMinimumSize(new Dimension(9, 30));
				this.form_twentyCentLabelX.setName("twentyCentLabelX");
				this.form_anzahlPanelNormal.add(this.form_twentyCentLabelX, "cell 1 3,growx,gapx 5");

				//---- form_tenCentLabelAmount ----
				this.form_tenCentLabelAmount.setText("0");
				this.form_tenCentLabelAmount.setPreferredSize(new Dimension(56, 30));
				this.form_tenCentLabelAmount.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_tenCentLabelAmount.setFont(this.form_tenCentLabelAmount.getFont().deriveFont(this.form_tenCentLabelAmount.getFont().getSize() + 3f));
				this.form_tenCentLabelAmount.setMaximumSize(null);
				this.form_tenCentLabelAmount.setMinimumSize(new Dimension(9, 30));
				this.form_tenCentLabelAmount.setName("tenCentLabelAmount");
				this.form_anzahlPanelNormal.add(this.form_tenCentLabelAmount, "cell 0 4,growx");

				//---- form_tenCentLabelX ----
				this.form_tenCentLabelX.setText("x");
				this.form_tenCentLabelX.setPreferredSize(new Dimension(15, 30));
				this.form_tenCentLabelX.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_tenCentLabelX.setFont(this.form_tenCentLabelX.getFont().deriveFont(this.form_tenCentLabelX.getFont().getSize() + 3f));
				this.form_tenCentLabelX.setMaximumSize(null);
				this.form_tenCentLabelX.setMinimumSize(new Dimension(9, 30));
				this.form_tenCentLabelX.setName("tenCentLabelX");
				this.form_anzahlPanelNormal.add(this.form_tenCentLabelX, "cell 1 4,growx,gapx 5");

				//---- form_fiveCentLabelAmount ----
				this.form_fiveCentLabelAmount.setText("0");
				this.form_fiveCentLabelAmount.setPreferredSize(new Dimension(56, 30));
				this.form_fiveCentLabelAmount.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_fiveCentLabelAmount.setFont(this.form_fiveCentLabelAmount.getFont().deriveFont(this.form_fiveCentLabelAmount.getFont().getSize() + 3f));
				this.form_fiveCentLabelAmount.setMaximumSize(null);
				this.form_fiveCentLabelAmount.setMinimumSize(new Dimension(9, 30));
				this.form_fiveCentLabelAmount.setName("fiveCentLabelAmount");
				this.form_anzahlPanelNormal.add(this.form_fiveCentLabelAmount, "cell 0 5,growx");

				//---- form_fiveCentLabelX ----
				this.form_fiveCentLabelX.setText("x");
				this.form_fiveCentLabelX.setPreferredSize(new Dimension(15, 30));
				this.form_fiveCentLabelX.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_fiveCentLabelX.setFont(this.form_fiveCentLabelX.getFont().deriveFont(this.form_fiveCentLabelX.getFont().getSize() + 3f));
				this.form_fiveCentLabelX.setMaximumSize(null);
				this.form_fiveCentLabelX.setMinimumSize(new Dimension(9, 30));
				this.form_fiveCentLabelX.setName("fiveCentLabelX");
				this.form_anzahlPanelNormal.add(this.form_fiveCentLabelX, "cell 1 5,growx,gapx 5");

				//---- form_twoCentLabelAmount ----
				this.form_twoCentLabelAmount.setText("0");
				this.form_twoCentLabelAmount.setPreferredSize(new Dimension(56, 30));
				this.form_twoCentLabelAmount.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_twoCentLabelAmount.setFont(this.form_twoCentLabelAmount.getFont().deriveFont(this.form_twoCentLabelAmount.getFont().getSize() + 3f));
				this.form_twoCentLabelAmount.setMaximumSize(null);
				this.form_twoCentLabelAmount.setMinimumSize(new Dimension(9, 30));
				this.form_twoCentLabelAmount.setName("twoCentLabelAmount");
				this.form_anzahlPanelNormal.add(this.form_twoCentLabelAmount, "cell 0 6,growx");

				//---- form_twoCentLabelX ----
				this.form_twoCentLabelX.setText("x");
				this.form_twoCentLabelX.setPreferredSize(new Dimension(15, 30));
				this.form_twoCentLabelX.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_twoCentLabelX.setFont(this.form_twoCentLabelX.getFont().deriveFont(this.form_twoCentLabelX.getFont().getSize() + 3f));
				this.form_twoCentLabelX.setMaximumSize(null);
				this.form_twoCentLabelX.setMinimumSize(new Dimension(9, 30));
				this.form_twoCentLabelX.setName("twoCentLabelX");
				this.form_anzahlPanelNormal.add(this.form_twoCentLabelX, "cell 1 6,growx,gapx 5");

				//---- form_oneCentLabelAmount ----
				this.form_oneCentLabelAmount.setText("0");
				this.form_oneCentLabelAmount.setPreferredSize(new Dimension(56, 30));
				this.form_oneCentLabelAmount.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_oneCentLabelAmount.setFont(this.form_oneCentLabelAmount.getFont().deriveFont(this.form_oneCentLabelAmount.getFont().getSize() + 3f));
				this.form_oneCentLabelAmount.setMaximumSize(null);
				this.form_oneCentLabelAmount.setMinimumSize(new Dimension(9, 30));
				this.form_oneCentLabelAmount.setName("oneCentLabelAmount");
				this.form_anzahlPanelNormal.add(this.form_oneCentLabelAmount, "cell 0 7,growx");

				//---- form_oneCentLabelX ----
				this.form_oneCentLabelX.setText("x");
				this.form_oneCentLabelX.setPreferredSize(new Dimension(15, 30));
				this.form_oneCentLabelX.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_oneCentLabelX.setFont(this.form_oneCentLabelX.getFont().deriveFont(this.form_oneCentLabelX.getFont().getSize() + 3f));
				this.form_oneCentLabelX.setMaximumSize(null);
				this.form_oneCentLabelX.setMinimumSize(new Dimension(9, 30));
				this.form_oneCentLabelX.setName("oneCentLabelX");
				this.form_anzahlPanelNormal.add(this.form_oneCentLabelX, "cell 1 7,growx,gapx 5");

				//---- form_vSpacer8 ----
				this.form_vSpacer8.setName("vSpacer8");
				this.form_anzahlPanelNormal.add(this.form_vSpacer8, "cell 0 8");

				//---- form_twohundredEuroLabelAmount ----
				this.form_twohundredEuroLabelAmount.setText("0");
				this.form_twohundredEuroLabelAmount.setPreferredSize(new Dimension(56, 30));
				this.form_twohundredEuroLabelAmount.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_twohundredEuroLabelAmount.setFont(this.form_twohundredEuroLabelAmount.getFont().deriveFont(this.form_twohundredEuroLabelAmount.getFont().getSize() + 3f));
				this.form_twohundredEuroLabelAmount.setMaximumSize(null);
				this.form_twohundredEuroLabelAmount.setMinimumSize(new Dimension(9, 30));
				this.form_twohundredEuroLabelAmount.setName("twohundredEuroLabelAmount");
				this.form_anzahlPanelNormal.add(this.form_twohundredEuroLabelAmount, "cell 0 9,growx");

				//---- form_twohundredEuroLabelX ----
				this.form_twohundredEuroLabelX.setText("x");
				this.form_twohundredEuroLabelX.setPreferredSize(new Dimension(15, 30));
				this.form_twohundredEuroLabelX.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_twohundredEuroLabelX.setFont(this.form_twohundredEuroLabelX.getFont().deriveFont(this.form_twohundredEuroLabelX.getFont().getSize() + 3f));
				this.form_twohundredEuroLabelX.setMaximumSize(null);
				this.form_twohundredEuroLabelX.setMinimumSize(new Dimension(9, 30));
				this.form_twohundredEuroLabelX.setName("twohundredEuroLabelX");
				this.form_anzahlPanelNormal.add(this.form_twohundredEuroLabelX, "cell 1 9,growx,gapx 5");

				//---- form_onehundredEuroLabelAmount ----
				this.form_onehundredEuroLabelAmount.setText("0");
				this.form_onehundredEuroLabelAmount.setPreferredSize(new Dimension(56, 30));
				this.form_onehundredEuroLabelAmount.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_onehundredEuroLabelAmount.setFont(this.form_onehundredEuroLabelAmount.getFont().deriveFont(this.form_onehundredEuroLabelAmount.getFont().getSize() + 3f));
				this.form_onehundredEuroLabelAmount.setMaximumSize(null);
				this.form_onehundredEuroLabelAmount.setMinimumSize(new Dimension(9, 30));
				this.form_onehundredEuroLabelAmount.setName("onehundredEuroLabelAmount");
				this.form_anzahlPanelNormal.add(this.form_onehundredEuroLabelAmount, "cell 0 10,growx");

				//---- form_onehundredEuroLabelX ----
				this.form_onehundredEuroLabelX.setText("x");
				this.form_onehundredEuroLabelX.setPreferredSize(new Dimension(15, 30));
				this.form_onehundredEuroLabelX.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_onehundredEuroLabelX.setFont(this.form_onehundredEuroLabelX.getFont().deriveFont(this.form_onehundredEuroLabelX.getFont().getSize() + 3f));
				this.form_onehundredEuroLabelX.setMaximumSize(null);
				this.form_onehundredEuroLabelX.setMinimumSize(new Dimension(9, 30));
				this.form_onehundredEuroLabelX.setName("onehundredEuroLabelX");
				this.form_anzahlPanelNormal.add(this.form_onehundredEuroLabelX, "cell 1 10,growx,gapx 5");

				//---- form_fiftyEuroLabelAmount ----
				this.form_fiftyEuroLabelAmount.setText("0");
				this.form_fiftyEuroLabelAmount.setPreferredSize(new Dimension(56, 30));
				this.form_fiftyEuroLabelAmount.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_fiftyEuroLabelAmount.setFont(this.form_fiftyEuroLabelAmount.getFont().deriveFont(this.form_fiftyEuroLabelAmount.getFont().getSize() + 3f));
				this.form_fiftyEuroLabelAmount.setMaximumSize(null);
				this.form_fiftyEuroLabelAmount.setMinimumSize(new Dimension(9, 30));
				this.form_fiftyEuroLabelAmount.setName("fiftyEuroLabelAmount");
				this.form_anzahlPanelNormal.add(this.form_fiftyEuroLabelAmount, "cell 0 11,growx");

				//---- form_fiftyEuroLabelX ----
				this.form_fiftyEuroLabelX.setText("x");
				this.form_fiftyEuroLabelX.setPreferredSize(new Dimension(15, 30));
				this.form_fiftyEuroLabelX.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_fiftyEuroLabelX.setFont(this.form_fiftyEuroLabelX.getFont().deriveFont(this.form_fiftyEuroLabelX.getFont().getSize() + 3f));
				this.form_fiftyEuroLabelX.setMaximumSize(null);
				this.form_fiftyEuroLabelX.setMinimumSize(new Dimension(9, 30));
				this.form_fiftyEuroLabelX.setName("fiftyEuroLabelX");
				this.form_anzahlPanelNormal.add(this.form_fiftyEuroLabelX, "cell 1 11,growx,gapx 5");

				//---- form_twentyEuroLabelAmount ----
				this.form_twentyEuroLabelAmount.setText("0");
				this.form_twentyEuroLabelAmount.setPreferredSize(new Dimension(56, 30));
				this.form_twentyEuroLabelAmount.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_twentyEuroLabelAmount.setFont(this.form_twentyEuroLabelAmount.getFont().deriveFont(this.form_twentyEuroLabelAmount.getFont().getSize() + 3f));
				this.form_twentyEuroLabelAmount.setMaximumSize(null);
				this.form_twentyEuroLabelAmount.setMinimumSize(new Dimension(9, 30));
				this.form_twentyEuroLabelAmount.setName("twentyEuroLabelAmount");
				this.form_anzahlPanelNormal.add(this.form_twentyEuroLabelAmount, "cell 0 12,growx");

				//---- form_twentyEuroLabelX ----
				this.form_twentyEuroLabelX.setText("x");
				this.form_twentyEuroLabelX.setPreferredSize(new Dimension(15, 30));
				this.form_twentyEuroLabelX.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_twentyEuroLabelX.setFont(this.form_twentyEuroLabelX.getFont().deriveFont(this.form_twentyEuroLabelX.getFont().getSize() + 3f));
				this.form_twentyEuroLabelX.setMaximumSize(null);
				this.form_twentyEuroLabelX.setMinimumSize(new Dimension(9, 30));
				this.form_twentyEuroLabelX.setName("twentyEuroLabelX");
				this.form_anzahlPanelNormal.add(this.form_twentyEuroLabelX, "cell 1 12,growx,gapx 5");

				//---- form_tenEuroLabelAmount ----
				this.form_tenEuroLabelAmount.setText("0");
				this.form_tenEuroLabelAmount.setPreferredSize(new Dimension(56, 30));
				this.form_tenEuroLabelAmount.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_tenEuroLabelAmount.setFont(this.form_tenEuroLabelAmount.getFont().deriveFont(this.form_tenEuroLabelAmount.getFont().getSize() + 3f));
				this.form_tenEuroLabelAmount.setMaximumSize(null);
				this.form_tenEuroLabelAmount.setMinimumSize(new Dimension(9, 30));
				this.form_tenEuroLabelAmount.setName("tenEuroLabelAmount");
				this.form_anzahlPanelNormal.add(this.form_tenEuroLabelAmount, "cell 0 13,growx");

				//---- form_tenEuroLabelX ----
				this.form_tenEuroLabelX.setText("x");
				this.form_tenEuroLabelX.setPreferredSize(new Dimension(15, 30));
				this.form_tenEuroLabelX.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_tenEuroLabelX.setFont(this.form_tenEuroLabelX.getFont().deriveFont(this.form_tenEuroLabelX.getFont().getSize() + 3f));
				this.form_tenEuroLabelX.setMaximumSize(null);
				this.form_tenEuroLabelX.setMinimumSize(new Dimension(9, 30));
				this.form_tenEuroLabelX.setName("tenEuroLabelX");
				this.form_anzahlPanelNormal.add(this.form_tenEuroLabelX, "cell 1 13,growx,gapx 5");

				//---- form_fiveEuroLabelAmount ----
				this.form_fiveEuroLabelAmount.setText("0");
				this.form_fiveEuroLabelAmount.setPreferredSize(new Dimension(56, 30));
				this.form_fiveEuroLabelAmount.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_fiveEuroLabelAmount.setFont(this.form_fiveEuroLabelAmount.getFont().deriveFont(this.form_fiveEuroLabelAmount.getFont().getSize() + 3f));
				this.form_fiveEuroLabelAmount.setMaximumSize(null);
				this.form_fiveEuroLabelAmount.setMinimumSize(new Dimension(9, 30));
				this.form_fiveEuroLabelAmount.setName("fiveEuroLabelAmount");
				this.form_anzahlPanelNormal.add(this.form_fiveEuroLabelAmount, "cell 0 14,growx");

				//---- form_fiveEuroLabelX ----
				this.form_fiveEuroLabelX.setText("x");
				this.form_fiveEuroLabelX.setPreferredSize(new Dimension(15, 30));
				this.form_fiveEuroLabelX.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_fiveEuroLabelX.setFont(this.form_fiveEuroLabelX.getFont().deriveFont(this.form_fiveEuroLabelX.getFont().getSize() + 3f));
				this.form_fiveEuroLabelX.setMaximumSize(null);
				this.form_fiveEuroLabelX.setMinimumSize(new Dimension(9, 30));
				this.form_fiveEuroLabelX.setName("fiveEuroLabelX");
				this.form_anzahlPanelNormal.add(this.form_fiveEuroLabelX, "cell 1 14,growx,gapx 5");
			}
			this.form_middlePanel.add(this.form_anzahlPanelNormal);

			//======== form_zweilenwertPanelNormal ========
			{
				this.form_zweilenwertPanelNormal.setPreferredSize(new Dimension(100, 500));
				this.form_zweilenwertPanelNormal.setFont(this.form_zweilenwertPanelNormal.getFont().deriveFont(this.form_zweilenwertPanelNormal.getFont().getSize() + 3f));
				this.form_zweilenwertPanelNormal.setName("zweilenwertPanelNormal");
				this.form_zweilenwertPanelNormal.setLayout(new MigLayout(
					"fill",
					// columns
					"[grow]" +
					"[]",
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
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]"));

				//---- form_twoEuroLabelValue ----
				this.form_twoEuroLabelValue.setText("0");
				this.form_twoEuroLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_twoEuroLabelValue.setFont(this.form_twoEuroLabelValue.getFont().deriveFont(this.form_twoEuroLabelValue.getFont().getSize() + 3f));
				this.form_twoEuroLabelValue.setMaximumSize(null);
				this.form_twoEuroLabelValue.setMinimumSize(new Dimension(9, 30));
				this.form_twoEuroLabelValue.setPreferredSize(new Dimension(9, 30));
				this.form_twoEuroLabelValue.setName("twoEuroLabelValue");
				this.form_zweilenwertPanelNormal.add(this.form_twoEuroLabelValue, "cell 0 0,growx");

				//---- form_twoEuroLabelSymbol ----
				this.form_twoEuroLabelSymbol.setText("\u20ac");
				this.form_twoEuroLabelSymbol.setPreferredSize(new Dimension(15, 30));
				this.form_twoEuroLabelSymbol.setHorizontalAlignment(SwingConstants.LEFT);
				this.form_twoEuroLabelSymbol.setFont(this.form_twoEuroLabelSymbol.getFont().deriveFont(this.form_twoEuroLabelSymbol.getFont().getSize() + 3f));
				this.form_twoEuroLabelSymbol.setMaximumSize(null);
				this.form_twoEuroLabelSymbol.setMinimumSize(new Dimension(15, 30));
				this.form_twoEuroLabelSymbol.setName("twoEuroLabelSymbol");
				this.form_zweilenwertPanelNormal.add(this.form_twoEuroLabelSymbol, "cell 1 0,growx,gapx 5");

				//---- form_oneEuroLabelValue ----
				this.form_oneEuroLabelValue.setText("0");
				this.form_oneEuroLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_oneEuroLabelValue.setFont(this.form_oneEuroLabelValue.getFont().deriveFont(this.form_oneEuroLabelValue.getFont().getSize() + 3f));
				this.form_oneEuroLabelValue.setMaximumSize(null);
				this.form_oneEuroLabelValue.setMinimumSize(new Dimension(9, 30));
				this.form_oneEuroLabelValue.setPreferredSize(new Dimension(9, 30));
				this.form_oneEuroLabelValue.setName("oneEuroLabelValue");
				this.form_zweilenwertPanelNormal.add(this.form_oneEuroLabelValue, "cell 0 1,growx");

				//---- form_oneEuroLabelSymbol ----
				this.form_oneEuroLabelSymbol.setText("\u20ac");
				this.form_oneEuroLabelSymbol.setPreferredSize(new Dimension(15, 30));
				this.form_oneEuroLabelSymbol.setHorizontalAlignment(SwingConstants.LEFT);
				this.form_oneEuroLabelSymbol.setFont(this.form_oneEuroLabelSymbol.getFont().deriveFont(this.form_oneEuroLabelSymbol.getFont().getSize() + 3f));
				this.form_oneEuroLabelSymbol.setMaximumSize(null);
				this.form_oneEuroLabelSymbol.setMinimumSize(new Dimension(15, 30));
				this.form_oneEuroLabelSymbol.setName("oneEuroLabelSymbol");
				this.form_zweilenwertPanelNormal.add(this.form_oneEuroLabelSymbol, "cell 1 1,growx,gapx 5");

				//---- form_fiftyCentLabelValue ----
				this.form_fiftyCentLabelValue.setText("0");
				this.form_fiftyCentLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_fiftyCentLabelValue.setFont(this.form_fiftyCentLabelValue.getFont().deriveFont(this.form_fiftyCentLabelValue.getFont().getSize() + 3f));
				this.form_fiftyCentLabelValue.setMaximumSize(null);
				this.form_fiftyCentLabelValue.setMinimumSize(new Dimension(9, 30));
				this.form_fiftyCentLabelValue.setPreferredSize(new Dimension(9, 30));
				this.form_fiftyCentLabelValue.setName("fiftyCentLabelValue");
				this.form_zweilenwertPanelNormal.add(this.form_fiftyCentLabelValue, "cell 0 2,growx");

				//---- form_fiftyCentLabelSymbol ----
				this.form_fiftyCentLabelSymbol.setText("\u20ac");
				this.form_fiftyCentLabelSymbol.setPreferredSize(new Dimension(15, 30));
				this.form_fiftyCentLabelSymbol.setHorizontalAlignment(SwingConstants.LEFT);
				this.form_fiftyCentLabelSymbol.setFont(this.form_fiftyCentLabelSymbol.getFont().deriveFont(this.form_fiftyCentLabelSymbol.getFont().getSize() + 3f));
				this.form_fiftyCentLabelSymbol.setMaximumSize(null);
				this.form_fiftyCentLabelSymbol.setMinimumSize(new Dimension(15, 30));
				this.form_fiftyCentLabelSymbol.setName("fiftyCentLabelSymbol");
				this.form_zweilenwertPanelNormal.add(this.form_fiftyCentLabelSymbol, "cell 1 2,growx,gapx 5");

				//---- form_twentyCentLabelValue ----
				this.form_twentyCentLabelValue.setText("0");
				this.form_twentyCentLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_twentyCentLabelValue.setFont(this.form_twentyCentLabelValue.getFont().deriveFont(this.form_twentyCentLabelValue.getFont().getSize() + 3f));
				this.form_twentyCentLabelValue.setMaximumSize(null);
				this.form_twentyCentLabelValue.setMinimumSize(new Dimension(9, 30));
				this.form_twentyCentLabelValue.setPreferredSize(new Dimension(9, 30));
				this.form_twentyCentLabelValue.setName("twentyCentLabelValue");
				this.form_zweilenwertPanelNormal.add(this.form_twentyCentLabelValue, "cell 0 3,growx");

				//---- form_twentyCentLabelSymbol ----
				this.form_twentyCentLabelSymbol.setText("\u20ac");
				this.form_twentyCentLabelSymbol.setPreferredSize(new Dimension(15, 30));
				this.form_twentyCentLabelSymbol.setHorizontalAlignment(SwingConstants.LEFT);
				this.form_twentyCentLabelSymbol.setFont(this.form_twentyCentLabelSymbol.getFont().deriveFont(this.form_twentyCentLabelSymbol.getFont().getSize() + 3f));
				this.form_twentyCentLabelSymbol.setMaximumSize(null);
				this.form_twentyCentLabelSymbol.setMinimumSize(new Dimension(15, 30));
				this.form_twentyCentLabelSymbol.setName("twentyCentLabelSymbol");
				this.form_zweilenwertPanelNormal.add(this.form_twentyCentLabelSymbol, "cell 1 3,growx,gapx 5");

				//---- form_tenCentLabelValue ----
				this.form_tenCentLabelValue.setText("0");
				this.form_tenCentLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_tenCentLabelValue.setFont(this.form_tenCentLabelValue.getFont().deriveFont(this.form_tenCentLabelValue.getFont().getSize() + 3f));
				this.form_tenCentLabelValue.setMaximumSize(null);
				this.form_tenCentLabelValue.setMinimumSize(new Dimension(9, 30));
				this.form_tenCentLabelValue.setPreferredSize(new Dimension(9, 30));
				this.form_tenCentLabelValue.setName("tenCentLabelValue");
				this.form_zweilenwertPanelNormal.add(this.form_tenCentLabelValue, "cell 0 4,growx");

				//---- form_tenCentLabelSymbol ----
				this.form_tenCentLabelSymbol.setText("\u20ac");
				this.form_tenCentLabelSymbol.setPreferredSize(new Dimension(15, 30));
				this.form_tenCentLabelSymbol.setHorizontalAlignment(SwingConstants.LEFT);
				this.form_tenCentLabelSymbol.setFont(this.form_tenCentLabelSymbol.getFont().deriveFont(this.form_tenCentLabelSymbol.getFont().getSize() + 3f));
				this.form_tenCentLabelSymbol.setMaximumSize(null);
				this.form_tenCentLabelSymbol.setMinimumSize(new Dimension(15, 30));
				this.form_tenCentLabelSymbol.setName("tenCentLabelSymbol");
				this.form_zweilenwertPanelNormal.add(this.form_tenCentLabelSymbol, "cell 1 4,growx,gapx 5");

				//---- form_fiveCentLabelValue ----
				this.form_fiveCentLabelValue.setText("0");
				this.form_fiveCentLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_fiveCentLabelValue.setFont(this.form_fiveCentLabelValue.getFont().deriveFont(this.form_fiveCentLabelValue.getFont().getSize() + 3f));
				this.form_fiveCentLabelValue.setMaximumSize(null);
				this.form_fiveCentLabelValue.setMinimumSize(new Dimension(9, 30));
				this.form_fiveCentLabelValue.setPreferredSize(new Dimension(9, 30));
				this.form_fiveCentLabelValue.setName("fiveCentLabelValue");
				this.form_zweilenwertPanelNormal.add(this.form_fiveCentLabelValue, "cell 0 5,growx");

				//---- form_fiveCentLabelSymbol ----
				this.form_fiveCentLabelSymbol.setText("\u20ac");
				this.form_fiveCentLabelSymbol.setPreferredSize(new Dimension(15, 30));
				this.form_fiveCentLabelSymbol.setHorizontalAlignment(SwingConstants.LEFT);
				this.form_fiveCentLabelSymbol.setFont(this.form_fiveCentLabelSymbol.getFont().deriveFont(this.form_fiveCentLabelSymbol.getFont().getSize() + 3f));
				this.form_fiveCentLabelSymbol.setMaximumSize(null);
				this.form_fiveCentLabelSymbol.setMinimumSize(new Dimension(15, 30));
				this.form_fiveCentLabelSymbol.setName("fiveCentLabelSymbol");
				this.form_zweilenwertPanelNormal.add(this.form_fiveCentLabelSymbol, "cell 1 5,growx,gapx 5");

				//---- form_twoCentLabelValue ----
				this.form_twoCentLabelValue.setText("0");
				this.form_twoCentLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_twoCentLabelValue.setFont(this.form_twoCentLabelValue.getFont().deriveFont(this.form_twoCentLabelValue.getFont().getSize() + 3f));
				this.form_twoCentLabelValue.setMaximumSize(null);
				this.form_twoCentLabelValue.setMinimumSize(new Dimension(9, 30));
				this.form_twoCentLabelValue.setPreferredSize(new Dimension(9, 30));
				this.form_twoCentLabelValue.setName("twoCentLabelValue");
				this.form_zweilenwertPanelNormal.add(this.form_twoCentLabelValue, "cell 0 6,growx");

				//---- form_twoCentLabelSymbol ----
				this.form_twoCentLabelSymbol.setText("\u20ac");
				this.form_twoCentLabelSymbol.setPreferredSize(new Dimension(15, 30));
				this.form_twoCentLabelSymbol.setHorizontalAlignment(SwingConstants.LEFT);
				this.form_twoCentLabelSymbol.setFont(this.form_twoCentLabelSymbol.getFont().deriveFont(this.form_twoCentLabelSymbol.getFont().getSize() + 3f));
				this.form_twoCentLabelSymbol.setMaximumSize(null);
				this.form_twoCentLabelSymbol.setMinimumSize(new Dimension(15, 30));
				this.form_twoCentLabelSymbol.setName("twoCentLabelSymbol");
				this.form_zweilenwertPanelNormal.add(this.form_twoCentLabelSymbol, "cell 1 6,growx,gapx 5");

				//---- form_oneCentLabelValue ----
				this.form_oneCentLabelValue.setText("0");
				this.form_oneCentLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_oneCentLabelValue.setFont(this.form_oneCentLabelValue.getFont().deriveFont(this.form_oneCentLabelValue.getFont().getSize() + 3f));
				this.form_oneCentLabelValue.setMaximumSize(null);
				this.form_oneCentLabelValue.setMinimumSize(new Dimension(9, 30));
				this.form_oneCentLabelValue.setPreferredSize(new Dimension(9, 30));
				this.form_oneCentLabelValue.setName("oneCentLabelValue");
				this.form_zweilenwertPanelNormal.add(this.form_oneCentLabelValue, "cell 0 7,growx");

				//---- form_oneCentLabelSymbol ----
				this.form_oneCentLabelSymbol.setText("\u20ac");
				this.form_oneCentLabelSymbol.setPreferredSize(new Dimension(15, 30));
				this.form_oneCentLabelSymbol.setHorizontalAlignment(SwingConstants.LEFT);
				this.form_oneCentLabelSymbol.setFont(this.form_oneCentLabelSymbol.getFont().deriveFont(this.form_oneCentLabelSymbol.getFont().getSize() + 3f));
				this.form_oneCentLabelSymbol.setMaximumSize(null);
				this.form_oneCentLabelSymbol.setMinimumSize(new Dimension(15, 30));
				this.form_oneCentLabelSymbol.setName("oneCentLabelSymbol");
				this.form_zweilenwertPanelNormal.add(this.form_oneCentLabelSymbol, "cell 1 7,growx,gapx 5");

				//---- form_vSpacer9 ----
				this.form_vSpacer9.setName("vSpacer9");
				this.form_zweilenwertPanelNormal.add(this.form_vSpacer9, "cell 0 8");

				//---- form_twohundredEuroLabelValue ----
				this.form_twohundredEuroLabelValue.setText("0");
				this.form_twohundredEuroLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_twohundredEuroLabelValue.setFont(this.form_twohundredEuroLabelValue.getFont().deriveFont(this.form_twohundredEuroLabelValue.getFont().getSize() + 3f));
				this.form_twohundredEuroLabelValue.setMaximumSize(null);
				this.form_twohundredEuroLabelValue.setMinimumSize(new Dimension(9, 30));
				this.form_twohundredEuroLabelValue.setPreferredSize(new Dimension(9, 30));
				this.form_twohundredEuroLabelValue.setName("twohundredEuroLabelValue");
				this.form_zweilenwertPanelNormal.add(this.form_twohundredEuroLabelValue, "cell 0 9,growx");

				//---- form_twohundredEuroLabelSymbol ----
				this.form_twohundredEuroLabelSymbol.setText("\u20ac");
				this.form_twohundredEuroLabelSymbol.setPreferredSize(new Dimension(15, 30));
				this.form_twohundredEuroLabelSymbol.setHorizontalAlignment(SwingConstants.LEFT);
				this.form_twohundredEuroLabelSymbol.setFont(this.form_twohundredEuroLabelSymbol.getFont().deriveFont(this.form_twohundredEuroLabelSymbol.getFont().getSize() + 3f));
				this.form_twohundredEuroLabelSymbol.setMaximumSize(null);
				this.form_twohundredEuroLabelSymbol.setMinimumSize(new Dimension(15, 30));
				this.form_twohundredEuroLabelSymbol.setName("twohundredEuroLabelSymbol");
				this.form_zweilenwertPanelNormal.add(this.form_twohundredEuroLabelSymbol, "cell 1 9,growx,gapx 5");

				//---- form_onehundredEuroLabelValue ----
				this.form_onehundredEuroLabelValue.setText("0");
				this.form_onehundredEuroLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_onehundredEuroLabelValue.setFont(this.form_onehundredEuroLabelValue.getFont().deriveFont(this.form_onehundredEuroLabelValue.getFont().getSize() + 3f));
				this.form_onehundredEuroLabelValue.setMaximumSize(null);
				this.form_onehundredEuroLabelValue.setMinimumSize(new Dimension(9, 30));
				this.form_onehundredEuroLabelValue.setPreferredSize(new Dimension(9, 30));
				this.form_onehundredEuroLabelValue.setName("onehundredEuroLabelValue");
				this.form_zweilenwertPanelNormal.add(this.form_onehundredEuroLabelValue, "cell 0 10,growx");

				//---- form_onehundredEuroLabelSymbol ----
				this.form_onehundredEuroLabelSymbol.setText("\u20ac");
				this.form_onehundredEuroLabelSymbol.setPreferredSize(new Dimension(15, 30));
				this.form_onehundredEuroLabelSymbol.setHorizontalAlignment(SwingConstants.LEFT);
				this.form_onehundredEuroLabelSymbol.setFont(this.form_onehundredEuroLabelSymbol.getFont().deriveFont(this.form_onehundredEuroLabelSymbol.getFont().getSize() + 3f));
				this.form_onehundredEuroLabelSymbol.setMaximumSize(null);
				this.form_onehundredEuroLabelSymbol.setMinimumSize(new Dimension(15, 30));
				this.form_onehundredEuroLabelSymbol.setName("onehundredEuroLabelSymbol");
				this.form_zweilenwertPanelNormal.add(this.form_onehundredEuroLabelSymbol, "cell 1 10,growx,gapx 5");

				//---- form_fiftyEuroLabelValue ----
				this.form_fiftyEuroLabelValue.setText("0");
				this.form_fiftyEuroLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_fiftyEuroLabelValue.setFont(this.form_fiftyEuroLabelValue.getFont().deriveFont(this.form_fiftyEuroLabelValue.getFont().getSize() + 3f));
				this.form_fiftyEuroLabelValue.setMaximumSize(null);
				this.form_fiftyEuroLabelValue.setMinimumSize(new Dimension(9, 30));
				this.form_fiftyEuroLabelValue.setPreferredSize(new Dimension(9, 30));
				this.form_fiftyEuroLabelValue.setName("fiftyEuroLabelValue");
				this.form_zweilenwertPanelNormal.add(this.form_fiftyEuroLabelValue, "cell 0 11,growx");

				//---- form_fiftyEuroLabelSymbol ----
				this.form_fiftyEuroLabelSymbol.setText("\u20ac");
				this.form_fiftyEuroLabelSymbol.setPreferredSize(new Dimension(15, 30));
				this.form_fiftyEuroLabelSymbol.setHorizontalAlignment(SwingConstants.LEFT);
				this.form_fiftyEuroLabelSymbol.setFont(this.form_fiftyEuroLabelSymbol.getFont().deriveFont(this.form_fiftyEuroLabelSymbol.getFont().getSize() + 3f));
				this.form_fiftyEuroLabelSymbol.setMaximumSize(null);
				this.form_fiftyEuroLabelSymbol.setMinimumSize(new Dimension(15, 30));
				this.form_fiftyEuroLabelSymbol.setName("fiftyEuroLabelSymbol");
				this.form_zweilenwertPanelNormal.add(this.form_fiftyEuroLabelSymbol, "cell 1 11,growx,gapx 5");

				//---- form_twentyEuroLabelValue ----
				this.form_twentyEuroLabelValue.setText("0");
				this.form_twentyEuroLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_twentyEuroLabelValue.setFont(this.form_twentyEuroLabelValue.getFont().deriveFont(this.form_twentyEuroLabelValue.getFont().getSize() + 3f));
				this.form_twentyEuroLabelValue.setMaximumSize(null);
				this.form_twentyEuroLabelValue.setMinimumSize(new Dimension(9, 30));
				this.form_twentyEuroLabelValue.setPreferredSize(new Dimension(9, 30));
				this.form_twentyEuroLabelValue.setName("twentyEuroLabelValue");
				this.form_zweilenwertPanelNormal.add(this.form_twentyEuroLabelValue, "cell 0 12,growx");

				//---- form_twentyEuroLabelSymbol ----
				this.form_twentyEuroLabelSymbol.setText("\u20ac");
				this.form_twentyEuroLabelSymbol.setPreferredSize(new Dimension(15, 30));
				this.form_twentyEuroLabelSymbol.setHorizontalAlignment(SwingConstants.LEFT);
				this.form_twentyEuroLabelSymbol.setFont(this.form_twentyEuroLabelSymbol.getFont().deriveFont(this.form_twentyEuroLabelSymbol.getFont().getSize() + 3f));
				this.form_twentyEuroLabelSymbol.setMaximumSize(null);
				this.form_twentyEuroLabelSymbol.setMinimumSize(new Dimension(15, 30));
				this.form_twentyEuroLabelSymbol.setName("twentyEuroLabelSymbol");
				this.form_zweilenwertPanelNormal.add(this.form_twentyEuroLabelSymbol, "cell 1 12,growx,gapx 5");

				//---- form_tenEuroLabelValue ----
				this.form_tenEuroLabelValue.setText("0");
				this.form_tenEuroLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_tenEuroLabelValue.setFont(this.form_tenEuroLabelValue.getFont().deriveFont(this.form_tenEuroLabelValue.getFont().getSize() + 3f));
				this.form_tenEuroLabelValue.setMaximumSize(null);
				this.form_tenEuroLabelValue.setMinimumSize(new Dimension(9, 30));
				this.form_tenEuroLabelValue.setPreferredSize(new Dimension(9, 30));
				this.form_tenEuroLabelValue.setName("tenEuroLabelValue");
				this.form_zweilenwertPanelNormal.add(this.form_tenEuroLabelValue, "cell 0 13,growx");

				//---- form_tenEuroLabelSymbol ----
				this.form_tenEuroLabelSymbol.setText("\u20ac");
				this.form_tenEuroLabelSymbol.setPreferredSize(new Dimension(15, 30));
				this.form_tenEuroLabelSymbol.setHorizontalAlignment(SwingConstants.LEFT);
				this.form_tenEuroLabelSymbol.setFont(this.form_tenEuroLabelSymbol.getFont().deriveFont(this.form_tenEuroLabelSymbol.getFont().getSize() + 3f));
				this.form_tenEuroLabelSymbol.setMaximumSize(null);
				this.form_tenEuroLabelSymbol.setMinimumSize(new Dimension(15, 30));
				this.form_tenEuroLabelSymbol.setName("tenEuroLabelSymbol");
				this.form_zweilenwertPanelNormal.add(this.form_tenEuroLabelSymbol, "cell 1 13,growx,gapx 5");

				//---- form_fiveEuroLabelValue ----
				this.form_fiveEuroLabelValue.setText("0");
				this.form_fiveEuroLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_fiveEuroLabelValue.setFont(this.form_fiveEuroLabelValue.getFont().deriveFont(this.form_fiveEuroLabelValue.getFont().getSize() + 3f));
				this.form_fiveEuroLabelValue.setMaximumSize(null);
				this.form_fiveEuroLabelValue.setMinimumSize(new Dimension(9, 30));
				this.form_fiveEuroLabelValue.setPreferredSize(new Dimension(9, 30));
				this.form_fiveEuroLabelValue.setName("fiveEuroLabelValue");
				this.form_zweilenwertPanelNormal.add(this.form_fiveEuroLabelValue, "cell 0 14,growx");

				//---- form_fiveEuroLabelSymbol ----
				this.form_fiveEuroLabelSymbol.setText("\u20ac");
				this.form_fiveEuroLabelSymbol.setPreferredSize(new Dimension(15, 30));
				this.form_fiveEuroLabelSymbol.setHorizontalAlignment(SwingConstants.LEFT);
				this.form_fiveEuroLabelSymbol.setFont(this.form_fiveEuroLabelSymbol.getFont().deriveFont(this.form_fiveEuroLabelSymbol.getFont().getSize() + 3f));
				this.form_fiveEuroLabelSymbol.setMaximumSize(null);
				this.form_fiveEuroLabelSymbol.setMinimumSize(new Dimension(15, 30));
				this.form_fiveEuroLabelSymbol.setName("fiveEuroLabelSymbol");
				this.form_zweilenwertPanelNormal.add(this.form_fiveEuroLabelSymbol, "cell 1 14,growx,gapx 5");
			}
			this.form_middlePanel.add(this.form_zweilenwertPanelNormal);
		}
		contentPane.add(this.form_middlePanel, BorderLayout.CENTER);

		//======== form_bottomPanel ========
		{
			this.form_bottomPanel.setName("bottomPanel");
			this.form_bottomPanel.setLayout(new GridLayout());

			//======== form_panel4 ========
			{
				this.form_panel4.setFocusable(false);
				this.form_panel4.setEnabled(false);
				this.form_panel4.setName("panel4");
				this.form_panel4.setLayout(new GridLayout(2, 1));

				//---- form_intermediateValueOverviewCoins ----
				this.form_intermediateValueOverviewCoins.setText("0,00 \u20ac");
				this.form_intermediateValueOverviewCoins.setBorder(new TitledBorder("coins"));
				this.form_intermediateValueOverviewCoins.setHorizontalTextPosition(SwingConstants.RIGHT);
				this.form_intermediateValueOverviewCoins.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_intermediateValueOverviewCoins.setFocusable(false);
				this.form_intermediateValueOverviewCoins.setFont(this.form_intermediateValueOverviewCoins.getFont().deriveFont(this.form_intermediateValueOverviewCoins.getFont().getSize() + 3f));
				this.form_intermediateValueOverviewCoins.setEnabled(false);
				this.form_intermediateValueOverviewCoins.setVerifyInputWhenFocusTarget(false);
				this.form_intermediateValueOverviewCoins.setRequestFocusEnabled(false);
				this.form_intermediateValueOverviewCoins.setInheritsPopupMenu(false);
				this.form_intermediateValueOverviewCoins.setName("intermediateValueOverviewCoins");
				this.form_panel4.add(this.form_intermediateValueOverviewCoins);

				//---- form_intermedateValueOverviewNotes ----
				this.form_intermedateValueOverviewNotes.setText("0,00 \u20ac");
				this.form_intermedateValueOverviewNotes.setBorder(new TitledBorder("notes"));
				this.form_intermedateValueOverviewNotes.setHorizontalTextPosition(SwingConstants.RIGHT);
				this.form_intermedateValueOverviewNotes.setHorizontalAlignment(SwingConstants.RIGHT);
				this.form_intermedateValueOverviewNotes.setFocusable(false);
				this.form_intermedateValueOverviewNotes.setFont(this.form_intermedateValueOverviewNotes.getFont().deriveFont(this.form_intermedateValueOverviewNotes.getFont().getSize() + 3f));
				this.form_intermedateValueOverviewNotes.setEnabled(false);
				this.form_intermedateValueOverviewNotes.setVerifyInputWhenFocusTarget(false);
				this.form_intermedateValueOverviewNotes.setRequestFocusEnabled(false);
				this.form_intermedateValueOverviewNotes.setInheritsPopupMenu(false);
				this.form_intermedateValueOverviewNotes.setName("intermedateValueOverviewNotes");
				this.form_panel4.add(this.form_intermedateValueOverviewNotes);
			}
			this.form_bottomPanel.add(this.form_panel4);

			//======== form_panel6 ========
			{
				this.form_panel6.setFocusable(false);
				this.form_panel6.setEnabled(false);
				this.form_panel6.setName("panel6");
				this.form_panel6.setLayout(new GridLayout(1, 1));

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
				this.form_totalValueLabel.setName("totalValueLabel");
				this.form_panel6.add(this.form_totalValueLabel);
			}
			this.form_bottomPanel.add(this.form_panel6);

			//======== form_panel5 ========
			{
				this.form_panel5.setEnabled(false);
				this.form_panel5.setFocusable(false);
				this.form_panel5.setName("panel5");
				this.form_panel5.setLayout(new GridLayout(1, 1));

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
				this.form_differenceValueLabel.setName("differenceValueLabel");
				this.form_panel5.add(this.form_differenceValueLabel);
			}
			this.form_bottomPanel.add(this.form_panel5);
		}
		contentPane.add(this.form_bottomPanel, BorderLayout.SOUTH);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner non-commercial license
	protected JMenuBar form_menuBar1;
	protected JMenu form_menu1;
	protected JMenuItem form_settingsMenuItem;
	protected JMenuItem form_exitMenuItem;
	protected JPanel form_topPanel;
	protected JPanel form_buttonPanel;
	protected JButton form_resetBtn;
	protected JSeparator form_separator1;
	protected JButton form_registerBtn;
	protected JButton form_pdfBtn;
	protected JButton form_calculateBtn;
	protected JSeparator form_separator2;
	protected JButton form_debitBtn;
	protected JSeparator form_separator3;
	protected JButton form_dateBtn;
	protected JPanel form_datePanel;
	protected JSpinner form_dateSpinner;
	protected JPanel form_debitPanel;
	protected JTextField form_debitTextField;
	protected JLabel form_label69;
	protected JPanel form_middlePanel;
	protected JPanel form_wertPanel;
	protected JLabel form_twoEuroLabel;
	protected JLabel form_oneEuroLabel;
	protected JLabel form_fiftyCentLabel;
	protected JLabel form_twentyCentLabel;
	protected JLabel form_tenCentLabel;
	protected JLabel form_fiveCentLabel;
	protected JLabel form_twoCentLabel;
	protected JLabel form_oneCentLabel;
	protected JPanel form_vSpacer4;
	protected JLabel form_twohundredEuroLabel;
	protected JLabel form_onehundredEuroLabel;
	protected JLabel form_fiftyEuroLabel;
	protected JLabel form_twentyEuroLabel;
	protected JLabel form_tenEuroLabel;
	protected JLabel form_fiveEuroLabel;
	protected JPanel form_ersteSpinnerNormal;
	protected JSpinner form_spinner2_1;
	protected JSpinner form_spinner1_1;
	protected JSpinner form_spinner050_1;
	protected JSpinner form_spinner020_1;
	protected JSpinner form_spinner010_1;
	protected JSpinner form_spinner005_1;
	protected JSpinner form_spinner002_1;
	protected JSpinner form_spinner001_1;
	protected JPanel form_vSpacer3;
	protected JSpinner form_spinner200_1;
	protected JSpinner form_spinner100_1;
	protected JSpinner form_spinner50_1;
	protected JSpinner form_spinner20_1;
	protected JSpinner form_spinner10_1;
	protected JSpinner form_spinner5_1;
	protected JPanel form_zweiteSpinnerNormal;
	protected JSpinner form_spinner2_2;
	protected JSpinner form_spinner1_2;
	protected JSpinner form_spinner050_2;
	protected JSpinner form_spinner020_2;
	protected JSpinner form_spinner010_2;
	protected JSpinner form_spinner005_2;
	protected JSpinner form_spinner002_2;
	protected JSpinner form_spinner001_2;
	protected JPanel form_vSpacer2;
	protected JSpinner form_spinner200_2;
	protected JSpinner form_spinner100_2;
	protected JSpinner form_spinner50_2;
	protected JSpinner form_spinner20_2;
	protected JSpinner form_spinner10_2;
	protected JSpinner form_spinner5_2;
	protected JPanel form_dritteSpinnerNormal;
	protected JSpinner form_spinner2_3;
	protected JSpinner form_spinner1_3;
	protected JSpinner form_spinner050_3;
	protected JSpinner form_spinner020_3;
	protected JSpinner form_spinner010_3;
	protected JSpinner form_spinner005_3;
	protected JSpinner form_spinner002_3;
	protected JSpinner form_spinner001_3;
	protected JPanel form_vSpacer5;
	protected JSpinner form_spinner200_3;
	protected JSpinner form_spinner100_3;
	protected JSpinner form_spinner50_3;
	protected JSpinner form_spinner20_3;
	protected JSpinner form_spinner10_3;
	protected JSpinner form_spinner5_3;
	protected JPanel form_vierteSpinnerNormal;
	protected JSpinner form_spinner2_4;
	protected JSpinner form_spinner1_4;
	protected JSpinner form_spinner050_4;
	protected JSpinner form_spinner020_4;
	protected JSpinner form_spinner010_4;
	protected JSpinner form_spinner005_4;
	protected JSpinner form_spinner002_4;
	protected JSpinner form_spinner001_4;
	protected JPanel form_vSpacer6;
	protected JSpinner form_spinner200_4;
	protected JSpinner form_spinner100_4;
	protected JSpinner form_spinner50_4;
	protected JSpinner form_spinner20_4;
	protected JSpinner form_spinner10_4;
	protected JSpinner form_spinner5_4;
	protected JPanel form_fuenfteSpinnerNormal;
	protected JSpinner form_spinner2_5;
	protected JSpinner form_spinner1_5;
	protected JSpinner form_spinner050_5;
	protected JSpinner form_spinner020_5;
	protected JSpinner form_spinner010_5;
	protected JSpinner form_spinner005_5;
	protected JSpinner form_spinner002_5;
	protected JSpinner form_spinner001_5;
	protected JPanel form_vSpacer7;
	protected JSpinner form_spinner200_5;
	protected JSpinner form_spinner100_5;
	protected JSpinner form_spinner50_5;
	protected JSpinner form_spinner20_5;
	protected JSpinner form_spinner10_5;
	protected JSpinner form_spinner5_5;
	protected JPanel form_anzahlPanelNormal;
	protected JLabel form_twoEuroLabelAmount;
	protected JLabel form_twoEuroLabelX;
	protected JLabel form_oneEuroLabelAmount;
	protected JLabel form_oneEuroLabelX;
	protected JLabel form_fiftyCentLabelAmount;
	protected JLabel form_fiftyCentLabelX;
	protected JLabel form_twentyCentLabelAmount;
	protected JLabel form_twentyCentLabelX;
	protected JLabel form_tenCentLabelAmount;
	protected JLabel form_tenCentLabelX;
	protected JLabel form_fiveCentLabelAmount;
	protected JLabel form_fiveCentLabelX;
	protected JLabel form_twoCentLabelAmount;
	protected JLabel form_twoCentLabelX;
	protected JLabel form_oneCentLabelAmount;
	protected JLabel form_oneCentLabelX;
	protected JPanel form_vSpacer8;
	protected JLabel form_twohundredEuroLabelAmount;
	protected JLabel form_twohundredEuroLabelX;
	protected JLabel form_onehundredEuroLabelAmount;
	protected JLabel form_onehundredEuroLabelX;
	protected JLabel form_fiftyEuroLabelAmount;
	protected JLabel form_fiftyEuroLabelX;
	protected JLabel form_twentyEuroLabelAmount;
	protected JLabel form_twentyEuroLabelX;
	protected JLabel form_tenEuroLabelAmount;
	protected JLabel form_tenEuroLabelX;
	protected JLabel form_fiveEuroLabelAmount;
	protected JLabel form_fiveEuroLabelX;
	protected JPanel form_zweilenwertPanelNormal;
	protected JLabel form_twoEuroLabelValue;
	protected JLabel form_twoEuroLabelSymbol;
	protected JLabel form_oneEuroLabelValue;
	protected JLabel form_oneEuroLabelSymbol;
	protected JLabel form_fiftyCentLabelValue;
	protected JLabel form_fiftyCentLabelSymbol;
	protected JLabel form_twentyCentLabelValue;
	protected JLabel form_twentyCentLabelSymbol;
	protected JLabel form_tenCentLabelValue;
	protected JLabel form_tenCentLabelSymbol;
	protected JLabel form_fiveCentLabelValue;
	protected JLabel form_fiveCentLabelSymbol;
	protected JLabel form_twoCentLabelValue;
	protected JLabel form_twoCentLabelSymbol;
	protected JLabel form_oneCentLabelValue;
	protected JLabel form_oneCentLabelSymbol;
	protected JPanel form_vSpacer9;
	protected JLabel form_twohundredEuroLabelValue;
	protected JLabel form_twohundredEuroLabelSymbol;
	protected JLabel form_onehundredEuroLabelValue;
	protected JLabel form_onehundredEuroLabelSymbol;
	protected JLabel form_fiftyEuroLabelValue;
	protected JLabel form_fiftyEuroLabelSymbol;
	protected JLabel form_twentyEuroLabelValue;
	protected JLabel form_twentyEuroLabelSymbol;
	protected JLabel form_tenEuroLabelValue;
	protected JLabel form_tenEuroLabelSymbol;
	protected JLabel form_fiveEuroLabelValue;
	protected JLabel form_fiveEuroLabelSymbol;
	protected JPanel form_bottomPanel;
	protected JPanel form_panel4;
	protected JLabel form_intermediateValueOverviewCoins;
	protected JLabel form_intermedateValueOverviewNotes;
	protected JPanel form_panel6;
	protected JLabel form_totalValueLabel;
	protected JPanel form_panel5;
	protected JLabel form_differenceValueLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
