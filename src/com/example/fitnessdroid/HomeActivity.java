package com.example.fitnessdroid;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.app.Activity;
import android.content.Intent;
import android.view.View.OnClickListener;


public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		abreCadastro();
		abrePersonalizar();
		abreConfiguracoes();
		abreTreino();
		
	}

	//public void run(){
		//startActivity(new Intent(this,com.example.fitnessdroid.CadastroActivity.class));
		//finish();
	//}
	
	public void runActivity(Class<?> class1){
		Intent intent=new Intent(this,class1);
		startActivity(intent);
	}
	
	public void abreCadastro(){
		LinearLayout cadastros=(LinearLayout) findViewById(R.id.layoutdados);
		
		cadastros.setClickable(true);
		cadastros.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				
				runActivity(com.example.fitnessdroid.CadastroActivity.class);
				
			}
		});
		
	}
	
	public void abrePersonalizar(){
		LinearLayout personalizar=(LinearLayout) findViewById(R.id.layoutpersonalizar);
		personalizar.setClickable(true);
		personalizar.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				
				runActivity(com.example.fitnessdroid.PersonalizarActivity.class);
				
			}
		});
		
	}
	
	public void abreConfiguracoes(){
		LinearLayout configuracoes=(LinearLayout) findViewById(R.id.layoutconfiguracoes);
		
		configuracoes.setClickable(true);
		configuracoes.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				
				runActivity(com.example.fitnessdroid.ConfiguracoesActivity.class);
				
			}
		});

}
	public void abreTreino(){
		LinearLayout treino=(LinearLayout) findViewById(R.id.layouttreinamento);
		
		treino.setClickable(true);
		treino.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				
				runActivity(com.example.fitnessdroid.ListUsuarioTreinoActivity.class);
				
			}
		});

}
}