package com.tachikoma.core.component.view;

import android.content.Context;
import com.tachikoma.core.component.IFactory;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKViewFactory implements IFactory<TKView> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tachikoma.core.component.IFactory
    public TKView newInstance(Context context, List<Object> list) {
        return new TKView(context, list);
    }

    @Override // com.tachikoma.core.component.IFactory
    public /* bridge */ /* synthetic */ TKView newInstance(Context context, List list) {
        return newInstance(context, (List<Object>) list);
    }
}
