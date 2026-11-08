package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzgl extends zzke implements zzlm {
    private static final zzgl zza;
    private int zze;
    private long zzf;
    private String zzg = "";
    private String zzh = "";
    private long zzi;
    private float zzj;
    private double zzk;

    static {
        zzgl zzglVar = new zzgl();
        zza = zzglVar;
        zzke.zzbJ(zzgl.class, zzglVar);
    }

    private zzgl() {
    }

    public static zzgk zzd() {
        return (zzgk) zza.zzbx();
    }

    static /* synthetic */ void zzh(zzgl zzglVar, long j) {
        zzglVar.zze |= 1;
        zzglVar.zzf = j;
    }

    static /* synthetic */ void zzi(zzgl zzglVar, String str) {
        str.getClass();
        zzglVar.zze |= 2;
        zzglVar.zzg = str;
    }

    static /* synthetic */ void zzj(zzgl zzglVar, String str) {
        str.getClass();
        zzglVar.zze |= 4;
        zzglVar.zzh = str;
    }

    static /* synthetic */ void zzk(zzgl zzglVar) {
        zzglVar.zze &= -5;
        zzglVar.zzh = zza.zzh;
    }

    static /* synthetic */ void zzm(zzgl zzglVar, long j) {
        zzglVar.zze |= 8;
        zzglVar.zzi = j;
    }

    static /* synthetic */ void zzn(zzgl zzglVar) {
        zzglVar.zze &= -9;
        zzglVar.zzi = 0L;
    }

    static /* synthetic */ void zzo(zzgl zzglVar, double d) {
        zzglVar.zze |= 32;
        zzglVar.zzk = d;
    }

    static /* synthetic */ void zzp(zzgl zzglVar) {
        zzglVar.zze &= -33;
        zzglVar.zzk = 0.0d;
    }

    public final double zza() {
        return this.zzk;
    }

    public final long zzb() {
        return this.zzi;
    }

    public final long zzc() {
        return this.zzf;
    }

    public final String zzf() {
        return this.zzg;
    }

    public final String zzg() {
        return this.zzh;
    }

    @Override // com.google.android.gms.internal.measurement.zzke
    protected final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        zzfj zzfjVar = null;
        switch (i2) {
            case 2:
                return zzbI(zza, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဂ\u0003\u0005ခ\u0004\u0006က\u0005", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
            case 3:
                return new zzgl();
            case 4:
                return new zzgk(zzfjVar);
            case 5:
                return zza;
            default:
                return null;
        }
    }

    public final boolean zzq() {
        return (this.zze & 32) != 0;
    }

    public final boolean zzr() {
        return (this.zze & 8) != 0;
    }

    public final boolean zzs() {
        return (this.zze & 1) != 0;
    }

    public final boolean zzt() {
        return (this.zze & 4) != 0;
    }
}
