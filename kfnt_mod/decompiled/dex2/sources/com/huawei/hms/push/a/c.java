package com.huawei.hms.push.a;

import android.content.Intent;
import android.os.Bundle;
import com.huawei.hmf.tasks.g;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.common.internal.TaskApiCall;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.log.HMSLog;
import com.mbridge.msdk.foundation.entity.CampaignEx;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c extends TaskApiCall<com.huawei.hms.push.utils.c, a> {
    private String a;
    private String b;

    @Override // com.huawei.hms.common.internal.TaskApiCall
    public int getMinApkVersion() {
        return 40003000;
    }

    public c(String str, String str2, String str3, String str4, String str5) {
        super(str, str2, str3);
        this.a = str4;
        this.b = str5;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hms.common.internal.TaskApiCall
    public void doExecute(com.huawei.hms.push.utils.c cVar, ResponseErrorCode responseErrorCode, String str, g<a> gVar) {
        if (responseErrorCode.getErrorCode() == 0) {
            HMSLog.i("SendUpStreamTask", "send up stream task,Operate succeed");
            gVar.a((g<a>) null);
        } else {
            HMSLog.e("SendUpStreamTask", "send up stream task,Operate failed with ret=" + responseErrorCode.getErrorCode());
            com.huawei.hms.push.constant.a aVarA = com.huawei.hms.push.constant.a.a(responseErrorCode.getErrorCode());
            if (aVarA != com.huawei.hms.push.constant.a.ERROR_UNKNOWN) {
                gVar.a(com.huawei.hms.push.constant.a.a(aVarA));
            } else {
                gVar.a(new ApiException(new Status(responseErrorCode.getErrorCode(), responseErrorCode.getErrorReason())));
            }
        }
        a(cVar, responseErrorCode);
    }

    private void a(com.huawei.hms.push.utils.c cVar, ResponseErrorCode responseErrorCode) {
        HMSLog.i("SendUpStreamTask", "receive upstream, msgId :" + this.b + " , packageName = " + this.a + " , errorCode = " + responseErrorCode.getErrorCode());
        Intent intent = new Intent("com.huawei.push.action.MESSAGING_EVENT");
        intent.setPackage(this.a);
        Bundle bundle = new Bundle();
        bundle.putString("message_id", this.b);
        bundle.putInt(CampaignEx.JSON_NATIVE_VIDEO_ERROR, responseErrorCode.getErrorCode());
        if (com.huawei.hms.push.constant.a.SUCCESS.a() == responseErrorCode.getErrorCode()) {
            bundle.putString(RemoteMessageConst.MSGTYPE, "sent_message");
        } else {
            bundle.putString(RemoteMessageConst.MSGTYPE, "send_error");
        }
        if (new com.huawei.hms.support.api.push.a.a().a(cVar.getContext(), bundle, intent)) {
            HMSLog.i("SendUpStreamTask", "receive upstream, start service success");
            com.huawei.hms.push.utils.b.a(cVar.getContext(), getUri(), responseErrorCode);
        } else {
            HMSLog.w("SendUpStreamTask", "receive upstream, start service failed");
            com.huawei.hms.push.utils.b.a(cVar.getContext(), getUri(), responseErrorCode.getTransactionId(), com.huawei.hms.push.constant.a.ERROR_BIND_SERVICE_SELF_MAPPING);
        }
    }
}
