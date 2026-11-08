package com.tachikoma.core.component.network;

import android.content.Context;
import com.tachikoma.core.component.IFactory;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NetworkFactory implements IFactory<Network> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tachikoma.core.component.IFactory
    public Network newInstance(Context context, List<Object> list) {
        return new Network(context, list);
    }

    @Override // com.tachikoma.core.component.IFactory
    public /* bridge */ /* synthetic */ Network newInstance(Context context, List list) {
        return newInstance(context, (List<Object>) list);
    }
}
