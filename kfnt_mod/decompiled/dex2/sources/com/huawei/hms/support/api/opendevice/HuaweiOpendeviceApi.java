package com.huawei.hms.support.api.opendevice;

import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hms.support.api.client.PendingResult;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public interface HuaweiOpendeviceApi {
    PendingResult<OaidResult> getOaid(HuaweiApiClient huaweiApiClient);

    PendingResult<OdidResult> getOdid(HuaweiApiClient huaweiApiClient);
}
