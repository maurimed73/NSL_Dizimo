package com.example.nsldizimo.fragments;

import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nsldizimo.R;
import com.example.nsldizimo.activity.CedulasActivity;
import com.example.nsldizimo.activity.RecyclerItemClickListener;
import com.example.nsldizimo.adapter.MyAdapter;
import com.example.nsldizimo.database.ConfereciasDAO;
import com.example.nsldizimo.model.Conferida;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ListaFragment extends Fragment {


    private ConfereciasDAO dao;
    private List<Conferida> conferidas;
    private List<Conferida>conferidasFiltradas = new ArrayList<>();
    private RecyclerView recyclerView;
    TextView txtDataAtual,txtSomaTotal;
    String filtroData = null;
    ImageButton btnCalendar, btnTodas,btnCedulas;
    NovoConferenteFragment novoConferenteFragment;
    ListaFragment listaFragment;

    public ListaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista, container, false);

        filtroData = null;



        // módulo calendar
        txtDataAtual = view.findViewById(R.id.txtDataAtual);

        SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");
        Date data = new Date();

        System.out.println(""+data);
        txtDataAtual.setText(formataData.format(data));
        txtSomaTotal = view.findViewById(R.id.txtValorSomadoDataAtual);
        btnCalendar = view.findViewById(R.id.imgBtnCalendar);
        btnTodas = view.findViewById(R.id.imgBtnTodas);
        btnCedulas = view.findViewById(R.id.imgBtnCedulas);



        // pegando a data atual ou retroativa
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                txtDataAtual.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                filtroData = ""+txtDataAtual.getText().toString();

                                listarTodas(getView(),filtroData);


                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();


            }

        });

        btnTodas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date data = new Date();
                SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");

                filtroData = formataData.format(data);
                txtDataAtual.setText(filtroData);

                listarTodas(getView(),filtroData);

            }
        });

        btnCedulas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), CedulasActivity.class);
                Bundle parametros = new Bundle();
                parametros.putSerializable("campos", (Serializable) conferidas);
                i.putExtras(parametros);
                startActivity(i);
            }
        });

        //módulo buscar listagem
        dao = new ConfereciasDAO(getContext());
        listarTodas(view,txtDataAtual.getText().toString());


        //módulo RecyclerView
        recyclerView = view.findViewById(R.id.recyclerview);

        //evento de Click
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),recyclerView,new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Conferida conferida = conferidas.get(position);
                                Toast.makeText(getContext(), "Conferencia selecionado: " + conferida.getNomeConferente(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Conferida conferida = conferidas.get(position);
                                Toast.makeText(getContext(), "Valor Conferencia selecionado: " + conferida.getValorTotal() , Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }));

        return view;
    }


    private void listarTodas(View view, String txtDataAtual) {
        conferidas = dao.conferenciasFiltradasData(txtDataAtual);
        String valor = dao.calcularSoma(txtDataAtual);




        txtSomaTotal.setText(valor);

        //String valor = conferidas.get(0).getValorTotal().toString();
        MyAdapter adapter = new MyAdapter(conferidas);
        //configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter( adapter );


    }


}