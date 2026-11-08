package com.baidu.mobads.sdk.api.annotation;

import com.baidu.mobads.sdk.api.AdservRemoteLoaderImpl;
import com.baidu.mobads.sdk.api.RouteInfo;
import com.baidu.mobads.sdk.internal.c;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class remote_adserv {
    public static HashMap<String, RouteInfo> getRoutesMap() {
        HashMap<String, RouteInfo> map = new HashMap<>();
        map.put(c.a.f3200a, RouteInfo.build(AdservRemoteLoaderImpl.class, c.a.f3200a));
        return map;
    }
}
