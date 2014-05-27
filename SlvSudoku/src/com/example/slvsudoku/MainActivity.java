package com.example.slvsudoku;

import java.io.IOException;
import java.util.Locale;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

@SuppressWarnings("unused")
public class MainActivity extends ActionBarActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	static EditText[][] a=new EditText[9][9];
	TextView text;
	
	public void onclicktext(View sender){
		EditText text=(EditText)sender;
		text.selectAll();
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
///
		 
		
		
		
		
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

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
			return 3;
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
			TextView textView = (TextView) rootView
					.findViewById(R.id.section_label);
			textView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return rootView;
		}
	}
	
	 public int selectbox(int x,int y)//returns the box no.
	  {
	      if(x<3)
	      {
	          if(y<3)return 1;
	          if(y<6)return 2;
	          if(y<9)return 3;
	      }
	      if(x<6)
	      {
	          if(y<3)return 4;
	          if(y<6)return 5;
	          if(y<9)return 6;
	      }
	      if(x<9)
	      {
	          if(y<3)return 7;
	          if(y<6)return 8;
	          if(y<9)return 9;
	      }
	      return 0;
	  }
	 public boolean box( Integer n,int b,int i,int j)//before start//checks whether n is present in box called for a[i][j]
		{
			int I=0,J=0;
		      switch(b)
		      {
		          case 1:I=J=0;break;
		          case 2:I=0;J=3;break;
		          case 3:I=0;J=6;break;
		          case 4:I=3;J=0;break;
		          case 5:I=3;J=3;break;
		          case 6:I=3;J=6;break;
		          case 7:I=6;J=0;break;
		          case 8:I=6;J=3;break;
		          case 9:I=6;J=6;break;
		          default: return false;    
		              
		      }
		      
		      int x,y;
		      for(x=I;x<I+3;x++)
		          for(y=J;y<J+3;y++)
		              if(x!=i)
		            	  if(y!=j)
		            		  if(n.toString().equals(a[x][y].getText().toString()))return true;
		      
		      
		      return false;
			
			
		}
	 
	 
	 
	public boolean box( Integer n,int b)//checks whether n is present in box b
	{
		int I=0,J=0;
	      switch(b)
	      {
	          case 1:I=J=0;break;
	          case 2:I=0;J=3;break;
	          case 3:I=0;J=6;break;
	          case 4:I=3;J=0;break;
	          case 5:I=3;J=3;break;
	          case 6:I=3;J=6;break;
	          case 7:I=6;J=0;break;
	          case 8:I=6;J=3;break;
	          case 9:I=6;J=6;break;
	          default: return false;    
	              
	      }
	      
	      int x,y;
	      for(x=I;x<I+3;x++)
	          for(y=J;y<J+3;y++)
	              if(n.toString().equals(a[x][y].getText().toString()))return true;
	      
	      
	      return false;
		
		
	}
	public boolean check(int x,int y, Integer n,int h)//checks before start... false if its present two times
	{int i,j;String a1,a2;
		//checks row
		for(i=x,j=0;j<9;j++ )
			if(j!=y){a1=a[i][j].getText().toString();a2=n.toString();
				if(a1.equals(a2))return false;
			}
		//checks column
		for(j=y,i=0;i<9;i++ )
			if(i!=x)
				if(a[i][j].getText().toString().equals(n.toString()))return false;
		// check box
		
		return !box(n,selectbox(x,y),x,y);
	}
	
	public boolean check(int x,int y, Integer n)//false if its presnt in r,c,b
	{int i,j;String a1,a2;
		//checks row
		for(i=x,j=0;j<9;j++ )
			if(j!=y){a1=a[i][j].getText().toString();a2=n.toString();
				if(a1.equals(a2))return false;
			}
		//checks column
		for(j=y,i=0;i<9;i++ )
			if(i!=x)
				if(a[i][j].getText().toString().equals(n.toString()))return false;
		// check box
		
		return !box(n,selectbox(x,y));
	}
	public int getnum(char c)
	{switch(c){
		case '1':return 1;
		case '2':return 2;
		case '3':return 3;
		case '4':return 4;
		case '5':return 5;
		case '6':return 6;
		case '7':return 7;
		case '8':return 8;
		case '9':return 9;
		case '0':return 0;
		}	 
		return 0;
	
	}
	
	public int check_given()//0 no slution.... 1= solution
	{int i,j;Integer n;
		for(i=0;i<9;i++)
			for(j=0;j<9;j++)
				if(a[i][j].getText().toString().length()>0)
				{n=getnum(a[i][j].getText().toString().charAt(0));
					if(check(i,j,n,0));//n has a pair
					else return 0;
				}
		return 1;
	}
	
	
	Integer m=100;
	@SuppressLint("NewApi")
	public int start(int i,int j)//1=done at i,j,  0= no value can be put,2=end 
	{/*
		text.setText("inside start");
		Integer n=1;
		if(a[i][j].toString().isEmpty())
		a[i][j].setText(n.toString());
		*/
		//taking care of i and j
		
		
		if(j>8){i++;j=0;}
		
		if(i>8)return 1;
		
		if(a[i][j].toString().equals("0"))a[i][j].setText("");
		//if i,j is filled
		text.setText(m.toString());
		if(a[i][j].length()<1)
		{m++;text.setText(m.toString());
		Integer n;m++;text.setText(m.toString());
			for( n=1;n<10;n++)
			{m=i*10+j;
			text.setText(m.toString());
				if(check(i,j,n))//checks whether n is not in row nor in column and even not in box
					{
					a[i][j].setText(n.toString());
					if(start(i,j+1)==1)break;
					
					}
			}
			if(n<10)return 1;
			else {a[i][j].setText("");return 0;}
			
		}
		else {//a[i][j].setTextColor(0x7f00);
		return start(i,j+1);}
				//return 1;
	}
	public void solveclicked(View sender)throws IOException,NullPointerException, InterruptedException
	{//R.id.EditText01;
	text=(TextView)findViewById(R.id.textView1);
	text.setText("Solving..");
	//text.wait(2000);
	//System.out.print("Solving..\n");
		try{
	a[0][0]=(EditText)findViewById(R.id.EditText73);
	a[0][1]=(EditText)findViewById(R.id.EditText74);
	a[0][2]=(EditText)findViewById(R.id.EditText75);
	a[0][3]=(EditText)findViewById(R.id.EditText76);
	a[0][4]=(EditText)findViewById(R.id.EditText77);
	a[0][5]=(EditText)findViewById(R.id.EditText78);
	a[0][6]=(EditText)findViewById(R.id.EditText79);
	a[0][7]=(EditText)findViewById(R.id.EditText80);
	a[0][8]=(EditText)findViewById(R.id.EditText81);
	
	a[1][0]=(EditText)findViewById(R.id.EditText64);
	a[1][1]=(EditText)findViewById(R.id.EditText65);
	a[1][2]=(EditText)findViewById(R.id.EditText66);
	a[1][3]=(EditText)findViewById(R.id.EditText67);
	a[1][4]=(EditText)findViewById(R.id.EditText68);
	a[1][5]=(EditText)findViewById(R.id.EditText69);
	a[1][6]=(EditText)findViewById(R.id.EditText70);
	a[1][7]=(EditText)findViewById(R.id.EditText71);
	a[1][8]=(EditText)findViewById(R.id.EditText72);
	
	a[2][0]=(EditText)findViewById(R.id.EditText55);
	a[2][1]=(EditText)findViewById(R.id.EditText56);
	a[2][2]=(EditText)findViewById(R.id.EditText57);
	a[2][3]=(EditText)findViewById(R.id.EditText58);
	a[2][4]=(EditText)findViewById(R.id.EditText59);
	a[2][5]=(EditText)findViewById(R.id.EditText60);
	a[2][6]=(EditText)findViewById(R.id.EditText61);
	a[2][7]=(EditText)findViewById(R.id.EditText62);
	a[2][8]=(EditText)findViewById(R.id.EditText63);
	
	a[3][0]=(EditText)findViewById(R.id.EditText46);
	a[3][1]=(EditText)findViewById(R.id.EditText47);
	a[3][2]=(EditText)findViewById(R.id.EditText48);
	a[3][3]=(EditText)findViewById(R.id.EditText49);
	a[3][4]=(EditText)findViewById(R.id.EditText50);
	a[3][5]=(EditText)findViewById(R.id.EditText51);
	a[3][6]=(EditText)findViewById(R.id.EditText52);
	a[3][7]=(EditText)findViewById(R.id.EditText53);
	a[3][8]=(EditText)findViewById(R.id.EditText54);
	
	a[4][0]=(EditText)findViewById(R.id.EditText37);
	a[4][1]=(EditText)findViewById(R.id.EditText38);
	a[4][2]=(EditText)findViewById(R.id.EditText39);
	a[4][3]=(EditText)findViewById(R.id.EditText40);
	a[4][4]=(EditText)findViewById(R.id.EditText41);
	a[4][5]=(EditText)findViewById(R.id.EditText42);
	a[4][6]=(EditText)findViewById(R.id.EditText43);
	a[4][7]=(EditText)findViewById(R.id.EditText44);
	a[4][8]=(EditText)findViewById(R.id.EditText45);
	
	a[5][0]=(EditText)findViewById(R.id.EditText28);
	a[5][1]=(EditText)findViewById(R.id.EditText29);
	a[5][2]=(EditText)findViewById(R.id.EditText30);
	a[5][3]=(EditText)findViewById(R.id.EditText31);
	a[5][4]=(EditText)findViewById(R.id.EditText32);
	a[5][5]=(EditText)findViewById(R.id.EditText33);
	a[5][6]=(EditText)findViewById(R.id.EditText34);
	a[5][7]=(EditText)findViewById(R.id.EditText35);
	a[5][8]=(EditText)findViewById(R.id.EditText36);
	
	a[6][0]=(EditText)findViewById(R.id.EditText19);
	a[6][1]=(EditText)findViewById(R.id.EditText20);
	a[6][2]=(EditText)findViewById(R.id.EditText21);
	a[6][3]=(EditText)findViewById(R.id.EditText22);
	a[6][4]=(EditText)findViewById(R.id.EditText23);
	a[6][5]=(EditText)findViewById(R.id.EditText24);
	a[6][6]=(EditText)findViewById(R.id.EditText25);
	a[6][7]=(EditText)findViewById(R.id.EditText26);
	a[6][8]=(EditText)findViewById(R.id.EditText27);
	
	a[7][0]=(EditText)findViewById(R.id.EditText10);
	a[7][1]=(EditText)findViewById(R.id.EditText11);
	a[7][2]=(EditText)findViewById(R.id.EditText12);
	a[7][3]=(EditText)findViewById(R.id.EditText13);
	a[7][4]=(EditText)findViewById(R.id.EditText14);
	a[7][5]=(EditText)findViewById(R.id.EditText15);
	a[7][6]=(EditText)findViewById(R.id.EditText16);
	a[7][7]=(EditText)findViewById(R.id.EditText17);
	a[7][8]=(EditText)findViewById(R.id.EditText18);
	
	a[8][0]=(EditText)findViewById(R.id.EditText01);
	a[8][1]=(EditText)findViewById(R.id.EditText02);
	a[8][2]=(EditText)findViewById(R.id.EditText03);
	a[8][3]=(EditText)findViewById(R.id.EditText04);
	a[8][4]=(EditText)findViewById(R.id.EditText05);
	a[8][5]=(EditText)findViewById(R.id.EditText06);
	a[8][6]=(EditText)findViewById(R.id.EditText07);
	a[8][7]=(EditText)findViewById(R.id.EditText08);
	a[8][8]=(EditText)findViewById(R.id.EditText09);
		
	text.setText("Assigning done..\n");	
	
		/*Integer i,j;
		for(i=0;i<9;i++)
			for(j=0;j<9;j++)
				a[i][j].setText("0");*/
	
	int flag;
		flag=check_given();
		if(flag!=0)flag=start(0,0);
		if(flag==0)text.setText("No Solution ! !");
	else if(flag==1)text.setText("Solved");
		//else if(flag==2)text.setText("Solved :)");
		}
		catch(Exception e)
		{Log.e("err", e.toString());
		e.printStackTrace();
		
		}
		
		
	
	
	}
	
	public void resetclicked(View view)
	{int i,j;text.setText("Ready !");
	try{for(i=0;i<9;i++)
		for(j=0;j<9;j++)
			a[i][j].setText("");
	}catch(Exception e)
	{Log.e("err", e.toString());
	e.printStackTrace();
	
	}}
	
	
	/*public void reset_clicked(View view)
	{
	int	
	}*/

}
