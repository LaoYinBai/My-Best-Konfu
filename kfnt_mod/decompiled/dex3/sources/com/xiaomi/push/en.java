package com.xiaomi.push;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class en implements ek {
    en() {
    }

    private void a(Service service, Intent intent) {
        String stringExtra = intent.getStringExtra("awake_info");
        if (!TextUtils.isEmpty(stringExtra)) {
            String strB = ec.b(stringExtra);
            if (!TextUtils.isEmpty(strB)) {
                ed.a(service.getApplicationContext(), strB, DownloadErrorCode.ERROR_DEVICE_NOT_FOUND, "play with service successfully");
                return;
            }
        }
        ed.a(service.getApplicationContext(), "service", DownloadErrorCode.ERROR_HANDLE_COMPLETE, "B get a incorrect message");
    }

    private void b(Context context, eg egVar) {
        String strM287a = egVar.m287a();
        String strB = egVar.b();
        String strD = egVar.d();
        int iA = egVar.a();
        if (context == null || TextUtils.isEmpty(strM287a) || TextUtils.isEmpty(strB) || TextUtils.isEmpty(strD)) {
            if (TextUtils.isEmpty(strD)) {
                ed.a(context, "service", DownloadErrorCode.ERROR_HANDLE_COMPLETE, "argument error");
                return;
            } else {
                ed.a(context, strD, DownloadErrorCode.ERROR_HANDLE_COMPLETE, "argument error");
                return;
            }
        }
        if (!com.xiaomi.push.service.g.a(context, strM287a, strB)) {
            ed.a(context, strD, 1003, "B is not ready");
            return;
        }
        ed.a(context, strD, 1002, "B is ready");
        ed.a(context, strD, 1004, "A is ready");
        try {
            Intent intent = new Intent();
            intent.setAction(strB);
            intent.setPackage(strM287a);
            intent.putExtra("awake_info", ec.a(strD));
            if (iA == 1 && !eh.m288a(context)) {
                ed.a(context, strD, DownloadErrorCode.ERROR_HANDLE_COMPLETE, "A not in foreground");
            } else if (context.startService(intent) == null) {
                ed.a(context, strD, DownloadErrorCode.ERROR_HANDLE_COMPLETE, "A is fail to help B's service");
            } else {
                ed.a(context, strD, 1005, "A is successful");
                ed.a(context, strD, DownloadErrorCode.ERROR_INSUFFICIENT_SPACE, "The job is finished");
            }
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            ed.a(context, strD, DownloadErrorCode.ERROR_HANDLE_COMPLETE, "A meet a exception when help B's service");
        }
    }

    @Override // com.xiaomi.push.ek
    public void a(Context context, Intent intent, String str) {
        if (context == null || !(context instanceof Service)) {
            ed.a(context, "service", DownloadErrorCode.ERROR_HANDLE_COMPLETE, "A receive incorrect message");
        } else {
            a((Service) context, intent);
        }
    }

    @Override // com.xiaomi.push.ek
    public void a(Context context, eg egVar) {
        if (egVar != null) {
            b(context, egVar);
        } else {
            ed.a(context, "service", DownloadErrorCode.ERROR_HANDLE_COMPLETE, "A receive incorrect message");
        }
    }
}
