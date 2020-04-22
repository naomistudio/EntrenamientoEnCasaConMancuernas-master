package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.CardView;
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
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.interfaces.OnCustomerListChangedListener;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.interfaces.OnStartDragListener;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets.Entrenamiento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pl.droidsonroids.gif.GifImageView;

import static android.graphics.Bitmap.Config.ARGB_8888;
import static android.graphics.Bitmap.createBitmap;

public class EntrenamientoAdapter extends RecyclerView.Adapter<EntrenamientoAdapter.CellExerciseViewHolder> implements ItemTouchHelperAdapter {

    private ArrayList<Entrenamiento> entrenamientoList = new ArrayList<>();

    private Context context;
    private OnExerciseItemClickListener onExerciseItemClickListener;
    private boolean showLoadingView = false;

    private Context mContext;
    private OnStartDragListener mDragStartListener;
    private OnCustomerListChangedListener mListChangedListener;

    public EntrenamientoAdapter(ArrayList<Entrenamiento> entrenamientoList, Context context,
                                OnStartDragListener dragLlistener,
                                OnCustomerListChangedListener listChangedListener) {
        this.context = context;
        this.entrenamientoList = entrenamientoList;
        this.mContext = context;
        this.mDragStartListener = dragLlistener;
        this.mListChangedListener = listChangedListener;
    }

    @NonNull
    @Override
    public CellExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_entrenamiento, parent, false);
        return new CellExerciseViewHolder(view);
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull final CellExerciseViewHolder cellExerciseViewHolder, int i) {
        cellExerciseViewHolder.context = context;
        cellExerciseViewHolder.entrenamiento = entrenamientoList.get(i);
        cellExerciseViewHolder.tvNameExercise.setText(entrenamientoList.get(i).getmName());
        cellExerciseViewHolder.gifIVExerciseItem.setImageResource(entrenamientoList.get(i).getmImage());


        cellExerciseViewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onExerciseItemClickListener) {
                    onExerciseItemClickListener.onCardExerciseClick(v,entrenamientoList,cellExerciseViewHolder.getAdapterPosition(),entrenamientoList.size());
                }
            }
        });

        cellExerciseViewHolder.civLogoUser.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(cellExerciseViewHolder);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return entrenamientoList.size();
    }


    public void setOnExerciseItemClickListener(OnExerciseItemClickListener onExerciseItemClickListener) {
        this.onExerciseItemClickListener = onExerciseItemClickListener;
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(entrenamientoList, fromPosition, toPosition);
        mListChangedListener.onNoteListChanged(entrenamientoList);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {

    }

    public static class CellExerciseViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {
        private final View mView;
        Context context;
        ImageView civLogoUser;
        CardView cv_exercise_develop;
        TextView tvNameExercise;
        GifImageView gifIVExerciseItem;
        Entrenamiento entrenamiento;

        public CellExerciseViewHolder(View view) {
            super(view);

            mView = view;
            civLogoUser = view.findViewById(R.id.civLogoUser);
            tvNameExercise = view.findViewById(R.id.tvNameExercise);
            cv_exercise_develop = view.findViewById(R.id.cv_exercise_develop);
            gifIVExerciseItem = view.findViewById(R.id.gifIVExerciseItem);

        }

        public Entrenamiento getFeedItem() {
            return entrenamiento;
        }

        @Override
        public void onItemSelected() {

            itemView.setBackgroundColor(context.getResources().getColor(R.color.colorDarkerGray));
            int semiTransparente = Color.argb(155,185,185,185);
            gifIVExerciseItem.setColorFilter(semiTransparente,PorterDuff.Mode.SRC_ATOP);
        }

        @Override
        public void onItemClear() {

            itemView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
            gifIVExerciseItem.setColorFilter(0);

        }

    }



    public interface OnExerciseItemClickListener {
        void onCardExerciseClick(View v, ArrayList<Entrenamiento> entrenamientoList,int pos,int length);

    }
}
