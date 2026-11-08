package com.vivo.push.c;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class e extends com.vivo.push.e {
    public e(com.vivo.push.g gVar) {
        super(gVar);
    }

    public static boolean a(Context context) {
        Intent intent = new Intent("com.vivo.pushservice.action.PUSH_SERVICE");
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> listQueryIntentServices = context.getPackageManager().queryIntentServices(intent, 576);
        if (listQueryIntentServices == null || listQueryIntentServices.size() <= 0) {
            com.vivo.push.util.n.a("OnChangePushStatusTask", "enableService error: can not find push service.");
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        ComponentName componentName = new ComponentName(context, listQueryIntentServices.get(0).serviceInfo.name);
        if (packageManager.getComponentEnabledSetting(componentName) != 1) {
            packageManager.setComponentEnabledSetting(componentName, 1, 1);
            com.vivo.push.util.n.d("OnChangePushStatusTask", "enableService push service.");
            return true;
        }
        com.vivo.push.util.n.d("OnChangePushStatusTask", "push service has enabled");
        return false;
    }

    @TargetApi(4)
    public static boolean b(Context context) {
        Intent intent = new Intent("com.vivo.pushservice.action.PUSH_SERVICE");
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> listQueryIntentServices = context.getPackageManager().queryIntentServices(intent, 576);
        if (listQueryIntentServices == null || listQueryIntentServices.size() <= 0) {
            com.vivo.push.util.n.a("OnChangePushStatusTask", "disableService error: can not find push service.");
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        ComponentName componentName = new ComponentName(context, listQueryIntentServices.get(0).serviceInfo.name);
        if (packageManager.getComponentEnabledSetting(componentName) != 2) {
            packageManager.setComponentEnabledSetting(componentName, 2, 1);
            com.vivo.push.util.n.d("OnChangePushStatusTask", "disableService push service.");
            return true;
        }
        com.vivo.push.util.n.d("OnChangePushStatusTask", "push service has disabled");
        return false;
    }

    private static List<ResolveInfo> c(Context context) {
        List<ResolveInfo> listQueryBroadcastReceivers;
        Intent intent = new Intent("com.vivo.pushservice.action.RECEIVE");
        intent.setPackage(context.getPackageName());
        try {
            listQueryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 576);
        } catch (Exception unused) {
            listQueryBroadcastReceivers = null;
        }
        if (listQueryBroadcastReceivers == null || listQueryBroadcastReceivers.size() <= 0) {
            Intent intent2 = new Intent("com.vivo.pushclient.action.RECEIVE");
            intent2.setPackage(context.getPackageName());
            try {
                return context.getPackageManager().queryBroadcastReceivers(intent2, 576);
            } catch (Exception unused2) {
            }
        }
        return listQueryBroadcastReceivers;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(com.vivo.push.g gVar) {
        if (this.a.getPackageName().equals(com.vivo.push.util.r.b(this.a))) {
            return;
        }
        com.vivo.push.b.j jVar = (com.vivo.push.b.j) gVar;
        int i = jVar.c;
        int i2 = jVar.d;
        com.vivo.push.util.n.d("OnChangePushStatusTask", "OnChangePushStatusTask serviceStatus is " + i + " ; receiverStatus is " + i2);
        if (i == 2) {
            b(this.a);
        } else if (i == 1) {
            a(this.a);
        } else if (i == 0) {
            Context context = this.a;
            Intent intent = new Intent("com.vivo.pushservice.action.PUSH_SERVICE");
            intent.setPackage(context.getPackageName());
            List<ResolveInfo> listQueryIntentServices = context.getPackageManager().queryIntentServices(intent, 576);
            if (listQueryIntentServices == null || listQueryIntentServices.size() <= 0) {
                com.vivo.push.util.n.a("OnChangePushStatusTask", "defaultService error: can not find push service.");
            } else {
                PackageManager packageManager = context.getPackageManager();
                ComponentName componentName = new ComponentName(context, listQueryIntentServices.get(0).serviceInfo.name);
                if (packageManager.getComponentEnabledSetting(componentName) != 0) {
                    packageManager.setComponentEnabledSetting(componentName, 0, 1);
                    com.vivo.push.util.n.d("OnChangePushStatusTask", "defaultService push service.");
                } else {
                    com.vivo.push.util.n.d("OnChangePushStatusTask", "push service has defaulted");
                }
            }
        }
        if (i2 == 2) {
            Context context2 = this.a;
            List<ResolveInfo> listC = c(context2);
            if (listC == null || listC.size() <= 0) {
                com.vivo.push.util.n.a("OnChangePushStatusTask", "disableReceiver error: can not find push service.");
            } else {
                String str = listC.get(0).activityInfo.name;
                if (TextUtils.isEmpty(str)) {
                    com.vivo.push.util.n.d("OnChangePushStatusTask", "disableReceiver error: className is null. ");
                } else {
                    PackageManager packageManager2 = context2.getPackageManager();
                    ComponentName componentName2 = new ComponentName(context2, str);
                    if (packageManager2.getComponentEnabledSetting(componentName2) != 2) {
                        packageManager2.setComponentEnabledSetting(componentName2, 2, 1);
                        com.vivo.push.util.n.d("OnChangePushStatusTask", "push service disableReceiver ");
                    } else {
                        com.vivo.push.util.n.d("OnChangePushStatusTask", "push service has disableReceiver ");
                    }
                }
            }
            com.vivo.push.sdk.a.a().c = null;
            return;
        }
        if (i2 == 1) {
            Context context3 = this.a;
            List<ResolveInfo> listC2 = c(context3);
            if (listC2 == null || listC2.size() <= 0) {
                com.vivo.push.util.n.a("OnChangePushStatusTask", "enableReceiver error: can not find push service.");
                return;
            }
            String str2 = listC2.get(0).activityInfo.name;
            if (TextUtils.isEmpty(str2)) {
                com.vivo.push.util.n.d("OnChangePushStatusTask", "enableReceiver error: className is null. ");
                return;
            }
            PackageManager packageManager3 = context3.getPackageManager();
            ComponentName componentName3 = new ComponentName(context3, str2);
            if (packageManager3.getComponentEnabledSetting(componentName3) != 1) {
                packageManager3.setComponentEnabledSetting(componentName3, 1, 1);
                com.vivo.push.util.n.d("OnChangePushStatusTask", "push service enableReceiver ");
                return;
            } else {
                com.vivo.push.util.n.d("OnChangePushStatusTask", "push service has enableReceiver ");
                return;
            }
        }
        if (i2 == 0) {
            Context context4 = this.a;
            List<ResolveInfo> listC3 = c(context4);
            if (listC3 == null || listC3.size() <= 0) {
                com.vivo.push.util.n.a("OnChangePushStatusTask", "defaultReceiver error: can not find push service.");
                return;
            }
            String str3 = listC3.get(0).activityInfo.name;
            if (TextUtils.isEmpty(str3)) {
                com.vivo.push.util.n.d("OnChangePushStatusTask", "defaultReceiver error: className is null. ");
                return;
            }
            PackageManager packageManager4 = context4.getPackageManager();
            ComponentName componentName4 = new ComponentName(context4, str3);
            if (packageManager4.getComponentEnabledSetting(componentName4) != 0) {
                packageManager4.setComponentEnabledSetting(componentName4, 0, 1);
                com.vivo.push.util.n.d("OnChangePushStatusTask", "push service defaultReceiver ");
            } else {
                com.vivo.push.util.n.d("OnChangePushStatusTask", "push service has defaulted");
            }
        }
    }
}
