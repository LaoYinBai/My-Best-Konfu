package com.ss.union.game.sdk.core.glide.tnc;

import com.bytedance.sdk.adtnc.sdk.a.b;
import com.ss.union.game.sdk.common.net.tnc.TncConstant;
import com.ss.union.game.sdk.common.net.tnc.VTncUtils;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class a {
    private String a = TncConstant.TNC_SDK_APP_ID;

    public a(String str) {
    }

    public String a(String str) {
        try {
            String strFilterUrl = VTncUtils.filterUrl(this.a, str);
            return !str.equals(strFilterUrl) ? strFilterUrl : str;
        } catch (Throwable unused) {
            return str;
        }
    }

    public void b(String str) {
        try {
            VTncUtils.onResponse(this.a, new com.bytedance.sdk.adtnc.sdk.a.a(str), new b(200, (Map) null));
        } catch (Throwable unused) {
        }
    }

    public void a(String str, Throwable th) {
        try {
            VTncUtils.onError(this.a, new com.bytedance.sdk.adtnc.sdk.a.a(str), th);
        } catch (Throwable unused) {
        }
    }
}
