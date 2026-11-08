package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzfw extends zzke implements zzlm {
    private static final zzfw zza;
    private int zze;
    private long zzh;
    private float zzi;
    private double zzj;
    private String zzf = "";
    private String zzg = "";
    private zzkl zzk = zzbD();

    static {
        zzfw zzfwVar = new zzfw();
        zza = zzfwVar;
        zzke.zzbJ(zzfw.class, zzfwVar);
    }

    private zzfw() {
    }

    public static zzfv zze() {
        return (zzfv) zza.zzbx();
    }

    static /* synthetic */ void zzj(zzfw zzfwVar, String str) {
        str.getClass();
        zzfwVar.zze |= 1;
        zzfwVar.zzf = str;
    }

    static /* synthetic */ void zzk(zzfw zzfwVar, String str) {
        str.getClass();
        zzfwVar.zze |= 2;
        zzfwVar.zzg = str;
    }

    static /* synthetic */ void zzm(zzfw zzfwVar) {
        zzfwVar.zze &= -3;
        zzfwVar.zzg = zza.zzg;
    }

    static /* synthetic */ void zzn(zzfw zzfwVar, long j) {
        zzfwVar.zze |= 4;
        zzfwVar.zzh = j;
    }

    static /* synthetic */ void zzo(zzfw zzfwVar) {
        zzfwVar.zze &= -5;
        zzfwVar.zzh = 0L;
    }

    static /* synthetic */ void zzp(zzfw zzfwVar, double d) {
        zzfwVar.zze |= 16;
        zzfwVar.zzj = d;
    }

    static /* synthetic */ void zzq(zzfw zzfwVar) {
        zzfwVar.zze &= -17;
        zzfwVar.zzj = 0.0d;
    }

    static /* synthetic */ void zzr(zzfw zzfwVar, zzfw zzfwVar2) {
        zzfwVar2.getClass();
        zzfwVar.zzz();
        zzfwVar.zzk.add(zzfwVar2);
    }

    static /* synthetic */ void zzs(zzfw zzfwVar, Iterable iterable) {
        zzfwVar.zzz();
        zzin.zzbt(iterable, zzfwVar.zzk);
    }

    private final void zzz() {
        zzkl zzklVar = this.zzk;
        if (zzklVar.zzc()) {
            return;
        }
        this.zzk = zzke.zzbE(zzklVar);
    }

    public final double zza() {
        return this.zzj;
    }

    public final float zzb() {
        return this.zzi;
    }

    public final int zzc() {
        return this.zzk.size();
    }

    public final long zzd() {
        return this.zzh;
    }

    public final String zzg() {
        return this.zzf;
    }

    public final String zzh() {
        return this.zzg;
    }

    public final List zzi() {
        return this.zzk;
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
                return zzbI(zza, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဂ\u0002\u0004ခ\u0003\u0005က\u0004\u0006\u001b", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", zzfw.class});
            case 3:
                return new zzfw();
            case 4:
                return new zzfv(zzfjVar);
            case 5:
                return zza;
            default:
                return null;
        }
    }

    public final boolean zzu() {
        return (this.zze & 16) != 0;
    }

    public final boolean zzv() {
        return (this.zze & 8) != 0;
    }

    public final boolean zzw() {
        return (this.zze & 4) != 0;
    }

    public final boolean zzx() {
        return (this.zze & 1) != 0;
    }

    public final boolean zzy() {
        return (this.zze & 2) != 0;
    }
}
