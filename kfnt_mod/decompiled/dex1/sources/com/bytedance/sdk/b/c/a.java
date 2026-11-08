package com.bytedance.sdk.b.c;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private com.bytedance.sdk.b.e.a.a f5131a;

    /* renamed from: b, reason: collision with root package name */
    private Map<String, com.bytedance.sdk.b.a.a> f5132b = new HashMap();

    public a(com.bytedance.sdk.b.e.a.a aVar) {
        this.f5131a = aVar;
    }

    public synchronized void a(String str, com.bytedance.sdk.b.a.a aVar) {
        if (!TextUtils.isEmpty(str) && aVar != null) {
            this.f5132b.put(str, aVar);
        }
    }

    public synchronized com.bytedance.sdk.b.a.a a(String str) {
        return this.f5132b.get(str);
    }
}
