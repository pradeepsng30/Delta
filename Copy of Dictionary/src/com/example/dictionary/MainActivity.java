package com.example.dictionary;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
    
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		//final String EXTRA_MESSAGE = "com.example.ditionary.MESSAGE";

		
	}

	
	EditText Text = (EditText) findViewById(R.id.editText1);
	float num1=0,num2=0,rslt=0;int a=1;char oprtn='0';
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	/** Called when the user clicks the Send button 
	 * @throws IOException */
	
	public void one(View view) throws IOException {
		compute('1');
	}
	
	public void two(View view) throws IOException {
		compute('2');
	}
	
	public void three(View view) throws IOException {
		compute('3');
	}
	public void four(View view) throws IOException {
		compute('4');
	}
	
	public void five(View view) throws IOException {
		compute('5');
	}
	
	public void six(View view) throws IOException {
		compute('6');
	}
	
	public void seven(View view) throws IOException {
		compute('7');
	}
	
	public void eight(View view) throws IOException {
		compute('8');
	}
	
	public void nine(View view) throws IOException {
		compute('9');
	}
	
	public void zero(View view) throws IOException {
		compute('0');
	}
	
	public void plus(View view) throws IOException {
		compute('+');
	}
	
	public void minus(View view) throws IOException {
		compute('-');
	}
	
	public void multiply(View view) throws IOException {
		compute('x');
	}
	
	public void divide(View view) throws IOException {
		compute('/');
	}
	
	
	public void equal(View view) throws IOException {
		compute('=');
	}
	public void buildno(int n){
		if(n<10)
		if(a==1)num1=num1*10+n;
		else num2=num2*10+n;
		
		if(n==10)
		{
			if(a==2){num1=getresult();
						num2=0;}
			else a=2;
			
		}
		
		if(n==11){
			if(a==2){rslt=getresult();a=1;}
			else rslt=num1;
		}
			
		
	}
	
	
	public float getresult()
	{
		switch(oprtn){
		case'+':rslt=num1+num2;
		case'-':rslt=num1-num2;
		case'x':rslt=num1*num2;
		case'/':rslt=num1/num2;
		case'0':rslt=num1;
		}
		oprtn='0';
		num1=num2=0;
		return rslt;
		
	}
	
	public void compute(char c)
	{
		switch(c)
		{
		case'1':buildno(1);Text.append(null, '1', c);break;
		case'2':buildno(2);Text.append(null, '2', c);break;
		case'3':buildno(3);Text.append(null, '3', c);break;
		case'4':buildno(4);Text.append(null, '4', c);break;
		case'5':buildno(5);Text.append(null, '5', c);break;
		case'6':buildno(6);Text.append(null, '6', c);break;
		case'7':buildno(7);Text.append(null, '7', c);break;
		case'8':buildno(8);Text.append(null, '8', c);break;
		case'9':buildno(9);Text.append(null, '9', c);break;
		case'0':buildno(0);Text.append(null, '0', c);break;
		case'+':buildno(10);Text.append(null, '+', c);if(a==1)oprtn=c;break;
		case'-':buildno(10);Text.append(null, '-', c);if(a==1)oprtn=c;break;
		case'x':buildno(10);Text.append(null, 'x', c);if(a==1)oprtn=c;break;
		case'/':buildno(10);Text.append(null, '/', c);if(a==1)oprtn=c;break;
		case'=':buildno(11);rslt=getresult();Text.setText(getString((int) rslt));a=1;
				
		}
		
		Text.setText(Text.getText());
	}
	/*
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
	
*/
}
