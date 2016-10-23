package com.example.fitnessdroid.DAO;

import com.example.fitnessdroid.POJO.instrutorVO;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class crudInstrutorDAO {
	
	private String nomeTabela="instrutor";
	private SQLiteDatabase database;	
	private conexaoDAO db;
	public crudInstrutorDAO(Context context){
		
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
	public void inserir(instrutorVO tbValue){
		//
		ContentValues value=new ContentValues();
		
		value.put("instrutor",tbValue.getInstrutor());
		value.put("foto_instrutor",tbValue.getFoto());
		value.put("email_instrutor",tbValue.getEmail());
		value.put("fone_instrutor",tbValue.getFone());
		value.put("descricao_instrutor",tbValue.getDescricao());
		
		
		database.insert("instrutor", null, value);
		
		
		
		close();
		
	}
	
	public void atualizar(instrutorVO tbValue,long rowID){
	
		ContentValues value=new ContentValues();
		
		value.put("instrutor",tbValue.getInstrutor());
		value.put("foto_instrutor",tbValue.getFoto());
		value.put("email_instrutor",tbValue.getEmail());
		value.put("fone_instrutor",tbValue.getFone());
		value.put("descricao_instrutor",tbValue.getDescricao());
		
		database.update("instrutor", value, "id_instrutor="+rowID, null);
	
		close();
	}
	public Cursor tbCursor(){
			String selectQuery="SELECT  * FROM "+nomeTabela;
			SQLiteDatabase myDb=db.getWritableDatabase();
			Cursor cursor=myDb.rawQuery(selectQuery, null);
			
			return cursor;
		
		}
	public Cursor idCursor(String id){
		
		String selectQuery="SELECT  * FROM "+nomeTabela+" WHERE id_instrutor="+id;
		SQLiteDatabase myDb=db.getWritableDatabase();
		Cursor cursor=myDb.rawQuery(selectQuery, null);
		
		return cursor;
	}
	
	public Cursor nameCursor(String name){
			
			String selectQuery="SELECT  * FROM "+nomeTabela+" WHERE instrutor="+name;
			SQLiteDatabase myDb=db.getWritableDatabase();
			Cursor cursor=myDb.rawQuery(selectQuery, null);
			
			return cursor;
		}
	
	public boolean deleteConfirm(long rowId)
	{
		open();
	return database.delete(nomeTabela, "id_instrutor=" + rowId, null) > 0;
	}
			
}
