package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.activity.MancuernasEnCasaActivity;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.R;

import java.util.Objects;

public class NameRutinaFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG_NAME_RUTINA = "NameRutinaFragment";
    private static final String ARG_PARAM1 = "param1";
    MancuernasEnCasaActivity activity;

    // TODO: Rename and change types of parameters
    EditText et_name_rutina;
    Button btn_aceptar_name_rutina,btn_cancelar_name_rutina;
    private String mParam1;
    private OnClickNameRutinaOkListener mOnClickNameRutinaOkListener;

    public NameRutinaFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static NameRutinaFragment newInstance(String param1) {
        NameRutinaFragment fragment = new NameRutinaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
        if(getActivity() != null){

            activity = (MancuernasEnCasaActivity) getActivity();

        }


    }
    @Override
    public void onResume() {
        // Store access variables for window and blank point
        Window window = getDialog().getWindow();
        Point size = new Point();
        // Store dimensions of the screen in `size`
        Display display = Objects.requireNonNull(window).getWindowManager().getDefaultDisplay();
        display.getSize(size);
        // Set the width of the dialog proportional to 75% of the screen width
        window.setLayout((int) (size.x * 0.85), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        // Call super onResume after sizing
        super.onResume();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Window window = dialog.getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        //Objects.requireNonNull(window).setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        //window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        //Objects.requireNonNull(window).clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        //dialog.setCancelable(false);
        return dialog;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = activity.getLayoutInflater().inflate(R.layout.fragment_name_rutina, container, false);

        init_dialog_entrenamiento(view);
        et_name_rutina.setText(mParam1);
        et_name_rutina.requestFocus();
        et_name_rutina.setSelection(0,mParam1.length());
        setupEtName();
        setupSendNameButton();
        btn_cancelar_name_rutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {

            mOnClickNameRutinaOkListener = (OnClickNameRutinaOkListener) getTargetFragment();

        }catch (ClassCastException e){

            Log.e(TAG_NAME_RUTINA,"onAttach : ClassCastException" + e.getMessage());


        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnClickNameRutinaOkListener = null;
    }


    public  interface OnClickNameRutinaOkListener{
        void SendOkNameRutina(String param1);
    }

    public void init_dialog_entrenamiento(View customDialog){
        et_name_rutina = customDialog.findViewById(R.id.et_name_rutina);
        btn_aceptar_name_rutina = customDialog.findViewById(R.id.btn_aceptar_name_rutina);
        btn_cancelar_name_rutina = customDialog.findViewById(R.id.btn_cancelar_name_rutina);

    }
    public boolean isValidName (String name){
        int count = 0;

        for (char item:name.toCharArray()) {

            String s = String.valueOf(item);
            if(s.equals(" ")){

                count ++;

            }

        }

        return !(name.toCharArray().length == count);


    }
    private boolean validateName(String string) {
        if (TextUtils.isEmpty(string) || !isValidName(string)|| string.length() == 0) {
            btn_aceptar_name_rutina.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.shake_error));
            return false;
        }

        return true;
    }
    private void setupEtName() {
        et_name_rutina.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                btn_aceptar_name_rutina.setBackground(validateName(String.valueOf(charSequence))?getResources().getDrawable(R.drawable.border_bottom) :
                            getResources().getDrawable(R.drawable.border_bottom_gray));

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void setupSendNameButton() {

        btn_aceptar_name_rutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validateName(et_name_rutina.getText().toString())){

                    if(mOnClickNameRutinaOkListener != null)

                        mOnClickNameRutinaOkListener.SendOkNameRutina(et_name_rutina.getText().toString());
                        getDialog().dismiss();




                }

            }
        });
    }
}
