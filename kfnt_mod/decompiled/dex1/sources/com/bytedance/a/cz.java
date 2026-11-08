package com.bytedance.a;

import com.bytedance.sdk.account.execute.ApiRequest;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class cz {

    /* renamed from: a, reason: collision with root package name */
    public Object f3483a;

    public cz() {
        try {
            this.f3483a = Class.forName("android.os.SystemProperties").newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            de.a("", e);
        }
    }

    public String a(String str) {
        try {
            return (String) this.f3483a.getClass().getMethod(ApiRequest.METHOD_GET, String.class).invoke(this.f3483a, str);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception unused) {
            return "";
        }
    }
}
