package com.tachikoma.core.component.listview;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.tachikoma.core.component.recyclerview.export.TKWaterLayoutConfiguration;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKCustomItemDecoration extends RecyclerView.ItemDecoration {
    private TKWaterLayoutConfiguration mConfiguration;

    public TKCustomItemDecoration(TKWaterLayoutConfiguration tKWaterLayoutConfiguration) {
        this.mConfiguration = tKWaterLayoutConfiguration;
    }

    private void offsetGridLayoutRowAndEdge(Rect rect, View view, RecyclerView recyclerView) {
        int i;
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            int spanCount = recyclerView.getLayoutManager().getSpanCount();
            if (spanCount <= 1) {
                offsetLinearLayoutRowAndEdge(rect, view, recyclerView);
                return;
            }
            int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
            if (childAdapterPosition == -1) {
                return;
            }
            int itemCount = recyclerView.getLayoutManager().getItemCount();
            int i2 = childAdapterPosition + 1;
            if (i2 % spanCount != 0) {
                rect.left = this.mConfiguration.edgePadding;
                if (childAdapterPosition != itemCount) {
                    i = this.mConfiguration.centerPadding;
                }
                if (itemCount > spanCount || i2 <= spanCount) {
                }
                rect.top = this.mConfiguration.rowPadding;
                return;
            }
            i = this.mConfiguration.edgePadding;
            rect.right = i;
            if (itemCount > spanCount) {
            }
        }
    }

    private void offsetLinearLayoutRowAndEdge(Rect rect, View view, RecyclerView recyclerView) {
        RecyclerView.Adapter adapter;
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        if (childAdapterPosition == -1 || (adapter = recyclerView.getAdapter()) == null || !(recyclerView.getLayoutManager() instanceof LinearLayoutManager)) {
            return;
        }
        if (recyclerView.getLayoutManager().getOrientation() == 1) {
            if (childAdapterPosition != adapter.getItemCount() - 1 && childAdapterPosition != 0) {
                rect.top = this.mConfiguration.rowPadding;
            }
            rect.left = this.mConfiguration.edgePadding;
        } else {
            if (childAdapterPosition != adapter.getItemCount() - 1) {
                rect.right = this.mConfiguration.centerPadding;
            }
            if (childAdapterPosition == 0) {
                rect.left = this.mConfiguration.edgePadding;
            }
            if (childAdapterPosition != adapter.getItemCount() - 1) {
                return;
            }
        }
        rect.right = this.mConfiguration.edgePadding;
    }

    private void offsetStaggerGridLayoutRowAndEdge(Rect rect, View view, RecyclerView recyclerView) {
        if (recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            int spanCount = recyclerView.getLayoutManager().getSpanCount();
            int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
            if (spanCount > 1) {
                int spanIndex = view.getLayoutParams().getSpanIndex();
                if (spanIndex == -1) {
                    return;
                }
                if (spanIndex == 0) {
                    rect.left = this.mConfiguration.edgePadding;
                }
                int i = spanCount - 1;
                if (spanIndex != i) {
                    rect.right = this.mConfiguration.centerPadding;
                }
                if (spanIndex == i) {
                    rect.right = this.mConfiguration.edgePadding;
                }
                if (childAdapterPosition + 1 <= spanCount) {
                    return;
                }
            } else {
                rect.left = this.mConfiguration.edgePadding;
                rect.right = this.mConfiguration.edgePadding;
                if (childAdapterPosition + 1 <= spanCount) {
                    return;
                }
            }
            rect.top = this.mConfiguration.rowPadding;
        }
    }

    public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            offsetGridLayoutRowAndEdge(rect, view, recyclerView);
        } else if (recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            offsetStaggerGridLayoutRowAndEdge(rect, view, recyclerView);
        } else if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            offsetLinearLayoutRowAndEdge(rect, view, recyclerView);
        }
    }
}
