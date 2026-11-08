package com.tachikoma.core.component.listview;

import android.content.Context;
import com.tachikoma.core.component.IFactory;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKRecyclerViewFactory implements IFactory<TKRecyclerView> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tachikoma.core.component.IFactory
    public TKRecyclerView newInstance(Context context, List<Object> list) {
        return new TKRecyclerView(context, list);
    }

    @Override // com.tachikoma.core.component.IFactory
    public /* bridge */ /* synthetic */ TKRecyclerView newInstance(Context context, List list) {
        return newInstance(context, (List<Object>) list);
    }
}
