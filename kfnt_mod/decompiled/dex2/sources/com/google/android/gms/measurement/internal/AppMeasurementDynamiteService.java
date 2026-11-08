package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* compiled from: com.google.android.gms:play-services-measurement-sdk@@21.1.1 */
@DynamiteApi
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class AppMeasurementDynamiteService extends com.google.android.gms.internal.measurement.zzcb {

    @VisibleForTesting
    zzfy zza = null;

    @GuardedBy("listenerMap")
    private final Map zzb = new ArrayMap();

    @EnsuresNonNull({"scion"})
    private final void zzb() {
        if (this.zza == null) {
            throw new IllegalStateException("Attempting to perform action before initialize.");
        }
    }

    private final void zzc(com.google.android.gms.internal.measurement.zzcf zzcfVar, String str) {
        zzb();
        this.zza.zzv().zzV(zzcfVar, str);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void beginAdUnitExposure(@NonNull String str, long j) throws IllegalStateException, RemoteException {
        zzb();
        this.zza.zzd().zzd(str, j);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void clearConditionalUserProperty(@NonNull String str, @NonNull String str2, @NonNull Bundle bundle) throws IllegalStateException, RemoteException {
        zzb();
        this.zza.zzq().zzA(str, str2, bundle);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void clearMeasurementEnabled(long j) throws IllegalStateException, RemoteException {
        zzb();
        this.zza.zzq().zzU(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void endAdUnitExposure(@NonNull String str, long j) throws IllegalStateException, RemoteException {
        zzb();
        this.zza.zzd().zze(str, j);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void generateEventId(com.google.android.gms.internal.measurement.zzcf zzcfVar) throws RemoteException {
        zzb();
        long jZzq = this.zza.zzv().zzq();
        zzb();
        this.zza.zzv().zzU(zzcfVar, jZzq);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void getAppInstanceId(com.google.android.gms.internal.measurement.zzcf zzcfVar) throws IllegalStateException, RemoteException {
        zzb();
        this.zza.zzaz().zzp(new zzi(this, zzcfVar));
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void getCachedAppInstanceId(com.google.android.gms.internal.measurement.zzcf zzcfVar) throws RemoteException {
        zzb();
        zzc(zzcfVar, this.zza.zzq().zzo());
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void getConditionalUserProperties(String str, String str2, com.google.android.gms.internal.measurement.zzcf zzcfVar) throws IllegalStateException, RemoteException {
        zzb();
        this.zza.zzaz().zzp(new zzm(this, zzcfVar, str, str2));
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void getCurrentScreenClass(com.google.android.gms.internal.measurement.zzcf zzcfVar) throws RemoteException {
        zzb();
        zzc(zzcfVar, this.zza.zzq().zzp());
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void getCurrentScreenName(com.google.android.gms.internal.measurement.zzcf zzcfVar) throws RemoteException {
        zzb();
        zzc(zzcfVar, this.zza.zzq().zzq());
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void getGmpAppId(com.google.android.gms.internal.measurement.zzcf zzcfVar) throws RemoteException {
        String strZzc;
        zzb();
        zzid zzidVarZzq = this.zza.zzq();
        if (zzidVarZzq.zzs.zzw() != null) {
            strZzc = zzidVarZzq.zzs.zzw();
        } else {
            try {
                strZzc = zzij.zzc(zzidVarZzq.zzs.zzau(), "google_app_id", zzidVarZzq.zzs.zzz());
            } catch (IllegalStateException e) {
                zzidVarZzq.zzs.zzay().zzd().zzb("getGoogleAppId failed with exception", e);
                strZzc = null;
            }
        }
        zzc(zzcfVar, strZzc);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void getMaxUserProperties(String str, com.google.android.gms.internal.measurement.zzcf zzcfVar) throws RemoteException {
        zzb();
        this.zza.zzq().zzh(str);
        zzb();
        this.zza.zzv().zzT(zzcfVar, 25);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void getTestFlag(com.google.android.gms.internal.measurement.zzcf zzcfVar, int i) throws RemoteException {
        zzb();
        switch (i) {
            case 0:
                this.zza.zzv().zzV(zzcfVar, this.zza.zzq().zzr());
                break;
            case 1:
                this.zza.zzv().zzU(zzcfVar, this.zza.zzq().zzm().longValue());
                break;
            case 2:
                zzlh zzlhVarZzv = this.zza.zzv();
                double dDoubleValue = this.zza.zzq().zzj().doubleValue();
                Bundle bundle = new Bundle();
                bundle.putDouble(CampaignEx.JSON_KEY_AD_R, dDoubleValue);
                try {
                    zzcfVar.zzd(bundle);
                    break;
                } catch (RemoteException e) {
                    zzlhVarZzv.zzs.zzay().zzk().zzb("Error returning double value to wrapper", e);
                    return;
                }
            case 3:
                this.zza.zzv().zzT(zzcfVar, this.zza.zzq().zzl().intValue());
                break;
            case 4:
                this.zza.zzv().zzP(zzcfVar, this.zza.zzq().zzi().booleanValue());
                break;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void getUserProperties(String str, String str2, boolean z, com.google.android.gms.internal.measurement.zzcf zzcfVar) throws IllegalStateException, RemoteException {
        zzb();
        this.zza.zzaz().zzp(new zzk(this, zzcfVar, str, str2, z));
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void initForTests(@NonNull Map map) throws RemoteException {
        zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void initialize(IObjectWrapper iObjectWrapper, com.google.android.gms.internal.measurement.zzcl zzclVar, long j) throws RemoteException {
        zzfy zzfyVar = this.zza;
        if (zzfyVar == null) {
            this.zza = zzfy.zzp((Context) Preconditions.checkNotNull((Context) ObjectWrapper.unwrap(iObjectWrapper)), zzclVar, Long.valueOf(j));
        } else {
            zzfyVar.zzay().zzk().zza("Attempting to initialize multiple times");
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void isDataCollectionEnabled(com.google.android.gms.internal.measurement.zzcf zzcfVar) throws IllegalStateException, RemoteException {
        zzb();
        this.zza.zzaz().zzp(new zzn(this, zzcfVar));
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void logEvent(@NonNull String str, @NonNull String str2, @NonNull Bundle bundle, boolean z, boolean z2, long j) throws IllegalStateException, RemoteException {
        zzb();
        this.zza.zzq().zzE(str, str2, bundle, z, z2, j);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void logEventAndBundle(String str, String str2, Bundle bundle, com.google.android.gms.internal.measurement.zzcf zzcfVar, long j) throws IllegalStateException, RemoteException {
        zzb();
        Preconditions.checkNotEmpty(str2);
        (bundle != null ? new Bundle(bundle) : new Bundle()).putString("_o", PushConstants.EXTRA_APPLICATION_PENDING_INTENT);
        this.zza.zzaz().zzp(new zzj(this, zzcfVar, new zzaw(str2, new zzau(bundle), PushConstants.EXTRA_APPLICATION_PENDING_INTENT, j), str));
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void logHealthData(int i, @NonNull String str, @NonNull IObjectWrapper iObjectWrapper, @NonNull IObjectWrapper iObjectWrapper2, @NonNull IObjectWrapper iObjectWrapper3) throws RemoteException {
        zzb();
        this.zza.zzay().zzt(i, true, false, str, iObjectWrapper == null ? null : ObjectWrapper.unwrap(iObjectWrapper), iObjectWrapper2 == null ? null : ObjectWrapper.unwrap(iObjectWrapper2), iObjectWrapper3 != null ? ObjectWrapper.unwrap(iObjectWrapper3) : null);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void onActivityCreated(@NonNull IObjectWrapper iObjectWrapper, @NonNull Bundle bundle, long j) throws RemoteException {
        zzb();
        zzic zzicVar = this.zza.zzq().zza;
        if (zzicVar != null) {
            this.zza.zzq().zzB();
            zzicVar.onActivityCreated((Activity) ObjectWrapper.unwrap(iObjectWrapper), bundle);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void onActivityDestroyed(@NonNull IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zzb();
        zzic zzicVar = this.zza.zzq().zza;
        if (zzicVar != null) {
            this.zza.zzq().zzB();
            zzicVar.onActivityDestroyed((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void onActivityPaused(@NonNull IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zzb();
        zzic zzicVar = this.zza.zzq().zza;
        if (zzicVar != null) {
            this.zza.zzq().zzB();
            zzicVar.onActivityPaused((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void onActivityResumed(@NonNull IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zzb();
        zzic zzicVar = this.zza.zzq().zza;
        if (zzicVar != null) {
            this.zza.zzq().zzB();
            zzicVar.onActivityResumed((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void onActivitySaveInstanceState(IObjectWrapper iObjectWrapper, com.google.android.gms.internal.measurement.zzcf zzcfVar, long j) throws RemoteException {
        zzb();
        zzic zzicVar = this.zza.zzq().zza;
        Bundle bundle = new Bundle();
        if (zzicVar != null) {
            this.zza.zzq().zzB();
            zzicVar.onActivitySaveInstanceState((Activity) ObjectWrapper.unwrap(iObjectWrapper), bundle);
        }
        try {
            zzcfVar.zzd(bundle);
        } catch (RemoteException e) {
            this.zza.zzay().zzk().zzb("Error returning bundle value to wrapper", e);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void onActivityStarted(@NonNull IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zzb();
        if (this.zza.zzq().zza != null) {
            this.zza.zzq().zzB();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void onActivityStopped(@NonNull IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zzb();
        if (this.zza.zzq().zza != null) {
            this.zza.zzq().zzB();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void performAction(Bundle bundle, com.google.android.gms.internal.measurement.zzcf zzcfVar, long j) throws RemoteException {
        zzb();
        zzcfVar.zzd(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void registerOnMeasurementEventListener(com.google.android.gms.internal.measurement.zzci zzciVar) throws RemoteException {
        zzgz zzpVar;
        zzb();
        synchronized (this.zzb) {
            zzpVar = (zzgz) this.zzb.get(Integer.valueOf(zzciVar.zzd()));
            if (zzpVar == null) {
                zzpVar = new zzp(this, zzciVar);
                this.zzb.put(Integer.valueOf(zzciVar.zzd()), zzpVar);
            }
        }
        this.zza.zzq().zzJ(zzpVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void resetAnalyticsData(long j) throws IllegalStateException, RemoteException {
        zzb();
        this.zza.zzq().zzK(j);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setConditionalUserProperty(@NonNull Bundle bundle, long j) throws IllegalStateException, RemoteException {
        zzb();
        if (bundle == null) {
            this.zza.zzay().zzd().zza("Conditional user property must not be null");
        } else {
            this.zza.zzq().zzQ(bundle, j);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setConsent(@NonNull final Bundle bundle, final long j) throws IllegalStateException, RemoteException {
        zzb();
        final zzid zzidVarZzq = this.zza.zzq();
        zzidVarZzq.zzs.zzaz().zzq(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzhc
            @Override // java.lang.Runnable
            public final void run() {
                zzid zzidVar = zzidVarZzq;
                Bundle bundle2 = bundle;
                long j2 = j;
                if (TextUtils.isEmpty(zzidVar.zzs.zzh().zzm())) {
                    zzidVar.zzR(bundle2, 0, j2);
                } else {
                    zzidVar.zzs.zzay().zzl().zza("Using developer consent only; google app id found");
                }
            }
        });
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setConsentThirdParty(@NonNull Bundle bundle, long j) throws RemoteException {
        zzb();
        this.zza.zzq().zzR(bundle, -20, j);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setCurrentScreen(@NonNull IObjectWrapper iObjectWrapper, @NonNull String str, @NonNull String str2, long j) throws IllegalStateException, RemoteException {
        zzb();
        this.zza.zzs().zzw((Activity) ObjectWrapper.unwrap(iObjectWrapper), str, str2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setDataCollectionEnabled(boolean z) throws IllegalStateException, RemoteException {
        zzb();
        zzid zzidVarZzq = this.zza.zzq();
        zzidVarZzq.zza();
        zzfy zzfyVar = zzidVarZzq.zzs;
        zzidVarZzq.zzs.zzaz().zzp(new zzhz(zzidVarZzq, z));
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setDefaultEventParameters(@NonNull Bundle bundle) throws IllegalStateException {
        zzb();
        final zzid zzidVarZzq = this.zza.zzq();
        final Bundle bundle2 = bundle == null ? null : new Bundle(bundle);
        zzidVarZzq.zzs.zzaz().zzp(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzhd
            @Override // java.lang.Runnable
            public final void run() {
                zzidVarZzq.zzC(bundle2);
            }
        });
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setEventInterceptor(com.google.android.gms.internal.measurement.zzci zzciVar) throws IllegalStateException, RemoteException {
        zzb();
        zzo zzoVar = new zzo(this, zzciVar);
        if (this.zza.zzaz().zzs()) {
            this.zza.zzq().zzT(zzoVar);
        } else {
            this.zza.zzaz().zzp(new zzl(this, zzoVar));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setInstanceIdProvider(com.google.android.gms.internal.measurement.zzck zzckVar) throws RemoteException {
        zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setMeasurementEnabled(boolean z, long j) throws IllegalStateException, RemoteException {
        zzb();
        this.zza.zzq().zzU(Boolean.valueOf(z));
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setMinimumSessionDuration(long j) throws RemoteException {
        zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setSessionTimeoutDuration(long j) throws IllegalStateException, RemoteException {
        zzb();
        zzid zzidVarZzq = this.zza.zzq();
        zzfy zzfyVar = zzidVarZzq.zzs;
        zzidVarZzq.zzs.zzaz().zzp(new zzhh(zzidVarZzq, j));
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setUserId(@NonNull final String str, long j) throws IllegalStateException, RemoteException {
        zzb();
        final zzid zzidVarZzq = this.zza.zzq();
        if (str != null && TextUtils.isEmpty(str)) {
            zzidVarZzq.zzs.zzay().zzk().zza("User ID must be non-empty or null");
        } else {
            zzidVarZzq.zzs.zzaz().zzp(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzhe
                @Override // java.lang.Runnable
                public final void run() {
                    zzid zzidVar = zzidVarZzq;
                    if (zzidVar.zzs.zzh().zzp(str)) {
                        zzidVar.zzs.zzh().zzo();
                    }
                }
            });
            zzidVarZzq.zzX(null, FileDownloadModel.ID, str, true, j);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setUserProperty(@NonNull String str, @NonNull String str2, @NonNull IObjectWrapper iObjectWrapper, boolean z, long j) throws IllegalStateException, RemoteException {
        zzb();
        this.zza.zzq().zzX(str, str2, ObjectWrapper.unwrap(iObjectWrapper), z, j);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void unregisterOnMeasurementEventListener(com.google.android.gms.internal.measurement.zzci zzciVar) throws RemoteException {
        zzgz zzpVar;
        zzb();
        synchronized (this.zzb) {
            zzpVar = (zzgz) this.zzb.remove(Integer.valueOf(zzciVar.zzd()));
        }
        if (zzpVar == null) {
            zzpVar = new zzp(this, zzciVar);
        }
        this.zza.zzq().zzZ(zzpVar);
    }
}
