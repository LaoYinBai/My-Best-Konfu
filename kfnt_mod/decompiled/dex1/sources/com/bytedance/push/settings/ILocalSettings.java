package com.bytedance.push.settings;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface ILocalSettings {
    void registerValChanged(Context context, String str, String str2, a aVar);

    void unregisterValChanged(a aVar);
}
