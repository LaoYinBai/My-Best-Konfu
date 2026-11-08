package com.huawei.hms.common;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.hmf.tasks.f;
import com.huawei.hmf.tasks.g;
import com.huawei.hms.api.Api;
import com.huawei.hms.api.Api.ApiOptions;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.AnyClient;
import com.huawei.hms.common.internal.ClientSettings;
import com.huawei.hms.common.internal.ConnectionManagerKey;
import com.huawei.hms.common.internal.HuaweiApiManager;
import com.huawei.hms.common.internal.TaskApiCall;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.client.SubAppInfo;
import com.huawei.hms.support.api.entity.auth.Scope;
import com.huawei.hms.support.hianalytics.b;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.Checker;
import com.huawei.hms.utils.HMSBIInitializer;
import com.huawei.hms.utils.Util;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class HuaweiApi<TOption extends Api.ApiOptions> {
    private HuaweiApiManager a;
    private Context b;
    private ConnectionManagerKey<TOption> c;
    private TOption d;
    private AbstractClientBuilder<?, TOption> e;
    private String f;
    private String g;
    private String h;
    private SubAppInfo i;
    private WeakReference<Activity> j;
    private int k;
    private int l = 1;
    private boolean m = false;

    public HuaweiApi(Activity activity, Api<TOption> api, TOption toption, AbstractClientBuilder abstractClientBuilder, int i) {
        Checker.checkNonNull(activity, "Null activity is not permitted.");
        this.j = new WeakReference<>(activity);
        a(activity, api, toption, abstractClientBuilder, i, null);
    }

    public HuaweiApi(Activity activity, Api<TOption> api, TOption toption, AbstractClientBuilder abstractClientBuilder, int i, String str) {
        Checker.checkNonNull(activity, "Null activity is not permitted.");
        this.j = new WeakReference<>(activity);
        a(activity, api, toption, abstractClientBuilder, i, str);
    }

    public HuaweiApi(Activity activity, Api<TOption> api, TOption toption, AbstractClientBuilder abstractClientBuilder) {
        Checker.checkNonNull(activity, "Null activity is not permitted.");
        this.j = new WeakReference<>(activity);
        a(activity, api, toption, abstractClientBuilder, 0, null);
    }

    public HuaweiApi(Context context, Api<TOption> api, TOption toption, AbstractClientBuilder abstractClientBuilder, int i) {
        Checker.checkNonNull(context, "Null context is not permitted.");
        a(context, api, toption, abstractClientBuilder, i, null);
    }

    public HuaweiApi(Context context, Api<TOption> api, TOption toption, AbstractClientBuilder abstractClientBuilder, int i, String str) {
        Checker.checkNonNull(context, "Null context is not permitted.");
        a(context, api, toption, abstractClientBuilder, i, str);
    }

    public HuaweiApi(Context context, Api<TOption> api, TOption toption, AbstractClientBuilder abstractClientBuilder) {
        Checker.checkNonNull(context, "Null context is not permitted.");
        a(context, api, toption, abstractClientBuilder, 0, null);
    }

    private void a(Context context, Api<TOption> api, TOption toption, AbstractClientBuilder abstractClientBuilder, int i, String str) {
        this.b = context.getApplicationContext();
        this.a = HuaweiApiManager.getInstance(this.b);
        this.c = ConnectionManagerKey.createConnectionManagerKey(api, toption, str);
        this.d = toption;
        this.e = abstractClientBuilder;
        this.f = Util.getAppId(context);
        this.g = this.f;
        this.h = Util.getCpId(context);
        this.i = new SubAppInfo("");
        this.k = i;
        if (!TextUtils.isEmpty(str)) {
            if (str.equals(this.f)) {
                HMSLog.e("HuaweiApi", "subAppId is host appid");
            } else {
                HMSLog.i("HuaweiApi", "subAppId is " + str);
                this.i = new SubAppInfo(str);
            }
        }
        a(context);
    }

    private void a(Context context) {
        HMSBIInitializer.getInstance(context).initBI();
    }

    public <TResult, TClient extends AnyClient> f<TResult> doWrite(TaskApiCall<TClient, TResult> taskApiCall) {
        this.m = true;
        if (taskApiCall == null) {
            HMSLog.e("HuaweiApi", "in doWrite:taskApiCall is null");
            g gVar = new g();
            gVar.a((Exception) new ApiException(Status.FAILURE));
            return gVar.a();
        }
        b.a(this.b, taskApiCall.getUri(), TextUtils.isEmpty(this.i.getSubAppID()) ? this.g : this.i.getSubAppID(), taskApiCall.getTransactionId(), String.valueOf(getKitSdkVersion()));
        return a(taskApiCall);
    }

    public int getApiLevel() {
        return this.l;
    }

    public void setApiLevel(int i) {
        this.l = i;
    }

    public ConnectionManagerKey<TOption> getConnectionManagerKey() {
        return this.c;
    }

    public f<Boolean> disconnectService() {
        g<Boolean> gVar = new g<>();
        this.a.disconnectService(this, gVar);
        return gVar.a();
    }

    private <TResult, TClient extends AnyClient> f<TResult> a(TaskApiCall<TClient, TResult> taskApiCall) {
        g<TResult> gVar;
        if (taskApiCall.getToken() == null) {
            gVar = new g<>();
        } else {
            gVar = new g<>(taskApiCall.getToken());
        }
        this.a.sendRequest(this, taskApiCall, gVar);
        return gVar.a();
    }

    public AnyClient getClient(Looper looper, HuaweiApiManager.ConnectionManager connectionManager) {
        return this.e.buildClient(this.b, getClientSetting(), connectionManager, connectionManager);
    }

    protected ClientSettings getClientSetting() {
        ClientSettings clientSettings = new ClientSettings(this.b.getPackageName(), this.b.getClass().getName(), getScopes(), this.f, null, this.i);
        clientSettings.setCpID(this.h);
        if (this.j != null) {
            clientSettings.setCpActivity(this.j.get());
        }
        return clientSettings;
    }

    protected List<Scope> getScopes() {
        return Collections.emptyList();
    }

    @Deprecated
    public boolean setSubAppInfo(SubAppInfo subAppInfo) {
        HMSLog.i("HuaweiApi", "Enter setSubAppInfo");
        if (this.i != null && !TextUtils.isEmpty(this.i.getSubAppID())) {
            HMSLog.e("HuaweiApi", "subAppInfo is already set");
            return false;
        }
        if (subAppInfo == null) {
            HMSLog.e("HuaweiApi", "subAppInfo is null");
            return false;
        }
        String subAppID = subAppInfo.getSubAppID();
        if (TextUtils.isEmpty(subAppID)) {
            HMSLog.e("HuaweiApi", "subAppId is empty");
            return false;
        }
        if (subAppID.equals(this.f)) {
            HMSLog.e("HuaweiApi", "subAppId is host appid");
            return false;
        }
        if (this.m) {
            HMSLog.e("HuaweiApi", "Client has sent request to Huawei Mobile Services, setting subAppId is not allowed");
            return false;
        }
        this.i = new SubAppInfo(subAppInfo);
        return true;
    }

    public void setSubAppId(String str) throws ApiException {
        if (!setSubAppInfo(new SubAppInfo(str))) {
            throw new ApiException(Status.FAILURE);
        }
    }

    public String getAppID() {
        return this.g;
    }

    public String getSubAppID() {
        return this.i.getSubAppID();
    }

    public Context getContext() {
        return this.b;
    }

    public TOption getOption() {
        return this.d;
    }

    public int getKitSdkVersion() {
        return this.k;
    }

    public void setKitSdkVersion(int i) {
        this.k = i;
    }
}
