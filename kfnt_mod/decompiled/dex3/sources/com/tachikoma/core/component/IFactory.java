package com.tachikoma.core.component;

import android.content.Context;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IFactory<T> {
    T newInstance(Context context, List<Object> list);
}
