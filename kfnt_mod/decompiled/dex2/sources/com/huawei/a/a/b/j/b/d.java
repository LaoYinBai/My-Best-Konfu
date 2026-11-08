package com.huawei.a.a.b.j.b;

import com.huawei.a.a.b.k.e;
import com.mbridge.msdk.playercommon.exoplayer2.source.ExtractorMediaSource;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d implements com.huawei.a.a.b.j.c.a {
    private String a;
    private String b;
    private String c;
    private List<com.huawei.a.a.b.c.b.d> d;

    public d(List<com.huawei.a.a.b.c.b.d> list, String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = list;
    }

    private void a() {
        com.huawei.a.a.b.i.a.a(com.huawei.a.a.a.b.j(), "backup_event", e.a(this.a, this.c, this.b));
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.d == null || this.d.size() == 0) {
            com.huawei.a.a.b.e.a.b("FailedEventHandlerTask", "failed events is empty");
            return;
        }
        if (com.huawei.a.a.b.k.b.a(com.huawei.a.a.a.b.j(), "cached_v2_1", com.huawei.a.a.a.b.i() * ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES)) {
            com.huawei.a.a.b.e.a.c("FailedEventHandlerTask", "The cacheFile is full,Can not writing data! reqID:" + this.b);
            return;
        }
        String strA = e.a(this.a, this.c);
        List<com.huawei.a.a.b.c.b.d> list = com.huawei.a.a.b.d.e.b(com.huawei.a.a.a.b.j(), "cached_v2_1", strA).get(strA);
        if (list != null && list.size() != 0) {
            this.d.addAll(list);
        }
        JSONArray jSONArray = new JSONArray();
        Iterator<com.huawei.a.a.b.c.b.d> it = this.d.iterator();
        while (it.hasNext()) {
            try {
                jSONArray.put(it.next().d());
            } catch (JSONException unused) {
                com.huawei.a.a.b.e.a.c("FailedEventHandlerTask", "event to json error");
            }
        }
        String string = jSONArray.toString();
        if (string.length() > com.huawei.a.a.a.b.h() * ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) {
            com.huawei.a.a.b.e.a.c("FailedEventHandlerTask", "this failed data is too long,can not writing it");
            this.d = null;
            return;
        }
        com.huawei.a.a.b.e.a.b("FailedEventHandlerTask", "data send failed, write to cache file...reqID:" + this.b);
        com.huawei.a.a.b.i.a.a(com.huawei.a.a.a.b.j(), "cached_v2_1", strA, string);
        a();
    }
}
