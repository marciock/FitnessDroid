package com.example.fitnessdroid;

import java.io.File;

import com.example.fitnessdroid.DAO.crudInstrutorDAO;
import com.example.fitnessdroid.DAO.crudUsuarioDAO;
import com.example.fitnessdroid.POJO.instrutorVO;
import com.example.fitnessdroid.POJO.usuarioVO;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FormAtualizarActivity extends Activity {
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
			variavel=extras.getString("atualizar");
			
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
		titulo="Instrutor -Atualizar";
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
				
				
				
				myLayout.setClickable(true);
				myLayout.setOnClickListener(new OnClickListener() {
											
					@Override
					public void onClick(View v) {
					v.setBackgroundColor(Color.parseColor("#87F007"));
					runActivity(com.example.fitnessdroid.AtualizaIstrutorActivity.class, "atualizar", var);
						
					}
				});
				
				
				
				linear.addView(myLayout);
				
				//mostraLista.add(consulta);
			}while(cursor.moveToNext());
		}
	}
	
	public void createListUsuario(){
		LinearLayout linear=(LinearLayout) findViewById(R.id.FundoMeio);
		titulo="Usuario -atualizar";
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
				final long rowId=consulta.getId();
				String txt=" "+consulta.getId()+" -";
				final String var=""+consulta.getId();
				
				
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
				
				
				
				myLayout.setClickable(true);
				myLayout.setOnClickListener(new OnClickListener() {
											
					@Override
					public void onClick(View v) {
					v.setBackgroundColor(Color.parseColor("#87F007"));
							runActivity(com.example.fitnessdroid.AtualizaUsuarioActivity.class, "atualizar", var);
					
					
						
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

	public void mensagens(Context context,String title,String message,String textP,String textN){
		AlertDialog.Builder builder=new AlertDialog.Builder(context);
		
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setNegativeButton(textN,new DialogInterface.OnClickListener() {
			
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					runActivity(com.example.fitnessdroid.FormAtualizarActivity.class,"atualizar",variavel);
					}
			} );
		builder.setPositiveButton(textP,new DialogInterface.OnClickListener() {
			
			
			public void onClick(DialogInterface dialog, int which) {
		
		
			runActivity(com.example.fitnessdroid.FormAtualizarActivity.class,"atualizar",variavel);
		}
		});
		 builder.show();
		
	}
	



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_form_atualizar, menu);
		return true;
	}

}
