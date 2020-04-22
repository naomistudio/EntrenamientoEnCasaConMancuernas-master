package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.R;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.activity.RutinaUserActivity;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.adapter.AddEntrenamientoAdapter;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.adapter.EntrenamientoAdapter;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.database.EntrenamientosDBHelper;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.helper.SimpleItemTouchHelperCallback;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.helper.SimpleSelectHelperCallback;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets.Entrenamiento;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddEjerciciosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddEjerciciosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddEjerciciosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    AddEntrenamientoAdapter entrenamientoAdapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView rvExerciseDevelop;
    private ItemTouchHelper mItemTouchHelper;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    public static String LIST_OF_SORTED_DATA_ID = "addexersicelist";
    public final static String PREFERENCE_FILE = "preference_file";
    ArrayList<Entrenamiento> entrenamientoList = new ArrayList<>();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private boolean mParam2;
    EntrenamientosDBHelper dbHelper;
    private OnFragmentInteractionListener mListener;

    RutinaUserActivity activity;

    public AddEjerciciosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddEjerciciosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddEjerciciosFragment newInstance(String param1, boolean param2) {
        AddEjerciciosFragment fragment = new AddEjerciciosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putBoolean(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getBoolean(ARG_PARAM2);
        }
        if (getActivity()!= null){
            activity = (RutinaUserActivity) getActivity();
        }

        if (!mParam2){
            activity.getTv_user_rutina().setText(mParam1);
        }

        dbHelper = new EntrenamientosDBHelper(activity);
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_ejercicios, container, false);
        rvExerciseDevelop = view.findViewById(R.id.rv_add_entrenamientos);
        mSharedPreferences = activity.getApplicationContext()
                .getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        entrenamientoList = AllExercise(mParam1);
        rvExerciseDevelop.setItemAnimator(new DefaultItemAnimator());
        layoutManager = new LinearLayoutManager(activity);
        rvExerciseDevelop.setLayoutManager(layoutManager);
        entrenamientoAdapter = new AddEntrenamientoAdapter(entrenamientoList,activity);
        entrenamientoAdapter.setOnExerciseItemClickListener(activity);
        rvExerciseDevelop.setAdapter(entrenamientoAdapter);
        entrenamientoAdapter.setSelectedItems();
        return view;
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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    private ArrayList<Entrenamiento> AllExercise(String aNameCategory) {

        ArrayList<Entrenamiento> entrenamientos = new ArrayList<>();
        for (Entrenamiento entrenamiento : dbHelper.getAllEntrenamientos()){

            if(entrenamiento.getmExersice().equals(aNameCategory)){

                entrenamientos.add(entrenamiento);

            }

        }
        return entrenamientos;

    }

    public AddEntrenamientoAdapter getEntrenamientoAdapter() {
        return entrenamientoAdapter;
    }
}
