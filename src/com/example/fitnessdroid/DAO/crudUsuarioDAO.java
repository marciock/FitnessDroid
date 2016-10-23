package com.example.fitnessdroid.DAO;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ListAdapter;

import com.example.fitnessdroid.R;
import com.example.fitnessdroid.POJO.usuarioVO;

public class crudUsuarioDAO {
	private String nomeTabela="usuario";
	private SQLiteDatabase database;	
	private conexaoDAO db;
	private SimpleCursorAdapter adapterCursor;
	public crudUsuarioDAO(Context context){
		
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
	public void inserir(usuarioVO tbValue){
		//
		ContentValues value=new ContentValues();
		
		value.put("nome_usuario",tbValue.getNome());
		value.put("foto_usuario",tbValue.getFoto());
		value.put("email_usuario",tbValue.getEmail());
		value.put("endereco_usuario",tbValue.getEndereco());
		value.put("cidade_usuario",tbValue.getCidade());
		value.put("estado_usuario",tbValue.getEstado());
		value.put("cep_usuario",tbValue.getCep());
		value.put("pais_usuario",tbValue.getPais());
		value.put("nascimento_usuario",tbValue.getNascimento());
		value.put("sexo_usuario",tbValue.getSexo());
		value.put("fone1_usuario",tbValue.getFone1());
		value.put("fone2_usuario",tbValue.getFone2());
		value.put("id_instrutor",tbValue.getInstrutor());
		
		//
		
		database.insert("usuario", null, value);
		
		
		close();
		
	}
		
	public void atualizar(usuarioVO tbValue,long rowID){
		//
		ContentValues value=new ContentValues();
		
		value.put("nome_usuario",tbValue.getNome());
		value.put("foto_usuario",tbValue.getFoto());
		value.put("email_usuario",tbValue.getEmail());
		value.put("endereco_usuario",tbValue.getEndereco());
		value.put("cidade_usuario",tbValue.getCidade());
		value.put("estado_usuario",tbValue.getEstado());
		value.put("cep_usuario",tbValue.getCep());
		value.put("pais_usuario",tbValue.getPais());
		value.put("nascimento_usuario",tbValue.getNascimento());
		value.put("sexo_usuario",tbValue.getSexo());
		value.put("fone1_usuario",tbValue.getFone1());
		value.put("fone2_usuario",tbValue.getFone2());
		value.put("id_instrutor",tbValue.getInstrutor());
		
		//
		
		database.update("usuario", value, "id_usuario="+rowID, null);
		
		
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
