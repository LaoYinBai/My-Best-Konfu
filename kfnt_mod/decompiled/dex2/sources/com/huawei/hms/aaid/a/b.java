package com.huawei.hms.aaid.a;

import com.huawei.hmf.tasks.g;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.aaid.entity.DeleteTokenResp;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.common.internal.TaskApiCall;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends TaskApiCall<d, Void> {
    @Override // com.huawei.hms.common.internal.TaskApiCall
    public int getMinApkVersion() {
        return 30000000;
    }

    public b(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hms.common.internal.TaskApiCall
    public void doExecute(d dVar, ResponseErrorCode responseErrorCode, String str, g<Void> gVar) {
        if (responseErrorCode.getErrorCode() != 0) {
            HMSLog.e(HmsInstanceId.TAG, "DeleteTokenTask failed, ErrorCode: " + responseErrorCode.getErrorCode());
            com.huawei.hms.aaid.constant.a aVarA = com.huawei.hms.aaid.constant.a.a(responseErrorCode.getErrorCode());
            if (aVarA != com.huawei.hms.aaid.constant.a.ERROR_UNKNOWN) {
                gVar.a(com.huawei.hms.aaid.constant.a.a(aVarA));
                return;
            } else {
                gVar.a(new ApiException(new Status(responseErrorCode.getErrorCode(), responseErrorCode.getErrorReason())));
                return;
            }
        }
        com.huawei.hms.aaid.constant.a aVarA2 = com.huawei.hms.aaid.constant.a.a(((DeleteTokenResp) JsonUtil.jsonToEntity(str, new DeleteTokenResp())).getRetCode());
        if (aVarA2 != com.huawei.hms.aaid.constant.a.SUCCESS) {
            gVar.a(com.huawei.hms.aaid.constant.a.a(aVarA2));
        } else {
            gVar.a((g<Void>) null);
            com.huawei.hms.aaid.b.b.a(dVar.getContext(), getUri(), responseErrorCode);
        }
    }
}
