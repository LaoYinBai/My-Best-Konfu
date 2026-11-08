package com.ss.union.game.sdk.core.browser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.ss.android.pushmanager.PushCommonConstants;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.ss.union.game.sdk.common.util.AppUtils;
import com.ss.union.game.sdk.common.util.DownloadUtils;
import com.ss.union.game.sdk.common.util.MainThreadExecutor;
import com.ss.union.game.sdk.common.util.RomUtils;
import com.ss.union.game.sdk.common.util.ToastUtils;
import com.ss.union.game.sdk.core.applog.AppLogManager;
import com.ss.union.game.sdk.core.applog.AppLogOaidListener;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.core.base.account.model.User;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.constant.SDKVersion;
import com.ss.union.game.sdk.core.base.event.EventJSONHeaders;
import com.ss.union.game.sdk.core.base.utils.DanJuanUtils;
import com.ss.union.game.sdk.core.util.LogDevKitUtils;
import com.umeng.commonsdk.proguard.o;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class JSInterface {
    private JSONObject appInfoObject = new JSONObject();
    private List<com.ss.union.game.sdk.core.browser.a> mJSHandlers = new ArrayList();
    private b mJSView;

    public static class a {
        public static final String a = "com.bd.ad.game.union";
        public static final String b = "snssdk174592://";
        public static final String c = "com.playgame.havefun";
        public static final String d = "vgame://";
        public static final int e = 10232;
    }

    private void startDeeplinkToSDK(String str) {
    }

    @JavascriptInterface
    public void hideBackBtn() {
    }

    @JavascriptInterface
    public void showBackBtn() {
    }

    public JSInterface(@NonNull b bVar, @NonNull List<com.ss.union.game.sdk.core.browser.a> list) throws JSONException, PackageManager.NameNotFoundException {
        this.mJSView = bVar;
        if (this.mJSView.c() == null) {
            LogDevKitUtils.logBrowser("初始化JSInterface失败，context=null");
            return;
        }
        this.mJSHandlers.addAll(list);
        Context contextC = this.mJSView.c();
        try {
            String did = AppLogManager.getInstance().getDid();
            this.appInfoObject.put("os", "Android");
            this.appInfoObject.put(o.E, Build.BRAND.toLowerCase());
            this.appInfoObject.put(o.C, Build.MODEL);
            this.appInfoObject.put("device_id", TextUtils.isEmpty(did) ? "" : did);
            this.appInfoObject.put(PushCommonConstants.KEY_OPENUDID, EventJSONHeaders.getInstance().getOpenUDID(contextC));
            try {
                String strSubstring = Build.VERSION.RELEASE;
                if (strSubstring != null && strSubstring.length() > 10) {
                    strSubstring = strSubstring.substring(0, 10);
                }
                this.appInfoObject.put(o.x, strSubstring);
            } catch (Exception unused) {
            }
            StringBuilder sb = new StringBuilder();
            try {
                if (RomUtils.isXiaomi()) {
                    sb.append("MIUI-");
                }
                sb.append(Build.VERSION.INCREMENTAL);
                this.appInfoObject.put("rom", sb.toString());
            } catch (Throwable unused2) {
            }
            PackageInfo packageInfo = contextC.getPackageManager().getPackageInfo(contextC.getPackageName(), 0);
            if (packageInfo.applicationInfo != null) {
                int i = packageInfo.applicationInfo.labelRes;
                if (i > 0) {
                    this.appInfoObject.put(o.r, contextC.getString(i));
                }
                this.appInfoObject.put("version_code", packageInfo.versionCode);
            }
            this.appInfoObject.put("u_version_name", getYouxiaobangVersionName(contextC));
            this.appInfoObject.put("u_version_code", getYouxiaobangVersionCode(contextC));
            this.appInfoObject.put("momoyu_version_name", getMomoYuVersionName(contextC));
            this.appInfoObject.put("momoyu_version_code", getMomoYuVersionCode(contextC));
            this.appInfoObject.put("is_running_virtual", DanJuanUtils.isRunningDanJuanVirtual() ? 1 : 0);
            String currentUserOpenId = LGAccountDataUtil.getCurrentUserOpenId();
            String loginToken = LGAccountDataUtil.getLoginToken();
            this.appInfoObject.put(o.t, SDKVersion.LG_SDK_TYPE_U_SDK);
            this.appInfoObject.put("sdk_version", 2333);
            this.appInfoObject.put("account_type", ConfigManager.LoginConfig.isNoUserLogin() ? "DEVICE" : "ACCOUNT");
            this.appInfoObject.put(User.KEY_OPEN_ID, TextUtils.isEmpty(currentUserOpenId) ? "" : currentUserOpenId);
            this.appInfoObject.put("token", TextUtils.isEmpty(loginToken) ? "" : loginToken);
            AppLogManager.getInstance().registerOaidListener(new AppLogOaidListener() { // from class: com.ss.union.game.sdk.core.browser.JSInterface.1
                @Override // com.ss.union.game.sdk.core.applog.AppLogOaidListener
                public void onOaidLoaded(String str) throws JSONException {
                    try {
                        JSInterface.this.appInfoObject.put("oaid", str);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    private String getYouxiaobangVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(a.a, 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private int getYouxiaobangVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(a.a, 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private String getMomoYuVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(a.c, 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private int getMomoYuVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(a.c, 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @JavascriptInterface
    public String getAppInfo() {
        return this.appInfoObject == null ? "" : this.appInfoObject.toString();
    }

    @JavascriptInterface
    public void closePage() {
        if (this.mJSView != null) {
            this.mJSView.b();
        }
    }

    @JavascriptInterface
    public boolean isAppInstalled(String str) {
        return AppUtils.isInstalledApp(str);
    }

    @JavascriptInterface
    public void downloadApp(String str) {
        String strOptString;
        String strOptString2;
        if (this.mJSView.c() == null || TextUtils.isEmpty(str)) {
            return;
        }
        String strOptString3 = null;
        try {
            JSONObject jSONObject = new JSONObject(str);
            strOptString = jSONObject.optString("download_url");
            try {
                strOptString2 = jSONObject.optString("app_name");
                try {
                    strOptString3 = jSONObject.optString(MonitorConstants.PKG_NAME);
                } catch (JSONException e) {
                    e = e;
                    e.printStackTrace();
                    if (TextUtils.isEmpty(strOptString)) {
                        return;
                    } else {
                        return;
                    }
                }
            } catch (JSONException e2) {
                e = e2;
                strOptString2 = null;
            }
        } catch (JSONException e3) {
            e = e3;
            strOptString = null;
            strOptString2 = null;
        }
        if (TextUtils.isEmpty(strOptString) || TextUtils.isEmpty(strOptString2) || TextUtils.isEmpty(strOptString3)) {
            return;
        }
        DownloadUtils.downloadUrlLink(strOptString, strOptString2, true);
    }

    @JavascriptInterface
    public void launchApp(String str) {
        Intent launchIntentForPackage;
        Context contextC = this.mJSView.c();
        if (contextC == null) {
            return;
        }
        if (!isAppInstalled(str) || (launchIntentForPackage = AppUtils.getLaunchIntentForPackage(contextC, str)) == null) {
            ToastUtils.getInstance().toast("请下载app后继续前往");
        } else {
            contextC.startActivity(launchIntentForPackage);
        }
    }

    @JavascriptInterface
    public void startDeeplink(String str) {
        Context contextC = this.mJSView.c();
        if (contextC == null || TextUtils.isEmpty(str)) {
            return;
        }
        if (str.startsWith(a.b) || str.startsWith(a.d)) {
            try {
                contextC.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @JavascriptInterface
    public void startDeeplinkForResult(String str) {
        try {
            this.mJSView.a(new Intent("android.intent.action.VIEW", Uri.parse(str)), a.e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        Iterator<com.ss.union.game.sdk.core.browser.a> it = this.mJSHandlers.iterator();
        while (it.hasNext()) {
            it.next().a(activity, i, i2, intent);
        }
    }

    @JavascriptInterface
    public void startDeeplinkInner(final String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.core.browser.JSInterface.2
            @Override // java.lang.Runnable
            public void run() {
                JSInterface.this.startDeeplinkInnerMainThread(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startDeeplinkInnerMainThread(String str) {
        try {
            LogDevKitUtils.logBrowser("receive h5 message: " + str);
            JSONObject jSONObject = new JSONObject(str);
            boolean zOptBoolean = jSONObject.optBoolean("is_close", false);
            String strOptString = jSONObject.optString("to_sdk", "");
            String strOptString2 = jSONObject.optString("to_cp", "");
            if (!TextUtils.isEmpty(strOptString)) {
                startDeeplinkToSDK(strOptString);
            }
            if (!TextUtils.isEmpty(strOptString2)) {
                startDeeplinkToCP(strOptString2);
            }
            if (zOptBoolean) {
                closePage();
            }
        } catch (Throwable unused) {
        }
    }

    private void startDeeplinkToCP(String str) {
        com.ss.union.game.sdk.core.valueAdded.a.a().a(str);
    }
}
