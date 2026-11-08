package com.bytedance.sdk.adnet.game.http.cookie;

import com.bytedance.sdk.adnet.game.ok3.okhttp3.Cookie;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.CookieJar;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.HttpUrl;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b implements CookieJar {

    /* renamed from: a, reason: collision with root package name */
    private ICookieStore f5036a;

    public b(ICookieStore iCookieStore) {
        this.f5036a = iCookieStore;
    }

    public ICookieStore a() {
        return this.f5036a;
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.CookieJar
    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        return this.f5036a.loadCookie(httpUrl);
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.CookieJar
    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<Cookie> it = list.iterator();
        while (it.hasNext()) {
            this.f5036a.saveCookie(httpUrl, it.next());
        }
    }
}
