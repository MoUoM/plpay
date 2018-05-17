package com.javen.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {

	private static final String sf = "yyyy-MM-dd HH:mm:ss";
	private static final String sf_yyyyMMdd = "yyyy-MM-dd";
	private static final String sf_yyMMdd = "yyMMdd";
	private static final String sf_yyyyMM = "yyyy-MM";
	private static final String sf_yyyyMMddHHmmssSSS = "yyyy-MM-dd HH:mm:ss.SSS";
	private static final String sf_yyMMddHHmmssSSS = "yyMMddHHmmssSSS";
	private static final String sf_yyyyMMddHHmmss = "yyyyMMddHHmmss";
	private static final String sf_yyyyYMMMddDHHHmmMssS = "yyyy年MM月dd日 HH:mm:ss";
	private static final String sf_yyMMddHHmmss = "yyMMddHHmmss";
	private static final String sf_MMddHHmmss = "MMddHHmmss";
	private static final String sf_yyyyMMddHH = "yyyyMMddHH";
	private static final String sf_YYYYMMDD = "yyyyMMdd";

	
	
	public static Date getsf_yyyyMMddHH() {
		try {
			return new SimpleDateFormat(sf_yyyyMMddHH).parse(new SimpleDateFormat(sf_yyyyMMddHH).format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date getsf() {
		try {
			return new SimpleDateFormat(sf).parse(new SimpleDateFormat(sf).format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String formatDate(Date date) {
		return new SimpleDateFormat(sf).format(date);
	}

	public static String formatDate(String date) {
		try {
			return formatDateyyyyMMddhhmmss(new SimpleDateFormat(sf).parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String formatDate(String date, String sf) {
		try {
			return formatDate(new SimpleDateFormat(sf).parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String formatDateyyyyMMddhhmmss(Date date) {
		return new SimpleDateFormat(sf_yyyyMMddHHmmss).format(date);
	}

	public static String formatDateyyyyMMdd(Date date) {
		return new SimpleDateFormat(sf_yyyyMMdd).format(date);
	}

	public static String formatDateYYYYMMDD(Date date) {
		return new SimpleDateFormat(sf_YYYYMMDD).format(date);
	}

	public static String formatDateyyyyYMMMddDhhHmmMssS(Date date) {
		return new SimpleDateFormat(sf_yyyyYMMMddDHHHmmMssS).format(date);
	}

	public static String getDateStringAfter15Minsf_yyyyMMddhhmmss() {

		Date tmpDate = new Date(System.currentTimeMillis() + 15 * 60 * 1000);

		return new SimpleDateFormat(sf_yyyyMMddHHmmss).format(tmpDate);

	}

	public static String getDateStringAfter15Min() {

		Date tmpDate = new Date(System.currentTimeMillis() + 15 * 60 * 1000);

		return new SimpleDateFormat(sf).format(tmpDate);

	}

	public static String getDateStringBefore15Min() {

		Date tmpDate = new Date(System.currentTimeMillis() - 15 * 60 * 1000);

		return new SimpleDateFormat(sf).format(tmpDate);

	}

	public static Date parseyyyyMMddhhmmss(String dateStr) {
		try {
			return new SimpleDateFormat(sf_yyyyMMddHHmmss).parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date parseyyMMddhhmmss(String dateStr) {
		try {
			return new SimpleDateFormat(sf_yyMMddHHmmss).parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date parseMMddhhmmss(String dateStr) {
		try {
			return new SimpleDateFormat(sf_MMddHHmmss).parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date parseDate(String dateStr) {
		try {
			return new SimpleDateFormat(sf).parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("dateStr=" + dateStr);
			return null;
		}
	}

	public static Date parseyyyyMMdd(String dateStr) {
		try {
			return new SimpleDateFormat(sf_yyyyMMdd).parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date parseyyyyMM(String dateStr) {
		try {
			return new SimpleDateFormat(sf_yyyyMM).parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getCurrentDateyyyyMMddhhmmssSSS() {
		return new SimpleDateFormat(sf_yyyyMMddHHmmssSSS).format(new Date());
	}
	
	public static String getCurrentDateyyMMddhhmmssSSS() {
		return new SimpleDateFormat(sf_yyMMddHHmmssSSS).format(new Date());
	}

	public static String getCurrentDateyyyyMMdd() {
		return new SimpleDateFormat(sf_yyyyMMdd).format(new Date());
	}

	public static String getCurrentDateyyMMdd() {
		return new SimpleDateFormat(sf_yyMMdd).format(new Date());
	}

	public static String getCurrentMonthyyyyMM() {
		return new SimpleDateFormat(sf_yyyyMM).format(new Date());
	}

	public static String getPreviousMonthyyyyMM(int offsetDay) {
		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.MONTH, -offsetDay);

		return new SimpleDateFormat(sf_yyyyMM).format(cal.getTime());
	}

	public static String getPreviousDateyyyyMMdd() {
		return getPreviousDateyyyyMMdd(1);
	}

	public static String getPreviousDateyyyyMMdd(int offsetDay) {
		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DAY_OF_YEAR, -offsetDay);

		return new SimpleDateFormat(sf_yyyyMMdd).format(cal.getTime());
	}
	
	public static String getPreviousDateyyyyMMdd(String preDay) throws ParseException {
		return getPreviousDateyyyyMMdd(preDay, 1);
	}

	public static String getPreviousDateyyyyMMdd(String preDay, int offsetDay) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new SimpleDateFormat(sf_yyyyMMdd).parse(preDay));
		
		cal.add(Calendar.DAY_OF_YEAR, -offsetDay);

		return new SimpleDateFormat(sf_yyyyMMdd).format(cal.getTime());
	}
	
	public static String getNextDateyyyyMMdd(String preDay, int offsetDay) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new SimpleDateFormat(sf_yyyyMMdd).parse(preDay));
		
		cal.add(Calendar.DAY_OF_YEAR, offsetDay);

		return new SimpleDateFormat(sf_yyyyMMdd).format(cal.getTime());
	}

	public static Date getDateOffSecond(int offSecond) {
		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.SECOND, -offSecond);

		return cal.getTime();
	}

	public static String getCurrentDateyyyyMMddhhmmss() {
		return new SimpleDateFormat(sf_yyyyMMddHHmmss).format(new Date());
	}

	public static String getCurrentDateyyMMddhhmmss() {
		return new SimpleDateFormat(sf_yyMMddHHmmss).format(new Date());
	}

	public static String getCurrentDateString() {
		return new SimpleDateFormat(sf).format(new Date());
	}

	public static void main(String[] args) throws ParseException {
		System.out.println(getDateStringAfter15Min());
		System.out.println(new SimpleDateFormat(sf_yyyyMMddHHmmss).parse("20150706121807"));
		System.out.println(getPreviousDateyyyyMMdd());
		Date s = new SimpleDateFormat(sf_yyyyMMdd).parse("2015-10-10");
		Calendar cal = Calendar.getInstance();
		cal.setTime(s);
		cal.add(Calendar.DAY_OF_YEAR, -2);
		System.out.println(new SimpleDateFormat(sf_yyyyMMdd).format(cal.getTime()));
		
		System.out.println(formatSecond(933454l));
		
	}

	/**
	 * 将秒转换成时分秒表示
	 * @param stayTime
	 * @return
	 */
	public static String formatSecond(Long second) {
		// TODO Auto-generated method stub
        String format;  
        Object[] array;  
        Integer hours =(int) (second / (60 * 60));  
        Integer minutes = (int) (second / 60 - hours * 60);  
        Integer seconds = (int) (second - minutes * 60 - hours * 60 * 60);  
        if(hours>0){  
            format="%1$,d时%2$,d分%3$,d秒";  
            array=new Object[]{hours,minutes,seconds};  
        }else if(minutes>0){  
            format="%1$,d分%2$,d秒";  
            array=new Object[]{minutes,seconds};  
        }else{  
            format="%1$,d秒";  
            array=new Object[]{seconds};  
        } 
		return String.format(format, array);
	}

	
}
