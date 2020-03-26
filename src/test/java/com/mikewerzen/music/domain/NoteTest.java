package com.mikewerzen.music.domain;

import org.junit.Test;

public class NoteTest
{

	@Test
	public void test()
	{
		Note note = new Note("C");
		System.out.println(note);

		System.out.println(Chord.ADD9.listNotes(note));
		System.out.println(Chord.MINOR.listNotes(note, 2));

	}
}
