package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.model.PeriodActivity;

public class HtmlUtil {
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String formatDates(PeriodActivity periodActivity) {
        return DateUtil.format(periodActivity.getDateBegin()) + " - " + DateUtil.format(periodActivity.getDateEnd());
    }
}
