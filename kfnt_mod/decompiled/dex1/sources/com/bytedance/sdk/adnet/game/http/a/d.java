package com.bytedance.sdk.adnet.game.http.a;

import com.bytedance.sdk.adnet.game.ok3.okhttp3.Interceptor;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Response;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d implements Interceptor {

    /* renamed from: a, reason: collision with root package name */
    Interceptor f5027a;

    public void a(Interceptor interceptor) {
        this.f5027a = interceptor;
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        return this.f5027a != null ? this.f5027a.intercept(chain) : chain.proceed(chain.request());
    }
}
