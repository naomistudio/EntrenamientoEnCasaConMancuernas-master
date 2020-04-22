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
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.R;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.activity.AllExersiceActivity;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets.Entrenamiento;

import java.util.ArrayList;
import java.util.Objects;

import pl.droidsonroids.gif.GifImageView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DialogFragmentEntrenamiento.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DialogFragmentEntrenamiento#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogFragmentEntrenamiento extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public  interface OnClickLikeListener{
        void SendLikeEntrenamiento(Entrenamiento entrenamiento);
    }

    public  OnClickLikeListener mOnClickLikeListener;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String TAG_DIALOG_ENTRENAMIENTO = "DialogEntrenamiento";
    AllExersiceActivity activity;

    TextView dialog_name_entrenamiento;
    TextView tv_dialog_length;
    TextView tv_dialog_pos;
    TextView dialog_descripcion;
    GifImageView dialog_photo;

    ImageButton dialog_atras;
    ImageButton dialog_bLike;
    ImageButton dialog_bClose;
    ImageButton dialog_alante;
    boolean IsCreated = false;
    int index = 1;

    // TODO: Rename and change types of parameters
    private int mParam3;
    private int mParam2;
    private ArrayList<Entrenamiento> mParam1;

    private OnFragmentInteractionListener mListener;

    public DialogFragmentEntrenamiento() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DialogFragmentEntrenamiento newInstance(ArrayList<Entrenamiento> param1,int param2,int param3) {
        DialogFragmentEntrenamiento fragment = new DialogFragmentEntrenamiento();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1,param1);
        args.putInt(ARG_PARAM2, param2);
        args.putInt(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getParcelableArrayList(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
            mParam3 = getArguments().getInt(ARG_PARAM3);
        }
        if(getActivity() != null){

            activity = (AllExersiceActivity) getActivity();

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
        window.setLayout((int) (size.x * 0.95), WindowManager.LayoutParams.WRAP_CONTENT);
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
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = activity.getLayoutInflater().inflate(R.layout.dialog_entrenamiento, container, false);
        init_dialog_entrenamiento(view);

        index = mParam2 + 1;

        button_dialog_enable(index,mParam3);
        show_dialog_pos(mParam1,mParam2,mParam3);

        dialog_bLike.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                Toast.makeText(activity, mParam1.get(index-1).getmName(), Toast.LENGTH_SHORT).show();
                onButtonPressed(mParam1.get(index-1));


            }
        });

        dialog_bClose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {

                getDialog().dismiss();

            }
        });

        dialog_atras.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                index--;
                button_dialog_enable(index,mParam3);
                show_dialog_pos(mParam1,index-1,mParam3);

            }
        });



        dialog_alante.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                index ++;
                button_dialog_enable(index,mParam3);
                show_dialog_pos(mParam1,index-1,mParam3);

            }
        });

        return view;
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Entrenamiento entrenamiento) {
        if (mOnClickLikeListener != null) {
            Toast.makeText(activity, mParam1.get(index-1).getmName(), Toast.LENGTH_SHORT).show();
            mOnClickLikeListener.SendLikeEntrenamiento(entrenamiento);
            Log.e(TAG_DIALOG_ENTRENAMIENTO,"yessss");

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {

            mOnClickLikeListener = (OnClickLikeListener)getTargetFragment();

        }catch (ClassCastException e){

            Log.e(TAG_DIALOG_ENTRENAMIENTO,"onAttach : ClassCastException" + e.getMessage());

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnClickLikeListener = null;
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void show_dialog_pos(ArrayList<Entrenamiento> entrenamientoList, int pos, int length){

        Entrenamiento entrenamiento = entrenamientoList.get(pos);
        dialog_photo.setImageResource(entrenamiento.getmImage());
        dialog_name_entrenamiento.setText(entrenamiento.getmName());
        dialog_descripcion.setText(entrenamiento.getmDescripcion());
        tv_dialog_pos.setText(String.valueOf(pos + 1));
        tv_dialog_length.setText(String.format("/%s",length));

    }

    public void init_dialog_entrenamiento(View customDialog){

        dialog_photo = customDialog.findViewById(R.id.dialog_image);
        dialog_name_entrenamiento = customDialog.findViewById(R.id.titulo);
        dialog_descripcion = customDialog.findViewById(R.id.contenido);
        dialog_alante = customDialog.findViewById(R.id.cancelar);
        dialog_atras = customDialog.findViewById(R.id.aceptar);
        dialog_bLike = customDialog.findViewById(R.id.bLike);
        dialog_bClose = customDialog.findViewById(R.id.bClose);
        tv_dialog_pos = customDialog.findViewById(R.id.tv_dialog_pos);
        tv_dialog_length = customDialog.findViewById(R.id.tv_dialog_length);

    }
    public void button_dialog_enable(int index,int length){
        dialog_atras.setEnabled(index != 1);
        dialog_alante.setEnabled(index != length);
    }
}
