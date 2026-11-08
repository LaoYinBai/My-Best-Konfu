package com.tachikoma.core.component.listview;

import android.content.Context;
import com.tachikoma.core.component.IFactory;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKListViewFactory implements IFactory<TKListView> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tachikoma.core.component.IFactory
    public TKListView newInstance(Context context, List<Object> list) {
        return new TKListView(context, list);
    }

    @Override // com.tachikoma.core.component.IFactory
    public /* bridge */ /* synthetic */ TKListView newInstance(Context context, List list) {
        return newInstance(context, (List<Object>) list);
    }
}
