package com.example.ironglider;

import com.example.ironglider.GameCode.GameState;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


public class GameView extends View {
	
	// 480 * 320
	Iron iron;
	Paint p = new Paint();
	float[] sensors = new float[3];
	float[] launchLine;

	
	public GameView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		p.setColor(Color.BLACK);
		this.setOnClickListener(clic);
	}
	
	@Override
	protected void onDraw(Canvas c)
	{
		super.onDraw(c);
		c.drawBitmap(iron.ironbmp, iron.x, iron.y, iron.p);
		c.drawText("x=" + sensors[0] + "  y=" + sensors[1]+ "  z=" + sensors[2], 0, 20, p);	
		Log.i("REFRESH", "VIEW");
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
	
	private OnClickListener clic = new OnClickListener() {
	    public void onClick(View v) {
	      //if (gameState == GameState.launch)
	    	//  gameState = GameState.fly;
	    }
	};
}
