package com.tachikoma.core.component.recyclerview.export;

import android.content.Context;
import com.tachikoma.core.component.IFactory;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKRefreshAnimatableViewFactory implements IFactory<TKRefreshAnimatableView> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tachikoma.core.component.IFactory
    public TKRefreshAnimatableView newInstance(Context context, List<Object> list) {
        return new TKRefreshAnimatableView(context, list);
    }

    @Override // com.tachikoma.core.component.IFactory
    public /* bridge */ /* synthetic */ TKRefreshAnimatableView newInstance(Context context, List list) {
        return newInstance(context, (List<Object>) list);
    }
}
