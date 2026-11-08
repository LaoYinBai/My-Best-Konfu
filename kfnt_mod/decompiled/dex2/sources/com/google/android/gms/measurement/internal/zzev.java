package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zzev implements Runnable {
    final /* synthetic */ boolean zza;
    final /* synthetic */ zzew zzb;

    zzev(zzew zzewVar, boolean z) {
        this.zzb = zzewVar;
        this.zza = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzb.zzJ(this.zza);
    }
}
