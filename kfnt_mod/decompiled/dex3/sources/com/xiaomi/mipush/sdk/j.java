package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class j implements Runnable {
    final /* synthetic */ Context a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ e f93a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ String f94a;

    j(String str, Context context, e eVar) {
        this.f94a = str;
        this.a = context;
        this.f93a = eVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (TextUtils.isEmpty(this.f94a)) {
            return;
        }
        String strSubstring = "";
        String[] strArrSplit = this.f94a.split(Constants.WAVE_SEPARATOR);
        int length = strArrSplit.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String str = strArrSplit[i];
            if (!TextUtils.isEmpty(str) && str.startsWith("token:")) {
                strSubstring = str.substring(str.indexOf(Constants.COLON_SEPARATOR) + 1);
                break;
            }
            i++;
        }
        if (TextUtils.isEmpty(strSubstring)) {
            com.xiaomi.channel.commonutils.logger.b.m22a("ASSEMBLE_PUSH : receive incorrect token");
            return;
        }
        com.xiaomi.channel.commonutils.logger.b.m22a("ASSEMBLE_PUSH : receive correct token");
        i.d(this.a, this.f93a, strSubstring);
        i.m81a(this.a);
    }
}
