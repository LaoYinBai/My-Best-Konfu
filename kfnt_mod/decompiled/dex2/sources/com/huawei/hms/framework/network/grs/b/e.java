package com.huawei.hms.framework.network.grs.b;

import android.content.Context;
import com.huawei.hms.framework.common.ExceptionCode;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.NetworkUtil;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.common.hianalytics.HianalyticsHelper;
import com.huawei.hms.framework.common.hianalytics.LinkedHashMapPack;
import com.mbridge.msdk.foundation.entity.DomainCampaignEx;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e {
    public static void a(final ArrayList<d> arrayList, final long j, final JSONArray jSONArray, final Context context) {
        if (context == null || arrayList == null || arrayList.size() <= 0 || !HianalyticsHelper.getInstance().isEnableReportNoSeed(context)) {
            return;
        }
        HianalyticsHelper.getInstance().getReportExecutor().submit(new Runnable() { // from class: com.huawei.hms.framework.network.grs.b.e.1
            @Override // java.lang.Runnable
            public void run() {
                boolean z;
                com.huawei.hms.framework.network.grs.b.b.a aVar = new com.huawei.hms.framework.network.grs.b.b.a();
                aVar.put("total_time", j);
                aVar.put("network_type", NetworkUtil.getNetworkType(context));
                Iterator it = arrayList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    d dVar = (d) it.next();
                    if (dVar.h()) {
                        aVar.put(e.b(dVar));
                        it.remove();
                        z = true;
                        break;
                    }
                }
                if (!z && arrayList.size() > 0) {
                    d dVar2 = (d) arrayList.get(arrayList.size() - 1);
                    aVar.put(e.b(dVar2));
                    arrayList.remove(dVar2);
                }
                if (arrayList.size() > 0) {
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        jSONArray.put(new JSONObject(e.b((d) it2.next())));
                    }
                }
                if (jSONArray.length() > 0) {
                    aVar.put("failed_info", jSONArray.toString());
                }
                Logger.d("HaReportHelper", "grssdk report data to aiops is: %s", new JSONObject(aVar.get()));
                HianalyticsHelper.getInstance().onEvent(aVar.get(), "networkkit_grs");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static LinkedHashMap<String, String> b(d dVar) {
        LinkedHashMapPack linkedHashMapPack = new LinkedHashMapPack();
        Exception excJ = dVar.j();
        if (excJ != null) {
            linkedHashMapPack.put("error_code", ExceptionCode.getErrorCodeFromException(excJ));
            linkedHashMapPack.put("exception_name", excJ.getClass().getSimpleName());
            linkedHashMapPack.put("message", StringUtils.anonymizeMessage(excJ.getMessage()));
        } else {
            linkedHashMapPack.put("error_code", dVar.l());
            linkedHashMapPack.put("exception_name", dVar.g());
        }
        try {
            linkedHashMapPack.put(DomainCampaignEx.LOOPBACK_DOMAIN, new URL(dVar.k()).getHost());
        } catch (MalformedURLException e) {
            Logger.w("HaReportHelper", "report host MalformedURLException", e);
        }
        linkedHashMapPack.put("req_start_time", dVar.a());
        linkedHashMapPack.put("req_end_time", dVar.b());
        linkedHashMapPack.put("req_total_time", dVar.m());
        return linkedHashMapPack.getAll();
    }
}
