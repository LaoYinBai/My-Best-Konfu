package com.huawei.hms.opendevice;

import android.app.Activity;
import android.content.Context;
import com.huawei.hmf.tasks.f;
import com.huawei.hms.api.Api;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.support.api.entity.opendevice.HuaweiOpendeviceNaming;
import com.huawei.hms.support.api.opendevice.OdidResult;
import com.huawei.hms.support.hianalytics.HiAnalyticsClient;
import com.huawei.hms.utils.JsonUtil;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class OpenDeviceClientImpl extends HuaweiApi<OpenDeviceOptions> implements OpenDeviceClient {
    private static final OpenDeviceHmsClientBuilder a = new OpenDeviceHmsClientBuilder();
    private static final Api<OpenDeviceOptions> b = new Api<>(HuaweiApiAvailability.HMS_API_NAME_OD);
    private static OpenDeviceOptions c = new OpenDeviceOptions();

    OpenDeviceClientImpl(Activity activity) {
        super(activity, b, c, (AbstractClientBuilder) a);
        super.setKitSdkVersion(40004301);
    }

    OpenDeviceClientImpl(Context context) {
        super(context, b, c, a);
        super.setKitSdkVersion(40004301);
    }

    @Override // com.huawei.hms.opendevice.OpenDeviceClient
    public f<OdidResult> getOdid() {
        return doWrite(new OpenDeviceTaskApiCall(HuaweiOpendeviceNaming.getOdid, JsonUtil.createJsonString(null), HiAnalyticsClient.reportEntry(getContext(), HuaweiOpendeviceNaming.getOdid, 40004301)));
    }
}
