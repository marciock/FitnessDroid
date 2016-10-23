package com.example.fitnessdroid;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ParseException;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessdroid.R.id;
import com.example.fitnessdroid.DAO.crudInstrutorDAO;
import com.example.fitnessdroid.DAO.crudUsuarioDAO;
import com.example.fitnessdroid.POJO.instrutorVO;
import com.example.fitnessdroid.POJO.usuarioVO;

@SuppressLint("SimpleDateFormat")
public class FormUsuariosActivity extends Activity {
	
	
	private String mySexo;
	private usuarioVO myUsuario;
	private crudUsuarioDAO myCrud;
	private crudInstrutorDAO crudInstrutor;
	private String myInstrutor;
	private HashMap<String,String> row= new HashMap<String,String>();
	private instrutorVO ivo;
	private String registro;
	private static Cursor cursor=null;
	private int instrutor;
	private String filePhoto;
	private String fileStorage;
	//private ImageView trocaImagem;
	private EditText nome;
	private EditText dia;
	private EditText mes;
	private EditText ano;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form_usuarios);
		crudInstrutor=new crudInstrutorDAO(this);
		 ivo=new instrutorVO();
		 TextView title=(TextView) findViewById(R.id.titleForm);
		// trocaImagem=(ImageView) findViewById(R.id.imageAvatar);
			title.setText("Novo - Usuário");
		loadBundle();
		abreFoto();
		
		AddItemOnSpinner();
		AddItemInstrutor();
		getFoto();
		insereFoto();
		
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
	
	
	
	
	
	public void novoUsuario(){
		
		
			nome=(EditText) findViewById(R.id.usuarioNome);
			dia=(EditText) findViewById(R.id.usuarioDia);
			mes=(EditText) findViewById(R.id.usuarioMes);
			ano=(EditText) findViewById(R.id.usuarioAno);	
		 String foto=getFoto();
		 EditText email=(EditText) findViewById(R.id.usuarioEmail);
		 EditText endereco=(EditText) findViewById(R.id.usuarioEndereco);
		 EditText cidade=(EditText) findViewById(R.id.usuarioCidade);
		 EditText estado=(EditText) findViewById(R.id.usuarioEstado);
		 EditText cep=(EditText) findViewById(R.id.usuarioCep);
		 EditText pais=(EditText) findViewById(R.id.usuarioPais);
		 
		 //sexo é a string mysexo
		 EditText fone1=(EditText) findViewById(R.id.usuarioFone1);
		 EditText fone2=(EditText) findViewById(R.id.usuarioFone2);
		// instrutor = 0;
		 //registro=row.get(myInstrutor);
		 //if(myInstrutor!=null){
			// instrutor=Integer.parseInt(registro);
		 //}
		 String myDate=ano.getEditableText().toString()+"-"+mes.getEditableText().toString()+"-"+dia.getEditableText().toString();
		 myCrud=new crudUsuarioDAO(this);
		 myUsuario=new usuarioVO();
		 
		 myUsuario.setNome(nome.getEditableText().toString());
		 myUsuario.setFoto(foto);
		 myUsuario.setEmail(email.getEditableText().toString());
		 myUsuario.setEndereco(endereco.getEditableText().toString());
		 myUsuario.setCidade(cidade.getEditableText().toString());
		 myUsuario.setEstado(estado.getEditableText().toString());
		 myUsuario.setCep(cep.getEditableText().toString());
		 myUsuario.setPais(pais.getEditableText().toString());
		 myUsuario.setNascimento(myDate);
		 myUsuario.setSexo(mySexo);
		 myUsuario.setFone1(fone1.getEditableText().toString());
		 myUsuario.setFone2(fone2.getEditableText().toString());
		 myUsuario.setInstrutor(0);
		 
		 myCrud.open();
		 myCrud.inserir(myUsuario);
		 myCrud.close();
		
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
	
	
	public void runDialog(){
		
		Dialog myDialog=new Dialog(FormUsuariosActivity.this);
		
		myDialog.setContentView(R.layout.activity_my_camera);
		myDialog.setTitle("Foto do usuario");
		myDialog.setCancelable(true);
		
		myDialog.show();
		
		
	}
	
	public void AddItemOnSpinner(){
		
		Spinner sexo=(Spinner) findViewById(id.spinnerSexo);
		
		List<String> list= new ArrayList<String>();
		
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
		runActivity(com.example.fitnessdroid.UsuariosActivity.class);
	}
	
	
	
	public void saveData(){
		
			novoUsuario();
			
			mensagens(this,"Salvando Dados","Salvo com sucesso!\n Cadastrar outro?","Sim","Não");	
		
		
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
		
		if(filePhoto!=null){
			foto=filePhoto;
		}
		
		if(fileStorage!=null){
			
			foto=fileStorage;
		}
		
		
		return foto;
		
		
		
	}
	
	public void loadBundle(){
		Bundle extras=getIntent().getExtras();
		if(extras!=null){
			
			if(extras.getString("BitmapImage")!=null){
				filePhoto=extras.getString("BitmapImage");
						
			}
			
			if(extras.getString("arquivo")!=null){
				fileStorage=extras.getString("arquivo");
			}
		}	
	}
	
	
	public void insereFoto(){
			
		ImageView trocaImagem=(ImageView) findViewById(R.id.imageAvatar);
		
		
		//if(filePhoto!=null){
			
		//	File urlPhoto=new File(filePhoto);
			
		//	if(urlPhoto.exists()){
		//		Bitmap bmp=BitmapFactory.decodeFile(filePhoto);
		//		trocaImagem.setImageBitmap(bmp);
				
		//	}
			
		//if(fileStorage!=null){
			File urlStorage=new File(getFoto().toString());
			
			if(urlStorage.exists()){
				
				
				//BitmapFactory.Options options=new BitmapFactory.Options();
				//options.inSampleSize=2;
				Bitmap bmp=BitmapFactory.decodeFile(getFoto().toString());
				trocaImagem.setImageBitmap(bmp);
				
			}
			
			
		}
		
	
	

}
	
	//Bundle extras=getIntent().getExtras();
		//String fileValue;
		
		//if(extras!=null){
	//	String fileValue=extras.getString("BitmapImage").toString();
			
			
		///	File image=new File(fileValue);	
		//	if(image.exists()){
		//		ImageView trocaImagem=(ImageView) findViewById(R.id.imageAvatar);
		//		Bitmap myBitmap=BitmapFactory.decodeFile(fileValue);
		///		trocaImagem.setImageBitmap(myBitmap);
			
		//	}
			//if(extras.getString("arquivo").isEmpty()){
			
			//	fileValue=extras.getString("arquivo").toString();
				
				
			//	image=new File(fileValue);	
			//	if(image.exists()){
			//		ImageView trocaImagem=(ImageView) findViewById(R.id.imageAvatar);
			//		Bitmap myBitmap=BitmapFactory.decodeFile(fileValue);
			//		trocaImagem.setImageBitmap(myBitmap);
			//	}	
				
				
				
			//}
		
		
			//}
			
		//}
		
	
	
	
	
	
