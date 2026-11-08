package com.bytedance.push.utils;

import android.os.SystemProperties;
import com.bytedance.sdk.account.execute.ApiRequest;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class j {

    /* renamed from: a, reason: collision with root package name */
    private Object f5000a;

    j() {
    }

    private Object a() {
        if (this.f5000a == null) {
            synchronized (RomVersionParamHelper.class) {
                if (this.f5000a == null) {
                    try {
                        this.f5000a = Class.forName("android.os.SystemProperties").newInstance();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }
        }
        return this.f5000a;
    }

    public String a(String str) throws IllegalArgumentException {
        try {
            return SystemProperties.get(str);
        } catch (Throwable th) {
            th.printStackTrace();
            try {
                Object objA = a();
                return (String) objA.getClass().getMethod(ApiRequest.METHOD_GET, String.class).invoke(objA, str);
            } catch (IllegalArgumentException e) {
                throw e;
            } catch (Throwable unused) {
                return "";
            }
        }
    }
}
