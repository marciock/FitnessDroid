package com.example.fitnessdroid.DAO;

import com.example.fitnessdroid.POJO.historicoMedicoVO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v4.widget.SimpleCursorAdapter;

public class crudHistoricoMedicoDAO {

	private String nomeTabela="historico_medico";
	private SQLiteDatabase database;	
	private conexaoDAO db;
	private SimpleCursorAdapter adapterCursor;
	
	public crudHistoricoMedicoDAO(Context context){
		
		db=new conexaoDAO(context);
}

	public void open() throws SQLiteException{
		database=db.getWritableDatabase();
	}
	public void close(){
		if (database!=null){
			database.close();
		}
	}
	public void inserir(historicoMedicoVO tbValue){
		//
		ContentValues value=new ContentValues();
		
		
		value.put("id_usuario",tbValue.getId_usuario());
		value.put("pai_cardiopata",tbValue.getPai_cardiopata());
		value.put("mae_cardiopata",tbValue.getMae_cardiopata());
		value.put("irmao_cardiopata",tbValue.getIrmao_cardiopata());
		value.put("avo_cardiopata",tbValue.getAvo_cardiopata());
		value.put("coluna",tbValue.getColuna());
		value.put("coracao",tbValue.getCoracao());
		value.put("rim",tbValue.getRim());
		value.put("digestivo",tbValue.getDigestivo());
		value.put("pulmao",tbValue.getPulmao());
		value.put("olhos",tbValue.getOlhos());
		value.put("alcoolismo",tbValue.getAlcoolismo());
		value.put("asma",tbValue.getAsma());
		value.put("anemia",tbValue.getAnemia());
		value.put("problema_renal",tbValue.getProblema_renal());
		value.put("hipertensao",tbValue.getHipertensao());
		value.put("ulcera",tbValue.getUlcera());
		value.put("avc",tbValue.getAvc());
		value.put("artite",tbValue.getArtite());
		value.put("diabetes",tbValue.getDiabetes());
		value.put("obesidade",tbValue.getObesidade());
		
		
		//
		
		database.insert("Historico_Medico", null, value);
		
		
		close();
		
	}
		
	public void atualizar(historicoMedicoVO tbValue,long rowID){
		//
		ContentValues value=new ContentValues();
		
		value.put("id_usuario",tbValue.getId_usuario());
		value.put("pai_cardiopata",tbValue.getPai_cardiopata());
		value.put("mae_cardiopata",tbValue.getMae_cardiopata());
		value.put("irmao_cardiopata",tbValue.getIrmao_cardiopata());
		value.put("avo_cardiopata",tbValue.getAvo_cardiopata());
		value.put("coluna",tbValue.getColuna());
		value.put("coracao",tbValue.getCoracao());
		value.put("rim",tbValue.getRim());
		value.put("digestivo",tbValue.getDigestivo());
		value.put("pulmao",tbValue.getPulmao());
		value.put("olhos",tbValue.getOlhos());
		value.put("alcoolismo",tbValue.getAlcoolismo());
		value.put("asma",tbValue.getAsma());
		value.put("anemia",tbValue.getAnemia());
		value.put("problema_renal",tbValue.getProblema_renal());
		value.put("hipertensao",tbValue.getHipertensao());
		value.put("ulcera",tbValue.getUlcera());
		value.put("avc",tbValue.getAvc());
		value.put("artite",tbValue.getArtite());
		value.put("diabetes",tbValue.getDiabetes());
		value.put("obesidade",tbValue.getObesidade());
		
		//
		
		database.update("historico_medico", value, "id_usuario="+rowID, null);
		
		
		close();
		
	}
	
	
	public Cursor tbCursor(){
		

		String selectQuery="SELECT  * FROM "+nomeTabela;
				SQLiteDatabase myDb=db.getWritableDatabase();
				Cursor cursor=myDb.rawQuery(selectQuery, null);
				
				return cursor;
	
	}
	
	public Cursor idCursor(String id){
		
		String selectQuery="SELECT  * FROM "+nomeTabela+" WHERE id_usuario="+id;
		SQLiteDatabase myDb=db.getWritableDatabase();
		Cursor cursor=myDb.rawQuery(selectQuery, null);
		
		return cursor;
	}
	
	
	
	public boolean deleteConfirm(long rowId)
	{
		open();
	return database.delete(nomeTabela, "id_usuario=" + rowId, null) > 0;
	}
}