package com.mikewerzen.music.domain;

public enum IntervalQuality
{
	PERFECT("Perfect"),
	MAJOR("Major"),
	MINOR("Minor"),
	AUGMENTED("Augmented"),
	DIMINISHED("Diminished");

	private String displayName;

	IntervalQuality(String displayName)
	{
		this.displayName = displayName;
	}

	public String getDisplayName()
	{
		return displayName;
	}
}
