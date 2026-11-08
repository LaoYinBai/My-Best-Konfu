package com.taobao.accs.ut.monitor;

import android.content.Context;
import android.text.TextUtils;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.statist.Dimension;
import anet.channel.statist.Measure;
import anet.channel.statist.Monitor;
import com.sigmob.sdk.common.Constants;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UtilityImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TrafficsMonitor {
    private Context d;
    private Map<String, List<a>> a = new HashMap();
    private Map<String, String> b = new HashMap<String, String>() { // from class: com.taobao.accs.ut.monitor.TrafficsMonitor.1
        {
            put("im", "512");
            put("motu", "513");
            put("acds", "514");
            put(GlobalClientInfo.AGOO_SERVICE_ID, "515");
            put("agooAck", "515");
            put("agooTokenReport", "515");
            put("accsSelf", Constants.SIGMOB_CHANNEL);
        }
    };
    private int c = 0;
    private String e = "";

    @Monitor(module = "NetworkSDK", monitorPoint = "TrafficStats")
    public static class StatTrafficMonitor extends BaseMonitor {

        @Dimension
        public String bizId;

        @Dimension
        public String date;

        @Dimension
        public String host;

        @Dimension
        public boolean isBackground;

        @Dimension
        public String serviceId;

        @Measure
        public long size;
    }

    public TrafficsMonitor(Context context) {
        this.d = context;
    }

    public void a(a aVar) {
        boolean z;
        if (aVar == null || aVar.e == null || aVar.f <= 0) {
            return;
        }
        aVar.c = TextUtils.isEmpty(aVar.c) ? "accsSelf" : aVar.c;
        synchronized (this.a) {
            String str = this.b.get(aVar.c);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            aVar.b = str;
            ALog.isPrintLog(ALog.Level.D);
            List<a> arrayList = this.a.get(str);
            if (arrayList != null) {
                Iterator<a> it = arrayList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = true;
                        break;
                    }
                    a next = it.next();
                    if (next.d == aVar.d && next.e != null && next.e.equals(aVar.e)) {
                        next.f += aVar.f;
                        z = false;
                        break;
                    }
                }
                if (z) {
                    arrayList.add(aVar);
                }
            } else {
                arrayList = new ArrayList<>();
                arrayList.add(aVar);
            }
            this.a.put(str, arrayList);
            this.c++;
            if (this.c >= 10) {
                b();
            }
        }
    }

    private void b() {
        String str;
        boolean z;
        synchronized (this.a) {
            String strA = UtilityImpl.a(System.currentTimeMillis());
            if (TextUtils.isEmpty(this.e) || this.e.equals(strA)) {
                str = strA;
                z = false;
            } else {
                str = this.e;
                z = true;
            }
            Iterator<String> it = this.a.keySet().iterator();
            while (it.hasNext()) {
                for (a aVar : this.a.get(it.next())) {
                    if (aVar != null) {
                        com.taobao.accs.c.a.a(this.d).a(aVar.e, aVar.c, this.b.get(aVar.c), aVar.d, aVar.f, str);
                    }
                }
            }
            if (ALog.isPrintLog(ALog.Level.D)) {
                ALog.d("TrafficsMonitor", "savetoDay:" + str + " saveTraffics" + this.a.toString(), new Object[0]);
            }
            if (z) {
                this.a.clear();
                c();
            } else if (ALog.isPrintLog(ALog.Level.D)) {
                ALog.d("TrafficsMonitor", "no need commit lastsaveDay:" + this.e + " currday:" + strA, new Object[0]);
            }
            this.e = strA;
            this.c = 0;
        }
    }

    public void a() {
        try {
            synchronized (this.a) {
                this.a.clear();
            }
            List<a> listA = com.taobao.accs.c.a.a(this.d).a(true);
            if (listA == null) {
                return;
            }
            Iterator<a> it = listA.iterator();
            while (it.hasNext()) {
                a(it.next());
            }
        } catch (Exception e) {
            ALog.w("TrafficsMonitor", e.toString(), new Object[0]);
        }
    }

    private void c() {
        List<a> listA = com.taobao.accs.c.a.a(this.d).a(false);
        if (listA == null) {
            return;
        }
        try {
            for (a aVar : listA) {
                if (aVar != null) {
                    StatTrafficMonitor statTrafficMonitor = new StatTrafficMonitor();
                    statTrafficMonitor.bizId = aVar.b;
                    statTrafficMonitor.date = aVar.a;
                    statTrafficMonitor.host = aVar.e;
                    statTrafficMonitor.isBackground = aVar.d;
                    statTrafficMonitor.size = aVar.f;
                    AppMonitor.getInstance().commitStat(statTrafficMonitor);
                }
            }
            com.taobao.accs.c.a.a(this.d).a();
        } catch (Throwable th) {
            ALog.e("", th.toString(), new Object[0]);
            th.printStackTrace();
        }
    }

    public static class a {
        String a;
        String b;
        String c;
        boolean d;
        String e;
        long f;

        public a(String str, boolean z, String str2, long j) {
            this.c = str;
            this.d = z;
            this.e = str2;
            this.f = j;
        }

        public a(String str, String str2, String str3, boolean z, String str4, long j) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = z;
            this.e = str4;
            this.f = j;
        }

        public String toString() {
            return "date:" + this.a + " bizId:" + this.b + " serviceId:" + this.c + " host:" + this.e + " isBackground:" + this.d + " size:" + this.f;
        }
    }
}
