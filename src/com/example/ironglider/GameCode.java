package com.example.ironglider;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.View;

public class GameCode implements Runnable, SensorEventListener {
	
	GameView gameView;
	Iron iron;
	Ground ground;
	float[] launchLine;
	SensorManager sm;
	float[] sensors = new float[3];
	float launchAngle;
	float initialSpeed = 100f;
	boolean launching = false;
	float time = 0;
	
	public GameCode(GameView v, Iron i, Ground g, float[] l,SensorManager sm)
	{
		this.gameView = v;
		this.iron = i;
		this.ground = g;
		this.launchLine = l;
		this.sm = sm;
		sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
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
		switch (Game.gameState)
		{
		case launch:{
			launchAngle = -sensors[0]/10 + 0.8f;
			if (launchAngle > 1.3) launchAngle = 1.3f;
			if (launchAngle < 0) launchAngle = 0;
			
			launchLine[0] = 100*(float) Math.cos(launchAngle);
			launchLine[1] = 100*(float) Math.sin(launchAngle);
			Log.i("ANGLE", String.valueOf(launchAngle));
			break;}
		
		case fly:{
			iron.x = (float) (initialSpeed * Math.cos(launchAngle)) * time + 20;
			if (iron.x < 200)
				iron.xView = iron.x;
			else
				ground.x = - iron.x + 200;
			
			if (ground.x < - ground.width)
				ground.x -= (int)(ground.x / ground.width) * ground.width;
			
			iron.y = (float) -((-9.8/2 * time * time) + (initialSpeed * Math.sin(launchAngle) * time)) + iron.defaultY;
			iron.yView = iron.y;
			
			if (iron.y >= 220 - iron.height)
			{
				iron.y = 220;
				Game.gameState = Game.GameState.ground;
			}
			time+=0.2;	
			break;}
		
		case ground:{
			
			
			break;}
		
		case pause:{break;}
		}
	}
	
	private void Draw()
	{
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
