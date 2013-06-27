package com.example.ironglider;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class GameCode implements Runnable, SensorEventListener {
	
	GameView gameView;
	Iron iron;
	final int XPositionOfFlyingIron = 150;
	Ground ground;
	Clouds clouds;
	Background background;
	float[] launchLine;
	SensorManager sm;
	float[] sensors = new float[3];
	float launchAngle;
	float initialSpeed;
	float time;
	
	public GameCode(GameView v, GameContent g,SensorManager sm)
	{
		GameContent.fuel = 100;
		this.gameView = v;
		this.iron = g.iron;
		this.ground = g.ground;
		this.launchLine = g.launchLine;
		this.clouds = g.clouds;
		this.background = g.background;
		initialSpeed = 100f;
		time = 0;
		
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
		Update();
		Draw();
	}
	
	private void Update()
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

			break;}
		
		case fly:{		
			iron.update(initialSpeed, launchAngle, XPositionOfFlyingIron, time);
			ground.update(iron.x, XPositionOfFlyingIron);
			clouds.update();
			
			time+=0.2;
			break;}
		
		case ground:{
			
			
			break;}
		
		case pause:{break;}
		default:{break;}
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
