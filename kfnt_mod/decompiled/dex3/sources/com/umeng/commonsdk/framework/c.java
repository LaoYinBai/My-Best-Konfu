package com.umeng.commonsdk.framework;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.common.ULog;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c {
    public static final String a = "content";
    public static final String b = "header";
    public static final String c = "exception";
    private static HandlerThread d = null;
    private static Handler e = null;
    private static b f = null;
    private static Object g = new Object();
    private static final int h = 768;
    private static final int i = 769;
    private static final int j = 770;
    private static final int k = 784;

    public static void a(UMSenderStateNotify uMSenderStateNotify) {
        if (f != null) {
            b.a(uMSenderStateNotify);
        }
    }

    public static void a(Context context, int i2, UMLogDataProtocol uMLogDataProtocol, Object obj) throws Throwable {
        if (context == null || uMLogDataProtocol == null) {
            ULog.d("--->>> Context or UMLogDataProtocol parameter cannot be null!");
            return;
        }
        UMModuleRegister.registerAppContext(context.getApplicationContext());
        if (UMModuleRegister.registerCallback(i2, uMLogDataProtocol)) {
            if (d == null || e == null) {
                e();
            }
            try {
                if (e != null) {
                    if (UMGlobalContext.getInstance().isMainProcess(context)) {
                        synchronized (g) {
                            if (f == null) {
                                UMFrUtils.syncLegacyEnvelopeIfNeeded(context);
                                f = new b(context, e);
                            }
                        }
                    }
                    Message messageObtainMessage = e.obtainMessage();
                    messageObtainMessage.what = h;
                    messageObtainMessage.arg1 = i2;
                    messageObtainMessage.obj = obj;
                    e.sendMessage(messageObtainMessage);
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(UMModuleRegister.getAppContext(), th);
            }
        }
    }

    private c() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d() throws Throwable {
        JSONObject jSONObjectBuildEnvelopeWithExtHeader;
        ULog.d("--->>> delayProcess Enter...");
        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> delayProcess Enter...");
        Context appContext = UMModuleRegister.getAppContext();
        if (appContext == null || !UMFrUtils.isOnline(appContext)) {
            return;
        }
        long jMaxDataSpace = UMEnvelopeBuild.maxDataSpace(appContext);
        UMLogDataProtocol callbackFromModuleName = UMModuleRegister.getCallbackFromModuleName("analytics");
        JSONObject jSONObject = null;
        if (callbackFromModuleName != null) {
            try {
                jSONObject = callbackFromModuleName.setupReportData(jMaxDataSpace);
                if (jSONObject == null) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> analyticsCB.setupReportData() return null");
                    return;
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(appContext, th);
                return;
            }
        }
        if (jSONObject == null || jSONObject.length() <= 0) {
            return;
        }
        JSONObject jSONObject2 = (JSONObject) jSONObject.opt("header");
        JSONObject jSONObject3 = (JSONObject) jSONObject.opt(a);
        if (appContext == null || jSONObject2 == null || jSONObject3 == null || (jSONObjectBuildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(appContext, jSONObject2, jSONObject3)) == null) {
            return;
        }
        try {
            if (jSONObjectBuildEnvelopeWithExtHeader.has(c)) {
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> autoProcess: Build envelope error code: " + jSONObjectBuildEnvelopeWithExtHeader.getInt(c));
            }
        } catch (Throwable unused) {
        }
        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> autoProcess: removeCacheData ... ");
        callbackFromModuleName.removeCacheData(jSONObjectBuildEnvelopeWithExtHeader);
    }

    public static void a(long j2) {
        if (e != null) {
            if (e.hasMessages(j)) {
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> MSG_DELAY_PROCESS has exist. do nothing.");
                return;
            }
            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> MSG_DELAY_PROCESS not exist. send it.");
            Message messageObtainMessage = e.obtainMessage();
            messageObtainMessage.what = j;
            e.sendMessageDelayed(messageObtainMessage, j2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0034 A[Catch: all -> 0x003b, TRY_LEAVE, TryCatch #1 {, blocks: (B:4:0x0003, B:5:0x0008, B:7:0x000c, B:9:0x001e, B:13:0x0034, B:12:0x002d), top: B:21:0x0003, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static synchronized void e() {
        /*
            java.lang.Class<com.umeng.commonsdk.framework.c> r0 = com.umeng.commonsdk.framework.c.class
            monitor-enter(r0)
            java.lang.String r1 = "--->>> Dispatch: init Enter..."
            com.umeng.commonsdk.statistics.common.ULog.d(r1)     // Catch: java.lang.Throwable -> L3b
            android.os.HandlerThread r1 = com.umeng.commonsdk.framework.c.d     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L3b
            if (r1 != 0) goto L34
            android.os.HandlerThread r1 = new android.os.HandlerThread     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L3b
            java.lang.String r2 = "work_thread"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L3b
            com.umeng.commonsdk.framework.c.d = r1     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L3b
            android.os.HandlerThread r1 = com.umeng.commonsdk.framework.c.d     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L3b
            r1.start()     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L3b
            android.os.Handler r1 = com.umeng.commonsdk.framework.c.e     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L3b
            if (r1 != 0) goto L34
            com.umeng.commonsdk.framework.c$1 r1 = new com.umeng.commonsdk.framework.c$1     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L3b
            android.os.HandlerThread r2 = com.umeng.commonsdk.framework.c.d     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L3b
            android.os.Looper r2 = r2.getLooper()     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L3b
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L3b
            com.umeng.commonsdk.framework.c.e = r1     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L3b
            goto L34
        L2c:
            r1 = move-exception
            android.content.Context r2 = com.umeng.commonsdk.framework.UMModuleRegister.getAppContext()     // Catch: java.lang.Throwable -> L3b
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r2, r1)     // Catch: java.lang.Throwable -> L3b
        L34:
            java.lang.String r1 = "--->>> Dispatch: init Exit..."
            com.umeng.commonsdk.statistics.common.ULog.d(r1)     // Catch: java.lang.Throwable -> L3b
            monitor-exit(r0)
            return
        L3b:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.framework.c.e():void");
    }

    public static synchronized boolean a(int i2) {
        if (e == null) {
            return false;
        }
        return e.hasMessages(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Message message) throws Throwable {
        int i2 = message.arg1;
        Object obj = message.obj;
        UMLogDataProtocol callbackFromModuleName = UMModuleRegister.getCallbackFromModuleName(UMModuleRegister.eventType2ModuleName(i2));
        if (callbackFromModuleName != null) {
            ULog.d("--->>> dispatch:handleEvent: call back workEvent with msg type [ 0x" + Integer.toHexString(i2) + "]");
            callbackFromModuleName.workEvent(obj, i2);
        }
    }

    private static void f() {
        if (d != null) {
            d = null;
        }
        if (e != null) {
            e = null;
        }
        if (f != null) {
            f = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void g() throws Throwable {
        if (f == null || d == null) {
            return;
        }
        b.c();
        ULog.d("--->>> handleQuit: Quit dispatch thread.");
        d.quit();
        f();
    }

    public static void a() {
        if (e != null) {
            Message messageObtainMessage = e.obtainMessage();
            messageObtainMessage.what = k;
            e.sendMessage(messageObtainMessage);
        }
    }
}
