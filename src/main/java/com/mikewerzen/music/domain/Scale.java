package com.mikewerzen.music.domain;

import com.mikewerzen.music.domain.util.IntervalNote;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.mikewerzen.music.domain.Interval.*;

public enum Scale
{
	MAJOR("Major", "", Arrays.asList(P1, M2, M3, P4, P5, M6, M7, P8)),
	NATURAL_MINOR("Natural Minor", "m", Arrays.asList(P1, M2, m3, P4, P5, m6, m7, P8)),
	MAJOR_PENTATONIC("Major Pentatonic", "", Arrays.asList(P1, M2, M3, P5, M6, P8)),
	MINOR_PENTATONIC("Minor Pentatonic", "", Arrays.asList(P1, m3, P4, P5, m7, P8)),
	CHROMATIC("Chromatic", "", Arrays.asList(P1, m2, M2, m3, M3, P4, A4, P5, m6, M6, m7, M7, P8)),
	CHROMATIC_EXTENDED("Chromatic Extended", "", Arrays.asList(P1, m2, M2, m3, M3, P4, A4, P5, m6, M6, m7, M7, P8,
			m9, M9, m10, M10, P11, A11, P12, m13, M13, m14, M14, P15));


	private String name;
	private String suffix;
	private List<Interval> intervals;

	Scale(String name, String suffix, List<Interval> intervals)
	{
		this.name = name;
		this.suffix = suffix;
		this.intervals = intervals;
	}

	public String getName()
	{
		return name;
	}

	public List<Note> getNotes(Note root)
	{
		return intervals.stream().map(interval -> root.addInterval(interval)).collect(Collectors.toList());
	}

	public List<Note> getNotesAcrossSevenOctaves(Note root)
	{
		int step = intervals.get(intervals.size() - 1).getSemitones() / 12;
		List<Note> allNotes = new ArrayList<>();
		for (int i = 1; i <= 7; i += step)
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

	public List<Note> getNotesWithIntervalNamesAcrossOctaves(Note root)
	{
		int step = intervals.get(intervals.size() - 1).getSemitones() / 12;
		List<Note> allNotes = new ArrayList<>();
		for (int i = 1; i <= 7; i += step)
		{
			root = root.setOctave(i);
			allNotes.addAll(getNotesWithIntervalNames(root));
			allNotes.remove(allNotes.size() - 1);
		}

		return allNotes;
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
