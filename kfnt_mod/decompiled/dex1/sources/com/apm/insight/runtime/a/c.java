package com.apm.insight.runtime.a;

import android.content.Context;
import android.os.Process;
import android.os.SystemClock;
import androidx.annotation.Nullable;
import com.apm.insight.AttachUserData;
import com.apm.insight.CrashType;
import com.apm.insight.ICommonParams;
import com.apm.insight.l.v;
import com.apm.insight.l.w;
import com.appsflyer.share.Constants;
import com.bytedance.pangle.provider.ContentProviderManager;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class c {

    /* renamed from: a, reason: collision with root package name */
    protected CrashType f3009a;

    /* renamed from: b, reason: collision with root package name */
    protected Context f3010b;

    /* renamed from: c, reason: collision with root package name */
    protected ICommonParams f3011c = com.apm.insight.h.a().c();
    protected b d;
    protected d e;

    public interface a {
        com.apm.insight.entity.a a(int i, com.apm.insight.entity.a aVar);

        com.apm.insight.entity.a a(int i, com.apm.insight.entity.a aVar, boolean z);

        void a(Throwable th);
    }

    c(CrashType crashType, Context context, b bVar, d dVar) {
        this.f3009a = crashType;
        this.f3010b = context;
        this.d = bVar;
        this.e = dVar;
    }

    private void i(com.apm.insight.entity.a aVar) {
        List<AttachUserData> listA = com.apm.insight.h.b().a(this.f3009a);
        HashMap map = new HashMap();
        JSONObject jSONObjectOptJSONObject = aVar.h().optJSONObject("custom");
        if (jSONObjectOptJSONObject == null) {
            jSONObjectOptJSONObject = new JSONObject();
            aVar.a("custom", jSONObjectOptJSONObject);
        }
        if (listA != null) {
            for (int i = 0; i < listA.size(); i++) {
                try {
                    AttachUserData attachUserData = listA.get(i);
                    long jUptimeMillis = SystemClock.uptimeMillis();
                    com.apm.insight.entity.a.a(jSONObjectOptJSONObject, attachUserData.getUserData(this.f3009a));
                    map.put("custom_cost_" + attachUserData.getClass().getName() + "_" + map.size(), Long.valueOf(SystemClock.uptimeMillis() - jUptimeMillis));
                } catch (Throwable th) {
                    com.apm.insight.entity.a.a(jSONObjectOptJSONObject, th);
                }
            }
        }
        try {
            jSONObjectOptJSONObject.put("fd_count", com.apm.insight.l.h.a());
        } catch (Throwable unused) {
        }
        List<AttachUserData> listB = com.apm.insight.h.b().b(this.f3009a);
        if (listB != null) {
            JSONObject jSONObjectOptJSONObject2 = aVar.h().optJSONObject("custom_long");
            if (jSONObjectOptJSONObject2 == null) {
                jSONObjectOptJSONObject2 = new JSONObject();
                aVar.a("custom_long", jSONObjectOptJSONObject2);
            }
            for (int i2 = 0; i2 < listB.size(); i2++) {
                try {
                    AttachUserData attachUserData2 = listB.get(i2);
                    long jUptimeMillis2 = SystemClock.uptimeMillis();
                    com.apm.insight.entity.a.a(jSONObjectOptJSONObject2, attachUserData2.getUserData(this.f3009a));
                    map.put("custom_cost_" + attachUserData2.getClass().getName() + "_" + map.size(), Long.valueOf(SystemClock.uptimeMillis() - jUptimeMillis2));
                } catch (Throwable th2) {
                    com.apm.insight.entity.a.a(jSONObjectOptJSONObject2, th2);
                }
            }
        }
        for (Map.Entry entry : map.entrySet()) {
            try {
                jSONObjectOptJSONObject.put((String) entry.getKey(), entry.getValue());
            } catch (Throwable unused2) {
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public com.apm.insight.entity.a a(int i, com.apm.insight.entity.a aVar) throws JSONException {
        if (aVar == null) {
            aVar = new com.apm.insight.entity.a();
        }
        switch (i) {
            case 0:
                b(aVar);
                return aVar;
            case 1:
                c(aVar);
                i(aVar);
                return aVar;
            case 2:
                e(aVar);
                return aVar;
            case 3:
            default:
                return aVar;
            case 4:
                f(aVar);
                return aVar;
            case 5:
                d(aVar);
                return aVar;
        }
    }

    public com.apm.insight.entity.a a(com.apm.insight.entity.a aVar) {
        return aVar;
    }

    public com.apm.insight.entity.a a(@Nullable com.apm.insight.entity.a aVar, @Nullable a aVar2, boolean z) throws JSONException {
        if (aVar == null) {
            aVar = new com.apm.insight.entity.a();
        }
        com.apm.insight.entity.a aVar3 = aVar;
        com.apm.insight.entity.a aVar4 = aVar3;
        for (int i = 0; i < b(); i++) {
            long jUptimeMillis = SystemClock.uptimeMillis();
            if (aVar2 != null) {
                try {
                    aVar4 = aVar2.a(i, aVar4);
                } catch (Throwable th) {
                    aVar2.a(th);
                }
            }
            try {
                aVar4 = a(i, aVar4);
            } catch (Throwable th2) {
                if (aVar2 != null) {
                    aVar2.a(th2);
                }
            }
            if (aVar2 != null) {
                try {
                    boolean z2 = true;
                    if (i != b() - 1) {
                        z2 = false;
                    }
                    aVar4 = aVar2.a(i, aVar4, z2);
                } catch (Throwable th3) {
                    aVar2.a(th3);
                }
                if (z) {
                    if (i != 0) {
                        aVar3.c(aVar4.h());
                    } else {
                        aVar3 = aVar4;
                    }
                    aVar4 = new com.apm.insight.entity.a();
                }
            }
            aVar3.b("step_cost_" + i, String.valueOf(SystemClock.uptimeMillis() - jUptimeMillis));
        }
        return a(aVar3);
    }

    protected boolean a() {
        return false;
    }

    public int b() {
        return 6;
    }

    public com.apm.insight.entity.a b(com.apm.insight.entity.a aVar) throws JSONException {
        aVar.a(com.apm.insight.h.p(), com.apm.insight.h.q());
        if (com.apm.insight.h.m()) {
            aVar.a("is_mp", (Object) 1);
        }
        try {
            aVar.a(this.f3011c.getPluginInfo());
        } catch (Throwable th) {
            try {
                HashMap map = new HashMap();
                map.put("代码中发生了错误导致数据获取失败:\n" + v.a(th), 0);
                aVar.a(map);
            } catch (Throwable unused) {
            }
        }
        aVar.b(com.apm.insight.h.o());
        aVar.a(ContentProviderManager.PLUGIN_PROCESS_NAME, com.apm.insight.l.a.c(com.apm.insight.h.g()));
        return aVar;
    }

    public com.apm.insight.entity.a c(com.apm.insight.entity.a aVar) throws JSONException {
        if (!com.apm.insight.l.a.b(com.apm.insight.h.g())) {
            aVar.a("remote_process", (Object) 1);
        }
        aVar.a(Constants.URL_MEDIA_SOURCE, Integer.valueOf(Process.myPid()));
        aVar.a(com.apm.insight.h.j());
        if (c() && this.d != null) {
            aVar.a(this.d);
        }
        try {
            aVar.a(this.f3011c.getPatchInfo());
        } catch (Throwable th) {
            try {
                aVar.a(Arrays.asList("代码中发生了错误导致数据获取失败:\n" + v.a(th)));
            } catch (Throwable unused) {
            }
        }
        String strK = com.apm.insight.h.k();
        if (strK != null) {
            aVar.a("business", (Object) strK);
        }
        aVar.a("is_background", Boolean.valueOf(true ^ com.apm.insight.l.a.a(this.f3010b)));
        return aVar;
    }

    protected boolean c() {
        return true;
    }

    public com.apm.insight.entity.a d(com.apm.insight.entity.a aVar) {
        if (d()) {
            aVar.b(w.a(this.f3010b));
        }
        return aVar;
    }

    protected boolean d() {
        return true;
    }

    public com.apm.insight.entity.a e(com.apm.insight.entity.a aVar) throws JSONException {
        aVar.a("battery", Integer.valueOf(this.e == null ? 0 : this.e.a()));
        aVar.c(com.apm.insight.h.b().a());
        return aVar;
    }

    public com.apm.insight.entity.a f(com.apm.insight.entity.a aVar) {
        if (a()) {
            h(aVar);
        }
        return aVar;
    }

    void g(com.apm.insight.entity.a aVar) {
    }

    protected void h(com.apm.insight.entity.a aVar) {
    }
}
