package com.bytedance.sdk.account.platform.base;

import android.os.Bundle;
import androidx.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AuthorizeErrorResponse {
    public Bundle extras;
    public boolean isCancel;
    public String logid = "";

    @Nullable
    public String platformErrorCode;

    @Nullable
    public String platformErrorDetail;

    @Nullable
    public String platformErrorMsg;

    public AuthorizeErrorResponse(@Nullable String str, @Nullable String str2) {
        this.platformErrorMsg = str2;
        this.platformErrorCode = str;
    }

    public AuthorizeErrorResponse() {
    }

    public AuthorizeErrorResponse(int i, String str) {
        this.platformErrorCode = String.valueOf(i);
        this.platformErrorMsg = str;
    }

    public AuthorizeErrorResponse(@Nullable String str) {
        this.platformErrorMsg = str;
    }

    public AuthorizeErrorResponse(int i, @Nullable String str, @Nullable String str2) {
        this.platformErrorCode = String.valueOf(i);
        this.platformErrorMsg = str;
        this.platformErrorDetail = str2;
    }

    public AuthorizeErrorResponse(boolean z) {
        this.isCancel = z;
    }
}
