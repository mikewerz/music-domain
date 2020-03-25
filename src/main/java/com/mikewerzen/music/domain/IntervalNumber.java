package com.mikewerzen.music.domain;

public enum IntervalNumber
{
	UNISON("Unison", 0),
	SECOND("Second", 1),
	THIRD("Third", 2),
	FOURTH("Fourth", 3),
	FIFTH("Fifth", 4),
	SIXTH("Sixth", 5),
	SEVENTH("Seventh", 6),
	OCTAVE("Octave", 7),
	NINTH("Ninth", 8),
	TENTH("Tenth", 9),
	ELEVENTH("Eleventh", 10),
	TWELFTH("Twelfth", 11),
	THIRTEENTH("Thirteenth", 12),
	FOURTEENTH("Fourteenth", 13),
	DOUBLE_OCTAVE("Double Octave", 14);


	private String displayName;
	private final int index;

	IntervalNumber(String displayName, int index)
	{
		this.displayName = displayName;
		this.index = index;
	}

	public int getIndex()
	{
		return index;
	}
}
