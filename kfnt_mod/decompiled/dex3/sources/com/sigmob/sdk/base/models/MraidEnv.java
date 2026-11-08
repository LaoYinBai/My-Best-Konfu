package com.sigmob.sdk.base.models;

import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MraidEnv {
    public final Boolean coppa;
    public final String version = "2.0";
    public final String sdk = Constants.SDK_FOLDER;
    public final String sdkVersion = Constants.SDK_VERSION;
    public final String appId = a.n().P();
    public final String ifa = a.n().w();
    public final Boolean limitAdTracking = Boolean.valueOf(a.n().x());

    public MraidEnv() {
        this.coppa = Boolean.valueOf(a.m() == 1);
    }
}
