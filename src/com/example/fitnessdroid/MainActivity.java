package com.example.fitnessdroid;

import com.example.fitnessdroid.DAO.conexaoDAO;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;


public class MainActivity extends Activity implements Runnable {
private conexaoDAO db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		db=new conexaoDAO(this);
		
		try {
			db.createDataBase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
		
		//openHome();
		
		Handler handler=new Handler();
		handler.postDelayed(this, 3000);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
		
	
	public void openHome(){
		setContentView(R.layout.activity_home);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		startActivity(new Intent(this,com.example.fitnessdroid.LogoActivity.class));
		finish();
	}
	
}
