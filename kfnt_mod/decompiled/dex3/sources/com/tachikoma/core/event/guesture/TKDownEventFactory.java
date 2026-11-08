package com.tachikoma.core.event.guesture;

import android.content.Context;
import com.tachikoma.core.component.IFactory;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKDownEventFactory implements IFactory<TKDownEvent> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tachikoma.core.component.IFactory
    public TKDownEvent newInstance(Context context, List<Object> list) {
        return new TKDownEvent(context, list);
    }

    @Override // com.tachikoma.core.component.IFactory
    public /* bridge */ /* synthetic */ TKDownEvent newInstance(Context context, List list) {
        return newInstance(context, (List<Object>) list);
    }
}
