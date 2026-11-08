package com.ss.union.game.sdk.core.base.event;

import android.text.TextUtils;
import com.ss.android.download.api.constant.BaseConstants;
import com.ss.union.game.sdk.common.util.DateUtils;
import com.ss.union.game.sdk.common.util.NetworkUtils;
import com.ss.union.game.sdk.core.glide.request.target.Target;
import com.umeng.message.MsgConstant;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class b {
    private final long a;
    private final String b;
    private final long c;
    private final JSONObject d;

    public b(String str, JSONObject jSONObject) throws JSONException {
        this.b = str;
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jSONObject != null && jSONObject.has(f.a)) {
            try {
                jCurrentTimeMillis = jSONObject.getLong(f.a);
            } catch (Throwable th) {
                g.b("获取事件时间戳失败", th);
            }
            jSONObject.remove(f.a);
        }
        this.c = jCurrentTimeMillis;
        this.d = a(str, jSONObject);
        this.a = -1L;
    }

    protected b(long j, long j2, String str, String str2) {
        JSONObject jSONObject;
        this.a = j;
        this.c = j2;
        this.b = str;
        if (TextUtils.isEmpty(str2)) {
            jSONObject = new JSONObject();
        } else {
            try {
                jSONObject = new JSONObject(str2);
            } catch (JSONException e) {
                e.printStackTrace();
                jSONObject = new JSONObject();
            }
        }
        this.d = jSONObject;
    }

    private JSONObject a(String str, JSONObject jSONObject) throws JSONException {
        String str2 = DateUtils.format(this.c, DateUtils.FORMAT_ONE);
        JSONObject jSONObject2 = new JSONObject();
        try {
            if (!TextUtils.isEmpty(str)) {
                jSONObject2.put("event_type_value", str);
            }
            jSONObject2.put("datetime", str2);
            if (jSONObject != null) {
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    jSONObject2.put(next, jSONObject.get(next));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        a(jSONObject2);
        return jSONObject2;
    }

    private void a(JSONObject jSONObject) throws JSONException {
        try {
            jSONObject.put("category", BaseConstants.CATEGORY_UMENG);
            jSONObject.put("tag", "Light_GAME");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            NetworkUtils.NetworkType networkType = NetworkUtils.getNetworkType();
            if (networkType != null) {
                jSONObject.put("nt", networkType.getValue());
            }
        } catch (Exception unused) {
        }
        if (TextUtils.isEmpty(jSONObject.optString(MsgConstant.INAPP_LABEL))) {
            try {
                jSONObject.put(MsgConstant.INAPP_LABEL, "lg_sdk_label");
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        if (jSONObject.optInt("value", Target.SIZE_ORIGINAL) == Integer.MIN_VALUE) {
            try {
                jSONObject.put("value", 0);
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
    }

    public JSONObject a() {
        return this.d;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.d.toString();
    }

    public long d() {
        return this.c;
    }

    public long e() {
        return this.a;
    }
}
