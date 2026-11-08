package com.mbridge.msdk.video.js.a;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.db.u;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.DomainCampaignEx;
import com.mbridge.msdk.foundation.entity.q;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.foundation.tools.y;
import com.mbridge.msdk.playercommon.exoplayer2.text.ttml.TtmlNode;
import com.mbridge.msdk.video.js.a.c;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: JSCommon.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class j extends c {
    private static final String o = "j";
    private Activity p;
    private String q;
    private String r;
    private Context s;
    private com.mbridge.msdk.video.bt.module.a.b t;
    private CampaignEx u;
    private List<CampaignEx> v;
    private int w;
    private String x = "";
    private String y = "";
    private boolean z = false;
    private boolean A = false;
    private boolean B = true;

    public final void a(CampaignEx campaignEx) {
        this.u = campaignEx;
    }

    public final void a(List<CampaignEx> list) {
        this.v = list;
    }

    public final void c(boolean z) {
        this.B = z;
    }

    public final void d(boolean z) {
        this.z = z;
    }

    public j(Activity activity, CampaignEx campaignEx) {
        this.p = activity;
        this.u = campaignEx;
    }

    public j(Activity activity, CampaignEx campaignEx, List<CampaignEx> list) {
        this.p = activity;
        this.u = campaignEx;
        this.v = list;
    }

    public final void o() {
        this.q = "";
    }

    public final void c(String str) {
        this.x = str;
    }

    public final void d(String str) {
        this.y = str;
    }

    public final int p() {
        return this.w;
    }

    public final void j(int i) {
        this.w = i;
    }

    public final void e(boolean z) {
        this.A = z;
    }

    @Override // com.mbridge.msdk.video.js.a.c, com.mbridge.msdk.video.js.c
    public final String d() {
        this.m.a();
        this.a = true;
        if (TextUtils.isEmpty(this.q)) {
            this.q = q();
        }
        return this.q;
    }

    private String q() {
        JSONObject jSONObject = new JSONObject();
        com.mbridge.msdk.foundation.tools.c cVar = new com.mbridge.msdk.foundation.tools.c(com.mbridge.msdk.foundation.controller.a.f().j());
        try {
            jSONObject.put(MBridgeConstans.PROPERTIES_UNIT_ID, this.j);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("instanceId", this.x);
            jSONObject2.put("rootViewInstanceId", this.y);
            jSONObject2.put("isRootTemplateWebView", this.z);
            jSONObject.put("sdk_info", "MAL_15.7.57,3.0.1");
            jSONObject2.put("playVideoMute", this.n);
            jSONObject.put("sdkSetting", jSONObject2);
            jSONObject.put("device", cVar.a());
            JSONArray jSONArray = new JSONArray();
            if (this.v != null && this.v.size() > 0) {
                for (CampaignEx campaignEx : this.v) {
                    jSONArray.put(CampaignEx.campaignToJsonObject(campaignEx, campaignEx.isReady(), c(campaignEx)));
                }
            } else {
                jSONArray.put(CampaignEx.campaignToJsonObject(this.u));
            }
            jSONObject.put("campaignList", jSONArray);
            jSONObject.put("unitSetting", z());
            String strC = com.mbridge.msdk.b.b.a().c(com.mbridge.msdk.foundation.controller.a.f().k());
            if (!TextUtils.isEmpty(strC)) {
                JSONObject jSONObject3 = new JSONObject(strC);
                a(jSONObject3);
                String strD = com.mbridge.msdk.b.b.a().d(this.j);
                if (!TextUtils.isEmpty(strD)) {
                    jSONObject3.put("ivreward", new JSONObject(strD));
                }
                jSONObject.put("appSetting", jSONObject3);
            }
            jSONObject.put("rewardSetting", A());
            if (!TextUtils.isEmpty(this.j)) {
                jSONObject.put(MBridgeConstans.PROPERTIES_UNIT_ID, this.j);
            }
            jSONObject.put("rw_plus", this.A ? "1" : PushConstants.PUSH_TYPE_NOTIFY);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject.toString();
    }

    private void a(JSONObject jSONObject) throws JSONException {
        try {
            Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
            String string = y.b(contextJ, "MBridge_ConfirmTitle" + this.j, "").toString();
            String string2 = y.b(contextJ, "MBridge_ConfirmContent" + this.j, "").toString();
            String string3 = y.b(contextJ, "MBridge_CancelText" + this.j, "").toString();
            String string4 = y.b(contextJ, "MBridge_ConfirmText" + this.j, "").toString();
            if (!TextUtils.isEmpty(string)) {
                jSONObject.put("confirm_title", string);
            }
            if (!TextUtils.isEmpty(string2)) {
                jSONObject.put("confirm_description", string2);
            }
            if (!TextUtils.isEmpty(string3)) {
                jSONObject.put("confirm_t", string3);
            }
            if (!TextUtils.isEmpty(string4)) {
                jSONObject.put("confirm_c_play", string4);
            }
            if (TextUtils.isEmpty(string4)) {
                return;
            }
            jSONObject.put("confirm_c_rv", string4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private CampaignEx a(String str, CampaignEx campaignEx) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return campaignEx;
        }
        if (TextUtils.isEmpty(str) && campaignEx == null) {
            return null;
        }
        if (!str.contains("notice")) {
            try {
                JSONObject jSONObjectCampaignToJsonObject = CampaignEx.campaignToJsonObject(campaignEx);
                CampaignEx campaignWithBackData = CampaignEx.parseCampaignWithBackData(jSONObjectCampaignToJsonObject);
                if (campaignWithBackData == null) {
                    campaignWithBackData = campaignEx;
                }
                if (!TextUtils.isEmpty(str)) {
                    a(jSONObjectCampaignToJsonObject, campaignWithBackData);
                    JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject(com.mbridge.msdk.foundation.same.a.o);
                    String strValueOf = "-999";
                    String strValueOf2 = "-999";
                    if (jSONObjectOptJSONObject != null) {
                        strValueOf = String.valueOf(w.b(this.p, Integer.valueOf(jSONObjectOptJSONObject.getString(com.mbridge.msdk.foundation.same.a.m)).intValue()));
                        strValueOf2 = String.valueOf(w.b(this.p, Integer.valueOf(jSONObjectOptJSONObject.getString(com.mbridge.msdk.foundation.same.a.n)).intValue()));
                    }
                    campaignWithBackData.setClickURL(com.mbridge.msdk.click.c.a(campaignWithBackData.getClickURL(), strValueOf, strValueOf2));
                    String noticeUrl = campaignWithBackData.getNoticeUrl();
                    if (jSONObjectOptJSONObject != null) {
                        Iterator<String> itKeys = jSONObjectOptJSONObject.keys();
                        StringBuilder sb = new StringBuilder();
                        while (itKeys.hasNext()) {
                            sb.append(ContainerUtils.FIELD_DELIMITER);
                            String next = itKeys.next();
                            String strOptString = jSONObjectOptJSONObject.optString(next);
                            if (com.mbridge.msdk.foundation.same.a.m.equals(next) || com.mbridge.msdk.foundation.same.a.n.equals(next)) {
                                strOptString = String.valueOf(w.b(this.p, Integer.valueOf(strOptString).intValue()));
                            }
                            sb.append(next);
                            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                            sb.append(strOptString);
                        }
                        campaignWithBackData.setNoticeUrl(noticeUrl + ((Object) sb));
                    }
                }
                return campaignWithBackData;
            } catch (JSONException e) {
                e.printStackTrace();
                return campaignEx;
            } catch (Throwable th) {
                th.printStackTrace();
                return campaignEx;
            }
        }
        try {
            JSONObject jSONObjectCampaignToJsonObject2 = CampaignEx.campaignToJsonObject(campaignEx);
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.opt(DomainCampaignEx.JSON_KEY_AKDLUI) == null) {
                jSONObject.put(DomainCampaignEx.JSON_KEY_AKDLUI, "");
            }
            try {
                if (!jSONObject.has(CampaignEx.JSON_KEY_DEEP_LINK_URL)) {
                    jSONObjectCampaignToJsonObject2.put(CampaignEx.JSON_KEY_DEEP_LINK_URL, "");
                }
            } catch (Exception unused) {
            }
            Iterator<String> itKeys2 = jSONObject.keys();
            while (itKeys2.hasNext()) {
                String next2 = itKeys2.next();
                jSONObjectCampaignToJsonObject2.put(next2, jSONObject.getString(next2));
            }
            CampaignEx campaignWithBackData2 = CampaignEx.parseCampaignWithBackData(jSONObjectCampaignToJsonObject2);
            a(jSONObjectCampaignToJsonObject2, campaignWithBackData2);
            return campaignWithBackData2;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return campaignEx;
        }
    }

    private void a(JSONObject jSONObject, CampaignEx campaignEx) {
        try {
            String strOptString = jSONObject.optString("unitId");
            if (TextUtils.isEmpty(strOptString)) {
                return;
            }
            campaignEx.setCampaignUnitId(strOptString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.mbridge.msdk.video.js.a.c, com.mbridge.msdk.video.js.d
    public void click(int i, String str) {
        super.click(i, str);
        try {
            if (i != 1) {
                if (i != 3) {
                    return;
                }
                if (this.k != null && this.k.o() == -1) {
                    a(new c.b(this, this.m));
                }
                click(1, str);
                return;
            }
            if (this.u == null && this.v != null && this.v.size() > 0) {
                this.u = this.v.get(0);
            }
            if (this.u == null) {
                return;
            }
            final CampaignEx campaignExA = a(str, this.u);
            if (campaignExA != null && campaignExA.needShowIDialog() && this.B && com.mbridge.msdk.click.c.a(campaignExA)) {
                campaignExA.getAppName();
                try {
                    com.mbridge.msdk.widget.dialog.a aVar = new com.mbridge.msdk.widget.dialog.a() { // from class: com.mbridge.msdk.video.js.a.j.1
                        @Override // com.mbridge.msdk.widget.dialog.a
                        public final void a() {
                            if (j.this.t != null) {
                                j.this.t.b();
                            }
                            j.this.b(campaignExA);
                        }

                        @Override // com.mbridge.msdk.widget.dialog.a
                        public final void b() {
                            if (j.this.t != null) {
                                j.this.t.b();
                            }
                        }

                        @Override // com.mbridge.msdk.widget.dialog.a
                        public final void c() {
                            j.this.b(campaignExA);
                        }
                    };
                    Context contextD = this.p;
                    if (contextD == null || contextD == contextD.getApplicationContext()) {
                        contextD = this.s;
                        if (contextD == null || contextD == contextD.getApplicationContext()) {
                            contextD = com.mbridge.msdk.video.bt.a.c.a().d(this.j + "_" + campaignExA.getRequestId());
                            if (contextD == null || contextD == contextD.getApplicationContext()) {
                                contextD = com.mbridge.msdk.foundation.controller.a.f().j();
                            }
                        }
                    }
                    Context context = contextD;
                    if (context != null && campaignExA != null) {
                        if (!r().b(campaignExA)) {
                            com.mbridge.msdk.videocommon.d.b.a().a(com.mbridge.msdk.foundation.controller.a.f().k(), this.j, false);
                            com.mbridge.msdk.mbjscommon.confirmation.e.a().a(com.mbridge.msdk.videocommon.d.c.a, campaignExA, context, this.j, aVar);
                            if (this.t != null) {
                                this.t.a();
                                return;
                            }
                            return;
                        }
                        this.m.a(false);
                        return;
                    }
                } catch (Throwable th) {
                    s.a(o, th.getMessage());
                }
                b(campaignExA);
                return;
            }
            b(campaignExA);
        } catch (Throwable th2) {
            s.b(o, th2.getMessage(), th2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(CampaignEx campaignEx) {
        String queryParameter;
        try {
            queryParameter = Uri.parse(campaignEx.getNoticeUrl()).getQueryParameter(com.mbridge.msdk.foundation.same.a.p);
        } catch (Throwable th) {
            s.b(o, "INSTALL", th);
        }
        int i = !TextUtils.isEmpty(queryParameter) ? Integer.parseInt(queryParameter) : 0;
        this.m.a(i == 2);
        r().a(this.m);
        r().c(campaignEx);
        com.mbridge.msdk.video.module.b.a.d(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx);
    }

    @Override // com.mbridge.msdk.video.js.a.c, com.mbridge.msdk.video.js.d
    public void handlerH5Exception(int i, String str) {
        super.handlerH5Exception(i, str);
        try {
            this.m.a(i, str);
        } catch (Throwable th) {
            s.b(o, th.getMessage(), th);
        }
    }

    @Override // com.mbridge.msdk.video.js.a.c, com.mbridge.msdk.video.js.c
    public final void a(int i, String str) {
        super.a(i, str);
        if (i != 2) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("event", "event");
            String strOptString2 = jSONObject.optString("template", "-1");
            String strOptString3 = jSONObject.optString(TtmlNode.TAG_LAYOUT, "-1");
            String strOptString4 = jSONObject.optString(MBridgeConstans.PROPERTIES_UNIT_ID, this.j);
            int iC = o.C(this.p.getApplication());
            u.a(com.mbridge.msdk.foundation.db.i.a(this.p.getApplication())).a(new q("2000039", strOptString, strOptString2, strOptString3, strOptString4, this.u.getId(), iC, o.a(this.p.getApplication(), iC)));
        } catch (Throwable th) {
            s.b(o, th.getMessage(), th);
        }
    }

    @Override // com.mbridge.msdk.video.js.a.c, com.mbridge.msdk.video.js.c
    public final void g() {
        super.g();
        if (this.m != null) {
            this.m.b();
        }
    }

    @Override // com.mbridge.msdk.video.js.a.c, com.mbridge.msdk.video.js.c
    public final void e() {
        super.e();
        try {
            if (this.p != null) {
                this.p.finish();
            }
        } catch (Throwable th) {
            s.b(o, th.getMessage(), th);
        }
    }

    private com.mbridge.msdk.click.b r() {
        if (this.l == null) {
            this.l = new com.mbridge.msdk.click.b(com.mbridge.msdk.foundation.controller.a.f().j(), this.j);
        }
        return this.l;
    }

    @Override // com.mbridge.msdk.video.js.a.c, com.mbridge.msdk.video.js.c
    public final String h(int i) {
        switch (i) {
            case 1:
                return t().toString();
            case 2:
                return u().toString();
            case 3:
                return v().toString();
            case 4:
                return w().toString();
            case 5:
                return x().toString();
            case 6:
                return y().toString();
            default:
                return s().toString();
        }
    }

    private JSONObject s() {
        JSONObject jSONObject = new JSONObject();
        com.mbridge.msdk.foundation.tools.c cVar = new com.mbridge.msdk.foundation.tools.c(com.mbridge.msdk.foundation.controller.a.f().j());
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("playVideoMute", this.n);
            jSONObject.put("sdkSetting", jSONObject2);
            jSONObject.put("device", cVar.a());
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(CampaignEx.campaignToJsonObject(this.u));
            jSONObject.put("campaignList", jSONArray);
            jSONObject.put("unitSetting", z());
            String strC = com.mbridge.msdk.b.b.a().c(com.mbridge.msdk.foundation.controller.a.f().k());
            if (!TextUtils.isEmpty(strC)) {
                JSONObject jSONObject3 = new JSONObject(strC);
                a(jSONObject3);
                String strD = com.mbridge.msdk.b.b.a().d(this.j);
                if (!TextUtils.isEmpty(strD)) {
                    jSONObject3.put("ivreward", strD);
                }
                jSONObject.put("appSetting", jSONObject3.toString());
            }
            jSONObject.put("rewardSetting", A());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    private JSONObject t() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sdk_info", "MAL_15.7.57,3.0.1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private JSONObject u() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(this.j)) {
                jSONObject.put(MBridgeConstans.PROPERTIES_UNIT_ID, this.j);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private JSONObject v() {
        JSONObject jSONObject = new JSONObject();
        try {
            String strC = com.mbridge.msdk.b.b.a().c(com.mbridge.msdk.foundation.controller.a.f().k());
            if (!TextUtils.isEmpty(strC)) {
                jSONObject.put("appSetting", new JSONObject(strC));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    private JSONObject w() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.k != null) {
                jSONObject.put("unitSetting", this.k.G());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private JSONObject x() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("device", new com.mbridge.msdk.foundation.tools.c(com.mbridge.msdk.foundation.controller.a.f().j()).a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private JSONObject y() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("playVideoMute", this.n);
            jSONObject2.put("instanceId", this.x);
            jSONObject.put("sdkSetting", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private JSONObject z() {
        return this.k != null ? this.k.G() : new JSONObject();
    }

    private JSONObject A() {
        JSONObject jSONObject = new JSONObject();
        com.mbridge.msdk.videocommon.d.a aVarB = com.mbridge.msdk.videocommon.d.b.a().b();
        return aVarB != null ? aVarB.j() : jSONObject;
    }

    @Override // com.mbridge.msdk.video.js.a.c, com.mbridge.msdk.video.js.c
    public final void a(Activity activity) {
        this.p = activity;
    }

    private boolean c(CampaignEx campaignEx) {
        com.mbridge.msdk.b.a aVarB;
        try {
            String strK = com.mbridge.msdk.foundation.controller.a.f().k();
            long jB = (TextUtils.isEmpty(strK) || (aVarB = com.mbridge.msdk.b.b.a().b(strK)) == null) ? 0L : aVarB.B() * 1000;
            com.mbridge.msdk.videocommon.d.a aVarB2 = com.mbridge.msdk.videocommon.d.b.a().b();
            long jD = aVarB2 != null ? aVarB2.d() : 0L;
            if (campaignEx != null) {
                return campaignEx.isSpareOffer(jD, jB);
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.mbridge.msdk.video.js.a.c, com.mbridge.msdk.video.js.c
    public final void a(Context context) {
        this.s = context;
    }

    @Override // com.mbridge.msdk.video.js.a.c, com.mbridge.msdk.video.js.c
    public final void a(com.mbridge.msdk.video.bt.module.a.b bVar) {
        this.t = bVar;
    }

    @Override // com.mbridge.msdk.video.js.a.c, com.mbridge.msdk.video.js.c
    public final void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.r = str;
    }

    @Override // com.mbridge.msdk.video.js.a.c, com.mbridge.msdk.video.js.c
    public final String j() {
        s.a(o, "getNotchArea");
        return this.r;
    }
}
