package com.example.fitnessdroid;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.fitnessdroid.DAO.crudRepertorioDAO;
import com.example.fitnessdroid.POJO.repertorioVO;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListFoldersActivity extends Activity {
	private String myPasta;
	private String tipo;
	private int caso;
	private String grupo;
	private crudRepertorioDAO dbExec;
	private String individuo;
	private repertorioVO consulta=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_folders);
		dbExec=new crudRepertorioDAO(this);
		
		requestView();
		levelUp();
		listaTudo();
		requestIndividuo();
	}

	public void levelUp(){
		
		LinearLayout levelUP=(LinearLayout) findViewById(R.id.LayoutLevelUp);
		TextView voltar=(TextView) findViewById(R.id.textVoltar);
		levelUP.setClickable(true);
		
		voltar.setText(myPasta);
		

		levelUP.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				v.setBackgroundColor(Color.parseColor("#87F007"));
				
				File file=new File(myPasta);
				String var=file.getParent().toString();
				
				
				
				runActivity(com.example.fitnessdroid.ListFoldersActivity.class,"diretorio",var);
			}
		});
		
		
		
	}
	
	
	
	public void runActivityBack(Class<?> class1,String receptor,String var){
		//var=variavel;
		Intent intent=new Intent(this,class1);
		intent.putExtra(receptor, var);
		intent.putExtra("filtro", tipo);
		intent.putExtra("grupo",grupo);
		intent.putExtra("individuo", individuo);
		//startActivity(intent);
		//setResult(resultCode);
		setResult(RESULT_OK, intent);
		finish();
	}
	
	public void runActivitySound(Class<?> class1,String receptor,String var){
		//var=variavel;
		Intent intent=new Intent(this,class1);
		//intent.putExtra("grupo",grupo);
		intent.putExtra(receptor, var);
		//intent.putExtra("filtro", tipo.toString());
		startActivity(intent);
		//setResult(RESULT_OK, intent);
		finish();
	}
	public void runActivityImage(String myActivite,String receptor,String var){
		
		Class<?> clazz = null;
		try {
			clazz = Class.forName(myActivite);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Intent intent=new Intent(this,clazz);
		
		intent.putExtra(receptor, var);
		
		startActivity(intent);
		
		finish();
	}
	
	public void runActivity(Class<?> class1,String receptor,String var){
		//var=variavel;
		Intent intent=new Intent(this,class1);
		intent.putExtra(receptor, var);
		intent.putExtra("filtro", tipo);
		intent.putExtra("grupo",grupo);
		intent.putExtra("individuo", individuo);
		startActivity(intent);
		//setResult(RESULT_OK, intent);
		finish();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_folders, menu);
		return true;
	}
	
	public void requestIndividuo(){
		Bundle extras=getIntent().getExtras();
		if(extras.getString("individuo")!=null){
			individuo=extras.getString("individuo").toString();
		}
	}
	public void requestView(){
		Bundle extras=getIntent().getExtras();
		
		if(extras.getString("diretorio")!=null){
			
			myPasta=extras.getString("diretorio").toString();
			
					
		}
		
		
		
	
	if(extras.getString("filtro")!=null){
		
		tipo=extras.getString("filtro").toString();
	}
	if(extras.getString("grupo")!=null){
		grupo=extras.getString("grupo").toString();
	}
	
	
	if(tipo.equals("sound")){	
		
		caso=0;
				
	}
	if(tipo.equals("image")){
		caso=1;
	}
	//listaTudo();

}
	
	public void listaTudo(){
		listaPasta();
		
		switch (caso) {
		case 0:
			listaSom();
			break;
		case 1:
			listaImagem();
			break;
		default:
			listaPasta();
			break;
		}
		
		
		
	}
	public void insereSom(String var,String myNome){
		consulta=new repertorioVO();
		
		long idgrupo=Long.parseLong(grupo);
		consulta.setId_grupo(idgrupo);
		consulta.setMusica(myNome);
		consulta.setCaminho(var);
		dbExec.open();
		dbExec.inserir(consulta);
		dbExec.close();
		
	}
	public void listaPasta(){
		
		//String lista=motivo;
		
		
		File dir=new File(myPasta);
		File afile=null;
		
		File[] fileList=dir.listFiles();
		
		LinearLayout layout=(LinearLayout) findViewById(R.id.listFolder);
		
		if(fileList !=null){
			for(int i=0;i<fileList.length;i++){
				
				ImageView imgFolder=new ImageView(this);
				
				//myFolder.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				
				TextView nomePasta=new TextView(this);
				 String  caminho = null;
				 
				
				 if(fileList[i].isDirectory()){
					 imgFolder.setImageResource(R.drawable.arquivo);
					 
				
				
				
				afile=new File(fileList[i].toString());
				nomePasta.setText(afile.getName().toString());
				
				caminho=fileList[i].getAbsolutePath().toString();
				 
				final String var=caminho;
				final  File fl=new File(fileList[i].toString());
				nomePasta.setTextSize(20);
				nomePasta.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
				
				LinearLayout ll=new LinearLayout(this);
				ll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
				ll.setGravity(Gravity.CENTER_VERTICAL);
				ll.setOrientation(LinearLayout.HORIZONTAL);
				ll.setGravity(Gravity.LEFT);
				
				ll.addView(imgFolder);
				ll.addView(nomePasta);
				ll.setClickable(true);
				ll.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						v.setBackgroundColor(Color.parseColor("#87F007"));
						
						
						
						 	runActivity(com.example.fitnessdroid.ListFoldersActivity.class,"diretorio",var );
							
						 
							
						}
														
				});
				
				layout.addView(ll);
				 }//final do if();
			}
			
		}
	}
	
	public void listaSom(){
		
		
		
		
		File dir=new File(myPasta);
		File afile=null;
		
		File[] fileList=dir.listFiles();
		
		LinearLayout layout=(LinearLayout) findViewById(R.id.listFolder);
		
		if(fileList !=null){
			for(int i=0;i<fileList.length;i++){
				
				ImageView imgFolder=new ImageView(this);
				
				//myFolder.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				
				TextView nomePasta=new TextView(this);
				 String  caminho = null;
				 
				 //afile=new File(fileList[i].toString());
				 //if(fileList[i].isDirectory()){
					// imgFolder.setImageResource(R.drawable.arquivo);
					 
			//	 }
				 			
				// if (fileList[i].toString().endsWith(".jpg") || fileList[i].toString().endsWith(".png") || fileList[i].toString().endsWith(".jpeg")) {
					// imgFolder.setImageResource(R.drawable.imagefolder);
					
				//}
				 
				
			 if (fileList[i].toString().endsWith(".mp3") || fileList[i].toString().endsWith(".mp4") || fileList[i].toString().endsWith(".wav")) {
				imgFolder.setImageResource(R.drawable.soundfolder);
				afile=new File(fileList[i].toString());
				
				nomePasta.setText(afile.getName().toString());
				
				caminho=fileList[i].getAbsolutePath().toString();
				
			 
				//itemList.add(caminho);
				final String var=caminho;
				final String myNome=afile.getName().toString();
				nomePasta.setTextSize(20);
				nomePasta.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
				
				LinearLayout ll=new LinearLayout(this);
				ll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
				ll.setGravity(Gravity.CENTER_VERTICAL);
				ll.setOrientation(LinearLayout.HORIZONTAL);
				ll.setGravity(Gravity.LEFT);
				
				
				ll.addView(imgFolder);
				ll.addView(nomePasta);
				ll.setClickable(true);
				ll.setOnClickListener(new OnClickListener() {
					
							
					@Override
					public void onClick(View v) {
						v.setBackgroundColor(Color.parseColor("#87F007"));
					//	
						insereSom(var,myNome);
						//finish();
						
							 
							runActivitySound(com.example.fitnessdroid.FormManagerSoundActivity.class,"grupo",grupo);
								
								
							
							
						}
						
						
				});
				
				
			 
				layout.addView(ll);
			 }//final do if();
			}
			
			
		}
		
	}
	
	
