package com.bytedance.sdk.open.aweme.base;

import androidx.annotation.NonNull;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IAPPCheckHelper {
    @NonNull
    String getPackageName();

    @NonNull
    String getRemoteAuthEntryActivity();

    boolean isAppInstalled();

    boolean isAppSupportAuthorization();

    boolean isAppSupportShare();

    boolean isSupportNewTiktokApi();
}
