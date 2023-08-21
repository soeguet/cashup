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
        resizeFrameIfNotBigEnough((int) dailyModule.getPreferredSize().getWidth(), (int) dailyModule.getPreferredSize().getHeight());
        form_desktopPane.add(dailyModule);
    }

    @Override
    protected void menuItemWeeklyModule(ActionEvent e) {

        WeeklyCashUpImpl weeklyModule = new WeeklyCashUpImpl();
        weeklyModule.setBounds(0, 0, weeklyModule.getPreferredSize().width + 100, weeklyModule.getPreferredSize().height);
        resizeFrameIfNotBigEnough((int) weeklyModule.getPreferredSize().getWidth(), (int) weeklyModule.getPreferredSize().getHeight());
        form_desktopPane.add(weeklyModule);
    }

    @Override
    protected void quitMenuItem(ActionEvent e) {

        System.exit(0);
    }

    private void resizeFrameIfNotBigEnough(int width, int height) {

        if (width > this.getWidth()) {

            this.setSize((int) (this.getWidth() * 1.05), this.getHeight());
            resizeFrameIfNotBigEnough(width, height);

        } else if (height > this.getHeight()) {

            this.setSize(this.getWidth(), (int) (this.getHeight() * 1.1));
            resizeFrameIfNotBigEnough(width, height);

        }
    }
}
