package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class hh {
    private static volatile hh a;

    /* renamed from: a, reason: collision with other field name */
    private final Context f469a;

    /* renamed from: a, reason: collision with other field name */
    private Map<String, hi> f470a = new HashMap();

    private hh(Context context) {
        this.f469a = context;
    }

    public static hh a(Context context) {
        if (context == null) {
            com.xiaomi.channel.commonutils.logger.b.d("[TinyDataManager]:mContext is null, TinyDataManager.getInstance(Context) failed.");
            return null;
        }
        if (a == null) {
            synchronized (hh.class) {
                if (a == null) {
                    a = new hh(context);
                }
            }
        }
        return a;
    }

    private boolean a(String str, String str2, String str3, String str4, long j, String str5) {
        hn hnVar = new hn();
        hnVar.d(str3);
        hnVar.c(str4);
        hnVar.a(j);
        hnVar.b(str5);
        hnVar.a(true);
        hnVar.a("push_sdk_channel");
        hnVar.e(str2);
        return a(hnVar, str);
    }

    hi a() {
        hi hiVar = this.f470a.get("UPLOADER_PUSH_CHANNEL");
        if (hiVar != null) {
            return hiVar;
        }
        hi hiVar2 = this.f470a.get("UPLOADER_HTTP");
        if (hiVar2 != null) {
            return hiVar2;
        }
        return null;
    }

    /* renamed from: a, reason: collision with other method in class */
    Map<String, hi> m382a() {
        return this.f470a;
    }

    public void a(hi hiVar, String str) {
        if (hiVar == null) {
            com.xiaomi.channel.commonutils.logger.b.d("[TinyDataManager]: please do not add null mUploader to TinyDataManager.");
        } else if (TextUtils.isEmpty(str)) {
            com.xiaomi.channel.commonutils.logger.b.d("[TinyDataManager]: can not add a provider from unkown resource.");
        } else {
            m382a().put(str, hiVar);
        }
    }

    public boolean a(hn hnVar, String str) {
        if (TextUtils.isEmpty(str)) {
            com.xiaomi.channel.commonutils.logger.b.m22a("pkgName is null or empty, upload ClientUploadDataItem failed.");
            return false;
        }
        if (com.xiaomi.push.service.bo.a(hnVar, false)) {
            return false;
        }
        if (TextUtils.isEmpty(hnVar.d())) {
            hnVar.f(com.xiaomi.push.service.bo.a());
        }
        hnVar.g(str);
        com.xiaomi.push.service.bp.a(this.f469a, hnVar);
        return true;
    }

    public boolean a(String str, String str2, long j, String str3) {
        return a(this.f469a.getPackageName(), this.f469a.getPackageName(), str, str2, j, str3);
    }
}
