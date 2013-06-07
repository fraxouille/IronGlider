package com.example.ironglider;

import java.util.Timer;
import java.util.TimerTask;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;


public class Game extends Activity {
	
	public static enum GameState
	{
		launch, fly, ground, pause
	};
	
	public static GameState gameState;
	
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
		gameState = GameState.launch;
		Iron iron = new Iron(getResources());
		float[] launchLine = {150,iron.defaultY+25};
		sm = (SensorManager)getSystemService(android.content.Context.SENSOR_SERVICE);
		gameCode = new GameCode(gameView, iron, launchLine, sm);
		gameView.registerObject(iron, launchLine);
		
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
	
	@Override
	protected void onStart()
	{
		super.onStart();
		gameIsRunning = true;
	}
	
}
