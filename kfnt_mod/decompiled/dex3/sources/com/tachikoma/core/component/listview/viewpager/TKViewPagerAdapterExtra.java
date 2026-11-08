package com.tachikoma.core.component.listview.viewpager;

import android.content.Context;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.tachikoma.core.component.listview.TKRecyclerAdapter;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKViewPagerAdapterExtra extends TKRecyclerAdapter {
    public TKViewPagerAdapterExtra(Context context, List<Object> list) {
        super(context, list);
    }

    @Override // com.tachikoma.core.component.listview.TKRecyclerAdapter
    public TKRecyclerAdapter.TKViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        TKRecyclerAdapter.TKViewHolder tKViewHolderOnCreateViewHolder = super.onCreateViewHolder(viewGroup, i);
        tKViewHolderOnCreateViewHolder.itemView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        return tKViewHolderOnCreateViewHolder;
    }
}
