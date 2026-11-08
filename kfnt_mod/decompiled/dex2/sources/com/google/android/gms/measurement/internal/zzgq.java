package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.os.Binder;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.BinderThread;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.common.util.VisibleForTesting;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzgq extends zzed {
    private final zzkz zza;
    private Boolean zzb;
    private String zzc;

    public zzgq(zzkz zzkzVar, String str) {
        Preconditions.checkNotNull(zzkzVar);
        this.zza = zzkzVar;
        this.zzc = null;
    }

    private final void zzA(zzaw zzawVar, zzq zzqVar) {
        this.zza.zzA();
        this.zza.zzE(zzawVar, zzqVar);
    }

    @BinderThread
    private final void zzy(zzq zzqVar, boolean z) {
        Preconditions.checkNotNull(zzqVar);
        Preconditions.checkNotEmpty(zzqVar.zza);
        zzz(zzqVar.zza, false);
        this.zza.zzv().zzX(zzqVar.zzb, zzqVar.zzq);
    }

    @BinderThread
    private final void zzz(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            this.zza.zzay().zzd().zza("Measurement Service called without app package");
            throw new SecurityException("Measurement Service called without app package");
        }
        if (z) {
            try {
                if (this.zzb == null) {
                    this.zzb = Boolean.valueOf("com.google.android.gms".equals(this.zzc) || UidVerifier.isGooglePlayServicesUid(this.zza.zzau(), Binder.getCallingUid()) || GoogleSignatureVerifier.getInstance(this.zza.zzau()).isUidGoogleSigned(Binder.getCallingUid()));
                }
                if (this.zzb.booleanValue()) {
                    return;
                }
            } catch (SecurityException e) {
                this.zza.zzay().zzd().zzb("Measurement Service called with invalid calling package. appId", zzeo.zzn(str));
                throw e;
            }
        }
        if (this.zzc == null && GooglePlayServicesUtilLight.uidHasPackageName(this.zza.zzau(), Binder.getCallingUid(), str)) {
            this.zzc = str;
        }
        if (str.equals(this.zzc)) {
        } else {
            throw new SecurityException(String.format("Unknown calling package name '%s'.", str));
        }
    }

    @VisibleForTesting
    final zzaw zzb(zzaw zzawVar, zzq zzqVar) {
        zzau zzauVar;
        if ("_cmp".equals(zzawVar.zza) && (zzauVar = zzawVar.zzb) != null && zzauVar.zza() != 0) {
            String strZzg = zzawVar.zzb.zzg("_cis");
            if ("referrer broadcast".equals(strZzg) || "referrer API".equals(strZzg)) {
                this.zza.zzay().zzi().zzb("Event has been filtered ", zzawVar.toString());
                return new zzaw("_cmpx", zzawVar.zzb, zzawVar.zzc, zzawVar.zzd);
            }
        }
        return zzawVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzee
    @BinderThread
    public final String zzd(zzq zzqVar) {
        zzy(zzqVar, false);
        return this.zza.zzx(zzqVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzee
    @BinderThread
    public final List zze(zzq zzqVar, boolean z) {
        zzy(zzqVar, false);
        String str = zzqVar.zza;
        Preconditions.checkNotNull(str);
        try {
            List<zzle> list = (List) this.zza.zzaz().zzh(new zzgn(this, str)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzle zzleVar : list) {
                if (z || !zzlh.zzah(zzleVar.zzc)) {
                    arrayList.add(new zzlc(zzleVar));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzay().zzd().zzc("Failed to get user properties. appId", zzeo.zzn(zzqVar.zza), e);
            return null;
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzee
    @BinderThread
    public final List zzf(String str, String str2, zzq zzqVar) {
        zzy(zzqVar, false);
        String str3 = zzqVar.zza;
        Preconditions.checkNotNull(str3);
        try {
            return (List) this.zza.zzaz().zzh(new zzge(this, str3, str, str2)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzay().zzd().zzb("Failed to get conditional user properties", e);
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzee
    @BinderThread
    public final List zzg(String str, String str2, String str3) {
        zzz(str, true);
        try {
            return (List) this.zza.zzaz().zzh(new zzgf(this, str, str2, str3)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzay().zzd().zzb("Failed to get conditional user properties as", e);
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzee
    @BinderThread
    public final List zzh(String str, String str2, boolean z, zzq zzqVar) {
        zzy(zzqVar, false);
        String str3 = zzqVar.zza;
        Preconditions.checkNotNull(str3);
        try {
            List<zzle> list = (List) this.zza.zzaz().zzh(new zzgc(this, str3, str, str2)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzle zzleVar : list) {
                if (z || !zzlh.zzah(zzleVar.zzc)) {
                    arrayList.add(new zzlc(zzleVar));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzay().zzd().zzc("Failed to query user properties. appId", zzeo.zzn(zzqVar.zza), e);
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzee
    @BinderThread
    public final List zzi(String str, String str2, String str3, boolean z) {
        zzz(str, true);
        try {
            List<zzle> list = (List) this.zza.zzaz().zzh(new zzgd(this, str, str2, str3)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzle zzleVar : list) {
                if (z || !zzlh.zzah(zzleVar.zzc)) {
                    arrayList.add(new zzlc(zzleVar));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzay().zzd().zzc("Failed to get user properties as. appId", zzeo.zzn(str), e);
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzee
    @BinderThread
    public final void zzj(zzq zzqVar) throws IllegalStateException {
        zzy(zzqVar, false);
        zzx(new zzgo(this, zzqVar));
    }

    @Override // com.google.android.gms.measurement.internal.zzee
    @BinderThread
    public final void zzk(zzaw zzawVar, zzq zzqVar) throws IllegalStateException {
        Preconditions.checkNotNull(zzawVar);
        zzy(zzqVar, false);
        zzx(new zzgj(this, zzawVar, zzqVar));
    }

    @Override // com.google.android.gms.measurement.internal.zzee
    @BinderThread
    public final void zzl(zzaw zzawVar, String str, String str2) throws IllegalStateException {
        Preconditions.checkNotNull(zzawVar);
        Preconditions.checkNotEmpty(str);
        zzz(str, true);
        zzx(new zzgk(this, zzawVar, str));
    }

    @Override // com.google.android.gms.measurement.internal.zzee
    @BinderThread
    public final void zzm(zzq zzqVar) throws IllegalStateException {
        Preconditions.checkNotEmpty(zzqVar.zza);
        zzz(zzqVar.zza, false);
        zzx(new zzgg(this, zzqVar));
    }

    @Override // com.google.android.gms.measurement.internal.zzee
    @BinderThread
    public final void zzn(zzac zzacVar, zzq zzqVar) throws IllegalStateException {
        Preconditions.checkNotNull(zzacVar);
        Preconditions.checkNotNull(zzacVar.zzc);
        zzy(zzqVar, false);
        zzac zzacVar2 = new zzac(zzacVar);
        zzacVar2.zza = zzqVar.zza;
        zzx(new zzga(this, zzacVar2, zzqVar));
    }

    @Override // com.google.android.gms.measurement.internal.zzee
    @BinderThread
    public final void zzo(zzac zzacVar) throws IllegalStateException {
        Preconditions.checkNotNull(zzacVar);
        Preconditions.checkNotNull(zzacVar.zzc);
        Preconditions.checkNotEmpty(zzacVar.zza);
        zzz(zzacVar.zza, true);
        zzx(new zzgb(this, new zzac(zzacVar)));
    }

    @Override // com.google.android.gms.measurement.internal.zzee
    @BinderThread
    public final void zzp(zzq zzqVar) throws IllegalStateException {
        Preconditions.checkNotEmpty(zzqVar.zza);
        Preconditions.checkNotNull(zzqVar.zzv);
        zzgi zzgiVar = new zzgi(this, zzqVar);
        Preconditions.checkNotNull(zzgiVar);
        if (this.zza.zzaz().zzs()) {
            zzgiVar.run();
        } else {
            this.zza.zzaz().zzq(zzgiVar);
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzee
    @BinderThread
    public final void zzq(long j, String str, String str2, String str3) throws IllegalStateException {
        zzx(new zzgp(this, str2, str3, str, j));
    }

    @Override // com.google.android.gms.measurement.internal.zzee
    @BinderThread
    public final void zzr(final Bundle bundle, zzq zzqVar) throws IllegalStateException {
        zzy(zzqVar, false);
        final String str = zzqVar.zza;
        Preconditions.checkNotNull(str);
        zzx(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzfz
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzw(str, bundle);
            }
        });
    }

    @Override // com.google.android.gms.measurement.internal.zzee
    @BinderThread
    public final void zzs(zzq zzqVar) throws IllegalStateException {
        zzy(zzqVar, false);
        zzx(new zzgh(this, zzqVar));
    }

    @Override // com.google.android.gms.measurement.internal.zzee
    @BinderThread
    public final void zzt(zzlc zzlcVar, zzq zzqVar) throws IllegalStateException {
        Preconditions.checkNotNull(zzlcVar);
        zzy(zzqVar, false);
        zzx(new zzgm(this, zzlcVar, zzqVar));
    }

    @Override // com.google.android.gms.measurement.internal.zzee
    @BinderThread
    public final byte[] zzu(zzaw zzawVar, String str) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzawVar);
        zzz(str, true);
        this.zza.zzay().zzc().zzb("Log and bundle. event", this.zza.zzj().zzd(zzawVar.zza));
        long jNanoTime = this.zza.zzav().nanoTime() / 1000000;
        try {
            byte[] bArr = (byte[]) this.zza.zzaz().zzi(new zzgl(this, zzawVar, str)).get();
            if (bArr == null) {
                this.zza.zzay().zzd().zzb("Log and bundle returned null. appId", zzeo.zzn(str));
                bArr = new byte[0];
            }
            this.zza.zzay().zzc().zzd("Log and bundle processed. event, size, time_ms", this.zza.zzj().zzd(zzawVar.zza), Integer.valueOf(bArr.length), Long.valueOf((this.zza.zzav().nanoTime() / 1000000) - jNanoTime));
            return bArr;
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzay().zzd().zzd("Failed to log and bundle. appId, event, error", zzeo.zzn(str), this.zza.zzj().zzd(zzawVar.zza), e);
            return null;
        }
    }

    final void zzv(zzaw zzawVar, zzq zzqVar) {
        if (!this.zza.zzo().zzo(zzqVar.zza)) {
            zzA(zzawVar, zzqVar);
            return;
        }
        this.zza.zzay().zzj().zzb("EES config found for", zzqVar.zza);
        zzfp zzfpVarZzo = this.zza.zzo();
        String str = zzqVar.zza;
        com.google.android.gms.internal.measurement.zzc zzcVar = TextUtils.isEmpty(str) ? null : (com.google.android.gms.internal.measurement.zzc) zzfpVarZzo.zzd.get(str);
        if (zzcVar == null) {
            this.zza.zzay().zzj().zzb("EES not loaded for", zzqVar.zza);
            zzA(zzawVar, zzqVar);
            return;
        }
        try {
            Map mapZzs = this.zza.zzu().zzs(zzawVar.zzb.zzc(), true);
            String strZza = zzgv.zza(zzawVar.zza);
            if (strZza == null) {
                strZza = zzawVar.zza;
            }
            if (zzcVar.zze(new com.google.android.gms.internal.measurement.zzaa(strZza, zzawVar.zzd, mapZzs))) {
                if (zzcVar.zzg()) {
                    this.zza.zzay().zzj().zzb("EES edited event", zzawVar.zza);
                    zzA(this.zza.zzu().zzi(zzcVar.zza().zzb()), zzqVar);
                } else {
                    zzA(zzawVar, zzqVar);
                }
                if (zzcVar.zzf()) {
                    for (com.google.android.gms.internal.measurement.zzaa zzaaVar : zzcVar.zza().zzc()) {
                        this.zza.zzay().zzj().zzb("EES logging created event", zzaaVar.zzd());
                        zzA(this.zza.zzu().zzi(zzaaVar), zzqVar);
                    }
                    return;
                }
                return;
            }
        } catch (com.google.android.gms.internal.measurement.zzd unused) {
            this.zza.zzay().zzd().zzc("EES error. appId, eventName", zzqVar.zzb, zzawVar.zza);
        }
        this.zza.zzay().zzj().zzb("EES was not applied to event", zzawVar.zza);
        zzA(zzawVar, zzqVar);
    }

    final /* synthetic */ void zzw(String str, Bundle bundle) {
        zzam zzamVarZzi = this.zza.zzi();
        zzamVarZzi.zzg();
        zzamVarZzi.zzW();
        byte[] bArrZzbv = zzamVarZzi.zzf.zzu().zzj(new zzar(zzamVarZzi.zzs, "", str, "dep", 0L, 0L, bundle)).zzbv();
        zzamVarZzi.zzs.zzay().zzj().zzc("Saving default event parameters, appId, data size", zzamVarZzi.zzs.zzj().zzd(str), Integer.valueOf(bArrZzbv.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put(PushConstants.PARAMS, bArrZzbv);
        try {
            if (zzamVarZzi.zzh().insertWithOnConflict("default_event_params", null, contentValues, 5) == -1) {
                zzamVarZzi.zzs.zzay().zzd().zzb("Failed to insert default event parameters (got -1). appId", zzeo.zzn(str));
            }
        } catch (SQLiteException e) {
            zzamVarZzi.zzs.zzay().zzd().zzc("Error storing default event parameters. appId", zzeo.zzn(str), e);
        }
    }

    @VisibleForTesting
    final void zzx(Runnable runnable) throws IllegalStateException {
        Preconditions.checkNotNull(runnable);
        if (this.zza.zzaz().zzs()) {
            runnable.run();
        } else {
            this.zza.zzaz().zzp(runnable);
        }
    }
}
