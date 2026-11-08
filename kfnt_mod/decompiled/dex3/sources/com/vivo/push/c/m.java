package com.vivo.push.c;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.tachikoma.core.utility.UriUtil;
import com.vivo.push.b.x;
import com.vivo.push.model.InsideNotificationItem;
import com.vivo.push.model.UPSNotificationMessage;
import com.vivo.push.util.NotifyAdapterUtil;
import com.vivo.push.util.y;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class m extends o {
    public m(com.vivo.push.g gVar) {
        super(gVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(com.vivo.push.g gVar) throws PackageManager.NameNotFoundException, URISyntaxException {
        String packageName;
        com.vivo.push.b.p pVar = (com.vivo.push.b.p) gVar;
        InsideNotificationItem insideNotificationItem = pVar.f;
        if (insideNotificationItem == null) {
            com.vivo.push.util.n.d("OnNotificationClickTask", "current notification item is null");
            return;
        }
        final UPSNotificationMessage uPSNotificationMessageA = com.vivo.push.util.o.a(insideNotificationItem);
        String str = pVar.c;
        boolean zEquals = this.a.getPackageName().equals(str);
        if (zEquals) {
            NotifyAdapterUtil.cancelNotify(this.a);
        }
        if (pVar.d != null) {
            str = pVar.d;
        }
        if (zEquals) {
            x xVar = new x(1030L);
            HashMap<String, String> map = new HashMap<>();
            map.put("type", "2");
            map.put("messageID", String.valueOf(pVar.e));
            map.put("platform", str);
            String strB = y.b(this.a, str);
            if (!TextUtils.isEmpty(strB)) {
                map.put("remoteAppId", strB);
            } else {
                map.put("ap", str);
            }
            xVar.c = map;
            com.vivo.push.d.a().a(xVar);
            com.vivo.push.util.n.d("OnNotificationClickTask", "notification is clicked by skip type[" + uPSNotificationMessageA.getSkipType() + "]");
            boolean z = true;
            switch (uPSNotificationMessageA.getSkipType()) {
                case 1:
                    final Context context = this.a;
                    final Map<String, String> params = uPSNotificationMessageA.getParams();
                    final String str2 = pVar.d;
                    new Thread(new Runnable() { // from class: com.vivo.push.c.m.5
                        @Override // java.lang.Runnable
                        public final void run() throws SecurityException {
                            String packageName2 = context.getPackageName();
                            if (!TextUtils.isEmpty(str2)) {
                                packageName2 = str2;
                            }
                            try {
                                List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(100);
                                if (runningTasks != null) {
                                    Iterator<ActivityManager.RunningTaskInfo> it = runningTasks.iterator();
                                    while (it.hasNext()) {
                                        ComponentName componentName = it.next().topActivity;
                                        if (componentName.getPackageName().equals(packageName2)) {
                                            com.vivo.push.util.n.d("OnNotificationClickTask", "topClassName=" + componentName.getClassName());
                                            Intent intent = new Intent();
                                            intent.setComponent(componentName);
                                            intent.setFlags(270532608);
                                            m.b(intent, params);
                                            context.startActivity(intent);
                                            return;
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                com.vivo.push.util.n.a("OnNotificationClickTask", "start recentIntent is error", e);
                            }
                            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(!TextUtils.isEmpty(str2) ? str2 : context.getPackageName());
                            if (launchIntentForPackage != null) {
                                launchIntentForPackage.setFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
                                m.b(launchIntentForPackage, params);
                                context.startActivity(launchIntentForPackage);
                                return;
                            }
                            com.vivo.push.util.n.a("OnNotificationClickTask", "LaunchIntent is null");
                        }
                    }).start();
                    com.vivo.push.f.b(new Runnable() { // from class: com.vivo.push.c.m.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            if (m.this.c != null) {
                                m.this.c.onNotificationMessageClicked(m.this.a, uPSNotificationMessageA);
                            }
                        }
                    });
                    break;
                case 2:
                    String skipContent = uPSNotificationMessageA.getSkipContent();
                    if (!skipContent.startsWith(UriUtil.HTTP_PREFIX) && !skipContent.startsWith(UriUtil.HTTPS_PREFIX)) {
                        z = false;
                    }
                    if (z) {
                        Uri uri = Uri.parse(skipContent);
                        Intent intent = new Intent("android.intent.action.VIEW", uri);
                        intent.setFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
                        b(intent, uPSNotificationMessageA.getParams());
                        try {
                            this.a.startActivity(intent);
                        } catch (Exception unused) {
                            com.vivo.push.util.n.a("OnNotificationClickTask", "startActivity error : " + uri);
                        }
                    } else {
                        com.vivo.push.util.n.a("OnNotificationClickTask", "url not legal");
                    }
                    com.vivo.push.f.b(new Runnable() { // from class: com.vivo.push.c.m.2
                        @Override // java.lang.Runnable
                        public final void run() {
                            if (m.this.c != null) {
                                m.this.c.onNotificationMessageClicked(m.this.a, uPSNotificationMessageA);
                            }
                        }
                    });
                    break;
                case 3:
                    com.vivo.push.f.b(new Runnable() { // from class: com.vivo.push.c.m.3
                        @Override // java.lang.Runnable
                        public final void run() {
                            if (m.this.c != null) {
                                m.this.c.onNotificationMessageClicked(m.this.a, uPSNotificationMessageA);
                            }
                        }
                    });
                    break;
                case 4:
                    String skipContent2 = uPSNotificationMessageA.getSkipContent();
                    try {
                        Intent uri2 = Intent.parseUri(skipContent2, 1);
                        String str3 = uri2.getPackage();
                        String packageName2 = null;
                        if (pVar.d != null) {
                            if (!TextUtils.isEmpty(str3) && !pVar.d.equals(str3)) {
                                com.vivo.push.util.n.a("OnNotificationClickTask", "open activity error : local pkgName getOpenPkgName is " + pVar.d + "; but remote pkgName is " + str3);
                                break;
                            } else {
                                if (uri2.getComponent() != null) {
                                    packageName2 = uri2.getComponent().getPackageName();
                                }
                                if (!TextUtils.isEmpty(packageName2) && !pVar.d.equals(packageName2)) {
                                    com.vivo.push.util.n.a("OnNotificationClickTask", "open activity component error : local pkgName getOpenPkgName is " + pVar.d + "; but remote pkgName is " + packageName2);
                                    break;
                                }
                            }
                        } else if (!TextUtils.isEmpty(str3) && !this.a.getPackageName().equals(str3)) {
                            com.vivo.push.util.n.a("OnNotificationClickTask", "open activity error : local pkgName is " + this.a.getPackageName() + "; but remote pkgName is " + str3);
                            break;
                        } else {
                            if (uri2.getComponent() != null) {
                                packageName2 = uri2.getComponent().getPackageName();
                            }
                            if (!TextUtils.isEmpty(packageName2) && !this.a.getPackageName().equals(packageName2)) {
                                com.vivo.push.util.n.a("OnNotificationClickTask", "open activity component error : local pkgName is " + this.a.getPackageName() + "; but remote pkgName is " + packageName2);
                                break;
                            }
                        }
                        if (pVar.d == null) {
                            packageName = this.a.getPackageName();
                        } else {
                            packageName = pVar.d;
                        }
                        uri2.setPackage(packageName);
                        uri2.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
                        b(uri2, uPSNotificationMessageA.getParams());
                        this.a.startActivity(uri2);
                    } catch (Exception e) {
                        com.vivo.push.util.n.a("OnNotificationClickTask", "open activity error : " + skipContent2, e);
                    }
                    com.vivo.push.f.b(new Runnable() { // from class: com.vivo.push.c.m.4
                        @Override // java.lang.Runnable
                        public final void run() {
                            if (m.this.c != null) {
                                m.this.c.onNotificationMessageClicked(m.this.a, uPSNotificationMessageA);
                            }
                        }
                    });
                    break;
                default:
                    com.vivo.push.util.n.a("OnNotificationClickTask", "illegitmacy skip type error : " + uPSNotificationMessageA.getSkipType());
                    break;
            }
            return;
        }
        com.vivo.push.util.n.a("OnNotificationClickTask", "notify is " + uPSNotificationMessageA + " ; isMatch is " + zEquals);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Intent b(Intent intent, Map<String, String> map) {
        if (map == null || map.entrySet() == null) {
            return intent;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry != null && entry.getKey() != null) {
                intent.putExtra(entry.getKey(), entry.getValue());
            }
        }
        return intent;
    }
}
