package com.bytedance.sdk.account.platform;

import android.os.Bundle;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
abstract class PlatformDelegate {
    PlatformBindAdapter bindDelegate;
    PlatformLoginAdapter loginDelegate;

    interface IFactory {
        PlatformDelegate createBind(PlatformBindAdapter platformBindAdapter);

        PlatformDelegate createLogin(PlatformLoginAdapter platformLoginAdapter);
    }

    abstract void cancelBind();

    abstract void cancelLogin();

    abstract void requestBind(Bundle bundle);

    abstract void requestLogin(Bundle bundle);

    PlatformDelegate(PlatformBindAdapter platformBindAdapter) {
        this.bindDelegate = platformBindAdapter;
        this.loginDelegate = null;
    }

    PlatformDelegate(PlatformLoginAdapter platformLoginAdapter) {
        this.loginDelegate = platformLoginAdapter;
        this.bindDelegate = null;
    }
}
