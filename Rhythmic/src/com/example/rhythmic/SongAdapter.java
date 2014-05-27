package com.example.rhythmic;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class SongAdapter extends BaseAdapter {

	
	private ArrayList<Song> songs;
	private LayoutInflater songInf;
	
	
	public SongAdapter(Context c,ArrayList<Song> theSongs){
		songs=theSongs;
		songInf=LayoutInflater.from(c);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return songs.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//map to song layout
		LinearLayout songLay=(LinearLayout)songInf.inflate(R.layout.song,parent , false);
		//get title and artist
		TextView songView=(TextView)songLay.findViewById(R.id.song_tiltle);
		TextView artistView=(TextView)songLay.findViewById(R.id.song_artist);
		// get song using position
		Song currSong=songs.get(position);
		//get title ndartist string
		songView.setText(currSong.getTitle());
		artistView.setText(currSong.getArtist());
		//set position asa tag
		songLay.setTag(position);
		return songLay;
		
		
		
		
	//	return null;
	}

}
