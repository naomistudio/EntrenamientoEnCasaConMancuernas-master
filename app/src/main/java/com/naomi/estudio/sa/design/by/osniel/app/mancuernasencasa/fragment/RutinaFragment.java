package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.activity.AllExersiceActivity;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.activity.MancuernasEnCasaActivity;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.R;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.adapter.MyRutinaViewAdapter;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.database.RutinaDBHelper;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.helper.SimpleItemTouchHelperCallback;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.helper.SwipeToDeleteCallback;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.interfaces.OnRutinaListChangedListener;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.interfaces.OnStartDragListener;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets.Rutina;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class RutinaFragment extends Fragment implements OnRutinaListChangedListener, OnStartDragListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static String LIST_OF_SORTED_DATA_ID = "json_list_sorted_rutina_id";
    public final static String PREFERENCE_FILE = "preference_file";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    MancuernasEnCasaActivity mancuernasEnCasaActivity;
    //List<Rutina> rutinaList;
    String mParam1;
    String mParam2;
    RutinaDBHelper rutinaDBHelper;
    private OnFragmentInteractionListener mListener;
    RecyclerView recyclerView;
    MyRutinaViewAdapter adapter;
    private ItemTouchHelper mItemTouchHelper;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    Button btn_all_exersice;
    public RutinaFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static RutinaFragment newInstance(String param1, String param2) {
        RutinaFragment fragment = new RutinaFragment();
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

        if (getActivity()!= null){
            mancuernasEnCasaActivity = (MancuernasEnCasaActivity) getActivity();
        }
        rutinaDBHelper = new RutinaDBHelper(mancuernasEnCasaActivity);

        //rutinaList = new ArrayList<>();

    }

    @SuppressLint("CommitPrefEdits")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rutina, container, false);
        recyclerView = view.findViewById(R.id.rv_rutinas);
        btn_all_exersice = view.findViewById(R.id.btn_all_exersice);
        btn_all_exersice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mancuernasEnCasaActivity, AllExersiceActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        mSharedPreferences = mancuernasEnCasaActivity.getApplicationContext()
                .getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        //rutinaList.clear();
        //rutinaList = getSampleData();
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mancuernasEnCasaActivity) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MyRutinaViewAdapter(mancuernasEnCasaActivity,RutinaFragment.this,RutinaFragment.this,mSharedPreferences);
        adapter.setOnRutinaLisItemClickListener(mancuernasEnCasaActivity);
        ItemTouchHelper.Callback callback = new SwipeToDeleteCallback(adapter,mancuernasEnCasaActivity);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
        mancuernasEnCasaActivity.getBtnCreateRutina().show();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && mancuernasEnCasaActivity.getBtnCreateRutina().getVisibility() == View.VISIBLE) {
                    mancuernasEnCasaActivity.getBtnCreateRutina().hide();
                } else if (dy < 0 && mancuernasEnCasaActivity.getBtnCreateRutina().getVisibility() != View.VISIBLE) {
                    mancuernasEnCasaActivity.getBtnCreateRutina().show();
                }
            }
        });
        /*if(adapter.getmValues().isEmpty()){
            mancuernasEnCasaActivity.getViewRutinaAdd().setVisibility(View.VISIBLE);
        }*/

        if (savedInstanceState == null) {
            adapter.updateItems(true);
        } else {
            adapter.updateItems(false);        }

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
        if (context instanceof OnFragmentBlanckInteractionListener) {
            mListener = (OnFragmentBlanckInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentBlanckInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public MyRutinaViewAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void onRutinaListChanged(List<Rutina> rutinaList) {
        //after drag and drop operation, the new list of Customers is passed in here
        //create a List of Long to hold the Ids of the
        //Customers in the List
        List<Integer> listOfSortedExerciseId = new ArrayList<>();

        for (Rutina rutina : rutinaList){
            listOfSortedExerciseId.add(rutina.getmId());
        }

        //convert the List of Longs to a JSON string
        Gson gson = new Gson();
        String jsonListOfSortedCustomerIds = gson.toJson(listOfSortedExerciseId);
        //save to SharedPreference
        mEditor.putString(LIST_OF_SORTED_DATA_ID, jsonListOfSortedCustomerIds).commit();
        mEditor.commit();
    }
    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
    @Override
    public void onResume() {
        super.onResume();
        adapter.updateItems(false);
    }
    /*private ArrayList<Rutina> getSampleData(){
        //Get the sample data
        ArrayList<Rutina> rutinas  = new ArrayList<>();
        ArrayList<Rutina> sortedCustomers = new ArrayList<>();
        List<Integer> listOfSortedCustomersId = new ArrayList<>();
        rutinaList.clear();
        rutinas.clear();
        rutinas = rutinaDBHelper.getAllRutinas();
        //create an empty array to hold the list of sorted Customers
        //get the JSON array of the ordered of sorted customers
        String jsonListOfSortedCustomerId = mSharedPreferences.getString(LIST_OF_SORTED_DATA_ID, "");
        //check for null
        if (!Objects.requireNonNull(jsonListOfSortedCustomerId).isEmpty()){

            //convert JSON array into a List<Long>
            Gson gson = new Gson();
            listOfSortedCustomersId = gson.fromJson(jsonListOfSortedCustomerId, new TypeToken<List<Integer>>(){}.getType());

            //build sorted list
            if (listOfSortedCustomersId != null && listOfSortedCustomersId.size() > 0){
                for (int id: listOfSortedCustomersId){
                    for (Rutina entrenamiento : rutinas){
                        if (entrenamiento.getmId() == id){
                            sortedCustomers.add(entrenamiento);
                            rutinas.remove(entrenamiento);
                            break;
                        }
                    }
                }
            }

            //if there are still customers that were not in the sorted list
            //maybe they were added after the last drag and drop
            //add them to the sorted list
            if (rutinas.size() > 0){
                sortedCustomers.addAll(rutinas);
            }

            return sortedCustomers;
        }else {
            return rutinas;
        }
    }*/

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
