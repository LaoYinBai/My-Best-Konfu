package com.vivo.push.util;

import com.vivo.push.NoPorGuard;

@NoPorGuard
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class VivoPushException extends Exception {
    public static final int REASON_CODE_ACCESS = 10000;
    private int mReasonCode;

    public VivoPushException(String str) {
        this(10000, str);
    }

    public VivoPushException(int i, String str) {
        super(str);
        this.mReasonCode = i;
    }

    public int getCode() {
        return this.mReasonCode;
    }
}
