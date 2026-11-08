package com.ss.android.downloadlib.applink;

import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ss.android.download.api.constant.BaseConstants;
import com.ss.android.download.api.model.DeepLink;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.downloadad.api.model.InnerUnifyData;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.addownload.DownloadInsideHelper;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.addownload.model.ModelBox;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.downloadlib.addownload.model.OpenAppResult;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.downloadlib.event.AdEventHandler;
import com.ss.android.downloadlib.event.ClickEventHelper;
import com.ss.android.downloadlib.exception.TTDownloaderMonitor;
import com.ss.android.downloadlib.utils.EventAppendUtils;
import com.ss.android.downloadlib.utils.OpenAppUtils;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.ss.android.socialbase.downloader.notification.DownloadNotificationManager;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AdAppLinkUtils {
    public static boolean tryAppLinkWhenClick(@NonNull ModelBox modelBox) throws JSONException {
        boolean z;
        DeepLink deepLink = modelBox.model.getDeepLink();
        String openUrl = deepLink == null ? null : deepLink.getOpenUrl();
        JSONObject jSONObjectAppendAppLinkParams = EventAppendUtils.appendAppLinkParams(new JSONObject(), modelBox);
        ToolUtils.safePut(jSONObjectAppendAppLinkParams, EventConstants.ExtraJson.APPLINK_SOURCE, EventConstants.AppLinkSource.CLICK_BY_SDK);
        AdEventHandler.getInstance().sendEvent(EventConstants.Label.APPLINK_CLICK, jSONObjectAppendAppLinkParams, modelBox);
        OpenAppResult openAppResultTryOpenByUrl = OpenAppUtils.tryOpenByUrl(openUrl, modelBox);
        if (openAppResultTryOpenByUrl.getType() == 2) {
            if (!TextUtils.isEmpty(openUrl)) {
                onFailedByUrl(EventConstants.AppLinkSource.BY_URL, openAppResultTryOpenByUrl, jSONObjectAppendAppLinkParams, modelBox);
            }
            openAppResultTryOpenByUrl = OpenAppUtils.tryOpenByPackage(GlobalInfo.getContext(), modelBox.model.getPackageName(), modelBox);
        }
        boolean z2 = false;
        if (isAppLinkAd(modelBox.id) && GlobalInfo.getDownloadSettings().optInt(DownloadSettingKeys.LINK_AD_CLICK_EVENT) == 1) {
            if (modelBox.model instanceof AdDownloadModel) {
                ((AdDownloadModel) modelBox.model).setFunnelType(4);
            }
            AdEventHandler.getInstance().sendClickEvent(modelBox.id, 0);
            z = true;
        } else {
            z = false;
        }
        int type = openAppResultTryOpenByUrl.getType();
        if (type != 1) {
            switch (type) {
                case 3:
                    onSuccessByPkg(EventConstants.AppLinkSource.BY_PACKAGE, jSONObjectAppendAppLinkParams, modelBox);
                    break;
                case 4:
                    onFailedByPkg(EventConstants.AppLinkSource.BY_PACKAGE, openAppResultTryOpenByUrl, jSONObjectAppendAppLinkParams, modelBox);
                    break;
                default:
                    TTDownloaderMonitor.inst().monitorPathError("AppLinkClick default");
                    break;
            }
            if (z2 && !z && ((ClickEventHelper.getInstance().sendClicEventkWithIdAndReqId() && !ClickEventHelper.getInstance().hasClickRecord(modelBox.id, modelBox.model.getLogExtra())) || ClickEventHelper.getInstance().sendClickEventWithClickButton())) {
                AdEventHandler.getInstance().sendClickEvent(modelBox.id, 2);
            }
            return z2;
        }
        onSuccessByUrl(EventConstants.AppLinkSource.BY_URL, jSONObjectAppendAppLinkParams, modelBox);
        z2 = true;
        if (z2) {
            AdEventHandler.getInstance().sendClickEvent(modelBox.id, 2);
        }
        return z2;
    }

    public static void tryAppLinkWhenClickNotification(@NonNull NativeDownloadModel nativeDownloadModel) throws JSONException {
        String openUrl = nativeDownloadModel.getOpenUrl();
        JSONObject jSONObjectAppendAppLinkParams = EventAppendUtils.appendAppLinkParams(new JSONObject(), nativeDownloadModel);
        ToolUtils.safePut(jSONObjectAppendAppLinkParams, EventConstants.ExtraJson.APPLINK_SOURCE, EventConstants.AppLinkSource.NOTIFY_CLICK);
        AdEventHandler.getInstance().sendEvent(EventConstants.Label.APPLINK_CLICK, jSONObjectAppendAppLinkParams, nativeDownloadModel);
        OpenAppResult openAppResultTryOpenByUrl = OpenAppUtils.tryOpenByUrl(openUrl, nativeDownloadModel);
        if (openAppResultTryOpenByUrl.getType() == 2) {
            if (!TextUtils.isEmpty(openUrl)) {
                onFailedByUrl(EventConstants.AppLinkSource.NOTIFY_BY_URL, openAppResultTryOpenByUrl, jSONObjectAppendAppLinkParams, nativeDownloadModel);
            }
            openAppResultTryOpenByUrl = OpenAppUtils.tryOpenByPackage(GlobalInfo.getContext(), nativeDownloadModel.getPackageName(), nativeDownloadModel);
        }
        int type = openAppResultTryOpenByUrl.getType();
        if (type != 1) {
            switch (type) {
                case 3:
                    onSuccessByPkg(EventConstants.AppLinkSource.NOTIFY_BY_PACKAGE, jSONObjectAppendAppLinkParams, nativeDownloadModel);
                    break;
                case 4:
                    onFailedByPkg(EventConstants.AppLinkSource.NOTIFY_BY_PACKAGE, openAppResultTryOpenByUrl, jSONObjectAppendAppLinkParams, nativeDownloadModel);
                    break;
                default:
                    TTDownloaderMonitor.inst().monitorPathError("AppLinkClickNotification default");
                    break;
            }
        }
        onSuccessByUrl(EventConstants.AppLinkSource.NOTIFY_BY_URL, jSONObjectAppendAppLinkParams, nativeDownloadModel);
    }

    public static void tryAppLinkWhenClickDialog(NativeDownloadModel nativeDownloadModel) {
        if (nativeDownloadModel == null) {
            return;
        }
        String openUrl = DownloadSetting.obtainGlobal().optInt(DownloadSettingKeys.APP_LINK_OPT) == 1 ? nativeDownloadModel.getOpenUrl() : null;
        JSONObject jSONObjectAppendAppLinkParams = EventAppendUtils.appendAppLinkParams(new JSONObject(), nativeDownloadModel);
        ToolUtils.safePut(jSONObjectAppendAppLinkParams, EventConstants.ExtraJson.APPLINK_SOURCE, EventConstants.AppLinkSource.DIALOG_CLICK);
        AdEventHandler.getInstance().sendEvent(EventConstants.Label.APPLINK_CLICK, jSONObjectAppendAppLinkParams, nativeDownloadModel);
        OpenAppResult openAppResultTryOpenByUrl = OpenAppUtils.tryOpenByUrl(openUrl, nativeDownloadModel);
        if (openAppResultTryOpenByUrl.getType() == 2) {
            if (!TextUtils.isEmpty(openUrl)) {
                onFailedByUrl(EventConstants.AppLinkSource.DIALOG_BY_URL, openAppResultTryOpenByUrl, jSONObjectAppendAppLinkParams, nativeDownloadModel);
            }
            openAppResultTryOpenByUrl = OpenAppUtils.tryOpenByPackage(GlobalInfo.getContext(), nativeDownloadModel.getPackageName(), nativeDownloadModel);
        }
        int type = openAppResultTryOpenByUrl.getType();
        if (type != 1) {
            switch (type) {
                case 3:
                    onSuccessByPkg(EventConstants.AppLinkSource.DIALOG_BY_PACKAGE, jSONObjectAppendAppLinkParams, nativeDownloadModel);
                    break;
                case 4:
                    onFailedByPkg(EventConstants.AppLinkSource.DIALOG_BY_PACKAGE, openAppResultTryOpenByUrl, jSONObjectAppendAppLinkParams, nativeDownloadModel);
                    break;
                default:
                    TTDownloaderMonitor.inst().monitorPathError("AppLinkClickDialog default");
                    break;
            }
            return;
        }
        onSuccessByUrl(EventConstants.AppLinkSource.DIALOG_BY_URL, jSONObjectAppendAppLinkParams, nativeDownloadModel);
    }

    public static boolean tryAutoDeepLink(String str, @NonNull NativeDownloadModel nativeDownloadModel) throws JSONException {
        if (!DownloadInsideHelper.isAllowDeepLink(nativeDownloadModel.getLinkMode())) {
            return false;
        }
        if (TextUtils.isEmpty(nativeDownloadModel.getOpenUrl()) && TextUtils.isEmpty(str)) {
            return false;
        }
        DownloadNotificationManager.getInstance().cancelNotification(nativeDownloadModel.getDownloadId());
        JSONObject jSONObject = new JSONObject();
        EventAppendUtils.appendAppLinkParams(jSONObject, nativeDownloadModel);
        ToolUtils.safePut(jSONObject, EventConstants.ExtraJson.APPLINK_SOURCE, EventConstants.AppLinkSource.AUTO_CLICK);
        AdEventHandler.getInstance().sendEvent(EventConstants.Label.APPLINK_CLICK, nativeDownloadModel);
        OpenAppResult openAppResultTryNewOpenByUrlOrPackage = OpenAppUtils.tryNewOpenByUrlOrPackage(nativeDownloadModel, nativeDownloadModel.getOpenUrl(), nativeDownloadModel.getPackageName());
        switch (openAppResultTryNewOpenByUrlOrPackage.getType()) {
            case 1:
                onSuccessByUrl(EventConstants.AppLinkSource.AUTO_BY_URL, jSONObject, nativeDownloadModel);
                break;
            case 2:
                onFailedByUrl(EventConstants.AppLinkSource.AUTO_BY_URL, openAppResultTryNewOpenByUrlOrPackage, jSONObject, nativeDownloadModel);
                break;
            case 3:
                onSuccessByPkg(EventConstants.AppLinkSource.AUTO_BY_PACKAGE, jSONObject, nativeDownloadModel);
                break;
            case 4:
                onFailedByPkg(EventConstants.AppLinkSource.AUTO_BY_PACKAGE, openAppResultTryNewOpenByUrlOrPackage, jSONObject, nativeDownloadModel);
                break;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void onSuccessByPkg(java.lang.String r8, @androidx.annotation.NonNull final org.json.JSONObject r9, @androidx.annotation.NonNull final com.ss.android.downloadad.api.model.InnerUnifyData r10) throws org.json.JSONException {
        /*
            java.lang.String r0 = "applink_source"
            com.ss.android.downloadlib.utils.ToolUtils.safePut(r9, r0, r8)
            java.lang.String r0 = "download_scene"
            int r1 = r10.getDownloadScene()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            com.ss.android.downloadlib.utils.ToolUtils.safePut(r9, r0, r1)
            com.ss.android.downloadlib.event.AdEventHandler r0 = com.ss.android.downloadlib.event.AdEventHandler.getInstance()
            java.lang.String r1 = "deeplink_app_open"
            r0.sendEvent(r1, r9, r10)
            int r0 = r8.hashCode()
            r1 = -1282070764(0xffffffffb3952714, float:-6.9454615E-8)
            r2 = 1
            if (r0 == r1) goto L53
            r1 = -441514770(0xffffffffe5af04ee, float:-1.0331313E23)
            if (r0 == r1) goto L49
            r1 = -185950114(0xfffffffff4eaa05e, float:-1.4871217E32)
            if (r0 == r1) goto L3f
            r1 = 368401333(0x15f55bb5, float:9.90994E-26)
            if (r0 == r1) goto L35
            goto L5d
        L35:
            java.lang.String r0 = "dialog_by_package"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L5d
            r0 = 3
            goto L5e
        L3f:
            java.lang.String r0 = "by_package"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L5d
            r0 = 2
            goto L5e
        L49:
            java.lang.String r0 = "auto_by_package"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L5d
            r0 = 1
            goto L5e
        L53:
            java.lang.String r0 = "notify_by_package"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L5d
            r0 = 0
            goto L5e
        L5d:
            r0 = -1
        L5e:
            switch(r0) {
                case 0: goto L62;
                case 1: goto L62;
                case 2: goto L62;
                case 3: goto L62;
                default: goto L61;
            }
        L61:
            goto La1
        L62:
            org.json.JSONObject r0 = com.ss.android.downloadlib.addownload.GlobalInfo.getDownloadSettings()
            java.lang.String r1 = "check_applink_mode"
            int r0 = r0.optInt(r1)
            r0 = r0 & r2
            if (r0 == 0) goto L85
            java.lang.String r8 = "check_applink_result_by_sdk"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r2)
            com.ss.android.downloadlib.utils.ToolUtils.safePut(r9, r8, r0)
            com.ss.android.downloadlib.applink.AppLinkMonitor r8 = com.ss.android.downloadlib.applink.AppLinkMonitor.getInstance()
            com.ss.android.downloadlib.applink.AdAppLinkUtils$1 r0 = new com.ss.android.downloadlib.applink.AdAppLinkUtils$1
            r0.<init>()
            r8.checkAppLinkResult(r0)
            goto La1
        L85:
            com.ss.android.download.api.config.DownloadActionListener r1 = com.ss.android.downloadlib.addownload.GlobalInfo.getDownloadActionListener()
            android.content.Context r2 = com.ss.android.downloadlib.addownload.GlobalInfo.getContext()
            com.ss.android.download.api.download.DownloadModel r3 = r10.getModel()
            com.ss.android.download.api.download.DownloadController r4 = r10.getController()
            com.ss.android.download.api.download.DownloadEventConfig r5 = r10.getEvent()
            java.lang.String r6 = r10.getPackageName()
            r7 = r8
            r1.onOpenApp(r2, r3, r4, r5, r6, r7)
        La1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.downloadlib.applink.AdAppLinkUtils.onSuccessByPkg(java.lang.String, org.json.JSONObject, com.ss.android.downloadad.api.model.InnerUnifyData):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void onSuccessByUrl(java.lang.String r8, @androidx.annotation.NonNull final org.json.JSONObject r9, @androidx.annotation.NonNull final com.ss.android.downloadad.api.model.InnerUnifyData r10) throws org.json.JSONException {
        /*
            java.lang.String r0 = "applink_source"
            com.ss.android.downloadlib.utils.ToolUtils.safePut(r9, r0, r8)
            java.lang.String r0 = "download_scene"
            int r1 = r10.getDownloadScene()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            com.ss.android.downloadlib.utils.ToolUtils.safePut(r9, r0, r1)
            com.ss.android.downloadlib.event.AdEventHandler r0 = com.ss.android.downloadlib.event.AdEventHandler.getInstance()
            java.lang.String r1 = "deeplink_url_open"
            r0.sendEvent(r1, r9, r10)
            int r0 = r8.hashCode()
            r1 = -1721882089(0xffffffff995e2a17, float:-1.1485631E-23)
            r2 = 1
            if (r0 == r1) goto L53
            r1 = -1374618233(0xffffffffae10fd87, float:-3.2966987E-11)
            if (r0 == r1) goto L49
            r1 = -129544387(0xfffffffff8474f3d, float:-1.616991E34)
            if (r0 == r1) goto L3f
            r1 = 829750366(0x3174fc5e, float:3.5650127E-9)
            if (r0 == r1) goto L35
            goto L5d
        L35:
            java.lang.String r0 = "dialog_by_url"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L5d
            r0 = 3
            goto L5e
        L3f:
            java.lang.String r0 = "notify_by_url"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L5d
            r0 = 0
            goto L5e
        L49:
            java.lang.String r0 = "by_url"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L5d
            r0 = 2
            goto L5e
        L53:
            java.lang.String r0 = "auto_by_url"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L5d
            r0 = 1
            goto L5e
        L5d:
            r0 = -1
        L5e:
            switch(r0) {
                case 0: goto L62;
                case 1: goto L62;
                case 2: goto L62;
                case 3: goto L62;
                default: goto L61;
            }
        L61:
            goto La1
        L62:
            org.json.JSONObject r0 = com.ss.android.downloadlib.addownload.GlobalInfo.getDownloadSettings()
            java.lang.String r1 = "check_applink_mode"
            int r0 = r0.optInt(r1)
            r0 = r0 & r2
            if (r0 == 0) goto L85
            java.lang.String r8 = "check_applink_result_by_sdk"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r2)
            com.ss.android.downloadlib.utils.ToolUtils.safePut(r9, r8, r0)
            com.ss.android.downloadlib.applink.AppLinkMonitor r8 = com.ss.android.downloadlib.applink.AppLinkMonitor.getInstance()
            com.ss.android.downloadlib.applink.AdAppLinkUtils$2 r0 = new com.ss.android.downloadlib.applink.AdAppLinkUtils$2
            r0.<init>()
            r8.checkAppLinkResult(r0)
            goto La1
        L85:
            com.ss.android.download.api.config.DownloadActionListener r1 = com.ss.android.downloadlib.addownload.GlobalInfo.getDownloadActionListener()
            android.content.Context r2 = com.ss.android.downloadlib.addownload.GlobalInfo.getContext()
            com.ss.android.download.api.download.DownloadModel r3 = r10.getModel()
            com.ss.android.download.api.download.DownloadController r4 = r10.getController()
            com.ss.android.download.api.download.DownloadEventConfig r5 = r10.getEvent()
            java.lang.String r6 = r10.getPackageName()
            r7 = r8
            r1.onOpenApp(r2, r3, r4, r5, r6, r7)
        La1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.downloadlib.applink.AdAppLinkUtils.onSuccessByUrl(java.lang.String, org.json.JSONObject, com.ss.android.downloadad.api.model.InnerUnifyData):void");
    }

    public static void onFailedByPkg(String str, @NonNull OpenAppResult openAppResult, @NonNull JSONObject jSONObject, @NonNull InnerUnifyData innerUnifyData) throws JSONException {
        ToolUtils.safePut(jSONObject, EventConstants.ExtraJson.APPLINK_SOURCE, str);
        ToolUtils.safePut(jSONObject, "error_code", Integer.valueOf(openAppResult.getMessage()));
        ToolUtils.safePut(jSONObject, "download_scene", Integer.valueOf(innerUnifyData.getDownloadScene()));
        AdEventHandler.getInstance().sendEvent(EventConstants.Label.DEEPLINK_APP_OPEN_FAIL, jSONObject, innerUnifyData);
    }

    public static void onFailedByUrl(String str, @NonNull OpenAppResult openAppResult, @NonNull JSONObject jSONObject, @NonNull InnerUnifyData innerUnifyData) throws JSONException {
        ToolUtils.safePut(jSONObject, EventConstants.ExtraJson.APPLINK_SOURCE, str);
        ToolUtils.safePut(jSONObject, "error_code", Integer.valueOf(openAppResult.getMessage()));
        ToolUtils.safePut(jSONObject, "download_scene", Integer.valueOf(innerUnifyData.getDownloadScene()));
        AdEventHandler.getInstance().sendEvent(EventConstants.Label.DEEPLINK_URL_OPEN_FAIL, jSONObject, innerUnifyData);
    }

    public static boolean tryOpenMarketWhenClick(@NonNull ModelBox modelBox, int i) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        ToolUtils.safePut(jSONObject, "download_scene", Integer.valueOf(modelBox.getDownloadScene()));
        AdEventHandler.getInstance().sendEvent(EventConstants.Label.MARKET_CLICK_OPEN, jSONObject, modelBox);
        OpenAppResult openAppResultTryOpenMarket = OpenAppUtils.tryOpenMarket(GlobalInfo.getContext(), modelBox, modelBox.model.getPackageName());
        String notEmptyStr = ToolUtils.getNotEmptyStr(openAppResultTryOpenMarket.getSource(), EventConstants.AppLinkSource.OPEN_MARKET);
        switch (openAppResultTryOpenMarket.getType()) {
            case 5:
                onMarketSuccess(notEmptyStr, jSONObject, modelBox, true);
                break;
            case 6:
                ToolUtils.safePut(jSONObject, "error_code", Integer.valueOf(openAppResultTryOpenMarket.getMessage()));
                ToolUtils.safePut(jSONObject, "download_scene", Integer.valueOf(modelBox.getDownloadScene()));
                AdEventHandler.getInstance().sendEvent(EventConstants.Label.MARKET_OPEN_FAILED, jSONObject, modelBox);
                return false;
            case 7:
                break;
            default:
                return false;
        }
        AdEventHandler.getInstance().sendClickEvent(modelBox.id, i);
        return true;
    }

    public static void onMarketSuccess(final String str, @Nullable final JSONObject jSONObject, final ModelBox modelBox, boolean z) throws JSONException {
        if (jSONObject == null) {
            try {
                jSONObject = new JSONObject();
            } catch (Exception e) {
                TTDownloaderMonitor.inst().monitorException(e, "onMarketSuccess");
                return;
            }
        }
        ToolUtils.safePut(jSONObject, EventConstants.ExtraJson.APPLINK_SOURCE, str);
        ToolUtils.safePut(jSONObject, "download_scene", Integer.valueOf(modelBox.getDownloadScene()));
        if (z) {
            AdEventHandler.getInstance().sendEvent(EventConstants.Label.MARKET_OPEN_SUCCESS, jSONObject, modelBox);
        }
        if ((GlobalInfo.getDownloadSettings().optInt(DownloadSettingKeys.CHECK_APPLINK_MODE) & 4) != 0) {
            AppLinkMonitor.getInstance().checkMarketOrInstaller(new AppLinkEventCallback() { // from class: com.ss.android.downloadlib.applink.AdAppLinkUtils.3
                @Override // com.ss.android.downloadlib.applink.AppLinkEventCallback
                public void onResult(boolean z2) throws JSONException {
                    if (!z2 && !EventConstants.AppLinkSource.OPEN_MARKET.equals(str)) {
                        AdAppLinkUtils.handleBackupOpenMarketResult(OpenAppUtils.tryOpenMarket(GlobalInfo.getContext(), Uri.parse(BaseConstants.MARKET_PREFIX + modelBox.getPackageName())), modelBox, false);
                    }
                    AdEventHandler.getInstance().sendUnityEvent(z2 ? EventConstants.UnityLabel.MARKET_DELAY_SUCCESS : EventConstants.UnityLabel.MARKET_DELAY_FAILED, jSONObject, modelBox);
                    if (z2) {
                        GlobalInfo.getOpenAppListener().onOpenApp(GlobalInfo.getContext(), modelBox.model, modelBox.controller, modelBox.event, modelBox.model.getPackageName(), 2);
                    }
                }
            });
        } else {
            GlobalInfo.getDownloadActionListener().onOpenApp(GlobalInfo.getContext(), modelBox.model, modelBox.controller, modelBox.event, modelBox.model.getPackageName(), str);
        }
        NativeDownloadModel nativeDownloadModel = new NativeDownloadModel(modelBox.model, modelBox.event, modelBox.controller);
        nativeDownloadModel.setDownloadStatus(2);
        nativeDownloadModel.setTimeStamp(System.currentTimeMillis());
        nativeDownloadModel.setInstallScene(4);
        nativeDownloadModel.setFunnelType(2);
        ModelManager.getInstance().putNativeModel(nativeDownloadModel);
    }

    public static void handleBackupOpenMarketResult(OpenAppResult openAppResult, ModelBox modelBox, boolean z) throws JSONException {
        String notEmptyStr = ToolUtils.getNotEmptyStr(openAppResult.getSource(), EventConstants.AppLinkSource.OPEN_MARKET);
        JSONObject jSONObject = new JSONObject();
        ToolUtils.safePut(jSONObject, EventConstants.ExtraJson.KEY_TYPE, "backup");
        switch (openAppResult.getType()) {
            case 5:
                onMarketSuccess(notEmptyStr, jSONObject, modelBox, z);
                break;
            case 6:
                ToolUtils.safePut(jSONObject, "error_code", Integer.valueOf(openAppResult.getMessage()));
                ToolUtils.safePut(jSONObject, "download_scene", Integer.valueOf(modelBox.getDownloadScene()));
                AdEventHandler.getInstance().sendEvent(EventConstants.Label.MARKET_OPEN_FAILED, jSONObject, modelBox);
                break;
        }
    }

    public static boolean isAppLinkAd(long j) {
        return ModelManager.getInstance().getNativeDownloadModel(j) == null;
    }
}
