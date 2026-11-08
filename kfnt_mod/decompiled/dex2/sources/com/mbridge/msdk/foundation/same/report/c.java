package com.mbridge.msdk.foundation.same.report;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.db.p;
import com.mbridge.msdk.foundation.db.r;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.DomainCampaignEx;
import com.mbridge.msdk.foundation.entity.e;
import com.mbridge.msdk.foundation.entity.o;
import com.mbridge.msdk.foundation.tools.SameMD5;
import com.mbridge.msdk.foundation.tools.aa;
import com.mbridge.msdk.foundation.tools.n;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbbid.common.BidResponsedEx;
import com.mbridge.msdk.out.Campaign;
import com.mbridge.msdk.out.Frame;
import com.mbridge.msdk.out.MBConfiguration;
import com.mbridge.msdk.rover.RoverCampaignUnit;
import com.mbridge.msdk.rover.f;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ReportController.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    public static final String a = "c";
    private Context b;
    private int c;

    public c(Context context, int i) {
        this.c = 0;
        this.b = context;
        this.c = i;
    }

    public c(Context context) {
        this.c = 0;
        this.b = context.getApplicationContext();
    }

    public final void a(int i, String str) {
        new com.mbridge.msdk.foundation.same.report.d.a(this.b).c(0, com.mbridge.msdk.foundation.same.net.g.d.c().a, d.a("event", d.a((Campaign) null, i, "request"), this.b, str), new com.mbridge.msdk.foundation.same.report.d.b() { // from class: com.mbridge.msdk.foundation.same.report.c.1
            @Override // com.mbridge.msdk.foundation.same.report.d.b
            public final void b(String str2) {
            }

            @Override // com.mbridge.msdk.foundation.same.report.d.b
            public final void a(String str2) {
                s.b(c.a, "report success");
            }
        });
    }

    public final void a(final o oVar, final Boolean bool) {
        if (oVar != null) {
            if (oVar.c().equals("GET")) {
                new com.mbridge.msdk.foundation.same.report.d.a(this.b).a(0, oVar.b(), null, new com.mbridge.msdk.foundation.same.report.d.b() { // from class: com.mbridge.msdk.foundation.same.report.c.12
                    @Override // com.mbridge.msdk.foundation.same.report.d.b
                    public final void b(String str) {
                    }

                    @Override // com.mbridge.msdk.foundation.same.report.d.b
                    public final void a(String str) {
                        s.b(c.a, "report success");
                        p.a(i.a(c.this.b)).a(oVar.b());
                        if (!bool.booleanValue() || p.a(i.a(c.this.b)).b() <= 20) {
                            return;
                        }
                        com.mbridge.msdk.foundation.controller.b.a().c();
                    }
                });
                return;
            }
            if (oVar.c().equals("POST")) {
                com.mbridge.msdk.foundation.same.report.d.a aVar = new com.mbridge.msdk.foundation.same.report.d.a(this.b);
                if (TextUtils.isEmpty(oVar.d())) {
                    return;
                }
                aVar.c(1, oVar.b(), d.a(oVar.d(), this.b, oVar.a()), new com.mbridge.msdk.foundation.same.report.d.b() { // from class: com.mbridge.msdk.foundation.same.report.c.13
                    @Override // com.mbridge.msdk.foundation.same.report.d.b
                    public final void b(String str) {
                    }

                    @Override // com.mbridge.msdk.foundation.same.report.d.b
                    public final void a(String str) {
                        s.b(c.a, "report success");
                        p.a(i.a(c.this.b)).a(oVar.d(), oVar.b());
                        if (!bool.booleanValue() || p.a(i.a(c.this.b)).b() <= 20) {
                            return;
                        }
                        com.mbridge.msdk.foundation.controller.b.a().c();
                    }
                });
            }
        }
    }

    public final void a(final o oVar) {
        new com.mbridge.msdk.foundation.same.report.d.a(this.b).c(0, com.mbridge.msdk.foundation.same.net.g.d.c().a, d.a(this.b, oVar), new com.mbridge.msdk.foundation.same.report.d.b() { // from class: com.mbridge.msdk.foundation.same.report.c.14
            @Override // com.mbridge.msdk.foundation.same.report.d.b
            public final void b(String str) {
            }

            @Override // com.mbridge.msdk.foundation.same.report.d.b
            public final void a(String str) {
                s.b(c.a, "report success");
                try {
                    if (oVar != null) {
                        int iE = oVar.e();
                        r.a(i.a(c.this.b)).a(iE + "");
                    }
                } catch (Exception unused) {
                }
            }
        });
    }

    public final void a(String str) {
        c(str);
    }

    private void c(String str) {
        try {
            new com.mbridge.msdk.foundation.same.report.d.a(this.b).c(0, com.mbridge.msdk.foundation.same.net.g.d.c().a, d.a(str, this.b, ""), new com.mbridge.msdk.foundation.same.report.d.b() { // from class: com.mbridge.msdk.foundation.same.report.c.15
                @Override // com.mbridge.msdk.foundation.same.report.d.b
                public final void a(String str2) {
                    s.b(c.a, "reportPB success data:" + str2);
                }

                @Override // com.mbridge.msdk.foundation.same.report.d.b
                public final void b(String str2) {
                    s.b(c.a, "reportPB onFailed msg:" + str2);
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void b(String str) {
        c(str);
    }

    public final void a(String str, String str2, String str3, String str4) {
        try {
            com.mbridge.msdk.foundation.same.report.d.a aVar = new com.mbridge.msdk.foundation.same.report.d.a(this.b);
            String str5 = "click_type=" + URLEncoder.encode(str, "utf-8") + ContainerUtils.FIELD_DELIMITER + BidResponsedEx.KEY_CID + ContainerUtils.KEY_VALUE_DELIMITER + URLEncoder.encode(str2, "utf-8") + ContainerUtils.FIELD_DELIMITER + MBridgeConstans.PROPERTIES_UNIT_ID + ContainerUtils.KEY_VALUE_DELIMITER + URLEncoder.encode(str3, "utf-8") + ContainerUtils.FIELD_DELIMITER + DomainCampaignEx.LOOPBACK_KEY + ContainerUtils.KEY_VALUE_DELIMITER + URLEncoder.encode("2000027", "utf-8") + ContainerUtils.FIELD_DELIMITER + "http_url" + ContainerUtils.KEY_VALUE_DELIMITER + URLEncoder.encode(str4, "utf-8");
            if (a.a().c()) {
                a.a().a(str5);
            } else {
                aVar.c(0, com.mbridge.msdk.foundation.same.net.g.d.c().a, d.a(str5, this.b, str3), new com.mbridge.msdk.foundation.same.report.d.b() { // from class: com.mbridge.msdk.foundation.same.report.c.2
                    @Override // com.mbridge.msdk.foundation.same.report.d.b
                    public final void a(String str6) {
                        s.a("", "SSL REPORT SUCCESS");
                    }

                    @Override // com.mbridge.msdk.foundation.same.report.d.b
                    public final void b(String str6) {
                        s.a("", "SSL REPORT FAILED");
                    }
                });
            }
        } catch (Exception unused) {
            s.d(a, "ssl  error report failed");
        }
    }

    public final void a(String str, final File file) throws JSONException {
        new com.mbridge.msdk.foundation.same.report.d.a(this.b).c(0, com.mbridge.msdk.foundation.same.net.g.d.c().a, d.a(this.b, str), new com.mbridge.msdk.foundation.same.report.d.b() { // from class: com.mbridge.msdk.foundation.same.report.c.3
            @Override // com.mbridge.msdk.foundation.same.report.d.b
            public final void a(String str2) {
                s.b(c.a, "report success exception");
                if (file != null) {
                    file.delete();
                }
            }

            @Override // com.mbridge.msdk.foundation.same.report.d.b
            public final void b(String str2) {
                s.b(c.a, "report failed exception");
            }
        });
    }

    public final void a(final String str, String str2, String str3, Frame frame) {
        com.mbridge.msdk.foundation.same.report.d.a aVar = new com.mbridge.msdk.foundation.same.report.d.a(this.b);
        com.mbridge.msdk.foundation.same.net.h.d dVarA = d.a(str2, this.b, str3);
        if (frame != null) {
            dVarA.a(RoverCampaignUnit.JSON_KEY_SESSION_ID, frame.getSessionId());
            dVarA.a("parent_session_id", frame.getParentSessionId());
        }
        aVar.c(0, com.mbridge.msdk.foundation.same.net.g.d.c().a, dVarA, new com.mbridge.msdk.foundation.same.report.d.b() { // from class: com.mbridge.msdk.foundation.same.report.c.4
            @Override // com.mbridge.msdk.foundation.same.report.d.b
            public final void a(String str4) {
                s.b(c.a, "report success");
                if ("net_time_stats".equals(str)) {
                    return;
                }
                if ("click_duration".equals(str)) {
                    com.mbridge.msdk.foundation.controller.b.a().c();
                } else if ("load_duration".equals(str)) {
                    com.mbridge.msdk.foundation.controller.b.a().c();
                    com.mbridge.msdk.foundation.controller.b.a().c();
                }
            }

            @Override // com.mbridge.msdk.foundation.same.report.d.b
            public final void b(String str4) {
                s.b(c.a, "report success");
            }
        });
    }

    public final void a(String str, e eVar, String str2) {
        com.mbridge.msdk.foundation.same.report.d.a aVar = new com.mbridge.msdk.foundation.same.report.d.a(this.b);
        String strA = e.a(eVar);
        if (TextUtils.isEmpty(strA)) {
            return;
        }
        if (a.a().c()) {
            a.a().a(strA);
        } else {
            aVar.c(0, com.mbridge.msdk.foundation.same.net.g.d.c().a, d.a(strA, this.b, str2), new com.mbridge.msdk.foundation.same.report.d.b() { // from class: com.mbridge.msdk.foundation.same.report.c.5
                @Override // com.mbridge.msdk.foundation.same.report.d.b
                public final void b(String str3) {
                }

                @Override // com.mbridge.msdk.foundation.same.report.d.b
                public final void a(String str3) {
                    s.b(c.a, "report success");
                }
            });
        }
    }

    public final void a(CampaignEx campaignEx, List<com.mbridge.msdk.rover.e> list, f fVar) throws JSONException {
        com.mbridge.msdk.foundation.same.report.d.a aVar = new com.mbridge.msdk.foundation.same.report.d.a(this.b);
        String strB = b();
        aVar.c(1, com.mbridge.msdk.foundation.same.net.g.d.c().p + strB, d.a(campaignEx, list), fVar);
    }

    private String b() throws JSONException {
        StringBuffer stringBuffer = new StringBuffer("?");
        stringBuffer.append("platform=");
        stringBuffer.append(URLEncoder.encode("1"));
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("os_version=");
        stringBuffer.append(URLEncoder.encode(Build.VERSION.RELEASE));
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("package_name=");
        stringBuffer.append(URLEncoder.encode(com.mbridge.msdk.foundation.tools.o.z(this.b)));
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("app_version_name=");
        stringBuffer.append(URLEncoder.encode(com.mbridge.msdk.foundation.tools.o.u(this.b)));
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("app_version_code=");
        stringBuffer.append(URLEncoder.encode(com.mbridge.msdk.foundation.tools.o.t(this.b) + ""));
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("screen_size=");
        stringBuffer.append(URLEncoder.encode(com.mbridge.msdk.foundation.tools.o.w(this.b) + "x" + com.mbridge.msdk.foundation.tools.o.x(this.b)));
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("orientation=");
        stringBuffer.append(URLEncoder.encode(com.mbridge.msdk.foundation.tools.o.s(this.b) + ""));
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("gaid=");
        stringBuffer.append(URLEncoder.encode(com.mbridge.msdk.foundation.tools.o.A()));
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        String strEncode = URLEncoder.encode(com.mbridge.msdk.foundation.tools.o.v());
        stringBuffer.append("brand=");
        stringBuffer.append(strEncode);
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("mnc=");
        stringBuffer.append(URLEncoder.encode(com.mbridge.msdk.foundation.tools.o.e(this.b)));
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("mcc=");
        stringBuffer.append(URLEncoder.encode(com.mbridge.msdk.foundation.tools.o.d(this.b)));
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        int iC = com.mbridge.msdk.foundation.tools.o.C(this.b);
        stringBuffer.append("network_type=");
        stringBuffer.append(URLEncoder.encode(iC + ""));
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("network_str");
        stringBuffer.append(URLEncoder.encode(com.mbridge.msdk.foundation.tools.o.a(this.b, iC)));
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("language=");
        stringBuffer.append(URLEncoder.encode(com.mbridge.msdk.foundation.tools.o.r(this.b)));
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("timezone=");
        stringBuffer.append(URLEncoder.encode(com.mbridge.msdk.foundation.tools.o.x()));
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        String strEncode2 = URLEncoder.encode(com.mbridge.msdk.foundation.tools.o.w());
        stringBuffer.append("useragent=");
        stringBuffer.append(strEncode2);
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("sdk_version=");
        stringBuffer.append(URLEncoder.encode(MBConfiguration.SDK_VERSION));
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        String strEncode3 = URLEncoder.encode(com.mbridge.msdk.foundation.tools.o.j(this.b));
        stringBuffer.append("gp_version=");
        stringBuffer.append(strEncode3);
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("sign=");
        stringBuffer.append(URLEncoder.encode(SameMD5.getMD5(com.mbridge.msdk.foundation.controller.a.f().k() + com.mbridge.msdk.foundation.controller.a.f().l())));
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("app_id=");
        stringBuffer.append(URLEncoder.encode(com.mbridge.msdk.foundation.controller.a.f().k()));
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
        if (aVarB != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                if (aVarB.E() == 1) {
                    if (com.mbridge.msdk.foundation.tools.o.b(this.b) != null) {
                        jSONObject.put("imei", com.mbridge.msdk.foundation.tools.o.b(this.b));
                    }
                    if (com.mbridge.msdk.foundation.tools.o.i(this.b) != null) {
                        jSONObject.put("mac", com.mbridge.msdk.foundation.tools.o.i(this.b));
                    }
                }
                if (aVarB.F() == 1 && com.mbridge.msdk.foundation.tools.o.g(this.b) != null) {
                    jSONObject.put("android_id", com.mbridge.msdk.foundation.tools.o.g(this.b));
                }
                if (!TextUtils.isEmpty(jSONObject.toString())) {
                    String strA = n.a(jSONObject.toString());
                    if (!TextUtils.isEmpty(strA)) {
                        stringBuffer.append("dvi=");
                        stringBuffer.append(strA);
                        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                    } else {
                        stringBuffer.append("dvi=");
                        stringBuffer.append("");
                        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                    }
                } else {
                    stringBuffer.append("dvi=");
                    stringBuffer.append("");
                    stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            stringBuffer.append("dvi=");
            stringBuffer.append("");
            stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        }
        stringBuffer.append("unit_id=");
        stringBuffer.append(0);
        return stringBuffer.toString();
    }

    public final void a(String str, String str2) {
        try {
            com.mbridge.msdk.foundation.same.report.d.a aVar = new com.mbridge.msdk.foundation.same.report.d.a(this.b);
            String strK = com.mbridge.msdk.foundation.controller.a.f().k();
            if (com.mbridge.msdk.b.b.a().b(strK) == null) {
                com.mbridge.msdk.b.b.a().b();
            }
            String str3 = "key=2000000&Appid=" + strK + ContainerUtils.FIELD_DELIMITER + "reason" + ContainerUtils.KEY_VALUE_DELIMITER + str + ContainerUtils.FIELD_DELIMITER + "host" + ContainerUtils.KEY_VALUE_DELIMITER + str2;
            if (a.a().c()) {
                a.a().a(str3);
            } else {
                aVar.c(0, com.mbridge.msdk.foundation.same.net.g.d.c().a, d.a(str3, this.b, ""), new com.mbridge.msdk.foundation.same.report.d.b() { // from class: com.mbridge.msdk.foundation.same.report.c.6
                    @Override // com.mbridge.msdk.foundation.same.report.d.b
                    public final void a(String str4) {
                        s.a("", "reportSettingLoadFailed onSuccess");
                    }

                    @Override // com.mbridge.msdk.foundation.same.report.d.b
                    public final void b(String str4) {
                        s.a("", "reportSettingLoadFailed onFailed:" + str4);
                    }
                });
            }
        } catch (Throwable unused) {
            s.d(a, "reportSettingLoadFailed onFailed");
        }
    }

    public final void a() {
        try {
            if (d.a()) {
                com.mbridge.msdk.foundation.same.report.d.a aVar = new com.mbridge.msdk.foundation.same.report.d.a(this.b);
                String strK = com.mbridge.msdk.foundation.controller.a.f().k();
                com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(strK);
                if (aVarB == null) {
                    aVarB = com.mbridge.msdk.b.b.a().b();
                }
                int iM = aVarB.m();
                String str = "key=2000053&Appid=" + strK + ContainerUtils.FIELD_DELIMITER + "uptips2" + ContainerUtils.KEY_VALUE_DELIMITER + aVarB.l() + ContainerUtils.FIELD_DELIMITER + "info_status" + ContainerUtils.KEY_VALUE_DELIMITER + com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().d() + ContainerUtils.FIELD_DELIMITER + "iseu" + ContainerUtils.KEY_VALUE_DELIMITER + iM;
                String strA = com.mbridge.msdk.foundation.tools.o.A();
                if (!TextUtils.isEmpty(strA)) {
                    str = str + ContainerUtils.FIELD_DELIMITER + "gaid" + ContainerUtils.KEY_VALUE_DELIMITER + strA;
                }
                String str2 = str + ContainerUtils.FIELD_DELIMITER + "GDPR_area" + ContainerUtils.KEY_VALUE_DELIMITER + aVarB.au() + ContainerUtils.FIELD_DELIMITER + "GDPR_consent" + ContainerUtils.KEY_VALUE_DELIMITER + com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().f();
                s.a(a, "reportPrivateAuthorityStatus  data:" + str2);
                if (a.a().c()) {
                    a.a().a(str2);
                    return;
                }
                aVar.c(0, com.mbridge.msdk.foundation.same.net.g.d.c().a, d.a(str2, this.b, ""), new com.mbridge.msdk.foundation.same.report.d.b() { // from class: com.mbridge.msdk.foundation.same.report.c.7
                    @Override // com.mbridge.msdk.foundation.same.report.d.b
                    public final void a(String str3) {
                        s.a("", "PrivateAuthorityStatus onSuccess ");
                    }

                    @Override // com.mbridge.msdk.foundation.same.report.d.b
                    public final void b(String str3) {
                        s.a("", "PrivateAuthorityStatus onFailed:" + str3);
                    }
                });
                d.b();
            }
        } catch (Throwable unused) {
            s.d(a, "PrivateAuthorityStatus onFailed");
        }
    }

    public final void a(int i, int i2, String str, String str2, String str3) {
        try {
            com.mbridge.msdk.foundation.same.report.d.a aVar = new com.mbridge.msdk.foundation.same.report.d.a(this.b);
            StringBuilder sb = new StringBuilder();
            sb.append(DomainCampaignEx.LOOPBACK_KEY);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode("2000058", "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append(HiAnalyticsConstant.HaKey.BI_KEY_APPID);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(com.mbridge.msdk.foundation.controller.a.f().k(), "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("dl_service");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(aa.c + "", "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("dl_service_rs");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(aa.d + "", "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("dl_type");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(i + "", "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("dl_link_type");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(i2 + "", "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("rid");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(str, "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("rid_n");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(str2, "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append(BidResponsedEx.KEY_CID);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(str3, "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("dl_v4");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(aa.i + "", "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("dl_v4_rs");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(aa.j + "", "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("dl_pkg");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(aa.a + "", "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("dl_pkg_rs");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(aa.b + "", "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("dl_i_p");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(aa.e + "", "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("dl_i_p_rs");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(aa.g + "", "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("dl_fp");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(aa.f + "", "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("dl_fp_rs");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(aa.h + "", "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("tgt_v");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(com.mbridge.msdk.foundation.tools.o.v(this.b) + "", "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("app_v_n");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(com.mbridge.msdk.foundation.tools.o.u(this.b) + "", "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("app_v_c");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(com.mbridge.msdk.foundation.tools.o.t(this.b) + "", "utf-8"));
            if (a.a().c()) {
                a.a().a(sb.toString());
            } else {
                aVar.c(0, com.mbridge.msdk.foundation.same.net.g.d.c().a, d.a(sb.toString(), this.b, ""), new com.mbridge.msdk.foundation.same.report.d.b() { // from class: com.mbridge.msdk.foundation.same.report.c.8
                    @Override // com.mbridge.msdk.foundation.same.report.d.b
                    public final void a(String str4) {
                        s.a("", "reportDownloadMethod REPORT SUCCESS");
                    }

                    @Override // com.mbridge.msdk.foundation.same.report.d.b
                    public final void b(String str4) {
                        s.a("", "reportDownloadMethod REPORT FAILED");
                    }
                });
            }
        } catch (Exception e) {
            if (MBridgeConstans.DEBUG) {
                e.printStackTrace();
            }
        }
    }

    public final void a(String str, String str2, String str3, String str4, String str5, boolean z) {
        try {
            if (TextUtils.isEmpty(str5)) {
                return;
            }
            com.mbridge.msdk.foundation.same.report.d.a aVar = new com.mbridge.msdk.foundation.same.report.d.a(this.b);
            StringBuilder sb = new StringBuilder();
            if (z) {
                sb.append("hb=");
                sb.append(1);
                sb.append(ContainerUtils.FIELD_DELIMITER);
            }
            sb.append(DomainCampaignEx.LOOPBACK_KEY);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode("2000066", "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("rid");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(str, "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("rid_n");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(str2, "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append(BidResponsedEx.KEY_CID);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(str3, "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append(MBridgeConstans.PROPERTIES_UNIT_ID);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(str4, "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("err_method");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(str5);
            if (a.a().c()) {
                a.a().a(sb.toString());
            } else {
                aVar.c(0, com.mbridge.msdk.foundation.same.net.g.d.c().a, d.a(sb.toString(), this.b, str4), new com.mbridge.msdk.foundation.same.report.d.b() { // from class: com.mbridge.msdk.foundation.same.report.c.9
                    @Override // com.mbridge.msdk.foundation.same.report.d.b
                    public final void a(String str6) {
                        s.a("", "MraidUnSupportMethod REPORT SUCCESS");
                    }

                    @Override // com.mbridge.msdk.foundation.same.report.d.b
                    public final void b(String str6) {
                        s.a("", "MraidUnSupportMethod REPORT FAILED");
                    }
                });
            }
        } catch (Exception e) {
            if (MBridgeConstans.DEBUG) {
                e.printStackTrace();
            }
        }
    }

    public final void b(String str, String str2, String str3, String str4, String str5, boolean z) {
        try {
            com.mbridge.msdk.foundation.same.report.d.a aVar = new com.mbridge.msdk.foundation.same.report.d.a(this.b);
            StringBuilder sb = new StringBuilder();
            if (z) {
                sb.append("hb=");
                sb.append(1);
                sb.append(ContainerUtils.FIELD_DELIMITER);
            }
            sb.append(DomainCampaignEx.LOOPBACK_KEY);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode("2000065", "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("rid");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(str, "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("rid_n");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(str2, "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append(BidResponsedEx.KEY_CID);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(str3, "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append(MBridgeConstans.PROPERTIES_UNIT_ID);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(str4, "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append(CampaignEx.JSON_KEY_CLICK_URL);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(str5, "utf-8"));
            if (a.a().c()) {
                a.a().a(sb.toString());
            } else {
                aVar.c(0, com.mbridge.msdk.foundation.same.net.g.d.c().a, d.a(sb.toString(), this.b, str4), new com.mbridge.msdk.foundation.same.report.d.b() { // from class: com.mbridge.msdk.foundation.same.report.c.10
                    @Override // com.mbridge.msdk.foundation.same.report.d.b
                    public final void a(String str6) {
                        s.a("", "MraidClic REPORT SUCCESS");
                    }

                    @Override // com.mbridge.msdk.foundation.same.report.d.b
                    public final void b(String str6) {
                        s.a("", "MraidClic REPORT FAILED");
                    }
                });
            }
        } catch (Exception e) {
            if (MBridgeConstans.DEBUG) {
                e.printStackTrace();
            }
        }
    }

    public final void a(String str, String str2, String str3, String str4, String str5) {
        try {
            com.mbridge.msdk.foundation.same.report.d.a aVar = new com.mbridge.msdk.foundation.same.report.d.a(this.b);
            StringBuilder sb = new StringBuilder();
            int iC = com.mbridge.msdk.foundation.tools.o.C(this.b);
            sb.append(DomainCampaignEx.LOOPBACK_KEY);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode("2000071", "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("rid");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(str, "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("rid_n");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(str2, "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append(BidResponsedEx.KEY_CID);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(str3, "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append(MBridgeConstans.PROPERTIES_UNIT_ID);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(str4, "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("reason");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(str5, "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("network_type");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(iC + "", "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("result");
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(PushConstants.PUSH_TYPE_NOTIFY, "utf-8"));
            if (a.a().c()) {
                a.a().a(sb.toString());
            } else {
                aVar.c(0, com.mbridge.msdk.foundation.same.net.g.d.c().a, d.a(sb.toString(), this.b, str4), new com.mbridge.msdk.foundation.same.report.d.b() { // from class: com.mbridge.msdk.foundation.same.report.c.11
                    @Override // com.mbridge.msdk.foundation.same.report.d.b
                    public final void a(String str6) {
                        s.a("", "OMSDK REPORT SUCCESS");
                    }

                    @Override // com.mbridge.msdk.foundation.same.report.d.b
                    public final void b(String str6) {
                        s.a("", "OMSDK REPORT FAILED");
                    }
                });
            }
        } catch (Exception e) {
            if (MBridgeConstans.DEBUG) {
                e.printStackTrace();
            }
        }
    }
}
