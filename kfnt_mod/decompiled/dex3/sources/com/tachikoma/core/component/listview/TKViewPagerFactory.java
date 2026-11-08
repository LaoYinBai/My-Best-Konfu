package com.tachikoma.core.component.listview;

import android.content.Context;
import com.tachikoma.core.component.IFactory;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKViewPagerFactory implements IFactory<TKViewPager> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tachikoma.core.component.IFactory
    public TKViewPager newInstance(Context context, List<Object> list) {
        return new TKViewPager(context, list);
    }

    @Override // com.tachikoma.core.component.IFactory
    public /* bridge */ /* synthetic */ TKViewPager newInstance(Context context, List list) {
        return newInstance(context, (List<Object>) list);
    }
}
