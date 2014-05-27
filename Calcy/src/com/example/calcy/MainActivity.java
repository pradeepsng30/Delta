package com.example.calcy;



import android.app.Activity;
//import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
//import android.os.Build;

public class MainActivity extends Activity {
TextView Disp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		Disp=(TextView)findViewById(R.id.textView1);
		Disp.setText("0");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {


		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
		
		
	}
	static boolean  lstequal=false,Err=false;
	static int accured=0;
	static short op=1,last=1;//1=+.2=-,3=x,4=/,5==,,,,, last,0=num,1=op
	static boolean isempty=true;
	
	
	public void num_clicked(View sender)
	{if(lstequal){Disp.setText("0");lstequal=false;accured=0;}
		Button bt=(Button)sender;
		if(Disp.getText().length()>10)return;
		
		if(isempty)
		{
			Disp.setText(bt.getText());
			isempty=false;
		}
		else{
			Disp.append(bt.getText());
		}
		last=0;
	}
	
	public void op_clicked(View sender){
		Button bt=(Button)sender;
		
		switch(op){
		
		case 1:
		case 2:
		case 3:
		case 4:if(last==1){if(bt.getText().toString().equals("+")){op=1;lstequal=false;}
							if(bt.getText().toString().equals("-")){op=2;lstequal=false;}
							if(bt.getText().toString().equals("x")){op=3;lstequal=false;}
							if(bt.getText().toString().equals("/")){op=4;lstequal=false;}
							return;
			  			}
		
		}
		
		
		
		
		
		//if(lstequal){Disp.setText("0");lstequal=false;accured=0;}
		
		switch(op){
		
		case 1:accured+=Integer.parseInt(Disp.getText().toString());break;
		case 2:accured-=Integer.parseInt(Disp.getText().toString());break;
		case 3:accured*=Integer.parseInt(Disp.getText().toString());break;
		case 4:if(Integer.parseInt(Disp.getText().toString())==0)Err=true;
				else accured/=Integer.parseInt(Disp.getText().toString());break;
		default:accured+=Integer.parseInt(Disp.getText().toString());
		}
		
		
		if(Err){Disp.setText("Math Error");Err=false;}
		else Disp.setText(Integer.toString(accured));
		
		
		if(bt.getText().toString().equals("+")){op=1;lstequal=false;}
		if(bt.getText().toString().equals("-")){op=2;lstequal=false;}
		if(bt.getText().toString().equals("x")){op=3;lstequal=false;}
		if(bt.getText().toString().equals("/")){op=4;lstequal=false;}
		if(bt.getText().toString().equals("=")){op=1;/*accured=0;*/lstequal=true;}
		isempty=true;
		last=1;
		
		
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

}
