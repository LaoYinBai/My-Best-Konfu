package com.xiaomi.push;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class em implements ek {
    em() {
    }

    private void a(Context context, String str) {
        String str2;
        String str3;
        try {
            if (TextUtils.isEmpty(str) || context == null) {
                str2 = "provider";
                str3 = "B get a incorrect message";
            } else {
                String[] strArrSplit = str.split("/");
                if (strArrSplit.length <= 0 || TextUtils.isEmpty(strArrSplit[strArrSplit.length - 1])) {
                    str2 = "provider";
                    str3 = "B get a incorrect message";
                } else {
                    String str4 = strArrSplit[strArrSplit.length - 1];
                    if (TextUtils.isEmpty(str4)) {
                        ed.a(context, "provider", DownloadErrorCode.ERROR_HANDLE_COMPLETE, "B get a incorrect message");
                        return;
                    }
                    String strDecode = Uri.decode(str4);
                    if (TextUtils.isEmpty(strDecode)) {
                        ed.a(context, "provider", DownloadErrorCode.ERROR_HANDLE_COMPLETE, "B get a incorrect message");
                        return;
                    }
                    String strB = ec.b(strDecode);
                    if (!TextUtils.isEmpty(strB)) {
                        ed.a(context, strB, DownloadErrorCode.ERROR_DEVICE_NOT_FOUND, "play with provider successfully");
                        return;
                    } else {
                        str2 = "provider";
                        str3 = "B get a incorrect message";
                    }
                }
            }
            ed.a(context, str2, DownloadErrorCode.ERROR_HANDLE_COMPLETE, str3);
        } catch (Exception e) {
            ed.a(context, "provider", DownloadErrorCode.ERROR_HANDLE_COMPLETE, "B meet a exception" + e.getMessage());
        }
    }

    private void b(Context context, eg egVar) {
        String strB = egVar.b();
        String strD = egVar.d();
        int iA = egVar.a();
        if (context == null || TextUtils.isEmpty(strB) || TextUtils.isEmpty(strD)) {
            if (TextUtils.isEmpty(strD)) {
                ed.a(context, "provider", DownloadErrorCode.ERROR_HANDLE_COMPLETE, "argument error");
                return;
            } else {
                ed.a(context, strD, DownloadErrorCode.ERROR_HANDLE_COMPLETE, "argument error");
                return;
            }
        }
        if (!com.xiaomi.push.service.g.b(context, strB)) {
            ed.a(context, strD, 1003, "B is not ready");
            return;
        }
        ed.a(context, strD, 1002, "B is ready");
        ed.a(context, strD, 1004, "A is ready");
        String strA = ec.a(strD);
        try {
            if (TextUtils.isEmpty(strA)) {
                ed.a(context, strD, DownloadErrorCode.ERROR_HANDLE_COMPLETE, "info is empty");
                return;
            }
            if (iA == 1 && !eh.m288a(context)) {
                ed.a(context, strD, DownloadErrorCode.ERROR_HANDLE_COMPLETE, "A not in foreground");
                return;
            }
            String type = context.getContentResolver().getType(ec.a(strB, strA));
            if (TextUtils.isEmpty(type) || !"success".equals(type)) {
                ed.a(context, strD, DownloadErrorCode.ERROR_HANDLE_COMPLETE, "A is fail to help B's provider");
            } else {
                ed.a(context, strD, 1005, "A is successful");
                ed.a(context, strD, DownloadErrorCode.ERROR_INSUFFICIENT_SPACE, "The job is finished");
            }
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            ed.a(context, strD, DownloadErrorCode.ERROR_HANDLE_COMPLETE, "A meet a exception when help B's provider");
        }
    }

    @Override // com.xiaomi.push.ek
    public void a(Context context, Intent intent, String str) {
        a(context, str);
    }

    @Override // com.xiaomi.push.ek
    public void a(Context context, eg egVar) {
        if (egVar != null) {
            b(context, egVar);
        } else {
            ed.a(context, "provider", DownloadErrorCode.ERROR_HANDLE_COMPLETE, "A receive incorrect message");
        }
    }
}
