package com.bytedance.sdk.adnet.game.http.cookie;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Cookie;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.HttpUrl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements ICookieStore {

    /* renamed from: a, reason: collision with root package name */
    private final Map<String, ConcurrentHashMap<String, Cookie>> f5034a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    private final SharedPreferences f5035b;

    public a(Context context) {
        Cookie cookieA;
        this.f5035b = context.getSharedPreferences("default_ad_net_ck", 0);
        for (Map.Entry<String, ?> entry : this.f5035b.getAll().entrySet()) {
            if (entry.getValue() != null && !entry.getKey().startsWith("ad_cookie_")) {
                for (String str : TextUtils.split((String) entry.getValue(), IWeiboService.Scope.EMPTY_SCOPE)) {
                    String string = this.f5035b.getString("ad_cookie_" + str, null);
                    if (string != null && (cookieA = c.a(string)) != null) {
                        if (!this.f5034a.containsKey(entry.getKey())) {
                            this.f5034a.put(entry.getKey(), new ConcurrentHashMap<>());
                        }
                        this.f5034a.get(entry.getKey()).put(str, cookieA);
                    }
                }
            }
        }
    }

    private String a(Cookie cookie) {
        return cookie.name() + "@" + cookie.domain();
    }

    private void a(HttpUrl httpUrl, Cookie cookie, String str) {
        this.f5034a.get(httpUrl.host()).put(str, cookie);
        SharedPreferences.Editor editorEdit = this.f5035b.edit();
        editorEdit.putString(httpUrl.host(), TextUtils.join(IWeiboService.Scope.EMPTY_SCOPE, this.f5034a.get(httpUrl.host()).keySet()));
        editorEdit.putString("ad_cookie_" + str, c.a(httpUrl.host(), cookie));
        editorEdit.apply();
    }

    private static boolean b(Cookie cookie) {
        return cookie.expiresAt() < System.currentTimeMillis();
    }

    @Override // com.bytedance.sdk.adnet.game.http.cookie.ICookieStore
    public List<Cookie> getAllCookie() {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = this.f5034a.keySet().iterator();
        while (it.hasNext()) {
            arrayList.addAll(this.f5034a.get(it.next()).values());
        }
        return arrayList;
    }

    @Override // com.bytedance.sdk.adnet.game.http.cookie.ICookieStore
    public List<Cookie> getCookie(HttpUrl httpUrl) {
        ArrayList arrayList = new ArrayList();
        ConcurrentHashMap<String, Cookie> concurrentHashMap = this.f5034a.get(httpUrl.host());
        if (concurrentHashMap != null) {
            arrayList.addAll(concurrentHashMap.values());
        }
        return arrayList;
    }

    @Override // com.bytedance.sdk.adnet.game.http.cookie.ICookieStore
    public List<Cookie> loadCookie(HttpUrl httpUrl) {
        ArrayList arrayList = new ArrayList();
        if (!this.f5034a.containsKey(httpUrl.host())) {
            return arrayList;
        }
        for (Cookie cookie : this.f5034a.get(httpUrl.host()).values()) {
            if (b(cookie)) {
                removeCookie(httpUrl, cookie);
            } else {
                arrayList.add(cookie);
            }
        }
        return arrayList;
    }

    @Override // com.bytedance.sdk.adnet.game.http.cookie.ICookieStore
    public boolean removeAllCookie() {
        this.f5034a.clear();
        SharedPreferences.Editor editorEdit = this.f5035b.edit();
        editorEdit.clear();
        editorEdit.apply();
        return true;
    }

    @Override // com.bytedance.sdk.adnet.game.http.cookie.ICookieStore
    public boolean removeCookie(HttpUrl httpUrl) {
        if (!this.f5034a.containsKey(httpUrl.host())) {
            return false;
        }
        Set<String> setKeySet = this.f5034a.remove(httpUrl.host()).keySet();
        SharedPreferences.Editor editorEdit = this.f5035b.edit();
        for (String str : setKeySet) {
            if (this.f5035b.contains("ad_cookie_" + str)) {
                editorEdit.remove("ad_cookie_" + str);
            }
        }
        editorEdit.remove(httpUrl.host());
        editorEdit.apply();
        return true;
    }

    @Override // com.bytedance.sdk.adnet.game.http.cookie.ICookieStore
    public boolean removeCookie(HttpUrl httpUrl, Cookie cookie) {
        if (!this.f5034a.containsKey(httpUrl.host())) {
            return false;
        }
        String strA = a(cookie);
        if (!this.f5034a.get(httpUrl.host()).containsKey(strA)) {
            return false;
        }
        this.f5034a.get(httpUrl.host()).remove(strA);
        SharedPreferences.Editor editorEdit = this.f5035b.edit();
        if (this.f5035b.contains("ad_cookie_" + strA)) {
            editorEdit.remove("ad_cookie_" + strA);
        }
        editorEdit.putString(httpUrl.host(), TextUtils.join(IWeiboService.Scope.EMPTY_SCOPE, this.f5034a.get(httpUrl.host()).keySet()));
        editorEdit.apply();
        return true;
    }

    @Override // com.bytedance.sdk.adnet.game.http.cookie.ICookieStore
    public void saveCookie(HttpUrl httpUrl, Cookie cookie) {
        if (!this.f5034a.containsKey(httpUrl.host())) {
            this.f5034a.put(httpUrl.host(), new ConcurrentHashMap<>());
        }
        if (b(cookie)) {
            removeCookie(httpUrl, cookie);
        } else {
            a(httpUrl, cookie, a(cookie));
        }
    }

    @Override // com.bytedance.sdk.adnet.game.http.cookie.ICookieStore
    public void saveCookie(HttpUrl httpUrl, List<Cookie> list) {
        Iterator<Cookie> it = list.iterator();
        while (it.hasNext()) {
            saveCookie(httpUrl, it.next());
        }
    }
}
