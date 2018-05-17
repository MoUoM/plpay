package com.javen.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;


public class Util {

	public static String getFilePath(String basePath, String partnerNo, String fileType, String preDay) {
		return String.format("%s/%s/%s.data.%s", basePath, partnerNo, preDay, fileType);
	}
	
	public static String getFilePath(String basePath, String partnerNo, String fileType) {
		return String.format("%s/%s/%s.data.%s", basePath, partnerNo, CalendarUtil.getCurrentDateyyyyMMdd(), fileType);
	}

	public static String getFileEndPrefix() {
		return "End ,";
	}

	public static BigDecimal getMoney(String value) {
		if (StringUtils.isNotBlank(value)) {
			return new BigDecimal(new BigDecimal(value).multiply(new BigDecimal("100")).longValue() + "");
		} else {
			return null;
		}
	}

	public static void closeFile(PrintWriter pw) {
		if (pw != null) {
			pw.close();
		}
	}

	public static void closeStrem(RandomAccessFile raf) {
		if (raf != null) {
			try {
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String getRemortIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}
	
	public static String replaceCarPlate(String carPlate, String replaceName){
		String body = replaceName;
		if(carPlate.length() > 6){
			body = carPlate.substring(0, 2) + "**" + carPlate.substring(4, carPlate.length());
		}
		
		return body;
	}
	public static String getStringRandom(int num) {

		String val = "";
		Random random = new Random();

		// 参数length，表示生成几位随机数
		for (int i = 0; i < num; i++) {

			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 输出字母还是数字
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 输出是大写字母还是小写字母
				int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (random.nextInt(26) + temp);
			} else if ("num".equalsIgnoreCase(charOrNum)) {
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}
}
