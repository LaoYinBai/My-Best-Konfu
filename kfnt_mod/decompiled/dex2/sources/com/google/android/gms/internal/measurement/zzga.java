package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzga extends zzke implements zzlm {
    private static final zzga zza;
    private zzkl zze = zzbD();

    static {
        zzga zzgaVar = new zzga();
        zza = zzgaVar;
        zzke.zzbJ(zzga.class, zzgaVar);
    }

    private zzga() {
    }

    public static zzfz zza() {
        return (zzfz) zza.zzbx();
    }

    static /* synthetic */ void zze(zzga zzgaVar, zzgc zzgcVar) {
        zzgcVar.getClass();
        zzkl zzklVar = zzgaVar.zze;
        if (!zzklVar.zzc()) {
            zzgaVar.zze = zzke.zzbE(zzklVar);
        }
        zzgaVar.zze.add(zzgcVar);
    }

    public final zzgc zzc(int i) {
        return (zzgc) this.zze.get(0);
    }

    public final List zzd() {
        return this.zze;
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
                return zzbI(zza, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zze", zzgc.class});
            case 3:
                return new zzga();
            case 4:
                return new zzfz(zzfjVar);
            case 5:
                return zza;
            default:
                return null;
        }
    }
}
