package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.pm.PackageManager;
import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;
import com.xiaomi.push.ec;
import com.xiaomi.push.eh;
import com.xiaomi.push.el;
import com.xiaomi.push.hj;
import com.xiaomi.push.ht;
import com.xiaomi.push.hw;
import com.xiaomi.push.ii;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c implements el {
    @Override // com.xiaomi.push.el
    public void a(Context context, HashMap<String, String> map) {
        ii iiVar = new ii();
        iiVar.b(eh.a(context).m290a());
        iiVar.d(eh.a(context).b());
        iiVar.c(ht.AwakeAppResponse.f509a);
        iiVar.a(com.xiaomi.push.service.at.a());
        iiVar.f650a = map;
        an.a(context).a((an) iiVar, hj.Notification, true, (hw) null, true);
        com.xiaomi.channel.commonutils.logger.b.m22a("MoleInfo：\u3000send data in app layer");
    }

    @Override // com.xiaomi.push.el
    public void b(Context context, HashMap<String, String> map) throws PackageManager.NameNotFoundException {
        MiTinyDataClient.upload("category_awake_app", "wake_up_app", 1L, ec.a(map));
        com.xiaomi.channel.commonutils.logger.b.m22a("MoleInfo：\u3000send data in app layer");
    }

    @Override // com.xiaomi.push.el
    public void c(Context context, HashMap<String, String> map) {
        com.xiaomi.channel.commonutils.logger.b.m22a("MoleInfo：\u3000" + ec.b(map));
        String str = map.get("event_type");
        String str2 = map.get("awake_info");
        if (String.valueOf(DownloadErrorCode.ERROR_DEVICE_NOT_FOUND).equals(str)) {
            o.a(context, str2);
        }
    }
}
