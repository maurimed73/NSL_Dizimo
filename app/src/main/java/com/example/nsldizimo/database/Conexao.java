package com.example.nsldizimo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexao extends SQLiteOpenHelper {

    private static final String name = "banco.db";
    private static final int version = 1;
    public Conexao(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table IF NOT EXISTS tb_confer(idConfer integer primary key autoincrement, nomeConfer varchar(50))");

        db.execSQL("create table IF NOT EXISTS tb_dizimo" +
                " (codigo integer primary key Autoincrement," +
                " conferente text, " +
                "valorDuzentos text, " +
                "valorCem text, " +
                "valorCinquenta text, " +
                "valorVinte text, " +
                "valorDez text, " +
                "valorCinco text, " +
                "valorDois text, " +
                "valorMoedas text, " +
                "dataContagem text, " +
                "valorSomaTotal text) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
