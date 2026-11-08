package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.PatternSyntaxException;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
abstract class zzy {
    final String zzb;
    final int zzc;
    Boolean zzd;
    Boolean zze;
    Long zzf;
    Long zzg;

    zzy(String str, int i) {
        this.zzb = str;
        this.zzc = i;
    }

    private static Boolean zzd(String str, int i, boolean z, String str2, List list, String str3, zzeo zzeoVar) {
        if (i == 7) {
            if (list == null || list.isEmpty()) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!z && i != 2) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (i - 1) {
            case 1:
                if (str3 != null) {
                    try {
                        break;
                    } catch (PatternSyntaxException unused) {
                        if (zzeoVar != null) {
                            zzeoVar.zzk().zzb("Invalid regular expression in REGEXP audience filter. expression", str3);
                        }
                        return null;
                    }
                }
                break;
            case 6:
                if (list != null) {
                    break;
                }
                break;
        }
        return null;
    }

    @VisibleForTesting
    static Boolean zze(BigDecimal bigDecimal, com.google.android.gms.internal.measurement.zzeq zzeqVar, double d) {
        BigDecimal bigDecimal2;
        BigDecimal bigDecimal3;
        BigDecimal bigDecimal4;
        Preconditions.checkNotNull(zzeqVar);
        if (zzeqVar.zzg()) {
            if (zzeqVar.zzm() != 1) {
                if (zzeqVar.zzm() == 5) {
                    if (!zzeqVar.zzk() || !zzeqVar.zzj()) {
                        return null;
                    }
                } else if (!zzeqVar.zzh()) {
                    return null;
                }
                int iZzm = zzeqVar.zzm();
                if (zzeqVar.zzm() == 5) {
                    if (!zzlb.zzx(zzeqVar.zze()) || !zzlb.zzx(zzeqVar.zzd())) {
                        return null;
                    }
                    try {
                        bigDecimal4 = new BigDecimal(zzeqVar.zze());
                        bigDecimal2 = new BigDecimal(zzeqVar.zzd());
                        bigDecimal3 = null;
                    } catch (NumberFormatException unused) {
                        return null;
                    }
                } else {
                    if (!zzlb.zzx(zzeqVar.zzc())) {
                        return null;
                    }
                    try {
                        bigDecimal2 = null;
                        bigDecimal3 = new BigDecimal(zzeqVar.zzc());
                        bigDecimal4 = null;
                    } catch (NumberFormatException unused2) {
                        return null;
                    }
                }
                if (iZzm == 5) {
                    if (bigDecimal4 == null) {
                        return null;
                    }
                } else if (bigDecimal3 == null) {
                    return null;
                }
                boolean z = false;
                switch (iZzm - 1) {
                    case 1:
                        if (bigDecimal3 == null) {
                            return null;
                        }
                        return Boolean.valueOf(bigDecimal.compareTo(bigDecimal3) < 0);
                    case 2:
                        if (bigDecimal3 == null) {
                            return null;
                        }
                        return Boolean.valueOf(bigDecimal.compareTo(bigDecimal3) > 0);
                    case 3:
                        if (bigDecimal3 == null) {
                            return null;
                        }
                        if (d == 0.0d) {
                            return Boolean.valueOf(bigDecimal.compareTo(bigDecimal3) == 0);
                        }
                        if (bigDecimal.compareTo(bigDecimal3.subtract(new BigDecimal(d).multiply(new BigDecimal(2)))) > 0 && bigDecimal.compareTo(bigDecimal3.add(new BigDecimal(d).multiply(new BigDecimal(2)))) < 0) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 4:
                        if (bigDecimal4 == null) {
                            return null;
                        }
                        if (bigDecimal.compareTo(bigDecimal4) >= 0 && bigDecimal.compareTo(bigDecimal2) <= 0) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    default:
                        return null;
                }
            }
        }
        return null;
    }

    @VisibleForTesting
    static Boolean zzf(String str, com.google.android.gms.internal.measurement.zzex zzexVar, zzeo zzeoVar) {
        List list;
        Preconditions.checkNotNull(zzexVar);
        if (str == null || !zzexVar.zzi() || zzexVar.zzj() == 1) {
            return null;
        }
        if (zzexVar.zzj() == 7) {
            if (zzexVar.zza() == 0) {
                return null;
            }
        } else if (!zzexVar.zzh()) {
            return null;
        }
        int iZzj = zzexVar.zzj();
        boolean zZzf = zzexVar.zzf();
        String strZzd = (zZzf || iZzj == 2 || iZzj == 7) ? zzexVar.zzd() : zzexVar.zzd().toUpperCase(Locale.ENGLISH);
        if (zzexVar.zza() == 0) {
            list = null;
        } else {
            List listZze = zzexVar.zze();
            if (!zZzf) {
                ArrayList arrayList = new ArrayList(listZze.size());
                Iterator it = listZze.iterator();
                while (it.hasNext()) {
                    arrayList.add(((String) it.next()).toUpperCase(Locale.ENGLISH));
                }
                listZze = Collections.unmodifiableList(arrayList);
            }
            list = listZze;
        }
        return zzd(str, iZzj, zZzf, strZzd, list, iZzj == 2 ? strZzd : null, zzeoVar);
    }

    static Boolean zzg(double d, com.google.android.gms.internal.measurement.zzeq zzeqVar) {
        try {
            return zze(new BigDecimal(d), zzeqVar, Math.ulp(d));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    static Boolean zzh(long j, com.google.android.gms.internal.measurement.zzeq zzeqVar) {
        try {
            return zze(new BigDecimal(j), zzeqVar, 0.0d);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    static Boolean zzi(String str, com.google.android.gms.internal.measurement.zzeq zzeqVar) {
        if (!zzlb.zzx(str)) {
            return null;
        }
        try {
            return zze(new BigDecimal(str), zzeqVar, 0.0d);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    @VisibleForTesting
    static Boolean zzj(Boolean bool, boolean z) {
        if (bool == null) {
            return null;
        }
        return Boolean.valueOf(bool.booleanValue() != z);
    }

    abstract int zza();

    abstract boolean zzb();

    abstract boolean zzc();
}
