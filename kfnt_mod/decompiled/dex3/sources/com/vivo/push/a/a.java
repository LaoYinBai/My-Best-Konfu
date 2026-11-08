package com.vivo.push.a;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.TextUtils;
import com.vivo.push.b;
import com.vivo.push.g;
import com.vivo.push.util.n;
import com.vivo.push.util.r;
import com.vivo.push.util.y;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class a {
    @SuppressLint({"NewApi"})
    public static void a(Context context, String str, g gVar) {
        boolean zA = gVar.a();
        b bVarA = b.a(context, zA ? "com.vivo.vms.upstageservice" : "com.vivo.vms.aidlservice");
        bVarA.b = r.b(bVarA.c);
        if (TextUtils.isEmpty(bVarA.b)) {
            n.c(bVarA.c, "push pkgname is null");
        } else {
            bVarA.a = y.a(bVarA.c, bVarA.b) >= 1260;
            z = bVarA.a;
        }
        if (TextUtils.isEmpty(gVar.b)) {
            gVar.b = context.getPackageName();
        }
        if (z && !"com.vivo.pushservice".equals(context.getPackageName())) {
            com.vivo.push.a aVar = new com.vivo.push.a(gVar.b, str, new Bundle());
            gVar.a(aVar);
            if (bVarA.a(aVar.a)) {
                return;
            }
            n.b("CommandBridge", "send command error by aidl");
            n.c(context, "send command error by aidl");
        }
        Intent intent = new Intent("com.vivo.pushservice.action.METHOD");
        intent.setPackage(str);
        intent.setClassName(str, zA ? "com.vivo.push.sdk.service.UpstageService" : "com.vivo.push.sdk.service.PushService");
        com.vivo.push.a aVarA = com.vivo.push.a.a(intent);
        if (aVarA == null) {
            n.b("PushCommand", "bundleWapper is null");
        } else {
            gVar.a(aVarA);
            Bundle bundle = aVarA.a;
            if (bundle != null) {
                intent.putExtras(bundle);
            }
        }
        try {
            a(context, intent);
        } catch (Exception e) {
            n.a("CommandBridge", "CommandBridge startService exception: ", e);
        }
    }

    @TargetApi(16)
    public static void a(Context context, Intent intent) throws Exception {
        if (context == null) {
            n.d("CommandBridge", "enter startService context is null");
            throw new Exception("context is null");
        }
        try {
            context.startService(intent);
        } catch (Exception e) {
            n.a("CommandBridge", "start service error", e);
            intent.setComponent(null);
            context.sendBroadcast(intent);
        }
    }

    @TargetApi(4)
    public static boolean a(Context context, String str, String str2) {
        Intent intent = new Intent(str);
        intent.setPackage(str2);
        try {
            List<ResolveInfo> listQueryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 576);
            if (listQueryBroadcastReceivers != null && listQueryBroadcastReceivers.size() > 0) {
                return true;
            }
            n.b("CommandBridge", "action check error：action>>" + str + ";pkgname>>" + str2);
            return false;
        } catch (Exception unused) {
            n.b("CommandBridge", "queryBroadcastReceivers error");
            return false;
        }
    }
}
