package com.example.ironglider;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.View;

public class GameCode implements Runnable, SensorEventListener {

	public static enum GameState
	{
		launch, fly, ground
	};
	
	GameState gameState;
	GameView gameView;
	Iron iron;
	SensorManager sm;
	Sensor acc;
	float[] sensors = new float[3];
	
	public GameCode(GameView v, Iron i, SensorManager sm)
	{
		this.gameView = v;
		this.iron = i;
		this.sm = sm;
		gameState = GameState.fly;
		sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
	}	

	public void onSensorChanged(SensorEvent event) {
		sensors[0] = event.values[0];
		sensors[1] = event.values[1];
		sensors[2] = event.values[2];
	}		

	public void onAccuracyChanged(Sensor sensor, int accuracy) {	}	
	
	public void run()
	{
		Update(gameView, iron);
		Draw();
	}
	
	private void Update(View view, Iron iron)
	{
		gameView.debug(sensors);
		switch (gameState)
		{
		case launch:{ break;}
		case fly:{
			iron.x++;
			iron.y += sensors[0];
			break;}
		case ground:{break;}
		}
	}
	
	private void Draw()
	{
		Log.i("DRAW", "DONE");
		gameView.invalidate();
	}
	
	public void onPause()
	{
		sm.unregisterListener(this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
	}
	
	public void onResume()
	{
		sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
	}

}
