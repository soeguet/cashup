/*
 * Created by JFormDesigner on Sun Mar 12 21:29:06 CET 2023
 */

package com.soeguet.design.register.weekly.properties;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

/**
 * @author Workstation
 */
public abstract class WeeklyPropertiesFrame extends JDialog {
	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner non-commercial license
	protected JPanel form_buttonBar;
	protected JButton form_okButton;
	protected JButton form_cancelButton;
	protected JPanel form_dialogPane;
	protected JPanel form_contentPanel;
	protected JSeparator form_separator7;
	protected JLabel form_label10;
	protected JSeparator form_separator1;
	protected JFormattedTextField form_pdfPathTextField;
	protected JSeparator form_separator6;
	protected JLabel form_label9;
	protected JFormattedTextField form_debitValueTextField;
	protected JSeparator form_separator4;
	protected JLabel form_label8;
	protected JFormattedTextField form_registerPathTextField;
	protected JSeparator form_separator5;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on


	protected abstract void registerPathTextFieldMouseClicked(MouseEvent e);

	protected abstract void pdfPathTextFieldMouseClicked(MouseEvent e);

	protected abstract void debitValueTextFieldMouseClicked(MouseEvent e);


	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner non-commercial license
		form_buttonBar = new JPanel();
		form_okButton = new JButton();
		form_cancelButton = new JButton();
		form_dialogPane = new JPanel();
		form_contentPanel = new JPanel();
		form_separator7 = new JSeparator();
		form_label10 = new JLabel();
		form_separator1 = new JSeparator();
		form_pdfPathTextField = new JFormattedTextField();
		form_separator6 = new JSeparator();
		form_label9 = new JLabel();
		form_debitValueTextField = new JFormattedTextField();
		form_separator4 = new JSeparator();
		form_label8 = new JLabel();
		form_registerPathTextField = new JFormattedTextField();
		form_separator5 = new JSeparator();

		//======== this ========
		setTitle("settings");
		setVisible(true);
		Container contentPane = getContentPane();
		contentPane.setLayout(new MigLayout(
			"insets 0,hidemode 3,gap 0 0",
			// columns
			"[grow,fill]",
			// rows
			"[grow,fill]" +
			"[fill]" +
			"[]"));

		//======== form_buttonBar ========
		{
			form_buttonBar.setLayout(new MigLayout(
				"insets dialog,alignx right",
				// columns
				"[button,fill]" +
				"[button,fill]",
				// rows
				null));

			//---- form_okButton ----
			form_okButton.setText("OK");
			form_okButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					okButtonMouseClicked(e);
				}
			});
			form_buttonBar.add(form_okButton, "cell 0 0");

			//---- form_cancelButton ----
			form_cancelButton.setText("Cancel");
			form_cancelButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					cancelButtonMouseClicked(e);
				}
			});
			form_buttonBar.add(form_cancelButton, "cell 1 0");
		}
		contentPane.add(form_buttonBar, "cell 0 2");

		//======== form_dialogPane ========
		{
			form_dialogPane.setLayout(new BorderLayout());

			//======== form_contentPanel ========
			{
				form_contentPanel.setLayout(new MigLayout(
					"fill,insets dialog,align center center",
					// columns
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[shrink 0,center]" +
					"[grow,shrink 0,center]" +
					"[shrink 0,center]" +
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
					"[]"));

				//---- form_separator7 ----
				form_separator7.setMinimumSize(new Dimension(1, 2));
				form_contentPanel.add(form_separator7, "cell 1 1 5 1");

				//---- form_label10 ----
				form_label10.setText("pdf save path");
				form_contentPanel.add(form_label10, "cell 1 2,align center center,grow 0 0");

				//---- form_separator1 ----
				form_separator1.setOrientation(SwingConstants.VERTICAL);
				form_contentPanel.add(form_separator1, "cell 2 1 1 7,grow");

				//---- form_pdfPathTextField ----
				form_pdfPathTextField.setEditable(false);
				form_pdfPathTextField.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						pdfPathTextFieldMouseClicked(e);
					}
				});
				form_contentPanel.add(form_pdfPathTextField, "cell 3 2 3 1,aligny center,grow 100 0");

				//---- form_separator6 ----
				form_separator6.setMinimumSize(new Dimension(1, 2));
				form_contentPanel.add(form_separator6, "cell 1 3 5 1");

				//---- form_label9 ----
				form_label9.setText("debit-grabber file path");
				form_contentPanel.add(form_label9, "cell 1 4,align center center,grow 0 0");

				//---- form_debitValueTextField ----
				form_debitValueTextField.setEditable(false);
				form_debitValueTextField.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						debitValueTextFieldMouseClicked(e);
					}
				});
				form_contentPanel.add(form_debitValueTextField, "cell 3 4 3 1,aligny center,grow 100 0");

				//---- form_separator4 ----
				form_separator4.setMinimumSize(new Dimension(1, 2));
				form_contentPanel.add(form_separator4, "cell 1 5 5 1");

				//---- form_label8 ----
				form_label8.setText("register-entry file path");
				form_contentPanel.add(form_label8, "cell 1 6,align center center,grow 0 0");

				//---- form_registerPathTextField ----
				form_registerPathTextField.setEditable(false);
				form_registerPathTextField.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						registerPathTextFieldMouseClicked(e);
					}
				});
				form_contentPanel.add(form_registerPathTextField, "cell 3 6 3 1,aligny center,grow 100 0");

				//---- form_separator5 ----
				form_separator5.setMinimumSize(new Dimension(1, 2));
				form_contentPanel.add(form_separator5, "cell 1 7 5 1");
			}
			form_dialogPane.add(form_contentPanel, BorderLayout.CENTER);
		}
		contentPane.add(form_dialogPane, "cell 0 0");
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

    public WeeklyPropertiesFrame() {
        super();
        initComponents();
    }

    protected abstract void cancelButtonMouseClicked(MouseEvent e);

    protected abstract void okButtonMouseClicked(MouseEvent e);
}
