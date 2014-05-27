package com.example.test;

import org.scilab.modules.javasci.JavasciException.InitializationException;
import org.scilab.modules.javasci.Scilab;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Build;

//import org.scilab.modules.javasci.*;

public class MainActivity extends ActionBarActivity implements SensorEventListener {
	static float x,y,z;
	SensorManager sm;
	Sensor proxSensor,acc;
	TextView Text1,Text2;
	View view2=null;
	Scilab scix=null,v,sciy=null,sciz=null;
	
	
	@Override
	
	
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setContentView(R.layout.activity_main);
		
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		sm=(SensorManager)getSystemService(SENSOR_SERVICE);
		proxSensor=sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		sm.registerListener(this, proxSensor, SensorManager.SENSOR_DELAY_NORMAL);
		acc=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sm.registerListener(this, acc, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		Scilab sci=null;
		
		if(event.sensor.getName().toString().contains("Acc"))
		{scix.exec("plot(x)");
		sciy.exec("plot(y)");
		sciz.exec("plot(z)");
		}
		
		
		else
		{//if(event.values[0]==0)
			//play(view2);
			//SystemClock.sleep(25);
		
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

}
