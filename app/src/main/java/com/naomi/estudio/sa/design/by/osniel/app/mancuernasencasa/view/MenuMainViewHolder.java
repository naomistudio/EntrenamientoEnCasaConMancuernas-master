package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.R;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets.Category;

public class MenuMainViewHolder extends ChildViewHolder {

    private TextView mExercisesTextView;
    private ImageView mExercisesImageView;

    public MenuMainViewHolder(View itemView) {
        super(itemView);
        mExercisesTextView = itemView.findViewById(R.id.tv_name_exercise_item);
        mExercisesImageView = itemView.findViewById(R.id.iv_logo_menu_main);
    }

    public void bind(final Category movies, final Context context) {
        mExercisesTextView.setText(movies.getmName());
        switch (movies.getmName()) {
            case "Biceps":
                mExercisesImageView.setImageResource(R.drawable.ic_biceps_menu_main);
                break;
            case "Triceps":
                mExercisesImageView.setImageResource(R.drawable.ic_triceps_menu_main_exercises48);
                break;
            case "Pechos":
                mExercisesImageView.setImageResource(R.drawable.ic_pechos_menu_main48color);
                break;
            case "Espalda":
                mExercisesImageView.setImageResource(R.drawable.ic_espalda_menu_main_48color);
                break;
            case "Hombros":
                mExercisesImageView.setImageResource(R.drawable.ic_hombros_menu_main48color);
                break;
            case "Piernas":
                mExercisesImageView.setImageResource(R.drawable.ic_biceps_menu_main_exercises48);
                break;
            case "Antebrazos":
                mExercisesImageView.setImageResource(R.drawable.ic_biceps_menu_main_exercises48);
                break;
            case "Abdominales":
                mExercisesImageView.setImageResource(R.drawable.ic_biceps_menu_main_exercises48);
                break;
            case "Beneficios":
                mExercisesImageView.setImageResource(R.drawable.ic_informacion_menu_main_48);
                break;
            case "Equipamiento":
                mExercisesImageView.setImageResource(R.drawable.ic_informacion_menu_main_48);
                break;
            case "Precausiones":
                mExercisesImageView.setImageResource(R.drawable.ic_informacion_menu_main_48);
                break;

        }
    }

}
