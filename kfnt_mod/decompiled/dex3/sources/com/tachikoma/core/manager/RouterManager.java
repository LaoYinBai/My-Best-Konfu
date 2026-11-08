package com.tachikoma.core.manager;

import android.net.Uri;
import android.text.TextUtils;
import com.tachikoma.core.api.IRouterInner;
import com.tachikoma.core.bridge.JSContext;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RouterManager {
    static volatile RouterManager routerManager;
    HashMap<JSContext, IRouterInner> routers = new HashMap<>();

    private RouterManager() {
    }

    public static RouterManager getInstance() {
        if (routerManager == null) {
            synchronized (RouterManager.class) {
                if (routerManager == null) {
                    routerManager = new RouterManager();
                }
            }
        }
        return routerManager;
    }

    public void navigateTo(JSContext jSContext, String str) {
        if (TextUtils.isEmpty(str) || jSContext == null) {
            return;
        }
        try {
            this.routers.get(jSContext).navigateTo(Uri.parse(str));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void registerRouter(JSContext jSContext, IRouterInner iRouterInner) {
        if (jSContext == null || iRouterInner == null) {
            return;
        }
        this.routers.put(jSContext, iRouterInner);
    }

    public void unRegisterRouter(JSContext jSContext) {
        if (jSContext != null) {
            this.routers.remove(jSContext);
        }
    }
}
