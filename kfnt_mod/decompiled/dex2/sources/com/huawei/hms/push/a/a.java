package com.huawei.hms.push.a;

import com.huawei.hmf.tasks.g;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.common.internal.TaskApiCall;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends TaskApiCall<com.huawei.hms.push.utils.c, Void> {
    @Override // com.huawei.hms.common.internal.TaskApiCall
    public int getMinApkVersion() {
        return 30000000;
    }

    public a(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hms.common.internal.TaskApiCall
    public void doExecute(com.huawei.hms.push.utils.c cVar, ResponseErrorCode responseErrorCode, String str, g<Void> gVar) {
        if (responseErrorCode.getErrorCode() == 0) {
            HMSLog.i("BaseVoidTask", "Operate succeed");
            gVar.a((g<Void>) null);
        } else {
            HMSLog.e("BaseVoidTask", "Operate failed with ret=" + responseErrorCode.getErrorCode());
            com.huawei.hms.push.constant.a aVarA = com.huawei.hms.push.constant.a.a(responseErrorCode.getErrorCode());
            if (aVarA != com.huawei.hms.push.constant.a.ERROR_UNKNOWN) {
                gVar.a(com.huawei.hms.push.constant.a.a(aVarA));
            } else {
                gVar.a(new ApiException(new Status(responseErrorCode.getErrorCode(), responseErrorCode.getErrorReason())));
            }
        }
        com.huawei.hms.push.utils.b.a(cVar.getContext(), getUri(), responseErrorCode);
    }
}
