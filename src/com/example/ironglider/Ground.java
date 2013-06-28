package com.example.ironglider;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Ground {

	private Bitmap groundBmp;
	public float x, y;
	public int width;
	Paint p = new Paint();
	
	public Ground(Resources r)
	{	
		x = 0;
		y = 240;
		groundBmp=BitmapFactory.decodeResource(r, R.drawable.grass);
		width = groundBmp.getWidth();	
	}
	
	public void update(float IronX, int XPositionOfFlyingIron)
	{
		if (IronX > XPositionOfFlyingIron)
		{
			x = - IronX + XPositionOfFlyingIron;
			if (x < - width)
				x -= (int)(x / width) * width;
		}
	}
	
	public void draw(Canvas c)
	{
		for (int i = 0; i < 3; i++)
		{
			c.drawBitmap(groundBmp, x + i * width, y, p);
		}
	}
	
}
