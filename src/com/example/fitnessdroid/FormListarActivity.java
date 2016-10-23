package com.example.fitnessdroid;

import java.io.File;

import com.example.fitnessdroid.DAO.crudInstrutorDAO;
import com.example.fitnessdroid.DAO.crudUsuarioDAO;
import com.example.fitnessdroid.POJO.instrutorVO;
import com.example.fitnessdroid.POJO.usuarioVO;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FormListarActivity extends Activity{
	
	TextView textTitulo;
	private String titulo;
	private String variavel;
	private int inteiro;
	private crudUsuarioDAO dbUsu=new crudUsuarioDAO(this);
	private crudInstrutorDAO dbInst=new crudInstrutorDAO(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form_atualizar);
		
		
		textTitulo=(TextView) findViewById(R.id.textTitulo);
		
		
		openList();
		
		
		
	}

	
	public void openList(){
		Bundle extras=getIntent().getExtras();
		if(extras!=null){
			variavel=extras.getString("listar");
			
			 inteiro=Integer.parseInt(variavel);
			
			switch(inteiro){
			
			case 1:
				createListUsuario();
				break;
			case 2:
				createListInstrutor();
				break;
			}
		
		
		
		}
		
	}
	
	public void createListInstrutor(){
		titulo="Instrutor -Listar";
		LinearLayout linear=(LinearLayout) findViewById(R.id.FundoMeio);
		
		textTitulo.setText(titulo, null);
		Cursor cursor=dbInst.tbCursor();
		
		
		if(cursor.moveToFirst()){
			do{
				instrutorVO consulta=new instrutorVO();
				consulta.setId(Integer.parseInt(cursor.getString(0)));
				consulta.setInstrutor(cursor.getString(1));
				consulta.setFoto(cursor.getString(2));
				
				LinearLayout myLayout=new LinearLayout(this);
				
				myLayout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
				myLayout.setOrientation(LinearLayout.HORIZONTAL);
				myLayout.setGravity(Gravity.LEFT);
				
				TextView idList=new TextView(this);
				TextView nomeList=new TextView(this);
				ImageView fotoList=new ImageView(this);
				
				idList.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				nomeList.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				fotoList.setLayoutParams(new LayoutParams(128,128));
				
				idList.setTextSize(14);
				nomeList.setTextSize(14);
				
				//final String myIsntrutor=consulta.getInstrutor().toString();
				//final long rowId=consulta.getId();
				String txt=" "+consulta.getId()+" -";
				final String var=""+consulta.getId();
				final Long myId= consulta.getId();
				
				
				idList.setText(txt);
				nomeList.setText(" "+consulta.getInstrutor().toString());
				
				
				
				String foto=consulta.getFoto().toString();
				File file=new File(foto);
				if(file.exists()){
					Bitmap bm =BitmapFactory.decodeFile(foto);
					//fotoList.setImageResource(R.drawable.avatar);
					fotoList.setImageBitmap(bm);
				}else{
					fotoList.setImageResource(R.drawable.avatar);
				}
				
				
				myLayout.addView(fotoList);
				myLayout.addView(idList);
				myLayout.addView(nomeList);
				
				//mensagem
				
				final AlertDialog.Builder builder=new AlertDialog.Builder(this); 
				
				
				builder.setTitle("Listar");
				builder.setMessage("O que deseja fazer ?");
				builder.setNegativeButton("Cancelar",new DialogInterface.OnClickListener() {
					
						@Override
						public void onClick(DialogInterface dialog, int which) {
							finish();
							runActivity(com.example.fitnessdroid.FormListarActivity.class,"listar",variavel);
							}
					} );
				
					Button btListar=new Button(this);
					Button btExcluir=new Button(this);
					Button btAtualizar=new Button(this);
					btListar.setText("Listar");
					btExcluir.setText("Excluir");
					btAtualizar.setText("Atualizar");
					LinearLayout btLayout=new LinearLayout(this);
					
					btListar.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							runActivity(com.example.fitnessdroid.ListaInstrutorActivity.class, "listar", var);
						}
					});
					
					btExcluir.setOnClickListener(new OnClickListener() {
											
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							dbInst.deleteConfirm(myId);
							runActivity(com.example.fitnessdroid.FormListarActivity.class, "listar", variavel);
						}
					});
					
					btAtualizar.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							runActivity(com.example.fitnessdroid.AtualizaIstrutorActivity.class, "atualizar", var);
						}
					});
					btLayout.setGravity(Gravity.CENTER_HORIZONTAL);
					btLayout.addView(btListar);
					btLayout.addView(btExcluir);
					btLayout.addView(btAtualizar);
					
					
				///
				builder.setView(btLayout);
				
				myLayout.setClickable(true);
				myLayout.setOnClickListener(new OnClickListener() {
											
					@Override
					public void onClick(View v) {
					v.setBackgroundColor(Color.parseColor("#87F007"));
					builder.show();
						
					}
				});
				
				
				
				linear.addView(myLayout);
				
				//mostraLista.add(consulta);
			}while(cursor.moveToNext());
		}
	}
	
	public void createListUsuario(){
		LinearLayout linear=(LinearLayout) findViewById(R.id.FundoMeio);
		titulo="Usuario -Listar";
		textTitulo.setText(titulo, null);
		Cursor cursor=dbUsu.tbCursor();
		//List<usuarioVO> mostraLista=new ArrayList<usuarioVO>();
		
		if(cursor.moveToFirst()){
			do{
				usuarioVO consulta=new usuarioVO();
				consulta.setId(Integer.parseInt(cursor.getString(0)));
				consulta.setNome(cursor.getString(1));
				consulta.setFoto(cursor.getString(2));
				
				LinearLayout myLayout=new LinearLayout(this);
				
				myLayout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
				myLayout.setOrientation(LinearLayout.HORIZONTAL);
				myLayout.setGravity(Gravity.LEFT);
				
				TextView idList=new TextView(this);
				TextView nomeList=new TextView(this);
				ImageView fotoList=new ImageView(this);
				
				idList.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				nomeList.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				fotoList.setLayoutParams(new LayoutParams(128,128));
				
				idList.setTextSize(14);
				nomeList.setTextSize(14);
				
				final String myUsuario=consulta.getNome().toString();
				//final long rowId=consulta.getId();
				String txt=" "+consulta.getId()+" -";
				final String var=""+consulta.getId();
				final Long myId= consulta.getId();
				
				idList.setText(txt);
				nomeList.setText(" "+consulta.getNome().toString());
				
				
				
				String foto=consulta.getFoto().toString();
				File file=new File(foto);
				if(file.exists()){
					Bitmap bm =BitmapFactory.decodeFile(foto);
					//fotoList.setImageResource(R.drawable.avatar);
					fotoList.setImageBitmap(bm);
				}else{
					fotoList.setImageResource(R.drawable.avatar);
				}
				
				
				myLayout.addView(fotoList);
				myLayout.addView(idList);
				myLayout.addView(nomeList);
				
				//mensagem
				
				final AlertDialog.Builder builder=new AlertDialog.Builder(this); 
				
				
				builder.setTitle("Listar");
				builder.setMessage("O que deseja fazer ?");
				builder.setNegativeButton("Cancelar",new DialogInterface.OnClickListener() {
					
						@Override
						public void onClick(DialogInterface dialog, int which) {
							finish();
							runActivity(com.example.fitnessdroid.FormListarActivity.class,"listar",variavel);
							}
					} );
				
					Button btListar=new Button(this);
					Button btExcluir=new Button(this);
					Button btAtualizar=new Button(this);
					btListar.setText("Listar");
					btExcluir.setText("Excluir");
					btAtualizar.setText("Atualizar");
					LinearLayout btLayout=new LinearLayout(this);
					
					btListar.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							runActivity(com.example.fitnessdroid.ListaUsuarioActivity.class, "listar", var);
						}
					});
					
					btExcluir.setOnClickListener(new OnClickListener() {
											
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							dbUsu.deleteConfirm(myId);
							runActivity(com.example.fitnessdroid.FormListarActivity.class, "listar", variavel);
						}
					});
					
					btAtualizar.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							runActivity(com.example.fitnessdroid.AtualizaUsuarioActivity.class, "atualizar", var);
						}
					});
					btLayout.setGravity(Gravity.CENTER_HORIZONTAL);
					btLayout.addView(btListar);
					btLayout.addView(btExcluir);
					btLayout.addView(btAtualizar);
					
					
				///
				builder.setView(btLayout);
				
				myLayout.setClickable(true);
				myLayout.setOnClickListener(new OnClickListener() {
											
					@Override
					public void onClick(View v) {
					v.setBackgroundColor(Color.parseColor("#87F007"));
							//runActivity(com.example.fitnessdroid.AtualizaUsuarioActivity.class, "atualizar", var);
						builder.show();
					}	
						
				});
				
				
				
				linear.addView(myLayout);
				
				//mostraLista.add(consulta);
			}while(cursor.moveToNext());
		}
	}


	public void runActivity(Class<?> class1,String receptor,String var){
		//var=variavel;
		Intent intent=new Intent(this,class1);
		intent.putExtra(receptor, var);
		startActivity(intent);
		finish();
	}

	
	



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_form_atualizar, menu);
		return true;
	}
}
