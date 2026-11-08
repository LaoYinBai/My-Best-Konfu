package com.apm.insight.runtime;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class t {

    /* renamed from: a, reason: collision with root package name */
    private String f3074a = null;

    /* renamed from: b, reason: collision with root package name */
    private String f3075b = null;

    /* renamed from: c, reason: collision with root package name */
    private int f3076c = -1;

    public t(Context context) {
    }

    public String a() {
        if (!TextUtils.isEmpty(this.f3074a) && !OnekeyLoginConstants.CU_RESULT_SUCCESS.equals(this.f3074a)) {
            return this.f3074a;
        }
        if (!TextUtils.isEmpty(this.f3075b) && !OnekeyLoginConstants.CU_RESULT_SUCCESS.equals(this.f3075b)) {
            return this.f3075b;
        }
        this.f3074a = com.apm.insight.h.a().d();
        if (!TextUtils.isEmpty(this.f3074a) && !OnekeyLoginConstants.CU_RESULT_SUCCESS.equals(this.f3074a)) {
            return this.f3074a;
        }
        this.f3075b = s.a().b();
        return this.f3075b;
    }

    public void a(String str) {
        this.f3074a = str;
        s.a().b(str);
    }

    public boolean b() {
        return this.f3074a != null;
    }
}
