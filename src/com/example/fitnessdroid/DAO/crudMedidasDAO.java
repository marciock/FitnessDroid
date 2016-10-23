package com.example.fitnessdroid.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v4.widget.SimpleCursorAdapter;

import com.example.fitnessdroid.POJO.medidasVO;

public class crudMedidasDAO {

	private String nomeTabela="medidas";
	private SQLiteDatabase database;	
	private conexaoDAO db;
	private SimpleCursorAdapter adapterCursor;
	
	public crudMedidasDAO(Context context){
		
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
	public void inserir(medidasVO tbValue){
		//
		ContentValues value=new ContentValues();
		
				
		
		
		value.put("id_medida",tbValue.getId_medida());
		value.put("idade",tbValue.getIdade());
		value.put("altura",tbValue.getAltura());
		value.put("pesos",tbValue.getPesos());
		value.put("imc",tbValue.getImc());
		value.put("tronco",tbValue.getTronco());
		value.put("ombros",tbValue.getOmbros());
		value.put("cintura",tbValue.getCintura());
		value.put("braco",tbValue.getBraco());
		value.put("perna",tbValue.getPerna());
		value.put("panturrilha",tbValue.getPanturrilha());
		value.put("triciptal",tbValue.getTriciptal());
		value.put("peitoral",tbValue.getPeitoral());
		value.put("sub_axilar",tbValue.getSub_axilar());
		value.put("supra_iliaca",tbValue.getSupra_iliaca());
		value.put("abdominal",tbValue.getAbdominal());
		value.put("coxa",tbValue.getCoxa());
		value.put("com_adpometro",tbValue.getCom_adpometro());
		value.put("sem_adpometro",tbValue.getSem_adpometro());
		
		
		
		//
		
		database.insert("medidas", null, value);
		
		
		close();
		
	}
		
	public void atualizar(medidasVO tbValue,long rowID){
		//
		ContentValues value=new ContentValues();
		
		value.put("id_medida",tbValue.getId_medida());
		value.put("idade",tbValue.getIdade());
		value.put("altura",tbValue.getAltura());
		value.put("pesos",tbValue.getPesos());
		value.put("imc",tbValue.getImc());
		value.put("tronco",tbValue.getTronco());
		value.put("ombros",tbValue.getOmbros());
		value.put("cintura",tbValue.getCintura());
		value.put("braco",tbValue.getBraco());
		value.put("perna",tbValue.getPerna());
		value.put("panturrilha",tbValue.getPanturrilha());
		value.put("triciptal",tbValue.getTriciptal());
		value.put("peitoral",tbValue.getPeitoral());
		value.put("sub_axilar",tbValue.getSub_axilar());
		value.put("supra_iliaca",tbValue.getSupra_iliaca());
		value.put("abdominal",tbValue.getAbdominal());
		value.put("coxa",tbValue.getCoxa());
		value.put("com_adpometro",tbValue.getCom_adpometro());
		value.put("sem_adpometro",tbValue.getSem_adpometro());
		//
		
		database.update("medidas", value, "id_medida="+rowID, null);
		
		
		close();
		
	}
	
	
	public Cursor tbCursor(){
		

		String selectQuery="SELECT  * FROM "+nomeTabela;
				SQLiteDatabase myDb=db.getWritableDatabase();
				Cursor cursor=myDb.rawQuery(selectQuery, null);
				
				return cursor;
	
	}
	
	public Cursor idCursor(String id){
		
		String selectQuery="SELECT  * FROM "+nomeTabela+" WHERE id_medida="+id;
		SQLiteDatabase myDb=db.getWritableDatabase();
		Cursor cursor=myDb.rawQuery(selectQuery, null);
		
		return cursor;
	}
	
	
	
	public boolean deleteConfirm(long rowId)
	{
		open();
	return database.delete(nomeTabela, "id_medida=" + rowId, null) > 0;
	}
	
}
