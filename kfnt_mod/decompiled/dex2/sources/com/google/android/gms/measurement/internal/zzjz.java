package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.MainThread;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzjz {
    private final Context zza;

    public zzjz(Context context) {
        Preconditions.checkNotNull(context);
        this.zza = context;
    }

    private final zzeo zzk() {
        return zzfy.zzp(this.zza, null, null).zzay();
    }

    @MainThread
    public final int zza(final Intent intent, int i, final int i2) throws IllegalStateException {
        zzfy zzfyVarZzp = zzfy.zzp(this.zza, null, null);
        final zzeo zzeoVarZzay = zzfyVarZzp.zzay();
        if (intent == null) {
            zzeoVarZzay.zzk().zza("AppMeasurementService started with null intent");
            return 2;
        }
        String action = intent.getAction();
        zzfyVarZzp.zzaw();
        zzeoVarZzay.zzj().zzc("Local AppMeasurementService called. startId, action", Integer.valueOf(i2), action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            zzh(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzjw
                @Override // java.lang.Runnable
                public final void run() {
                    this.zza.zzc(i2, zzeoVarZzay, intent);
                }
            });
        }
        return 2;
    }

    @MainThread
    public final IBinder zzb(Intent intent) {
        if (intent == null) {
            zzk().zzd().zza("onBind called with null intent");
            return null;
        }
        String action = intent.getAction();
        if ("com.google.android.gms.measurement.START".equals(action)) {
            return new zzgq(zzkz.zzt(this.zza), null);
        }
        zzk().zzk().zzb("onBind received unknown action", action);
        return null;
    }

    final /* synthetic */ void zzc(int i, zzeo zzeoVar, Intent intent) {
        if (((zzjy) this.zza).zzc(i)) {
            zzeoVar.zzj().zzb("Local AppMeasurementService processed last upload request. StartId", Integer.valueOf(i));
            zzk().zzj().zza("Completed wakeful intent.");
            ((zzjy) this.zza).zza(intent);
        }
    }

    final /* synthetic */ void zzd(zzeo zzeoVar, JobParameters jobParameters) {
        zzeoVar.zzj().zza("AppMeasurementJobService processed last upload request.");
        ((zzjy) this.zza).zzb(jobParameters, false);
    }

    @MainThread
    public final void zze() {
        zzfy zzfyVarZzp = zzfy.zzp(this.zza, null, null);
        zzeo zzeoVarZzay = zzfyVarZzp.zzay();
        zzfyVarZzp.zzaw();
        zzeoVarZzay.zzj().zza("Local AppMeasurementService is starting up");
    }

    @MainThread
    public final void zzf() {
        zzfy zzfyVarZzp = zzfy.zzp(this.zza, null, null);
        zzeo zzeoVarZzay = zzfyVarZzp.zzay();
        zzfyVarZzp.zzaw();
        zzeoVarZzay.zzj().zza("Local AppMeasurementService is shutting down");
    }

    @MainThread
    public final void zzg(Intent intent) {
        if (intent == null) {
            zzk().zzd().zza("onRebind called with null intent");
        } else {
            zzk().zzj().zzb("onRebind called. action", intent.getAction());
        }
    }

    public final void zzh(Runnable runnable) throws IllegalStateException {
        zzkz zzkzVarZzt = zzkz.zzt(this.zza);
        zzkzVarZzt.zzaz().zzp(new zzjx(this, zzkzVarZzt, runnable));
    }

    @TargetApi(24)
    @MainThread
    public final boolean zzi(final JobParameters jobParameters) throws IllegalStateException {
        zzfy zzfyVarZzp = zzfy.zzp(this.zza, null, null);
        final zzeo zzeoVarZzay = zzfyVarZzp.zzay();
        String string = jobParameters.getExtras().getString("action");
        zzfyVarZzp.zzaw();
        zzeoVarZzay.zzj().zzb("Local AppMeasurementJobService called. action", string);
        if (!"com.google.android.gms.measurement.UPLOAD".equals(string)) {
            return true;
        }
        zzh(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzjv
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzd(zzeoVarZzay, jobParameters);
            }
        });
        return true;
    }

    @MainThread
    public final boolean zzj(Intent intent) {
        if (intent == null) {
            zzk().zzd().zza("onUnbind called with null intent");
            return true;
        }
        zzk().zzj().zzb("onUnbind called for intent. action", intent.getAction());
        return true;
    }
}
