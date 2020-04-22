package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.adapter;

import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.interfaces.ParentListItem;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.wrapper.ParentWrapper;

import java.util.ArrayList;
import java.util.List;

public class ExpandableRecyclerAdapterHelper {

    public static List<Object> generateParentChildItemList(List<? extends ParentListItem> parentItemList) {
        List<Object> parentWrapperList = new ArrayList<>();
        ParentListItem parentListItem;
        ParentWrapper parentWrapper;

        int parentListItemCount = parentItemList.size();
        for (int i = 0; i < parentListItemCount; i++) {
            parentListItem = parentItemList.get(i);
            parentWrapper = new ParentWrapper(parentListItem);
            parentWrapperList.add(parentWrapper);

            if (parentWrapper.isInitiallyExpanded()) {
                parentWrapper.setExpanded(true);

                int childListItemCount = parentWrapper.getChildItemList().size();
                for (int j = 0; j < childListItemCount; j++) {
                    parentWrapperList.add(parentWrapper.getChildItemList().get(j));
                }
            }
        }

        return parentWrapperList;
    }
}
