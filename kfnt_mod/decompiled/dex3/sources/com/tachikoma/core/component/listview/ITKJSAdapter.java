package com.tachikoma.core.component.listview;

import com.kwad.v8.V8Object;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface ITKJSAdapter {
    int itemCount(int i);

    int itemType(int i, int i2);

    V8Object renderFooter(int i);

    V8Object renderHeader(int i);

    V8Object renderItem(int i, int i2, String str);

    void reuseItemView(V8Object v8Object, int i, int i2);
}
