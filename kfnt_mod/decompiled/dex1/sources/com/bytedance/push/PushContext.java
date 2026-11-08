package com.bytedance.push;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import com.ss.android.pushmanager.IMessageContext;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class PushContext implements IMessageContext {
    private final Application mApplication;

    public String getTweakedChannel() {
        return "update";
    }

    public PushContext(Application application) {
        this.mApplication = application;
    }

    public Context getContext() {
        return this.mApplication;
    }

    public String getAppName() {
        return getManifestAppName(this.mApplication);
    }

    public String getVersion() {
        return getVersionName(this.mApplication);
    }

    public int getVersionCode() {
        return getVersionCode(this.mApplication);
    }

    public int getUpdateVersionCode() {
        return getVersionCode();
    }

    private int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String getManifestAppName(Application application) {
        PackageManager packageManager = application.getPackageManager();
        try {
            return (String) packageManager.getApplicationLabel(packageManager.getPackageInfo(application.getPackageName(), 0).applicationInfo);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }
}
