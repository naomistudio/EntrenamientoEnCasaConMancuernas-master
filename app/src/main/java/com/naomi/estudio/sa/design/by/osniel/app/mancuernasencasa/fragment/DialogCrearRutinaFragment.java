package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.activity.MancuernasEnCasaActivity;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.R;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.activity.RutinaUserActivity;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.database.RutinaDBHelper;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets.Rutina;


public class DialogCrearRutinaFragment extends BottomSheetDialogFragment implements RadioGroup.OnCheckedChangeListener,View.OnClickListener,NameRutinaFragment.OnClickNameRutinaOkListener{
    private static final String TAG_DIALOG_RUTINA = "MySheetDialogFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String EDIT_RUTINA = "editRutina";
    RadioGroup radioGroupOpcion;
    AppCompatRadioButton rbLunes,rbMartes,rbMiercoles,rbJueves,rbViernes,rbSabado,rbDomingo;
    LinearLayout llcontentCrearRutina;
    TextView titleCrearRutina;

    String nombreRutina;
    String diaRutina;

    public  interface OnClickDialogRutinaOkCreateListener{

        void SendOkCreateRutina(Rutina param1);

    }
    TextView tv_name_rutina;
    Button btn_aceptar_rutina,btn_cancelar_rutina;
    OnClickDialogRutinaOkCreateListener mOnClickDialogRutinaListener;
    Rutina rutina;
    boolean editRutina;
    MancuernasEnCasaActivity mancuernasEnCasaActivity;
    public DialogCrearRutinaFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static DialogCrearRutinaFragment newInstance(Rutina param1,boolean editRutina) {
        DialogCrearRutinaFragment fragment = new DialogCrearRutinaFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, param1);
        args.putBoolean(EDIT_RUTINA, editRutina);
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rutina = getArguments().getParcelable(ARG_PARAM1);
            editRutina = getArguments().getBoolean(EDIT_RUTINA);
        }
        if (getActivity()!= null){
            mancuernasEnCasaActivity = (MancuernasEnCasaActivity) getActivity();
        }
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = mancuernasEnCasaActivity.getLayoutInflater().inflate(R.layout.dialog_crear_rutina, container, false);
        init_dialog_entrenamiento(view);
        radioGroupOpcion.setOnCheckedChangeListener(DialogCrearRutinaFragment.this);
        btn_aceptar_rutina.setOnClickListener(DialogCrearRutinaFragment.this);
        tv_name_rutina.setOnClickListener(DialogCrearRutinaFragment.this);
        btn_cancelar_rutina.setOnClickListener(DialogCrearRutinaFragment.this);

        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Window window = dialog.getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressedOk(Rutina rutina) {
        if (mOnClickDialogRutinaListener != null) {

            mOnClickDialogRutinaListener.SendOkCreateRutina(rutina);

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {

            mOnClickDialogRutinaListener = (OnClickDialogRutinaOkCreateListener) getActivity();

        }catch (ClassCastException e){

            Log.e(TAG_DIALOG_RUTINA,"onAttach : ClassCastException" + e.getMessage());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnClickDialogRutinaListener = null;
    }

    public void init_dialog_entrenamiento(View customDialog){
        titleCrearRutina = customDialog.findViewById(R.id.titleCrearRutina);
        llcontentCrearRutina = customDialog.findViewById(R.id.llcontentCrearRutina);
        tv_name_rutina = customDialog.findViewById(R.id.tv_name_rutina);
        rbLunes = customDialog.findViewById(R.id.rbLunes);
        rbMartes = customDialog.findViewById(R.id.rbMartes);
        rbMiercoles = customDialog.findViewById(R.id.rbMiercoles);
        rbJueves = customDialog.findViewById(R.id.rbJueves);
        rbViernes = customDialog.findViewById(R.id.rbViernes);
        rbSabado = customDialog.findViewById(R.id.rbSabado);
        rbDomingo = customDialog.findViewById(R.id.rbDomingo);
        btn_aceptar_rutina = customDialog.findViewById(R.id.btn_aceptar_rutina);
        btn_cancelar_rutina = customDialog.findViewById(R.id.btn_cancelar_rutina);
        radioGroupOpcion = customDialog.findViewById(R.id.radioGroupId);
        titleCrearRutina.setText( editRutina ? "Editar Rutina" : "Rutina Nueva");
        tv_name_rutina.setText(rutina.getmName());
        nombreRutina = rutina.getmName();
        if(editRutina){
            switch (rutina.getmDia()){
                case "Lunes":
                    rbLunes.setChecked(true);
                    diaRutina = "Lunes";
                    break;
                case "Martes":
                    rbMartes.setChecked(true);
                    diaRutina = "Martes";

                    break;
                case "Miercoles":
                    rbMiercoles.setChecked(true);
                    diaRutina = "Miercoles";

                    break;
                case "Jueves":
                    rbJueves.setChecked(true);
                    diaRutina = "Jueves";

                    break;
                case "Viernes":
                    rbViernes.setChecked(true);
                    diaRutina = "Viernes";

                    break;
                case "Sabado":
                    rbSabado.setChecked(true);
                    diaRutina = "Sabado";

                    break;
                case "Domingo":
                    rbDomingo.setChecked(true);
                    diaRutina = "Domingo";

                    break;
                default:
                    break;
            }

        }else{

            rbLunes.setChecked(true);
            diaRutina = "Lunes";
        }



    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_aceptar_rutina:
                rutina.setmName(nombreRutina);
                rutina.setmDia(diaRutina);
                onButtonPressedOk(rutina);
                getDialog().dismiss();

                break;

            case R.id.tv_name_rutina:

                NameRutinaFragment dialogFragment = NameRutinaFragment.newInstance(tv_name_rutina.getText().toString());
                dialogFragment.setTargetFragment(DialogCrearRutinaFragment.this,1);
                FragmentManager ft = mancuernasEnCasaActivity.getSupportFragmentManager();
                dialogFragment.setStyle(DialogFragment.STYLE_NORMAL,R.style.AppDialogThemeName);
                dialogFragment.show(ft,"NameRutinaFragment");

                break;

            case R.id.btn_cancelar_rutina:
                getDialog().dismiss();
                break;
            default:
                break;



        }
    }
    @Override
    public void SendOkNameRutina(String param1) {
        nombreRutina = param1;
        tv_name_rutina.setText(param1);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rbLunes) {
            diaRutina = "Lunes";
        } else if (checkedId == R.id.rbMartes) {
            diaRutina = "Martes";

        } else if (checkedId == R.id.rbMiercoles) {
            diaRutina = "Miercoles";

        } else if (checkedId == R.id.rbJueves) {
            diaRutina = "Jueves";

        } else if (checkedId == R.id.rbViernes) {
            diaRutina = "Viernes";

        } else if (checkedId == R.id.rbSabado) {
            diaRutina = "Sabado";

        } else if (checkedId == R.id.rbDomingo) {
            diaRutina = "Domingo";

        }

    }

}
