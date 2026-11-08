package com.tachikoma.core.component.listview;

import android.content.Context;
import com.tachikoma.core.component.IFactory;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKViewPagerAdapterFactory implements IFactory<TKViewPagerAdapter> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tachikoma.core.component.IFactory
    public TKViewPagerAdapter newInstance(Context context, List<Object> list) {
        return new TKViewPagerAdapter(context, list);
    }

    @Override // com.tachikoma.core.component.IFactory
    public /* bridge */ /* synthetic */ TKViewPagerAdapter newInstance(Context context, List list) {
        return newInstance(context, (List<Object>) list);
    }
}
