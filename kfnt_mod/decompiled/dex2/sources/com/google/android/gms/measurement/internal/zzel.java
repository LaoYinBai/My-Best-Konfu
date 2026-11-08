package com.google.android.gms.measurement.internal;

import android.util.Log;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zzel implements Runnable {
    final /* synthetic */ int zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ Object zzc;
    final /* synthetic */ Object zzd;
    final /* synthetic */ Object zze;
    final /* synthetic */ zzeo zzf;

    zzel(zzeo zzeoVar, int i, String str, Object obj, Object obj2, Object obj3) {
        this.zzf = zzeoVar;
        this.zza = i;
        this.zzb = str;
        this.zzc = obj;
        this.zzd = obj2;
        this.zze = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzfd zzfdVarZzm = this.zzf.zzs.zzm();
        if (!zzfdVarZzm.zzx()) {
            Log.println(6, this.zzf.zzq(), "Persisted config not initialized. Not logging error/warn");
            return;
        }
        zzeo zzeoVar = this.zzf;
        if (zzeoVar.zza == 0) {
            if (zzeoVar.zzs.zzf().zzy()) {
                zzeo zzeoVar2 = this.zzf;
                zzeoVar2.zzs.zzaw();
                zzeoVar2.zza = 'C';
            } else {
                zzeo zzeoVar3 = this.zzf;
                zzeoVar3.zzs.zzaw();
                zzeoVar3.zza = 'c';
            }
        }
        zzeo zzeoVar4 = this.zzf;
        if (zzeoVar4.zzb < 0) {
            zzeoVar4.zzs.zzf().zzh();
            zzeoVar4.zzb = 73000L;
        }
        char cCharAt = "01VDIWEA?".charAt(this.zza);
        zzeo zzeoVar5 = this.zzf;
        String strSubstring = "2" + cCharAt + zzeoVar5.zza + zzeoVar5.zzb + ":" + zzeo.zzo(true, this.zzb, this.zzc, this.zzd, this.zze);
        if (strSubstring.length() > 1024) {
            strSubstring = this.zzb.substring(0, 1024);
        }
        zzfb zzfbVar = zzfdVarZzm.zzb;
        if (zzfbVar != null) {
            zzfbVar.zzb(strSubstring, 1L);
        }
    }
}
