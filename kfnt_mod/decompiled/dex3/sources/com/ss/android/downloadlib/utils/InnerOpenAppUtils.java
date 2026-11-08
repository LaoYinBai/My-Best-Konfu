package com.ss.android.downloadlib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.ss.android.download.api.config.DownloadNetworkFactory;
import com.ss.android.download.api.config.IHttpCallback;
import com.ss.android.download.api.constant.BaseConstants;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.downloadad.api.model.InnerUnifyData;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.DownloadComponentManager;
import com.ss.android.downloadlib.activity.JumpKllkActivity;
import com.ss.android.downloadlib.activity.TTDelegateActivity;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.addownload.model.ModelBox;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.downloadlib.addownload.model.OpenAppResult;
import com.ss.android.downloadlib.am.m1.DeviceUtils;
import com.ss.android.downloadlib.am.m2.AidlClient;
import com.ss.android.downloadlib.am.m2.AidlMsg;
import com.ss.android.downloadlib.applink.AdAppLinkUtils;
import com.ss.android.downloadlib.applink.AppLinkOptimiseHelper;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.downloadlib.event.AdEventHandler;
import com.ss.android.downloadlib.exception.TTDownloaderMonitor;
import com.ss.android.socialbase.appdownloader.util.AnUtils;
import com.ss.android.socialbase.appdownloader.util.DecryptUtils;
import com.ss.android.socialbase.appdownloader.util.RomUtils;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.umeng.commonsdk.proguard.o;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class InnerOpenAppUtils {
    public static OpenAppResult tryOpenMarket(Context context, Uri uri) {
        if (context == null || uri == null || !"market".equals(uri.getScheme())) {
            return new OpenAppResult(6, 12);
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            if (!ToolUtils.isIntentAvailable(context, intent)) {
                return new OpenAppResult(6, 13);
            }
            String marketPackageName = RomUtils.getMarketPackageName();
            if (ToolUtils.isInstalledApp(context, marketPackageName) && !RomUtils.isSamsung()) {
                intent.setPackage(marketPackageName);
            }
            if (DownloadSetting.obtainGlobal().optBugFix(DownloadSettingKeys.BugFix.FIX_JUMP_MARKET)) {
                intent.addFlags(335544320);
            } else if (!(context instanceof Activity)) {
                intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            }
            intent.putExtra(BaseConstants.START_ONLY_FOR_ANDROID, true);
            context.startActivity(intent);
            return new OpenAppResult(5);
        } catch (Exception unused) {
            return new OpenAppResult(6, 14);
        }
    }

    public static OpenAppResult tryOpenMarket(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return new OpenAppResult(6, 11);
        }
        if (RomUtils.isSamsung() && ToolUtils.isInstalledApp(context, RomUtils.MARKET_PKG_NAME_SAMSUNG)) {
            return tryOpenSamsungMarket(context, str);
        }
        return tryOpenMarket(context, Uri.parse(BaseConstants.MARKET_PREFIX + str));
    }

    public static OpenAppResult tryOpenMarket(Context context, ModelBox modelBox, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return new OpenAppResult(6, 11);
        }
        if (RomUtils.isSamsung() && ToolUtils.isInstalledApp(context, RomUtils.MARKET_PKG_NAME_SAMSUNG)) {
            return tryOpenSamsungMarket(context, str);
        }
        if (!modelBox.model.isAd() || !modelBox.controller.enableAM()) {
            return tryOpenMarket(context, Uri.parse(BaseConstants.MARKET_PREFIX + str));
        }
        JSONArray jSONArrayOptJSONArray = GlobalInfo.getDownloadSettings().optJSONArray(DownloadSettingKeys.KEY_AM_PLANS);
        if (RomUtils.isMiui() && AnUtils.enableAmPlan(jSONArrayOptJSONArray, DownloadSettingKeys.AM_0)) {
            tryOpenM1Market(context, modelBox, str);
            return new OpenAppResult(7, OpenAppResult.Source.AM_M1);
        }
        if (RomUtils.isOppo() && AnUtils.enableAmPlan(jSONArrayOptJSONArray, DownloadSettingKeys.AM_3)) {
            return tryOpenKllk2Market(context, modelBox, str);
        }
        if (RomUtils.isFlyme() && AnUtils.enableAmPlan(jSONArrayOptJSONArray, DownloadSettingKeys.AM_2)) {
            tryOpenM2Market(context, modelBox, str);
            return new OpenAppResult(7, OpenAppResult.Source.AM_M2);
        }
        if (RomUtils.isVivo() && AnUtils.enableAmPlan(jSONArrayOptJSONArray, DownloadSettingKeys.AM_5)) {
            tryOpenV1Market(context, modelBox, str);
            return new OpenAppResult(7, OpenAppResult.Source.AM_V1);
        }
        return tryOpenMarket(context, Uri.parse(BaseConstants.MARKET_PREFIX + str));
    }

    private static OpenAppResult tryOpenSamsungMarket(@NonNull Context context, @NonNull String str) {
        try {
            Uri uri = Uri.parse("https://www.samsungapps.com/appquery/appDetail.as?appId=" + str);
            Intent intent = new Intent();
            intent.setClassName(RomUtils.MARKET_PKG_NAME_SAMSUNG, "com.sec.android.app.samsungapps.Main");
            intent.setData(uri);
            if (!(context instanceof Activity)) {
                intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            }
            intent.putExtra(BaseConstants.START_ONLY_FOR_ANDROID, true);
            context.startActivity(intent);
            return new OpenAppResult(5);
        } catch (Exception unused) {
            return new OpenAppResult(6, 14);
        }
    }

    private static void tryOpenM1Market(final Context context, final ModelBox modelBox, final String str) {
        DownloadComponentManager.getInstance().submitCPUTask(new Runnable() { // from class: com.ss.android.downloadlib.utils.InnerOpenAppUtils.1
            @Override // java.lang.Runnable
            public void run() throws JSONException {
                final JSONObject downloadSettings = GlobalInfo.getDownloadSettings();
                final String strOptString = downloadSettings.optString(o.at);
                final JSONObject jSONObject = new JSONObject();
                String strDecrypt = DecryptUtils.decrypt(downloadSettings.optString("x"), strOptString);
                JSONObject jSONObject2 = new JSONObject();
                ToolUtils.safePut(jSONObject2, "p", str);
                ToolUtils.safePut(jSONObject2, o.au, Build.VERSION.INCREMENTAL);
                ToolUtils.safePut(jSONObject2, "m", Build.MODEL);
                ToolUtils.safePut(jSONObject2, "im", DeviceUtils.getImeiMd5(context));
                ToolUtils.safePut(jSONObject2, o.aq, DeviceUtils.getDeviceId(context));
                ToolUtils.safePut(jSONObject2, o.av, "m");
                byte[] bytes = jSONObject2.toString().getBytes();
                GlobalInfo.getDownloadNetworkFactory().postBody(strDecrypt, GlobalInfo.getEncryptor().encrypt(bytes, bytes.length), DownloadNetworkFactory.ContentType.TT_DATA, 0, new IHttpCallback() { // from class: com.ss.android.downloadlib.utils.InnerOpenAppUtils.1.1
                    @Override // com.ss.android.download.api.config.IHttpCallback
                    public void onResponse(String str2) throws JSONException {
                        InnerOpenAppUtils.tryInnerOpenM1Market(context, str, str2, modelBox, jSONObject, downloadSettings, strOptString);
                    }

                    @Override // com.ss.android.download.api.config.IHttpCallback
                    public void onError(Throwable th) throws JSONException {
                        AdAppLinkUtils.handleBackupOpenMarketResult(InnerOpenAppUtils.tryOpenMarket(context, Uri.parse(BaseConstants.MARKET_PREFIX + str)), modelBox, true);
                        ToolUtils.safePut(jSONObject, EventConstants.ExtraJson.KEY_MESSAGE, th != null ? th.getMessage() : "null");
                        InnerOpenAppUtils.trySendAmResultEvent(modelBox, jSONObject, 4, 1);
                    }
                });
            }
        });
    }

    private static String replaceM1Url(String str, @NonNull JSONObject jSONObject, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String strDecrypt = DecryptUtils.decrypt(jSONObject.optString("g"), str2);
        String strDecrypt2 = DecryptUtils.decrypt(jSONObject.optString("h"), str2);
        return (TextUtils.isEmpty(strDecrypt) || TextUtils.isEmpty(strDecrypt2)) ? str : str.replace(strDecrypt, strDecrypt2);
    }

    private static OpenAppResult tryOpenKllk2Market(Context context, ModelBox modelBox, String str) throws JSONException {
        Intent intent = new Intent(context, (Class<?>) JumpKllkActivity.class);
        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        intent.putExtra("p", str);
        intent.putExtra(AdDownloadModel.JsonKey.ID, modelBox.id);
        intent.putExtra(BaseConstants.START_ONLY_FOR_ANDROID, true);
        JSONObject jSONObject = new JSONObject();
        try {
            context.startActivity(intent);
            return new OpenAppResult(7, OpenAppResult.Source.AM_KLLK2);
        } catch (Throwable unused) {
            trySendAmResultEvent(modelBox, jSONObject, 1, 3);
            return tryOpenMarket(context, Uri.parse(BaseConstants.MARKET_PREFIX + str));
        }
    }

    public static void realOpenKllk2Market(Context context, String str, long j, boolean z) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        ModelBox modelBox = ModelManager.getInstance().getModelBox(j);
        try {
            JSONObject downloadSettings = GlobalInfo.getDownloadSettings();
            String strOptString = downloadSettings.optString(o.at);
            String strDecrypt = DecryptUtils.decrypt(downloadSettings.optString("aa"), strOptString);
            String strDecrypt2 = DecryptUtils.decrypt(downloadSettings.optString("ac"), strOptString);
            String strDecrypt3 = DecryptUtils.decrypt(downloadSettings.optString("af"), strOptString);
            boolean zModifyPackageName = AnUtils.modifyPackageName(downloadSettings, context, strDecrypt2);
            StringBuilder sb = new StringBuilder(String.format(strDecrypt, str, strDecrypt3, strDecrypt2));
            Intent intent = new Intent("android.intent.action.VIEW");
            String marketPackageName = RomUtils.getMarketPackageName();
            if (ToolUtils.isInstalledApp(context, marketPackageName)) {
                intent.setPackage(marketPackageName);
            }
            if (z) {
                sb.append(DecryptUtils.decrypt(downloadSettings.optString("ae"), strOptString));
            } else {
                intent.addFlags(335544320);
            }
            ToolUtils.safePut(jSONObject, "mf", Boolean.valueOf(zModifyPackageName));
            ToolUtils.safePut(jSONObject, "if", Boolean.valueOf(z));
            intent.setData(Uri.parse(sb.toString()));
            intent.putExtra(BaseConstants.START_ONLY_FOR_ANDROID, true);
            context.startActivity(intent);
            AdAppLinkUtils.onMarketSuccess(OpenAppResult.Source.AM_KLLK2, jSONObject, modelBox, true);
            if (zModifyPackageName) {
                trySendAmResultEvent(modelBox, jSONObject, -1, 3);
            } else {
                trySendAmResultEvent(modelBox, jSONObject, 3, 3);
            }
        } catch (Exception unused) {
            AdAppLinkUtils.handleBackupOpenMarketResult(tryOpenMarket(GlobalInfo.getContext(), Uri.parse(BaseConstants.MARKET_PREFIX + str)), modelBox, true);
            trySendAmResultEvent(modelBox, jSONObject, 2, 3);
        }
    }

    private static boolean tryInnerOpen(Context context, String str) {
        if (context == null) {
            context = GlobalInfo.getContext();
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        if (!(context instanceof Activity)) {
            intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            if (DownloadSetting.obtainGlobal().optBugFix(DownloadSettingKeys.BugFix.FIX_APP_LINK_FLAG)) {
                intent.addFlags(32768);
            }
        }
        intent.setData(Uri.parse(str));
        intent.putExtra(BaseConstants.START_ONLY_FOR_ANDROID, true);
        String marketPackageName = RomUtils.getMarketPackageName();
        if (ToolUtils.isInstalledApp(GlobalInfo.getContext(), marketPackageName)) {
            intent.setPackage(marketPackageName);
        }
        if (!ToolUtils.isIntentAvailable(GlobalInfo.getContext(), intent)) {
            return false;
        }
        try {
            context.startActivity(intent);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private static boolean finalInnerOpenV1Market(@NonNull Activity activity, @NonNull String str, @NonNull HashMap<String, String> map) throws JSONException {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(BaseConstants.MARKET_PREFIX + str));
        intent.putExtra(BaseConstants.START_ONLY_FOR_ANDROID, true);
        intent.putExtra(TTDelegateActivity.PARAM, map);
        String marketPackageName = RomUtils.getMarketPackageName();
        if (ToolUtils.isInstalledApp(GlobalInfo.getContext(), marketPackageName)) {
            intent.setPackage(marketPackageName);
        }
        if (!ToolUtils.isIntentAvailable(GlobalInfo.getContext(), intent)) {
            return false;
        }
        try {
            activity.startActivity(intent);
            return true;
        } catch (Exception e) {
            TTDownloaderMonitor.inst().monitorException(e, "start v1");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void tryInnerOpenM1Market(Context context, String str, String str2, @NonNull ModelBox modelBox, @NonNull JSONObject jSONObject, @NonNull JSONObject jSONObject2, String str3) throws JSONException {
        ToolUtils.safePut(jSONObject, EventConstants.ExtraJson.KEY_TYPE, 1);
        try {
            String strReplaceM1Url = replaceM1Url(DecryptUtils.decryptOpenUrl(new JSONObject(str2).optString(o.ap)), jSONObject2, str3);
            ToolUtils.safePut(jSONObject, "open_url", strReplaceM1Url);
            if (tryInnerOpen(context, strReplaceM1Url)) {
                trySendAmResultEvent(modelBox, jSONObject, -1, 1);
                AdAppLinkUtils.onMarketSuccess(OpenAppResult.Source.AM_M1, jSONObject, modelBox, true);
            } else {
                AdAppLinkUtils.handleBackupOpenMarketResult(tryOpenMarket(context, Uri.parse(BaseConstants.MARKET_PREFIX + str)), modelBox, true);
                trySendAmResultEvent(modelBox, jSONObject, 2, 1);
            }
        } catch (Exception unused) {
            AdAppLinkUtils.handleBackupOpenMarketResult(tryOpenMarket(context, Uri.parse(BaseConstants.MARKET_PREFIX + str)), modelBox, true);
            trySendAmResultEvent(modelBox, jSONObject, 3, 1);
        }
    }

    private static void tryOpenM2Market(final Context context, final ModelBox modelBox, final String str) {
        DownloadComponentManager.getInstance().submitCPUTask(new Runnable() { // from class: com.ss.android.downloadlib.utils.InnerOpenAppUtils.2
            @Override // java.lang.Runnable
            public void run() throws JSONException {
                AdAppLinkUtils.handleBackupOpenMarketResult(InnerOpenAppUtils.tryOpenMarket(context, Uri.parse(BaseConstants.MARKET_PREFIX + str)), modelBox, true);
                JSONObject jSONObject = new JSONObject();
                try {
                    JSONObject downloadSettings = GlobalInfo.getDownloadSettings();
                    Thread.sleep(downloadSettings.optInt(DownloadSettingKeys.M2_DELAY_MILLIS, 1000));
                    AidlClient.getInstance().init(context, true);
                    AidlMsg aidlMsg = new AidlMsg();
                    aidlMsg.action = 1;
                    aidlMsg.code = 0;
                    aidlMsg.data = String.format(DecryptUtils.decrypt(downloadSettings.optString("v"), downloadSettings.optString(o.at)), str);
                    AidlClient.getInstance().doAction(aidlMsg, null);
                    AidlClient.getInstance().onDestroy();
                    InnerOpenAppUtils.trySendAmResultEvent(modelBox, jSONObject, -1, 2);
                } catch (Throwable th) {
                    th.printStackTrace();
                    InnerOpenAppUtils.trySendAmResultEvent(modelBox, jSONObject, 1, 2);
                }
            }
        });
    }

    private static void tryOpenV1Market(final Context context, final ModelBox modelBox, final String str) {
        DownloadComponentManager.getInstance().submitCPUTask(new Runnable() { // from class: com.ss.android.downloadlib.utils.InnerOpenAppUtils.3
            @Override // java.lang.Runnable
            public void run() throws JSONException {
                JSONObject downloadSettings = GlobalInfo.getDownloadSettings();
                String strOptString = downloadSettings.optString(o.at);
                final JSONObject jSONObject = new JSONObject();
                String strDecrypt = DecryptUtils.decrypt(downloadSettings.optString("x"), strOptString);
                JSONObject jSONObject2 = new JSONObject();
                ToolUtils.safePut(jSONObject2, o.av, "v");
                ToolUtils.safePut(jSONObject2, "p", str);
                byte[] bytes = jSONObject2.toString().getBytes();
                GlobalInfo.getDownloadNetworkFactory().postBody(strDecrypt, GlobalInfo.getEncryptor().encrypt(bytes, bytes.length), DownloadNetworkFactory.ContentType.TT_DATA, 0, new IHttpCallback() { // from class: com.ss.android.downloadlib.utils.InnerOpenAppUtils.3.1
                    @Override // com.ss.android.download.api.config.IHttpCallback
                    public void onResponse(String str2) throws JSONException {
                        InnerOpenAppUtils.tryInnerOpenV1Market(context, str, str2, modelBox, jSONObject);
                    }

                    @Override // com.ss.android.download.api.config.IHttpCallback
                    public void onError(Throwable th) throws JSONException {
                        AdAppLinkUtils.handleBackupOpenMarketResult(InnerOpenAppUtils.tryOpenMarket(context, Uri.parse(BaseConstants.MARKET_PREFIX + str)), modelBox, true);
                        ToolUtils.safePut(jSONObject, EventConstants.ExtraJson.KEY_MESSAGE, th != null ? th.getMessage() : "null");
                        InnerOpenAppUtils.trySendAmResultEvent(modelBox, jSONObject, 7, 5);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void tryInnerOpenV1Market(Context context, String str, String str2, @NonNull ModelBox modelBox, @NonNull JSONObject jSONObject) throws JSONException {
        ToolUtils.safePut(jSONObject, EventConstants.ExtraJson.KEY_TYPE, 5);
        try {
            String strDecryptOpenUrl = DecryptUtils.decryptOpenUrl(new JSONObject(str2).optString(o.ap));
            if (!TextUtils.isEmpty(strDecryptOpenUrl)) {
                TTDelegateActivity.openV1Market(str, modelBox.id, strDecryptOpenUrl, jSONObject);
            } else {
                AdAppLinkUtils.handleBackupOpenMarketResult(tryOpenMarket(context, Uri.parse(BaseConstants.MARKET_PREFIX + str)), modelBox, true);
                trySendAmResultEvent(modelBox, jSONObject, 5, 5);
            }
        } catch (Exception unused) {
            AdAppLinkUtils.handleBackupOpenMarketResult(tryOpenMarket(context, Uri.parse(BaseConstants.MARKET_PREFIX + str)), modelBox, true);
            trySendAmResultEvent(modelBox, jSONObject, 6, 5);
        }
    }

    public static void realInnerOpenV1Market(@NonNull Activity activity, String str, long j, String str2, String str3) {
        JSONObject jSONObject;
        int i;
        try {
            jSONObject = new JSONObject(str3);
        } catch (JSONException unused) {
            jSONObject = new JSONObject();
        }
        ModelBox modelBox = ModelManager.getInstance().getModelBox(j);
        try {
            JSONObject downloadSettings = GlobalInfo.getDownloadSettings();
            boolean zModifyPackageName = AnUtils.modifyPackageName(downloadSettings, activity, DecryptUtils.decrypt(downloadSettings.optString("bg"), downloadSettings.optString(o.at)));
            HashMap<String, String> mapJsonToMap = ToolUtils.jsonToMap(new JSONObject(str2));
            if (zModifyPackageName && !mapJsonToMap.isEmpty() && finalInnerOpenV1Market(activity, str, mapJsonToMap)) {
                trySendAmResultEvent(modelBox, jSONObject, -1, 5);
                AdAppLinkUtils.onMarketSuccess(OpenAppResult.Source.AM_V1, jSONObject, modelBox, true);
                return;
            }
            if (zModifyPackageName) {
                i = mapJsonToMap.isEmpty() ? 1 : 2;
            } else {
                i = 3;
            }
            trySendAmResultEvent(modelBox, jSONObject, i, 5);
            AdAppLinkUtils.handleBackupOpenMarketResult(tryOpenMarket(activity, Uri.parse(BaseConstants.MARKET_PREFIX + str)), modelBox, true);
        } catch (Exception unused2) {
            AdAppLinkUtils.handleBackupOpenMarketResult(tryOpenMarket(GlobalInfo.getContext(), Uri.parse(BaseConstants.MARKET_PREFIX + str)), modelBox, true);
            trySendAmResultEvent(modelBox, jSONObject, 4, 5);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void trySendAmResultEvent(ModelBox modelBox, JSONObject jSONObject, int i, int i2) throws JSONException {
        ToolUtils.safePut(jSONObject, "error_code", Integer.valueOf(i));
        ToolUtils.safePut(jSONObject, EventConstants.ExtraJson.KEY_TYPE, Integer.valueOf(i2));
        ToolUtils.safePut(jSONObject, RomUtils.getMarketPackageName(), Integer.valueOf(ToolUtils.getVersionCode(GlobalInfo.getContext(), RomUtils.getMarketPackageName())));
        AdEventHandler.getInstance().sendEvent(EventConstants.Label.ANTI_MARKET_RESULT, jSONObject, modelBox);
    }

    static OpenAppResult tryOpenByPackage(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return new OpenAppResult(4, 11);
        }
        if (context == null) {
            context = GlobalInfo.getContext();
        }
        Intent launchIntentForPackage = ToolUtils.getLaunchIntentForPackage(context, str);
        if (launchIntentForPackage == null) {
            return new OpenAppResult(4, 22);
        }
        launchIntentForPackage.putExtra(BaseConstants.START_ONLY_FOR_ANDROID, true);
        try {
            context.startActivity(launchIntentForPackage);
            return new OpenAppResult(3);
        } catch (Exception unused) {
            return new OpenAppResult(4, 23);
        }
    }

    static OpenAppResult tryOpenByPackage(Context context, String str, InnerUnifyData innerUnifyData) {
        Intent launchIntentForPackage = ToolUtils.getLaunchIntentForPackage(context, str);
        if (launchIntentForPackage == null) {
            return new OpenAppResult(4, 22);
        }
        if (Build.VERSION.SDK_INT >= 26 && GlobalInfo.getDownloadSettings().optInt(DownloadSettingKeys.KEY_OPEN_PACKAGE_MODE) == 1 && GlobalInfo.getAppStatusChangeListener() != null && GlobalInfo.getAppStatusChangeListener().isAppInBackground() && innerUnifyData.enableNewActivity()) {
            TTDelegateActivity.openAppByPKG(str, innerUnifyData);
            return new OpenAppResult(3);
        }
        launchIntentForPackage.putExtra(BaseConstants.START_ONLY_FOR_ANDROID, true);
        try {
            context.startActivity(launchIntentForPackage);
            return new OpenAppResult(3);
        } catch (Exception unused) {
            return new OpenAppResult(4, 23);
        }
    }

    static OpenAppResult tryOpenByPackage(String str, InnerUnifyData innerUnifyData) {
        return tryOpenByPackage(GlobalInfo.getContext(), str, innerUnifyData);
    }

    static OpenAppResult tryOpenByUrl(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return new OpenAppResult(2, 21);
        }
        if (context == null) {
            context = GlobalInfo.getContext();
        }
        Uri uri = Uri.parse(str);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(uri);
        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        intent.putExtra("open_url", str);
        intent.putExtra(BaseConstants.START_ONLY_FOR_ANDROID, true);
        if (DownloadSetting.obtainGlobal().optBugFix(DownloadSettingKeys.BugFix.FIX_APP_LINK_FLAG)) {
            intent.addFlags(DownloadExpSwitchCode.BACK_BUGFIX_SIGBUS);
        }
        if (ToolUtils.isInstalledApp(context, intent)) {
            try {
                context.startActivity(intent);
                return new OpenAppResult(1);
            } catch (Exception unused) {
                return new OpenAppResult(2);
            }
        }
        return new OpenAppResult(2, 24);
    }

    static OpenAppResult tryOpenByUrl(String str, @NonNull InnerUnifyData innerUnifyData) {
        if (TextUtils.isEmpty(str)) {
            return new OpenAppResult(2, 21);
        }
        Context context = GlobalInfo.getContext();
        Uri uri = Uri.parse(str);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(uri);
        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        intent.putExtra("open_url", str);
        intent.putExtra(BaseConstants.START_ONLY_FOR_ANDROID, true);
        if (DownloadSetting.obtainGlobal().optBugFix(DownloadSettingKeys.BugFix.FIX_APP_LINK_FLAG)) {
            intent.addFlags(DownloadExpSwitchCode.BACK_BUGFIX_SIGBUS);
        }
        if (ToolUtils.isInstalledApp(context, intent)) {
            if (GlobalInfo.getDownloadSettings().optInt(DownloadSettingKeys.KEY_OPEN_URL_MODE) == 0 && GlobalInfo.getAppStatusChangeListener() != null && GlobalInfo.getAppStatusChangeListener().isAppInBackground() && Build.VERSION.SDK_INT >= 26 && innerUnifyData.enableNewActivity()) {
                TTDelegateActivity.openApp(str, innerUnifyData);
            } else {
                try {
                    GlobalInfo.getContext().startActivity(intent);
                } catch (Exception unused) {
                    return new OpenAppResult(2);
                }
            }
            return new OpenAppResult(1);
        }
        return new OpenAppResult(2, 24);
    }

    static OpenAppResult tryNewOpenByUrlOrPackage(@NonNull NativeDownloadModel nativeDownloadModel, String str, String str2) {
        OpenAppResult openAppResultTryOpenByUrl = tryOpenByUrl(str, nativeDownloadModel);
        return (AppLinkOptimiseHelper.switchIsOpen(nativeDownloadModel) && openAppResultTryOpenByUrl.getType() == 2) ? tryOpenByPackage(str2, nativeDownloadModel) : openAppResultTryOpenByUrl;
    }

    static boolean tryOpenByQuickAppUrl(Context context, String str) {
        if (context == null) {
            return false;
        }
        try {
            Uri uri = Uri.parse(str);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(uri);
            intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            intent.putExtra("open_url", str);
            intent.putExtra(BaseConstants.START_ONLY_FOR_ANDROID, true);
            context.startActivity(intent);
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }
}
