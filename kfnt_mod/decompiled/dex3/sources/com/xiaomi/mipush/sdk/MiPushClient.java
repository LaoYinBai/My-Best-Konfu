package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.text.TextUtils;
import com.ss.android.downloadlib.applink.AppLinkMonitor;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.xiaomi.clientreport.data.Config;
import com.xiaomi.clientreport.manager.ClientReportClient;
import com.xiaomi.mipush.sdk.MiTinyDataClient;
import com.xiaomi.push.ax;
import com.xiaomi.push.bm;
import com.xiaomi.push.dl;
import com.xiaomi.push.dm;
import com.xiaomi.push.ep;
import com.xiaomi.push.eq;
import com.xiaomi.push.er;
import com.xiaomi.push.fb;
import com.xiaomi.push.hj;
import com.xiaomi.push.hn;
import com.xiaomi.push.ho;
import com.xiaomi.push.ht;
import com.xiaomi.push.hw;
import com.xiaomi.push.hx;
import com.xiaomi.push.id;
import com.xiaomi.push.ii;
import com.xiaomi.push.ij;
import com.xiaomi.push.in;
import com.xiaomi.push.ip;
import com.xiaomi.push.ir;
import com.xiaomi.push.service.aq;
import com.xiaomi.push.service.receivers.NetworkStatusReceiver;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class MiPushClient {
    public static final String COMMAND_REGISTER = "register";
    public static final String COMMAND_SET_ACCEPT_TIME = "accept-time";
    public static final String COMMAND_SET_ACCOUNT = "set-account";
    public static final String COMMAND_SET_ALIAS = "set-alias";
    public static final String COMMAND_SUBSCRIBE_TOPIC = "subscribe-topic";
    public static final String COMMAND_UNREGISTER = "unregister";
    public static final String COMMAND_UNSET_ACCOUNT = "unset-account";
    public static final String COMMAND_UNSET_ALIAS = "unset-alias";
    public static final String COMMAND_UNSUBSCRIBE_TOPIC = "unsubscibe-topic";
    public static final String PREF_EXTRA = "mipush_extra";
    private static Context sContext;
    private static long sCurMsgId = System.currentTimeMillis();

    public static class CodeResult {
        private long resultCode = -1;

        public long getResultCode() {
            return this.resultCode;
        }

        protected void setResultCode(long j) {
            this.resultCode = j;
        }
    }

    public interface ICallbackResult<R> {
        void onResult(R r);
    }

    @Deprecated
    public static abstract class MiPushClientCallback {
        private String category;

        protected String getCategory() {
            return this.category;
        }

        public void onCommandResult(String str, long j, String str2, List<String> list) {
        }

        public void onInitializeResult(long j, String str, String str2) {
        }

        public void onReceiveMessage(MiPushMessage miPushMessage) {
        }

        public void onReceiveMessage(String str, String str2, String str3, boolean z) {
        }

        public void onSubscribeResult(long j, String str, String str2) {
        }

        public void onUnsubscribeResult(long j, String str, String str2) {
        }

        protected void setCategory(String str) {
            this.category = str;
        }
    }

    public static class TokenResult {
        private String token = null;
        private long resultCode = -1;

        public long getResultCode() {
            return this.resultCode;
        }

        public String getToken() {
            return this.token;
        }

        protected void setResultCode(long j) {
            this.resultCode = j;
        }

        protected void setToken(String str) {
            this.token = str;
        }
    }

    public interface UPSRegisterCallBack extends ICallbackResult<TokenResult> {
    }

    public interface UPSTurnCallBack extends ICallbackResult<CodeResult> {
    }

    public interface UPSUnRegisterCallBack extends ICallbackResult<TokenResult> {
    }

    private static boolean acceptTimeSet(Context context, String str, String str2) {
        return TextUtils.equals(getAcceptTime(context), str + Constants.ACCEPT_TIME_SEPARATOR_SP + str2);
    }

    public static long accountSetTime(Context context, String str) {
        return context.getSharedPreferences("mipush_extra", 0).getLong("account_" + str, -1L);
    }

    static synchronized void addAcceptTime(Context context, String str, String str2) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("mipush_extra", 0).edit();
        editorEdit.putString(Constants.EXTRA_KEY_ACCEPT_TIME, str + Constants.ACCEPT_TIME_SEPARATOR_SP + str2);
        com.xiaomi.push.r.a(editorEdit);
    }

    static synchronized void addAccount(Context context, String str) {
        context.getSharedPreferences("mipush_extra", 0).edit().putLong("account_" + str, System.currentTimeMillis()).commit();
    }

    static synchronized void addAlias(Context context, String str) {
        context.getSharedPreferences("mipush_extra", 0).edit().putLong("alias_" + str, System.currentTimeMillis()).commit();
    }

    private static void addPullNotificationTime(Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("mipush_extra", 0).edit();
        editorEdit.putLong("last_pull_notification", System.currentTimeMillis());
        com.xiaomi.push.r.a(editorEdit);
    }

    private static void addRegRequestTime(Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("mipush_extra", 0).edit();
        editorEdit.putLong("last_reg_request", System.currentTimeMillis());
        com.xiaomi.push.r.a(editorEdit);
    }

    static synchronized void addTopic(Context context, String str) {
        context.getSharedPreferences("mipush_extra", 0).edit().putLong("topic_" + str, System.currentTimeMillis()).commit();
    }

    public static long aliasSetTime(Context context, String str) {
        return context.getSharedPreferences("mipush_extra", 0).getLong("alias_" + str, -1L);
    }

    public static void awakeApps(final Context context, final String[] strArr) {
        com.xiaomi.push.ai.a(context).a(new Runnable() { // from class: com.xiaomi.mipush.sdk.MiPushClient.4
            @Override // java.lang.Runnable
            public void run() {
                PackageInfo packageInfo;
                try {
                    for (String str : strArr) {
                        if (!TextUtils.isEmpty(str) && (packageInfo = context.getPackageManager().getPackageInfo(str, 4)) != null) {
                            MiPushClient.awakePushServiceByPackageInfo(context, packageInfo);
                        }
                    }
                } catch (Throwable th) {
                    com.xiaomi.channel.commonutils.logger.b.a(th);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void awakePushServiceByPackageInfo(Context context, PackageInfo packageInfo) {
        ServiceInfo[] serviceInfoArr = packageInfo.services;
        if (serviceInfoArr != null) {
            for (ServiceInfo serviceInfo : serviceInfoArr) {
                if (serviceInfo.exported && serviceInfo.enabled && "com.xiaomi.mipush.sdk.PushMessageHandler".equals(serviceInfo.name) && !context.getPackageName().equals(serviceInfo.packageName)) {
                    try {
                        Thread.sleep(((long) ((Math.random() * 2.0d) + 1.0d)) * 1000);
                        Intent intent = new Intent();
                        intent.setClassName(serviceInfo.packageName, serviceInfo.name);
                        intent.setAction("com.xiaomi.mipush.sdk.WAKEUP");
                        intent.putExtra("waker_pkgname", context.getPackageName());
                        PushMessageHandler.a(context, intent);
                        return;
                    } catch (Throwable unused) {
                        return;
                    }
                }
            }
        }
    }

    private static void checkNotNull(Object obj, String str) {
        if (obj != null) {
            return;
        }
        throw new IllegalArgumentException("param " + str + " is not nullable");
    }

    protected static void clearExtras(Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("mipush_extra", 0).edit();
        editorEdit.clear();
        editorEdit.commit();
    }

    public static void clearLocalNotificationType(Context context) {
        an.a(context).m62e();
    }

    public static void clearNotification(Context context) {
        an.a(context).a(-1);
    }

    public static void clearNotification(Context context, int i) {
        an.a(context).a(i);
    }

    public static void clearNotification(Context context, String str, String str2) {
        an.a(context).a(str, str2);
    }

    public static void disablePush(Context context) {
        an.a(context).a(true);
    }

    public static void enablePush(Context context) {
        an.a(context).a(false);
    }

    protected static String getAcceptTime(Context context) {
        return context.getSharedPreferences("mipush_extra", 0).getString(Constants.EXTRA_KEY_ACCEPT_TIME, "00:00-23:59");
    }

    public static List<String> getAllAlias(Context context) {
        ArrayList arrayList = new ArrayList();
        for (String str : context.getSharedPreferences("mipush_extra", 0).getAll().keySet()) {
            if (str.startsWith("alias_")) {
                arrayList.add(str.substring("alias_".length()));
            }
        }
        return arrayList;
    }

    public static List<String> getAllTopic(Context context) {
        ArrayList arrayList = new ArrayList();
        for (String str : context.getSharedPreferences("mipush_extra", 0).getAll().keySet()) {
            if (str.startsWith("topic_") && !str.contains("**ALL**")) {
                arrayList.add(str.substring("topic_".length()));
            }
        }
        return arrayList;
    }

    public static List<String> getAllUserAccount(Context context) {
        ArrayList arrayList = new ArrayList();
        for (String str : context.getSharedPreferences("mipush_extra", 0).getAll().keySet()) {
            if (str.startsWith("account_")) {
                arrayList.add(str.substring("account_".length()));
            }
        }
        return arrayList;
    }

    public static String getAppRegion(Context context) {
        if (b.m63a(context).m72c()) {
            return b.m63a(context).f();
        }
        return null;
    }

    private static boolean getDefaultSwitch() {
        return com.xiaomi.push.l.m550b();
    }

    protected static boolean getOpenFCMPush(Context context) {
        checkNotNull(context, "context");
        return f.a(context).b(e.ASSEMBLE_PUSH_FCM);
    }

    protected static boolean getOpenHmsPush(Context context) {
        checkNotNull(context, "context");
        return f.a(context).b(e.ASSEMBLE_PUSH_HUAWEI);
    }

    protected static boolean getOpenOPPOPush(Context context) {
        checkNotNull(context, "context");
        return f.a(context).b(e.ASSEMBLE_PUSH_COS);
    }

    protected static boolean getOpenVIVOPush(Context context) {
        return f.a(context).b(e.ASSEMBLE_PUSH_FTOS);
    }

    public static String getRegId(Context context) {
        if (b.m63a(context).m72c()) {
            return b.m63a(context).m71c();
        }
        return null;
    }

    private static void initEventPerfLogic(final Context context) {
        er.a(new er.a() { // from class: com.xiaomi.mipush.sdk.MiPushClient.5
            @Override // com.xiaomi.push.er.a
            public void uploader(Context context2, hn hnVar) {
                MiTinyDataClient.upload(context2, hnVar);
            }
        });
        Config configA = er.a(context);
        com.xiaomi.clientreport.manager.a.a(context).a("3_8_2");
        ClientReportClient.init(context, configA, new ep(context), new eq(context));
        a.a(context);
        s.a(context, configA);
        com.xiaomi.push.service.aq.a(context).a(new aq.a(100, "perf event job update") { // from class: com.xiaomi.mipush.sdk.MiPushClient.6
            @Override // com.xiaomi.push.service.aq.a
            protected void onCallback() {
                er.m293a(context);
            }
        });
    }

    @Deprecated
    public static void initialize(Context context, String str, String str2, MiPushClientCallback miPushClientCallback) {
        initialize(context, str, str2, miPushClientCallback, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void initialize(Context context, String str, String str2, MiPushClientCallback miPushClientCallback, String str3, ICallbackResult iCallbackResult) {
        try {
            com.xiaomi.channel.commonutils.logger.b.a(context.getApplicationContext());
            com.xiaomi.channel.commonutils.logger.b.e("sdk_version = 3_8_2");
            if (miPushClientCallback != null) {
                PushMessageHandler.a(miPushClientCallback);
            }
            if (iCallbackResult != null) {
                PushMessageHandler.a(iCallbackResult);
            }
            if (com.xiaomi.push.t.m647a(sContext)) {
                u.a(sContext);
            }
            boolean z = b.m63a(sContext).a() != Constants.a();
            if (!z && !shouldSendRegRequest(sContext)) {
                an.a(sContext).m54a();
                com.xiaomi.channel.commonutils.logger.b.m22a("Could not send  register message within 5s repeatly .");
                return;
            }
            if (z || !b.m63a(sContext).a(str, str2) || b.m63a(sContext).m74e()) {
                String strA = bm.a(6);
                b.m63a(sContext).m65a();
                b.m63a(sContext).a(Constants.a());
                b.m63a(sContext).a(str, str2, strA);
                MiTinyDataClient.a.a().b(MiTinyDataClient.PENDING_REASON_APPID);
                clearExtras(sContext);
                clearNotification(context);
                ij ijVar = new ij();
                ijVar.a(com.xiaomi.push.service.at.a());
                ijVar.b(str);
                ijVar.e(str2);
                ijVar.d(sContext.getPackageName());
                ijVar.f(strA);
                ijVar.c(com.xiaomi.push.g.m345a(sContext, sContext.getPackageName()));
                ijVar.b(com.xiaomi.push.g.a(sContext, sContext.getPackageName()));
                ijVar.h("3_8_2");
                ijVar.a(30802);
                ijVar.a(hx.Init);
                if (!TextUtils.isEmpty(str3)) {
                    ijVar.g(str3);
                }
                if (!com.xiaomi.push.l.d()) {
                    String strG = com.xiaomi.push.i.g(sContext);
                    if (!TextUtils.isEmpty(strG)) {
                        ijVar.i(bm.a(strG) + Constants.ACCEPT_TIME_SEPARATOR_SP + com.xiaomi.push.i.j(sContext));
                    }
                }
                int iA = com.xiaomi.push.i.a();
                if (iA >= 0) {
                    ijVar.c(iA);
                }
                an.a(sContext).a(ijVar, z);
                sContext.getSharedPreferences("mipush_extra", 4).getBoolean("mipush_registed", true);
            } else {
                if (1 == PushMessageHelper.getPushMode(sContext)) {
                    checkNotNull(miPushClientCallback, "callback");
                    miPushClientCallback.onInitializeResult(0L, null, b.m63a(sContext).m71c());
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(b.m63a(sContext).m71c());
                    PushMessageHelper.sendCommandMessageBroadcast(sContext, PushMessageHelper.generateCommandMessage(fb.COMMAND_REGISTER.f347a, arrayList, 0L, null, null));
                }
                an.a(sContext).m54a();
                if (b.m63a(sContext).m67a()) {
                    ii iiVar = new ii();
                    iiVar.b(b.m63a(sContext).m64a());
                    iiVar.c(ht.ClientInfoUpdate.f509a);
                    iiVar.a(com.xiaomi.push.service.at.a());
                    iiVar.f650a = new HashMap();
                    iiVar.f650a.put("app_version", com.xiaomi.push.g.m345a(sContext, sContext.getPackageName()));
                    iiVar.f650a.put(Constants.EXTRA_KEY_APP_VERSION_CODE, Integer.toString(com.xiaomi.push.g.a(sContext, sContext.getPackageName())));
                    iiVar.f650a.put("push_sdk_vn", "3_8_2");
                    iiVar.f650a.put("push_sdk_vc", Integer.toString(30802));
                    com.xiaomi.push.i.a(sContext, iiVar.f650a);
                    String strE = b.m63a(sContext).e();
                    if (!TextUtils.isEmpty(strE)) {
                        iiVar.f650a.put("deviceid", strE);
                    }
                    an.a(sContext).a((an) iiVar, hj.Notification, false, (hw) null);
                }
                if (!com.xiaomi.push.n.m551a(sContext, "update_devId", false)) {
                    updateImeiOrOaid();
                    com.xiaomi.push.n.a(sContext, "update_devId", true);
                }
                String strC = com.xiaomi.push.i.c(sContext);
                if (!TextUtils.isEmpty(strC)) {
                    id idVar = new id();
                    idVar.a(com.xiaomi.push.service.at.a());
                    idVar.b(str);
                    idVar.c(fb.COMMAND_CHK_VDEVID.f347a);
                    ArrayList arrayList2 = new ArrayList();
                    String strB = com.xiaomi.push.i.b(sContext);
                    if (!TextUtils.isEmpty(strB)) {
                        arrayList2.add(strB);
                    }
                    if (strC == null) {
                        strC = "";
                    }
                    arrayList2.add(strC);
                    arrayList2.add(Build.MODEL != null ? Build.MODEL : "");
                    arrayList2.add(Build.BOARD != null ? Build.BOARD : "");
                    idVar.a(arrayList2);
                    an.a(sContext).a((an) idVar, hj.Command, false, (hw) null);
                }
                if (shouldUseMIUIPush(sContext) && shouldPullNotification(sContext)) {
                    ii iiVar2 = new ii();
                    iiVar2.b(b.m63a(sContext).m64a());
                    iiVar2.c(ht.PullOfflineMessage.f509a);
                    iiVar2.a(com.xiaomi.push.service.at.a());
                    iiVar2.a(false);
                    an.a(sContext).a((an) iiVar2, hj.Notification, false, (hw) null, false);
                    addPullNotificationTime(sContext);
                }
            }
            addRegRequestTime(sContext);
            scheduleOcVersionCheckJob();
            scheduleDataCollectionJobs(sContext);
            initEventPerfLogic(sContext);
            au.a(sContext);
            if (!sContext.getPackageName().equals("com.xiaomi.xmsf")) {
                if (Logger.getUserLogger() != null) {
                    Logger.setLogger(sContext, Logger.getUserLogger());
                }
                com.xiaomi.channel.commonutils.logger.b.a(2);
            }
            operateSyncAction(context);
        } catch (Throwable th) {
            com.xiaomi.channel.commonutils.logger.b.a(th);
        }
    }

    private static void operateSyncAction(Context context) {
        if ("syncing".equals(ae.a(sContext).a(at.DISABLE_PUSH))) {
            disablePush(sContext);
        }
        if ("syncing".equals(ae.a(sContext).a(at.ENABLE_PUSH))) {
            enablePush(sContext);
        }
        if ("syncing".equals(ae.a(sContext).a(at.UPLOAD_HUAWEI_TOKEN))) {
            syncAssemblePushToken(sContext);
        }
        if ("syncing".equals(ae.a(sContext).a(at.UPLOAD_FCM_TOKEN))) {
            syncAssembleFCMPushToken(sContext);
        }
        if ("syncing".equals(ae.a(sContext).a(at.UPLOAD_COS_TOKEN))) {
            syncAssembleCOSPushToken(context);
        }
        if ("syncing".equals(ae.a(sContext).a(at.UPLOAD_FTOS_TOKEN))) {
            syncAssembleFTOSPushToken(context);
        }
    }

    public static void pausePush(Context context, String str) {
        setAcceptTime(context, 0, 0, 0, 0, str);
    }

    static void reInitialize(Context context, hx hxVar) {
        if (b.m63a(context).m72c()) {
            String strA = bm.a(6);
            String strM64a = b.m63a(context).m64a();
            String strB = b.m63a(context).b();
            b.m63a(context).m65a();
            clearNotification(context);
            b.m63a(context).a(Constants.a());
            b.m63a(context).a(strM64a, strB, strA);
            ij ijVar = new ij();
            ijVar.a(com.xiaomi.push.service.at.a());
            ijVar.b(strM64a);
            ijVar.e(strB);
            ijVar.f(strA);
            ijVar.d(context.getPackageName());
            ijVar.c(com.xiaomi.push.g.m345a(context, context.getPackageName()));
            ijVar.a(hxVar);
            an.a(context).a(ijVar, false);
        }
    }

    @Deprecated
    public static void registerCrashHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);
    }

    private static void registerNetworkReceiver(Context context) {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            intentFilter.addCategory("android.intent.category.DEFAULT");
            context.getApplicationContext().registerReceiver(new NetworkStatusReceiver(null), intentFilter);
        } catch (Throwable th) {
            com.xiaomi.channel.commonutils.logger.b.a(th);
        }
    }

    public static void registerPush(Context context, String str, String str2) {
        registerPush(context, str, str2, new PushConfiguration());
    }

    public static void registerPush(Context context, String str, String str2, PushConfiguration pushConfiguration) {
        registerPush(context, str, str2, pushConfiguration, null, null);
    }

    private static void registerPush(Context context, final String str, final String str2, PushConfiguration pushConfiguration, final String str3, final ICallbackResult iCallbackResult) {
        checkNotNull(context, "context");
        checkNotNull(str, AppIdManager.KEY_APP_ID_IN_SP);
        checkNotNull(str2, "appToken");
        sContext = context.getApplicationContext();
        if (sContext == null) {
            sContext = context;
        }
        Context context2 = sContext;
        com.xiaomi.push.t.m645a(context2);
        if (!NetworkStatusReceiver.a()) {
            registerNetworkReceiver(sContext);
        }
        f.a(sContext).a(pushConfiguration);
        com.xiaomi.push.ai.a(context2).a(new Runnable() { // from class: com.xiaomi.mipush.sdk.MiPushClient.1
            @Override // java.lang.Runnable
            public void run() {
                MiPushClient.initialize(MiPushClient.sContext, str, str2, null, str3, iCallbackResult);
            }
        });
    }

    public static void registerPush(Context context, String str, String str2, String str3) {
        registerPush(context, str, str2, new PushConfiguration(), str3, null);
    }

    public static void registerToken(Context context, String str, String str2, String str3, UPSRegisterCallBack uPSRegisterCallBack) {
        registerPush(context, str, str2, new PushConfiguration(), null, uPSRegisterCallBack);
    }

    static synchronized void removeAcceptTime(Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("mipush_extra", 0).edit();
        editorEdit.remove(Constants.EXTRA_KEY_ACCEPT_TIME);
        com.xiaomi.push.r.a(editorEdit);
    }

    static synchronized void removeAccount(Context context, String str) {
        context.getSharedPreferences("mipush_extra", 0).edit().remove("account_" + str).commit();
    }

    static synchronized void removeAlias(Context context, String str) {
        context.getSharedPreferences("mipush_extra", 0).edit().remove("alias_" + str).commit();
    }

    static synchronized void removeAllAccounts(Context context) {
        Iterator<String> it = getAllUserAccount(context).iterator();
        while (it.hasNext()) {
            removeAccount(context, it.next());
        }
    }

    static synchronized void removeAllAliases(Context context) {
        Iterator<String> it = getAllAlias(context).iterator();
        while (it.hasNext()) {
            removeAlias(context, it.next());
        }
    }

    static synchronized void removeAllTopics(Context context) {
        Iterator<String> it = getAllTopic(context).iterator();
        while (it.hasNext()) {
            removeTopic(context, it.next());
        }
    }

    static synchronized void removeTopic(Context context, String str) {
        context.getSharedPreferences("mipush_extra", 0).edit().remove("topic_" + str).commit();
    }

    public static void reportAppRunInBackground(Context context, boolean z) {
        if (b.m63a(context).m70b()) {
            ht htVar = z ? ht.APP_SLEEP : ht.APP_WAKEUP;
            ii iiVar = new ii();
            iiVar.b(b.m63a(context).m64a());
            iiVar.c(htVar.f509a);
            iiVar.d(context.getPackageName());
            iiVar.a(com.xiaomi.push.service.at.a());
            iiVar.a(false);
            an.a(context).a((an) iiVar, hj.Notification, false, (hw) null, false);
        }
    }

    static void reportIgnoreRegMessageClicked(Context context, String str, hw hwVar, String str2, String str3) {
        ii iiVar = new ii();
        if (TextUtils.isEmpty(str3)) {
            com.xiaomi.channel.commonutils.logger.b.d("do not report clicked message");
            return;
        }
        iiVar.b(str3);
        iiVar.c("bar:click");
        iiVar.a(str);
        iiVar.a(false);
        an.a(context).a(iiVar, hj.Notification, false, true, hwVar, true, str2, str3);
    }

    public static void reportMessageClicked(Context context, MiPushMessage miPushMessage) {
        hw hwVar = new hw();
        hwVar.a(miPushMessage.getMessageId());
        hwVar.b(miPushMessage.getTopic());
        hwVar.d(miPushMessage.getDescription());
        hwVar.c(miPushMessage.getTitle());
        hwVar.c(miPushMessage.getNotifyId());
        hwVar.a(miPushMessage.getNotifyType());
        hwVar.b(miPushMessage.getPassThrough());
        hwVar.a(miPushMessage.getExtra());
        reportMessageClicked(context, miPushMessage.getMessageId(), hwVar, null);
    }

    @Deprecated
    public static void reportMessageClicked(Context context, String str) {
        reportMessageClicked(context, str, null, null);
    }

    static void reportMessageClicked(Context context, String str, hw hwVar, String str2) {
        ii iiVar = new ii();
        if (TextUtils.isEmpty(str2)) {
            if (!b.m63a(context).m70b()) {
                com.xiaomi.channel.commonutils.logger.b.d("do not report clicked message");
                return;
            }
            str2 = b.m63a(context).m64a();
        }
        iiVar.b(str2);
        iiVar.c("bar:click");
        iiVar.a(str);
        iiVar.a(false);
        an.a(context).a((an) iiVar, hj.Notification, false, hwVar);
    }

    public static void resumePush(Context context, String str) {
        setAcceptTime(context, 0, 0, 23, 59, str);
    }

    private static void scheduleDataCollectionJobs(Context context) {
        if (com.xiaomi.push.service.aq.a(sContext).a(ho.DataCollectionSwitch.a(), getDefaultSwitch())) {
            dl.a().a(new r(context));
            com.xiaomi.push.ai.a(sContext).a(new Runnable() { // from class: com.xiaomi.mipush.sdk.MiPushClient.2
                @Override // java.lang.Runnable
                public void run() {
                    dm.a(MiPushClient.sContext);
                }
            }, 10);
        }
    }

    private static void scheduleOcVersionCheckJob() {
        com.xiaomi.push.ai.a(sContext).a(new ad(sContext), com.xiaomi.push.service.aq.a(sContext).a(ho.OcVersionCheckFrequency.a(), com.ss.android.socialbase.appdownloader.constants.Constants.MAX_RETRY_AFTER), 5);
    }

    public static void setAcceptTime(Context context, int i, int i2, int i3, int i4, String str) {
        if (i < 0 || i >= 24 || i3 < 0 || i3 >= 24 || i2 < 0 || i2 >= 60 || i4 < 0 || i4 >= 60) {
            throw new IllegalArgumentException("the input parameter is not valid.");
        }
        long rawOffset = ((TimeZone.getTimeZone("GMT+08").getRawOffset() - TimeZone.getDefault().getRawOffset()) / 1000) / 60;
        long j = ((((i * 60) + i2) + rawOffset) + 1440) % 1440;
        long j2 = ((((i3 * 60) + i4) + rawOffset) + 1440) % 1440;
        ArrayList arrayList = new ArrayList();
        arrayList.add(String.format("%1$02d:%2$02d", Long.valueOf(j / 60), Long.valueOf(j % 60)));
        arrayList.add(String.format("%1$02d:%2$02d", Long.valueOf(j2 / 60), Long.valueOf(j2 % 60)));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(String.format("%1$02d:%2$02d", Integer.valueOf(i), Integer.valueOf(i2)));
        arrayList2.add(String.format("%1$02d:%2$02d", Integer.valueOf(i3), Integer.valueOf(i4)));
        if (!acceptTimeSet(context, (String) arrayList.get(0), (String) arrayList.get(1))) {
            setCommand(context, fb.COMMAND_SET_ACCEPT_TIME.f347a, (ArrayList<String>) arrayList, str);
        } else if (1 == PushMessageHelper.getPushMode(context)) {
            PushMessageHandler.a(context, str, fb.COMMAND_SET_ACCEPT_TIME.f347a, 0L, null, arrayList2);
        } else {
            PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(fb.COMMAND_SET_ACCEPT_TIME.f347a, arrayList2, 0L, null, null));
        }
    }

    public static void setAlias(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        setCommand(context, fb.COMMAND_SET_ALIAS.f347a, str, str2);
    }

    protected static void setCommand(Context context, String str, String str2, String str3) {
        StringBuilder sb;
        String str4;
        fb fbVar;
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
        }
        if (fb.COMMAND_SET_ALIAS.f347a.equalsIgnoreCase(str) && Math.abs(System.currentTimeMillis() - aliasSetTime(context, str2)) < 86400000) {
            if (1 != PushMessageHelper.getPushMode(context)) {
                fbVar = fb.COMMAND_SET_ALIAS;
                PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(fbVar.f347a, arrayList, 0L, null, str3));
                return;
            }
            PushMessageHandler.a(context, str3, str, 0L, null, arrayList);
            return;
        }
        if (fb.COMMAND_UNSET_ALIAS.f347a.equalsIgnoreCase(str) && aliasSetTime(context, str2) < 0) {
            sb = new StringBuilder();
            str4 = "Don't cancel alias for ";
        } else {
            if (fb.COMMAND_SET_ACCOUNT.f347a.equalsIgnoreCase(str) && Math.abs(System.currentTimeMillis() - accountSetTime(context, str2)) < 3600000) {
                if (1 != PushMessageHelper.getPushMode(context)) {
                    fbVar = fb.COMMAND_SET_ACCOUNT;
                    PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(fbVar.f347a, arrayList, 0L, null, str3));
                    return;
                }
                PushMessageHandler.a(context, str3, str, 0L, null, arrayList);
                return;
            }
            if (!fb.COMMAND_UNSET_ACCOUNT.f347a.equalsIgnoreCase(str) || accountSetTime(context, str2) >= 0) {
                setCommand(context, str, (ArrayList<String>) arrayList, str3);
                return;
            } else {
                sb = new StringBuilder();
                str4 = "Don't cancel account for ";
            }
        }
        sb.append(str4);
        sb.append(bm.a(arrayList.toString(), 3));
        sb.append(" is unseted");
        com.xiaomi.channel.commonutils.logger.b.m22a(sb.toString());
    }

    protected static void setCommand(Context context, String str, ArrayList<String> arrayList, String str2) {
        if (TextUtils.isEmpty(b.m63a(context).m64a())) {
            return;
        }
        id idVar = new id();
        String strA = com.xiaomi.push.service.at.a();
        idVar.a(strA);
        idVar.b(b.m63a(context).m64a());
        idVar.c(str);
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            idVar.m452a(it.next());
        }
        idVar.e(str2);
        idVar.d(context.getPackageName());
        com.xiaomi.channel.commonutils.logger.b.e("cmd:" + str + ", " + strA);
        an.a(context).a((an) idVar, hj.Command, (hw) null);
    }

    public static void setLocalNotificationType(Context context, int i) {
        an.a(context).b(i & (-1));
    }

    public static void setUserAccount(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        setCommand(context, fb.COMMAND_SET_ACCOUNT.f347a, str, str2);
    }

    private static boolean shouldPullNotification(Context context) {
        return Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_pull_notification", -1L)) > 300000;
    }

    private static boolean shouldSendRegRequest(Context context) {
        return Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_reg_request", -1L)) > AppLinkMonitor.DELAY_TIME;
    }

    public static boolean shouldUseMIUIPush(Context context) {
        return an.a(context).m56a();
    }

    public static void subscribe(Context context, String str, String str2) {
        if (TextUtils.isEmpty(b.m63a(context).m64a()) || TextUtils.isEmpty(str)) {
            return;
        }
        if (Math.abs(System.currentTimeMillis() - topicSubscribedTime(context, str)) <= 86400000) {
            if (1 == PushMessageHelper.getPushMode(context)) {
                PushMessageHandler.a(context, str2, 0L, null, str);
                return;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(fb.COMMAND_SUBSCRIBE_TOPIC.f347a, arrayList, 0L, null, null));
            return;
        }
        in inVar = new in();
        String strA = com.xiaomi.push.service.at.a();
        inVar.a(strA);
        inVar.b(b.m63a(context).m64a());
        inVar.c(str);
        inVar.d(context.getPackageName());
        inVar.e(str2);
        com.xiaomi.channel.commonutils.logger.b.e("cmd:" + fb.COMMAND_SUBSCRIBE_TOPIC + ", " + strA);
        an.a(context).a((an) inVar, hj.Subscription, (hw) null);
    }

    public static void syncAssembleCOSPushToken(Context context) {
        an.a(context).a((String) null, at.UPLOAD_COS_TOKEN, e.ASSEMBLE_PUSH_COS);
    }

    public static void syncAssembleFCMPushToken(Context context) {
        an.a(context).a((String) null, at.UPLOAD_FCM_TOKEN, e.ASSEMBLE_PUSH_FCM);
    }

    public static void syncAssembleFTOSPushToken(Context context) {
        an.a(context).a((String) null, at.UPLOAD_FTOS_TOKEN, e.ASSEMBLE_PUSH_FTOS);
    }

    public static void syncAssemblePushToken(Context context) {
        an.a(context).a((String) null, at.UPLOAD_HUAWEI_TOKEN, e.ASSEMBLE_PUSH_HUAWEI);
    }

    public static long topicSubscribedTime(Context context, String str) {
        return context.getSharedPreferences("mipush_extra", 0).getLong("topic_" + str, -1L);
    }

    public static void turnOffPush(Context context, UPSTurnCallBack uPSTurnCallBack) {
        disablePush(context);
        if (uPSTurnCallBack != null) {
            CodeResult codeResult = new CodeResult();
            codeResult.setResultCode(0L);
            codeResult.getResultCode();
            uPSTurnCallBack.onResult(codeResult);
        }
    }

    public static void turnOnPush(Context context, UPSTurnCallBack uPSTurnCallBack) {
        enablePush(context);
        if (uPSTurnCallBack != null) {
            CodeResult codeResult = new CodeResult();
            codeResult.setResultCode(0L);
            codeResult.getResultCode();
            uPSTurnCallBack.onResult(codeResult);
        }
    }

    public static void unRegisterToken(Context context, UPSUnRegisterCallBack uPSUnRegisterCallBack) {
        unregisterPush(context);
        if (uPSUnRegisterCallBack != null) {
            TokenResult tokenResult = new TokenResult();
            tokenResult.setToken(null);
            tokenResult.getToken();
            tokenResult.setResultCode(0L);
            tokenResult.getResultCode();
            uPSUnRegisterCallBack.onResult(tokenResult);
        }
    }

    public static void unregisterPush(Context context) {
        i.c(context);
        com.xiaomi.push.service.aq.a(context).a();
        if (b.m63a(context).m70b()) {
            ip ipVar = new ip();
            ipVar.a(com.xiaomi.push.service.at.a());
            ipVar.b(b.m63a(context).m64a());
            ipVar.c(b.m63a(context).m71c());
            ipVar.e(b.m63a(context).b());
            ipVar.d(context.getPackageName());
            an.a(context).a(ipVar);
            PushMessageHandler.a();
            PushMessageHandler.b();
            b.m63a(context).m69b();
            clearLocalNotificationType(context);
            clearNotification(context);
            clearExtras(context);
        }
    }

    public static void unsetAlias(Context context, String str, String str2) {
        setCommand(context, fb.COMMAND_UNSET_ALIAS.f347a, str, str2);
    }

    public static void unsetUserAccount(Context context, String str, String str2) {
        setCommand(context, fb.COMMAND_UNSET_ACCOUNT.f347a, str, str2);
    }

    public static void unsubscribe(Context context, String str, String str2) {
        if (b.m63a(context).m70b()) {
            if (topicSubscribedTime(context, str) < 0) {
                com.xiaomi.channel.commonutils.logger.b.m22a("Don't cancel subscribe for " + str + " is unsubscribed");
                return;
            }
            ir irVar = new ir();
            String strA = com.xiaomi.push.service.at.a();
            irVar.a(strA);
            irVar.b(b.m63a(context).m64a());
            irVar.c(str);
            irVar.d(context.getPackageName());
            irVar.e(str2);
            com.xiaomi.channel.commonutils.logger.b.e("cmd:" + fb.COMMAND_UNSUBSCRIBE_TOPIC + ", " + strA);
            an.a(context).a((an) irVar, hj.UnSubscription, (hw) null);
        }
    }

    private static void updateImeiOrOaid() {
        new Thread(new Runnable() { // from class: com.xiaomi.mipush.sdk.MiPushClient.3
            @Override // java.lang.Runnable
            public void run() {
                if (com.xiaomi.push.l.d()) {
                    return;
                }
                if (com.xiaomi.push.i.f(MiPushClient.sContext) != null || ax.a(MiPushClient.sContext).mo101a()) {
                    ii iiVar = new ii();
                    iiVar.b(b.m63a(MiPushClient.sContext).m64a());
                    iiVar.c(ht.ClientInfoUpdate.f509a);
                    iiVar.a(com.xiaomi.push.service.at.a());
                    iiVar.a(new HashMap());
                    String str = "";
                    String strF = com.xiaomi.push.i.f(MiPushClient.sContext);
                    if (!TextUtils.isEmpty(strF)) {
                        str = "" + bm.a(strF);
                    }
                    String strH = com.xiaomi.push.i.h(MiPushClient.sContext);
                    if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(strH)) {
                        str = str + Constants.ACCEPT_TIME_SEPARATOR_SP + strH;
                    }
                    if (!TextUtils.isEmpty(str)) {
                        iiVar.m477a().put(Constants.EXTRA_KEY_IMEI_MD5, str);
                    }
                    ax.a(MiPushClient.sContext).a(iiVar.m477a());
                    com.xiaomi.push.i.a(MiPushClient.sContext, iiVar.f650a);
                    int iA = com.xiaomi.push.i.a();
                    if (iA >= 0) {
                        iiVar.m477a().put("space_id", Integer.toString(iA));
                    }
                    an.a(MiPushClient.sContext).a((an) iiVar, hj.Notification, false, (hw) null);
                }
            }
        }).start();
    }
}
