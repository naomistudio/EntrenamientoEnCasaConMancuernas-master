package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.activity.AllExersiceActivity;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.R;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.adapter.MyItemRecyclerViewAdapter;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets.ExerciseItem;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InicioFragment extends Fragment implements MyItemRecyclerViewAdapter.OnListFragmentExerciseInteractionListener{

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final int EXERCISE_ITEM = 2 ;
    // TODO: Customize parameters
    private int mColumnCount = 1;
    List<ExerciseItem> exerciseItems = new ArrayList<>();
    MyItemRecyclerViewAdapter myItemRecyclerViewAdapter;
    AllExersiceActivity allExersiceActivity;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public InicioFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static InicioFragment newInstance(int columnCount) {
        InicioFragment fragment = new InicioFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        exerciseItems.addAll(Arrays.asList(
                new ExerciseItem(1L, "Biceps"),
                new ExerciseItem(2L, "Triceps"),
                new ExerciseItem(3L, "Pechos"),
                new ExerciseItem(4L, "Espalda"),
                new ExerciseItem(5L, "Hombros"),
                new ExerciseItem(6L, "Piernas"),
                new ExerciseItem(7L, "Abdominales"),
                new ExerciseItem(8L, "Antebrazos")));
        if (getActivity()!= null){
            allExersiceActivity = (AllExersiceActivity) getActivity();
        }

        allExersiceActivity.getTv_all_exersice().setText("Todos los Ejercicios");


    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ejercicio, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            myItemRecyclerViewAdapter = new MyItemRecyclerViewAdapter(exerciseItems);
            myItemRecyclerViewAdapter.setOnExerciseLisItemClickListener(InicioFragment.this);
            recyclerView.setAdapter(myItemRecyclerViewAdapter);
        }

        return view;
    }

    private static byte[] imageViewtoBye(Drawable imageView) {

        byte[] bytes;
        Bitmap newBitmap = ((BitmapDrawable)imageView).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream(4096);
        newBitmap.compress(Bitmap.CompressFormat.PNG, 0 , stream);
        bytes = stream.toByteArray();
        return bytes;
    }


    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    @Override
    public void onListFragmentExersiceInteraction(ExerciseItem item) {

        allExersiceActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_all_exersice, AllEntrenamientosFragment.newInstance(item.getName(),item.getName()),"entrenamientos")
                .commit();

    }

}
