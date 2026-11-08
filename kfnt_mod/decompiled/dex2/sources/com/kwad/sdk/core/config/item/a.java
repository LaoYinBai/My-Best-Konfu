package com.kwad.sdk.core.config.item;

import androidx.annotation.Nullable;
import com.kwad.sdk.plugin.DevelopMangerPlugin;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class a<T> extends b<T> {
    public a(String str, T t) {
        super(str, t);
    }

    @Override // com.kwad.sdk.core.config.item.b
    @Nullable
    public T a() {
        DevelopMangerPlugin.DevelopValue developValueA = (!com.kwad.sdk.b.c.booleanValue() || com.kwad.sdk.plugin.f.a(DevelopMangerPlugin.class) == null) ? null : ((DevelopMangerPlugin) com.kwad.sdk.plugin.f.a(DevelopMangerPlugin.class)).a(b());
        return developValueA != null ? (T) developValueA.getValue() : (T) super.a();
    }
}
