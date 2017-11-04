package com.intellect.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {

	static SimpleDateFormat sfd = new SimpleDateFormat("dd-MMM-yyyy");
	
	public static Date convertStringToDate(final String date) throws ParseException {
		Date parsedDate = null;
		if (date!=null) {
			parsedDate = sfd.parse(date);
		}
		return parsedDate;
	}
	
	public static String convertDateToString(final Date date) throws ParseException {
		String parsedDate = null;
		if (date!=null) {
			parsedDate = sfd.format(date);
		}
		return parsedDate;
	}
}
