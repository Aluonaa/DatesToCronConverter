package com.strelets.alyona;

import com.strelets.alyona.DatesToCron;
import com.strelets.alyona.DatesToCronConvertException;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public Main() {
    }
    public static void main(String[] args) throws DatesToCronConvertException {
        DatesToCron datesToCron = new DatesToCron();
        List<String> dates = new ArrayList();
        dates.add("2022-01-24T19:53:00");
        dates.add("2022-01-24T20:02:30");
        dates.add("2022-01-24T19:54:30");
        dates.add("2022-01-24T19:55:30");
        dates.add("2022-01-24T19:56:30");
        dates.add("2022-01-24T19:58:30");
        dates.add("2022-01-24T19:57:30");
        dates.add("2022-01-24T19:59:00");
        dates.add("2022-01-24T20:00:00");
        dates.add("2022-01-24T20:01:00");
        datesToCron.convert(dates);
        System.out.println(datesToCron.getImplementationInfo());
    }
}