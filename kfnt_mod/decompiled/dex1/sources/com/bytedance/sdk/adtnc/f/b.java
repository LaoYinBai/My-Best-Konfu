package com.bytedance.sdk.adtnc.f;

import android.os.Handler;
import android.os.Looper;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b extends a {
    public b(com.bytedance.sdk.adtnc.sdk.b.a aVar) {
        super(aVar);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.bytedance.sdk.adtnc.f.b.1
            @Override // java.lang.Runnable
            public void run() {
                com.bytedance.sdk.adtnc.g.b.a("InitializeStrategy", "InitializeHandler", "strategy trigger ");
                b.this.a(false);
            }
        });
    }
}
