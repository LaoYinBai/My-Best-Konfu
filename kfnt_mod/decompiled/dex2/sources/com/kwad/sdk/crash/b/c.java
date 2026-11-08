package com.kwad.sdk.crash.b;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.crash.e;
import com.kwad.sdk.crash.model.message.ExceptionMessage;
import java.io.File;
import java.util.concurrent.CountDownLatch;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class c extends b {

    private static class a {
        private static final c a = new c();
    }

    private c() {
    }

    public static c d() {
        return a.a;
    }

    @Override // com.kwad.sdk.crash.b.b
    public void a(File file, e eVar, com.kwad.sdk.crash.report.c cVar) {
        super.a(file, eVar, cVar);
        if (com.kwad.sdk.crash.d.a().i()) {
            a(new File("sdcard/kwad_ex/java_crash/dump"));
        }
    }

    public void a(@NonNull Throwable th, @NonNull ExceptionMessage exceptionMessage, @Nullable Context context) throws Throwable {
        a(th, exceptionMessage, context, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:72:0x0211 A[Catch: Throwable -> 0x027b, TryCatch #0 {Throwable -> 0x027b, blocks: (B:70:0x0207, B:72:0x0211, B:74:0x0224, B:76:0x022b, B:78:0x0243, B:79:0x024c, B:80:0x024f, B:82:0x0255), top: B:90:0x0207 }] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0253  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(@androidx.annotation.NonNull java.lang.Throwable r18, @androidx.annotation.NonNull com.kwad.sdk.crash.model.message.ExceptionMessage r19, @androidx.annotation.Nullable android.content.Context r20, boolean r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 648
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.crash.b.c.a(java.lang.Throwable, com.kwad.sdk.crash.model.message.ExceptionMessage, android.content.Context, boolean):void");
    }

    @Override // com.kwad.sdk.crash.b.b
    protected void a(@NonNull File[] fileArr, @Nullable CountDownLatch countDownLatch) {
        com.kwad.sdk.crash.report.d dVar = new com.kwad.sdk.crash.report.d();
        dVar.a(a());
        for (File file : fileArr) {
            dVar.b(file);
        }
    }

    @Override // com.kwad.sdk.crash.b.b
    protected int c() {
        return 1;
    }
}
