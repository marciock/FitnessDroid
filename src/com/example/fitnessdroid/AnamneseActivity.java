package com.example.fitnessdroid;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class AnamneseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_anamnese);
		
		abreNovo();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_anamnese, menu);
		return true;
	}

	
	public void runActivity(Class<?> class1){
		Intent intent=new Intent(this,class1);
		startActivity(intent);
		finish();
	}
	
	public void abreNovo(){
		
		
		LinearLayout novAnamnese=(LinearLayout) findViewById(R.id.lanamnesenovo);
		
		novAnamnese.setClickable(true);
		novAnamnese.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			
			
			runActivity(com.example.fitnessdroid.ListUsuarioAnamneseActivity.class);
			
		}
	});
		
	}
	
}
