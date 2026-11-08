package com.appsflyer;

import android.content.Context;
import android.util.Log;
import com.unity3d.player.UnityPlayer;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AppsFlyerUnityHelper {
    private static String TAG = "AppsFlyerUnityHelper";

    public static void createConversionDataListener(Context context, final String str) {
        AppsFlyerLib.getInstance().registerConversionListener(context, new AppsFlyerConversionListener() { // from class: com.appsflyer.AppsFlyerUnityHelper.1
            @Override // com.appsflyer.AppsFlyerConversionListener
            public void onInstallConversionDataLoaded(Map<String, String> map) {
                UnityPlayer.UnitySendMessage(str, "didReceiveConversionData", new JSONObject(map).toString());
            }

            @Override // com.appsflyer.AppsFlyerConversionListener
            public void onInstallConversionFailure(String str2) {
                UnityPlayer.UnitySendMessage(str, "didReceiveConversionDataWithError", str2);
            }

            @Override // com.appsflyer.AppsFlyerConversionListener
            public void onAppOpenAttribution(Map<String, String> map) {
                UnityPlayer.UnitySendMessage(str, "onAppOpenAttribution", new JSONObject(map).toString());
            }

            @Override // com.appsflyer.AppsFlyerConversionListener
            public void onAttributionFailure(String str2) {
                UnityPlayer.UnitySendMessage(str, "onAppOpenAttributionFailure", str2);
            }
        });
    }

    public static void createValidateInAppListener(Context context, final String str, final String str2, final String str3) {
        AppsFlyerLib.getInstance().registerValidatorListener(context, new AppsFlyerInAppPurchaseValidatorListener() { // from class: com.appsflyer.AppsFlyerUnityHelper.2
            @Override // com.appsflyer.AppsFlyerInAppPurchaseValidatorListener
            public void onValidateInApp() {
                Log.i("AppsFlyerLibUnityhelper", "onValidateInApp called.");
                UnityPlayer.UnitySendMessage(str, str2, "Validate success");
            }

            @Override // com.appsflyer.AppsFlyerInAppPurchaseValidatorListener
            public void onValidateInAppFailure(String str4) {
                Log.i("AppsFlyerLibUnityhelper", "onValidateInAppFailure called.");
                UnityPlayer.UnitySendMessage(str, str3, str4);
            }
        });
    }
}
