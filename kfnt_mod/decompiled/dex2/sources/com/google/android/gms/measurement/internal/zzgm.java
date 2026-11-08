package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zzgm implements Runnable {
    final /* synthetic */ zzlc zza;
    final /* synthetic */ zzq zzb;
    final /* synthetic */ zzgq zzc;

    zzgm(zzgq zzgqVar, zzlc zzlcVar, zzq zzqVar) {
        this.zzc = zzgqVar;
        this.zza = zzlcVar;
        this.zzb = zzqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zza.zzA();
        if (this.zza.zza() == null) {
            this.zzc.zza.zzP(this.zza, this.zzb);
        } else {
            this.zzc.zza.zzW(this.zza, this.zzb);
        }
    }
}
