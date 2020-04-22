package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets;

import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.interfaces.ParentListItem;

import java.util.List;

public class ExerciseCategory implements ParentListItem {
    private String mName;
    private List<Category> mCategory;

    public ExerciseCategory(String name, List<Category> categories) {
        mName = name;
        mCategory = categories;
    }

    public String getName() {
        return mName;
    }

    @Override
    public List<?> getChildItemList() {
        return mCategory;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
