package com.example.fitnessdroid;

import java.io.File;

import com.example.fitnessdroid.DAO.crudInstrutorDAO;
import com.example.fitnessdroid.POJO.instrutorVO;
import com.example.fitnessdroid.R.id;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ListaInstrutorActivity extends Activity{
	
	private crudInstrutorDAO myCrud;
	private instrutorVO myInstrutor;
	private EditText instrutor;
	private ImageView foto;
	private String fotoPath;
	private EditText email;
	private EditText fone;
	private EditText descricao;
	private long rowID;
	private String variavel;
	private String registro;



	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form_instrutor);
		
		setTitle("Instrutor");
		
		TextView title=(TextView) findViewById(R.id.titleForm);
		
		title.setText("Visualizar - Instrutor");
		instrutor=(EditText) findViewById(R.id.editInstrutor);
		foto=(ImageView) findViewById(R.id.imageAvatar);
		 email=(EditText) findViewById(R.id.editEmailInstrutor);
		 fone=(EditText) findViewById(R.id.editInstrutorFone);
		 descricao=(EditText) findViewById(R.id.editDescricaoTecnica);
		
		 myCrud=new crudInstrutorDAO(this);
		
		 inflaCampos();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_form_instrutor, menu);
		//return true;
		return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		
		if(item.getItemId()==R.id.menu_cancelar){
			sair();
		}
		
		return super.onOptionsItemSelected(item);
	}		
	public void runActivity(Class<?> class1){
		Intent intent=new Intent(this,class1);
		startActivity(intent);
		finish();
	}
	 
	public long recebeID(){
		// recebe os dados o intent put.extra
		
		Bundle extras=getIntent().getExtras();
		String value=extras.getString("atualizar");
		//long myID=Long.parseLong(extras.getLong("atualizar"));
			long myID=Long.parseLong(value);
		
		return myID;
		
	}
	
	public void recebeString(){
		Bundle extras=getIntent().getExtras();
		//if(extras!=null){
			variavel=extras.getString("atualizar");
			
		//}
	}
	public void inflaCampos(){
		
		//faz consulta pelo id e infla os dados nos formularios
		
		recebeString();
		
		//if(extras!=null){
		
			
//		}else{
			
	//	}
		Cursor cursor=myCrud.idCursor(variavel);
		
		if(cursor.moveToFirst()){
			 myInstrutor=new instrutorVO();
			//usuarioVO consulta=new usuarioVO();
			myInstrutor.setId(Integer.parseInt(cursor.getString(0)));
			myInstrutor.setInstrutor(cursor.getString(1));
			myInstrutor.setFoto(cursor.getString(2));
			myInstrutor.setEmail(cursor.getString(3));
			myInstrutor.setFone(cursor.getString(4));
			myInstrutor.setDescricao(cursor.getString(5));
			
			
			
			instrutor.setText(myInstrutor.getInstrutor().toString());
			
			String pathName=myInstrutor.getFoto().toString();
			File url=new File(pathName);
			
			if(url.exists()){
				Bitmap bmp=BitmapFactory.decodeFile(pathName);
				foto.setImageBitmap(bmp);
				
			}
			//else{
				//foto.setImageResource(R.id.imageAvatar);
			//}
			
			email.setText(myInstrutor.getEmail().toString());
			
			fone.setText(myInstrutor.getFone().toString());
			descricao.setText(myInstrutor.getDescricao().toString());
			
			//fotoPath=myInstrutor.getFoto().toString();
		
		}
		
	}
	
		
	
	
	public void abreFoto(){
			//Abre o dialogo da foto do usuario
			
			ImageView imageFoto=(ImageView) findViewById(id.imageAvatar);
			
			
			
			
			imageFoto.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//runDialog();
					runActivity(com.example.fitnessdroid.MyCameraActivity.class);
				}
			});
					
					
		}
	
		
	
		
		public void sair(){
			finish();
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
				runActivity(com.example.fitnessdroid.FormAtualizarActivity.class);
			}
		});
		builder.show();
		
	}
	
			public String getFoto(){
				String foto = "sem foto";
				Bundle extras=getIntent().getExtras();
				if(extras!=null){
					foto=extras.getString("BitmapImage");
					
				}else{
					foto=fotoPath;
				}
				
		
				return foto;
		
		
		
			}
			public void insereFoto(){
				Bundle extras=getIntent().getExtras();
				if(extras!=null){
					String fileValue=extras.getString("BitmapImage");
				ImageView trocaImagem=(ImageView) findViewById(R.id.imageAvatar);
				File image=new File(fileValue);	
				if(image.exists()){
					
					Bitmap myBitmap=BitmapFactory.decodeFile(fileValue);
					trocaImagem.setImageBitmap(myBitmap);
				}
				
					
					
				}
				
			}	

}
