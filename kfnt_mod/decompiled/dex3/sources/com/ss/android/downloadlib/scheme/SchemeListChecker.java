package com.ss.android.downloadlib.scheme;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import androidx.annotation.NonNull;
import com.sigmob.sdk.common.Constants;
import com.ss.android.download.api.config.DownloadNetworkFactory;
import com.ss.android.download.api.config.IHttpCallback;
import com.ss.android.downloadad.api.constant.AdBaseConstants;
import com.ss.android.downloadlib.DownloadComponentManager;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.utils.ToolUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class SchemeListChecker {
    private static final String APP_ID = "app_id";
    private static final String DATETIME = "datetime";
    private static final String DEVICE_ID = "device_id";
    private static final String DEVICE_PLATFORM = "device_platform";
    private static final String DEVICE_PLATFORM_VALUE = "android";
    private static final String DEVICE_TYPE = "device_type";
    private static final String KEY_SCHEME_LIST_CHECK_TIME = "scheme_list_check_time";
    private static final int MSG_HANDLE_SCHEME_LIST = 10903;
    private static final int MSG_QUERY_SCHEME_LIST = 10902;
    private static final String SCHEME_FAIL_LIST = "scheme_fail_list";
    private static final String SCHEME_SUCCESS_LIST = "scheme_success_list";
    private static final String SP_SCHEME_LIST_CHECKER = "scheme_list_checker";
    private static final String USER_ID = "user_id";
    private static final String VERSION = "version";
    private Handler mHandler;
    private HandlerThread mHandlerThread = new HandlerThread("SchemeListChecker", 10) { // from class: com.ss.android.downloadlib.scheme.SchemeListChecker.1
        @Override // android.os.HandlerThread
        protected void onLooperPrepared() {
            super.onLooperPrepared();
            SchemeListChecker.this.mHandler = new Handler(SchemeListChecker.this.mHandlerThread.getLooper()) { // from class: com.ss.android.downloadlib.scheme.SchemeListChecker.1.1
                @Override // android.os.Handler
                public void handleMessage(@NonNull Message message) {
                    super.handleMessage(message);
                    switch (message.what) {
                        case 10902:
                            SchemeListChecker.this.querySchemeList();
                            break;
                        case 10903:
                            SchemeListChecker.this.handleSchemeList((JSONArray) message.obj);
                            break;
                    }
                }
            };
            long lastCheckTime = SchemeListChecker.this.getLastCheckTime();
            SchemeListChecker.this.checkNextSchemeList(System.currentTimeMillis() - lastCheckTime < SchemeListHelper.schemeListCheckInterval() ? (lastCheckTime + SchemeListHelper.schemeListCheckInterval()) - System.currentTimeMillis() : 0L);
        }
    };

    SchemeListChecker() {
    }

    void startCheck() {
        startCheck(0L);
    }

    void startCheck(long j) {
        if (SchemeListHelper.schemeListSwitch()) {
            long jSchemeListCheckDelay = SchemeListHelper.schemeListCheckDelay();
            if (jSchemeListCheckDelay > j) {
                j = jSchemeListCheckDelay;
            }
            DownloadComponentManager.getInstance().submitScheduledTask(new Runnable() { // from class: com.ss.android.downloadlib.scheme.SchemeListChecker.2
                @Override // java.lang.Runnable
                public void run() {
                    SchemeListChecker.this.mHandlerThread.start();
                }
            }, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void querySchemeList() {
        if (!SchemeListHelper.schemeListSwitch()) {
            if (Build.VERSION.SDK_INT >= 18) {
                this.mHandlerThread.quitSafely();
                return;
            } else {
                this.mHandlerThread.quit();
                return;
            }
        }
        GlobalInfo.getDownloadNetworkFactory().execute("GET", SchemeListHelper.getSchemeListDomain() + AdBaseConstants.HTTP_PATH_QUERY_SCHEME_LIST, null, new IHttpCallback() { // from class: com.ss.android.downloadlib.scheme.SchemeListChecker.3
            @Override // com.ss.android.download.api.config.IHttpCallback
            public void onResponse(String str) {
                try {
                    try {
                        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("data");
                        if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                            SchemeListChecker.this.checkNextSchemeList(jSONArrayOptJSONArray);
                        }
                    } catch (Exception e) {
                        GlobalInfo.getTTMonitor().monitorException(e, "querySchemeList onResponse");
                    }
                } finally {
                    SchemeListChecker.this.checkNextSchemeList(SchemeListHelper.schemeListCheckInterval());
                }
            }

            @Override // com.ss.android.download.api.config.IHttpCallback
            public void onError(Throwable th) {
                SchemeListChecker.this.checkNextSchemeList(SchemeListHelper.schemeListCheckInterval());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkNextSchemeList(long j) {
        if (SchemeListHelper.schemeListSwitch() && this.mHandler != null && this.mHandlerThread.isAlive()) {
            Message message = new Message();
            message.what = 10902;
            this.mHandler.sendMessageDelayed(message, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkNextSchemeList(JSONArray jSONArray) {
        if (SchemeListHelper.schemeListSwitch() && this.mHandler != null && this.mHandlerThread.isAlive()) {
            Message message = new Message();
            message.what = 10903;
            message.obj = jSONArray;
            this.mHandler.sendMessage(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSchemeList(JSONArray jSONArray) {
        try {
            int length = jSONArray.length();
            JSONArray jSONArray2 = new JSONArray();
            JSONArray jSONArray3 = new JSONArray();
            for (int i = 0; i < length; i++) {
                String strOptString = jSONArray.optString(i);
                if (checkSchemeResult(strOptString)) {
                    jSONArray2.put(strOptString);
                } else {
                    jSONArray3.put(strOptString);
                }
            }
            reportSchemeList(jSONArray2, jSONArray3);
        } catch (Exception e) {
            GlobalInfo.getTTMonitor().monitorException(e, "handleSchemeList");
        }
    }

    private boolean checkSchemeResult(String str) {
        return ToolUtils.isOpenUrlValid(str);
    }

    private void reportSchemeList(JSONArray jSONArray, JSONArray jSONArray2) throws JSONException {
        if (jSONArray == null || jSONArray2 == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        String userId = Constants.FAIL;
        String deviceId = "";
        if (GlobalInfo.getUserInfoListener() != null) {
            userId = GlobalInfo.getUserInfoListener().getUserId();
            deviceId = GlobalInfo.getUserInfoListener().getDeviceId();
        }
        try {
            jSONObject.put("user_id", userId);
            jSONObject.put("app_id", GlobalInfo.getAppInfo().appId);
            jSONObject.put("device_id", deviceId);
            jSONObject.put(DEVICE_PLATFORM, DEVICE_PLATFORM_VALUE);
            jSONObject.put("device_type", Build.MODEL);
            jSONObject.put("version", GlobalInfo.getAppInfo().appVersion);
            jSONObject.put(DATETIME, System.currentTimeMillis());
            jSONObject.put(SCHEME_SUCCESS_LIST, jSONArray);
            jSONObject.put(SCHEME_FAIL_LIST, jSONArray2);
        } catch (Exception e) {
            GlobalInfo.getTTMonitor().monitorException(e, "reportSchemeList");
        }
        GlobalInfo.getDownloadNetworkFactory().postBody(SchemeListHelper.getSchemeListDomain() + AdBaseConstants.HTTP_PATH_REPORT_SCHEME_LIST, jSONObject.toString().getBytes(), DownloadNetworkFactory.ContentType.JSON, 0, new IHttpCallback() { // from class: com.ss.android.downloadlib.scheme.SchemeListChecker.4
            @Override // com.ss.android.download.api.config.IHttpCallback
            public void onError(Throwable th) {
            }

            @Override // com.ss.android.download.api.config.IHttpCallback
            public void onResponse(String str) {
                SchemeListChecker.this.recordLastCheckerTime();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordLastCheckerTime() {
        SharedPreferences.Editor editorEdit = GlobalInfo.getContext().getSharedPreferences(SP_SCHEME_LIST_CHECKER, 0).edit();
        editorEdit.putLong(KEY_SCHEME_LIST_CHECK_TIME, System.currentTimeMillis());
        editorEdit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getLastCheckTime() {
        return GlobalInfo.getContext().getSharedPreferences(SP_SCHEME_LIST_CHECKER, 0).getLong(KEY_SCHEME_LIST_CHECK_TIME, 0L);
    }
}
