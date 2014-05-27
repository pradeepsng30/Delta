package com.example.rhythmic;

import java.util.ArrayList;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.MenuItem;
import android.view.View;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

import com.example.rhythmic.MusicService.MusicBinder;

import android.util.*;
//import android.widget.MediaController.MediaPlayerControl;
import android.net.Uri;
import android.content.ContentResolver;
import android.database.Cursor;
import android.widget.ListView;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.MediaController.MediaPlayerControl;

public class MainActivity extends Activity implements  SensorEventListener, MediaPlayerControl  {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v13.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	private ArrayList<Song> songList;
	private ListView songView;
	SensorManager sm;
//	Sensor proxSensor,acc;
	//TextView ProxText,AccText;
	private MusicController controller;
	private MusicService musicSrv;
	private Intent playIntent;
	private boolean musicBound=false;
	private boolean paused=false, playbackPaused=false;
	
	//connect to the service
	private ServiceConnection musicConnection = new ServiceConnection(){

	  @Override
	  public void onServiceConnected(ComponentName name, IBinder service) {
	    MusicBinder binder = (MusicBinder)service;
	    //get service
	    musicSrv = binder.getService();
	    //pass list
	    musicSrv.setList(songList);
	    musicBound = true;
	  }

	  protected void onStart() {
	    //super.onStart();
	    if(playIntent==null){
	      playIntent = new Intent();
	      bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
	      startService(playIntent);
	    }
	  }
	  @Override
	  public void onServiceDisconnected(ComponentName name) {
	    musicBound = false;
	  }
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		songView=(ListView)findViewById(R.id.song_list);
		songList=new ArrayList<Song>();
		getSongList();
		
		Collections.sort(songList,new Comparator<Song>(){
			public int compare(Song a, Song b){
				return a.getTitle().compareToIgnoreCase(b.getTitle());}
		});
		//songView.s
		songList.add(new Song(000,"thisTitle:",":thisArtist"));
		SongAdapter songAdt=new SongAdapter(this,songList);
		try{
		songView.setAdapter(songAdt);
		}
	catch(Exception e)
	{}
		//songView.
		setController();
		
		//sm=(SensorManager)getSystemService(SENSOR_SERVICE);
		//proxSensor=sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		//sm.registerListener(this, proxSensor, SensorManager.SENSOR_DELAY_NORMAL);
		//acc=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		//sm.registerListener(this, acc, SensorManager.SENSOR_DELAY_NORMAL);
		
		//ProxText.setText("0");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_shuffle:
		//	  musicSrv.setShuffle();
			  break;
		  //shuffle
		
		case R.id.action_end:
		  stopService(playIntent);
		  musicSrv=null;
		  System.exit(0);
		  break;
		  
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	protected void onDestroy() {
	  stopService(playIntent);
	  musicSrv=null;
	  super.onDestroy();
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			return PlaceholderFragment.newInstance(position + 1);
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 1;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			//TextView textView = (TextView) rootView
			//		.findViewById(R.id.section_label);
			//textView.setText(Integer.toString(getArguments().getInt(
			//		ARG_SECTION_NUMBER)));
			return rootView;
		}
	}

	private void setController(){
		//set controller up
		controller=new MusicController(this);
		controller.setPrevNextListeners(new View.OnClickListener(){
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				playNext();
			}
		},new View.OnClickListener(){
			
			@Override
			public void onClick(View v) {
				playPrev();
				// TODO Auto-generated method stub
				
			}
		});
		controller.setMediaPlayer(this);
		controller.setAnchorView(findViewById(R.id.song_list));
		controller.setEnabled(true);
	}
	
	
	//play next
	private void playNext(){
		  musicSrv.playNext();
		  if(playbackPaused){
		    setController();
		    playbackPaused=false;
		  }
		  controller.show(0);
		}

		private void playPrev(){
		  musicSrv.playPrev();
		  if(playbackPaused){
		    setController();
		    playbackPaused=false;
		  }
		  controller.show(0);
		}
	public void songPicked(View view){
		  musicSrv.setSong(Integer.parseInt(view.getTag().toString()));
		  musicSrv.playSong();
		  
		  if(playbackPaused){
			    setController();
			    playbackPaused=false;
			  }
			  controller.show(0);
		}
	
	@Override
	protected void onResume(){
	  super.onResume();
	  if(paused){
	    setController();
	    paused=false;
	  }
	}
	
	@Override
	protected void onPause(){
	  super.onPause();
	  paused=true;
	}
	
	@Override
	protected void onStop() {
	  controller.hide();
	  super.onStop();
	}
	public void getSongList(){
		ContentResolver musicResolver = getContentResolver();
		Uri musicUri=android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		Cursor musicCursor=musicResolver.query(musicUri, null, null, null, null);
		
		
		if(musicCursor!=null && musicCursor.moveToFirst()){
			//get columns
			int titleColumn=musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE);
			int idColumn=musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media._ID);
			int artistColumn=musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media.ARTIST);
			//add songs to list
			do{
				long thisId =musicCursor.getLong(idColumn);
				String thisTitle=musicCursor.getString(titleColumn);
				String thisArtist=musicCursor.getString(artistColumn);
				songList.add(new Song(thisId,thisTitle,thisArtist));
			}while(musicCursor.moveToNext());
			}
			
			
		}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		/*
		try{ProxText.setText(event.sensor.getName().toString());
			AccText.setText("X: "+String.valueOf(event.values[0])+
							"\nY: "+String.valueOf(event.values[1])
							+"\nZ: "+String.valueOf(event.values[2]));
		}catch(Exception e)
		{
		// TODO Auto-generated method stub
		}
		*/
	}
	
	
	
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	public void onClick(View sender){
		
	}
	public void start_Clicked(View sender){
		//ProxText=(TextView)findViewById(R.id.textView1);
		//AccText=(TextView)findViewById(R.id.textView2);
	}

	@Override
	public void start() {
		musicSrv.go();		
	}

	@Override
	public void pause() {
		playbackPaused=true;
		  musicSrv.pausePlayer();
	}

	@Override
	public int getDuration() {
		if(musicSrv!=null && musicBound && musicSrv.isPng())
		    return musicSrv.getDur();
		  else return 0;
		}

	@Override
	public int getCurrentPosition() {
		if(musicSrv!=null && musicBound && musicSrv.isPng())
		    return musicSrv.getPosn();
		  else return 0;
	}

	@Override
	public void seekTo(int pos) {
		  musicSrv.seek(pos);		
	}

	@Override
	public boolean isPlaying() {
		if(musicSrv!=null && musicBound)
		    return musicSrv.isPng();
		  return false;
	}

	@Override
	public int getBufferPercentage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canPause() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canSeekBackward() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canSeekForward() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getAudioSessionId() {
		// TODO Auto-generated method stub
		return 0;
	}
}
