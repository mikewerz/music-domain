package com.mikewerzen.music.domain;

import org.junit.Test;

public class NoteTest
{

	@Test
	public void test()
	{
		Note note = new Note("D");
		System.out.println(note);

		System.out.println(Scale.MAJOR.listNotes(note));
		System.out.println(Scale.MINOR.listNotes(note));

	}
}
