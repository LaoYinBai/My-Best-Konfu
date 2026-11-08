package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.Constants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class dw extends dx {
    public static String a = "";
    public static String b = "";

    public dw(Context context, int i) {
        super(context, i);
    }

    private String a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        String[] strArrSplit = str2.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        if (strArrSplit.length <= 10) {
            return str2;
        }
        int length = strArrSplit.length;
        while (true) {
            length--;
            if (length < strArrSplit.length - 10) {
                return str;
            }
            str = str + strArrSplit[length];
        }
    }

    @Override // com.xiaomi.push.dx
    /* renamed from: a */
    public hl mo205a() {
        return hl.BroadcastAction;
    }

    @Override // com.xiaomi.push.ai.a
    /* renamed from: a */
    public String mo136a() {
        return "12";
    }

    @Override // com.xiaomi.push.dx
    public String b() {
        String str = "";
        if (!TextUtils.isEmpty(a)) {
            str = "" + a(dr.f247a, a);
            a = "";
        }
        if (TextUtils.isEmpty(b)) {
            return str;
        }
        String str2 = str + a(dr.b, b);
        b = "";
        return str2;
    }
}
