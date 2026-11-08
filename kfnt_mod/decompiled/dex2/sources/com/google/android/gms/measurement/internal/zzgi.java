package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zzgi implements Runnable {
    final /* synthetic */ zzq zza;
    final /* synthetic */ zzgq zzb;

    zzgi(zzgq zzgqVar, zzq zzqVar) {
        this.zzb = zzgqVar;
        this.zza = zzqVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        this.zzb.zza.zzA();
        zzkz zzkzVar = this.zzb.zza;
        zzq zzqVar = this.zza;
        zzkzVar.zzaz().zzg();
        zzkzVar.zzB();
        Preconditions.checkNotEmpty(zzqVar.zza);
        zzai zzaiVarZzb = zzai.zzb(zzqVar.zzv);
        zzai zzaiVarZzh = zzkzVar.zzh(zzqVar.zza);
        zzkzVar.zzay().zzj().zzc("Setting consent, package, consent", zzqVar.zza, zzaiVarZzb);
        zzkzVar.zzV(zzqVar.zza, zzaiVarZzb);
        if (zzaiVarZzb.zzk(zzaiVarZzh)) {
            zzkzVar.zzQ(zzqVar);
        }
    }
}
