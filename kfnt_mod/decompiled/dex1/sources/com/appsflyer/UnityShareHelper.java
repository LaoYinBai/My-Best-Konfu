package com.appsflyer;

import android.content.Context;
import android.util.Log;
import com.appsflyer.CreateOneLinkHttpTask;
import com.appsflyer.share.CrossPromotionHelper;
import com.appsflyer.share.LinkGenerator;
import com.appsflyer.share.ShareInviteHelper;
import com.unity3d.player.UnityPlayer;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class UnityShareHelper {
    private static String callbackMethodFailedName;
    private static String callbackMethodName;
    private static String callbackObjectName;
    private static UnityShareHelper instance;

    public static UnityShareHelper getInstance() {
        if (instance == null) {
            instance = new UnityShareHelper();
            return instance;
        }
        return instance;
    }

    public void trackAndOpenStore(Context context, String str, String str2, Map<String, Object> map) {
        if (str2 == null || str == null) {
            return;
        }
        if (map != null) {
            HashMap map2 = new HashMap();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getKey().toString() != null && entry.getValue().toString() != null) {
                    map2.put(entry.getKey().toString(), entry.getValue().toString());
                }
            }
            CrossPromotionHelper.trackAndOpenStore(context, str, str2, map2);
            return;
        }
        CrossPromotionHelper.trackAndOpenStore(context, str, str2);
    }

    public void createOneLinkInviteListener(Context context, Map<String, Object> map, String str, String str2, String str3) {
        callbackObjectName = str;
        callbackMethodName = str2;
        callbackMethodFailedName = str3;
        LinkGenerator linkGeneratorGenerateInviteUrl = ShareInviteHelper.generateInviteUrl(context);
        Object obj = map.get("channel");
        Object obj2 = map.get("campaign");
        Object obj3 = map.get("referrerName");
        Object obj4 = map.get("referrerImageUrl");
        Object obj5 = map.get("customerID");
        Object obj6 = map.get("baseDeepLink");
        if (obj != null) {
            linkGeneratorGenerateInviteUrl.setChannel(obj.toString());
            map.remove("channel");
        }
        if (obj2 != null) {
            linkGeneratorGenerateInviteUrl.setCampaign(obj2.toString());
            map.remove("campaign");
        }
        if (obj3 != null) {
            linkGeneratorGenerateInviteUrl.setReferrerName(obj3.toString());
            map.remove("referrerName");
        }
        if (obj4 != null) {
            linkGeneratorGenerateInviteUrl.setReferrerImageURL(obj4.toString());
            map.remove("referrerImageUrl");
        }
        if (obj5 != null) {
            linkGeneratorGenerateInviteUrl.setReferrerCustomerId(obj5.toString());
            map.remove("customerID");
        }
        if (obj6 != null) {
            linkGeneratorGenerateInviteUrl.setBaseDeeplink(obj6.toString());
            map.remove("baseDeepLink");
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                linkGeneratorGenerateInviteUrl.addParameter(entry.getKey().toString(), entry.getValue().toString());
            }
        }
        linkGeneratorGenerateInviteUrl.generateLink(context, new inviteCallbacksImpl());
    }

    private class inviteCallbacksImpl implements CreateOneLinkHttpTask.ResponseListener {
        private inviteCallbacksImpl() {
        }

        @Override // com.appsflyer.CreateOneLinkHttpTask.ResponseListener
        public void onResponse(String str) {
            Log.i("AppsFlyerLibUnityhelper", "CreateOneLinkHttpTask.ResponseListener called.");
            UnityPlayer.UnitySendMessage(UnityShareHelper.callbackObjectName, UnityShareHelper.callbackMethodName, str);
        }

        @Override // com.appsflyer.CreateOneLinkHttpTask.ResponseListener
        public void onResponseError(String str) {
            Log.i("AppsFlyerLibUnityhelper", "onValidateInAppFailure called.");
            UnityPlayer.UnitySendMessage(UnityShareHelper.callbackObjectName, UnityShareHelper.callbackMethodFailedName, str);
        }
    }
}
