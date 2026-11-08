package com.bytedance.a;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import com.bytedance.applog.ILogger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class de {

    /* renamed from: a, reason: collision with root package name */
    public static ILogger f3492a = null;

    /* renamed from: b, reason: collision with root package name */
    public static boolean f3493b = false;

    /* renamed from: c, reason: collision with root package name */
    public static boolean f3494c = false;
    public static final int d;

    static {
        d = String.valueOf(6030090).charAt(0) >= '4' ? 16029689 : 6030090;
    }

    public static void a(Context context, @NonNull ILogger iLogger, boolean z) {
        try {
            f3494c = (context.getApplicationInfo().flags & 2) != 0;
        } catch (Throwable unused) {
            f3494c = true;
        }
        f3492a = iLogger;
        f3493b = z;
    }

    public static void a(String str, Throwable th) {
        ILogger iLogger = f3492a;
        if (iLogger != null) {
            iLogger.log(str, th);
        } else if (f3494c && f3493b) {
            Log.d("AppLog", str, th);
        }
    }
}
