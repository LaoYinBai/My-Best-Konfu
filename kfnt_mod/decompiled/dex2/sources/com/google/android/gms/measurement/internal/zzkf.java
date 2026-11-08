package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zzkf extends zzap {
    final /* synthetic */ zzkg zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzkf(zzkg zzkgVar, zzgt zzgtVar) {
        super(zzgtVar);
        this.zza = zzkgVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzap
    @WorkerThread
    public final void zzc() {
        zzkg zzkgVar = this.zza;
        zzkgVar.zzc.zzg();
        zzkgVar.zzd(false, false, zzkgVar.zzc.zzs.zzav().elapsedRealtime());
        zzkgVar.zzc.zzs.zzd().zzf(zzkgVar.zzc.zzs.zzav().elapsedRealtime());
    }
}
