package com.tachikoma.core.module;

import android.content.Context;
import com.tachikoma.core.component.IFactory;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKWebCardBridgeImplFactory implements IFactory<TKWebCardBridgeImpl> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tachikoma.core.component.IFactory
    public TKWebCardBridgeImpl newInstance(Context context, List<Object> list) {
        return new TKWebCardBridgeImpl(context, list);
    }

    @Override // com.tachikoma.core.component.IFactory
    public /* bridge */ /* synthetic */ TKWebCardBridgeImpl newInstance(Context context, List list) {
        return newInstance(context, (List<Object>) list);
    }
}
