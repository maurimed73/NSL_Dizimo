package com.example.nsldizimo.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.nsldizimo.model.Conferente;
import com.example.nsldizimo.model.Conferida;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ConfereciasDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public ConfereciasDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public List<Conferida> conferenciasFiltradasData(String data) {
        Cursor cursor;
        ArrayList<Conferida> conferidas = new ArrayList<>();
        if(data != null){
             cursor = banco.query("tb_dizimo",new String[]{"codigo","conferente","valorDuzentos","valorCem","valorCinquenta","valorVinte","valorDez","valorCinco","valorDois","valorMoedas","dataContagem","valorSomaTotal"},"dataContagem = ?", new String[]{data},null,null,null,null);
        }else{
             cursor = banco.query("tb_dizimo",new String[]{"codigo","conferente","valorDuzentos","valorCem","valorCinquenta","valorVinte","valorDez","valorCinco","valorDois","valorMoedas","dataContagem","valorSomaTotal"},null,null,null,null,null);
        }

        while(cursor.moveToNext()){
            Conferida a = new Conferida();
            a.setCodigo(cursor.getInt(0));
            a.setNomeConferente(cursor.getString(1));
            a.setValorDuzentos(cursor.getDouble(2));
            a.setValorCem(cursor.getDouble(3));
            a.setValorCinquenta(cursor.getDouble(4));
            a.setValorVinte(cursor.getDouble(5));
            a.setValorDez(cursor.getDouble(6));
            a.setValorCinco(cursor.getDouble(7));
            a.setValorDois(cursor.getDouble(8));
            a.setValorMoedas(cursor.getString(9));
            a.setDataContagem(cursor.getString(10));
            a.setValorTotal(cursor.getString(11));
            conferidas.add(a);
        }
        return conferidas;
    }

    public void apagarTabela() {
        banco.delete("tb_dizimo",null,null );
    }

    public String calcularSoma(String txtDataAtual) {

        String resultado = String.valueOf(0);
        String data = txtDataAtual;
        Cursor res = banco.rawQuery("select SUM(valorSomaTotal)from tb_dizimo where dataContagem="+"'"+data+"'",null);
        if(res != null){
            res.moveToFirst();
            if(res.getInt(0) == 0){
                System.out.println("Tabela esta vazia");
                resultado = "";
            }else {
                System.out.println(""+res.getString(0).toString());
                DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
                String somaTotal = formatter.format(Double.parseDouble(String.valueOf(res.getString(0).toString())));
                resultado = somaTotal;
            }


        }else{
            System.out.println("Deu errado");

        }

        return resultado;
    }
}
