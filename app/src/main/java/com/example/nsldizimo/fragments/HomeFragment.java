package com.example.nsldizimo.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.nsldizimo.R;
import com.example.nsldizimo.database.ConferenteDAO;
import com.example.nsldizimo.model.Conferente;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public Spinner spinner;

    private List<Conferente> conferentes;
    private ConferenteDAO dao;
    private List<Conferente>conferentesFiltrados = new ArrayList<>();

    EditText qtd200,qtd100,qtd50,qtd20,qtd10,qtd5,qtd2,moedas;
    TextView resultado200;
    TextView resultado100;
    TextView resultado50;
    TextView resultado20;
    TextView resultado10;
    TextView resultado5;
    TextView resultado2;
    TextView resultadoMoedas;
    TextView resultadoSomaTotal;
    double[]totalSoma = new double[8];
    Button incluir;
    Button btnLimpar;
    Button btnNovoConferente;
    ListView lista;
    String somaTotalBD;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        qtd200 = (EditText) view.findViewById(R.id.duzentosId);
        resultado200 = (TextView)view.findViewById(R.id.resultado200);
        qtd100 = (EditText) view.findViewById(R.id.cemId);
        resultado100 = (TextView)view.findViewById(R.id.resultado100);
        qtd50 = (EditText) view.findViewById(R.id.cinquentaId);
        resultado50 = (TextView)view.findViewById(R.id.resultado50);
        qtd20 = (EditText) view.findViewById(R.id.vinteId);
        resultado20 = (TextView)view.findViewById(R.id.resultado20);
        qtd10 = (EditText) view.findViewById(R.id.dezId);
        resultado10 = (TextView)view.findViewById(R.id.resultado10);
        qtd5 = (EditText) view.findViewById(R.id.cincoId);
        resultado5 = (TextView)view.findViewById(R.id.resultado5);
        qtd2 = (EditText) view.findViewById(R.id.doisId);
        resultado2 = (TextView)view.findViewById(R.id.resultado2);
        moedas = (EditText) view.findViewById(R.id.moedaId);
        resultadoMoedas = (TextView)view.findViewById(R.id.resultadoMoedas);
        resultadoSomaTotal = (TextView)view.findViewById(R.id.SomaTotal);
        incluir = (Button) view.findViewById(R.id.buttonIncluir);


        /*spinner = view.findViewById(R.id.spnConferentes);
        //textNome = view.findViewById(R.id.NomeConferente);
        dao = new ConferenteDAO(getContext());
        conferentesFiltrados.clear();
        spinner.invalidate();
        conferentes = dao.buscaDadosSpinner();
        conferentesFiltrados.addAll(conferentes);
        ArrayAdapter<Conferente> adaptador = new ArrayAdapter<Conferente>(getContext(), R.layout.spinner_item_text,conferentesFiltrados);
        spinner.setAdapter(adaptador);*/

        //*********   SetOnItem  SPINNER   ************************
        /*spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //textNome.setText(spinner.getSelectedItem().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });*/


        //*********   SetOnItem  BOTÃO LIMPAR   ************************
        btnLimpar = view.findViewById(R.id.btnLimpar);
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Limpando os campos de texto do formulário


                resultado200.setText("");
                resultado100.setText("");
                resultado50.setText("");
                resultado20.setText("");
                resultado10.setText("");
                resultado5.setText("");
                resultado2.setText("");
                resultadoMoedas.setText("");
                resultadoSomaTotal.setText("");

                qtd200.setText("");
                qtd100.setText("");
                qtd50.setText("");
                qtd20.setText("");
                qtd10.setText("");
                qtd5.setText("");
                qtd2.setText("");
                moedas.setText("");
                qtd200.setText("");
            }
        });





        return view;
    }
}