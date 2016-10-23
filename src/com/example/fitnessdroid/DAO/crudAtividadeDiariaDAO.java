package com.example.fitnessdroid.DAO;

import com.example.fitnessdroid.POJO.atividadeDiariaVO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v4.widget.SimpleCursorAdapter;

public class crudAtividadeDiariaDAO {

	private String nomeTabela="atividade_diaria";
	private SQLiteDatabase database;	
	private conexaoDAO db;
	private SimpleCursorAdapter adapterCursor;
	public crudAtividadeDiariaDAO(Context context){
		
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
	public void inserir(atividadeDiariaVO tbValue){
		//
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
		
		
		
		ContentValues value=new ContentValues();
		
		
		value.put("horas_trabalhadas",tbValue.getHoras_trabalhadas());
		value.put("cadeira",tbValue.getCadeira());
		value.put("caminhar",tbValue.getCaminhar());
		value.put("dirigir",tbValue.getDirigir());
		value.put("pesos",tbValue.getPesos());
		value.put("em_pe",tbValue.getEm_pe());
		value.put("estetica",tbValue.getEstetica());
		value.put("lazer",tbValue.getLazer());
		value.put("terapeutico",tbValue.getTerapeutico());
		value.put("convivio_social",tbValue.getConvivio_social());
		value.put("emagrecimento",tbValue.getEmagrecimento());
		value.put("codicicionamento",tbValue.getCodicicionamento());
		value.put("id_usuario",tbValue.getId_usuario());
		//
		
		database.insert("atividade_diaria", null, value);
		
		
		close();
		
	}
		
	public void atualizar(atividadeDiariaVO tbValue,long rowID){
		//
		ContentValues value=new ContentValues();
		
		value.put("horas_trabalhadas",tbValue.getHoras_trabalhadas());
		value.put("cadeira",tbValue.getCadeira());
		value.put("caminhar",tbValue.getCaminhar());
		value.put("dirigir",tbValue.getDirigir());
		value.put("pesos",tbValue.getPesos());
		value.put("em_pe",tbValue.getEm_pe());
		value.put("estetica",tbValue.getEstetica());
		value.put("lazer",tbValue.getLazer());
		value.put("terapeutico",tbValue.getTerapeutico());
		value.put("convivio_social",tbValue.getConvivio_social());
		value.put("emagrecimento",tbValue.getEmagrecimento());
		value.put("codicicionamento",tbValue.getCodicicionamento());
		value.put("id_usuario",tbValue.getId_usuario());
		
		//
		
		database.update("atividade_diaria", value, "id_usuario="+rowID, null);
		
		
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