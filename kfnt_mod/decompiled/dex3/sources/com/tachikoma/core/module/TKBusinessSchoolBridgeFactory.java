package com.tachikoma.core.module;

import android.content.Context;
import com.tachikoma.core.component.IFactory;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKBusinessSchoolBridgeFactory implements IFactory<TKBusinessSchoolBridge> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tachikoma.core.component.IFactory
    public TKBusinessSchoolBridge newInstance(Context context, List<Object> list) {
        return new TKBusinessSchoolBridge(context, list);
    }

    @Override // com.tachikoma.core.component.IFactory
    public /* bridge */ /* synthetic */ TKBusinessSchoolBridge newInstance(Context context, List list) {
        return newInstance(context, (List<Object>) list);
    }
}
