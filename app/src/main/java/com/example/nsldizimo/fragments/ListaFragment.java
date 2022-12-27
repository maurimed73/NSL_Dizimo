package com.example.nsldizimo.fragments;

import android.app.DatePickerDialog;
import android.app.FragmentManager;
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
import com.example.nsldizimo.activity.RecyclerItemClickListener;
import com.example.nsldizimo.adapter.MyAdapter;
import com.example.nsldizimo.database.ConfereciasDAO;
import com.example.nsldizimo.model.Conferida;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ListaFragment extends Fragment {


    private ConfereciasDAO dao;
    private List<Conferida> conferidas;
    private List<Conferida>conferidasFiltradas = new ArrayList<>();
    private RecyclerView recyclerView;
    TextView txtDataAtual;
    String filtroData = null;
    ImageButton btnCalendar, btnTodas;
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
        btnCalendar = view.findViewById(R.id.imgBtnCalendar);
        btnTodas = view.findViewById(R.id.imgBtnTodas);



        // pegando a data atual ou retroativa
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Clicou em calendario", Toast.LENGTH_SHORT).show();
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
                filtroData = null;
                listarTodas(getView(),filtroData);
            }
        });

        //módulo buscar listagem
        dao = new ConfereciasDAO(getContext());
        listarTodas(view,filtroData);


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