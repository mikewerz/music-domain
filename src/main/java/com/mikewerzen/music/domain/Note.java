package com.mikewerzen.music.domain;

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
		this.octave = 4;
	}

	public Note(String name, Letter letter, int octave, int semitonesAboveC)
	{
		this.name = name;
		this.letter = letter;
		this.octave = octave;
		this.semitonesAboveC = semitonesAboveC;
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

		StringBuilder nameBuilder = new StringBuilder(noteLetter.name());

		while(accidentalsNeeded > 0)
		{
			nameBuilder.append(Constants.SHARP);
			accidentalsNeeded--;
		}

		while(accidentalsNeeded < 0)
		{
			nameBuilder.append(Constants.FLAT);
			accidentalsNeeded++;
		}

		noteSemitones = noteSemitones % Constants.SCALE_LENGTH;

		return new Note(nameBuilder.toString(), noteLetter, noteOctave, noteSemitones);


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

	public int getSemitonesFromLowestC() {
		return getSemitonesAboveC() + ((octave + 1) * 12);
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
