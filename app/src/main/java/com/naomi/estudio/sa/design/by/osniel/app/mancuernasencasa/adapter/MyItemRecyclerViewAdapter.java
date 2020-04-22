package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.R;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.interfaces.ItemTouchHelperAdapter;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.interfaces.ItemTouchHelperViewHolder;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.interfaces.OnExersiceListChangedListener;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.interfaces.OnRutinaListChangedListener;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.interfaces.OnStartDragListener;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets.ExerciseItem;

import java.util.Collections;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>  implements ItemTouchHelperAdapter {

    private final List<ExerciseItem> mValues;
    private OnListFragmentExerciseInteractionListener mListener;
    Context context;
    private OnStartDragListener mDragStartListener;
    private OnExersiceListChangedListener mListChangedListener;

    public MyItemRecyclerViewAdapter(List<ExerciseItem> items) {
        mValues = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_exersice, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIvDrag.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        holder.mContentView.setText(mValues.get(position).name);

        switch (position){
            case 0:
                holder.mLogoExercise.setImageResource(R.drawable.img_biceps_exercise);
                break;
            case 1:
                holder.mLogoExercise.setImageResource(R.drawable.img_triceps_exercise);
                break;
            case 2:
                holder.mLogoExercise.setImageResource(R.drawable.img_pecho_exercise);
                break;
            case 3:
                holder.mLogoExercise.setImageResource(R.drawable.img_espalda_exercise);
                break;
            case 4:
                holder.mLogoExercise.setImageResource(R.drawable.img_hombros_exercise);
                break;
            case 5:
                holder.mLogoExercise.setImageResource(R.drawable.img_piernas_exercise);
                break;
            case 6:
                holder.mLogoExercise.setImageResource(R.drawable.img_abdominales_exercise);
                break;
            case 7:
                holder.mLogoExercise.setImageResource(R.drawable.img_antebrazos_exercise);
                break;
        }

        /*ByteArrayInputStream bais = new ByteArrayInputStream(mValues.get(position).getmLogoExercise());
        Bitmap bitmap = BitmapFactory.decodeStream(bais);
        holder.mLogoExercise.setImageBitmap(bitmap);*/

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentExersiceInteraction(holder.mItem);
                }
            }
        });
    }

    public void setOnExerciseLisItemClickListener(OnListFragmentExerciseInteractionListener onListFragmentInteractionListener) {
        this.mListener = onListFragmentInteractionListener;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mValues, fromPosition, toPosition);
        mListChangedListener.onExersiceListChanged(mValues);
        notifyItemMoved(fromPosition, toPosition);

    }

    @Override
    public void onItemDismiss(int position) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {
        private final View mView;
        private final ImageView mIvDrag;
        private final TextView mContentView;
        private final GifImageView mLogoExercise;
        private ExerciseItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIvDrag = view.findViewById(R.id.item_drag);
            mContentView = view.findViewById(R.id.tv_name_exercise_item);
            mLogoExercise = view.findViewById(R.id.iv_logo_menu_main);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

        @Override
        public void onItemSelected() {

            itemView.setBackgroundColor(context.getResources().getColor(R.color.colorDarkerGray));
            int semiTransparente = Color.argb(155,185,185,185);
            mLogoExercise.setColorFilter(semiTransparente, PorterDuff.Mode.SRC_ATOP);

        }

        @Override
        public void onItemClear() {

            itemView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
            mLogoExercise.setColorFilter(0);

        }
    }
    public interface OnListFragmentExerciseInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentExersiceInteraction(ExerciseItem item);
    }
}
