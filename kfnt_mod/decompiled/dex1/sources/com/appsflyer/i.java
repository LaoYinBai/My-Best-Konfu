package com.appsflyer;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class i {

    static final class c {

        /* renamed from: ˎ, reason: contains not printable characters */
        static final i f209 = new i();
    }

    i() {
    }

    static final class b {

        /* renamed from: ˊ, reason: contains not printable characters */
        private final String f206;

        /* renamed from: ˋ, reason: contains not printable characters */
        private final String f207;

        /* renamed from: ॱ, reason: contains not printable characters */
        private final String f208;

        b(@NonNull String str, @Nullable String str2, @Nullable String str3) {
            this.f207 = str;
            this.f206 = str2;
            this.f208 = str3;
        }

        /* renamed from: ˋ, reason: contains not printable characters */
        final String m173() {
            return this.f207;
        }

        @Nullable
        /* renamed from: ॱ, reason: contains not printable characters */
        final String m175() {
            return this.f206;
        }

        @Nullable
        /* renamed from: ˎ, reason: contains not printable characters */
        final String m174() {
            return this.f208;
        }

        b() {
        }

        /* renamed from: ˋ, reason: contains not printable characters */
        static void m172(Context context) {
            Context applicationContext = context.getApplicationContext();
            AFLogger.afInfoLog("onBecameBackground");
            AppsFlyerLib.getInstance().m107();
            AFLogger.afInfoLog("callStatsBackground background call");
            AppsFlyerLib.getInstance().m108(new WeakReference<>(applicationContext));
            r rVarM212 = r.m212();
            if (rVarM212.m228()) {
                rVarM212.m223();
                if (applicationContext != null) {
                    r.m214(applicationContext.getPackageName(), applicationContext.getPackageManager());
                }
                rVarM212.m219();
            } else {
                AFLogger.afDebugLog("RD status is OFF");
            }
            AFExecutor.getInstance().m38();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00bb  */
    /* renamed from: ॱ, reason: contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.appsflyer.i.b m171(@androidx.annotation.NonNull android.content.Context r10) {
        /*
            Method dump skipped, instructions count: 208
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.i.m171(android.content.Context):com.appsflyer.i$b");
    }
}
