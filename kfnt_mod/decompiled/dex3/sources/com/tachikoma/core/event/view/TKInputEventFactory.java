package com.tachikoma.core.event.view;

import android.content.Context;
import com.tachikoma.core.component.IFactory;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKInputEventFactory implements IFactory<TKInputEvent> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tachikoma.core.component.IFactory
    public TKInputEvent newInstance(Context context, List<Object> list) {
        return new TKInputEvent(context, list);
    }

    @Override // com.tachikoma.core.component.IFactory
    public /* bridge */ /* synthetic */ TKInputEvent newInstance(Context context, List list) {
        return newInstance(context, (List<Object>) list);
    }
}
