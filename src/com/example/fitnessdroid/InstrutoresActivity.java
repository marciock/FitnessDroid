package com.example.fitnessdroid;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class InstrutoresActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instrutores);
		
		abreNovo();
		abreExcluir();
		abreEditar();
		abreListar();
		
	}
		
	

	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_instrutores, menu);
		return true;
	}

	public void runActivity(Class<?> class1){
		Intent intent=new Intent(this,class1);
		startActivity(intent);
	}
	public void runEnviar(Class<?> class1,String receptor,String variavel){
		Intent intent=new Intent(getBaseContext(),class1);
		intent.putExtra(receptor, variavel);
		startActivity(intent);
		
	}
	
	public void abreNovo(){
		LinearLayout novoIsntrutor=(LinearLayout) findViewById(R.id.linstrutoresnovo);
		
		novoIsntrutor.setClickable(true);
		novoIsntrutor.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				finish();
				runActivity(com.example.fitnessdroid.FormInstrutorActivity.class);
				
			}
		});
	}
		public void abreExcluir(){
			LinearLayout excluirInstrutor=(LinearLayout) findViewById(R.id.linstrutoresoexcluir);
			
			excluirInstrutor.setClickable(true);
			excluirInstrutor.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				
				runEnviar(com.example.fitnessdroid.FormExcluirActivity.class,"excluir","2");
				
			}
		});
			
		
		
	}
	
		public void abreEditar(){
			LinearLayout editarInstrutor=(LinearLayout) findViewById(R.id.linstrutoreseditar);
			
			editarInstrutor.setClickable(true);
			editarInstrutor.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				finish();
				runEnviar(com.example.fitnessdroid.FormAtualizarActivity.class,"atualizar","2");
				
			}
		});
			
		}
		public void abreListar(){
			LinearLayout listarInstrutor=(LinearLayout) findViewById(R.id.linstrutoreslistar);
			
			listarInstrutor.setClickable(true);
			listarInstrutor.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				
				runEnviar(com.example.fitnessdroid.FormListarActivity.class,"listar","2");
				
			}
		});
			
		}
	
}
