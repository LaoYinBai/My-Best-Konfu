package com.umeng.commonsdk.statistics;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.proguard.al;
import com.umeng.commonsdk.proguard.o;
import com.umeng.commonsdk.proguard.w;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.common.ReportPolicy;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.statistics.idtracking.e;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.statistics.internal.StatTracer;
import com.umeng.commonsdk.statistics.internal.d;
import com.umeng.commonsdk.statistics.noise.ABTest;
import com.umeng.commonsdk.statistics.noise.Defcon;
import com.umeng.commonsdk.statistics.proto.Response;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c {
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final String o = "thtstart";
    private static final String p = "gkvc";
    private static final String q = "ekvc";
    String a;
    private com.umeng.commonsdk.statistics.internal.c f;
    private ImprintHandler g;
    private e h;
    private ImprintHandler.a i;
    private Defcon k;
    private long l;
    private int m;
    private int n;
    private Context r;
    private final int e = 1;
    private ABTest j = null;
    private ReportPolicy.ReportStrategy s = null;

    public c(Context context) {
        this.i = null;
        this.k = null;
        this.l = 0L;
        this.m = 0;
        this.n = 0;
        this.a = null;
        this.r = context;
        this.i = ImprintHandler.getImprintService(this.r).c();
        this.k = Defcon.getService(this.r);
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(this.r);
        this.l = sharedPreferences.getLong(o, 0L);
        this.m = sharedPreferences.getInt(p, 0);
        this.n = sharedPreferences.getInt(q, 0);
        this.a = UMEnvelopeBuild.imprintProperty(this.r, "track_list", null);
        this.g = ImprintHandler.getImprintService(this.r);
        this.g.a(new d() { // from class: com.umeng.commonsdk.statistics.c.1
            @Override // com.umeng.commonsdk.statistics.internal.d
            public void onImprintChanged(ImprintHandler.a aVar) {
                Class<?> cls;
                c.this.k.onImprintChanged(aVar);
                c.this.a = UMEnvelopeBuild.imprintProperty(c.this.r, "track_list", null);
                try {
                    String strA = com.umeng.commonsdk.framework.a.a(c.this.r, o.e, (String) null);
                    if (TextUtils.isEmpty(strA) || (cls = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent")) == null) {
                        return;
                    }
                    cls.getMethod("updateUMTT", Context.class, String.class).invoke(cls, c.this.r, strA);
                } catch (Throwable unused) {
                }
            }
        });
        this.h = e.a(this.r);
        this.f = new com.umeng.commonsdk.statistics.internal.c(this.r);
        this.f.a(StatTracer.getInstance(this.r));
    }

    public boolean a(File file) {
        int iA;
        if (file == null) {
            return false;
        }
        try {
            byte[] byteArray = UMFrUtils.toByteArray(file.getPath());
            if (byteArray == null) {
                return false;
            }
            com.umeng.commonsdk.statistics.internal.a.a(this.r).c(file.getName());
            boolean zA = com.umeng.commonsdk.statistics.internal.a.a(this.r).a(file.getName());
            boolean zB = com.umeng.commonsdk.statistics.internal.a.a(this.r).b(file.getName());
            byte[] bArrA = this.f.a(byteArray, zA);
            if (bArrA == null) {
                iA = 1;
            } else {
                iA = a(bArrA, zA || zB);
            }
            switch (iA) {
                case 2:
                    this.h.d();
                    StatTracer.getInstance(this.r).saveSate();
                    break;
                case 3:
                    StatTracer.getInstance(this.r).saveSate();
                    break;
            }
            return iA == 2;
        } catch (Throwable th) {
            UMCrashManager.reportCrash(this.r, th);
            return false;
        }
    }

    private int a(byte[] bArr, boolean z) {
        Response response = new Response();
        try {
            new w(new al.a()).a(response, bArr);
            if (response.resp_code == 1) {
                this.g.b(response.getImprint());
                this.g.d();
            }
            if (z) {
                MLog.i("send log:" + response.getMsg());
            } else {
                MLog.i("inner req:" + response.getMsg());
            }
            if (z) {
                UMRTLog.i(UMRTLog.RTLOG_TAG, "send log: " + response.getMsg());
            } else {
                UMRTLog.i(UMRTLog.RTLOG_TAG, "inner req: " + response.getMsg());
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(this.r, th);
        }
        return response.resp_code == 1 ? 2 : 3;
    }
}
