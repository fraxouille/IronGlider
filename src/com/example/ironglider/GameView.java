package com.example.ironglider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


public class GameView extends View {
	
	Iron iron;
	Paint p = new Paint();
	float[] sensors = new float[3];

	
	public GameView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		p.setColor(Color.BLACK);
	}
	
	@Override
	protected void onDraw(Canvas c)
	{
		super.onDraw(c);
		c.drawBitmap(iron.ironbmp, iron.x, iron.y, iron.p);
		c.drawText("x=" + sensors[0] + "  y=" + sensors[1]+ "  z=" + sensors[2], 0, 20, p);	
		Log.i("REFRESH", "VIEW");	
	}
	
	public void registerObject(Iron i)
	{
		this.iron = i;
	}
	
	public void debug(float[] s)
	{
		sensors[0] = s[0];
		sensors[1] = s[1];
		sensors[2] = s[2];
	}
}
