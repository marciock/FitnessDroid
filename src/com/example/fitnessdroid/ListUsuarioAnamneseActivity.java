package com.example.fitnessdroid;

import java.io.File;

import com.example.fitnessdroid.DAO.crudUsuarioDAO;
import com.example.fitnessdroid.POJO.usuarioVO;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListUsuarioAnamneseActivity extends Activity{
	
	private crudUsuarioDAO dbUsu=new crudUsuarioDAO(this);
	private String titulo;
	
	private int inteiro;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_usuario);
		listaUsuario();
	
	}
	
	
	public void listaUsuario(){
		LinearLayout linear=(LinearLayout) findViewById(R.id.FundoMeio);
		
		
		Cursor cursor=dbUsu.tbCursor();
		
		
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
				
				//final String myUsuario=consulta.getNome().toString();
				//final long rowId=consulta.getId();
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
							
							runActivity(com.example.fitnessdroid.FormAnamneseActivity.class, "usuario", var);
							
							
						
					}
				});
				
				
				
				linear.addView(myLayout);
				
				
			}while(cursor.moveToNext());
		}else{
			finish();
		}
	}
	
	public void runActivity(Class<?> class1,String receptor1,String var1){
		//var=variavel;
		Intent intent=new Intent(this,class1);
		intent.putExtra(receptor1, var1);
		startActivity(intent);
		//finish();
	}

}
	

