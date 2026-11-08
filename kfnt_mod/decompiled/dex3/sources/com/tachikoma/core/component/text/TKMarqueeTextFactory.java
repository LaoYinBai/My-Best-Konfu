package com.tachikoma.core.component.text;

import android.content.Context;
import com.tachikoma.core.component.IFactory;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKMarqueeTextFactory implements IFactory<TKMarqueeText> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tachikoma.core.component.IFactory
    public TKMarqueeText newInstance(Context context, List<Object> list) {
        return new TKMarqueeText(context, list);
    }

    @Override // com.tachikoma.core.component.IFactory
    public /* bridge */ /* synthetic */ TKMarqueeText newInstance(Context context, List list) {
        return newInstance(context, (List<Object>) list);
    }
}
