package com.baidu.mobads.sdk.internal;

import com.baidu.mobads.sdk.api.RouteInfo;
import java.lang.reflect.Method;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ag extends RouteInfo {

    /* renamed from: a, reason: collision with root package name */
    private q f3103a;

    public ag(String str) {
        setPath(str);
        b();
    }

    public Object a() {
        return this.f3103a;
    }

    private void b() {
        RouteInfo routeInfo;
        try {
            Class<?> cls = Class.forName(r.ai + getPath());
            if (cls != null) {
                Method declaredMethod = cls.getDeclaredMethod("getRoutesMap", new Class[0]);
                declaredMethod.setAccessible(true);
                HashMap map = (HashMap) declaredMethod.invoke(null, new Object[0]);
                if (map == null || map.size() <= 0 || (routeInfo = (RouteInfo) map.get(getPath())) == null) {
                    return;
                }
                this.f3103a = (q) routeInfo.getDestination().getConstructor(new Class[0]).newInstance(new Object[0]);
            }
        } catch (Throwable unused) {
        }
    }
}
