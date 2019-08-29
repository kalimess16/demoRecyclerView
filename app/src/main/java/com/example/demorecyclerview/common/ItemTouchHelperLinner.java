package com.example.demorecyclerview.common;

import com.example.demorecyclerview.RV.RecyclerAdapter;

public interface ItemTouchHelperLinner {
    void onItemMove(int fromPosition, int endPosition);

    void onItemDimiss(int position);

    void onRowSelect(RecyclerAdapter.ViewHolder view);

    void onRowClear(RecyclerAdapter.ViewHolder view);
}
