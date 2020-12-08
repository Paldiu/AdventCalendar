package com.heldenmc.calendar;

import com.heldenmc.utils.ProjectBase;
import com.heldenmc.utils.Utilities;

public class GUI extends AbstractGUI {
    public GUI() {
        super(54, Utilities.colorize(ProjectBase.getInstance().getPlugin().config.getString("calendar-name")));
    }
}
