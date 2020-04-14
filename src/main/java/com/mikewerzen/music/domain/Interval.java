package com.mikewerzen.music.domain;

import java.util.Arrays;

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
	A7(AUGMENTED, SEVENTH, 12),
	d9(DIMINISHED, NINTH, 12),
	m9(MINOR, NINTH, 13),
	A8(AUGMENTED, OCTAVE, 13),
	M9(MAJOR, NINTH, 14),
	d10(DIMINISHED, TENTH, 14),
	m10(MINOR, TENTH, 15),
	A9(AUGMENTED, NINTH, 15),
	M10(MAJOR, TENTH, 16),
	d11(DIMINISHED, ELEVENTH, 16),
	P11(PERFECT, ELEVENTH, 17),
	A10(AUGMENTED, TENTH, 17),
	d12(DIMINISHED, TWELFTH, 18),
	A11(AUGMENTED, ELEVENTH, 18),
	P12(PERFECT, TWELFTH, 19),
	d13(DIMINISHED, THIRTEENTH, 19),
	m13(MINOR, THIRTEENTH, 20),
	A12(AUGMENTED, TWELFTH, 20),
	M13(MAJOR, THIRTEENTH, 21),
	d14(DIMINISHED, FOURTEENTH, 21),
	m14(MINOR, FOURTEENTH, 22),
	A13(AUGMENTED, THIRTEENTH, 22),
	M14(MAJOR, FOURTEENTH, 23),
	d15(DIMINISHED, DOUBLE_OCTAVE, 23),
	P15(PERFECT, DOUBLE_OCTAVE, 24),
	A14(AUGMENTED, FOURTEENTH, 24),
	A15(AUGMENTED, DOUBLE_OCTAVE, 25)
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

	public Interval invert()
	{
		if(getNumber().isCompound())
		{
			return invertCompoundInterval();
		}

		return invertSimpleInterval();
	}

	private Interval invertSimpleInterval()
	{
		IntervalQuality invertedQuality = null;
		switch (quality) {
			case MAJOR:
				invertedQuality = MINOR;
				break;
			case MINOR:
				invertedQuality = MAJOR;
				break;
			case PERFECT:
				invertedQuality = PERFECT;
				break;
			case AUGMENTED:
				invertedQuality = DIMINISHED;
				break;
			case DIMINISHED:
				invertedQuality = AUGMENTED;
				break;
		}

		IntervalNumber invertedNumber = IntervalNumber.findByNumber(9 - (number.getIndex() + 1));

		return findByQualityAndNumber(invertedQuality, invertedNumber);
	}

	public static Interval findByQualityAndNumber(IntervalQuality quality, IntervalNumber number)
	{
		return Arrays
				.stream(Interval.values())
				.filter(interval -> interval.quality == quality && interval.number == number)
				.findAny()
				.orElseThrow(() -> new RuntimeException("Could Not Find Interval: " + quality + " " + number));
	}

	public String getName()
	{
		return quality.getDisplayName() + " " + number.getDisplayName();
	}

	private Interval invertCompoundInterval()
	{
		return findByQualityAndNumber(quality, IntervalNumber.findByNumber((number.getIndex() + 1) - 7));
	}
}
