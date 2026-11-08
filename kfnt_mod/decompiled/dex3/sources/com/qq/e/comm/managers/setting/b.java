package com.qq.e.comm.managers.setting;

import android.text.TextUtils;
import com.qq.e.comm.util.GDTLogger;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class b {
    protected JSONObject a;

    b() {
        this(null);
    }

    b(String str) {
        String simpleName = getClass().getSimpleName();
        GDTLogger.d("Initialize " + simpleName + ",Json=" + str);
        if (!TextUtils.isEmpty(str)) {
            try {
                this.a = new JSONObject(str);
            } catch (JSONException unused) {
                GDTLogger.d("JsonException While build" + simpleName + " Instance from JSON");
            }
        }
        if (this.a == null) {
            this.a = new JSONObject();
        }
    }
}
