package com.example.nsldizimo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nsldizimo.model.Conferente;

import java.util.ArrayList;
import java.util.List;

public class ConferenteDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public ConferenteDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Conferente conferente){
        ContentValues values = new ContentValues();
        values.put("nomeConfer",conferente.getNomeConferente());
        return banco.insert("tb_confer",null,values);
    }

    public List<Conferente> obterTodos(){
        List<Conferente> conferentes = new ArrayList<>();
        Cursor cursor = banco.query("tb_confer",new String[]{"idConfer","nomeConfer"},null,null,null,null,null);
        while(cursor.moveToNext()){
            Conferente a = new Conferente();
            a.setIdConferente(cursor.getInt(0));
            a.setNomeConferente(cursor.getString(1));
            conferentes.add(a);
        }
        return conferentes;
    }

    public List<Conferente>buscaDadosSpinner(){
        List<Conferente>conferentes = new ArrayList<>();
        Cursor cursor = banco.query("tb_confer",new String[]{"nomeConfer"},null,null,null,null,null);
        while (cursor.moveToNext()){
            Conferente a = new Conferente();
            a.setNomeConferente(cursor.getString(0));
            conferentes.add(a);
        }
       return conferentes;
    }

    public void excluir(Conferente a){
        banco.delete("tb_confer", "idConfer = ?", new String[]{a.getIdConferente().toString()} );
    }

    public void atualizar(Conferente a){
        ContentValues values = new ContentValues();
        values.put("nomeConfer",a.getNomeConferente());
        banco.update("tb_confer",values,"idConfer = ?",new String[]{a.getIdConferente().toString()});


    }
}
