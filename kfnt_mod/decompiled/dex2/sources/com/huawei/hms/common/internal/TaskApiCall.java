package com.huawei.hms.common.internal;

import android.os.Parcelable;
import com.huawei.hmf.tasks.g;
import com.huawei.hms.common.internal.AnyClient;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class TaskApiCall<ClientT extends AnyClient, ResultT> {
    private final String a;
    private final String b;
    private Parcelable c;
    private String d;
    private com.huawei.hmf.tasks.a e;
    private int f;

    protected abstract void doExecute(ClientT clientt, ResponseErrorCode responseErrorCode, String str, g<ResultT> gVar);

    @Deprecated
    public int getMinApkVersion() {
        return 30000000;
    }

    @Deprecated
    public TaskApiCall(String str, String str2) {
        this.f = 1;
        this.a = str;
        this.b = str2;
        this.c = null;
        this.d = null;
    }

    public TaskApiCall(String str, String str2, String str3) {
        this.f = 1;
        this.a = str;
        this.b = str2;
        this.c = null;
        this.d = str3;
    }

    public TaskApiCall(String str, String str2, String str3, int i) {
        this.f = 1;
        this.a = str;
        this.b = str2;
        this.c = null;
        this.d = str3;
        this.f = i;
    }

    public com.huawei.hmf.tasks.a getToken() {
        return this.e;
    }

    public void setToken(com.huawei.hmf.tasks.a aVar) {
        this.e = aVar;
    }

    public String getUri() {
        return this.a;
    }

    public String getTransactionId() {
        return this.d;
    }

    public void setTransactionId(String str) {
        this.d = str;
    }

    public String getRequestJson() {
        return this.b;
    }

    public Parcelable getParcelable() {
        return this.c;
    }

    public void setParcelable(Parcelable parcelable) {
        this.c = parcelable;
    }

    public int getApiLevel() {
        return this.f;
    }

    public void setApiLevel(int i) {
        this.f = i;
    }

    public final void onResponse(ClientT clientt, ResponseErrorCode responseErrorCode, String str, g<ResultT> gVar) {
        if (this.e != null && this.e.a()) {
            HMSLog.i("TaskApiCall", "This Task has been canceled, uri:" + this.a + ", transactionId:" + this.d);
            return;
        }
        doExecute(clientt, responseErrorCode, str, gVar);
    }
}
