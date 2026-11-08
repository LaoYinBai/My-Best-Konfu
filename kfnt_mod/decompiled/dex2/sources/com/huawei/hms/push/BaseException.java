package com.huawei.hms.push;

import com.huawei.hms.push.constant.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class BaseException extends Exception {
    private final int a;
    private final a b;

    public BaseException(int i) {
        this.b = a.a(i);
        this.a = this.b.b();
    }

    public int getErrorCode() {
        return this.a;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.b.c();
    }
}
