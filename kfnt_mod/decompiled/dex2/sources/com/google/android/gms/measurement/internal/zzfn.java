package com.google.android.gms.measurement.internal;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zzfn implements com.google.android.gms.internal.measurement.zzr {
    final /* synthetic */ zzfp zza;

    zzfn(zzfp zzfpVar) {
        this.zza = zzfpVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzr
    public final void zza(int i, String str, List list, boolean z, boolean z2) {
        zzem zzemVarZzc;
        switch (i - 1) {
            case 0:
                zzemVarZzc = this.zza.zzs.zzay().zzc();
                break;
            case 1:
                if (!z) {
                    if (!z2) {
                        zzemVarZzc = this.zza.zzs.zzay().zze();
                        break;
                    } else {
                        zzemVarZzc = this.zza.zzs.zzay().zzd();
                        break;
                    }
                } else {
                    zzemVarZzc = this.zza.zzs.zzay().zzh();
                    break;
                }
            case 2:
            default:
                zzemVarZzc = this.zza.zzs.zzay().zzi();
                break;
            case 3:
                zzemVarZzc = this.zza.zzs.zzay().zzj();
                break;
            case 4:
                if (!z) {
                    if (!z2) {
                        zzemVarZzc = this.zza.zzs.zzay().zzl();
                        break;
                    } else {
                        zzemVarZzc = this.zza.zzs.zzay().zzk();
                        break;
                    }
                } else {
                    zzemVarZzc = this.zza.zzs.zzay().zzm();
                    break;
                }
        }
        switch (list.size()) {
            case 1:
                zzemVarZzc.zzb(str, list.get(0));
                break;
            case 2:
                zzemVarZzc.zzc(str, list.get(0), list.get(1));
                break;
            case 3:
                zzemVarZzc.zzd(str, list.get(0), list.get(1), list.get(2));
                break;
            default:
                zzemVarZzc.zza(str);
                break;
        }
    }
}
