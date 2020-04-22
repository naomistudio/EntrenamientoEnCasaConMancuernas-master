package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
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
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.activity.RutinaUserActivity;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.interfaces.ItemTouchHelperAdapter;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.interfaces.ItemTouchHelperViewHolder;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.interfaces.OnCustomerListChangedListener;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.interfaces.OnStartDragListener;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets.Entrenamiento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class AddEntrenamientoAdapter extends SelectableAdapter<AddEntrenamientoAdapter.CellExerciseViewHolder>{

    private ArrayList<Entrenamiento> entrenamientoList = new ArrayList<>();
    private Context context;
    private OnEntrenamientoItemClickListener onEntrenamientoItemClickListener;
    private boolean showLoadingView = false;
    private RutinaUserActivity activity;


    public AddEntrenamientoAdapter(ArrayList<Entrenamiento> entrenamientoList, Context context) {
        this.context = context;
        this.entrenamientoList = entrenamientoList;
        this.activity = (RutinaUserActivity)context;

    }

    @NonNull
    @Override
    public CellExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_add_entrenamiento, parent, false);
        return new CellExerciseViewHolder(view);
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull final CellExerciseViewHolder cellExerciseViewHolder, @SuppressLint("RecyclerView") final int i) {

        cellExerciseViewHolder.context = context;
        cellExerciseViewHolder.entrenamiento = entrenamientoList.get(i);
        cellExerciseViewHolder.tvNameExercise.setText(entrenamientoList.get(i).getmName());
        cellExerciseViewHolder.gifIVExerciseItem.setImageResource(entrenamientoList.get(i).getmImage());

        cellExerciseViewHolder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (null != onEntrenamientoItemClickListener) {

                    onEntrenamientoItemClickListener.onItemLongClicked(i,entrenamientoList.get(i));
                }
                return true;
            }
        });

        cellExerciseViewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onEntrenamientoItemClickListener) {

                    onEntrenamientoItemClickListener.onItemClicked(i,entrenamientoList.get(i));
                }
            }
        });
        cellExerciseViewHolder.selectedOverlay.setVisibility(activity.getEntrenamientoList().contains(entrenamientoList.get(i).getmId()) ? View.VISIBLE : View.INVISIBLE);

    }
    public void toggleSelectionList(int id,int position) {
        if (activity.getEntrenamientoList().contains(id)) {
            activity.getEntrenamientoList().remove(activity.getEntrenamientoList().indexOf(id));
        } else {
            activity.getEntrenamientoList().add(id);
        }
        notifyItemChanged(position);
    }
    public void clearSelectionList() {
        List<Integer> selection = getSelectedItemsList();
        activity.getEntrenamientoList().clear();
        for (int i = 0; i < activity.getEntrenamientoList().size(); ++i) {
            activity.getEntrenamientoList().remove(activity.getEntrenamientoList().indexOf(i));
            notifyItemChanged(activity.getEntrenamientoList().indexOf(i));
        }
    }

    public List<Integer> getSelectedItemsList() {
        List<Integer> items = new ArrayList<>(activity.getEntrenamientoList().size());
        for (int i = 0; i < activity.getEntrenamientoList().size(); ++i) {
            items.add(activity.getEntrenamientoList().get(i));
        }
        return items;
    }



    public void removeItem(int position) {
        entrenamientoList.remove(position);
        notifyItemRemoved(position);
    }

    public void removeItems(List<Integer> positions) {
        // Reverse-sort the list
        Collections.sort(positions, new Comparator<Integer>() {
            @Override
            public int compare(Integer lhs, Integer rhs) {
                return rhs - lhs;
            }
        });

        // Split the list in ranges
        while (!positions.isEmpty()) {
            if (positions.size() == 1) {
                removeItem(positions.get(0));
                positions.remove(0);
            } else {
                int count = 1;
                while (positions.size() > count && positions.get(count).equals(positions.get(count - 1) - 1)) {
                    ++count;
                }

                if (count == 1) {
                    removeItem(positions.get(0));
                } else {
                    removeRange(positions.get(count - 1), count);
                }

                for (int i = 0; i < count; ++i) {
                    positions.remove(0);
                }
            }
        }
    }

    private void removeRange(int positionStart, int itemCount) {
        for (int i = 0; i < itemCount; ++i) {
            entrenamientoList.remove(positionStart);
        }
        notifyItemRangeRemoved(positionStart, itemCount);
    }

    @Override
    public int getItemCount() {
        return entrenamientoList.size();
    }


    public void setOnExerciseItemClickListener(OnEntrenamientoItemClickListener onEntrenamientoItemClickListener) {
        this.onEntrenamientoItemClickListener = onEntrenamientoItemClickListener;
    }


    public static class CellExerciseViewHolder extends RecyclerView.ViewHolder{
        private final View mView;
        Context context;
        CardView cv_exercise_develop;
        TextView tvNameExercise;
        GifImageView gifIVExerciseItem;
        Entrenamiento entrenamiento;
        View selectedOverlay;

        public CellExerciseViewHolder(View view) {
            super(view);

            mView = view;
            tvNameExercise = view.findViewById(R.id.tvNameExercise);
            cv_exercise_develop = view.findViewById(R.id.cv_exercise_develop);
            gifIVExerciseItem = view.findViewById(R.id.gifIVExerciseItem);
            selectedOverlay = view.findViewById(R.id.selected_overlay);

        }

        public Entrenamiento getFeedItem() {
            return entrenamiento;
        }

    }



    public interface OnEntrenamientoItemClickListener {
        public void onItemClicked(int position,Entrenamiento entrenamiento);
        public boolean onItemLongClicked(int position,Entrenamiento entrenamiento);

    }
}

