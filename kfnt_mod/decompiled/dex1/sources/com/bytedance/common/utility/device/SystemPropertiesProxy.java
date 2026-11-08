package com.bytedance.common.utility.device;

import com.bytedance.sdk.account.execute.ApiRequest;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class SystemPropertiesProxy {
    SystemPropertiesProxy() {
    }

    public String get(String str) throws Throwable {
        return (String) Class.forName("android.os.SystemProperties").getMethod(ApiRequest.METHOD_GET, String.class).invoke(null, str);
    }
}
