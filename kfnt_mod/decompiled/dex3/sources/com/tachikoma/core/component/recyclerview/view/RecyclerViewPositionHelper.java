package com.tachikoma.core.component.recyclerview.view;

import android.view.View;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RecyclerViewPositionHelper {
    final RecyclerView.LayoutManager layoutManager;
    final RecyclerView recyclerView;

    RecyclerViewPositionHelper(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.layoutManager = recyclerView.getLayoutManager();
    }

    public static RecyclerViewPositionHelper createHelper(RecyclerView recyclerView) {
        if (recyclerView != null) {
            return new RecyclerViewPositionHelper(recyclerView);
        }
        throw new NullPointerException("Recycler View is null");
    }

    private View findOneVisibleChild(int i, int i2, boolean z, boolean z2) {
        OrientationHelper orientationHelperCreateVerticalHelper = this.layoutManager.canScrollVertically() ? OrientationHelper.createVerticalHelper(this.layoutManager) : OrientationHelper.createHorizontalHelper(this.layoutManager);
        int startAfterPadding = orientationHelperCreateVerticalHelper.getStartAfterPadding();
        int endAfterPadding = orientationHelperCreateVerticalHelper.getEndAfterPadding();
        int i3 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            View childAt = this.layoutManager.getChildAt(i);
            int decoratedStart = orientationHelperCreateVerticalHelper.getDecoratedStart(childAt);
            int decoratedEnd = orientationHelperCreateVerticalHelper.getDecoratedEnd(childAt);
            if (decoratedStart < endAfterPadding && decoratedEnd > startAfterPadding) {
                if (!z) {
                    return childAt;
                }
                if (decoratedStart >= startAfterPadding && decoratedEnd <= endAfterPadding) {
                    return childAt;
                }
                if (z2 && view == null) {
                    view = childAt;
                }
            }
            i += i3;
        }
        return view;
    }

    public int findFirstCompletelyVisibleItemPosition() {
        View viewFindOneVisibleChild = findOneVisibleChild(0, this.layoutManager.getChildCount(), true, false);
        if (viewFindOneVisibleChild == null) {
            return -1;
        }
        return this.recyclerView.getChildAdapterPosition(viewFindOneVisibleChild);
    }

    public int findFirstVisibleItemPosition() {
        View viewFindOneVisibleChild = findOneVisibleChild(0, this.layoutManager.getChildCount(), false, true);
        if (viewFindOneVisibleChild == null) {
            return -1;
        }
        return this.recyclerView.getChildAdapterPosition(viewFindOneVisibleChild);
    }

    public int findLastCompletelyVisibleItemPosition() {
        View viewFindOneVisibleChild = findOneVisibleChild(this.layoutManager.getChildCount() - 1, -1, true, false);
        if (viewFindOneVisibleChild == null) {
            return -1;
        }
        return this.recyclerView.getChildAdapterPosition(viewFindOneVisibleChild);
    }

    public int findLastVisibleItemPosition() {
        View viewFindOneVisibleChild = findOneVisibleChild(this.layoutManager.getChildCount() - 1, -1, false, true);
        if (viewFindOneVisibleChild == null) {
            return -1;
        }
        return this.recyclerView.getChildAdapterPosition(viewFindOneVisibleChild);
    }

    public int getItemCount() {
        if (this.layoutManager == null) {
            return 0;
        }
        return this.layoutManager.getItemCount();
    }

    public int getVisibleItemCount() {
        if (this.layoutManager == null) {
            return 0;
        }
        return this.layoutManager.getChildCount();
    }
}
