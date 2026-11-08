package com.sigmob.sdk.videoAd;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.common.t;
import com.sigmob.sdk.base.common.x;
import com.sigmob.sdk.base.common.y;
import com.sigmob.sdk.base.common.z;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.mta.PointCategory;
import com.sigmob.sdk.common.utils.j;
import com.sigmob.sdk.common.utils.m;
import java.io.Serializable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class g implements Serializable {
    private static final long serialVersionUID = 0;
    private final int a;
    private final int b;
    private final com.sigmob.sdk.base.views.b c;
    private final int d;
    private a e;
    private String f;
    private String g;
    private int h;
    private boolean i = false;
    private com.sigmob.sdk.videoAd.a j;

    public interface a {
        void b_();

        void c_();
    }

    g(int i, int i2, int i3, String str, String str2, com.sigmob.sdk.base.views.b bVar) {
        this.a = i;
        this.b = i2;
        this.d = i3;
        this.g = str2;
        this.f = str;
        this.c = bVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Context context, final BaseAdUnit baseAdUnit, String str, com.sigmob.sdk.base.a aVar, String str2) {
        if (TextUtils.isEmpty(this.f)) {
            if (this.e != null) {
                this.e.c_();
                return;
            }
            return;
        }
        final String strMacroProcess = baseAdUnit.getMacroCommon().macroProcess(this.f);
        t.a(str, "click", baseAdUnit, Constants.FAIL, strMacroProcess, str2, this.h);
        if (this.d == f.BrowserType.a()) {
            z.a(strMacroProcess, new z.a() { // from class: com.sigmob.sdk.videoAd.g.2
                @Override // com.sigmob.sdk.base.common.z.a
                public void a(String str3) {
                    if (!baseAdUnit.getAd().forbiden_parse_landingpage.booleanValue()) {
                        t.a(baseAdUnit, str3);
                    }
                    try {
                        j.a(context.getApplicationContext(), Uri.parse(str3));
                        if (g.this.e != null) {
                            g.this.e.b_();
                        }
                    } catch (Throwable th) {
                        SigmobLog.e(th.getMessage());
                        if (g.this.e != null) {
                            g.this.e.c_();
                        }
                    }
                }

                @Override // com.sigmob.sdk.base.common.z.a
                public void a(String str3, Throwable th) {
                    if (!baseAdUnit.getAd().forbiden_parse_landingpage.booleanValue()) {
                        t.a(baseAdUnit, strMacroProcess);
                    }
                    try {
                        j.a(context.getApplicationContext(), Uri.parse(strMacroProcess));
                        if (g.this.e != null) {
                            g.this.e.b_();
                        }
                    } catch (com.sigmob.sdk.common.c.b e) {
                        SigmobLog.e(e.getMessage());
                        if (g.this.e != null) {
                            g.this.e.c_();
                        }
                    }
                }
            });
            return;
        }
        this.j.a(context, baseAdUnit, strMacroProcess, aVar, str2, false);
        if (this.e != null) {
            this.e.b_();
        }
    }

    private void a(com.sigmob.sdk.base.a aVar, BaseAdUnit baseAdUnit, String str, String str2) {
        switch (aVar) {
            case COMPANION:
                t.a(PointCategory.COMPANION, "click", baseAdUnit, Constants.FAIL, str, str2, this.h);
                a(baseAdUnit);
                break;
            case VIDEO_CLICK:
                t.a(PointCategory.VIDEO_CLICK, "click", baseAdUnit, Constants.FAIL, str, str2, this.h);
                b(baseAdUnit);
                break;
            default:
                t.a(PointCategory.ENDCARD, "click", baseAdUnit, Constants.FAIL, str, str2, this.h);
                c(baseAdUnit);
                break;
        }
    }

    private void a(final BaseAdUnit baseAdUnit, final Context context, String str, final String str2, final com.sigmob.sdk.base.a aVar) {
        String str3;
        switch (aVar) {
            case COMPANION:
                str3 = PointCategory.COMPANION;
                a(baseAdUnit);
                break;
            case VIDEO_CLICK:
                str3 = PointCategory.VIDEO_CLICK;
                b(baseAdUnit);
                break;
            default:
                str3 = PointCategory.ENDCARD;
                c(baseAdUnit);
                break;
        }
        final String str4 = str3;
        new y.a().a(x.IGNORE_ABOUT_SCHEME, x.OPEN_WITH_BROWSER, x.FOLLOW_DEEP_LINK).a(new y.b() { // from class: com.sigmob.sdk.videoAd.g.1
            @Override // com.sigmob.sdk.base.common.y.b
            public void a(String str5, x xVar) {
            }

            @Override // com.sigmob.sdk.base.common.y.b
            public void b(String str5, x xVar) {
            }

            @Override // com.sigmob.sdk.base.common.y.b
            public void c(String str5, x xVar) {
                String str6;
                String str7;
                BaseAdUnit baseAdUnit2;
                String str8;
                SigmobLog.d("urlHandlingFailed: " + xVar.name() + " url: " + str5);
                if (!baseAdUnit.getAd().forbiden_parse_landingpage.booleanValue()) {
                    t.a(baseAdUnit, str5);
                }
                if (x.FOLLOW_DEEP_LINK == xVar) {
                    com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_OPEN_DEEPLINK);
                    str6 = str4;
                    str7 = "click";
                    baseAdUnit2 = baseAdUnit;
                    str8 = "1";
                } else {
                    if (!TextUtils.isEmpty(g.this.g)) {
                        com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_OPEN_DEEPLINK_FAIL);
                    }
                    str6 = str4;
                    str7 = "click";
                    baseAdUnit2 = baseAdUnit;
                    str8 = Constants.FAIL;
                }
                t.a(str6, str7, baseAdUnit2, str8, str5, str2, g.this.h);
                if (g.this.e != null) {
                    g.this.e.b_();
                }
            }

            @Override // com.sigmob.sdk.base.common.y.b
            public void d(String str5, x xVar) {
                SigmobLog.d("urlHandlingFailed: " + xVar.name() + " url: " + str5);
                if (!baseAdUnit.getAd().forbiden_parse_landingpage.booleanValue()) {
                    t.a(baseAdUnit, str5);
                }
                if (x.FOLLOW_DEEP_LINK == xVar) {
                    com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_OPEN_DEEPLINK_FAIL);
                    g.this.a(context, baseAdUnit, str4, aVar, str2);
                } else {
                    t.a(str4, "click", baseAdUnit, Constants.FAIL, str5, str2, g.this.h);
                    if (g.this.e != null) {
                        g.this.e.c_();
                    }
                }
            }
        }).a(baseAdUnit.isSkipSigmobBrowser()).a(baseAdUnit).b(baseAdUnit.getAd().forbiden_parse_landingpage.booleanValue()).a().a(context, str);
    }

    public com.sigmob.sdk.base.views.b a() {
        return this.c;
    }

    public void a(int i) {
        this.h = i;
    }

    public void a(Context context, int i, BaseAdUnit baseAdUnit, String str) {
        m.a.a(context);
        com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_SHOW);
        t.a(PointCategory.ENDCARD, PointCategory.SHOW, baseAdUnit);
    }

    public void a(Context context, String str, com.sigmob.sdk.base.a aVar, BaseAdUnit baseAdUnit, String str2) {
        m.a.a(context);
        b(context, this.f, aVar, baseAdUnit, str2);
    }

    public void a(BaseAdUnit baseAdUnit) {
        com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_COMPANION_CLICK);
    }

    public void a(com.sigmob.sdk.videoAd.a aVar) {
        this.j = aVar;
    }

    public void a(a aVar) {
        this.e = aVar;
    }

    public void a(String str) {
        this.g = str;
    }

    public String b() {
        return this.g;
    }

    public void b(Context context, String str, com.sigmob.sdk.base.a aVar, BaseAdUnit baseAdUnit, String str2) {
        this.i = true;
        if (!TextUtils.isEmpty(this.g)) {
            a(baseAdUnit, context, baseAdUnit.getMacroCommon().macroProcess(this.g), str2, aVar);
            return;
        }
        if (TextUtils.isEmpty(str)) {
            SigmobLog.e("landing page is null");
            if (this.e == null) {
                return;
            }
        } else {
            if (this.d == f.DownloadType.a() || this.d == f.DownloadOpenDeepLinkType.a()) {
                a(aVar, baseAdUnit, str, str2);
                if (this.e != null) {
                    this.e.b_();
                }
                this.j.a(context, baseAdUnit, str, (com.sigmob.sdk.base.a) null, str2, false);
                return;
            }
            if (this.d == f.BrowserType.a()) {
                a(baseAdUnit, context, str, str2, aVar);
                return;
            } else {
                SigmobLog.e("not support interActionType");
                if (this.e == null) {
                    return;
                }
            }
        }
        this.e.c_();
    }

    public void b(BaseAdUnit baseAdUnit) {
        com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_VIDEO_CLICK);
    }

    public void c(BaseAdUnit baseAdUnit) {
        com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_CLICK);
    }

    public boolean c() {
        return this.i;
    }
}
