package com.example.nsldizimo.fragments;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nsldizimo.R;
import com.example.nsldizimo.database.ConferenteDAO;
import com.example.nsldizimo.model.Conferente;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class NovoConferenteFragment extends Fragment {

    private EditText nomeConferente;
    BottomNavigationView bottomNavigationView;
    private ConferenteDAO dao;
    private ListView listViewConferentes;
    private List<Conferente> conferentes;
    private List<Conferente>conferentesFiltrados = new ArrayList<>();
    public static Conferente conferenteAtualizar;
    public static String idConfer;
    public ImageButton btnSalvar; public MenuInflater menuInflater;
    public boolean dadosvalidados;

    public NovoConferenteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_novo_conferente, container, false);

        btnSalvar = view.findViewById(R.id.btnSalvar);
        nomeConferente = view.findViewById(R.id.editTextTextPersonName);
        dao = new ConferenteDAO(getContext());
        listViewConferentes = view.findViewById(R.id.listView_conferentes);
        conferentes = dao.obterTodos();
        conferentesFiltrados.clear();
        listViewConferentes.invalidateViews();
        conferentesFiltrados.addAll(conferentes);
        ArrayAdapter<Conferente>adaptador = new ArrayAdapter<Conferente>(getContext(), R.layout.list_item_text,conferentesFiltrados);
        listViewConferentes.setAdapter(adaptador);
        registerForContextMenu(listViewConferentes);


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idConfer == null){
                    Conferente a = new Conferente();
                    dadosvalidados = validarFormulario();
                    if (dadosvalidados){
                        a.setNomeConferente(nomeConferente.getText().toString());
                        long id = dao.inserir(a);
                        Toast.makeText(getContext(), "Conferente inserido", Toast.LENGTH_SHORT).show();
                        atualizaListView();
                        closeKeyboard(view);
                        idConfer=null;
                   }



                }
                else{
                    conferenteAtualizar.setNomeConferente(nomeConferente.getText().toString());
                    dao.atualizar(conferenteAtualizar);
                    Toast.makeText(getContext(), "Atualizado com sucesso", Toast.LENGTH_SHORT).show();
                    atualizaListView();
                    closeKeyboard(view);
                    idConfer=null;

                }
            }
        });
        return view;
    }

    private boolean validarFormulario() {
//Regra de validação  - usar classe TextUtils
        boolean retorno = false;
        //edit novoConferente
        if(!TextUtils.isEmpty(nomeConferente.getText().toString())){
            return true;
        }else{
            nomeConferente.setError("*");
            nomeConferente.requestFocus();
        }
        return retorno;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE, R.id.excluir, Menu.NONE, "Excluir");
        menu.add(Menu.NONE, R.id.atualizar, Menu.NONE, "Atualizar");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.excluir:

                excluir(item);

                return true;
            case R.id.atualizar:

                atualizar(item);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    public void atualizaListView() {
        conferentes = dao.obterTodos();
        conferentesFiltrados.clear();
        conferentesFiltrados.addAll(conferentes);
        listViewConferentes.invalidateViews();
        ArrayAdapter<Conferente>adaptador = new ArrayAdapter<Conferente>(getContext(), R.layout.list_item_text,conferentesFiltrados);
        listViewConferentes.setAdapter(adaptador);
        nomeConferente.setText("");
    }

    private void closeKeyboard(View v) {
        if(v!=null){
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(),0);
        }

    }

    public void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Conferente conferenteExcluir = conferentesFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setTitle("Atenção")
                .setMessage("Deseja excluir o conferente?")
                .setNegativeButton("Não",null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        conferentesFiltrados.remove(conferenteExcluir) ;
                        conferentesFiltrados.remove(conferenteExcluir);
                        dao.excluir(conferenteExcluir);
                        listViewConferentes.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    public void atualizar(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Conferente a  = conferentesFiltrados.get(menuInfo.position);
        nomeConferente.setText(a.getNomeConferente());
        conferenteAtualizar = conferentesFiltrados.get(menuInfo.position);
        idConfer = a.getIdConferente().toString();
    }





}