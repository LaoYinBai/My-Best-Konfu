package com.mbridge.msdk.mbjscommon.windvane;

import android.text.TextUtils;

/* compiled from: MVCallJs.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class e {
    private static e a = new e();

    private e() {
    }

    public static e a() {
        return a;
    }

    public final void a(Object obj, String str) {
        String str2;
        if (obj instanceof a) {
            a aVar = (a) obj;
            if (TextUtils.isEmpty(str)) {
                str2 = String.format("javascript:window.OfferWall.onSuccess(%s,'');", aVar.g);
            } else {
                str2 = String.format("javascript:window.OfferWall.onSuccess(%s,'%s');", aVar.g, k.c(str));
            }
            if (aVar.a != null) {
                try {
                    aVar.a.loadUrl(str2);
                } catch (Exception e) {
                    e.printStackTrace();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }
}
