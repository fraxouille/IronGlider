package com.example.ironglider;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;

public class Iron {

	public int defaultY = 120;
	public float x, y;
	public float xView, yView;
	public int width, height;
	Bitmap ironbmp;
	Paint p = new Paint();
	
	public Iron(Resources r)
	{
		x = 20;
		y = defaultY;
		yView = y;
		xView = x;
		ironbmp = BitmapFactory.decodeResource(r, R.drawable.iron);
		this.width = ironbmp.getWidth();
		this.height = ironbmp.getHeight();
	}
	
}
