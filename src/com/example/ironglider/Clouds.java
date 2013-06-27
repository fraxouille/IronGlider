package com.example.ironglider;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Clouds {

	private Bitmap[] cloudsbmp = new Bitmap[3];
	public float[] x = new float[3];
	public float[] y = new float[3];
	private Paint p = new Paint();
	private float speed = 5;
	
	public Clouds(Resources r)
	{
		cloudsbmp[0] = BitmapFactory.decodeResource(r, R.drawable.nuage1);
		cloudsbmp[1] = BitmapFactory.decodeResource(r, R.drawable.nuage2);
		cloudsbmp[2] = BitmapFactory.decodeResource(r, R.drawable.nuage3);
		for(int i = 0; i < 3; i++)
		{
			x[i] = (float) Math.random() * 400;
			y[i] = (float) Math.random() * 150 - 40;
		}
	}
	
	public void update()
	{
		for(int i = 0; i < 3; i++)
		{
			x[i] -= speed;
			if (x[i] < - cloudsbmp[i].getWidth())
			{
				x[i] = (float) Math.random() * 200 + 480;
				y[i] = (float) Math.random() * 150 - 40;
			}
		}		
	}	
	
	public void draw(Canvas c)
	{
		for (int i =0; i<3; i++)
		{
			c.drawBitmap(cloudsbmp[i], x[i], y[i], p);
		}
	}

}
