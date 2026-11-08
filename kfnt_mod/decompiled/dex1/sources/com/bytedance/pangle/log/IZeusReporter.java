package com.bytedance.pangle.log;

import androidx.annotation.Keep;
import org.json.JSONObject;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IZeusReporter {
    void report(String str, JSONObject jSONObject);
}
