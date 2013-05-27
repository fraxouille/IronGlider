package com.example.ironglider;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class GameCode implements Runnable {
	
	GameView gameView;
	Iron iron;
	SensorManager sm;
	Sensor acc;
	SensorEventListener sl = new SensorEventListener() {		
		@Override
		public void onSensorChanged(SensorEvent event) {
			float[] last = new float[3];
			gameView.updateSensors(last, event.values);
			Log.i("VALUES=", event.values[0] + " / " + event.values[1]);
			//Toast.makeText(gameView.getContext(), "VALUES=" + event.values[0] + " / " + event.values[1] , 1000).show();
		}		
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {			
		}
	};
	
	
	public GameCode(GameView v, Iron i, SensorManager sm)
	{
		this.gameView = v;
		this.iron = i;
		this.sm = sm;
		sm.registerListener(sl, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	public void run()
	{
		Update(gameView, iron);
		Draw();
	}
	
	private void Update(View view, Iron iron)
	{
		Log.i("UPDATE", "DONE");
		iron.x++;
	}
	
	private void Draw()
	{
		Log.i("DRAW", "DONE");
		gameView.invalidate();
	}

}
