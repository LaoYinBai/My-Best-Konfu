package com.xiaomi.push;

import android.text.TextUtils;
import com.sigmob.sdk.common.Constants;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.xiaomi.push.eb;
import com.xiaomi.push.service.av;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class ff {
    public static void a(av.b bVar, String str, fp fpVar) throws NumberFormatException {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String strA;
        eb.c cVar = new eb.c();
        if (!TextUtils.isEmpty(bVar.c)) {
            cVar.a(bVar.c);
        }
        if (!TextUtils.isEmpty(bVar.e)) {
            cVar.d(bVar.e);
        }
        if (!TextUtils.isEmpty(bVar.f)) {
            cVar.e(bVar.f);
        }
        cVar.b(bVar.f905a ? "1" : Constants.FAIL);
        cVar.c(!TextUtils.isEmpty(bVar.d) ? bVar.d : "XIAOMI-SASL");
        fg fgVar = new fg();
        fgVar.c(bVar.f906b);
        fgVar.a(Integer.parseInt(bVar.g));
        fgVar.b(bVar.f903a);
        fgVar.a("BIND", (String) null);
        fgVar.a(fgVar.e());
        com.xiaomi.channel.commonutils.logger.b.m22a("[Slim]: bind id=" + fgVar.e());
        HashMap map = new HashMap();
        map.put("challenge", str);
        map.put("token", bVar.c);
        map.put("chid", bVar.g);
        map.put("from", bVar.f906b);
        map.put(AdDownloadModel.JsonKey.ID, fgVar.e());
        map.put("to", "xiaomi.com");
        if (bVar.f905a) {
            str2 = "kick";
            str3 = "1";
        } else {
            str2 = "kick";
            str3 = Constants.FAIL;
        }
        map.put(str2, str3);
        if (TextUtils.isEmpty(bVar.e)) {
            str4 = "client_attrs";
            str5 = "";
        } else {
            str4 = "client_attrs";
            str5 = bVar.e;
        }
        map.put(str4, str5);
        if (TextUtils.isEmpty(bVar.f)) {
            str6 = "cloud_attrs";
            str7 = "";
        } else {
            str6 = "cloud_attrs";
            str7 = bVar.f;
        }
        map.put(str6, str7);
        if (bVar.d.equals("XIAOMI-PASS") || bVar.d.equals("XMPUSH-PASS")) {
            strA = bk.a(bVar.d, null, map, bVar.h);
        } else {
            bVar.d.equals("XIAOMI-SASL");
            strA = null;
        }
        cVar.f(strA);
        fgVar.a(cVar.a(), (String) null);
        fpVar.b(fgVar);
    }

    public static void a(String str, String str2, fp fpVar) throws NumberFormatException {
        fg fgVar = new fg();
        fgVar.c(str2);
        fgVar.a(Integer.parseInt(str));
        fgVar.a("UBND", (String) null);
        fpVar.b(fgVar);
    }
}
