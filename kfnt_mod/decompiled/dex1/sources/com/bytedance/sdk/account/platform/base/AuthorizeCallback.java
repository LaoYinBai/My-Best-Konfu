package com.bytedance.sdk.account.platform.base;

import android.os.Bundle;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface AuthorizeCallback {
    void onError(AuthorizeErrorResponse authorizeErrorResponse);

    void onSuccess(Bundle bundle);
}
