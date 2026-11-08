package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zzke {
    final /* synthetic */ zzki zza;
    private zzkd zzb;

    zzke(zzki zzkiVar) {
        this.zza = zzkiVar;
    }

    @WorkerThread
    final void zza(long j) {
        this.zzb = new zzkd(this, this.zza.zzs.zzav().currentTimeMillis(), j);
        this.zza.zzd.postDelayed(this.zzb, 2000L);
    }

    @WorkerThread
    final void zzb() {
        this.zza.zzg();
        zzkd zzkdVar = this.zzb;
        if (zzkdVar != null) {
            this.zza.zzd.removeCallbacks(zzkdVar);
        }
        this.zza.zzs.zzm().zzl.zza(false);
    }
}
