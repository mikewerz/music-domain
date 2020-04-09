package com.mikewerzen.music.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Chord
{
	private String name;
	private String shortName;
	private ChordStructure chordStructure;
	private Note root;
	private Note slashRoot;
	private List<Note> notes;

	public Chord(String chordName)
	{
		String rootName;
		String chordSuffix;

		if (chordName.length() > 1 && (chordName.substring(1,2).contains("#") || chordName.substring(1,2).contains("b")))
		{
			rootName = chordName.substring(0,2);
			chordSuffix = chordName.substring(2);
		}
		else
		{
			rootName = chordName.substring(0, 1);
			chordSuffix = chordName.substring(1);
		}

		Note root = new Note(rootName);

		this.notes = new ArrayList<>();

		if(chordName.contains("/") && !chordName.contains("6/9"))
		{
			String[] split = chordSuffix.split("/");
			slashRoot = new Note(split[1] + root.getOctave());
			if(slashRoot.getSemitonesFromLowestC() > root.getSemitonesFromLowestC())
			{
				slashRoot = slashRoot.setOctave(slashRoot.getOctave() - 1);
			}
			chordSuffix = split[0];

			notes.add(slashRoot);
		}

		ChordStructure chordStructure = ChordStructure.findBySuffix(chordSuffix);

		this.name = root.getName() + " " + chordStructure.getName();
		this.shortName = chordName;
		this.chordStructure = chordStructure;
		this.root = root;
		this.notes.addAll(chordStructure.getNotes(root));

	}

	private Chord(String name, String shortName, ChordStructure chordStructure, Note root, List<Note> notes)
	{
		this.name = name;
		this.shortName = shortName;
		this.chordStructure = chordStructure;
		this.root = root;
		this.notes = notes;
	}

	public Chord(Note root, ChordStructure chordStructure)
	{
		this.name = root.getName() + " " + chordStructure.getName();
		this.shortName = root.getName() + chordStructure.getSuffixes().get(0);
		this.chordStructure = chordStructure;
		this.root = root;
		this.notes = chordStructure.getNotes(root);
	}

	public Chord getInversion(int inversion)
	{
		if (inversion > notes.size())
		{
			throw new RuntimeException("Inversion: " + inversion + " is greater than number of notes in chord: " + name);
		}

		List<Note> newNotes = notes.stream().collect(Collectors.toList());

		for(int i = 0; i < inversion; i++)
		{
			Note note = newNotes.remove(0);
			note = note.addInterval(Interval.P8);
			newNotes.add(note);
		}

		String name = this.name + " " + Utils.getNumberName(inversion) + " Inversion";
		String shortName = this.shortName + "/" + newNotes.get(0).getName();
		return new Chord(name, shortName, chordStructure, root, newNotes);
	}

	public List<Chord> getInversions()
	{
		List<Chord> inversions = new ArrayList<>();
		inversions.add(this);
		for (int i = 1; i < notes.size(); i++)
		{
			inversions.add(getInversion(i));
		}
		return inversions;
	}

	public boolean matchesNotes(Collection<Note> testNotes)
	{
		if (testNotes == null || testNotes.isEmpty() || notes.size() != testNotes.size())
		{
			return false;
		}

		return testNotes.stream().allMatch(this::containsNoteWithoutOctave);
	}

	public boolean matchesNotesAndInversion(Collection<Note> testNotes)
	{
		if (testNotes == null || testNotes.isEmpty() || notes.size() != testNotes.size())
		{
			return false;
		}

		List<Note> sortedNotes = new ArrayList<>(testNotes);
		Collections.sort(sortedNotes);

		for (int i = 0; i < sortedNotes.size(); i++)
		{
			if (notes.get(i).getSemitonesAboveC() != sortedNotes.get(i).getSemitonesAboveC())
			{
				return false;
			}
		}

		return true;
	}

	public String getName()
	{
		return name;
	}

	public String getShortName()
	{
		return shortName;
	}

	public List<Note> getNotes()
	{
		return Collections.unmodifiableList(notes);
	}

	public List<Note> getNotesWithIntervalNames()
	{
		return chordStructure.getNotesWithIntervalNames(root);
	}

	private boolean containsNoteWithoutOctave(Note test)
	{
		return notes.stream().filter(note -> note.getSemitonesAboveC() == test.getSemitonesAboveC()).findAny().isPresent();
	}

	@Override public String toString()
	{
		return "Chord{" +
				"name='" + name + '\'' +
				", shortName='" + shortName + '\'' +
				", chordStructure=" + chordStructure +
				", root=" + root +
				", notes=" + notes +
				'}';
	}
}
