package com.example.fitnessdroid;

import java.util.Calendar;

import com.example.fitnessdroid.R.layout;
import com.example.fitnessdroid.DAO.crudAtividadeDiariaDAO;
import com.example.fitnessdroid.DAO.crudHistoricoMedicoDAO;
import com.example.fitnessdroid.DAO.crudUsuarioDAO;
import com.example.fitnessdroid.POJO.atividadeDiariaVO;
import com.example.fitnessdroid.POJO.historicoMedicoVO;
import com.example.fitnessdroid.POJO.usuarioVO;

import android.R.id;
import android.os.Bundle;
import android.provider.VoicemailContract;

import android.app.Activity;
import android.app.LocalActivityManager;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;




public class FormAnamneseActivity extends Activity {
	//private Resources ressources = getResources(); 
	
    private TextView anamneseNome;
    private TextView anamneseNome2;
    private TextView anamneseSexo;
    private TextView anamneseIdade;
	private CheckBox menos_de_20;
	private CheckBox de_20_a_40;
	private CheckBox de_41_a_60;
	private CheckBox sentar_na_cadeira;
	private CheckBox carregar_peso;
	private CheckBox caminhar;
	private CheckBox dirigir;
	private CheckBox ficar_em_pe;
	private CheckBox estetica;
	private CheckBox lazer;
	private CheckBox terapeutico;
	private CheckBox convivio_social;
	private CheckBox emagrecimento;
	private CheckBox condicionamento;
	//hstorico
	private CheckBox pai;
	private CheckBox mae;
	private CheckBox irmao;
	private CheckBox avo;
	private CheckBox coracao;
	private CheckBox coluna;
	private CheckBox articulacoes;
	private CheckBox rim;
	private CheckBox pulmao;
	private CheckBox olhos;
	private CheckBox alcoolismo;
	private CheckBox asma;
	private CheckBox anemia;
	private CheckBox renal;
	private CheckBox ocular;
	private CheckBox hipertensao;
	private CheckBox ulcera;
	private CheckBox avc;
	private CheckBox artrite;
	private CheckBox diabetes;
	private CheckBox obesidade;
	
    private crudHistoricoMedicoDAO crudHistorico;
    private crudAtividadeDiariaDAO crudAtividade;
    private crudUsuarioDAO crudUsuario;
    
    private historicoMedicoVO voHistorico;
    private atividadeDiariaVO voAtividade;
    private usuarioVO voUsuario;
    
