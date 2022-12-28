package com.example.nsldizimo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.nsldizimo.R;
import com.example.nsldizimo.model.Conferida;

import java.util.List;

public class CedulasActivity extends AppCompatActivity {


    int numeroCedula2 = 0;
    int numero = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cedulas);


        Intent iRecebedora = getIntent();
        Bundle parametros = iRecebedora.getExtras();
        if(parametros != null){
            List<Conferida> conferidas = (List<Conferida>) parametros.getSerializable("campos");


numero = 0;
                for(int i = 0; i<5;i++){
                    numeroCedula2 = (int) (conferidas.get(i).getValorDois()/2);
                    System.out.println("Valor de nota: "+numeroCedula2);
                    numero = numero + numeroCedula2;
                }

        }
        Toast.makeText(this, "número de cédulas 2: "+numero, Toast.LENGTH_LONG).show();




    }
}