package com.strelets.alyona;

public class DateUtil {

    public DateUtil() {
    }

    // Метод для проверки совпадения cron и списка дат
    public static boolean checkDates(String cron, String dateToCheck) {
        if (cron.equals("*")) {
            return true;
        } else {
            if (cron.equals(dateToCheck)) {
                return true;
            } else {
                return false;
            }
        }
    }
}