public void listaImagem(){
		
		
		
		
		File dir=new File(myPasta);
		File afile=null;
		
		File[] fileList=dir.listFiles();
		
		LinearLayout layout=(LinearLayout) findViewById(R.id.listFolder);
		
		if(fileList !=null){
			for(int i=0;i<fileList.length;i++){
				
				ImageView imgFolder=new ImageView(this);
				
				//myFolder.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				
				TextView nomePasta=new TextView(this);
				 String  caminho = null;
				 
				 //afile=new File(fileList[i].toString());
				 //if(fileList[i].isDirectory()){
					// imgFolder.setImageResource(R.drawable.arquivo);
					 
			//	 }
				 			
				 if (fileList[i].toString().endsWith(".jpg") || fileList[i].toString().endsWith(".png") || fileList[i].toString().endsWith(".jpeg")) {
					 imgFolder.setImageResource(R.drawable.imagefolder);
					
				
				
					afile=new File(fileList[i].toString());
					
					nomePasta.setText(afile.getName().toString());
					
					caminho=fileList[i].getAbsolutePath().toString();
					
				 
					
					final String var=fileList[i].getAbsolutePath().toString();
					
					nomePasta.setTextSize(20);
					nomePasta.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
					
					LinearLayout ll=new LinearLayout(this);
					ll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
					ll.setGravity(Gravity.CENTER_VERTICAL);
					ll.setOrientation(LinearLayout.HORIZONTAL);
					ll.setGravity(Gravity.LEFT);
					
					
					ll.addView(imgFolder);
					ll.addView(nomePasta);
					ll.setClickable(true);
					ll.setOnClickListener(new OnClickListener() {
						
								
						@Override
						public void onClick(View v) {
							v.setBackgroundColor(Color.parseColor("#87F007"));
							
							
							
								
								runActivityImage(individuo,"arquivo",var);
							
								
							
							
							
							//	runActivitySound(com.example.fitnessdroid.FormInstrutorActivity.class,"arquivo",var);
							
							
								
								
						}	
							
							
					});
					
					
				 
					layout.addView(ll);
			 }//final do if();
			}
			
			
		}
		
	}
	
	
}
