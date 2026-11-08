package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzoz;
import com.google.android.gms.internal.measurement.zzpf;
import com.kwad.sdk.collector.AppStatusRules;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzef extends zzf {
    private String zza;
    private String zzb;
    private int zzc;
    private String zzd;
    private String zze;
    private long zzf;
    private final long zzg;
    private List zzh;
    private String zzi;
    private int zzj;
    private String zzk;
    private String zzl;
    private String zzm;
    private long zzn;
    private String zzo;

    zzef(zzfy zzfyVar, long j) {
        super(zzfyVar);
        this.zzn = 0L;
        this.zzo = null;
        this.zzg = j;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0197 A[Catch: IllegalStateException -> 0x01de, TryCatch #0 {IllegalStateException -> 0x01de, blocks: (B:43:0x0173, B:47:0x018f, B:49:0x0197, B:53:0x01b5, B:52:0x01b1, B:55:0x01bf, B:57:0x01d5, B:59:0x01da, B:58:0x01d8), top: B:82:0x0173 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01bf A[Catch: IllegalStateException -> 0x01de, TryCatch #0 {IllegalStateException -> 0x01de, blocks: (B:43:0x0173, B:47:0x018f, B:49:0x0197, B:53:0x01b5, B:52:0x01b1, B:55:0x01bf, B:57:0x01d5, B:59:0x01da, B:58:0x01d8), top: B:82:0x0173 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x024f  */
    @Override // com.google.android.gms.measurement.internal.zzf
    @org.checkerframework.checker.nullness.qual.EnsuresNonNull({"appId", "appStore", "appName", "gmpAppId", "gaAppId"})
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected final void zzd() throws android.content.res.Resources.NotFoundException, android.content.pm.PackageManager.NameNotFoundException {
        /*
            Method dump skipped, instructions count: 614
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzef.zzd():void");
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    protected final boolean zzf() {
        return true;
    }

    @WorkerThread
    final int zzh() {
        zza();
        return this.zzj;
    }

    @WorkerThread
    final int zzi() {
        zza();
        return this.zzc;
    }

    @WorkerThread
    final zzq zzj(String str) throws IllegalAccessException, NoSuchAlgorithmException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        String str2;
        Class<?> clsLoadClass;
        Object objInvoke;
        String str3;
        long jMin;
        long j;
        List list;
        String str4;
        zzg();
        String strZzl = zzl();
        String strZzm = zzm();
        zza();
        String str5 = this.zzb;
        zza();
        long j2 = this.zzc;
        zza();
        Preconditions.checkNotNull(this.zzd);
        String str6 = this.zzd;
        this.zzs.zzf().zzh();
        zza();
        zzg();
        long j3 = this.zzf;
        if (j3 == 0) {
            zzlh zzlhVarZzv = this.zzs.zzv();
            Context contextZzau = this.zzs.zzau();
            String packageName = this.zzs.zzau().getPackageName();
            zzlhVarZzv.zzg();
            Preconditions.checkNotNull(contextZzau);
            Preconditions.checkNotEmpty(packageName);
            PackageManager packageManager = contextZzau.getPackageManager();
            MessageDigest messageDigestZzF = zzlh.zzF();
            long jZzp = -1;
            if (messageDigestZzF == null) {
                zzlhVarZzv.zzs.zzay().zzd().zza("Could not get MD5 instance");
            } else {
                if (packageManager != null) {
                    try {
                        if (zzlhVarZzv.zzag(contextZzau, packageName)) {
                            jZzp = 0;
                        } else {
                            PackageInfo packageInfo = Wrappers.packageManager(contextZzau).getPackageInfo(zzlhVarZzv.zzs.zzau().getPackageName(), 64);
                            if (packageInfo.signatures == null || packageInfo.signatures.length <= 0) {
                                zzlhVarZzv.zzs.zzay().zzk().zza("Could not get signatures");
                            } else {
                                jZzp = zzlh.zzp(messageDigestZzF.digest(packageInfo.signatures[0].toByteArray()));
                            }
                        }
                    } catch (PackageManager.NameNotFoundException e) {
                        zzlhVarZzv.zzs.zzay().zzd().zzb("Package name not found", e);
                    }
                }
                j3 = 0;
                this.zzf = j3;
            }
            j3 = jZzp;
            this.zzf = j3;
        }
        long j4 = j3;
        boolean zZzJ = this.zzs.zzJ();
        boolean z = !this.zzs.zzm().zzk;
        zzg();
        if (this.zzs.zzJ()) {
            zzpf.zzc();
            if (this.zzs.zzf().zzs(null, zzeb.zzaa)) {
                this.zzs.zzay().zzj().zza("Disabled IID for tests.");
            } else {
                try {
                    clsLoadClass = this.zzs.zzau().getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
                } catch (ClassNotFoundException unused) {
                }
                if (clsLoadClass != null) {
                    try {
                        objInvoke = clsLoadClass.getDeclaredMethod("getInstance", Context.class).invoke(null, this.zzs.zzau());
                    } catch (Exception unused2) {
                        this.zzs.zzay().zzm().zza("Failed to obtain Firebase Analytics instance");
                    }
                    if (objInvoke == null) {
                        str2 = null;
                    } else {
                        try {
                            str2 = (String) clsLoadClass.getDeclaredMethod("getFirebaseInstanceId", new Class[0]).invoke(objInvoke, new Object[0]);
                        } catch (Exception unused3) {
                            this.zzs.zzay().zzl().zza("Failed to retrieve Firebase Instance Id");
                        }
                    }
                }
            }
            str2 = null;
        } else {
            str2 = null;
        }
        zzfy zzfyVar = this.zzs;
        long jZza = zzfyVar.zzm().zzc.zza();
        if (jZza == 0) {
            str3 = strZzl;
            jMin = zzfyVar.zzc;
        } else {
            str3 = strZzl;
            jMin = Math.min(zzfyVar.zzc, jZza);
        }
        zza();
        int i = this.zzj;
        boolean zZzr = this.zzs.zzf().zzr();
        zzfd zzfdVarZzm = this.zzs.zzm();
        zzfdVarZzm.zzg();
        boolean z2 = zzfdVarZzm.zza().getBoolean("deferred_analytics_collection", false);
        zza();
        String str7 = this.zzl;
        Boolean boolValueOf = this.zzs.zzf().zzk("google_analytics_default_allow_ad_personalization_signals") == null ? null : Boolean.valueOf(!r2.booleanValue());
        long j5 = this.zzg;
        List list2 = this.zzh;
        String strZzh = this.zzs.zzm().zzc().zzh();
        if (this.zzi == null) {
            j = j5;
            if (this.zzs.zzf().zzs(null, zzeb.zzax)) {
                this.zzi = this.zzs.zzv().zzC();
            } else {
                this.zzi = "";
            }
        } else {
            j = j5;
        }
        String str8 = this.zzi;
        zzoz.zzc();
        if (this.zzs.zzf().zzs(null, zzeb.zzas)) {
            zzg();
            if (this.zzn == 0) {
                list = list2;
            } else {
                list = list2;
                long jCurrentTimeMillis = this.zzs.zzav().currentTimeMillis() - this.zzn;
                if (this.zzm != null && jCurrentTimeMillis > AppStatusRules.DEFAULT_START_TIME && this.zzo == null) {
                    zzo();
                }
            }
            if (this.zzm == null) {
                zzo();
            }
            str4 = this.zzm;
        } else {
            list = list2;
            str4 = null;
        }
        return new zzq(str3, strZzm, str5, j2, str6, 73000L, j4, str, zZzJ, z, str2, 0L, jMin, i, zZzr, z2, str7, boolValueOf, j, list, (String) null, strZzh, str8, str4);
    }

    @WorkerThread
    final String zzk() {
        zza();
        return this.zzl;
    }

    @WorkerThread
    final String zzl() {
        zza();
        Preconditions.checkNotNull(this.zza);
        return this.zza;
    }

    @WorkerThread
    final String zzm() {
        zzg();
        zza();
        Preconditions.checkNotNull(this.zzk);
        return this.zzk;
    }

    @WorkerThread
    final List zzn() {
        return this.zzh;
    }

    @WorkerThread
    final void zzo() {
        String str;
        zzg();
        if (this.zzs.zzm().zzc().zzi(zzah.ANALYTICS_STORAGE)) {
            byte[] bArr = new byte[16];
            this.zzs.zzv().zzG().nextBytes(bArr);
            str = String.format(Locale.US, "%032x", new BigInteger(1, bArr));
        } else {
            this.zzs.zzay().zzc().zza("Analytics Storage consent is not granted");
            str = null;
        }
        zzem zzemVarZzc = this.zzs.zzay().zzc();
        Object[] objArr = new Object[1];
        objArr[0] = str == null ? "null" : "not null";
        zzemVarZzc.zza(String.format("Resetting session stitching token to %s", objArr));
        this.zzm = str;
        this.zzn = this.zzs.zzav().currentTimeMillis();
    }

    final boolean zzp(String str) {
        String str2 = this.zzo;
        boolean z = false;
        if (str2 != null && !str2.equals(str)) {
            z = true;
        }
        this.zzo = str;
        return z;
    }
}
