package com.baidu.mobads.sdk.internal;

import android.text.TextUtils;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class cl {

    /* renamed from: a, reason: collision with root package name */
    public static final String f3213a = "error_message";

    /* renamed from: b, reason: collision with root package name */
    public static final String f3214b = "error_code";
    private static volatile cl d;

    /* renamed from: c, reason: collision with root package name */
    protected final bb f3215c = bb.a();

    private cl() {
    }

    public static cl a() {
        if (d == null) {
            synchronized (cl.class) {
                if (d == null) {
                    d = new cl();
                }
            }
        }
        return d;
    }

    public String a(ay ayVar, String str) {
        if (ayVar == null) {
            return "";
        }
        return a(ayVar.b() + "", ayVar.c(), str);
    }

    public String a(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            sb.append("ErrorCode: [");
            sb.append(str);
            sb.append("];");
        }
        if (!TextUtils.isEmpty(str2)) {
            sb.append("ErrorDesc: [");
            sb.append(str2);
            sb.append("];");
        }
        if (!TextUtils.isEmpty(str3)) {
            sb.append(" Extra: [");
            sb.append(str3);
            sb.append("];");
        }
        return sb.toString();
    }

    public String a(Map<String, Object> map) {
        String str;
        String strA = "";
        if (map != null) {
            try {
                if (map.containsKey("msg")) {
                    strA = a((ay) map.get("msg"), "");
                } else if (map.containsKey("error_message")) {
                    strA = (String) map.get("error_message");
                }
                str = strA;
            } catch (Exception unused) {
                str = "";
            }
        } else {
            str = strA;
        }
        return str == null ? "" : str;
    }
}
