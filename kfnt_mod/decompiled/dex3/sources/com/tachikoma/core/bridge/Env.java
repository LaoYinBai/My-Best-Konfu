package com.tachikoma.core.bridge;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.os.Build;
import com.sigmob.sdk.common.Constants;
import com.tachikoma.core.BuildConfig;
import com.tachikoma.core.component.anim.AnimationProperty;
import com.tachikoma.core.utility.TKConverter;
import com.taobao.accs.utl.UtilityImpl;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Env {
    public static TKEnvListener mEnvListener;
    public static HashMap<String, Object> sContextInfo = new HashMap<>();

    public interface TKEnvListener {
        void onUpdate(HashMap<String, Object> map);
    }

    public Env(Context context) {
    }

    public static String getApplicationName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int i = applicationInfo.labelRes;
        try {
            return i == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(i);
        } catch (Exception e) {
            e.printStackTrace();
            return applicationInfo.name;
        }
    }

    public static String getApplicationVersion(Context context) {
        return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
    }

    public static void initEnvironment(Context context) {
        String applicationVersion;
        String applicationName = getApplicationName(context);
        try {
            applicationVersion = getApplicationVersion(context);
        } catch (Throwable unused) {
            applicationVersion = UtilityImpl.NET_TYPE_UNKNOWN;
        }
        String str = Build.VERSION.RELEASE;
        int i = Resources.getSystem().getDisplayMetrics().widthPixels;
        int i2 = Resources.getSystem().getDisplayMetrics().heightPixels;
        float f = context.getResources().getDisplayMetrics().density;
        int iPx2dp = (int) TKConverter.px2dp(context, i);
        int iPx2dp2 = (int) TKConverter.px2dp(context, i2);
        sContextInfo.put("platform", "Android");
        sContextInfo.put(Constants.APPNAME, applicationName);
        sContextInfo.put(com.taobao.accs.common.Constants.KEY_APP_VERSION, applicationVersion);
        sContextInfo.put("sysVersion", str);
        sContextInfo.put("deviceWidth", Integer.valueOf(iPx2dp));
        sContextInfo.put("deviceHeight", Integer.valueOf(iPx2dp2));
        sContextInfo.put("availableWidth", Integer.valueOf(iPx2dp));
        sContextInfo.put("availableHeight", Integer.valueOf(iPx2dp2));
        sContextInfo.put("engineVersion", BuildConfig.VERSION_NAME);
        sContextInfo.put(AnimationProperty.SCALE, Float.valueOf(f));
    }

    public static void put(String str, Object obj) {
        sContextInfo.put(str, obj);
        if (mEnvListener != null) {
            mEnvListener.onUpdate(sContextInfo);
        }
    }
}
