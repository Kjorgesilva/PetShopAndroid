package com.example.pethealth.Activity;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.pethealth.Fragment.CadastroFragment;
import com.example.pethealth.Fragment.HistoricoFragment;
import com.example.pethealth.Fragment.HomeFragment;
import com.example.pethealth.Fragment.RelatorioMedicoFragment;
import com.example.pethealth.Fragment.VacinasFragment;
import com.example.pethealth.InterfaceHelp.InterfaceHelp;
import com.example.pethealth.R;

public class MainActivity extends AppCompatActivity implements InterfaceHelp {
    private Toolbar toolbar;
    private BottomNavigationView bottom_nav_view;
    private int contador = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();
        onClickView();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("PetHealth");

        setFragment(new HomeFragment());

    }

    @Override
    public void findView() {
        toolbar = findViewById(R.id.toolbar);
        bottom_nav_view = findViewById(R.id.bottom_nav_view);

    }

    @Override
    public void onClickView() {

        bottom_nav_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = null;

                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        selectedFragment = HomeFragment.newInstance();
                        break;
                    case R.id.navigation_cadastro:
                        selectedFragment = CadastroFragment.newInstance();
                        break;
                    case R.id.navigation_relatorio:
                        selectedFragment = RelatorioMedicoFragment.newInstance();
                        break;

                    case R.id.navigation_vacinas:
                        selectedFragment = VacinasFragment.newInstance();
                        break;

                    case R.id.navigation_historico:
                        selectedFragment = HistoricoFragment.newInstance();
                        break;



                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                transaction.replace(R.id.layout_frame, selectedFragment);
                transaction.commit();
                return true;
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.navigation_home:
                if (contador == 1) {
                    bottom_nav_view.setSelectedItemId(R.id.navigation_home);
                    contador = 0;

                } else {

                    finish();
                }

        }

        return true;
    }
    private void setFragment(Fragment valor_frag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.layout_frame, valor_frag);
        transaction.commit();
    }


}
