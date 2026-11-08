package com.sigmob.sdk.base.common;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;
import com.qq.e.comm.constants.Constants;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.common.y;
import com.sigmob.sdk.base.common.z;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.base.models.SigMacroCommon;
import com.sigmob.sdk.base.views.e;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.a;
import com.sigmob.sdk.common.e.d;
import com.sigmob.sdk.common.mta.PointCategory;
import com.sigmob.sdk.common.utils.m;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class f implements Serializable {
    private static final long serialVersionUID = 2;
    protected String a;
    protected String b;
    protected a.EnumC0015a c = a.EnumC0015a.UNDEFINED;
    protected int d = 1;
    private final List<com.sigmob.sdk.videoAd.e> e = new ArrayList();
    private boolean f;
    private String g;
    private s h;
    private com.sigmob.sdk.base.views.e i;
    private WeakReference<Context> j;
    private e.b k;
    private BaseAdUnit l;

    protected f() {
    }

    public static f a(BaseAdUnit baseAdUnit) {
        f fVar = new f();
        fVar.b(baseAdUnit);
        return fVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Context context, final BaseAdUnit baseAdUnit, com.sigmob.sdk.base.a aVar, String str, boolean z) {
        final String strMacroProcess = baseAdUnit.getMacroCommon().macroProcess(baseAdUnit.getLanding_page());
        if (z) {
            if (baseAdUnit.getAd_type() == 5) {
                baseAdUnit.getSessionManager().a(a.AD_CLICK, 0);
            } else {
                t.a(aVar, "click", baseAdUnit, Constants.FAIL, strMacroProcess, str);
            }
        }
        if (baseAdUnit.getMaterial().interaction_type.intValue() == com.sigmob.sdk.videoAd.f.BrowserType.a()) {
            z.a(strMacroProcess, new z.a() { // from class: com.sigmob.sdk.base.common.f.3
                @Override // com.sigmob.sdk.base.common.z.a
                public void a(String str2) {
                    if (!baseAdUnit.getAd().forbiden_parse_landingpage.booleanValue()) {
                        t.a(baseAdUnit, str2);
                    }
                    try {
                        com.sigmob.sdk.common.utils.j.a(context.getApplicationContext(), Uri.parse(str2));
                    } catch (com.sigmob.sdk.common.c.b e) {
                        SigmobLog.e(e.getMessage());
                    }
                }

                @Override // com.sigmob.sdk.base.common.z.a
                public void a(String str2, Throwable th) {
                    if (!baseAdUnit.getAd().forbiden_parse_landingpage.booleanValue()) {
                        t.a(baseAdUnit, strMacroProcess);
                    }
                    try {
                        com.sigmob.sdk.common.utils.j.a(context.getApplicationContext(), Uri.parse(strMacroProcess));
                    } catch (com.sigmob.sdk.common.c.b e) {
                        SigmobLog.e(e.getMessage());
                    }
                }
            });
        } else {
            a(context, baseAdUnit, strMacroProcess, aVar, str, z);
        }
    }

    private void a(final BaseAdUnit baseAdUnit, final Context context, String str, String str2, final com.sigmob.sdk.base.a aVar, final String str3, final boolean z) {
        new y.a().a(x.IGNORE_ABOUT_SCHEME, x.OPEN_WITH_BROWSER, x.FOLLOW_DEEP_LINK).a(new y.b() { // from class: com.sigmob.sdk.base.common.f.4
            @Override // com.sigmob.sdk.base.common.y.b
            public void a(String str4, x xVar) {
                if (!baseAdUnit.getAd().forbiden_parse_landingpage.booleanValue()) {
                    t.a(baseAdUnit, str4);
                }
                if (z) {
                    com.sigmob.sdk.base.a.c.a(baseAdUnit, a.AD_OPEN_DEEPLINK);
                    t.a(PointCategory.OPEN_DEEPLINK, (String) null, baseAdUnit);
                    if (baseAdUnit.getAd_type() == 5) {
                        baseAdUnit.getSessionManager().a(a.AD_CLICK, 0);
                    } else {
                        t.a(aVar, "click", baseAdUnit, "1", str4, str3);
                    }
                }
                if (f.this.h != null) {
                    f.this.h.a();
                }
            }

            @Override // com.sigmob.sdk.base.common.y.b
            public void b(String str4, x xVar) {
                if (!baseAdUnit.getAd().forbiden_parse_landingpage.booleanValue()) {
                    t.a(baseAdUnit, str4);
                }
                if (z) {
                    com.sigmob.sdk.base.a.c.a(baseAdUnit, a.AD_OPEN_DEEPLINK_FAIL);
                    t.a(PointCategory.OPEN_DEEPLINK_FAILED, (String) null, baseAdUnit);
                }
            }

            @Override // com.sigmob.sdk.base.common.y.b
            public void c(String str4, x xVar) {
                com.sigmob.sdk.base.a aVar2;
                String str5;
                BaseAdUnit baseAdUnit2;
                String str6;
                SigmobLog.d("urlHandlingFailed: " + xVar.name() + " url: " + str4);
                if (!baseAdUnit.getAd().forbiden_parse_landingpage.booleanValue()) {
                    t.a(baseAdUnit, str4);
                }
                if (x.FOLLOW_DEEP_LINK == xVar) {
                    if (z) {
                        com.sigmob.sdk.base.a.c.a(baseAdUnit, a.AD_OPEN_DEEPLINK);
                        t.a(PointCategory.OPEN_DEEPLINK, (String) null, baseAdUnit);
                        if (baseAdUnit.getAd_type() != 5) {
                            aVar2 = aVar;
                            str5 = "click";
                            baseAdUnit2 = baseAdUnit;
                            str6 = "1";
                            t.a(aVar2, str5, baseAdUnit2, str6, str4, str3);
                        }
                        baseAdUnit.getSessionManager().a(a.AD_CLICK, 0);
                    }
                } else if (z) {
                    if (baseAdUnit.getAd_type() != 5) {
                        aVar2 = aVar;
                        str5 = "click";
                        baseAdUnit2 = baseAdUnit;
                        str6 = Constants.FAIL;
                        t.a(aVar2, str5, baseAdUnit2, str6, str4, str3);
                    }
                    baseAdUnit.getSessionManager().a(a.AD_CLICK, 0);
                }
                if (f.this.h != null) {
                    f.this.h.a();
                }
            }

            @Override // com.sigmob.sdk.base.common.y.b
            public void d(String str4, x xVar) {
                SigmobLog.d("urlHandlingFailed: " + xVar.name() + " url: " + str4);
                if (!baseAdUnit.getAd().forbiden_parse_landingpage.booleanValue()) {
                    t.a(baseAdUnit, str4);
                }
                if (x.FOLLOW_DEEP_LINK == xVar) {
                    com.sigmob.sdk.base.a.c.a(baseAdUnit, a.AD_OPEN_DEEPLINK_FAIL);
                    t.a(PointCategory.OPEN_DEEPLINK_FAILED, (String) null, baseAdUnit);
                    f.this.a(context, baseAdUnit, aVar, str3, z);
                } else if (z) {
                    if (baseAdUnit.getAd_type() == 5) {
                        baseAdUnit.getSessionManager().a(a.AD_CLICK, 0);
                    } else {
                        t.a(aVar, "click", baseAdUnit, Constants.FAIL, str4, str3);
                    }
                    if (f.this.h != null) {
                        f.this.h.a();
                    }
                }
            }
        }).a(baseAdUnit).a(baseAdUnit.isSkipSigmobBrowser()).b(baseAdUnit.getAd().forbiden_parse_landingpage.booleanValue()).a().a(context, str, str2);
    }

    private boolean d() {
        if (this.j != null && this.l != null) {
            final Context context = this.j.get();
            if (context != null && com.sigmob.sdk.common.a.n().ar() && this.l.getadPrivacy() != null && this.i == null) {
                this.i = new com.sigmob.sdk.base.views.e(context, this.l);
                this.i.a(new e.b() { // from class: com.sigmob.sdk.base.common.f.1
                    @Override // com.sigmob.sdk.base.views.e.b
                    public void a() {
                        if (f.this.i != null) {
                            f.this.i.dismiss();
                            f.this.i.c();
                            f.this.i = null;
                            f.this.f = false;
                        }
                        if (f.this.l == null) {
                            return;
                        }
                        v sessionManager = f.this.l.getSessionManager();
                        if (sessionManager != null) {
                            sessionManager.a(a.AD_FOUR_ELEMENTS_CLOSE, 0);
                        }
                        if (f.this.k != null) {
                            f.this.k.a();
                        }
                    }

                    @Override // com.sigmob.sdk.base.views.e.b
                    public void a(String str, String str2) {
                        if (f.this.l == null) {
                            return;
                        }
                        f.this.a(f.this.l, context, str, com.sigmob.sdk.base.a.COMPANION, str2, true);
                        if (f.this.k != null) {
                            f.this.k.a(str, str2);
                        }
                    }

                    @Override // com.sigmob.sdk.base.views.e.b
                    public void b() {
                        if (f.this.l == null) {
                            return;
                        }
                        v sessionManager = f.this.l.getSessionManager();
                        if (sessionManager != null) {
                            sessionManager.a(a.AD_FOUR_ELEMENTS_SHOW, 0);
                        }
                        if (f.this.k != null) {
                            f.this.k.b();
                        }
                    }
                });
            }
            if (this.i != null && this.i.a() && !this.f) {
                this.i.show();
                this.f = true;
                return true;
            }
        }
        return false;
    }

    public String a() {
        return this.a;
    }

    public List<e> a(long j, long j2) {
        if (j2 <= 0 || j < 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        com.sigmob.sdk.videoAd.e eVar = new com.sigmob.sdk.videoAd.e(a.AD_PLAY_QUARTER, j / j2);
        int size = this.e.size();
        for (int i = 0; i < size; i++) {
            com.sigmob.sdk.videoAd.e eVar2 = this.e.get(i);
            if (eVar2.compareTo(eVar) > 0) {
                break;
            }
            if (!eVar2.l()) {
                arrayList.add(eVar2);
            }
        }
        return arrayList;
    }

    public void a(Context context, int i, BaseAdUnit baseAdUnit) {
        m.a.a(context, "context cannot be null");
        t.b(PointCategory.START, null, baseAdUnit);
        com.sigmob.sdk.base.a.c.a(baseAdUnit, a.AD_START);
    }

    public void a(Context context, BaseAdUnit baseAdUnit) {
        t.a(PointCategory.LANDING_PAGE_SHOW, (String) null, baseAdUnit);
    }

    public void a(Context context, BaseAdUnit baseAdUnit, e.b bVar) {
        if (context == null || baseAdUnit == null) {
            return;
        }
        this.j = new WeakReference<>(context);
        this.l = baseAdUnit;
        this.k = bVar;
    }

    public void a(Context context, BaseAdUnit baseAdUnit, String str, com.sigmob.sdk.base.a aVar, String str2, boolean z) {
        if (d()) {
            return;
        }
        b(context, baseAdUnit, str, aVar, str2, z);
    }

    public void a(Context context, com.sigmob.sdk.common.utils.q qVar, com.sigmob.sdk.common.utils.q qVar2, com.sigmob.sdk.base.a aVar, BaseAdUnit baseAdUnit) {
        m.a.a(context, "context cannot be null");
    }

    public void a(Context context, String str, BaseAdUnit baseAdUnit, String str2, int i) {
        m.a.a(context, "context cannot be null");
        t.a(str2, i, str, baseAdUnit);
    }

    public void a(s sVar) {
        this.h = sVar;
    }

    public void a(BaseAdUnit baseAdUnit, Context context, String str, com.sigmob.sdk.base.a aVar, String str2, boolean z) {
        String strMacroProcess = !TextUtils.isEmpty(str) ? str : null;
        int iIntValue = baseAdUnit.getMaterial().interaction_type.intValue();
        String strMacroProcess2 = TextUtils.isEmpty(baseAdUnit.getMaterial().deeplink_url) ? null : baseAdUnit.getMacroCommon().macroProcess(baseAdUnit.getMaterial().deeplink_url);
        if (!TextUtils.isEmpty(baseAdUnit.getLanding_page())) {
            strMacroProcess = baseAdUnit.getLanding_page();
        }
        if (!TextUtils.isEmpty(strMacroProcess)) {
            strMacroProcess = baseAdUnit.getMacroCommon().macroProcess(strMacroProcess);
        }
        String str3 = strMacroProcess;
        if (iIntValue != com.sigmob.sdk.videoAd.f.DownloadType.a()) {
            a(baseAdUnit, context, strMacroProcess2, str3, aVar, str2, z);
        } else {
            b(context, baseAdUnit, str3, aVar, str2, z);
        }
    }

    public void a(BaseAdUnit baseAdUnit, Context context, String str, com.sigmob.sdk.base.a aVar, String str2, boolean z, boolean z2) {
        if (TextUtils.isEmpty(str) || !z2) {
            b(baseAdUnit, context, str, aVar, str2, z);
        } else {
            a(context, baseAdUnit, str, aVar, str2, z);
        }
    }

    protected void a(String str) {
        this.a = str;
    }

    public void a(List<com.sigmob.sdk.videoAd.e> list) {
        m.a.a(list, "fractionalTrackers cannot be null");
        this.e.addAll(list);
        Collections.sort(this.e);
    }

    public String b() {
        return this.b;
    }

    public void b(Context context, int i, BaseAdUnit baseAdUnit) {
        m.a.a(context, "context cannot be null");
    }

    public void b(Context context, BaseAdUnit baseAdUnit) {
        t.a(PointCategory.LANDING_PAGE_CLOSE, (String) null, baseAdUnit);
    }

    public void b(final Context context, final BaseAdUnit baseAdUnit, String str, com.sigmob.sdk.base.a aVar, String str2, boolean z) {
        if (l.b(context, this.g)) {
            try {
                Toast.makeText(context, "当前正在努力下载，请稍等", 1).show();
                return;
            } catch (Throwable unused) {
                return;
            }
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String strMacroProcess = baseAdUnit.getMacroCommon().macroProcess(str);
        if (z) {
            if (baseAdUnit.getAd_type() == 5) {
                baseAdUnit.getSessionManager().a(a.AD_CLICK, 0);
            } else {
                t.a(aVar, "click", baseAdUnit, Constants.FAIL, strMacroProcess, str2);
            }
        }
        if (baseAdUnit.getMaterial().sub_interaction_type.intValue() == 1) {
            com.sigmob.sdk.common.e.d dVar = new com.sigmob.sdk.common.e.d(strMacroProcess, new d.a() { // from class: com.sigmob.sdk.base.common.f.2
                @Override // com.sigmob.volley.n.a
                public void onErrorResponse(com.sigmob.volley.s sVar) {
                    t.b(PointCategory.DOWNLOAD_START, Constants.FAIL, baseAdUnit);
                    t.a(PointCategory.DOWNLOAD_START, 0, sVar.getMessage(), baseAdUnit);
                    SigmobLog.e(sVar.getMessage());
                }

                @Override // com.sigmob.sdk.common.e.d.a
                public void onSuccess(JSONObject jSONObject) {
                    try {
                        SigmobLog.d("GDTConvertRequest response " + jSONObject);
                        if (jSONObject.getInt(Constants.KEYS.RET) != 0) {
                            t.b(PointCategory.DOWNLOAD_START, com.sigmob.sdk.common.Constants.FAIL, baseAdUnit);
                            t.a(PointCategory.DOWNLOAD_START, 0, jSONObject.toString(), baseAdUnit);
                            return;
                        }
                        String string = jSONObject.getJSONObject("data").getString("dstlink");
                        String string2 = jSONObject.getJSONObject("data").getString("clickid");
                        SigMacroCommon macroCommon = baseAdUnit.getMacroCommon();
                        if (macroCommon instanceof SigMacroCommon) {
                            macroCommon.addMarcoKey(SigMacroCommon._CLICKID_, string2);
                        }
                        f.this.g = string;
                        l.a(context.getApplicationContext(), string, baseAdUnit);
                    } catch (Throwable th) {
                        SigmobLog.e(th.getMessage());
                        t.b(PointCategory.DOWNLOAD_START, com.sigmob.sdk.common.Constants.FAIL, baseAdUnit);
                        t.a(PointCategory.DOWNLOAD_START, 0, th.getMessage(), baseAdUnit);
                    }
                }
            }, 1);
            com.sigmob.sdk.common.e.l lVarA = com.sigmob.sdk.common.e.f.a();
            if (lVarA == null) {
                t.b(PointCategory.DOWNLOAD_START, com.sigmob.sdk.common.Constants.FAIL, baseAdUnit);
                t.a(PointCategory.DOWNLOAD_START, 0, "request queue is null", baseAdUnit);
            } else {
                dVar.b((Object) "GDTRequestConvert");
                lVarA.a(dVar);
            }
        } else {
            this.g = strMacroProcess;
            l.a(context.getApplicationContext(), strMacroProcess, baseAdUnit);
        }
        if (this.h != null) {
            this.h.a();
        }
    }

    public void b(BaseAdUnit baseAdUnit) {
        a(baseAdUnit.getSplashFilePath());
    }

    public void b(BaseAdUnit baseAdUnit, Context context, String str, com.sigmob.sdk.base.a aVar, String str2, boolean z) {
        String strMacroProcess = !TextUtils.isEmpty(str) ? str : null;
        int iIntValue = baseAdUnit.getMaterial().interaction_type.intValue();
        String strMacroProcess2 = TextUtils.isEmpty(baseAdUnit.getMaterial().deeplink_url) ? null : baseAdUnit.getMacroCommon().macroProcess(baseAdUnit.getMaterial().deeplink_url);
        if (!TextUtils.isEmpty(baseAdUnit.getLanding_page())) {
            strMacroProcess = baseAdUnit.getLanding_page();
        }
        if (!TextUtils.isEmpty(strMacroProcess)) {
            strMacroProcess = baseAdUnit.getMacroCommon().macroProcess(strMacroProcess);
        }
        String str3 = strMacroProcess;
        if (iIntValue != com.sigmob.sdk.videoAd.f.DownloadType.a()) {
            a(baseAdUnit, context, strMacroProcess2, str3, aVar, str2, z);
        } else {
            a(context, baseAdUnit, str3, aVar, str2, z);
        }
    }

    public void c() {
        this.l = null;
        this.k = null;
    }

    public void c(Context context, int i, BaseAdUnit baseAdUnit) {
        m.a.a(context, "context cannot be null");
    }

    public void d(Context context, int i, BaseAdUnit baseAdUnit) {
        m.a.a(context, "context cannot be null");
        com.sigmob.sdk.base.a.c.a(baseAdUnit, a.AD_OPEN_DEEPLINK);
        t.a(PointCategory.OPEN_DEEPLINK, (String) null, baseAdUnit);
    }
}
