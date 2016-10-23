package com.example.fitnessdroid;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.fitnessdroid.DAO.crudExercicioDAO;
import com.example.fitnessdroid.DAO.crudInstrutorDAO;
import com.example.fitnessdroid.POJO.exercicioVO;
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
import android.text.InputType;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class FormManagerExercicesActivity extends Activity {
	private TextView exercicios;
	private EditText editExercicios;
	private ListView lListExercicios;
	private Cursor cExercicios=null;
	private crudExercicioDAO dbExec=new crudExercicioDAO(this);
	private exercicioVO myExercicio;
	private String variavel=null;
	private String varID=null;
	private int conta=0;
	private int intGrupo=0;
	private ArrayList<Integer> id_exercicio=new ArrayList<Integer>();
	private ArrayList<TextView> listaExercicio=new ArrayList<TextView>();
	private EditText listaEdit;
	private ArrayAdapter<String> listAdapter;
	private LinearLayout addNew;
	private ArrayList<String> excluir=new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form_manager_exercices);
		userInset();
		novoCampo();
		
		excluiCampo();
		
		
		addNew=(LinearLayout) findViewById(R.id.lListExercices);;
		
		//lExercices=(LinearLayout) findViewById(R.id.lListExercices);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.form_manager_exercices, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item){
		
		if(item.getItemId()==R.id.menu_cancelar){
			sair();
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public void sair(){
		finish();
	}
	
	
	public void saveData(){
		
		int tamanho=listaEdit.getText().length();
		
		if(tamanho>0){
			myExercicio=new exercicioVO();
			
			intGrupo=Integer.parseInt(variavel); 
			 
			
			 myExercicio.setId_grupo(intGrupo);
			 myExercicio.setExercicio(listaEdit.getText().toString());
			 
			 dbExec.open();
			 dbExec.inserir(myExercicio);
			 dbExec.close();
		}
		
		
		
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
	public void runActivity(Class<?> class1,String receptor1,String var1){
		//var=variavel;
		Intent intent=new Intent(this,class1);
		intent.putExtra(receptor1, var1);
		intent.putExtra("grupo", variavel);
		startActivity(intent);
		
	
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
		
		
		exercicioVO consulta=new exercicioVO();
		
		cExercicios=dbExec.idCursor(variavel);
		
		 conta=cExercicios.getCount();
			
	if(cExercicios.moveToFirst()){
			//int conta=cExercicios.getCount();
						
			int i=0;
			
			 lListExercicios=(ListView) findViewById(R.id.listExercices);
			List<String> item=new ArrayList<String>();	
			do{
				
				consulta.setId_exercicio(Integer.parseInt(cExercicios.getString(0)));
				consulta.setId_grupo(Integer.parseInt(cExercicios.getString(1)));
				consulta.setExercicio(cExercicios.getString(2));
				
				
				String varia=consulta.getExercicio().toString();
						
						
				item.add(varia);
				
				int myExercicio=(int) consulta.getId_exercicio();
				id_exercicio.add(myExercicio);
				
				i++;
				
				
			}while(cExercicios.moveToNext());
				
			listAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,item);
			lListExercicios.setChoiceMode(lListExercicios.CHOICE_MODE_MULTIPLE);
			lListExercicios.setAdapter(listAdapter);
			
			
			
			
		}
		
			lListExercicios.setOnItemClickListener(new ListView.OnItemClickListener() {
		        @Override
		        public void onItemClick(AdapterView<?> a, View v, int i, long l) {
		            try {
		                // Remembers the selected Index
		            	SparseBooleanArray checked=lListExercicios.getCheckedItemPositions();		            	
		            	
		            	
		            	for(int ii=0;ii< checked.size();ii++){
		            		int position=checked.keyAt(ii); 
		            		if(checked.valueAt(ii)){;
		            			excluir.add(a.getAdapter().getItem(position).toString());
		            		
		            		}
		            	}
		            		
		            	
		            	
		            }
		            catch(Exception e) {
		                System.out.println("não existe item clicado");
		            }
		        }
		    });
	}
	


public void novoCampo(){
	listaEdit=new EditText(this);
	final AlertDialog.Builder builder=new AlertDialog.Builder(this); 
	
	LinearLayout addExercices=(LinearLayout) findViewById(R.id.laddExercices);
		
	addExercices.setClickable(true);
	addExercices.setOnClickListener(new OnClickListener() {
			
		@Override
		public void onClick(View v) {
			
			
			
			builder.setTitle("Novo Exercício");
			builder.setMessage("Digite o novo Exercício.");
			builder.setNegativeButton("Salvar",new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					saveData();
					finish();
					runActivity(com.example.fitnessdroid.FormManagerExercicesActivity.class,"","");
				}
			} );
			builder.setPositiveButton("Cancelar",new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					finish();
					runActivity(com.example.fitnessdroid.FormManagerExercicesActivity.class,"","");
				}
			});
			
			builder.setView(listaEdit);
			
			builder.show();
			
			
		}
	});
	
	
}

public void excluiCampo(){
	
	 LinearLayout delExercices=(LinearLayout) findViewById(R.id.ldelExercices);
	 
	 delExercices.setClickable(true);
	 delExercices.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			for(int i=0;i>excluir.size();i++){
				Cursor cursor=dbExec.fieldCursor(excluir.get(i));
				String id=cursor.getString(0);
				exercicioVO evo=new exercicioVO();
				evo.setId_exercicio(Integer.parseInt(cursor.getString(0)));
				
				if(id !=null){
					dbExec.deleteConfirm(Integer.parseInt(cursor.getString(0)));
					finish();
					runActivity(com.example.fitnessdroid.FormManagerExercicesActivity.class,"","");
				}
			}
				
			
		}
	});
	
}

	public void msnInsert(Context context,String title,String message,String textP,String textN){
	
		
	
		
	
	}


}
