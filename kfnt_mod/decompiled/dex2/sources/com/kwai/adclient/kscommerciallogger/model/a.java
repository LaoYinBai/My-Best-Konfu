package com.kwai.adclient.kscommerciallogger.model;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.heytap.mcssdk.mode.Message;
import com.huawei.hms.push.constant.RemoteMessageConst;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private final String a;
    private final BusinessType b;
    private final SubBusinessType c;
    private final String d;
    private final b e;
    private final JSONObject f;
    private final JSONObject g;
    private final String h;

    /* renamed from: com.kwai.adclient.kscommerciallogger.model.a$a, reason: collision with other inner class name */
    public static class C0088a {
        private final String a;
        private BusinessType b;
        private SubBusinessType c;
        private String d;
        private b e;
        private JSONObject f;
        private JSONObject g;
        private String h;

        public C0088a(@NonNull String str) {
            this.a = str;
        }

        public static C0088a a() {
            return new C0088a("ad_client_error_log");
        }

        public static C0088a b() {
            return new C0088a("ad_client_apm_log");
        }

        public C0088a a(BusinessType businessType) {
            this.b = businessType;
            return this;
        }

        public C0088a a(@NonNull String str) {
            this.d = str;
            return this;
        }

        public C0088a a(JSONObject jSONObject) {
            this.f = jSONObject;
            return this;
        }

        public C0088a b(@NonNull String str) {
            this.h = str;
            return this;
        }

        public a c() {
            if (com.kwai.adclient.kscommerciallogger.a.a().c() && (TextUtils.isEmpty(this.a) || TextUtils.isEmpty(this.d) || TextUtils.isEmpty(this.h))) {
                throw new IllegalArgumentException("param is error, please check it");
            }
            if (com.kwai.adclient.kscommerciallogger.a.a().b() != null) {
                this.g = com.kwai.adclient.kscommerciallogger.a.a().b();
            }
            return new a(this);
        }
    }

    private a(C0088a c0088a) {
        this.a = c0088a.a;
        this.b = c0088a.b;
        this.c = c0088a.c;
        this.d = c0088a.d;
        this.e = c0088a.e;
        this.f = c0088a.f;
        this.g = c0088a.g;
        this.h = c0088a.h;
    }

    public String a() {
        return this.a;
    }

    public BusinessType b() {
        return this.b;
    }

    public SubBusinessType c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public b e() {
        return this.e;
    }

    public JSONObject f() {
        return this.f;
    }

    public JSONObject g() {
        return this.g;
    }

    public String h() {
        return this.h;
    }

    public String toString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.b != null) {
                jSONObject.put("biz", this.b.value);
            }
            if (this.c != null) {
                jSONObject.put("sub_biz", this.c.value);
            }
            jSONObject.put(RemoteMessageConst.Notification.TAG, this.d);
            if (this.e != null) {
                jSONObject.put(Message.TYPE, this.e.a());
            }
            if (this.f != null) {
                jSONObject.put(RemoteMessageConst.MessageBody.MSG, this.f);
            }
            if (this.g != null) {
                jSONObject.put("extra_param", this.g);
            }
            jSONObject.put("event_id", this.h);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
