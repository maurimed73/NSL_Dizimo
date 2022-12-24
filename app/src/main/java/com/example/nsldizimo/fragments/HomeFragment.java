package com.example.nsldizimo.fragments;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nsldizimo.R;
import com.example.nsldizimo.database.Conexao;
import com.example.nsldizimo.database.ConferenteDAO;
import com.example.nsldizimo.model.Conferente;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment {

    public Spinner spinner;

    private Animation animation;
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
    Button incluir,novoConfer;
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
        incluir = (Button) view.findViewById(R.id.btnsalvar);
        novoConfer = (Button)view.findViewById(R.id.btnNovoConferente);



        animation = new AlphaAnimation(1, 0); // Altera alpha de visível a invisível
        animation.setDuration(500); // duração - meio segundo
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE); // Repetir infinitamente
        animation.setRepeatMode(Animation.REVERSE); //Inverte a animação no final para que o botão vá desaparecendo


        // botão novo conferente
        novoConfer.startAnimation(animation);
        novoConfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinner.setEnabled(true);
                spinner.setFocusable(true);
                spinner.requestFocus();
                limparCampos(v);
                habilitarCampos();
                popularSpinner();
                v.clearAnimation();
                spinner.performClick();

            }
        });

        spinner = view.findViewById(R.id.spnConferentes);
        spinner.setEnabled(false);

        //*********   SetOnItem  SPINNER   ************************
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //textNome.setText(spinner.getSelectedItem().toString());
                String conferente = spinner.getSelectedItem().toString();
                System.out.println(conferente);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //*********   SetOnItem  BOTÃO LIMPAR   ************************
        btnLimpar = view.findViewById(R.id.btnLimpar);
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Limpando os campos de texto do formulário
                limparCampos(v);
            }
        });


        // botão Salvar
        incluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Incluir(v);
                novoConfer.startAnimation(animation);
                limparCampos(v);
            }
        });

        // listener 200 reais
        qtd200.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length() == 0){

                    double resultado = Calcular(200,0);
                    totalSoma[0] = resultado;
                    resultado200.setText(""+resultado);
                    somatorio();
                    //Toast.makeText(Conferente.this, "Valor do Text View "+ resultado, Toast.LENGTH_SHORT).show();

                }else{
                    int unidade = 200;
                    double valor200 = Double.parseDouble(qtd200.getText().toString());

                    double resultado = Calcular(unidade,valor200);
                    totalSoma[0] = resultado;
                    resultado200.setText(""+resultado+"0");
                    somatorio();

                    //Toast.makeText(Conferente.this, "Valor do Text View "+ totalSoma[0], Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // listener 100 reais
        qtd100.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length() == 0){

                    double resultado = Calcular(100,0);
                    totalSoma[1] = resultado;
                    resultado100.setText(""+resultado);
                    somatorio();
                    //Toast.makeText(Conferente.this, "Valor do Text View "+ resultado, Toast.LENGTH_SHORT).show();

                }else{
                    int unidade = 100;
                    double valor100 = Double.parseDouble(qtd100.getText().toString());

                    double resultado = Calcular(unidade,valor100);
                    totalSoma[1] = resultado;
                    resultado100.setText(""+resultado+"0");
                    somatorio();
                    //Toast.makeText(Conferente.this, "Valor do Text View "+ resultado, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // listener 50 reais
        qtd50.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length() == 0){

                    double resultado = Calcular(50,0);
                    totalSoma[2] = resultado;
                    resultado50.setText(""+resultado);
                    somatorio();
                    //Toast.makeText(Conferente.this, "Valor do Text View "+ resultado, Toast.LENGTH_SHORT).show();

                }else{
                    int unidade = 50;
                    double valor50 = Double.parseDouble(qtd50.getText().toString());

                    double resultado = Calcular(unidade,valor50);
                    totalSoma[2] = resultado;
                    resultado50.setText(""+resultado+"0");
                    somatorio();
                    //Toast.makeText(Conferente.this, "Valor do Text View "+ resultado, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // listener 20 reais
        qtd20.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length() == 0){

                    double resultado = Calcular(20,0);
                    totalSoma[3] = resultado;
                    resultado20.setText(""+resultado);
                    somatorio();
                    //Toast.makeText(Conferente.this, "Valor do Text View "+ resultado, Toast.LENGTH_SHORT).show();

                }else{
                    int unidade = 20;
                    double valor20 = Double.parseDouble(qtd20.getText().toString());

                    double resultado = Calcular(unidade,valor20);
                    totalSoma[3] = resultado;
                    resultado20.setText(""+resultado+"0");
                    somatorio();
                    //Toast.makeText(Conferente.this, "Valor do Text View "+ resultado, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // listener 10 reais
        qtd10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length() == 0){

                    double resultado = Calcular(10,0);
                    totalSoma[4] = resultado;
                    resultado10.setText(""+resultado);
                    somatorio();
                    //Toast.makeText(Conferente.this, "Valor do Text View "+ resultado, Toast.LENGTH_SHORT).show();

                }else{
                    int unidade = 10;
                    double valor10 = Double.parseDouble(qtd10.getText().toString());

                    double resultado = Calcular(unidade,valor10);
                    totalSoma[4] = resultado;
                    resultado10.setText(""+resultado+"0");
                    somatorio();
                    //Toast.makeText(Conferente.this, "Valor do Text View "+ resultado, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // listener 5 reais
        qtd5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length() == 0){

                    double resultado = Calcular(10,0);
                    totalSoma[5] = resultado;
                    resultado5.setText(""+resultado);
                    somatorio();
                    //Toast.makeText(Conferente.this, "Valor do Text View "+ resultado, Toast.LENGTH_SHORT).show();

                }else{
                    int unidade = 5;
                    double valor5 = Double.parseDouble(qtd5.getText().toString());

                    double resultado = Calcular(unidade,valor5);
                    totalSoma[5] = resultado;
                    resultado5.setText(""+resultado+"0");
                    somatorio();
                    //Toast.makeText(Conferente.this, "Valor do Text View "+ resultado, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // listener 2 reais
        qtd2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length() == 0){

                    double resultado = Calcular(2,0);
                    totalSoma[6] = resultado;
                    resultado2.setText(""+resultado);
                    somatorio();
                    //Toast.makeText(Conferente.this, "Valor do Text View "+ resultado, Toast.LENGTH_SHORT).show();

                }else{
                    int unidade = 2;
                    double valor2 = Double.parseDouble(qtd2.getText().toString());

                    double resultado = Calcular(unidade,valor2);
                    totalSoma[6] = resultado;
                    resultado2.setText(""+resultado+"0");
                    somatorio();
                    //Toast.makeText(Conferente.this, "Valor do Text View "+ resultado, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // listener moedas
        moedas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length() == 0){

                    double resultado = Calcular(1,0);
                    totalSoma[7] = resultado;
                    resultadoMoedas.setText(""+resultado);
                    somatorio();
                    //Toast.makeText(Conferente.this, "Valor do Text View "+ resultado, Toast.LENGTH_SHORT).show();

                }else{
                    int unidade = 1;
                    double valor2 = Double.parseDouble(moedas.getText().toString());

                    double resultado = Calcular(unidade,valor2);
                    totalSoma[7] = resultado;
                    resultadoMoedas.setText(""+resultado);
                    somatorio();
                    //Toast.makeText(Conferente.this, "Valor do Text View "+ resultado, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return view;
    }

    private void somatorio() {
        double somaCampo = 0;
        for (double n : totalSoma) {
            somaCampo += n;
        }

        somaTotalBD = ""+somaCampo;
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        String somaRel = formatter.format(Double.parseDouble(String.valueOf(somaCampo)));



        resultadoSomaTotal.setText(""+ somaRel);
        System.out.println("A soma é " + somaCampo);
        System.out.println(somaCampo);
    }

    private double Calcular(int unidade, double valor) {
        double soma;
        if(valor != 0){
            soma = unidade * valor;
            return soma;
        }else
        {
            soma = 0.00;
            return soma;
        }
    }

    private void habilitarCampos() {

        qtd200.setEnabled(true);
        qtd100.setEnabled(true);
        qtd50.setEnabled(true);
        qtd20.setEnabled(true);
        qtd10.setEnabled(true);
        qtd5.setEnabled(true);
        qtd2.setEnabled(true);
        moedas.setEnabled(true);

    }

    private void popularSpinner() {
        dao = new ConferenteDAO(getContext());
        conferentesFiltrados.clear();
        spinner.invalidate();
        conferentes = dao.buscaDadosSpinner();
        conferentesFiltrados.addAll(conferentes);
        ArrayAdapter<Conferente> adaptador = new ArrayAdapter<Conferente>(getContext(), R.layout.spinner_item_text,conferentesFiltrados);
        spinner.setAdapter(adaptador);
    }

    private void limparCampos(View v){
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
        qtd200.setEnabled(false);

        qtd100.setText("");
        qtd100.setEnabled(false);

        qtd50.setText("");
        qtd50.setEnabled(false);

        qtd20.setText("");
        qtd20.setEnabled(false);

        qtd10.setText("");
        qtd10.setEnabled(false);

        qtd5.setText("");
        qtd5.setEnabled(false);

        qtd2.setText("");
        qtd2.setEnabled(false);

        moedas.setText("");
        moedas.setEnabled(false);

        resultadoMoedas.setEnabled(false);



        FragmentManager fragmentManager = getFragmentManager();
        //this will clear the back stack and displays no animation on the screen
        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        spinner.setAdapter(null);


        if(v!=null){
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(),0);
        }

    }

    private void closeKeyboard(View v) {
        if(v!=null){
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(),0);
        }

    }

    private void Incluir(View v) {

        String valor = null;

        if(spinner != null && spinner.getSelectedItem() !=null && spinner.getSelectedItemPosition() != -1 ) {

            Conexao admin = new Conexao(getContext());
            SQLiteDatabase bd = admin.getWritableDatabase();

            SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");
            Date data = new Date();

            String nomeConfer = spinner.getSelectedItem().toString();
            String result200 = resultado200.getText().toString();
            String result100 = resultado100.getText().toString();
            String result50 = resultado50.getText().toString();
            String result20 = resultado20.getText().toString();
            String result10 = resultado10.getText().toString();
            String result5 = resultado5.getText().toString();
            String result2 = resultado2.getText().toString();
            String resultMoedas = resultadoMoedas.getText().toString();
            String resultTotal = somaTotalBD;
            String dataAtual = formataData.format(data);

            //Passando os valores para a tabela
            ContentValues registros = new ContentValues();
            registros.put("conferente",nomeConfer);
            registros.put("valorDuzentos",result200);
            registros.put("valorCem",result100);
            registros.put("valorCinquenta",result50);
            registros.put("valorVinte",result20);
            registros.put("valorDez",result10);
            registros.put("valorCinco",result5);
            registros.put("valorDois",result2);
            registros.put("valorMoedas",resultMoedas);
            registros.put("valorSomaTotal",resultTotal);
            registros.put("dataContagem",dataAtual);

            //Inserindo os valores na tabela do BD
            bd.insert("tb_dizimo",null,registros);
            bd.close();

            //Mensagem de cadastro com sucesso
            Toast.makeText(getContext(), "Os dados foram salvos com sucesso", Toast.LENGTH_SHORT).show();
            limparCampos(v);


        } else  {


            Toast.makeText(getContext(), "Conferente Obrigatório", Toast.LENGTH_SHORT).show();
        }
    }
}