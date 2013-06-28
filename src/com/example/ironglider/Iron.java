package com.example.ironglider;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Iron {

	private Bitmap ironbmp, steambmp;
	public final int defaultX = -50;
	public final int defaultY = 220;
	private final int groundHeight = 300;
	public float x, y;
	private float verticalSpeed;
	public float xView, yView;
	public int width, height;
	private Paint p = new Paint();
	public boolean isSteamOn = false;
	private boolean firstStart = true;
	
	public Iron(Resources r)
	{
		x = defaultX;
		y = defaultY;
		yView = y;
		xView = x;
		verticalSpeed = 0f;
		ironbmp = BitmapFactory.decodeResource(r, R.drawable.iron);
		this.width = ironbmp.getWidth();
		this.height = ironbmp.getHeight();
		steambmp = BitmapFactory.decodeResource(r, R.drawable.vapeur);
	}

	public void update(float initialSpeed, float launchAngle, int XPositionOfFlyingIron, float time)
	{
		if (isSteamOn && GameContent.fuel > 0)
		{
			GameContent.fuel -= 1;
			verticalSpeed -=0.5;
		}

		x = (float) (initialSpeed * Math.cos(launchAngle)) * time + defaultX;
		if (x < XPositionOfFlyingIron)
			xView = x;	

		if (firstStart)
		{
			y = defaultY;
			verticalSpeed = -(float) (initialSpeed * Math.sin(launchAngle))/8;
			firstStart = false;
		}	
		else
		{
			verticalSpeed += 0.2;
			y += verticalSpeed;
		}
		
/*		y = (float) -((-gravity/2 * time * time)
				+ (initialSpeed * Math.sin(launchAngle) * time)*0	)
				+ defaultY;
*/				
		yView = y;
		
		if (y >= groundHeight - height)
		{
			y = groundHeight - height;
			yView = y;
			Game.gameState = Game.GameState.ground;
		}
		
	}
	
	public void draw(Canvas c)
	{
		if (isSteamOn && GameContent.fuel > 0)
			c.drawBitmap(steambmp, xView + 62, yView + 50, p);		
		c.drawBitmap(ironbmp, xView, yView, p);
	}
	
}
