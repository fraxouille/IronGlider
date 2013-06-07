package com.example.ironglider;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


public class GameView extends View {
	
	// 480 * 320
	Iron iron;
	Paint paintBlack= new Paint();
	Paint paintWhite = new Paint();
	Paint paintRed = new Paint();
	Paint paintBrown = new Paint();
	float[] sensors = new float[3];
	float[] launchLine;
	Bitmap bmp;

	
	public GameView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		paintBlack.setColor(Color.BLACK);
		paintWhite.setColor(Color.WHITE);
		paintWhite.setTextSize(16);
		paintRed.setColor(Color.RED);
		paintRed.setStrokeWidth(4);
		paintBrown.setColor(Color.rgb(150, 90, 10));
		paintBrown.setStrokeWidth(10);
		this.setOnClickListener(clic);
		bmp = iron.ironbmp;
	}
	
	@Override
	protected void onDraw(Canvas c)
	{
		super.onDraw(c);
		
		bmp = RotateBitmap(bmp, 10f);
		
		
		c.drawBitmap(bmp/*iron.ironbmp*/, iron.x, iron.y, iron.p);
		c.drawText("x=" + sensors[0] + "  y=" + sensors[1]+ "  z=" + sensors[2], 0, 20, paintBlack);	
		c.drawLine(0, 220, 500, 220, paintBrown);

		switch (Game.gameState)
		{
			case launch:
			{
				c.drawLine(100, iron.defaultY+25, 100+launchLine[0], iron.defaultY+25-launchLine[1], paintRed);
				break;
			}
			case fly:
			{
				
				break;
			}
			case ground :
			{
				
				break;
			}
			case pause :
			{
				c.drawRect(getWidth()/2 - 60, getHeight()/2-20, getWidth()/2 +60, getHeight()/2 +20, paintBlack);
				c.drawText("PAUSE", getWidth()/2 - 25, getHeight()/2 +5, paintWhite);
				break;
			}
		}
	}
	
	public void registerObject(Iron i, float[] l)
	{
		this.iron = i;
		this.launchLine = l;
	}
	
	public void debug(float[] s)
	{
		sensors[0] = s[0];
		sensors[1] = s[1];
		sensors[2] = s[2];
	}
	
	public static Bitmap RotateBitmap(Bitmap source, float angle)
	{
	      Matrix matrix = new Matrix();
	      matrix.postRotate(angle);
	      return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
	}
	
	private OnClickListener clic = new OnClickListener() {
	    public void onClick(View v) {
	    	switch (Game.gameState)
	    	{
	    	case launch : {Game.gameState = Game.GameState.fly; break;}
	    	case fly : {Game.gameState = Game.GameState.pause; break;}
	    	case ground : break;
	    	case pause : {Game.gameState = Game.GameState.fly; break;}
	    	}

	    }
	};
	
}
