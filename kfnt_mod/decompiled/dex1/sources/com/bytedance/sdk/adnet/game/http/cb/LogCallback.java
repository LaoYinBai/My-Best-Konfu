package com.bytedance.sdk.adnet.game.http.cb;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class LogCallback {
    public boolean interceptorLog(String str) {
        return false;
    }

    public abstract boolean isOpen();

    public boolean isPrintHeader() {
        return false;
    }

    public String logTag() {
        return "AD_HTTP_LOG";
    }
}
