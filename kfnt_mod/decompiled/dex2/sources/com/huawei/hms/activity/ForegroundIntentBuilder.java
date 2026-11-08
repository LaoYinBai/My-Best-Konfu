package com.huawei.hms.activity;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.hms.activity.internal.BusResponseCallback;
import com.huawei.hms.activity.internal.ForegroundBusResponseMgr;
import com.huawei.hms.activity.internal.ForegroundInnerHeader;
import com.huawei.hms.common.internal.RequestHeader;
import com.huawei.hms.common.internal.TransactionIdCreater;
import com.huawei.hms.support.api.entity.core.CoreNaming;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hms.utils.Util;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ForegroundIntentBuilder {
    private Activity a;
    private RequestHeader b;
    private String c;
    private ForegroundInnerHeader d;

    public ForegroundIntentBuilder(Activity activity) throws IllegalArgumentException {
        if (activity == null) {
            throw new IllegalArgumentException("listener must not be null.");
        }
        this.a = activity;
        this.b = new RequestHeader();
        this.b.setPkgName(activity.getPackageName());
        this.b.setSdkVersion(40004300);
        this.c = "";
        this.d = new ForegroundInnerHeader();
        this.d.setApkVersion(30000000);
    }

    public Intent build() {
        Intent intentStartBridgeActivity = BridgeActivity.getIntentStartBridgeActivity(this.a, a.class.getName());
        if (this.b.getAppID() == null) {
            this.b.setAppID(Util.getAppId(this.a) + HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        } else {
            this.b.setAppID(Util.getAppId(this.a) + HiAnalyticsConstant.REPORT_VAL_SEPARATOR + this.b.getAppID());
        }
        if (TextUtils.isEmpty(this.b.getTransactionId())) {
            this.b.setTransactionId(TransactionIdCreater.getId(this.b.getAppID(), CoreNaming.HUBREQUEST));
        }
        intentStartBridgeActivity.putExtra("HMS_FOREGROUND_REQ_HEADER", this.b.toJson());
        intentStartBridgeActivity.putExtra("HMS_FOREGROUND_REQ_BODY", this.c);
        intentStartBridgeActivity.putExtra("HMS_FOREGROUND_REQ_INNER", this.d.toJson());
        return intentStartBridgeActivity;
    }

    public ForegroundIntentBuilder setSubAppId(String str) {
        this.b.setAppID(str);
        return this;
    }

    public ForegroundIntentBuilder setRequestBody(String str) {
        this.c = str;
        return this;
    }

    public ForegroundIntentBuilder setMinApkVersion(int i) {
        this.d.setApkVersion(i);
        return this;
    }

    public ForegroundIntentBuilder setResponseCallback(String str, BusResponseCallback busResponseCallback) {
        this.d.setResponseCallbackKey(str);
        ForegroundBusResponseMgr.getInstance().registerObserver(str, busResponseCallback);
        return this;
    }

    public ForegroundIntentBuilder setResponseCallback(String str) {
        this.d.setResponseCallbackKey(str);
        return this;
    }

    public ForegroundIntentBuilder setAction(String str) {
        this.b.setApiName(str);
        return this;
    }

    public ForegroundIntentBuilder setServiceName(String str) {
        this.b.setSrvName(str);
        return this;
    }

    public ForegroundIntentBuilder setTransactionId(String str) {
        this.b.setTransactionId(str);
        return this;
    }

    public ForegroundIntentBuilder setKitSdkVersion(int i) {
        this.b.setKitSdkVersion(i);
        return this;
    }

    public static void registerResponseCallback(String str, BusResponseCallback busResponseCallback) {
        ForegroundBusResponseMgr.getInstance().registerObserver(str, busResponseCallback);
    }

    public static void unregisterResponseCallback(String str) {
        ForegroundBusResponseMgr.getInstance().unRegisterObserver(str);
    }
}
