package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzgh extends zzke implements zzlm {
    private static final zzgh zza;
    private zzkk zze = zzbB();
    private zzkk zzf = zzbB();
    private zzkl zzg = zzbD();
    private zzkl zzh = zzbD();

    static {
        zzgh zzghVar = new zzgh();
        zza = zzghVar;
        zzke.zzbJ(zzgh.class, zzghVar);
    }

    private zzgh() {
    }

    public static zzgg zzf() {
        return (zzgg) zza.zzbx();
    }

    public static zzgh zzh() {
        return zza;
    }

    static /* synthetic */ void zzo(zzgh zzghVar, Iterable iterable) {
        zzkk zzkkVar = zzghVar.zze;
        if (!zzkkVar.zzc()) {
            zzghVar.zze = zzke.zzbC(zzkkVar);
        }
        zzin.zzbt(iterable, zzghVar.zze);
    }

    static /* synthetic */ void zzq(zzgh zzghVar, Iterable iterable) {
        zzkk zzkkVar = zzghVar.zzf;
        if (!zzkkVar.zzc()) {
            zzghVar.zzf = zzke.zzbC(zzkkVar);
        }
        zzin.zzbt(iterable, zzghVar.zzf);
    }

    static /* synthetic */ void zzs(zzgh zzghVar, Iterable iterable) {
        zzghVar.zzy();
        zzin.zzbt(iterable, zzghVar.zzg);
    }

    static /* synthetic */ void zzu(zzgh zzghVar, int i) {
        zzghVar.zzy();
        zzghVar.zzg.remove(i);
    }

    static /* synthetic */ void zzv(zzgh zzghVar, Iterable iterable) {
        zzghVar.zzz();
        zzin.zzbt(iterable, zzghVar.zzh);
    }

    static /* synthetic */ void zzx(zzgh zzghVar, int i) {
        zzghVar.zzz();
        zzghVar.zzh.remove(i);
    }

    private final void zzy() {
        zzkl zzklVar = this.zzg;
        if (zzklVar.zzc()) {
            return;
        }
        this.zzg = zzke.zzbE(zzklVar);
    }

    private final void zzz() {
        zzkl zzklVar = this.zzh;
        if (zzklVar.zzc()) {
            return;
        }
        this.zzh = zzke.zzbE(zzklVar);
    }

    public final int zza() {
        return this.zzg.size();
    }

    public final int zzb() {
        return this.zzf.size();
    }

    public final int zzc() {
        return this.zzh.size();
    }

    public final int zzd() {
        return this.zze.size();
    }

    public final zzfq zze(int i) {
        return (zzfq) this.zzg.get(i);
    }

    public final zzgj zzi(int i) {
        return (zzgj) this.zzh.get(i);
    }

    public final List zzj() {
        return this.zzg;
    }

    public final List zzk() {
        return this.zzf;
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
                return zzbI(zza, "\u0001\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0004\u0000\u0001\u0015\u0002\u0015\u0003\u001b\u0004\u001b", new Object[]{"zze", "zzf", "zzg", zzfq.class, "zzh", zzgj.class});
            case 3:
                return new zzgh();
            case 4:
                return new zzgg(zzfjVar);
            case 5:
                return zza;
            default:
                return null;
        }
    }

    public final List zzm() {
        return this.zzh;
    }

    public final List zzn() {
        return this.zze;
    }
}
