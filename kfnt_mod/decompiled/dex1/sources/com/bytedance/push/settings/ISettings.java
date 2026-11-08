package com.bytedance.push.settings;

import android.content.Context;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface ISettings {
    void registerValChanged(Context context, String str, String str2, a aVar);

    void unregisterValChanged(a aVar);

    void updateSettings(Context context, JSONObject jSONObject);
}
