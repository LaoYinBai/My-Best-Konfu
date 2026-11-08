package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zzfx implements Runnable {
    final /* synthetic */ zzhb zza;
    final /* synthetic */ zzfy zzb;

    zzfx(zzfy zzfyVar, zzhb zzhbVar) {
        this.zzb = zzfyVar;
        this.zza = zzhbVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws ClassNotFoundException {
        zzfy.zzA(this.zzb, this.zza);
        this.zzb.zzH(this.zza.zzg);
    }
}
