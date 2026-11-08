package com.bytedance.sdk.adtnc.sdk.a;

import android.text.TextUtils;
import java.net.InetAddress;
import java.net.URL;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private String f5120a;

    /* renamed from: b, reason: collision with root package name */
    private String f5121b;

    public a(String str) {
        this.f5120a = str;
        this.f5121b = a(str);
    }

    private String a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return InetAddress.getByName(new URL(str).getHost()).getHostAddress();
            } catch (Exception unused) {
            }
        }
        return "";
    }

    public String a() {
        return this.f5120a;
    }

    public String b() {
        return this.f5121b;
    }
}
