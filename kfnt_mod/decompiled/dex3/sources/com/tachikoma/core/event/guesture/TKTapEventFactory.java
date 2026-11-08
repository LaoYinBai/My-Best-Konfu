package com.tachikoma.core.event.guesture;

import android.content.Context;
import com.tachikoma.core.component.IFactory;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKTapEventFactory implements IFactory<TKTapEvent> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tachikoma.core.component.IFactory
    public TKTapEvent newInstance(Context context, List<Object> list) {
        return new TKTapEvent(context, list);
    }

    @Override // com.tachikoma.core.component.IFactory
    public /* bridge */ /* synthetic */ TKTapEvent newInstance(Context context, List list) {
        return newInstance(context, (List<Object>) list);
    }
}
