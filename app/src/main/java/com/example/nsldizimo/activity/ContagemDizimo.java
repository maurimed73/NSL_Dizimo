package com.example.nsldizimo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.nsldizimo.R;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ContagemDizimo extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contagem_dizimo);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.listar);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(8);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        return true;
                }

                switch(item.getItemId()){
                    case R.id.novoconfer:
                        startActivity(new Intent(getApplicationContext(),NovoConferenteActivity.class));

                        return true;
                }




                return false;
            }
        });


    }
}