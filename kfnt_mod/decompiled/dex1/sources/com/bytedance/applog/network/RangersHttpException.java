package com.bytedance.applog.network;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class RangersHttpException extends Exception {
    public int mResponseCode;

    public RangersHttpException(int i, String str) {
        super(str);
        this.mResponseCode = i;
    }

    public RangersHttpException(int i, Throwable th) {
        super(th);
        this.mResponseCode = i;
    }

    public int getResponseCode() {
        return this.mResponseCode;
    }
}
