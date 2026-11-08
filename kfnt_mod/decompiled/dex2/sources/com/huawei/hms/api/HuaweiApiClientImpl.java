package com.huawei.hms.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.hms.api.Api;
import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hms.common.api.ConnectionPostProcessor;
import com.huawei.hms.common.internal.AutoLifecycleFragment;
import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.RequestHeader;
import com.huawei.hms.core.aidl.ResponseHeader;
import com.huawei.hms.core.aidl.c;
import com.huawei.hms.core.aidl.d;
import com.huawei.hms.core.aidl.e;
import com.huawei.hms.support.api.PendingResultImpl;
import com.huawei.hms.support.api.ResolveResult;
import com.huawei.hms.support.api.client.ApiClient;
import com.huawei.hms.support.api.client.BundleResult;
import com.huawei.hms.support.api.client.InnerApiClient;
import com.huawei.hms.support.api.client.PendingResult;
import com.huawei.hms.support.api.client.ResultCallback;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.client.SubAppInfo;
import com.huawei.hms.support.api.core.ConnectService;
import com.huawei.hms.support.api.entity.auth.PermissionInfo;
import com.huawei.hms.support.api.entity.auth.Scope;
import com.huawei.hms.support.api.entity.core.CheckConnectInfo;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.huawei.hms.support.api.entity.core.ConnectInfo;
import com.huawei.hms.support.api.entity.core.ConnectResp;
import com.huawei.hms.support.api.entity.core.DisconnectInfo;
import com.huawei.hms.support.api.entity.core.DisconnectResp;
import com.huawei.hms.support.api.entity.core.JosGetNoticeResp;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.Checker;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hms.utils.PackageManagerHelper;
import com.huawei.hms.utils.Util;
import com.huawei.updatesdk.UpdateSdkAPI;
import com.huawei.updatesdk.service.appmgr.bean.ApkUpgradeInfo;
import com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack;
import com.huawei.updatesdk.service.otaupdate.UpdateKey;
import com.mbridge.msdk.playercommon.exoplayer2.DefaultRenderersFactory;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class HuaweiApiClientImpl extends HuaweiApiClient implements ServiceConnection, InnerApiClient {
    public static final String DEFAULT_ACCOUNT = "<<default account>>";
    public static final int SIGN_IN_MODE_OPTIONAL = 2;
    public static final int SIGN_IN_MODE_REQUIRED = 1;
    private static final Object a = new Object();
    private static final Object b = new Object();
    private CheckUpdateCallBack C;
    private final Context d;
    private final String e;
    private String f;
    private String g;
    private volatile com.huawei.hms.core.aidl.d h;
    private String i;
    private WeakReference<Activity> j;
    private WeakReference<Activity> k;
    private List<Scope> n;
    private List<PermissionInfo> o;
    private Map<Api<?>, Api.ApiOptions> p;
    private SubAppInfo q;
    private ConnectionResult w;
    private HuaweiApiClient.ConnectionCallbacks x;
    private HuaweiApiClient.OnConnectionFailedListener y;
    private int c = -1;
    private boolean l = false;
    private AtomicInteger m = new AtomicInteger(1);
    private long r = 0;
    private int s = 0;
    private final Object t = new Object();
    private final ReentrantLock u = new ReentrantLock();
    private final Condition v = this.u.newCondition();
    private Handler z = null;
    private Handler A = null;
    private CheckUpdatelistener B = null;

    @Override // com.huawei.hms.api.HuaweiApiClient
    public void print(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    private void a() {
        this.C = new CheckUpdateCallBack() { // from class: com.huawei.hms.api.HuaweiApiClientImpl.1
            @Override // com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack
            public void onUpdateInfo(Intent intent) {
                if (intent == null || HuaweiApiClientImpl.this.B == null) {
                    return;
                }
                try {
                    int intExtra = intent.getIntExtra("status", -99);
                    HMSLog.i("HuaweiApiClientImpl", "onUpdateInfo status: " + intExtra + ",failcause: " + intent.getIntExtra(UpdateKey.FAIL_CODE, -99) + ",isExit: " + intent.getBooleanExtra(UpdateKey.MUST_UPDATE, false));
                    if (intExtra == 7) {
                        ApkUpgradeInfo apkUpgradeInfo = (ApkUpgradeInfo) intent.getSerializableExtra(UpdateKey.INFO);
                        if (apkUpgradeInfo != null) {
                            HMSLog.i("HuaweiApiClientImpl", "onUpdateInfo: " + apkUpgradeInfo.toString());
                        }
                        HuaweiApiClientImpl.this.B.onResult(1);
                    } else if (intExtra == 3) {
                        HuaweiApiClientImpl.this.B.onResult(0);
                    } else {
                        HuaweiApiClientImpl.this.B.onResult(-1);
                    }
                    HuaweiApiClientImpl.this.B = null;
                } catch (Exception e) {
                    HMSLog.e("HuaweiApiClientImpl", "intent has some error" + e.getMessage());
                    HuaweiApiClientImpl.this.B.onResult(-1);
                }
            }

            @Override // com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack
            public void onMarketInstallInfo(Intent intent) {
                if (intent != null) {
                    int intExtra = intent.getIntExtra(UpdateKey.MARKET_DLD_STATUS, -99);
                    HMSLog.i("HuaweiApiClientImpl", "onMarketInstallInfo installState: " + intent.getIntExtra(UpdateKey.MARKET_INSTALL_STATE, -99) + ",installType: " + intent.getIntExtra(UpdateKey.MARKET_INSTALL_TYPE, -99) + ",downloadCode: " + intExtra);
                }
            }

            @Override // com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack
            public void onMarketStoreError(int i) {
                HMSLog.e("HuaweiApiClientImpl", "onMarketStoreError responseCode: " + i);
            }

            @Override // com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack
            public void onUpdateStoreError(int i) {
                HMSLog.e("HuaweiApiClientImpl", "onUpdateStoreError responseCode: " + i);
            }
        };
    }

    public HuaweiApiClientImpl(Context context) {
        this.d = context;
        this.e = Util.getAppId(context);
        this.f = this.e;
        this.g = Util.getCpId(context);
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public Context getContext() {
        return this.d;
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public String getPackageName() {
        return this.d.getPackageName();
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public String getAppID() {
        return this.f;
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public String getCpID() {
        return this.g;
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public String getTransportName() {
        return IPCTransport.class.getName();
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public final SubAppInfo getSubAppInfo() {
        return this.q;
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public List<Scope> getScopes() {
        return this.n;
    }

    public void setScopes(List<Scope> list) {
        this.n = list;
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public List<PermissionInfo> getPermissionInfos() {
        return this.o;
    }

    public void setPermissionInfos(List<PermissionInfo> list) {
        this.o = list;
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public Map<Api<?>, Api.ApiOptions> getApiMap() {
        return this.p;
    }

    public void setApiMap(Map<Api<?>, Api.ApiOptions> map) {
        this.p = map;
    }

    @Override // com.huawei.hms.support.api.client.AidlApiClient
    public com.huawei.hms.core.aidl.d getService() {
        return this.h;
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public String getSessionId() {
        return this.i;
    }

    public void setHasShowNotice(boolean z) {
        this.l = z;
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public void connect(Activity activity) {
        HMSLog.i("HuaweiApiClientImpl", "====== HMSSDK version: 40004300 ======");
        int i = this.m.get();
        HMSLog.i("HuaweiApiClientImpl", "Enter connect, Connection Status: " + i);
        if (i == 3 || i == 5 || i == 2 || i == 4) {
            return;
        }
        if (activity != null) {
            this.j = new WeakReference<>(activity);
            this.k = new WeakReference<>(activity);
        }
        this.f = TextUtils.isEmpty(this.e) ? Util.getAppId(this.d) : this.e;
        int iD = d();
        HMSLog.i("HuaweiApiClientImpl", "connect minVersion:" + iD);
        HuaweiApiAvailability.setServicesVersionCode(iD);
        int iIsHuaweiMobileServicesAvailable = HuaweiMobileServicesUtil.isHuaweiMobileServicesAvailable(this.d, iD);
        HMSLog.i("HuaweiApiClientImpl", "In connect, isHuaweiMobileServicesAvailable result: " + iIsHuaweiMobileServicesAvailable);
        this.s = HMSPackageManager.getInstance(this.d).getHmsVersionCode();
        if (iIsHuaweiMobileServicesAvailable == 0) {
            a(5);
            if (this.h == null) {
                g();
                return;
            }
            a(2);
            l();
            i();
            return;
        }
        if (this.y != null) {
            PendingIntent resolveErrorPendingIntent = null;
            if (this.j != null && this.j.get() != null) {
                resolveErrorPendingIntent = HuaweiApiAvailability.getInstance().getResolveErrorPendingIntent(this.j.get(), iIsHuaweiMobileServicesAvailable);
                HMSLog.i("HuaweiApiClientImpl", "connect 2.0 fail: " + iIsHuaweiMobileServicesAvailable);
            }
            ConnectionResult connectionResult = new ConnectionResult(iIsHuaweiMobileServicesAvailable, resolveErrorPendingIntent);
            this.y.onConnectionFailed(connectionResult);
            this.w = connectionResult;
        }
    }

    private void b() {
        HMSLog.i("HuaweiApiClientImpl", "Enter sendForceConnectApiServceRequest.");
        ConnectService.forceConnect(this, m()).setResultCallback(new a());
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public void connectForeground() {
        HMSLog.i("HuaweiApiClientImpl", "====== HMSSDK version: 40004300 ======");
        int i = this.m.get();
        HMSLog.i("HuaweiApiClientImpl", "Enter forceConnect, Connection Status: " + i);
        if (i == 3 || i == 5 || i == 2 || i == 4) {
            return;
        }
        this.f = TextUtils.isEmpty(this.e) ? Util.getAppId(this.d) : this.e;
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.y != null) {
            int i = Util.isBackground(this.d) ? 7 : 6;
            PendingIntent resolveErrorPendingIntent = null;
            if (this.j != null && this.j.get() != null) {
                resolveErrorPendingIntent = HuaweiApiAvailability.getInstance().getResolveErrorPendingIntent(this.j.get(), i);
            }
            ConnectionResult connectionResult = new ConnectionResult(i, resolveErrorPendingIntent);
            this.y.onConnectionFailed(connectionResult);
            this.w = connectionResult;
        }
    }

    private int d() {
        int hmsVersion = Util.getHmsVersion(this.d);
        if (hmsVersion != 0 && hmsVersion >= 20503000) {
            return hmsVersion;
        }
        int iE = e();
        if (f()) {
            if (iE < 20503000) {
                return 20503000;
            }
            return iE;
        }
        if (iE < 20600000) {
            return 20600000;
        }
        return iE;
    }

    private int e() {
        Integer num;
        int iIntValue;
        Map<Api<?>, Api.ApiOptions> apiMap = getApiMap();
        int i = 0;
        if (apiMap == null) {
            return 0;
        }
        Iterator<Api<?>> it = apiMap.keySet().iterator();
        while (it.hasNext()) {
            String apiName = it.next().getApiName();
            if (!TextUtils.isEmpty(apiName) && (num = HuaweiApiAvailability.getApiMap().get(apiName)) != null && (iIntValue = num.intValue()) > i) {
                i = iIntValue;
            }
        }
        return i;
    }

    private boolean f() {
        if (this.p == null) {
            return false;
        }
        Iterator<Api<?>> it = this.p.keySet().iterator();
        while (it.hasNext()) {
            if (HuaweiApiAvailability.HMS_API_NAME_GAME.equals(it.next().getApiName())) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        this.m.set(i);
        if (i == 1 || i == 3 || i == 2) {
            this.u.lock();
            try {
                this.v.signalAll();
            } finally {
                this.u.unlock();
            }
        }
    }

    private void g() {
        Intent intent = new Intent("com.huawei.hms.core.aidlservice");
        intent.setPackage(HMSPackageManager.getInstance(this.d).getHMSPackageName());
        synchronized (a) {
            if (this.d.bindService(intent, this, 1)) {
                h();
                return;
            }
            a(1);
            HMSLog.e("HuaweiApiClientImpl", "In connect, bind core service fail");
            c();
        }
    }

    private void h() {
        if (this.z != null) {
            this.z.removeMessages(2);
        } else {
            this.z = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.huawei.hms.api.HuaweiApiClientImpl.2
                @Override // android.os.Handler.Callback
                public boolean handleMessage(Message message) {
                    if (message == null || message.what != 2) {
                        return false;
                    }
                    HMSLog.e("HuaweiApiClientImpl", "In connect, bind core service time out");
                    if (HuaweiApiClientImpl.this.m.get() == 5) {
                        HuaweiApiClientImpl.this.a(1);
                        HuaweiApiClientImpl.this.c();
                    }
                    return true;
                }
            });
        }
        this.z.sendEmptyMessageDelayed(2, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
    }

    private void i() {
        synchronized (b) {
            if (this.A != null) {
                this.A.removeMessages(3);
            } else {
                this.A = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.huawei.hms.api.HuaweiApiClientImpl.3
                    @Override // android.os.Handler.Callback
                    public boolean handleMessage(Message message) {
                        if (message == null || message.what != 3) {
                            return false;
                        }
                        HMSLog.e("HuaweiApiClientImpl", "In connect, process time out");
                        if (HuaweiApiClientImpl.this.m.get() == 2) {
                            HuaweiApiClientImpl.this.a(1);
                        }
                        return true;
                    }
                });
            }
            HMSLog.d("HuaweiApiClientImpl", "sendEmptyMessageDelayed for onConnectionResult 3 seconds. the result is : " + this.A.sendEmptyMessageDelayed(3, 3000L));
        }
    }

    private void b(int i) {
        if (i == 2) {
            synchronized (a) {
                if (this.z != null) {
                    this.z.removeMessages(i);
                    this.z = null;
                }
            }
        }
        if (i == 3) {
            synchronized (b) {
                if (this.A != null) {
                    this.A.removeMessages(i);
                    this.A = null;
                }
            }
        }
        synchronized (a) {
            if (this.z != null) {
                this.z.removeMessages(2);
                this.z = null;
            }
        }
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public void disconnect() {
        int i = this.m.get();
        HMSLog.i("HuaweiApiClientImpl", "Enter disconnect, Connection Status: " + i);
        switch (i) {
            case 2:
                a(4);
                break;
            case 3:
                a(4);
                j();
                break;
            case 5:
                b(2);
                a(4);
                break;
        }
    }

    @Override // com.huawei.hms.api.HuaweiApiClient, com.huawei.hms.support.api.client.ApiClient
    public boolean isConnected() {
        if (this.s == 0) {
            this.s = HMSPackageManager.getInstance(this.d).getHmsVersionCode();
        }
        if (this.s < 20504000) {
            long jCurrentTimeMillis = System.currentTimeMillis() - this.r;
            if (jCurrentTimeMillis > 0 && jCurrentTimeMillis < 300000) {
                return innerIsConnected();
            }
            if (!innerIsConnected()) {
                return false;
            }
            Status status = ((ResolveResult) ConnectService.checkconnect(this, new CheckConnectInfo()).awaitOnAnyThread(2000L, TimeUnit.MILLISECONDS)).getStatus();
            if (status.isSuccess()) {
                this.r = System.currentTimeMillis();
                return true;
            }
            int statusCode = status.getStatusCode();
            HMSLog.e("HuaweiApiClientImpl", "isConnected is false, statuscode:" + statusCode);
            if (statusCode == 907135004) {
                return false;
            }
            n();
            a(1);
            this.r = System.currentTimeMillis();
            return false;
        }
        return innerIsConnected();
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public boolean isConnecting() {
        int i = this.m.get();
        return i == 2 || i == 5;
    }

    @Override // com.huawei.hms.support.api.client.InnerApiClient
    public boolean innerIsConnected() {
        return this.m.get() == 3 || this.m.get() == 4;
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public boolean setSubAppInfo(SubAppInfo subAppInfo) {
        HMSLog.i("HuaweiApiClientImpl", "Enter setSubAppInfo");
        if (subAppInfo == null) {
            HMSLog.e("HuaweiApiClientImpl", "subAppInfo is null");
            return false;
        }
        String subAppID = subAppInfo.getSubAppID();
        if (TextUtils.isEmpty(subAppID)) {
            HMSLog.e("HuaweiApiClientImpl", "subAppId is empty");
            return false;
        }
        if (subAppID.equals(TextUtils.isEmpty(this.e) ? Util.getAppId(this.d) : this.e)) {
            HMSLog.e("HuaweiApiClientImpl", "subAppId is host appid");
            return false;
        }
        this.q = new SubAppInfo(subAppInfo);
        return true;
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public void checkUpdate(Activity activity, CheckUpdatelistener checkUpdatelistener) {
        if (Util.isAvailableLibExist(this.d)) {
            HMSLog.i("HuaweiApiClientImpl", "Enter checkUpdate");
            if (checkUpdatelistener == null) {
                HMSLog.e("HuaweiApiClientImpl", "listener is null!");
                return;
            }
            if (activity == null || activity.isFinishing()) {
                HMSLog.e("HuaweiApiClientImpl", "checkUpdate, activity is illegal: " + activity);
                checkUpdatelistener.onResult(-1);
                return;
            }
            this.B = checkUpdatelistener;
            if (this.C == null) {
                a();
            }
            UpdateSdkAPI.checkClientOTAUpdate(activity, this.C, true, 0, true);
            return;
        }
        HMSLog.e("HuaweiApiClientImpl", "available lib does not exist.");
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public void onResume(Activity activity) {
        if (activity != null) {
            HMSLog.i("HuaweiApiClientImpl", "onResume");
            this.k = new WeakReference<>(activity);
        }
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public void onPause(Activity activity) {
        HMSLog.i("HuaweiApiClientImpl", "onPause");
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public Activity getTopActivity() {
        if (this.k == null) {
            return null;
        }
        return this.k.get();
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public void setConnectionCallbacks(HuaweiApiClient.ConnectionCallbacks connectionCallbacks) {
        this.x = connectionCallbacks;
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public void setConnectionFailedListener(HuaweiApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.y = onConnectionFailedListener;
    }

    private void j() {
        ConnectService.disconnect(this, k()).setResultCallback(new b());
    }

    private DisconnectInfo k() {
        ArrayList arrayList = new ArrayList();
        if (this.p != null) {
            Iterator<Api<?>> it = this.p.keySet().iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getApiName());
            }
        }
        return new DisconnectInfo(this.n, arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ResolveResult<DisconnectResp> resolveResult) {
        HMSLog.i("HuaweiApiClientImpl", "Enter onDisconnectionResult, disconnect from server result: " + resolveResult.getStatus().getStatusCode());
        n();
        a(1);
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        HMSLog.i("HuaweiApiClientImpl", "Enter onServiceConnected.");
        b(2);
        this.h = d.a.a(iBinder);
        if (this.h == null) {
            HMSLog.e("HuaweiApiClientImpl", "In onServiceConnected, mCoreService must not be null.");
            n();
            a(1);
            if (this.y != null) {
                PendingIntent resolveErrorPendingIntent = null;
                if (this.j != null && this.j.get() != null) {
                    resolveErrorPendingIntent = HuaweiApiAvailability.getInstance().getResolveErrorPendingIntent(this.j.get(), 10);
                }
                ConnectionResult connectionResult = new ConnectionResult(10, resolveErrorPendingIntent);
                this.y.onConnectionFailed(connectionResult);
                this.w = connectionResult;
                return;
            }
            return;
        }
        if (this.m.get() == 5) {
            a(2);
            l();
            i();
        } else if (this.m.get() != 3) {
            n();
        }
    }

    private void l() {
        HMSLog.i("HuaweiApiClientImpl", "Enter sendConnectApiServceRequest.");
        ConnectService.connect(this, m()).setResultCallback(new a());
    }

    private ConnectInfo m() throws PackageManager.NameNotFoundException {
        String packageSignature = new PackageManagerHelper(this.d).getPackageSignature(this.d.getPackageName());
        if (packageSignature == null) {
            packageSignature = "";
        }
        return new ConnectInfo(getApiNameList(), this.n, packageSignature, this.q == null ? null : this.q.getSubAppID());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(ResolveResult<ConnectResp> resolveResult) {
        HMSLog.i("HuaweiApiClientImpl", "Enter onConnectionResult");
        b(3);
        ConnectResp value = resolveResult.getValue();
        if (value != null) {
            this.i = value.sessionId;
        }
        PendingIntent resolveErrorPendingIntent = null;
        String subAppID = this.q == null ? null : this.q.getSubAppID();
        if (!TextUtils.isEmpty(subAppID)) {
            this.f = subAppID;
        }
        int statusCode = resolveResult.getStatus().getStatusCode();
        HMSLog.i("HuaweiApiClientImpl", "Enter onConnectionResult, connect to server result: " + statusCode);
        if (Status.SUCCESS.equals(resolveResult.getStatus())) {
            if (resolveResult.getValue() != null) {
                ProtocolNegotiate.getInstance().negotiate(resolveResult.getValue().protocolVersion);
            }
            a(3);
            this.w = null;
            if (this.x != null) {
                this.x.onConnected();
            }
            if (this.j != null) {
                o();
            }
            for (Map.Entry<Api<?>, Api.ApiOptions> entry : getApiMap().entrySet()) {
                if (entry.getKey().getmConnetctPostList() != null && !entry.getKey().getmConnetctPostList().isEmpty()) {
                    HMSLog.i("HuaweiApiClientImpl", "Enter onConnectionResult, get the ConnetctPostList ");
                    for (ConnectionPostProcessor connectionPostProcessor : entry.getKey().getmConnetctPostList()) {
                        HMSLog.i("HuaweiApiClientImpl", "Enter onConnectionResult, processor.run");
                        connectionPostProcessor.run(this, this.j);
                    }
                }
            }
            return;
        }
        if (resolveResult.getStatus() != null && resolveResult.getStatus().getStatusCode() == 1001) {
            n();
            a(1);
            if (this.x != null) {
                this.x.onConnectionSuspended(3);
                return;
            }
            return;
        }
        n();
        a(1);
        if (this.y != null) {
            if (this.j != null && this.j.get() != null) {
                resolveErrorPendingIntent = HuaweiApiAvailability.getInstance().getResolveErrorPendingIntent(this.j.get(), statusCode);
            }
            ConnectionResult connectionResult = new ConnectionResult(statusCode, resolveErrorPendingIntent);
            this.y.onConnectionFailed(connectionResult);
            this.w = connectionResult;
        }
    }

    private void n() {
        Util.unBindServiceCatchException(this.d, this);
        this.h = null;
    }

    private void o() {
        if (this.l) {
            HMSLog.i("HuaweiApiClientImpl", "Connect notice has been shown.");
        } else if (HuaweiApiAvailability.getInstance().isHuaweiMobileNoticeAvailable(this.d) == 0) {
            ConnectService.getNotice(this, 0, "4.0.4.300").setResultCallback(new c());
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        HMSLog.i("HuaweiApiClientImpl", "Enter onServiceDisconnected.");
        this.h = null;
        a(1);
        if (this.x != null) {
            this.x.onConnectionSuspended(1);
        }
    }

    public int asyncRequest(Bundle bundle, String str, int i, final ResultCallback<BundleResult> resultCallback) {
        HMSLog.i("HuaweiApiClientImpl", "Enter asyncRequest.");
        if (resultCallback == null || str == null || bundle == null) {
            HMSLog.e("HuaweiApiClientImpl", "arguments is invalid.");
            return CommonCode.ErrorCode.ARGUMENTS_INVALID;
        }
        if (!innerIsConnected()) {
            HMSLog.e("HuaweiApiClientImpl", "client is unConnect.");
            return CommonCode.ErrorCode.CLIENT_API_INVALID;
        }
        com.huawei.hms.core.aidl.b bVar = new com.huawei.hms.core.aidl.b(str, i);
        e eVarA = com.huawei.hms.core.aidl.a.a(bVar.c());
        bVar.a(bundle);
        RequestHeader requestHeader = new RequestHeader(getAppID(), getPackageName(), 40004300, getSessionId());
        requestHeader.setApiNameList(getApiNameList());
        bVar.b = eVarA.a(requestHeader, new Bundle());
        try {
            getService().a(bVar, new c.a() { // from class: com.huawei.hms.api.HuaweiApiClientImpl.4
                @Override // com.huawei.hms.core.aidl.c
                public void call(com.huawei.hms.core.aidl.b bVar2) {
                    if (bVar2 != null) {
                        e eVarA2 = com.huawei.hms.core.aidl.a.a(bVar2.c());
                        ResponseHeader responseHeader = new ResponseHeader();
                        eVarA2.a(bVar2.b, responseHeader);
                        BundleResult bundleResult = new BundleResult(responseHeader.getStatusCode(), bVar2.a());
                        HMSLog.i("HuaweiApiClientImpl", "Exit asyncRequest onResult");
                        resultCallback.onResult(bundleResult);
                        return;
                    }
                    HMSLog.i("HuaweiApiClientImpl", "Exit asyncRequest onResult -1");
                    resultCallback.onResult(new BundleResult(-1, null));
                }
            });
            return 0;
        } catch (RemoteException e) {
            HMSLog.e("HuaweiApiClientImpl", "remote exception:" + e.getMessage());
            return CommonCode.ErrorCode.INTERNAL_ERROR;
        }
    }

    @Override // com.huawei.hms.support.api.client.AidlApiClient
    public List<String> getApiNameList() {
        ArrayList arrayList = new ArrayList();
        if (this.p != null) {
            Iterator<Api<?>> it = this.p.keySet().iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getApiName());
            }
        }
        return arrayList;
    }

    private class b implements ResultCallback<ResolveResult<DisconnectResp>> {
        private b() {
        }

        @Override // com.huawei.hms.support.api.client.ResultCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResult(final ResolveResult<DisconnectResp> resolveResult) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.hms.api.HuaweiApiClientImpl.b.1
                @Override // java.lang.Runnable
                public void run() {
                    HuaweiApiClientImpl.this.a((ResolveResult<DisconnectResp>) resolveResult);
                }
            });
        }
    }

    private class a implements ResultCallback<ResolveResult<ConnectResp>> {
        private a() {
        }

        @Override // com.huawei.hms.support.api.client.ResultCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResult(final ResolveResult<ConnectResp> resolveResult) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.hms.api.HuaweiApiClientImpl.a.1
                @Override // java.lang.Runnable
                public void run() {
                    HuaweiApiClientImpl.this.b((ResolveResult<ConnectResp>) resolveResult);
                }
            });
        }
    }

    private class c implements ResultCallback<ResolveResult<JosGetNoticeResp>> {
        private c() {
        }

        @Override // com.huawei.hms.support.api.client.ResultCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResult(ResolveResult<JosGetNoticeResp> resolveResult) {
            JosGetNoticeResp value;
            Intent noticeIntent;
            if (resolveResult == null || !resolveResult.getStatus().isSuccess() || (noticeIntent = (value = resolveResult.getValue()).getNoticeIntent()) == null || value.getStatusCode() != 0) {
                return;
            }
            HMSLog.i("HuaweiApiClientImpl", "get notice has intent.");
            Activity validActivity = Util.getValidActivity((Activity) HuaweiApiClientImpl.this.j.get(), HuaweiApiClientImpl.this.getTopActivity());
            if (validActivity != null) {
                HuaweiApiClientImpl.this.l = true;
                validActivity.startActivity(noticeIntent);
            } else {
                HMSLog.e("HuaweiApiClientImpl", "showNotice no valid activity!");
            }
        }
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public boolean hasConnectionSuccessListener(HuaweiApiClient.ConnectionCallbacks connectionCallbacks) {
        Checker.checkNonNull(connectionCallbacks, "connectionCallbacksListener should not be null");
        synchronized (this.t) {
            return this.x == connectionCallbacks;
        }
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public boolean hasConnectionFailureListener(HuaweiApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Checker.checkNonNull(onConnectionFailedListener, "onConnectionFailedListener should not be null");
        synchronized (this.t) {
            return this.y == onConnectionFailedListener;
        }
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public void removeConnectionSuccessListener(HuaweiApiClient.ConnectionCallbacks connectionCallbacks) {
        Checker.checkNonNull(connectionCallbacks, "connectionCallbacksListener should not be null");
        synchronized (this.t) {
            if (this.x != connectionCallbacks) {
                HMSLog.w("HuaweiApiClientImpl", "unregisterConnectionCallback: this connectionCallbacksListener has not been registered");
            } else {
                this.x = null;
            }
        }
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public void removeConnectionFailureListener(HuaweiApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Checker.checkNonNull(onConnectionFailedListener, "onConnectionFailedListener should not be null");
        synchronized (this.t) {
            if (this.y != onConnectionFailedListener) {
                HMSLog.w("HuaweiApiClientImpl", "unregisterConnectionFailedListener: this onConnectionFailedListener has not been registered");
            } else {
                this.y = null;
            }
        }
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public ConnectionResult holdUpConnect() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("blockingConnect must not be called on the UI thread");
        }
        this.u.lock();
        try {
            connect((Activity) null);
            while (isConnecting()) {
                this.v.await();
            }
            if (!isConnected()) {
                return this.w != null ? this.w : new ConnectionResult(13, (PendingIntent) null);
            }
            this.w = null;
            return new ConnectionResult(0, (PendingIntent) null);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            return new ConnectionResult(15, (PendingIntent) null);
        } finally {
            this.u.unlock();
        }
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public ConnectionResult holdUpConnect(long j, TimeUnit timeUnit) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("blockingConnect must not be called on the UI thread");
        }
        this.u.lock();
        try {
            connect((Activity) null);
            long nanos = timeUnit.toNanos(j);
            while (isConnecting()) {
                if (nanos <= 0) {
                    disconnect();
                    return new ConnectionResult(14, (PendingIntent) null);
                }
                nanos = this.v.awaitNanos(nanos);
            }
            if (!isConnected()) {
                return this.w != null ? this.w : new ConnectionResult(13, (PendingIntent) null);
            }
            this.w = null;
            return new ConnectionResult(0, (PendingIntent) null);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            return new ConnectionResult(15, (PendingIntent) null);
        } finally {
            this.u.unlock();
        }
    }

    static class d extends PendingResultImpl<Status, IMessageEntity> {
        public d(ApiClient apiClient, String str, IMessageEntity iMessageEntity) {
            super(apiClient, str, iMessageEntity);
        }

        @Override // com.huawei.hms.support.api.PendingResultImpl
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Status onComplete(IMessageEntity iMessageEntity) {
            return new Status(0);
        }
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public PendingResult<Status> discardAndReconnect() {
        return new d(this, null, null);
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public ConnectionResult getConnectionResult(Api<?> api) {
        if (isConnected()) {
            this.w = null;
            return new ConnectionResult(0, (PendingIntent) null);
        }
        if (this.w != null) {
            return this.w;
        }
        return new ConnectionResult(13, (PendingIntent) null);
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public void reconnect() {
        disconnect();
        connect((Activity) null);
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public boolean hasConnectedApi(Api<?> api) {
        return isConnected();
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public void connect(int i) {
        connect((Activity) null);
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public void disableLifeCycleManagement(Activity activity) {
        if (this.c >= 0) {
            AutoLifecycleFragment autoLifecycleFragment = AutoLifecycleFragment.getInstance(activity);
            if (autoLifecycleFragment == null) {
                return;
            }
            autoLifecycleFragment.stopAutoManage(this.c);
            return;
        }
        throw new IllegalStateException("disableLifeCycleManagement failed");
    }

    protected void setAutoLifecycleClientId(int i) {
        this.c = i;
    }
}
