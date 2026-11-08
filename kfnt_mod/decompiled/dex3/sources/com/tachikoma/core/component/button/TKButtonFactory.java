package com.tachikoma.core.component.button;

import android.content.Context;
import com.tachikoma.core.component.IFactory;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKButtonFactory implements IFactory<TKButton> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tachikoma.core.component.IFactory
    public TKButton newInstance(Context context, List<Object> list) {
        return new TKButton(context, list);
    }

    @Override // com.tachikoma.core.component.IFactory
    public /* bridge */ /* synthetic */ TKButton newInstance(Context context, List list) {
        return newInstance(context, (List<Object>) list);
    }
}
