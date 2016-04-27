/**
 * Copyright (c) 2014-present Yg0R2. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.tool.datamanipulator.util;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.util.Calendar;
import java.util.Random;

/**
 * @author Yg0R2
 */
public class RandomUtil {

	public static boolean nextBoolean() {
		return _rnd.nextBoolean();
	}

	public static Calendar nextCalendar(Calendar cal1, Calendar cal2) {
		if (Validator.isNull(cal1) || Validator.isNull(cal2)) {
			throw new IllegalArgumentException("The calendars must be valid!");
		}

		long fromMilisec = cal1.getTimeInMillis();

		long toMilisec = cal2.getTimeInMillis();

		if (fromMilisec == toMilisec) {
			return (Calendar)cal1.clone();
		}

		if (fromMilisec > toMilisec) {
			long tmp = fromMilisec;

			fromMilisec = toMilisec;

			toMilisec = tmp;
		}

		Calendar randomCalendar = Calendar.getInstance();

		randomCalendar.setTimeInMillis(
			nextLong(toMilisec - fromMilisec) + fromMilisec);

		return randomCalendar;
	}

	public static int nextInt() {
		return _rnd.nextInt();
	}

	public static int nextInt(int max) {
		if (max <= 0) {
			return 0;
		}

		return _rnd.nextInt(max);
	}

	public static int nextInt(int min, int max) {
		return _rnd.nextInt(max - min) + min;
	}

	public static long nextLong(long n) {
		if (n <= 0) {
			throw new IllegalArgumentException("n must be positive");
		}

		long bits, val;

		do {
			bits = _rnd.nextInt();
			val = bits % n;
		} while (bits - val + (n-1) < 0);

		return val;
	}

	public static String nextString() {
		return nextString(12);
	}

	public static String nextString(int maxCharacter) {
		StringBundler sb = new StringBundler(maxCharacter);

		for (int i = 0; i < maxCharacter; i++) {
			int index = nextInt(_ALLOWED_DIGITS.length);

			sb.append(_ALLOWED_DIGITS[index]);
		}

		return sb.toString();
	}

	private static final char[] _ALLOWED_DIGITS = {
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
		'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b',
		'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
		'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
	};

	private static Random _rnd = new Random();;

}