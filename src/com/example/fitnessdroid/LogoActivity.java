package com.example.fitnessdroid;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;

public class LogoActivity extends Activity implements Runnable{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logo);
		
		
		Handler handler=new Handler();
		handler.postDelayed(this, 3000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_logo, menu);
		return true;
	}

	public void run() {
		// TODO Auto-generated method stub
		startActivity(new Intent(this,com.example.fitnessdroid.HomeActivity.class));
		finish();
	}
	

}
