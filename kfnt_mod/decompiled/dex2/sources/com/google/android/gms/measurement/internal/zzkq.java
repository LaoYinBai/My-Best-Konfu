package com.google.android.gms.measurement.internal;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zzkq implements zzeq {
    final /* synthetic */ String zza;
    final /* synthetic */ zzkz zzb;

    zzkq(zzkz zzkzVar, String str) {
        this.zzb = zzkzVar;
        this.zza = str;
    }

    @Override // com.google.android.gms.measurement.internal.zzeq
    public final void zza(String str, int i, Throwable th, byte[] bArr, Map map) {
        this.zzb.zzK(i, th, bArr, this.zza);
    }
}
