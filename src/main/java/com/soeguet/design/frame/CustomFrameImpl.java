package com.soeguet.design.frame;

import com.soeguet.design.register.daily.DailyCash;
import com.soeguet.design.register.daily.DailyCashImpl;
import com.soeguet.design.register.weekly.WeeklyCashUpImpl;
import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;

public class CustomFrameImpl extends CustomFrame {

  public CustomFrameImpl() {
    super();
  }

  @Override
  protected void menuItemDailyModule(ActionEvent e) {
    DailyCash dailyModule = new DailyCashImpl();
    dailyModule.setBounds(
        0, 0, dailyModule.getPreferredSize().width, dailyModule.getPreferredSize().height);
    resizeFrameIfNotBigEnough(
        (int) dailyModule.getPreferredSize().getWidth(),
        (int) dailyModule.getPreferredSize().getHeight());
    form_desktopPane.add(dailyModule);
  }

  @Override
  protected void menuItemWeeklyModule(ActionEvent e) {
    WeeklyCashUpImpl weeklyModule = new WeeklyCashUpImpl();
    weeklyModule.setBounds(
        0, 0, weeklyModule.getPreferredSize().width+100, weeklyModule.getPreferredSize().height);
    resizeFrameIfNotBigEnough(
        (int) weeklyModule.getPreferredSize().getWidth(),
        (int) weeklyModule.getPreferredSize().getHeight());
    form_desktopPane.add(weeklyModule);
  }

  @Override
  protected void quitMenuItem(ActionEvent e) {
    System.exit(0);
  }

  private void resizeFrameIfNotBigEnough(int width, int height) {
    if (width > this.getWidth()) {
      SwingUtilities.invokeLater(
          () -> {
            this.setSize((int) (width * 1.1), this.getHeight());
            Thread.yield();
            resizeFrameIfNotBigEnough(width, height);
            setLocationRelativeTo(this);
          });
    }

    if (height > this.getHeight()) {
      SwingUtilities.invokeLater(
          () -> {
            this.setSize(this.getWidth(), (int) (height * 1.1));
            resizeFrameIfNotBigEnough(width, height);
            setLocationRelativeTo(this);
          });
    }
  }
}
