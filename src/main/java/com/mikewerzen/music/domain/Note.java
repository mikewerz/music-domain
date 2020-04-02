package com.mikewerzen.music.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Note
{
	private final String name;
	private final Letter letter;
	private final int octave;
	private final int semitonesAboveC;

	public Note(String name)
	{
		this.name = name;
		this.letter = Letter.valueOf(name.substring(0, 1));
		int accidentals = name.chars().map(c -> c == '#' ? 1 : c == 'b' ? -1 : 0).sum();
		this.semitonesAboveC = letter.getSemitonesFromC() + accidentals;
		this.octave = getOctaveOrDefault(name);;
	}

	public Note(String name, Letter letter, int octave, int semitonesAboveC)
	{
		this.name = name;
		this.letter = letter;
		this.octave = octave;
		this.semitonesAboveC = semitonesAboveC;
	}

	public Note(int semitonesAboveLowestC)
	{
		octave = Math.floorDiv(semitonesAboveLowestC, 12);
		semitonesAboveC = semitonesAboveLowestC % 12;

		letter = Arrays.stream(Letter.values())
				.filter(letter -> letter.getSemitonesFromC() <= semitonesAboveC)
				.reduce((f, s) -> s)
				.orElseThrow(() -> new RuntimeException("Could Not Find Letter"));

		name = letter.name() + getAccidentals(letter, semitonesAboveC);
	}

	private static int getOctaveOrDefault(String name)
	{
		String octave = name.substring(name.length() - 1);
		int oct = 4;
		try { oct = Integer.parseInt(octave); } catch (Exception e) { oct = 4; }
		return oct;
	}


	public Note addInterval(Interval interval)
	{
		int noteOctave = octave;
		int noteLetterIndex = letter.getIndex() + interval.getNumber().getIndex();

		if(noteLetterIndex >= Constants.LETTERS_LENGTH)
		{
			noteOctave += Math.floor(noteLetterIndex / Constants.LETTERS_LENGTH);
			noteLetterIndex = noteLetterIndex % Constants.LETTERS_LENGTH;
		}

		Letter noteLetter = Letter.values()[noteLetterIndex];

		int noteSemitones = semitonesAboveC + interval.getSemitones();
		int letterSemitones = noteLetter.getSemitonesFromC() + ((noteOctave - octave) * Constants.SCALE_LENGTH);
		int accidentalsNeeded = noteSemitones - letterSemitones;

		String name = noteLetter.name() + getAccidentals(noteLetter, accidentalsNeeded);

		noteSemitones = noteSemitones % Constants.SCALE_LENGTH;

		return new Note(name, noteLetter, noteOctave, noteSemitones);


	}

	public Note subtractInterval(Interval interval)
	{
		if(interval.getNumber().isCompound())
		{
			Note newNote = addInterval(interval.invert().invert());
			newNote = newNote.setOctave(newNote.octave - 2);
			return newNote;
		}

		Note newNote = addInterval(interval.invert());
		newNote = newNote.setOctave(newNote.octave - 1);
		return newNote;
	}

	private static String getAccidentals(Letter letter,  int desiredSemitonesAboveC)
	{
		return getAccidentals(letter.getSemitonesFromC(), desiredSemitonesAboveC);
	}

	private static String getAccidentals(int baseSemitones,  int desiredSemitonesAboveC)
	{
		int accidentalsNeeded = desiredSemitonesAboveC - baseSemitones;

		StringBuilder stringBuilder = new StringBuilder();
		while(accidentalsNeeded > 0)
		{
			stringBuilder.append(Constants.SHARP);
			accidentalsNeeded--;
		}

		while(accidentalsNeeded < 0)
		{
			stringBuilder.append(Constants.FLAT);
			accidentalsNeeded++;
		}

		return stringBuilder.toString();
	}

	public Optional<Note> getEnharmonicNote()
	{
		int enharmonicLetterIndex = getLetter().getIndex();

		if(name.contains("#"))
		{
			enharmonicLetterIndex++;
		}
		else if (name.contains("b"))
		{
			enharmonicLetterIndex--;
		}
		else
		{
			return Optional.empty();
		}

		if(enharmonicLetterIndex >= Constants.LETTERS_LENGTH)
		{
			enharmonicLetterIndex = enharmonicLetterIndex % Constants.LETTERS_LENGTH;
		}

		Letter enharmonicLetter = Letter.values()[enharmonicLetterIndex];

		String name = enharmonicLetter.name() + getAccidentals(enharmonicLetter, semitonesAboveC);

		return Optional.of(new Note(name, enharmonicLetter, octave, semitonesAboveC ));
	}

	public int getSemitonesFromLowestC() {
		return getSemitonesAboveC() + ((octave + 1) * 12);
	}

	@Override public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}
		Note note = (Note) o;
		return getSemitonesFromLowestC() == note.getSemitonesFromLowestC();
	}

	@Override public int hashCode()
	{
		return Objects.hash(getSemitonesFromLowestC());
	}

	public Note setOctave(int octave) {
		return new Note(name, letter, octave, semitonesAboveC);
	}

	public String getName()
	{
		return name;
	}

	public Letter getLetter()
	{
		return letter;
	}

	public int getOctave()
	{
		return octave;
	}

	public int getSemitonesAboveC()
	{
		return semitonesAboveC;
	}

	@Override public String toString()
	{
		return "Note{" +
				"name='" + name + '\'' +
				", letter=" + letter +
				", octave=" + octave +
				", semitonesAboveC=" + semitonesAboveC +
				'}';
	}
}
