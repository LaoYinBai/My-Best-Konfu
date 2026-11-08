package com.bytedance.sdk.account.platform.base;

import android.content.Context;
import com.bytedance.sdk.account.platform.api.IAuthorizeService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface AuthorizeCreator<T extends IAuthorizeService> {
    T createService(Context context);
}
