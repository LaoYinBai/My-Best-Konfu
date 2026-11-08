package com.xiaomi.mipush.sdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;
import com.xiaomi.push.er;
import com.xiaomi.push.es;
import java.util.HashSet;
import java.util.Set;

@TargetApi(14)
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a implements Application.ActivityLifecycleCallbacks {
    private Set<String> a = new HashSet();

    private static void a(Application application) {
        application.registerActivityLifecycleCallbacks(new a());
    }

    public static void a(Context context) {
        a((Application) context.getApplicationContext());
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        es esVarA;
        String packageName;
        String strM292a;
        int i;
        Intent intent = activity.getIntent();
        if (intent == null) {
            return;
        }
        String stringExtra = intent.getStringExtra("messageId");
        int intExtra = intent.getIntExtra("eventMessageType", -1);
        if (TextUtils.isEmpty(stringExtra) || intExtra <= 0 || this.a.contains(stringExtra)) {
            return;
        }
        this.a.add(stringExtra);
        if (intExtra == 3000) {
            esVarA = es.a(activity.getApplicationContext());
            packageName = activity.getPackageName();
            strM292a = er.m292a(intExtra);
            i = 3008;
        } else {
            if (intExtra != 1000) {
                return;
            }
            esVarA = es.a(activity.getApplicationContext());
            packageName = activity.getPackageName();
            strM292a = er.m292a(intExtra);
            i = DownloadErrorCode.ERROR_HANDLE_COMPLETE;
        }
        esVarA.a(packageName, strM292a, stringExtra, i, null);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }
}
