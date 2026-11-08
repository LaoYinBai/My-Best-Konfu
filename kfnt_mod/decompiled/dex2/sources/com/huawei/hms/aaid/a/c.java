package com.huawei.hms.aaid.a;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hmf.tasks.g;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.aaid.HmsInstanceIdEx;
import com.huawei.hms.aaid.constant.AaidIdConstant;
import com.huawei.hms.aaid.entity.TokenResp;
import com.huawei.hms.aaid.entity.TokenResult;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.common.internal.TaskApiCall;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c extends TaskApiCall<d, TokenResult> {
    private Context a;

    @Override // com.huawei.hms.common.internal.TaskApiCall
    public int getMinApkVersion() {
        return 30000000;
    }

    public c(String str, String str2, Context context, String str3) {
        super(str, str2, str3);
        this.a = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hms.common.internal.TaskApiCall
    public void doExecute(d dVar, ResponseErrorCode responseErrorCode, String str, g<TokenResult> gVar) {
        if (responseErrorCode.getErrorCode() != 0) {
            HMSLog.e(HmsInstanceIdEx.TAG, "TokenTask failed, ErrorCode: " + responseErrorCode.getErrorCode());
            com.huawei.hms.aaid.constant.a aVarA = com.huawei.hms.aaid.constant.a.a(responseErrorCode.getErrorCode());
            if (aVarA != com.huawei.hms.aaid.constant.a.ERROR_UNKNOWN) {
                gVar.a(com.huawei.hms.aaid.constant.a.a(aVarA));
            } else {
                gVar.a(new ApiException(new Status(responseErrorCode.getErrorCode(), responseErrorCode.getErrorReason())));
            }
        } else {
            TokenResp tokenResp = (TokenResp) JsonUtil.jsonToEntity(str, new TokenResp());
            com.huawei.hms.aaid.constant.a aVarA2 = com.huawei.hms.aaid.constant.a.a(tokenResp.getRetCode());
            if (aVarA2 != com.huawei.hms.aaid.constant.a.SUCCESS) {
                gVar.a(com.huawei.hms.aaid.constant.a.a(aVarA2));
                HMSLog.e(HmsInstanceIdEx.TAG, "TokenTask failed, StatusCode:" + aVarA2.a());
            } else {
                TokenResult tokenResult = new TokenResult();
                tokenResult.setToken(tokenResp.getToken());
                tokenResult.setBelongId(tokenResp.getBelongId());
                tokenResult.setRetCode(com.huawei.hms.aaid.constant.a.a(tokenResp.getRetCode()).a());
                gVar.a((g<TokenResult>) tokenResult);
                String token = tokenResp.getToken();
                if (TextUtils.isEmpty(token)) {
                    HMSLog.i(HmsInstanceId.TAG, "GetTokenTask receive a empty token, please check HmsMessageService.onNewToken receive result.");
                    com.huawei.hms.aaid.b.b.a(dVar.getContext(), getUri(), responseErrorCode);
                    return;
                } else if (!com.huawei.hms.aaid.encrypt.b.a(this.a, AaidIdConstant.PushClientSelfInfo.FILE_NAME).equals(token)) {
                    HMSLog.i(HmsInstanceId.TAG, "receive a token, refresh the local token");
                    com.huawei.hms.aaid.encrypt.b.a(this.a, AaidIdConstant.PushClientSelfInfo.FILE_NAME, token);
                }
            }
        }
        com.huawei.hms.aaid.b.b.a(dVar.getContext(), getUri(), responseErrorCode);
    }
}
