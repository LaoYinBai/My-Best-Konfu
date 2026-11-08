package com.mbridge.msdk.advanced.js;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import com.mbridge.msdk.b.d;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.db.m;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView;
import com.mbridge.msdk.mbjscommon.windvane.h;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeAdvancedJSBridgeImpl.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a extends com.mbridge.msdk.mbjscommon.bridge.a {
    private WeakReference<Context> c;
    private List<CampaignEx> d;
    private String e;
    private String f;
    private int g;
    private int h;
    private com.mbridge.msdk.advanced.c.a j;
    private NativeAdvancedExpandDialog k;
    private String b = "NativeAdvancedJSBridgeImpl";
    private int i = 5;

    public a(Context context, String str, String str2) {
        this.f = str;
        this.e = str2;
        this.c = new WeakReference<>(context);
    }

    public final void a(int i) {
        this.h = i;
    }

    public final void b(int i) {
        this.i = i;
    }

    public final void a(com.mbridge.msdk.advanced.c.a aVar) {
        if (aVar != null) {
            this.j = aVar;
        }
    }

    public final void a(List<CampaignEx> list) {
        this.d = list;
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.a, com.mbridge.msdk.mbjscommon.bridge.c
    public final void a(Object obj, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            com.mbridge.msdk.advanced.common.a aVar = new com.mbridge.msdk.advanced.common.a(com.mbridge.msdk.foundation.controller.a.f().j());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("dev_close_state", this.g);
            jSONObject2.put("customURLScheme", 1);
            jSONObject.put("sdkSetting", jSONObject2);
            jSONObject.put("device", aVar.a());
            jSONObject.put("campaignList", CampaignEx.parseCamplistToJson(this.d));
            d dVarG = com.mbridge.msdk.b.b.a().g(com.mbridge.msdk.foundation.controller.a.f().k(), this.e);
            if (dVarG == null) {
                dVarG = d.c(this.e);
            }
            if (!TextUtils.isEmpty(this.f)) {
                dVarG.h(this.f);
            }
            dVarG.a(this.e);
            dVarG.b(this.i);
            dVarG.a(this.h);
            jSONObject.put("unitSetting", dVarG.v());
            String strC = com.mbridge.msdk.b.b.a().c(com.mbridge.msdk.foundation.controller.a.f().k());
            if (!TextUtils.isEmpty(strC)) {
                jSONObject.put("appSetting", new JSONObject(strC));
            }
            jSONObject.put("sdk_info", com.mbridge.msdk.mbjscommon.base.d.a);
            s.d(this.b, "init" + jSONObject.toString());
            h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (Throwable th) {
            s.b(this.b, "init", th);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.a, com.mbridge.msdk.mbjscommon.bridge.c
    public final void b(Object obj, String str) {
        CampaignEx campaignEx = null;
        try {
            if (this.d != null && this.d.size() > 0) {
                campaignEx = this.d.get(0);
            }
            if (TextUtils.isEmpty(str)) {
                return;
            }
            try {
                JSONObject jSONObjectCampaignToJsonObject = CampaignEx.campaignToJsonObject(campaignEx);
                JSONObject jSONObject = new JSONObject(str).getJSONObject("pt");
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    jSONObjectCampaignToJsonObject.put(next, jSONObject.getString(next));
                }
                CampaignEx campaignWithBackData = CampaignEx.parseCampaignWithBackData(jSONObjectCampaignToJsonObject);
                String strOptString = jSONObjectCampaignToJsonObject.optString("unitId");
                if (!TextUtils.isEmpty(strOptString)) {
                    campaignWithBackData.setCampaignUnitId(strOptString);
                }
                campaignEx = campaignWithBackData;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (this.j != null) {
                this.j.a(campaignEx);
            }
        } catch (Throwable th) {
            s.b(this.b, CampaignEx.JSON_NATIVE_VIDEO_CLICK, th);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.a, com.mbridge.msdk.mbjscommon.bridge.c
    public final void c(Object obj, String str) {
        WindVaneWebView windVaneWebView;
        try {
            if (!(obj instanceof com.mbridge.msdk.mbjscommon.windvane.a) || (windVaneWebView = ((com.mbridge.msdk.mbjscommon.windvane.a) obj).a) == null) {
                return;
            }
            try {
                windVaneWebView.getWebViewListener().a(windVaneWebView, new JSONObject(str).getInt("isReady"));
            } catch (Exception unused) {
                windVaneWebView.getWebViewListener().a(windVaneWebView, 2);
            }
        } catch (Throwable th) {
            s.a(this.b, th.getMessage());
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.a, com.mbridge.msdk.mbjscommon.bridge.c
    public final void d(Object obj, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            int iOptInt = new JSONObject(str).optInt("state");
            if (this.j != null) {
                this.j.a(iOptInt);
            }
        } catch (Throwable th) {
            s.b(this.b, "toggleCloseBtn", th);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.a, com.mbridge.msdk.mbjscommon.bridge.c
    public final void e(Object obj, String str) throws JSONException {
        if (this.j != null) {
            b.a(obj);
            this.j.a(obj, str);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.a, com.mbridge.msdk.mbjscommon.bridge.c
    public final void f(Object obj, String str) {
        try {
            if (obj instanceof com.mbridge.msdk.mbjscommon.windvane.a) {
                h.a().a(((com.mbridge.msdk.mbjscommon.windvane.a) obj).a);
            }
        } catch (Throwable th) {
            s.b(this.b, "onJSBridgeConnect", th);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.a, com.mbridge.msdk.mbjscommon.bridge.c
    public final void g(Object obj, String str) {
        s.d(this.b, "install");
        CampaignEx campaignEx = null;
        try {
            if (this.d != null && this.d.size() > 0) {
                campaignEx = this.d.get(0);
            }
            if (TextUtils.isEmpty(str)) {
                return;
            }
            try {
                JSONObject jSONObjectCampaignToJsonObject = CampaignEx.campaignToJsonObject(campaignEx);
                JSONObject jSONObject = new JSONObject(str).getJSONObject("pt");
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    jSONObjectCampaignToJsonObject.put(next, jSONObject.getString(next));
                }
                CampaignEx campaignWithBackData = CampaignEx.parseCampaignWithBackData(jSONObjectCampaignToJsonObject);
                String strOptString = jSONObjectCampaignToJsonObject.optString("unitId");
                if (!TextUtils.isEmpty(strOptString)) {
                    campaignWithBackData.setCampaignUnitId(strOptString);
                }
                campaignEx = campaignWithBackData;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (this.j != null) {
                this.j.a(campaignEx);
            }
        } catch (Throwable th) {
            s.b(this.b, "install", th);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.a, com.mbridge.msdk.mbjscommon.bridge.c
    public final void h(Object obj, String str) throws JSONException {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            int i = new JSONObject(str).getInt("countdown");
            if (this.j != null) {
                this.j.b(i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.mraid.b
    public final void open(String str) {
        if (this.j != null) {
            this.j.a(true, str);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.mraid.b
    public final void close() {
        s.d(this.b, CampaignEx.JSON_NATIVE_VIDEO_CLOSE);
        try {
            if (this.j != null) {
                this.j.a();
            }
        } catch (Throwable th) {
            s.b(this.b, CampaignEx.JSON_NATIVE_VIDEO_CLOSE, th);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.mraid.b
    public final void unload() {
        close();
    }

    @Override // com.mbridge.msdk.mbjscommon.mraid.b
    public final void useCustomClose(boolean z) {
        try {
            if (this.h == -1) {
                int i = z ? 2 : 1;
                if (this.j != null) {
                    this.j.a(i);
                }
            }
        } catch (Throwable th) {
            s.b(this.b, "useCustomClose", th);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.mraid.b
    public final void expand(String str, boolean z) {
        try {
            Bundle bundle = new Bundle();
            bundle.putString("url", str);
            bundle.putBoolean("shouldUseCustomClose", z);
            if (this.c == null || this.c.get() == null) {
                return;
            }
            if (this.k == null || !this.k.isShowing()) {
                this.k = new NativeAdvancedExpandDialog(this.c.get(), bundle, this.j);
                this.k.setCampaignList(this.e, this.d);
                this.k.show();
                if (this.j != null) {
                    this.j.a(true);
                }
                com.mbridge.msdk.advanced.d.a.a(this.e, (this.d == null || this.d.size() <= 0) ? null : this.d.get(0), str);
            }
        } catch (Throwable th) {
            s.b(this.b, "expand", th);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.a, com.mbridge.msdk.mbjscommon.bridge.c
    public final void i(Object obj, String str) {
        s.a(this.b, "sendImpressions:" + str);
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            JSONArray jSONArray = new JSONArray(str);
            final ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                String string = jSONArray.getString(i);
                for (CampaignEx campaignEx : this.d) {
                    if (campaignEx.getId().equals(string)) {
                        com.mbridge.msdk.foundation.same.a.d.b(this.e, campaignEx, "h5_native");
                        arrayList.add(string);
                    }
                }
            }
            new Thread(new Runnable() { // from class: com.mbridge.msdk.advanced.js.a.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        m mVarA = m.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            mVarA.b((String) it.next());
                        }
                    } catch (Exception unused) {
                        s.d(a.this.b, "campain can't insert db");
                    }
                }
            }).start();
        } catch (Throwable th) {
            s.b(this.b, "sendImpressions", th);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.a, com.mbridge.msdk.mbjscommon.bridge.c
    public final void j(Object obj, String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            com.mbridge.msdk.mbjscommon.bridge.b.a(obj, "params is empty");
            return;
        }
        try {
            b.a(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.a(this.b, th.getMessage());
        }
    }
}
