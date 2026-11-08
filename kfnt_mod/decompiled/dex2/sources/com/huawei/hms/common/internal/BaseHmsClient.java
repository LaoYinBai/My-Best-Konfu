package com.huawei.hms.common.internal;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import com.huawei.hms.adapter.AvailableAdapter;
import com.huawei.hms.adapter.BinderAdapter;
import com.huawei.hms.api.ConnectionResult;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.api.IPCTransport;
import com.huawei.hms.core.aidl.d;
import com.huawei.hms.support.api.client.AidlApiClient;
import com.huawei.hms.support.api.client.SubAppInfo;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hms.utils.Util;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class BaseHmsClient implements AidlApiClient {
    private static final Object a = new Object();
    private final Context c;
    private String d;
    private final ClientSettings e;
    private volatile d f;
    private final ConnectionCallbacks h;
    private final OnConnectionFailedListener i;
    private BinderAdapter k;
    protected String sessionId;
    private int b = 0;
    private final AtomicInteger g = new AtomicInteger(1);
    private Handler j = null;

    public interface ConnectionCallbacks {
        public static final int CAUSE_API_CLIENT_EXPIRED = 3;
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;

        void onConnected();

        void onConnectionSuspended(int i);
    }

    public interface OnConnectionFailedListener {
        void onConnectionFailed(ConnectionResult connectionResult);
    }

    @Deprecated
    public int getMinApkVersion() {
        return 30000000;
    }

    public String getServiceAction() {
        return "com.huawei.hms.core.aidlservice";
    }

    public BaseHmsClient(Context context, ClientSettings clientSettings, OnConnectionFailedListener onConnectionFailedListener, ConnectionCallbacks connectionCallbacks) {
        this.c = context;
        this.e = clientSettings;
        this.d = this.e.getAppID();
        this.i = onConnectionFailedListener;
        this.h = connectionCallbacks;
    }

    public void onConnecting() {
        connectionConnected();
    }

    protected final void connectionConnected() {
        a(3);
        if (this.h != null) {
            this.h.onConnected();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        HMSLog.i("BaseHmsClient", "enter bindCoreService");
        this.k = new BinderAdapter(this.c, getServiceAction(), HMSPackageManager.getInstance(this.c).getHMSPackageName());
        this.k.binder(new BinderAdapter.BinderCallBack() { // from class: com.huawei.hms.common.internal.BaseHmsClient.1
            @Override // com.huawei.hms.adapter.BinderAdapter.BinderCallBack
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                HMSLog.i("BaseHmsClient", "Enter onServiceConnected.");
                BaseHmsClient.this.f = d.a.a(iBinder);
                if (BaseHmsClient.this.f == null) {
                    HMSLog.e("BaseHmsClient", "Failed to get service as interface, trying to unbind.");
                    BaseHmsClient.this.k.unBind();
                    BaseHmsClient.this.a(1);
                    BaseHmsClient.this.b(10);
                    return;
                }
                BaseHmsClient.this.onConnecting();
            }

            @Override // com.huawei.hms.adapter.BinderAdapter.BinderCallBack
            public void onBinderFailed(int i) {
                onBinderFailed(i, null);
            }

            @Override // com.huawei.hms.adapter.BinderAdapter.BinderCallBack
            public void onBinderFailed(int i, PendingIntent pendingIntent) {
                BaseHmsClient.this.a(new ConnectionResult(10, pendingIntent));
                BaseHmsClient.this.f = null;
            }

            @Override // com.huawei.hms.adapter.BinderAdapter.BinderCallBack
            public void onServiceDisconnected(ComponentName componentName) {
                HMSLog.i("BaseHmsClient", "Enter onServiceDisconnected.");
                BaseHmsClient.this.a(1);
                if (BaseHmsClient.this.h != null) {
                    BaseHmsClient.this.h.onConnectionSuspended(1);
                }
            }
        });
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public String getAppID() {
        return this.d;
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public boolean isConnected() {
        return this.g.get() == 3 || this.g.get() == 4;
    }

    public boolean isConnecting() {
        return this.g.get() == 5;
    }

    public void disconnect() {
        int i = this.g.get();
        HMSLog.i("BaseHmsClient", "Enter disconnect, Connection Status: " + i);
        if (i != 1) {
            switch (i) {
                case 3:
                    if (this.k != null) {
                        this.k.unBind();
                    }
                    a(1);
                    break;
                case 5:
                    b();
                    a(4);
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        this.g.set(i);
    }

    private void b() {
        synchronized (a) {
            if (this.j != null) {
                this.j.removeMessages(2);
                this.j = null;
            }
        }
    }

    public void checkAvailabilityAndConnect(int i) {
        HMSLog.i("BaseHmsClient", "====== HMSSDK version: 40004300 ======");
        int i2 = this.g.get();
        HMSLog.i("BaseHmsClient", "Enter connect, Connection Status: " + i2);
        if (i2 == 3 || i2 == 5 || i2 == 4) {
            return;
        }
        a(5);
        if (getMinApkVersion() > i) {
            i = getMinApkVersion();
        }
        HMSLog.i("BaseHmsClient", "connect minVersion:" + i);
        if (Util.isAvailableLibExist(this.c)) {
            AvailableAdapter availableAdapter = new AvailableAdapter(i);
            int iIsHuaweiMobileServicesAvailable = availableAdapter.isHuaweiMobileServicesAvailable(this.c);
            HMSLog.i("BaseHmsClient", "check available result: " + iIsHuaweiMobileServicesAvailable);
            if (iIsHuaweiMobileServicesAvailable == 0) {
                a();
                return;
            }
            if (availableAdapter.isUserResolvableError(iIsHuaweiMobileServicesAvailable)) {
                HMSLog.i("BaseHmsClient", "bindCoreService3.0 fail, start resolution now.");
                a(availableAdapter);
                return;
            }
            HMSLog.i("BaseHmsClient", "bindCoreService3.0 fail: " + iIsHuaweiMobileServicesAvailable + " is not resolvable.");
            b(iIsHuaweiMobileServicesAvailable);
            return;
        }
        int iIsHuaweiMobileServicesAvailable2 = HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(this.c, i);
        HMSLog.i("BaseHmsClient", "HuaweiApiAvailability check available result: " + iIsHuaweiMobileServicesAvailable2);
        if (iIsHuaweiMobileServicesAvailable2 == 0) {
            a();
        } else {
            b(iIsHuaweiMobileServicesAvailable2);
        }
    }

    private void a(AvailableAdapter availableAdapter) {
        HMSLog.i("BaseHmsClient", "enter HmsCore resolution");
        if (!getClientSettings().isHasActivity()) {
            b(26);
            return;
        }
        Activity activeActivity = Util.getActiveActivity(getClientSettings().getCpActivity(), getContext());
        if (activeActivity != null) {
            availableAdapter.startResolution(activeActivity, new AvailableAdapter.AvailableCallBack() { // from class: com.huawei.hms.common.internal.BaseHmsClient.2
                @Override // com.huawei.hms.adapter.AvailableAdapter.AvailableCallBack
                public void onComplete(int i) {
                    if (i == 0) {
                        BaseHmsClient.this.a();
                    } else {
                        BaseHmsClient.this.b(i);
                    }
                }
            });
        } else {
            b(26);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        HMSLog.i("BaseHmsClient", "notifyFailed result: " + i);
        if (this.i != null) {
            this.i.onConnectionFailed(new ConnectionResult(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ConnectionResult connectionResult) {
        HMSLog.i("BaseHmsClient", "notifyFailed result: " + connectionResult.getErrorCode());
        if (this.i != null) {
            this.i.onConnectionFailed(connectionResult);
        }
    }

    public void connect(int i) {
        checkAvailabilityAndConnect(i);
    }

    @Override // com.huawei.hms.support.api.client.AidlApiClient
    public d getService() {
        return this.f;
    }

    protected final void checkConnected() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public String getSessionId() {
        return this.sessionId;
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public Context getContext() {
        return this.c;
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public SubAppInfo getSubAppInfo() {
        return this.e.getSubAppID();
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public String getPackageName() {
        return this.e.getClientPackageName();
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public String getCpID() {
        return this.e.getCpID();
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public String getTransportName() {
        return IPCTransport.class.getName();
    }

    @Override // com.huawei.hms.support.api.client.AidlApiClient
    public List<String> getApiNameList() {
        return this.e.getApiName();
    }

    protected ClientSettings getClientSettings() {
        return this.e;
    }
}
