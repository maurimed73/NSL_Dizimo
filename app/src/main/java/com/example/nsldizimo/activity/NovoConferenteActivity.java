package com.example.nsldizimo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nsldizimo.R;
import com.example.nsldizimo.database.ConferenteDAO;
import com.example.nsldizimo.model.Conferente;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class NovoConferenteActivity extends AppCompatActivity {

    private EditText nomeConferente;
    BottomNavigationView bottomNavigationView;
    private ConferenteDAO dao;
    private ListView listViewConferentes;
    private List<Conferente>conferentes;
    private List<Conferente>conferentesFiltrados = new ArrayList<>();
    public static Conferente conferenteAtualizar;
    public static String idConfer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_conferente);


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        nomeConferente = findViewById(R.id.editTextTextPersonName);

        dao = new ConferenteDAO(this);
        listViewConferentes = (ListView)findViewById(R.id.listView_conferentes);
        conferentes = dao.obterTodos();
        conferentesFiltrados.addAll(conferentes);
        ArrayAdapter<Conferente>adaptador = new ArrayAdapter<Conferente>(this, R.layout.list_item_text,conferentesFiltrados);
        listViewConferentes.setAdapter(adaptador);
        registerForContextMenu(listViewConferentes);

        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.listar);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(8);




        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),ContagemDizimo.class));

                        return true;
                }

                switch(item.getItemId()){
                    case R.id.novoconfer:
                       return true;
                }




                return false;
            }
        });
    }

    public void salvar (View view){
        if (idConfer == null){
            Conferente a = new Conferente();
            a.setNomeConferente(nomeConferente.getText().toString());
            long id = dao.inserir(a);
            Toast.makeText(this, "Conferente inserido", Toast.LENGTH_SHORT).show();
            atualizaListView();
            closeKeyboard(view);
            idConfer=null;

            
        }
            else{
                conferenteAtualizar.setNomeConferente(nomeConferente.getText().toString());
                dao.atualizar(conferenteAtualizar);
            Toast.makeText(this, "Atualizado com sucesso", Toast.LENGTH_SHORT).show();
            atualizaListView();
            closeKeyboard(view);
            idConfer=null;

        }
        }

    private void closeKeyboard(View v) {
        if(v!=null){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);
        }

    }

    private void atualizaListView() {
        conferentes = dao.obterTodos();
        conferentesFiltrados.clear();
        conferentesFiltrados.addAll(conferentes);
        listViewConferentes.invalidateViews();
        ArrayAdapter<Conferente>adaptador = new ArrayAdapter<Conferente>(this, R.layout.list_item_text,conferentesFiltrados);
        listViewConferentes.setAdapter(adaptador);
        nomeConferente.setText("");
    }

    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate((R.menu.menu_contexto),menu);

    }

    public void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Conferente conferenteExcluir = conferentesFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
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