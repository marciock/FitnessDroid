package com.example.fitnessdroid;



import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.app.Activity;
import android.content.Intent;
import android.view.View.OnClickListener;

public class CadastroActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro);
		
		
		abreUsuario();
		abreInstrutor();
		abreMedidas();
		abreAnamnese();
		
		
		
	}
	
	
	
	
	
	
	public void runActivity(Class<?> class1){
		Intent intent=new Intent(this,class1);
		startActivity(intent);
	}
	
	public void abreUsuario(){
		
		LinearLayout myusuario=(LinearLayout) findViewById(R.id.lconfigexercicios);
		
		myusuario.setClickable(true);
				
		myusuario.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				runActivity(com.example.fitnessdroid.UsuariosActivity.class);
				
			}
		});
				
					
	}
	
	
	public void abreInstrutor(){
			
		LinearLayout myisntrutor=(LinearLayout) findViewById(R.id.lconfigrepertorio);
			
		myisntrutor.setClickable(true);
					
		myisntrutor.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					runActivity(com.example.fitnessdroid.InstrutoresActivity.class);
					
				}
			});
					
						
		}
	public void abreMedidas(){
		
		LinearLayout mymedias=(LinearLayout) findViewById(R.id.layoutmedidas);
			
		mymedias.setClickable(true);
					
		mymedias.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					runActivity(com.example.fitnessdroid.MedidasActivity.class);
					
				}
			});
					
						
		}
	public void abreAnamnese(){
		
		LinearLayout myanamnese=(LinearLayout) findViewById(R.id.layoutanamnese);
			
		myanamnese.setClickable(true);
					
		myanamnese.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					runActivity(com.example.fitnessdroid.AnamneseActivity.class);
					
				}
			});
					
						
		}
	
}
	


