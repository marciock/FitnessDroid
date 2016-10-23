package com.example.fitnessdroid;

import java.util.Calendar;

import com.example.fitnessdroid.DAO.crudMedidasDAO;
import com.example.fitnessdroid.DAO.crudUsuarioDAO;
import com.example.fitnessdroid.POJO.medidasVO;
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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class FormMedidasActivity extends Activity {
	private ImageView foto;
	private TextView nome;
	private EditText idade;
	private EditText peso;
	private EditText altura;
	private TextView imc;
	private EditText tronco;
	private EditText ombros;
	private EditText cintura;
	private EditText braco;
	private EditText coxa;
	private EditText panturrilha;
	private EditText triciptal;
	private EditText peitoral;
	private EditText medioaxilar;
	private EditText subescapular;
	private EditText suprailiaca;
	private EditText dobraCoxa;
	private EditText abdominal;
	private TextView calculoDobras;
	private TextView calculoSemDobras;
	private String id_usuario;
	private medidasVO myMedidas;
	private crudMedidasDAO myMedidasCrud;
	private usuarioVO myUsuario;
	private crudUsuarioDAO myUsuarioCrud;
	private  Cursor cursorUsuario;
	private double calculoDensidade;
	private String mySexo;
	private int myIdade;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form_medidas);
		

	
		requestExtras();
		
		
		usuComplete();
		loadForm();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.form_medidas, menu);
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
		finish();
		runActivity(com.example.fitnessdroid.MedidasActivity.class);
	}
	
	public void saveData(){
			
			novaMedida();
			
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
	
	public void requestExtras(){
		//&& requestCode==REQUEST_CODE
		
			Bundle extras=getIntent().getExtras();
			
			if(extras!=null){
				id_usuario=extras.getString("usuario");
				
			}
			
	}
	
	public void usuComplete(){
		
		foto=(ImageView)findViewById(R.id.imageAvatar);
		  nome=(TextView)findViewById(R.id.editNome);
		
		 myUsuarioCrud=new crudUsuarioDAO(this);
		 
		 //myUsuario.setNome(nome.getEditableText().toString());
		// myUsuario.setFoto(foto.toString());
		
		// if(id_usuario!=null){
			  cursorUsuario=myUsuarioCrud.idCursor(id_usuario);
		
			if(cursorUsuario.moveToFirst()){
			  do{
				
				  
				  
				  myUsuario=new usuarioVO();
			  
						 
					myUsuario.setNome(cursorUsuario.getString(1));
			  		myUsuario.setFoto(cursorUsuario.getString(2));
			  		myUsuario.setSexo(cursorUsuario.getString(10));
			  
			  		//idade
			  		Calendar cal=Calendar.getInstance();
					int anoAtual=cal.get(Calendar.YEAR);
					
					
					String nascimento=cursorUsuario.getString(9);
					int ano=Integer.parseInt(nascimento.substring(0,4));
					
					 myIdade=anoAtual-ano;
			  		
			  		
			  		
			  		String myNome=myUsuario.getNome().toString();
					 String myFoto=myUsuario.getFoto().toString();
					 mySexo=myUsuario.getSexo().toString();
					nome.setText(myNome);
					if(myFoto!=null){
						Bitmap myBitmap=BitmapFactory.decodeFile(myFoto);
						foto.setImageBitmap(myBitmap);
					}
		
			}while(cursorUsuario.moveToNext());
				}
	}
	
	
	public void jacksonPollok(String sexo){
		
		//variaveis para mulheres
		
		double tc=Double.parseDouble(triciptal.getText().toString());
		double sp=Double.parseDouble(suprailiaca.getText().toString());
		double cx=Double.parseDouble(coxa.getText().toString());
		
		
		//double DcFeminino;
		double x1Feminino=tc+sp+cx;
		double x2Feminino=myIdade;
		
		//variaveis para masculino
		
		double pt=Double.parseDouble(peitoral.getText().toString());
		double ab=Double.parseDouble(abdominal.getText().toString());
		
		
		//double DcMasculino;
		double x1Masculino=pt+ab+cx;
		double x2Masculino=myIdade;
		
		//calculo feminino
		if(sexo=="feminino"){
			calculoDensidade=1.099421-0.0009929*(x1Feminino)+0.0000023*Math.pow(x1Feminino,2)-0.0001392*(x2Feminino);
		}
		
		if(sexo=="masculino"){
			calculoDensidade=1.10938-0.0008267*(x1Masculino)+0.0000016*Math.pow(x1Masculino,2)-0.0002574*(x2Masculino);
			
		}
		
	}
	
	public void loadForm(){
		
		  idade=(EditText)findViewById(R.id.editIdade);
		  peso=(EditText)findViewById(R.id.editPeso);
		  altura=(EditText)findViewById(R.id.editAltura);
		  imc=(TextView)findViewById(R.id.textIMC);
		  tronco=(EditText)findViewById(R.id.editTronco);
		  ombros=(EditText)findViewById(R.id.editOmbros);
		  cintura=(EditText)findViewById(R.id.editCintura);
		  braco=(EditText)findViewById(R.id.editBraco);
		  coxa=(EditText)findViewById(R.id.editCoxa);
		  panturrilha=(EditText)findViewById(R.id.editPanturrilha);
		  triciptal=(EditText)findViewById(R.id.editTriciptal);
		  peitoral=(EditText)findViewById(R.id.editPeitoral);
		  medioaxilar=(EditText)findViewById(R.id.editmedioaxilar);
		  subescapular=(EditText)findViewById(R.id.editSubescapular);
		  suprailiaca=(EditText)findViewById(R.id.editSuprailiaca);
		  dobraCoxa=(EditText)findViewById(R.id.editDobraCoxa);
		  abdominal=(EditText)findViewById(R.id.editAbdominal);
		  calculoDobras=(TextView)findViewById(R.id.textDobras);
		  calculoSemDobras=(TextView)findViewById(R.id.textResultadoCircunferencia);
		
		
	}
	public void novaMedida(){
		
		
		 // idUsuario;
		
		// idade.getText().toString();
		//  peso.getText().toString();
		//  altura.getText().toString();
		//  imc.getText().toString();
		//  tronco.getText().toString();
		//  ombros.getText().toString();
		//  cintura.getText().toString();
		//  braco.getText().toString();
		//  coxa.getText().toString();
		//  panturrilha.getText().toString();
		//  triciptal.getText().toString();
		//  peitoral.getText().toString();
		//  medioaxilar.getText().toString();
		//  subescapular.getText().toString();
		//	dobraCoxa.getText().toString();
		//	abdominal.getText().toString(); 
		//  calculoDobras.getText().toString();
		//  calculoSemDobras.getText().toString();
		
		
		 
		 
		 myMedidasCrud=new crudMedidasDAO(this);
		 myMedidas=new medidasVO();
		 
		 
		 //myMedidas.setId_usuario(id_usuario.toString());
		 
		
		
		 
		 myMedidasCrud.open();
		 myMedidasCrud.inserir(myMedidas);
		 myMedidasCrud.close();
	}

}
