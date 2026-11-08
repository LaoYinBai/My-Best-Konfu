package com.ss.union.game.sdk.common.net;

import com.bytedance.sdk.adnet.game.http.NetClient;
import com.bytedance.sdk.adnet.game.http.cookie.ICookieStore;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Cookie;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.HttpUrl;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class OkCookieManager {
    public static void saveCookie(String str, List<Cookie> list) {
        ICookieStore iCookieStoreCookieStore = NetClient.getInstance().cookieStore();
        if (iCookieStoreCookieStore != null) {
            iCookieStoreCookieStore.saveCookie(HttpUrl.parse(str), list);
        }
    }

    public static void saveCookie(String str, Cookie cookie) {
        ICookieStore iCookieStoreCookieStore = NetClient.getInstance().cookieStore();
        if (iCookieStoreCookieStore != null) {
            iCookieStoreCookieStore.saveCookie(HttpUrl.parse(str), cookie);
        }
    }

    public static List<Cookie> loadCookie(String str) {
        ICookieStore iCookieStoreCookieStore = NetClient.getInstance().cookieStore();
        if (iCookieStoreCookieStore != null) {
            return iCookieStoreCookieStore.loadCookie(HttpUrl.parse(str));
        }
        return null;
    }

    public static List<Cookie> getAllCookie() {
        ICookieStore iCookieStoreCookieStore = NetClient.getInstance().cookieStore();
        if (iCookieStoreCookieStore != null) {
            return iCookieStoreCookieStore.getAllCookie();
        }
        return null;
    }

    public static List<Cookie> getCookie(String str) {
        ICookieStore iCookieStoreCookieStore = NetClient.getInstance().cookieStore();
        if (iCookieStoreCookieStore != null) {
            return iCookieStoreCookieStore.getCookie(HttpUrl.parse(str));
        }
        return null;
    }

    public static boolean removeCookie(String str, Cookie cookie) {
        ICookieStore iCookieStoreCookieStore = NetClient.getInstance().cookieStore();
        if (iCookieStoreCookieStore != null) {
            return iCookieStoreCookieStore.removeCookie(HttpUrl.parse(str), cookie);
        }
        return false;
    }

    public static boolean removeCookie(String str) {
        ICookieStore iCookieStoreCookieStore = NetClient.getInstance().cookieStore();
        if (iCookieStoreCookieStore != null) {
            return iCookieStoreCookieStore.removeCookie(HttpUrl.parse(str));
        }
        return false;
    }

    public static boolean removeAllCookie() {
        ICookieStore iCookieStoreCookieStore = NetClient.getInstance().cookieStore();
        if (iCookieStoreCookieStore != null) {
            return iCookieStoreCookieStore.removeAllCookie();
        }
        return false;
    }
}
