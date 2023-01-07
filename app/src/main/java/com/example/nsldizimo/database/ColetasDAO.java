package com.example.nsldizimo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nsldizimo.model.Coletas;
import com.example.nsldizimo.model.Conferente;
import com.example.nsldizimo.model.Conferida;

import java.util.ArrayList;
import java.util.List;

public class ColetasDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public ColetasDAO(Context context) {
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Coletas coleta){
        ContentValues values = new ContentValues();
        values.put("valorColeta",coleta.getValorColeta());
        values.put("dataColeta",coleta.getDataColeta());
        values.put("dia",coleta.getDia());
        values.put("mes",coleta.getMes());
        values.put("ano",coleta.getAno());
        return banco.insert("tb_coleta",null,values);

    }

    public List<Coletas> obterTodos(){
        Cursor cursor;
        ArrayList<Coletas> coletas = new ArrayList<>();
        cursor = banco.query("tb_coleta",new String[]{"idColeta","valorColeta","dataColeta","dia","mes","ano"},null,null,null,null,null);

        while(cursor.moveToNext()){
            Coletas a = new Coletas();
            a.setIdColeta(cursor.getInt(0));
            a.setValorColeta(cursor.getString(1));
            a.setDataColeta(cursor.getString(2));
            a.setDia(cursor.getInt(3));
            a.setMes(cursor.getInt(4));
            a.setAno(cursor.getInt(5));


            coletas.add(a);
        }
        return coletas;



    }

    public List<Coletas> filtrarData(String date) {
        Cursor cursor;
        ArrayList<Coletas> coletas = new ArrayList<>();
        cursor = banco.rawQuery("SELECT * from tb_coleta WHERE dataColeta = '" + date + "' ",null);

        while(cursor.moveToNext()){
            Coletas a = new Coletas();
            a.setIdColeta(cursor.getInt(0));
            a.setValorColeta(cursor.getString(1));
            a.setDataColeta(cursor.getString(2));


            coletas.add(a);
        }
        return coletas;
    }

    public List<Coletas> coletasAZ() {
        Cursor cursor;
        ArrayList<Coletas> coletas = new ArrayList<>();
        cursor = banco.rawQuery("SELECT * from tb_coleta ORDER BY ano,mes,dia ASC ",null);

        while(cursor.moveToNext()){
            Coletas a = new Coletas();
            a.setIdColeta(cursor.getInt(0));
            a.setValorColeta(cursor.getString(1));
            a.setDataColeta(cursor.getString(2));
            coletas.add(a);
        }
        return coletas;
    }
}
