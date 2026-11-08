package com.kwad.sdk.core.page.recycle;

import android.view.View;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f {
    final RecyclerView a;
    final RecyclerView.LayoutManager b;

    f(RecyclerView recyclerView) {
        this.a = recyclerView;
        this.b = recyclerView.getLayoutManager();
    }

    private View a(int i, int i2, boolean z, boolean z2) {
        OrientationHelper orientationHelperCreateVerticalHelper = this.b.canScrollVertically() ? OrientationHelper.createVerticalHelper(this.b) : OrientationHelper.createHorizontalHelper(this.b);
        int startAfterPadding = orientationHelperCreateVerticalHelper.getStartAfterPadding();
        int endAfterPadding = orientationHelperCreateVerticalHelper.getEndAfterPadding();
        int i3 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            View childAt = this.b.getChildAt(i);
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

    public static f a(RecyclerView recyclerView) {
        if (recyclerView != null) {
            return new f(recyclerView);
        }
        throw new NullPointerException("Recycler View is null");
    }

    public int a() {
        View viewA = a(0, this.b.getChildCount(), false, true);
        if (viewA == null) {
            return -1;
        }
        return this.a.getChildAdapterPosition(viewA);
    }

    public int b() {
        View viewA = a(this.b.getChildCount() - 1, -1, false, true);
        if (viewA == null) {
            return -1;
        }
        return this.a.getChildAdapterPosition(viewA);
    }
}
