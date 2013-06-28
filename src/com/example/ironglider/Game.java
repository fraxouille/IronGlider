package com.example.ironglider;

import java.util.Timer;
import java.util.TimerTask;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;


public class Game extends Activity {
	
	public static enum GameState
	{
		launch, fly, ground, pause, stop, restart
	};
	
	public static GameState gameState;
	
	final long FPS = 20;
	Timer timer = new Timer();
	GameContent g;
	GameCode gameCode;
	boolean gameIsRunning;
	SensorManager sm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_screen);

		sm = (SensorManager)getSystemService(android.content.Context.SENSOR_SERVICE);	
		GameView gameView = (GameView) findViewById(R.id.gameView);
		gameState = GameState.launch;
		gameIsRunning = true;
		
		g = new GameContent(getResources());
		gameCode = new GameCode(gameView, g, sm);
		gameView.registerObject(g);
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		timer.schedule(new TimerTask()
		{
			public void run()
			{
				if (gameState == GameState.stop)
				{
					gameIsRunning = false;
					finish();
				}
				if (gameState == GameState.restart)
				{
					gameIsRunning = false;
					finish();
					startActivity(getIntent());
				}
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
