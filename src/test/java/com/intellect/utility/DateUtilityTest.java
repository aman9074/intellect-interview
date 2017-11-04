package com.intellect.utility;

import java.text.ParseException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.intellect.utils.DateUtility;

public class DateUtilityTest {

	@Test
	public void testDateGenerator() throws ParseException {
		Date date = DateUtility.convertStringToDate("02-Mar-1980");
		Assert.assertNotNull(date);
	}
}
