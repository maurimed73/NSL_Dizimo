package com.example.nsldizimo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nsldizimo.R;
import com.example.nsldizimo.database.ColetasDAO;
import com.example.nsldizimo.model.Coletas;
import com.example.nsldizimo.model.Conferente;
import com.example.nsldizimo.model.Conferida;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CedulasActivity extends AppCompatActivity {


    int numeroCedula2 = 0;
    int numeroCedula5 = 0;
    int numeroCedula10 = 0;
    int numeroCedula20 = 0;
    int numeroCedula50 = 0;
    int numeroCedula100 = 0;
    int numeroCedula200 = 0;
    double numeroCedulaMoedas = 0.0;
    double numeroSomaTotal = 0.0;
    int resDois;
    int resCinco;
    int resDez;
    int resVinte;
    int resCinquenta;
    int resCem;
    int resDuzentos;
    double resMoedas;
    double resTotal;
    TextView quant200,quant100,quant50,quant20,quant10,quant5,quant2;
    TextView valor200,valor100,valor50,valor20,valor10,valor5,valor2,valorMoedas,txtDataDizimo,txtValorDizimo;
    String data;
    private ListView lstColetas;
    private ColetasDAO dao;
    private List<Coletas> coletas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cedulas);

        txtValorDizimo = findViewById(R.id.txtValorDizimo);
        txtDataDizimo = findViewById(R.id.txtDataDizimo);
        quant200 = findViewById(R.id.txtQuant200);
        valor200 = findViewById(R.id.txtValor200);
        quant100 = findViewById(R.id.txtQuant100);
        valor100 = findViewById(R.id.txtValor100);
        quant50 = findViewById(R.id.txtQuant50);
        valor50 = findViewById(R.id.txtValor50);
        quant20 = findViewById(R.id.txtQuant20);
        valor20 = findViewById(R.id.txtValor20);
        quant10 = findViewById(R.id.txtQuant10);
        valor10 = findViewById(R.id.txtValor10);
        quant5 = findViewById(R.id.txtQuant5);
        valor5 = findViewById(R.id.txtValor5);
        quant2 = findViewById(R.id.txtQuant2);
        valor2 = findViewById(R.id.txtValor2);
        valorMoedas = findViewById(R.id.txtMoedas2);
        Intent iRecebedora = getIntent();
        Bundle parametros = iRecebedora.getExtras();

        lstColetas = findViewById(R.id.lstColetas);

        ArrayList<String>listaColetas = new ArrayList<String>();
        listaColetas.add("02-12-2022     R$328,23");
        listaColetas.add("12-12-2022     R$185,20");
        listaColetas.add("22-12-2022     R$452,59");
        listaColetas.add("32-12-2022      R$89,84");

        dao = new ColetasDAO(this);
        coletas = dao.obterTodos();

        ArrayAdapter<Coletas>adaptador = new ArrayAdapter<Coletas>(this,android.R.layout.simple_list_item_1,coletas);
        lstColetas.setAdapter(adaptador);


        if(parametros != null){
            List<Conferida> conferidas = (List<Conferida>) parametros.getSerializable("campos");
            int nConfer = conferidas.size();
            data = conferidas.get(0).getDataContagem();

            //**************   Somatório notas de Dois
                resDois= 0;
                for(int i = 0; i<nConfer;i++){
                    numeroCedula2 = (int) (conferidas.get(i).getValorDois()/2);
                    System.out.println("Valor de nota: "+numeroCedula2);
                    resDois = resDois + numeroCedula2;
                }

            //**************   Somatório notas de Cinco
            resCinco = 0;
            for(int i = 0; i<nConfer;i++){
                numeroCedula5 = (int) (conferidas.get(i).getValorCinco()/5);
                System.out.println("Valor de nota: "+numeroCedula5);
                resCinco = resCinco + numeroCedula5;
            }

            //**************   Somatório notas de Dez
            resDez = 0;
            for(int i = 0; i<nConfer;i++){
                numeroCedula10 = (int) (conferidas.get(i).getValorDez()/10);
                System.out.println("Valor de nota: "+numeroCedula10);
                resDez = resDez + numeroCedula10;
            }

            //**************   Somatório notas de Vinte
            resVinte = 0;
            for(int i = 0; i<nConfer;i++){
                numeroCedula20 = (int) (conferidas.get(i).getValorVinte()/20);
                System.out.println("Valor de nota: "+numeroCedula20);
                resVinte = resVinte + numeroCedula20;
            }

            //**************   Somatório notas de Cinquenta
            resCinquenta = 0;
            for(int i = 0; i<nConfer;i++){
                numeroCedula50 = (int) (conferidas.get(i).getValorCinquenta()/50);
                System.out.println("Valor de nota: "+numeroCedula50);
                resCinquenta = resCinquenta + numeroCedula50;
            }

            //**************   Somatório notas de Cem
            resCem = 0;
            for(int i = 0; i<nConfer;i++){
                numeroCedula100 = (int) (conferidas.get(i).getValorCem()/100);
                System.out.println("Valor de nota: "+numeroCedula100);
                resCem = resCem + numeroCedula100;
            }

            //**************   Somatório notas de Duzentos
            resDuzentos = 0;
            for(int i = 0; i<nConfer;i++){
                numeroCedula200 = (int) (conferidas.get(i).getValorDuzentos()/200);
                System.out.println("Valor de nota: "+numeroCedula200);
                resDuzentos = resDuzentos + numeroCedula200;
            }

            //**************   Somatório Moedas
            resMoedas = 0.0;
            for(int i = 0; i<nConfer;i++){
                numeroCedulaMoedas = Double.parseDouble((conferidas.get(i).getValorMoedas()));
                System.out.println("Valor de nota: "+numeroCedulaMoedas);
                resMoedas = resMoedas + numeroCedulaMoedas;
            }

            //**************   Somatório Total
            resTotal = 0.0;
            for(int i = 0; i<nConfer;i++){
                numeroSomaTotal = Double.parseDouble((conferidas.get(i).getValorTotal()));
                System.out.println("Valor de nota: "+numeroSomaTotal);
                resTotal = resTotal + numeroSomaTotal;

            }

        }

        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        /*System.out.println("Notas de Dois: "+ resDois);
        System.out.println("Notas de Cinco: "+ resCinco);
        System.out.println("Notas de Dez: "+ resDez);
        System.out.println("Notas de Vinte: "+ resVinte);
        System.out.println("Notas de Cinquenta: "+ resCinquenta);
        System.out.println("Notas de Cem: "+ resCem);
        System.out.println("Notas de Duzentos: "+ resDuzentos);
        System.out.println("Notas de Moedas: "+ resMoedas);
        System.out.println("Notas de Soma Total: "+ resTotal);*/
        quant200.setText(""+resDuzentos);
        valor200.setText("R$"+formatter.format(Double.parseDouble(String.valueOf(resDuzentos*200))));
        quant100.setText(""+resCem);
        valor100.setText("R$"+formatter.format(Double.parseDouble(String.valueOf(resCem*100))));
        quant50.setText(""+resCinquenta);
        valor50.setText("R$"+formatter.format(Double.parseDouble(String.valueOf(resCinquenta*50))));
        quant20.setText(""+resVinte);
        valor20.setText("R$"+formatter.format(Double.parseDouble(String.valueOf(resVinte*20))));
        quant10.setText(""+resDez);
        valor10.setText("R$"+formatter.format(Double.parseDouble(String.valueOf(resDez*10))));
        quant5.setText(""+resCinco);
        valor5.setText("R$"+formatter.format(Double.parseDouble(String.valueOf(resCinco*5))));
        quant2.setText(""+resDois);
        valor2.setText("R$"+formatter.format(Double.parseDouble(String.valueOf(resDois*2))));
        valorMoedas.setText("R$"+formatter.format(Double.parseDouble(String.valueOf(resMoedas*1))));
        txtValorDizimo.setText("R$"+formatter.format(Double.parseDouble(String.valueOf(resTotal))));
        txtDataDizimo.setText(""+ data);
    }

    public void voltarInicio(View v){
        finish();
    }

}