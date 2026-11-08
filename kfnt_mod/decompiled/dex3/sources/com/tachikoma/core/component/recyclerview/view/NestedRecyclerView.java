package com.tachikoma.core.component.recyclerview.view;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NestedRecyclerView extends CustomRecyclerView {
    public NestedRecyclerView(Context context) {
        super(context);
    }

    public NestedRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NestedRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void smoothScrollBy(int i, int i2) {
        startNestedScroll(2, 1);
        super.smoothScrollBy(i, i2);
    }

    public void smoothScrollToPosition(int i) {
        startNestedScroll(2, 1);
        super.smoothScrollToPosition(i);
    }
}
