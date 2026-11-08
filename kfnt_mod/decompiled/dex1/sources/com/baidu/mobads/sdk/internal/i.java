package com.baidu.mobads.sdk.internal;

import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public enum i {
    NEWS("news", 0),
    IMAGE("image", 1),
    VIDEO("video", 2),
    TOPIC("topic", 3),
    AD("ad", 4),
    HOTDOC("hotkey", 5),
    SMALLVIDEO("smallvideo", 6),
    RECALLNEWS("recallNews", 8),
    POLICETASK("policetask", 9);

    String j;
    int k;

    i(String str, int i) {
        this.j = str;
        this.k = i;
    }

    public String b() {
        return this.j;
    }

    public int c() {
        return this.k;
    }

    public static i b(String str) {
        for (i iVar : values()) {
            if (iVar != null && TextUtils.isEmpty(iVar.j) && iVar.j.equals(str)) {
                return iVar;
            }
        }
        return null;
    }
}
