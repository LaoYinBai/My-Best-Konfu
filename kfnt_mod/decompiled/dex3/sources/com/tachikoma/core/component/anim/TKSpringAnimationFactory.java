package com.tachikoma.core.component.anim;

import android.content.Context;
import com.tachikoma.core.component.IFactory;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKSpringAnimationFactory implements IFactory<TKSpringAnimation> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tachikoma.core.component.IFactory
    public TKSpringAnimation newInstance(Context context, List<Object> list) {
        return new TKSpringAnimation(context, list);
    }

    @Override // com.tachikoma.core.component.IFactory
    public /* bridge */ /* synthetic */ TKSpringAnimation newInstance(Context context, List list) {
        return newInstance(context, (List<Object>) list);
    }
}
