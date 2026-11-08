package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzfg extends zzke implements zzlm {
    private static final zzfg zza;
    private int zze;
    private String zzf = "";
    private String zzg = "";

    static {
        zzfg zzfgVar = new zzfg();
        zza = zzfgVar;
        zzke.zzbJ(zzfg.class, zzfgVar);
    }

    private zzfg() {
    }

    @Override // com.google.android.gms.internal.measurement.zzke
    protected final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        zzey zzeyVar = null;
        switch (i2) {
            case 2:
                return zzbI(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zze", "zzf", "zzg"});
            case 3:
                return new zzfg();
            case 4:
                return new zzff(zzeyVar);
            case 5:
                return zza;
            default:
                return null;
        }
    }
}
