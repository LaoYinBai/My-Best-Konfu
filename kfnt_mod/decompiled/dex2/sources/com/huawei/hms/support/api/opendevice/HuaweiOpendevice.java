package com.huawei.hms.support.api.opendevice;

import com.huawei.hms.api.Api;
import com.huawei.hms.api.HuaweiApiAvailability;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class HuaweiOpendevice {
    public static final Api<Api.ApiOptions.NoOptions> OPEN_DEVICE_API = new Api<>(HuaweiApiAvailability.HMS_API_NAME_OD);
    public static final HuaweiOpendeviceApi HuaweiOpendeviceApi = new HuaweiOpendeviceApiImpl();
}
