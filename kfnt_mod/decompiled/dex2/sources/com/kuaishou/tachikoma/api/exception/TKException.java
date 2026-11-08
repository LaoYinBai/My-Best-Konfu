package com.kuaishou.tachikoma.api.exception;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class TKException extends RuntimeException {
    public TKException(String str) {
        super(attachExtraInfo(str));
    }

    public TKException(String str, Throwable th) {
        super(attachExtraInfo(str), th);
    }

    public TKException(Throwable th) {
        this(attachExtraInfo(""), th);
    }

    private static String attachExtraInfo(String str) {
        return str + " ##### TACHIKOMA异常了";
    }
}
