package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ActionMode;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.R;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.adapter.AddEntrenamientoAdapter;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.adapter.EntrenamientoAdapter;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.fragment.AddEjerciciosFragment;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.fragment.DialogSelectExersice;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.fragment.RutinasUserFragment;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets.Entrenamiento;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets.Rutina;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class RutinaUserActivity extends AppCompatActivity implements DialogSelectExersice.OnFragmentInteractionListener, AddEntrenamientoAdapter.OnEntrenamientoItemClickListener {
    public static final String RUTINA_USERID = "rutinaId";
    Rutina rutina;
    FragmentManager fragmentManager = getSupportFragmentManager();
    TextView tv_user_rutina;
    FloatingActionButton fab_add_entrenamientos_user;
    AddEntrenamientoAdapter adapter;
    Toolbar toolbar;
    View rutinacrearGone;
    AppBarLayout appBarLayout;
    AppBarLayout.LayoutParams params;
    boolean activateSelect = false;
    String nameCategory;
    List<Integer> entrenamientoList = new ArrayList<>();
    int count = 0;
    public static void openCuenta(Activity openingActivity, Rutina rutina) {
        Intent intent = new Intent(openingActivity, RutinaUserActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(RUTINA_USERID,rutina);
        openingActivity.startActivityForResult(intent,2);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutina_user);
        setupToolbar();
        if (savedInstanceState == null) {
            rutina = getIntent().getParcelableExtra(RUTINA_USERID);
        } else {
            rutina = savedInstanceState.getParcelable(RUTINA_USERID);
        }
        setTitle("");
        tv_user_rutina = findViewById(R.id.tv_user_rutina);
        appBarLayout = findViewById(R.id.appBarLayoutRutina);
        rutinacrearGone = findViewById(R.id.rutinacrearGone);
        tv_user_rutina.setText(rutina.getmName());
        params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        fab_add_entrenamientos_user = findViewById(R.id.fab_add_entrenamientos_user);
        fab_add_entrenamientos_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogSelectExersice buttomSheetDialogFragment = DialogSelectExersice.newInstance("","");
                buttomSheetDialogFragment.setStyle(DialogFragment.STYLE_NORMAL,R.style.ButtomSheetDialogFragmentTheme);
                buttomSheetDialogFragment.show(getSupportFragmentManager(),"DialogSelectExersice");
            }
        });
        fragmentManager.beginTransaction().replace(R.id.ll_content_rutina_id, RutinasUserFragment.newInstance(rutina.getmName(),""),"rutinaUser").commit();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(RUTINA_USERID,rutina);
    }
    protected void setupToolbar() {

        toolbar = findViewById(R.id.toolbarRutina);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_action_atras_home);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnHome();
            }
        });

    }

    public TextView getTv_user_rutina() {
        return tv_user_rutina;
    }

    public void returnHome() {

        Fragment home = fragmentManager.findFragmentByTag("rutinaUser");
        AddEjerciciosFragment fragmentTwo = (AddEjerciciosFragment)getSupportFragmentManager().findFragmentByTag("addEntrena");
        if(activateSelect){
            clearActionSelection();
            tv_user_rutina.setText(nameCategory);
            if(fragmentTwo != null){
                fragmentTwo.getEntrenamientoAdapter().clearSelectionList();
            }
        }else if(home instanceof RutinasUserFragment && home.isVisible()) {
            finish();
        }else {
            fragmentManager.beginTransaction()
                    .replace(R.id.ll_content_rutina_id, RutinasUserFragment.newInstance(rutina.getmName(),""),"rutinaUser")
                    .commit();
        }
    }
    @Override
    public void onFragmentInteraction(String uri) {
        nameCategory = uri;
        fragmentManager.beginTransaction().replace(R.id.ll_content_rutina_id, AddEjerciciosFragment.newInstance(uri,activateSelect),"addEntrena").commit();
    }


    @Override
    public void onBackPressed() {
        Fragment home = fragmentManager.findFragmentByTag("rutinaUser");
        final AddEjerciciosFragment fragmentTwo = (AddEjerciciosFragment)getSupportFragmentManager().findFragmentByTag("addEntrena");
        if(activateSelect){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Añadir Ejercicios")
                    .setCancelable(false)
                    .setIcon(R.drawable.ic_launcher_background)
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Toast.makeText(getApplicationContext(), String.valueOf(entrenamientoList.size()) + "Añadidos", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(fragmentTwo != null){
                                fragmentTwo.getEntrenamientoAdapter().clearSelectionList();
                                fragmentManager.beginTransaction()
                                        .replace(R.id.ll_content_rutina_id, RutinasUserFragment.newInstance(rutina.getmName(),""),"rutinaUser")
                                        .commit();
                                Toast.makeText(getApplicationContext(), String.valueOf(entrenamientoList.size()), Toast.LENGTH_SHORT).show();
                            }
                            clearActionSelection();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }else if (home instanceof RutinasUserFragment && home.isVisible()) {
            super.onBackPressed();
        }else {
            fragmentManager.beginTransaction()
                    .replace(R.id.ll_content_rutina_id, RutinasUserFragment.newInstance(rutina.getmName(),""),"rutinaUser")
                    .commit();
        }
    }
    @Override
    public void onItemClicked(int position,Entrenamiento entrenamiento) {
        if (activateSelect) {
            toggleSelection(entrenamiento.getmId(),position);
        }
    }
    @Override
    public boolean onItemLongClicked(int position,Entrenamiento entrenamiento) {
        if (!activateSelect) {
            activateSelect();//actionMode = startSupportActionMode(actionModeCallback);
        }
        toggleSelection(entrenamiento.getmId(),position);
        return true;
    }
    public List<Integer> getEntrenamientoList() {
        return entrenamientoList;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_add) {
            Toast.makeText(getApplicationContext(), String.valueOf(entrenamientoList.size()), Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void activateSelect(){

        if(!activateSelect){
            toolbar.inflateMenu(R.menu.selected_menu);
            params.setScrollFlags(0);
            toolbar.setNavigationIcon(R.drawable.ic_close);
            toolbar.setLogo(null);
            toolbar.setTitle(null);
            //floatingActionButton.setVisibility(View.INVISIBLE);
            //floatingActionButtonDelete.setVisibility(View.VISIBLE);
            activateSelect = true;
        }
    }
    public void clearActionSelection() {
        toolbar.getMenu().clear();
        params.setScrollFlags(1);
        toolbar.setNavigationIcon(R.drawable.ic_action_atras_home);
        activateSelect = false;
        //floatingActionButton.setVisibility(View.VISIBLE);
        //floatingActionButtonDelete.setVisibility(View.INVISIBLE);
        //count = 0;
        //textViewSelection.setText(String.format("Seleccionado/s: %s", String.valueOf(count)));
    }
    private void toggleSelection(int id,int position) {
        AddEjerciciosFragment fragment = (AddEjerciciosFragment)getSupportFragmentManager().findFragmentByTag("addEntrena");
        if(fragment != null){
            adapter = fragment.getEntrenamientoAdapter();
        }
        adapter.toggleSelectionList(id,position);
        int count = entrenamientoList.size();

        if (count == 0) {
            clearActionSelection();
            tv_user_rutina.setText(nameCategory);
        } else {
            tv_user_rutina.setText(String.format(String.valueOf(count) + " %s","seleccionados"));
        }
    }
}
