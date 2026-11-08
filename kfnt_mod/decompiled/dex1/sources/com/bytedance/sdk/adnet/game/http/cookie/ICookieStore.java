package com.bytedance.sdk.adnet.game.http.cookie;

import com.bytedance.sdk.adnet.game.ok3.okhttp3.Cookie;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.HttpUrl;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface ICookieStore {
    List<Cookie> getAllCookie();

    List<Cookie> getCookie(HttpUrl httpUrl);

    List<Cookie> loadCookie(HttpUrl httpUrl);

    boolean removeAllCookie();

    boolean removeCookie(HttpUrl httpUrl);

    boolean removeCookie(HttpUrl httpUrl, Cookie cookie);

    void saveCookie(HttpUrl httpUrl, Cookie cookie);

    void saveCookie(HttpUrl httpUrl, List<Cookie> list);
}
