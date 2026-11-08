package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.huawei.hms.framework.common.ContainerUtils;
import java.util.EnumMap;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzai {
    public static final zzai zza = new zzai(null, null);
    private final EnumMap zzb = new EnumMap(zzah.class);

    public zzai(Boolean bool, Boolean bool2) {
        this.zzb.put((EnumMap) zzah.AD_STORAGE, (zzah) bool);
        this.zzb.put((EnumMap) zzah.ANALYTICS_STORAGE, (zzah) bool2);
    }

    public static zzai zza(Bundle bundle) {
        if (bundle == null) {
            return zza;
        }
        EnumMap enumMap = new EnumMap(zzah.class);
        for (zzah zzahVar : zzah.values()) {
            enumMap.put((EnumMap) zzahVar, (zzah) zzn(bundle.getString(zzahVar.zzd)));
        }
        return new zzai(enumMap);
    }

    public static zzai zzb(String str) {
        EnumMap enumMap = new EnumMap(zzah.class);
        if (str != null) {
            int i = 0;
            while (true) {
                int length = zzah.zzc.length;
                if (i < 2) {
                    zzah zzahVar = zzah.zzc[i];
                    int i2 = i + 2;
                    if (i2 < str.length()) {
                        char cCharAt = str.charAt(i2);
                        Boolean bool = null;
                        if (cCharAt != '-') {
                            switch (cCharAt) {
                                case '0':
                                    bool = Boolean.FALSE;
                                    break;
                                case '1':
                                    bool = Boolean.TRUE;
                                    break;
                            }
                        }
                        enumMap.put((EnumMap) zzahVar, (zzah) bool);
                    }
                    i++;
                }
            }
        }
        return new zzai(enumMap);
    }

    public static String zzg(Bundle bundle) {
        String string;
        for (zzah zzahVar : zzah.values()) {
            if (bundle.containsKey(zzahVar.zzd) && (string = bundle.getString(zzahVar.zzd)) != null && zzn(string) == null) {
                return string;
            }
        }
        return null;
    }

    public static boolean zzj(int i, int i2) {
        return i <= i2;
    }

    static final int zzm(Boolean bool) {
        if (bool == null) {
            return 0;
        }
        return bool.booleanValue() ? 1 : 2;
    }

    private static Boolean zzn(String str) {
        if (str == null) {
            return null;
        }
        if (str.equals("granted")) {
            return Boolean.TRUE;
        }
        if (str.equals("denied")) {
            return Boolean.FALSE;
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzai)) {
            return false;
        }
        zzai zzaiVar = (zzai) obj;
        for (zzah zzahVar : zzah.values()) {
            if (zzm((Boolean) this.zzb.get(zzahVar)) != zzm((Boolean) zzaiVar.zzb.get(zzahVar))) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        Iterator it = this.zzb.values().iterator();
        int iZzm = 17;
        while (it.hasNext()) {
            iZzm = (iZzm * 31) + zzm((Boolean) it.next());
        }
        return iZzm;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("settings: ");
        zzah[] zzahVarArrValues = zzah.values();
        int length = zzahVarArrValues.length;
        for (int i = 0; i < length; i++) {
            zzah zzahVar = zzahVarArrValues[i];
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(zzahVar.name());
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            Boolean bool = (Boolean) this.zzb.get(zzahVar);
            if (bool == null) {
                sb.append("uninitialized");
            } else {
                sb.append(true != bool.booleanValue() ? "denied" : "granted");
            }
        }
        return sb.toString();
    }

    public final zzai zzc(zzai zzaiVar) {
        EnumMap enumMap = new EnumMap(zzah.class);
        for (zzah zzahVar : zzah.values()) {
            Boolean boolValueOf = (Boolean) this.zzb.get(zzahVar);
            Boolean bool = (Boolean) zzaiVar.zzb.get(zzahVar);
            if (boolValueOf == null) {
                boolValueOf = bool;
            } else if (bool != null) {
                boolValueOf = Boolean.valueOf(boolValueOf.booleanValue() && bool.booleanValue());
            }
            enumMap.put((EnumMap) zzahVar, (zzah) boolValueOf);
        }
        return new zzai(enumMap);
    }

    public final zzai zzd(zzai zzaiVar) {
        EnumMap enumMap = new EnumMap(zzah.class);
        for (zzah zzahVar : zzah.values()) {
            Boolean bool = (Boolean) this.zzb.get(zzahVar);
            if (bool == null) {
                bool = (Boolean) zzaiVar.zzb.get(zzahVar);
            }
            enumMap.put((EnumMap) zzahVar, (zzah) bool);
        }
        return new zzai(enumMap);
    }

    public final Boolean zze() {
        return (Boolean) this.zzb.get(zzah.AD_STORAGE);
    }

    public final Boolean zzf() {
        return (Boolean) this.zzb.get(zzah.ANALYTICS_STORAGE);
    }

    public final String zzh() {
        StringBuilder sb = new StringBuilder("G1");
        zzah[] zzahVarArr = zzah.zzc;
        int length = zzahVarArr.length;
        for (int i = 0; i < 2; i++) {
            Boolean bool = (Boolean) this.zzb.get(zzahVarArr[i]);
            sb.append(bool == null ? '-' : bool.booleanValue() ? '1' : '0');
        }
        return sb.toString();
    }

    public final boolean zzi(zzah zzahVar) {
        Boolean bool = (Boolean) this.zzb.get(zzahVar);
        return bool == null || bool.booleanValue();
    }

    public final boolean zzk(zzai zzaiVar) {
        return zzl(zzaiVar, (zzah[]) this.zzb.keySet().toArray(new zzah[0]));
    }

    public final boolean zzl(zzai zzaiVar, zzah... zzahVarArr) {
        for (zzah zzahVar : zzahVarArr) {
            Boolean bool = (Boolean) this.zzb.get(zzahVar);
            Boolean bool2 = (Boolean) zzaiVar.zzb.get(zzahVar);
            if (bool == Boolean.FALSE && bool2 != Boolean.FALSE) {
                return true;
            }
        }
        return false;
    }

    public zzai(EnumMap enumMap) {
        this.zzb.putAll(enumMap);
    }
}
