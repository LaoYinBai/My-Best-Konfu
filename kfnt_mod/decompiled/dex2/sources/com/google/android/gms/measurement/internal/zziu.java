package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zziu implements Runnable {
    final /* synthetic */ zzq zza;
    final /* synthetic */ boolean zzb;
    final /* synthetic */ zzlc zzc;
    final /* synthetic */ zzjs zzd;

    zziu(zzjs zzjsVar, zzq zzqVar, boolean z, zzlc zzlcVar) {
        this.zzd = zzjsVar;
        this.zza = zzqVar;
        this.zzb = z;
        this.zzc = zzlcVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzjs zzjsVar = this.zzd;
        zzee zzeeVar = zzjsVar.zzb;
        if (zzeeVar == null) {
            zzjsVar.zzs.zzay().zzd().zza("Discarding data. Failed to set user property");
            return;
        }
        Preconditions.checkNotNull(this.zza);
        this.zzd.zzD(zzeeVar, this.zzb ? null : this.zzc, this.zza);
        this.zzd.zzQ();
    }
}
