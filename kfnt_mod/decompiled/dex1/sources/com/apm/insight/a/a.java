package com.apm.insight.a;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.apm.insight.CrashType;
import com.apm.insight.ICrashCallback;
import com.apm.insight.runtime.p;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements ICrashCallback {
    private static volatile a d;

    /* renamed from: a, reason: collision with root package name */
    private volatile String f2756a;

    /* renamed from: b, reason: collision with root package name */
    private volatile b f2757b;

    /* renamed from: c, reason: collision with root package name */
    private volatile c f2758c;
    private volatile boolean e = false;

    private a() {
    }

    public static a a() {
        if (d == null) {
            synchronized (a.class) {
                if (d == null) {
                    d = new a();
                }
            }
        }
        return d;
    }

    public void a(CrashType crashType, long j, String str) {
    }

    public void a(String str) {
    }

    public void a(String str, b bVar, c cVar) {
        this.f2756a = str;
        this.f2757b = bVar;
        this.f2758c = cVar;
        if (this.e) {
            return;
        }
        this.e = true;
        p.b().a(new Runnable() { // from class: com.apm.insight.a.a.1
            @Override // java.lang.Runnable
            public void run() {
            }
        });
    }

    public void b() {
    }

    @Override // com.apm.insight.ICrashCallback
    public void onCrash(@NonNull CrashType crashType, @Nullable String str, @Nullable Thread thread) {
        if (crashType.equals(CrashType.NATIVE)) {
        }
    }
}
