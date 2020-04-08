package com.mikewerzen.music.domain.util;

import com.mikewerzen.music.domain.Interval;
import com.mikewerzen.music.domain.Note;

public class IntervalNote
{
	private static final String ROOT_NAME = "R";

	private Interval interval;
	private Note root;


	public IntervalNote(Interval interval, Note root)
	{
		this.interval = interval;
		this.root = root;
	}

	public Interval getInterval()
	{
		return interval;
	}

	public Note getRoot()
	{
		return root;
	}

	public Note getNote()
	{
		return root.addInterval(interval);
	}

	public Note getNoteWithIntervalName()
	{
		Note converted = getNote();
		return new Note(getIntervalName(), converted.getLetter(), converted.getOctave(), converted.getSemitonesAboveC());
	}

	private String getIntervalName()
	{
		switch (interval)
		{
			case P1:
				return ROOT_NAME;
			case P8:
				return ROOT_NAME;
			case P15:
				return ROOT_NAME;
			default:
				return interval.name();
		}
	}


}
