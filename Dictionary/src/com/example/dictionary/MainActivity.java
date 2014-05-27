package com.example.dictionary;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
    public final static String EXTRA_MESSAGE = "com.example.ditionary.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/** Called when the user clicks the Send button 
	 * @throws IOException */
	public void sendMessage(View view) throws IOException {
		Intent intent = new Intent(this, Result.class);
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String message = editText.getText().toString();
		
		
		
		String search=null,word;
		

	    //DataOutputStream d =new DataOutputStream(new FileOutputStream("C:\\Users\\MYPC\\Documents\\test.txt")); 
	    //DataOutputStream e=new DataOutputStream(new FileOutputStream("test1.txt"));
	    word=message;
		int i=0;
	
	   do
	   {
	   //     if(search.equals(word)){d.close(); cout("found");return 1;}
		   if(word.compareTo("g")<0)
		   search=Wordlist.WORD_LIST[i++];
		   else if(word.compareTo("r")<0)
			   search=Wrdlisten.WORD_LIST[i++];
		   else  search=Wordlist3.WORD_LIST[i++];
	      //search=d.readLine();
	      
	      
	      if(search.equalsIgnoreCase(word)){ message="\'" + word + "\' is a Correct Word";break;}
	      if(search.compareTo(word)>0)break;
	   }while(!search.equalsIgnoreCase("000"));
	    
		 
	    
	    if(message.equalsIgnoreCase("\'" + word + "\' is a Correct Word"));
	    else message="\'" + word+ "\' is a Incorrect Word";
		
		
		
		
		
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	    // Do something in response to button
	}
	

}
