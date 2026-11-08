package com.baidu.mobads.sdk.internal;

import android.content.Context;
import android.text.TextUtils;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ah {

    /* renamed from: a, reason: collision with root package name */
    private static final String f3104a = r.n;

    /* renamed from: b, reason: collision with root package name */
    private static final String f3105b = "pauseDlByPk";

    /* renamed from: c, reason: collision with root package name */
    private static final String f3106c = "resumeDownload";
    private static final String d = "getDownloadStatus";
    private static ah f;
    private ai e;

    private ah(Context context) {
        this.e = ai.a(context, f3104a);
    }

    public static ah a(Context context) {
        if (f == null) {
            synchronized (ah.class) {
                if (f == null) {
                    f = new ah(context);
                }
            }
        }
        return f;
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.e.a(null, f3105b, str);
    }

    public boolean a(Context context, JSONObject jSONObject, String str, String str2) {
        if (context != null && jSONObject != null) {
            Object objB = this.e.b(null, f3106c, context, jSONObject, str, str2);
            if (objB instanceof Boolean) {
                return ((Boolean) objB).booleanValue();
            }
        }
        return false;
    }

    public int a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (bc.a(context, str)) {
            return 103;
        }
        Object objB = this.e.b(null, d, str);
        if (objB instanceof Integer) {
            return ((Integer) objB).intValue();
        }
        return -1;
    }
}
