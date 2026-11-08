package com.apm.insight.c;

import com.apm.insight.h;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static AtomicBoolean f2819a = new AtomicBoolean(false);

    public static boolean a() {
        synchronized (f2819a) {
            if (f2819a.get()) {
                return false;
            }
            f2819a.set(true);
            return b();
        }
    }

    public static boolean b() {
        if (!com.apm.insight.runtime.a.h()) {
            return false;
        }
        try {
            File file = new File(h.h().getFilesDir(), "apminsight/crashCommand");
            file.mkdirs();
            new File(file, "0_" + System.currentTimeMillis()).createNewFile();
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}
