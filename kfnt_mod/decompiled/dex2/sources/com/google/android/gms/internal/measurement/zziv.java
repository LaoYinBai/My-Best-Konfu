package com.google.android.gms.internal.measurement;

import com.liulishuo.filedownloader.model.FileDownloadStatus;
import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zziv implements Comparator {
    zziv() {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzjd zzjdVar = (zzjd) obj;
        zzjd zzjdVar2 = (zzjd) obj2;
        zziu zziuVar = new zziu(zzjdVar);
        zziu zziuVar2 = new zziu(zzjdVar2);
        while (zziuVar.hasNext() && zziuVar2.hasNext()) {
            int iCompareTo = Integer.valueOf(zziuVar.zza() & FileDownloadStatus.error).compareTo(Integer.valueOf(zziuVar2.zza() & FileDownloadStatus.error));
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return Integer.valueOf(zzjdVar.zzd()).compareTo(Integer.valueOf(zzjdVar2.zzd()));
    }
}
