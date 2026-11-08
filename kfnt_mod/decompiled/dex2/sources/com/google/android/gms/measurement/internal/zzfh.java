package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.MainThread;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzfh {
    private final zza zza;

    /* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
    public interface zza {
        void doStartService(Context context, Intent intent);
    }

    public zzfh(zza zzaVar) {
        Preconditions.checkNotNull(zzaVar);
        this.zza = zzaVar;
    }

    @MainThread
    public final void zza(Context context, Intent intent) {
        zzfy zzfyVarZzp = zzfy.zzp(context, null, null);
        zzeo zzeoVarZzay = zzfyVarZzp.zzay();
        if (intent == null) {
            zzeoVarZzay.zzk().zza("Receiver called with null intent");
            return;
        }
        zzfyVarZzp.zzaw();
        String action = intent.getAction();
        zzeoVarZzay.zzj().zzb("Local receiver got", action);
        if (!"com.google.android.gms.measurement.UPLOAD".equals(action)) {
            if ("com.android.vending.INSTALL_REFERRER".equals(action)) {
                zzeoVarZzay.zzk().zza("Install Referrer Broadcasts are deprecated");
            }
        } else {
            Intent className = new Intent().setClassName(context, "com.google.android.gms.measurement.AppMeasurementService");
            className.setAction("com.google.android.gms.measurement.UPLOAD");
            zzeoVarZzay.zzj().zza("Starting wakeful intent.");
            this.zza.doStartService(context, className);
        }
    }
}
