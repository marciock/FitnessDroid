package com.example.fitnessdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class ListGroupTreinoActivity extends Activity{
	private String idUsuario=null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_configura_exercicios);
		recebeGrupo();
		abrePeitorais();
		abreDorsais();
		abreBiceps();
		abreTriceps();
		abreDeltoides();
		abreAbdomen();
		abreAnteriores();
		abrePosteriores();
		
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.configura_exercicios, menu);
		return true;
	}
	
	public void recebeGrupo(){
		
		
		Bundle extras=getIntent().getExtras();
		
		if(extras!=null){
			idUsuario=extras.getString("usuario");
		}
		
	}
	
	
	public void runActivity(Class<?> class1){
		Intent intent=new Intent(this,class1);
		startActivity(intent);
		finish();
	}
	
		
	public void runEnviar(Class<?> class1,String receptor,String variavel){
		Intent intent=new Intent(getBaseContext(),class1);
		intent.putExtra(receptor, variavel);
		intent.putExtra("usuario", idUsuario);
		startActivity(intent);
		//finish();
	}
	
	
	
	
	public void abrePeitorais(){
		LinearLayout peitorais=(LinearLayout) findViewById(R.id.layoutpeitorais);
		
		peitorais.setClickable(true);
		peitorais.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			
			
			runEnviar(com.example.fitnessdroid.FormTreinoActivity.class,"grupo","1");
			
		}
	});
		
	}
	
	public void abreDorsais(){
		LinearLayout dorsais=(LinearLayout) findViewById(R.id.layoutdorsais);
		
		dorsais.setClickable(true);
		dorsais.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			
			
			runEnviar(com.example.fitnessdroid.FormTreinoActivity.class,"grupo","2");
			
		}
	});
		
	}
	
	public void abreBiceps(){
		LinearLayout biceps=(LinearLayout) findViewById(R.id.layoutbiceps);
		
		biceps.setClickable(true);
		biceps.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			
			
			runEnviar(com.example.fitnessdroid.FormTreinoActivity.class,"grupo","3");
			
		}
	});
		
	}
	
	public void abreTriceps(){
		LinearLayout triceps=(LinearLayout) findViewById(R.id.layouttriceps);
		
		triceps.setClickable(true);
		triceps.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			
			
			runEnviar(com.example.fitnessdroid.FormTreinoActivity.class,"grupo","4");
			
		}
	});
		
	}
	
	public void abreDeltoides(){
		LinearLayout deltoides=(LinearLayout) findViewById(R.id.layoutdeltoides);
		
		deltoides.setClickable(true);
		deltoides.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			
			
			runEnviar(com.example.fitnessdroid.FormTreinoActivity.class,"grupo","5");
			
		}
	});
		
	}
	
	public void abreAbdomen(){
		LinearLayout abdomen=(LinearLayout) findViewById(R.id.layoutabdomen);
		
		abdomen.setClickable(true);
		abdomen.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			
			
			runEnviar(com.example.fitnessdroid.FormTreinoActivity.class,"grupo","6");
			
		}
	});
		
	}
	public void abreAnteriores(){
		LinearLayout anteriores=(LinearLayout) findViewById(R.id.layoutanteriores);
		
		anteriores.setClickable(true);
		anteriores.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			
			
			runEnviar(com.example.fitnessdroid.FormTreinoActivity.class,"grupo","7");
			
		}
	});
		
	}
	public void abrePosteriores(){
		LinearLayout posteriores=(LinearLayout) findViewById(R.id.layoutposteriores);
		
		posteriores.setClickable(true);
		posteriores.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			
			
			runEnviar(com.example.fitnessdroid.FormTreinoActivity.class,"grupo","8");
			
		}
	});
		
	}

}
