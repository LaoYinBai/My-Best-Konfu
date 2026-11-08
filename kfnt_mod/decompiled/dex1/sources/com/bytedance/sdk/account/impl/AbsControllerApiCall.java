package com.bytedance.sdk.account.impl;

import com.bytedance.sdk.account.api.pool.IApiController;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class AbsControllerApiCall {
    protected IApiController mJobController;

    protected void attachController(IApiController iApiController) {
        this.mJobController = iApiController;
    }

    protected void cancelApi() {
        if (this.mJobController != null) {
            this.mJobController.cancel();
        }
    }
}
