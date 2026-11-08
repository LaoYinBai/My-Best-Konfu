package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzgs extends zzke implements zzlm {
    private static final zzgs zza;
    private int zze;
    private zzkl zzf = zzbD();
    private zzgo zzg;

    static {
        zzgs zzgsVar = new zzgs();
        zza = zzgsVar;
        zzke.zzbJ(zzgs.class, zzgsVar);
    }

    private zzgs() {
    }

    public final zzgo zza() {
        zzgo zzgoVar = this.zzg;
        return zzgoVar == null ? zzgo.zzc() : zzgoVar;
    }

    public final List zzc() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzke
    protected final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        zzgm zzgmVar = null;
        switch (i2) {
            case 2:
                return zzbI(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002ဉ\u0000", new Object[]{"zze", "zzf", zzgx.class, "zzg"});
            case 3:
                return new zzgs();
            case 4:
                return new zzgr(zzgmVar);
            case 5:
                return zza;
            default:
                return null;
        }
    }
}
