package com.mikewerzen.music.domain;

import java.util.Arrays;
import java.util.List;

class Utils
{
	static List<String> s(String... strings)
	{
		return Arrays.asList(strings);
	}

	static List<Interval> i(Interval... intervals)
	{
		return Arrays.asList(intervals);
	}

	public static String getNumberName(int number)
	{
		switch (number)
		{
			case 1:
				return "First";
			case 2:
				return "Second";
			case 3:
				return "Third";
			case 4:
				return "Fourth";
			case 5:
				return "Fifth";
			case 6:
				return "Sixth";
			case 7:
				return "Seventh";
			case 8:
				return "Eighth";
			case 9:
				return "Ninth";
			case 10:
				return "Tenth";
			case 11:
				return "Eleventh";
			case 12:
				return "Twelfth";
			case 13:
				return "Thirteenth";
			case 14:
				return "Fourteenth";
			default:
				return number + "th";
		}
	}
}
