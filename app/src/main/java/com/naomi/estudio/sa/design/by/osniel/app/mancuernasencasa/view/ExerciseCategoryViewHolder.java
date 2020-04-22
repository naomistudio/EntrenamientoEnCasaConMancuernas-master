package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.view;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.R;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets.ExerciseCategory;

public class ExerciseCategoryViewHolder extends ParentViewHolder {

    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;

    private final ImageView mArrowExpandImageView;
    private final ImageView mLogoMenuMainCircleImage;
    private TextView mMovieTextView;

    public ExerciseCategoryViewHolder(View itemView) {
        super(itemView);
        mMovieTextView = itemView.findViewById(R.id.tv_exercise_category);
        mLogoMenuMainCircleImage = itemView.findViewById(R.id.civ_logo_menu_main);
        mArrowExpandImageView = itemView.findViewById(R.id.iv_arrow_expand);
    }

    public void bind(ExerciseCategory exerciseCategory) {
        mMovieTextView.setText(exerciseCategory.getName());
        if(0 == getAdapterPosition()){
            mLogoMenuMainCircleImage.setImageResource(R.drawable.ic_mancuernas_menu_main_exercises48);
        }else if (1 == getAdapterPosition()){
            mLogoMenuMainCircleImage.setImageResource(R.drawable.ic_informacion_menu_main_48);
        }else if (2 == getAdapterPosition()){
            mLogoMenuMainCircleImage.setImageResource(R.drawable.ic_noticias_menu_main_48);
        }
    }

    @Override
    public void setExpanded(boolean expanded) {
        super.setExpanded(expanded);

        if (expanded) {
            mArrowExpandImageView.setRotation(ROTATED_POSITION);
        } else {
            mArrowExpandImageView.setRotation(INITIAL_POSITION);
        }

    }

    @Override
    public void onExpansionToggled(boolean expanded) {
        super.onExpansionToggled(expanded);

        RotateAnimation rotateAnimation;
        if (expanded) { // rotate clockwise
            rotateAnimation = new RotateAnimation(ROTATED_POSITION,
                    INITIAL_POSITION,
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        } else { // rotate counterclockwise
            rotateAnimation = new RotateAnimation(-1 * ROTATED_POSITION,
                    INITIAL_POSITION,
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        }

        rotateAnimation.setDuration(200);
        rotateAnimation.setFillAfter(true);
        mArrowExpandImageView.startAnimation(rotateAnimation);

    }
}
