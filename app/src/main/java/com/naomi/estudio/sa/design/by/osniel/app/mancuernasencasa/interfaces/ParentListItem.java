package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.interfaces;

import java.util.List;

public interface ParentListItem {


    List<?> getChildItemList();

    /**
     * Getter used to determine if this {@link ParentListItem}'s
     * {@link android.view.View} should show up initially as expanded.
     *
     * @return true if expanded, false if not
     */
    boolean isInitiallyExpanded();
}
