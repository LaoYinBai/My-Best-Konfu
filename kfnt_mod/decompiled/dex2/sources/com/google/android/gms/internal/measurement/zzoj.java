package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzoj implements zzoi {
    public static final zzia zza;
    public static final zzia zzb;
    public static final zzia zzc;
    public static final zzia zzd;
    public static final zzia zze;

    static {
        zzhx zzhxVarZza = new zzhx(zzhp.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zzhxVarZza.zzf("measurement.client.global_params", true);
        zzb = zzhxVarZza.zzf("measurement.service.global_params_in_payload", true);
        zzc = zzhxVarZza.zzf("measurement.service.clear_global_params_on_uninstall", true);
        zzd = zzhxVarZza.zzf("measurement.service.global_params", true);
        zze = zzhxVarZza.zzd("measurement.id.service.global_params", 0L);
    }

    @Override // com.google.android.gms.internal.measurement.zzoi
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzoi
    public final boolean zzb() {
        return ((Boolean) zzc.zzb()).booleanValue();
    }
}
