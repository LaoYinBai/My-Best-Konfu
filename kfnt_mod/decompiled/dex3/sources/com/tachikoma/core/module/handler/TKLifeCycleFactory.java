package com.tachikoma.core.module.handler;

import android.content.Context;
import com.tachikoma.core.component.IFactory;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKLifeCycleFactory implements IFactory<TKLifeCycle> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tachikoma.core.component.IFactory
    public TKLifeCycle newInstance(Context context, List<Object> list) {
        return new TKLifeCycle(context, list);
    }

    @Override // com.tachikoma.core.component.IFactory
    public /* bridge */ /* synthetic */ TKLifeCycle newInstance(Context context, List list) {
        return newInstance(context, (List<Object>) list);
    }
}
