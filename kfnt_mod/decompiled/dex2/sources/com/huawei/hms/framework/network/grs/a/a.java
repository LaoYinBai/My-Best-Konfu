package com.huawei.hms.framework.network.grs.a;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.b.d;
import com.huawei.hms.framework.network.grs.b.h;
import com.huawei.hms.framework.network.grs.c.e;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static final String a = "a";
    private Map<String, Map<String, Map<String, String>>> b = new ConcurrentHashMap(16);
    private Map<String, Long> c = new ConcurrentHashMap(16);
    private c d;
    private h e;

    public a(c cVar, h hVar) {
        this.d = cVar;
        this.e = hVar;
        this.e.a(this);
    }

    private void a(GrsBaseInfo grsBaseInfo, b bVar, Context context, String str) {
        Long l = this.c.get(grsBaseInfo.getGrsParasKey(false, true, context));
        if (e.a(l)) {
            bVar.a(2);
            return;
        }
        if (e.a(l, 300000L)) {
            this.e.a(grsBaseInfo, context, null, str);
        }
        bVar.a(1);
    }

    private void a(GrsBaseInfo grsBaseInfo, String str, Context context) {
        if (e.a(this.c.get(str), 300000L)) {
            this.e.a(grsBaseInfo, context, null, null);
        }
    }

    public String a(GrsBaseInfo grsBaseInfo, String str, String str2, b bVar, Context context) {
        Map<String, String> mapA = a(grsBaseInfo, str, bVar, context);
        if (mapA == null) {
            return null;
        }
        return mapA.get(str2);
    }

    public Map<String, String> a(GrsBaseInfo grsBaseInfo, String str, b bVar, Context context) {
        Map<String, Map<String, String>> map = this.b.get(grsBaseInfo.getGrsParasKey(false, true, context));
        if (map == null || map.isEmpty()) {
            return new HashMap();
        }
        a(grsBaseInfo, bVar, context, str);
        return map.get(str);
    }

    public void a(GrsBaseInfo grsBaseInfo, Context context) throws NumberFormatException {
        String grsParasKey = grsBaseInfo.getGrsParasKey(false, true, context);
        String strA = this.d.a(grsParasKey, "");
        String strA2 = this.d.a(grsParasKey + "time", PushConstants.PUSH_TYPE_NOTIFY);
        long j = 0;
        if (!TextUtils.isEmpty(strA2) && strA2.matches("\\d+")) {
            try {
                j = Long.parseLong(strA2);
            } catch (NumberFormatException e) {
                Logger.w(a, "convert urlParamKey from String to Long catch NumberFormatException.", e);
            }
        }
        this.b.put(grsParasKey, com.huawei.hms.framework.network.grs.a.a(strA));
        this.c.put(grsParasKey, Long.valueOf(j));
        a(grsBaseInfo, grsParasKey, context);
    }

    public void a(GrsBaseInfo grsBaseInfo, d dVar, Context context) {
        if (dVar.f() == 2) {
            Logger.w(a, "update cache from server failed");
            return;
        }
        String grsParasKey = grsBaseInfo.getGrsParasKey(false, true, context);
        this.d.b(grsParasKey, dVar.c());
        this.d.b(grsParasKey + "time", dVar.d());
        this.b.put(grsParasKey, com.huawei.hms.framework.network.grs.a.a(dVar.c()));
        this.c.put(grsParasKey, Long.valueOf(Long.parseLong(dVar.d())));
    }

    public void b(GrsBaseInfo grsBaseInfo, Context context) {
        String grsParasKey = grsBaseInfo.getGrsParasKey(false, true, context);
        this.d.b(grsParasKey + "time", PushConstants.PUSH_TYPE_NOTIFY);
        this.c.remove(grsParasKey + "time");
        this.b.remove(grsParasKey);
        this.e.a(grsParasKey);
    }
}
