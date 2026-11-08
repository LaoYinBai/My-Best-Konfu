package com.mbridge.msdk.foundation.same.report;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.heytap.mcssdk.mode.CommandMessage;
import com.heytap.mcssdk.mode.Message;
import com.huawei.hms.framework.common.ContainerUtils;
import com.kwad.sdk.collector.AppStatusRules;
import com.liulishuo.filedownloader.services.FileDownloadBroadcastHandler;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.DomainCampaignEx;
import com.mbridge.msdk.foundation.same.net.Aa;
import com.mbridge.msdk.foundation.tools.n;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.y;
import com.mbridge.msdk.mbbid.common.BidResponsedEx;
import com.mbridge.msdk.out.Campaign;
import com.mbridge.msdk.out.MBConfiguration;
import com.mbridge.msdk.rover.e;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ReportUtil.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d {
    public static final String a = "d";

    private static String b(Context context, String str) throws JSONException {
        if (context == null) {
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("pf", "1");
            jSONObject.put("ov", o.y());
            jSONObject.put("pn", o.z(context));
            jSONObject.put("vn", o.u(context));
            jSONObject.put("vc", o.t(context));
            jSONObject.put("ot", o.s(context));
            jSONObject.put("dm", o.t());
            jSONObject.put("bd", o.v());
            jSONObject.put("gaid", o.A());
            jSONObject.put("mnc", o.e(context));
            jSONObject.put("mcc", o.d(context));
            int iC = o.C(context);
            jSONObject.put("nt", iC);
            jSONObject.put("nts", o.a(context, iC));
            jSONObject.put(NotifyType.LIGHTS, o.r(context));
            jSONObject.put("tz", o.x());
            jSONObject.put("ua", o.w());
            jSONObject.put("app_id", com.mbridge.msdk.foundation.controller.a.f().k());
            jSONObject.put(MBridgeConstans.PROPERTIES_UNIT_ID, str);
            jSONObject.put("sv", MBConfiguration.SDK_VERSION);
            jSONObject.put("gpv", o.j(context));
            jSONObject.put("ss", o.w(context) + "x" + o.x(context));
            com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
            if (aVarB != null) {
                JSONObject jSONObject2 = new JSONObject();
                try {
                    if (aVarB.E() == 1) {
                        if (o.b(context) != null) {
                            jSONObject2.put("imei", o.b(context));
                        }
                        if (o.i(context) != null) {
                            jSONObject2.put("mac", o.i(context));
                        }
                    }
                    if (aVarB.F() == 1 && o.g(context) != null) {
                        jSONObject2.put("android_id", o.g(context));
                    }
                    try {
                        if (!TextUtils.isEmpty(o.D())) {
                            jSONObject2.put("manufacturer", o.D());
                        }
                        if (!TextUtils.isEmpty(o.b())) {
                            jSONObject2.put("cpu2", o.b());
                        }
                        if (!TextUtils.isEmpty(o.d())) {
                            jSONObject2.put(CommandMessage.TYPE_TAGS, o.d());
                        }
                        if (!TextUtils.isEmpty(o.e())) {
                            jSONObject2.put("user", o.e());
                        }
                        if (!TextUtils.isEmpty(o.f())) {
                            jSONObject2.put("radio", o.f());
                        }
                        if (!TextUtils.isEmpty(o.g())) {
                            jSONObject2.put("bootloader", o.g());
                        }
                        if (!TextUtils.isEmpty(o.h())) {
                            jSONObject2.put("hardware", o.h());
                        }
                        if (!TextUtils.isEmpty(o.i())) {
                            jSONObject2.put("host", o.i());
                        }
                        if (!TextUtils.isEmpty(o.j())) {
                            jSONObject2.put("codename", o.j());
                        }
                        if (!TextUtils.isEmpty(o.k())) {
                            jSONObject2.put("incremental", o.k());
                        }
                        if (!TextUtils.isEmpty(o.l())) {
                            jSONObject2.put("serial", o.l());
                        }
                        if (!TextUtils.isEmpty(o.m())) {
                            jSONObject2.put("display", o.m());
                        }
                        if (!TextUtils.isEmpty(o.n())) {
                            jSONObject2.put("board", o.n());
                        }
                        if (!TextUtils.isEmpty(o.o())) {
                            jSONObject2.put(Message.TYPE, o.o());
                        }
                        if (!TextUtils.isEmpty(o.c())) {
                            jSONObject2.put("support", o.c());
                        }
                        if (!TextUtils.isEmpty(o.p())) {
                            jSONObject2.put("release", o.p());
                        }
                        if (o.q() != -1) {
                            jSONObject2.put("sdkint", o.q());
                        }
                        if (!TextUtils.isEmpty(o.l(context))) {
                            jSONObject2.put("battery", o.l(context));
                        }
                        if (o.k(context) != -1) {
                            jSONObject2.put("batterystatus", o.k(context));
                        }
                        if (o.r() != -1) {
                            jSONObject2.put("baseos", o.r());
                        }
                        if (!TextUtils.isEmpty(o.E(context))) {
                            jSONObject2.put("is24H", o.E(context));
                        }
                        if (o.n(context) != -1) {
                            jSONObject2.put("sensor", o.n(context));
                        }
                        if (!TextUtils.isEmpty(o.o(context))) {
                            jSONObject2.put("ime", o.o(context));
                        }
                        if (o.m(context) != -1) {
                            jSONObject2.put("phonetype", o.m(context));
                        }
                        if (!TextUtils.isEmpty(o.E())) {
                            jSONObject2.put("totalram", o.E());
                        }
                        if (!TextUtils.isEmpty(o.F(context))) {
                            jSONObject2.put("totalmemory", o.F(context));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    if (!TextUtils.isEmpty(jSONObject2.toString())) {
                        String strA = n.a(jSONObject2.toString());
                        if (!TextUtils.isEmpty(strA)) {
                            jSONObject.put("dvi", URLEncoder.encode(strA, "utf-8"));
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return jSONObject.toString();
        } catch (Exception e3) {
            e3.printStackTrace();
            return "";
        }
    }

    public static String a(Campaign campaign, int i, String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("category", "adtrack");
            jSONObject.put("action", str);
            jSONObject.put("label", i);
            if (campaign != null) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("id", campaign.getId());
                jSONObject2.put("title", campaign.getAppName());
                jSONObject.put("value", jSONObject2);
            } else {
                jSONObject.put("value", "");
            }
        } catch (Exception unused) {
            s.d(a, "ad track data failed !");
        }
        return jSONObject.toString();
    }

    public static com.mbridge.msdk.foundation.same.net.h.d a(String str, String str2, Context context, String str3) {
        String string;
        if (context != null) {
            context = context.getApplicationContext();
        }
        com.mbridge.msdk.foundation.same.net.h.d dVar = new com.mbridge.msdk.foundation.same.net.h.d();
        dVar.a("m_device_info", b(context, str3));
        dVar.a("m_action", str);
        try {
        } catch (Throwable th) {
            th = th;
        }
        if (TextUtils.isEmpty(str2)) {
            string = str2;
        } else {
            s.a(a, "8.5.0 add channel ,before value : " + str2);
            String strA = Aa.a();
            if (strA == null) {
                strA = "";
            }
            JSONObject jSONObject = new JSONObject(str2);
            jSONObject.put("channel", strA);
            string = jSONObject.toString();
            try {
                s.a(a, "8.5.0 add channel ,update value : " + string);
            } catch (Throwable th2) {
                str2 = string;
                th = th2;
                th.printStackTrace();
                string = str2;
                dVar.a("m_data", string);
                dVar.a("m_sdk", "msdk");
                return dVar;
            }
        }
        dVar.a("m_data", string);
        dVar.a("m_sdk", "msdk");
        return dVar;
    }

    public static com.mbridge.msdk.foundation.same.net.h.d a(Context context) throws JSONException {
        com.mbridge.msdk.foundation.same.net.h.d dVar = new com.mbridge.msdk.foundation.same.net.h.d();
        try {
            dVar.a("platform", "1");
            String strZ = o.z(context);
            if (!TextUtils.isEmpty(strZ)) {
                dVar.a(CampaignEx.JSON_KEY_PACKAGE_NAME, URLEncoder.encode(strZ));
            }
            if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
                dVar.a("os_version", Build.VERSION.RELEASE);
                dVar.a("brand", URLEncoder.encode(o.v()));
                dVar.a(FileDownloadBroadcastHandler.KEY_MODEL, URLEncoder.encode(o.t()));
                dVar.a("gaid", o.A());
                dVar.a("mnc", o.e(context));
                dVar.a("mcc", o.d(context));
                int iC = o.C(context);
                dVar.a("network_type", iC + "");
                dVar.a("network_str", o.a(context, iC));
                dVar.a("language", URLEncoder.encode(o.r(context)));
                dVar.a("timezone", URLEncoder.encode(o.x()));
                dVar.a("ua", URLEncoder.encode(o.w()));
                dVar.a("gp_version", URLEncoder.encode(o.j(context)));
            }
            dVar.a("sdk_version", MBConfiguration.SDK_VERSION);
            dVar.a("app_version_name", URLEncoder.encode(o.u(context)));
            dVar.a("orientation", URLEncoder.encode(o.s(context) + ""));
            try {
                if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
                    Class.forName("com.google.android.gms.common.GooglePlayServicesUtil");
                    dVar.a("gpsv", GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE + "");
                }
            } catch (Exception unused) {
                s.d(a, "can't find com.google.android.gms.common.GooglePlayServicesUtil class");
            } catch (Throwable th) {
                th.printStackTrace();
            }
            dVar.a("screen_size", o.w(context) + "x" + o.x(context));
            com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
            if (aVarB == null) {
                aVarB = com.mbridge.msdk.b.b.a().b();
            }
            if (aVarB != null) {
                JSONObject jSONObject = new JSONObject();
                try {
                    if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a("authority_imei_mac") && aVarB.E() == 1) {
                        if (o.b(context) != null) {
                            jSONObject.put("imei", o.b(context));
                        }
                        if (o.i(context) != null) {
                            jSONObject.put("mac", o.i(context));
                        }
                    }
                    if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a("authority_android_id") && aVarB.F() == 1 && o.g(context) != null) {
                        jSONObject.put("android_id", o.g(context));
                    }
                    try {
                        try {
                            if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
                                if (!TextUtils.isEmpty(o.D())) {
                                    jSONObject.put("manufacturer", o.D());
                                }
                                if (!TextUtils.isEmpty(o.b())) {
                                    jSONObject.put("cpu2", o.b());
                                }
                                if (!TextUtils.isEmpty(o.d())) {
                                    jSONObject.put(CommandMessage.TYPE_TAGS, o.d());
                                }
                                if (!TextUtils.isEmpty(o.e())) {
                                    jSONObject.put("user", o.e());
                                }
                                if (!TextUtils.isEmpty(o.f())) {
                                    jSONObject.put("radio", o.f());
                                }
                                if (!TextUtils.isEmpty(o.g())) {
                                    jSONObject.put("bootloader", o.g());
                                }
                                if (!TextUtils.isEmpty(o.h())) {
                                    jSONObject.put("hardware", o.h());
                                }
                                if (!TextUtils.isEmpty(o.i())) {
                                    jSONObject.put("host", o.i());
                                }
                                if (!TextUtils.isEmpty(o.j())) {
                                    jSONObject.put("codename", o.j());
                                }
                                if (!TextUtils.isEmpty(o.k())) {
                                    jSONObject.put("incremental", o.k());
                                }
                                if (!TextUtils.isEmpty(o.l())) {
                                    jSONObject.put("serial", o.l());
                                }
                                if (!TextUtils.isEmpty(o.m())) {
                                    jSONObject.put("display", o.m());
                                }
                                if (!TextUtils.isEmpty(o.n())) {
                                    jSONObject.put("board", o.n());
                                }
                                if (!TextUtils.isEmpty(o.o())) {
                                    jSONObject.put(Message.TYPE, o.o());
                                }
                                if (!TextUtils.isEmpty(o.c())) {
                                    jSONObject.put("support", o.c());
                                }
                                if (!TextUtils.isEmpty(o.p())) {
                                    jSONObject.put("release", o.p());
                                }
                                if (o.q() != -1) {
                                    jSONObject.put("sdkint", o.q());
                                }
                                if (!TextUtils.isEmpty(o.l(context))) {
                                    jSONObject.put("battery", o.l(context));
                                }
                                if (o.k(context) != -1) {
                                    jSONObject.put("batterystatus", o.k(context));
                                }
                                if (o.r() != -1) {
                                    jSONObject.put("baseos", o.r());
                                }
                                if (!TextUtils.isEmpty(o.E(context))) {
                                    jSONObject.put("is24H", o.E(context));
                                }
                                if (o.n(context) != -1) {
                                    jSONObject.put("sensor", o.n(context));
                                }
                                if (!TextUtils.isEmpty(o.o(context))) {
                                    jSONObject.put("ime", o.o(context));
                                }
                                if (o.m(context) != -1) {
                                    jSONObject.put("phonetype", o.m(context));
                                }
                                if (!TextUtils.isEmpty(o.E())) {
                                    jSONObject.put("totalram", o.E());
                                }
                                if (!TextUtils.isEmpty(o.F(context))) {
                                    jSONObject.put("totalmemory", o.F(context));
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    if (!TextUtils.isEmpty(jSONObject.toString())) {
                        String strA = n.a(jSONObject.toString());
                        if (!TextUtils.isEmpty(strA)) {
                            dVar.a("dvi", strA);
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } catch (Exception e3) {
            if (MBridgeConstans.DEBUG) {
                e3.printStackTrace();
            }
        }
        return dVar;
    }

    public static com.mbridge.msdk.foundation.same.net.h.d a(Context context, com.mbridge.msdk.foundation.entity.o oVar) {
        try {
            com.mbridge.msdk.foundation.same.net.h.d dVarA = a(context);
            try {
                dVarA.a("app_id", com.mbridge.msdk.foundation.controller.a.f().k() + "");
                dVarA.a("data", URLEncoder.encode(oVar.d()));
                return dVarA;
            } catch (Exception unused) {
                return dVarA;
            }
        } catch (Exception unused2) {
            return null;
        }
    }

    public static com.mbridge.msdk.foundation.same.net.h.d a(Context context, String str) throws JSONException {
        com.mbridge.msdk.foundation.same.net.h.d dVarA = a(context);
        dVarA.a("app_id", com.mbridge.msdk.foundation.controller.a.f().k() + "");
        if (!TextUtils.isEmpty(str)) {
            try {
                dVarA.a("data", URLEncoder.encode(str, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return dVarA;
    }

    public static com.mbridge.msdk.foundation.same.net.h.d a(String str, Context context, String str2) {
        com.mbridge.msdk.foundation.same.net.h.d dVarA = a(context);
        dVarA.a("app_id", com.mbridge.msdk.foundation.controller.a.f().k() + "");
        dVarA.a(MBridgeConstans.PROPERTIES_UNIT_ID, str2);
        if (!TextUtils.isEmpty(str)) {
            try {
                dVarA.a("data", URLEncoder.encode(str, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        dVarA.a("m_sdk", "msdk");
        return dVarA;
    }

    public static com.mbridge.msdk.foundation.same.net.h.d a(String str, Context context) throws JSONException {
        com.mbridge.msdk.foundation.same.net.h.d dVarA = a(context);
        dVarA.a("app_id", com.mbridge.msdk.foundation.controller.a.f().k() + "");
        if (!TextUtils.isEmpty(str)) {
            try {
                dVarA.a("data", URLEncoder.encode(str, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        dVarA.a("m_sdk", "msdk");
        return dVarA;
    }

    public static String a(String str, Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("key=" + str);
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                sb.append(ContainerUtils.FIELD_DELIMITER + entry.getKey() + ContainerUtils.KEY_VALUE_DELIMITER + entry.getValue());
            }
        }
        sb.append("\n");
        return sb.toString();
    }

    public static com.mbridge.msdk.foundation.same.net.h.d a(CampaignEx campaignEx, List<e> list) throws JSONException {
        com.mbridge.msdk.foundation.same.net.h.d dVar = new com.mbridge.msdk.foundation.same.net.h.d();
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        if (campaignEx != null) {
            try {
                jSONObject.put(BidResponsedEx.KEY_CID, campaignEx.getId());
                jSONObject.put(DomainCampaignEx.ROVER_KEY_MARK, campaignEx.getRoverMark());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (list != null && list.size() >= 0) {
            JSONArray jSONArray2 = new JSONArray();
            for (e eVar : list) {
                if (eVar != null) {
                    jSONArray2.put(eVar.a());
                }
            }
            jSONObject.put("urls", jSONArray2);
        }
        jSONArray.put(jSONObject);
        dVar.a("data", jSONArray.toString());
        return dVar;
    }

    public static boolean a() {
        try {
            return System.currentTimeMillis() - AppStatusRules.DEFAULT_START_TIME > ((Long) y.b(com.mbridge.msdk.foundation.controller.a.f().j(), "privateAuthorityTimesTamp", 0L)).longValue();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static void b() {
        try {
            y.a(com.mbridge.msdk.foundation.controller.a.f().j(), "privateAuthorityTimesTamp", Long.valueOf(System.currentTimeMillis()));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(Context context, CampaignEx campaignEx, int i, int i2) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("key=2000056&");
            if (campaignEx != null) {
                stringBuffer.append("cid=" + campaignEx.getId() + ContainerUtils.FIELD_DELIMITER);
            }
            com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
            if (aVarB == null) {
                aVarB = com.mbridge.msdk.b.b.a().b();
            }
            stringBuffer.append("unit_id=" + aVarB.g() + ContainerUtils.FIELD_DELIMITER);
            String strA = o.A();
            if (!TextUtils.isEmpty(strA)) {
                stringBuffer.append("gaid=" + strA + ContainerUtils.FIELD_DELIMITER);
            }
            stringBuffer.append("action_type=" + i + ContainerUtils.FIELD_DELIMITER);
            stringBuffer.append("jm_a=" + com.mbridge.msdk.c.b.a(context).c() + ContainerUtils.FIELD_DELIMITER);
            stringBuffer.append("jm_n=" + com.mbridge.msdk.c.b.a(context).a() + ContainerUtils.FIELD_DELIMITER);
            if (campaignEx != null) {
                stringBuffer.append("rid=");
                stringBuffer.append(campaignEx.getRequestId());
                stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid_n=" + campaignEx.getRequestIdNotice() + ContainerUtils.FIELD_DELIMITER);
            }
            stringBuffer.append("result_type=" + i2);
            if (a.a().c()) {
                a.a().a(stringBuffer.toString());
            } else {
                new c(context).b(stringBuffer.toString());
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(Context context, String str, String str2, boolean z) {
        if (context != null) {
            try {
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("key=2000047&");
                    stringBuffer.append("network_type=" + o.C(context) + ContainerUtils.FIELD_DELIMITER);
                    stringBuffer.append("unit_id=" + str2 + ContainerUtils.FIELD_DELIMITER);
                    stringBuffer.append("hb=");
                    stringBuffer.append(z ? 1 : 0);
                    stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                    stringBuffer.append("reason=" + str);
                    if (a.a().c()) {
                        a.a().a(stringBuffer.toString());
                    } else {
                        String string = stringBuffer.toString();
                        if (context != null && !TextUtils.isEmpty(string)) {
                            try {
                                new com.mbridge.msdk.foundation.same.report.d.a(context).c(0, com.mbridge.msdk.foundation.same.net.g.d.c().a, a(string, context), new com.mbridge.msdk.foundation.same.report.d.b() { // from class: com.mbridge.msdk.foundation.same.report.d.3
                                    @Override // com.mbridge.msdk.foundation.same.report.d.b
                                    public final void a(String str3) {
                                        s.d(d.a, str3);
                                    }

                                    @Override // com.mbridge.msdk.foundation.same.report.d.b
                                    public final void b(String str3) {
                                        s.d(d.a, str3);
                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                                s.d(a, e.getMessage());
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static void a(Context context, List<CampaignEx> list, String str, boolean z) {
        if (context == null || list == null) {
            return;
        }
        try {
            if (list.size() <= 0 || TextUtils.isEmpty(str)) {
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("key=2000048&");
            stringBuffer.append("network_type=" + o.C(context) + ContainerUtils.FIELD_DELIMITER);
            stringBuffer.append("unit_id=" + str + ContainerUtils.FIELD_DELIMITER);
            CampaignEx campaignEx = list.get(0);
            stringBuffer.append("cid=" + campaignEx.getId() + ContainerUtils.FIELD_DELIMITER);
            if (z) {
                stringBuffer.append("hb=");
                stringBuffer.append(1);
                stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
            }
            String requestId = campaignEx.getRequestId();
            if (!TextUtils.isEmpty(requestId)) {
                stringBuffer.append("rid=");
                stringBuffer.append(requestId);
                stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
            }
            String requestIdNotice = campaignEx.getRequestIdNotice();
            if (!TextUtils.isEmpty(requestIdNotice)) {
                stringBuffer.append("rid_n=");
                stringBuffer.append(requestIdNotice);
                stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
            }
            StringBuilder sb = new StringBuilder("rtins_type=");
            for (int i = 0; i < list.size(); i++) {
                CampaignEx campaignEx2 = list.get(i);
                if (i < list.size() - 1) {
                    sb.append(campaignEx2.getRtinsType());
                    sb.append(",");
                } else {
                    sb.append(campaignEx2.getRtinsType());
                }
            }
            stringBuffer.append((CharSequence) sb);
            if (a.a().c()) {
                a.a().a(stringBuffer.toString());
            } else {
                a(context, stringBuffer.toString(), str);
            }
        } catch (Throwable th) {
            s.d(a, th.getMessage());
        }
    }

    public static void a(Context context, CampaignEx campaignEx, String str, String str2) {
        if (context == null || campaignEx == null) {
            return;
        }
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("key=2000054&");
            stringBuffer.append("network_type=" + o.C(context) + ContainerUtils.FIELD_DELIMITER);
            stringBuffer.append("unit_id=" + str + ContainerUtils.FIELD_DELIMITER);
            stringBuffer.append("cid=" + campaignEx.getId() + ContainerUtils.FIELD_DELIMITER);
            stringBuffer.append("reason=&");
            stringBuffer.append("result=2&");
            if (!TextUtils.isEmpty(str2)) {
                stringBuffer.append(str2);
            }
            if (campaignEx != null && campaignEx.getAdType() == 287) {
                stringBuffer.append("creative=" + URLEncoder.encode(campaignEx.getendcard_url()) + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("ad_type=3&");
            } else if (campaignEx != null && campaignEx.getAdType() == 94) {
                stringBuffer.append("creative=" + URLEncoder.encode(campaignEx.getendcard_url()) + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("ad_type=1&");
            } else if (campaignEx != null && campaignEx.getAdType() == 296) {
                stringBuffer.append("ad_type=5&");
                stringBuffer.append("creative=" + campaignEx.getCreativeId() + ContainerUtils.FIELD_DELIMITER);
            } else if (campaignEx != null && campaignEx.getAdType() == 297) {
                stringBuffer.append("ad_type=6&");
                stringBuffer.append("creative=" + campaignEx.getCreativeId() + ContainerUtils.FIELD_DELIMITER);
            } else if (campaignEx != null && campaignEx.getAdType() == 298) {
                stringBuffer.append("ad_type=7&");
                stringBuffer.append("creative=" + campaignEx.getCreativeId() + ContainerUtils.FIELD_DELIMITER);
            } else {
                stringBuffer.append("creative=" + URLEncoder.encode(campaignEx.getendcard_url()) + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("ad_type=1&");
            }
            stringBuffer.append("devid=" + o.A() + ContainerUtils.FIELD_DELIMITER);
            if (campaignEx != null) {
                if (campaignEx.isBidCampaign()) {
                    stringBuffer.append("hb=1&");
                }
                stringBuffer.append("rid=");
                stringBuffer.append(campaignEx.getRequestId());
                stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid_n=" + campaignEx.getRequestIdNotice());
            }
            if (a.a().c()) {
                a.a().a(stringBuffer.toString());
            } else {
                a(context, stringBuffer.toString(), str);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void b(Context context, CampaignEx campaignEx, String str, String str2) {
        if (context != null) {
            try {
                if ((!TextUtils.isEmpty(str)) && (!TextUtils.isEmpty(str2))) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("key=2000054&");
                    stringBuffer.append("network_type=" + o.C(context) + ContainerUtils.FIELD_DELIMITER);
                    stringBuffer.append("unit_id=" + str + ContainerUtils.FIELD_DELIMITER);
                    stringBuffer.append("reason=" + str2 + ContainerUtils.FIELD_DELIMITER);
                    stringBuffer.append("result=1&");
                    stringBuffer.append("devid=" + o.A() + ContainerUtils.FIELD_DELIMITER);
                    if (campaignEx != null) {
                        stringBuffer.append("cid=" + campaignEx.getId() + ContainerUtils.FIELD_DELIMITER);
                        if (campaignEx.getAdType() == 287) {
                            stringBuffer.append("creative=" + URLEncoder.encode(campaignEx.getendcard_url()) + ContainerUtils.FIELD_DELIMITER);
                            stringBuffer.append("ad_type=3&");
                        } else if (campaignEx.getAdType() == 94) {
                            stringBuffer.append("creative=" + URLEncoder.encode(campaignEx.getendcard_url()) + ContainerUtils.FIELD_DELIMITER);
                            stringBuffer.append("ad_type=1&");
                        } else if (campaignEx.getAdType() == 296) {
                            stringBuffer.append("ad_type=5&");
                            stringBuffer.append("creative=" + campaignEx.getCreativeId() + ContainerUtils.FIELD_DELIMITER);
                        } else if (campaignEx.getAdType() == 297) {
                            stringBuffer.append("ad_type=6&");
                            stringBuffer.append("ad_html=" + campaignEx.getAdHtml() + ContainerUtils.FIELD_DELIMITER);
                            stringBuffer.append("ad_tpl_url=" + URLEncoder.encode(campaignEx.getAdZip()) + ContainerUtils.FIELD_DELIMITER);
                        } else if (campaignEx.getAdType() == 298) {
                            stringBuffer.append("ad_type=7&");
                            stringBuffer.append("ad_html=" + campaignEx.getAdHtml() + ContainerUtils.FIELD_DELIMITER);
                            stringBuffer.append("ad_tpl_url=" + URLEncoder.encode(campaignEx.getAdZip()) + ContainerUtils.FIELD_DELIMITER);
                        } else {
                            stringBuffer.append("creative=" + URLEncoder.encode(campaignEx.getendcard_url()) + ContainerUtils.FIELD_DELIMITER);
                            stringBuffer.append("ad_type=1&");
                        }
                        stringBuffer.append("rid=");
                        stringBuffer.append(campaignEx.getRequestId());
                        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                        stringBuffer.append("rid_n=" + campaignEx.getRequestIdNotice());
                    }
                    if (a.a().c()) {
                        a.a().a(stringBuffer.toString());
                    } else {
                        a(context, stringBuffer.toString(), str);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static void a(Context context, CampaignEx campaignEx, String str, String str2, int i, String str3, int i2) {
        if (context != null) {
            try {
                if (TextUtils.isEmpty(str2)) {
                    return;
                }
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("key=2000073&");
                stringBuffer.append("network_type=" + o.C(context) + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("unit_id=" + str2 + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("reason=" + str3 + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("result=" + i + ContainerUtils.FIELD_DELIMITER);
                if (campaignEx != null) {
                    stringBuffer.append("cid=" + campaignEx.getId() + ContainerUtils.FIELD_DELIMITER);
                    stringBuffer.append("rid=");
                    stringBuffer.append(campaignEx.getRequestId());
                    stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                    stringBuffer.append("rid_n=" + campaignEx.getRequestIdNotice() + ContainerUtils.FIELD_DELIMITER);
                }
                stringBuffer.append("d_t=" + i2 + ContainerUtils.FIELD_DELIMITER);
                StringBuilder sb = new StringBuilder();
                sb.append("url=");
                sb.append(URLEncoder.encode(str, "utf-8"));
                stringBuffer.append(sb.toString());
                if (a.a().c()) {
                    a.a().a(stringBuffer.toString());
                } else {
                    a(context, stringBuffer.toString(), str2);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static void a(Context context, CampaignEx campaignEx, String str, String str2, int i, int i2) {
        if (context == null || campaignEx == null) {
            return;
        }
        try {
            if (!TextUtils.isEmpty(str2)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("key=2000074&");
                stringBuffer.append("network_type=" + o.C(context) + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("unit_id=" + str2 + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("cid=" + campaignEx.getId() + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("type=" + i + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid=");
                stringBuffer.append(campaignEx.getRequestId());
                stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid_n=" + campaignEx.getRequestIdNotice() + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("d_t=" + i2 + ContainerUtils.FIELD_DELIMITER);
                StringBuilder sb = new StringBuilder();
                sb.append("url=");
                sb.append(URLEncoder.encode(str, "utf-8"));
                stringBuffer.append(sb.toString());
                if (a.a().c()) {
                    a.a().a(stringBuffer.toString());
                } else {
                    a(context, stringBuffer.toString(), str2);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(Context context, CampaignEx campaignEx, String str, String str2, int i, int i2, int i3) {
        if (context == null || campaignEx == null) {
            return;
        }
        try {
            if (!TextUtils.isEmpty(str2)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("key=2000075&");
                stringBuffer.append("network_type=" + o.C(context) + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("unit_id=" + str2 + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("cid=" + campaignEx.getId() + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("type=" + i + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid=");
                stringBuffer.append(campaignEx.getRequestId());
                stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid_n=" + campaignEx.getRequestIdNotice() + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("statue=" + i2 + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("d_t=" + i3 + ContainerUtils.FIELD_DELIMITER);
                StringBuilder sb = new StringBuilder();
                sb.append("url=");
                sb.append(URLEncoder.encode(str, "utf-8"));
                stringBuffer.append(sb.toString());
                if (a.a().c()) {
                    a.a().a(stringBuffer.toString());
                } else {
                    a(context, stringBuffer.toString(), str2);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(Context context, String str, String str2, int i, String str3) {
        if (context != null) {
            try {
                if (!TextUtils.isEmpty(str2)) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("key=2000076&");
                    stringBuffer.append("network_type=" + o.C(context) + ContainerUtils.FIELD_DELIMITER);
                    stringBuffer.append("unit_id=" + str2 + ContainerUtils.FIELD_DELIMITER);
                    stringBuffer.append("reason=" + str3 + ContainerUtils.FIELD_DELIMITER);
                    stringBuffer.append("result=" + i + ContainerUtils.FIELD_DELIMITER);
                    StringBuilder sb = new StringBuilder();
                    sb.append("url=");
                    sb.append(URLEncoder.encode(str, "utf-8"));
                    stringBuffer.append(sb.toString());
                    if (a.a().c()) {
                        a.a().a(stringBuffer.toString());
                    } else {
                        a(context, stringBuffer.toString(), str2);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static void a(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        if (context != null) {
            try {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("key=2000086&");
                stringBuffer.append("network_type=" + o.C(context) + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("uniquekey=" + str + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid=" + str2 + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid_n=" + str4 + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("cid=" + str5 + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("unit_id=" + str6 + ContainerUtils.FIELD_DELIMITER);
                StringBuilder sb = new StringBuilder();
                sb.append("packageName=");
                sb.append(str3);
                stringBuffer.append(sb.toString());
                if (a.a().c()) {
                    a.a().a(stringBuffer.toString());
                } else {
                    c(context, stringBuffer.toString());
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static void a(Context context, String str, String str2, String str3, int i, String str4, String str5, String str6) {
        if (context != null) {
            try {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("key=2000087&");
                stringBuffer.append("network_type=" + o.C(context) + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("uniquekey=" + str + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid=" + str2 + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid_n=" + str4 + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("cid=" + str5 + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("unit_id=" + str6 + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("packageName=" + str3 + ContainerUtils.FIELD_DELIMITER);
                StringBuilder sb = new StringBuilder();
                sb.append("result=");
                sb.append(i);
                stringBuffer.append(sb.toString());
                if (a.a().c()) {
                    a.a().a(stringBuffer.toString());
                } else {
                    c(context, stringBuffer.toString());
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        if (context != null) {
            try {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("key=2000089&");
                stringBuffer.append("appid=" + com.mbridge.msdk.foundation.controller.a.f().k() + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("s=" + str + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("i=" + str2 + ContainerUtils.FIELD_DELIMITER);
                StringBuilder sb = new StringBuilder();
                sb.append("i_c=");
                sb.append(str3);
                stringBuffer.append(sb.toString());
                if (a.a().c()) {
                    a.a().a(stringBuffer.toString());
                } else {
                    c(context, stringBuffer.toString());
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private static void c(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            new com.mbridge.msdk.foundation.same.report.d.a(context).c(0, com.mbridge.msdk.foundation.same.net.g.d.c().a, a(str, context, ""), new com.mbridge.msdk.foundation.same.report.d.b() { // from class: com.mbridge.msdk.foundation.same.report.d.1
                @Override // com.mbridge.msdk.foundation.same.report.d.b
                public final void a(String str2) {
                    s.d(d.a, str2);
                }

                @Override // com.mbridge.msdk.foundation.same.report.d.b
                public final void b(String str2) {
                    s.d(d.a, str2);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            s.d(a, e.getMessage());
        }
    }

    private static void a(Context context, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        try {
            new com.mbridge.msdk.foundation.same.report.d.a(context).c(0, com.mbridge.msdk.foundation.same.net.g.d.c().a, a(str, context, str2), new com.mbridge.msdk.foundation.same.report.d.b() { // from class: com.mbridge.msdk.foundation.same.report.d.2
                @Override // com.mbridge.msdk.foundation.same.report.d.b
                public final void a(String str3) {
                    s.d(d.a, str3);
                }

                @Override // com.mbridge.msdk.foundation.same.report.d.b
                public final void b(String str3) {
                    s.d(d.a, str3);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            s.d(a, e.getMessage());
        }
    }

    public static void a(String str) {
        com.mbridge.msdk.foundation.same.report.d.a aVar = new com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j());
        String str2 = "key=2000088&state=" + str + "&network_type=" + o.C(com.mbridge.msdk.foundation.controller.a.f().j());
        s.d(a, str2);
        aVar.c(0, com.mbridge.msdk.foundation.same.net.g.d.c().a, a(str2, com.mbridge.msdk.foundation.controller.a.f().j(), ""), null);
    }

    public static void a(int i, String str, String str2) {
        try {
            new com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j()).c(0, com.mbridge.msdk.foundation.same.net.g.d.c().a, a("key=2000080" + ContainerUtils.FIELD_DELIMITER + "reason=" + str2 + ContainerUtils.FIELD_DELIMITER + "ad_type=" + i + ContainerUtils.FIELD_DELIMITER + "url=" + URLEncoder.encode(str, "utf-8"), com.mbridge.msdk.foundation.controller.a.f().j(), ""), null);
        } catch (Exception e) {
            s.d(a, e.getMessage());
        }
    }
}
