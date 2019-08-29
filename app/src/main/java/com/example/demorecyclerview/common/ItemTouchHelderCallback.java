package com.example.demorecyclerview.common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.example.demorecyclerview.RV.RecyclerAdapter;

public class ItemTouchHelderCallback extends ItemTouchHelper.Callback {

    private final ItemTouchHelperLinner mTouchHelperLinner;

    public ItemTouchHelderCallback(ItemTouchHelperLinner linner) {
        this.mTouchHelperLinner = linner;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return super.isItemViewSwipeEnabled();
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }

    /**
     * xác định vị trí lên xuống và hướng vuốt
     * điều hướng cho item lên hoặc xuống
     */

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView,
            @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
            @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        int from = viewHolder.getAdapterPosition();
        int end = target.getAdapterPosition();
        mTouchHelperLinner.onItemMove(from, end);
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        mTouchHelperLinner.onItemDimiss(viewHolder.getAdapterPosition());
    }

    /** thay đổi khi chạm */
    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder instanceof RecyclerAdapter.ViewHolder) {
                RecyclerAdapter.ViewHolder holder = (RecyclerAdapter.ViewHolder) viewHolder;
                mTouchHelperLinner.onRowSelect(holder);
            }
        }

        super.onSelectedChanged(viewHolder, actionState);
    }

    /** thay đổi khi chạm xong */
    @Override
    public void clearView(@NonNull RecyclerView recyclerView,
            @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if (viewHolder instanceof RecyclerAdapter.ViewHolder) {
            RecyclerAdapter.ViewHolder holder = (RecyclerAdapter.ViewHolder) viewHolder;
            mTouchHelperLinner.onRowClear(holder);
        }
    }
}
