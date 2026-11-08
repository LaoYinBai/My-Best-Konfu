package com.unity3d.services.ads.token;

import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.WebViewEventCategory;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TokenStorage {
    private static int _accessCounter;
    private static boolean _peekMode;
    private static ConcurrentLinkedQueue<String> _queue;

    public static synchronized void createTokens(JSONArray jSONArray) throws JSONException {
        _queue = new ConcurrentLinkedQueue<>();
        _accessCounter = 0;
        for (int i = 0; i < jSONArray.length(); i++) {
            _queue.add(jSONArray.getString(i));
        }
    }

    public static synchronized void appendTokens(JSONArray jSONArray) throws JSONException {
        if (_queue == null) {
            _queue = new ConcurrentLinkedQueue<>();
            _accessCounter = 0;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            _queue.add(jSONArray.getString(i));
        }
    }

    public static synchronized void deleteTokens() {
        _queue = null;
        _accessCounter = 0;
    }

    public static synchronized String getToken() {
        if (_queue == null) {
            return null;
        }
        if (_queue.isEmpty()) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.TOKEN, TokenEvent.QUEUE_EMPTY, new Object[0]);
            return null;
        }
        if (_peekMode) {
            WebViewApp currentApp = WebViewApp.getCurrentApp();
            WebViewEventCategory webViewEventCategory = WebViewEventCategory.TOKEN;
            TokenEvent tokenEvent = TokenEvent.TOKEN_ACCESS;
            int i = _accessCounter;
            _accessCounter = i + 1;
            currentApp.sendEvent(webViewEventCategory, tokenEvent, Integer.valueOf(i));
            return _queue.peek();
        }
        WebViewApp currentApp2 = WebViewApp.getCurrentApp();
        WebViewEventCategory webViewEventCategory2 = WebViewEventCategory.TOKEN;
        TokenEvent tokenEvent2 = TokenEvent.TOKEN_ACCESS;
        int i2 = _accessCounter;
        _accessCounter = i2 + 1;
        currentApp2.sendEvent(webViewEventCategory2, tokenEvent2, Integer.valueOf(i2));
        return _queue.poll();
    }

    public static synchronized void setPeekMode(boolean z) {
        _peekMode = z;
    }
}
