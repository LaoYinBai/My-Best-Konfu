package com.tachikoma.core.component.text;

import android.content.Context;
import com.tachikoma.core.component.IFactory;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKTextFactory implements IFactory<TKText> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tachikoma.core.component.IFactory
    public TKText newInstance(Context context, List<Object> list) {
        return new TKText(context, list);
    }

    @Override // com.tachikoma.core.component.IFactory
    public /* bridge */ /* synthetic */ TKText newInstance(Context context, List list) {
        return newInstance(context, (List<Object>) list);
    }
}
