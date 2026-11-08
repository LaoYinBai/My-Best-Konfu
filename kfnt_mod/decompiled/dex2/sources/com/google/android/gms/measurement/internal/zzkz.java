package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzns;
import com.google.android.gms.internal.measurement.zzoh;
import com.google.android.gms.internal.measurement.zzow;
import com.google.android.gms.internal.measurement.zzoz;
import com.kwad.v8.Platform;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzkz implements zzgt {
    private static volatile zzkz zzb;
    private long zzA;
    private final Map zzB;
    private final Map zzC;
    private zzik zzD;
    private String zzE;

    @VisibleForTesting
    long zza;
    private final zzfp zzc;
    private final zzeu zzd;
    private zzam zze;
    private zzew zzf;
    private zzkl zzg;
    private zzaa zzh;
    private final zzlb zzi;
    private zzii zzj;
    private zzju zzk;
    private final zzko zzl;
    private zzfg zzm;
    private final zzfy zzn;
    private boolean zzp;
    private List zzq;
    private int zzr;
    private int zzs;
    private boolean zzt;
    private boolean zzu;
    private boolean zzv;
    private FileLock zzw;
    private FileChannel zzx;
    private List zzy;
    private List zzz;
    private boolean zzo = false;
    private final zzlg zzF = new zzku(this);

    zzkz(zzla zzlaVar, zzfy zzfyVar) throws IllegalStateException {
        Preconditions.checkNotNull(zzlaVar);
        this.zzn = zzfy.zzp(zzlaVar.zza, null, null);
        this.zzA = -1L;
        this.zzl = new zzko(this);
        zzlb zzlbVar = new zzlb(this);
        zzlbVar.zzX();
        this.zzi = zzlbVar;
        zzeu zzeuVar = new zzeu(this);
        zzeuVar.zzX();
        this.zzd = zzeuVar;
        zzfp zzfpVar = new zzfp(this);
        zzfpVar.zzX();
        this.zzc = zzfpVar;
        this.zzB = new HashMap();
        this.zzC = new HashMap();
        zzaz().zzp(new zzkp(this, zzlaVar));
    }

    @VisibleForTesting
    static final void zzaa(com.google.android.gms.internal.measurement.zzfr zzfrVar, int i, String str) {
        List listZzp = zzfrVar.zzp();
        for (int i2 = 0; i2 < listZzp.size(); i2++) {
            if ("_err".equals(((com.google.android.gms.internal.measurement.zzfw) listZzp.get(i2)).zzg())) {
                return;
            }
        }
        com.google.android.gms.internal.measurement.zzfv zzfvVarZze = com.google.android.gms.internal.measurement.zzfw.zze();
        zzfvVarZze.zzj("_err");
        zzfvVarZze.zzi(Long.valueOf(i).longValue());
        com.google.android.gms.internal.measurement.zzfw zzfwVar = (com.google.android.gms.internal.measurement.zzfw) zzfvVarZze.zzaE();
        com.google.android.gms.internal.measurement.zzfv zzfvVarZze2 = com.google.android.gms.internal.measurement.zzfw.zze();
        zzfvVarZze2.zzj("_ev");
        zzfvVarZze2.zzk(str);
        com.google.android.gms.internal.measurement.zzfw zzfwVar2 = (com.google.android.gms.internal.measurement.zzfw) zzfvVarZze2.zzaE();
        zzfrVar.zzf(zzfwVar);
        zzfrVar.zzf(zzfwVar2);
    }

    @VisibleForTesting
    static final void zzab(com.google.android.gms.internal.measurement.zzfr zzfrVar, @NonNull String str) {
        List listZzp = zzfrVar.zzp();
        for (int i = 0; i < listZzp.size(); i++) {
            if (str.equals(((com.google.android.gms.internal.measurement.zzfw) listZzp.get(i)).zzg())) {
                zzfrVar.zzh(i);
                return;
            }
        }
    }

    @WorkerThread
    private final zzq zzac(String str) {
        zzam zzamVar = this.zze;
        zzal(zzamVar);
        zzh zzhVarZzj = zzamVar.zzj(str);
        if (zzhVarZzj == null || TextUtils.isEmpty(zzhVarZzj.zzw())) {
            zzay().zzc().zzb("No app data available; dropping", str);
            return null;
        }
        Boolean boolZzad = zzad(zzhVarZzj);
        if (boolZzad != null && !boolZzad.booleanValue()) {
            zzay().zzd().zzb("App version does not match; dropping. appId", zzeo.zzn(str));
            return null;
        }
        String strZzy = zzhVarZzj.zzy();
        String strZzw = zzhVarZzj.zzw();
        long jZzb = zzhVarZzj.zzb();
        String strZzv = zzhVarZzj.zzv();
        long jZzm = zzhVarZzj.zzm();
        long jZzj = zzhVarZzj.zzj();
        boolean zZzai = zzhVarZzj.zzai();
        String strZzx = zzhVarZzj.zzx();
        zzhVarZzj.zza();
        return new zzq(str, strZzy, strZzw, jZzb, strZzv, jZzm, jZzj, (String) null, zZzai, false, strZzx, 0L, 0L, 0, zzhVarZzj.zzah(), false, zzhVarZzj.zzr(), zzhVarZzj.zzq(), zzhVarZzj.zzk(), zzhVarZzj.zzC(), (String) null, zzh(str).zzh(), "", (String) null);
    }

    @WorkerThread
    private final Boolean zzad(zzh zzhVar) {
        try {
            if (zzhVar.zzb() != -2147483648L) {
                if (zzhVar.zzb() == Wrappers.packageManager(this.zzn.zzau()).getPackageInfo(zzhVar.zzt(), 0).versionCode) {
                    return true;
                }
            } else {
                String str = Wrappers.packageManager(this.zzn.zzau()).getPackageInfo(zzhVar.zzt(), 0).versionName;
                String strZzw = zzhVar.zzw();
                if (strZzw != null && strZzw.equals(str)) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    @WorkerThread
    private final void zzae() {
        zzaz().zzg();
        if (this.zzt || this.zzu || this.zzv) {
            zzay().zzj().zzd("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzt), Boolean.valueOf(this.zzu), Boolean.valueOf(this.zzv));
            return;
        }
        zzay().zzj().zza("Stopping uploading service(s)");
        List list = this.zzq;
        if (list == null) {
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
        ((List) Preconditions.checkNotNull(this.zzq)).clear();
    }

    @VisibleForTesting
    private final void zzaf(com.google.android.gms.internal.measurement.zzgb zzgbVar, long j, boolean z) {
        String str = true != z ? "_lte" : "_se";
        zzam zzamVar = this.zze;
        zzal(zzamVar);
        zzle zzleVarZzp = zzamVar.zzp(zzgbVar.zzap(), str);
        zzle zzleVar = (zzleVarZzp == null || zzleVarZzp.zze == null) ? new zzle(zzgbVar.zzap(), "auto", str, zzav().currentTimeMillis(), Long.valueOf(j)) : new zzle(zzgbVar.zzap(), "auto", str, zzav().currentTimeMillis(), Long.valueOf(((Long) zzleVarZzp.zze).longValue() + j));
        com.google.android.gms.internal.measurement.zzgk zzgkVarZzd = com.google.android.gms.internal.measurement.zzgl.zzd();
        zzgkVarZzd.zzf(str);
        zzgkVarZzd.zzg(zzav().currentTimeMillis());
        zzgkVarZzd.zze(((Long) zzleVar.zze).longValue());
        com.google.android.gms.internal.measurement.zzgl zzglVar = (com.google.android.gms.internal.measurement.zzgl) zzgkVarZzd.zzaE();
        int iZza = zzlb.zza(zzgbVar, str);
        if (iZza >= 0) {
            zzgbVar.zzam(iZza, zzglVar);
        } else {
            zzgbVar.zzm(zzglVar);
        }
        if (j > 0) {
            zzam zzamVar2 = this.zze;
            zzal(zzamVar2);
            zzamVar2.zzL(zzleVar);
            zzay().zzj().zzc("Updated engagement user property. scope, value", true != z ? "lifetime" : "session-scoped", zzleVar.zze);
        }
    }

    @WorkerThread
    private final void zzag() {
        long jMax;
        long jMax2;
        zzaz().zzg();
        zzB();
        if (this.zza > 0) {
            long jAbs = 3600000 - Math.abs(zzav().elapsedRealtime() - this.zza);
            if (jAbs > 0) {
                zzay().zzj().zzb("Upload has been suspended. Will update scheduling later in approximately ms", Long.valueOf(jAbs));
                zzm().zzc();
                zzkl zzklVar = this.zzg;
                zzal(zzklVar);
                zzklVar.zza();
                return;
            }
            this.zza = 0L;
        }
        if (!this.zzn.zzM() || !zzai()) {
            zzay().zzj().zza("Nothing to upload or uploading impossible");
            zzm().zzc();
            zzkl zzklVar2 = this.zzg;
            zzal(zzklVar2);
            zzklVar2.zza();
            return;
        }
        long jCurrentTimeMillis = zzav().currentTimeMillis();
        zzg();
        long jMax3 = Math.max(0L, ((Long) zzeb.zzz.zza(null)).longValue());
        zzam zzamVar = this.zze;
        zzal(zzamVar);
        boolean z = true;
        if (!zzamVar.zzH()) {
            zzam zzamVar2 = this.zze;
            zzal(zzamVar2);
            if (!zzamVar2.zzG()) {
                z = false;
            }
        }
        if (z) {
            String strZzl = zzg().zzl();
            if (TextUtils.isEmpty(strZzl) || ".none.".equals(strZzl)) {
                zzg();
                jMax = Math.max(0L, ((Long) zzeb.zzt.zza(null)).longValue());
            } else {
                zzg();
                jMax = Math.max(0L, ((Long) zzeb.zzu.zza(null)).longValue());
            }
        } else {
            zzg();
            jMax = Math.max(0L, ((Long) zzeb.zzs.zza(null)).longValue());
        }
        long jZza = this.zzk.zzc.zza();
        long jZza2 = this.zzk.zzd.zza();
        zzam zzamVar3 = this.zze;
        zzal(zzamVar3);
        boolean z2 = z;
        long jZzd = zzamVar3.zzd();
        zzam zzamVar4 = this.zze;
        zzal(zzamVar4);
        long jMax4 = Math.max(jZzd, zzamVar4.zze());
        if (jMax4 == 0) {
            jMax2 = 0;
        } else {
            long jAbs2 = jCurrentTimeMillis - Math.abs(jMax4 - jCurrentTimeMillis);
            long jAbs3 = Math.abs(jZza - jCurrentTimeMillis);
            long jAbs4 = jCurrentTimeMillis - Math.abs(jZza2 - jCurrentTimeMillis);
            long jMax5 = Math.max(jCurrentTimeMillis - jAbs3, jAbs4);
            long jMin = jAbs2 + jMax3;
            if (z2 && jMax5 > 0) {
                jMin = Math.min(jAbs2, jMax5) + jMax;
            }
            zzlb zzlbVar = this.zzi;
            zzal(zzlbVar);
            jMax2 = !zzlbVar.zzw(jMax5, jMax) ? jMax5 + jMax : jMin;
            if (jAbs4 != 0 && jAbs4 >= jAbs2) {
                long jMax6 = jMax2;
                int i = 0;
                while (true) {
                    zzg();
                    if (i >= Math.min(20, Math.max(0, ((Integer) zzeb.zzB.zza(null)).intValue()))) {
                        break;
                    }
                    zzg();
                    jMax6 += Math.max(0L, ((Long) zzeb.zzA.zza(null)).longValue()) * (1 << i);
                    if (jMax6 > jAbs4) {
                        jMax2 = jMax6;
                        break;
                    }
                    i++;
                }
                jMax2 = 0;
            }
        }
        if (jMax2 == 0) {
            zzay().zzj().zza("Next upload time is 0");
            zzm().zzc();
            zzkl zzklVar3 = this.zzg;
            zzal(zzklVar3);
            zzklVar3.zza();
            return;
        }
        zzeu zzeuVar = this.zzd;
        zzal(zzeuVar);
        if (!zzeuVar.zza()) {
            zzay().zzj().zza("No network");
            zzm().zzb();
            zzkl zzklVar4 = this.zzg;
            zzal(zzklVar4);
            zzklVar4.zza();
            return;
        }
        long jZza3 = this.zzk.zzb.zza();
        zzg();
        long jMax7 = Math.max(0L, ((Long) zzeb.zzq.zza(null)).longValue());
        zzlb zzlbVar2 = this.zzi;
        zzal(zzlbVar2);
        if (!zzlbVar2.zzw(jZza3, jMax7)) {
            jMax2 = Math.max(jMax2, jZza3 + jMax7);
        }
        zzm().zzc();
        long jCurrentTimeMillis2 = jMax2 - zzav().currentTimeMillis();
        if (jCurrentTimeMillis2 <= 0) {
            zzg();
            jCurrentTimeMillis2 = Math.max(0L, ((Long) zzeb.zzv.zza(null)).longValue());
            this.zzk.zzc.zzb(zzav().currentTimeMillis());
        }
        zzay().zzj().zzb("Upload scheduled in approximately ms", Long.valueOf(jCurrentTimeMillis2));
        zzkl zzklVar5 = this.zzg;
        zzal(zzklVar5);
        zzklVar5.zzd(jCurrentTimeMillis2);
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x036d A[Catch: all -> 0x0cf4, TryCatch #0 {all -> 0x0cf4, blocks: (B:3:0x000a, B:5:0x0022, B:8:0x002a, B:9:0x003c, B:11:0x0046, B:13:0x0069, B:15:0x009f, B:18:0x00b1, B:20:0x00bd, B:162:0x0521, B:22:0x00df, B:24:0x00ef, B:27:0x0111, B:29:0x0117, B:31:0x0127, B:33:0x0135, B:35:0x0145, B:36:0x0152, B:37:0x0155, B:39:0x016a, B:103:0x03a0, B:104:0x03ac, B:106:0x03b2, B:112:0x03d9, B:109:0x03c6, B:134:0x0460, B:136:0x046e, B:139:0x0483, B:141:0x0496, B:143:0x04a2, B:161:0x050b, B:148:0x04b9, B:150:0x04c5, B:153:0x04da, B:155:0x04ed, B:157:0x04f9, B:116:0x03e1, B:118:0x03ed, B:120:0x03f9, B:132:0x0442, B:124:0x041a, B:127:0x042c, B:129:0x0432, B:131:0x043c, B:60:0x01bc, B:62:0x01c2, B:64:0x01d4, B:69:0x0226, B:65:0x01f4, B:67:0x0208, B:73:0x023d, B:75:0x026b, B:76:0x0297, B:78:0x02cd, B:80:0x02d6, B:83:0x02e2, B:85:0x0318, B:86:0x0333, B:88:0x0339, B:90:0x0349, B:94:0x035f, B:91:0x0352, B:97:0x0366, B:100:0x036d, B:101:0x0387, B:165:0x0531, B:167:0x0541, B:169:0x054e, B:180:0x0583, B:170:0x0556, B:172:0x0563, B:174:0x0569, B:177:0x0575, B:179:0x057d, B:181:0x0586, B:182:0x0592, B:184:0x0598, B:186:0x05aa, B:187:0x05b8, B:189:0x05c0, B:193:0x05e7, B:195:0x060c, B:197:0x061d, B:199:0x0623, B:201:0x062f, B:202:0x0660, B:204:0x0666, B:206:0x0676, B:207:0x067a, B:208:0x067d, B:209:0x0680, B:210:0x068e, B:212:0x0694, B:214:0x06a4, B:215:0x06ab, B:217:0x06b7, B:218:0x06be, B:219:0x06c1, B:221:0x06ff, B:222:0x0712, B:224:0x0718, B:226:0x072e, B:228:0x0749, B:230:0x0760, B:232:0x0765, B:234:0x0769, B:236:0x076d, B:238:0x0777, B:239:0x0783, B:241:0x0787, B:243:0x078d, B:244:0x079d, B:245:0x07a6, B:313:0x09ec, B:247:0x07b2, B:249:0x07c9, B:255:0x07e4, B:257:0x0804, B:258:0x080c, B:260:0x0812, B:262:0x0824, B:269:0x084d, B:270:0x0870, B:272:0x087c, B:274:0x0893, B:276:0x08d2, B:280:0x08ea, B:282:0x08f1, B:284:0x0900, B:286:0x0904, B:288:0x0908, B:290:0x090c, B:291:0x0918, B:292:0x091d, B:294:0x0923, B:296:0x0941, B:297:0x0946, B:312:0x09e9, B:298:0x095e, B:300:0x0962, B:304:0x0989, B:306:0x09b7, B:307:0x09c1, B:308:0x09d1, B:310:0x09d9, B:301:0x096f, B:267:0x0838, B:253:0x07d0, B:314:0x09f7, B:316:0x0a03, B:317:0x0a09, B:318:0x0a11, B:320:0x0a17, B:322:0x0a2e, B:324:0x0a3f, B:344:0x0ab3, B:346:0x0ab9, B:348:0x0ad1, B:351:0x0ad8, B:356:0x0b07, B:358:0x0b4a, B:361:0x0b80, B:362:0x0b84, B:363:0x0b8f, B:365:0x0bd2, B:366:0x0bdf, B:368:0x0bee, B:372:0x0c08, B:374:0x0c21, B:360:0x0b5d, B:352:0x0ae0, B:354:0x0aec, B:355:0x0af0, B:375:0x0c39, B:376:0x0c51, B:379:0x0c59, B:380:0x0c5e, B:381:0x0c6e, B:383:0x0c88, B:384:0x0ca3, B:386:0x0cad, B:391:0x0cd0, B:390:0x0cbd, B:325:0x0a57, B:327:0x0a5d, B:329:0x0a67, B:331:0x0a6e, B:337:0x0a7e, B:339:0x0a85, B:341:0x0aa4, B:343:0x0aab, B:342:0x0aa8, B:338:0x0a82, B:330:0x0a6b, B:190:0x05c5, B:192:0x05cd, B:394:0x0ce2), top: B:400:0x000a, inners: #1, #2, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0387 A[Catch: all -> 0x0cf4, TryCatch #0 {all -> 0x0cf4, blocks: (B:3:0x000a, B:5:0x0022, B:8:0x002a, B:9:0x003c, B:11:0x0046, B:13:0x0069, B:15:0x009f, B:18:0x00b1, B:20:0x00bd, B:162:0x0521, B:22:0x00df, B:24:0x00ef, B:27:0x0111, B:29:0x0117, B:31:0x0127, B:33:0x0135, B:35:0x0145, B:36:0x0152, B:37:0x0155, B:39:0x016a, B:103:0x03a0, B:104:0x03ac, B:106:0x03b2, B:112:0x03d9, B:109:0x03c6, B:134:0x0460, B:136:0x046e, B:139:0x0483, B:141:0x0496, B:143:0x04a2, B:161:0x050b, B:148:0x04b9, B:150:0x04c5, B:153:0x04da, B:155:0x04ed, B:157:0x04f9, B:116:0x03e1, B:118:0x03ed, B:120:0x03f9, B:132:0x0442, B:124:0x041a, B:127:0x042c, B:129:0x0432, B:131:0x043c, B:60:0x01bc, B:62:0x01c2, B:64:0x01d4, B:69:0x0226, B:65:0x01f4, B:67:0x0208, B:73:0x023d, B:75:0x026b, B:76:0x0297, B:78:0x02cd, B:80:0x02d6, B:83:0x02e2, B:85:0x0318, B:86:0x0333, B:88:0x0339, B:90:0x0349, B:94:0x035f, B:91:0x0352, B:97:0x0366, B:100:0x036d, B:101:0x0387, B:165:0x0531, B:167:0x0541, B:169:0x054e, B:180:0x0583, B:170:0x0556, B:172:0x0563, B:174:0x0569, B:177:0x0575, B:179:0x057d, B:181:0x0586, B:182:0x0592, B:184:0x0598, B:186:0x05aa, B:187:0x05b8, B:189:0x05c0, B:193:0x05e7, B:195:0x060c, B:197:0x061d, B:199:0x0623, B:201:0x062f, B:202:0x0660, B:204:0x0666, B:206:0x0676, B:207:0x067a, B:208:0x067d, B:209:0x0680, B:210:0x068e, B:212:0x0694, B:214:0x06a4, B:215:0x06ab, B:217:0x06b7, B:218:0x06be, B:219:0x06c1, B:221:0x06ff, B:222:0x0712, B:224:0x0718, B:226:0x072e, B:228:0x0749, B:230:0x0760, B:232:0x0765, B:234:0x0769, B:236:0x076d, B:238:0x0777, B:239:0x0783, B:241:0x0787, B:243:0x078d, B:244:0x079d, B:245:0x07a6, B:313:0x09ec, B:247:0x07b2, B:249:0x07c9, B:255:0x07e4, B:257:0x0804, B:258:0x080c, B:260:0x0812, B:262:0x0824, B:269:0x084d, B:270:0x0870, B:272:0x087c, B:274:0x0893, B:276:0x08d2, B:280:0x08ea, B:282:0x08f1, B:284:0x0900, B:286:0x0904, B:288:0x0908, B:290:0x090c, B:291:0x0918, B:292:0x091d, B:294:0x0923, B:296:0x0941, B:297:0x0946, B:312:0x09e9, B:298:0x095e, B:300:0x0962, B:304:0x0989, B:306:0x09b7, B:307:0x09c1, B:308:0x09d1, B:310:0x09d9, B:301:0x096f, B:267:0x0838, B:253:0x07d0, B:314:0x09f7, B:316:0x0a03, B:317:0x0a09, B:318:0x0a11, B:320:0x0a17, B:322:0x0a2e, B:324:0x0a3f, B:344:0x0ab3, B:346:0x0ab9, B:348:0x0ad1, B:351:0x0ad8, B:356:0x0b07, B:358:0x0b4a, B:361:0x0b80, B:362:0x0b84, B:363:0x0b8f, B:365:0x0bd2, B:366:0x0bdf, B:368:0x0bee, B:372:0x0c08, B:374:0x0c21, B:360:0x0b5d, B:352:0x0ae0, B:354:0x0aec, B:355:0x0af0, B:375:0x0c39, B:376:0x0c51, B:379:0x0c59, B:380:0x0c5e, B:381:0x0c6e, B:383:0x0c88, B:384:0x0ca3, B:386:0x0cad, B:391:0x0cd0, B:390:0x0cbd, B:325:0x0a57, B:327:0x0a5d, B:329:0x0a67, B:331:0x0a6e, B:337:0x0a7e, B:339:0x0a85, B:341:0x0aa4, B:343:0x0aab, B:342:0x0aa8, B:338:0x0a82, B:330:0x0a6b, B:190:0x05c5, B:192:0x05cd, B:394:0x0ce2), top: B:400:0x000a, inners: #1, #2, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:103:0x03a0 A[Catch: all -> 0x0cf4, TryCatch #0 {all -> 0x0cf4, blocks: (B:3:0x000a, B:5:0x0022, B:8:0x002a, B:9:0x003c, B:11:0x0046, B:13:0x0069, B:15:0x009f, B:18:0x00b1, B:20:0x00bd, B:162:0x0521, B:22:0x00df, B:24:0x00ef, B:27:0x0111, B:29:0x0117, B:31:0x0127, B:33:0x0135, B:35:0x0145, B:36:0x0152, B:37:0x0155, B:39:0x016a, B:103:0x03a0, B:104:0x03ac, B:106:0x03b2, B:112:0x03d9, B:109:0x03c6, B:134:0x0460, B:136:0x046e, B:139:0x0483, B:141:0x0496, B:143:0x04a2, B:161:0x050b, B:148:0x04b9, B:150:0x04c5, B:153:0x04da, B:155:0x04ed, B:157:0x04f9, B:116:0x03e1, B:118:0x03ed, B:120:0x03f9, B:132:0x0442, B:124:0x041a, B:127:0x042c, B:129:0x0432, B:131:0x043c, B:60:0x01bc, B:62:0x01c2, B:64:0x01d4, B:69:0x0226, B:65:0x01f4, B:67:0x0208, B:73:0x023d, B:75:0x026b, B:76:0x0297, B:78:0x02cd, B:80:0x02d6, B:83:0x02e2, B:85:0x0318, B:86:0x0333, B:88:0x0339, B:90:0x0349, B:94:0x035f, B:91:0x0352, B:97:0x0366, B:100:0x036d, B:101:0x0387, B:165:0x0531, B:167:0x0541, B:169:0x054e, B:180:0x0583, B:170:0x0556, B:172:0x0563, B:174:0x0569, B:177:0x0575, B:179:0x057d, B:181:0x0586, B:182:0x0592, B:184:0x0598, B:186:0x05aa, B:187:0x05b8, B:189:0x05c0, B:193:0x05e7, B:195:0x060c, B:197:0x061d, B:199:0x0623, B:201:0x062f, B:202:0x0660, B:204:0x0666, B:206:0x0676, B:207:0x067a, B:208:0x067d, B:209:0x0680, B:210:0x068e, B:212:0x0694, B:214:0x06a4, B:215:0x06ab, B:217:0x06b7, B:218:0x06be, B:219:0x06c1, B:221:0x06ff, B:222:0x0712, B:224:0x0718, B:226:0x072e, B:228:0x0749, B:230:0x0760, B:232:0x0765, B:234:0x0769, B:236:0x076d, B:238:0x0777, B:239:0x0783, B:241:0x0787, B:243:0x078d, B:244:0x079d, B:245:0x07a6, B:313:0x09ec, B:247:0x07b2, B:249:0x07c9, B:255:0x07e4, B:257:0x0804, B:258:0x080c, B:260:0x0812, B:262:0x0824, B:269:0x084d, B:270:0x0870, B:272:0x087c, B:274:0x0893, B:276:0x08d2, B:280:0x08ea, B:282:0x08f1, B:284:0x0900, B:286:0x0904, B:288:0x0908, B:290:0x090c, B:291:0x0918, B:292:0x091d, B:294:0x0923, B:296:0x0941, B:297:0x0946, B:312:0x09e9, B:298:0x095e, B:300:0x0962, B:304:0x0989, B:306:0x09b7, B:307:0x09c1, B:308:0x09d1, B:310:0x09d9, B:301:0x096f, B:267:0x0838, B:253:0x07d0, B:314:0x09f7, B:316:0x0a03, B:317:0x0a09, B:318:0x0a11, B:320:0x0a17, B:322:0x0a2e, B:324:0x0a3f, B:344:0x0ab3, B:346:0x0ab9, B:348:0x0ad1, B:351:0x0ad8, B:356:0x0b07, B:358:0x0b4a, B:361:0x0b80, B:362:0x0b84, B:363:0x0b8f, B:365:0x0bd2, B:366:0x0bdf, B:368:0x0bee, B:372:0x0c08, B:374:0x0c21, B:360:0x0b5d, B:352:0x0ae0, B:354:0x0aec, B:355:0x0af0, B:375:0x0c39, B:376:0x0c51, B:379:0x0c59, B:380:0x0c5e, B:381:0x0c6e, B:383:0x0c88, B:384:0x0ca3, B:386:0x0cad, B:391:0x0cd0, B:390:0x0cbd, B:325:0x0a57, B:327:0x0a5d, B:329:0x0a67, B:331:0x0a6e, B:337:0x0a7e, B:339:0x0a85, B:341:0x0aa4, B:343:0x0aab, B:342:0x0aa8, B:338:0x0a82, B:330:0x0a6b, B:190:0x05c5, B:192:0x05cd, B:394:0x0ce2), top: B:400:0x000a, inners: #1, #2, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:133:0x045f  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x046e A[Catch: all -> 0x0cf4, TryCatch #0 {all -> 0x0cf4, blocks: (B:3:0x000a, B:5:0x0022, B:8:0x002a, B:9:0x003c, B:11:0x0046, B:13:0x0069, B:15:0x009f, B:18:0x00b1, B:20:0x00bd, B:162:0x0521, B:22:0x00df, B:24:0x00ef, B:27:0x0111, B:29:0x0117, B:31:0x0127, B:33:0x0135, B:35:0x0145, B:36:0x0152, B:37:0x0155, B:39:0x016a, B:103:0x03a0, B:104:0x03ac, B:106:0x03b2, B:112:0x03d9, B:109:0x03c6, B:134:0x0460, B:136:0x046e, B:139:0x0483, B:141:0x0496, B:143:0x04a2, B:161:0x050b, B:148:0x04b9, B:150:0x04c5, B:153:0x04da, B:155:0x04ed, B:157:0x04f9, B:116:0x03e1, B:118:0x03ed, B:120:0x03f9, B:132:0x0442, B:124:0x041a, B:127:0x042c, B:129:0x0432, B:131:0x043c, B:60:0x01bc, B:62:0x01c2, B:64:0x01d4, B:69:0x0226, B:65:0x01f4, B:67:0x0208, B:73:0x023d, B:75:0x026b, B:76:0x0297, B:78:0x02cd, B:80:0x02d6, B:83:0x02e2, B:85:0x0318, B:86:0x0333, B:88:0x0339, B:90:0x0349, B:94:0x035f, B:91:0x0352, B:97:0x0366, B:100:0x036d, B:101:0x0387, B:165:0x0531, B:167:0x0541, B:169:0x054e, B:180:0x0583, B:170:0x0556, B:172:0x0563, B:174:0x0569, B:177:0x0575, B:179:0x057d, B:181:0x0586, B:182:0x0592, B:184:0x0598, B:186:0x05aa, B:187:0x05b8, B:189:0x05c0, B:193:0x05e7, B:195:0x060c, B:197:0x061d, B:199:0x0623, B:201:0x062f, B:202:0x0660, B:204:0x0666, B:206:0x0676, B:207:0x067a, B:208:0x067d, B:209:0x0680, B:210:0x068e, B:212:0x0694, B:214:0x06a4, B:215:0x06ab, B:217:0x06b7, B:218:0x06be, B:219:0x06c1, B:221:0x06ff, B:222:0x0712, B:224:0x0718, B:226:0x072e, B:228:0x0749, B:230:0x0760, B:232:0x0765, B:234:0x0769, B:236:0x076d, B:238:0x0777, B:239:0x0783, B:241:0x0787, B:243:0x078d, B:244:0x079d, B:245:0x07a6, B:313:0x09ec, B:247:0x07b2, B:249:0x07c9, B:255:0x07e4, B:257:0x0804, B:258:0x080c, B:260:0x0812, B:262:0x0824, B:269:0x084d, B:270:0x0870, B:272:0x087c, B:274:0x0893, B:276:0x08d2, B:280:0x08ea, B:282:0x08f1, B:284:0x0900, B:286:0x0904, B:288:0x0908, B:290:0x090c, B:291:0x0918, B:292:0x091d, B:294:0x0923, B:296:0x0941, B:297:0x0946, B:312:0x09e9, B:298:0x095e, B:300:0x0962, B:304:0x0989, B:306:0x09b7, B:307:0x09c1, B:308:0x09d1, B:310:0x09d9, B:301:0x096f, B:267:0x0838, B:253:0x07d0, B:314:0x09f7, B:316:0x0a03, B:317:0x0a09, B:318:0x0a11, B:320:0x0a17, B:322:0x0a2e, B:324:0x0a3f, B:344:0x0ab3, B:346:0x0ab9, B:348:0x0ad1, B:351:0x0ad8, B:356:0x0b07, B:358:0x0b4a, B:361:0x0b80, B:362:0x0b84, B:363:0x0b8f, B:365:0x0bd2, B:366:0x0bdf, B:368:0x0bee, B:372:0x0c08, B:374:0x0c21, B:360:0x0b5d, B:352:0x0ae0, B:354:0x0aec, B:355:0x0af0, B:375:0x0c39, B:376:0x0c51, B:379:0x0c59, B:380:0x0c5e, B:381:0x0c6e, B:383:0x0c88, B:384:0x0ca3, B:386:0x0cad, B:391:0x0cd0, B:390:0x0cbd, B:325:0x0a57, B:327:0x0a5d, B:329:0x0a67, B:331:0x0a6e, B:337:0x0a7e, B:339:0x0a85, B:341:0x0aa4, B:343:0x0aab, B:342:0x0aa8, B:338:0x0a82, B:330:0x0a6b, B:190:0x05c5, B:192:0x05cd, B:394:0x0ce2), top: B:400:0x000a, inners: #1, #2, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:148:0x04b9 A[Catch: all -> 0x0cf4, TryCatch #0 {all -> 0x0cf4, blocks: (B:3:0x000a, B:5:0x0022, B:8:0x002a, B:9:0x003c, B:11:0x0046, B:13:0x0069, B:15:0x009f, B:18:0x00b1, B:20:0x00bd, B:162:0x0521, B:22:0x00df, B:24:0x00ef, B:27:0x0111, B:29:0x0117, B:31:0x0127, B:33:0x0135, B:35:0x0145, B:36:0x0152, B:37:0x0155, B:39:0x016a, B:103:0x03a0, B:104:0x03ac, B:106:0x03b2, B:112:0x03d9, B:109:0x03c6, B:134:0x0460, B:136:0x046e, B:139:0x0483, B:141:0x0496, B:143:0x04a2, B:161:0x050b, B:148:0x04b9, B:150:0x04c5, B:153:0x04da, B:155:0x04ed, B:157:0x04f9, B:116:0x03e1, B:118:0x03ed, B:120:0x03f9, B:132:0x0442, B:124:0x041a, B:127:0x042c, B:129:0x0432, B:131:0x043c, B:60:0x01bc, B:62:0x01c2, B:64:0x01d4, B:69:0x0226, B:65:0x01f4, B:67:0x0208, B:73:0x023d, B:75:0x026b, B:76:0x0297, B:78:0x02cd, B:80:0x02d6, B:83:0x02e2, B:85:0x0318, B:86:0x0333, B:88:0x0339, B:90:0x0349, B:94:0x035f, B:91:0x0352, B:97:0x0366, B:100:0x036d, B:101:0x0387, B:165:0x0531, B:167:0x0541, B:169:0x054e, B:180:0x0583, B:170:0x0556, B:172:0x0563, B:174:0x0569, B:177:0x0575, B:179:0x057d, B:181:0x0586, B:182:0x0592, B:184:0x0598, B:186:0x05aa, B:187:0x05b8, B:189:0x05c0, B:193:0x05e7, B:195:0x060c, B:197:0x061d, B:199:0x0623, B:201:0x062f, B:202:0x0660, B:204:0x0666, B:206:0x0676, B:207:0x067a, B:208:0x067d, B:209:0x0680, B:210:0x068e, B:212:0x0694, B:214:0x06a4, B:215:0x06ab, B:217:0x06b7, B:218:0x06be, B:219:0x06c1, B:221:0x06ff, B:222:0x0712, B:224:0x0718, B:226:0x072e, B:228:0x0749, B:230:0x0760, B:232:0x0765, B:234:0x0769, B:236:0x076d, B:238:0x0777, B:239:0x0783, B:241:0x0787, B:243:0x078d, B:244:0x079d, B:245:0x07a6, B:313:0x09ec, B:247:0x07b2, B:249:0x07c9, B:255:0x07e4, B:257:0x0804, B:258:0x080c, B:260:0x0812, B:262:0x0824, B:269:0x084d, B:270:0x0870, B:272:0x087c, B:274:0x0893, B:276:0x08d2, B:280:0x08ea, B:282:0x08f1, B:284:0x0900, B:286:0x0904, B:288:0x0908, B:290:0x090c, B:291:0x0918, B:292:0x091d, B:294:0x0923, B:296:0x0941, B:297:0x0946, B:312:0x09e9, B:298:0x095e, B:300:0x0962, B:304:0x0989, B:306:0x09b7, B:307:0x09c1, B:308:0x09d1, B:310:0x09d9, B:301:0x096f, B:267:0x0838, B:253:0x07d0, B:314:0x09f7, B:316:0x0a03, B:317:0x0a09, B:318:0x0a11, B:320:0x0a17, B:322:0x0a2e, B:324:0x0a3f, B:344:0x0ab3, B:346:0x0ab9, B:348:0x0ad1, B:351:0x0ad8, B:356:0x0b07, B:358:0x0b4a, B:361:0x0b80, B:362:0x0b84, B:363:0x0b8f, B:365:0x0bd2, B:366:0x0bdf, B:368:0x0bee, B:372:0x0c08, B:374:0x0c21, B:360:0x0b5d, B:352:0x0ae0, B:354:0x0aec, B:355:0x0af0, B:375:0x0c39, B:376:0x0c51, B:379:0x0c59, B:380:0x0c5e, B:381:0x0c6e, B:383:0x0c88, B:384:0x0ca3, B:386:0x0cad, B:391:0x0cd0, B:390:0x0cbd, B:325:0x0a57, B:327:0x0a5d, B:329:0x0a67, B:331:0x0a6e, B:337:0x0a7e, B:339:0x0a85, B:341:0x0aa4, B:343:0x0aab, B:342:0x0aa8, B:338:0x0a82, B:330:0x0a6b, B:190:0x05c5, B:192:0x05cd, B:394:0x0ce2), top: B:400:0x000a, inners: #1, #2, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0556 A[Catch: all -> 0x0cf4, TryCatch #0 {all -> 0x0cf4, blocks: (B:3:0x000a, B:5:0x0022, B:8:0x002a, B:9:0x003c, B:11:0x0046, B:13:0x0069, B:15:0x009f, B:18:0x00b1, B:20:0x00bd, B:162:0x0521, B:22:0x00df, B:24:0x00ef, B:27:0x0111, B:29:0x0117, B:31:0x0127, B:33:0x0135, B:35:0x0145, B:36:0x0152, B:37:0x0155, B:39:0x016a, B:103:0x03a0, B:104:0x03ac, B:106:0x03b2, B:112:0x03d9, B:109:0x03c6, B:134:0x0460, B:136:0x046e, B:139:0x0483, B:141:0x0496, B:143:0x04a2, B:161:0x050b, B:148:0x04b9, B:150:0x04c5, B:153:0x04da, B:155:0x04ed, B:157:0x04f9, B:116:0x03e1, B:118:0x03ed, B:120:0x03f9, B:132:0x0442, B:124:0x041a, B:127:0x042c, B:129:0x0432, B:131:0x043c, B:60:0x01bc, B:62:0x01c2, B:64:0x01d4, B:69:0x0226, B:65:0x01f4, B:67:0x0208, B:73:0x023d, B:75:0x026b, B:76:0x0297, B:78:0x02cd, B:80:0x02d6, B:83:0x02e2, B:85:0x0318, B:86:0x0333, B:88:0x0339, B:90:0x0349, B:94:0x035f, B:91:0x0352, B:97:0x0366, B:100:0x036d, B:101:0x0387, B:165:0x0531, B:167:0x0541, B:169:0x054e, B:180:0x0583, B:170:0x0556, B:172:0x0563, B:174:0x0569, B:177:0x0575, B:179:0x057d, B:181:0x0586, B:182:0x0592, B:184:0x0598, B:186:0x05aa, B:187:0x05b8, B:189:0x05c0, B:193:0x05e7, B:195:0x060c, B:197:0x061d, B:199:0x0623, B:201:0x062f, B:202:0x0660, B:204:0x0666, B:206:0x0676, B:207:0x067a, B:208:0x067d, B:209:0x0680, B:210:0x068e, B:212:0x0694, B:214:0x06a4, B:215:0x06ab, B:217:0x06b7, B:218:0x06be, B:219:0x06c1, B:221:0x06ff, B:222:0x0712, B:224:0x0718, B:226:0x072e, B:228:0x0749, B:230:0x0760, B:232:0x0765, B:234:0x0769, B:236:0x076d, B:238:0x0777, B:239:0x0783, B:241:0x0787, B:243:0x078d, B:244:0x079d, B:245:0x07a6, B:313:0x09ec, B:247:0x07b2, B:249:0x07c9, B:255:0x07e4, B:257:0x0804, B:258:0x080c, B:260:0x0812, B:262:0x0824, B:269:0x084d, B:270:0x0870, B:272:0x087c, B:274:0x0893, B:276:0x08d2, B:280:0x08ea, B:282:0x08f1, B:284:0x0900, B:286:0x0904, B:288:0x0908, B:290:0x090c, B:291:0x0918, B:292:0x091d, B:294:0x0923, B:296:0x0941, B:297:0x0946, B:312:0x09e9, B:298:0x095e, B:300:0x0962, B:304:0x0989, B:306:0x09b7, B:307:0x09c1, B:308:0x09d1, B:310:0x09d9, B:301:0x096f, B:267:0x0838, B:253:0x07d0, B:314:0x09f7, B:316:0x0a03, B:317:0x0a09, B:318:0x0a11, B:320:0x0a17, B:322:0x0a2e, B:324:0x0a3f, B:344:0x0ab3, B:346:0x0ab9, B:348:0x0ad1, B:351:0x0ad8, B:356:0x0b07, B:358:0x0b4a, B:361:0x0b80, B:362:0x0b84, B:363:0x0b8f, B:365:0x0bd2, B:366:0x0bdf, B:368:0x0bee, B:372:0x0c08, B:374:0x0c21, B:360:0x0b5d, B:352:0x0ae0, B:354:0x0aec, B:355:0x0af0, B:375:0x0c39, B:376:0x0c51, B:379:0x0c59, B:380:0x0c5e, B:381:0x0c6e, B:383:0x0c88, B:384:0x0ca3, B:386:0x0cad, B:391:0x0cd0, B:390:0x0cbd, B:325:0x0a57, B:327:0x0a5d, B:329:0x0a67, B:331:0x0a6e, B:337:0x0a7e, B:339:0x0a85, B:341:0x0aa4, B:343:0x0aab, B:342:0x0aa8, B:338:0x0a82, B:330:0x0a6b, B:190:0x05c5, B:192:0x05cd, B:394:0x0ce2), top: B:400:0x000a, inners: #1, #2, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0804 A[Catch: all -> 0x0cf4, TryCatch #0 {all -> 0x0cf4, blocks: (B:3:0x000a, B:5:0x0022, B:8:0x002a, B:9:0x003c, B:11:0x0046, B:13:0x0069, B:15:0x009f, B:18:0x00b1, B:20:0x00bd, B:162:0x0521, B:22:0x00df, B:24:0x00ef, B:27:0x0111, B:29:0x0117, B:31:0x0127, B:33:0x0135, B:35:0x0145, B:36:0x0152, B:37:0x0155, B:39:0x016a, B:103:0x03a0, B:104:0x03ac, B:106:0x03b2, B:112:0x03d9, B:109:0x03c6, B:134:0x0460, B:136:0x046e, B:139:0x0483, B:141:0x0496, B:143:0x04a2, B:161:0x050b, B:148:0x04b9, B:150:0x04c5, B:153:0x04da, B:155:0x04ed, B:157:0x04f9, B:116:0x03e1, B:118:0x03ed, B:120:0x03f9, B:132:0x0442, B:124:0x041a, B:127:0x042c, B:129:0x0432, B:131:0x043c, B:60:0x01bc, B:62:0x01c2, B:64:0x01d4, B:69:0x0226, B:65:0x01f4, B:67:0x0208, B:73:0x023d, B:75:0x026b, B:76:0x0297, B:78:0x02cd, B:80:0x02d6, B:83:0x02e2, B:85:0x0318, B:86:0x0333, B:88:0x0339, B:90:0x0349, B:94:0x035f, B:91:0x0352, B:97:0x0366, B:100:0x036d, B:101:0x0387, B:165:0x0531, B:167:0x0541, B:169:0x054e, B:180:0x0583, B:170:0x0556, B:172:0x0563, B:174:0x0569, B:177:0x0575, B:179:0x057d, B:181:0x0586, B:182:0x0592, B:184:0x0598, B:186:0x05aa, B:187:0x05b8, B:189:0x05c0, B:193:0x05e7, B:195:0x060c, B:197:0x061d, B:199:0x0623, B:201:0x062f, B:202:0x0660, B:204:0x0666, B:206:0x0676, B:207:0x067a, B:208:0x067d, B:209:0x0680, B:210:0x068e, B:212:0x0694, B:214:0x06a4, B:215:0x06ab, B:217:0x06b7, B:218:0x06be, B:219:0x06c1, B:221:0x06ff, B:222:0x0712, B:224:0x0718, B:226:0x072e, B:228:0x0749, B:230:0x0760, B:232:0x0765, B:234:0x0769, B:236:0x076d, B:238:0x0777, B:239:0x0783, B:241:0x0787, B:243:0x078d, B:244:0x079d, B:245:0x07a6, B:313:0x09ec, B:247:0x07b2, B:249:0x07c9, B:255:0x07e4, B:257:0x0804, B:258:0x080c, B:260:0x0812, B:262:0x0824, B:269:0x084d, B:270:0x0870, B:272:0x087c, B:274:0x0893, B:276:0x08d2, B:280:0x08ea, B:282:0x08f1, B:284:0x0900, B:286:0x0904, B:288:0x0908, B:290:0x090c, B:291:0x0918, B:292:0x091d, B:294:0x0923, B:296:0x0941, B:297:0x0946, B:312:0x09e9, B:298:0x095e, B:300:0x0962, B:304:0x0989, B:306:0x09b7, B:307:0x09c1, B:308:0x09d1, B:310:0x09d9, B:301:0x096f, B:267:0x0838, B:253:0x07d0, B:314:0x09f7, B:316:0x0a03, B:317:0x0a09, B:318:0x0a11, B:320:0x0a17, B:322:0x0a2e, B:324:0x0a3f, B:344:0x0ab3, B:346:0x0ab9, B:348:0x0ad1, B:351:0x0ad8, B:356:0x0b07, B:358:0x0b4a, B:361:0x0b80, B:362:0x0b84, B:363:0x0b8f, B:365:0x0bd2, B:366:0x0bdf, B:368:0x0bee, B:372:0x0c08, B:374:0x0c21, B:360:0x0b5d, B:352:0x0ae0, B:354:0x0aec, B:355:0x0af0, B:375:0x0c39, B:376:0x0c51, B:379:0x0c59, B:380:0x0c5e, B:381:0x0c6e, B:383:0x0c88, B:384:0x0ca3, B:386:0x0cad, B:391:0x0cd0, B:390:0x0cbd, B:325:0x0a57, B:327:0x0a5d, B:329:0x0a67, B:331:0x0a6e, B:337:0x0a7e, B:339:0x0a85, B:341:0x0aa4, B:343:0x0aab, B:342:0x0aa8, B:338:0x0a82, B:330:0x0a6b, B:190:0x05c5, B:192:0x05cd, B:394:0x0ce2), top: B:400:0x000a, inners: #1, #2, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:267:0x0838 A[Catch: all -> 0x0cf4, TryCatch #0 {all -> 0x0cf4, blocks: (B:3:0x000a, B:5:0x0022, B:8:0x002a, B:9:0x003c, B:11:0x0046, B:13:0x0069, B:15:0x009f, B:18:0x00b1, B:20:0x00bd, B:162:0x0521, B:22:0x00df, B:24:0x00ef, B:27:0x0111, B:29:0x0117, B:31:0x0127, B:33:0x0135, B:35:0x0145, B:36:0x0152, B:37:0x0155, B:39:0x016a, B:103:0x03a0, B:104:0x03ac, B:106:0x03b2, B:112:0x03d9, B:109:0x03c6, B:134:0x0460, B:136:0x046e, B:139:0x0483, B:141:0x0496, B:143:0x04a2, B:161:0x050b, B:148:0x04b9, B:150:0x04c5, B:153:0x04da, B:155:0x04ed, B:157:0x04f9, B:116:0x03e1, B:118:0x03ed, B:120:0x03f9, B:132:0x0442, B:124:0x041a, B:127:0x042c, B:129:0x0432, B:131:0x043c, B:60:0x01bc, B:62:0x01c2, B:64:0x01d4, B:69:0x0226, B:65:0x01f4, B:67:0x0208, B:73:0x023d, B:75:0x026b, B:76:0x0297, B:78:0x02cd, B:80:0x02d6, B:83:0x02e2, B:85:0x0318, B:86:0x0333, B:88:0x0339, B:90:0x0349, B:94:0x035f, B:91:0x0352, B:97:0x0366, B:100:0x036d, B:101:0x0387, B:165:0x0531, B:167:0x0541, B:169:0x054e, B:180:0x0583, B:170:0x0556, B:172:0x0563, B:174:0x0569, B:177:0x0575, B:179:0x057d, B:181:0x0586, B:182:0x0592, B:184:0x0598, B:186:0x05aa, B:187:0x05b8, B:189:0x05c0, B:193:0x05e7, B:195:0x060c, B:197:0x061d, B:199:0x0623, B:201:0x062f, B:202:0x0660, B:204:0x0666, B:206:0x0676, B:207:0x067a, B:208:0x067d, B:209:0x0680, B:210:0x068e, B:212:0x0694, B:214:0x06a4, B:215:0x06ab, B:217:0x06b7, B:218:0x06be, B:219:0x06c1, B:221:0x06ff, B:222:0x0712, B:224:0x0718, B:226:0x072e, B:228:0x0749, B:230:0x0760, B:232:0x0765, B:234:0x0769, B:236:0x076d, B:238:0x0777, B:239:0x0783, B:241:0x0787, B:243:0x078d, B:244:0x079d, B:245:0x07a6, B:313:0x09ec, B:247:0x07b2, B:249:0x07c9, B:255:0x07e4, B:257:0x0804, B:258:0x080c, B:260:0x0812, B:262:0x0824, B:269:0x084d, B:270:0x0870, B:272:0x087c, B:274:0x0893, B:276:0x08d2, B:280:0x08ea, B:282:0x08f1, B:284:0x0900, B:286:0x0904, B:288:0x0908, B:290:0x090c, B:291:0x0918, B:292:0x091d, B:294:0x0923, B:296:0x0941, B:297:0x0946, B:312:0x09e9, B:298:0x095e, B:300:0x0962, B:304:0x0989, B:306:0x09b7, B:307:0x09c1, B:308:0x09d1, B:310:0x09d9, B:301:0x096f, B:267:0x0838, B:253:0x07d0, B:314:0x09f7, B:316:0x0a03, B:317:0x0a09, B:318:0x0a11, B:320:0x0a17, B:322:0x0a2e, B:324:0x0a3f, B:344:0x0ab3, B:346:0x0ab9, B:348:0x0ad1, B:351:0x0ad8, B:356:0x0b07, B:358:0x0b4a, B:361:0x0b80, B:362:0x0b84, B:363:0x0b8f, B:365:0x0bd2, B:366:0x0bdf, B:368:0x0bee, B:372:0x0c08, B:374:0x0c21, B:360:0x0b5d, B:352:0x0ae0, B:354:0x0aec, B:355:0x0af0, B:375:0x0c39, B:376:0x0c51, B:379:0x0c59, B:380:0x0c5e, B:381:0x0c6e, B:383:0x0c88, B:384:0x0ca3, B:386:0x0cad, B:391:0x0cd0, B:390:0x0cbd, B:325:0x0a57, B:327:0x0a5d, B:329:0x0a67, B:331:0x0a6e, B:337:0x0a7e, B:339:0x0a85, B:341:0x0aa4, B:343:0x0aab, B:342:0x0aa8, B:338:0x0a82, B:330:0x0a6b, B:190:0x05c5, B:192:0x05cd, B:394:0x0ce2), top: B:400:0x000a, inners: #1, #2, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:269:0x084d A[Catch: all -> 0x0cf4, TryCatch #0 {all -> 0x0cf4, blocks: (B:3:0x000a, B:5:0x0022, B:8:0x002a, B:9:0x003c, B:11:0x0046, B:13:0x0069, B:15:0x009f, B:18:0x00b1, B:20:0x00bd, B:162:0x0521, B:22:0x00df, B:24:0x00ef, B:27:0x0111, B:29:0x0117, B:31:0x0127, B:33:0x0135, B:35:0x0145, B:36:0x0152, B:37:0x0155, B:39:0x016a, B:103:0x03a0, B:104:0x03ac, B:106:0x03b2, B:112:0x03d9, B:109:0x03c6, B:134:0x0460, B:136:0x046e, B:139:0x0483, B:141:0x0496, B:143:0x04a2, B:161:0x050b, B:148:0x04b9, B:150:0x04c5, B:153:0x04da, B:155:0x04ed, B:157:0x04f9, B:116:0x03e1, B:118:0x03ed, B:120:0x03f9, B:132:0x0442, B:124:0x041a, B:127:0x042c, B:129:0x0432, B:131:0x043c, B:60:0x01bc, B:62:0x01c2, B:64:0x01d4, B:69:0x0226, B:65:0x01f4, B:67:0x0208, B:73:0x023d, B:75:0x026b, B:76:0x0297, B:78:0x02cd, B:80:0x02d6, B:83:0x02e2, B:85:0x0318, B:86:0x0333, B:88:0x0339, B:90:0x0349, B:94:0x035f, B:91:0x0352, B:97:0x0366, B:100:0x036d, B:101:0x0387, B:165:0x0531, B:167:0x0541, B:169:0x054e, B:180:0x0583, B:170:0x0556, B:172:0x0563, B:174:0x0569, B:177:0x0575, B:179:0x057d, B:181:0x0586, B:182:0x0592, B:184:0x0598, B:186:0x05aa, B:187:0x05b8, B:189:0x05c0, B:193:0x05e7, B:195:0x060c, B:197:0x061d, B:199:0x0623, B:201:0x062f, B:202:0x0660, B:204:0x0666, B:206:0x0676, B:207:0x067a, B:208:0x067d, B:209:0x0680, B:210:0x068e, B:212:0x0694, B:214:0x06a4, B:215:0x06ab, B:217:0x06b7, B:218:0x06be, B:219:0x06c1, B:221:0x06ff, B:222:0x0712, B:224:0x0718, B:226:0x072e, B:228:0x0749, B:230:0x0760, B:232:0x0765, B:234:0x0769, B:236:0x076d, B:238:0x0777, B:239:0x0783, B:241:0x0787, B:243:0x078d, B:244:0x079d, B:245:0x07a6, B:313:0x09ec, B:247:0x07b2, B:249:0x07c9, B:255:0x07e4, B:257:0x0804, B:258:0x080c, B:260:0x0812, B:262:0x0824, B:269:0x084d, B:270:0x0870, B:272:0x087c, B:274:0x0893, B:276:0x08d2, B:280:0x08ea, B:282:0x08f1, B:284:0x0900, B:286:0x0904, B:288:0x0908, B:290:0x090c, B:291:0x0918, B:292:0x091d, B:294:0x0923, B:296:0x0941, B:297:0x0946, B:312:0x09e9, B:298:0x095e, B:300:0x0962, B:304:0x0989, B:306:0x09b7, B:307:0x09c1, B:308:0x09d1, B:310:0x09d9, B:301:0x096f, B:267:0x0838, B:253:0x07d0, B:314:0x09f7, B:316:0x0a03, B:317:0x0a09, B:318:0x0a11, B:320:0x0a17, B:322:0x0a2e, B:324:0x0a3f, B:344:0x0ab3, B:346:0x0ab9, B:348:0x0ad1, B:351:0x0ad8, B:356:0x0b07, B:358:0x0b4a, B:361:0x0b80, B:362:0x0b84, B:363:0x0b8f, B:365:0x0bd2, B:366:0x0bdf, B:368:0x0bee, B:372:0x0c08, B:374:0x0c21, B:360:0x0b5d, B:352:0x0ae0, B:354:0x0aec, B:355:0x0af0, B:375:0x0c39, B:376:0x0c51, B:379:0x0c59, B:380:0x0c5e, B:381:0x0c6e, B:383:0x0c88, B:384:0x0ca3, B:386:0x0cad, B:391:0x0cd0, B:390:0x0cbd, B:325:0x0a57, B:327:0x0a5d, B:329:0x0a67, B:331:0x0a6e, B:337:0x0a7e, B:339:0x0a85, B:341:0x0aa4, B:343:0x0aab, B:342:0x0aa8, B:338:0x0a82, B:330:0x0a6b, B:190:0x05c5, B:192:0x05cd, B:394:0x0ce2), top: B:400:0x000a, inners: #1, #2, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0870 A[Catch: all -> 0x0cf4, TryCatch #0 {all -> 0x0cf4, blocks: (B:3:0x000a, B:5:0x0022, B:8:0x002a, B:9:0x003c, B:11:0x0046, B:13:0x0069, B:15:0x009f, B:18:0x00b1, B:20:0x00bd, B:162:0x0521, B:22:0x00df, B:24:0x00ef, B:27:0x0111, B:29:0x0117, B:31:0x0127, B:33:0x0135, B:35:0x0145, B:36:0x0152, B:37:0x0155, B:39:0x016a, B:103:0x03a0, B:104:0x03ac, B:106:0x03b2, B:112:0x03d9, B:109:0x03c6, B:134:0x0460, B:136:0x046e, B:139:0x0483, B:141:0x0496, B:143:0x04a2, B:161:0x050b, B:148:0x04b9, B:150:0x04c5, B:153:0x04da, B:155:0x04ed, B:157:0x04f9, B:116:0x03e1, B:118:0x03ed, B:120:0x03f9, B:132:0x0442, B:124:0x041a, B:127:0x042c, B:129:0x0432, B:131:0x043c, B:60:0x01bc, B:62:0x01c2, B:64:0x01d4, B:69:0x0226, B:65:0x01f4, B:67:0x0208, B:73:0x023d, B:75:0x026b, B:76:0x0297, B:78:0x02cd, B:80:0x02d6, B:83:0x02e2, B:85:0x0318, B:86:0x0333, B:88:0x0339, B:90:0x0349, B:94:0x035f, B:91:0x0352, B:97:0x0366, B:100:0x036d, B:101:0x0387, B:165:0x0531, B:167:0x0541, B:169:0x054e, B:180:0x0583, B:170:0x0556, B:172:0x0563, B:174:0x0569, B:177:0x0575, B:179:0x057d, B:181:0x0586, B:182:0x0592, B:184:0x0598, B:186:0x05aa, B:187:0x05b8, B:189:0x05c0, B:193:0x05e7, B:195:0x060c, B:197:0x061d, B:199:0x0623, B:201:0x062f, B:202:0x0660, B:204:0x0666, B:206:0x0676, B:207:0x067a, B:208:0x067d, B:209:0x0680, B:210:0x068e, B:212:0x0694, B:214:0x06a4, B:215:0x06ab, B:217:0x06b7, B:218:0x06be, B:219:0x06c1, B:221:0x06ff, B:222:0x0712, B:224:0x0718, B:226:0x072e, B:228:0x0749, B:230:0x0760, B:232:0x0765, B:234:0x0769, B:236:0x076d, B:238:0x0777, B:239:0x0783, B:241:0x0787, B:243:0x078d, B:244:0x079d, B:245:0x07a6, B:313:0x09ec, B:247:0x07b2, B:249:0x07c9, B:255:0x07e4, B:257:0x0804, B:258:0x080c, B:260:0x0812, B:262:0x0824, B:269:0x084d, B:270:0x0870, B:272:0x087c, B:274:0x0893, B:276:0x08d2, B:280:0x08ea, B:282:0x08f1, B:284:0x0900, B:286:0x0904, B:288:0x0908, B:290:0x090c, B:291:0x0918, B:292:0x091d, B:294:0x0923, B:296:0x0941, B:297:0x0946, B:312:0x09e9, B:298:0x095e, B:300:0x0962, B:304:0x0989, B:306:0x09b7, B:307:0x09c1, B:308:0x09d1, B:310:0x09d9, B:301:0x096f, B:267:0x0838, B:253:0x07d0, B:314:0x09f7, B:316:0x0a03, B:317:0x0a09, B:318:0x0a11, B:320:0x0a17, B:322:0x0a2e, B:324:0x0a3f, B:344:0x0ab3, B:346:0x0ab9, B:348:0x0ad1, B:351:0x0ad8, B:356:0x0b07, B:358:0x0b4a, B:361:0x0b80, B:362:0x0b84, B:363:0x0b8f, B:365:0x0bd2, B:366:0x0bdf, B:368:0x0bee, B:372:0x0c08, B:374:0x0c21, B:360:0x0b5d, B:352:0x0ae0, B:354:0x0aec, B:355:0x0af0, B:375:0x0c39, B:376:0x0c51, B:379:0x0c59, B:380:0x0c5e, B:381:0x0c6e, B:383:0x0c88, B:384:0x0ca3, B:386:0x0cad, B:391:0x0cd0, B:390:0x0cbd, B:325:0x0a57, B:327:0x0a5d, B:329:0x0a67, B:331:0x0a6e, B:337:0x0a7e, B:339:0x0a85, B:341:0x0aa4, B:343:0x0aab, B:342:0x0aa8, B:338:0x0a82, B:330:0x0a6b, B:190:0x05c5, B:192:0x05cd, B:394:0x0ce2), top: B:400:0x000a, inners: #1, #2, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:360:0x0b5d A[Catch: all -> 0x0cf4, TryCatch #0 {all -> 0x0cf4, blocks: (B:3:0x000a, B:5:0x0022, B:8:0x002a, B:9:0x003c, B:11:0x0046, B:13:0x0069, B:15:0x009f, B:18:0x00b1, B:20:0x00bd, B:162:0x0521, B:22:0x00df, B:24:0x00ef, B:27:0x0111, B:29:0x0117, B:31:0x0127, B:33:0x0135, B:35:0x0145, B:36:0x0152, B:37:0x0155, B:39:0x016a, B:103:0x03a0, B:104:0x03ac, B:106:0x03b2, B:112:0x03d9, B:109:0x03c6, B:134:0x0460, B:136:0x046e, B:139:0x0483, B:141:0x0496, B:143:0x04a2, B:161:0x050b, B:148:0x04b9, B:150:0x04c5, B:153:0x04da, B:155:0x04ed, B:157:0x04f9, B:116:0x03e1, B:118:0x03ed, B:120:0x03f9, B:132:0x0442, B:124:0x041a, B:127:0x042c, B:129:0x0432, B:131:0x043c, B:60:0x01bc, B:62:0x01c2, B:64:0x01d4, B:69:0x0226, B:65:0x01f4, B:67:0x0208, B:73:0x023d, B:75:0x026b, B:76:0x0297, B:78:0x02cd, B:80:0x02d6, B:83:0x02e2, B:85:0x0318, B:86:0x0333, B:88:0x0339, B:90:0x0349, B:94:0x035f, B:91:0x0352, B:97:0x0366, B:100:0x036d, B:101:0x0387, B:165:0x0531, B:167:0x0541, B:169:0x054e, B:180:0x0583, B:170:0x0556, B:172:0x0563, B:174:0x0569, B:177:0x0575, B:179:0x057d, B:181:0x0586, B:182:0x0592, B:184:0x0598, B:186:0x05aa, B:187:0x05b8, B:189:0x05c0, B:193:0x05e7, B:195:0x060c, B:197:0x061d, B:199:0x0623, B:201:0x062f, B:202:0x0660, B:204:0x0666, B:206:0x0676, B:207:0x067a, B:208:0x067d, B:209:0x0680, B:210:0x068e, B:212:0x0694, B:214:0x06a4, B:215:0x06ab, B:217:0x06b7, B:218:0x06be, B:219:0x06c1, B:221:0x06ff, B:222:0x0712, B:224:0x0718, B:226:0x072e, B:228:0x0749, B:230:0x0760, B:232:0x0765, B:234:0x0769, B:236:0x076d, B:238:0x0777, B:239:0x0783, B:241:0x0787, B:243:0x078d, B:244:0x079d, B:245:0x07a6, B:313:0x09ec, B:247:0x07b2, B:249:0x07c9, B:255:0x07e4, B:257:0x0804, B:258:0x080c, B:260:0x0812, B:262:0x0824, B:269:0x084d, B:270:0x0870, B:272:0x087c, B:274:0x0893, B:276:0x08d2, B:280:0x08ea, B:282:0x08f1, B:284:0x0900, B:286:0x0904, B:288:0x0908, B:290:0x090c, B:291:0x0918, B:292:0x091d, B:294:0x0923, B:296:0x0941, B:297:0x0946, B:312:0x09e9, B:298:0x095e, B:300:0x0962, B:304:0x0989, B:306:0x09b7, B:307:0x09c1, B:308:0x09d1, B:310:0x09d9, B:301:0x096f, B:267:0x0838, B:253:0x07d0, B:314:0x09f7, B:316:0x0a03, B:317:0x0a09, B:318:0x0a11, B:320:0x0a17, B:322:0x0a2e, B:324:0x0a3f, B:344:0x0ab3, B:346:0x0ab9, B:348:0x0ad1, B:351:0x0ad8, B:356:0x0b07, B:358:0x0b4a, B:361:0x0b80, B:362:0x0b84, B:363:0x0b8f, B:365:0x0bd2, B:366:0x0bdf, B:368:0x0bee, B:372:0x0c08, B:374:0x0c21, B:360:0x0b5d, B:352:0x0ae0, B:354:0x0aec, B:355:0x0af0, B:375:0x0c39, B:376:0x0c51, B:379:0x0c59, B:380:0x0c5e, B:381:0x0c6e, B:383:0x0c88, B:384:0x0ca3, B:386:0x0cad, B:391:0x0cd0, B:390:0x0cbd, B:325:0x0a57, B:327:0x0a5d, B:329:0x0a67, B:331:0x0a6e, B:337:0x0a7e, B:339:0x0a85, B:341:0x0aa4, B:343:0x0aab, B:342:0x0aa8, B:338:0x0a82, B:330:0x0a6b, B:190:0x05c5, B:192:0x05cd, B:394:0x0ce2), top: B:400:0x000a, inners: #1, #2, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01b9  */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean zzah(java.lang.String r58, long r59) {
        /*
            Method dump skipped, instructions count: 3338
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkz.zzah(java.lang.String, long):boolean");
    }

    private final boolean zzai() {
        zzaz().zzg();
        zzB();
        zzam zzamVar = this.zze;
        zzal(zzamVar);
        if (zzamVar.zzF()) {
            return true;
        }
        zzam zzamVar2 = this.zze;
        zzal(zzamVar2);
        return !TextUtils.isEmpty(zzamVar2.zzr());
    }

    private final boolean zzaj(com.google.android.gms.internal.measurement.zzfr zzfrVar, com.google.android.gms.internal.measurement.zzfr zzfrVar2) {
        Preconditions.checkArgument("_e".equals(zzfrVar.zzo()));
        zzal(this.zzi);
        com.google.android.gms.internal.measurement.zzfw zzfwVarZzB = zzlb.zzB((com.google.android.gms.internal.measurement.zzfs) zzfrVar.zzaE(), "_sc");
        String strZzh = zzfwVarZzB == null ? null : zzfwVarZzB.zzh();
        zzal(this.zzi);
        com.google.android.gms.internal.measurement.zzfw zzfwVarZzB2 = zzlb.zzB((com.google.android.gms.internal.measurement.zzfs) zzfrVar2.zzaE(), "_pc");
        String strZzh2 = zzfwVarZzB2 != null ? zzfwVarZzB2.zzh() : null;
        if (strZzh2 == null || !strZzh2.equals(strZzh)) {
            return false;
        }
        Preconditions.checkArgument("_e".equals(zzfrVar.zzo()));
        zzal(this.zzi);
        com.google.android.gms.internal.measurement.zzfw zzfwVarZzB3 = zzlb.zzB((com.google.android.gms.internal.measurement.zzfs) zzfrVar.zzaE(), "_et");
        if (zzfwVarZzB3 == null || !zzfwVarZzB3.zzw() || zzfwVarZzB3.zzd() <= 0) {
            return true;
        }
        long jZzd = zzfwVarZzB3.zzd();
        zzal(this.zzi);
        com.google.android.gms.internal.measurement.zzfw zzfwVarZzB4 = zzlb.zzB((com.google.android.gms.internal.measurement.zzfs) zzfrVar2.zzaE(), "_et");
        if (zzfwVarZzB4 != null && zzfwVarZzB4.zzd() > 0) {
            jZzd += zzfwVarZzB4.zzd();
        }
        zzal(this.zzi);
        zzlb.zzz(zzfrVar2, "_et", Long.valueOf(jZzd));
        zzal(this.zzi);
        zzlb.zzz(zzfrVar, "_fr", 1L);
        return true;
    }

    private static final boolean zzak(zzq zzqVar) {
        return (TextUtils.isEmpty(zzqVar.zzb) && TextUtils.isEmpty(zzqVar.zzq)) ? false : true;
    }

    private static final zzkn zzal(zzkn zzknVar) {
        if (zzknVar == null) {
            throw new IllegalStateException("Upload Component not created");
        }
        if (zzknVar.zzY()) {
            return zzknVar;
        }
        throw new IllegalStateException("Component not initialized: ".concat(String.valueOf(String.valueOf(zzknVar.getClass()))));
    }

    public static zzkz zzt(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzb == null) {
            synchronized (zzkz.class) {
                if (zzb == null) {
                    zzb = new zzkz((zzla) Preconditions.checkNotNull(new zzla(context)), null);
                }
            }
        }
        return zzb;
    }

    static /* bridge */ /* synthetic */ void zzy(zzkz zzkzVar, zzla zzlaVar) {
        zzkzVar.zzaz().zzg();
        zzkzVar.zzm = new zzfg(zzkzVar);
        zzam zzamVar = new zzam(zzkzVar);
        zzamVar.zzX();
        zzkzVar.zze = zzamVar;
        zzkzVar.zzg().zzq((zzaf) Preconditions.checkNotNull(zzkzVar.zzc));
        zzju zzjuVar = new zzju(zzkzVar);
        zzjuVar.zzX();
        zzkzVar.zzk = zzjuVar;
        zzaa zzaaVar = new zzaa(zzkzVar);
        zzaaVar.zzX();
        zzkzVar.zzh = zzaaVar;
        zzii zziiVar = new zzii(zzkzVar);
        zziiVar.zzX();
        zzkzVar.zzj = zziiVar;
        zzkl zzklVar = new zzkl(zzkzVar);
        zzklVar.zzX();
        zzkzVar.zzg = zzklVar;
        zzkzVar.zzf = new zzew(zzkzVar);
        if (zzkzVar.zzr != zzkzVar.zzs) {
            zzkzVar.zzay().zzd().zzc("Not all upload components initialized", Integer.valueOf(zzkzVar.zzr), Integer.valueOf(zzkzVar.zzs));
        }
        zzkzVar.zzo = true;
    }

    @VisibleForTesting
    @WorkerThread
    final void zzA() {
        zzaz().zzg();
        zzB();
        if (this.zzp) {
            return;
        }
        this.zzp = true;
        if (zzZ()) {
            FileChannel fileChannel = this.zzx;
            zzaz().zzg();
            int i = 0;
            if (fileChannel == null || !fileChannel.isOpen()) {
                zzay().zzd().zza("Bad channel to read from");
            } else {
                ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
                try {
                    fileChannel.position(0L);
                    int i2 = fileChannel.read(byteBufferAllocate);
                    if (i2 == 4) {
                        byteBufferAllocate.flip();
                        i = byteBufferAllocate.getInt();
                    } else if (i2 != -1) {
                        zzay().zzk().zzb("Unexpected data length. Bytes read", Integer.valueOf(i2));
                    }
                } catch (IOException e) {
                    zzay().zzd().zzb("Failed to read from channel", e);
                }
            }
            int iZzi = this.zzn.zzh().zzi();
            zzaz().zzg();
            if (i > iZzi) {
                zzay().zzd().zzc("Panic: can't downgrade version. Previous, current version", Integer.valueOf(i), Integer.valueOf(iZzi));
                return;
            }
            if (i < iZzi) {
                FileChannel fileChannel2 = this.zzx;
                zzaz().zzg();
                if (fileChannel2 == null || !fileChannel2.isOpen()) {
                    zzay().zzd().zza("Bad channel to read from");
                } else {
                    ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(4);
                    byteBufferAllocate2.putInt(iZzi);
                    byteBufferAllocate2.flip();
                    try {
                        fileChannel2.truncate(0L);
                        fileChannel2.write(byteBufferAllocate2);
                        fileChannel2.force(true);
                        if (fileChannel2.size() != 4) {
                            zzay().zzd().zzb("Error writing to channel. Bytes written", Long.valueOf(fileChannel2.size()));
                        }
                        zzay().zzj().zzc("Storage version upgraded. Previous, current version", Integer.valueOf(i), Integer.valueOf(iZzi));
                        return;
                    } catch (IOException e2) {
                        zzay().zzd().zzb("Failed to write to channel", e2);
                    }
                }
                zzay().zzd().zzc("Storage version upgrade failed. Previous, current version", Integer.valueOf(i), Integer.valueOf(iZzi));
            }
        }
    }

    final void zzB() {
        if (!this.zzo) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }

    final void zzC(String str, com.google.android.gms.internal.measurement.zzgb zzgbVar) {
        int iZza;
        int iIndexOf;
        zzow.zzc();
        if (zzg().zzs(str, zzeb.zzam)) {
            zzfp zzfpVar = this.zzc;
            zzal(zzfpVar);
            Set setZzk = zzfpVar.zzk(str);
            if (setZzk != null) {
                zzgbVar.zzi(setZzk);
            }
        }
        if (zzg().zzs(str, zzeb.zzao)) {
            zzfp zzfpVar2 = this.zzc;
            zzal(zzfpVar2);
            if (zzfpVar2.zzv(str)) {
                zzgbVar.zzp();
            }
            zzfp zzfpVar3 = this.zzc;
            zzal(zzfpVar3);
            if (zzfpVar3.zzy(str)) {
                if (zzg().zzs(str, zzeb.zzay)) {
                    String strZzar = zzgbVar.zzar();
                    if (!TextUtils.isEmpty(strZzar) && (iIndexOf = strZzar.indexOf(".")) != -1) {
                        zzgbVar.zzY(strZzar.substring(0, iIndexOf));
                    }
                } else {
                    zzgbVar.zzu();
                }
            }
        }
        if (zzg().zzs(str, zzeb.zzap)) {
            zzfp zzfpVar4 = this.zzc;
            zzal(zzfpVar4);
            if (zzfpVar4.zzz(str) && (iZza = zzlb.zza(zzgbVar, FileDownloadModel.ID)) != -1) {
                zzgbVar.zzB(iZza);
            }
        }
        if (zzg().zzs(str, zzeb.zzaq)) {
            zzfp zzfpVar5 = this.zzc;
            zzal(zzfpVar5);
            if (zzfpVar5.zzx(str)) {
                zzgbVar.zzq();
            }
        }
        if (zzg().zzs(str, zzeb.zzat)) {
            zzfp zzfpVar6 = this.zzc;
            zzal(zzfpVar6);
            if (zzfpVar6.zzu(str)) {
                zzgbVar.zzn();
                if (zzg().zzs(str, zzeb.zzau)) {
                    zzky zzkyVar = (zzky) this.zzC.get(str);
                    if (zzkyVar == null || zzkyVar.zzb + zzg().zzi(str, zzeb.zzR) < zzav().elapsedRealtime()) {
                        zzkyVar = new zzky(this);
                        this.zzC.put(str, zzkyVar);
                    }
                    zzgbVar.zzR(zzkyVar.zza);
                }
            }
        }
        if (zzg().zzs(str, zzeb.zzav)) {
            zzfp zzfpVar7 = this.zzc;
            zzal(zzfpVar7);
            if (zzfpVar7.zzw(str)) {
                zzgbVar.zzy();
            }
        }
    }

    @WorkerThread
    final void zzD(zzh zzhVar) throws IllegalStateException {
        ArrayMap arrayMap;
        ArrayMap arrayMap2;
        zzaz().zzg();
        if (TextUtils.isEmpty(zzhVar.zzy()) && TextUtils.isEmpty(zzhVar.zzr())) {
            zzI((String) Preconditions.checkNotNull(zzhVar.zzt()), 204, null, null, null);
            return;
        }
        zzko zzkoVar = this.zzl;
        Uri.Builder builder = new Uri.Builder();
        String strZzy = zzhVar.zzy();
        if (TextUtils.isEmpty(strZzy)) {
            strZzy = zzhVar.zzr();
        }
        ArrayMap arrayMap3 = null;
        Uri.Builder builderAppendQueryParameter = builder.scheme((String) zzeb.zzd.zza(null)).encodedAuthority((String) zzeb.zze.zza(null)).path("config/app/".concat(String.valueOf(strZzy))).appendQueryParameter("platform", Platform.ANDROID);
        zzkoVar.zzs.zzf().zzh();
        builderAppendQueryParameter.appendQueryParameter("gmp_version", String.valueOf(73000L)).appendQueryParameter("runtime_version", PushConstants.PUSH_TYPE_NOTIFY);
        zzow.zzc();
        if (!zzkoVar.zzs.zzf().zzs(zzhVar.zzt(), zzeb.zzak)) {
            builder.appendQueryParameter("app_instance_id", zzhVar.zzu());
        }
        String string = builder.build().toString();
        try {
            String str = (String) Preconditions.checkNotNull(zzhVar.zzt());
            URL url = new URL(string);
            zzay().zzj().zzb("Fetching remote configuration", str);
            zzfp zzfpVar = this.zzc;
            zzal(zzfpVar);
            com.google.android.gms.internal.measurement.zzfe zzfeVarZze = zzfpVar.zze(str);
            zzfp zzfpVar2 = this.zzc;
            zzal(zzfpVar2);
            String strZzh = zzfpVar2.zzh(str);
            if (zzfeVarZze == null) {
                arrayMap = arrayMap3;
            } else {
                if (TextUtils.isEmpty(strZzh)) {
                    arrayMap2 = null;
                } else {
                    arrayMap2 = new ArrayMap();
                    arrayMap2.put("If-Modified-Since", strZzh);
                }
                zzow.zzc();
                if (zzg().zzs(null, zzeb.zzaw)) {
                    zzfp zzfpVar3 = this.zzc;
                    zzal(zzfpVar3);
                    String strZzf = zzfpVar3.zzf(str);
                    if (!TextUtils.isEmpty(strZzf)) {
                        if (arrayMap2 == null) {
                            arrayMap2 = new ArrayMap();
                        }
                        arrayMap3 = arrayMap2;
                        arrayMap3.put("If-None-Match", strZzf);
                        arrayMap = arrayMap3;
                    }
                }
                arrayMap = arrayMap2;
            }
            this.zzt = true;
            zzeu zzeuVar = this.zzd;
            zzal(zzeuVar);
            zzkr zzkrVar = new zzkr(this);
            zzeuVar.zzg();
            zzeuVar.zzW();
            Preconditions.checkNotNull(url);
            Preconditions.checkNotNull(zzkrVar);
            zzeuVar.zzs.zzaz().zzo(new zzet(zzeuVar, str, url, null, arrayMap, zzkrVar));
        } catch (MalformedURLException unused) {
            zzay().zzd().zzc("Failed to parse config URL. Not fetching. appId", zzeo.zzn(zzhVar.zzt()), string);
        }
    }

    @WorkerThread
    final void zzE(zzaw zzawVar, zzq zzqVar) {
        zzaw zzawVar2;
        List<zzac> listZzt;
        List<zzac> listZzt2;
        List<zzac> listZzt3;
        String str;
        Preconditions.checkNotNull(zzqVar);
        Preconditions.checkNotEmpty(zzqVar.zza);
        zzaz().zzg();
        zzB();
        String str2 = zzqVar.zza;
        long j = zzawVar.zzd;
        zzep zzepVarZzb = zzep.zzb(zzawVar);
        zzaz().zzg();
        zzik zzikVar = null;
        if (this.zzD != null && (str = this.zzE) != null && str.equals(str2)) {
            zzikVar = this.zzD;
        }
        zzlh.zzK(zzikVar, zzepVarZzb.zzd, false);
        zzaw zzawVarZza = zzepVarZzb.zza();
        zzal(this.zzi);
        if (zzlb.zzA(zzawVarZza, zzqVar)) {
            if (!zzqVar.zzh) {
                zzd(zzqVar);
                return;
            }
            List list = zzqVar.zzt;
            if (list == null) {
                zzawVar2 = zzawVarZza;
            } else if (!list.contains(zzawVarZza.zza)) {
                zzay().zzc().zzd("Dropping non-safelisted event. appId, event name, origin", str2, zzawVarZza.zza, zzawVarZza.zzc);
                return;
            } else {
                Bundle bundleZzc = zzawVarZza.zzb.zzc();
                bundleZzc.putLong("ga_safelisted", 1L);
                zzawVar2 = new zzaw(zzawVarZza.zza, new zzau(bundleZzc), zzawVarZza.zzc, zzawVarZza.zzd);
            }
            zzam zzamVar = this.zze;
            zzal(zzamVar);
            zzamVar.zzw();
            try {
                zzam zzamVar2 = this.zze;
                zzal(zzamVar2);
                Preconditions.checkNotEmpty(str2);
                zzamVar2.zzg();
                zzamVar2.zzW();
                if (j < 0) {
                    zzamVar2.zzs.zzay().zzk().zzc("Invalid time querying timed out conditional properties", zzeo.zzn(str2), Long.valueOf(j));
                    listZzt = Collections.emptyList();
                } else {
                    listZzt = zzamVar2.zzt("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str2, String.valueOf(j)});
                }
                for (zzac zzacVar : listZzt) {
                    if (zzacVar != null) {
                        zzay().zzj().zzd("User property timed out", zzacVar.zza, this.zzn.zzj().zzf(zzacVar.zzc.zzb), zzacVar.zzc.zza());
                        zzaw zzawVar3 = zzacVar.zzg;
                        if (zzawVar3 != null) {
                            zzY(new zzaw(zzawVar3, j), zzqVar);
                        }
                        zzam zzamVar3 = this.zze;
                        zzal(zzamVar3);
                        zzamVar3.zza(str2, zzacVar.zzc.zzb);
                    }
                }
                zzam zzamVar4 = this.zze;
                zzal(zzamVar4);
                Preconditions.checkNotEmpty(str2);
                zzamVar4.zzg();
                zzamVar4.zzW();
                if (j < 0) {
                    zzamVar4.zzs.zzay().zzk().zzc("Invalid time querying expired conditional properties", zzeo.zzn(str2), Long.valueOf(j));
                    listZzt2 = Collections.emptyList();
                } else {
                    listZzt2 = zzamVar4.zzt("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str2, String.valueOf(j)});
                }
                ArrayList arrayList = new ArrayList(listZzt2.size());
                for (zzac zzacVar2 : listZzt2) {
                    if (zzacVar2 != null) {
                        zzay().zzj().zzd("User property expired", zzacVar2.zza, this.zzn.zzj().zzf(zzacVar2.zzc.zzb), zzacVar2.zzc.zza());
                        zzam zzamVar5 = this.zze;
                        zzal(zzamVar5);
                        zzamVar5.zzA(str2, zzacVar2.zzc.zzb);
                        zzaw zzawVar4 = zzacVar2.zzk;
                        if (zzawVar4 != null) {
                            arrayList.add(zzawVar4);
                        }
                        zzam zzamVar6 = this.zze;
                        zzal(zzamVar6);
                        zzamVar6.zza(str2, zzacVar2.zzc.zzb);
                    }
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    zzY(new zzaw((zzaw) it.next(), j), zzqVar);
                }
                zzam zzamVar7 = this.zze;
                zzal(zzamVar7);
                String str3 = zzawVar2.zza;
                Preconditions.checkNotEmpty(str2);
                Preconditions.checkNotEmpty(str3);
                zzamVar7.zzg();
                zzamVar7.zzW();
                if (j < 0) {
                    zzamVar7.zzs.zzay().zzk().zzd("Invalid time querying triggered conditional properties", zzeo.zzn(str2), zzamVar7.zzs.zzj().zzd(str3), Long.valueOf(j));
                    listZzt3 = Collections.emptyList();
                } else {
                    listZzt3 = zzamVar7.zzt("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str2, str3, String.valueOf(j)});
                }
                ArrayList arrayList2 = new ArrayList(listZzt3.size());
                for (zzac zzacVar3 : listZzt3) {
                    if (zzacVar3 != null) {
                        zzlc zzlcVar = zzacVar3.zzc;
                        zzle zzleVar = new zzle((String) Preconditions.checkNotNull(zzacVar3.zza), zzacVar3.zzb, zzlcVar.zzb, j, Preconditions.checkNotNull(zzlcVar.zza()));
                        zzam zzamVar8 = this.zze;
                        zzal(zzamVar8);
                        if (zzamVar8.zzL(zzleVar)) {
                            zzay().zzj().zzd("User property triggered", zzacVar3.zza, this.zzn.zzj().zzf(zzleVar.zzc), zzleVar.zze);
                        } else {
                            zzay().zzd().zzd("Too many active user properties, ignoring", zzeo.zzn(zzacVar3.zza), this.zzn.zzj().zzf(zzleVar.zzc), zzleVar.zze);
                        }
                        zzaw zzawVar5 = zzacVar3.zzi;
                        if (zzawVar5 != null) {
                            arrayList2.add(zzawVar5);
                        }
                        zzacVar3.zzc = new zzlc(zzleVar);
                        zzacVar3.zze = true;
                        zzam zzamVar9 = this.zze;
                        zzal(zzamVar9);
                        zzamVar9.zzK(zzacVar3);
                    }
                }
                zzY(zzawVar2, zzqVar);
                Iterator it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    zzY(new zzaw((zzaw) it2.next(), j), zzqVar);
                }
                zzam zzamVar10 = this.zze;
                zzal(zzamVar10);
                zzamVar10.zzC();
            } finally {
                zzam zzamVar11 = this.zze;
                zzal(zzamVar11);
                zzamVar11.zzx();
            }
        }
    }

    @WorkerThread
    final void zzF(zzaw zzawVar, String str) {
        zzam zzamVar = this.zze;
        zzal(zzamVar);
        zzh zzhVarZzj = zzamVar.zzj(str);
        if (zzhVarZzj == null || TextUtils.isEmpty(zzhVarZzj.zzw())) {
            zzay().zzc().zzb("No app data available; dropping event", str);
            return;
        }
        Boolean boolZzad = zzad(zzhVarZzj);
        if (boolZzad == null) {
            if (!"_ui".equals(zzawVar.zza)) {
                zzay().zzk().zzb("Could not find package. appId", zzeo.zzn(str));
            }
        } else if (!boolZzad.booleanValue()) {
            zzay().zzd().zzb("App version does not match; dropping event. appId", zzeo.zzn(str));
            return;
        }
        String strZzy = zzhVarZzj.zzy();
        String strZzw = zzhVarZzj.zzw();
        long jZzb = zzhVarZzj.zzb();
        String strZzv = zzhVarZzj.zzv();
        long jZzm = zzhVarZzj.zzm();
        long jZzj = zzhVarZzj.zzj();
        boolean zZzai = zzhVarZzj.zzai();
        String strZzx = zzhVarZzj.zzx();
        zzhVarZzj.zza();
        zzG(zzawVar, new zzq(str, strZzy, strZzw, jZzb, strZzv, jZzm, jZzj, (String) null, zZzai, false, strZzx, 0L, 0L, 0, zzhVarZzj.zzah(), false, zzhVarZzj.zzr(), zzhVarZzj.zzq(), zzhVarZzj.zzk(), zzhVarZzj.zzC(), (String) null, zzh(str).zzh(), "", (String) null));
    }

    @WorkerThread
    final void zzG(zzaw zzawVar, zzq zzqVar) {
        Preconditions.checkNotEmpty(zzqVar.zza);
        zzep zzepVarZzb = zzep.zzb(zzawVar);
        zzlh zzlhVarZzv = zzv();
        Bundle bundle = zzepVarZzb.zzd;
        zzam zzamVar = this.zze;
        zzal(zzamVar);
        zzlhVarZzv.zzL(bundle, zzamVar.zzi(zzqVar.zza));
        zzv().zzM(zzepVarZzb, zzg().zzd(zzqVar.zza));
        zzaw zzawVarZza = zzepVarZzb.zza();
        if ("_cmp".equals(zzawVarZza.zza) && "referrer API v2".equals(zzawVarZza.zzb.zzg("_cis"))) {
            String strZzg = zzawVarZza.zzb.zzg("gclid");
            if (!TextUtils.isEmpty(strZzg)) {
                zzW(new zzlc("_lgclid", zzawVarZza.zzd, strZzg, "auto"), zzqVar);
            }
        }
        zzE(zzawVarZza, zzqVar);
    }

    final void zzH() {
        this.zzs++;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0125 A[Catch: all -> 0x019b, TRY_ENTER, TryCatch #1 {all -> 0x019b, blocks: (B:8:0x0030, B:18:0x004e, B:73:0x018d, B:23:0x0068, B:28:0x00ba, B:27:0x00ab, B:31:0x00c2, B:34:0x00ce, B:36:0x00d4, B:38:0x00dc, B:41:0x00ed, B:44:0x00f9, B:46:0x00ff, B:51:0x010c, B:63:0x0141, B:65:0x0156, B:67:0x0175, B:69:0x0180, B:71:0x0186, B:72:0x018a, B:66:0x0164, B:57:0x0125, B:59:0x0130), top: B:81:0x0030, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0130 A[Catch: all -> 0x019b, TRY_LEAVE, TryCatch #1 {all -> 0x019b, blocks: (B:8:0x0030, B:18:0x004e, B:73:0x018d, B:23:0x0068, B:28:0x00ba, B:27:0x00ab, B:31:0x00c2, B:34:0x00ce, B:36:0x00d4, B:38:0x00dc, B:41:0x00ed, B:44:0x00f9, B:46:0x00ff, B:51:0x010c, B:63:0x0141, B:65:0x0156, B:67:0x0175, B:69:0x0180, B:71:0x0186, B:72:0x018a, B:66:0x0164, B:57:0x0125, B:59:0x0130), top: B:81:0x0030, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0156 A[Catch: all -> 0x019b, TryCatch #1 {all -> 0x019b, blocks: (B:8:0x0030, B:18:0x004e, B:73:0x018d, B:23:0x0068, B:28:0x00ba, B:27:0x00ab, B:31:0x00c2, B:34:0x00ce, B:36:0x00d4, B:38:0x00dc, B:41:0x00ed, B:44:0x00f9, B:46:0x00ff, B:51:0x010c, B:63:0x0141, B:65:0x0156, B:67:0x0175, B:69:0x0180, B:71:0x0186, B:72:0x018a, B:66:0x0164, B:57:0x0125, B:59:0x0130), top: B:81:0x0030, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0164 A[Catch: all -> 0x019b, TryCatch #1 {all -> 0x019b, blocks: (B:8:0x0030, B:18:0x004e, B:73:0x018d, B:23:0x0068, B:28:0x00ba, B:27:0x00ab, B:31:0x00c2, B:34:0x00ce, B:36:0x00d4, B:38:0x00dc, B:41:0x00ed, B:44:0x00f9, B:46:0x00ff, B:51:0x010c, B:63:0x0141, B:65:0x0156, B:67:0x0175, B:69:0x0180, B:71:0x0186, B:72:0x018a, B:66:0x0164, B:57:0x0125, B:59:0x0130), top: B:81:0x0030, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0180 A[Catch: all -> 0x019b, TryCatch #1 {all -> 0x019b, blocks: (B:8:0x0030, B:18:0x004e, B:73:0x018d, B:23:0x0068, B:28:0x00ba, B:27:0x00ab, B:31:0x00c2, B:34:0x00ce, B:36:0x00d4, B:38:0x00dc, B:41:0x00ed, B:44:0x00f9, B:46:0x00ff, B:51:0x010c, B:63:0x0141, B:65:0x0156, B:67:0x0175, B:69:0x0180, B:71:0x0186, B:72:0x018a, B:66:0x0164, B:57:0x0125, B:59:0x0130), top: B:81:0x0030, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x018a A[Catch: all -> 0x019b, TryCatch #1 {all -> 0x019b, blocks: (B:8:0x0030, B:18:0x004e, B:73:0x018d, B:23:0x0068, B:28:0x00ba, B:27:0x00ab, B:31:0x00c2, B:34:0x00ce, B:36:0x00d4, B:38:0x00dc, B:41:0x00ed, B:44:0x00f9, B:46:0x00ff, B:51:0x010c, B:63:0x0141, B:65:0x0156, B:67:0x0175, B:69:0x0180, B:71:0x0186, B:72:0x018a, B:66:0x0164, B:57:0x0125, B:59:0x0130), top: B:81:0x0030, outer: #0 }] */
    @com.google.android.gms.common.util.VisibleForTesting
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final void zzI(java.lang.String r9, int r10, java.lang.Throwable r11, byte[] r12, java.util.Map r13) {
        /*
            Method dump skipped, instructions count: 427
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkz.zzI(java.lang.String, int, java.lang.Throwable, byte[], java.util.Map):void");
    }

    final void zzJ(boolean z) {
        zzag();
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x014f A[Catch: all -> 0x0010, TryCatch #0 {all -> 0x0010, blocks: (B:4:0x000d, B:7:0x0013, B:47:0x0127, B:52:0x015e, B:51:0x014f, B:13:0x002a, B:35:0x00c8, B:37:0x00dd, B:39:0x00e3, B:41:0x00ee, B:40:0x00e7, B:43:0x00f2, B:44:0x00fa, B:46:0x00fc), top: B:57:0x000d, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x002a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @com.google.android.gms.common.util.VisibleForTesting
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final void zzK(int r9, java.lang.Throwable r10, byte[] r11, java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 373
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkz.zzK(int, java.lang.Throwable, byte[], java.lang.String):void");
    }

    @WorkerThread
    final void zzL(zzq zzqVar) {
        int i;
        zzas zzasVarZzn;
        boolean z;
        PackageInfo packageInfo;
        long j;
        ApplicationInfo applicationInfo;
        long j2;
        boolean z2;
        zzaz().zzg();
        zzB();
        Preconditions.checkNotNull(zzqVar);
        Preconditions.checkNotEmpty(zzqVar.zza);
        if (zzak(zzqVar)) {
            zzam zzamVar = this.zze;
            zzal(zzamVar);
            zzh zzhVarZzj = zzamVar.zzj(zzqVar.zza);
            if (zzhVarZzj != null && TextUtils.isEmpty(zzhVarZzj.zzy()) && !TextUtils.isEmpty(zzqVar.zzb)) {
                zzhVarZzj.zzL(0L);
                zzam zzamVar2 = this.zze;
                zzal(zzamVar2);
                zzamVar2.zzD(zzhVarZzj);
                zzfp zzfpVar = this.zzc;
                zzal(zzfpVar);
                zzfpVar.zzm(zzqVar.zza);
            }
            if (!zzqVar.zzh) {
                zzd(zzqVar);
                return;
            }
            long jCurrentTimeMillis = zzqVar.zzm;
            if (jCurrentTimeMillis == 0) {
                jCurrentTimeMillis = zzav().currentTimeMillis();
            }
            this.zzn.zzg().zzd();
            int i2 = zzqVar.zzn;
            if (i2 == 0 || i2 == 1) {
                i = i2;
            } else {
                zzay().zzk().zzc("Incorrect app type, assuming installed app. appId, appType", zzeo.zzn(zzqVar.zza), Integer.valueOf(i2));
                i = 0;
            }
            zzam zzamVar3 = this.zze;
            zzal(zzamVar3);
            zzamVar3.zzw();
            try {
                zzam zzamVar4 = this.zze;
                zzal(zzamVar4);
                zzle zzleVarZzp = zzamVar4.zzp(zzqVar.zza, "_npa");
                if (zzleVarZzp == null || "auto".equals(zzleVarZzp.zzb)) {
                    if (zzqVar.zzr != null) {
                        zzlc zzlcVar = new zzlc("_npa", jCurrentTimeMillis, Long.valueOf(true != zzqVar.zzr.booleanValue() ? 0L : 1L), "auto");
                        if (zzleVarZzp == null || !zzleVarZzp.zze.equals(zzlcVar.zzd)) {
                            zzW(zzlcVar, zzqVar);
                        }
                    } else if (zzleVarZzp != null) {
                        zzP(new zzlc("_npa", jCurrentTimeMillis, null, "auto"), zzqVar);
                    }
                }
                zzam zzamVar5 = this.zze;
                zzal(zzamVar5);
                zzh zzhVarZzj2 = zzamVar5.zzj((String) Preconditions.checkNotNull(zzqVar.zza));
                if (zzhVarZzj2 != null && zzv().zzam(zzqVar.zzb, zzhVarZzj2.zzy(), zzqVar.zzq, zzhVarZzj2.zzr())) {
                    zzay().zzk().zzb("New GMP App Id passed in. Removing cached database data. appId", zzeo.zzn(zzhVarZzj2.zzt()));
                    zzam zzamVar6 = this.zze;
                    zzal(zzamVar6);
                    String strZzt = zzhVarZzj2.zzt();
                    zzamVar6.zzW();
                    zzamVar6.zzg();
                    Preconditions.checkNotEmpty(strZzt);
                    try {
                        SQLiteDatabase sQLiteDatabaseZzh = zzamVar6.zzh();
                        String[] strArr = {strZzt};
                        int iDelete = sQLiteDatabaseZzh.delete("events", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("user_attributes", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("conditional_properties", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("apps", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("raw_events", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("raw_events_metadata", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("event_filters", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("property_filters", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("audience_filter_values", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("consent_settings", "app_id=?", strArr);
                        zzoh.zzc();
                        if (zzamVar6.zzs.zzf().zzs(null, zzeb.zzaB)) {
                            iDelete += sQLiteDatabaseZzh.delete("default_event_params", "app_id=?", strArr);
                        }
                        if (iDelete > 0) {
                            zzamVar6.zzs.zzay().zzj().zzc("Deleted application data. app, records", strZzt, Integer.valueOf(iDelete));
                        }
                    } catch (SQLiteException e) {
                        zzamVar6.zzs.zzay().zzd().zzc("Error deleting application data. appId, error", zzeo.zzn(strZzt), e);
                    }
                    zzhVarZzj2 = null;
                }
                if (zzhVarZzj2 != null) {
                    boolean z3 = (zzhVarZzj2.zzb() == -2147483648L || zzhVarZzj2.zzb() == zzqVar.zzj) ? false : true;
                    String strZzw = zzhVarZzj2.zzw();
                    if (((zzhVarZzj2.zzb() != -2147483648L || strZzw == null || strZzw.equals(zzqVar.zzc)) ? false : true) | z3) {
                        Bundle bundle = new Bundle();
                        bundle.putString("_pv", strZzw);
                        zzE(new zzaw("_au", new zzau(bundle), "auto", jCurrentTimeMillis), zzqVar);
                    }
                }
                zzd(zzqVar);
                if (i == 0) {
                    zzam zzamVar7 = this.zze;
                    zzal(zzamVar7);
                    zzasVarZzn = zzamVar7.zzn(zzqVar.zza, "_f");
                    z = false;
                } else {
                    zzam zzamVar8 = this.zze;
                    zzal(zzamVar8);
                    zzasVarZzn = zzamVar8.zzn(zzqVar.zza, "_v");
                    z = true;
                }
                if (zzasVarZzn == null) {
                    long j3 = ((jCurrentTimeMillis / 3600000) + 1) * 3600000;
                    if (z) {
                        zzW(new zzlc("_fvt", jCurrentTimeMillis, Long.valueOf(j3), "auto"), zzqVar);
                        zzaz().zzg();
                        zzB();
                        Bundle bundle2 = new Bundle();
                        bundle2.putLong("_c", 1L);
                        bundle2.putLong("_r", 1L);
                        bundle2.putLong("_et", 1L);
                        if (zzqVar.zzp) {
                            bundle2.putLong("_dac", 1L);
                        }
                        zzG(new zzaw("_v", new zzau(bundle2), "auto", jCurrentTimeMillis), zzqVar);
                    } else {
                        zzW(new zzlc("_fot", jCurrentTimeMillis, Long.valueOf(j3), "auto"), zzqVar);
                        zzaz().zzg();
                        zzfg zzfgVar = (zzfg) Preconditions.checkNotNull(this.zzm);
                        String str = zzqVar.zza;
                        if (str == null || str.isEmpty()) {
                            zzfgVar.zza.zzay().zzm().zza("Install Referrer Reporter was called with invalid app package name");
                        } else {
                            zzfgVar.zza.zzaz().zzg();
                            if (zzfgVar.zza()) {
                                zzff zzffVar = new zzff(zzfgVar, str);
                                zzfgVar.zza.zzaz().zzg();
                                Intent intent = new Intent("com.google.android.finsky.BIND_GET_INSTALL_REFERRER_SERVICE");
                                intent.setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.externalreferrer.GetInstallReferrerService"));
                                PackageManager packageManager = zzfgVar.zza.zzau().getPackageManager();
                                if (packageManager == null) {
                                    zzfgVar.zza.zzay().zzm().zza("Failed to obtain Package Manager to verify binding conditions for Install Referrer");
                                } else {
                                    List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
                                    if (listQueryIntentServices == null || listQueryIntentServices.isEmpty()) {
                                        zzfgVar.zza.zzay().zzi().zza("Play Service for fetching Install Referrer is unavailable on device");
                                    } else {
                                        ResolveInfo resolveInfo = listQueryIntentServices.get(0);
                                        if (resolveInfo.serviceInfo != null) {
                                            String str2 = resolveInfo.serviceInfo.packageName;
                                            if (resolveInfo.serviceInfo.name != null && "com.android.vending".equals(str2) && zzfgVar.zza()) {
                                                try {
                                                    zzfgVar.zza.zzay().zzj().zzb("Install Referrer Service is", true != ConnectionTracker.getInstance().bindService(zzfgVar.zza.zzau(), new Intent(intent), zzffVar, 1) ? "not available" : "available");
                                                } catch (RuntimeException e2) {
                                                    zzfgVar.zza.zzay().zzd().zzb("Exception occurred while binding to Install Referrer Service", e2.getMessage());
                                                }
                                            } else {
                                                zzfgVar.zza.zzay().zzk().zza("Play Store version 8.3.73 or higher required for Install Referrer");
                                            }
                                        }
                                    }
                                }
                            } else {
                                zzfgVar.zza.zzay().zzi().zza("Install Referrer Reporter is not available");
                            }
                        }
                        zzaz().zzg();
                        zzB();
                        Bundle bundle3 = new Bundle();
                        bundle3.putLong("_c", 1L);
                        bundle3.putLong("_r", 1L);
                        bundle3.putLong("_uwa", 0L);
                        bundle3.putLong("_pfo", 0L);
                        bundle3.putLong("_sys", 0L);
                        bundle3.putLong("_sysu", 0L);
                        bundle3.putLong("_et", 1L);
                        if (zzqVar.zzp) {
                            bundle3.putLong("_dac", 1L);
                        }
                        String str3 = (String) Preconditions.checkNotNull(zzqVar.zza);
                        zzam zzamVar9 = this.zze;
                        zzal(zzamVar9);
                        Preconditions.checkNotEmpty(str3);
                        zzamVar9.zzg();
                        zzamVar9.zzW();
                        long jZzc = zzamVar9.zzc(str3, "first_open_count");
                        if (this.zzn.zzau().getPackageManager() == null) {
                            zzay().zzd().zzb("PackageManager is null, first open report might be inaccurate. appId", zzeo.zzn(str3));
                        } else {
                            try {
                                packageInfo = Wrappers.packageManager(this.zzn.zzau()).getPackageInfo(str3, 0);
                            } catch (PackageManager.NameNotFoundException e3) {
                                zzay().zzd().zzc("Package info is null, first open report might be inaccurate. appId", zzeo.zzn(str3), e3);
                                packageInfo = null;
                            }
                            if (packageInfo == null || packageInfo.firstInstallTime == 0) {
                                j = 1;
                            } else {
                                if (packageInfo.firstInstallTime != packageInfo.lastUpdateTime) {
                                    if (!zzg().zzs(null, zzeb.zzab)) {
                                        bundle3.putLong("_uwa", 1L);
                                    } else if (jZzc == 0) {
                                        bundle3.putLong("_uwa", 1L);
                                        z2 = false;
                                        j2 = 0;
                                    }
                                    j2 = jZzc;
                                    z2 = false;
                                } else {
                                    j2 = jZzc;
                                    z2 = true;
                                }
                                long j4 = j2;
                                j = 1;
                                zzW(new zzlc("_fi", jCurrentTimeMillis, Long.valueOf(true != z2 ? 0L : 1L), "auto"), zzqVar);
                                jZzc = j4;
                            }
                            try {
                                applicationInfo = Wrappers.packageManager(this.zzn.zzau()).getApplicationInfo(str3, 0);
                            } catch (PackageManager.NameNotFoundException e4) {
                                zzay().zzd().zzc("Application info is null, first open report might be inaccurate. appId", zzeo.zzn(str3), e4);
                                applicationInfo = null;
                            }
                            if (applicationInfo != null) {
                                if ((applicationInfo.flags & 1) != 0) {
                                    bundle3.putLong("_sys", j);
                                }
                                if ((applicationInfo.flags & 128) != 0) {
                                    bundle3.putLong("_sysu", j);
                                }
                            }
                        }
                        if (jZzc >= 0) {
                            bundle3.putLong("_pfo", jZzc);
                        }
                        zzG(new zzaw("_f", new zzau(bundle3), "auto", jCurrentTimeMillis), zzqVar);
                    }
                } else if (zzqVar.zzi) {
                    zzG(new zzaw("_cd", new zzau(new Bundle()), "auto", jCurrentTimeMillis), zzqVar);
                }
                zzam zzamVar10 = this.zze;
                zzal(zzamVar10);
                zzamVar10.zzC();
            } finally {
                zzam zzamVar11 = this.zze;
                zzal(zzamVar11);
                zzamVar11.zzx();
            }
        }
    }

    final void zzM() {
        this.zzr++;
    }

    @WorkerThread
    final void zzN(zzac zzacVar) {
        zzq zzqVarZzac = zzac((String) Preconditions.checkNotNull(zzacVar.zza));
        if (zzqVarZzac != null) {
            zzO(zzacVar, zzqVarZzac);
        }
    }

    @WorkerThread
    final void zzO(zzac zzacVar, zzq zzqVar) {
        Preconditions.checkNotNull(zzacVar);
        Preconditions.checkNotEmpty(zzacVar.zza);
        Preconditions.checkNotNull(zzacVar.zzc);
        Preconditions.checkNotEmpty(zzacVar.zzc.zzb);
        zzaz().zzg();
        zzB();
        if (zzak(zzqVar)) {
            if (!zzqVar.zzh) {
                zzd(zzqVar);
                return;
            }
            zzam zzamVar = this.zze;
            zzal(zzamVar);
            zzamVar.zzw();
            try {
                zzd(zzqVar);
                String str = (String) Preconditions.checkNotNull(zzacVar.zza);
                zzam zzamVar2 = this.zze;
                zzal(zzamVar2);
                zzac zzacVarZzk = zzamVar2.zzk(str, zzacVar.zzc.zzb);
                if (zzacVarZzk != null) {
                    zzay().zzc().zzc("Removing conditional user property", zzacVar.zza, this.zzn.zzj().zzf(zzacVar.zzc.zzb));
                    zzam zzamVar3 = this.zze;
                    zzal(zzamVar3);
                    zzamVar3.zza(str, zzacVar.zzc.zzb);
                    if (zzacVarZzk.zze) {
                        zzam zzamVar4 = this.zze;
                        zzal(zzamVar4);
                        zzamVar4.zzA(str, zzacVar.zzc.zzb);
                    }
                    zzaw zzawVar = zzacVar.zzk;
                    if (zzawVar != null) {
                        zzau zzauVar = zzawVar.zzb;
                        zzY((zzaw) Preconditions.checkNotNull(zzv().zzz(str, ((zzaw) Preconditions.checkNotNull(zzacVar.zzk)).zza, zzauVar != null ? zzauVar.zzc() : null, zzacVarZzk.zzb, zzacVar.zzk.zzd, true, true)), zzqVar);
                    }
                } else {
                    zzay().zzk().zzc("Conditional user property doesn't exist", zzeo.zzn(zzacVar.zza), this.zzn.zzj().zzf(zzacVar.zzc.zzb));
                }
                zzam zzamVar5 = this.zze;
                zzal(zzamVar5);
                zzamVar5.zzC();
            } finally {
                zzam zzamVar6 = this.zze;
                zzal(zzamVar6);
                zzamVar6.zzx();
            }
        }
    }

    @WorkerThread
    final void zzP(zzlc zzlcVar, zzq zzqVar) {
        zzaz().zzg();
        zzB();
        if (zzak(zzqVar)) {
            if (!zzqVar.zzh) {
                zzd(zzqVar);
                return;
            }
            if ("_npa".equals(zzlcVar.zzb) && zzqVar.zzr != null) {
                zzay().zzc().zza("Falling back to manifest metadata value for ad personalization");
                zzW(new zzlc("_npa", zzav().currentTimeMillis(), Long.valueOf(true != zzqVar.zzr.booleanValue() ? 0L : 1L), "auto"), zzqVar);
                return;
            }
            zzay().zzc().zzb("Removing user property", this.zzn.zzj().zzf(zzlcVar.zzb));
            zzam zzamVar = this.zze;
            zzal(zzamVar);
            zzamVar.zzw();
            try {
                zzd(zzqVar);
                if (FileDownloadModel.ID.equals(zzlcVar.zzb)) {
                    zzam zzamVar2 = this.zze;
                    zzal(zzamVar2);
                    zzamVar2.zzA((String) Preconditions.checkNotNull(zzqVar.zza), "_lair");
                }
                zzam zzamVar3 = this.zze;
                zzal(zzamVar3);
                zzamVar3.zzA((String) Preconditions.checkNotNull(zzqVar.zza), zzlcVar.zzb);
                zzam zzamVar4 = this.zze;
                zzal(zzamVar4);
                zzamVar4.zzC();
                zzay().zzc().zzb("User property removed", this.zzn.zzj().zzf(zzlcVar.zzb));
            } finally {
                zzam zzamVar5 = this.zze;
                zzal(zzamVar5);
                zzamVar5.zzx();
            }
        }
    }

    @VisibleForTesting
    @WorkerThread
    final void zzQ(zzq zzqVar) {
        if (this.zzy != null) {
            this.zzz = new ArrayList();
            this.zzz.addAll(this.zzy);
        }
        zzam zzamVar = this.zze;
        zzal(zzamVar);
        String str = (String) Preconditions.checkNotNull(zzqVar.zza);
        Preconditions.checkNotEmpty(str);
        zzamVar.zzg();
        zzamVar.zzW();
        try {
            SQLiteDatabase sQLiteDatabaseZzh = zzamVar.zzh();
            String[] strArr = {str};
            int iDelete = sQLiteDatabaseZzh.delete("apps", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("events", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("user_attributes", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("conditional_properties", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("raw_events", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("raw_events_metadata", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("queue", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("audience_filter_values", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("main_event_params", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("default_event_params", "app_id=?", strArr);
            if (iDelete > 0) {
                zzamVar.zzs.zzay().zzj().zzc("Reset analytics data. app, records", str, Integer.valueOf(iDelete));
            }
        } catch (SQLiteException e) {
            zzamVar.zzs.zzay().zzd().zzc("Error resetting analytics data. appId, error", zzeo.zzn(str), e);
        }
        if (zzqVar.zzh) {
            zzL(zzqVar);
        }
    }

    @WorkerThread
    public final void zzR(String str, zzik zzikVar) {
        zzaz().zzg();
        String str2 = this.zzE;
        if (str2 == null || str2.equals(str) || zzikVar != null) {
            this.zzE = str;
            this.zzD = zzikVar;
        }
    }

    @WorkerThread
    protected final void zzS() {
        zzaz().zzg();
        zzam zzamVar = this.zze;
        zzal(zzamVar);
        zzamVar.zzz();
        if (this.zzk.zzc.zza() == 0) {
            this.zzk.zzc.zzb(zzav().currentTimeMillis());
        }
        zzag();
    }

    @WorkerThread
    final void zzT(zzac zzacVar) {
        zzq zzqVarZzac = zzac((String) Preconditions.checkNotNull(zzacVar.zza));
        if (zzqVarZzac != null) {
            zzU(zzacVar, zzqVarZzac);
        }
    }

    @WorkerThread
    final void zzU(zzac zzacVar, zzq zzqVar) {
        Preconditions.checkNotNull(zzacVar);
        Preconditions.checkNotEmpty(zzacVar.zza);
        Preconditions.checkNotNull(zzacVar.zzb);
        Preconditions.checkNotNull(zzacVar.zzc);
        Preconditions.checkNotEmpty(zzacVar.zzc.zzb);
        zzaz().zzg();
        zzB();
        if (zzak(zzqVar)) {
            if (!zzqVar.zzh) {
                zzd(zzqVar);
                return;
            }
            zzac zzacVar2 = new zzac(zzacVar);
            boolean z = false;
            zzacVar2.zze = false;
            zzam zzamVar = this.zze;
            zzal(zzamVar);
            zzamVar.zzw();
            try {
                zzam zzamVar2 = this.zze;
                zzal(zzamVar2);
                zzac zzacVarZzk = zzamVar2.zzk((String) Preconditions.checkNotNull(zzacVar2.zza), zzacVar2.zzc.zzb);
                if (zzacVarZzk != null && !zzacVarZzk.zzb.equals(zzacVar2.zzb)) {
                    zzay().zzk().zzd("Updating a conditional user property with different origin. name, origin, origin (from DB)", this.zzn.zzj().zzf(zzacVar2.zzc.zzb), zzacVar2.zzb, zzacVarZzk.zzb);
                }
                if (zzacVarZzk != null && zzacVarZzk.zze) {
                    zzacVar2.zzb = zzacVarZzk.zzb;
                    zzacVar2.zzd = zzacVarZzk.zzd;
                    zzacVar2.zzh = zzacVarZzk.zzh;
                    zzacVar2.zzf = zzacVarZzk.zzf;
                    zzacVar2.zzi = zzacVarZzk.zzi;
                    zzacVar2.zze = true;
                    zzlc zzlcVar = zzacVar2.zzc;
                    zzacVar2.zzc = new zzlc(zzlcVar.zzb, zzacVarZzk.zzc.zzc, zzlcVar.zza(), zzacVarZzk.zzc.zzf);
                } else if (TextUtils.isEmpty(zzacVar2.zzf)) {
                    zzlc zzlcVar2 = zzacVar2.zzc;
                    zzacVar2.zzc = new zzlc(zzlcVar2.zzb, zzacVar2.zzd, zzlcVar2.zza(), zzacVar2.zzc.zzf);
                    zzacVar2.zze = true;
                    z = true;
                }
                if (zzacVar2.zze) {
                    zzlc zzlcVar3 = zzacVar2.zzc;
                    zzle zzleVar = new zzle((String) Preconditions.checkNotNull(zzacVar2.zza), zzacVar2.zzb, zzlcVar3.zzb, zzlcVar3.zzc, Preconditions.checkNotNull(zzlcVar3.zza()));
                    zzam zzamVar3 = this.zze;
                    zzal(zzamVar3);
                    if (zzamVar3.zzL(zzleVar)) {
                        zzay().zzc().zzd("User property updated immediately", zzacVar2.zza, this.zzn.zzj().zzf(zzleVar.zzc), zzleVar.zze);
                    } else {
                        zzay().zzd().zzd("(2)Too many active user properties, ignoring", zzeo.zzn(zzacVar2.zza), this.zzn.zzj().zzf(zzleVar.zzc), zzleVar.zze);
                    }
                    if (z && zzacVar2.zzi != null) {
                        zzY(new zzaw(zzacVar2.zzi, zzacVar2.zzd), zzqVar);
                    }
                }
                zzam zzamVar4 = this.zze;
                zzal(zzamVar4);
                if (zzamVar4.zzK(zzacVar2)) {
                    zzay().zzc().zzd("Conditional property added", zzacVar2.zza, this.zzn.zzj().zzf(zzacVar2.zzc.zzb), zzacVar2.zzc.zza());
                } else {
                    zzay().zzd().zzd("Too many conditional properties, ignoring", zzeo.zzn(zzacVar2.zza), this.zzn.zzj().zzf(zzacVar2.zzc.zzb), zzacVar2.zzc.zza());
                }
                zzam zzamVar5 = this.zze;
                zzal(zzamVar5);
                zzamVar5.zzC();
            } finally {
                zzam zzamVar6 = this.zze;
                zzal(zzamVar6);
                zzamVar6.zzx();
            }
        }
    }

    @WorkerThread
    final void zzV(String str, zzai zzaiVar) {
        zzaz().zzg();
        zzB();
        this.zzB.put(str, zzaiVar);
        zzam zzamVar = this.zze;
        zzal(zzamVar);
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(zzaiVar);
        zzamVar.zzg();
        zzamVar.zzW();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("consent_state", zzaiVar.zzh());
        try {
            if (zzamVar.zzh().insertWithOnConflict("consent_settings", null, contentValues, 5) == -1) {
                zzamVar.zzs.zzay().zzd().zzb("Failed to insert/update consent setting (got -1). appId", zzeo.zzn(str));
            }
        } catch (SQLiteException e) {
            zzamVar.zzs.zzay().zzd().zzc("Error storing consent setting. appId, error", zzeo.zzn(str), e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00d4  */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final void zzW(com.google.android.gms.measurement.internal.zzlc r14, com.google.android.gms.measurement.internal.zzq r15) {
        /*
            Method dump skipped, instructions count: 475
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkz.zzW(com.google.android.gms.measurement.internal.zzlc, com.google.android.gms.measurement.internal.zzq):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:231:0x0584, code lost:
    
        if (r9 != null) goto L215;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0278 A[Catch: all -> 0x05a6, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x05a6, blocks: (B:3:0x0010, B:5:0x0021, B:9:0x0034, B:11:0x003a, B:13:0x004a, B:15:0x0052, B:17:0x0058, B:19:0x0063, B:21:0x0073, B:23:0x007e, B:25:0x0091, B:27:0x00b1, B:29:0x00b7, B:30:0x00ba, B:32:0x00c6, B:33:0x00de, B:35:0x00ef, B:37:0x00f5, B:42:0x010c, B:59:0x0136, B:46:0x0116, B:62:0x013c, B:63:0x013f, B:64:0x0140, B:68:0x0168, B:72:0x0170, B:78:0x01a6, B:139:0x02ae, B:141:0x02b4, B:143:0x02c0, B:144:0x02c4, B:146:0x02ca, B:148:0x02de, B:152:0x02e7, B:154:0x02ed, B:160:0x0312, B:157:0x0302, B:159:0x030c, B:161:0x0315, B:163:0x0330, B:167:0x033f, B:169:0x0364, B:171:0x039e, B:173:0x03a3, B:175:0x03ab, B:176:0x03ae, B:178:0x03b3, B:179:0x03b6, B:181:0x03c2, B:182:0x03d8, B:183:0x03e0, B:185:0x03f1, B:187:0x0402, B:189:0x0424, B:191:0x0435, B:194:0x047d, B:196:0x048f, B:198:0x04a4, B:200:0x04af, B:201:0x04b8, B:197:0x049d, B:203:0x04fc, B:192:0x046a, B:193:0x0474, B:123:0x0278, B:138:0x02ab, B:207:0x0514, B:208:0x0517, B:209:0x0518, B:215:0x055c, B:233:0x0587, B:235:0x058d, B:237:0x0598, B:220:0x0567, B:240:0x05a2, B:241:0x05a5), top: B:247:0x0010, inners: #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:141:0x02b4 A[Catch: all -> 0x05a6, TryCatch #2 {all -> 0x05a6, blocks: (B:3:0x0010, B:5:0x0021, B:9:0x0034, B:11:0x003a, B:13:0x004a, B:15:0x0052, B:17:0x0058, B:19:0x0063, B:21:0x0073, B:23:0x007e, B:25:0x0091, B:27:0x00b1, B:29:0x00b7, B:30:0x00ba, B:32:0x00c6, B:33:0x00de, B:35:0x00ef, B:37:0x00f5, B:42:0x010c, B:59:0x0136, B:46:0x0116, B:62:0x013c, B:63:0x013f, B:64:0x0140, B:68:0x0168, B:72:0x0170, B:78:0x01a6, B:139:0x02ae, B:141:0x02b4, B:143:0x02c0, B:144:0x02c4, B:146:0x02ca, B:148:0x02de, B:152:0x02e7, B:154:0x02ed, B:160:0x0312, B:157:0x0302, B:159:0x030c, B:161:0x0315, B:163:0x0330, B:167:0x033f, B:169:0x0364, B:171:0x039e, B:173:0x03a3, B:175:0x03ab, B:176:0x03ae, B:178:0x03b3, B:179:0x03b6, B:181:0x03c2, B:182:0x03d8, B:183:0x03e0, B:185:0x03f1, B:187:0x0402, B:189:0x0424, B:191:0x0435, B:194:0x047d, B:196:0x048f, B:198:0x04a4, B:200:0x04af, B:201:0x04b8, B:197:0x049d, B:203:0x04fc, B:192:0x046a, B:193:0x0474, B:123:0x0278, B:138:0x02ab, B:207:0x0514, B:208:0x0517, B:209:0x0518, B:215:0x055c, B:233:0x0587, B:235:0x058d, B:237:0x0598, B:220:0x0567, B:240:0x05a2, B:241:0x05a5), top: B:247:0x0010, inners: #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0514 A[Catch: all -> 0x05a6, TryCatch #2 {all -> 0x05a6, blocks: (B:3:0x0010, B:5:0x0021, B:9:0x0034, B:11:0x003a, B:13:0x004a, B:15:0x0052, B:17:0x0058, B:19:0x0063, B:21:0x0073, B:23:0x007e, B:25:0x0091, B:27:0x00b1, B:29:0x00b7, B:30:0x00ba, B:32:0x00c6, B:33:0x00de, B:35:0x00ef, B:37:0x00f5, B:42:0x010c, B:59:0x0136, B:46:0x0116, B:62:0x013c, B:63:0x013f, B:64:0x0140, B:68:0x0168, B:72:0x0170, B:78:0x01a6, B:139:0x02ae, B:141:0x02b4, B:143:0x02c0, B:144:0x02c4, B:146:0x02ca, B:148:0x02de, B:152:0x02e7, B:154:0x02ed, B:160:0x0312, B:157:0x0302, B:159:0x030c, B:161:0x0315, B:163:0x0330, B:167:0x033f, B:169:0x0364, B:171:0x039e, B:173:0x03a3, B:175:0x03ab, B:176:0x03ae, B:178:0x03b3, B:179:0x03b6, B:181:0x03c2, B:182:0x03d8, B:183:0x03e0, B:185:0x03f1, B:187:0x0402, B:189:0x0424, B:191:0x0435, B:194:0x047d, B:196:0x048f, B:198:0x04a4, B:200:0x04af, B:201:0x04b8, B:197:0x049d, B:203:0x04fc, B:192:0x046a, B:193:0x0474, B:123:0x0278, B:138:0x02ab, B:207:0x0514, B:208:0x0517, B:209:0x0518, B:215:0x055c, B:233:0x0587, B:235:0x058d, B:237:0x0598, B:220:0x0567, B:240:0x05a2, B:241:0x05a5), top: B:247:0x0010, inners: #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x010c A[Catch: all -> 0x05a6, PHI: r11
  0x010c: PHI (r11v22 android.database.Cursor) = (r11v21 android.database.Cursor), (r11v24 android.database.Cursor) binds: [B:57:0x0133, B:41:0x010a] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x05a6, blocks: (B:3:0x0010, B:5:0x0021, B:9:0x0034, B:11:0x003a, B:13:0x004a, B:15:0x0052, B:17:0x0058, B:19:0x0063, B:21:0x0073, B:23:0x007e, B:25:0x0091, B:27:0x00b1, B:29:0x00b7, B:30:0x00ba, B:32:0x00c6, B:33:0x00de, B:35:0x00ef, B:37:0x00f5, B:42:0x010c, B:59:0x0136, B:46:0x0116, B:62:0x013c, B:63:0x013f, B:64:0x0140, B:68:0x0168, B:72:0x0170, B:78:0x01a6, B:139:0x02ae, B:141:0x02b4, B:143:0x02c0, B:144:0x02c4, B:146:0x02ca, B:148:0x02de, B:152:0x02e7, B:154:0x02ed, B:160:0x0312, B:157:0x0302, B:159:0x030c, B:161:0x0315, B:163:0x0330, B:167:0x033f, B:169:0x0364, B:171:0x039e, B:173:0x03a3, B:175:0x03ab, B:176:0x03ae, B:178:0x03b3, B:179:0x03b6, B:181:0x03c2, B:182:0x03d8, B:183:0x03e0, B:185:0x03f1, B:187:0x0402, B:189:0x0424, B:191:0x0435, B:194:0x047d, B:196:0x048f, B:198:0x04a4, B:200:0x04af, B:201:0x04b8, B:197:0x049d, B:203:0x04fc, B:192:0x046a, B:193:0x0474, B:123:0x0278, B:138:0x02ab, B:207:0x0514, B:208:0x0517, B:209:0x0518, B:215:0x055c, B:233:0x0587, B:235:0x058d, B:237:0x0598, B:220:0x0567, B:240:0x05a2, B:241:0x05a5), top: B:247:0x0010, inners: #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x013c A[Catch: all -> 0x05a6, TryCatch #2 {all -> 0x05a6, blocks: (B:3:0x0010, B:5:0x0021, B:9:0x0034, B:11:0x003a, B:13:0x004a, B:15:0x0052, B:17:0x0058, B:19:0x0063, B:21:0x0073, B:23:0x007e, B:25:0x0091, B:27:0x00b1, B:29:0x00b7, B:30:0x00ba, B:32:0x00c6, B:33:0x00de, B:35:0x00ef, B:37:0x00f5, B:42:0x010c, B:59:0x0136, B:46:0x0116, B:62:0x013c, B:63:0x013f, B:64:0x0140, B:68:0x0168, B:72:0x0170, B:78:0x01a6, B:139:0x02ae, B:141:0x02b4, B:143:0x02c0, B:144:0x02c4, B:146:0x02ca, B:148:0x02de, B:152:0x02e7, B:154:0x02ed, B:160:0x0312, B:157:0x0302, B:159:0x030c, B:161:0x0315, B:163:0x0330, B:167:0x033f, B:169:0x0364, B:171:0x039e, B:173:0x03a3, B:175:0x03ab, B:176:0x03ae, B:178:0x03b3, B:179:0x03b6, B:181:0x03c2, B:182:0x03d8, B:183:0x03e0, B:185:0x03f1, B:187:0x0402, B:189:0x0424, B:191:0x0435, B:194:0x047d, B:196:0x048f, B:198:0x04a4, B:200:0x04af, B:201:0x04b8, B:197:0x049d, B:203:0x04fc, B:192:0x046a, B:193:0x0474, B:123:0x0278, B:138:0x02ab, B:207:0x0514, B:208:0x0517, B:209:0x0518, B:215:0x055c, B:233:0x0587, B:235:0x058d, B:237:0x0598, B:220:0x0567, B:240:0x05a2, B:241:0x05a5), top: B:247:0x0010, inners: #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01a0 A[Catch: all -> 0x027f, SQLiteException -> 0x0284, TRY_LEAVE, TryCatch #13 {SQLiteException -> 0x0284, blocks: (B:74:0x019a, B:76:0x01a0, B:80:0x01ad, B:81:0x01b3, B:82:0x01b7, B:83:0x01c2), top: B:258:0x019a }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01ad A[Catch: all -> 0x027f, SQLiteException -> 0x0284, TRY_ENTER, TryCatch #13 {SQLiteException -> 0x0284, blocks: (B:74:0x019a, B:76:0x01a0, B:80:0x01ad, B:81:0x01b3, B:82:0x01b7, B:83:0x01c2), top: B:258:0x019a }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:231:0x0584 -> B:215:0x055c). Please report as a decompilation issue!!! */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final void zzX() {
        /*
            Method dump skipped, instructions count: 1454
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkz.zzX():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0371  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x03c5  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x06fa  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x015d A[Catch: all -> 0x0a2a, TryCatch #5 {all -> 0x0a2a, blocks: (B:28:0x0119, B:30:0x0127, B:32:0x0131, B:37:0x013f, B:84:0x02d2, B:93:0x0309, B:95:0x0349, B:97:0x034e, B:98:0x0365, B:101:0x0373, B:103:0x038c, B:105:0x0391, B:106:0x03a8, B:110:0x03ca, B:114:0x03ed, B:115:0x0404, B:118:0x0411, B:120:0x042c, B:121:0x0442, B:123:0x044c, B:125:0x045b, B:127:0x0461, B:128:0x046c, B:129:0x047a, B:136:0x04c6, B:137:0x04db, B:139:0x0505, B:142:0x051d, B:144:0x0559, B:146:0x0587, B:148:0x05c5, B:149:0x05ca, B:151:0x05d2, B:152:0x05d7, B:154:0x05df, B:155:0x05e4, B:157:0x05f3, B:159:0x05fb, B:160:0x0600, B:162:0x0609, B:163:0x060d, B:165:0x061a, B:166:0x061f, B:168:0x0646, B:170:0x064e, B:171:0x0653, B:173:0x065b, B:174:0x065e, B:176:0x0676, B:179:0x067e, B:180:0x0697, B:182:0x069d, B:184:0x06b1, B:186:0x06bd, B:188:0x06ca, B:192:0x06e4, B:193:0x06f4, B:197:0x06fd, B:198:0x0700, B:200:0x071e, B:202:0x0722, B:204:0x0734, B:206:0x0738, B:208:0x0743, B:209:0x074e, B:211:0x078d, B:213:0x0796, B:214:0x0799, B:216:0x07a6, B:218:0x07c8, B:219:0x07d5, B:220:0x080b, B:222:0x0813, B:224:0x081d, B:225:0x082a, B:227:0x0834, B:228:0x0841, B:229:0x084d, B:231:0x0853, B:233:0x0883, B:234:0x08c9, B:235:0x08d3, B:236:0x08df, B:238:0x08e5, B:247:0x0931, B:248:0x0983, B:250:0x0993, B:264:0x09f7, B:253:0x09ab, B:255:0x09af, B:241:0x08f2, B:243:0x091c, B:259:0x09c8, B:260:0x09df, B:263:0x09e2, B:145:0x0579, B:133:0x04ab, B:87:0x02ea, B:88:0x02f1, B:90:0x02f7, B:92:0x0303, B:42:0x0153, B:44:0x015d, B:46:0x0176, B:51:0x0191, B:59:0x01d3, B:61:0x01d9, B:63:0x01e7, B:65:0x01fc, B:68:0x0203, B:81:0x0299, B:83:0x02a4, B:69:0x022f, B:70:0x024b, B:72:0x025c, B:80:0x027d, B:79:0x026a, B:54:0x01a1, B:58:0x01c7), top: B:282:0x0119, inners: #0, #2, #6, #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01c7 A[Catch: all -> 0x0a2a, TRY_ENTER, TryCatch #5 {all -> 0x0a2a, blocks: (B:28:0x0119, B:30:0x0127, B:32:0x0131, B:37:0x013f, B:84:0x02d2, B:93:0x0309, B:95:0x0349, B:97:0x034e, B:98:0x0365, B:101:0x0373, B:103:0x038c, B:105:0x0391, B:106:0x03a8, B:110:0x03ca, B:114:0x03ed, B:115:0x0404, B:118:0x0411, B:120:0x042c, B:121:0x0442, B:123:0x044c, B:125:0x045b, B:127:0x0461, B:128:0x046c, B:129:0x047a, B:136:0x04c6, B:137:0x04db, B:139:0x0505, B:142:0x051d, B:144:0x0559, B:146:0x0587, B:148:0x05c5, B:149:0x05ca, B:151:0x05d2, B:152:0x05d7, B:154:0x05df, B:155:0x05e4, B:157:0x05f3, B:159:0x05fb, B:160:0x0600, B:162:0x0609, B:163:0x060d, B:165:0x061a, B:166:0x061f, B:168:0x0646, B:170:0x064e, B:171:0x0653, B:173:0x065b, B:174:0x065e, B:176:0x0676, B:179:0x067e, B:180:0x0697, B:182:0x069d, B:184:0x06b1, B:186:0x06bd, B:188:0x06ca, B:192:0x06e4, B:193:0x06f4, B:197:0x06fd, B:198:0x0700, B:200:0x071e, B:202:0x0722, B:204:0x0734, B:206:0x0738, B:208:0x0743, B:209:0x074e, B:211:0x078d, B:213:0x0796, B:214:0x0799, B:216:0x07a6, B:218:0x07c8, B:219:0x07d5, B:220:0x080b, B:222:0x0813, B:224:0x081d, B:225:0x082a, B:227:0x0834, B:228:0x0841, B:229:0x084d, B:231:0x0853, B:233:0x0883, B:234:0x08c9, B:235:0x08d3, B:236:0x08df, B:238:0x08e5, B:247:0x0931, B:248:0x0983, B:250:0x0993, B:264:0x09f7, B:253:0x09ab, B:255:0x09af, B:241:0x08f2, B:243:0x091c, B:259:0x09c8, B:260:0x09df, B:263:0x09e2, B:145:0x0579, B:133:0x04ab, B:87:0x02ea, B:88:0x02f1, B:90:0x02f7, B:92:0x0303, B:42:0x0153, B:44:0x015d, B:46:0x0176, B:51:0x0191, B:59:0x01d3, B:61:0x01d9, B:63:0x01e7, B:65:0x01fc, B:68:0x0203, B:81:0x0299, B:83:0x02a4, B:69:0x022f, B:70:0x024b, B:72:0x025c, B:80:0x027d, B:79:0x026a, B:54:0x01a1, B:58:0x01c7), top: B:282:0x0119, inners: #0, #2, #6, #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01d9 A[Catch: all -> 0x0a2a, TryCatch #5 {all -> 0x0a2a, blocks: (B:28:0x0119, B:30:0x0127, B:32:0x0131, B:37:0x013f, B:84:0x02d2, B:93:0x0309, B:95:0x0349, B:97:0x034e, B:98:0x0365, B:101:0x0373, B:103:0x038c, B:105:0x0391, B:106:0x03a8, B:110:0x03ca, B:114:0x03ed, B:115:0x0404, B:118:0x0411, B:120:0x042c, B:121:0x0442, B:123:0x044c, B:125:0x045b, B:127:0x0461, B:128:0x046c, B:129:0x047a, B:136:0x04c6, B:137:0x04db, B:139:0x0505, B:142:0x051d, B:144:0x0559, B:146:0x0587, B:148:0x05c5, B:149:0x05ca, B:151:0x05d2, B:152:0x05d7, B:154:0x05df, B:155:0x05e4, B:157:0x05f3, B:159:0x05fb, B:160:0x0600, B:162:0x0609, B:163:0x060d, B:165:0x061a, B:166:0x061f, B:168:0x0646, B:170:0x064e, B:171:0x0653, B:173:0x065b, B:174:0x065e, B:176:0x0676, B:179:0x067e, B:180:0x0697, B:182:0x069d, B:184:0x06b1, B:186:0x06bd, B:188:0x06ca, B:192:0x06e4, B:193:0x06f4, B:197:0x06fd, B:198:0x0700, B:200:0x071e, B:202:0x0722, B:204:0x0734, B:206:0x0738, B:208:0x0743, B:209:0x074e, B:211:0x078d, B:213:0x0796, B:214:0x0799, B:216:0x07a6, B:218:0x07c8, B:219:0x07d5, B:220:0x080b, B:222:0x0813, B:224:0x081d, B:225:0x082a, B:227:0x0834, B:228:0x0841, B:229:0x084d, B:231:0x0853, B:233:0x0883, B:234:0x08c9, B:235:0x08d3, B:236:0x08df, B:238:0x08e5, B:247:0x0931, B:248:0x0983, B:250:0x0993, B:264:0x09f7, B:253:0x09ab, B:255:0x09af, B:241:0x08f2, B:243:0x091c, B:259:0x09c8, B:260:0x09df, B:263:0x09e2, B:145:0x0579, B:133:0x04ab, B:87:0x02ea, B:88:0x02f1, B:90:0x02f7, B:92:0x0303, B:42:0x0153, B:44:0x015d, B:46:0x0176, B:51:0x0191, B:59:0x01d3, B:61:0x01d9, B:63:0x01e7, B:65:0x01fc, B:68:0x0203, B:81:0x0299, B:83:0x02a4, B:69:0x022f, B:70:0x024b, B:72:0x025c, B:80:0x027d, B:79:0x026a, B:54:0x01a1, B:58:0x01c7), top: B:282:0x0119, inners: #0, #2, #6, #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x02a4 A[Catch: all -> 0x0a2a, TryCatch #5 {all -> 0x0a2a, blocks: (B:28:0x0119, B:30:0x0127, B:32:0x0131, B:37:0x013f, B:84:0x02d2, B:93:0x0309, B:95:0x0349, B:97:0x034e, B:98:0x0365, B:101:0x0373, B:103:0x038c, B:105:0x0391, B:106:0x03a8, B:110:0x03ca, B:114:0x03ed, B:115:0x0404, B:118:0x0411, B:120:0x042c, B:121:0x0442, B:123:0x044c, B:125:0x045b, B:127:0x0461, B:128:0x046c, B:129:0x047a, B:136:0x04c6, B:137:0x04db, B:139:0x0505, B:142:0x051d, B:144:0x0559, B:146:0x0587, B:148:0x05c5, B:149:0x05ca, B:151:0x05d2, B:152:0x05d7, B:154:0x05df, B:155:0x05e4, B:157:0x05f3, B:159:0x05fb, B:160:0x0600, B:162:0x0609, B:163:0x060d, B:165:0x061a, B:166:0x061f, B:168:0x0646, B:170:0x064e, B:171:0x0653, B:173:0x065b, B:174:0x065e, B:176:0x0676, B:179:0x067e, B:180:0x0697, B:182:0x069d, B:184:0x06b1, B:186:0x06bd, B:188:0x06ca, B:192:0x06e4, B:193:0x06f4, B:197:0x06fd, B:198:0x0700, B:200:0x071e, B:202:0x0722, B:204:0x0734, B:206:0x0738, B:208:0x0743, B:209:0x074e, B:211:0x078d, B:213:0x0796, B:214:0x0799, B:216:0x07a6, B:218:0x07c8, B:219:0x07d5, B:220:0x080b, B:222:0x0813, B:224:0x081d, B:225:0x082a, B:227:0x0834, B:228:0x0841, B:229:0x084d, B:231:0x0853, B:233:0x0883, B:234:0x08c9, B:235:0x08d3, B:236:0x08df, B:238:0x08e5, B:247:0x0931, B:248:0x0983, B:250:0x0993, B:264:0x09f7, B:253:0x09ab, B:255:0x09af, B:241:0x08f2, B:243:0x091c, B:259:0x09c8, B:260:0x09df, B:263:0x09e2, B:145:0x0579, B:133:0x04ab, B:87:0x02ea, B:88:0x02f1, B:90:0x02f7, B:92:0x0303, B:42:0x0153, B:44:0x015d, B:46:0x0176, B:51:0x0191, B:59:0x01d3, B:61:0x01d9, B:63:0x01e7, B:65:0x01fc, B:68:0x0203, B:81:0x0299, B:83:0x02a4, B:69:0x022f, B:70:0x024b, B:72:0x025c, B:80:0x027d, B:79:0x026a, B:54:0x01a1, B:58:0x01c7), top: B:282:0x0119, inners: #0, #2, #6, #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x02e7  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x02ea A[Catch: all -> 0x0a2a, TryCatch #5 {all -> 0x0a2a, blocks: (B:28:0x0119, B:30:0x0127, B:32:0x0131, B:37:0x013f, B:84:0x02d2, B:93:0x0309, B:95:0x0349, B:97:0x034e, B:98:0x0365, B:101:0x0373, B:103:0x038c, B:105:0x0391, B:106:0x03a8, B:110:0x03ca, B:114:0x03ed, B:115:0x0404, B:118:0x0411, B:120:0x042c, B:121:0x0442, B:123:0x044c, B:125:0x045b, B:127:0x0461, B:128:0x046c, B:129:0x047a, B:136:0x04c6, B:137:0x04db, B:139:0x0505, B:142:0x051d, B:144:0x0559, B:146:0x0587, B:148:0x05c5, B:149:0x05ca, B:151:0x05d2, B:152:0x05d7, B:154:0x05df, B:155:0x05e4, B:157:0x05f3, B:159:0x05fb, B:160:0x0600, B:162:0x0609, B:163:0x060d, B:165:0x061a, B:166:0x061f, B:168:0x0646, B:170:0x064e, B:171:0x0653, B:173:0x065b, B:174:0x065e, B:176:0x0676, B:179:0x067e, B:180:0x0697, B:182:0x069d, B:184:0x06b1, B:186:0x06bd, B:188:0x06ca, B:192:0x06e4, B:193:0x06f4, B:197:0x06fd, B:198:0x0700, B:200:0x071e, B:202:0x0722, B:204:0x0734, B:206:0x0738, B:208:0x0743, B:209:0x074e, B:211:0x078d, B:213:0x0796, B:214:0x0799, B:216:0x07a6, B:218:0x07c8, B:219:0x07d5, B:220:0x080b, B:222:0x0813, B:224:0x081d, B:225:0x082a, B:227:0x0834, B:228:0x0841, B:229:0x084d, B:231:0x0853, B:233:0x0883, B:234:0x08c9, B:235:0x08d3, B:236:0x08df, B:238:0x08e5, B:247:0x0931, B:248:0x0983, B:250:0x0993, B:264:0x09f7, B:253:0x09ab, B:255:0x09af, B:241:0x08f2, B:243:0x091c, B:259:0x09c8, B:260:0x09df, B:263:0x09e2, B:145:0x0579, B:133:0x04ab, B:87:0x02ea, B:88:0x02f1, B:90:0x02f7, B:92:0x0303, B:42:0x0153, B:44:0x015d, B:46:0x0176, B:51:0x0191, B:59:0x01d3, B:61:0x01d9, B:63:0x01e7, B:65:0x01fc, B:68:0x0203, B:81:0x0299, B:83:0x02a4, B:69:0x022f, B:70:0x024b, B:72:0x025c, B:80:0x027d, B:79:0x026a, B:54:0x01a1, B:58:0x01c7), top: B:282:0x0119, inners: #0, #2, #6, #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0349 A[Catch: all -> 0x0a2a, TryCatch #5 {all -> 0x0a2a, blocks: (B:28:0x0119, B:30:0x0127, B:32:0x0131, B:37:0x013f, B:84:0x02d2, B:93:0x0309, B:95:0x0349, B:97:0x034e, B:98:0x0365, B:101:0x0373, B:103:0x038c, B:105:0x0391, B:106:0x03a8, B:110:0x03ca, B:114:0x03ed, B:115:0x0404, B:118:0x0411, B:120:0x042c, B:121:0x0442, B:123:0x044c, B:125:0x045b, B:127:0x0461, B:128:0x046c, B:129:0x047a, B:136:0x04c6, B:137:0x04db, B:139:0x0505, B:142:0x051d, B:144:0x0559, B:146:0x0587, B:148:0x05c5, B:149:0x05ca, B:151:0x05d2, B:152:0x05d7, B:154:0x05df, B:155:0x05e4, B:157:0x05f3, B:159:0x05fb, B:160:0x0600, B:162:0x0609, B:163:0x060d, B:165:0x061a, B:166:0x061f, B:168:0x0646, B:170:0x064e, B:171:0x0653, B:173:0x065b, B:174:0x065e, B:176:0x0676, B:179:0x067e, B:180:0x0697, B:182:0x069d, B:184:0x06b1, B:186:0x06bd, B:188:0x06ca, B:192:0x06e4, B:193:0x06f4, B:197:0x06fd, B:198:0x0700, B:200:0x071e, B:202:0x0722, B:204:0x0734, B:206:0x0738, B:208:0x0743, B:209:0x074e, B:211:0x078d, B:213:0x0796, B:214:0x0799, B:216:0x07a6, B:218:0x07c8, B:219:0x07d5, B:220:0x080b, B:222:0x0813, B:224:0x081d, B:225:0x082a, B:227:0x0834, B:228:0x0841, B:229:0x084d, B:231:0x0853, B:233:0x0883, B:234:0x08c9, B:235:0x08d3, B:236:0x08df, B:238:0x08e5, B:247:0x0931, B:248:0x0983, B:250:0x0993, B:264:0x09f7, B:253:0x09ab, B:255:0x09af, B:241:0x08f2, B:243:0x091c, B:259:0x09c8, B:260:0x09df, B:263:0x09e2, B:145:0x0579, B:133:0x04ab, B:87:0x02ea, B:88:0x02f1, B:90:0x02f7, B:92:0x0303, B:42:0x0153, B:44:0x015d, B:46:0x0176, B:51:0x0191, B:59:0x01d3, B:61:0x01d9, B:63:0x01e7, B:65:0x01fc, B:68:0x0203, B:81:0x0299, B:83:0x02a4, B:69:0x022f, B:70:0x024b, B:72:0x025c, B:80:0x027d, B:79:0x026a, B:54:0x01a1, B:58:0x01c7), top: B:282:0x0119, inners: #0, #2, #6, #7 }] */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final void zzY(com.google.android.gms.measurement.internal.zzaw r30, com.google.android.gms.measurement.internal.zzq r31) throws java.lang.IllegalStateException {
        /*
            Method dump skipped, instructions count: 2617
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkz.zzY(com.google.android.gms.measurement.internal.zzaw, com.google.android.gms.measurement.internal.zzq):void");
    }

    @VisibleForTesting
    @WorkerThread
    final boolean zzZ() {
        zzaz().zzg();
        FileLock fileLock = this.zzw;
        if (fileLock != null && fileLock.isValid()) {
            zzay().zzj().zza("Storage concurrent access okay");
            return true;
        }
        this.zze.zzs.zzf();
        try {
            this.zzx = new RandomAccessFile(new File(this.zzn.zzau().getFilesDir(), "google_app_measurement.db"), "rw").getChannel();
            this.zzw = this.zzx.tryLock();
            if (this.zzw != null) {
                zzay().zzj().zza("Storage concurrent access okay");
                return true;
            }
            zzay().zzd().zza("Storage concurrent data access panic");
            return false;
        } catch (FileNotFoundException e) {
            zzay().zzd().zzb("Failed to acquire storage lock", e);
            return false;
        } catch (IOException e2) {
            zzay().zzd().zzb("Failed to access storage lock file", e2);
            return false;
        } catch (OverlappingFileLockException e3) {
            zzay().zzk().zzb("Storage lock already acquired", e3);
            return false;
        }
    }

    final long zza() {
        long jCurrentTimeMillis = zzav().currentTimeMillis();
        zzju zzjuVar = this.zzk;
        zzjuVar.zzW();
        zzjuVar.zzg();
        long jZza = zzjuVar.zze.zza();
        if (jZza == 0) {
            jZza = zzjuVar.zzs.zzv().zzG().nextInt(86400000) + 1;
            zzjuVar.zze.zzb(jZza);
        }
        return ((((jCurrentTimeMillis + jZza) / 1000) / 60) / 60) / 24;
    }

    @Override // com.google.android.gms.measurement.internal.zzgt
    public final Context zzau() {
        return this.zzn.zzau();
    }

    @Override // com.google.android.gms.measurement.internal.zzgt
    public final Clock zzav() {
        return ((zzfy) Preconditions.checkNotNull(this.zzn)).zzav();
    }

    @Override // com.google.android.gms.measurement.internal.zzgt
    public final zzab zzaw() {
        throw null;
    }

    @Override // com.google.android.gms.measurement.internal.zzgt
    public final zzeo zzay() {
        return ((zzfy) Preconditions.checkNotNull(this.zzn)).zzay();
    }

    @Override // com.google.android.gms.measurement.internal.zzgt
    public final zzfv zzaz() {
        return ((zzfy) Preconditions.checkNotNull(this.zzn)).zzaz();
    }

    @WorkerThread
    final zzh zzd(zzq zzqVar) {
        zzaz().zzg();
        zzB();
        Preconditions.checkNotNull(zzqVar);
        Preconditions.checkNotEmpty(zzqVar.zza);
        zzow.zzc();
        zzkx zzkxVar = null;
        if (zzg().zzs(zzqVar.zza, zzeb.zzat) && !zzqVar.zzw.isEmpty()) {
            this.zzC.put(zzqVar.zza, new zzky(this, zzqVar.zzw));
        }
        zzam zzamVar = this.zze;
        zzal(zzamVar);
        zzh zzhVarZzj = zzamVar.zzj(zzqVar.zza);
        zzai zzaiVarZzc = zzh(zzqVar.zza).zzc(zzai.zzb(zzqVar.zzv));
        String strZzf = zzaiVarZzc.zzi(zzah.AD_STORAGE) ? this.zzk.zzf(zzqVar.zza, zzqVar.zzo) : "";
        if (zzhVarZzj == null) {
            zzhVarZzj = new zzh(this.zzn, zzqVar.zza);
            if (zzaiVarZzc.zzi(zzah.ANALYTICS_STORAGE)) {
                zzhVarZzj.zzH(zzw(zzaiVarZzc));
            }
            if (zzaiVarZzc.zzi(zzah.AD_STORAGE)) {
                zzhVarZzj.zzae(strZzf);
            }
        } else if (zzaiVarZzc.zzi(zzah.AD_STORAGE) && strZzf != null && !strZzf.equals(zzhVarZzj.zzA())) {
            zzhVarZzj.zzae(strZzf);
            if (zzqVar.zzo && !"00000000-0000-0000-0000-000000000000".equals(this.zzk.zzd(zzqVar.zza, zzaiVarZzc).first)) {
                zzhVarZzj.zzH(zzw(zzaiVarZzc));
                zzam zzamVar2 = this.zze;
                zzal(zzamVar2);
                if (zzamVar2.zzp(zzqVar.zza, FileDownloadModel.ID) != null) {
                    zzam zzamVar3 = this.zze;
                    zzal(zzamVar3);
                    if (zzamVar3.zzp(zzqVar.zza, "_lair") == null) {
                        zzle zzleVar = new zzle(zzqVar.zza, "auto", "_lair", zzav().currentTimeMillis(), 1L);
                        zzam zzamVar4 = this.zze;
                        zzal(zzamVar4);
                        zzamVar4.zzL(zzleVar);
                    }
                }
            }
        } else if (TextUtils.isEmpty(zzhVarZzj.zzu()) && zzaiVarZzc.zzi(zzah.ANALYTICS_STORAGE)) {
            zzhVarZzj.zzH(zzw(zzaiVarZzc));
        }
        zzhVarZzj.zzW(zzqVar.zzb);
        zzhVarZzj.zzF(zzqVar.zzq);
        if (!TextUtils.isEmpty(zzqVar.zzk)) {
            zzhVarZzj.zzV(zzqVar.zzk);
        }
        long j = zzqVar.zze;
        if (j != 0) {
            zzhVarZzj.zzX(j);
        }
        if (!TextUtils.isEmpty(zzqVar.zzc)) {
            zzhVarZzj.zzJ(zzqVar.zzc);
        }
        zzhVarZzj.zzK(zzqVar.zzj);
        String str = zzqVar.zzd;
        if (str != null) {
            zzhVarZzj.zzI(str);
        }
        zzhVarZzj.zzS(zzqVar.zzf);
        zzhVarZzj.zzac(zzqVar.zzh);
        if (!TextUtils.isEmpty(zzqVar.zzg)) {
            zzhVarZzj.zzY(zzqVar.zzg);
        }
        zzhVarZzj.zzG(zzqVar.zzo);
        zzhVarZzj.zzad(zzqVar.zzr);
        zzhVarZzj.zzT(zzqVar.zzs);
        zzoz.zzc();
        if (zzg().zzs(null, zzeb.zzar)) {
            zzhVarZzj.zzag(zzqVar.zzx);
        }
        zzns.zzc();
        if (zzg().zzs(null, zzeb.zzaj)) {
            zzhVarZzj.zzaf(zzqVar.zzt);
        } else {
            zzns.zzc();
            if (zzg().zzs(null, zzeb.zzai)) {
                zzhVarZzj.zzaf(null);
            }
        }
        if (zzhVarZzj.zzaj()) {
            zzam zzamVar5 = this.zze;
            zzal(zzamVar5);
            zzamVar5.zzD(zzhVarZzj);
        }
        return zzhVarZzj;
    }

    public final zzaa zzf() {
        zzaa zzaaVar = this.zzh;
        zzal(zzaaVar);
        return zzaaVar;
    }

    public final zzag zzg() {
        return ((zzfy) Preconditions.checkNotNull(this.zzn)).zzf();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0041 A[PHI: r2
  0x0041: PHI (r2v3 java.lang.String) = (r2v1 java.lang.String), (r2v4 java.lang.String) binds: [B:12:0x0047, B:9:0x003f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006e  */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final com.google.android.gms.measurement.internal.zzai zzh(java.lang.String r7) throws java.lang.Throwable {
        /*
            r6 = this;
            com.google.android.gms.measurement.internal.zzai r0 = com.google.android.gms.measurement.internal.zzai.zza
            com.google.android.gms.measurement.internal.zzfv r0 = r6.zzaz()
            r0.zzg()
            r6.zzB()
            java.util.Map r0 = r6.zzB
            java.lang.Object r0 = r0.get(r7)
            com.google.android.gms.measurement.internal.zzai r0 = (com.google.android.gms.measurement.internal.zzai) r0
            if (r0 != 0) goto L72
            com.google.android.gms.measurement.internal.zzam r0 = r6.zze
            zzal(r0)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r7)
            r0.zzg()
            r0.zzW()
            r1 = 1
            java.lang.String[] r1 = new java.lang.String[r1]
            r2 = 0
            r1[r2] = r7
            android.database.sqlite.SQLiteDatabase r3 = r0.zzh()
            java.lang.String r4 = "select consent_state from consent_settings where app_id=? limit 1;"
            r5 = 0
            android.database.Cursor r1 = r3.rawQuery(r4, r1)     // Catch: java.lang.Throwable -> L58 android.database.sqlite.SQLiteException -> L5a
            boolean r3 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L52 android.database.sqlite.SQLiteException -> L55
            if (r3 == 0) goto L45
            java.lang.String r2 = r1.getString(r2)     // Catch: java.lang.Throwable -> L52 android.database.sqlite.SQLiteException -> L55
            if (r1 == 0) goto L4a
        L41:
            r1.close()
            goto L4a
        L45:
            java.lang.String r2 = "G1"
            if (r1 == 0) goto L4a
            goto L41
        L4a:
            com.google.android.gms.measurement.internal.zzai r0 = com.google.android.gms.measurement.internal.zzai.zzb(r2)
            r6.zzV(r7, r0)
            goto L72
        L52:
            r7 = move-exception
            r5 = r1
            goto L6c
        L55:
            r7 = move-exception
            r5 = r1
            goto L5b
        L58:
            r7 = move-exception
            goto L6c
        L5a:
            r7 = move-exception
        L5b:
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzs     // Catch: java.lang.Throwable -> L6b
            com.google.android.gms.measurement.internal.zzeo r0 = r0.zzay()     // Catch: java.lang.Throwable -> L6b
            com.google.android.gms.measurement.internal.zzem r0 = r0.zzd()     // Catch: java.lang.Throwable -> L6b
            java.lang.String r1 = "Database error"
            r0.zzc(r1, r4, r7)     // Catch: java.lang.Throwable -> L6b
            throw r7     // Catch: java.lang.Throwable -> L6b
        L6b:
            r7 = move-exception
        L6c:
            if (r5 == 0) goto L71
            r5.close()
        L71:
            throw r7
        L72:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkz.zzh(java.lang.String):com.google.android.gms.measurement.internal.zzai");
    }

    public final zzam zzi() {
        zzam zzamVar = this.zze;
        zzal(zzamVar);
        return zzamVar;
    }

    public final zzej zzj() {
        return this.zzn.zzj();
    }

    public final zzeu zzl() {
        zzeu zzeuVar = this.zzd;
        zzal(zzeuVar);
        return zzeuVar;
    }

    public final zzew zzm() {
        zzew zzewVar = this.zzf;
        if (zzewVar != null) {
            return zzewVar;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    public final zzfp zzo() {
        zzfp zzfpVar = this.zzc;
        zzal(zzfpVar);
        return zzfpVar;
    }

    final zzfy zzq() {
        return this.zzn;
    }

    public final zzii zzr() {
        zzii zziiVar = this.zzj;
        zzal(zziiVar);
        return zziiVar;
    }

    public final zzju zzs() {
        return this.zzk;
    }

    public final zzlb zzu() {
        zzlb zzlbVar = this.zzi;
        zzal(zzlbVar);
        return zzlbVar;
    }

    public final zzlh zzv() {
        return ((zzfy) Preconditions.checkNotNull(this.zzn)).zzv();
    }

    @WorkerThread
    final String zzw(zzai zzaiVar) {
        if (!zzaiVar.zzi(zzah.ANALYTICS_STORAGE)) {
            return null;
        }
        byte[] bArr = new byte[16];
        zzv().zzG().nextBytes(bArr);
        return String.format(Locale.US, "%032x", new BigInteger(1, bArr));
    }

    final String zzx(zzq zzqVar) throws IllegalStateException {
        try {
            return (String) zzaz().zzh(new zzks(this, zzqVar)).get(30000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            zzay().zzd().zzc("Failed to get app instance id. appId", zzeo.zzn(zzqVar.zza), e);
            return null;
        }
    }

    @WorkerThread
    final void zzz(Runnable runnable) {
        zzaz().zzg();
        if (this.zzq == null) {
            this.zzq = new ArrayList();
        }
        this.zzq.add(runnable);
    }
}
