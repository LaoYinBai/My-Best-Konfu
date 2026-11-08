package com.kwad.sdk.crash.utils;

import android.os.Build;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e {

    private static abstract class a {
        private a() {
        }

        abstract Object a();

        abstract void a(Object obj);
    }

    private static class b extends a {
        private final PrintWriter a;

        b(PrintWriter printWriter) {
            super();
            this.a = printWriter;
        }

        @Override // com.kwad.sdk.crash.utils.e.a
        Object a() {
            return this.a;
        }

        @Override // com.kwad.sdk.crash.utils.e.a
        void a(Object obj) {
            this.a.println(obj);
        }
    }

    private static void a(Throwable th, a aVar) {
        Set setNewSetFromMap = Collections.newSetFromMap(new IdentityHashMap());
        setNewSetFromMap.add(th);
        synchronized (aVar.a()) {
            aVar.a(th);
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                aVar.a("\tat " + stackTraceElement);
            }
            if (Build.VERSION.SDK_INT >= 19) {
                for (Throwable th2 : th.getSuppressed()) {
                    a(th2, aVar, "Suppressed: ", "\t", setNewSetFromMap);
                }
            }
            Throwable cause = th.getCause();
            if (cause != null) {
                a(cause, aVar, "Caused by: ", "", setNewSetFromMap);
            }
        }
    }

    private static void a(Throwable th, a aVar, String str, String str2, Set<Throwable> set) {
        if (set.contains(th)) {
            aVar.a("\t[CIRCULAR REFERENCE:" + th + "]");
            return;
        }
        set.add(th);
        StackTraceElement[] stackTrace = th.getStackTrace();
        aVar.a(str2 + str + th);
        for (StackTraceElement stackTraceElement : stackTrace) {
            aVar.a(str2 + "\tat " + stackTraceElement);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            for (Throwable th2 : th.getSuppressed()) {
                a(th2, aVar, "Suppressed: ", str2 + "\t", set);
            }
        }
        Throwable cause = th.getCause();
        if (cause != null) {
            a(cause, aVar, "Caused by: ", str2, set);
        }
    }

    public static void a(Throwable th, PrintWriter printWriter) {
        a(th, new b(printWriter));
    }
}
