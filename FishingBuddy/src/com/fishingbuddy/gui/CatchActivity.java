package com.fishingbuddy.gui;

import com.fishingbuddy.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CatchActivity extends Activity {
	static public String KEY_NAME = "KEY_NAME";
	static public String KEY_SWIM = "KEY_SWIM";
	static public String KEY_WEIGTH = "KEY_WEIGTH";
	static public String KEY_DESCRIPTION = "KEY_DESCRIPTION";
	private String name,swim,description,weigth;
	
	TextView tvname,tvswim,tvdescritpion,tvweigth;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_catch);
		Intent in = getIntent();
	       name = in.getExtras().getString(KEY_NAME);
	       swim = in.getExtras().getString(KEY_SWIM);
	       description = in.getExtras().getString(KEY_DESCRIPTION);
	       weigth = in.getExtras().getString(KEY_WEIGTH);
	       
	       tvname = (TextView) findViewById(R.id.tvNameAC);
	       tvswim = (TextView) findViewById(R.id.tvSwimAC);
	       tvdescritpion = (TextView) findViewById(R.id.tvDescriptionAC);
	       tvweigth = (TextView) findViewById(R.id.tvWeigthAC);
	       
	       tvname.setText(name);
	       tvswim.setText(swim);
	       tvdescritpion.setText(description);
	       tvweigth.setText(weigth);
	}
	

}
