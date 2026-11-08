package com.ss.android.downloadlib.addownload.compliance;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.NonNull;
import com.ss.android.download.api.config.IHttpCallback;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.downloadlib.DownloadDispatcher;
import com.ss.android.downloadlib.activity.TTDelegateActivity;
import com.ss.android.downloadlib.addownload.CommonDownloadHandler;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.addownload.model.AppInfoX;
import com.ss.android.downloadlib.addownload.model.ModelBox;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.downloadlib.exception.TTDownloaderMonitor;
import com.ss.android.downloadlib.utils.Chain;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.umeng.message.common.a;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AdLpComplianceManager {
    private static final String API = "https://apps.oceanengine.com/customer/api/app/pkg_info?";
    private SoftReference<Activity> dialogActivityRef;

    private static class SingleTonHolder {
        private static AdLpComplianceManager INSTANCE = new AdLpComplianceManager();

        private SingleTonHolder() {
        }
    }

    public static AdLpComplianceManager getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    private AdLpComplianceManager() {
    }

    public void showAppInfoDialog(long j) {
        TTDelegateActivity.showAppInfoDialog(j);
    }

    public boolean shouldShowDialog(DownloadModel downloadModel) {
        if (!downloadModel.isAd() || GlobalInfo.getDownloadSettings().optInt(DownloadSettingKeys.AD_LP_SHOW_APP_DIALOG) == 0) {
            return false;
        }
        String webUrl = downloadModel.getDeepLink() == null ? null : downloadModel.getDeepLink().getWebUrl();
        return (TextUtils.isEmpty(webUrl) || Pattern.compile(GlobalInfo.getDownloadSettings().optString(DownloadSettingKeys.AD_ALLOW_WEB_URL_REGEX, ".+(www.chengzijianzhan.com|www.toutiaopage.com/tetris/page|ad.toutiao.com/tetris/page).+")).matcher(webUrl).matches()) ? false : true;
    }

    public boolean requestAppInfo(@NonNull ModelBox modelBox) throws JSONException {
        long jOptLong;
        long j;
        if (!TextUtils.isEmpty(modelBox.model.getLogExtra())) {
            try {
                jOptLong = ToolUtils.optLong(new JSONObject(modelBox.model.getLogExtra()), "convert_id");
            } catch (Exception e) {
                e.printStackTrace();
                jOptLong = 0;
            }
            if (jOptLong <= 0) {
                EventSender.sendErrorEvent(3, modelBox);
            }
            j = jOptLong;
        } else {
            EventSender.sendErrorEvent(9, modelBox);
            TTDownloaderMonitor.inst().monitorDataError("requestAppInfo getLogExtra null");
            j = 0;
        }
        final long j2 = modelBox.id;
        AppInfoX appInfoX = AppInfoCache.getInstance().getAppInfoX(j, j2);
        if (appInfoX != null) {
            BitmapCache.getInstance().loadBitmap(appInfoX.getId(), j2, appInfoX.appIcon);
            showAppInfoDialog(appInfoX.getId());
            EventSender.sendEvent(EventConstants.Label.LP_APP_DIALOG_TRY_SHOW, modelBox);
            return true;
        }
        StringBuilder sb = new StringBuilder();
        if (j > 0) {
            sb.append("convert_id=");
            sb.append(j);
        }
        if (!TextUtils.isEmpty(modelBox.model.getPackageName())) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append("package_name=");
            sb.append(modelBox.model.getPackageName());
        }
        if (sb.length() <= 0) {
            EventSender.sendErrorEvent(6, modelBox);
            return false;
        }
        final long j3 = j;
        Chain.createOnIo(new Chain.IRun<String, Boolean>() { // from class: com.ss.android.downloadlib.addownload.compliance.AdLpComplianceManager.2
            @Override // com.ss.android.downloadlib.utils.Chain.IRun
            public Boolean run(String str) {
                final boolean[] zArr = {false};
                GlobalInfo.getDownloadNetworkFactory().execute("GET", str, new HashMap(), new IHttpCallback() { // from class: com.ss.android.downloadlib.addownload.compliance.AdLpComplianceManager.2.1
                    @Override // com.ss.android.download.api.config.IHttpCallback
                    public void onResponse(String str2) {
                        zArr[0] = AdLpComplianceManager.this.parseResponse(j3, j2, str2);
                    }

                    @Override // com.ss.android.download.api.config.IHttpCallback
                    public void onError(Throwable th) throws JSONException {
                        EventSender.sendErrorEvent(2, j2);
                        zArr[0] = false;
                    }
                });
                return Boolean.valueOf(zArr[0]);
            }
        }, API + sb.toString()).nextOnMain(new Chain.IRun<Boolean, Object>() { // from class: com.ss.android.downloadlib.addownload.compliance.AdLpComplianceManager.1
            @Override // com.ss.android.downloadlib.utils.Chain.IRun
            public Object run(Boolean bool) {
                if (bool.booleanValue()) {
                    AdLpComplianceManager.this.showAppInfoDialog(AppInfoX.getId(j3, j2));
                    EventSender.sendEvent(EventConstants.Label.LP_APP_DIALOG_TRY_SHOW, j2);
                    return null;
                }
                AdLpComplianceManager.this.startDownload(j2);
                return null;
            }
        }).start();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean parseResponse(long j, long j2, String str) throws JSONException {
        try {
            JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject(a.u);
            if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.length() != 0) {
                AppInfoX appInfoX = new AppInfoX();
                appInfoX.convertId = j;
                appInfoX.cid = j2;
                appInfoX.appIcon = jSONObjectOptJSONObject.optString("icon_url");
                appInfoX.appName = jSONObjectOptJSONObject.optString("app_name");
                appInfoX.packageName = jSONObjectOptJSONObject.optString("package_name");
                appInfoX.versionName = jSONObjectOptJSONObject.optString(AdDownloadModel.JsonKey.VERSION_NAME);
                appInfoX.developer = jSONObjectOptJSONObject.optString("developer_name");
                appInfoX.privacyPolicyUrl = jSONObjectOptJSONObject.optString("policy_url");
                JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("permissions");
                if (jSONArrayOptJSONArray != null) {
                    for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                        JSONObject jSONObject = (JSONObject) jSONArrayOptJSONArray.get(i);
                        appInfoX.permissions.add(new Pair<>(jSONObject.optString("permission_name"), jSONObject.optString("permission_desc")));
                    }
                }
                AppInfoCache.getInstance().putAppInfoX(appInfoX);
                BitmapCache.getInstance().loadBitmap(appInfoX.getId(), j2, appInfoX.appIcon);
                return true;
            }
            EventSender.sendErrorEvent(7, j2);
            return false;
        } catch (Exception e) {
            TTDownloaderMonitor.inst().monitorException(e, "AdLpComplianceManager parseResponse");
            EventSender.sendErrorEvent(7, j2);
            return false;
        }
    }

    public void startDownload(long j) {
        CommonDownloadHandler commonDownloadHandler = DownloadDispatcher.getInstance().getCommonDownloadHandler(ModelManager.getInstance().getModelBox(j).model.getDownloadUrl());
        if (commonDownloadHandler != null) {
            commonDownloadHandler.innerStartDownload(true, true);
        } else {
            EventSender.sendErrorEvent(11, j);
            TTDownloaderMonitor.inst().monitorPathError("startDownload handler null");
        }
    }

    public void pushDialogActivity(Activity activity) {
        this.dialogActivityRef = new SoftReference<>(activity);
    }

    public Activity popDialogActivity() {
        Activity activity = this.dialogActivityRef.get();
        this.dialogActivityRef = null;
        return activity;
    }
}
