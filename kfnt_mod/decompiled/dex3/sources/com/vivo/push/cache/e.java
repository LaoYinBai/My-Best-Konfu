package com.vivo.push.cache;

import android.annotation.TargetApi;
import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.vivo.push.util.n;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class e extends c<com.vivo.push.model.a> {
    @Override // com.vivo.push.cache.c
    protected final String a() {
        return "com.vivo.pushservice.other";
    }

    public e(Context context) {
        super(context);
    }

    @Override // com.vivo.push.cache.c
    public final List<com.vivo.push.model.a> a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str2 : str.trim().split("@#")) {
            String strTrim = str2.trim();
            String[] strArrSplit = strTrim.trim().split(Constants.ACCEPT_TIME_SEPARATOR_SP);
            if (strArrSplit.length >= 2) {
                try {
                    arrayList.add(new com.vivo.push.model.a(strArrSplit[0], strTrim.substring(strArrSplit[0].length() + 1)));
                } catch (Exception e) {
                    n.d("PushConfigSettings", "str2Clients E: " + e);
                }
            }
        }
        return arrayList;
    }

    public final String c(String str) {
        synchronized (c) {
            for (T t : this.d) {
                if (!TextUtils.isEmpty(t.a) && t.a.equals(str)) {
                    return t.b;
                }
            }
            return null;
        }
    }

    @Override // com.vivo.push.cache.c
    @TargetApi(8)
    final String b(String str) throws Exception {
        return new String(com.vivo.push.util.e.a(com.vivo.push.util.e.a(a), com.vivo.push.util.e.a(b), Base64.decode(str, 2)), "utf-8");
    }
}
