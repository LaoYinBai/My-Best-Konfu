package com.bun.lib;

import android.content.Context;
import android.content.pm.PackageInfo;
import androidx.annotation.Keep;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class sysParamters {

    @Keep
    private static volatile sysParamters d;

    /* renamed from: a, reason: collision with root package name */
    @Keep
    private String f3262a;

    /* renamed from: b, reason: collision with root package name */
    @Keep
    private String f3263b;

    /* renamed from: c, reason: collision with root package name */
    @Keep
    private String f3264c;

    @Keep
    private String sdk_version = "10012";

    @Keep
    private String sdk_vname = "1.0.12";

    @Keep
    private sysParamters() {
    }

    @Keep
    private static native PackageInfo a(Context context, String str);

    @Keep
    public static native String a(Context context);

    @Keep
    public static native String a(String str, String str2);

    @Keep
    public static native String e();

    @Keep
    public static native sysParamters f();

    @Keep
    public static native String g();

    @Keep
    private static native String h();

    @Keep
    public native String a();

    @Keep
    public native String b();

    @Keep
    public native String c();

    @Keep
    public native String d();
}
