package com.example.ironglider;

import android.content.res.Resources;

public class GameContent {

	public Iron iron;
	public Ground ground;
	public Clouds clouds;
	public Background background;
	public float[] launchLine = new float[2];
	public static float fuel;
	
	
	public GameContent(Resources r)
	{
		iron = new Iron(r);
		ground = new Ground(r);
		clouds = new Clouds(r);
		background = new Background(r);
		launchLine[0] = 150;
		launchLine[1] = iron.defaultY+25;
	}
	
}
