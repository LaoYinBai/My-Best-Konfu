package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zziy implements Runnable {
    final /* synthetic */ zzq zza;
    final /* synthetic */ com.google.android.gms.internal.measurement.zzcf zzb;
    final /* synthetic */ zzjs zzc;

    zziy(zzjs zzjsVar, zzq zzqVar, com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        this.zzc = zzjsVar;
        this.zza = zzqVar;
        this.zzb = zzcfVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        Throwable th;
        String strZzd;
        RemoteException e;
        try {
            try {
                if (!this.zzc.zzs.zzm().zzc().zzi(zzah.ANALYTICS_STORAGE)) {
                    this.zzc.zzs.zzay().zzl().zza("Analytics storage consent denied; will not get app instance id");
                    this.zzc.zzs.zzq().zzO(null);
                    this.zzc.zzs.zzm().zze.zzb(null);
                    this.zzc.zzs.zzv().zzV(this.zzb, null);
                    return;
                }
                zzjs zzjsVar = this.zzc;
                zzee zzeeVar = zzjsVar.zzb;
                if (zzeeVar == null) {
                    zzjsVar.zzs.zzay().zzd().zza("Failed to get app instance id");
                    this.zzc.zzs.zzv().zzV(this.zzb, null);
                    return;
                }
                Preconditions.checkNotNull(this.zza);
                strZzd = zzeeVar.zzd(this.zza);
                if (strZzd != null) {
                    try {
                        this.zzc.zzs.zzq().zzO(strZzd);
                        this.zzc.zzs.zzm().zze.zzb(strZzd);
                    } catch (RemoteException e2) {
                        e = e2;
                        this.zzc.zzs.zzay().zzd().zzb("Failed to get app instance id", e);
                        this.zzc.zzs.zzv().zzV(this.zzb, strZzd);
                        return;
                    }
                }
                this.zzc.zzQ();
                this.zzc.zzs.zzv().zzV(this.zzb, strZzd);
            } catch (Throwable th2) {
                th = th2;
                this.zzc.zzs.zzv().zzV(this.zzb, null);
                throw th;
            }
        } catch (RemoteException e3) {
            strZzd = null;
            e = e3;
        } catch (Throwable th3) {
            th = th3;
            this.zzc.zzs.zzv().zzV(this.zzb, null);
            throw th;
        }
    }
}
