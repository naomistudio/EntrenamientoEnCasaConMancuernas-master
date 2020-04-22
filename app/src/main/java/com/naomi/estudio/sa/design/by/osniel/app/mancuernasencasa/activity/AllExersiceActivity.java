package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.R;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.fragment.InicioFragment;

public class AllExersiceActivity extends AppCompatActivity {

    TextView tv_all_exersice;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_exersice);
        setupToolbar();
        tv_all_exersice = findViewById(R.id.tv_all_exersice);
        setTitle("");
        fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_all_exersice, InicioFragment.newInstance(0),"exersice");
        fragmentTransaction.commit();

    }

    public TextView getTv_all_exersice() {
        return tv_all_exersice;
    }

    protected void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbarAll);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_action_atras_home);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment home = fragmentManager.findFragmentByTag("exersice");

                if (home instanceof InicioFragment && home.isVisible()) {
                    finish();
                }else {
                    fragmentManager.beginTransaction()
                            .replace(R.id.content_all_exersice, InicioFragment.newInstance(0),"exersice")
                            .commit();


                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Fragment home = fragmentManager.findFragmentByTag("exersice");

        if (home instanceof InicioFragment && home.isVisible()) {
            super.onBackPressed();
        }else {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_all_exersice, InicioFragment.newInstance(0),"exersice")
                    .commit();


        }
    }



}
