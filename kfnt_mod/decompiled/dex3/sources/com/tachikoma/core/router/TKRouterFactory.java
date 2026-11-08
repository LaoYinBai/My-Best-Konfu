package com.tachikoma.core.router;

import android.content.Context;
import com.tachikoma.core.component.IFactory;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKRouterFactory implements IFactory<TKRouter> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tachikoma.core.component.IFactory
    public TKRouter newInstance(Context context, List<Object> list) {
        return new TKRouter(context, list);
    }

    @Override // com.tachikoma.core.component.IFactory
    public /* bridge */ /* synthetic */ TKRouter newInstance(Context context, List list) {
        return newInstance(context, (List<Object>) list);
    }
}
