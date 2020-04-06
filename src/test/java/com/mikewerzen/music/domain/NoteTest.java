package com.mikewerzen.music.domain;

import org.junit.Test;

public class NoteTest
{

	@Test
	public void test()
	{
		Note root = new Note("C");
		Chord chord = new Chord(root, ChordStructure.ADD9);

		System.out.println(chord.matchesNotes(new Chord("Cadd9").getNotes()));
		System.out.println(chord.getInversion(1));
		System.out.println(chord.getInversion(3));

		System.out.println(Scale.CHROMATIC.getNotes(root));

	}
}

