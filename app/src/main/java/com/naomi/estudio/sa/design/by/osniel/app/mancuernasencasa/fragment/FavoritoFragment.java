package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.activity.MancuernasEnCasaActivity;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.R;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.adapter.ExercisesCategoryAdapter;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.adapter.ExpandableRecyclerAdapter;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets.Category;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets.ExerciseCategory;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FavoritoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FavoritoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoritoFragment extends Fragment implements ExercisesCategoryAdapter.OnChildItemClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final int CATEGORY = 2;
    private ExercisesCategoryAdapter mAdapter;
    RecyclerView recyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    MancuernasEnCasaActivity mancuernasEnCasaActivity;
    private OnFragmentInteractionListener mListener;

    public FavoritoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoritoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoritoFragment newInstance(String param1, String param2) {
        FavoritoFragment fragment = new FavoritoFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        Category movie_eight = new Category("Beneficios");
        Category movie_nine = new Category("Equipamiento");
        Category movie_ten = new Category("Precausiones");
        //---------------------------------------------------------------------
        Category movie_eleven = new Category("Forrest Gump");
        Category movie_tweleve = new Category("Inception");

        ExerciseCategory molvie_category_two = new ExerciseCategory("Informacio", Arrays.asList(movie_eight, movie_nine, movie_ten));
        ExerciseCategory molvie_category_three = new ExerciseCategory("Noticias", Arrays.asList(movie_eleven,movie_tweleve));

        final List<ExerciseCategory> exerciseCategories = Arrays.asList(molvie_category_two, molvie_category_three);

        recyclerView = rootView.findViewById(R.id.rv_mancuernas_id);
        mAdapter = new ExercisesCategoryAdapter(getContext(), exerciseCategories);
        mAdapter.setOnItemChildMenuClickListener(FavoritoFragment.this);
        mAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @Override
            public void onListItemExpanded(int position) {
                ExerciseCategory expandedMovieCategory = exerciseCategories.get(position);
                //String toastMsg = getResources().getString(R.string.expanded, expandedMovieCategory.getName());

                /*Toast.makeText(MancuernasEnCasaAct.this,
                        toastMsg,
                        Toast.LENGTH_SHORT)
                        .show();*/
            }

            @Override
            public void onListItemCollapsed(int position) {
                ExerciseCategory collapsedMovieCategory = exerciseCategories.get(position);
                //String toastMsg = getResources().getString(R.string.collapsed, collapsedMovieCategory.getName());
                /*Toast.makeText(MancuernasEnCasaAct.this,
                        toastMsg,
                        Toast.LENGTH_SHORT)
                        .show();*/
            }
        });

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mancuernasEnCasaActivity.getBtnCreateRutina().hide();


        return rootView;
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
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mAdapter.onSaveInstanceState(outState);

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        mAdapter.onRestoreInstanceState(savedInstanceState);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onChildClick(View v, Category category, int position) {

        Toast.makeText(getContext(), "yes", Toast.LENGTH_SHORT).show();

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
}
