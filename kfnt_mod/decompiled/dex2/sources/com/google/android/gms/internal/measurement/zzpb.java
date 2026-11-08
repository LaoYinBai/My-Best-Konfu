package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzpb implements zzpa {
    public static final zzia zza;
    public static final zzia zzb;

    static {
        zzhx zzhxVarZza = new zzhx(zzhp.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zzhxVarZza.zzf("measurement.collection.enable_session_stitching_token.client.dev", false);
        zzb = zzhxVarZza.zzf("measurement.collection.enable_session_stitching_token.service", false);
    }

    @Override // com.google.android.gms.internal.measurement.zzpa
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzpa
    public final boolean zzb() {
        return ((Boolean) zza.zzb()).booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpa
    public final boolean zzc() {
        return ((Boolean) zzb.zzb()).booleanValue();
    }
}
