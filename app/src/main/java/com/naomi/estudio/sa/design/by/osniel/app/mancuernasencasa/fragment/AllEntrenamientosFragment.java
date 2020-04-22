package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.R;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.activity.AllExersiceActivity;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.adapter.EntrenamientoAdapter;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.database.EntrenamientosDBHelper;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.helper.SimpleItemTouchHelperCallback;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.interfaces.OnCustomerListChangedListener;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.interfaces.OnStartDragListener;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets.Entrenamiento;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AllEntrenamientosFragment extends Fragment implements DialogFragmentEntrenamiento.OnClickLikeListener,OnCustomerListChangedListener, OnStartDragListener, EntrenamientoAdapter.OnExerciseItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EntrenamientoAdapter entrenamientoAdapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView rvExerciseDevelop;
    private ItemTouchHelper mItemTouchHelper;
    ArrayList<Entrenamiento> entrenamientoList = new ArrayList<>();
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    public static String LIST_OF_SORTED_DATA_ID = "json_list_sorted_biceps_id";
    public final static String PREFERENCE_FILE = "preference_file";
    // TODO: Rename and change types of parameters
    private String mParam1;
    String mParam2;
    AllExersiceActivity allExersiceActivity;
    OnFragmentInteractionListener mListener;
    EntrenamientosDBHelper dbHelper;
    public AllEntrenamientosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllEntrenamientosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllEntrenamientosFragment newInstance(String param1, String param2) {
        AllEntrenamientosFragment fragment = new AllEntrenamientosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        if (getActivity() != null)
            allExersiceActivity = (AllExersiceActivity) getActivity();

        dbHelper = new EntrenamientosDBHelper(allExersiceActivity);
        allExersiceActivity.getTv_all_exersice().setText(mParam1);

    }

    @SuppressLint("CommitPrefEdits")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_entrenamientos, container, false);
        Context context = view.getContext();
        setHasOptionsMenu(true);
        rvExerciseDevelop = view.findViewById(R.id.rv_exercise_develop);
        mSharedPreferences = allExersiceActivity.getApplicationContext()
                .getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        entrenamientoList = getSampleData(mParam1);
        rvExerciseDevelop.setHasFixedSize(true);
        rvExerciseDevelop.setNestedScrollingEnabled(false);
        rvExerciseDevelop.setItemAnimator(new DefaultItemAnimator());
        layoutManager = new LinearLayoutManager(context);
        rvExerciseDevelop.setLayoutManager(layoutManager);
        entrenamientoAdapter = new EntrenamientoAdapter(entrenamientoList,allExersiceActivity,AllEntrenamientosFragment.this,AllEntrenamientosFragment.this);
        entrenamientoAdapter.setOnExerciseItemClickListener(AllEntrenamientosFragment.this);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(entrenamientoAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(rvExerciseDevelop);
        rvExerciseDevelop.setAdapter(entrenamientoAdapter);


        return  view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            startActivity(new Intent(getActivity(), AllExersiceActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onNoteListChanged(List<Entrenamiento> entrenamientoList) {
        //after drag and drop operation, the new list of Customers is passed in here
        //create a List of Long to hold the Ids of the
        //Customers in the List
        List<Integer> listOfSortedExerciseId = new ArrayList<>();

        for (Entrenamiento entrenamiento : entrenamientoList){
            listOfSortedExerciseId.add(entrenamiento.getmId());
        }

        //convert the List of Longs to a JSON string
        Gson gson = new Gson();
        String jsonListOfSortedCustomerIds = gson.toJson(listOfSortedExerciseId);


        //save to SharedPreference
        mEditor.putString(LIST_OF_SORTED_DATA_ID, jsonListOfSortedCustomerIds).commit();
        mEditor.commit();

    }

    @Override
    public void onCardExerciseClick(View v, ArrayList<Entrenamiento> entrenamientoList, int pos, int length) {

        DialogFragmentEntrenamiento dialogFragmentEntrenamiento = DialogFragmentEntrenamiento.newInstance(entrenamientoList,pos,length);
        dialogFragmentEntrenamiento.setTargetFragment(AllEntrenamientosFragment.this,2);
        dialogFragmentEntrenamiento.setStyle(DialogFragment.STYLE_NORMAL, R.style.AppDialogTheme);
        dialogFragmentEntrenamiento.show(allExersiceActivity.getSupportFragmentManager(),"DialogEntrenamiento");

    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {

        mItemTouchHelper.startDrag(viewHolder);


    }

    @Override
    public void SendLikeEntrenamiento(Entrenamiento entrenamiento) {
        Toast.makeText(allExersiceActivity, entrenamiento.getmName(), Toast.LENGTH_SHORT).show();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    private ArrayList<Entrenamiento> AllExercise(String aNameCategory) {

        LIST_OF_SORTED_DATA_ID = aNameCategory;

        ArrayList<Entrenamiento> entrenamientos = new ArrayList<>();
        for (Entrenamiento entrenamiento : dbHelper.getAllEntrenamientos()){

            if(entrenamiento.getmExersice().equals(aNameCategory)){

                entrenamientos.add(entrenamiento);

            }

        }
        return entrenamientos;

    }
    private ArrayList<Entrenamiento> getSampleData(String aNameExercise){

        //Get the sample data

        ArrayList<Entrenamiento> entrenamientos = AllExercise(aNameExercise);

        //create an empty array to hold the list of sorted Customers
        ArrayList<Entrenamiento> sortedCustomers = new ArrayList<>();

        //get the JSON array of the ordered of sorted customers
        String jsonListOfSortedCustomerId = mSharedPreferences.getString(LIST_OF_SORTED_DATA_ID, "");


        //check for null
        if (!Objects.requireNonNull(jsonListOfSortedCustomerId).isEmpty()){

            //convert JSON array into a List<Long>
            Gson gson = new Gson();
            List<Integer> listOfSortedCustomersId = gson.fromJson(jsonListOfSortedCustomerId, new TypeToken<List<Integer>>(){}.getType());

            //build sorted list
            if (listOfSortedCustomersId != null && listOfSortedCustomersId.size() > 0){
                for (int id: listOfSortedCustomersId){
                    for (Entrenamiento entrenamiento : entrenamientos){
                        if (entrenamiento.getmId() == id){
                            sortedCustomers.add(entrenamiento);
                            entrenamientos.remove(entrenamiento);
                            break;
                        }
                    }
                }
            }

            //if there are still customers that were not in the sorted list
            //maybe they were added after the last drag and drop
            //add them to the sorted list
            if (entrenamientos.size() > 0){
                sortedCustomers.addAll(entrenamientos);
            }

            return sortedCustomers;
        }else {
            return entrenamientos;
        }
    }
}
