package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.R;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.interfaces.ParentListItem;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets.Category;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets.ExerciseCategory;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.view.ExerciseCategoryViewHolder;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.view.MenuMainViewHolder;

import java.util.List;

public class ExercisesCategoryAdapter extends ExpandableRecyclerAdapter<ExerciseCategoryViewHolder, MenuMainViewHolder> {

    private LayoutInflater mInflator;
    private MenuMainViewHolder menuMainViewHolder;
    private Context mContext;
    OnChildItemClickListener onChildItemClickListener;


    public ExercisesCategoryAdapter(Context context, List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        mInflator = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public ExerciseCategoryViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View exerciseCategoryView = mInflator.inflate(R.layout.menu_main_view_mancuernas, parentViewGroup, false);
        return new ExerciseCategoryViewHolder(exerciseCategoryView);
    }

    @Override
    public MenuMainViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View itemCategoriaView = mInflator.inflate(R.layout.item_category_menu_view, childViewGroup, false);
        menuMainViewHolder = new MenuMainViewHolder(itemCategoriaView);
        return menuMainViewHolder;
    }

    @Override
    public void onBindParentViewHolder(ExerciseCategoryViewHolder movieCategoryViewHolder, int position, ParentListItem parentListItem) {
        ExerciseCategory movieCategory = (ExerciseCategory) parentListItem;
        movieCategoryViewHolder.bind(movieCategory);
    }

    @Override
    public void onBindChildViewHolder(MenuMainViewHolder moviesViewHolder, final int position, Object childListItem) {
        final Category category = (Category) childListItem;
        moviesViewHolder.bind(category,mContext);
        moviesViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onChildItemClickListener.onChildClick(view, category,position);
            }
        });
    }
    public void setOnItemChildMenuClickListener(OnChildItemClickListener onChildItemClickListener) {
        this.onChildItemClickListener = onChildItemClickListener;
    }
    public interface OnChildItemClickListener {
        void onChildClick(View v, Category category, int position);
    }
}
