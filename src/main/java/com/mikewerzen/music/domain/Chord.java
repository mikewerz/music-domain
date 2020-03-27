package com.mikewerzen.music.domain;

import java.util.Arrays;
import java.util.List;

import static com.mikewerzen.music.domain.Interval.*;
import static com.mikewerzen.music.domain.Utils.*;

public enum Chord
{
	// Major
	MAJOR("Major", s("", "maj"), P1, M3, P5),
	FLAT5("Major Flat Five", s("b5"), P1, M3, d5),
	MAJ6("Major Sixth", s("6", "maj6"), P1, M3, P5, M6),
	SIX_NINE("Six Nine", s("6/9"), P1, M3, P5, M6, M9),
	ADD9("Added Ninth", s("add9"), P1, M3, P5, M9),
	MAJ7("Major Seventh", s("maj7"), P1, M3, P5, M7),
	MAJ7FLAT5("Major Seventh Flat Five", s("maj7b5"), P1, M3, d5, M7),
	MAJ7SHARP5("Major Seventh Sharp Five", s("maj7#5"), P1, M3, A5, M7),
	MAJ9("Major Ninth", s("maj9"), P1, M3, P5, M7, M9),
	MAJ11("Major Eleventh", s("maj11"), P1, M3, P5, M7, M9, P11),
	MAJ7SHARP11("Major Seventh Sharp Eleventh", s("maj7#11"), P1, M3, P5, M7, M9, A11),
	MAJ13("Major Thirteenth", s("maj13"), P1, M3, P5, M7, M9, P11, M13),


	//Minor
	MINOR("Minor", s("m", "min", "-"), P1, m3, P5),

	//Dom
	DOM7("Dominant Seventh", s("7", "dom7"), P1, M3, P5, m7),

	//Misc
	SUS2("Suspended Second", s("sus2"), P1, M2, P5),
	SUS4("Suspended Fourth", s("sus4"), P1, P4, P5),
	MIN7("Minor Seventh", s("m7", "min7", "-7"), P1, m3, P5, m7),

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

	public String getName()
	{
		return name;
	}

	public List<String> getSuffixes()
	{
		return suffixes;
	}

	public List<Interval> getIntervals()
	{
		return intervals;
	}
}
