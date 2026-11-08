package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zzlo<T> implements zzlw<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzmx.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzll zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final int[] zzj;
    private final int zzk;
    private final int zzl;
    private final zzkz zzm;
    private final zzmn zzn;
    private final zzjr zzo;
    private final zzlq zzp;
    private final zzlg zzq;

    private zzlo(int[] iArr, Object[] objArr, int i, int i2, zzll zzllVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzlq zzlqVar, zzkz zzkzVar, zzmn zzmnVar, zzjr zzjrVar, zzlg zzlgVar, byte[] bArr) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzi = z;
        boolean z3 = false;
        if (zzjrVar != null && zzjrVar.zzc(zzllVar)) {
            z3 = true;
        }
        this.zzh = z3;
        this.zzj = iArr2;
        this.zzk = i3;
        this.zzl = i4;
        this.zzp = zzlqVar;
        this.zzm = zzkzVar;
        this.zzn = zzmnVar;
        this.zzo = zzjrVar;
        this.zzg = zzllVar;
        this.zzq = zzlgVar;
    }

    private static int zzA(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzB(int i) {
        return this.zzc[i + 1];
    }

    private static long zzC(Object obj, long j) {
        return ((Long) zzmx.zzf(obj, j)).longValue();
    }

    private final zzki zzD(int i) {
        int i2 = i / 3;
        return (zzki) this.zzd[i2 + i2 + 1];
    }

    private final zzlw zzE(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzlw zzlwVar = (zzlw) this.zzd[i3];
        if (zzlwVar != null) {
            return zzlwVar;
        }
        zzlw zzlwVarZzb = zzlt.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzlwVarZzb;
        return zzlwVarZzb;
    }

    private final Object zzF(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private static Field zzG(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private final void zzH(Object obj, Object obj2, int i) {
        long jZzB = zzB(i) & 1048575;
        if (zzO(obj2, i)) {
            Object objZzf = zzmx.zzf(obj, jZzB);
            Object objZzf2 = zzmx.zzf(obj2, jZzB);
            if (objZzf != null && objZzf2 != null) {
                zzmx.zzs(obj, jZzB, zzkm.zzg(objZzf, objZzf2));
                zzJ(obj, i);
            } else if (objZzf2 != null) {
                zzmx.zzs(obj, jZzB, objZzf2);
                zzJ(obj, i);
            }
        }
    }

    private final void zzI(Object obj, Object obj2, int i) {
        int iZzB = zzB(i);
        int i2 = this.zzc[i];
        long j = iZzB & 1048575;
        if (zzR(obj2, i2, i)) {
            Object objZzf = zzR(obj, i2, i) ? zzmx.zzf(obj, j) : null;
            Object objZzf2 = zzmx.zzf(obj2, j);
            if (objZzf != null && objZzf2 != null) {
                zzmx.zzs(obj, j, zzkm.zzg(objZzf, objZzf2));
                zzK(obj, i2, i);
            } else if (objZzf2 != null) {
                zzmx.zzs(obj, j, objZzf2);
                zzK(obj, i2, i);
            }
        }
    }

    private final void zzJ(Object obj, int i) {
        int iZzy = zzy(i);
        long j = 1048575 & iZzy;
        if (j == 1048575) {
            return;
        }
        zzmx.zzq(obj, j, (1 << (iZzy >>> 20)) | zzmx.zzc(obj, j));
    }

    private final void zzK(Object obj, int i, int i2) {
        zzmx.zzq(obj, zzy(i2) & 1048575, i);
    }

    private final void zzL(Object obj, zznf zznfVar) throws IOException {
        int i;
        int i2;
        if (this.zzh) {
            this.zzo.zza(obj);
            throw null;
        }
        int length = this.zzc.length;
        Unsafe unsafe = zzb;
        int i3 = 0;
        int i4 = 1048575;
        int i5 = 0;
        while (i3 < length) {
            int iZzB = zzB(i3);
            int[] iArr = this.zzc;
            int i6 = iArr[i3];
            int iZzA = zzA(iZzB);
            if (iZzA <= 17) {
                int i7 = iArr[i3 + 2];
                i = i7 & 1048575;
                if (i != i4) {
                    i5 = unsafe.getInt(obj, i);
                } else {
                    i = i4;
                }
                i2 = 1 << (i7 >>> 20);
            } else {
                i = i4;
                i2 = 0;
            }
            long j = iZzB & 1048575;
            switch (iZzA) {
                case 0:
                    if ((i2 & i5) == 0) {
                        break;
                    } else {
                        zznfVar.zzf(i6, zzmx.zza(obj, j));
                        break;
                    }
                case 1:
                    if ((i2 & i5) == 0) {
                        break;
                    } else {
                        zznfVar.zzo(i6, zzmx.zzb(obj, j));
                        break;
                    }
                case 2:
                    if ((i2 & i5) == 0) {
                        break;
                    } else {
                        zznfVar.zzt(i6, unsafe.getLong(obj, j));
                        break;
                    }
                case 3:
                    if ((i2 & i5) == 0) {
                        break;
                    } else {
                        zznfVar.zzJ(i6, unsafe.getLong(obj, j));
                        break;
                    }
                case 4:
                    if ((i2 & i5) == 0) {
                        break;
                    } else {
                        zznfVar.zzr(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 5:
                    if ((i2 & i5) == 0) {
                        break;
                    } else {
                        zznfVar.zzm(i6, unsafe.getLong(obj, j));
                        break;
                    }
                case 6:
                    if ((i2 & i5) == 0) {
                        break;
                    } else {
                        zznfVar.zzk(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 7:
                    if ((i2 & i5) == 0) {
                        break;
                    } else {
                        zznfVar.zzb(i6, zzmx.zzw(obj, j));
                        break;
                    }
                case 8:
                    if ((i2 & i5) == 0) {
                        break;
                    } else {
                        zzT(i6, unsafe.getObject(obj, j), zznfVar);
                        break;
                    }
                case 9:
                    if ((i2 & i5) == 0) {
                        break;
                    } else {
                        zznfVar.zzv(i6, unsafe.getObject(obj, j), zzE(i3));
                        break;
                    }
                case 10:
                    if ((i2 & i5) == 0) {
                        break;
                    } else {
                        zznfVar.zzd(i6, (zzjd) unsafe.getObject(obj, j));
                        break;
                    }
                case 11:
                    if ((i2 & i5) == 0) {
                        break;
                    } else {
                        zznfVar.zzH(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 12:
                    if ((i2 & i5) == 0) {
                        break;
                    } else {
                        zznfVar.zzi(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 13:
                    if ((i2 & i5) == 0) {
                        break;
                    } else {
                        zznfVar.zzw(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 14:
                    if ((i2 & i5) == 0) {
                        break;
                    } else {
                        zznfVar.zzy(i6, unsafe.getLong(obj, j));
                        break;
                    }
                case 15:
                    if ((i2 & i5) == 0) {
                        break;
                    } else {
                        zznfVar.zzA(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 16:
                    if ((i2 & i5) == 0) {
                        break;
                    } else {
                        zznfVar.zzC(i6, unsafe.getLong(obj, j));
                        break;
                    }
                case 17:
                    if ((i2 & i5) == 0) {
                        break;
                    } else {
                        zznfVar.zzq(i6, unsafe.getObject(obj, j), zzE(i3));
                        break;
                    }
                case 18:
                    zzly.zzJ(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, false);
                    break;
                case 19:
                    zzly.zzN(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, false);
                    break;
                case 20:
                    zzly.zzQ(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, false);
                    break;
                case 21:
                    zzly.zzY(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, false);
                    break;
                case 22:
                    zzly.zzP(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, false);
                    break;
                case 23:
                    zzly.zzM(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, false);
                    break;
                case 24:
                    zzly.zzL(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, false);
                    break;
                case 25:
                    zzly.zzH(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, false);
                    break;
                case 26:
                    zzly.zzW(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar);
                    break;
                case 27:
                    zzly.zzR(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, zzE(i3));
                    break;
                case 28:
                    zzly.zzI(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar);
                    break;
                case 29:
                    zzly.zzX(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, false);
                    break;
                case 30:
                    zzly.zzK(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, false);
                    break;
                case 31:
                    zzly.zzS(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, false);
                    break;
                case 32:
                    zzly.zzT(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, false);
                    break;
                case 33:
                    zzly.zzU(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, false);
                    break;
                case 34:
                    zzly.zzV(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, false);
                    break;
                case 35:
                    zzly.zzJ(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, true);
                    break;
                case 36:
                    zzly.zzN(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, true);
                    break;
                case 37:
                    zzly.zzQ(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, true);
                    break;
                case 38:
                    zzly.zzY(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, true);
                    break;
                case 39:
                    zzly.zzP(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, true);
                    break;
                case 40:
                    zzly.zzM(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, true);
                    break;
                case 41:
                    zzly.zzL(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, true);
                    break;
                case 42:
                    zzly.zzH(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, true);
                    break;
                case 43:
                    zzly.zzX(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, true);
                    break;
                case 44:
                    zzly.zzK(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, true);
                    break;
                case 45:
                    zzly.zzS(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, true);
                    break;
                case 46:
                    zzly.zzT(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, true);
                    break;
                case 47:
                    zzly.zzU(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, true);
                    break;
                case 48:
                    zzly.zzV(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, true);
                    break;
                case 49:
                    zzly.zzO(this.zzc[i3], (List) unsafe.getObject(obj, j), zznfVar, zzE(i3));
                    break;
                case 50:
                    zzM(zznfVar, i6, unsafe.getObject(obj, j), i3);
                    break;
                case 51:
                    if (!zzR(obj, i6, i3)) {
                        break;
                    } else {
                        zznfVar.zzf(i6, zzn(obj, j));
                        break;
                    }
                case 52:
                    if (!zzR(obj, i6, i3)) {
                        break;
                    } else {
                        zznfVar.zzo(i6, zzo(obj, j));
                        break;
                    }
                case 53:
                    if (!zzR(obj, i6, i3)) {
                        break;
                    } else {
                        zznfVar.zzt(i6, zzC(obj, j));
                        break;
                    }
                case 54:
                    if (!zzR(obj, i6, i3)) {
                        break;
                    } else {
                        zznfVar.zzJ(i6, zzC(obj, j));
                        break;
                    }
                case 55:
                    if (!zzR(obj, i6, i3)) {
                        break;
                    } else {
                        zznfVar.zzr(i6, zzr(obj, j));
                        break;
                    }
                case 56:
                    if (!zzR(obj, i6, i3)) {
                        break;
                    } else {
                        zznfVar.zzm(i6, zzC(obj, j));
                        break;
                    }
                case 57:
                    if (!zzR(obj, i6, i3)) {
                        break;
                    } else {
                        zznfVar.zzk(i6, zzr(obj, j));
                        break;
                    }
                case 58:
                    if (!zzR(obj, i6, i3)) {
                        break;
                    } else {
                        zznfVar.zzb(i6, zzS(obj, j));
                        break;
                    }
                case 59:
                    if (!zzR(obj, i6, i3)) {
                        break;
                    } else {
                        zzT(i6, unsafe.getObject(obj, j), zznfVar);
                        break;
                    }
                case 60:
                    if (!zzR(obj, i6, i3)) {
                        break;
                    } else {
                        zznfVar.zzv(i6, unsafe.getObject(obj, j), zzE(i3));
                        break;
                    }
                case 61:
                    if (!zzR(obj, i6, i3)) {
                        break;
                    } else {
                        zznfVar.zzd(i6, (zzjd) unsafe.getObject(obj, j));
                        break;
                    }
                case 62:
                    if (!zzR(obj, i6, i3)) {
                        break;
                    } else {
                        zznfVar.zzH(i6, zzr(obj, j));
                        break;
                    }
                case 63:
                    if (!zzR(obj, i6, i3)) {
                        break;
                    } else {
                        zznfVar.zzi(i6, zzr(obj, j));
                        break;
                    }
                case 64:
                    if (!zzR(obj, i6, i3)) {
                        break;
                    } else {
                        zznfVar.zzw(i6, zzr(obj, j));
                        break;
                    }
                case 65:
                    if (!zzR(obj, i6, i3)) {
                        break;
                    } else {
                        zznfVar.zzy(i6, zzC(obj, j));
                        break;
                    }
                case 66:
                    if (!zzR(obj, i6, i3)) {
                        break;
                    } else {
                        zznfVar.zzA(i6, zzr(obj, j));
                        break;
                    }
                case 67:
                    if (!zzR(obj, i6, i3)) {
                        break;
                    } else {
                        zznfVar.zzC(i6, zzC(obj, j));
                        break;
                    }
                case 68:
                    if (!zzR(obj, i6, i3)) {
                        break;
                    } else {
                        zznfVar.zzq(i6, unsafe.getObject(obj, j), zzE(i3));
                        break;
                    }
            }
            i3 += 3;
            i4 = i;
        }
        zzmn zzmnVar = this.zzn;
        zzmnVar.zzi(zzmnVar.zzc(obj), zznfVar);
    }

    private final void zzM(zznf zznfVar, int i, Object obj, int i2) throws IOException {
        if (obj == null) {
            return;
        }
        throw null;
    }

    private final boolean zzN(Object obj, Object obj2, int i) {
        return zzO(obj, i) == zzO(obj2, i);
    }

    private final boolean zzO(Object obj, int i) {
        int iZzy = zzy(i);
        long j = iZzy & 1048575;
        if (j != 1048575) {
            return (zzmx.zzc(obj, j) & (1 << (iZzy >>> 20))) != 0;
        }
        int iZzB = zzB(i);
        long j2 = iZzB & 1048575;
        switch (zzA(iZzB)) {
            case 0:
                return Double.doubleToRawLongBits(zzmx.zza(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzmx.zzb(obj, j2)) != 0;
            case 2:
                return zzmx.zzd(obj, j2) != 0;
            case 3:
                return zzmx.zzd(obj, j2) != 0;
            case 4:
                return zzmx.zzc(obj, j2) != 0;
            case 5:
                return zzmx.zzd(obj, j2) != 0;
            case 6:
                return zzmx.zzc(obj, j2) != 0;
            case 7:
                return zzmx.zzw(obj, j2);
            case 8:
                Object objZzf = zzmx.zzf(obj, j2);
                if (objZzf instanceof String) {
                    return !((String) objZzf).isEmpty();
                }
                if (objZzf instanceof zzjd) {
                    return !zzjd.zzb.equals(objZzf);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzmx.zzf(obj, j2) != null;
            case 10:
                return !zzjd.zzb.equals(zzmx.zzf(obj, j2));
            case 11:
                return zzmx.zzc(obj, j2) != 0;
            case 12:
                return zzmx.zzc(obj, j2) != 0;
            case 13:
                return zzmx.zzc(obj, j2) != 0;
            case 14:
                return zzmx.zzd(obj, j2) != 0;
            case 15:
                return zzmx.zzc(obj, j2) != 0;
            case 16:
                return zzmx.zzd(obj, j2) != 0;
            case 17:
                return zzmx.zzf(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzP(Object obj, int i, int i2, int i3, int i4) {
        return i2 == 1048575 ? zzO(obj, i) : (i3 & i4) != 0;
    }

    private static boolean zzQ(Object obj, int i, zzlw zzlwVar) {
        return zzlwVar.zzk(zzmx.zzf(obj, i & 1048575));
    }

    private final boolean zzR(Object obj, int i, int i2) {
        return zzmx.zzc(obj, (long) (zzy(i2) & 1048575)) == i;
    }

    private static boolean zzS(Object obj, long j) {
        return ((Boolean) zzmx.zzf(obj, j)).booleanValue();
    }

    private static final void zzT(int i, Object obj, zznf zznfVar) throws IOException {
        if (obj instanceof String) {
            zznfVar.zzF(i, (String) obj);
        } else {
            zznfVar.zzd(i, (zzjd) obj);
        }
    }

    static zzmo zzd(Object obj) {
        zzke zzkeVar = (zzke) obj;
        zzmo zzmoVar = zzkeVar.zzc;
        if (zzmoVar != zzmo.zzc()) {
            return zzmoVar;
        }
        zzmo zzmoVarZze = zzmo.zze();
        zzkeVar.zzc = zzmoVarZze;
        return zzmoVarZze;
    }

    static zzlo zzl(Class cls, zzli zzliVar, zzlq zzlqVar, zzkz zzkzVar, zzmn zzmnVar, zzjr zzjrVar, zzlg zzlgVar) {
        if (zzliVar instanceof zzlv) {
            return zzm((zzlv) zzliVar, zzlqVar, zzkzVar, zzmnVar, zzjrVar, zzlgVar);
        }
        throw null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0392  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.google.android.gms.internal.measurement.zzlo zzm(com.google.android.gms.internal.measurement.zzlv r36, com.google.android.gms.internal.measurement.zzlq r37, com.google.android.gms.internal.measurement.zzkz r38, com.google.android.gms.internal.measurement.zzmn r39, com.google.android.gms.internal.measurement.zzjr r40, com.google.android.gms.internal.measurement.zzlg r41) {
        /*
            Method dump skipped, instructions count: 1028
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlo.zzm(com.google.android.gms.internal.measurement.zzlv, com.google.android.gms.internal.measurement.zzlq, com.google.android.gms.internal.measurement.zzkz, com.google.android.gms.internal.measurement.zzmn, com.google.android.gms.internal.measurement.zzjr, com.google.android.gms.internal.measurement.zzlg):com.google.android.gms.internal.measurement.zzlo");
    }

    private static double zzn(Object obj, long j) {
        return ((Double) zzmx.zzf(obj, j)).doubleValue();
    }

    private static float zzo(Object obj, long j) {
        return ((Float) zzmx.zzf(obj, j)).floatValue();
    }

    private final int zzp(Object obj) {
        int i;
        Unsafe unsafe = zzb;
        int i2 = 1048575;
        int i3 = 0;
        int iZzA = 0;
        int i4 = 1048575;
        int i5 = 0;
        while (i3 < this.zzc.length) {
            int iZzB = zzB(i3);
            int[] iArr = this.zzc;
            int i6 = iArr[i3];
            int iZzA2 = zzA(iZzB);
            if (iZzA2 <= 17) {
                int i7 = iArr[i3 + 2];
                int i8 = i7 & i2;
                i = 1 << (i7 >>> 20);
                if (i8 != i4) {
                    i5 = unsafe.getInt(obj, i8);
                    i4 = i8;
                }
            } else {
                i = 0;
            }
            long j = iZzB & i2;
            switch (iZzA2) {
                case 0:
                    if ((i5 & i) != 0) {
                        iZzA += zzjl.zzA(i6 << 3) + 8;
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                case 1:
                    if ((i5 & i) != 0) {
                        iZzA += zzjl.zzA(i6 << 3) + 4;
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                case 2:
                    if ((i5 & i) != 0) {
                        iZzA += zzjl.zzA(i6 << 3) + zzjl.zzB(unsafe.getLong(obj, j));
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                case 3:
                    if ((i5 & i) != 0) {
                        iZzA += zzjl.zzA(i6 << 3) + zzjl.zzB(unsafe.getLong(obj, j));
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                case 4:
                    if ((i5 & i) != 0) {
                        iZzA += zzjl.zzA(i6 << 3) + zzjl.zzv(unsafe.getInt(obj, j));
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                case 5:
                    if ((i5 & i) != 0) {
                        iZzA += zzjl.zzA(i6 << 3) + 8;
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                case 6:
                    if ((i5 & i) != 0) {
                        iZzA += zzjl.zzA(i6 << 3) + 4;
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                case 7:
                    if ((i5 & i) != 0) {
                        iZzA += zzjl.zzA(i6 << 3) + 1;
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                case 8:
                    if ((i5 & i) != 0) {
                        Object object = unsafe.getObject(obj, j);
                        if (object instanceof zzjd) {
                            int iZzA3 = zzjl.zzA(i6 << 3);
                            int iZzd = ((zzjd) object).zzd();
                            iZzA += iZzA3 + zzjl.zzA(iZzd) + iZzd;
                        } else {
                            iZzA += zzjl.zzA(i6 << 3) + zzjl.zzy((String) object);
                        }
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                case 9:
                    if ((i5 & i) != 0) {
                        iZzA += zzly.zzo(i6, unsafe.getObject(obj, j), zzE(i3));
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                case 10:
                    if ((i5 & i) != 0) {
                        zzjd zzjdVar = (zzjd) unsafe.getObject(obj, j);
                        int iZzA4 = zzjl.zzA(i6 << 3);
                        int iZzd2 = zzjdVar.zzd();
                        iZzA += iZzA4 + zzjl.zzA(iZzd2) + iZzd2;
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                case 11:
                    if ((i5 & i) != 0) {
                        iZzA += zzjl.zzA(i6 << 3) + zzjl.zzA(unsafe.getInt(obj, j));
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                case 12:
                    if ((i5 & i) != 0) {
                        iZzA += zzjl.zzA(i6 << 3) + zzjl.zzv(unsafe.getInt(obj, j));
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                case 13:
                    if ((i5 & i) != 0) {
                        iZzA += zzjl.zzA(i6 << 3) + 4;
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                case 14:
                    if ((i5 & i) != 0) {
                        iZzA += zzjl.zzA(i6 << 3) + 8;
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                case 15:
                    if ((i5 & i) != 0) {
                        int i9 = unsafe.getInt(obj, j);
                        iZzA += zzjl.zzA(i6 << 3) + zzjl.zzA((i9 >> 31) ^ (i9 + i9));
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                case 16:
                    if ((i & i5) != 0) {
                        long j2 = unsafe.getLong(obj, j);
                        iZzA += zzjl.zzA(i6 << 3) + zzjl.zzB((j2 >> 63) ^ (j2 + j2));
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                case 17:
                    if ((i5 & i) != 0) {
                        iZzA += zzjl.zzu(i6, (zzll) unsafe.getObject(obj, j), zzE(i3));
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                case 18:
                    iZzA += zzly.zzh(i6, (List) unsafe.getObject(obj, j), false);
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                case 19:
                    iZzA += zzly.zzf(i6, (List) unsafe.getObject(obj, j), false);
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                case 20:
                    iZzA += zzly.zzm(i6, (List) unsafe.getObject(obj, j), false);
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                case 21:
                    iZzA += zzly.zzx(i6, (List) unsafe.getObject(obj, j), false);
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                case 22:
                    iZzA += zzly.zzk(i6, (List) unsafe.getObject(obj, j), false);
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                case 23:
                    iZzA += zzly.zzh(i6, (List) unsafe.getObject(obj, j), false);
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                case 24:
                    iZzA += zzly.zzf(i6, (List) unsafe.getObject(obj, j), false);
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                case 25:
                    iZzA += zzly.zza(i6, (List) unsafe.getObject(obj, j), false);
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                case 26:
                    iZzA += zzly.zzu(i6, (List) unsafe.getObject(obj, j));
                    break;
                case 27:
                    iZzA += zzly.zzp(i6, (List) unsafe.getObject(obj, j), zzE(i3));
                    break;
                case 28:
                    iZzA += zzly.zzc(i6, (List) unsafe.getObject(obj, j));
                    break;
                case 29:
                    iZzA += zzly.zzv(i6, (List) unsafe.getObject(obj, j), false);
                    break;
                case 30:
                    iZzA += zzly.zzd(i6, (List) unsafe.getObject(obj, j), false);
                    break;
                case 31:
                    iZzA += zzly.zzf(i6, (List) unsafe.getObject(obj, j), false);
                    break;
                case 32:
                    iZzA += zzly.zzh(i6, (List) unsafe.getObject(obj, j), false);
                    break;
                case 33:
                    iZzA += zzly.zzq(i6, (List) unsafe.getObject(obj, j), false);
                    break;
                case 34:
                    iZzA += zzly.zzs(i6, (List) unsafe.getObject(obj, j), false);
                    break;
                case 35:
                    int iZzi = zzly.zzi((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzA += zzjl.zzz(i6) + zzjl.zzA(iZzi) + iZzi;
                        break;
                    }
                    break;
                case 36:
                    int iZzg = zzly.zzg((List) unsafe.getObject(obj, j));
                    if (iZzg > 0) {
                        iZzA += zzjl.zzz(i6) + zzjl.zzA(iZzg) + iZzg;
                        break;
                    }
                    break;
                case 37:
                    int iZzn = zzly.zzn((List) unsafe.getObject(obj, j));
                    if (iZzn > 0) {
                        iZzA += zzjl.zzz(i6) + zzjl.zzA(iZzn) + iZzn;
                        break;
                    }
                    break;
                case 38:
                    int iZzy = zzly.zzy((List) unsafe.getObject(obj, j));
                    if (iZzy > 0) {
                        iZzA += zzjl.zzz(i6) + zzjl.zzA(iZzy) + iZzy;
                        break;
                    }
                    break;
                case 39:
                    int iZzl = zzly.zzl((List) unsafe.getObject(obj, j));
                    if (iZzl > 0) {
                        iZzA += zzjl.zzz(i6) + zzjl.zzA(iZzl) + iZzl;
                        break;
                    }
                    break;
                case 40:
                    int iZzi2 = zzly.zzi((List) unsafe.getObject(obj, j));
                    if (iZzi2 > 0) {
                        iZzA += zzjl.zzz(i6) + zzjl.zzA(iZzi2) + iZzi2;
                        break;
                    }
                    break;
                case 41:
                    int iZzg2 = zzly.zzg((List) unsafe.getObject(obj, j));
                    if (iZzg2 > 0) {
                        iZzA += zzjl.zzz(i6) + zzjl.zzA(iZzg2) + iZzg2;
                        break;
                    }
                    break;
                case 42:
                    int iZzb = zzly.zzb((List) unsafe.getObject(obj, j));
                    if (iZzb > 0) {
                        iZzA += zzjl.zzz(i6) + zzjl.zzA(iZzb) + iZzb;
                        break;
                    }
                    break;
                case 43:
                    int iZzw = zzly.zzw((List) unsafe.getObject(obj, j));
                    if (iZzw > 0) {
                        iZzA += zzjl.zzz(i6) + zzjl.zzA(iZzw) + iZzw;
                        break;
                    }
                    break;
                case 44:
                    int iZze = zzly.zze((List) unsafe.getObject(obj, j));
                    if (iZze > 0) {
                        iZzA += zzjl.zzz(i6) + zzjl.zzA(iZze) + iZze;
                        break;
                    }
                    break;
                case 45:
                    int iZzg3 = zzly.zzg((List) unsafe.getObject(obj, j));
                    if (iZzg3 > 0) {
                        iZzA += zzjl.zzz(i6) + zzjl.zzA(iZzg3) + iZzg3;
                        break;
                    }
                    break;
                case 46:
                    int iZzi3 = zzly.zzi((List) unsafe.getObject(obj, j));
                    if (iZzi3 > 0) {
                        iZzA += zzjl.zzz(i6) + zzjl.zzA(iZzi3) + iZzi3;
                        break;
                    }
                    break;
                case 47:
                    int iZzr = zzly.zzr((List) unsafe.getObject(obj, j));
                    if (iZzr > 0) {
                        iZzA += zzjl.zzz(i6) + zzjl.zzA(iZzr) + iZzr;
                        break;
                    }
                    break;
                case 48:
                    int iZzt = zzly.zzt((List) unsafe.getObject(obj, j));
                    if (iZzt > 0) {
                        iZzA += zzjl.zzz(i6) + zzjl.zzA(iZzt) + iZzt;
                        break;
                    }
                    break;
                case 49:
                    iZzA += zzly.zzj(i6, (List) unsafe.getObject(obj, j), zzE(i3));
                    break;
                case 50:
                    zzlg.zza(i6, unsafe.getObject(obj, j), zzF(i3));
                    break;
                case 51:
                    if (zzR(obj, i6, i3)) {
                        iZzA += zzjl.zzA(i6 << 3) + 8;
                        break;
                    }
                    break;
                case 52:
                    if (zzR(obj, i6, i3)) {
                        iZzA += zzjl.zzA(i6 << 3) + 4;
                        break;
                    }
                    break;
                case 53:
                    if (zzR(obj, i6, i3)) {
                        iZzA += zzjl.zzA(i6 << 3) + zzjl.zzB(zzC(obj, j));
                        break;
                    }
                    break;
                case 54:
                    if (zzR(obj, i6, i3)) {
                        iZzA += zzjl.zzA(i6 << 3) + zzjl.zzB(zzC(obj, j));
                        break;
                    }
                    break;
                case 55:
                    if (zzR(obj, i6, i3)) {
                        iZzA += zzjl.zzA(i6 << 3) + zzjl.zzv(zzr(obj, j));
                        break;
                    }
                    break;
                case 56:
                    if (zzR(obj, i6, i3)) {
                        iZzA += zzjl.zzA(i6 << 3) + 8;
                        break;
                    }
                    break;
                case 57:
                    if (zzR(obj, i6, i3)) {
                        iZzA += zzjl.zzA(i6 << 3) + 4;
                        break;
                    }
                    break;
                case 58:
                    if (zzR(obj, i6, i3)) {
                        iZzA += zzjl.zzA(i6 << 3) + 1;
                        break;
                    }
                    break;
                case 59:
                    if (zzR(obj, i6, i3)) {
                        Object object2 = unsafe.getObject(obj, j);
                        if (!(object2 instanceof zzjd)) {
                            iZzA += zzjl.zzA(i6 << 3) + zzjl.zzy((String) object2);
                            break;
                        } else {
                            int iZzA5 = zzjl.zzA(i6 << 3);
                            int iZzd3 = ((zzjd) object2).zzd();
                            iZzA += iZzA5 + zzjl.zzA(iZzd3) + iZzd3;
                            break;
                        }
                    }
                    break;
                case 60:
                    if (zzR(obj, i6, i3)) {
                        iZzA += zzly.zzo(i6, unsafe.getObject(obj, j), zzE(i3));
                        break;
                    }
                    break;
                case 61:
                    if (zzR(obj, i6, i3)) {
                        zzjd zzjdVar2 = (zzjd) unsafe.getObject(obj, j);
                        int iZzA6 = zzjl.zzA(i6 << 3);
                        int iZzd4 = zzjdVar2.zzd();
                        iZzA += iZzA6 + zzjl.zzA(iZzd4) + iZzd4;
                        break;
                    }
                    break;
                case 62:
                    if (zzR(obj, i6, i3)) {
                        iZzA += zzjl.zzA(i6 << 3) + zzjl.zzA(zzr(obj, j));
                        break;
                    }
                    break;
                case 63:
                    if (zzR(obj, i6, i3)) {
                        iZzA += zzjl.zzA(i6 << 3) + zzjl.zzv(zzr(obj, j));
                        break;
                    }
                    break;
                case 64:
                    if (zzR(obj, i6, i3)) {
                        iZzA += zzjl.zzA(i6 << 3) + 4;
                        break;
                    }
                    break;
                case 65:
                    if (zzR(obj, i6, i3)) {
                        iZzA += zzjl.zzA(i6 << 3) + 8;
                        break;
                    }
                    break;
                case 66:
                    if (zzR(obj, i6, i3)) {
                        int iZzr2 = zzr(obj, j);
                        iZzA += zzjl.zzA(i6 << 3) + zzjl.zzA((iZzr2 >> 31) ^ (iZzr2 + iZzr2));
                        break;
                    }
                    break;
                case 67:
                    if (zzR(obj, i6, i3)) {
                        long jZzC = zzC(obj, j);
                        iZzA += zzjl.zzA(i6 << 3) + zzjl.zzB((jZzC >> 63) ^ (jZzC + jZzC));
                        break;
                    }
                    break;
                case 68:
                    if (zzR(obj, i6, i3)) {
                        iZzA += zzjl.zzu(i6, (zzll) unsafe.getObject(obj, j), zzE(i3));
                        break;
                    }
                    break;
            }
            i3 += 3;
            i2 = 1048575;
        }
        zzmn zzmnVar = this.zzn;
        int iZza = iZzA + zzmnVar.zza(zzmnVar.zzc(obj));
        if (!this.zzh) {
            return iZza;
        }
        this.zzo.zza(obj);
        throw null;
    }

    private final int zzq(Object obj) {
        Unsafe unsafe = zzb;
        int iZzA = 0;
        for (int i = 0; i < this.zzc.length; i += 3) {
            int iZzB = zzB(i);
            int iZzA2 = zzA(iZzB);
            int i2 = this.zzc[i];
            long j = iZzB & 1048575;
            if (iZzA2 >= zzjw.DOUBLE_LIST_PACKED.zza() && iZzA2 <= zzjw.SINT64_LIST_PACKED.zza()) {
                int i3 = this.zzc[i + 2];
            }
            switch (iZzA2) {
                case 0:
                    if (zzO(obj, i)) {
                        iZzA += zzjl.zzA(i2 << 3) + 8;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzO(obj, i)) {
                        iZzA += zzjl.zzA(i2 << 3) + 4;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzO(obj, i)) {
                        iZzA += zzjl.zzA(i2 << 3) + zzjl.zzB(zzmx.zzd(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzO(obj, i)) {
                        iZzA += zzjl.zzA(i2 << 3) + zzjl.zzB(zzmx.zzd(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzO(obj, i)) {
                        iZzA += zzjl.zzA(i2 << 3) + zzjl.zzv(zzmx.zzc(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzO(obj, i)) {
                        iZzA += zzjl.zzA(i2 << 3) + 8;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzO(obj, i)) {
                        iZzA += zzjl.zzA(i2 << 3) + 4;
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzO(obj, i)) {
                        iZzA += zzjl.zzA(i2 << 3) + 1;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzO(obj, i)) {
                        Object objZzf = zzmx.zzf(obj, j);
                        if (objZzf instanceof zzjd) {
                            int iZzA3 = zzjl.zzA(i2 << 3);
                            int iZzd = ((zzjd) objZzf).zzd();
                            iZzA += iZzA3 + zzjl.zzA(iZzd) + iZzd;
                            break;
                        } else {
                            iZzA += zzjl.zzA(i2 << 3) + zzjl.zzy((String) objZzf);
                            break;
                        }
                    } else {
                        break;
                    }
                case 9:
                    if (zzO(obj, i)) {
                        iZzA += zzly.zzo(i2, zzmx.zzf(obj, j), zzE(i));
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (zzO(obj, i)) {
                        zzjd zzjdVar = (zzjd) zzmx.zzf(obj, j);
                        int iZzA4 = zzjl.zzA(i2 << 3);
                        int iZzd2 = zzjdVar.zzd();
                        iZzA += iZzA4 + zzjl.zzA(iZzd2) + iZzd2;
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzO(obj, i)) {
                        iZzA += zzjl.zzA(i2 << 3) + zzjl.zzA(zzmx.zzc(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzO(obj, i)) {
                        iZzA += zzjl.zzA(i2 << 3) + zzjl.zzv(zzmx.zzc(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzO(obj, i)) {
                        iZzA += zzjl.zzA(i2 << 3) + 4;
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzO(obj, i)) {
                        iZzA += zzjl.zzA(i2 << 3) + 8;
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzO(obj, i)) {
                        int iZzc = zzmx.zzc(obj, j);
                        iZzA += zzjl.zzA(i2 << 3) + zzjl.zzA((iZzc >> 31) ^ (iZzc + iZzc));
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzO(obj, i)) {
                        long jZzd = zzmx.zzd(obj, j);
                        iZzA += zzjl.zzA(i2 << 3) + zzjl.zzB((jZzd >> 63) ^ (jZzd + jZzd));
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (zzO(obj, i)) {
                        iZzA += zzjl.zzu(i2, (zzll) zzmx.zzf(obj, j), zzE(i));
                        break;
                    } else {
                        break;
                    }
                case 18:
                    iZzA += zzly.zzh(i2, (List) zzmx.zzf(obj, j), false);
                    break;
                case 19:
                    iZzA += zzly.zzf(i2, (List) zzmx.zzf(obj, j), false);
                    break;
                case 20:
                    iZzA += zzly.zzm(i2, (List) zzmx.zzf(obj, j), false);
                    break;
                case 21:
                    iZzA += zzly.zzx(i2, (List) zzmx.zzf(obj, j), false);
                    break;
                case 22:
                    iZzA += zzly.zzk(i2, (List) zzmx.zzf(obj, j), false);
                    break;
                case 23:
                    iZzA += zzly.zzh(i2, (List) zzmx.zzf(obj, j), false);
                    break;
                case 24:
                    iZzA += zzly.zzf(i2, (List) zzmx.zzf(obj, j), false);
                    break;
                case 25:
                    iZzA += zzly.zza(i2, (List) zzmx.zzf(obj, j), false);
                    break;
                case 26:
                    iZzA += zzly.zzu(i2, (List) zzmx.zzf(obj, j));
                    break;
                case 27:
                    iZzA += zzly.zzp(i2, (List) zzmx.zzf(obj, j), zzE(i));
                    break;
                case 28:
                    iZzA += zzly.zzc(i2, (List) zzmx.zzf(obj, j));
                    break;
                case 29:
                    iZzA += zzly.zzv(i2, (List) zzmx.zzf(obj, j), false);
                    break;
                case 30:
                    iZzA += zzly.zzd(i2, (List) zzmx.zzf(obj, j), false);
                    break;
                case 31:
                    iZzA += zzly.zzf(i2, (List) zzmx.zzf(obj, j), false);
                    break;
                case 32:
                    iZzA += zzly.zzh(i2, (List) zzmx.zzf(obj, j), false);
                    break;
                case 33:
                    iZzA += zzly.zzq(i2, (List) zzmx.zzf(obj, j), false);
                    break;
                case 34:
                    iZzA += zzly.zzs(i2, (List) zzmx.zzf(obj, j), false);
                    break;
                case 35:
                    int iZzi = zzly.zzi((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzA += zzjl.zzz(i2) + zzjl.zzA(iZzi) + iZzi;
                        break;
                    } else {
                        break;
                    }
                case 36:
                    int iZzg = zzly.zzg((List) unsafe.getObject(obj, j));
                    if (iZzg > 0) {
                        iZzA += zzjl.zzz(i2) + zzjl.zzA(iZzg) + iZzg;
                        break;
                    } else {
                        break;
                    }
                case 37:
                    int iZzn = zzly.zzn((List) unsafe.getObject(obj, j));
                    if (iZzn > 0) {
                        iZzA += zzjl.zzz(i2) + zzjl.zzA(iZzn) + iZzn;
                        break;
                    } else {
                        break;
                    }
                case 38:
                    int iZzy = zzly.zzy((List) unsafe.getObject(obj, j));
                    if (iZzy > 0) {
                        iZzA += zzjl.zzz(i2) + zzjl.zzA(iZzy) + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 39:
                    int iZzl = zzly.zzl((List) unsafe.getObject(obj, j));
                    if (iZzl > 0) {
                        iZzA += zzjl.zzz(i2) + zzjl.zzA(iZzl) + iZzl;
                        break;
                    } else {
                        break;
                    }
                case 40:
                    int iZzi2 = zzly.zzi((List) unsafe.getObject(obj, j));
                    if (iZzi2 > 0) {
                        iZzA += zzjl.zzz(i2) + zzjl.zzA(iZzi2) + iZzi2;
                        break;
                    } else {
                        break;
                    }
                case 41:
                    int iZzg2 = zzly.zzg((List) unsafe.getObject(obj, j));
                    if (iZzg2 > 0) {
                        iZzA += zzjl.zzz(i2) + zzjl.zzA(iZzg2) + iZzg2;
                        break;
                    } else {
                        break;
                    }
                case 42:
                    int iZzb = zzly.zzb((List) unsafe.getObject(obj, j));
                    if (iZzb > 0) {
                        iZzA += zzjl.zzz(i2) + zzjl.zzA(iZzb) + iZzb;
                        break;
                    } else {
                        break;
                    }
                case 43:
                    int iZzw = zzly.zzw((List) unsafe.getObject(obj, j));
                    if (iZzw > 0) {
                        iZzA += zzjl.zzz(i2) + zzjl.zzA(iZzw) + iZzw;
                        break;
                    } else {
                        break;
                    }
                case 44:
                    int iZze = zzly.zze((List) unsafe.getObject(obj, j));
                    if (iZze > 0) {
                        iZzA += zzjl.zzz(i2) + zzjl.zzA(iZze) + iZze;
                        break;
                    } else {
                        break;
                    }
                case 45:
                    int iZzg3 = zzly.zzg((List) unsafe.getObject(obj, j));
                    if (iZzg3 > 0) {
                        iZzA += zzjl.zzz(i2) + zzjl.zzA(iZzg3) + iZzg3;
                        break;
                    } else {
                        break;
                    }
                case 46:
                    int iZzi3 = zzly.zzi((List) unsafe.getObject(obj, j));
                    if (iZzi3 > 0) {
                        iZzA += zzjl.zzz(i2) + zzjl.zzA(iZzi3) + iZzi3;
                        break;
                    } else {
                        break;
                    }
                case 47:
                    int iZzr = zzly.zzr((List) unsafe.getObject(obj, j));
                    if (iZzr > 0) {
                        iZzA += zzjl.zzz(i2) + zzjl.zzA(iZzr) + iZzr;
                        break;
                    } else {
                        break;
                    }
                case 48:
                    int iZzt = zzly.zzt((List) unsafe.getObject(obj, j));
                    if (iZzt > 0) {
                        iZzA += zzjl.zzz(i2) + zzjl.zzA(iZzt) + iZzt;
                        break;
                    } else {
                        break;
                    }
                case 49:
                    iZzA += zzly.zzj(i2, (List) zzmx.zzf(obj, j), zzE(i));
                    break;
                case 50:
                    zzlg.zza(i2, zzmx.zzf(obj, j), zzF(i));
                    break;
                case 51:
                    if (zzR(obj, i2, i)) {
                        iZzA += zzjl.zzA(i2 << 3) + 8;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzR(obj, i2, i)) {
                        iZzA += zzjl.zzA(i2 << 3) + 4;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzR(obj, i2, i)) {
                        iZzA += zzjl.zzA(i2 << 3) + zzjl.zzB(zzC(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzR(obj, i2, i)) {
                        iZzA += zzjl.zzA(i2 << 3) + zzjl.zzB(zzC(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzR(obj, i2, i)) {
                        iZzA += zzjl.zzA(i2 << 3) + zzjl.zzv(zzr(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzR(obj, i2, i)) {
                        iZzA += zzjl.zzA(i2 << 3) + 8;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzR(obj, i2, i)) {
                        iZzA += zzjl.zzA(i2 << 3) + 4;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzR(obj, i2, i)) {
                        iZzA += zzjl.zzA(i2 << 3) + 1;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzR(obj, i2, i)) {
                        Object objZzf2 = zzmx.zzf(obj, j);
                        if (objZzf2 instanceof zzjd) {
                            int iZzA5 = zzjl.zzA(i2 << 3);
                            int iZzd3 = ((zzjd) objZzf2).zzd();
                            iZzA += iZzA5 + zzjl.zzA(iZzd3) + iZzd3;
                            break;
                        } else {
                            iZzA += zzjl.zzA(i2 << 3) + zzjl.zzy((String) objZzf2);
                            break;
                        }
                    } else {
                        break;
                    }
                case 60:
                    if (zzR(obj, i2, i)) {
                        iZzA += zzly.zzo(i2, zzmx.zzf(obj, j), zzE(i));
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzR(obj, i2, i)) {
                        zzjd zzjdVar2 = (zzjd) zzmx.zzf(obj, j);
                        int iZzA6 = zzjl.zzA(i2 << 3);
                        int iZzd4 = zzjdVar2.zzd();
                        iZzA += iZzA6 + zzjl.zzA(iZzd4) + iZzd4;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzR(obj, i2, i)) {
                        iZzA += zzjl.zzA(i2 << 3) + zzjl.zzA(zzr(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzR(obj, i2, i)) {
                        iZzA += zzjl.zzA(i2 << 3) + zzjl.zzv(zzr(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzR(obj, i2, i)) {
                        iZzA += zzjl.zzA(i2 << 3) + 4;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzR(obj, i2, i)) {
                        iZzA += zzjl.zzA(i2 << 3) + 8;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzR(obj, i2, i)) {
                        int iZzr2 = zzr(obj, j);
                        iZzA += zzjl.zzA(i2 << 3) + zzjl.zzA((iZzr2 >> 31) ^ (iZzr2 + iZzr2));
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzR(obj, i2, i)) {
                        long jZzC = zzC(obj, j);
                        iZzA += zzjl.zzA(i2 << 3) + zzjl.zzB((jZzC >> 63) ^ (jZzC + jZzC));
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzR(obj, i2, i)) {
                        iZzA += zzjl.zzu(i2, (zzll) zzmx.zzf(obj, j), zzE(i));
                        break;
                    } else {
                        break;
                    }
            }
        }
        zzmn zzmnVar = this.zzn;
        return iZzA + zzmnVar.zza(zzmnVar.zzc(obj));
    }

    private static int zzr(Object obj, long j) {
        return ((Integer) zzmx.zzf(obj, j)).intValue();
    }

    private final int zzs(Object obj, byte[] bArr, int i, int i2, int i3, long j, zziq zziqVar) throws IOException {
        Unsafe unsafe = zzb;
        Object objZzF = zzF(i3);
        Object object = unsafe.getObject(obj, j);
        if (!((zzlf) object).zze()) {
            zzlf zzlfVarZzb = zzlf.zza().zzb();
            zzlg.zzb(zzlfVarZzb, object);
            unsafe.putObject(obj, j, zzlfVarZzb);
        }
        throw null;
    }

    private final int zzt(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zziq zziqVar) throws IOException {
        Unsafe unsafe = zzb;
        long j2 = this.zzc[i8 + 2] & 1048575;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Double.valueOf(Double.longBitsToDouble(zzir.zzn(bArr, i))));
                    unsafe.putInt(obj, j2, i4);
                    return i + 8;
                }
                break;
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Float.valueOf(Float.intBitsToFloat(zzir.zzb(bArr, i))));
                    unsafe.putInt(obj, j2, i4);
                    return i + 4;
                }
                break;
            case 53:
            case 54:
                if (i5 == 0) {
                    int iZzm = zzir.zzm(bArr, i, zziqVar);
                    unsafe.putObject(obj, j, Long.valueOf(zziqVar.zzb));
                    unsafe.putInt(obj, j2, i4);
                    return iZzm;
                }
                break;
            case 55:
            case 62:
                if (i5 == 0) {
                    int iZzj = zzir.zzj(bArr, i, zziqVar);
                    unsafe.putObject(obj, j, Integer.valueOf(zziqVar.zza));
                    unsafe.putInt(obj, j2, i4);
                    return iZzj;
                }
                break;
            case 56:
            case 65:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Long.valueOf(zzir.zzn(bArr, i)));
                    unsafe.putInt(obj, j2, i4);
                    return i + 8;
                }
                break;
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Integer.valueOf(zzir.zzb(bArr, i)));
                    unsafe.putInt(obj, j2, i4);
                    return i + 4;
                }
                break;
            case 58:
                if (i5 == 0) {
                    int iZzm2 = zzir.zzm(bArr, i, zziqVar);
                    unsafe.putObject(obj, j, Boolean.valueOf(zziqVar.zzb != 0));
                    unsafe.putInt(obj, j2, i4);
                    return iZzm2;
                }
                break;
            case 59:
                if (i5 == 2) {
                    int iZzj2 = zzir.zzj(bArr, i, zziqVar);
                    int i9 = zziqVar.zza;
                    if (i9 == 0) {
                        unsafe.putObject(obj, j, "");
                    } else {
                        if ((i6 & 536870912) != 0 && !zznc.zzf(bArr, iZzj2, iZzj2 + i9)) {
                            throw zzko.zzc();
                        }
                        unsafe.putObject(obj, j, new String(bArr, iZzj2, i9, zzkm.zzb));
                        iZzj2 += i9;
                    }
                    unsafe.putInt(obj, j2, i4);
                    return iZzj2;
                }
                break;
            case 60:
                if (i5 == 2) {
                    int iZzd = zzir.zzd(zzE(i8), bArr, i, i2, zziqVar);
                    Object object = unsafe.getInt(obj, j2) == i4 ? unsafe.getObject(obj, j) : null;
                    if (object == null) {
                        unsafe.putObject(obj, j, zziqVar.zzc);
                    } else {
                        unsafe.putObject(obj, j, zzkm.zzg(object, zziqVar.zzc));
                    }
                    unsafe.putInt(obj, j2, i4);
                    return iZzd;
                }
                break;
            case 61:
                if (i5 == 2) {
                    int iZza = zzir.zza(bArr, i, zziqVar);
                    unsafe.putObject(obj, j, zziqVar.zzc);
                    unsafe.putInt(obj, j2, i4);
                    return iZza;
                }
                break;
            case 63:
                if (i5 == 0) {
                    int iZzj3 = zzir.zzj(bArr, i, zziqVar);
                    int i10 = zziqVar.zza;
                    zzki zzkiVarZzD = zzD(i8);
                    if (zzkiVarZzD == null || zzkiVarZzD.zza(i10)) {
                        unsafe.putObject(obj, j, Integer.valueOf(i10));
                        unsafe.putInt(obj, j2, i4);
                    } else {
                        zzd(obj).zzh(i3, Long.valueOf(i10));
                    }
                    return iZzj3;
                }
                break;
            case 66:
                if (i5 == 0) {
                    int iZzj4 = zzir.zzj(bArr, i, zziqVar);
                    unsafe.putObject(obj, j, Integer.valueOf(zzjh.zzb(zziqVar.zza)));
                    unsafe.putInt(obj, j2, i4);
                    return iZzj4;
                }
                break;
            case 67:
                if (i5 == 0) {
                    int iZzm3 = zzir.zzm(bArr, i, zziqVar);
                    unsafe.putObject(obj, j, Long.valueOf(zzjh.zzc(zziqVar.zzb)));
                    unsafe.putInt(obj, j2, i4);
                    return iZzm3;
                }
                break;
            case 68:
                if (i5 == 3) {
                    int iZzc = zzir.zzc(zzE(i8), bArr, i, i2, (i3 & (-8)) | 4, zziqVar);
                    Object object2 = unsafe.getInt(obj, j2) == i4 ? unsafe.getObject(obj, j) : null;
                    if (object2 == null) {
                        unsafe.putObject(obj, j, zziqVar.zzc);
                    } else {
                        unsafe.putObject(obj, j, zzkm.zzg(object2, zziqVar.zzc));
                    }
                    unsafe.putInt(obj, j2, i4);
                    return iZzc;
                }
                break;
        }
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x02d9, code lost:
    
        if (r0 != r5) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x02db, code lost:
    
        r12 = r34;
        r11 = r37;
        r1 = r19;
        r2 = r23;
        r6 = r28;
        r7 = r29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x02e9, code lost:
    
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x031c, code lost:
    
        if (r0 != r15) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x033f, code lost:
    
        if (r0 != r15) goto L102;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:27:0x0093. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v9, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int zzu(java.lang.Object r33, byte[] r34, int r35, int r36, com.google.android.gms.internal.measurement.zziq r37) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 940
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlo.zzu(java.lang.Object, byte[], int, int, com.google.android.gms.internal.measurement.zziq):int");
    }

    private final int zzv(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zziq zziqVar) throws IOException {
        int iZzj;
        zzkl zzklVarZzd = (zzkl) zzb.getObject(obj, j2);
        if (!zzklVarZzd.zzc()) {
            int size = zzklVarZzd.size();
            zzklVarZzd = zzklVarZzd.zzd(size == 0 ? 10 : size + size);
            zzb.putObject(obj, j2, zzklVarZzd);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i5 == 2) {
                    zzjn zzjnVar = (zzjn) zzklVarZzd;
                    int iZzj2 = zzir.zzj(bArr, i, zziqVar);
                    int i8 = zziqVar.zza + iZzj2;
                    while (iZzj2 < i8) {
                        zzjnVar.zze(Double.longBitsToDouble(zzir.zzn(bArr, iZzj2)));
                        iZzj2 += 8;
                    }
                    if (iZzj2 == i8) {
                        return iZzj2;
                    }
                    throw zzko.zzf();
                }
                if (i5 == 1) {
                    zzjn zzjnVar2 = (zzjn) zzklVarZzd;
                    zzjnVar2.zze(Double.longBitsToDouble(zzir.zzn(bArr, i)));
                    int i9 = i + 8;
                    while (i9 < i2) {
                        int iZzj3 = zzir.zzj(bArr, i9, zziqVar);
                        if (i3 != zziqVar.zza) {
                            return i9;
                        }
                        zzjnVar2.zze(Double.longBitsToDouble(zzir.zzn(bArr, iZzj3)));
                        i9 = iZzj3 + 8;
                    }
                    return i9;
                }
                return i;
            case 19:
            case 36:
                if (i5 == 2) {
                    zzjx zzjxVar = (zzjx) zzklVarZzd;
                    int iZzj4 = zzir.zzj(bArr, i, zziqVar);
                    int i10 = zziqVar.zza + iZzj4;
                    while (iZzj4 < i10) {
                        zzjxVar.zze(Float.intBitsToFloat(zzir.zzb(bArr, iZzj4)));
                        iZzj4 += 4;
                    }
                    if (iZzj4 == i10) {
                        return iZzj4;
                    }
                    throw zzko.zzf();
                }
                if (i5 == 5) {
                    zzjx zzjxVar2 = (zzjx) zzklVarZzd;
                    zzjxVar2.zze(Float.intBitsToFloat(zzir.zzb(bArr, i)));
                    int i11 = i + 4;
                    while (i11 < i2) {
                        int iZzj5 = zzir.zzj(bArr, i11, zziqVar);
                        if (i3 != zziqVar.zza) {
                            return i11;
                        }
                        zzjxVar2.zze(Float.intBitsToFloat(zzir.zzb(bArr, iZzj5)));
                        i11 = iZzj5 + 4;
                    }
                    return i11;
                }
                return i;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    zzla zzlaVar = (zzla) zzklVarZzd;
                    int iZzj6 = zzir.zzj(bArr, i, zziqVar);
                    int i12 = zziqVar.zza + iZzj6;
                    while (iZzj6 < i12) {
                        iZzj6 = zzir.zzm(bArr, iZzj6, zziqVar);
                        zzlaVar.zzg(zziqVar.zzb);
                    }
                    if (iZzj6 == i12) {
                        return iZzj6;
                    }
                    throw zzko.zzf();
                }
                if (i5 == 0) {
                    zzla zzlaVar2 = (zzla) zzklVarZzd;
                    int iZzm = zzir.zzm(bArr, i, zziqVar);
                    zzlaVar2.zzg(zziqVar.zzb);
                    while (iZzm < i2) {
                        int iZzj7 = zzir.zzj(bArr, iZzm, zziqVar);
                        if (i3 != zziqVar.zza) {
                            return iZzm;
                        }
                        iZzm = zzir.zzm(bArr, iZzj7, zziqVar);
                        zzlaVar2.zzg(zziqVar.zzb);
                    }
                    return iZzm;
                }
                return i;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i5 == 2) {
                    return zzir.zzf(bArr, i, zzklVarZzd, zziqVar);
                }
                if (i5 == 0) {
                    return zzir.zzl(i3, bArr, i, i2, zzklVarZzd, zziqVar);
                }
                return i;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i5 == 2) {
                    zzla zzlaVar3 = (zzla) zzklVarZzd;
                    int iZzj8 = zzir.zzj(bArr, i, zziqVar);
                    int i13 = zziqVar.zza + iZzj8;
                    while (iZzj8 < i13) {
                        zzlaVar3.zzg(zzir.zzn(bArr, iZzj8));
                        iZzj8 += 8;
                    }
                    if (iZzj8 == i13) {
                        return iZzj8;
                    }
                    throw zzko.zzf();
                }
                if (i5 == 1) {
                    zzla zzlaVar4 = (zzla) zzklVarZzd;
                    zzlaVar4.zzg(zzir.zzn(bArr, i));
                    int i14 = i + 8;
                    while (i14 < i2) {
                        int iZzj9 = zzir.zzj(bArr, i14, zziqVar);
                        if (i3 != zziqVar.zza) {
                            return i14;
                        }
                        zzlaVar4.zzg(zzir.zzn(bArr, iZzj9));
                        i14 = iZzj9 + 8;
                    }
                    return i14;
                }
                return i;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i5 == 2) {
                    zzkf zzkfVar = (zzkf) zzklVarZzd;
                    int iZzj10 = zzir.zzj(bArr, i, zziqVar);
                    int i15 = zziqVar.zza + iZzj10;
                    while (iZzj10 < i15) {
                        zzkfVar.zzh(zzir.zzb(bArr, iZzj10));
                        iZzj10 += 4;
                    }
                    if (iZzj10 == i15) {
                        return iZzj10;
                    }
                    throw zzko.zzf();
                }
                if (i5 == 5) {
                    zzkf zzkfVar2 = (zzkf) zzklVarZzd;
                    zzkfVar2.zzh(zzir.zzb(bArr, i));
                    int i16 = i + 4;
                    while (i16 < i2) {
                        int iZzj11 = zzir.zzj(bArr, i16, zziqVar);
                        if (i3 != zziqVar.zza) {
                            return i16;
                        }
                        zzkfVar2.zzh(zzir.zzb(bArr, iZzj11));
                        i16 = iZzj11 + 4;
                    }
                    return i16;
                }
                return i;
            case 25:
            case 42:
                if (i5 == 2) {
                    zzis zzisVar = (zzis) zzklVarZzd;
                    iZzj = zzir.zzj(bArr, i, zziqVar);
                    int i17 = zziqVar.zza + iZzj;
                    while (iZzj < i17) {
                        iZzj = zzir.zzm(bArr, iZzj, zziqVar);
                        zzisVar.zze(zziqVar.zzb != 0);
                    }
                    if (iZzj != i17) {
                        throw zzko.zzf();
                    }
                    return iZzj;
                }
                if (i5 == 0) {
                    zzis zzisVar2 = (zzis) zzklVarZzd;
                    int iZzm2 = zzir.zzm(bArr, i, zziqVar);
                    zzisVar2.zze(zziqVar.zzb != 0);
                    while (iZzm2 < i2) {
                        int iZzj12 = zzir.zzj(bArr, iZzm2, zziqVar);
                        if (i3 != zziqVar.zza) {
                            return iZzm2;
                        }
                        iZzm2 = zzir.zzm(bArr, iZzj12, zziqVar);
                        zzisVar2.zze(zziqVar.zzb != 0);
                    }
                    return iZzm2;
                }
                return i;
            case 26:
                if (i5 == 2) {
                    if ((j & 536870912) == 0) {
                        int iZzj13 = zzir.zzj(bArr, i, zziqVar);
                        int i18 = zziqVar.zza;
                        if (i18 < 0) {
                            throw zzko.zzd();
                        }
                        if (i18 == 0) {
                            zzklVarZzd.add("");
                        } else {
                            zzklVarZzd.add(new String(bArr, iZzj13, i18, zzkm.zzb));
                            iZzj13 += i18;
                        }
                        while (iZzj13 < i2) {
                            int iZzj14 = zzir.zzj(bArr, iZzj13, zziqVar);
                            if (i3 != zziqVar.zza) {
                                return iZzj13;
                            }
                            iZzj13 = zzir.zzj(bArr, iZzj14, zziqVar);
                            int i19 = zziqVar.zza;
                            if (i19 < 0) {
                                throw zzko.zzd();
                            }
                            if (i19 == 0) {
                                zzklVarZzd.add("");
                            } else {
                                zzklVarZzd.add(new String(bArr, iZzj13, i19, zzkm.zzb));
                                iZzj13 += i19;
                            }
                        }
                        return iZzj13;
                    }
                    int iZzj15 = zzir.zzj(bArr, i, zziqVar);
                    int i20 = zziqVar.zza;
                    if (i20 < 0) {
                        throw zzko.zzd();
                    }
                    if (i20 == 0) {
                        zzklVarZzd.add("");
                    } else {
                        int i21 = iZzj15 + i20;
                        if (!zznc.zzf(bArr, iZzj15, i21)) {
                            throw zzko.zzc();
                        }
                        zzklVarZzd.add(new String(bArr, iZzj15, i20, zzkm.zzb));
                        iZzj15 = i21;
                    }
                    while (iZzj15 < i2) {
                        int iZzj16 = zzir.zzj(bArr, iZzj15, zziqVar);
                        if (i3 != zziqVar.zza) {
                            return iZzj15;
                        }
                        iZzj15 = zzir.zzj(bArr, iZzj16, zziqVar);
                        int i22 = zziqVar.zza;
                        if (i22 < 0) {
                            throw zzko.zzd();
                        }
                        if (i22 == 0) {
                            zzklVarZzd.add("");
                        } else {
                            int i23 = iZzj15 + i22;
                            if (!zznc.zzf(bArr, iZzj15, i23)) {
                                throw zzko.zzc();
                            }
                            zzklVarZzd.add(new String(bArr, iZzj15, i22, zzkm.zzb));
                            iZzj15 = i23;
                        }
                    }
                    return iZzj15;
                }
                return i;
            case 27:
                if (i5 == 2) {
                    return zzir.zze(zzE(i6), i3, bArr, i, i2, zzklVarZzd, zziqVar);
                }
                return i;
            case 28:
                if (i5 == 2) {
                    int iZzj17 = zzir.zzj(bArr, i, zziqVar);
                    int i24 = zziqVar.zza;
                    if (i24 < 0) {
                        throw zzko.zzd();
                    }
                    if (i24 > bArr.length - iZzj17) {
                        throw zzko.zzf();
                    }
                    if (i24 == 0) {
                        zzklVarZzd.add(zzjd.zzb);
                    } else {
                        zzklVarZzd.add(zzjd.zzl(bArr, iZzj17, i24));
                        iZzj17 += i24;
                    }
                    while (iZzj17 < i2) {
                        int iZzj18 = zzir.zzj(bArr, iZzj17, zziqVar);
                        if (i3 != zziqVar.zza) {
                            return iZzj17;
                        }
                        iZzj17 = zzir.zzj(bArr, iZzj18, zziqVar);
                        int i25 = zziqVar.zza;
                        if (i25 < 0) {
                            throw zzko.zzd();
                        }
                        if (i25 > bArr.length - iZzj17) {
                            throw zzko.zzf();
                        }
                        if (i25 == 0) {
                            zzklVarZzd.add(zzjd.zzb);
                        } else {
                            zzklVarZzd.add(zzjd.zzl(bArr, iZzj17, i25));
                            iZzj17 += i25;
                        }
                    }
                    return iZzj17;
                }
                return i;
            case 30:
            case 44:
                if (i5 != 2) {
                    if (i5 == 0) {
                        iZzj = zzir.zzl(i3, bArr, i, i2, zzklVarZzd, zziqVar);
                    }
                    return i;
                }
                iZzj = zzir.zzf(bArr, i, zzklVarZzd, zziqVar);
                zzke zzkeVar = (zzke) obj;
                zzmo zzmoVar = zzkeVar.zzc;
                if (zzmoVar == zzmo.zzc()) {
                    zzmoVar = null;
                }
                Object objZzC = zzly.zzC(i4, zzklVarZzd, zzD(i6), zzmoVar, this.zzn);
                if (objZzC != null) {
                    zzkeVar.zzc = (zzmo) objZzC;
                    return iZzj;
                }
                return iZzj;
            case 33:
            case 47:
                if (i5 == 2) {
                    zzkf zzkfVar3 = (zzkf) zzklVarZzd;
                    int iZzj19 = zzir.zzj(bArr, i, zziqVar);
                    int i26 = zziqVar.zza + iZzj19;
                    while (iZzj19 < i26) {
                        iZzj19 = zzir.zzj(bArr, iZzj19, zziqVar);
                        zzkfVar3.zzh(zzjh.zzb(zziqVar.zza));
                    }
                    if (iZzj19 == i26) {
                        return iZzj19;
                    }
                    throw zzko.zzf();
                }
                if (i5 == 0) {
                    zzkf zzkfVar4 = (zzkf) zzklVarZzd;
                    int iZzj20 = zzir.zzj(bArr, i, zziqVar);
                    zzkfVar4.zzh(zzjh.zzb(zziqVar.zza));
                    while (iZzj20 < i2) {
                        int iZzj21 = zzir.zzj(bArr, iZzj20, zziqVar);
                        if (i3 != zziqVar.zza) {
                            return iZzj20;
                        }
                        iZzj20 = zzir.zzj(bArr, iZzj21, zziqVar);
                        zzkfVar4.zzh(zzjh.zzb(zziqVar.zza));
                    }
                    return iZzj20;
                }
                return i;
            case 34:
            case 48:
                if (i5 == 2) {
                    zzla zzlaVar5 = (zzla) zzklVarZzd;
                    int iZzj22 = zzir.zzj(bArr, i, zziqVar);
                    int i27 = zziqVar.zza + iZzj22;
                    while (iZzj22 < i27) {
                        iZzj22 = zzir.zzm(bArr, iZzj22, zziqVar);
                        zzlaVar5.zzg(zzjh.zzc(zziqVar.zzb));
                    }
                    if (iZzj22 == i27) {
                        return iZzj22;
                    }
                    throw zzko.zzf();
                }
                if (i5 == 0) {
                    zzla zzlaVar6 = (zzla) zzklVarZzd;
                    int iZzm3 = zzir.zzm(bArr, i, zziqVar);
                    zzlaVar6.zzg(zzjh.zzc(zziqVar.zzb));
                    while (iZzm3 < i2) {
                        int iZzj23 = zzir.zzj(bArr, iZzm3, zziqVar);
                        if (i3 != zziqVar.zza) {
                            return iZzm3;
                        }
                        iZzm3 = zzir.zzm(bArr, iZzj23, zziqVar);
                        zzlaVar6.zzg(zzjh.zzc(zziqVar.zzb));
                    }
                    return iZzm3;
                }
                return i;
            default:
                if (i5 == 3) {
                    zzlw zzlwVarZzE = zzE(i6);
                    int i28 = (i3 & (-8)) | 4;
                    int iZzc = zzir.zzc(zzlwVarZzE, bArr, i, i2, i28, zziqVar);
                    zzklVarZzd.add(zziqVar.zzc);
                    while (iZzc < i2) {
                        int iZzj24 = zzir.zzj(bArr, iZzc, zziqVar);
                        if (i3 != zziqVar.zza) {
                            return iZzc;
                        }
                        iZzc = zzir.zzc(zzlwVarZzE, bArr, iZzj24, i2, i28, zziqVar);
                        zzklVarZzd.add(zziqVar.zzc);
                    }
                    return iZzc;
                }
                return i;
        }
    }

    private final int zzw(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzz(i, 0);
    }

    private final int zzx(int i, int i2) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzz(i, i2);
    }

    private final int zzy(int i) {
        return this.zzc[i + 2];
    }

    private final int zzz(int i, int i2) {
        int length = (this.zzc.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzc[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.measurement.zzlw
    public final int zza(Object obj) {
        return this.zzi ? zzq(obj) : zzp(obj);
    }

    @Override // com.google.android.gms.internal.measurement.zzlw
    public final int zzb(Object obj) {
        int length = this.zzc.length;
        int iZzc = 0;
        for (int i = 0; i < length; i += 3) {
            int iZzB = zzB(i);
            int i2 = this.zzc[i];
            long j = 1048575 & iZzB;
            switch (zzA(iZzB)) {
                case 0:
                    iZzc = (iZzc * 53) + zzkm.zzc(Double.doubleToLongBits(zzmx.zza(obj, j)));
                    break;
                case 1:
                    iZzc = (iZzc * 53) + Float.floatToIntBits(zzmx.zzb(obj, j));
                    break;
                case 2:
                    iZzc = (iZzc * 53) + zzkm.zzc(zzmx.zzd(obj, j));
                    break;
                case 3:
                    iZzc = (iZzc * 53) + zzkm.zzc(zzmx.zzd(obj, j));
                    break;
                case 4:
                    iZzc = (iZzc * 53) + zzmx.zzc(obj, j);
                    break;
                case 5:
                    iZzc = (iZzc * 53) + zzkm.zzc(zzmx.zzd(obj, j));
                    break;
                case 6:
                    iZzc = (iZzc * 53) + zzmx.zzc(obj, j);
                    break;
                case 7:
                    iZzc = (iZzc * 53) + zzkm.zza(zzmx.zzw(obj, j));
                    break;
                case 8:
                    iZzc = (iZzc * 53) + ((String) zzmx.zzf(obj, j)).hashCode();
                    break;
                case 9:
                    Object objZzf = zzmx.zzf(obj, j);
                    iZzc = (iZzc * 53) + (objZzf != null ? objZzf.hashCode() : 37);
                    break;
                case 10:
                    iZzc = (iZzc * 53) + zzmx.zzf(obj, j).hashCode();
                    break;
                case 11:
                    iZzc = (iZzc * 53) + zzmx.zzc(obj, j);
                    break;
                case 12:
                    iZzc = (iZzc * 53) + zzmx.zzc(obj, j);
                    break;
                case 13:
                    iZzc = (iZzc * 53) + zzmx.zzc(obj, j);
                    break;
                case 14:
                    iZzc = (iZzc * 53) + zzkm.zzc(zzmx.zzd(obj, j));
                    break;
                case 15:
                    iZzc = (iZzc * 53) + zzmx.zzc(obj, j);
                    break;
                case 16:
                    iZzc = (iZzc * 53) + zzkm.zzc(zzmx.zzd(obj, j));
                    break;
                case 17:
                    Object objZzf2 = zzmx.zzf(obj, j);
                    iZzc = (iZzc * 53) + (objZzf2 != null ? objZzf2.hashCode() : 37);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    iZzc = (iZzc * 53) + zzmx.zzf(obj, j).hashCode();
                    break;
                case 50:
                    iZzc = (iZzc * 53) + zzmx.zzf(obj, j).hashCode();
                    break;
                case 51:
                    if (zzR(obj, i2, i)) {
                        iZzc = (iZzc * 53) + zzkm.zzc(Double.doubleToLongBits(zzn(obj, j)));
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzR(obj, i2, i)) {
                        iZzc = (iZzc * 53) + Float.floatToIntBits(zzo(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzR(obj, i2, i)) {
                        iZzc = (iZzc * 53) + zzkm.zzc(zzC(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzR(obj, i2, i)) {
                        iZzc = (iZzc * 53) + zzkm.zzc(zzC(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzR(obj, i2, i)) {
                        iZzc = (iZzc * 53) + zzr(obj, j);
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzR(obj, i2, i)) {
                        iZzc = (iZzc * 53) + zzkm.zzc(zzC(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzR(obj, i2, i)) {
                        iZzc = (iZzc * 53) + zzr(obj, j);
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzR(obj, i2, i)) {
                        iZzc = (iZzc * 53) + zzkm.zza(zzS(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzR(obj, i2, i)) {
                        iZzc = (iZzc * 53) + ((String) zzmx.zzf(obj, j)).hashCode();
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zzR(obj, i2, i)) {
                        iZzc = (iZzc * 53) + zzmx.zzf(obj, j).hashCode();
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzR(obj, i2, i)) {
                        iZzc = (iZzc * 53) + zzmx.zzf(obj, j).hashCode();
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzR(obj, i2, i)) {
                        iZzc = (iZzc * 53) + zzr(obj, j);
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzR(obj, i2, i)) {
                        iZzc = (iZzc * 53) + zzr(obj, j);
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzR(obj, i2, i)) {
                        iZzc = (iZzc * 53) + zzr(obj, j);
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzR(obj, i2, i)) {
                        iZzc = (iZzc * 53) + zzkm.zzc(zzC(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzR(obj, i2, i)) {
                        iZzc = (iZzc * 53) + zzr(obj, j);
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzR(obj, i2, i)) {
                        iZzc = (iZzc * 53) + zzkm.zzc(zzC(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzR(obj, i2, i)) {
                        iZzc = (iZzc * 53) + zzmx.zzf(obj, j).hashCode();
                        break;
                    } else {
                        break;
                    }
            }
        }
        int iHashCode = (iZzc * 53) + this.zzn.zzc(obj).hashCode();
        if (!this.zzh) {
            return iHashCode;
        }
        this.zzo.zza(obj);
        throw null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:152:0x0498, code lost:
    
        if (r0 == r3) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x049a, code lost:
    
        r33.putInt(r11, r0, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x04a0, code lost:
    
        r0 = r8.zzk;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x04a4, code lost:
    
        if (r0 >= r8.zzl) goto L242;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x04a6, code lost:
    
        r1 = r8.zzj[r0];
        r4 = r8.zzc[r1];
        r4 = com.google.android.gms.internal.measurement.zzmx.zzf(r11, r8.zzB(r1) & r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x04b8, code lost:
    
        if (r4 != null) goto L160;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x04bf, code lost:
    
        if (r8.zzD(r1) != null) goto L241;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x04c1, code lost:
    
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x04c4, code lost:
    
        r4 = (com.google.android.gms.internal.measurement.zzlf) r4;
        r0 = (com.google.android.gms.internal.measurement.zzle) r8.zzF(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x04cc, code lost:
    
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x04cd, code lost:
    
        if (r6 != 0) goto L171;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x04d1, code lost:
    
        if (r2 != r39) goto L169;
     */
    /* JADX WARN: Code restructure failed: missing block: B:170:0x04d8, code lost:
    
        throw com.google.android.gms.internal.measurement.zzko.zze();
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x04db, code lost:
    
        if (r2 > r39) goto L175;
     */
    /* JADX WARN: Code restructure failed: missing block: B:173:0x04dd, code lost:
    
        if (r7 != r6) goto L175;
     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x04df, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:176:0x04e4, code lost:
    
        throw com.google.android.gms.internal.measurement.zzko.zze();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final int zzc(java.lang.Object r36, byte[] r37, int r38, int r39, int r40, com.google.android.gms.internal.measurement.zziq r41) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1292
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlo.zzc(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.measurement.zziq):int");
    }

    @Override // com.google.android.gms.internal.measurement.zzlw
    public final Object zze() {
        return ((zzke) this.zzg).zzl(4, null, null);
    }

    @Override // com.google.android.gms.internal.measurement.zzlw
    public final void zzf(Object obj) {
        int i;
        int i2 = this.zzk;
        while (true) {
            i = this.zzl;
            if (i2 >= i) {
                break;
            }
            long jZzB = zzB(this.zzj[i2]) & 1048575;
            Object objZzf = zzmx.zzf(obj, jZzB);
            if (objZzf != null) {
                ((zzlf) objZzf).zzc();
                zzmx.zzs(obj, jZzB, objZzf);
            }
            i2++;
        }
        int length = this.zzj.length;
        while (i < length) {
            this.zzm.zza(obj, this.zzj[i]);
            i++;
        }
        this.zzn.zzg(obj);
        if (this.zzh) {
            this.zzo.zzb(obj);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlw
    public final void zzg(Object obj, Object obj2) {
        if (obj2 == null) {
            throw null;
        }
        for (int i = 0; i < this.zzc.length; i += 3) {
            int iZzB = zzB(i);
            long j = 1048575 & iZzB;
            int i2 = this.zzc[i];
            switch (zzA(iZzB)) {
                case 0:
                    if (zzO(obj2, i)) {
                        zzmx.zzo(obj, j, zzmx.zza(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzO(obj2, i)) {
                        zzmx.zzp(obj, j, zzmx.zzb(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzO(obj2, i)) {
                        zzmx.zzr(obj, j, zzmx.zzd(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzO(obj2, i)) {
                        zzmx.zzr(obj, j, zzmx.zzd(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzO(obj2, i)) {
                        zzmx.zzq(obj, j, zzmx.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzO(obj2, i)) {
                        zzmx.zzr(obj, j, zzmx.zzd(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzO(obj2, i)) {
                        zzmx.zzq(obj, j, zzmx.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzO(obj2, i)) {
                        zzmx.zzm(obj, j, zzmx.zzw(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzO(obj2, i)) {
                        zzmx.zzs(obj, j, zzmx.zzf(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    zzH(obj, obj2, i);
                    break;
                case 10:
                    if (zzO(obj2, i)) {
                        zzmx.zzs(obj, j, zzmx.zzf(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzO(obj2, i)) {
                        zzmx.zzq(obj, j, zzmx.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzO(obj2, i)) {
                        zzmx.zzq(obj, j, zzmx.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzO(obj2, i)) {
                        zzmx.zzq(obj, j, zzmx.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzO(obj2, i)) {
                        zzmx.zzr(obj, j, zzmx.zzd(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzO(obj2, i)) {
                        zzmx.zzq(obj, j, zzmx.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzO(obj2, i)) {
                        zzmx.zzr(obj, j, zzmx.zzd(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    zzH(obj, obj2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzm.zzb(obj, obj2, j);
                    break;
                case 50:
                    zzly.zzaa(this.zzq, obj, obj2, j);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (zzR(obj2, i2, i)) {
                        zzmx.zzs(obj, j, zzmx.zzf(obj2, j));
                        zzK(obj, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    zzI(obj, obj2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (zzR(obj2, i2, i)) {
                        zzmx.zzs(obj, j, zzmx.zzf(obj2, j));
                        zzK(obj, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    zzI(obj, obj2, i);
                    break;
            }
        }
        zzly.zzF(this.zzn, obj, obj2);
        if (this.zzh) {
            zzly.zzE(this.zzo, obj, obj2);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlw
    public final void zzh(Object obj, byte[] bArr, int i, int i2, zziq zziqVar) throws IOException {
        if (this.zzi) {
            zzu(obj, bArr, i, i2, zziqVar);
        } else {
            zzc(obj, bArr, i, i2, 0, zziqVar);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlw
    public final void zzi(Object obj, zznf zznfVar) throws IOException {
        if (!this.zzi) {
            zzL(obj, zznfVar);
            return;
        }
        if (this.zzh) {
            this.zzo.zza(obj);
            throw null;
        }
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int iZzB = zzB(i);
            int i2 = this.zzc[i];
            switch (zzA(iZzB)) {
                case 0:
                    if (zzO(obj, i)) {
                        zznfVar.zzf(i2, zzmx.zza(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzO(obj, i)) {
                        zznfVar.zzo(i2, zzmx.zzb(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzO(obj, i)) {
                        zznfVar.zzt(i2, zzmx.zzd(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzO(obj, i)) {
                        zznfVar.zzJ(i2, zzmx.zzd(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzO(obj, i)) {
                        zznfVar.zzr(i2, zzmx.zzc(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzO(obj, i)) {
                        zznfVar.zzm(i2, zzmx.zzd(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzO(obj, i)) {
                        zznfVar.zzk(i2, zzmx.zzc(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzO(obj, i)) {
                        zznfVar.zzb(i2, zzmx.zzw(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzO(obj, i)) {
                        zzT(i2, zzmx.zzf(obj, iZzB & 1048575), zznfVar);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    if (zzO(obj, i)) {
                        zznfVar.zzv(i2, zzmx.zzf(obj, iZzB & 1048575), zzE(i));
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (zzO(obj, i)) {
                        zznfVar.zzd(i2, (zzjd) zzmx.zzf(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzO(obj, i)) {
                        zznfVar.zzH(i2, zzmx.zzc(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzO(obj, i)) {
                        zznfVar.zzi(i2, zzmx.zzc(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzO(obj, i)) {
                        zznfVar.zzw(i2, zzmx.zzc(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzO(obj, i)) {
                        zznfVar.zzy(i2, zzmx.zzd(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzO(obj, i)) {
                        zznfVar.zzA(i2, zzmx.zzc(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzO(obj, i)) {
                        zznfVar.zzC(i2, zzmx.zzd(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (zzO(obj, i)) {
                        zznfVar.zzq(i2, zzmx.zzf(obj, iZzB & 1048575), zzE(i));
                        break;
                    } else {
                        break;
                    }
                case 18:
                    zzly.zzJ(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, false);
                    break;
                case 19:
                    zzly.zzN(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, false);
                    break;
                case 20:
                    zzly.zzQ(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, false);
                    break;
                case 21:
                    zzly.zzY(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, false);
                    break;
                case 22:
                    zzly.zzP(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, false);
                    break;
                case 23:
                    zzly.zzM(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, false);
                    break;
                case 24:
                    zzly.zzL(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, false);
                    break;
                case 25:
                    zzly.zzH(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, false);
                    break;
                case 26:
                    zzly.zzW(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar);
                    break;
                case 27:
                    zzly.zzR(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, zzE(i));
                    break;
                case 28:
                    zzly.zzI(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar);
                    break;
                case 29:
                    zzly.zzX(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, false);
                    break;
                case 30:
                    zzly.zzK(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, false);
                    break;
                case 31:
                    zzly.zzS(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, false);
                    break;
                case 32:
                    zzly.zzT(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, false);
                    break;
                case 33:
                    zzly.zzU(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, false);
                    break;
                case 34:
                    zzly.zzV(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, false);
                    break;
                case 35:
                    zzly.zzJ(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, true);
                    break;
                case 36:
                    zzly.zzN(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, true);
                    break;
                case 37:
                    zzly.zzQ(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, true);
                    break;
                case 38:
                    zzly.zzY(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, true);
                    break;
                case 39:
                    zzly.zzP(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, true);
                    break;
                case 40:
                    zzly.zzM(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, true);
                    break;
                case 41:
                    zzly.zzL(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, true);
                    break;
                case 42:
                    zzly.zzH(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, true);
                    break;
                case 43:
                    zzly.zzX(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, true);
                    break;
                case 44:
                    zzly.zzK(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, true);
                    break;
                case 45:
                    zzly.zzS(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, true);
                    break;
                case 46:
                    zzly.zzT(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, true);
                    break;
                case 47:
                    zzly.zzU(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, true);
                    break;
                case 48:
                    zzly.zzV(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, true);
                    break;
                case 49:
                    zzly.zzO(i2, (List) zzmx.zzf(obj, iZzB & 1048575), zznfVar, zzE(i));
                    break;
                case 50:
                    zzM(zznfVar, i2, zzmx.zzf(obj, iZzB & 1048575), i);
                    break;
                case 51:
                    if (zzR(obj, i2, i)) {
                        zznfVar.zzf(i2, zzn(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzR(obj, i2, i)) {
                        zznfVar.zzo(i2, zzo(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzR(obj, i2, i)) {
                        zznfVar.zzt(i2, zzC(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzR(obj, i2, i)) {
                        zznfVar.zzJ(i2, zzC(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzR(obj, i2, i)) {
                        zznfVar.zzr(i2, zzr(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzR(obj, i2, i)) {
                        zznfVar.zzm(i2, zzC(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzR(obj, i2, i)) {
                        zznfVar.zzk(i2, zzr(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzR(obj, i2, i)) {
                        zznfVar.zzb(i2, zzS(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzR(obj, i2, i)) {
                        zzT(i2, zzmx.zzf(obj, iZzB & 1048575), zznfVar);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zzR(obj, i2, i)) {
                        zznfVar.zzv(i2, zzmx.zzf(obj, iZzB & 1048575), zzE(i));
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzR(obj, i2, i)) {
                        zznfVar.zzd(i2, (zzjd) zzmx.zzf(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzR(obj, i2, i)) {
                        zznfVar.zzH(i2, zzr(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzR(obj, i2, i)) {
                        zznfVar.zzi(i2, zzr(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzR(obj, i2, i)) {
                        zznfVar.zzw(i2, zzr(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzR(obj, i2, i)) {
                        zznfVar.zzy(i2, zzC(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzR(obj, i2, i)) {
                        zznfVar.zzA(i2, zzr(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzR(obj, i2, i)) {
                        zznfVar.zzC(i2, zzC(obj, iZzB & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzR(obj, i2, i)) {
                        zznfVar.zzq(i2, zzmx.zzf(obj, iZzB & 1048575), zzE(i));
                        break;
                    } else {
                        break;
                    }
            }
        }
        zzmn zzmnVar = this.zzn;
        zzmnVar.zzi(zzmnVar.zzc(obj), zznfVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzlw
    public final boolean zzj(Object obj, Object obj2) {
        boolean zZzZ;
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int iZzB = zzB(i);
            long j = iZzB & 1048575;
            switch (zzA(iZzB)) {
                case 0:
                    if (!zzN(obj, obj2, i) || Double.doubleToLongBits(zzmx.zza(obj, j)) != Double.doubleToLongBits(zzmx.zza(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 1:
                    if (!zzN(obj, obj2, i) || Float.floatToIntBits(zzmx.zzb(obj, j)) != Float.floatToIntBits(zzmx.zzb(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 2:
                    if (!zzN(obj, obj2, i) || zzmx.zzd(obj, j) != zzmx.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 3:
                    if (!zzN(obj, obj2, i) || zzmx.zzd(obj, j) != zzmx.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 4:
                    if (!zzN(obj, obj2, i) || zzmx.zzc(obj, j) != zzmx.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 5:
                    if (!zzN(obj, obj2, i) || zzmx.zzd(obj, j) != zzmx.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 6:
                    if (!zzN(obj, obj2, i) || zzmx.zzc(obj, j) != zzmx.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 7:
                    if (!zzN(obj, obj2, i) || zzmx.zzw(obj, j) != zzmx.zzw(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 8:
                    if (!zzN(obj, obj2, i) || !zzly.zzZ(zzmx.zzf(obj, j), zzmx.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 9:
                    if (!zzN(obj, obj2, i) || !zzly.zzZ(zzmx.zzf(obj, j), zzmx.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 10:
                    if (!zzN(obj, obj2, i) || !zzly.zzZ(zzmx.zzf(obj, j), zzmx.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 11:
                    if (!zzN(obj, obj2, i) || zzmx.zzc(obj, j) != zzmx.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 12:
                    if (!zzN(obj, obj2, i) || zzmx.zzc(obj, j) != zzmx.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 13:
                    if (!zzN(obj, obj2, i) || zzmx.zzc(obj, j) != zzmx.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 14:
                    if (!zzN(obj, obj2, i) || zzmx.zzd(obj, j) != zzmx.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 15:
                    if (!zzN(obj, obj2, i) || zzmx.zzc(obj, j) != zzmx.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 16:
                    if (!zzN(obj, obj2, i) || zzmx.zzd(obj, j) != zzmx.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 17:
                    if (!zzN(obj, obj2, i) || !zzly.zzZ(zzmx.zzf(obj, j), zzmx.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    zZzZ = zzly.zzZ(zzmx.zzf(obj, j), zzmx.zzf(obj2, j));
                    break;
                case 50:
                    zZzZ = zzly.zzZ(zzmx.zzf(obj, j), zzmx.zzf(obj2, j));
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                    long jZzy = zzy(i) & 1048575;
                    if (zzmx.zzc(obj, jZzy) != zzmx.zzc(obj2, jZzy) || !zzly.zzZ(zzmx.zzf(obj, j), zzmx.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                default:
            }
            if (!zZzZ) {
                return false;
            }
        }
        if (!this.zzn.zzc(obj).equals(this.zzn.zzc(obj2))) {
            return false;
        }
        if (!this.zzh) {
            return true;
        }
        this.zzo.zza(obj);
        this.zzo.zza(obj2);
        throw null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:72:0x00e1, code lost:
    
        continue;
     */
    /* JADX WARN: Removed duplicated region for block: B:40:0x009e  */
    @Override // com.google.android.gms.internal.measurement.zzlw
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean zzk(java.lang.Object r19) {
        /*
            Method dump skipped, instructions count: 252
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlo.zzk(java.lang.Object):boolean");
    }
}
