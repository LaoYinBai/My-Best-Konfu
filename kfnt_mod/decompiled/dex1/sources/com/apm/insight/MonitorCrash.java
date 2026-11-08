package com.apm.insight;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import anet.channel.util.HttpConstant;
import com.apm.applog.e;
import com.apm.insight.l.q;
import com.apm.insight.runtime.ConfigManager;
import com.apm.insight.runtime.o;
import com.apm.insight.runtime.p;
import com.appsflyer.share.Constants;
import com.bytedance.sdk.openadsdk.api.plugin.PluginConstants;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class MonitorCrash {
    private static volatile boolean sAppMonitorCrashInit = false;
    com.apm.applog.d mApmApplogConfig;
    AttachUserData mCustomData;
    AttachUserData mCustomLongData;
    HeaderParams mParams;
    HashMap<String, String> mTagMap = new HashMap<>();
    Config mConfig = new Config();

    public class Config {
        String mAid;
        String mChannel;
        String mDeviceId;
        String[] mPackageName;
        String mSSID;
        String[] mSoList;
        String mUID;
        long mVersionInt = -1;
        String mVersionStr;

        public Config() {
        }

        public Config setChannel(String str) {
            this.mChannel = str;
            MonitorCrash.this.mApmApplogConfig.b(str);
            com.apm.insight.j.b.d();
            return this;
        }

        public Config setDeviceId(String str) {
            this.mDeviceId = str;
            MonitorCrash.this.mApmApplogConfig.a(str);
            com.apm.insight.j.b.d();
            return this;
        }

        public Config setPackageName(String str) {
            return setPackageName(str);
        }

        public Config setPackageName(String... strArr) {
            this.mPackageName = strArr;
            com.apm.insight.j.b.d();
            return this;
        }

        public Config setSSID(String str) {
            this.mSSID = str;
            com.apm.insight.j.b.d();
            return this;
        }

        public Config setSoList(String[] strArr) {
            this.mSoList = strArr;
            com.apm.insight.j.b.d();
            return this;
        }

        public Config setUID(String str) {
            this.mUID = str;
            com.apm.insight.j.b.d();
            return this;
        }
    }

    public interface HeaderParams {
        Map<String, Object> getCommonParams();
    }

    private MonitorCrash(Context context, String str, long j, String str2) {
        this.mConfig.mAid = str;
        this.mConfig.mVersionInt = j;
        this.mConfig.mVersionStr = str2;
        g.a(context, this);
        initAppLog(context, true);
    }

    private MonitorCrash(String str, long j, String str2, String... strArr) {
        this.mConfig.mAid = str;
        this.mConfig.mVersionInt = j;
        this.mConfig.mVersionStr = str2;
        this.mConfig.mPackageName = strArr;
        g.a(this);
        initAppLog(h.g(), false);
    }

    @Nullable
    public static MonitorCrash init(Context context, String str, long j, String str2) {
        if (sAppMonitorCrashInit) {
            return null;
        }
        synchronized (MonitorCrash.class) {
            if (sAppMonitorCrashInit) {
                return null;
            }
            sAppMonitorCrashInit = true;
            o.a(context, true, true, true, true, 0L);
            return new MonitorCrash(context, str, j, str2);
        }
    }

    private void initAppLog(final Context context, final boolean z) {
        this.mApmApplogConfig = new com.apm.applog.d(this.mConfig.mAid, "empty");
        p.b().a(new Runnable() { // from class: com.apm.insight.MonitorCrash.1
            @Override // java.lang.Runnable
            public void run() {
                if (z) {
                    MonitorCrash.this.mApmApplogConfig.d((int) MonitorCrash.this.mConfig.mVersionInt);
                    MonitorCrash.this.mApmApplogConfig.c((int) MonitorCrash.this.mConfig.mVersionInt);
                    MonitorCrash.this.mApmApplogConfig.n(MonitorCrash.this.mConfig.mVersionStr);
                    MonitorCrash.this.mApmApplogConfig.m(MonitorCrash.this.mConfig.mVersionStr);
                    MonitorCrash.this.mApmApplogConfig.j(MonitorCrash.this.mConfig.mVersionStr);
                } else {
                    String strB = com.apm.insight.entity.b.b(g.a());
                    HashMap map = new HashMap();
                    map.put("host_app_id", strB);
                    map.put(PluginConstants.KEY_SDK_VERSION, MonitorCrash.this.mConfig.mVersionStr);
                    MonitorCrash.this.mApmApplogConfig.a(map);
                }
                com.apm.applog.a.a(context, MonitorCrash.this.mApmApplogConfig);
            }
        }, 10L);
    }

    public static MonitorCrash initSDK(Context context, String str, long j, String str2, String str3) {
        o.a(context, true, true, true, true, 0L);
        MonitorCrash monitorCrash = new MonitorCrash(str, j, str2, str3);
        monitorCrash.config().setPackageName(str3);
        return monitorCrash;
    }

    public static MonitorCrash initSDK(Context context, String str, long j, String str2, String str3, String[] strArr) {
        o.a(context, true, true, true, true, 0L);
        MonitorCrash monitorCrash = new MonitorCrash(str, j, str2, str3);
        monitorCrash.config().setPackageName(str3).setSoList(strArr);
        return monitorCrash;
    }

    public static MonitorCrash initSDK(Context context, String str, long j, String str2, String... strArr) {
        o.a(context, true, true, true, true, 0L);
        MonitorCrash monitorCrash = new MonitorCrash(str, j, str2, strArr);
        monitorCrash.config().setPackageName(strArr);
        return monitorCrash;
    }

    public static MonitorCrash initSDK(Context context, String str, long j, String str2, String[] strArr, String[] strArr2) {
        o.a(context, true, true, true, true, 0L);
        MonitorCrash monitorCrash = new MonitorCrash(str, j, str2, strArr);
        monitorCrash.config().setPackageName(strArr).setSoList(strArr2);
        return monitorCrash;
    }

    public MonitorCrash addTags(String str, String str2) {
        this.mTagMap.put(str, str2);
        return this;
    }

    @NonNull
    public Config config() {
        return this.mConfig;
    }

    public void reportCustomErr(String str, String str2, Throwable th) {
        com.apm.insight.f.b.a(this, th, str, true, null, str2, "core_exception_monitor");
    }

    public MonitorCrash setCustomDataCallback(AttachUserData attachUserData) {
        this.mCustomData = attachUserData;
        return this;
    }

    public MonitorCrash setReportUrl(String str) {
        int length;
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        int iIndexOf = str.indexOf(HttpConstant.SCHEME_SPLIT);
        if (iIndexOf < 0) {
            length = "https://".length();
            str = "https://" + str;
        } else {
            length = iIndexOf + HttpConstant.SCHEME_SPLIT.length();
        }
        int iIndexOf2 = str.indexOf(Constants.URL_PATH_DELIMITER, length);
        if (iIndexOf2 >= 0) {
            str = str.substring(0, iIndexOf2);
        }
        q.a((Object) ("set url " + str));
        h.i().setLaunchCrashUrl(str + ConfigManager.EXCEPTION_URL_SUFFIX);
        h.i().setJavaCrashUploadUrl(str + ConfigManager.JAVA_URL_SUFFIX);
        h.i().setNativeCrashUrl(str + ConfigManager.NATIVE_URL_SUFFIX);
        h.i().setConfigUrl(str + ConfigManager.CONFIG_URL_SUFFIX);
        this.mApmApplogConfig.a(new e.a().a(str + com.apm.applog.e.f2746c).a(new String[]{str + com.apm.applog.e.e}).a());
        return this;
    }

    public MonitorCrash withOtherHeaders(@Nullable HeaderParams headerParams) {
        this.mParams = headerParams;
        return this;
    }
}
