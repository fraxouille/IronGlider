package com.example.ironglider;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;

public class Iron {

	float x, y;
	Bitmap ironbmp;
	Paint p = new Paint();
	
	public Iron(Resources r)
	{
		x = 100;
		y = 50;
		ironbmp = BitmapFactory.decodeResource(r, R.drawable.iron);
	}
	
}
