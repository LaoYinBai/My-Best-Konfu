package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzgf extends zzke implements zzlm {
    private static final zzgf zza;
    private int zze;
    private int zzf = 1;
    private zzkl zzg = zzbD();

    static {
        zzgf zzgfVar = new zzgf();
        zza = zzgfVar;
        zzke.zzbJ(zzgf.class, zzgfVar);
    }

    private zzgf() {
    }

    public static zzgd zza() {
        return (zzgd) zza.zzbx();
    }

    static /* synthetic */ void zzc(zzgf zzgfVar, zzfu zzfuVar) {
        zzfuVar.getClass();
        zzkl zzklVar = zzgfVar.zzg;
        if (!zzklVar.zzc()) {
            zzgfVar.zzg = zzke.zzbE(zzklVar);
        }
        zzgfVar.zzg.add(zzfuVar);
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
                return zzbI(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဌ\u0000\u0002\u001b", new Object[]{"zze", "zzf", zzge.zza, "zzg", zzfu.class});
            case 3:
                return new zzgf();
            case 4:
                return new zzgd(zzfjVar);
            case 5:
                return zza;
            default:
                return null;
        }
    }
}
