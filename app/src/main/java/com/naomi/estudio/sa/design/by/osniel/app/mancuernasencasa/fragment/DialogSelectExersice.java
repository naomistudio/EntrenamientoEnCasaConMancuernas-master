package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.R;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.activity.RutinaUserActivity;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets.ExerciseItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DialogSelectExersice.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DialogSelectExersice#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogSelectExersice extends BottomSheetDialogFragment implements RadioGroup.OnCheckedChangeListener,View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    List<ExerciseItem> exerciseItems = new ArrayList<>();
    RutinaUserActivity rutinaUserActivity;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RadioGroup radioGroupOpcion;
    AppCompatRadioButton rbBiceps,rbTriceps,rbPechos,rbEspalda,rbHombros,rbPiernas,rbAbdominales,rbAntebrazos;
    private OnFragmentInteractionListener mListener;
    ImageButton btn_aceptar_exersice,btn_cancelar_exersice;
    String nameExersice;

    public DialogSelectExersice() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DialogSelectExersice.
     */
    // TODO: Rename and change types and number of parameters
    public static DialogSelectExersice newInstance(String param1, String param2) {
        DialogSelectExersice fragment = new DialogSelectExersice();
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
            rutinaUserActivity = (RutinaUserActivity) getActivity();
        }

        //rutinaUserActivity.getTv_all_exersice().setText("Todos los Ejercicios");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_dialog_select_exersice, container, false);

        init_dialog_(view);


        return view;
    }

    private void init_dialog_(View view) {
        rbBiceps = view.findViewById(R.id.rbBiceps);
        rbTriceps = view.findViewById(R.id.rbTriceps);
        rbPechos = view.findViewById(R.id.rbPechos);
        rbEspalda = view.findViewById(R.id.rbEspalda);
        rbHombros = view.findViewById(R.id.rbHombros);
        rbPiernas = view.findViewById(R.id.rbPiernas);
        rbAbdominales = view.findViewById(R.id.rbAbdominales);
        rbAntebrazos = view.findViewById(R.id.rbAntebrazos);
        btn_aceptar_exersice = view.findViewById(R.id.btn_aceptar_exersice);
        btn_cancelar_exersice = view.findViewById(R.id.btn_cancelar_exersice);
        radioGroupOpcion = view.findViewById(R.id.rgAddExersice);

        radioGroupOpcion.setOnCheckedChangeListener(DialogSelectExersice.this);
        btn_aceptar_exersice.setOnClickListener(DialogSelectExersice.this);
        btn_cancelar_exersice.setOnClickListener(DialogSelectExersice.this);

        nameExersice = "Biceps";
        rbBiceps.setChecked(true);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String nameExersice) {
        if (mListener != null) {
            mListener.onFragmentInteraction(nameExersice);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {

            mListener = (OnFragmentInteractionListener) getActivity();

        }catch (ClassCastException e){

            Log.e("TAG","onAttach : ClassCastException" + e.getMessage());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (checkedId == R.id.rbBiceps) {
            nameExersice = "Biceps";
        } else if (checkedId == R.id.rbTriceps) {
            nameExersice = "Triceps";

        } else if (checkedId == R.id.rbPechos) {
            nameExersice = "Pechos";

        } else if (checkedId == R.id.rbEspalda) {
            nameExersice = "Espalda";

        } else if (checkedId == R.id.rbHombros) {
            nameExersice = "Hombros";

        } else if (checkedId == R.id.rbPiernas) {
            nameExersice = "Piernas";

        } else if (checkedId == R.id.rbAbdominales) {
            nameExersice = "Abdominales";
        } else{
            nameExersice = "Antebrazos";
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_aceptar_exersice:
                onButtonPressed(nameExersice);
                getDialog().dismiss();

                break;

            case R.id.btn_cancelar_exersice:
                getDialog().dismiss();
                break;
            default:
                break;



        }

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String uri);
    }
}
