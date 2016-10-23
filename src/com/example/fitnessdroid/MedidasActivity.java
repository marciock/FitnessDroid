package com.example.fitnessdroid;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class MedidasActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medidas);
		
		
		
		novaMedida();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_medidas, menu);
		return true;
	}

	
	
	public void runActivity(Class<?> class1){
		Intent intent=new Intent(this,class1);
		startActivity(intent);
	}
	
	public void novaMedida(){
		
		LinearLayout myNovaMedida=(LinearLayout) findViewById(R.id.lmedidasnovo);
		
		myNovaMedida.setClickable(true);
				
		myNovaMedida.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				runActivity(com.example.fitnessdroid.ListUsuarioMedidasActivity.class);
				
			}
		});
				
					
	}
	
	
}
