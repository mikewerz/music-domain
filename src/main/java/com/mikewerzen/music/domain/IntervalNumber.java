package com.mikewerzen.music.domain;

import java.util.Arrays;

public enum IntervalNumber
{
	UNISON("Unison", 0, false),
	SECOND("Second", 1, false),
	THIRD("Third", 2, false),
	FOURTH("Fourth", 3, false),
	FIFTH("Fifth", 4, false),
	SIXTH("Sixth", 5, false),
	SEVENTH("Seventh", 6, false),
	OCTAVE("Octave", 7, false),
	NINTH("Ninth", 8, true),
	TENTH("Tenth", 9, true),
	ELEVENTH("Eleventh", 10, true),
	TWELFTH("Twelfth", 11, true),
	THIRTEENTH("Thirteenth", 12, true),
	FOURTEENTH("Fourteenth", 13, true),
	DOUBLE_OCTAVE("Double Octave", 14, true);


	private String displayName;
	private final int index;
	private final boolean isCompound;

	IntervalNumber(String displayName, int index, boolean isCompound)
	{
		this.displayName = displayName;
		this.index = index;
		this.isCompound = isCompound;
	}

	public int getIndex()
	{
		return index;
	}

	public static IntervalNumber findByNumber(int number)
	{
		return Arrays.stream(IntervalNumber.values()).filter(m -> m.index == (number - 1)).findFirst().orElseThrow(() -> new RuntimeException("Could Not Find Interval"));
	}

	public boolean isCompound()
	{
		return isCompound;
	}

	public String getDisplayName()
	{
		return displayName;
	}
}
