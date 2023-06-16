/*
 * Created by JFormDesigner on Fri Nov 18 23:04:27 CET 2022
 */

package com.soeguet.design.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

public abstract class CustomFrame extends JFrame {

  public CustomFrame() {
    initComponents();
  }

  protected abstract void menuItemDailyModule(ActionEvent e);

  protected abstract void menuItemWeeklyModule(ActionEvent e);

  protected abstract void quitMenuItem(ActionEvent e);

  private void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY
    // //GEN-BEGIN:initComponents
    // @formatter:off
    // Generated using JFormDesigner non-commercial license
    form_menuBar1 = new JMenuBar();
    form_menu1 = new JMenu();
    form_menuItem1 = new JMenuItem();
    form_menuItem2 = new JMenuItem();
    form_menuItem4 = new JMenuItem();
    form_desktopPane = new JDesktopPane();

    // ======== this ========
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setTitle("moduls");
    setMinimumSize(new Dimension(800, 600));
    setPreferredSize(null);
    Container contentPane = getContentPane();
    contentPane.setLayout(new BorderLayout());

    // ======== form_menuBar1 ========
    {
      form_menuBar1.setMinimumSize(new Dimension(75, 20));
      form_menuBar1.setBackground(new Color(0xf2f2f2));

      // ======== form_menu1 ========
      {
        form_menu1.setText("file");

        // ---- form_menuItem1 ----
        form_menuItem1.setText("daily cash");
        form_menuItem1.addActionListener(e -> menuItemDailyModule(e));
        form_menu1.add(form_menuItem1);

        // ---- form_menuItem2 ----
        form_menuItem2.setText("weekly cash up");
        form_menuItem2.addActionListener(e -> menuItemWeeklyModule(e));
        form_menu1.add(form_menuItem2);
        form_menu1.addSeparator();

        // ---- form_menuItem4 ----
        form_menuItem4.setText("quit");
        form_menuItem4.addActionListener(e -> quitMenuItem(e));
        form_menu1.add(form_menuItem4);
      }
      form_menuBar1.add(form_menu1);
    }
    setJMenuBar(form_menuBar1);

    // ======== form_desktopPane ========
    {
      form_desktopPane.setMinimumSize(new Dimension(800, 600));
      form_desktopPane.setBackground(new Color(0xf2f2f2));
      form_desktopPane.setFocusable(false);
      form_desktopPane.setFocusCycleRoot(false);
      form_desktopPane.setName("Desktop");
      form_desktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
    }
    contentPane.add(form_desktopPane, BorderLayout.CENTER);
    pack();
    setLocationRelativeTo(getOwner());
    // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
  }

  // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
  // Generated using JFormDesigner non-commercial license
  protected JMenuBar form_menuBar1;
  protected JMenu form_menu1;
  protected JMenuItem form_menuItem1;
  protected JMenuItem form_menuItem2;
  protected JMenuItem form_menuItem4;
  protected JDesktopPane form_desktopPane;
  // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
