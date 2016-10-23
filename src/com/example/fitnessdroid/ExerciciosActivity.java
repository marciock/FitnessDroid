package com.example.fitnessdroid;

import java.io.File;
import java.util.ArrayList;
import com.example.fitnessdroid.R.menu;
import com.example.fitnessdroid.DAO.crudExercicioDAO;
import com.example.fitnessdroid.DAO.crudTreinoDAO;
import com.example.fitnessdroid.DAO.crudUsuarioDAO;
import com.example.fitnessdroid.POJO.exercicioVO;
import com.example.fitnessdroid.POJO.treinoVO;
import com.example.fitnessdroid.POJO.usuarioVO;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ExerciciosActivity extends Activity {
	private ImageView avatar;
	private TextView nome;
	private EditText ordem;
	private TextView exercicios;
	private EditText serie;
	private TextView o_x;
	private EditText repeticao;
	private EditText carga;
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
	private int altura;
	private int largura;
	private ArrayList<EditText> listaOrdem=new ArrayList<EditText>();
	private ArrayList<TextView> listaExercicio=new ArrayList<TextView>();
	private ArrayList<EditText> listaSerie=new ArrayList<EditText>();
	private ArrayList<EditText> listaRepeticao=new ArrayList<EditText>();
	private ArrayList<EditText> listaCarga=new ArrayList<EditText>();
	private int conta=0;
	private ArrayList<Integer> id_exercicio=new ArrayList<Integer>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exercicios);
		
		
		tabela=(TableLayout) findViewById(R.id.tabExercicios);
		avatar=(ImageView) findViewById(R.id.imgAvatar);
		nome=(TextView) findViewById(R.id.textNome);
		titulo=(LinearLayout) findViewById(R.id.barraNome);
		altura=avatar.getHeight();
		largura=avatar.getWidth();
		
		dataFound();
		//userInset();
		//exercicio();
		 //onOptionsItemSelected(menu.activity_triceps);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_triceps, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem items){
		
		if(items.getItemId()==R.id.menu_cancelar){
			sair();
		}
		if(items.getItemId()==R.id.menu_salvar){
			saveData();
		}
		return super.onOptionsItemSelected(items);
	}
		
	
	
	public void runActivity(Class<?> class1){
		Intent intent=new Intent(this,class1);
		startActivity(intent);
		finish();
	}
	
	public void runOpen(Class<?> class1){
		Intent intent=new Intent(this,class1);
		startActivity(intent);
		
	}
	
	public void sair(){
		finish();
		runActivity(com.example.fitnessdroid.PersonalizarActivity.class);
		
	}
	
	
	
	public void mensagens(Context context,String title,String message,String textP){
		
		AlertDialog.Builder builder=new AlertDialog.Builder(context);
		
		builder.setTitle(title);
		builder.setMessage(message);
		
			
			
		builder.setPositiveButton(textP,new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
				
				runActivity(com.example.fitnessdroid.PersonalizarActivity.class);
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
	
	public void userInset(){
		
		Bundle extras=getIntent().getExtras();
		
		if(extras!=null){
			varID=extras.getString("listar");
			variavel=extras.getString("grupo");
		}
		
		mudaTitulo(variavel);
		
		usuarioVO usuConsulta=new usuarioVO();
		
		
		Cursor usuCursor=dbUsu.idCursor(varID);
		if(usuCursor.moveToFirst()){
			//usuConsulta.setId(Integer.parseInt(usuCursor.getString(0)));
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
			
		}
		
		
		
		exercicioVO consulta=new exercicioVO();
		
		cExercicios=dbExec.idCursor(variavel);
		
		 conta=cExercicios.getCount();
		
		
		
				
	if(cExercicios.moveToFirst()){
			//int conta=cExercicios.getCount();
						
			int i=0;
			
			
			
			do{
				
				consulta.setId_exercicio(Integer.parseInt(cExercicios.getString(0)));
				consulta.setId_grupo(Integer.parseInt(cExercicios.getString(1)));
				consulta.setExercicio(cExercicios.getString(2));
				
				
				
				ordem=new EditText(this);
				exercicios=new TextView(this);
				serie=new EditText(this);
				
				
				
				
				o_x=new TextView(this);
				
				repeticao=new EditText(this);
				carga=new EditText(this);
				

				encaixa=new LinearLayout(this);
				//encaixa.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				encaixa.setGravity(Gravity.CENTER);
				linha=new TableRow(this);
				exercicios.setTextAppearance(this, android.R.attr.textAppearanceMedium);
				o_x.setTextAppearance(this, android.R.attr.textAppearanceMedium);
				
				exercicios.setTextSize(12);
				exercicios.setText(consulta.getExercicio().toString());
				int myExercicio=(int) consulta.getId_exercicio();
				id_exercicio.add(myExercicio);
				//repeticao[i].setTextAppearance(this, android.R.attr.textAppearanceMedium);
				//carga[i].setTextAppearance(this, android.R.attr.textAppearanceMedium);
				
				//exercicios[i]
				ordem.setEms(1);
				serie.setEms(2);
				repeticao.setEms(2);
				carga.setEms(2);
				
				ordem.setTextColor(Color.parseColor("#000000"));
				serie.setTextColor(Color.parseColor("#000000"));
				repeticao.setTextColor(Color.parseColor("#000000"));
				carga.setTextColor(Color.parseColor("#000000"));
				
				
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
				
				
				
				
				encaixa.addView(serie,new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				encaixa.addView(o_x,new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				encaixa.addView(repeticao,new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				
				listaOrdem.add(ordem);
				listaExercicio.add(exercicios);
				listaSerie.add(serie);
				listaRepeticao.add(repeticao);
				listaCarga.add(carga);
				
				linha.addView(ordem,new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				linha.addView(exercicios,new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				linha.addView(encaixa,new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				linha.addView(carga,new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				i++;
				
				
				
				tabela.addView(linha,new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				
			}while(cExercicios.moveToNext());
				
			
			
			
		}
		
		
	}
	
	public void dataFound(){
		
		
			
			Bundle extras=getIntent().getExtras();
			
			if(extras!=null){
				varID=extras.getString("listar");
				variavel=extras.getString("grupo");
				exercicioVO consulta=new exercicioVO();
				
				cExercicios=dbExec.idCursor(variavel);
				
				 conta=cExercicios.getCount();
				
			
			}
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
							
							ordem=new EditText(this);
							exercicios=new TextView(this);
							serie=new EditText(this);
														
							o_x=new TextView(this);
							
							repeticao=new EditText(this);
							carga=new EditText(this);
							
							ordem.setText(treinoConsulta.getOrdem().toString());
							serie.setText(treinoConsulta.getSerie().toString());
							repeticao.setText(treinoConsulta.getRepeticao().toString());
							carga.setText(treinoConsulta.getCarga().toString());
							
		
							encaixa=new LinearLayout(this);
							
							encaixa.setGravity(Gravity.CENTER);
							linha=new TableRow(this);
							exercicios.setTextAppearance(this, android.R.attr.textAppearanceMedium);
							o_x.setTextAppearance(this, android.R.attr.textAppearanceMedium);
							
							exercicios.setTextSize(12);
							exercicios.setText(treinoConsulta.getExercicio().toString());
							int myExercicio=(int) treinoConsulta.getId_exercicio();
							id_exercicio.add(myExercicio);
														
							ordem.setEms(1);
							serie.setEms(2);
							repeticao.setEms(2);
							carga.setEms(2);
							
							ordem.setTextColor(Color.parseColor("#000000"));
							serie.setTextColor(Color.parseColor("#000000"));
							repeticao.setTextColor(Color.parseColor("#000000"));
							carga.setTextColor(Color.parseColor("#000000"));
							
					
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
							
							
							encaixa.addView(serie,new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
							encaixa.addView(o_x,new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
							encaixa.addView(repeticao,new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
							
							listaOrdem.add(ordem);
							listaExercicio.add(exercicios);
							listaSerie.add(serie);
							listaRepeticao.add(repeticao);
							listaCarga.add(carga);
							
							linha.addView(ordem,new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
							linha.addView(exercicios,new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
							linha.addView(encaixa,new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
							linha.addView(carga,new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
						i++;
						
							
							tabela.addView(linha,new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
							
						}while(cTreino.moveToNext());
					
				
				
				
				}else{
					userInset();
				}
			
			
				
			}
		
	}
	
	
	public void saveData(){
		
		treinoVO treino=new treinoVO();
		int id_usuario=Integer.parseInt(varID.toString());
		
		String usuario=varID.toString();
		//Cursor idCursor=dbTreino.innerJoin(usuario, variavel);
		Cursor idCursor=dbTreino.idAndCursor(usuario,variavel);
		
		int jatem=idCursor.getCount();
		
		
		int myconta=0;
	
		//livrando dos campos em branco
		for(int i=0;i<conta;i++){
			
			if(listaOrdem.get(i).getText().toString()!=null && listaSerie.get(i).getText().toString()!=null && listaRepeticao.get(i).getText().toString()!=null && listaCarga.get(i).getText().toString()!=null){
				
				myconta++;
				
					if(jatem>0){	
		
						treino.setId_usuario(id_usuario);
						treino.setId_exercicio(Integer.parseInt(id_exercicio.get(i).toString()));
						treino.setId_grupo(Integer.parseInt(variavel));
						treino.setOrdem(listaOrdem.get(i).getText().toString());
						treino.setSerie(listaSerie.get(i).getText().toString());
						treino.setRepeticao(listaRepeticao.get(i).getText().toString());
						treino.setCarga(listaCarga.get(i).getText().toString());
						int idExercicio=Integer.parseInt(id_exercicio.get(i).toString());
						
						dbTreino.open();
						dbTreino.atualizar(treino,id_usuario,idExercicio);
						dbTreino.close();
						
					}else{
						treino.setId_usuario(id_usuario);
						treino.setId_exercicio(Integer.parseInt(id_exercicio.get(i).toString()));
						treino.setId_grupo(Integer.parseInt(variavel));
						treino.setOrdem(listaOrdem.get(i).getText().toString());
						treino.setSerie(listaSerie.get(i).getText().toString());
						treino.setRepeticao(listaRepeticao.get(i).getText().toString());
						treino.setCarga(listaCarga.get(i).getText().toString());
							
						dbTreino.open();
						dbTreino.inserir(treino);
						dbTreino.close();
					
					}
				
			}
						
			
		}
		
		mensagens(this,"Salvando Dados","Salvo com sucesso.","OK");	
					
			}
		
		
	
	public void exercicio(){
		
		String m=""+conta;
		
		listaCarga.get(2).setText(m);
		
	}
	
}
