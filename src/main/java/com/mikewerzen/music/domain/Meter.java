package com.mikewerzen.music.domain;

public class Meter
{
	public static final Meter FOUR_FOUR_TIME = new Meter(4, 4);

	private int beatsPerMeasure;
	private int notesWithBeat;

	public Meter(int beatsPerMeasure, int notesWithBeat)
	{
		this.beatsPerMeasure = beatsPerMeasure;
		this.notesWithBeat = notesWithBeat;
	}

	public int getBeatsPerMeasure()
	{
		return beatsPerMeasure;
	}

	public int getNotesWithBeat()
	{
		return notesWithBeat;
	}

	public String getSignatureFraction()
	{
		return beatsPerMeasure + "/" + notesWithBeat;
	}
}
