package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zzkd implements Runnable {
    final long zza;
    final long zzb;
    final /* synthetic */ zzke zzc;

    zzkd(zzke zzkeVar, long j, long j2) {
        this.zzc = zzkeVar;
        this.zza = j;
        this.zzb = j2;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalStateException {
        this.zzc.zza.zzs.zzaz().zzp(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzkc
            @Override // java.lang.Runnable
            public final void run() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                zzkd zzkdVar = this.zza;
                zzke zzkeVar = zzkdVar.zzc;
                long j = zzkdVar.zza;
                long j2 = zzkdVar.zzb;
                zzkeVar.zza.zzg();
                zzkeVar.zza.zzs.zzay().zzc().zza("Application going to the background");
                zzkeVar.zza.zzs.zzm().zzl.zza(true);
                Bundle bundle = new Bundle();
                if (!zzkeVar.zza.zzs.zzf().zzu()) {
                    zzkeVar.zza.zzb.zzb(j2);
                    zzkeVar.zza.zzb.zzd(false, false, j2);
                }
                zzkeVar.zza.zzs.zzq().zzH("auto", "_ab", j, bundle);
            }
        });
    }
}
