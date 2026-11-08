package com.huawei.hms.support.api.opendevice;

import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hms.support.api.PendingResultImpl;
import com.huawei.hms.support.api.client.PendingResult;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.opendevice.HuaweiOpendeviceNaming;
import com.huawei.hms.support.api.entity.opendevice.OaidReq;
import com.huawei.hms.support.api.entity.opendevice.OaidResp;
import com.huawei.hms.support.api.entity.opendevice.OdidReq;
import com.huawei.hms.support.api.entity.opendevice.OdidResp;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class HuaweiOpendeviceApiImpl implements HuaweiOpendeviceApi {
    @Override // com.huawei.hms.support.api.opendevice.HuaweiOpendeviceApi
    public PendingResult<OaidResult> getOaid(HuaweiApiClient huaweiApiClient) {
        HMSLog.i("OpenIdentifierApiImpl", "Enter getOaid");
        return new PendingResultImpl<OaidResult, OaidResp>(huaweiApiClient, HuaweiOpendeviceNaming.getOaid, new OaidReq()) { // from class: com.huawei.hms.support.api.opendevice.HuaweiOpendeviceApiImpl.1
            @Override // com.huawei.hms.support.api.PendingResultImpl
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public OaidResult onComplete(OaidResp oaidResp) {
                if (oaidResp == null) {
                    HMSLog.e("OpenIdentifierApiImpl", "getOaid OaidResp is null");
                    return null;
                }
                Status commonStatus = oaidResp.getCommonStatus();
                if (commonStatus == null) {
                    HMSLog.e("OpenIdentifierApiImpl", "getOaid commonStatus is null");
                    return null;
                }
                HMSLog.i("OpenIdentifierApiImpl", "getOaid onComplete:" + commonStatus.getStatusCode());
                OaidResult oaidResult = new OaidResult();
                oaidResult.setStatus(commonStatus);
                oaidResult.setId(oaidResp.getId());
                oaidResult.setTrackLimited(oaidResp.isTrackLimited());
                oaidResult.setSettingIntent(oaidResp.getSettingIntent());
                return oaidResult;
            }
        };
    }

    @Override // com.huawei.hms.support.api.opendevice.HuaweiOpendeviceApi
    public PendingResult<OdidResult> getOdid(HuaweiApiClient huaweiApiClient) {
        HMSLog.i("OpenIdentifierApiImpl", "Enter getOdid");
        return new PendingResultImpl<OdidResult, OdidResp>(huaweiApiClient, HuaweiOpendeviceNaming.getOdid, new OdidReq()) { // from class: com.huawei.hms.support.api.opendevice.HuaweiOpendeviceApiImpl.2
            @Override // com.huawei.hms.support.api.PendingResultImpl
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public OdidResult onComplete(OdidResp odidResp) {
                if (odidResp == null) {
                    HMSLog.e("OpenIdentifierApiImpl", "getOdid OaidResp is null");
                    return null;
                }
                Status commonStatus = odidResp.getCommonStatus();
                if (commonStatus == null) {
                    HMSLog.e("OpenIdentifierApiImpl", "getOdid commonStatus is null");
                    return null;
                }
                HMSLog.i("OpenIdentifierApiImpl", "getOdid onComplete:" + commonStatus.getStatusCode());
                OdidResult odidResult = new OdidResult();
                odidResult.setStatus(commonStatus);
                odidResult.setId(odidResp.getId());
                return odidResult;
            }
        };
    }
}
