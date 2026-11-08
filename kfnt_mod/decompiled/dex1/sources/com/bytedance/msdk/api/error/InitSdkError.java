package com.bytedance.msdk.api.error;

import com.bytedance.msdk.api.AdError;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class InitSdkError extends AdError {
    public int thirdSdkErrorCode;
    public String thirdSdkErrorMessage;

    public InitSdkError(int i, String str) {
        super(i, AdError.messageFormat(AdError.AD_THIRD_SDK_ADAPTER_INITIALIZATION_SUCCESS_MSG, "【" + str + "】", new String[0]));
        this.thirdSdkErrorCode = i;
        this.thirdSdkErrorMessage = AdError.messageFormat(AdError.AD_THIRD_SDK_ADAPTER_INITIALIZATION_SUCCESS_MSG, "【" + str + "】", new String[0]);
    }

    public String printMsg() {
        return this.thirdSdkErrorMessage;
    }

    @Override // com.bytedance.msdk.api.AdError
    public String toString() {
        return "InitSdkError{thirdSdkErrorCode=" + this.thirdSdkErrorCode + ", thirdSdkErrorMessage='" + this.thirdSdkErrorMessage + "'}";
    }
}
