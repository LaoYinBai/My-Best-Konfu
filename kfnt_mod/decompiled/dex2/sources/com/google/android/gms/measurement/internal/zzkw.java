package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zzkw {
    com.google.android.gms.internal.measurement.zzgc zza;
    List zzb;
    List zzc;
    long zzd;
    final /* synthetic */ zzkz zze;

    /* synthetic */ zzkw(zzkz zzkzVar, zzkv zzkvVar) {
        this.zze = zzkzVar;
    }

    private static final long zzb(com.google.android.gms.internal.measurement.zzfs zzfsVar) {
        return ((zzfsVar.zzd() / 1000) / 60) / 60;
    }

    public final boolean zza(long j, com.google.android.gms.internal.measurement.zzfs zzfsVar) {
        Preconditions.checkNotNull(zzfsVar);
        if (this.zzc == null) {
            this.zzc = new ArrayList();
        }
        if (this.zzb == null) {
            this.zzb = new ArrayList();
        }
        if (!this.zzc.isEmpty() && zzb((com.google.android.gms.internal.measurement.zzfs) this.zzc.get(0)) != zzb(zzfsVar)) {
            return false;
        }
        long jZzbw = this.zzd + zzfsVar.zzbw();
        this.zze.zzg();
        if (jZzbw >= Math.max(0, ((Integer) zzeb.zzh.zza(null)).intValue())) {
            return false;
        }
        this.zzd = jZzbw;
        this.zzc.add(zzfsVar);
        this.zzb.add(Long.valueOf(j));
        int size = this.zzc.size();
        this.zze.zzg();
        return size < Math.max(1, ((Integer) zzeb.zzi.zza(null)).intValue());
    }
}
