package com.strelets.alyona;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class DatesToCron implements DatesToCronConverter {
    List<String> secondsList = new ArrayList();
    List<String> minuteList = new ArrayList();
    List<String> hourList = new ArrayList();
    List<String> dayOfMonthList = new ArrayList();
    List<String> monthList = new ArrayList();
    List<String> dayOfWeek = new ArrayList();

    public DatesToCron() {
    }

    @Override
    public String convert(List<String> dates) throws DatesToCronConvertException {
        //Сортировка списка
        List<String> sortedDates = dates.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DatesToCronConverter.DATE_FORMAT);
        Map<String, Integer> freqMap = null;

        String cronSec = "",
                cronMin = "",
                cronHour = "",
                cronDayOfMonth = "",
                cronMonth = "",
                cronDayOfWeek = "";

        int trueDates = 0;

        // Разделение дат на части
        for (String s : sortedDates) {
            LocalDateTime localDateTime = LocalDateTime.parse(s, formatter);
            secondsList.add(String.valueOf(localDateTime.getSecond()));
            minuteList.add(String.valueOf(localDateTime.getMinute()));
            hourList.add(String.valueOf(localDateTime.getHour()));
            dayOfMonthList.add(String.valueOf(localDateTime.getDayOfMonth()));
            monthList.add(String.valueOf(localDateTime.getMonth()));
            dayOfWeek.add(String.valueOf(localDateTime.getDayOfWeek()));
        }
        freqMap = secondsList.stream().sorted().collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() * 100 / secondsList.size() >= 50) {
                cronSec = entry.getKey();
                break;
            } else {
                cronSec = "*";
            }

        }
        freqMap = minuteList.stream().sorted().collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() * 100 / minuteList.size() >= 50) {
                cronMin = entry.getKey();
                break;
            } else {
                cronMin = "*";
            }
        }
        freqMap = hourList.stream().sorted().collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() * 100 / hourList.size() >= 50) {
                cronHour = entry.getKey();
                break;
            } else {
                cronHour = "*";
            }
        }
        freqMap = dayOfMonthList.stream().sorted().collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() * 100 / dayOfMonthList.size() >= 50) {
                cronDayOfMonth = entry.getKey();
                break;
            } else {
                cronDayOfMonth = "*";
            }
        }
        freqMap = monthList.stream().sorted().collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() * 100 / monthList.size() >= 50) {
                cronMonth = entry.getKey();
                break;
            } else {
                cronMonth = "*";
            }
        }
        freqMap = dayOfWeek.stream().sorted().collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() * 100 / dayOfWeek.size() >= 50) {
                cronDayOfWeek = entry.getKey();
                break;
            } else {
                cronDayOfWeek = "*";
            }
        }

        for (String s : sortedDates) {
            LocalDateTime localDateTime = LocalDateTime.parse(s, formatter);

            // Вызов метода для проверки совпадения cron и списка дат
            if ((DateUtil.checkDates(cronSec,String.valueOf(localDateTime.getSecond())))
                    && (DateUtil.checkDates(cronMin,String.valueOf(localDateTime.getMinute())))
                    && (DateUtil.checkDates(cronHour,String.valueOf(localDateTime.getHour())))
                    && (DateUtil.checkDates(cronDayOfMonth,String.valueOf(localDateTime.getDayOfMonth())))
                    && (DateUtil.checkDates(cronDayOfMonth,String.valueOf(localDateTime.getDayOfMonth())))
                    && (DateUtil.checkDates(cronMonth,String.valueOf(localDateTime.getMonth())))
                    && (DateUtil.checkDates(cronDayOfWeek,String.valueOf(localDateTime.getDayOfWeek())))) {
                trueDates += 1;
            }
            else {
            }
        }

        String cronGenerated = cronSec + " " + cronMin + " " + cronHour + " " + cronDayOfMonth + " " + cronMonth.substring(0, 3) + " " + cronDayOfWeek.substring(0, 3);

        String[] strArray = cronGenerated.split(" ");
        if (strArray.length != 6) {
            throw new DatesToCronConvertException("0", "Валидация не пройдена");
        }
        if (trueDates >= (sortedDates.size() / 2)) {
            System.out.println("Cron подходит под условия:" + " " + cronGenerated);
        }
        else {
            System.out.println(trueDates);
            throw new DatesToCronConvertException("1", "Cron не подходит под условия");
        }
        return cronGenerated;
    }

    public String getImplementationInfo() {
        return "Стрелец Алёна Геннадьевна " + DatesToCron.class.getSimpleName() + " " + DatesToCron.class.getPackage().getName() + " https://github.com/Alionass";
    }
}