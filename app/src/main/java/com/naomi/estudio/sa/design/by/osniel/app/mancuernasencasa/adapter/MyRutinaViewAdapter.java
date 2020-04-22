package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.R;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.activity.MancuernasEnCasaActivity;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.database.RutinaDBHelper;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.interfaces.ItemTouchHelperAdapter;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.interfaces.ItemTouchHelperViewHolder;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.interfaces.OnRutinaListChangedListener;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.interfaces.OnStartDragListener;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets.Rutina;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class MyRutinaViewAdapter extends RecyclerView.Adapter<MyRutinaViewAdapter.ViewHolder> implements ItemTouchHelperAdapter {
    public final static String PREFERENCE_FILE = "preference_file";
    private List<Rutina> mValues = new ArrayList<>();
    private OnListFragmentRutinaInteractionListener mListener;
    private Context context;
    private OnStartDragListener mDragStartListener;
    private OnRutinaListChangedListener mListChangedListener;
    private Rutina mRecentlyDeletedItem;
    private int mRecentlyDeletedItemPosition;
    private RutinaDBHelper rutinaDBHelper;
    private MancuernasEnCasaActivity activity;
    private SharedPreferences mSharedPreferences;



    public MyRutinaViewAdapter(Context context
            ,OnStartDragListener mDragStartListener
            ,OnRutinaListChangedListener mListChangedListener
            ,SharedPreferences mSharedPreferences) {
        this.context = context;
        this.mDragStartListener = mDragStartListener;
        this.mListChangedListener = mListChangedListener;
        this.activity = (MancuernasEnCasaActivity) context;
        this.rutinaDBHelper = new RutinaDBHelper(activity);
        this.mSharedPreferences = mSharedPreferences;

    }

    public Context getContext() {
        return context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rutina, parent, false);
        return new ViewHolder(view);
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.context = context;
        holder.mItem = mValues.get(position);
        holder.tv_item_dia_rutina.setText(mValues.get(position).getmDiaText());
        switch (mValues.get(position).getmDia()){
            case "Lunes":
                holder.tv_item_dia_rutina.setBackground(context.getResources().getDrawable(R.drawable.border_circle_lunes));
                break;
            case "Martes":
                holder.tv_item_dia_rutina.setBackground(context.getResources().getDrawable(R.drawable.border_circle_martes));
                break;
            case "Miercoles":
                holder.tv_item_dia_rutina.setBackground(context.getResources().getDrawable(R.drawable.border_circle_miercoles));
                break;
            case "Jueves":
                holder.tv_item_dia_rutina.setBackground(context.getResources().getDrawable(R.drawable.border_circle_jueves));
                break;
            case "Viernes":
                holder.tv_item_dia_rutina.setBackground(context.getResources().getDrawable(R.drawable.border_circle_viernes));
                break;
            case "Sabado":
                holder.tv_item_dia_rutina.setBackground(context.getResources().getDrawable(R.drawable.border_circle_sabado));
                break;
            case "Domingo":
                holder.tv_item_dia_rutina.setBackground(context.getResources().getDrawable(R.drawable.border_circle_domingo));
                break;
            default:
                break;

        }
        holder.tvNameRutina.setText(mValues.get(position).getmName());
        holder.tvnumber_entrenamiento_rutina.setText(String.format("%s ejercicios", String.valueOf(mValues.get(position).getmCountEntrenamiento())));

        holder.iv_drag_rutina.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem);
                }
                Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (null != mListener) {
                    mListener.onClickLongEditRutina(holder.mItem,position);
                }
                return true;
            }
        });
    }
    public void updateItems(boolean animated) {
        mValues.clear();
        mValues = getSampleData();

        if (animated) {
            notifyItemRangeInserted(0, mValues.size());
        } else {
            notifyDataSetChanged();
        }
    }

    public List<Rutina> getmValues() {
        return mValues;
    }

    public void setOnRutinaLisItemClickListener(OnListFragmentRutinaInteractionListener onListFragmentInteractionListener) {
        this.mListener = onListFragmentInteractionListener;
    }

    public void insertRutina(Rutina rutina) {
        rutinaDBHelper.insertRutina(rutina);
        mValues.add(rutina);
        notifyItemInserted(mValues.size() - 1);
    }

    public void updateItem(Rutina rutina, int pos) {
        rutinaDBHelper.updateRutina(rutina);
        mValues.set(pos,rutina);
        notifyItemChanged(pos);
    }
    public void deleteItem(int position) {
        rutinaDBHelper.deleteRutina(mValues.get(position));
        mRecentlyDeletedItem = mValues.get(position);
        mRecentlyDeletedItemPosition = position;
        mValues.remove(position);
        notifyItemRemoved(position);
        //showUndoSnackbar();
    }
    private void showUndoSnackbar() {
        View view = activity.findViewById(R.id.coordinator_layout);
        Snackbar snackbar = Snackbar.make(view, "delete",
                Snackbar.LENGTH_LONG);
        snackbar.setAction("Desacer", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undoDelete();
            }
        });
        snackbar.show();

    }

    public MancuernasEnCasaActivity getActivity() {
        return activity;
    }

    private void undoDelete() {
        mValues.add(mRecentlyDeletedItemPosition,
                mRecentlyDeletedItem);
        notifyItemInserted(mRecentlyDeletedItemPosition);
    }

    public void itemMove(int fromPosition, int toPosition) {
        Collections.swap(mValues, fromPosition, toPosition);
        mListChangedListener.onRutinaListChanged(mValues);
        notifyItemMoved(fromPosition,toPosition);
    }

    public void setmValues(List<Rutina> mValues) {
        this.mValues = mValues;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mValues, fromPosition, toPosition);
        mListChangedListener.onRutinaListChanged(mValues);
        notifyItemMoved(fromPosition,toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        rutinaDBHelper.deleteRutina(mValues.get(position));
        mValues.remove(position);
        notifyItemRemoved(position);
        //showUndoSnackbar();

    }

    public void update() {
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {
        Context context;
        private final View mView;
        ImageView iv_drag_rutina;
        private final TextView tvNameRutina;
        private final TextView tvnumber_entrenamiento_rutina;
        private final TextView tv_item_dia_rutina;
        private Rutina mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            iv_drag_rutina = view.findViewById(R.id.iv_drag_rutina);
            tvNameRutina = view.findViewById(R.id.tvNameRutina);
            tvnumber_entrenamiento_rutina = view.findViewById(R.id.tvnumber_entrenamiento_rutina);
            tv_item_dia_rutina = view.findViewById(R.id.tv_item_dia_rutina);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + tvNameRutina.getText() + "'";
        }

        @Override
        public void onItemSelected() {

            itemView.setBackgroundColor(context.getResources().getColor(R.color.colorDarkerGray));
            tv_item_dia_rutina.setBackgroundColor(context.getResources().getColor(R.color.colorDarkerGray));

        }

        @Override
        public void onItemClear() {

            itemView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));

            switch (mItem.getmDia()){
                case "Lunes":
                    tv_item_dia_rutina.setBackground(context.getResources().getDrawable(R.drawable.border_circle_lunes));
                    break;
                case "Martes":
                    tv_item_dia_rutina.setBackground(context.getResources().getDrawable(R.drawable.border_circle_martes));
                    break;
                case "Miercoles":
                    tv_item_dia_rutina.setBackground(context.getResources().getDrawable(R.drawable.border_circle_miercoles));
                    break;
                case "Jueves":
                    tv_item_dia_rutina.setBackground(context.getResources().getDrawable(R.drawable.border_circle_jueves));
                    break;
                case "Viernes":
                    tv_item_dia_rutina.setBackground(context.getResources().getDrawable(R.drawable.border_circle_viernes));
                    break;
                case "Sabado":
                    tv_item_dia_rutina.setBackground(context.getResources().getDrawable(R.drawable.border_circle_sabado));
                    break;
                case "Domingo":
                    tv_item_dia_rutina.setBackground(context.getResources().getDrawable(R.drawable.border_circle_domingo));
                    break;
                default:
                    break;
            }

            notifyDataSetChanged();

        }
    }
    public interface OnListFragmentRutinaInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Rutina item);
        void onClickLongEditRutina(Rutina item, int pos);
    }
    public ArrayList<Rutina> getSampleData(){

        //Get the sample data
        ArrayList<Rutina> rutinas  = new ArrayList<>();
        ArrayList<Rutina> sortedCustomers = new ArrayList<>();
        List<Integer> listOfSortedCustomersId = new ArrayList<>();
        rutinas.clear();
        sortedCustomers.clear();
        listOfSortedCustomersId.clear();
        rutinas = rutinaDBHelper.getAllRutinas();
        //create an empty array to hold the list of sorted Customers
        //get the JSON array of the ordered of sorted customers
        String LIST_OF_SORTED_DATA_ID = "json_list_sorted_rutina_id";
        String jsonListOfSortedCustomerId = mSharedPreferences.getString(LIST_OF_SORTED_DATA_ID, "");
        //check for null
        if (!Objects.requireNonNull(jsonListOfSortedCustomerId).isEmpty()){

            //convert JSON array into a List<Long>
            Gson gson = new Gson();
            listOfSortedCustomersId = gson.fromJson(jsonListOfSortedCustomerId, new TypeToken<List<Integer>>(){}.getType());

            //build sorted list
            if (listOfSortedCustomersId != null && listOfSortedCustomersId.size() > 0){
                for (int id: listOfSortedCustomersId){
                    for (Rutina entrenamiento : rutinas){
                        if (entrenamiento.getmId() == id){
                            sortedCustomers.add(entrenamiento);
                            rutinas.remove(entrenamiento);
                            break;
                        }
                    }
                }
            }

            //if there are still customers that were not in the sorted list
            //maybe they were added after the last drag and drop
            //add them to the sorted list
            if (rutinas.size() > 0){
                sortedCustomers.addAll(rutinas);
            }

            return sortedCustomers;
        }else {
            return rutinas;
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
