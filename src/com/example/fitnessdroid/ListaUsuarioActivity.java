package com.example.fitnessdroid;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.fitnessdroid.DAO.crudInstrutorDAO;
import com.example.fitnessdroid.DAO.crudUsuarioDAO;
import com.example.fitnessdroid.POJO.instrutorVO;
import com.example.fitnessdroid.POJO.usuarioVO;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class ListaUsuarioActivity extends Activity {
	private usuarioVO myUsuario;
	private crudUsuarioDAO myCrud;
	private instrutorVO ivo;
	private crudInstrutorDAO crudInstrutor;
	private String myInstrutor;
	private EditText nome;
	private ImageView foto;
	private String fotoPath;
	private EditText email;
	private EditText endereco;
	private EditText cidade;
	private EditText estado;
	private EditText cep;
	private EditText pais;
	private EditText dia;
	private EditText mes;
	private EditText ano;
	private String mySexo; 
	private EditText fone1;
	private EditText fone2;
	private HashMap<String,String> row= new HashMap<String,String>();
	private int instrutor;
	private long rowID;
	private String variavel;
	private String registro;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form_usuarios);
		TextView title=(TextView) findViewById(R.id.titleForm);
		
		title.setText("Visualizar - Usuário");
		//inflaCampos();
		foto=(ImageView)findViewById(R.id.imageAvatar);
		  nome=(EditText) findViewById(R.id.usuarioNome);
		  email=(EditText) findViewById(R.id.usuarioEmail);
		  endereco=(EditText) findViewById(R.id.usuarioEndereco);
		  cidade=(EditText) findViewById(R.id.usuarioCidade);
		  estado=(EditText) findViewById(R.id.usuarioEstado);
		  cep=(EditText) findViewById(R.id.usuarioCep);
		  pais=(EditText) findViewById(R.id.usuarioPais);
		  dia=(EditText) findViewById(R.id.usuarioDia);
		  mes=(EditText) findViewById(R.id.usuarioMes);
		  ano=(EditText) findViewById(R.id.usuarioAno);
		  fone1=(EditText) findViewById(R.id.usuarioFone1);
		  fone2=(EditText) findViewById(R.id.usuarioFone2);
		  
		  myCrud=new crudUsuarioDAO(this);
		  myUsuario=new usuarioVO();
		 
		  
		 AddItemOnSpinner();
		inflaCampos();
		 
		
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_form_usuarios, menu);
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
		variavel=extras.getString("listar");
		
	}
	public void inflaCampos(){
		
		//faz consulta pelo id e infla os dados nos formularios
		
		recebeString();
		
		//if(extras!=null){
		
			
//		}else{
			
	//	}
		Cursor cursor=myCrud.idCursor(variavel);
		
		if(cursor.moveToFirst()){
		
			//usuarioVO consulta=new usuarioVO();
			myUsuario.setId(Integer.parseInt(cursor.getString(0)));
			myUsuario.setNome(cursor.getString(1));
			myUsuario.setFoto(cursor.getString(2));
			myUsuario.setEmail(cursor.getString(3));
			myUsuario.setEndereco(cursor.getString(4));
			myUsuario.setCidade(cursor.getString(5));
			myUsuario.setEstado(cursor.getString(6));
			myUsuario.setCep(cursor.getString(7));
			myUsuario.setPais(cursor.getString(8));
			myUsuario.setNascimento(cursor.getString(9));
			myUsuario.setSexo(cursor.getString(10));
			myUsuario.setFone1(cursor.getString(11));
			myUsuario.setFone2(cursor.getString(12));
			//myUsuario.setInstrutor(cursor.getLong(13));
			
			
			nome.setText(myUsuario.getNome().toString());
			
			String pathName=myUsuario.getFoto().toString();
			File url=new File(pathName);
			
			if(url.exists()){
				Bitmap bmp=BitmapFactory.decodeFile(pathName);
				foto.setImageBitmap(bmp);
				
			}
			//else{
				//foto.setImageResource(R.id.imageAvatar);
			//}
			
			email.setText(myUsuario.getEmail().toString());
			endereco.setText(myUsuario.getEndereco().toString());
			cidade.setText(myUsuario.getCidade().toString());
			estado.setText(myUsuario.getEstado().toString());
			cep.setText(myUsuario.getCep().toString());
			pais.setText(myUsuario.getPais().toString());
			//nascimento.setText(myUsuario.getNascimento().toString());
			mySexo=myUsuario.getSexo().toString();
			fone1.setText(myUsuario.getFone1().toString());
			fone2.setText(myUsuario.getFone2().toString());
			
			//fotoPath=myUsuario.getFoto().toString();
		
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
	
	
	
	
	public void AddItemOnSpinner(){
		
		Spinner sexo=(Spinner) findViewById(id.spinnerSexo);
		
		List<String> list= new ArrayList<String>();
//		list.add(mySexo);
		list.add("Escolha o sexo");
		list.add("Masculino");
		list.add("Feminino");
		
		ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
		
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		sexo.setAdapter(adapter);
		
		
		sexo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			
			public void onItemSelected(AdapterView<?> parent, View v,int posicao,long id){
				
				mySexo=parent.getItemAtPosition(posicao).toString();
			}
			public void onNothingSelected(AdapterView<?> parent){
				
			}
			
		});
	}
		
		public void AddItemInstrutor(){
			
			
			Spinner instrutor=(Spinner) findViewById(id.spinnerInstrutor);
			List<String> list= new ArrayList<String>();
			
				Cursor cursor=null;
				cursor=crudInstrutor.tbCursor();
			
				
			if(cursor.moveToFirst()){
				do{
					
					ivo.setInstrutor(cursor.getString(1));
					
					list.add(ivo.getInstrutor().toString());
					row.put(cursor.getString(1),cursor.getString(0));
					
				}while(cursor.moveToNext());
			}else{
			list.add("Nenhum");
			}
			ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
			
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			instrutor.setAdapter(adapter);
			instrutor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
				
				public void onItemSelected(AdapterView<?> parent, View v,int posicao,long id){
					
					myInstrutor=parent.getItemAtPosition(posicao).toString();
					
					
				}
				public void onNothingSelected(AdapterView<?> parent){
					
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
				runActivity(com.example.fitnessdroid.FormUsuariosActivity.class);
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
