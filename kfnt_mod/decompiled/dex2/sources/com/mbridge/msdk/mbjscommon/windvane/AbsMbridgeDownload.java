package com.mbridge.msdk.mbjscommon.windvane;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.heytap.mcssdk.mode.CommandMessage;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.DomainCampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.out.IDownloadListener;
import java.io.File;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class AbsMbridgeDownload extends j {
    private static int FAILED = 1;
    private static final int HANDLER_WHAT_KEY = 1;
    private static int SUCCESS;
    private Object currentObject;
    private String downloadUrl;
    private int mProgress;
    private int mStatus;
    private String packageName;
    private WindVaneWebView webView;
    private String TAG = "MbridgeDownload";
    private String JSON_KEY_AKDLUI = DomainCampaignEx.JSON_KEY_AKDLUI;
    private String JSON_KEY_DOWNLOAD_PROGRESS_NOTIFY_INTERVAL = "progressNotifyInterval";
    private String JSON_KEY_UNIQUE = "uniqueKey";
    private String JSON_KEY_URL = "apkURL";
    private String JSON_KEY_PACKAGE_NAME = "packageName";
    private String JSON_KEY_STATUS = "status";
    private String JSON_KEY_PROGRESS = "progress";
    private String JSON_KEY_CAMPAIGN = "campaign";
    private HashMap<String, IDownloadListener> keyListenerMap = new HashMap<>();
    private long DEFAULT_PROGRESS_NOTIFY_INTERVAL = 2000;
    private long progressNotifyInterval = this.DEFAULT_PROGRESS_NOTIFY_INTERVAL;
    private boolean canNotifyProgress = true;
    private Handler handler = new Handler(Looper.getMainLooper()) { // from class: com.mbridge.msdk.mbjscommon.windvane.AbsMbridgeDownload.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 1) {
                return;
            }
            AbsMbridgeDownload.this.canNotifyProgress = true;
        }
    };

    private String statusIntToString(int i) {
        switch (i) {
            case 1:
                return FirebaseAnalytics.Param.SUCCESS;
            case 2:
                return "downloading";
            case 3:
            case 4:
            case 7:
            default:
                return "undownload";
            case 5:
            case 6:
                return CampaignEx.JSON_NATIVE_VIDEO_PAUSE;
            case 8:
                return "fail";
            case 9:
                return "installed";
        }
    }

    public abstract void sendNoticeAndCallBackClick(Object obj, String str);

    private IDownloadListener getiDownloadListenerByUniqueKey(final String str) {
        if (this.keyListenerMap.containsKey(str)) {
            return this.keyListenerMap.get(str);
        }
        IDownloadListener iDownloadListener = new IDownloadListener() { // from class: com.mbridge.msdk.mbjscommon.windvane.AbsMbridgeDownload.2
            @Override // com.mbridge.msdk.out.IDownloadListener
            public final void onStart() {
            }

            @Override // com.mbridge.msdk.out.IDownloadListener
            public final void onStatus(int i) throws JSONException {
                s.a("click control", "onStatus:" + i);
                if (AbsMbridgeDownload.this.mStatus != i) {
                    s.a("click control", "========onStatus:" + i);
                    AbsMbridgeDownload.this.mStatus = i;
                    AbsMbridgeDownload.this.onDownloadAPKStatusChange(i, str);
                }
            }

            @Override // com.mbridge.msdk.out.IDownloadListener
            public final void onProgressUpdate(int i) throws JSONException {
                s.a("click control", "onProgressUpdate:" + i);
                if (AbsMbridgeDownload.this.canNotifyProgress || (i == 100 && AbsMbridgeDownload.this.mProgress != i)) {
                    s.a("click control", "=============onProgressUpdate:" + i);
                    AbsMbridgeDownload.this.canNotifyProgress = false;
                    AbsMbridgeDownload.this.handler.sendEmptyMessageDelayed(1, AbsMbridgeDownload.this.progressNotifyInterval);
                    AbsMbridgeDownload.this.mProgress = i;
                    AbsMbridgeDownload.this.onDownloadAPKProgressChange(i, str);
                }
            }

            @Override // com.mbridge.msdk.out.IDownloadListener
            public final void onEnd(int i, int i2, String str2) {
                s.b(AbsMbridgeDownload.this.TAG, "download listener onEnd result = " + i + " nid = " + i2 + " file = " + str2);
                if (TextUtils.isEmpty(str2)) {
                    return;
                }
                File file = new File(str2);
                if (file.exists()) {
                    com.mbridge.msdk.click.c.a(AbsMbridgeDownload.this.mContext, Uri.fromFile(file), AbsMbridgeDownload.this.downloadUrl, str);
                }
            }
        };
        this.keyListenerMap.put(str, iDownloadListener);
        return iDownloadListener;
    }

    private String getUniqueKeyAndIntervalTimeFromCampaign(JSONObject jSONObject) {
        String string;
        string = "";
        if (jSONObject != null) {
            try {
                string = jSONObject.opt(this.JSON_KEY_AKDLUI) != null ? jSONObject.getString(this.JSON_KEY_AKDLUI) : "";
                if (jSONObject.opt(this.JSON_KEY_DOWNLOAD_PROGRESS_NOTIFY_INTERVAL) != null) {
                    this.progressNotifyInterval = jSONObject.optLong(this.JSON_KEY_DOWNLOAD_PROGRESS_NOTIFY_INTERVAL, this.DEFAULT_PROGRESS_NOTIFY_INTERVAL);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return string;
    }

    private String getUniqueKeyAndUrl(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        JSONObject jSONObject = new JSONObject(str);
        String string = jSONObject.getString(this.JSON_KEY_UNIQUE);
        this.downloadUrl = jSONObject.getString(this.JSON_KEY_URL);
        this.packageName = jSONObject.optString(this.JSON_KEY_PACKAGE_NAME);
        return string;
    }

    public void getMultipleDownloadAPKStatus(Object obj, String str) throws JSONException {
        String strOptString;
        JSONObject downloadStatus;
        this.currentObject = obj;
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                JSONArray jSONArray = new JSONArray();
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("downloaderList");
                if (jSONArrayOptJSONArray != null) {
                    if (jSONArrayOptJSONArray.length() > 0) {
                        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                            if (jSONObjectOptJSONObject != null && (downloadStatus = getDownloadStatus(obj, (strOptString = jSONObjectOptJSONObject.optString("uniqueKey")))) != null) {
                                downloadStatus.put("uniqueKey", strOptString);
                                jSONArray.put(downloadStatus);
                            }
                        }
                    } else {
                        callbackExcep(obj, "downloaderList is 0");
                    }
                } else {
                    callbackExcep(obj, "downloaderList is null");
                }
                if (jSONArray.length() > 0) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("downloaderList", jSONArray);
                    callbackSuccessWithData(obj, jSONObject2);
                    return;
                }
                return;
            } catch (Throwable th) {
                th.printStackTrace();
                callbackExcep(obj, th.getMessage());
                return;
            }
        }
        callbackExcep(obj, "params is null");
    }

    public void getDownloadAPKStatus(Object obj, String str) throws JSONException {
        this.currentObject = obj;
        try {
            JSONObject downloadStatus = getDownloadStatus(obj, getUniqueKeyAndUrl(str));
            if (downloadStatus != null) {
                callbackSuccessWithData(obj, downloadStatus);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            callbackExcep(obj, th.getMessage());
        }
    }

    private JSONObject getDownloadStatus(Object obj, String str) throws JSONException {
        try {
            this.currentObject = obj;
        } catch (Throwable th) {
            s.a(this.TAG, th.getMessage());
            callbackExcep(obj, th.getMessage());
        }
        if (!TextUtils.isEmpty(str)) {
            Class<?> cls = Class.forName("com.mbridge.msdk.mbdownload.manager.ADownloadManager");
            Object objInvoke = cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
            int iIntValue = ((Integer) cls.getMethod("getTaskStatusByUniqueKey", String.class).invoke(objInvoke, str)).intValue();
            int iIntValue2 = ((Integer) cls.getMethod("getTaskProgressByUniqueKey", String.class).invoke(objInvoke, str)).intValue();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("status", statusIntToString(iIntValue));
            jSONObject.put("progress", iIntValue2);
            return jSONObject;
        }
        callbackExcep(obj, "uniqueKey is null");
        return null;
    }

    public void callbackSuccessWithData(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(CommandMessage.CODE, SUCCESS);
            jSONObject2.put("message", "");
            jSONObject2.put("data", jSONObject);
            h.a().a(obj, Base64.encodeToString(jSONObject2.toString().getBytes(), 2));
        } catch (Exception e) {
            callbackExcep(obj, e.getMessage());
            s.a(this.TAG, e.getMessage());
        }
    }

    public void callbackSuccess(Object obj) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CommandMessage.CODE, SUCCESS);
            jSONObject.put("message", "");
            jSONObject.put("data", new JSONObject());
            h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (Exception e) {
            callbackExcep(obj, e.getMessage());
            s.a(this.TAG, e.getMessage());
        }
    }

    public void callbackExcep(Object obj, String str) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CommandMessage.CODE, FAILED);
            jSONObject.put("message", str);
            jSONObject.put("data", new JSONObject());
            h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (Exception e) {
            s.a(this.TAG, e.getMessage());
        }
    }

    private void checkMustParamsAndFillDefaultIfNull(JSONObject jSONObject, String str, Object obj) throws JSONException {
        try {
            if (jSONObject.opt(str) == null) {
                jSONObject.put(str, obj);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void addMultipleDownloaderListener(Object obj, String str) throws JSONException {
        this.currentObject = obj;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("akdluiList");
            if (jSONArrayOptJSONArray != null) {
                if (jSONArrayOptJSONArray.length() > 0) {
                    for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                        addDownloadListening(obj, jSONArrayOptJSONArray.optString(i));
                    }
                    return;
                }
                callbackExcep(obj, "akdluiList is 0");
                return;
            }
            callbackExcep(obj, "akdluiList is null");
        } catch (JSONException e) {
            e.printStackTrace();
            callbackExcep(obj, e.getMessage());
        }
    }

    public String addDownloaderListener(Object obj, String str) throws JSONException {
        s.d("=====AbsMbridgeDownload", "====before---params:" + str);
        this.currentObject = obj;
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String uniqueKeyAndIntervalTimeFromCampaign = getUniqueKeyAndIntervalTimeFromCampaign(jSONObject);
                checkMustParamsAndFillDefaultIfNull(jSONObject, this.JSON_KEY_AKDLUI, "");
                checkMustParamsAndFillDefaultIfNull(jSONObject, DomainCampaignEx.JSON_KEY_ATAT_TYPE, 0);
                checkMustParamsAndFillDefaultIfNull(jSONObject, DomainCampaignEx.JSON_KEY_NTBARPASBL, 0);
                checkMustParamsAndFillDefaultIfNull(jSONObject, DomainCampaignEx.JSON_KEY_NTBARPT, 0);
                addDownloadListening(obj, uniqueKeyAndIntervalTimeFromCampaign);
                str = jSONObject.toString();
            } catch (JSONException e) {
                e.printStackTrace();
                callbackExcep(obj, e.getMessage());
            }
        }
        s.d("=====AbsMbridgeDownload", "====end--params:" + str);
        return str;
    }

    private void addDownloadListening(Object obj, String str) throws JSONException {
        if (!TextUtils.isEmpty(str)) {
            try {
                IDownloadListener iDownloadListener = getiDownloadListenerByUniqueKey(str);
                Class<?> cls = Class.forName("com.mbridge.msdk.mbdownload.manager.ADownloadManager");
                cls.getMethod("addDownloadListener", String.class, IDownloadListener.class).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), str, iDownloadListener);
                callbackSuccess(obj);
                return;
            } catch (Throwable th) {
                s.a(this.TAG, th.getMessage());
                callbackExcep(obj, th.getMessage());
                return;
            }
        }
        callbackExcep(obj, "uniqueKey is null");
    }

    public void restartDownloadAPK(Object obj, String str) throws JSONException {
        this.currentObject = obj;
        try {
            String uniqueKeyAndUrl = getUniqueKeyAndUrl(str);
            IDownloadListener iDownloadListener = getiDownloadListenerByUniqueKey(uniqueKeyAndUrl);
            Class<?> cls = Class.forName("com.mbridge.msdk.mbdownload.manager.ADownloadManager");
            Object objInvoke = cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
            cls.getMethod("addDownloadListener", String.class, IDownloadListener.class).invoke(objInvoke, uniqueKeyAndUrl, iDownloadListener);
            cls.getMethod("start", String.class, String.class).invoke(objInvoke, uniqueKeyAndUrl, this.downloadUrl);
            parseActionEvent(obj, str);
            callbackSuccess(obj);
        } catch (Throwable th) {
            s.a(this.TAG, th.getMessage());
            callbackExcep(obj, th.getMessage());
        }
    }

    public void installAPK(Object obj, String str) throws JSONException {
        try {
            getUniqueKeyAndUrl(str);
            parseActionEvent(obj, str);
            callbackSuccess(obj);
        } catch (Throwable th) {
            s.a(this.TAG, th.getMessage());
            callbackExcep(obj, th.getMessage());
        }
    }

    public void activateAPK(Object obj, String str) throws JSONException {
        try {
            getUniqueKeyAndUrl(str);
            parseActionEvent(obj, str);
            callbackSuccess(obj);
        } catch (Exception e) {
            callbackExcep(obj, e.getMessage());
        }
    }

    public void pauseDownloadAPK(Object obj, String str) throws JSONException {
        try {
            String uniqueKeyAndUrl = getUniqueKeyAndUrl(str);
            Class<?> cls = Class.forName("com.mbridge.msdk.mbdownload.manager.ADownloadManager");
            cls.getMethod(CampaignEx.JSON_NATIVE_VIDEO_PAUSE, String.class).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), uniqueKeyAndUrl);
            parseActionEvent(obj, str);
            callbackSuccess(obj);
        } catch (Throwable th) {
            s.a(this.TAG, th.getMessage());
            callbackExcep(obj, th.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDownloadAPKStatusChange(int i, String str) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(this.JSON_KEY_UNIQUE, str);
            jSONObject.put(this.JSON_KEY_URL, this.downloadUrl);
            jSONObject.put(this.JSON_KEY_STATUS, statusIntToString(i));
            final String strEncodeToString = Base64.encodeToString(jSONObject.toString().getBytes(), 2);
            if (this.currentObject instanceof a) {
                a aVar = (a) this.currentObject;
                if (aVar.a == null || aVar.a.isDestoryed()) {
                    return;
                }
                aVar.a.post(new Runnable() { // from class: com.mbridge.msdk.mbjscommon.windvane.AbsMbridgeDownload.3
                    @Override // java.lang.Runnable
                    public final void run() {
                        h.a().a(AbsMbridgeDownload.this.currentObject, "onDownloadAPKStatusChange", strEncodeToString);
                    }
                });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDownloadAPKProgressChange(int i, String str) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(this.JSON_KEY_UNIQUE, str);
            jSONObject.put(this.JSON_KEY_URL, this.downloadUrl);
            jSONObject.put(this.JSON_KEY_PROGRESS, i);
            final String strEncodeToString = Base64.encodeToString(jSONObject.toString().getBytes(), 2);
            if (this.currentObject instanceof a) {
                a aVar = (a) this.currentObject;
                if (aVar.a == null || aVar.a.isDestoryed()) {
                    return;
                }
                aVar.a.post(new Runnable() { // from class: com.mbridge.msdk.mbjscommon.windvane.AbsMbridgeDownload.4
                    @Override // java.lang.Runnable
                    public final void run() {
                        h.a().a(AbsMbridgeDownload.this.currentObject, "onDownloadAPKProgressChange", strEncodeToString);
                    }
                });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseActionEvent(Object obj, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.opt(this.JSON_KEY_CAMPAIGN) != null) {
                sendNoticeAndCallBackClick(obj, jSONObject.getString(this.JSON_KEY_CAMPAIGN));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
