package com.mikewerzen.music.domain;

public class Key
{
	private Scale scale;
	private Note root;

	public Key(Scale scale, Note root)
	{
		this.scale = scale;
		this.root = root;
	}

	public Scale getScale()
	{
		return scale;
	}

	public Note getRoot()
	{
		return root;
	}

	public String getName() {
		return root.getName() + scale.getSuffix();
	}
}
