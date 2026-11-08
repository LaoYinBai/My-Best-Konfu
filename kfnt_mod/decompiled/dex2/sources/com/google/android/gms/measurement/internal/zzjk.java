package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zzjk implements Runnable {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzq zzc;
    final /* synthetic */ com.google.android.gms.internal.measurement.zzcf zzd;
    final /* synthetic */ zzjs zze;

    zzjk(zzjs zzjsVar, String str, String str2, zzq zzqVar, com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        this.zze = zzjsVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzqVar;
        this.zzd = zzcfVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        Throwable th;
        ArrayList arrayListZzH;
        RemoteException e;
        ArrayList arrayList = new ArrayList();
        try {
            try {
                zzjs zzjsVar = this.zze;
                zzee zzeeVar = zzjsVar.zzb;
                if (zzeeVar == null) {
                    zzjsVar.zzs.zzay().zzd().zzc("Failed to get conditional properties; not connected to service", this.zza, this.zzb);
                    this.zze.zzs.zzv().zzQ(this.zzd, arrayList);
                    return;
                }
                Preconditions.checkNotNull(this.zzc);
                arrayListZzH = zzlh.zzH(zzeeVar.zzf(this.zza, this.zzb, this.zzc));
                try {
                    this.zze.zzQ();
                    this.zze.zzs.zzv().zzQ(this.zzd, arrayListZzH);
                } catch (RemoteException e2) {
                    e = e2;
                    this.zze.zzs.zzay().zzd().zzd("Failed to get conditional properties; remote exception", this.zza, this.zzb, e);
                    this.zze.zzs.zzv().zzQ(this.zzd, arrayListZzH);
                }
            } catch (Throwable th2) {
                th = th2;
                this.zze.zzs.zzv().zzQ(this.zzd, arrayList);
                throw th;
            }
        } catch (RemoteException e3) {
            arrayListZzH = arrayList;
            e = e3;
        } catch (Throwable th3) {
            th = th3;
            this.zze.zzs.zzv().zzQ(this.zzd, arrayList);
            throw th;
        }
    }
}
