package com.baidu.mobads.sdk.internal;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public enum ay {
    INTERFACE_USE_PROBLEM(1010001, "接口使用问题"),
    SHOW_STANDARD_UNFIT(3040001, "容器大小不达标");


    /* renamed from: c, reason: collision with root package name */
    public static final String f3141c = "msg";
    private int d;
    private String e;

    ay(int i, String str) {
        this.d = i;
        this.e = str;
    }

    public int b() {
        return this.d;
    }

    public String c() {
        return this.e;
    }
}
