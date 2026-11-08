package com.unity3d.player;

import android.graphics.BitmapFactory;
import com.qq.e.comm.constants.Constants;
import com.tachikoma.core.utility.UriUtil;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class l {
    private String a;
    private String b;
    private String[] c;
    private String[] d;
    private int e;
    private long f;
    private String[] g;
    private String[] h;
    private String i;
    private String j;
    private String[] k;

    public l(JSONObject jSONObject) {
        this.a = jSONObject.optString("imageUrl");
        this.b = jSONObject.optString(Constants.KEYS.EXPOSED_CLICK_URL_KEY, "");
        this.e = jSONObject.optInt("duration", 5);
        this.f = jSONObject.optLong("expiration", 0L);
        this.c = a(jSONObject.optJSONArray("impression"));
        this.d = a(jSONObject.optJSONArray("clickImpression"));
        this.g = a(jSONObject.optJSONArray("primaryClickImpression"));
        this.h = a(jSONObject.optJSONArray("fallbackClickImpression"));
        this.i = jSONObject.optString("mediaType");
        this.j = jSONObject.optString("videoUrl");
        this.k = a(jSONObject.optJSONArray("completeClickImpression"));
    }

    private static String[] a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        String[] strArr = new String[jSONArray.length()];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = jSONArray.optString(i);
        }
        return strArr;
    }

    public final boolean a() {
        if ("VIDEO".equals(j())) {
            return (k() == null || k() == "") ? false : true;
        }
        String strB = b();
        if (strB == null || strB == "") {
            return false;
        }
        if (strB.startsWith(UriUtil.FILE_PREFIX)) {
            strB = strB.substring(7);
        }
        return BitmapFactory.decodeFile(strB) != null;
    }

    public final String b() {
        return this.a;
    }

    public final String c() {
        return this.b;
    }

    public final String[] d() {
        return this.c;
    }

    public final String[] e() {
        return this.d;
    }

    public final int f() {
        return this.e;
    }

    public final long g() {
        return this.f;
    }

    public final String[] h() {
        return this.g;
    }

    public final String[] i() {
        return this.h;
    }

    public final String j() {
        return this.i;
    }

    public final String k() {
        return this.j;
    }

    public final String[] l() {
        return this.k;
    }
}
