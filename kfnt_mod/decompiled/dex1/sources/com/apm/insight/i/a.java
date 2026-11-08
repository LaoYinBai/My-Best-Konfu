package com.apm.insight.i;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import com.apm.insight.runtime.s;
import com.baidu.mobads.sdk.internal.av;
import java.util.UUID;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static volatile UUID f2879a = null;

    /* renamed from: b, reason: collision with root package name */
    private static String f2880b = "";

    @SuppressLint({"MissingPermission", "HardwareIds"})
    private a(Context context) {
        String string;
        if (f2879a == null) {
            synchronized (a.class) {
                if (f2879a == null) {
                    String strA = s.a().a((String) null);
                    if (strA != null) {
                        f2879a = UUID.fromString(strA);
                    } else {
                        try {
                            string = Settings.Secure.getString(context.getContentResolver(), av.f);
                        } catch (Throwable unused) {
                            string = null;
                        }
                        try {
                            f2879a = string != null ? UUID.nameUUIDFromBytes(string.getBytes("utf8")) : UUID.randomUUID();
                        } catch (Throwable unused2) {
                        }
                        try {
                            s.a().c(f2879a.toString());
                        } catch (Throwable unused3) {
                        }
                    }
                }
            }
        }
    }

    public static synchronized String a(Context context) {
        UUID uuidA;
        if (TextUtils.isEmpty(f2880b) && (uuidA = new a(context).a()) != null) {
            f2880b = uuidA.toString();
        }
        return f2880b;
    }

    public UUID a() {
        return f2879a;
    }
}
