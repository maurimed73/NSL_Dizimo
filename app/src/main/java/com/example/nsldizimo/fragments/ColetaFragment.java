package com.example.nsldizimo.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nsldizimo.R;
import com.example.nsldizimo.adapter.ColetasAdapter;
import com.example.nsldizimo.database.ColetasDAO;
import com.example.nsldizimo.model.Coletas;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ColetaFragment extends Fragment {

    private ColetasDAO dao;
    private List<Coletas> coletas;
    private List<Coletas> coletasFiltradas = new ArrayList<>();
    Snackbar snackbar;
    TextView txtData, txtValorColeta, lblValorColeta;
    ImageButton btnCalendar;
    Button btnSalvar, btnFiltrar, btnTodas, btnColetasAZ;
    String idColeta;
    public boolean dadosvalidados;
    RecyclerView recyclerView;
    Integer dia = null;
    Integer mes = null;
    Integer ano = null;
    String dataParametro = "";


    public ColetaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coleta, container, false);

        btnSalvar = view.findViewById(R.id.btnSalvarColeta);
        btnTodas = view.findViewById(R.id.btnTodasColetas);
        btnColetasAZ = view.findViewById(R.id.btnColetasAZ);
        btnFiltrar = view.findViewById(R.id.btnFiltrarColeta);
        lblValorColeta = view.findViewById(R.id.lblValorColeta);
        btnSalvar.setVisibility(View.INVISIBLE);
        btnCalendar = view.findViewById(R.id.btnCalendarColeta);
        txtData = view.findViewById(R.id.txtDataColetaRec);
        txtData.setText("");

        txtValorColeta = view.findViewById(R.id.txtValorColeta);
        recyclerView = view.findViewById(R.id.recyclerViewColetas);

        dao = new ColetasDAO(getContext());

        btnFiltrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateParam();


            }
        });

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idColeta == null) {
                    Coletas a = new Coletas();
                    dadosvalidados = validarFormulario(v);
                    if (dadosvalidados) {

                        a.setValorColeta(txtValorColeta.getText().toString());
                        a.setDataColeta(txtData.getText().toString());
                        a.setDia(dia.intValue());
                        a.setMes(mes.intValue());
                        a.setAno(ano.intValue());
                        long id = dao.inserir(a);
                        Toast.makeText(getContext(), "Coleta inserida", Toast.LENGTH_SHORT).show();
                        atualizaListView();
                        closeKeyboard(view);
                        idColeta = null;
                    }
                } else {
                    Toast.makeText(getContext(), "Erro", Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnTodas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizaListView();
            }
        });

        btnColetasAZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coletas = dao.coletasAZ();
                //String valor = conferidas.get(0).getValorTotal().toString();
                ColetasAdapter adapter = new ColetasAdapter(coletas);
                //configurar RecyclerView
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);
            }
        });


        atualizaListView();

        return view;
    }

    private void closeKeyboard(View v) {
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }

    }

    public void atualizaListView() {
        coletas = dao.obterTodos();
        //String valor = conferidas.get(0).getValorTotal().toString();
        ColetasAdapter adapter = new ColetasAdapter(coletas);
        //configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        txtValorColeta.setText("");
        txtValorColeta.setEnabled(false);
        txtData.setText("");
        btnSalvar.setVisibility(View.GONE);
        lblValorColeta.setVisibility(View.INVISIBLE);

    }

    private void date() {

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        dia = dayOfMonth;
                        mes = monthOfYear + 1;
                        ano = year;
                        String diaText = "" + dayOfMonth;
                        String mesText = "" + monthOfYear;
                        ano = year;
                        monthOfYear = monthOfYear + 1;

                        if (dayOfMonth < 10) {
                            diaText = "0" + dayOfMonth;
                        } else {
                            diaText = "" + dayOfMonth;
                        }

                        if (monthOfYear < 10) {
                            mesText = "0" + monthOfYear;
                        } else {
                            mesText = "" + monthOfYear;
                        }

                        txtData.setText(diaText + "-" + (mesText) + "-" + year);

                        if (txtData.getText() != null) {
                            btnSalvar.setVisibility(View.VISIBLE);
                            lblValorColeta.setVisibility(View.VISIBLE);
                            txtValorColeta.setVisibility(View.VISIBLE);
                            txtValorColeta.setEnabled(true);
                            txtValorColeta.setCursorVisible(true);

                        }
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private boolean validarFormulario(View v) {
//Regra de validação  - usar classe TextUtils
        boolean retorno = false;
        //edit novoConferente
        if (!TextUtils.isEmpty(txtData.getText().toString()) && !TextUtils.isEmpty(txtValorColeta.getText().toString())) {
            return true;
        } else {
            if (TextUtils.isEmpty(txtData.getText().toString())) {
                txtData.setError("*");
                txtData.requestFocus();
                snackbar = Snackbar.make(v, "Defina a data", Snackbar.LENGTH_LONG);
                snackbar.show();
            } else {
                txtValorColeta.setError("*");
                txtValorColeta.requestFocus();
                snackbar = Snackbar.make(v, "Defina o valor", Snackbar.LENGTH_LONG);
                snackbar.show();
            }

        }
        return retorno;
    }

    private void dateParam() {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        dia = dayOfMonth;
                        mes = monthOfYear + 1;
                        ano = year;
                        String diaText = "" + dayOfMonth;
                        String mesText = "" + monthOfYear;
                        ano = year;
                        monthOfYear = monthOfYear + 1;

                        if (dayOfMonth < 10) {
                            diaText = "0" + dayOfMonth;
                        } else {
                            diaText = "" + dayOfMonth;
                        }

                        if (monthOfYear < 10) {
                            mesText = "0" + monthOfYear;
                        } else {
                            mesText = "" + monthOfYear;
                        }

                        dataParametro = diaText + "-" + (mesText) + "-" + year;
                        procurarColeta(dataParametro);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();



    }

    public void procurarColeta(String parametro){
        coletas = dao.filtrarData(dataParametro);
        //String valor = conferidas.get(0).getValorTotal().toString();
        ColetasAdapter adapter = new ColetasAdapter(coletas);
        //configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }


}