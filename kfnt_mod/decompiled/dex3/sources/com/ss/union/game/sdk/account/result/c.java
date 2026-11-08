package com.ss.union.game.sdk.account.result;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public enum c {
    UN_LOGIN(-4001, b.k),
    CANCEL_SWITCH(-1004, com.ss.union.game.sdk.account.b.b);

    final int c;
    final String d;

    public int a() {
        return this.c;
    }

    public String b() {
        return this.d;
    }

    c(int i, String str) {
        this.c = i;
        this.d = str;
    }
}
