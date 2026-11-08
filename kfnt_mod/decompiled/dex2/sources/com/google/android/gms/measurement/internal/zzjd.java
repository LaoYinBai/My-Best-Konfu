package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zzjd implements Runnable {
    final /* synthetic */ zzaw zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ com.google.android.gms.internal.measurement.zzcf zzc;
    final /* synthetic */ zzjs zzd;

    zzjd(zzjs zzjsVar, zzaw zzawVar, String str, com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        this.zzd = zzjsVar;
        this.zza = zzawVar;
        this.zzb = str;
        this.zzc = zzcfVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        Throwable th;
        byte[] bArrZzu;
        RemoteException e;
        try {
            try {
                zzjs zzjsVar = this.zzd;
                zzee zzeeVar = zzjsVar.zzb;
                if (zzeeVar == null) {
                    zzjsVar.zzs.zzay().zzd().zza("Discarding data. Failed to send event to service to bundle");
                    this.zzd.zzs.zzv().zzS(this.zzc, null);
                    return;
                }
                bArrZzu = zzeeVar.zzu(this.zza, this.zzb);
                try {
                    this.zzd.zzQ();
                    this.zzd.zzs.zzv().zzS(this.zzc, bArrZzu);
                } catch (RemoteException e2) {
                    e = e2;
                    this.zzd.zzs.zzay().zzd().zzb("Failed to send event to the service to bundle", e);
                    this.zzd.zzs.zzv().zzS(this.zzc, bArrZzu);
                }
            } catch (Throwable th2) {
                th = th2;
                this.zzd.zzs.zzv().zzS(this.zzc, null);
                throw th;
            }
        } catch (RemoteException e3) {
            bArrZzu = null;
            e = e3;
        } catch (Throwable th3) {
            th = th3;
            this.zzd.zzs.zzv().zzS(this.zzc, null);
            throw th;
        }
    }
}
