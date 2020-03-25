package com.mikewerzen.music.domain;

import static com.mikewerzen.music.domain.IntervalNumber.*;
import static com.mikewerzen.music.domain.IntervalQuality.*;

public enum Interval
{
	P1(PERFECT, UNISON, 0),
	d2(DIMINISHED, SECOND, 0),
	m2(MINOR, SECOND, 1),
	A1(AUGMENTED, UNISON, 1),
	M2(MAJOR, SECOND, 2),
	d3(DIMINISHED, THIRD, 2),
	m3(MINOR, THIRD, 3),
	A2(AUGMENTED, SECOND, 3),
	M3(MAJOR, THIRD, 4),
	d4(DIMINISHED, FOURTH, 4),
	P4(PERFECT, FOURTH, 5),
	A3(AUGMENTED, THIRD, 5),
	d5(DIMINISHED, FIFTH, 6),
	A4(AUGMENTED, FOURTH, 6),
	P5(PERFECT, FIFTH, 7),
	d6(DIMINISHED, SIXTH, 7),
	m6(MINOR, SIXTH, 8),
	A5(AUGMENTED, FIFTH, 8),
	M6(MAJOR, SIXTH, 9),
	d7(DIMINISHED, SEVENTH, 9),
	m7(MINOR, SEVENTH, 10),
	A6(AUGMENTED, SIXTH, 10),
	M7(MAJOR, SEVENTH, 11),
	d8(DIMINISHED, OCTAVE, 11),
	P8(PERFECT, OCTAVE, 12),
	A7(AUGMENTED, SEVENTH, 12)

	;




	private final IntervalQuality quality;
	private final IntervalNumber number;
	private final int semitones;

	Interval(IntervalQuality quality, IntervalNumber number, int semitones)
	{
		this.quality = quality;
		this.number = number;
		this.semitones = semitones;
	}

	public int getSemitones()
	{
		return semitones;
	}

	public IntervalNumber getNumber()
	{
		return number;
	}

	public IntervalQuality getQuality()
	{
		return quality;
	}
}
