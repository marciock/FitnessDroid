package com.example.fitnessdroid;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.fitnessdroid.DAO.crudExercicioDAO;
import com.example.fitnessdroid.DAO.crudRepertorioDAO;
import com.example.fitnessdroid.DAO.crudTreinoDAO;
import com.example.fitnessdroid.DAO.crudUsuarioDAO;
import com.example.fitnessdroid.POJO.exercicioVO;
import com.example.fitnessdroid.POJO.repertorioVO;
import com.example.fitnessdroid.POJO.treinoVO;
import com.example.fitnessdroid.POJO.usuarioVO;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.InputType;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class FormTreinoActivity extends Activity {
	private ImageView avatar;
	private TextView nome;
	private TextView ordem;
	private TextView exercicios;
	private TextView serie;
	private TextView o_x;
	private TextView repeticao;
	private TextView carga;
	private TableRow linha;
	private TableLayout tabela;
	private LinearLayout encaixa;
	private LinearLayout titulo;
	private Cursor cExercicios=null;
	private crudExercicioDAO dbExec=new crudExercicioDAO(this);
	private crudUsuarioDAO dbUsu=new crudUsuarioDAO(this);
	private crudTreinoDAO dbTreino=new crudTreinoDAO(this);
	private String variavel=null;
	private String varID=null;
	private int conta;
	private MediaPlayer toqueBem=new MediaPlayer();
	private crudRepertorioDAO dbRepertorio=new crudRepertorioDAO(this);
	private repertorioVO voRepertorio;
	private ArrayList<String> listaMusicas= new ArrayList<String>();
	private int track=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form_treino);
		

		tabela=(TableLayout) findViewById(R.id.tabExercicios);
		avatar=(ImageView) findViewById(R.id.imgAvatar);
		nome=(TextView) findViewById(R.id.textNome);
		titulo=(LinearLayout) findViewById(R.id.barraNome);
		
		entrada();
		//tocar();
		setTitle("Treino");
		dataFound();
		//onBackPressed();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.form_treino, menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item){
		
		if(item.getItemId()==R.id.menu_tras){
			irParaTras();
			
		}
		if(item.getItemId()==R.id.menu_tocar){
			tocar(track);
		}
		if(item.getItemId()==R.id.menu_pause){
					pausar();
		}
		if(item.getItemId()==R.id.menu_frente){
			irParFrente();
		}
		return super.onOptionsItemSelected(item);
	}
	public void abreRepertorio(){
		
			Bundle extras=getIntent().getExtras();
		
		
			
			voRepertorio=new repertorioVO();
			
			Cursor cursor=dbRepertorio.idCursor(variavel);
			
			if(cursor.moveToFirst()){
				
				do{
					voRepertorio.setCaminho(cursor.getString(3));
					listaMusicas.add(voRepertorio.getCaminho().toString());
					
				}while(cursor.moveToNext());
						
		}
	}
	
	public void onBackPressed(){
		
		
		toqueBem.reset();
		
		finish();
		
	
		
	}
	
	
	public void irParaTras(){
		int total=listaMusicas.size();
		
		if(track==0){
			track=total;
		}else{
			
			track--;
		}
		toqueBem.reset();
		tocar(track);
	}
	public void irParFrente(){
		int total=listaMusicas.size();
		
		if(track>total){
			track=0;
		}else{
			
			track++;
		}
		toqueBem.reset();
		tocar(track);
	}
	public void tocar(int myTrack){
		abreRepertorio();	
		//track=;
	
		try {
			toqueBem.setDataSource(listaMusicas.get(myTrack).toString());
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
		
		toqueBem.start();
	
		
	}
	public void pausar(){
		
		toqueBem.pause();
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
	
	public void entrada(){
		Bundle extras=getIntent().getExtras();
		

		if(extras!=null){
			varID=extras.getString("usuario");
			variavel=extras.getString("grupo");
	}
	
	}
	public void dataFound(){
		
		
		
			exercicioVO consulta=new exercicioVO();
			
			cExercicios=dbExec.idCursor(variavel);
			
			 conta=cExercicios.getCount();
			
		
		
		mudaTitulo(variavel);
		
		usuarioVO usuConsulta=new usuarioVO();
		
		
		
		
		
		Cursor usuCursor=dbUsu.idCursor(varID);
		
		
		
		if(usuCursor.moveToFirst()){
			treinoVO treinoConsulta=new treinoVO();
			
			usuConsulta.setId(Integer.parseInt(usuCursor.getString(0)));
			
			//Long idUsuario=usuConsulta.getId();
			//Long idGrupo=Long.parseLong(variavel);
			String idUsuario=varID;
			String idGrupo=variavel;
			
			
			usuConsulta.setNome(usuCursor.getString(1));
			usuConsulta.setFoto(usuCursor.getString(2));
			
			nome.setText(" "+usuConsulta.getNome().toString());
			
			String foto=usuConsulta.getFoto().toString();
			
			File file=new File(foto);
			if(file.exists()){
				Bitmap bm =BitmapFactory.decodeFile(foto);
				//fotoList.setImageResource(R.drawable.avatar);
				//avatar.setLayoutParams(new LayoutParams(largura,altura));
				avatar.setImageBitmap(bm);
				avatar.getLayoutParams().height=64;
				avatar.getLayoutParams().width=64;
				
			}else{
				avatar.setImageResource(R.drawable.avatar_mini);
			}
			
		
		
		
			Cursor cTreino=dbTreino.innerJoin(idUsuario, idGrupo);
				
						
			if(cTreino.moveToFirst()){
				
						
					int i=0;
					
					
					
					do{
						
						treinoConsulta.setId_exercicio(Integer.parseInt(cTreino.getString(2)));
						treinoConsulta.setId_grupo(Integer.parseInt(cTreino.getString(4)));
						treinoConsulta.setExercicio(cTreino.getString(1));
						treinoConsulta.setOrdem(cTreino.getString(5));
						treinoConsulta.setSerie(cTreino.getString(6));
						treinoConsulta.setRepeticao(cTreino.getString(7));
						treinoConsulta.setCarga(cTreino.getString(8));
						
						ordem=new TextView(this);
						exercicios=new TextView(this);
						serie=new TextView(this);
													
						o_x=new TextView(this);
						
						repeticao=new TextView(this);
						carga=new TextView(this);
						
						ordem.setText(treinoConsulta.getOrdem().toString());
						serie.setText(treinoConsulta.getSerie().toString());
						repeticao.setText(treinoConsulta.getRepeticao().toString());
						carga.setText(treinoConsulta.getCarga().toString());
						
	
						encaixa=new LinearLayout(this);
						
						encaixa.setGravity(Gravity.CENTER);
						linha=new TableRow(this);
						exercicios.setTextAppearance(this, android.R.attr.textAppearanceMedium);
						o_x.setTextAppearance(this, android.R.attr.textAppearanceMedium);
						
						exercicios.setTextSize(14);
						exercicios.setText(treinoConsulta.getExercicio().toString());
						//int myExercicio=(int) treinoConsulta.getId_exercicio();
						//id_exercicio.add(myExercicio);
						o_x.setText("X");
						
						ordem.setEms(1);
						serie.setEms(2);
						repeticao.setEms(2);
						carga.setEms(2);
						
						ordem.setTextColor(Color.parseColor("#000000"));
						serie.setTextColor(Color.parseColor("#000000"));
						repeticao.setTextColor(Color.parseColor("#000000"));
						carga.setTextColor(Color.parseColor("#000000"));
						o_x.setTextColor(Color.parseColor("#87F007"));
						
						
				
						ordem.setHintTextColor(Color.parseColor("#585858"));
						serie.setHintTextColor(Color.parseColor("#585858"));
						repeticao.setHintTextColor(Color.parseColor("#585858"));
						carga.setHintTextColor(Color.parseColor("#585858"));
						
						serie.setInputType(InputType.TYPE_CLASS_NUMBER);
						repeticao.setInputType(InputType.TYPE_CLASS_NUMBER);
						carga.setInputType(InputType.TYPE_CLASS_NUMBER);
						
						exercicios.setId(i);
						ordem.setId(i);
						serie.setId(i);
						repeticao.setId(i);
						carga.setId(i);
						
						
						
						ordem.setGravity(Gravity.CENTER);
						serie.setGravity(Gravity.CENTER);
						repeticao.setGravity(Gravity.CENTER);
						carga.setGravity(Gravity.CENTER);
						
						encaixa.addView(serie,new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
						encaixa.addView(o_x,new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
						encaixa.addView(repeticao,new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
						
						
						
						linha.addView(ordem,new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
						linha.addView(exercicios,new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
						linha.addView(encaixa,new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
						linha.addView(carga,new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
					i++;
					
						
						tabela.addView(linha,new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
						
					}while(cTreino.moveToNext());
				
			
			
			
			}
			
			}
		
		
			
		}
	


}





