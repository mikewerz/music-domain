package com.mikewerzen.music.domain;

import java.util.Arrays;
import java.util.List;

import static com.mikewerzen.music.domain.Interval.*;

public enum Chord
{
	MAJOR("Major", Arrays.asList("", "maj"), P1, M3, P5),
	MINOR("Minor", Arrays.asList("m", "min", "-"), P1, m3, P5),
	SUS2("Suspended Second", Arrays.asList("sus2"), P1, M2, P5),
	SUS4("Suspended Fourth", Arrays.asList("sus4"), P1, P4, P5),
	//ADD9("Added Ninth", Arrays.asList("add9"), P1, M3, P5, ),
	DOM7("Dominant Seventh", Arrays.asList("7", "dom7"), P1, M3, P5, m7),
	MAJ7("Major Seventh", Arrays.asList("maj7"), P1, M3, P5, M7),
	MIN7("Minor Seventh", Arrays.asList("m7", "min7", "-7"), P1, m3, P5, m7),

	;

	private String name;
	private List<String> suffixes;
	private List<Interval> intervals;


	Chord(String name, List<String> suffixes, Interval... intervals)
	{
		this.name = name;
		this.suffixes = suffixes;
		this.intervals = Arrays.asList(intervals);
	}

	Chord(String name, List<String> suffixes, List<Interval> intervals)
	{
		this.name = name;
		this.suffixes = suffixes;
		this.intervals = intervals;
	}


	public String listNotes(Note root)
	{
		String notes = "";

		for(Interval interval : intervals)
		{
			notes += root.addInterval(interval);
		}

		return notes;
	}

	public String listNotes(Note root, int inversion)
	{
		int currInv = 0;

		String notes = "";

		for(Interval interval : intervals)
		{
			if(currInv < inversion)
			{
				notes += root.addInterval(P8).addInterval(interval);
				currInv++;
			}
			else
			{
				notes += root.addInterval(interval);
			}
		}

		return notes;
	}
}
