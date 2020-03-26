package com.mikewerzen.music.domain;

import java.util.Arrays;
import java.util.List;

class Utils
{
	static List<String> s(String...strings) {
		return Arrays.asList(strings);
	}

	static List<Interval> i(Interval...intervals) {
		return Arrays.asList(intervals);
	}
}
