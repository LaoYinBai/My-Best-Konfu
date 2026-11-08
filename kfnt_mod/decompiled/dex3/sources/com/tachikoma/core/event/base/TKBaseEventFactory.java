package com.tachikoma.core.event.base;

import android.content.Context;
import com.tachikoma.core.component.IFactory;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKBaseEventFactory implements IFactory<TKBaseEvent> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tachikoma.core.component.IFactory
    public TKBaseEvent newInstance(Context context, List<Object> list) {
        return new TKBaseEvent(context, list);
    }

    @Override // com.tachikoma.core.component.IFactory
    public /* bridge */ /* synthetic */ TKBaseEvent newInstance(Context context, List list) {
        return newInstance(context, (List<Object>) list);
    }
}
