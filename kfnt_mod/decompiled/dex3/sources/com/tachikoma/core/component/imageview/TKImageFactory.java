package com.tachikoma.core.component.imageview;

import android.content.Context;
import com.tachikoma.core.component.IFactory;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKImageFactory implements IFactory<TKImage> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tachikoma.core.component.IFactory
    public TKImage newInstance(Context context, List<Object> list) {
        return new TKImage(context, list);
    }

    @Override // com.tachikoma.core.component.IFactory
    public /* bridge */ /* synthetic */ TKImage newInstance(Context context, List list) {
        return newInstance(context, (List<Object>) list);
    }
}
