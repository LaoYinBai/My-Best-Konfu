package com.sigmob.sdk.base.a;

import android.text.TextUtils;
import android.util.Log;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.common.e;
import com.sigmob.sdk.base.common.t;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.base.models.SigMacroCommon;
import com.sigmob.sdk.common.SDKConfig;
import com.sigmob.sdk.common.a.c;
import com.sigmob.sdk.common.e.f;
import com.sigmob.sdk.common.e.k;
import com.sigmob.volley.i;
import com.sigmob.volley.n;
import com.sigmob.volley.p;
import com.sigmob.volley.s;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c extends k<i> {
    private static int b;
    private final a c;

    public interface a extends n.a {
        void a(i iVar);
    }

    public c(String str, int i, int i2, a aVar) {
        super(str, 0, aVar);
        this.c = aVar;
        int i3 = 3;
        if (i < 0) {
            i3 = 0;
        } else if (i <= 3) {
            i3 = i;
        }
        b(true);
        a((p) new com.sigmob.volley.c(i2, 10000, i3, 0.0f));
        a(false);
    }

    public c(String str, int i, a aVar) {
        this(str, i, 10000, aVar);
    }

    public static int a(BaseAdUnit baseAdUnit, String str, boolean z) throws UnsupportedEncodingException {
        if (baseAdUnit == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str)) {
            return -1;
        }
        List<e> adTracker = baseAdUnit.getAdTracker(str);
        if (adTracker == null || adTracker.isEmpty()) {
            return -2;
        }
        for (e eVar : adTracker) {
            if (z) {
                eVar.a("js");
            }
            SigMacroCommon macroCommon = baseAdUnit.getMacroCommon();
            if (macroCommon instanceof SigMacroCommon) {
                macroCommon.addMarcoKey(SigMacroCommon._PLAYFIRSTFRAME_, "1");
            }
            a(eVar, baseAdUnit, false);
        }
        return 0;
    }

    public static void a(final e eVar, final BaseAdUnit baseAdUnit, boolean z) {
        if (eVar == null || eVar.h() != e.a.TRACKING_URL || eVar.l()) {
            return;
        }
        final String strMacroProcess = baseAdUnit != null ? baseAdUnit.getMacroCommon().macroProcess(eVar.j()) : eVar.j();
        if (!z) {
            eVar.k();
        }
        final boolean z2 = eVar.e() != null;
        c cVar = new c(strMacroProcess, z2 ? 0 : eVar.i().intValue(), new a() { // from class: com.sigmob.sdk.base.a.c.1
            @Override // com.sigmob.sdk.base.a.c.a
            public void a(i iVar) {
                t.a(eVar, strMacroProcess, baseAdUnit, iVar, (t.a) null);
                if (z2) {
                    Log.i("adtracker", "retry Send success " + c.b);
                    com.sigmob.sdk.common.b.c.a(new Runnable() { // from class: com.sigmob.sdk.base.a.c.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            eVar.f();
                        }
                    });
                }
            }

            @Override // com.sigmob.volley.n.a
            public void onErrorResponse(s sVar) {
                i iVar = sVar.a;
                if (eVar.i().intValue() > 0 || z2) {
                    com.sigmob.sdk.common.b.c.a(new Runnable() { // from class: com.sigmob.sdk.base.a.c.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (!z2) {
                                eVar.b(strMacroProcess);
                                eVar.a((c.a) null);
                                return;
                            }
                            eVar.c();
                            if (eVar.b() >= SDKConfig.sharedInstance().getAdTrackerMaxRetryNum()) {
                                eVar.f();
                            } else {
                                eVar.d();
                            }
                        }
                    });
                }
                t.a(eVar, strMacroProcess, baseAdUnit, sVar);
                SigmobLog.e(sVar.getMessage());
            }
        });
        if (f.g() != null) {
            f.g().a(cVar);
        } else {
            t.a(eVar, strMacroProcess, baseAdUnit, (s) null);
            SigmobLog.e("RequestQueue is null");
        }
    }

    public static void a(BaseAdUnit baseAdUnit, com.sigmob.sdk.base.common.a aVar) {
        a(baseAdUnit, aVar, false);
    }

    public static void a(BaseAdUnit baseAdUnit, com.sigmob.sdk.base.common.a aVar, boolean z) throws UnsupportedEncodingException {
        List<e> adTracker;
        if (aVar == null || baseAdUnit == null || TextUtils.isEmpty(aVar.a()) || (adTracker = baseAdUnit.getAdTracker(aVar.a())) == null) {
            return;
        }
        for (e eVar : adTracker) {
            SigMacroCommon macroCommon = baseAdUnit.getMacroCommon();
            if (macroCommon instanceof SigMacroCommon) {
                macroCommon.addMarcoKey(SigMacroCommon._PLAYFIRSTFRAME_, "1");
            }
            a(eVar, baseAdUnit, z);
        }
    }

    public static void a_() throws Throwable {
        e.b(3000L);
        f.g().a();
        Iterator<e> it = e.a(3000, SDKConfig.sharedInstance().getADTrackerExpiredTime()).iterator();
        while (it.hasNext()) {
            a(it.next(), (BaseAdUnit) null, false);
        }
        e.a(SDKConfig.sharedInstance().getADTrackerExpiredTime());
    }

    @Override // com.sigmob.sdk.common.e.k, com.sigmob.volley.l
    protected n<i> a(i iVar) {
        return n.a(iVar, null);
    }

    @Override // com.sigmob.volley.l
    public void a(s sVar) {
        SigmobLog.e("send tracking: " + h() + " fail");
        super.a(sVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sigmob.volley.l
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void a(i iVar) {
        a aVar;
        synchronized (this.a) {
            aVar = this.c;
        }
        SigmobLog.i("send tracking: " + h() + " success");
        if (aVar != null) {
            aVar.a(iVar);
        }
    }

    @Override // com.sigmob.volley.l
    public int d() {
        return 100;
    }
}
