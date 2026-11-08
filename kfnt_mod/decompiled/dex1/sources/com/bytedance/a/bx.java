package com.bytedance.a;

import com.bytedance.applog.UriConfig;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class bx implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public z f3436a;

    /* renamed from: b, reason: collision with root package name */
    public final aj f3437b;

    /* renamed from: c, reason: collision with root package name */
    public final bn<ae> f3438c;

    public bx(@org.b.a.d UriConfig uriConfig, @org.b.a.d aj ajVar, @org.b.a.d bn<ae> bnVar) {
        Intrinsics.checkParameterIsNotNull(uriConfig, "uriConfig");
        Intrinsics.checkParameterIsNotNull(ajVar, "queryParam");
        Intrinsics.checkParameterIsNotNull(bnVar, "requestListener");
        this.f3437b = ajVar;
        this.f3438c = bnVar;
        this.f3436a = new bi(uriConfig);
    }

    @Override // java.lang.Runnable
    public void run() {
        ae aeVar;
        String str;
        int i;
        ao<ae> aoVarA = ((bi) this.f3436a).a(this.f3437b);
        boolean z = false;
        if (aoVarA != null) {
            i = aoVarA.f3363b;
            str = aoVarA.f3364c;
            aeVar = (ae) aoVarA.d;
            if (i == 0) {
                z = true;
            }
        } else {
            aeVar = null;
            str = "";
            i = -1;
        }
        if (!z) {
            this.f3438c.a(i, str);
        } else if (aeVar != null) {
            this.f3438c.a(aeVar);
        }
    }
}
