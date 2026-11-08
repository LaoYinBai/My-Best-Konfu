package com.ss.android.token;

import android.content.Context;
import com.bytedance.sdk.account.api.call.AbsApiCall;
import com.bytedance.sdk.account.api.call.LogoutApiResponse;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TTTokenManager {
    private static volatile boolean sEnable = true;
    private static Set<String> sHostList = Collections.synchronizedSet(new HashSet());
    private static volatile boolean sInited;

    public static void initialize(Context context, TTTokenConfig tTTokenConfig) {
        if (sInited) {
            return;
        }
        TokenFactory.initialize(context, tTTokenConfig);
        TokenFactory.getInstance().setTokenEnable(sEnable);
        sInited = true;
        if (sHostList.size() != 0) {
            TokenFactory.getInstance().addConfigHost(sHostList);
            sHostList.clear();
            sHostList = null;
        }
    }

    public static void onSessionExpired(String str, List<TTTokenHeader> list, AbsApiCall<LogoutApiResponse> absApiCall) throws JSONException {
        if (sInited) {
            TokenFactory.getInstance().onSessionExpired(str, list, true, absApiCall);
        }
    }

    public static void onSessionExpired(String str, List<TTTokenHeader> list, boolean z, AbsApiCall<LogoutApiResponse> absApiCall) throws JSONException {
        if (sInited) {
            TokenFactory.getInstance().onSessionExpired(str, list, z, absApiCall);
        }
    }

    public static void setEnableToken(boolean z) {
        if (!sInited || z == sEnable) {
            return;
        }
        TokenFactory.getInstance().setTokenEnable(z);
        sEnable = z;
    }

    public static void clearToken() {
        if (sInited) {
            TokenFactory.getInstance().clearToken();
        }
    }

    public static Map<String, String> addRequestHeader(String str) {
        if (sInited) {
            return TokenFactory.getInstance().addRequestHeader(str);
        }
        return null;
    }

    public static void processResponseHeader(String str, List<TTTokenHeader> list) {
        if (sInited) {
            TokenFactory.getInstance().processResponseHeader(str, list);
        }
    }

    public static boolean isInited() {
        return sInited;
    }

    public static boolean isTokenEnable() {
        return sEnable;
    }

    public static String getTokenBeatUrl(boolean z, boolean z2) {
        return TokenFactory.getInstance().getTokenBeatUrl(z, z2);
    }

    public static void addConfigHost(Collection<String> collection) {
        if (sInited) {
            TokenFactory.getInstance().addConfigHost(collection);
        } else if (sHostList != null) {
            sHostList.addAll(collection);
        }
    }
}
