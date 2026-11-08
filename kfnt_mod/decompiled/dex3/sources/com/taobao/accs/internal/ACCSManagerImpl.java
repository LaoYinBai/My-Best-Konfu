package com.taobao.accs.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import anet.channel.SessionCenter;
import com.sigmob.sdk.common.mta.PointCategory;
import com.taobao.accs.ACCSClient;
import com.taobao.accs.ACCSManager;
import com.taobao.accs.AccsClientConfig;
import com.taobao.accs.AccsException;
import com.taobao.accs.ErrorCode;
import com.taobao.accs.IAgooAppReceiver;
import com.taobao.accs.IAppReceiver;
import com.taobao.accs.ILoginInfo;
import com.taobao.accs.base.AccsAbstractDataListener;
import com.taobao.accs.base.AccsDataListener;
import com.taobao.accs.base.TaoBaseService;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.d;
import com.taobao.accs.data.Message;
import com.taobao.accs.data.g;
import com.taobao.accs.f;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.j;
import com.taobao.accs.utl.k;
import com.taobao.accs.utl.l;
import com.taobao.accs.utl.t;
import com.taobao.accs.utl.w;
import com.taobao.aranger.ARanger;
import com.taobao.aranger.exception.IPCException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ACCSManagerImpl implements com.taobao.accs.c {
    private static String c = "ACCSMgrImpl_";
    private String b;
    private d d;
    private int a = 0;
    private BroadcastReceiver e = new BroadcastReceiver() { // from class: com.taobao.accs.internal.ACCSManagerImpl.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                AccsClientConfig configByTag = AccsClientConfig.getConfigByTag(ACCSManagerImpl.this.b);
                if ("com.taobao.aranger.DISCONNECT".equals(intent.getAction())) {
                    ARanger.getInstance(com.taobao.accs.a.class.getName(), f.class, new Object[]{context});
                    if (GlobalClientInfo.b != null) {
                        IAgooAppReceiver iAgooAppReceiver = GlobalClientInfo.b;
                    }
                    if (GlobalClientInfo.getInstance(context).getAppReceiver() != null) {
                        for (Map.Entry<String, IAppReceiver> entry : GlobalClientInfo.getInstance(context).getAppReceiver().entrySet()) {
                            entry.getKey();
                            entry.getValue();
                        }
                    }
                    if (GlobalClientInfo.getInstance(context).getListener() != null) {
                        for (Map.Entry<String, AccsDataListener> entry2 : GlobalClientInfo.getInstance(context).getListener().entrySet()) {
                            entry2.getKey();
                            entry2.getValue();
                        }
                    }
                    ACCSManagerImpl.this.d = (d) ARanger.getService(com.taobao.accs.a.class.getName(), d.class, new Object[]{configByTag});
                    ACCSManagerImpl.this.d.a();
                }
            } catch (Exception e) {
                ALog.e(ACCSManagerImpl.c, "on receive action error, Error:", e, new Object[0]);
                if (e instanceof IPCException) {
                    t.f();
                    ACCSManagerImpl.this.d = new a(ACCSManagerImpl.this.b);
                }
            }
        }
    };
    private BroadcastReceiver f = new BroadcastReceiver() { // from class: com.taobao.accs.internal.ACCSManagerImpl.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                AccsClientConfig configByTag = AccsClientConfig.getConfigByTag(ACCSManagerImpl.this.b);
                String action = intent.getAction();
                char c2 = 65535;
                int iHashCode = action.hashCode();
                if (iHashCode != -1322929215) {
                    if (iHashCode != -1034470443) {
                        if (iHashCode == -1034337366 && action.equals(l.ACTION_STATE_FORE)) {
                            c2 = 0;
                        }
                    } else if (action.equals(l.ACTION_STATE_BACK)) {
                        c2 = 1;
                    }
                } else if (action.equals(l.ACTION_STATE_DEEPBACK)) {
                    c2 = 2;
                }
                switch (c2) {
                    case 0:
                        if (ACCSManagerImpl.this.d == null) {
                            ACCSManagerImpl.this.d = (d) ARanger.getService(com.taobao.accs.a.class.getName(), d.class, new Object[]{configByTag});
                        }
                        ACCSManagerImpl.this.d.a(1);
                        if (intent.getBooleanExtra("state", true)) {
                            ACCSManagerImpl.this.d.a(Message.buildForeground(ACCSManagerImpl.this.d.b(null)));
                            ALog.i(ACCSManagerImpl.c, "send foreground state frame", new Object[0]);
                            break;
                        }
                        break;
                    case 1:
                        if (ACCSManagerImpl.this.d == null) {
                            ACCSManagerImpl.this.d = (d) ARanger.getService(com.taobao.accs.a.class.getName(), d.class, new Object[]{configByTag});
                        }
                        ACCSManagerImpl.this.d.a(0);
                        break;
                    case 2:
                        if (ACCSManagerImpl.this.d == null) {
                            ACCSManagerImpl.this.d = (d) ARanger.getService(com.taobao.accs.a.class.getName(), d.class, new Object[]{configByTag});
                        }
                        ACCSManagerImpl.this.d.a(2);
                        ACCSManagerImpl.this.d.a(Message.buildBackground(ACCSManagerImpl.this.d.b(null)));
                        ALog.i(ACCSManagerImpl.c, "send background state frame", new Object[0]);
                        break;
                }
            } catch (Exception e) {
                ALog.e(ACCSManagerImpl.c, "on receive action error, Error:", e, new Object[0]);
                if (e instanceof IPCException) {
                    ACCSManagerImpl.this.d = new a(ACCSManagerImpl.this.b);
                    t.f();
                }
            }
        }
    };

    public ACCSManagerImpl(Context context, String str) throws AccsException {
        GlobalClientInfo.a = context.getApplicationContext();
        this.b = str;
        if (t.g()) {
            AccsClientConfig configByTag = AccsClientConfig.getConfigByTag(this.b);
            if (configByTag == null) {
                try {
                    configByTag = new AccsClientConfig.Builder().setAppKey(ACCSManager.getDefaultAppkey(context)).setTag(str).build();
                } catch (AccsException e) {
                    ALog.e(c, "ACCSManagerImpl build config", e, new Object[0]);
                }
            }
            try {
                this.d = (d) ARanger.getService(com.taobao.accs.a.class.getName(), d.class, new Object[]{configByTag});
                if (w.e(context)) {
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("com.taobao.aranger.DISCONNECT");
                    context.registerReceiver(this.e, intentFilter);
                    IntentFilter intentFilter2 = new IntentFilter();
                    intentFilter2.addAction(l.ACTION_STATE_FORE);
                    intentFilter2.addAction(l.ACTION_STATE_BACK);
                    intentFilter2.addAction(l.ACTION_STATE_DEEPBACK);
                    this.d.a(l.a().c());
                    LocalBroadcastManager.getInstance(context).registerReceiver(this.f, intentFilter2);
                }
            } catch (Exception unused) {
                t.f();
                this.d = new a(str);
            }
        } else {
            this.d = new a(str);
        }
        c += this.b;
    }

    @Override // com.taobao.accs.c
    public void a(Context context, String str, String str2, String str3, IAppReceiver iAppReceiver) {
        if (context == null) {
            return;
        }
        ALog.i(c, "bindApp", Constants.KEY_APP_KEY, str);
        Message messageBuildParameterError = Message.buildParameterError(context.getPackageName(), 1);
        if (UtilityImpl.d(context)) {
            ALog.e(c, "accs disabled, try enable", new Object[0]);
            UtilityImpl.focusEnableService(context);
        }
        if (TextUtils.isEmpty(str)) {
            this.d.a(messageBuildParameterError, -14);
            return;
        }
        this.d.c(str3);
        this.d.d(str);
        UtilityImpl.d(context, str);
        if (iAppReceiver != null) {
            GlobalClientInfo.getInstance(context).setAppReceiver(this.b, iAppReceiver);
        }
        UtilityImpl.enableService(context);
        Intent intentB = b(context, 1);
        if (intentB == null) {
            return;
        }
        try {
            String str4 = GlobalClientInfo.getInstance(context).getPackageInfo().versionName;
            boolean z = UtilityImpl.c(context) || UtilityImpl.a(Constants.SP_FILE_NAME, context) || UtilityImpl.b(Constants.SP_FILE_NAME, context);
            if (z) {
                ALog.d(c, "bindApp", "need force bind");
                intentB.putExtra(Constants.KEY_FOUCE_BIND, true);
            }
            intentB.putExtra(Constants.KEY_APP_KEY, str);
            intentB.putExtra(Constants.KEY_TTID, str3);
            intentB.putExtra(Constants.KEY_APP_VERSION, str4);
            if (TextUtils.isEmpty(str2)) {
                str2 = this.d.d();
            }
            intentB.putExtra("app_sercet", str2);
            if (UtilityImpl.isMainProcess(context)) {
                Message messageBuildBindApp = Message.buildBindApp(this.d.b(null), this.b, context, intentB);
                if (messageBuildBindApp != null && messageBuildBindApp.getNetPermanceMonitor() != null) {
                    messageBuildBindApp.getNetPermanceMonitor().setDataId(messageBuildBindApp.dataId);
                    messageBuildBindApp.getNetPermanceMonitor().setMsgType(1);
                    messageBuildBindApp.getNetPermanceMonitor().setHost(messageBuildBindApp.host.toString());
                }
                a(context, messageBuildBindApp, 1, z);
            } else {
                ALog.w(c, "bindApp only allow in main process", new Object[0]);
            }
            this.d.b();
            try {
                if (t.a) {
                    t.a(new String[]{"accs"}, new t.a());
                    t.i();
                    t.h();
                }
            } catch (Throwable unused) {
                ALog.w(c, "no orange sdk", new Object[0]);
            }
        } catch (Throwable th) {
            ALog.e(c, "bindApp exception", th, new Object[0]);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void a(Context context, Message message, int i, boolean z) {
        boolean z2;
        this.d.a();
        if (message == null) {
            ALog.e(c, "message is null", new Object[0]);
            this.d.a(Message.buildParameterError(context.getPackageName(), i), -2);
            return;
        }
        switch (i) {
            case 1:
                String packageName = message.getPackageName();
                if (this.d.e(packageName) && !z) {
                    ALog.i(c, "isAppBinded", com.umeng.message.common.a.u, packageName);
                    this.d.a(message, 200);
                    z2 = false;
                    break;
                }
                z2 = true;
                break;
            case 2:
                if (this.d.f(message.getPackageName())) {
                    ALog.i(c, message.getPackageName() + " isAppUnbinded", new Object[0]);
                    this.d.a(message, 200);
                    z2 = false;
                    break;
                }
                z2 = true;
                break;
            case 3:
                if (this.d.a(message.getPackageName(), message.userinfo) && !z) {
                    ALog.i(c, message.getPackageName() + "/" + message.userinfo + " isUserBinded", "isForceBind", Boolean.valueOf(z));
                    this.d.a(message, 200);
                    z2 = false;
                    break;
                }
                z2 = true;
                break;
            default:
                z2 = true;
                break;
        }
        if (z2) {
            ALog.i(c, "sendControlMessage", Constants.KEY_COMMAND, Integer.valueOf(i));
            this.d.a(message, true);
        }
    }

    @Override // com.taobao.accs.c
    public void a(Context context, String str) {
        a(context, str, false);
    }

    @Override // com.taobao.accs.c
    public void a(Context context, String str, boolean z) {
        try {
            ALog.i(c, "bindUser", "userId", str);
            if (UtilityImpl.d(context)) {
                ALog.e(c, "accs disabled", new Object[0]);
                return;
            }
            Intent intentB = b(context, 3);
            if (intentB == null) {
                ALog.e(c, "intent null", new Object[0]);
                a(context, 3, (String) null, (String) null);
                return;
            }
            String strC = this.d.c();
            if (TextUtils.isEmpty(strC)) {
                ALog.e(c, "appKey null", new Object[0]);
                return;
            }
            if (UtilityImpl.c(context) || z) {
                ALog.i(c, "force bind User", new Object[0]);
                intentB.putExtra(Constants.KEY_FOUCE_BIND, true);
                z = true;
            }
            intentB.putExtra(Constants.KEY_APP_KEY, strC);
            intentB.putExtra(Constants.KEY_USER_ID, str);
            if (UtilityImpl.isMainProcess(context)) {
                Message messageBuildBindUser = Message.buildBindUser(this.d.b(null), this.b, intentB);
                if (messageBuildBindUser != null && messageBuildBindUser.getNetPermanceMonitor() != null) {
                    messageBuildBindUser.getNetPermanceMonitor().setDataId(messageBuildBindUser.dataId);
                    messageBuildBindUser.getNetPermanceMonitor().setMsgType(2);
                    messageBuildBindUser.getNetPermanceMonitor().setHost(messageBuildBindUser.host.toString());
                }
                a(context, messageBuildBindUser, 3, z);
            }
            this.d.b();
        } catch (Throwable th) {
            ALog.e(c, "bindUser", th, new Object[0]);
        }
    }

    @Override // com.taobao.accs.c
    public void a(Context context) {
        if (UtilityImpl.d(context) || UtilityImpl.d(context)) {
            return;
        }
        Intent intentB = b(context, 4);
        if (intentB == null) {
            a(context, 4, (String) null, (String) null);
            return;
        }
        String strC = this.d.c();
        if (TextUtils.isEmpty(strC)) {
            return;
        }
        intentB.putExtra(Constants.KEY_APP_KEY, strC);
        if (UtilityImpl.isMainProcess(context)) {
            a(context, Message.buildUnbindUser(this.d.b(null), this.b, intentB), 4, false);
        }
    }

    @Override // com.taobao.accs.c
    public void b(Context context, String str) {
        if (UtilityImpl.d(context) || UtilityImpl.d(context)) {
            return;
        }
        Intent intentB = b(context, 5);
        if (intentB == null) {
            a(context, 5, str, (String) null);
            return;
        }
        String strC = this.d.c();
        if (TextUtils.isEmpty(strC)) {
            return;
        }
        intentB.putExtra(Constants.KEY_APP_KEY, strC);
        intentB.putExtra(Constants.KEY_SERVICE_ID, str);
        if (UtilityImpl.isMainProcess(context)) {
            Message messageBuildBindService = Message.buildBindService(this.d.b(null), this.b, intentB);
            if (messageBuildBindService != null && messageBuildBindService.getNetPermanceMonitor() != null) {
                messageBuildBindService.getNetPermanceMonitor().setDataId(messageBuildBindService.dataId);
                messageBuildBindService.getNetPermanceMonitor().setMsgType(3);
                messageBuildBindService.getNetPermanceMonitor().setHost(messageBuildBindService.host.toString());
            }
            a(context, messageBuildBindService, 5, false);
        }
        this.d.b();
    }

    @Override // com.taobao.accs.c
    public void c(Context context, String str) {
        if (UtilityImpl.d(context)) {
            return;
        }
        Intent intentB = b(context, 6);
        if (intentB == null) {
            a(context, 6, str, (String) null);
            return;
        }
        String strC = this.d.c();
        if (TextUtils.isEmpty(strC)) {
            return;
        }
        intentB.putExtra(Constants.KEY_APP_KEY, strC);
        intentB.putExtra(Constants.KEY_SERVICE_ID, str);
        if (UtilityImpl.isMainProcess(context)) {
            a(context, Message.buildUnbindService(this.d.b(null), this.b, intentB), 6, false);
        }
    }

    @Override // com.taobao.accs.c
    public String a(Context context, String str, String str2, byte[] bArr, String str3) {
        return a(context, str, str2, bArr, str3, (String) null);
    }

    @Override // com.taobao.accs.c
    public String a(Context context, String str, String str2, byte[] bArr, String str3, String str4) {
        return a(context, str, str2, bArr, str3, str4, null);
    }

    @Override // com.taobao.accs.c
    public String a(Context context, String str, String str2, byte[] bArr, String str3, String str4, URL url) {
        return a(context, new ACCSManager.AccsRequest(str, str2, bArr, str3, str4, url, null));
    }

    @Override // com.taobao.accs.c
    public String a(Context context, ACCSManager.AccsRequest accsRequest) {
        boolean zD;
        try {
            zD = UtilityImpl.d(context);
        } catch (Throwable th) {
            k.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, accsRequest.serviceId, "1", "data " + th.toString());
            ALog.e(c, "sendData", th, "dataid", accsRequest.dataId);
        }
        if (!UtilityImpl.isMainProcess(context)) {
            ALog.e(c, "sendData not in mainprocess", new Object[0]);
            return null;
        }
        if (!zD && accsRequest != null) {
            if (TextUtils.isEmpty(accsRequest.dataId)) {
                synchronized (ACCSManagerImpl.class) {
                    this.a++;
                    accsRequest.dataId = this.a + "";
                }
            }
            if (TextUtils.isEmpty(this.d.c())) {
                k.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, accsRequest.serviceId, "1", "data appkey null");
                ALog.e(c, "sendData appkey null", Constants.KEY_DATA_ID, accsRequest.dataId);
                return null;
            }
            this.d.a();
            Message messageBuildSendData = Message.buildSendData(this.d.b(null), this.b, this.d.e(), context, context.getPackageName(), accsRequest);
            if (messageBuildSendData != null && messageBuildSendData.getNetPermanceMonitor() != null) {
                messageBuildSendData.getNetPermanceMonitor().onSend();
            }
            this.d.a(messageBuildSendData, true);
            return accsRequest.dataId;
        }
        if (zD) {
            k.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, accsRequest.serviceId, "1", "accs disable");
        } else {
            k.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, "", "1", "data null");
        }
        ALog.e(c, "sendData dataInfo null or disable:" + zD, new Object[0]);
        return null;
    }

    @Override // com.taobao.accs.c
    public String b(Context context, String str, String str2, byte[] bArr, String str3, String str4) {
        return b(context, str, str2, bArr, str3, str4, null);
    }

    @Override // com.taobao.accs.c
    public String b(Context context, String str, String str2, byte[] bArr, String str3, String str4, URL url) {
        return b(context, new ACCSManager.AccsRequest(str, str2, bArr, str3, str4, url, null));
    }

    @Override // com.taobao.accs.c
    public String a(Context context, ACCSManager.AccsRequest accsRequest, String str, boolean z) {
        try {
        } catch (Throwable th) {
            if (accsRequest != null) {
                k.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, accsRequest.serviceId, "1", "request " + th.toString());
                ALog.e(c, "sendRequest", th, Constants.KEY_DATA_ID, accsRequest.dataId);
            }
        }
        if (accsRequest == null) {
            ALog.e(c, "sendRequest request null", new Object[0]);
            k.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, null, "1", "request null");
            return null;
        }
        if (!UtilityImpl.isMainProcess(context)) {
            ALog.e(c, "sendRequest not in mainprocess", new Object[0]);
            return null;
        }
        if (UtilityImpl.d(context)) {
            ALog.e(c, "sendRequest disable", new Object[0]);
            k.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, accsRequest.serviceId, "1", "accs disable");
            return null;
        }
        if (TextUtils.isEmpty(accsRequest.dataId)) {
            synchronized (ACCSManagerImpl.class) {
                this.a++;
                accsRequest.dataId = this.a + "";
            }
        }
        if (TextUtils.isEmpty(this.d.c())) {
            k.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, accsRequest.serviceId, "1", "request appkey null");
            ALog.e(c, "sendRequest appkey null", Constants.KEY_DATA_ID, accsRequest.dataId);
            return null;
        }
        this.d.a();
        if (str == null) {
            str = context.getPackageName();
        }
        Message messageBuildRequest = Message.buildRequest(context, this.d.b(null), this.b, "", str, Constants.TARGET_SERVICE_PRE, accsRequest, z);
        if (messageBuildRequest != null && messageBuildRequest.getNetPermanceMonitor() != null) {
            messageBuildRequest.getNetPermanceMonitor().onSend();
        }
        this.d.a(messageBuildRequest, true);
        return accsRequest.dataId;
    }

    @Override // com.taobao.accs.c
    public String b(Context context, ACCSManager.AccsRequest accsRequest) {
        return a(context, accsRequest, (String) null, true);
    }

    @Override // com.taobao.accs.c
    public String a(Context context, ACCSManager.AccsRequest accsRequest, TaoBaseService.ExtraInfo extraInfo) {
        boolean z;
        try {
            if (context == null || accsRequest == null) {
                ALog.e(c, "sendPushResponse input null", "context", context, "response", accsRequest, "extraInfo", extraInfo);
                k.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, "", "1", "sendPushResponse null");
                return null;
            }
            k.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, "push response total");
            if (UtilityImpl.d(context)) {
                k.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, accsRequest.serviceId, "1", "sendPushResponse accs disable");
                return null;
            }
            String strC = this.d.c();
            if (TextUtils.isEmpty(strC)) {
                k.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, accsRequest.serviceId, "1", "sendPushResponse appkey null");
                ALog.e(c, "sendPushResponse appkey null dataid:" + accsRequest.dataId, new Object[0]);
                return null;
            }
            if (TextUtils.isEmpty(accsRequest.dataId)) {
                synchronized (ACCSManagerImpl.class) {
                    this.a++;
                    accsRequest.dataId = this.a + "";
                }
            }
            TaoBaseService.ExtraInfo extraInfo2 = extraInfo == null ? new TaoBaseService.ExtraInfo() : extraInfo;
            accsRequest.host = null;
            extraInfo2.fromPackage = context.getPackageName();
            if (extraInfo2.connType == 0 || extraInfo2.fromHost == null) {
                extraInfo2.connType = 0;
                ALog.w(c, "pushresponse use channel", Constants.KEY_HOST, extraInfo2.fromHost);
                z = false;
            } else {
                z = true;
            }
            ALog.i(c, "sendPushResponse", "sendbyInapp", Boolean.valueOf(z), Constants.KEY_HOST, extraInfo2.fromHost, "pkg", extraInfo2.fromPackage, Constants.KEY_DATA_ID, accsRequest.dataId);
            if (z) {
                ALog.i(c, "sendPushResponse inapp by", PointCategory.APP, extraInfo2.fromPackage);
                accsRequest.host = new URL(extraInfo2.fromHost);
                if (context.getPackageName().equals(extraInfo2.fromPackage) && UtilityImpl.isMainProcess(context)) {
                    a(context, accsRequest, context.getPackageName(), false);
                    return null;
                }
                Intent intent = new Intent(Constants.ACTION_SEND);
                intent.setClassName(extraInfo2.fromPackage, j.msgService);
                intent.putExtra("packageName", context.getPackageName());
                intent.putExtra(Constants.KEY_SEND_REQDATA, accsRequest);
                intent.putExtra(Constants.KEY_APP_KEY, strC);
                intent.putExtra(Constants.KEY_CONFIG_TAG, this.b);
                com.taobao.accs.a.a.a(context, intent);
                return null;
            }
            Intent intentB = b(context, 100);
            if (intentB == null) {
                k.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, accsRequest.serviceId, "1", "push response intent null");
                a(context, 100, accsRequest.serviceId, accsRequest.dataId);
                ALog.e(c, "sendPushResponse input null", "context", context, "response", accsRequest, "extraInfo", extraInfo2);
                return null;
            }
            ALog.i(c, "sendPushResponse channel by", PointCategory.APP, extraInfo2.fromPackage);
            intentB.setClassName(extraInfo2.fromPackage, j.channelService);
            intentB.putExtra(Constants.KEY_SEND_TYPE, Message.ReqType.REQ);
            intentB.putExtra(Constants.KEY_APP_KEY, strC);
            intentB.putExtra(Constants.KEY_USER_ID, accsRequest.userId);
            intentB.putExtra(Constants.KEY_SERVICE_ID, accsRequest.serviceId);
            intentB.putExtra("data", accsRequest.data);
            intentB.putExtra(Constants.KEY_DATA_ID, accsRequest.dataId);
            intentB.putExtra(Constants.KEY_CONFIG_TAG, this.b);
            if (!TextUtils.isEmpty(accsRequest.businessId)) {
                intentB.putExtra(Constants.KEY_BUSINESSID, accsRequest.businessId);
            }
            if (!TextUtils.isEmpty(accsRequest.tag)) {
                intentB.putExtra(Constants.KEY_EXT_TAG, accsRequest.tag);
            }
            if (accsRequest.target != null) {
                intentB.putExtra(Constants.KEY_TARGET, accsRequest.target);
            }
            com.taobao.accs.a.a.a(context, intentB);
            return null;
        } catch (Throwable th) {
            k.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, accsRequest.serviceId, "1", "push response " + th.toString());
            ALog.e(c, "sendPushResponse dataid:" + accsRequest.dataId, th, new Object[0]);
            return null;
        }
    }

    @Override // com.taobao.accs.c
    public boolean b(Context context) {
        return UtilityImpl.k(context);
    }

    private Intent b(Context context, int i) {
        if (i != 1 && UtilityImpl.d(context)) {
            ALog.e(c, "getIntent null command:" + i + " accs enabled:" + UtilityImpl.d(context), new Object[0]);
            return null;
        }
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION_COMMAND);
        intent.setClassName(context.getPackageName(), j.channelService);
        intent.putExtra("packageName", context.getPackageName());
        intent.putExtra(Constants.KEY_COMMAND, i);
        intent.putExtra(Constants.KEY_APP_KEY, this.d.c());
        intent.putExtra(Constants.KEY_CONFIG_TAG, this.b);
        return intent;
    }

    @Override // com.taobao.accs.c
    public void c(Context context) {
        UtilityImpl.focusDisableService(context);
    }

    @Override // com.taobao.accs.c
    public boolean a() {
        return this.d != null && this.d.f();
    }

    @Override // com.taobao.accs.c
    public void d(Context context) {
        UtilityImpl.focusEnableService(context);
    }

    @Override // com.taobao.accs.c
    @Deprecated
    public void a(Context context, int i) {
        ACCSClient.setEnvironment(context, i);
    }

    private void a(Context context, int i, String str, String str2) {
        Intent intent = new Intent(Constants.ACTION_RECEIVE);
        intent.setPackage(context.getPackageName());
        intent.putExtra(Constants.KEY_COMMAND, i);
        intent.putExtra(Constants.KEY_SERVICE_ID, str);
        intent.putExtra(Constants.KEY_DATA_ID, str2);
        intent.putExtra(Constants.KEY_APP_KEY, this.d.c());
        intent.putExtra(Constants.KEY_CONFIG_TAG, this.b);
        intent.putExtra(Constants.KEY_ERROR_CODE, i == 2 ? 200 : 300);
        g.a(context, intent);
    }

    @Override // com.taobao.accs.c
    public void a(Context context, String str, int i) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
        if (!TextUtils.isEmpty(str)) {
            editorEdit.putString(Constants.KEY_PROXY_HOST, str);
        }
        editorEdit.putInt(Constants.KEY_PROXY_PORT, i);
        editorEdit.apply();
    }

    @Override // com.taobao.accs.c
    public void a(Context context, String str, String str2, IAppReceiver iAppReceiver) {
        GlobalClientInfo.getInstance(context).setAppReceiver(this.b, iAppReceiver);
        if (!UtilityImpl.isMainProcess(context)) {
            ALog.d(c, "inapp only init in main process!", new Object[0]);
            return;
        }
        ALog.d(c, "startInAppConnection APPKEY:" + str, new Object[0]);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (!TextUtils.equals(this.d.c(), str)) {
            this.d.c(str2);
            this.d.d(str);
            UtilityImpl.d(context, str);
        }
        this.d.a();
    }

    @Override // com.taobao.accs.c
    public void a(Context context, ILoginInfo iLoginInfo) {
        GlobalClientInfo.getInstance(context).setLoginInfoImpl(this.b, iLoginInfo);
    }

    @Override // com.taobao.accs.c
    public void e(Context context) {
        GlobalClientInfo.getInstance(context).clearLoginInfoImpl();
    }

    @Override // com.taobao.accs.c
    public boolean a(String str) {
        return this.d.a(str);
    }

    @Override // com.taobao.accs.c
    public Map<String, Boolean> b() throws Exception {
        String strB = this.d.b(null);
        HashMap map = new HashMap();
        map.put(strB, false);
        if (SessionCenter.getInstance(this.d.c()).getThrowsException(strB, 60000L) != null) {
            map.put(strB, true);
        }
        ALog.d(c, "getChannelState " + map.toString(), new Object[0]);
        return map;
    }

    @Override // com.taobao.accs.c
    public Map<String, Boolean> c() throws Exception {
        SessionCenter.getInstance(this.d.c()).forceRecreateAccsSession();
        return b();
    }

    @Override // com.taobao.accs.c
    public boolean a(int i) {
        return ErrorCode.isChannelError(i);
    }

    @Override // com.taobao.accs.c
    public void a(Context context, String str, String str2) {
        GlobalClientInfo.getInstance(context).registerService(str, str2);
    }

    @Override // com.taobao.accs.c
    public void d(Context context, String str) {
        GlobalClientInfo.getInstance(context).unRegisterService(str);
    }

    @Override // com.taobao.accs.c
    public void a(Context context, String str, AccsAbstractDataListener accsAbstractDataListener) {
        GlobalClientInfo.getInstance(context).registerListener(str, accsAbstractDataListener);
    }

    @Override // com.taobao.accs.c
    public void e(Context context, String str) {
        GlobalClientInfo.getInstance(context).unregisterListener(str);
    }

    @Override // com.taobao.accs.c
    public void a(String str, String str2, String str3, short s, String str4, Map<Integer, String> map) {
        this.d.a(Message.buildPushAck(this.d.b(null), this.b, str, str2, str3, true, s, str4, map), true);
    }

    @Override // com.taobao.accs.c
    public void a(AccsClientConfig accsClientConfig) {
        this.d.a(accsClientConfig);
    }
}
