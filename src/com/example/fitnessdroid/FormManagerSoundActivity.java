package com.example.fitnessdroid;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.example.fitnessdroid.DAO.crudRepertorioDAO;
import com.example.fitnessdroid.POJO.repertorioVO;
import com.example.fitnessdroid.R.layout;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData.Item;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class FormManagerSoundActivity extends Activity {
	private ArrayAdapter<String> listAdapter;
	
	private String fileValue;
	private String variavel=null;
	private List<String> item=new ArrayList<String>();
	
	private Cursor cRepertorio=null;
	private crudRepertorioDAO dbExec=new crudRepertorioDAO(this);
	private repertorioVO myRepertorio;
	private String[] myPath;
	private int conta;
	private ArrayList<Integer> id_repertorio=new ArrayList<Integer>();
	private ListView lListSound;
	private LinearLayout btAddSound;
	private LinearLayout btDelSound;
	private LinearLayout btStopSound;
	private LinearLayout btPlaySound;
	private MediaPlayer toqueBem;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form_manager_sound);
		toqueBem=new MediaPlayer();
		
		requestExtras();
		addSounds();
		ListSounds();
		delSounds();
		playSound();
		stopSound();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.form_manager_sound, menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item){
		
		if(item.getItemId()==R.id.menu_cancelar){
			sair();
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	
	

	public void sair(){
		//finish();
		runActivity(com.example.fitnessdroid.ExercicesForSounds.class, "", "");
	}
	
	
	
	public void mensagens(Context context,String title,String message,String textP,String textN){
		
		AlertDialog.Builder builder=new AlertDialog.Builder(context);
		
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setNegativeButton(textN,new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
				runActivity(com.example.fitnessdroid.FormManagerSoundActivity.class,"","");
			}
		} );
		builder.setPositiveButton(textP,new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				finish();
				runActivity(com.example.fitnessdroid.FormManagerSoundActivity.class,"","");
			}
		});
		builder.show();
		
}

public void mudaTitulo(String id){
		
		int i=Integer.parseInt(id);
		
		switch (i) {
		case 1:
			this.setTitle("Peitorais");
			break;
		case 2:
			this.setTitle("Dorsais");
			break;
		case 3:
			this.setTitle("Biceps");
			break;
		case 4:
			this.setTitle("Triceps");
			break;
		case 5:
			this.setTitle("Deltóides");
			break;
		case 6:
			this.setTitle("Abdomen");
			break;
		case 7:
			this.setTitle("M.I. Anteriores");
			break;
		case 8:
			this.setTitle("M.I. Posteriores");
			break;
			
		default:
			break;
		}
		
	}
	
	
	
	public void addSounds(){
		
		btAddSound=(LinearLayout) findViewById(R.id.laddsound);
		
		btAddSound.setClickable(true);
		btAddSound.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				runActivity(com.example.fitnessdroid.ListFoldersActivity.class,"filtro","sound");
				
				
			}
		});
		
		
		
	}
		public void delSounds(){
				
				btDelSound=(LinearLayout) findViewById(R.id.ldelsound);
				
				btDelSound.setClickable(true);
				
				btDelSound.setOnClickListener(new OnClickListener() {
						
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						
						String music=selectChoices();
						Cursor pesquisa=dbExec.porID("musica LIKE","'%"+music+"%'");
						if(pesquisa.moveToFirst()){
						int id=Integer.parseInt(pesquisa.getString(0));
						
						dbExec.deleteConfirm(id);
						runActivity(com.example.fitnessdroid.FormManagerSoundActivity.class, "grupo", variavel);
						}
					}
				});
				
			}
		
		public void playSound()
		{
			btPlaySound=(LinearLayout) findViewById(R.id.lplaysound);
			
			btPlaySound.setClickable(true);
			btPlaySound.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					

					String music=selectChoices();
					Cursor pesquisa=dbExec.porID("musica LIKE","'%"+music+"%'");
					if(pesquisa.moveToFirst()){
						String url=pesquisa.getString(3);
						try {
							toqueBem.setDataSource(url);
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							
						}
						try {
							toqueBem.prepare();
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						stopSound();
						toqueBem.start();
					
					}
					
				}
			});
			
			
			
			String music=selectChoices();
			Cursor pesquisa=dbExec.porID("musica LIKE","'%"+music+"%'");
			if(pesquisa.moveToFirst()){
			int id=Integer.parseInt(pesquisa.getString(0));
			
			dbExec.deleteConfirm(id);
			runActivity(com.example.fitnessdroid.FormManagerSoundActivity.class, "grupo", variavel);
			}
			
			
		}
		
		public void stopSound(){
			
			btStopSound=(LinearLayout) findViewById(R.id.lstopsound);
			
			btStopSound.setClickable(true);
			btStopSound.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(toqueBem.isPlaying()){
						toqueBem.stop();
						
					}
				}
			});
			
		}
	public void runActivity(Class<?> class1,String receptor1,String var1){
		
		Intent intent=new Intent(this,class1);
		intent.putExtra(receptor1, var1);
		intent.putExtra("diretorio","/mnt");
		intent.putExtra("grupo",variavel);
		startActivity(intent);
		finish();
	
	}
	
	
	public void requestExtras(){
		//&& requestCode==REQUEST_CODE
		
			Bundle extras=getIntent().getExtras();
			
			if(extras.getString("arquivo")!=null){
				fileValue=extras.getString("arquivo");
				
			}
			
			if(extras.getString("grupo")!=null){
				variavel=extras.getString("grupo");
				
				mudaTitulo(variavel);
			
			}
			
		
	}
	
	public void ListSounds(){
		
		
		//LinearLayout layoutSound=(LinearLayout) findViewById(R.id.lListSound);
		
		lListSound=(ListView) findViewById(R.id.listSound);
		
		Bundle extras=getIntent().getExtras();
		
		//começa o banco
		
	
		
		repertorioVO consulta=new repertorioVO();
		
		if(variavel!=null){
		cRepertorio=dbExec.idCursor(variavel);
		
		 conta=cRepertorio.getCount();
		
		
		
				
	if(cRepertorio.moveToFirst()){
			//int conta=cRepertorios.getCount();
						
			int i=0;
			//ListView lListSound=(ListView) findViewById(R.id.listExercices);
			//List<String> item=new ArrayList<String>();	
			
			do{
				
				consulta.setId_repertorio(Integer.parseInt(cRepertorio.getString(0)));
				consulta.setId_grupo(Integer.parseInt(cRepertorio.getString(1)));
				consulta.setMusica(cRepertorio.getString(2));
				consulta.setCaminho(cRepertorio.getString(3));
				
				
				String varia=consulta.getMusica().toString();
						
					//File myFile=new File(varia);
					
					
					
				item.add(varia);
				
				int myRepertorio=(int) consulta.getId_repertorio();
				id_repertorio.add(myRepertorio);
					
				
				
				i++;
				
				
			}while(cRepertorio.moveToNext());
				
			
			
		}
	
		listAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,item);
		lListSound.setChoiceMode(lListSound.CHOICE_MODE_SINGLE);
		lListSound.setAdapter(listAdapter);
		
		
		
		
		
		}
	}
	
	private String selectChoices(){
		int conta=lListSound.getCheckedItemCount();
		String var = null;
		for(int i=0;i<conta;i++){
			
			var=lListSound.getItemAtPosition(i).toString();
					
			
			
		}
		
		return var;
		
		
	}
}

