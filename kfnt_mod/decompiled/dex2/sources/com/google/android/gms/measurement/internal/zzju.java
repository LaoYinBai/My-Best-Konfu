package com.google.android.gms.measurement.internal;

import android.util.Pair;
import androidx.annotation.WorkerThread;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzju extends zzkn {
    public final zzez zza;
    public final zzez zzb;
    public final zzez zzc;
    public final zzez zzd;
    public final zzez zze;
    private final Map zzg;

    zzju(zzkz zzkzVar) {
        super(zzkzVar);
        this.zzg = new HashMap();
        zzfd zzfdVarZzm = this.zzs.zzm();
        zzfdVarZzm.getClass();
        this.zza = new zzez(zzfdVarZzm, "last_delete_stale", 0L);
        zzfd zzfdVarZzm2 = this.zzs.zzm();
        zzfdVarZzm2.getClass();
        this.zzb = new zzez(zzfdVarZzm2, "backoff", 0L);
        zzfd zzfdVarZzm3 = this.zzs.zzm();
        zzfdVarZzm3.getClass();
        this.zzc = new zzez(zzfdVarZzm3, "last_upload", 0L);
        zzfd zzfdVarZzm4 = this.zzs.zzm();
        zzfdVarZzm4.getClass();
        this.zzd = new zzez(zzfdVarZzm4, "last_upload_attempt", 0L);
        zzfd zzfdVarZzm5 = this.zzs.zzm();
        zzfdVarZzm5.getClass();
        this.zze = new zzez(zzfdVarZzm5, "midnight_offset", 0L);
    }

    @WorkerThread
    @Deprecated
    final Pair zza(String str) {
        zzjt zzjtVar;
        AdvertisingIdClient.Info advertisingIdInfo;
        zzg();
        long jElapsedRealtime = this.zzs.zzav().elapsedRealtime();
        zzjt zzjtVar2 = (zzjt) this.zzg.get(str);
        if (zzjtVar2 != null && jElapsedRealtime < zzjtVar2.zzc) {
            return new Pair(zzjtVar2.zza, Boolean.valueOf(zzjtVar2.zzb));
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        long jZzi = jElapsedRealtime + this.zzs.zzf().zzi(str, zzeb.zza);
        try {
            advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.zzs.zzau());
        } catch (Exception e) {
            this.zzs.zzay().zzc().zzb("Unable to get advertising id", e);
            zzjtVar = new zzjt("", false, jZzi);
        }
        if (advertisingIdInfo == null) {
            return new Pair("", false);
        }
        String id = advertisingIdInfo.getId();
        zzjtVar = id != null ? new zzjt(id, advertisingIdInfo.isLimitAdTrackingEnabled(), jZzi) : new zzjt("", advertisingIdInfo.isLimitAdTrackingEnabled(), jZzi);
        this.zzg.put(str, zzjtVar);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair(zzjtVar.zza, Boolean.valueOf(zzjtVar.zzb));
    }

    @Override // com.google.android.gms.measurement.internal.zzkn
    protected final boolean zzb() {
        return false;
    }

    @WorkerThread
    final Pair zzd(String str, zzai zzaiVar) {
        return zzaiVar.zzi(zzah.AD_STORAGE) ? zza(str) : new Pair("", false);
    }

    @WorkerThread
    @Deprecated
    final String zzf(String str, boolean z) throws NoSuchAlgorithmException {
        zzg();
        String str2 = z ? (String) zza(str).first : "00000000-0000-0000-0000-000000000000";
        MessageDigest messageDigestZzF = zzlh.zzF();
        if (messageDigestZzF == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new BigInteger(1, messageDigestZzF.digest(str2.getBytes())));
    }
}
