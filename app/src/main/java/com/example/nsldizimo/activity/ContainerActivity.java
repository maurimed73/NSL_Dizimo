package com.example.nsldizimo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.nsldizimo.R;

import com.example.nsldizimo.fragments.HomeFragment;
import com.example.nsldizimo.fragments.ListaFragment;
import com.example.nsldizimo.fragments.NovoConferenteFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class ContainerActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    ListaFragment listaFragment = new ListaFragment();
    NovoConferenteFragment novoConferenteFragment = new NovoConferenteFragment();
    HomeFragment homeFragment = new HomeFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.nsldizimo.R.layout.activity_container);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameConteudo, homeFragment).commit();

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.novoconfer:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameConteudo, novoConferenteFragment).commit();
                        return true;
                }

                switch(item.getItemId()){
                    case R.id.listar:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameConteudo, listaFragment).commit();
                        return true;
                }
                switch(item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameConteudo, homeFragment ).commit();
                        return true;
                }




                return false;
            }
        });






    }
}