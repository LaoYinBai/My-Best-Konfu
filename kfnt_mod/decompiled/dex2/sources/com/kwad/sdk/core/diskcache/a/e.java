package com.kwad.sdk.core.diskcache.a;

import androidx.annotation.NonNull;
import com.kwad.sdk.core.diskcache.kwai.a;
import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class e {
    static Object a(@NonNull com.kwad.sdk.core.diskcache.kwai.a aVar, @NonNull String str) throws Throwable {
        InputStream inputStreamA;
        a.c cVarA;
        try {
            try {
                cVarA = aVar.a(str);
            } catch (Throwable th) {
                th = th;
                d.a(aVar);
                throw th;
            }
        } catch (Exception e) {
            e = e;
            inputStreamA = null;
        } catch (Throwable th2) {
            th = th2;
            aVar = null;
            d.a(aVar);
            throw th;
        }
        if (cVarA == null) {
            d.a((Closeable) null);
            return null;
        }
        inputStreamA = cVarA.a(0);
        try {
            Object objA = d.a(inputStreamA);
            d.a((Closeable) inputStreamA);
            return objA;
        } catch (Exception e2) {
            e = e2;
            com.kwad.sdk.core.d.a.a(e);
            d.a((Closeable) inputStreamA);
            return null;
        }
    }

    static void a(@NonNull com.kwad.sdk.core.diskcache.kwai.a aVar, @NonNull String str, @NonNull Object obj) throws Throwable {
        OutputStream outputStreamA;
        OutputStream outputStream = null;
        try {
            try {
                a.C0033a c0033aB = aVar.b(str);
                if (c0033aB != null) {
                    outputStreamA = c0033aB.a(0);
                    try {
                        if (d.a(outputStreamA, obj)) {
                            c0033aB.a();
                        } else {
                            c0033aB.b();
                        }
                        aVar.b();
                        outputStream = outputStreamA;
                    } catch (Exception e) {
                        e = e;
                        outputStream = outputStreamA;
                        com.kwad.sdk.core.d.a.a(e);
                        d.a(outputStream);
                    } catch (Throwable th) {
                        th = th;
                        d.a(outputStreamA);
                        throw th;
                    }
                }
            } catch (Exception e2) {
                e = e2;
            }
            d.a(outputStream);
        } catch (Throwable th2) {
            th = th2;
            outputStreamA = outputStream;
        }
    }
}
