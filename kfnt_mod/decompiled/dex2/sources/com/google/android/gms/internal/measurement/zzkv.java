package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zzkv extends zzkz {
    private static final Class zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzkv() {
        super(null);
    }

    /* synthetic */ zzkv(zzku zzkuVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzkz
    final void zza(Object obj, long j) {
        Object objUnmodifiableList;
        List list = (List) zzmx.zzf(obj, j);
        if (list instanceof zzkt) {
            objUnmodifiableList = ((zzkt) list).zze();
        } else {
            if (zza.isAssignableFrom(list.getClass())) {
                return;
            }
            if ((list instanceof zzls) && (list instanceof zzkl)) {
                zzkl zzklVar = (zzkl) list;
                if (zzklVar.zzc()) {
                    zzklVar.zzb();
                    return;
                }
                return;
            }
            objUnmodifiableList = Collections.unmodifiableList(list);
        }
        zzmx.zzs(obj, j, objUnmodifiableList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.measurement.zzkz
    final void zzb(Object obj, Object obj2, long j) {
        zzks zzksVar;
        List list = (List) zzmx.zzf(obj2, j);
        int size = list.size();
        List zzksVar2 = (List) zzmx.zzf(obj, j);
        if (zzksVar2.isEmpty()) {
            zzksVar2 = zzksVar2 instanceof zzkt ? new zzks(size) : ((zzksVar2 instanceof zzls) && (zzksVar2 instanceof zzkl)) ? ((zzkl) zzksVar2).zzd(size) : new ArrayList(size);
            zzmx.zzs(obj, j, zzksVar2);
        } else {
            if (zza.isAssignableFrom(zzksVar2.getClass())) {
                ArrayList arrayList = new ArrayList(zzksVar2.size() + size);
                arrayList.addAll(zzksVar2);
                zzmx.zzs(obj, j, arrayList);
                zzksVar = arrayList;
            } else if (zzksVar2 instanceof zzms) {
                zzks zzksVar3 = new zzks(zzksVar2.size() + size);
                zzksVar3.addAll(zzksVar3.size(), (zzms) zzksVar2);
                zzmx.zzs(obj, j, zzksVar3);
                zzksVar = zzksVar3;
            } else if ((zzksVar2 instanceof zzls) && (zzksVar2 instanceof zzkl)) {
                zzkl zzklVar = (zzkl) zzksVar2;
                if (!zzklVar.zzc()) {
                    zzkl zzklVarZzd = zzklVar.zzd(zzksVar2.size() + size);
                    zzmx.zzs(obj, j, zzklVarZzd);
                    zzksVar2 = zzklVarZzd;
                }
            }
            zzksVar2 = zzksVar;
        }
        int size2 = zzksVar2.size();
        int size3 = list.size();
        if (size2 > 0 && size3 > 0) {
            zzksVar2.addAll(list);
        }
        if (size2 > 0) {
            list = zzksVar2;
        }
        zzmx.zzs(obj, j, list);
    }
}
