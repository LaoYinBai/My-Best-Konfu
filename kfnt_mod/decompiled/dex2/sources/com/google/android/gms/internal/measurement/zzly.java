package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zzly {
    private static final Class zza;
    private static final zzmn zzb;
    private static final zzmn zzc;
    private static final zzmn zzd;

    static {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        zza = cls;
        zzb = zzab(false);
        zzc = zzab(true);
        zzd = new zzmp();
    }

    public static zzmn zzA() {
        return zzc;
    }

    public static zzmn zzB() {
        return zzd;
    }

    static Object zzC(int i, List list, zzki zzkiVar, Object obj, zzmn zzmnVar) {
        if (zzkiVar == null) {
            return obj;
        }
        if (!(list instanceof RandomAccess)) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int iIntValue = ((Integer) it.next()).intValue();
                if (!zzkiVar.zza(iIntValue)) {
                    obj = zzD(i, iIntValue, obj, zzmnVar);
                    it.remove();
                }
            }
            return obj;
        }
        int size = list.size();
        Object objZzD = obj;
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            int iIntValue2 = ((Integer) list.get(i3)).intValue();
            if (zzkiVar.zza(iIntValue2)) {
                if (i3 != i2) {
                    list.set(i2, Integer.valueOf(iIntValue2));
                }
                i2++;
            } else {
                objZzD = zzD(i, iIntValue2, objZzD, zzmnVar);
            }
        }
        if (i2 == size) {
            return objZzD;
        }
        list.subList(i2, size).clear();
        return objZzD;
    }

    static Object zzD(int i, int i2, Object obj, zzmn zzmnVar) {
        if (obj == null) {
            obj = zzmnVar.zze();
        }
        zzmnVar.zzf(obj, i, i2);
        return obj;
    }

    static void zzE(zzjr zzjrVar, Object obj, Object obj2) {
        zzjrVar.zza(obj2);
        throw null;
    }

    static void zzF(zzmn zzmnVar, Object obj, Object obj2) {
        zzmnVar.zzh(obj, zzmnVar.zzd(zzmnVar.zzc(obj), zzmnVar.zzc(obj2)));
    }

    public static void zzG(Class cls) {
        Class cls2;
        if (!zzke.class.isAssignableFrom(cls) && (cls2 = zza) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzH(int i, List list, zznf zznfVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznfVar.zzc(i, list, z);
    }

    public static void zzI(int i, List list, zznf zznfVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznfVar.zze(i, list);
    }

    public static void zzJ(int i, List list, zznf zznfVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznfVar.zzg(i, list, z);
    }

    public static void zzK(int i, List list, zznf zznfVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznfVar.zzj(i, list, z);
    }

    public static void zzL(int i, List list, zznf zznfVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznfVar.zzl(i, list, z);
    }

    public static void zzM(int i, List list, zznf zznfVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznfVar.zzn(i, list, z);
    }

    public static void zzN(int i, List list, zznf zznfVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznfVar.zzp(i, list, z);
    }

    public static void zzO(int i, List list, zznf zznfVar, zzlw zzlwVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ((zzjm) zznfVar).zzq(i, list.get(i2), zzlwVar);
        }
    }

    public static void zzP(int i, List list, zznf zznfVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznfVar.zzs(i, list, z);
    }

    public static void zzQ(int i, List list, zznf zznfVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznfVar.zzu(i, list, z);
    }

    public static void zzR(int i, List list, zznf zznfVar, zzlw zzlwVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ((zzjm) zznfVar).zzv(i, list.get(i2), zzlwVar);
        }
    }

    public static void zzS(int i, List list, zznf zznfVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznfVar.zzx(i, list, z);
    }

    public static void zzT(int i, List list, zznf zznfVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznfVar.zzz(i, list, z);
    }

    public static void zzU(int i, List list, zznf zznfVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznfVar.zzB(i, list, z);
    }

    public static void zzV(int i, List list, zznf zznfVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznfVar.zzD(i, list, z);
    }

    public static void zzW(int i, List list, zznf zznfVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznfVar.zzG(i, list);
    }

    public static void zzX(int i, List list, zznf zznfVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznfVar.zzI(i, list, z);
    }

    public static void zzY(int i, List list, zznf zznfVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznfVar.zzK(i, list, z);
    }

    static boolean zzZ(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    static int zza(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzjl.zzA(i << 3) + 1);
    }

    static void zzaa(zzlg zzlgVar, Object obj, Object obj2, long j) {
        zzmx.zzs(obj, j, zzlg.zzb(zzmx.zzf(obj, j), zzmx.zzf(obj2, j)));
    }

    private static zzmn zzab(boolean z) {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            cls = null;
        }
        if (cls == null) {
            return null;
        }
        try {
            return (zzmn) cls.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused2) {
            return null;
        }
    }

    static int zzb(List list) {
        return list.size();
    }

    static int zzc(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzz = size * zzjl.zzz(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            iZzz += zzjl.zzt((zzjd) list.get(i2));
        }
        return iZzz;
    }

    static int zzd(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzjl.zzz(i));
    }

    static int zze(List list) {
        int iZzv;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkf) {
            zzkf zzkfVar = (zzkf) list;
            iZzv = 0;
            while (i < size) {
                iZzv += zzjl.zzv(zzkfVar.zze(i));
                i++;
            }
        } else {
            iZzv = 0;
            while (i < size) {
                iZzv += zzjl.zzv(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return iZzv;
    }

    static int zzf(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzjl.zzA(i << 3) + 4);
    }

    static int zzg(List list) {
        return list.size() * 4;
    }

    static int zzh(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzjl.zzA(i << 3) + 8);
    }

    static int zzi(List list) {
        return list.size() * 8;
    }

    static int zzj(int i, List list, zzlw zzlwVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzu = 0;
        for (int i2 = 0; i2 < size; i2++) {
            iZzu += zzjl.zzu(i, (zzll) list.get(i2), zzlwVar);
        }
        return iZzu;
    }

    static int zzk(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzl(list) + (size * zzjl.zzz(i));
    }

    static int zzl(List list) {
        int iZzv;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkf) {
            zzkf zzkfVar = (zzkf) list;
            iZzv = 0;
            while (i < size) {
                iZzv += zzjl.zzv(zzkfVar.zze(i));
                i++;
            }
        } else {
            iZzv = 0;
            while (i < size) {
                iZzv += zzjl.zzv(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return iZzv;
    }

    static int zzm(int i, List list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzn(list) + (list.size() * zzjl.zzz(i));
    }

    static int zzn(List list) {
        int iZzB;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzla) {
            zzla zzlaVar = (zzla) list;
            iZzB = 0;
            while (i < size) {
                iZzB += zzjl.zzB(zzlaVar.zza(i));
                i++;
            }
        } else {
            iZzB = 0;
            while (i < size) {
                iZzB += zzjl.zzB(((Long) list.get(i)).longValue());
                i++;
            }
        }
        return iZzB;
    }

    static int zzo(int i, Object obj, zzlw zzlwVar) {
        if (!(obj instanceof zzkr)) {
            return zzjl.zzA(i << 3) + zzjl.zzx((zzll) obj, zzlwVar);
        }
        int iZzA = zzjl.zzA(i << 3);
        int iZza = ((zzkr) obj).zza();
        return iZzA + zzjl.zzA(iZza) + iZza;
    }

    static int zzp(int i, List list, zzlw zzlwVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzz = zzjl.zzz(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            iZzz += obj instanceof zzkr ? zzjl.zzw((zzkr) obj) : zzjl.zzx((zzll) obj, zzlwVar);
        }
        return iZzz;
    }

    static int zzq(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzr(list) + (size * zzjl.zzz(i));
    }

    static int zzr(List list) {
        int iZzA;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkf) {
            zzkf zzkfVar = (zzkf) list;
            iZzA = 0;
            while (i < size) {
                int iZze = zzkfVar.zze(i);
                iZzA += zzjl.zzA((iZze >> 31) ^ (iZze + iZze));
                i++;
            }
        } else {
            iZzA = 0;
            while (i < size) {
                int iIntValue = ((Integer) list.get(i)).intValue();
                iZzA += zzjl.zzA((iIntValue >> 31) ^ (iIntValue + iIntValue));
                i++;
            }
        }
        return iZzA;
    }

    static int zzs(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzt(list) + (size * zzjl.zzz(i));
    }

    static int zzt(List list) {
        int iZzB;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzla) {
            zzla zzlaVar = (zzla) list;
            iZzB = 0;
            while (i < size) {
                long jZza = zzlaVar.zza(i);
                iZzB += zzjl.zzB((jZza >> 63) ^ (jZza + jZza));
                i++;
            }
        } else {
            iZzB = 0;
            while (i < size) {
                long jLongValue = ((Long) list.get(i)).longValue();
                iZzB += zzjl.zzB((jLongValue >> 63) ^ (jLongValue + jLongValue));
                i++;
            }
        }
        return iZzB;
    }

    static int zzu(int i, List list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int iZzz = zzjl.zzz(i) * size;
        if (list instanceof zzkt) {
            zzkt zzktVar = (zzkt) list;
            while (i2 < size) {
                Object objZzf = zzktVar.zzf(i2);
                iZzz += objZzf instanceof zzjd ? zzjl.zzt((zzjd) objZzf) : zzjl.zzy((String) objZzf);
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                iZzz += obj instanceof zzjd ? zzjl.zzt((zzjd) obj) : zzjl.zzy((String) obj);
                i2++;
            }
        }
        return iZzz;
    }

    static int zzv(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzw(list) + (size * zzjl.zzz(i));
    }

    static int zzw(List list) {
        int iZzA;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkf) {
            zzkf zzkfVar = (zzkf) list;
            iZzA = 0;
            while (i < size) {
                iZzA += zzjl.zzA(zzkfVar.zze(i));
                i++;
            }
        } else {
            iZzA = 0;
            while (i < size) {
                iZzA += zzjl.zzA(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return iZzA;
    }

    static int zzx(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzy(list) + (size * zzjl.zzz(i));
    }

    static int zzy(List list) {
        int iZzB;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzla) {
            zzla zzlaVar = (zzla) list;
            iZzB = 0;
            while (i < size) {
                iZzB += zzjl.zzB(zzlaVar.zza(i));
                i++;
            }
        } else {
            iZzB = 0;
            while (i < size) {
                iZzB += zzjl.zzB(((Long) list.get(i)).longValue());
                i++;
            }
        }
        return iZzB;
    }

    public static zzmn zzz() {
        return zzb;
    }
}
