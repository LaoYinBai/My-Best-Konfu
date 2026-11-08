package com.bytedance.a;

import com.bytedance.applog.UriConfig;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class bs implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public z f3428a;

    /* renamed from: b, reason: collision with root package name */
    public final ay f3429b;

    /* renamed from: c, reason: collision with root package name */
    public final aj f3430c;
    public final bn<at> d;

    public bs(@org.b.a.d UriConfig uriConfig, @org.b.a.d ay ayVar, @org.b.a.d aj ajVar, @org.b.a.d bn<at> bnVar) {
        Intrinsics.checkParameterIsNotNull(uriConfig, "uriConfig");
        Intrinsics.checkParameterIsNotNull(ayVar, TTLogUtil.TAG_EVENT_REQUEST);
        Intrinsics.checkParameterIsNotNull(ajVar, "queryParam");
        Intrinsics.checkParameterIsNotNull(bnVar, "requestListener");
        this.f3429b = ayVar;
        this.f3430c = ajVar;
        this.d = bnVar;
        this.f3428a = new bi(uriConfig);
    }

    @Override // java.lang.Runnable
    public void run() throws JSONException {
        at atVar;
        String str;
        int i;
        ao<at> aoVarA = ((bi) this.f3428a).a(this.f3429b, this.f3430c);
        boolean z = false;
        if (aoVarA != null) {
            i = aoVarA.f3363b;
            str = aoVarA.f3364c;
            atVar = (at) aoVarA.d;
            if (i == 0) {
                z = true;
            }
        } else {
            atVar = null;
            str = "";
            i = -1;
        }
        if (!z) {
            this.d.a(i, str);
        } else if (atVar != null) {
            this.d.a(atVar);
        }
    }
}
