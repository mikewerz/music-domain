package com.mikewerzen.music.domain;

import com.mikewerzen.music.domain.util.IntervalNote;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.mikewerzen.music.domain.Interval.*;
import static com.mikewerzen.music.domain.Utils.s;

public enum ChordStructure
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
	MINOR6("Minor Sixth", s("m6", "min6", "-6"), P1, m3, P5, M6),
	MIN7("Minor Seventh", s("m7", "min7", "-7"), P1, m3, P5, m7),
	MINADD9("Minor Added Ninth", s("madd9"), P1, m3, P5, M9),
	MINORSIX_NINE("Minor Six Add Nine", s("m6/9"), P1, m3, P5, M6, M9),
	MIN9("Minor Ninth", s("m9"), P1, m3, P5, m7, M9),
	MIN11("Minor Eleventh", s("m11"), P1, m3, P5, m7, M9, P11),
	MIN13("Minor Thirteenth", s("m13"), P1, m3, P5, m7, M9, P11, M13),
	MINMAJ7("Minor Major Seventh", s("m(Maj7)"), P1, m3, P5, M7),
	MINMAJ9("Minor Major Ninth", s("m(Maj9)"), P1, m3, P5, M7, M9),
	MINMAJ11("Minor Major Eleventh", s("m(Maj11)"), P1, m3, P5, M7, M9, P11),
	MINMAJ13("Minor Major Thirteenth", s("m(Maj13)"), P1, m3, P5, M7, M9, P11, M13),
	MIN7FLAT5("Minor Seventh Flat Five", s("m7b5"), P1, m3, d5, m7),

	//Dom
	DOM7("Dominant Seventh", s("7", "dom7"), P1, M3, P5, m7),
	NINTH("Ninth", s("9", "dom9"), P1, M3, P5, m7, M9),
	ELEVENTH("Eleventh", s("11", "dom11"), P1, M3, P5, m7, M9, P11),
	THIRTEENTH("Thirteenth", s("13", "dom13"), P1, M3, P5, m7, M9, P11, M13),
	SEVENSHARPFIVE("Seven Sharp Five", s("7#5"), P1, M3, A5, m7),
	SEVENFLATFIVE("Seven Flat Five", s("7b5"), P1, M3, d5, m7),
	SEVENFLATNINE("Seven Flat Nine", s("7b9"), P1, M3, P5, m7, m9),
	SEVENSHARPNINE("Seven Sharp Nine", s("7#9"), P1, M3, P5, m7, A9),
	NINESHARPFIVE("Nine Sharp Five", s("9#5"), P1, M3, A5, m7, M9),
	NINEFLATFIVE("Nine Flat Five", s("9b5"), P1, M3, d5, m7, M9),
	SEVENSHARPFIVESHARPNINE("Seven Sharp Five Sharp Nine", s("7#5#9"), P1, M3, A5, m7, A9),
	SEVENSHARPFIVEFLATNINE("Seven Sharp Five Flat Nine", s("7#5b9"), P1, M3, A5, m7, m9),
	SEVENFLATFIVESHARPNINE("Seven Flat Five Sharp Nine", s("7b5#9"), P1, M3, d5, m7, A9),
	SEVENFLATFIVEFLATNINE("Seven Flat Five Flat Nine", s("7b5b9"), P1, M3, d5, m7, m9),
	SEVENSHARPELEVEN("Seven Sharp Eleven", s("7#11"), P1, M3, P5, m7, A11),

	//Dim
	DIM("Diminished", s("dim"), P1, m3, d5),
	DIM7("Diminished Seventh", s("dim7"), P1, m3, d5, d7),
	HALF_DIM("Half Diminished", s("m7b5"), P1, m3, d5, m7),

	//Misc
	SUS2("Suspended Second", s("sus2"), P1, M2, P5),
	SUS4("Suspended Fourth", s("sus4"), P1, P4, P5),
	SUS7("Suspended Seventh", s("7sus4", "7sus"), P1, P4, P5, m7),
	AUG("Augmented", s("aug, +"), P1, M3, A5),
	AUG7("Augmented Seventh", s("aug7", "7#5", "7+"), P1, M3, A5, m7),
	FIFTH("Five", s("5"), P1, P5),
	FLAT_FIFTH("Flat Five", s("b5", "-5"), P1, d5),

	;

	private String name;
	private List<String> suffixes;
	private List<Interval> intervals;


	ChordStructure(String name, List<String> suffixes, Interval... intervals)
	{
		this.name = name;
		this.suffixes = suffixes;
		this.intervals = Arrays.asList(intervals);
	}

	ChordStructure(String name, List<String> suffixes, List<Interval> intervals)
	{
		this.name = name;
		this.suffixes = suffixes;
		this.intervals = intervals;
	}

	public static ChordStructure findBySuffix(String suffix)
	{
		return Arrays
				.stream(values())
				.filter(chordStructure -> chordStructure.getSuffixes().contains(suffix))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Could Not Find Chord Suffix"));
	}

	public List<Note> getNotes(Note root)
	{
		return intervals.stream().map(interval -> root.addInterval(interval)).collect(Collectors.toList());
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
