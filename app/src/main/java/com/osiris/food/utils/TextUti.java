package com.osiris.food.utils;

public class TextUti {


	public static String ToDBC(String str) {

		char[] c = str.toCharArray();

		for (int i = 0; i < c.length; i++) {

			if (c[i] == 12288) {

				c[i] = (char) 32;

				continue;

			}

			if (c[i] > 65280 && c[i] < 65375)

				c[i] = (char) (c[i] - 65248);

		}

		return new String(c);

	}
}
