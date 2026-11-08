package com.tachikoma.core.component.recyclerview.export;

import android.content.Context;
import com.tachikoma.core.component.IFactory;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKRefreshControlFactory implements IFactory<TKRefreshControl> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tachikoma.core.component.IFactory
    public TKRefreshControl newInstance(Context context, List<Object> list) {
        return new TKRefreshControl(context, list);
    }

    @Override // com.tachikoma.core.component.IFactory
    public /* bridge */ /* synthetic */ TKRefreshControl newInstance(Context context, List list) {
        return newInstance(context, (List<Object>) list);
    }
}
