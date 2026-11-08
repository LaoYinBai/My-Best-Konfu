package com.tachikoma.core.event.guesture;

import android.content.Context;
import com.tachikoma.core.component.IFactory;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKPanEventFactory implements IFactory<TKPanEvent> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tachikoma.core.component.IFactory
    public TKPanEvent newInstance(Context context, List<Object> list) {
        return new TKPanEvent(context, list);
    }

    @Override // com.tachikoma.core.component.IFactory
    public /* bridge */ /* synthetic */ TKPanEvent newInstance(Context context, List list) {
        return newInstance(context, (List<Object>) list);
    }
}
