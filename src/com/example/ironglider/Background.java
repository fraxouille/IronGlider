package com.example.ironglider;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Background {

	private Bitmap background; 
	private Paint p = new Paint();
	
	public Background(Resources r)
	{
		background = BitmapFactory.decodeResource(r, R.drawable.bg);
	}
	
	public void draw(Canvas c)
	{
		c.drawBitmap(background, 0, 0, p);
	}
	
}
