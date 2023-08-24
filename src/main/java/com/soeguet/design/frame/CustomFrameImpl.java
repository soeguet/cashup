package com.soeguet.design.frame;

import com.soeguet.design.register.daily.DailyCash;
import com.soeguet.design.register.daily.DailyCashImpl;
import com.soeguet.design.register.weekly.WeeklyCashUpImpl;

import java.awt.event.ActionEvent;

public class CustomFrameImpl extends CustomFrame {

    public CustomFrameImpl() {

        super();
    }

    @Override
    protected void menuItemDailyModule(ActionEvent e) {

        DailyCash dailyModule = new DailyCashImpl();
        dailyModule.setBounds(0, 0, dailyModule.getPreferredSize().width, dailyModule.getPreferredSize().height);
        resizeFrameIfNotBigEnough((int) dailyModule.getSize().getWidth(), (int) dailyModule.getSize().getHeight());
        form_desktopPane.add(dailyModule);
    }

    @Override
    protected void menuItemWeeklyModule(ActionEvent e) {

        WeeklyCashUpImpl weeklyModule = new WeeklyCashUpImpl();
        weeklyModule.setBounds(0, 0, weeklyModule.getPreferredSize().width + 100, weeklyModule.getPreferredSize().height);
        resizeFrameIfNotBigEnough((int) weeklyModule.getSize().getWidth(), (int) weeklyModule.getSize().getHeight());
        form_desktopPane.add(weeklyModule);
    }

    @Override
    protected void quitMenuItem(ActionEvent e) {

        System.exit(0);
    }

    private void resizeFrameIfNotBigEnough(int width, int height) {

        while (width > this.getWidth()) {

            this.setSize(this.getWidth() + 15, this.getHeight());
        }

        int titleBarHeight = this.getHeight() - this.getContentPane().getHeight();

        while (height > this.getHeight() - titleBarHeight) {

            this.setSize(this.getWidth(), this.getHeight() + 15);
        }
    }

}
