package com.mikewerzen.music.domain;

import java.util.Arrays;
import java.util.List;

import static com.mikewerzen.music.domain.Interval.*;

public enum Scale
{
	MAJOR(Arrays.asList(P1, M2, M3, P4, P5, M6, M7, P8)),
	MINOR(Arrays.asList(P1, M2, m3, P4, P5, m6, m7, P8))
	;

	private List<Interval> intervals;

	Scale(List<Interval> intervals)
	{
		this.intervals = intervals;
	}

	public String listNotes(Note root) {
		String notes = "";

		for(Interval interval : intervals)
		{
			notes += root.addInterval(interval) + "\n";
		}


		return notes;
	}
}
