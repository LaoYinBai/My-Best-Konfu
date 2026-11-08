package com.qq.e.comm.managers.setting;

import com.qq.e.comm.util.GDTLogger;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class e extends b {
    e() {
    }

    e(String str) {
        super(str);
    }

    void a(String str, Object obj) throws JSONException {
        try {
            this.a.putOpt(str, obj);
        } catch (JSONException unused) {
            GDTLogger.d("Exception while update setting");
        }
    }

    public String toString() {
        return "GDTSDKSetting[" + this.a.toString() + "]";
    }
}
