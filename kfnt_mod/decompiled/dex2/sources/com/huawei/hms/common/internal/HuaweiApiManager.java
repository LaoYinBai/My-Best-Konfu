package com.huawei.hms.common.internal;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.hmf.tasks.g;
import com.huawei.hms.api.Api;
import com.huawei.hms.api.ConnectionResult;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.common.internal.AnyClient;
import com.huawei.hms.common.internal.BaseHmsClient;
import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.Checker;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hms.utils.Util;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class HuaweiApiManager implements Handler.Callback {
    private static final Object a = new Object();
    private static HuaweiApiManager b;
    private final Handler c;
    private final AtomicInteger d = new AtomicInteger(0);
    private final Map<ConnectionManagerKey<?>, ConnectionManager<?>> e = new ConcurrentHashMap(5, 0.75f, 1);

    private HuaweiApiManager(Context context, Looper looper, HuaweiApiAvailability huaweiApiAvailability) {
        this.c = new Handler(looper, this);
    }

    public static HuaweiApiManager getInstance(Context context) {
        synchronized (a) {
            if (b == null) {
                HandlerThread handlerThread = new HandlerThread("HuaweiApiManager");
                handlerThread.start();
                b = new HuaweiApiManager(context.getApplicationContext(), handlerThread.getLooper(), HuaweiApiAvailability.getInstance());
            }
        }
        return b;
    }

    public final <TOption extends Api.ApiOptions, TResult> void sendRequest(HuaweiApi<TOption> huaweiApi, TaskApiCall<? extends AnyClient, TResult> taskApiCall, g<TResult> gVar) {
        this.c.sendMessage(this.c.obtainMessage(4, new b(new TaskApiCallWrapper(taskApiCall, gVar), this.d.getAndIncrement(), huaweiApi)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HuaweiApi<?> huaweiApi, g<Boolean> gVar) {
        ConnectionManager<?> connectionManager = this.e.get(huaweiApi.getConnectionManagerKey());
        if (connectionManager == null) {
            gVar.a((g<Boolean>) false);
        } else {
            gVar.a((g<Boolean>) Boolean.valueOf(connectionManager.a()));
        }
    }

    public void disconnectService(final HuaweiApi<?> huaweiApi, final g<Boolean> gVar) {
        if (Looper.myLooper() == this.c.getLooper()) {
            a(huaweiApi, gVar);
        } else {
            this.c.post(new Runnable() { // from class: com.huawei.hms.common.internal.HuaweiApiManager.1
                @Override // java.lang.Runnable
                public void run() {
                    HuaweiApiManager.this.a(huaweiApi, gVar);
                }
            });
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what == 4) {
            a((b) message.obj);
            return true;
        }
        HMSLog.w("HuaweiApiManager", "Unknown message id: " + message.what);
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void a(b bVar) {
        HuaweiApi<?> huaweiApi = bVar.b;
        ConnectionManager<?> connectionManager = this.e.get(huaweiApi.getConnectionManagerKey());
        if (connectionManager == null) {
            connectionManager = new ConnectionManager<>(huaweiApi);
            this.e.put(huaweiApi.getConnectionManagerKey(), connectionManager);
        }
        connectionManager.a((TaskApiCallWrapper) bVar.a);
    }

    private static class a {
        private final TaskApiCallWrapper a;
        private final AnyClient.CallBack b;

        a(TaskApiCallWrapper taskApiCallWrapper, AnyClient.CallBack callBack) {
            this.a = taskApiCallWrapper;
            this.b = callBack;
        }

        TaskApiCallWrapper a() {
            return this.a;
        }

        AnyClient.CallBack b() {
            return this.b;
        }
    }

    public class ConnectionManager<OptionsT extends Api.ApiOptions> implements BaseHmsClient.ConnectionCallbacks, BaseHmsClient.OnConnectionFailedListener {
        private final AnyClient c;
        private final ConnectionManagerKey e;
        private final HuaweiApi<OptionsT> f;
        private final Queue<a> b = new LinkedList();
        private ConnectionResult d = null;

        ConnectionManager(HuaweiApi<OptionsT> huaweiApi) {
            this.f = huaweiApi;
            this.c = huaweiApi.getClient(HuaweiApiManager.this.c.getLooper(), this);
            this.e = huaweiApi.getConnectionManagerKey();
        }

        void a(TaskApiCallWrapper taskApiCallWrapper) {
            HMSLog.i("HuaweiApiManager", "sendRequest");
            Checker.assertHandlerThread(HuaweiApiManager.this.c);
            a aVarB = b(taskApiCallWrapper);
            int minApkVersion = taskApiCallWrapper.getTaskApiCall().getMinApkVersion();
            if (this.c.isConnected()) {
                if (HMSPackageManager.getInstance(this.f.getContext()).hmsVerHigherThan(minApkVersion)) {
                    a(aVarB);
                    return;
                }
                a();
                this.b.add(aVarB);
                a(minApkVersion);
                return;
            }
            this.b.add(aVarB);
            if (this.d != null && this.d.getErrorCode() != 0) {
                onConnectionFailed(this.d);
            } else {
                a(minApkVersion);
            }
        }

        private String a(String str, String str2) {
            return TextUtils.isEmpty(str) ? TransactionIdCreater.getId(this.f.getAppID(), str2) : str;
        }

        private void a(a aVar) {
            String uri = aVar.a().getTaskApiCall().getUri();
            RequestHeader requestHeader = new RequestHeader();
            requestHeader.setSrvName(uri.split("\\.")[0]);
            requestHeader.setApiName(uri);
            requestHeader.setAppID(this.f.getAppID() + HiAnalyticsConstant.REPORT_VAL_SEPARATOR + this.f.getSubAppID());
            requestHeader.setPkgName(this.f.getContext().getPackageName());
            requestHeader.setSessionId(this.c.getSessionId());
            TaskApiCall taskApiCall = aVar.a().getTaskApiCall();
            requestHeader.setTransactionId(a(taskApiCall.getTransactionId(), uri));
            requestHeader.setParcelable(taskApiCall.getParcelable());
            requestHeader.setKitSdkVersion(this.f.getKitSdkVersion());
            requestHeader.setApiLevel(this.f.getApiLevel() > taskApiCall.getApiLevel() ? this.f.getApiLevel() : taskApiCall.getApiLevel());
            this.c.post(requestHeader, taskApiCall.getRequestJson(), aVar.b());
        }

        private a b(final TaskApiCallWrapper taskApiCallWrapper) {
            return new a(taskApiCallWrapper, new AnyClient.CallBack() { // from class: com.huawei.hms.common.internal.HuaweiApiManager.ConnectionManager.1
                @Override // com.huawei.hms.common.internal.AnyClient.CallBack
                public void onCallback(IMessageEntity iMessageEntity, String str) {
                    if (!(iMessageEntity instanceof ResponseHeader)) {
                        HMSLog.e("HuaweiApiManager", "header is not instance of ResponseHeader");
                        return;
                    }
                    ResponseHeader responseHeader = (ResponseHeader) iMessageEntity;
                    if (!TextUtils.isEmpty(responseHeader.getResolution())) {
                        HMSLog.e("HuaweiApiManager", "Response has resolution: " + responseHeader.getResolution());
                    }
                    com.huawei.hms.support.hianalytics.b.a(ConnectionManager.this.f.getContext(), responseHeader, String.valueOf(ConnectionManager.this.f.getKitSdkVersion()));
                    taskApiCallWrapper.getTaskApiCall().onResponse(ConnectionManager.this.c, responseHeader, str, taskApiCallWrapper.getTaskCompletionSource());
                }
            });
        }

        boolean a() {
            Checker.assertHandlerThread(HuaweiApiManager.this.c);
            this.c.disconnect();
            return true;
        }

        synchronized void a(int i) {
            Checker.assertHandlerThread(HuaweiApiManager.this.c);
            if (this.c.isConnected()) {
                HMSLog.d("HuaweiApiManager", "client is connected");
            } else if (this.c.isConnecting()) {
                HMSLog.d("HuaweiApiManager", "client is isConnecting");
            } else {
                this.c.connect(i);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(ConnectionResult connectionResult) {
            Checker.assertHandlerThread(HuaweiApiManager.this.c);
            this.d = connectionResult;
            Iterator<a> it = this.b.iterator();
            boolean z = true;
            while (it.hasNext()) {
                TaskApiCallWrapper taskApiCallWrapperA = it.next().a();
                ResponseHeader responseHeader = new ResponseHeader(1, CommonCode.ErrorCode.CLIENT_API_INVALID, "Connection Failed:" + b(connectionResult) + "(" + connectionResult.getErrorCode() + ")");
                responseHeader.setTransactionId(taskApiCallWrapperA.getTaskApiCall().getTransactionId());
                com.huawei.hms.support.hianalytics.b.a(this.f.getContext(), responseHeader, String.valueOf(this.f.getKitSdkVersion()));
                if (this.d.getResolution() != null && z) {
                    responseHeader.setParcelable(this.d.getResolution());
                    z = false;
                }
                taskApiCallWrapperA.getTaskApiCall().onResponse(this.c, responseHeader, null, taskApiCallWrapperA.getTaskCompletionSource());
            }
            this.b.clear();
            this.d = null;
            this.c.disconnect();
            HuaweiApiManager.this.e.remove(this.e);
        }

        @Override // com.huawei.hms.common.internal.BaseHmsClient.OnConnectionFailedListener
        public void onConnectionFailed(final ConnectionResult connectionResult) {
            HMSLog.i("HuaweiApiManager", "onConnectionFailed");
            if (Looper.myLooper() != HuaweiApiManager.this.c.getLooper()) {
                HuaweiApiManager.this.c.post(new Runnable() { // from class: com.huawei.hms.common.internal.HuaweiApiManager.ConnectionManager.2
                    @Override // java.lang.Runnable
                    public void run() {
                        ConnectionManager.this.a(connectionResult);
                    }
                });
            } else {
                a(connectionResult);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b() {
            Checker.assertHandlerThread(HuaweiApiManager.this.c);
            this.d = null;
            Iterator<a> it = this.b.iterator();
            while (it.hasNext()) {
                a(it.next());
            }
            this.b.clear();
        }

        @Override // com.huawei.hms.common.internal.BaseHmsClient.ConnectionCallbacks
        public void onConnected() {
            HMSLog.d("HuaweiApiManager", "onConnected");
            if (Looper.myLooper() != HuaweiApiManager.this.c.getLooper()) {
                HuaweiApiManager.this.c.post(new Runnable() { // from class: com.huawei.hms.common.internal.HuaweiApiManager.ConnectionManager.3
                    @Override // java.lang.Runnable
                    public void run() {
                        ConnectionManager.this.b();
                    }
                });
            } else {
                b();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(int i) {
            Checker.assertHandlerThread(HuaweiApiManager.this.c);
            Iterator<a> it = this.b.iterator();
            while (it.hasNext()) {
                TaskApiCallWrapper taskApiCallWrapperA = it.next().a();
                ResponseHeader responseHeader = new ResponseHeader(1, CommonCode.ErrorCode.CLIENT_API_INVALID, "Connection Suspended");
                responseHeader.setTransactionId(taskApiCallWrapperA.getTaskApiCall().getTransactionId());
                taskApiCallWrapperA.getTaskApiCall().onResponse(this.c, responseHeader, null, taskApiCallWrapperA.getTaskCompletionSource());
            }
            this.b.clear();
            this.d = null;
            this.c.disconnect();
            HuaweiApiManager.this.e.remove(this.e);
        }

        @Override // com.huawei.hms.common.internal.BaseHmsClient.ConnectionCallbacks
        public void onConnectionSuspended(final int i) {
            HMSLog.i("HuaweiApiManager", "onConnectionSuspended");
            if (Looper.myLooper() != HuaweiApiManager.this.c.getLooper()) {
                HuaweiApiManager.this.c.post(new Runnable() { // from class: com.huawei.hms.common.internal.HuaweiApiManager.ConnectionManager.4
                    @Override // java.lang.Runnable
                    public void run() {
                        ConnectionManager.this.b(i);
                    }
                });
            } else {
                b(i);
            }
        }

        private String b(ConnectionResult connectionResult) {
            if (Util.isAvailableLibExist(this.f.getContext())) {
                int errorCode = connectionResult.getErrorCode();
                if (errorCode == -1) {
                    return "get update result, but has other error codes";
                }
                if (errorCode == 3) {
                    return "HuaWei Mobile Service is disabled";
                }
                if (errorCode == 8) {
                    return "internal error";
                }
                if (errorCode == 10) {
                    return "application configuration error, please developer check configuration";
                }
                if (errorCode == 13) {
                    return "update cancelled";
                }
                if (errorCode == 21) {
                    return "device is too old to be support";
                }
                switch (errorCode) {
                    case 25:
                        return "failed to get update result";
                    case 26:
                        return "update failed, because no activity incoming, can't pop update page";
                    case 27:
                        return "there is already an update popup at the front desk, but it hasn't been clicked or it is not effective for a while";
                    default:
                        return "unknown errorReason";
                }
            }
            int errorCode2 = connectionResult.getErrorCode();
            return errorCode2 != -1 ? errorCode2 != 8 ? errorCode2 != 10 ? "unknown errorReason" : "application configuration error, please developer check configuration" : "internal error" : "get update result, but has other error codes";
        }
    }
}
