package com.apm.insight.runtime;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.apm.insight.ICommonParams;
import com.appsflyer.share.Constants;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ConfigManager {
    private static final String ALOG_UPLOAD_URL = "https://mon.snssdk.com/monitor/collect/c/logcollect";
    private static final String ASAN_REPORT_URL = "https://mon.snssdk.com/monitor/collect/c/native_bin_crash";
    public static final long BLOCK_MONITOR_INTERVAL = 1000;
    private static final long BLOCK_MONITOR_MIN_INTERVAL = 10;
    private static final String CONFIG_URL = "https://tbm.snssdk.com/settings/get";
    public static final String CONFIG_URL_SUFFIX = "/settings/get";
    private static final String CORE_DUMP_URL = "https://i.snssdk.com/monitor/collect/c/core_dump_collect";
    private static final String EXCEPTION_URL = "https://tbm.snssdk.com/monitor/collect/c/exception";
    public static final String EXCEPTION_URL_SUFFIX = "/monitor/collect/c/exception";
    private static final String JAVA_CRASH_URL = "https://tbm.snssdk.com/monitor/collect/c/crash";
    public static final String JAVA_URL_SUFFIX = "/monitor/collect/c/crash";
    private static final long LAUNCH_CRASH_INTERVAL = 8000;
    private static final String LAUNCH_CRASH_URL = "https://tbm.snssdk.com/monitor/collect/c/exception/dump_collection";
    public static final String LAUNCH_URL_SUFFIX = "/monitor/collect/c/exception/dump_collection";
    public static final String LOG_TYPE_ALL_STACK = "npth_enable_all_thread_stack";
    private static final String NATIVE_CRASH_URL = "https://tbm.snssdk.com/monitor/collect/c/native_bin_crash";
    private static final String NATIVE_MEM_URL = "https://i.snssdk.com/monitor/collect/c/rapheal_file_collect";
    public static final String NATIVE_URL_SUFFIX = "/monitor/collect/c/native_bin_crash";
    private ThreadPoolExecutor mThreadPoolExecutor;
    private boolean reportErrorEnable = true;
    private String mNativeMemUrl = NATIVE_MEM_URL;
    private String mCoreDumpUrl = CORE_DUMP_URL;
    private String mJavaCrashUploadUrl = JAVA_CRASH_URL;
    private String mLaunchCrashUploadUrl = LAUNCH_CRASH_URL;
    private String mExceptionUploadUrl = EXCEPTION_URL;
    private String mConfigUrl = CONFIG_URL;
    private String mNativeCrashUploadUrl = NATIVE_CRASH_URL;
    private String mAlogUploadUrl = ALOG_UPLOAD_URL;
    private String mAsanReportUploadUrl = ASAN_REPORT_URL;
    private long mLaunchCrashInterval = LAUNCH_CRASH_INTERVAL;
    private com.apm.insight.d mEncryptImpl = new com.apm.insight.d() { // from class: com.apm.insight.runtime.ConfigManager.1
        @Override // com.apm.insight.d
        public byte[] a(byte[] bArr) {
            return com.apm.insight.l.g.a(bArr);
        }
    };
    private int mLogcatDumpCount = 512;
    private int mLogcatLevel = 1;
    private boolean mNativeCrashMiniDump = true;
    private boolean mEnsureEnable = true;
    private boolean mEnsureWithLogcat = false;
    private long mBlockMonitorInterval = 1000;
    private boolean mBlockMonitorEnable = false;
    private boolean mIsDebugMode = false;

    public static void setDefaultCommonParams(ICommonParams iCommonParams, Context context) {
        com.apm.insight.h.a(new d(context, iCommonParams));
    }

    public static void updateDid(final String str) {
        p.b().a(new Runnable() { // from class: com.apm.insight.runtime.ConfigManager.2
            @Override // java.lang.Runnable
            public void run() {
                com.apm.insight.h.c().a(str);
                com.apm.insight.j.b.d();
            }
        });
    }

    public String getAlogUploadUrl() {
        return this.mAlogUploadUrl;
    }

    public String getAsanReportUploadUrl() {
        return this.mAsanReportUploadUrl;
    }

    public long getBlockInterval() {
        return this.mBlockMonitorInterval;
    }

    public String getConfigUrl() {
        return this.mConfigUrl;
    }

    public String getCoreDumpUrl() {
        return this.mCoreDumpUrl;
    }

    @NonNull
    public com.apm.insight.d getEncryptImpl() {
        return this.mEncryptImpl;
    }

    public String getExceptionUploadUrl() {
        return this.mExceptionUploadUrl;
    }

    public Set<String> getFilterThreadSet() {
        return com.apm.insight.l.j.a();
    }

    public String getJavaCrashUploadUrl() {
        return this.mJavaCrashUploadUrl;
    }

    public long getLaunchCrashInterval() {
        return this.mLaunchCrashInterval;
    }

    public String getLaunchCrashUploadUrl() {
        return this.mLaunchCrashUploadUrl;
    }

    public int getLogcatDumpCount() {
        return this.mLogcatDumpCount;
    }

    public int getLogcatLevel() {
        return this.mLogcatLevel;
    }

    public String getNativeCrashUploadUrl() {
        return this.mNativeCrashUploadUrl;
    }

    public String getNativeMemUrl() {
        return this.mNativeMemUrl;
    }

    public ThreadPoolExecutor getThreadPoolExecutor() {
        return this.mThreadPoolExecutor;
    }

    public boolean isApmExists() {
        return com.apm.insight.k.a.c();
    }

    public boolean isBlockMonitorEnable() {
        return this.mBlockMonitorEnable;
    }

    public boolean isCrashIgnored(final String str) {
        try {
            f fVar = new f() { // from class: com.apm.insight.runtime.ConfigManager.3
                @Override // com.apm.insight.runtime.f
                @Nullable
                public Object a(String str2) {
                    return str2.equals("md5") ? str : super.a(str2);
                }
            };
            if (n.a("java_crash_ignore", fVar)) {
                return true;
            }
            if (!com.apm.insight.l.p.b(com.apm.insight.h.g())) {
                return false;
            }
            com.apm.insight.k.a.d();
            return n.a("java_crash_ignore", fVar);
        } catch (Throwable unused) {
            return false;
        }
    }

    public boolean isDebugMode() {
        return this.mIsDebugMode;
    }

    public boolean isEnsureEnable() {
        return this.mEnsureEnable;
    }

    public boolean isEnsureWithLogcat() {
        return this.mEnsureWithLogcat;
    }

    public boolean isNativeCrashMiniDump() {
        return this.mNativeCrashMiniDump;
    }

    public boolean isReportErrorEnable() {
        return this.reportErrorEnable;
    }

    public void setAlogUploadUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mAlogUploadUrl = str;
    }

    public void setBlockMonitorEnable(boolean z) {
        this.mBlockMonitorEnable = z;
    }

    public void setBlockMonitorInterval(long j) {
        this.mBlockMonitorInterval = j;
    }

    public void setConfigUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mConfigUrl = str;
    }

    public void setCurrentProcessName(String str) {
        com.apm.insight.l.a.a(str);
    }

    public void setDebugMode(boolean z) {
        this.mIsDebugMode = z;
    }

    public void setEncryptImpl(com.apm.insight.d dVar) {
        if (dVar != null) {
            this.mEncryptImpl = dVar;
        }
    }

    public void setEnsureEnable(boolean z) {
        this.mEnsureEnable = z;
    }

    public void setEnsureWithLogcat(boolean z) {
        this.mEnsureWithLogcat = z;
    }

    public void setJavaCrashUploadUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mJavaCrashUploadUrl = str;
    }

    public void setLaunchCrashInterval(long j) {
        if (j > 0) {
            this.mLaunchCrashInterval = j;
        }
    }

    public void setLaunchCrashUrl(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mExceptionUploadUrl = str;
        int iIndexOf = str.indexOf("//");
        if (iIndexOf == -1) {
            str2 = str.substring(0, str.indexOf(Constants.URL_PATH_DELIMITER) + 1) + "monitor/collect/c/exception/dump_collection";
        } else {
            str2 = str.substring(0, str.indexOf(Constants.URL_PATH_DELIMITER, iIndexOf + 2) + 1) + "monitor/collect/c/exception/dump_collection";
        }
        this.mLaunchCrashUploadUrl = str2;
    }

    public void setLogcatDumpCount(int i) {
        if (i > 0) {
            this.mLogcatDumpCount = i;
        }
    }

    public void setLogcatLevel(int i) {
        if (i < 0 || i > 4) {
            return;
        }
        this.mLogcatLevel = i;
    }

    public void setNativeCrashUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mNativeCrashUploadUrl = str;
    }

    public void setReportErrorEnable(boolean z) {
        this.reportErrorEnable = z;
    }

    public void setThreadPoolExecutor(ThreadPoolExecutor threadPoolExecutor) {
        this.mThreadPoolExecutor = threadPoolExecutor;
    }
}
