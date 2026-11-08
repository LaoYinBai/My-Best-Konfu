package com.kwad.sdk.api.loader;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.kwad.sdk.api.core.IKsAdSDK;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class j {
    private final String a;
    private final String b;
    private final String c;
    private Resources d;
    private ClassLoader e;
    private IKsAdSDK f;

    private j(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    static synchronized j a(Context context, String str) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return a(context, g.d(context, str), g.e(context, str), g.f(context, str));
    }

    static j a(Context context, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            throw new RuntimeException("mApk is null");
        }
        File file = new File(str);
        if (!file.exists() || !file.isFile()) {
            throw new RuntimeException("mApk not a file");
        }
        j jVar = new j(str, str2, str3);
        jVar.a(context);
        return jVar;
    }

    private void a(Context context) {
        d();
        Resources resourcesA = o.a(context, context.getResources(), this.a);
        ClassLoader classLoaderA = d.a(context, this.a, this.b, this.c);
        IKsAdSDK iKsAdSDKA = Loader.a(classLoaderA);
        this.d = resourcesA;
        this.e = classLoaderA;
        this.f = iKsAdSDKA;
        int sDKType = iKsAdSDKA.getSDKType();
        if (sDKType == 1) {
            return;
        }
        throw new RuntimeException("sdkType error apiType: 1 , sdkType:" + sDKType);
    }

    private void d() {
        if (TextUtils.isEmpty(this.a)) {
            throw new RuntimeException("mApk is null");
        }
        File file = new File(this.a);
        if (!file.isFile() || !file.exists()) {
            throw new RuntimeException("mApk not a file");
        }
    }

    Resources a() {
        return this.d;
    }

    ClassLoader b() {
        return this.e;
    }

    IKsAdSDK c() {
        return this.f;
    }

    public String toString() {
        return "ExternalPackage{mApk='" + this.a + "', mDexDir='" + this.b + "', mNativeLibDir='" + this.c + "', mResource=" + this.d + ", mClassLoader=" + this.e + ", mKsSdk=" + this.f + '}';
    }
}
