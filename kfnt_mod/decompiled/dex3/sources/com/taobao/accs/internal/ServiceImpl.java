package com.taobao.accs.internal;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.text.TextUtils;
import com.sigmob.sdk.common.mta.PointCategory;
import com.ss.android.socialbase.appdownloader.constants.Constants;
import com.tachikoma.core.utility.UriUtil;
import com.taobao.accs.ACCSManager;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.ThreadPoolExecutorFactory;
import com.taobao.accs.data.Message;
import com.taobao.accs.data.g;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.k;
import com.taobao.accs.utl.q;
import com.taobao.accs.utl.t;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import org.android.agoo.common.Config;
import org.android.agoo.service.IMessageService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ServiceImpl extends b {
    private static com.taobao.accs.b.a e;
    private Service b;
    private Context c;
    private long d;
    private String f;
    private final IMessageService.Stub g;

    @Override // com.taobao.accs.internal.b, com.taobao.accs.base.IBaseService
    public boolean onUnbind(Intent intent) {
        return false;
    }

    public ServiceImpl(Service service) {
        super(service);
        this.b = null;
        this.f = UtilityImpl.NET_TYPE_UNKNOWN;
        this.g = new AnonymousClass1();
        this.b = service;
        this.c = service.getApplicationContext();
    }

    @Override // com.taobao.accs.internal.b, com.taobao.accs.base.IBaseService
    public void onCreate() throws Throwable {
        super.onCreate();
        a();
    }

    @Override // com.taobao.accs.internal.b
    public int a(Intent intent) {
        String action;
        Bundle extras;
        int i = 1;
        if (ALog.isPrintLog(ALog.Level.I)) {
            ALog.i("ServiceImpl", "onHostStartCommand", Constants.INTENT, intent);
        }
        try {
            try {
                if (ALog.isPrintLog(ALog.Level.D) && intent != null && (extras = intent.getExtras()) != null) {
                    for (String str : extras.keySet()) {
                        ALog.d("ServiceImpl", "onHostStartCommand", com.ss.union.game.sdk.redemptionCode.a.p, str, " value", extras.get(str));
                    }
                }
                int iC = q.c();
                if (iC > 3) {
                    try {
                        ALog.e("ServiceImpl", "onHostStartCommand load SO fail 4 times, don't auto restart", new Object[0]);
                        k.a("accs", BaseMonitor.COUNT_POINT_SOFAIL, UtilityImpl.a(iC), 0.0d);
                        i = 2;
                    } catch (Throwable th) {
                        th = th;
                        i = 2;
                        ALog.e("ServiceImpl", "onHostStartCommand", th, new Object[0]);
                        return i;
                    }
                }
                action = intent == null ? null : intent.getAction();
            } catch (Throwable th2) {
                th = th2;
            }
            if (TextUtils.isEmpty(action)) {
                b();
                a(false, false);
                return i;
            }
            a(intent, action);
            return i;
        } finally {
            com.taobao.accs.client.a.g.incrementAndGet();
        }
    }

    private void a() throws Throwable {
        ALog.d("ServiceImpl", "init start", new Object[0]);
        GlobalClientInfo.getInstance(this.c);
        com.taobao.accs.client.a.g.incrementAndGet();
        this.d = System.currentTimeMillis();
        this.f = UtilityImpl.h(this.c);
        if (Config.f(this.c)) {
            com.taobao.accs.b.a.c(this.c);
            e = com.taobao.accs.b.a.a(this.c, 600, false);
            if (e != null) {
                e.a();
            }
        }
        if (ALog.isPrintLog(ALog.Level.I)) {
            ALog.i("ServiceImpl", PointCategory.INIT, com.taobao.accs.common.Constants.KEY_SDK_VERSION, Integer.valueOf(com.taobao.accs.common.Constants.SDK_VERSION_CODE), "procStart", Integer.valueOf(com.taobao.accs.client.a.g.intValue()));
        }
        UTMini.getInstance().commitEvent(66001, "START", UtilityImpl.d(), "PROXY");
        long jN = UtilityImpl.n(this.c);
        ALog.d("ServiceImpl", "getServiceAliveTime", "aliveTime", Long.valueOf(jN));
        if (jN > 20000) {
            k.a("accs", "service_alive", "", jN / 1000);
        }
        UtilityImpl.a(this.c, com.taobao.accs.common.Constants.SP_KEY_SERVICE_START, System.currentTimeMillis());
        UTMini.getInstance().commitEvent(66001, "NOTIFY", UtilityImpl.r(this.c));
    }

    private void a(Intent intent, String str) {
        ALog.d("ServiceImpl", "handleAction", "action", str);
        try {
            if (!TextUtils.isEmpty(str) && com.taobao.accs.common.Constants.ACTION_PING.equals(str)) {
                String stringExtra = intent.getStringExtra("source");
                ALog.i("ServiceImpl", "org.agoo.android.intent.action.PING_V4,start channel by brothers", "serviceStart", Integer.valueOf(com.taobao.accs.client.a.g.intValue()), "source" + stringExtra);
                k.a("accs", "startChannel", stringExtra, 0.0d);
                if (com.taobao.accs.client.a.c()) {
                    k.a("accs", "createChannel", stringExtra, 0.0d);
                }
            }
            b();
            if (TextUtils.equals(str, "android.intent.action.PACKAGE_REMOVED")) {
                return;
            }
            if (TextUtils.equals(str, "android.net.conn.CONNECTIVITY_CHANGE")) {
                String strH = UtilityImpl.h(this.c);
                boolean zK = UtilityImpl.k(this.c);
                ALog.i("ServiceImpl", "network change:" + this.f + " to " + strH, new Object[0]);
                if (zK) {
                    this.f = strH;
                    c();
                    a(true, false);
                    UTMini.getInstance().commitEvent(66001, "CONNECTIVITY_CHANGE", strH, UtilityImpl.d(), com.sigmob.sdk.common.Constants.FAIL);
                }
                if (strH.equals(UtilityImpl.NET_TYPE_UNKNOWN)) {
                    c();
                    this.f = strH;
                    return;
                }
                return;
            }
            if (TextUtils.equals(str, "android.intent.action.BOOT_COMPLETED")) {
                a(true, false);
                return;
            }
            if (TextUtils.equals(str, "android.intent.action.USER_PRESENT")) {
                ALog.d("ServiceImpl", "action android.intent.action.USER_PRESENT", new Object[0]);
                a(true, false);
            } else if (str.equals(com.taobao.accs.common.Constants.ACTION_COMMAND)) {
                b(intent);
            } else if (str.equals(com.taobao.accs.common.Constants.ACTION_START_FROM_AGOO)) {
                ALog.i("ServiceImpl", "ACTION_START_FROM_AGOO", new Object[0]);
            }
        } catch (Throwable th) {
            ALog.e("ServiceImpl", "handleAction", th, new Object[0]);
        }
    }

    private void b(Intent intent) {
        com.taobao.accs.net.b bVar;
        Message message;
        Message.ReqType reqType;
        URL url;
        Message messageBuildRequest;
        Message messageBuildUnbindUser;
        URL url2;
        int intExtra = intent.getIntExtra(com.taobao.accs.common.Constants.KEY_COMMAND, -1);
        ALog.i("ServiceImpl", "handleCommand", com.taobao.accs.common.Constants.KEY_COMMAND, Integer.valueOf(intExtra));
        String stringExtra = intent.getStringExtra("packageName");
        String stringExtra2 = intent.getStringExtra(com.taobao.accs.common.Constants.KEY_SERVICE_ID);
        String stringExtra3 = intent.getStringExtra(com.taobao.accs.common.Constants.KEY_USER_ID);
        String stringExtra4 = intent.getStringExtra(com.taobao.accs.common.Constants.KEY_APP_KEY);
        String stringExtra5 = intent.getStringExtra(com.taobao.accs.common.Constants.KEY_CONFIG_TAG);
        String stringExtra6 = intent.getStringExtra(com.taobao.accs.common.Constants.KEY_TTID);
        intent.getStringExtra(com.taobao.accs.common.Constants.KEY_SID);
        intent.getStringExtra(com.taobao.accs.common.Constants.KEY_ANTI_BRUSH_COOKIE);
        if (intExtra == 201) {
            a(Message.BuildPing(true, 0), true);
            d();
        }
        if (intExtra <= 0 || TextUtils.isEmpty(stringExtra)) {
            return;
        }
        com.taobao.accs.net.b bVarA = a(this.c, stringExtra5, true);
        if (bVarA != null) {
            bVarA.a();
            if (intExtra != 1) {
                bVar = bVarA;
                if (intExtra == 2) {
                    ALog.e("ServiceImpl", "onHostStartCommand COMMAND_UNBIND_APP", new Object[0]);
                    if (bVar.j().d(stringExtra)) {
                        Message messageBuildUnbindApp = Message.buildUnbindApp(bVar.b((String) null), stringExtra);
                        ALog.i("ServiceImpl", stringExtra + " isAppUnbinded", new Object[0]);
                        bVar.b(messageBuildUnbindApp, 200);
                        return;
                    }
                } else if (intExtra == 5) {
                    messageBuildUnbindUser = Message.buildBindService(bVar.b((String) null), stringExtra2);
                } else if (intExtra == 6) {
                    messageBuildUnbindUser = Message.buildUnbindService(stringExtra, stringExtra2);
                } else if (intExtra == 3) {
                    messageBuildUnbindUser = Message.buildBindUser(stringExtra, stringExtra3);
                    if (bVar.j().b(stringExtra, stringExtra3) && !intent.getBooleanExtra(com.taobao.accs.common.Constants.KEY_FOUCE_BIND, false)) {
                        ALog.i("ServiceImpl", stringExtra + "/" + stringExtra3 + " isUserBinded", new Object[0]);
                        if (messageBuildUnbindUser != null) {
                            bVar.b(messageBuildUnbindUser, 200);
                            return;
                        }
                        return;
                    }
                } else if (intExtra == 4) {
                    messageBuildUnbindUser = Message.buildUnbindUser(stringExtra);
                } else {
                    if (intExtra == 100) {
                        byte[] byteArrayExtra = intent.getByteArrayExtra("data");
                        String stringExtra7 = intent.getStringExtra(com.taobao.accs.common.Constants.KEY_DATA_ID);
                        String stringExtra8 = intent.getStringExtra(com.taobao.accs.common.Constants.KEY_TARGET);
                        String stringExtra9 = intent.getStringExtra(com.taobao.accs.common.Constants.KEY_BUSINESSID);
                        String stringExtra10 = intent.getStringExtra(com.taobao.accs.common.Constants.KEY_EXT_TAG);
                        try {
                            reqType = (Message.ReqType) intent.getSerializableExtra(com.taobao.accs.common.Constants.KEY_SEND_TYPE);
                        } catch (Exception unused) {
                            reqType = null;
                        }
                        if (byteArrayExtra != null) {
                            try {
                                if (!t.g()) {
                                    url2 = new URL(UriUtil.HTTPS_PREFIX + ((com.taobao.accs.net.t) bVar).o());
                                } else {
                                    url2 = new URL(bVar.b((String) null));
                                }
                                url = url2;
                            } catch (MalformedURLException e2) {
                                e2.printStackTrace();
                                url = null;
                            }
                            message = null;
                            ACCSManager.AccsRequest accsRequest = new ACCSManager.AccsRequest(stringExtra3, stringExtra2, byteArrayExtra, stringExtra7, stringExtra8, url, stringExtra9);
                            accsRequest.setTag(stringExtra10);
                            if (reqType == null) {
                                messageBuildRequest = Message.buildSendData(bVar.b((String) null), stringExtra5, bVar.i.getStoreId(), this.c, stringExtra, accsRequest, false);
                            } else if (reqType == Message.ReqType.REQ) {
                                messageBuildRequest = Message.buildRequest(this.c, bVar.b((String) null), stringExtra5, bVar.i.getStoreId(), stringExtra, com.taobao.accs.common.Constants.TARGET_SERVICE_PRE, accsRequest, false);
                            }
                            messageBuildUnbindUser = messageBuildRequest;
                        }
                    } else {
                        message = null;
                        if (intExtra == 106) {
                            intent.setAction(com.taobao.accs.common.Constants.ACTION_RECEIVE);
                            intent.putExtra(com.taobao.accs.common.Constants.KEY_COMMAND, -1);
                            g.a(this.c, intent);
                            return;
                        }
                    }
                    messageBuildUnbindUser = message;
                }
                message = null;
                messageBuildUnbindUser = message;
            } else {
                if (!stringExtra.equals(this.c.getPackageName())) {
                    ALog.e("ServiceImpl", "handleCommand bindapp pkg error", new Object[0]);
                    return;
                }
                messageBuildUnbindUser = Message.buildBindApp(this.c, stringExtra5, stringExtra4, intent.getStringExtra("app_sercet"), stringExtra, stringExtra6, intent.getStringExtra(com.taobao.accs.common.Constants.KEY_APP_VERSION));
                bVarA.a = stringExtra6;
                UtilityImpl.d(this.c, stringExtra4);
                if (bVarA.j().c(stringExtra) && !intent.getBooleanExtra(com.taobao.accs.common.Constants.KEY_FOUCE_BIND, false)) {
                    ALog.i("ServiceImpl", stringExtra + " isAppBinded", new Object[0]);
                    bVarA.b(messageBuildUnbindUser, 200);
                    return;
                }
                bVar = bVarA;
            }
            if (messageBuildUnbindUser != null) {
                ALog.d("ServiceImpl", "try send message", new Object[0]);
                if (messageBuildUnbindUser.getNetPermanceMonitor() != null) {
                    messageBuildUnbindUser.getNetPermanceMonitor().onSend();
                }
                bVar.b(messageBuildUnbindUser, true);
                return;
            }
            ALog.e("ServiceImpl", "message is null", new Object[0]);
            bVar.b(Message.buildParameterError(stringExtra, intExtra), -2);
            return;
        }
        ALog.e("ServiceImpl", "no connection", com.taobao.accs.common.Constants.KEY_CONFIG_TAG, stringExtra5, com.taobao.accs.common.Constants.KEY_COMMAND, Integer.valueOf(intExtra));
    }

    @Override // com.taobao.accs.internal.b, com.taobao.accs.base.IBaseService
    public IBinder onBind(Intent intent) {
        String action = intent.getAction();
        ALog.d("ServiceImpl", "accs probeTaoBao begin......action=" + action, new Object[0]);
        if (TextUtils.isEmpty(action) || !TextUtils.equals(action, com.taobao.accs.common.Constants.ACTION_PING)) {
            return null;
        }
        UTMini.getInstance().commitEvent(66001, "probeChannelService", UtilityImpl.l(this.c), intent.getStringExtra("source"));
        return this.g;
    }

    /* renamed from: com.taobao.accs.internal.ServiceImpl$1, reason: invalid class name */
    class AnonymousClass1 extends IMessageService.Stub {
        public boolean ping() throws RemoteException {
            return true;
        }

        AnonymousClass1() {
        }

        public void probe() throws RemoteException {
            ALog.d("ServiceImpl", "ReceiverImpl probeTaoBao begin......messageServiceBinder [probe]", new Object[0]);
            ThreadPoolExecutorFactory.execute(new c(this));
        }
    }

    @Override // com.taobao.accs.internal.b, com.taobao.accs.base.IBaseService
    public void onDestroy() {
        super.onDestroy();
        ALog.e("ServiceImpl", "Service onDestroy", new Object[0]);
        UtilityImpl.a(this.c, com.taobao.accs.common.Constants.SP_KEY_SERVICE_END, System.currentTimeMillis());
        this.b = null;
        this.c = null;
        e();
        Process.killProcess(Process.myPid());
    }

    private synchronized void b() {
        if (a != null && a.size() != 0) {
            for (Map.Entry<String, com.taobao.accs.net.b> entry : a.entrySet()) {
                com.taobao.accs.net.b value = entry.getValue();
                if (value == null) {
                    ALog.e("ServiceImpl", "tryConnect connection null", "appkey", value.i());
                    return;
                }
                ALog.i("ServiceImpl", "tryConnect", "appkey", value.i(), com.taobao.accs.common.Constants.KEY_CONFIG_TAG, entry.getKey());
                if (value.l() && TextUtils.isEmpty(value.i.getAppSecret())) {
                    ALog.e("ServiceImpl", "tryConnect secret is null", new Object[0]);
                } else {
                    value.a();
                }
            }
            return;
        }
        ALog.w("ServiceImpl", "tryConnect no connections", new Object[0]);
    }

    private void a(Message message, boolean z) {
        if (a == null || a.size() == 0) {
            return;
        }
        Iterator<Map.Entry<String, com.taobao.accs.net.b>> it = a.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().b(message, z);
        }
    }

    private void a(boolean z, boolean z2) {
        if (a == null || a.size() == 0) {
            return;
        }
        Iterator<Map.Entry<String, com.taobao.accs.net.b>> it = a.entrySet().iterator();
        while (it.hasNext()) {
            com.taobao.accs.net.b value = it.next().getValue();
            value.a(z, z2);
            ALog.i("ServiceImpl", "ping connection", "appkey", value.i());
        }
    }

    private void c() {
        if (a == null || a.size() == 0) {
            return;
        }
        Iterator<Map.Entry<String, com.taobao.accs.net.b>> it = a.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().b();
        }
    }

    private void d() {
        if (a == null || a.size() == 0) {
            return;
        }
        Iterator<Map.Entry<String, com.taobao.accs.net.b>> it = a.entrySet().iterator();
        while (it.hasNext()) {
            com.taobao.accs.ut.a.c cVarC = it.next().getValue().c();
            if (cVarC != null) {
                cVarC.h = this.d;
                cVarC.a();
            }
        }
    }

    private void e() {
        if (a == null || a.size() == 0) {
            return;
        }
        Iterator<Map.Entry<String, com.taobao.accs.net.b>> it = a.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().e();
        }
    }
}
