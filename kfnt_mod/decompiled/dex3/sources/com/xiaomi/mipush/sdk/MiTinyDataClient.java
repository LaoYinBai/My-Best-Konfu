package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.xiaomi.push.hj;
import com.xiaomi.push.hn;
import com.xiaomi.push.hw;
import com.xiaomi.push.ii;
import com.xiaomi.push.service.bo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MiTinyDataClient {
    public static final String PENDING_REASON_APPID = "com.xiaomi.xmpushsdk.tinydataPending.appId";
    public static final String PENDING_REASON_CHANNEL = "com.xiaomi.xmpushsdk.tinydataPending.channel";
    public static final String PENDING_REASON_INIT = "com.xiaomi.xmpushsdk.tinydataPending.init";

    public static class a {
        private static volatile a a;

        /* renamed from: a, reason: collision with other field name */
        private Context f48a;

        /* renamed from: a, reason: collision with other field name */
        private Boolean f50a;

        /* renamed from: a, reason: collision with other field name */
        private String f51a;

        /* renamed from: a, reason: collision with other field name */
        private C0164a f49a = new C0164a();

        /* renamed from: a, reason: collision with other field name */
        private final ArrayList<hn> f52a = new ArrayList<>();

        /* renamed from: com.xiaomi.mipush.sdk.MiTinyDataClient$a$a, reason: collision with other inner class name */
        public class C0164a {

            /* renamed from: a, reason: collision with other field name */
            private ScheduledFuture<?> f55a;

            /* renamed from: a, reason: collision with other field name */
            private ScheduledThreadPoolExecutor f56a = new ScheduledThreadPoolExecutor(1);

            /* renamed from: a, reason: collision with other field name */
            public final ArrayList<hn> f54a = new ArrayList<>();

            /* renamed from: a, reason: collision with other field name */
            private final Runnable f53a = new aa(this);

            public C0164a() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void a() {
                if (this.f55a == null) {
                    this.f55a = this.f56a.scheduleAtFixedRate(this.f53a, 1000L, 1000L, TimeUnit.MILLISECONDS);
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void b() {
                hn hnVarRemove = this.f54a.remove(0);
                for (ii iiVar : bo.a(Arrays.asList(hnVarRemove), a.this.f48a.getPackageName(), b.m63a(a.this.f48a).m64a(), 30720)) {
                    com.xiaomi.channel.commonutils.logger.b.c("MiTinyDataClient Send item by PushServiceClient.sendMessage(XmActionNotification)." + hnVarRemove.d());
                    an.a(a.this.f48a).a((an) iiVar, hj.Notification, true, (hw) null);
                }
            }

            public void a(hn hnVar) {
                this.f56a.execute(new z(this, hnVar));
            }
        }

        public static a a() {
            if (a == null) {
                synchronized (a.class) {
                    if (a == null) {
                        a = new a();
                    }
                }
            }
            return a;
        }

        private void a(hn hnVar) {
            synchronized (this.f52a) {
                if (!this.f52a.contains(hnVar)) {
                    this.f52a.add(hnVar);
                    if (this.f52a.size() > 100) {
                        this.f52a.remove(0);
                    }
                }
            }
        }

        private boolean a(Context context) throws PackageManager.NameNotFoundException {
            if (!an.a(context).m56a()) {
                return true;
            }
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4);
                if (packageInfo == null) {
                    return false;
                }
                return packageInfo.versionCode >= 108;
            } catch (Exception unused) {
                return false;
            }
        }

        private boolean b(Context context) throws PackageManager.NameNotFoundException {
            return b.m63a(context).m64a() == null && !a(this.f48a);
        }

        private boolean b(hn hnVar) {
            if (bo.a(hnVar, false)) {
                return false;
            }
            if (!this.f50a.booleanValue()) {
                this.f49a.a(hnVar);
                return true;
            }
            com.xiaomi.channel.commonutils.logger.b.c("MiTinyDataClient Send item by PushServiceClient.sendTinyData(ClientUploadDataItem)." + hnVar.d());
            an.a(this.f48a).a(hnVar);
            return true;
        }

        /* renamed from: a, reason: collision with other method in class */
        public void m36a(Context context) throws PackageManager.NameNotFoundException {
            if (context == null) {
                com.xiaomi.channel.commonutils.logger.b.m22a("context is null, MiTinyDataClientImp.init() failed.");
                return;
            }
            this.f48a = context;
            this.f50a = Boolean.valueOf(a(context));
            b(MiTinyDataClient.PENDING_REASON_INIT);
        }

        public synchronized void a(String str) {
            if (TextUtils.isEmpty(str)) {
                com.xiaomi.channel.commonutils.logger.b.m22a("channel is null, MiTinyDataClientImp.setChannel(String) failed.");
            } else {
                this.f51a = str;
                b(MiTinyDataClient.PENDING_REASON_CHANNEL);
            }
        }

        /* renamed from: a, reason: collision with other method in class */
        public boolean m37a() {
            return this.f48a != null;
        }

        /* renamed from: a, reason: collision with other method in class */
        public synchronized boolean m38a(hn hnVar) throws PackageManager.NameNotFoundException {
            String str;
            if (hnVar == null) {
                return false;
            }
            if (bo.a(hnVar, true)) {
                return false;
            }
            boolean z = TextUtils.isEmpty(hnVar.m386a()) && TextUtils.isEmpty(this.f51a);
            boolean z2 = !m37a();
            boolean z3 = this.f48a == null || b(this.f48a);
            if (!z2 && !z && !z3) {
                com.xiaomi.channel.commonutils.logger.b.c("MiTinyDataClient Send item immediately." + hnVar.d());
                if (TextUtils.isEmpty(hnVar.d())) {
                    hnVar.f(com.xiaomi.push.service.at.a());
                }
                if (TextUtils.isEmpty(hnVar.m386a())) {
                    hnVar.a(this.f51a);
                }
                if (TextUtils.isEmpty(hnVar.c())) {
                    hnVar.e(this.f48a.getPackageName());
                }
                if (hnVar.a() <= 0) {
                    hnVar.b(System.currentTimeMillis());
                }
                return b(hnVar);
            }
            if (z) {
                str = "MiTinyDataClient Pending " + hnVar.b() + " reason is " + MiTinyDataClient.PENDING_REASON_CHANNEL;
            } else {
                if (!z2) {
                    if (z3) {
                        str = "MiTinyDataClient Pending " + hnVar.b() + " reason is " + MiTinyDataClient.PENDING_REASON_APPID;
                    }
                    a(hnVar);
                    return true;
                }
                str = "MiTinyDataClient Pending " + hnVar.b() + " reason is " + MiTinyDataClient.PENDING_REASON_INIT;
            }
            com.xiaomi.channel.commonutils.logger.b.c(str);
            a(hnVar);
            return true;
        }

        public void b(String str) throws PackageManager.NameNotFoundException {
            com.xiaomi.channel.commonutils.logger.b.c("MiTinyDataClient.processPendingList(" + str + com.umeng.message.proguard.l.t);
            ArrayList arrayList = new ArrayList();
            synchronized (this.f52a) {
                arrayList.addAll(this.f52a);
                this.f52a.clear();
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                m38a((hn) it.next());
            }
        }
    }

    public static void init(Context context, String str) throws PackageManager.NameNotFoundException {
        if (context == null) {
            com.xiaomi.channel.commonutils.logger.b.m22a("context is null, MiTinyDataClient.init(Context, String) failed.");
            return;
        }
        a.a().m36a(context);
        if (TextUtils.isEmpty(str)) {
            com.xiaomi.channel.commonutils.logger.b.m22a("channel is null or empty, MiTinyDataClient.init(Context, String) failed.");
        } else {
            a.a().a(str);
        }
    }

    public static boolean upload(Context context, hn hnVar) {
        com.xiaomi.channel.commonutils.logger.b.c("MiTinyDataClient.upload " + hnVar.d());
        if (!a.a().m37a()) {
            a.a().m36a(context);
        }
        return a.a().m38a(hnVar);
    }

    public static boolean upload(Context context, String str, String str2, long j, String str3) {
        hn hnVar = new hn();
        hnVar.d(str);
        hnVar.c(str2);
        hnVar.a(j);
        hnVar.b(str3);
        hnVar.a(true);
        hnVar.a("push_sdk_channel");
        return upload(context, hnVar);
    }

    public static boolean upload(String str, String str2, long j, String str3) throws PackageManager.NameNotFoundException {
        hn hnVar = new hn();
        hnVar.d(str);
        hnVar.c(str2);
        hnVar.a(j);
        hnVar.b(str3);
        return a.a().m38a(hnVar);
    }
}
