package com.example.fitnessdroid.DAO;

import com.example.fitnessdroid.POJO.usuarioVO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class crudDAO {
	private SQLiteDatabase database;	
	private conexaoDAO db;
	public crudDAO(Context context){
		
		db=new conexaoDAO(context);
		
		//db.openDataBase();
	}
	
	//public String[] makeColunas(String[] colunas){
		
		//colunas=colunas;
		
		//return colunas;
		
	//}
	
	
	
	public void open() throws SQLiteException{
		database=db.getWritableDatabase();
	}
	public void close(){
		if (database!=null){
			database.close();
		}
	}
	public void inserir(usuarioVO tbValue){
		//
		ContentValues value=new ContentValues();
		
		value.put("nome_usuario",tbValue.getNome());
		value.put("foto_usuario",tbValue.getFoto());
		value.put("email_usuario",tbValue.getEmail());
		value.put("endereco_usuario",tbValue.getEndereco());
		value.put("cidade_usuario",tbValue.getCidade());
		//value.put("nome_usuario",tbValue.getNome());
		//value.put("nome_usuario",tbValue.getNome());
		//value.put("nome_usuario",tbValue.getNome());
		//value.put("nome_usuario",tbValue.getNome());
		//value.put("nome_usuario",tbValue.getNome());
		//value.put("nome_usuario",tbValue.getNome());
		//value.put("nome_usuario",tbValue.getNome());
		//value.put("nome_usuario",tbValue.getNome());
		
		//
		
		database.insert("usuario", null, value);
		
		
		close();
		
	}
	
	
}
