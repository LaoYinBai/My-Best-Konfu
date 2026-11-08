package com.xiaomi.push;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class ef implements ek {
    ef() {
    }

    private void a(Activity activity, Intent intent) {
        String stringExtra = intent.getStringExtra("awake_info");
        if (!TextUtils.isEmpty(stringExtra)) {
            String strB = ec.b(stringExtra);
            if (!TextUtils.isEmpty(strB)) {
                ed.a(activity.getApplicationContext(), strB, DownloadErrorCode.ERROR_DEVICE_NOT_FOUND, "play with activity successfully");
                return;
            }
        }
        ed.a(activity.getApplicationContext(), "activity", DownloadErrorCode.ERROR_HANDLE_COMPLETE, "B get incorrect message");
    }

    private void b(Context context, eg egVar) {
        String strM287a = egVar.m287a();
        String strB = egVar.b();
        String strD = egVar.d();
        int iA = egVar.a();
        if (context == null || TextUtils.isEmpty(strM287a) || TextUtils.isEmpty(strB) || TextUtils.isEmpty(strD)) {
            if (TextUtils.isEmpty(strD)) {
                ed.a(context, "activity", DownloadErrorCode.ERROR_HANDLE_COMPLETE, "argument error");
                return;
            } else {
                ed.a(context, strD, DownloadErrorCode.ERROR_HANDLE_COMPLETE, "argument error");
                return;
            }
        }
        if (!com.xiaomi.push.service.g.b(context, strM287a, strB)) {
            ed.a(context, strD, 1003, "B is not ready");
            return;
        }
        ed.a(context, strD, 1002, "B is ready");
        ed.a(context, strD, 1004, "A is ready");
        Intent intent = new Intent(strB);
        intent.setPackage(strM287a);
        intent.putExtra("awake_info", ec.a(strD));
        intent.addFlags(276824064);
        intent.setAction(strB);
        if (iA == 1) {
            try {
                if (!eh.m288a(context)) {
                    ed.a(context, strD, DownloadErrorCode.ERROR_HANDLE_COMPLETE, "A not in foreground");
                    return;
                }
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.logger.b.a(e);
                ed.a(context, strD, DownloadErrorCode.ERROR_HANDLE_COMPLETE, "A meet a exception when help B's activity");
                return;
            }
        }
        context.startActivity(intent);
        ed.a(context, strD, 1005, "A is successful");
        ed.a(context, strD, DownloadErrorCode.ERROR_INSUFFICIENT_SPACE, "The job is finished");
    }

    @Override // com.xiaomi.push.ek
    public void a(Context context, Intent intent, String str) {
        if (context == null || !(context instanceof Activity) || intent == null) {
            ed.a(context, "activity", DownloadErrorCode.ERROR_HANDLE_COMPLETE, "B receive incorrect message");
        } else {
            a((Activity) context, intent);
        }
    }

    @Override // com.xiaomi.push.ek
    public void a(Context context, eg egVar) {
        if (egVar != null) {
            b(context, egVar);
        } else {
            ed.a(context, "activity", DownloadErrorCode.ERROR_HANDLE_COMPLETE, "A receive incorrect message");
        }
    }
}
