package com.tachikoma.core.component.recyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class RecyclerHeaderObserver extends RecyclerView.AdapterDataObserver {

    @NonNull
    private final RecyclerHeaderFooterAdapter mWrapped;

    RecyclerHeaderObserver(@NonNull RecyclerHeaderFooterAdapter recyclerHeaderFooterAdapter) {
        this.mWrapped = recyclerHeaderFooterAdapter;
    }

    public void onChanged() {
        this.mWrapped.notifyDataSetChanged();
    }

    public void onItemRangeChanged(int i, int i2) {
        this.mWrapped.notifyItemRangeChanged(i, i2);
    }

    public void onItemRangeChanged(int i, int i2, Object obj) {
        this.mWrapped.notifyItemRangeChanged(i, i2);
    }

    public void onItemRangeInserted(int i, int i2) {
        this.mWrapped.notifyItemRangeInserted(i, i2);
    }

    public void onItemRangeMoved(int i, int i2, int i3) {
        this.mWrapped.notifyItemMoved(i, i2);
    }

    public void onItemRangeRemoved(int i, int i2) {
        this.mWrapped.notifyItemRangeRemoved(i, i2);
    }
}