    private String idUsuario;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form_anamnese);
		
		  	anamneseNome=(TextView) findViewById(R.id.anamneseNome);
			anamneseNome2=(TextView) findViewById(R.id.anamneseNome2);
		    anamneseSexo=(TextView) findViewById(R.id.anameseSexo);
		    anamneseIdade=(TextView) findViewById(R.id.anameseIdade);
		identiFicacao();
		    
		    
		final TabHost th=(TabHost) findViewById(id.tabhost);
		th.setup();
		TabSpec spec=th.newTabSpec("Atividades");
		spec.setContent(R.id.tab1);
		spec.setIndicator("Atividades");
		th.addTab(spec);
		
		spec=th.newTabSpec("Historico");
		spec.setContent(R.id.tab2);
		spec.setIndicator("Histórico");
		th.addTab(spec);
		
			
		//hsitorico
		//pai_cardiopata
		//mae_cardiopata
		//irmao_cardiopata
		//avo_cardiopata
		//coluna
		//coracao
		//articulacao
		//rim
		//digestivo
		//pulmao
		//olhos
		//alcoolismo
		//asma
		//anemia
		//problema_renal
		//problema_ocular
		//hipertensao
		//ulcera
		//avc
		//artite
		//diabetes
		//obesidade
		
		//atividades diarias
		//id_atividade_diaria
		//horas_trabalhadas
		//cadeira
		//caminhar
		//dirigir
		//pesos
		//em_pe
		//estetica
		//lazer
		//	terapeutico
		//	convivio_social
		//	emagrecimento
		//	codicicionamento
		
		
		
		
		
				
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.form_anamnese, menu);
		return true;
	}
	 
	public boolean onOptionsItemSelected(MenuItem item){
		
		if(item.getItemId()==R.id.menu_cancelar){
			sair();
		}
		if(item.getItemId()==R.id.menu_salvar){
			novaAnamnese();
			sair();
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
	runActivity(com.example.fitnessdroid.AnamneseActivity.class);
	
}

public void reciveBundles(){
	Bundle extras=getIntent().getExtras();
	
	if(extras.getString("usuario")!=null){
		idUsuario=extras.getString("usuario");
		
	}
	
}
public void identiFicacao(){
	
	
	reciveBundles();
	
	voUsuario=new usuarioVO();
	crudUsuario=new crudUsuarioDAO(this);
	
	Cursor cursor=crudUsuario.idCursor(idUsuario);
	
	if(cursor.moveToFirst()){
		
		Calendar cal=Calendar.getInstance();
		int anoAtual=cal.get(Calendar.YEAR);
		
		
		String nascimento=cursor.getString(9);
		int ano=Integer.parseInt(nascimento.substring(0,4));
		
		int idade=anoAtual-ano;
		
		voUsuario.setNome(cursor.getString(1));
		voUsuario.setSexo(cursor.getString(10));
		
		anamneseNome.setText(voUsuario.getNome().toString());
		anamneseNome2.setText(voUsuario.getNome().toString());
		anamneseSexo.setText(voUsuario.getSexo().toString());
		anamneseIdade.setText(idade+" anos");
		
		
	}
	
}
public void novaAnamnese(){
	

	
	menos_de_20=(CheckBox) findViewById(R.id.menos_de_20);
	de_20_a_40=(CheckBox) findViewById(R.id.de_20_a_40);
	de_41_a_60=(CheckBox) findViewById(R.id.de_41_a_60);
	sentar_na_cadeira=(CheckBox) findViewById(R.id.sentar_na_cadeira);
	carregar_peso=(CheckBox) findViewById(R.id.carregar_peso);
	caminhar=(CheckBox) findViewById(R.id.caminhar);
	dirigir=(CheckBox) findViewById(R.id.dirigir);
	ficar_em_pe=(CheckBox) findViewById(R.id.ficar_em_pe);
	estetica=(CheckBox) findViewById(R.id.estetica);
	lazer=(CheckBox) findViewById(R.id.lazer);
	terapeutico=(CheckBox) findViewById(R.id.terapeutico);
	convivio_social=(CheckBox) findViewById(R.id.convivio_social);
	emagrecimento=(CheckBox) findViewById(R.id.emagrecimento);
	condicionamento=(CheckBox) findViewById(R.id.condicionamento);
	
	
	
	
	
	
	
	String horas=" ";
	 
	if(menos_de_20.equals(true)){
		
		horas="menos de 20";
		
	}
	if(de_20_a_40.equals(true)){
		
		horas="entre 20 e 40";
		
	}
	if(de_41_a_60.equals(true)){
		
		horas="entre 41 e 60";
		
	}
	
	 crudAtividade=new crudAtividadeDiariaDAO(this);
	 voAtividade=new atividadeDiariaVO();
	 
	 voAtividade.setHoras_trabalhadas(horas);
	 voAtividade.setCadeira(sentar_na_cadeira.callOnClick());
	 //voAtividade.setCadeira(sentar_na_cadeira.callOnClick());
	
	int myID=Integer.parseInt(idUsuario);	
	 
		Cursor cAtividade=crudAtividade.idCursor(idUsuario);
		
		voAtividade.setHoras_trabalhadas(horas);
		voAtividade.setCadeira(sentar_na_cadeira.isChecked());
		voAtividade.setPesos(carregar_peso.isChecked());
		voAtividade.setCaminhar(caminhar.isChecked());
		voAtividade.setDirigir(dirigir.isChecked());
		voAtividade.setEm_pe(ficar_em_pe.isChecked());
		voAtividade.setEstetica(estetica.isChecked());
		voAtividade.setLazer(lazer.isChecked());
		voAtividade.setTerapeutico(terapeutico.isChecked());
		voAtividade.setConvivio_social(convivio_social.isChecked());
		voAtividade.setEmagrecimento(emagrecimento.isChecked());
		voAtividade.setCodicicionamento(condicionamento.isChecked());
		voAtividade.setId_usuario(myID);
	
	
		crudAtividade.open();
		crudAtividade.inserir(voAtividade);
		crudAtividade.close();
		
		
		
			//atividades diarias
			//id_atividade_diaria
			//horas_trabalhadas
			//cadeira
			//caminhar
			//dirigir
			//pesos
			//em_pe
			//estetica
			//lazer
			//	terapeutico
			//	convivio_social
			//	emagrecimento
			//	codicicionamento
			
	
	
	 pai=(CheckBox) findViewById(R.id.pai);
	 mae=(CheckBox) findViewById(R.id.mae);
	irmao=(CheckBox) findViewById(R.id.irmao);
	 avo=(CheckBox) findViewById(R.id.avo);
	 coracao=(CheckBox) findViewById(R.id.coracao);
	 coluna=(CheckBox) findViewById(R.id.coluna);
	articulacoes=(CheckBox) findViewById(R.id.articulacao);
	rim=(CheckBox) findViewById(R.id.rim);
	pulmao=(CheckBox) findViewById(R.id.pulma);
	olhos=(CheckBox) findViewById(R.id.olhos);
	alcoolismo=(CheckBox) findViewById(R.id.alcoolismo);
	asma=(CheckBox) findViewById(R.id.asma);
	anemia=(CheckBox) findViewById(R.id.anemia);
	renal=(CheckBox) findViewById(R.id.renal);
	ocular=(CheckBox) findViewById(R.id.ocular);
	hipertensao=(CheckBox) findViewById(R.id.hipertensao);
	ulcera=(CheckBox) findViewById(R.id.ulcera);
	avc=(CheckBox) findViewById(R.id.avc);
	artrite=(CheckBox) findViewById(R.id.artrite);
	diabetes=(CheckBox) findViewById(R.id.diabetes);
	obesidade=(CheckBox) findViewById(R.id.obesidade);
	
	
	
	
	
	
	// crudHistorico.open();
	 //crudHistorico.inserir(myUsuario);
	 //crudHistorico.close();
	
}
	
	
	

}
