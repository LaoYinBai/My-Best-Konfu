package com.kwad.sdk.crash.report;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class d extends b {
    /* JADX WARN: Removed duplicated region for block: B:18:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00cf  */
    @Override // com.kwad.sdk.crash.report.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected com.kwad.sdk.crash.model.message.ExceptionMessage a(@androidx.annotation.NonNull java.io.File r6, java.io.File r7, java.io.File r8, java.lang.String r9) {
        /*
            r5 = this;
            r7 = 0
            java.lang.String r0 = com.kwad.sdk.crash.utils.g.d(r6)     // Catch: java.lang.Exception -> L6
            goto L20
        L6:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r5.b
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = "\n"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r5.b = r0
            r0 = r7
        L20:
            if (r0 == 0) goto L4f
            com.kwad.sdk.crash.model.message.JavaExceptionMessage r1 = new com.kwad.sdk.crash.model.message.JavaExceptionMessage     // Catch: java.lang.Exception -> L32
            r1.<init>()     // Catch: java.lang.Exception -> L32
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch: java.lang.Exception -> L30
            r7.<init>(r0)     // Catch: java.lang.Exception -> L30
            r1.parseJson(r7)     // Catch: java.lang.Exception -> L30
            goto L50
        L30:
            r7 = move-exception
            goto L36
        L32:
            r1 = move-exception
            r4 = r1
            r1 = r7
            r7 = r4
        L36:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r5.b
            r2.append(r3)
            r2.append(r7)
            java.lang.String r7 = "\n"
            r2.append(r7)
            java.lang.String r7 = r2.toString()
            r5.b = r7
            goto L50
        L4f:
            r1 = r7
        L50:
            if (r1 != 0) goto L5f
            com.kwad.sdk.crash.model.message.JavaExceptionMessage r1 = new com.kwad.sdk.crash.model.message.JavaExceptionMessage
            r1.<init>()
            boolean r7 = android.text.TextUtils.isEmpty(r0)
            if (r7 != 0) goto L5f
            r1.mCrashDetail = r0
        L5f:
            java.lang.String r7 = r6.getName()     // Catch: java.lang.Throwable -> Lab
            java.lang.String r7 = com.kwad.sdk.crash.utils.f.a(r7)     // Catch: java.lang.Throwable -> Lab
            r1.mLogUUID = r7     // Catch: java.lang.Throwable -> Lab
            com.kwad.sdk.crash.report.c r7 = r5.a     // Catch: java.lang.Throwable -> Lab
            java.lang.String r0 = "ExceptionCollector"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lab
            r2.<init>()     // Catch: java.lang.Throwable -> Lab
            java.lang.String r3 = "------ Java Crash Report Begin ------\n"
            r2.append(r3)     // Catch: java.lang.Throwable -> Lab
            r2.append(r1)     // Catch: java.lang.Throwable -> Lab
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> Lab
            r7.a(r0, r2)     // Catch: java.lang.Throwable -> Lab
            java.io.File r7 = new java.io.File     // Catch: java.lang.Throwable -> Lab
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lab
            r0.<init>()     // Catch: java.lang.Throwable -> Lab
            r0.append(r9)     // Catch: java.lang.Throwable -> Lab
            java.lang.String r9 = ".jtrace"
            r0.append(r9)     // Catch: java.lang.Throwable -> Lab
            java.lang.String r9 = r0.toString()     // Catch: java.lang.Throwable -> Lab
            r7.<init>(r9)     // Catch: java.lang.Throwable -> Lab
            r5.a(r7, r1)     // Catch: java.lang.Throwable -> Lab
            r5.b(r8, r1)     // Catch: java.lang.Throwable -> Lab
            java.lang.String r7 = r1.toString()     // Catch: java.lang.Throwable -> Lab
            com.kwad.sdk.crash.utils.f.a(r6, r7)     // Catch: java.lang.Throwable -> Lab
            com.kwad.sdk.crash.utils.f.a(r8, r6)     // Catch: java.lang.Throwable -> Lab
            r6.renameTo(r8)     // Catch: java.lang.Throwable -> Lab
            goto Lc7
        Lab:
            r6 = move-exception
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = r5.b
            r7.append(r8)
            r7.append(r6)
            java.lang.String r8 = "\n"
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            r5.b = r7
            com.kwad.sdk.core.d.a.b(r6)
        Lc7:
            java.lang.String r6 = r5.b
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 != 0) goto Le4
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = r1.mErrorMessage
            r6.append(r7)
            java.lang.String r7 = r5.b
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r1.mErrorMessage = r6
        Le4:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.crash.report.d.a(java.io.File, java.io.File, java.io.File, java.lang.String):com.kwad.sdk.crash.model.message.ExceptionMessage");
    }
}
