package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.R;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.adapter.MyRutinaViewAdapter;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.database.EntrenamientosDBHelper;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.fragment.CuentaFragment;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.fragment.DialogCrearRutinaFragment;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.fragment.FavoritoFragment;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.fragment.RutinaFragment;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.interfaces.DoubleClickListener;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets.Rutina;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.view.DoubleClick;

import java.util.ArrayList;
import java.util.List;

public class MancuernasEnCasaActivity extends AppCompatActivity implements  DialogCrearRutinaFragment.OnClickDialogRutinaOkCreateListener,MyRutinaViewAdapter.OnListFragmentRutinaInteractionListener {
    public final static String PREFERENCE_FILE = "preference_file";
    BottomNavigationViewEx bnvMenuMain;
    FloatingActionButton btnCreateRutina;
    AppBarLayout appBarLayout;
    View mContentLinear;
    View viewRutinaAdd;
    TextView tvCategoryName;
    Toolbar toolbar;
    AppBarLayout.LayoutParams params;
    Rutina rutina;
    private SharedPreferences mSharedPreferences;
    FragmentManager fragmentManager = getSupportFragmentManager();
    int posUpdateRutina;
    boolean editRutina;
    int IdFragment = 0;
    EntrenamientosDBHelper dbHelper;
    private BottomNavigationViewEx.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationViewEx.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {

                case R.id.navigation_home:
                    if(IdFragment != 0){

                        setIdFragment(0);
                        tvCategoryName.setText("Mancuernas en Casa");
                        fragmentManager.beginTransaction()
                                .replace(R.id.ll_mancuernas_id, RutinaFragment.newInstance("rutina","rutina"),"rutina")
                                .commit();

                        expandToolbar();

                    }
                    return true;

                case R.id.navigation_cuenta:
                    if(IdFragment != 1){

                        setIdFragment(1);
                        tvCategoryName.setText("Yo");
                        fragmentManager.beginTransaction()
                                .replace(R.id.ll_mancuernas_id, CuentaFragment.newInstance("cuenta","cuenta"),"user")
                                .commit();
                        expandToolbar();

                    }


                    return true;
                case R.id.navigation_mas:

                    if(IdFragment != 2){

                        setIdFragment(2);
                        tvCategoryName.setText("Mas");
                        fragmentManager.beginTransaction()
                                .replace(R.id.ll_mancuernas_id, FavoritoFragment.newInstance("Favorito","Favorito"),"mas")
                                .commit();
                        expandToolbar();

                    }

                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mancuernas_en_casa);
        setupToolbar();
        dbHelper = new EntrenamientosDBHelper(this);

        //params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        //params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
        bnvMenuMain = findViewById(R.id.bnvMenuMain);
        tvCategoryName = findViewById(R.id.tvCategoryName);
        mContentLinear = findViewById(R.id.ll_mancuernas_id);
        appBarLayout = findViewById(R.id.appBarLayoutMain);
        btnCreateRutina = findViewById(R.id.btnCreateRutina);
        viewRutinaAdd = findViewById(R.id.viewRutinaAdd);
        mSharedPreferences = getApplicationContext()
                .getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
        btnCreateRutina.setOnClickListener(new DoubleClick(new DoubleClickListener() {
            @Override
            public void onSingleClick(View view) {

                editRutina = false;
                rutina = new Rutina("Biceps y Pechos","Lunes","");
                DialogCrearRutinaFragment buttomSheetDialogFragment = DialogCrearRutinaFragment.newInstance(rutina,false);
                buttomSheetDialogFragment.setStyle(DialogFragment.STYLE_NORMAL,R.style.ButtomSheetDialogFragmentTheme);
                buttomSheetDialogFragment.show(getSupportFragmentManager(),"DialogCrearRutinaFragment");

            }

            @Override
            public void onDoubleClick(View view) {

            }
        }));
        setTitle("");
        setup_buttom_menu();
        fragmentManager.beginTransaction().replace(R.id.ll_mancuernas_id, RutinaFragment.newInstance("rutina","rutina"),"rutina").commit();

    }

    public View getViewRutinaAdd() {
        return viewRutinaAdd;
    }

    public int getIdFragment() {
        return IdFragment;
    }

    public FloatingActionButton getBtnCreateRutina() {
        return btnCreateRutina;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case 2:

                if (resultCode == RESULT_OK) {

                }

                break;

        }
    }

    public BottomNavigationViewEx getBnvMenuMain() {
        return bnvMenuMain;
    }

    protected void setupToolbar() {

        toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);

    }

    private void setup_buttom_menu() {

        bnvMenuMain.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bnvMenuMain.enableAnimation(false);
        bnvMenuMain.enableShiftingMode(IdFragment,false);

    }
    public void expandToolbar(){
        //setExpanded(boolean expanded, boolean animate)
        appBarLayout.setExpanded(true, true);
    }

    public void setIdFragment(int idFragment) {
        IdFragment = idFragment;
    }

    @Override
    public void onListFragmentInteraction(Rutina item) {

        RutinaUserActivity.openCuenta(this,item);

    }

    @Override
    public void onClickLongEditRutina(Rutina item, int pos) {

        posUpdateRutina = pos;
        editRutina = true;
        DialogCrearRutinaFragment buttomSheetDialogFragment = DialogCrearRutinaFragment.newInstance(item,true);
        buttomSheetDialogFragment.setStyle(DialogFragment.STYLE_NORMAL,R.style.ButtomSheetDialogFragmentTheme);
        buttomSheetDialogFragment.show(getSupportFragmentManager(),"DialogCrearRutinaFragment");

    }

    @Override
    public void SendOkCreateRutina(final Rutina param1) {
        if (editRutina){
            RutinaFragment rutinaFragment = (RutinaFragment)getSupportFragmentManager().findFragmentByTag("rutina");
            if(rutinaFragment != null){


                rutinaFragment.getAdapter().updateItem(param1,posUpdateRutina);

            }
        }else{

            RutinaUserActivity.openCuenta(this,param1);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    RutinaFragment rutinaFragment = (RutinaFragment)getSupportFragmentManager().findFragmentByTag("rutina");
                    if (rutinaFragment != null) {

                        rutinaFragment.getAdapter().insertRutina(param1);

                    }

                }
            },250);

        }

    }


}
