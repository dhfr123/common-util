/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.createk.common.util;

import com.createk.common.CommonUtilConstant;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Years;

/**
 *
 * @author Deni Husni Fahri Rizal
 * @version 1
 * @since 13 January 2013
 */
public class DateTimeUtil {

    /**
     * <p>
     * Get or return Past or Future date based on parameter</p>
     * <p>
     * <b>Parameter amount :</b> use negative symbol to get past date, use
     * positive symbol to get future date. Parameter amount combination with
     * parameter constantParameter. </p>
     * <p>
     * <b>Parameter constantParameter :</b> the type of times that will be
     * added. Example : CommonUtilConstant.DATE_FORMAT_MONTH</p>
     * <p>
     * <b>Parameter type :</b></p>
     * <ul>
     * <li>CommonUtilConstant.DATE_FORMAT_MILLISECOND = millisecond</li>
     * <li>CommonUtilConstant.DATE_FORMAT_SECOND = second</li>
     * <li>CommonUtilConstant.DATE_FORMAT_MINUTES = minutes</li>
     * <li>CommonUtilConstant.DATE_FORMAT_HOURS = hours</li>
     * <li>CommonUtilConstant.DATE_FORMAT_DAY = day</li>
     * <li>CommonUtilConstant.DATE_FORMAT_WEEK = week</li>
     * <li>CommonUtilConstant.DATE_FORMAT_MONTH = month</li>
     * <li>CommonUtilConstant.DATE_FORMAT_YEAR = year</li>
     * </ul>
     *
     * @return Date result of past or future date
     * @param inputParam Date reference to calculate
     * @param timeDifference Integer reference, can be negative value like -1 or
     * positive value like 7
     * @param constantParameter String reference,see the CommonUtilConstant
     */
    public static Date getDateFrom(Date inputParam, int timeDifference, String constantParameter) {
        Date returnDate = null;
        if (constantParameter.equalsIgnoreCase(CommonUtilConstant.DATE_FORMAT_DAY)) {
            returnDate = DateUtils.addDays(inputParam, timeDifference);
        }
        if (constantParameter.equalsIgnoreCase(CommonUtilConstant.DATE_FORMAT_HOURS)) {
            returnDate = DateUtils.addHours(inputParam, timeDifference);
        }
        if (constantParameter.equalsIgnoreCase(CommonUtilConstant.DATE_FORMAT_MILLISECOND)) {
            returnDate = DateUtils.addMilliseconds(inputParam, timeDifference);
        }
        if (constantParameter.equalsIgnoreCase(CommonUtilConstant.DATE_FORMAT_MINUTES)) {
            returnDate = DateUtils.addMinutes(inputParam, timeDifference);
        }
        if (constantParameter.equalsIgnoreCase(CommonUtilConstant.DATE_FORMAT_MONTH)) {
            returnDate = DateUtils.addMonths(inputParam, timeDifference);
        }
        if (constantParameter.equalsIgnoreCase(CommonUtilConstant.DATE_FORMAT_SECOND)) {
            returnDate = DateUtils.addSeconds(inputParam, timeDifference);
        }
        if (constantParameter.equalsIgnoreCase(CommonUtilConstant.DATE_FORMAT_YEAR)) {
            returnDate = DateUtils.addYears(inputParam, timeDifference);
        }
        if (constantParameter.equalsIgnoreCase(CommonUtilConstant.DATE_FORMAT_WEEK)) {
            returnDate = DateUtils.addWeeks(inputParam, timeDifference);
        }
        return returnDate;
    }

    /**
     * Checking from two date objects, will return true if the date have the
     * same value.
     *
     * @return Boolean
     * @param date1 Date reference
     * @param date2 Date reference
     */
    public static Boolean isSameDateWithTimeIgnore(Date date1, Date date2) {
        return DateUtils.isSameDay(date1, date2);
    }

    /**
     * Checking from two date objects ( included time ), will return true if the
     * date have the same value.
     *
     * @return Boolean
     * @param date1 Date reference
     * @param date2 Date reference
     */

    public static Boolean isSameDateAndTime(Date date1, Date date2) {
        return DateUtils.isSameInstant(date1, date2);
    }

    /**
     * get total times (Age) based on parameter date
     *
     * @param birthdate input date type
     * @return Integer
     */
    public static Integer getAge(Date birthdate) {
        DateMidnight date1 = new DateMidnight(birthdate);
        DateTime now = new DateTime();
        Years years = Years.yearsBetween(date1, now);
        return years.getYears();
    }

    /**
     * get total days, between two date object
     *
     * @return Integer
     * @param date1 Date reference
     * @param date2 Date reference
     */
    public static Integer getTotalDayDifference(Date date1, Date date2) {
        Days days = Days.daysBetween(new DateMidnight(date1), new DateMidnight(date2));
        return days.getDays();
    }

    /**
     * get total Months, between two date object
     *
     * @return Integer
     * @param date1 Date reference
     * @param date2 Date reference
     */
    public static Integer getTotalMonthDifference(Date date1, Date date2) {
        Months months = Months.monthsBetween(new DateMidnight(date1), new DateMidnight(date2));
        return months.getMonths();
    }

    /**
     * get total years, between two date object
     *
     * @return Integer
     * @param date1 Date reference
     * @param date2 Date reference
     */
    public static Integer getToalYearDifference(Date date1, Date date2) {
        return Years.yearsBetween(new DateMidnight(date1), new DateMidnight(date2)).getYears();
    }

    /**
     * get total working days
     *
     * @param startDate
     * @return Integer
     * @param endDate Date reference
     * @param totalPublicHoliday, total of public holiday
     * @param totalAnnualLeave, total of annual leave
     */
    public static Integer getTotalWorkingDay(Date startDate, Date endDate, int totalPublicHoliday, int totalAnnualLeave) {
        DateTime start = new DateTime(startDate);
        DateTime end = new DateTime(endDate);
        int satSunCount = 0;
        int numOfDays = 0;
        DateTime iterate = start;
        if (iterate.getDayOfWeek() == 6 | iterate.getDayOfWeek() == 7) {
            ++satSunCount;
        }
        ++numOfDays;
        while (!iterate.isEqual(end)) {
            ++numOfDays;
            iterate = iterate.plusDays(1);
            if (iterate.getDayOfWeek() == 6 | iterate.getDayOfWeek() == 7) {
                ++satSunCount;
            }
        }
        int workingDays = numOfDays - satSunCount;
        int totalWorkingDays = workingDays - totalAnnualLeave - totalPublicHoliday;
        return totalWorkingDays;
    }
}
