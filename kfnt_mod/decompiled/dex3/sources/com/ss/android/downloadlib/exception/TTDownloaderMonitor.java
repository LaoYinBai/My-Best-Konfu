package com.ss.android.downloadlib.exception;

import android.text.TextUtils;
import android.util.Log;
import com.ss.android.download.api.inner.ITTDownloaderMonitor;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.ss.android.socialbase.appdownloader.util.SecurityUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TTDownloaderMonitor implements ITTDownloaderMonitor {
    private static final String SERVICE_NAME = "service_ttdownloader";

    private interface Status {
        public static final int DATA_ERROR = 2;
        public static final int EXCEPTION = 1;
        public static final int PATH_ERROR = 3;
    }

    private static class SingleTonHolder {
        private static TTDownloaderMonitor INSTANCE = new TTDownloaderMonitor();

        private SingleTonHolder() {
        }
    }

    public static TTDownloaderMonitor inst() {
        return SingleTonHolder.INSTANCE;
    }

    @Override // com.ss.android.download.api.inner.ITTDownloaderMonitor
    public void monitorException(Throwable th, String str) throws JSONException {
        monitorException(true, th, str);
    }

    @Override // com.ss.android.download.api.inner.ITTDownloaderMonitor
    public void monitorException(boolean z, Throwable th, String str) throws JSONException {
        if (disableMonitor()) {
            return;
        }
        if (th == null) {
            th = new Throwable();
        }
        if (z) {
            debugCrash(th);
        }
        JSONObject jSONObject = new JSONObject();
        if (TextUtils.isEmpty(str)) {
            str = th.getMessage();
        }
        ToolUtils.safePut(jSONObject, "msg", str);
        ToolUtils.safePut(jSONObject, "stack", Log.getStackTraceString(th));
        GlobalInfo.getDownloaderMonitor().monitorStatusRate(SERVICE_NAME, 1, jSONObject);
    }

    @Override // com.ss.android.download.api.inner.ITTDownloaderMonitor
    public void monitorDataError(String str) throws JSONException {
        monitorDataError(true, str);
    }

    @Override // com.ss.android.download.api.inner.ITTDownloaderMonitor
    public void monitorDataError(boolean z, String str) throws JSONException {
        if (disableMonitor()) {
            return;
        }
        if (z) {
            debugCrash(new RuntimeException(str));
        }
        JSONObject jSONObject = new JSONObject();
        ToolUtils.safePut(jSONObject, "msg", str);
        ToolUtils.safePut(jSONObject, "stack", printStackTrace(new Throwable()));
        GlobalInfo.getDownloaderMonitor().monitorStatusRate(SERVICE_NAME, 2, jSONObject);
    }

    @Override // com.ss.android.download.api.inner.ITTDownloaderMonitor
    public void monitorPathError(String str) throws JSONException {
        monitorPathError(true, str);
    }

    @Override // com.ss.android.download.api.inner.ITTDownloaderMonitor
    public void monitorPathError(boolean z, String str) throws JSONException {
        if (disableMonitor()) {
            return;
        }
        if (z) {
            debugCrash(new RuntimeException(str));
        }
        JSONObject jSONObject = new JSONObject();
        ToolUtils.safePut(jSONObject, "msg", str);
        ToolUtils.safePut(jSONObject, "stack", printStackTrace(new Throwable()));
        GlobalInfo.getDownloaderMonitor().monitorStatusRate(SERVICE_NAME, 3, jSONObject);
    }

    private void debugCrash(Throwable th) {
        if (SecurityUtils.isDebug(GlobalInfo.getContext())) {
            throw new MonitorException(th);
        }
    }

    public static String printStackTrace(Throwable th) {
        try {
            return Log.getStackTraceString(th);
        } catch (Exception unused) {
            return null;
        }
    }

    private boolean disableMonitor() {
        return GlobalInfo.getDownloadSettings().optInt(DownloadSettingKeys.ENABLE_MONITOR, 1) != 1;
    }
}
