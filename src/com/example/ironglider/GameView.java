package com.example.ironglider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class GameView extends View {
	
	// 480 * 320
	Iron iron;
	Ground ground;
	Clouds clouds;
	Background background;
	Paint paintBlack= new Paint();
	Paint paintWhite = new Paint();
	Paint paintRed = new Paint();
	Paint paintFuel = new Paint();
	float[] sensors = new float[3];
	float[] launchLine;
	int pauseTimer = 0;
	
	public GameView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		paintBlack.setColor(Color.BLACK);
		paintWhite.setColor(Color.WHITE);
		paintWhite.setTextSize(16);
		paintRed.setColor(Color.RED);
		paintRed.setStrokeWidth(4);
		paintFuel.setColor(Color.GREEN);
		paintFuel.setStrokeWidth(10);
		this.setOnTouchListener(clic);
	}
	
	@Override
	protected void onDraw(Canvas c)
	{
		super.onDraw(c);
		background.draw(c);
		ground.draw(c);
		clouds.draw(c);
		iron.draw(c);
    	c.drawLine(460, 250, 460, 250-GameContent.fuel, paintFuel);
		c.drawText("x=" + sensors[0] + "  y=" + sensors[1]+ "  z=" + sensors[2] + "Fuel="+ GameContent.fuel, 0, 20, paintBlack);

		switch (Game.gameState)
		{
			case launch:
			{
				c.drawLine(100, iron.defaultY+25, 100+launchLine[0], iron.defaultY+25-launchLine[1], paintRed);
				break;
			}
			case fly:
			{
		    	pauseTimer++;
				break;
			}
			case ground:
			{
				c.drawText("Vous avez parcouru "+iron.x +"m", 100, 100, paintBlack);
				break;
			}
			case pause:
			{
				c.drawRect(getWidth()/2 - 60, getHeight()/2-20, getWidth()/2 +60, getHeight()/2 +20, paintBlack);
				c.drawText("PAUSE", getWidth()/2 - 25, getHeight()/2 +5, paintWhite);
				pauseTimer++;
				break;
			}
		}
	}
	
	public void registerObject(GameContent g)
	{
		this.iron = g.iron;
		this.ground = g.ground;
		this.launchLine = g.launchLine;
		this.clouds = g.clouds;
		this.background = g.background;
	}
	
	public void debug(float[] s)
	{
		sensors[0] = s[0];
		sensors[1] = s[1];
		sensors[2] = s[2];
	}
	
	private OnTouchListener clic = new OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent e) {
	    	
	    	if (e.getAction() == MotionEvent.ACTION_DOWN)
	    	{
		    	switch (Game.gameState)
		    	{
		    	case launch : {Game.gameState = Game.GameState.fly; pauseTimer = 0; break;}
		    	case fly : {
		    		if (pauseTimer >=10 && e.getX() < 50 && e.getY() < 50)
		    			{Game.gameState = Game.GameState.pause; pauseTimer = 0; break;}
		    		else {
		    			if (GameContent.fuel > 0)
		    				iron.isSteamOn = true;
		    			else
		    				iron.isSteamOn = false;
		    		}
		    	}
		    	case ground : break;
		    	case pause : {
		    		if (pauseTimer >=10)
		    			{Game.gameState = Game.GameState.fly; pauseTimer=0; break;}}
		    	}
	    	}
	    	else if (e.getAction() == MotionEvent.ACTION_UP)
	    	{
	    		iron.isSteamOn = false;
	    	}
	    	return true;
	    }
	};
	
}
