package com.example.ironglider;

import java.util.Timer;
import java.util.TimerTask;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;


public class Game extends Activity {

	final long FPS = 20;
	Timer timer = new Timer();
	GameCode gameCode;
	boolean gameIsRunning;
	SensorManager sm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_screen);
		GameView gameView = (GameView) findViewById(R.id.gameView);
		Iron iron = new Iron(getResources());
		sm = (SensorManager)getSystemService(android.content.Context.SENSOR_SERVICE);
		gameCode = new GameCode(gameView, iron, sm);
		gameView.registerObject(iron);
		
		gameIsRunning = true;
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		timer.schedule(new TimerTask()
		{
			public void run()
			{
				if (gameIsRunning)
				runOnUiThread(gameCode);
			}
		}, 0, 1000/FPS);
	}
	
	@Override
	protected void onPause()
	{
		super.onPause();
		gameIsRunning = false;
	}
	
}
