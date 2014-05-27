package com.example.hello_world;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	int k=1;
	TextView text;
	char text2[];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		 
        
	}
	public void Start()
	{k++;
		 text= (TextView) findViewById(R.id.textView1);
	        text2=text.getText().toString().toCharArray();
	       
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

	
	public void jumble_clicked(View view)
    {
		
		if(k==1)Start();
    String a = null;char c;
    
    text2=text.getText().toString().toCharArray();
    
    //1 index ahead
    
    c=text2[text2.length-1];
    
    for(int i=text2.length-1;i>2;i--)
    	text2[i]=text2[i-1];
    
    text2[2]=c;
    //reverse the text
    for (int i=0;i<text2.length/2;i++)
    {c=text2[i];
     text2[i]=text2[text2.length-1-i];
     text2[text2.length-1-i]=c;
    }
    
    //replace d by e and vice versa
    a=String.copyValueOf(text2);
    a=a.replace('d','z');
    a=a.replace('e','d');
    a=a.replace('z','e');
   
    
    text.setText(a);
    
    }
}
