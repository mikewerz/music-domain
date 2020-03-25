package com.mikewerzen.music.domain;

public enum Letter
{
	C(0, 0),
	D(1, 2),
	E(2, 4),
	F(3, 5),
	G(4, 7),
	A(5, 9),
	B(6, 11);

	private final int index;
	private final int semitonesFromC;

	Letter(int index, int semitonesFromC)
	{
		this.index = index;
		this.semitonesFromC = semitonesFromC;
	}

	public int getSemitonesFromC()
	{
		return semitonesFromC;
	}

	public int getIndex()
	{
		return index;
	}
}
