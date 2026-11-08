package anet.channel;

import android.text.TextUtils;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class b {

    /* renamed from: a, reason: collision with root package name */
    Map<String, Integer> f400a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    Map<String, SessionInfo> f401b = new ConcurrentHashMap();

    b() {
    }

    void a(SessionInfo sessionInfo) {
        if (sessionInfo == null) {
            throw new NullPointerException("info is null");
        }
        if (TextUtils.isEmpty(sessionInfo.host)) {
            throw new IllegalArgumentException("host cannot be null or empty");
        }
        this.f401b.put(sessionInfo.host, sessionInfo);
    }

    SessionInfo a(String str) {
        return this.f401b.remove(str);
    }

    SessionInfo b(String str) {
        return this.f401b.get(str);
    }

    Collection<SessionInfo> a() {
        return this.f401b.values();
    }

    void a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("host cannot be null or empty");
        }
        synchronized (this.f400a) {
            this.f400a.put(str, Integer.valueOf(i));
        }
    }

    public int c(String str) {
        Integer num;
        synchronized (this.f400a) {
            num = this.f400a.get(str);
        }
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }
}
