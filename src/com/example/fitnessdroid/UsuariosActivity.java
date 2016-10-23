package com.example.fitnessdroid;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.app.Activity;
import android.content.Intent;


public class UsuariosActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_usuarios);
	
		abreNovo();
		abreExcluir();
		abreEditar();
		abreListar();
	}
	
	
	public void runActivity(Class<?> class1){
		Intent intent=new Intent(this,class1);
		startActivity(intent);
		finish();
	}
	
	public void runEnviar(Class<?> class1,String receptor,String variavel){
		Intent intent=new Intent(getBaseContext(),class1);
		intent.putExtra(receptor, variavel);
		startActivity(intent);
		finish();
	}
	
	
	public void abreNovo(){
		
		
		LinearLayout novoUsuario=(LinearLayout) findViewById(R.id.lusuariosnovo);
		
		novoUsuario.setClickable(true);
		novoUsuario.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			
			
			runActivity(com.example.fitnessdroid.FormUsuariosActivity.class);
			
		}
	});
		
	}
	
	public void abreExcluir(){
		LinearLayout excluirUsuario=(LinearLayout) findViewById(R.id.lusuarioexcluir);
		
		excluirUsuario.setClickable(true);
		excluirUsuario.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			
			
			runEnviar(com.example.fitnessdroid.FormExcluirActivity.class,"excluir","1");
			
		}
	});
		
	}
	public void abreEditar(){
		LinearLayout editarUsuario=(LinearLayout) findViewById(R.id.lusuarioeditar);
		
		editarUsuario.setClickable(true);
		editarUsuario.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			
			
			runEnviar(com.example.fitnessdroid.FormAtualizarActivity.class,"atualizar","1");
			
		}
	});
		
	}
	public void abreListar(){
		LinearLayout listarUsuario=(LinearLayout) findViewById(R.id.lusuariolistar);
		
		listarUsuario.setClickable(true);
		listarUsuario.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			
			
			runEnviar(com.example.fitnessdroid.FormListarActivity.class,"listar","1");
			
		}
	});
		
	}
	
}

	


