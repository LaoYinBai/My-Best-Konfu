package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzel extends zzke implements zzlm {
    private static final zzel zza;
    private int zze;
    private zzex zzf;
    private zzeq zzg;
    private boolean zzh;
    private String zzi = "";

    static {
        zzel zzelVar = new zzel();
        zza = zzelVar;
        zzke.zzbJ(zzel.class, zzelVar);
    }

    private zzel() {
    }

    public static zzel zzb() {
        return zza;
    }

    static /* synthetic */ void zzf(zzel zzelVar, String str) {
        zzelVar.zze |= 8;
        zzelVar.zzi = str;
    }

    public final zzeq zzc() {
        zzeq zzeqVar = this.zzg;
        return zzeqVar == null ? zzeq.zzb() : zzeqVar;
    }

    public final zzex zzd() {
        zzex zzexVar = this.zzf;
        return zzexVar == null ? zzex.zzc() : zzexVar;
    }

    public final String zze() {
        return this.zzi;
    }

    public final boolean zzg() {
        return this.zzh;
    }

    public final boolean zzh() {
        return (this.zze & 4) != 0;
    }

    public final boolean zzi() {
        return (this.zze & 2) != 0;
    }

    public final boolean zzj() {
        return (this.zze & 8) != 0;
    }

    public final boolean zzk() {
        return (this.zze & 1) != 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzke
    protected final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        zzef zzefVar = null;
        switch (i2) {
            case 2:
                return zzbI(zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဇ\u0002\u0004ဈ\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
            case 3:
                return new zzel();
            case 4:
                return new zzek(zzefVar);
            case 5:
                return zza;
            default:
                return null;
        }
    }
}
