package com.unity3d.services.monetization.core.api;

import com.unity3d.ads.UnityAds;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import com.unity3d.services.monetization.UnityMonetization;
import com.unity3d.services.monetization.core.placementcontent.PlacementContentResultFactory;
import com.unity3d.services.monetization.core.utilities.JSONUtilities;
import com.unity3d.services.monetization.placementcontent.ads.ShowAdPlacementContent;
import java.lang.reflect.InvocationTargetException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PlacementContents {
    @WebViewExposed
    public static void createPlacementContent(String str, JSONObject jSONObject, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        com.unity3d.services.monetization.core.placementcontent.PlacementContents.putPlacementContent(str, PlacementContentResultFactory.create(str, JSONUtilities.jsonObjectToMap(jSONObject)));
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void setPlacementContentState(String str, String str2, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        com.unity3d.services.monetization.core.placementcontent.PlacementContents.setPlacementContentState(str, UnityMonetization.PlacementContentState.valueOf(str2));
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void sendAdFinished(String str, String str2, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        ShowAdPlacementContent.sendAdFinished(str, UnityAds.FinishState.valueOf(str2));
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void sendAdStarted(String str, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        ShowAdPlacementContent.sendAdStarted(str);
        webViewCallback.invoke(new Object[0]);
    }
}
