package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import java.net.URL;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.1.1 */
@WorkerThread
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zzig implements Runnable {
    final /* synthetic */ zzih zza;
    private final URL zzb;
    private final String zzc;
    private final zzfw zzd;

    public zzig(zzih zzihVar, String str, URL url, byte[] bArr, Map map, zzfw zzfwVar, byte[] bArr2) {
        this.zza = zzihVar;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(zzfwVar);
        this.zzb = url;
        this.zzd = zzfwVar;
        this.zzc = str;
    }

    private final void zzb(final int i, final Exception exc, final byte[] bArr, final Map map) throws IllegalStateException {
        this.zza.zzs.zzaz().zzp(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzif
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zza(i, exc, bArr, map);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00a0  */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v9 */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() throws java.lang.Throwable {
        /*
            r10 = this;
            com.google.android.gms.measurement.internal.zzih r0 = r10.zza
            r0.zzax()
            r0 = 0
            r1 = 0
            com.google.android.gms.measurement.internal.zzih r2 = r10.zza     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L9b
            java.net.URL r3 = r10.zzb     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L9b
            java.net.URLConnection r3 = r3.openConnection()     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L9b
            boolean r4 = r3 instanceof java.net.HttpURLConnection     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L9b
            if (r4 == 0) goto L87
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L9b
            r3.setDefaultUseCaches(r0)     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L9b
            com.google.android.gms.measurement.internal.zzfy r4 = r2.zzs     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L9b
            r4.zzf()     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L9b
            r4 = 60000(0xea60, float:8.4078E-41)
            r3.setConnectTimeout(r4)     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L9b
            com.google.android.gms.measurement.internal.zzfy r2 = r2.zzs     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L9b
            r2.zzf()     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L9b
            r2 = 61000(0xee48, float:8.5479E-41)
            r3.setReadTimeout(r2)     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L9b
            r3.setInstanceFollowRedirects(r0)     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L9b
            r2 = 1
            r3.setDoInput(r2)     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L9b
            int r2 = r3.getResponseCode()     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L84
            java.util.Map r4 = r3.getHeaderFields()     // Catch: java.lang.Throwable -> L75 java.io.IOException -> L7b
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L68
            r5.<init>()     // Catch: java.lang.Throwable -> L68
            java.io.InputStream r6 = r3.getInputStream()     // Catch: java.lang.Throwable -> L68
            r7 = 1024(0x400, float:1.435E-42)
            byte[] r7 = new byte[r7]     // Catch: java.lang.Throwable -> L66
        L4a:
            int r8 = r6.read(r7)     // Catch: java.lang.Throwable -> L66
            if (r8 <= 0) goto L54
            r5.write(r7, r0, r8)     // Catch: java.lang.Throwable -> L66
            goto L4a
        L54:
            byte[] r0 = r5.toByteArray()     // Catch: java.lang.Throwable -> L66
            if (r6 == 0) goto L5d
            r6.close()     // Catch: java.lang.Throwable -> L70 java.io.IOException -> L72
        L5d:
            if (r3 == 0) goto L62
            r3.disconnect()
        L62:
            r10.zzb(r2, r1, r0, r4)
            return
        L66:
            r0 = move-exception
            goto L6a
        L68:
            r0 = move-exception
            r6 = r1
        L6a:
            if (r6 == 0) goto L74
            r6.close()     // Catch: java.lang.Throwable -> L70 java.io.IOException -> L72
            goto L74
        L70:
            r0 = move-exception
            goto L77
        L72:
            r0 = move-exception
            goto L7d
        L74:
            throw r0     // Catch: java.lang.Throwable -> L70 java.io.IOException -> L72
        L75:
            r0 = move-exception
            r4 = r1
        L77:
            r9 = r2
            r2 = r0
            r0 = r9
            goto L92
        L7b:
            r0 = move-exception
            r4 = r1
        L7d:
            r9 = r2
            r2 = r0
            r0 = r9
            goto L9e
        L81:
            r2 = move-exception
            r4 = r1
            goto L92
        L84:
            r2 = move-exception
            r4 = r1
            goto L9e
        L87:
            java.io.IOException r2 = new java.io.IOException     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L9b
            java.lang.String r3 = "Failed to obtain HTTP connection"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L9b
            throw r2     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L9b
        L8f:
            r2 = move-exception
            r3 = r1
            r4 = r3
        L92:
            if (r3 == 0) goto L97
            r3.disconnect()
        L97:
            r10.zzb(r0, r1, r1, r4)
            throw r2
        L9b:
            r2 = move-exception
            r3 = r1
            r4 = r3
        L9e:
            if (r3 == 0) goto La3
            r3.disconnect()
        La3:
            r10.zzb(r0, r2, r1, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzig.run():void");
    }

    final /* synthetic */ void zza(int i, Exception exc, byte[] bArr, Map map) {
        zzfw zzfwVar = this.zzd;
        zzfwVar.zza.zzC(this.zzc, i, exc, bArr, map);
    }
}
