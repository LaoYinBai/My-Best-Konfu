package com.bytedance.sdk.account.platform.base;

import android.content.Context;
import com.bytedance.sdk.account.platform.api.IAuthorizeService;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AuthorizeFramework {
    private static Context appContext;
    private static Map<Class, IAuthorizeService> serviceHashMap = new ConcurrentHashMap();

    public static <T extends IAuthorizeService> void registerService(Class<T> cls, IAuthorizeService iAuthorizeService) {
        if (iAuthorizeService != null) {
            serviceHashMap.put(cls, iAuthorizeService);
        }
    }

    public static <T extends IAuthorizeService, W extends AuthorizeCreator<T>> T createService(Context context, W w) {
        return (T) w.createService(context);
    }

    public static <T extends IAuthorizeService> T getService(Class<T> cls) {
        return (T) serviceHashMap.get(cls);
    }

    public static <T extends AuthorizeIniter> void init(Context context, T... tArr) {
        appContext = context;
        if (tArr != null) {
            for (T t : tArr) {
                t.init(context);
            }
        }
    }
}
