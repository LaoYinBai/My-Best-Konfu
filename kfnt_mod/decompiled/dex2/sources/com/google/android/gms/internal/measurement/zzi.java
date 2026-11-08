package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzi {
    public static zzap zza(zzgx zzgxVar) {
        if (zzgxVar == null) {
            return zzap.zzf;
        }
        switch (zzgxVar.zzj() - 1) {
            case 1:
                return zzgxVar.zzi() ? new zzat(zzgxVar.zzd()) : zzap.zzm;
            case 2:
                return zzgxVar.zzh() ? new zzah(Double.valueOf(zzgxVar.zza())) : new zzah(null);
            case 3:
                return zzgxVar.zzg() ? new zzaf(Boolean.valueOf(zzgxVar.zzf())) : new zzaf(null);
            case 4:
                List listZze = zzgxVar.zze();
                ArrayList arrayList = new ArrayList();
                Iterator it = listZze.iterator();
                while (it.hasNext()) {
                    arrayList.add(zza((zzgx) it.next()));
                }
                return new zzaq(zzgxVar.zzc(), arrayList);
            default:
                throw new IllegalArgumentException("Unknown type found. Cannot convert entity");
        }
    }

    public static zzap zzb(Object obj) {
        if (obj == null) {
            return zzap.zzg;
        }
        if (obj instanceof String) {
            return new zzat((String) obj);
        }
        if (obj instanceof Double) {
            return new zzah((Double) obj);
        }
        if (obj instanceof Long) {
            return new zzah(Double.valueOf(((Long) obj).doubleValue()));
        }
        if (obj instanceof Integer) {
            return new zzah(Double.valueOf(((Integer) obj).doubleValue()));
        }
        if (obj instanceof Boolean) {
            return new zzaf((Boolean) obj);
        }
        if (!(obj instanceof Map)) {
            if (!(obj instanceof List)) {
                throw new IllegalArgumentException("Invalid value type");
            }
            zzae zzaeVar = new zzae();
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                zzaeVar.zzq(zzaeVar.zzc(), zzb(it.next()));
            }
            return zzaeVar;
        }
        zzam zzamVar = new zzam();
        Map map = (Map) obj;
        for (Object string : map.keySet()) {
            zzap zzapVarZzb = zzb(map.get(string));
            if (string != null) {
                if (!(string instanceof String)) {
                    string = string.toString();
                }
                zzamVar.zzr((String) string, zzapVarZzb);
            }
        }
        return zzamVar;
    }
}
