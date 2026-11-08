package com.google.android.gms.internal.measurement;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzf {
    final zzax zza = new zzax();
    final zzg zzc = new zzg(null, this.zza);
    final zzg zzb = this.zzc.zza();
    final zzj zzd = new zzj();

    public zzf() {
        this.zzc.zzg("require", new zzw(this.zzd));
        this.zzd.zza("internal.platform", new Callable() { // from class: com.google.android.gms.internal.measurement.zze
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return new zzy();
            }
        });
        this.zzc.zzg("runtime.counter", new zzah(Double.valueOf(0.0d)));
    }

    public final zzap zza(zzg zzgVar, zzgx... zzgxVarArr) {
        zzap zzapVarZza = zzap.zzf;
        for (zzgx zzgxVar : zzgxVarArr) {
            zzapVarZza = zzi.zza(zzgxVar);
            zzh.zzc(this.zzc);
            if ((zzapVarZza instanceof zzaq) || (zzapVarZza instanceof zzao)) {
                zzapVarZza = this.zza.zza(zzgVar, zzapVarZza);
            }
        }
        return zzapVarZza;
    }
}
