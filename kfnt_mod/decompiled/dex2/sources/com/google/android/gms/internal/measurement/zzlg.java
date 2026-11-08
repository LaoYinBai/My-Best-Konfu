package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zzlg {
    zzlg() {
    }

    public static final int zza(int i, Object obj, Object obj2) {
        zzlf zzlfVar = (zzlf) obj;
        if (zzlfVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzlfVar.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw null;
    }

    public static final Object zzb(Object obj, Object obj2) {
        zzlf zzlfVarZzb = (zzlf) obj;
        zzlf zzlfVar = (zzlf) obj2;
        if (!zzlfVar.isEmpty()) {
            if (!zzlfVarZzb.zze()) {
                zzlfVarZzb = zzlfVarZzb.zzb();
            }
            zzlfVarZzb.zzd(zzlfVar);
        }
        return zzlfVarZzb;
    }
}
