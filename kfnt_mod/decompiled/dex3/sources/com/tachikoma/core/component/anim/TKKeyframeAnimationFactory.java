package com.tachikoma.core.component.anim;

import android.content.Context;
import com.tachikoma.core.component.IFactory;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKKeyframeAnimationFactory implements IFactory<TKKeyframeAnimation> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tachikoma.core.component.IFactory
    public TKKeyframeAnimation newInstance(Context context, List<Object> list) {
        return new TKKeyframeAnimation(context, list);
    }

    @Override // com.tachikoma.core.component.IFactory
    public /* bridge */ /* synthetic */ TKKeyframeAnimation newInstance(Context context, List list) {
        return newInstance(context, (List<Object>) list);
    }
}
