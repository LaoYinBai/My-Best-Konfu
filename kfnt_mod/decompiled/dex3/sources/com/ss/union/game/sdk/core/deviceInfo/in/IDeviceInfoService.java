package com.ss.union.game.sdk.core.deviceInfo.in;

import com.ss.union.game.sdk.core.deviceInfo.callback.ICloseDeviceInfoCallback;
import com.ss.union.game.sdk.core.deviceInfo.callback.IQueryDevicePrivacyInfoCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IDeviceInfoService {
    void closeDeviceInfo(ICloseDeviceInfoCallback iCloseDeviceInfoCallback);

    void queryDevicePrivacyInfo(IQueryDevicePrivacyInfoCallback iQueryDevicePrivacyInfoCallback);
}
