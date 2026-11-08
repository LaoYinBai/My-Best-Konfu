package com.huawei.hms.support.api.client;

import com.huawei.hms.common.api.Releasable;
import com.huawei.hms.support.api.client.Result;
import com.huawei.hms.support.log.HMSLog;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class ResultCallbacks<R extends Result> implements ResultCallback<R> {
    public abstract void onFailure(Status status);

    public abstract void onSuccess(R r);

    @Override // com.huawei.hms.support.api.client.ResultCallback
    public final void onResult(R r) {
        try {
            Status status = r.getStatus();
            if (status.isSuccess()) {
                onSuccess(r);
            } else {
                onFailure(status);
                if (r instanceof Releasable) {
                    ((Releasable) r).release();
                }
            }
        } catch (Exception e) {
            HMSLog.w("ResultCallbacks", "Failed to release " + r + ", reason: " + e);
        }
    }
}
