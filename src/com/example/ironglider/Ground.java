package com.example.ironglider;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Ground {

	public float x, y;
	public int width;
	Bitmap groundBmp;
	
	public Ground(Resources r)
	{
		x = 0;
		y = 220;

		groundBmp=BitmapFactory.decodeResource(r, R.drawable.grass);
		width = groundBmp.getWidth();	
	}
	
}
