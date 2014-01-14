/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.createk.common.util;

import com.createk.common.CommonUtilConstant;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * NumberFormatter, a class with function is to format and parse from String to
 * Number and vice versa. Managed by Spring.
 *
 * @author Deni Husni Fahri Rizal
 *
 * <p>
 * minFractionDigit total minimum digit</P>
 * <p>
 * maxFractionDigit total maximum digit</p>
 * <p>defaultLocale default local where the application running, see
 * application.properties activeLocale active locale. locale that used by
 * application, get locale from local session or application session
 *
 */
public class NumberFormatter {

    private Integer minFractionDigit;
    private Integer maxFractionDigit;
    private String defaultLocale;

    public Integer getMinFractionDigit() {
        return minFractionDigit;
    }

    public void setMinFractionDigit(Integer minFractionDigit) {
        this.minFractionDigit = minFractionDigit;
    }

    public Integer getMaxFractionDigit() {
        return maxFractionDigit;
    }

    public void setMaxFractionDigit(Integer maxFractionDigit) {
        this.maxFractionDigit = maxFractionDigit;
    }

    public String getDefaultLocale() {
        return defaultLocale;
    }

    public void setDefaultLocale(String defaultLocale) {
        this.defaultLocale = defaultLocale;
    }

    /**
     * <p>
     * Get number format with default locale. the return value is String </p>
     * <p>
     * the maximal and minimal fraction are configured in
     * application.properties</p>
     * <p>
     * typeInstance - Type number format</p>
     * <ul>
     * <li>number</li>
     * <li>currency</li>
     * <li>percent</li>
     * </ul>
     *
     * @return String number format
     * @param number number reference
     * @param typeInstance type of number format
     */
    public String getNumberAsStringDefaultLocale(Number number, String typeInstance) {
        NumberFormat nf = getNumberFormat(typeInstance, new Locale(defaultLocale));
        nf.setMaximumFractionDigits(maxFractionDigit);
        nf.setMinimumFractionDigits(minFractionDigit);
        return nf.format(number);
    }

    /**
     * <p>
     * Get number format with specific locale. the return value is String </p>
     * <p>
     * the maximal and minimal fraction are configured in
     * application.properties</p>
     * <p>
     * typeInstance - Type number format</p>
     * <ul>
     * <li>number</li>
     * <li>currency</li>
     * <li>percent</li>
     * </ul>
     *
     * @return String number format
     * @param number number reference
     * @param locale Locale reference
     * @param typeInstance type of number format
     */
    public String getNumberAsStringActiveLocale(Number number, Locale locale, String typeInstance) {
        NumberFormat nf = getNumberFormat(typeInstance, locale);
        nf.setMaximumFractionDigits(maxFractionDigit);
        nf.setMinimumFractionDigits(minFractionDigit);
        return nf.format(number);
    }

    /**
     * <p>
     * Get number format with default locale. the return value is Number </p>
     * <p>
     * the maximal and minimal fraction are configured in
     * application.properties</p>
     * <p>
     * typeInstance - Type number format</p>
     * <ul>
     * <li>number</li>
     * <li>currency</li>
     * <li>percent</li>
     * </ul>
     *
     * @return Number number format
     * @param number number reference
     * @param typeInstance type of number format
     */
    public Number getNumberDefaultLocale(String inputNumber, String typeInstance) throws ParseException {
        NumberFormat nf = getNumberFormat(typeInstance, new Locale(defaultLocale));
        nf.setMaximumFractionDigits(maxFractionDigit);
        nf.setMinimumFractionDigits(minFractionDigit);
        return nf.parse(inputNumber);
    }

    /**
     * <p>
     * Get number format with specific locale. the return value is Number </p>
     * <p>
     * the maximal and minimal fraction are configured in
     * application.properties</p>
     * <p>
     * typeInstance - Type number format</p>
     * <ul>
     * <li>number</li>
     * <li>currency</li>
     * <li>percent</li>
     * </ul>
     *
     * @return String number format
     * @param number number reference
     * @param locale Locale reference
     * @param typeInstance type of number format
     */
    public Number getNumberActiveLocale(String inputNumber, Locale locale, String typeInstance) throws ParseException {
        NumberFormat nf = getNumberFormat(typeInstance, locale);
        nf.setMaximumFractionDigits(maxFractionDigit);
        nf.setMinimumFractionDigits(minFractionDigit);
        return nf.parse(inputNumber);
    }

    private NumberFormat getNumberFormat(String typeInstance, Locale locale) {
        NumberFormat nf = null;
        if (typeInstance.equalsIgnoreCase(CommonUtilConstant.NUMBER_FORMAT_NUMBER_TYPE)) {
            nf = NumberFormat.getNumberInstance(locale);
        }
        if (typeInstance.equalsIgnoreCase(CommonUtilConstant.NUMBER_FORMAT_PERCENT_TYPE)) {
            nf = NumberFormat.getPercentInstance(locale);
        }
        if (typeInstance.equalsIgnoreCase(CommonUtilConstant.NUMBER_FORMAT_CURRENCY_TYPE)) {
            nf = NumberFormat.getCurrencyInstance(locale);
        }
        return nf;
    }
}
