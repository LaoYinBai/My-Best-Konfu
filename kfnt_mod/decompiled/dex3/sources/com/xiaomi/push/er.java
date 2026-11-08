package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.ss.android.socialbase.appdownloader.constants.Constants;
import com.xiaomi.clientreport.data.Config;
import com.xiaomi.clientreport.data.EventClientReport;
import com.xiaomi.clientreport.data.PerfClientReport;
import com.xiaomi.clientreport.manager.ClientReportClient;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class er {
    private static a a;

    /* renamed from: a, reason: collision with other field name */
    private static Map<String, ht> f328a;

    public interface a {
        void uploader(Context context, hn hnVar);
    }

    public static int a(int i) {
        if (i > 0) {
            return i + 1000;
        }
        return -1;
    }

    public static int a(Enum r1) {
        if (r1 != null) {
            if (r1 instanceof hj) {
                return r1.ordinal() + 1001;
            }
            if (r1 instanceof ht) {
                return r1.ordinal() + 2001;
            }
            if (r1 instanceof fb) {
                return r1.ordinal() + 3001;
            }
        }
        return -1;
    }

    public static Config a(Context context) {
        boolean zA = com.xiaomi.push.service.aq.a(context).a(ho.PerfUploadSwitch.a(), false);
        boolean zA2 = com.xiaomi.push.service.aq.a(context).a(ho.EventUploadNewSwitch.a(), false);
        return Config.getBuilder().setEventUploadSwitchOpen(zA2).setEventUploadFrequency(com.xiaomi.push.service.aq.a(context).a(ho.EventUploadFrequency.a(), Constants.MAX_RETRY_AFTER)).setPerfUploadSwitchOpen(zA).setPerfUploadFrequency(com.xiaomi.push.service.aq.a(context).a(ho.PerfUploadFrequency.a(), Constants.MAX_RETRY_AFTER)).build(context);
    }

    public static EventClientReport a(Context context, String str, String str2, int i, long j, String str3) {
        EventClientReport eventClientReportA = a(str);
        eventClientReportA.eventId = str2;
        eventClientReportA.eventType = i;
        eventClientReportA.eventTime = j;
        eventClientReportA.eventContent = str3;
        return eventClientReportA;
    }

    public static EventClientReport a(String str) {
        EventClientReport eventClientReport = new EventClientReport();
        eventClientReport.production = 1000;
        eventClientReport.reportType = 1001;
        eventClientReport.clientInterfaceId = str;
        return eventClientReport;
    }

    public static PerfClientReport a() {
        PerfClientReport perfClientReport = new PerfClientReport();
        perfClientReport.production = 1000;
        perfClientReport.reportType = 1000;
        perfClientReport.clientInterfaceId = "P100000";
        return perfClientReport;
    }

    public static PerfClientReport a(Context context, int i, long j, long j2) {
        PerfClientReport perfClientReportA = a();
        perfClientReportA.code = i;
        perfClientReportA.perfCounts = j;
        perfClientReportA.perfLatencies = j2;
        return perfClientReportA;
    }

    public static hn a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        hn hnVar = new hn();
        hnVar.d("category_client_report_data");
        hnVar.a("push_sdk_channel");
        hnVar.a(1L);
        hnVar.b(str);
        hnVar.a(true);
        hnVar.b(System.currentTimeMillis());
        hnVar.g(context.getPackageName());
        hnVar.e("com.xiaomi.xmsf");
        hnVar.f(com.xiaomi.push.service.bo.a());
        hnVar.c("quality_support");
        return hnVar;
    }

    /* renamed from: a, reason: collision with other method in class */
    public static ht m291a(String str) {
        if (f328a == null) {
            synchronized (ht.class) {
                if (f328a == null) {
                    f328a = new HashMap();
                    for (ht htVar : ht.values()) {
                        f328a.put(htVar.f509a.toLowerCase(), htVar);
                    }
                }
            }
        }
        ht htVar2 = f328a.get(str.toLowerCase());
        return htVar2 != null ? htVar2 : ht.Invalid;
    }

    /* renamed from: a, reason: collision with other method in class */
    public static String m292a(int i) {
        return i == 1000 ? "E100000" : i == 3000 ? "E100002" : i == 2000 ? "E100001" : i == 6000 ? "E100003" : "";
    }

    /* renamed from: a, reason: collision with other method in class */
    public static void m293a(Context context) {
        ClientReportClient.updateConfig(context, a(context));
    }

    public static void a(Context context, Config config) {
        ClientReportClient.init(context, config, new ep(context), new eq(context));
    }

    private static void a(Context context, hn hnVar) {
        if (m294a(context.getApplicationContext())) {
            com.xiaomi.push.service.bp.a(context.getApplicationContext(), hnVar);
        } else if (a != null) {
            a.uploader(context, hnVar);
        }
    }

    public static void a(Context context, List<String> list) {
        if (list == null) {
            return;
        }
        try {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                hn hnVarA = a(context, it.next());
                if (!com.xiaomi.push.service.bo.a(hnVarA, false)) {
                    a(context, hnVarA);
                }
            }
        } catch (Throwable th) {
            com.xiaomi.channel.commonutils.logger.b.d(th.getMessage());
        }
    }

    public static void a(a aVar) {
        a = aVar;
    }

    /* renamed from: a, reason: collision with other method in class */
    public static boolean m294a(Context context) {
        return (context == null || TextUtils.isEmpty(context.getPackageName()) || !"com.xiaomi.xmsf".equals(context.getPackageName())) ? false : true;
    }
}
