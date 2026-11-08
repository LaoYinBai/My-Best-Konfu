package com.xiaomi.push;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class eo implements ek {
    eo() {
    }

    private void a(Service service, Intent intent) {
        if ("com.xiaomi.mipush.sdk.WAKEUP".equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra("waker_pkgname");
            String stringExtra2 = intent.getStringExtra("awake_info");
            if (TextUtils.isEmpty(stringExtra)) {
                ed.a(service.getApplicationContext(), "service", DownloadErrorCode.ERROR_DEVICE_NOT_FOUND, "old version message");
                return;
            }
            if (TextUtils.isEmpty(stringExtra2)) {
                ed.a(service.getApplicationContext(), stringExtra, DownloadErrorCode.ERROR_DEVICE_NOT_FOUND, "play with service ");
                return;
            }
            String strB = ec.b(stringExtra2);
            if (TextUtils.isEmpty(strB)) {
                ed.a(service.getApplicationContext(), "service", DownloadErrorCode.ERROR_HANDLE_COMPLETE, "B get a incorrect message");
            } else {
                ed.a(service.getApplicationContext(), strB, DownloadErrorCode.ERROR_DEVICE_NOT_FOUND, "old version message ");
            }
        }
    }

    private void a(Context context, String str, String str2, String str3) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            if (TextUtils.isEmpty(str3)) {
                ed.a(context, "service", DownloadErrorCode.ERROR_HANDLE_COMPLETE, "argument error");
                return;
            } else {
                ed.a(context, str3, DownloadErrorCode.ERROR_HANDLE_COMPLETE, "argument error");
                return;
            }
        }
        if (!com.xiaomi.push.service.g.a(context, str)) {
            ed.a(context, str3, 1003, "B is not ready");
            return;
        }
        ed.a(context, str3, 1002, "B is ready");
        ed.a(context, str3, 1004, "A is ready");
        try {
            Intent intent = new Intent();
            intent.setClassName(str, str2);
            intent.setAction("com.xiaomi.mipush.sdk.WAKEUP");
            intent.putExtra("waker_pkgname", context.getPackageName());
            intent.putExtra("awake_info", ec.a(str3));
            if (context.startService(intent) == null) {
                ed.a(context, str3, DownloadErrorCode.ERROR_HANDLE_COMPLETE, "A is fail to help B's service");
            } else {
                ed.a(context, str3, 1005, "A is successful");
                ed.a(context, str3, DownloadErrorCode.ERROR_INSUFFICIENT_SPACE, "The job is finished");
            }
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            ed.a(context, str3, DownloadErrorCode.ERROR_HANDLE_COMPLETE, "A meet a exception when help B's service");
        }
    }

    @Override // com.xiaomi.push.ek
    public void a(Context context, Intent intent, String str) {
        if (context == null || !(context instanceof Service)) {
            return;
        }
        a((Service) context, intent);
    }

    @Override // com.xiaomi.push.ek
    public void a(Context context, eg egVar) {
        if (egVar != null) {
            a(context, egVar.m287a(), egVar.c(), egVar.d());
        }
    }
}
