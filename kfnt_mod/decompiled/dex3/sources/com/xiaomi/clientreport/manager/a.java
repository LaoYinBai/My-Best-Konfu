package com.xiaomi.clientreport.manager;

import android.content.Context;
import com.xiaomi.clientreport.data.Config;
import com.xiaomi.clientreport.data.EventClientReport;
import com.xiaomi.clientreport.data.PerfClientReport;
import com.xiaomi.clientreport.processor.IEventProcessor;
import com.xiaomi.clientreport.processor.IPerfProcessor;
import com.xiaomi.push.ai;
import com.xiaomi.push.bm;
import com.xiaomi.push.bn;
import com.xiaomi.push.bo;
import com.xiaomi.push.bp;
import com.xiaomi.push.bq;
import com.xiaomi.push.bt;
import com.xiaomi.push.l;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static final int a;

    /* renamed from: a, reason: collision with other field name */
    private static volatile a f30a;

    /* renamed from: a, reason: collision with other field name */
    private Context f31a;

    /* renamed from: a, reason: collision with other field name */
    private Config f32a;

    /* renamed from: a, reason: collision with other field name */
    private IEventProcessor f33a;

    /* renamed from: a, reason: collision with other field name */
    private IPerfProcessor f34a;

    /* renamed from: a, reason: collision with other field name */
    private String f35a;

    /* renamed from: a, reason: collision with other field name */
    private ExecutorService f37a = Executors.newSingleThreadExecutor();

    /* renamed from: a, reason: collision with other field name */
    private HashMap<String, HashMap<String, com.xiaomi.clientreport.data.a>> f36a = new HashMap<>();
    private HashMap<String, ArrayList<com.xiaomi.clientreport.data.a>> b = new HashMap<>();

    static {
        a = l.m547a() ? 30 : 10;
    }

    private a(Context context) {
        this.f31a = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a() {
        if (this.b == null) {
            return 0;
        }
        Iterator<String> it = this.b.keySet().iterator();
        int size = 0;
        while (it.hasNext()) {
            ArrayList<com.xiaomi.clientreport.data.a> arrayList = this.b.get(it.next());
            size += arrayList != null ? arrayList.size() : 0;
        }
        return size;
    }

    public static a a(Context context) {
        if (f30a == null) {
            synchronized (a.class) {
                if (f30a == null) {
                    f30a = new a(context);
                }
            }
        }
        return f30a;
    }

    private void a(ai.a aVar, int i) {
        ai.a(this.f31a).b(aVar, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b() {
        int i = 0;
        if (this.f36a != null) {
            Iterator<String> it = this.f36a.keySet().iterator();
            while (it.hasNext()) {
                HashMap<String, com.xiaomi.clientreport.data.a> map = this.f36a.get(it.next());
                if (map != null) {
                    Iterator<String> it2 = map.keySet().iterator();
                    while (it2.hasNext()) {
                        com.xiaomi.clientreport.data.a aVar = map.get(it2.next());
                        if (aVar instanceof PerfClientReport) {
                            i = (int) (i + ((PerfClientReport) aVar).perfCounts);
                        }
                    }
                }
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(EventClientReport eventClientReport) {
        if (this.f33a != null) {
            this.f33a.mo30a(eventClientReport);
            if (a() < 10) {
                a(new d(this), a);
            } else {
                d();
                ai.a(this.f31a).m94a("100888");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(PerfClientReport perfClientReport) {
        if (this.f34a != null) {
            this.f34a.mo30a(perfClientReport);
            if (b() < 10) {
                a(new f(this), a);
            } else {
                e();
                ai.a(this.f31a).m94a("100889");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        try {
            this.f33a.b();
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.d("we: " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        try {
            this.f34a.b();
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.d("wp: " + e.getMessage());
        }
    }

    private void f() {
        if (a(this.f31a).m27a().isEventUploadSwitchOpen()) {
            bn bnVar = new bn(this.f31a);
            int eventUploadFrequency = (int) a(this.f31a).m27a().getEventUploadFrequency();
            if (eventUploadFrequency < 1800) {
                eventUploadFrequency = 1800;
            }
            if (System.currentTimeMillis() - bt.a(this.f31a).a("sp_client_report_status", "event_last_upload_time", 0L) > eventUploadFrequency * 1000) {
                ai.a(this.f31a).a(new h(this, bnVar), 10);
            }
            synchronized (a.class) {
                if (!ai.a(this.f31a).a((ai.a) bnVar, eventUploadFrequency)) {
                    ai.a(this.f31a).m94a("100886");
                    ai.a(this.f31a).a((ai.a) bnVar, eventUploadFrequency);
                }
            }
        }
    }

    private void g() {
        if (a(this.f31a).m27a().isPerfUploadSwitchOpen()) {
            bo boVar = new bo(this.f31a);
            int perfUploadFrequency = (int) a(this.f31a).m27a().getPerfUploadFrequency();
            if (perfUploadFrequency < 1800) {
                perfUploadFrequency = 1800;
            }
            if (System.currentTimeMillis() - bt.a(this.f31a).a("sp_client_report_status", "perf_last_upload_time", 0L) > perfUploadFrequency * 1000) {
                ai.a(this.f31a).a(new i(this, boVar), 15);
            }
            synchronized (a.class) {
                if (!ai.a(this.f31a).a((ai.a) boVar, perfUploadFrequency)) {
                    ai.a(this.f31a).m94a("100887");
                    ai.a(this.f31a).a((ai.a) boVar, perfUploadFrequency);
                }
            }
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public synchronized Config m27a() {
        if (this.f32a == null) {
            this.f32a = Config.defaultConfig(this.f31a);
        }
        return this.f32a;
    }

    public EventClientReport a(int i, String str) {
        EventClientReport eventClientReport = new EventClientReport();
        eventClientReport.eventContent = str;
        eventClientReport.eventTime = System.currentTimeMillis();
        eventClientReport.eventType = i;
        eventClientReport.eventId = bm.a(6);
        eventClientReport.production = 1000;
        eventClientReport.reportType = 1001;
        eventClientReport.clientInterfaceId = "E100004";
        eventClientReport.setAppPackageName(this.f31a.getPackageName());
        eventClientReport.setSdkVersion(this.f35a);
        return eventClientReport;
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m28a() {
        a(this.f31a).f();
        a(this.f31a).g();
    }

    public void a(Config config, IEventProcessor iEventProcessor, IPerfProcessor iPerfProcessor) {
        this.f32a = config;
        this.f33a = iEventProcessor;
        this.f34a = iPerfProcessor;
        this.f33a.setEventMap(this.b);
        this.f34a.setPerfMap(this.f36a);
    }

    public void a(EventClientReport eventClientReport) {
        if (m27a().isEventUploadSwitchOpen()) {
            this.f37a.execute(new b(this, eventClientReport));
        }
    }

    public void a(PerfClientReport perfClientReport) {
        if (m27a().isPerfUploadSwitchOpen()) {
            this.f37a.execute(new c(this, perfClientReport));
        }
    }

    public void a(String str) {
        this.f35a = str;
    }

    public void a(boolean z, boolean z2, long j, long j2) {
        if (this.f32a != null) {
            if (z == this.f32a.isEventUploadSwitchOpen() && z2 == this.f32a.isPerfUploadSwitchOpen() && j == this.f32a.getEventUploadFrequency() && j2 == this.f32a.getPerfUploadFrequency()) {
                return;
            }
            long eventUploadFrequency = this.f32a.getEventUploadFrequency();
            long perfUploadFrequency = this.f32a.getPerfUploadFrequency();
            Config configBuild = Config.getBuilder().setAESKey(bq.a(this.f31a)).setEventEncrypted(this.f32a.isEventEncrypted()).setEventUploadSwitchOpen(z).setEventUploadFrequency(j).setPerfUploadSwitchOpen(z2).setPerfUploadFrequency(j2).build(this.f31a);
            this.f32a = configBuild;
            if (!this.f32a.isEventUploadSwitchOpen()) {
                ai.a(this.f31a).m94a("100886");
            } else if (eventUploadFrequency != configBuild.getEventUploadFrequency()) {
                com.xiaomi.channel.commonutils.logger.b.c(this.f31a.getPackageName() + "reset event job " + configBuild.getEventUploadFrequency());
                f();
            }
            if (!this.f32a.isPerfUploadSwitchOpen()) {
                ai.a(this.f31a).m94a("100887");
                return;
            }
            if (perfUploadFrequency != configBuild.getPerfUploadFrequency()) {
                com.xiaomi.channel.commonutils.logger.b.c(this.f31a.getPackageName() + "reset perf job " + configBuild.getPerfUploadFrequency());
                g();
            }
        }
    }

    /* renamed from: b, reason: collision with other method in class */
    public void m29b() {
        if (m27a().isEventUploadSwitchOpen()) {
            bp bpVar = new bp();
            bpVar.a(this.f31a);
            bpVar.a(this.f33a);
            this.f37a.execute(bpVar);
        }
    }

    public void c() {
        if (m27a().isPerfUploadSwitchOpen()) {
            bp bpVar = new bp();
            bpVar.a(this.f34a);
            bpVar.a(this.f31a);
            this.f37a.execute(bpVar);
        }
    }
}
