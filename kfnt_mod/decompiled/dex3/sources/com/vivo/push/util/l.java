package com.vivo.push.util;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class l implements m {
    private static final String a = com.umeng.message.proguard.l.s + Process.myPid() + com.umeng.message.proguard.l.t;
    private static final String[] b = {"com.vivo.pushservice", "com.vivo.sdk.test", "com.vivo.pushdemo.test"};

    @Override // com.vivo.push.util.m
    public final int a(String str, String str2) {
        return Log.e("VivoPush." + str, a + str2);
    }

    @Override // com.vivo.push.util.m
    public final int a(String str, Throwable th) {
        return Log.e("VivoPush." + str, Log.getStackTraceString(th));
    }

    @Override // com.vivo.push.util.m
    public final int a(String str, String str2, Throwable th) {
        return Log.e("VivoPush." + str, a + str2, th);
    }

    @Override // com.vivo.push.util.m
    public final int b(String str, String str2) {
        return Log.w("VivoPush." + str, a + str2);
    }

    @Override // com.vivo.push.util.m
    public final int c(String str, String str2) {
        return Log.d("VivoPush." + str, a + str2);
    }

    @Override // com.vivo.push.util.m
    public final int d(String str, String str2) {
        if (!n.a()) {
            return -1;
        }
        return Log.i("VivoPush." + str, a + str2);
    }

    @Override // com.vivo.push.util.m
    public final int b(String str, String str2, Throwable th) {
        if (!n.a()) {
            return -1;
        }
        return Log.i("VivoPush." + str, a + str2, th);
    }

    @Override // com.vivo.push.util.m
    public final int e(String str, String str2) {
        if (!n.a()) {
            return -1;
        }
        return Log.v("VivoPush." + str, a + str2);
    }

    @Override // com.vivo.push.util.m
    public final String a(Throwable th) {
        return Log.getStackTraceString(th);
    }

    @Override // com.vivo.push.util.m
    public final void a(Context context, String str) throws Exception {
        if (n.a()) {
            a(context, str, 0);
        }
    }

    @Override // com.vivo.push.util.m
    public final void b(Context context, String str) throws Exception {
        if (n.a()) {
            a(context, str, 1);
        }
    }

    @Override // com.vivo.push.util.m
    public final void c(Context context, String str) throws Exception {
        if (n.a()) {
            a(context, str, 2);
        }
    }

    private void a(Context context, String str, int i) throws Exception {
        com.vivo.push.b.n nVar = new com.vivo.push.b.n();
        nVar.c = str;
        nVar.d = i;
        if (i > 0) {
            d("LogController", str);
        }
        if (y.a(context)) {
            nVar.e = true;
            for (String str2 : b) {
                a(context, nVar, str2);
            }
            return;
        }
        nVar.e = false;
        a(context, nVar, context.getPackageName());
    }

    private static void a(Context context, com.vivo.push.b.n nVar, String str) throws Exception {
        if (!str.contains("test")) {
            if (str.equals("com.vivo.pushservice")) {
                return;
            } else {
                return;
            }
        }
        try {
            boolean zA = r.a(context, str, "com.vivo.pushclient.action.RECEIVE");
            String str2 = zA ? "com.vivo.pushclient.action.RECEIVE" : "com.vivo.pushservice.action.RECEIVE";
            if (TextUtils.isEmpty(str)) {
                n.c(context, "消息接受者包名为空！");
                throw new Exception("消息接受者包名为空！");
            }
            if (com.vivo.push.a.a.a(context, str2, str)) {
                if (TextUtils.isEmpty(nVar.b)) {
                    nVar.b = context.getPackageName();
                }
                Intent intent = new Intent();
                intent.setFlags(DownloadExpSwitchCode.BUGFIX_ONLY_WIFI);
                if (!TextUtils.isEmpty(str2)) {
                    intent.setAction(str2);
                }
                intent.setPackage(str);
                intent.setClassName(str, zA ? "com.vivo.push.sdk.service.CommandClientService" : "com.vivo.push.sdk.service.CommandService");
                nVar.a(intent);
                intent.putExtra("command_type", "reflect_receiver");
                com.vivo.push.a.a.a(context, intent);
            }
        } catch (Exception e) {
            n.a("CommandBridge", "CommandBridge sendCommandToClient exception", e);
        }
    }
}
