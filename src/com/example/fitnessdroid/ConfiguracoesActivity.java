package com.example.fitnessdroid;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class ConfiguracoesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_configuracoes);
		
		abreExercicios();
		abreRepertorio();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.configuracoes, menu);
		return true;
	}
	
public boolean onOptionsItemSelected(MenuItem item){
		
		if(item.getItemId()==R.id.menu_cancelar){
			sair();
		}
		if(item.getItemId()==R.id.menu_salvar){
			saveData();
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void runActivity(Class<?> class1){
		Intent intent=new Intent(this,class1);
		startActivity(intent);
		finish();
	}
	
	
	public void abreRepertorio(){
		
		LinearLayout myrepertorio=(LinearLayout) findViewById(R.id.lconfigrepertorio);
		
		myrepertorio.setClickable(true);
				
		myrepertorio.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				runActivity(com.example.fitnessdroid.ExercicesForSounds.class);
				
			}
		});
				
					
	}
	public void abreExercicios(){
		
		LinearLayout myExercicios=(LinearLayout) findViewById(R.id.lconfigexercicios);
		
		myExercicios.setClickable(true);
				
		myExercicios.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				runActivity(com.example.fitnessdroid.ConfiguraExerciciosActivity.class);
				
			}
		});
				
					
	}
	
	
	public void sair(){
		finish();
	}
	
	
	public void saveData(){
		
		
		
		mensagens(this,"Salvando Dados","Salvo com sucesso!\n Cadastrar outro?","Sim","Não");	
		
		//finish();
		
		
	}
	
	public void mensagens(Context context,String title,String message,String textP,String textN){
		
		AlertDialog.Builder builder=new AlertDialog.Builder(context);
		
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setNegativeButton(textN,new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
				runActivity(com.example.fitnessdroid.UsuariosActivity.class);
			}
		} );
		builder.setPositiveButton(textP,new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				finish();
				runActivity(com.example.fitnessdroid.FormUsuariosActivity.class);
			}
		});
		builder.show();
		
}
	
	
	

}
