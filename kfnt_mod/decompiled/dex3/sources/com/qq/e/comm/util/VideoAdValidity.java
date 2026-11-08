package com.qq.e.comm.util;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public enum VideoAdValidity {
    VALID("有效且可展示的广告"),
    OVERDUE("过期的广告"),
    SHOWED("已经展示的广告"),
    NONE_CACHE("视频未缓存的广告");

    private String a;

    VideoAdValidity(String str) {
        this.a = str;
    }

    public String getMessage() {
        return this.a;
    }
}
