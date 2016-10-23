package com.example.fitnessdroid;

import java.io.File;

import com.example.fitnessdroid.DAO.crudInstrutorDAO;
import com.example.fitnessdroid.DAO.crudUsuarioDAO;
import com.example.fitnessdroid.POJO.instrutorVO;
import com.example.fitnessdroid.POJO.usuarioVO;
import com.example.fitnessdroid.R.id;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class FormInstrutorActivity extends Activity {
	private instrutorVO myInstrutor;
	private crudInstrutorDAO myCrud;
	private String filePhoto;
	private String fileStorage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form_instrutor);
		
		TextView title=(TextView) findViewById(R.id.titleForm);
		
		title.setText("Novo - Instrutor");
		loadBundle();
		abreFoto();
		getFoto();
		insereFoto();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_form_instrutor, menu);
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
	
	public void sair(){
		runActivity(com.example.fitnessdroid.InstrutoresActivity.class);
	}
	
	
	public void saveData(){
		
		novoInstrutor();
		
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
	
	public void novoInstrutor(){
		 EditText instrutor=(EditText) findViewById(R.id.editInstrutor);
		 String foto=getFoto();
		 EditText email=(EditText) findViewById(R.id.editEmailInstrutor);
		 EditText fone=(EditText) findViewById(R.id.editInstrutorFone);
		 EditText descricao=(EditText) findViewById(R.id.editDescricaoTecnica);
		 
		 myInstrutor=new instrutorVO();
		 myCrud=new crudInstrutorDAO(this);
		 
		 
		 myInstrutor.setInstrutor(instrutor.getEditableText().toString());
		 myInstrutor.setFoto(foto);
		 myInstrutor.setEmail(email.getEditableText().toString());
		 myInstrutor.setFone(fone.getEditableText().toString());
		 myInstrutor.setDescricao(descricao.getEditableText().toString());
		 
		 myCrud.open();
		 myCrud.inserir(myInstrutor);
		 myCrud.close();
		
	}
	
	public void abreFoto(){
		//Abre o dialogo da foto do usuario
		
		ImageView imageFoto=(ImageView) findViewById(id.imageAvatar);
		
		imageFoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//runDialog();
				runActivity(com.example.fitnessdroid.MyCameraInstrutor.class);
			}
		});
				
				
	}
	
	
	public void runDialog(){
		
		Dialog myDialog=new Dialog(FormInstrutorActivity.this);
		
		myDialog.setContentView(R.layout.activity_my_camera);
		myDialog.setTitle("Foto do Isntrutor");
		myDialog.setCancelable(true);
		
		myDialog.show();
		
		
	}
	public String getFoto(){
			
			
			
			String foto = "sem foto";
			
			if(filePhoto!=null){
				foto=filePhoto;
			}
			
			if(fileStorage!=null){
				
				foto=fileStorage;
			}
			
			
			return foto;
			
			
			
		}
	
	
	
	public void loadBundle(){
		Bundle extras=getIntent().getExtras();
		if(extras!=null){
			
			if(extras.getString("BitmapImage")!=null){
				filePhoto=extras.getString("BitmapImage");
						
			}
			
			if(extras.getString("arquivo")!=null){
				fileStorage=extras.getString("arquivo");
			}
		}	
	}
	
	
	public void insereFoto(){
	ImageView trocaImagem=(ImageView) findViewById(R.id.imageAvatar);
			
			
			//if(filePhoto!=null){
				
			//	File urlPhoto=new File(filePhoto);
				
			//	if(urlPhoto.exists()){
			//		Bitmap bmp=BitmapFactory.decodeFile(filePhoto);
			//		trocaImagem.setImageBitmap(bmp);
					
			//	}
				
			//if(fileStorage!=null){
				File urlStorage=new File(getFoto().toString());
				
				if(urlStorage.exists()){
					
					
					//BitmapFactory.Options options=new BitmapFactory.Options();
					//options.inSampleSize=2;
					Bitmap bmp=BitmapFactory.decodeFile(getFoto().toString());
					trocaImagem.setImageBitmap(bmp);
					
				}
				
				
			}
}