package com.mikewerzen.music.domain;

import org.junit.Test;

public class NoteTest
{

	@Test
	public void test()
	{
		Note note = new Note("C#");
		System.out.println(note);

		//System.out.println(Scale.MAJOR.getNotesAcrossSevenOctaves(note));
		//System.out.println(Chord.MINOR.listNotes(note, 2));

		System.out.println(note.getEnharmonicNote());
		System.out.println(note.getEnharmonicNote().get().equals(note));

	}
}

