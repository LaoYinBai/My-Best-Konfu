package com.huawei.hms.aaid.plugin;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ProxyCenter {
    private PushProxy proxy;

    private static class a {
        private static ProxyCenter a = new ProxyCenter();
    }

    public static ProxyCenter getInstance() {
        return a.a;
    }

    public PushProxy getProxy() {
        return this.proxy;
    }

    public void register(PushProxy pushProxy) {
        this.proxy = pushProxy;
    }
}
