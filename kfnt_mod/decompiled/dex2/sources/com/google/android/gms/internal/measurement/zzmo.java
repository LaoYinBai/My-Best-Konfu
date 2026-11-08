package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzmo {
    private static final zzmo zza = new zzmo(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzmo() {
        this(0, new int[8], new Object[8], true);
    }

    private zzmo(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public static zzmo zzc() {
        return zza;
    }

    static zzmo zzd(zzmo zzmoVar, zzmo zzmoVar2) {
        int i = zzmoVar.zzb + zzmoVar2.zzb;
        int[] iArrCopyOf = Arrays.copyOf(zzmoVar.zzc, i);
        System.arraycopy(zzmoVar2.zzc, 0, iArrCopyOf, zzmoVar.zzb, zzmoVar2.zzb);
        Object[] objArrCopyOf = Arrays.copyOf(zzmoVar.zzd, i);
        System.arraycopy(zzmoVar2.zzd, 0, objArrCopyOf, zzmoVar.zzb, zzmoVar2.zzb);
        return new zzmo(i, iArrCopyOf, objArrCopyOf, true);
    }

    static zzmo zze() {
        return new zzmo(0, new int[8], new Object[8], true);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzmo)) {
            return false;
        }
        zzmo zzmoVar = (zzmo) obj;
        int i = this.zzb;
        if (i == zzmoVar.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzmoVar.zzc;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    Object[] objArr = this.zzd;
                    Object[] objArr2 = zzmoVar.zzd;
                    int i3 = this.zzb;
                    for (int i4 = 0; i4 < i3; i4++) {
                        if (objArr[i4].equals(objArr2[i4])) {
                        }
                    }
                    return true;
                }
                if (iArr[i2] != iArr2[i2]) {
                    break;
                }
                i2++;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zzb;
        int i2 = (i + 527) * 31;
        int[] iArr = this.zzc;
        int iHashCode = 17;
        int i3 = 17;
        for (int i4 = 0; i4 < i; i4++) {
            i3 = (i3 * 31) + iArr[i4];
        }
        int i5 = (i2 + i3) * 31;
        Object[] objArr = this.zzd;
        int i6 = this.zzb;
        for (int i7 = 0; i7 < i6; i7++) {
            iHashCode = (iHashCode * 31) + objArr[i7].hashCode();
        }
        return i5 + iHashCode;
    }

    public final int zza() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int iZzA = 0;
        for (int i2 = 0; i2 < this.zzb; i2++) {
            int i3 = this.zzc[i2];
            int i4 = i3 >>> 3;
            int i5 = i3 & 7;
            if (i5 != 5) {
                switch (i5) {
                    case 0:
                        iZzA += zzjl.zzA(i4 << 3) + zzjl.zzB(((Long) this.zzd[i2]).longValue());
                        break;
                    case 1:
                        ((Long) this.zzd[i2]).longValue();
                        iZzA += zzjl.zzA(i4 << 3) + 8;
                        break;
                    case 2:
                        zzjd zzjdVar = (zzjd) this.zzd[i2];
                        int iZzA2 = zzjl.zzA(i4 << 3);
                        int iZzd = zzjdVar.zzd();
                        iZzA += iZzA2 + zzjl.zzA(iZzd) + iZzd;
                        break;
                    case 3:
                        int iZzz = zzjl.zzz(i4);
                        iZzA += iZzz + iZzz + ((zzmo) this.zzd[i2]).zza();
                        break;
                    default:
                        throw new IllegalStateException(zzko.zza());
                }
            } else {
                ((Integer) this.zzd[i2]).intValue();
                iZzA += zzjl.zzA(i4 << 3) + 4;
            }
        }
        this.zze = iZzA;
        return iZzA;
    }

    public final int zzb() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int iZzA = 0;
        for (int i2 = 0; i2 < this.zzb; i2++) {
            int i3 = this.zzc[i2];
            zzjd zzjdVar = (zzjd) this.zzd[i2];
            int iZzA2 = zzjl.zzA(8);
            int iZzd = zzjdVar.zzd();
            iZzA += iZzA2 + iZzA2 + zzjl.zzA(16) + zzjl.zzA(i3 >>> 3) + zzjl.zzA(24) + zzjl.zzA(iZzd) + iZzd;
        }
        this.zze = iZzA;
        return iZzA;
    }

    public final void zzf() {
        this.zzf = false;
    }

    final void zzg(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzln.zzb(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    final void zzh(int i, Object obj) {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
        int i2 = this.zzb;
        int[] iArr = this.zzc;
        if (i2 == iArr.length) {
            int i3 = i2 + (i2 < 4 ? 8 : i2 >> 1);
            this.zzc = Arrays.copyOf(iArr, i3);
            this.zzd = Arrays.copyOf(this.zzd, i3);
        }
        int[] iArr2 = this.zzc;
        int i4 = this.zzb;
        iArr2[i4] = i;
        this.zzd[i4] = obj;
        this.zzb = i4 + 1;
    }

    public final void zzi(zznf zznfVar) throws IOException {
        if (this.zzb != 0) {
            for (int i = 0; i < this.zzb; i++) {
                int i2 = this.zzc[i];
                Object obj = this.zzd[i];
                int i3 = i2 >>> 3;
                int i4 = i2 & 7;
                if (i4 != 5) {
                    switch (i4) {
                        case 0:
                            zznfVar.zzt(i3, ((Long) obj).longValue());
                            break;
                        case 1:
                            zznfVar.zzm(i3, ((Long) obj).longValue());
                            break;
                        case 2:
                            zznfVar.zzd(i3, (zzjd) obj);
                            break;
                        case 3:
                            zznfVar.zzE(i3);
                            ((zzmo) obj).zzi(zznfVar);
                            zznfVar.zzh(i3);
                            break;
                        default:
                            throw new RuntimeException(zzko.zza());
                    }
                } else {
                    zznfVar.zzk(i3, ((Integer) obj).intValue());
                }
            }
        }
    }
}
