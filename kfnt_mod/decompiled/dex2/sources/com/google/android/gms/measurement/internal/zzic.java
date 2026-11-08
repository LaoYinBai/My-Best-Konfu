package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.MainThread;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.1.1 */
@TargetApi(14)
@MainThread
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zzic implements Application.ActivityLifecycleCallbacks {
    final /* synthetic */ zzid zza;

    /* synthetic */ zzic(zzid zzidVar, zzib zzibVar) {
        this.zza = zzidVar;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zzfy zzfyVar;
        Uri data;
        try {
            try {
                this.zza.zzs.zzay().zzj().zza("onActivityCreated");
                Intent intent = activity.getIntent();
                if (intent == null || (data = intent.getData()) == null || !data.isHierarchical()) {
                    zzfyVar = this.zza.zzs;
                } else {
                    this.zza.zzs.zzv();
                    String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
                    boolean z = true;
                    String str = true != ("android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(stringExtra) || "https://www.google.com".equals(stringExtra) || "android-app://com.google.appcrawler".equals(stringExtra)) ? "auto" : "gs";
                    String queryParameter = data.getQueryParameter("referrer");
                    if (bundle != null) {
                        z = false;
                    }
                    this.zza.zzs.zzaz().zzp(new zzia(this, z, data, str, queryParameter));
                    zzfyVar = this.zza.zzs;
                }
            } catch (RuntimeException e) {
                this.zza.zzs.zzay().zzd().zzb("Throwable caught in onActivityCreated", e);
                zzfyVar = this.zza.zzs;
            }
            zzfyVar.zzs().zzr(activity, bundle);
        } catch (Throwable th) {
            this.zza.zzs.zzs().zzr(activity, bundle);
            throw th;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        this.zza.zzs.zzs().zzs(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    @MainThread
    public final void onActivityPaused(Activity activity) throws IllegalStateException {
        this.zza.zzs.zzs().zzt(activity);
        zzki zzkiVarZzu = this.zza.zzs.zzu();
        zzkiVarZzu.zzs.zzaz().zzp(new zzkb(zzkiVarZzu, zzkiVarZzu.zzs.zzav().elapsedRealtime()));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    @MainThread
    public final void onActivityResumed(Activity activity) throws IllegalStateException {
        zzki zzkiVarZzu = this.zza.zzs.zzu();
        zzkiVarZzu.zzs.zzaz().zzp(new zzka(zzkiVarZzu, zzkiVarZzu.zzs.zzav().elapsedRealtime()));
        this.zza.zzs.zzs().zzu(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.zza.zzs.zzs().zzv(activity, bundle);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }
}
