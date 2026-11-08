package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zzmp extends zzmn {
    zzmp() {
    }

    @Override // com.google.android.gms.internal.measurement.zzmn
    final /* synthetic */ int zza(Object obj) {
        return ((zzmo) obj).zza();
    }

    @Override // com.google.android.gms.internal.measurement.zzmn
    final /* synthetic */ int zzb(Object obj) {
        return ((zzmo) obj).zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzmn
    final /* synthetic */ Object zzc(Object obj) {
        return ((zzke) obj).zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzmn
    final /* bridge */ /* synthetic */ Object zzd(Object obj, Object obj2) {
        zzmo zzmoVar = (zzmo) obj2;
        return zzmoVar.equals(zzmo.zzc()) ? obj : zzmo.zzd((zzmo) obj, zzmoVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzmn
    final /* synthetic */ Object zze() {
        return zzmo.zze();
    }

    @Override // com.google.android.gms.internal.measurement.zzmn
    final /* bridge */ /* synthetic */ void zzf(Object obj, int i, long j) {
        ((zzmo) obj).zzh(i << 3, Long.valueOf(j));
    }

    @Override // com.google.android.gms.internal.measurement.zzmn
    final void zzg(Object obj) {
        ((zzke) obj).zzc.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzmn
    final /* synthetic */ void zzh(Object obj, Object obj2) {
        ((zzke) obj).zzc = (zzmo) obj2;
    }

    @Override // com.google.android.gms.internal.measurement.zzmn
    final /* synthetic */ void zzi(Object obj, zznf zznfVar) throws IOException {
        ((zzmo) obj).zzi(zznfVar);
    }
}
