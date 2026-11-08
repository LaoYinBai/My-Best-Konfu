package com.tachikoma.core.component.input;

import android.content.Context;
import com.tachikoma.core.component.IFactory;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKTextAreaFactory implements IFactory<TKTextArea> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tachikoma.core.component.IFactory
    public TKTextArea newInstance(Context context, List<Object> list) {
        return new TKTextArea(context, list);
    }

    @Override // com.tachikoma.core.component.IFactory
    public /* bridge */ /* synthetic */ TKTextArea newInstance(Context context, List list) {
        return newInstance(context, (List<Object>) list);
    }
}
