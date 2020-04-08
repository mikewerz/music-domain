package com.mikewerzen.music.domain;

import com.mikewerzen.music.domain.util.IntervalNote;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.mikewerzen.music.domain.Interval.*;

public enum Scale
{
	MAJOR("", Arrays.asList(P1, M2, M3, P4, P5, M6, M7, P8)),
	MINOR("m", Arrays.asList(P1, M2, m3, P4, P5, m6, m7, P8)),
	CHROMATIC("", Arrays.asList(P1, m2, M2, m3, M3, P4, A4, P5, m6, M6, m7, M7, P8))
	;

	private String suffix;
	private List<Interval> intervals;

	Scale(String suffix, List<Interval> intervals)
	{
		this.suffix = suffix;
		this.intervals = intervals;
	}

	public List<Note> getNotes(Note root)
	{
		return intervals.stream().map(interval -> root.addInterval(interval)).collect(Collectors.toList());
	}

	public List<Note> getNotesAcrossSevenOctaves(Note root)
	{
		List<Note> allNotes = new ArrayList<>();
		for (int i = 1; i <= 7; i++)
		{
			root = root.setOctave(i);
			allNotes.addAll(getNotes(root));
			allNotes.remove(allNotes.size() - 1);
		}

		return allNotes;
	}

	public List<IntervalNote> getIntervalNotes(Note root)
	{
		return intervals.stream().map(interval -> new IntervalNote(interval, root)).collect(Collectors.toList());
	}

	public List<Note> getNotesWithIntervalNames(Note root)
	{
		return intervals
				.stream()
				.map(interval -> new IntervalNote(interval, root))
				.map(IntervalNote::getNoteWithIntervalName)
				.collect(Collectors.toList());
	}

	public String printNotes(Note root)
	{
		String notes = "";

		for (Interval interval : intervals)
		{
			notes += root.addInterval(interval) + "\n";
		}


		return notes;
	}

	public String getSuffix()
	{
		return suffix;
	}
}
