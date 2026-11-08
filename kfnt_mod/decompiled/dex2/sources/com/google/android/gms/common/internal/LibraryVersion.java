package com.google.android.gms.common.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
@KeepForSdk
@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class LibraryVersion {
    private static final GmsLogger zza = new GmsLogger("LibraryVersion", "");
    private static LibraryVersion zzb = new LibraryVersion();
    private ConcurrentHashMap zzc = new ConcurrentHashMap();

    @VisibleForTesting
    protected LibraryVersion() {
    }

    @NonNull
    @KeepForSdk
    public static LibraryVersion getInstance() {
        return zzb;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00bc  */
    @androidx.annotation.NonNull
    @com.google.android.gms.common.annotation.KeepForSdk
    @java.lang.Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String getVersion(@androidx.annotation.NonNull java.lang.String r8) throws java.lang.Throwable {
        /*
            r7 = this;
            java.lang.String r0 = "Please provide a valid libraryName"
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r8, r0)
            java.util.concurrent.ConcurrentHashMap r0 = r7.zzc
            boolean r0 = r0.containsKey(r8)
            if (r0 == 0) goto L16
            java.util.concurrent.ConcurrentHashMap r0 = r7.zzc
            java.lang.Object r8 = r0.get(r8)
            java.lang.String r8 = (java.lang.String) r8
            return r8
        L16:
            java.util.Properties r0 = new java.util.Properties
            r0.<init>()
            r1 = 1
            r2 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86
            r3 = 0
            r1[r3] = r8     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86
            java.lang.Class<com.google.android.gms.common.internal.LibraryVersion> r3 = com.google.android.gms.common.internal.LibraryVersion.class
            java.lang.String r4 = "/%s.properties"
            java.lang.String r1 = java.lang.String.format(r4, r1)     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86
            java.io.InputStream r1 = r3.getResourceAsStream(r1)     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86
            if (r1 == 0) goto L5f
            r0.load(r1)     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            java.lang.String r3 = "version"
            java.lang.String r0 = r0.getProperty(r3, r2)     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            com.google.android.gms.common.internal.GmsLogger r2 = com.google.android.gms.common.internal.LibraryVersion.zza     // Catch: java.io.IOException -> L55 java.lang.Throwable -> L5b
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L55 java.lang.Throwable -> L5b
            r3.<init>()     // Catch: java.io.IOException -> L55 java.lang.Throwable -> L5b
            r3.append(r8)     // Catch: java.io.IOException -> L55 java.lang.Throwable -> L5b
            java.lang.String r4 = " version is "
            r3.append(r4)     // Catch: java.io.IOException -> L55 java.lang.Throwable -> L5b
            r3.append(r0)     // Catch: java.io.IOException -> L55 java.lang.Throwable -> L5b
            java.lang.String r4 = "LibraryVersion"
            java.lang.String r3 = r3.toString()     // Catch: java.io.IOException -> L55 java.lang.Throwable -> L5b
            r2.v(r4, r3)     // Catch: java.io.IOException -> L55 java.lang.Throwable -> L5b
            goto L78
        L55:
            r2 = move-exception
            r6 = r1
            r1 = r0
            r0 = r2
            r2 = r6
            goto L88
        L5b:
            r8 = move-exception
            goto L7e
        L5d:
            r0 = move-exception
            goto L80
        L5f:
            com.google.android.gms.common.internal.GmsLogger r0 = com.google.android.gms.common.internal.LibraryVersion.zza     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            r3.<init>()     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            java.lang.String r4 = "Failed to get app version for libraryName: "
            r3.append(r4)     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            r3.append(r8)     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            java.lang.String r4 = "LibraryVersion"
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            r0.w(r4, r3)     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            r0 = r2
        L78:
            if (r1 == 0) goto La6
            com.google.android.gms.common.util.IOUtils.closeQuietly(r1)
            goto La6
        L7e:
            r2 = r1
            goto Lba
        L80:
            r6 = r2
            r2 = r1
            r1 = r6
            goto L88
        L84:
            r8 = move-exception
            goto Lba
        L86:
            r0 = move-exception
            r1 = r2
        L88:
            com.google.android.gms.common.internal.GmsLogger r3 = com.google.android.gms.common.internal.LibraryVersion.zza     // Catch: java.lang.Throwable -> Lb9
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb9
            r4.<init>()     // Catch: java.lang.Throwable -> Lb9
            java.lang.String r5 = "Failed to get app version for libraryName: "
            r4.append(r5)     // Catch: java.lang.Throwable -> Lb9
            r4.append(r8)     // Catch: java.lang.Throwable -> Lb9
            java.lang.String r5 = "LibraryVersion"
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> Lb9
            r3.e(r5, r4, r0)     // Catch: java.lang.Throwable -> Lb9
            if (r2 == 0) goto La5
            com.google.android.gms.common.util.IOUtils.closeQuietly(r2)
        La5:
            r0 = r1
        La6:
            if (r0 != 0) goto Lb3
            com.google.android.gms.common.internal.GmsLogger r0 = com.google.android.gms.common.internal.LibraryVersion.zza
            java.lang.String r1 = "LibraryVersion"
            java.lang.String r2 = ".properties file is dropped during release process. Failure to read app version is expected during Google internal testing where locally-built libraries are used"
            r0.d(r1, r2)
            java.lang.String r0 = "UNKNOWN"
        Lb3:
            java.util.concurrent.ConcurrentHashMap r1 = r7.zzc
            r1.put(r8, r0)
            return r0
        Lb9:
            r8 = move-exception
        Lba:
            if (r2 == 0) goto Lbf
            com.google.android.gms.common.util.IOUtils.closeQuietly(r2)
        Lbf:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.LibraryVersion.getVersion(java.lang.String):java.lang.String");
    }
}
