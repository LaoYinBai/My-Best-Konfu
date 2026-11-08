package com.tachikoma.core.component.switchview;

import android.content.Context;
import com.tachikoma.core.component.IFactory;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKSwitchFactory implements IFactory<TKSwitch> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tachikoma.core.component.IFactory
    public TKSwitch newInstance(Context context, List<Object> list) {
        return new TKSwitch(context, list);
    }

    @Override // com.tachikoma.core.component.IFactory
    public /* bridge */ /* synthetic */ TKSwitch newInstance(Context context, List list) {
        return newInstance(context, (List<Object>) list);
    }
}
