package com.appsflyer;

import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AppsFlyer2dXConversionCallback implements AppsFlyerConversionListener {
    public native void onAppOpenAttributionNative(Object obj);

    public native void onAttributionFailureNative(Object obj);

    public native void onInstallConversionDataLoadedNative(Object obj);

    public native void onInstallConversionFailureNative(Object obj);

    @Override // com.appsflyer.AppsFlyerConversionListener
    public void onInstallConversionDataLoaded(Map<String, String> map) {
        onInstallConversionDataLoadedNative(map);
    }

    @Override // com.appsflyer.AppsFlyerConversionListener
    public void onInstallConversionFailure(String str) throws JSONException {
        m53("onAttributionFailure", str);
    }

    @Override // com.appsflyer.AppsFlyerConversionListener
    public void onAppOpenAttribution(Map<String, String> map) {
        onAppOpenAttributionNative(map);
    }

    @Override // com.appsflyer.AppsFlyerConversionListener
    public void onAttributionFailure(String str) throws JSONException {
        m53("onInstallConversionFailure", str);
    }

    /* renamed from: ˊ, reason: contains not printable characters */
    private void m53(String str, String str2) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("status", "failure");
            jSONObject.put("data", str2);
            char c2 = 65535;
            int iHashCode = str.hashCode();
            if (iHashCode != -1390007222) {
                if (iHashCode == 1050716216 && str.equals("onInstallConversionFailure")) {
                    c2 = 0;
                }
            } else if (str.equals("onAttributionFailure")) {
                c2 = 1;
            }
            switch (c2) {
                case 0:
                    onInstallConversionFailureNative(jSONObject);
                    break;
                case 1:
                    onAttributionFailureNative(jSONObject);
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
