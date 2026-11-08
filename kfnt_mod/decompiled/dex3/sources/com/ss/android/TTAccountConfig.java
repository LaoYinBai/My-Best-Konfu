package com.ss.android;

import android.content.Context;
import com.bytedance.sdk.account.IMonitor;
import com.bytedance.sdk.account.INetWork;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface TTAccountConfig {
    Context getApplicationContext();

    IMonitor getMonitor();

    INetWork getNetwork();

    String host();

    boolean isSecureCaptchaEnabled();

    boolean isSupportMultiLogin();
}
