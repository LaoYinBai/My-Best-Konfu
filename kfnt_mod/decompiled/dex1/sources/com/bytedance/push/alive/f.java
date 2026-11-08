package com.bytedance.push.alive;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.ContentObserver;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.appsflyer.share.Constants;
import com.bytedance.push.settings.AliveOnlineSettings;
import com.bytedance.push.settings.SettingsManager;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider;
import com.ss.android.pushmanager.setting.PushSetting;
import java.io.File;
import java.io.IOException;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f implements d {

    /* renamed from: a, reason: collision with root package name */
    private com.bytedance.common.push.utility.a.c f4743a;

    /* renamed from: b, reason: collision with root package name */
    private String f4744b;

    /* renamed from: c, reason: collision with root package name */
    private Context f4745c;
    private ContentObserver d;
    private com.bytedance.push.settings.a e;

    public String toString() {
        return "Native alive";
    }

    public f(Context context, com.bytedance.common.push.utility.a.c cVar) {
        this.f4745c = context;
        this.f4743a = cVar;
        if (f()) {
            e(context);
        }
    }

    private void e(Context context) {
        this.d = new ContentObserver(this.f4743a) { // from class: com.bytedance.push.alive.f.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                if (com.bytedance.push.utils.e.a()) {
                    com.bytedance.push.utils.e.a("PushService", "BUNDLE_FROM_SSID_CHANGE");
                }
                f.this.d();
            }
        };
        context.getContentResolver().registerContentObserver(PushMultiProcessSharedProvider.getContentUri(context, "ssids", "string"), true, this.d);
        this.e = new com.bytedance.push.settings.a() { // from class: com.bytedance.push.alive.f.2
            @Override // com.bytedance.push.settings.a
            public void a() {
                f.this.f4743a.post(new Runnable() { // from class: com.bytedance.push.alive.f.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (com.bytedance.push.utils.e.a()) {
                            com.bytedance.push.utils.e.a("PushService", "BUNDLE_FROM_UNINSTALL_QUESTION_URL");
                        }
                        if (PushSetting.getInstance().isAllowNetwork()) {
                            f.this.d();
                        }
                    }
                });
            }
        };
        ((AliveOnlineSettings) SettingsManager.obtain(context, AliveOnlineSettings.class)).registerValChanged(context, "uninstall_question_url", "string", this.e);
    }

    private void a() {
        try {
            if (f() && ((AliveOnlineSettings) SettingsManager.obtain(com.ss.android.message.a.a(), AliveOnlineSettings.class)).i()) {
                String str = this.f4745c.getApplicationInfo().dataDir + "/lib";
                String str2 = str + "/libsupervisor.so";
                String packageName = this.f4745c.getPackageName();
                String name = ForegroundService.class.getName();
                String strA = com.ss.android.message.a.b.a(this.f4745c);
                String strB = com.bytedance.push.utils.g.b(this.f4745c.getApplicationContext());
                if (strB == null) {
                    return;
                }
                this.f4744b = com.bytedance.common.push.utility.d.a(packageName + SystemClock.elapsedRealtime());
                String strB2 = com.ss.android.message.a.b.b(this.f4745c);
                StringBuilder sb = new StringBuilder();
                sb.append(str2);
                sb.append(" ");
                sb.append(packageName);
                sb.append(" ");
                sb.append(name);
                sb.append(" ");
                sb.append(strA);
                sb.append(" ");
                sb.append(strB);
                sb.append(" ");
                sb.append(this.f4744b);
                sb.append(" ");
                if (strB2 != null) {
                    sb.append(strB2);
                }
                a(sb.toString(), str);
                com.bytedance.push.utils.e.a("PushAlive", "启动 Native 保活");
            }
        } catch (Throwable th) {
            th.printStackTrace();
            com.bytedance.push.utils.e.a("PushAlive", "启动 Native 保活失败" + th);
        }
    }

    private void a(String str, String str2) throws IOException {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            Log.d("PushAlive", "cmd = " + str);
            Runtime.getRuntime().exec(str, (String[]) null, (File) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void b() throws IOException {
        String strB;
        if (f() && (strB = com.bytedance.push.utils.g.b(this.f4745c.getApplicationContext())) != null) {
            File file = new File(strB + "/files/noPushFile");
            if (file.exists()) {
                return;
            }
            try {
                file.createNewFile();
            } catch (IOException unused) {
            }
        }
    }

    private void c() throws IOException {
        com.bytedance.push.utils.g.a(this.f4745c.getApplicationContext(), new String[]{"noPushFile"});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (Build.VERSION.SDK_INT < 21 && ((AliveOnlineSettings) SettingsManager.obtain(com.ss.android.message.a.a(), AliveOnlineSettings.class)).i() && com.bytedance.push.utils.g.b(this.f4745c.getApplicationContext()) != null) {
            com.bytedance.common.push.utility.b.b.a(new Runnable() { // from class: com.bytedance.push.alive.f.3
                /* JADX WARN: Removed duplicated region for block: B:113:0x02db A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:115:0x02c2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:123:0x02d1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:140:? A[SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:96:0x02cd A[ORIG_RETURN, RETURN] */
                @Override // java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void run() throws java.lang.Throwable {
                    /*
                        Method dump skipped, instructions count: 735
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.bytedance.push.alive.f.AnonymousClass3.run():void");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LocalSocket e() throws IOException {
        AliveLocalSettings aliveLocalSettings = (AliveLocalSettings) SettingsManager.obtain(this.f4745c, AliveLocalSettings.class);
        try {
            LocalSocket localSocket = new LocalSocket();
            String str = this.f4745c.getPackageName() + Constants.URL_PATH_DELIMITER + this.f4744b;
            a(localSocket, str);
            aliveLocalSettings.a(str);
            return localSocket;
        } catch (IOException e) {
            String strA = aliveLocalSettings.a();
            if (!TextUtils.isEmpty(strA)) {
                LocalSocket localSocket2 = new LocalSocket();
                a(localSocket2, strA);
                return localSocket2;
            }
            throw new IOException(e);
        }
    }

    private void a(LocalSocket localSocket, String str) throws IOException {
        LocalSocketAddress localSocketAddress = new LocalSocketAddress(str, LocalSocketAddress.Namespace.ABSTRACT);
        if (com.bytedance.push.utils.e.a()) {
            com.bytedance.push.utils.e.a("PushService", "Socket Connect " + str);
        }
        localSocket.connect(localSocketAddress);
    }

    @Override // com.bytedance.push.alive.d
    public void a(Context context) {
        try {
            if (f()) {
                com.bytedance.push.utils.g.a(context);
                c();
                if (((AliveOnlineSettings) SettingsManager.obtain(com.ss.android.message.a.a(), AliveOnlineSettings.class)).i()) {
                    a();
                    d();
                }
            }
        } catch (Throwable unused) {
        }
    }

    public void c(Context context) {
        try {
            b();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.e != null) {
            ((AliveOnlineSettings) SettingsManager.obtain(context, AliveOnlineSettings.class)).unregisterValChanged(this.e);
            this.e = null;
        }
        if (this.d != null) {
            context.getContentResolver().unregisterContentObserver(this.d);
            this.d = null;
        }
    }

    @Override // com.bytedance.push.alive.d
    public boolean b(Context context) {
        return f() && com.bytedance.common.push.b.a.a.a.a.a(context);
    }

    private boolean f() {
        return Build.VERSION.SDK_INT < 21;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String f(Context context) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.setDataAndType(Uri.parse(BDAccountNetApi.Platform.PROJECT_MODE_SCHEME), null);
            List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 65568);
            if (listQueryIntentActivities.size() <= 0) {
                return "com.android.browser/com.android.browser.BrowserActivity";
            }
            for (ResolveInfo resolveInfo : listQueryIntentActivities) {
                if (resolveInfo != null && resolveInfo.activityInfo != null && "com.android.browser".equals(resolveInfo.activityInfo.packageName)) {
                    return resolveInfo.activityInfo.packageName + Constants.URL_PATH_DELIMITER + resolveInfo.activityInfo.name;
                }
            }
            ResolveInfo resolveInfo2 = listQueryIntentActivities.get(0);
            if (resolveInfo2 == null || resolveInfo2.activityInfo == null) {
                return "com.android.browser/com.android.browser.BrowserActivity";
            }
            return resolveInfo2.activityInfo.packageName + Constants.URL_PATH_DELIMITER + resolveInfo2.activityInfo.name;
        } catch (Exception e) {
            e.printStackTrace();
            return "com.android.browser/com.android.browser.BrowserActivity";
        }
    }
}
