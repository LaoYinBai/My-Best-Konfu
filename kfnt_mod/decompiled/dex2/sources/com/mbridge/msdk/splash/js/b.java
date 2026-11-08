package com.mbridge.msdk.splash.js;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import com.heytap.mcssdk.mode.CommandMessage;
import com.heytap.mcssdk.mode.Message;
import com.mbridge.msdk.b.d;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.db.m;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView;
import com.mbridge.msdk.mbjscommon.windvane.h;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SplashJSBridgeImpl.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b implements a {
    private WeakReference<Context> b;
    private List<CampaignEx> c;
    private String d;
    private String e;
    private int f;
    private int g;
    private int i;
    private com.mbridge.msdk.splash.d.a j;
    private SplashExpandDialog k;
    private String a = "SplashJSBridgeImpl";
    private int h = 5;

    public b(Context context, String str, String str2) {
        this.e = str;
        this.d = str2;
        this.b = new WeakReference<>(context);
    }

    public final void a(Context context) {
        this.b = new WeakReference<>(context);
    }

    public final void a(int i) {
        this.g = i;
    }

    public final void b(int i) {
        this.h = i;
    }

    public final void c(int i) {
        this.i = i;
    }

    public final void a(com.mbridge.msdk.splash.d.a aVar) {
        if (aVar != null) {
            this.j = aVar;
        }
    }

    public final void a(List<CampaignEx> list) {
        this.c = list;
    }

    public final void a(Object obj, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            com.mbridge.msdk.splash.a.a aVar = new com.mbridge.msdk.splash.a.a(com.mbridge.msdk.foundation.controller.a.f().j());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("dev_close_state", this.f);
            jSONObject.put("sdkSetting", jSONObject2);
            jSONObject.put("device", aVar.a());
            jSONObject.put("campaignList", CampaignEx.parseCamplistToJson(this.c));
            d dVarF = com.mbridge.msdk.b.b.a().f(com.mbridge.msdk.foundation.controller.a.f().k(), this.d);
            if (dVarF == null) {
                dVarF = d.d(this.d);
            }
            if (!TextUtils.isEmpty(this.e)) {
                dVarF.h(this.e);
            }
            dVarF.a(this.d);
            dVarF.b(this.h);
            dVarF.a(this.g);
            jSONObject.put("unitSetting", dVarF.v());
            String strC = com.mbridge.msdk.b.b.a().c(com.mbridge.msdk.foundation.controller.a.f().k());
            if (!TextUtils.isEmpty(strC)) {
                jSONObject.put("appSetting", new JSONObject(strC));
            }
            jSONObject.put("sdk_info", com.mbridge.msdk.mbjscommon.base.d.a);
            s.d(this.a, "init" + jSONObject.toString());
            h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (Throwable th) {
            s.b(this.a, "init", th);
        }
    }

    public final void b(Object obj, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            int iOptInt = new JSONObject(str).optInt("state");
            if (this.j != null) {
                this.j.a(iOptInt);
            }
        } catch (Throwable th) {
            s.b(this.a, "toggleCloseBtn", th);
        }
    }

    public final void c(Object obj, String str) {
        if (this.j != null) {
            this.j.a(obj, str);
        }
    }

    public final void d(Object obj, String str) {
        s.d(this.a, "install");
        try {
            if (this.c == null) {
                return;
            }
            CampaignEx campaignEx = null;
            if (this.c != null && this.c.size() > 0) {
                campaignEx = this.c.get(0);
            }
            if (TextUtils.isEmpty(str)) {
                return;
            }
            try {
                JSONObject jSONObjectCampaignToJsonObject = CampaignEx.campaignToJsonObject(campaignEx);
                JSONObject jSONObject = new JSONObject(str);
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
            s.b(this.a, CampaignEx.JSON_NATIVE_VIDEO_CLICK, th);
        }
    }

    public final void e(Object obj, String str) throws JSONException {
        WindVaneWebView windVaneWebView;
        s.d(this.a, "openURL:" + str);
        if (TextUtils.isEmpty(str)) {
            com.mbridge.msdk.mbjscommon.bridge.b.a(obj, "params is null");
            return;
        }
        Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (contextJ == null) {
            try {
                if ((obj instanceof com.mbridge.msdk.mbjscommon.windvane.a) && (windVaneWebView = ((com.mbridge.msdk.mbjscommon.windvane.a) obj).a) != null) {
                    contextJ = windVaneWebView.getContext();
                }
            } catch (Exception e) {
                s.d(this.a, e.getMessage());
            }
        }
        if (contextJ == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("url");
            int iOptInt = jSONObject.optInt(Message.TYPE);
            if (iOptInt == 1) {
                com.mbridge.msdk.click.c.a(contextJ, strOptString);
            } else if (iOptInt == 2) {
                com.mbridge.msdk.click.c.b(contextJ, strOptString);
            }
        } catch (JSONException e2) {
            s.d(this.a, e2.getMessage());
        } catch (Throwable th) {
            s.d(this.a, th.getMessage());
        }
    }

    public final void f(Object obj, String str) throws JSONException {
        s.a(this.a, "cai:" + str);
        if (TextUtils.isEmpty(str)) {
            com.mbridge.msdk.mbjscommon.bridge.b.a(obj, "params is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            String strOptString = new JSONObject(str).optString("packageName");
            if (TextUtils.isEmpty(strOptString)) {
                com.mbridge.msdk.mbjscommon.bridge.b.a(obj, "packageName is empty");
            }
            int i = w.c(com.mbridge.msdk.foundation.controller.a.f().j(), strOptString) ? 1 : 2;
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(CommandMessage.CODE, com.mbridge.msdk.mbjscommon.bridge.b.b);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("result", i);
                jSONObject.put("data", jSONObject2);
                h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
            } catch (Exception e) {
                com.mbridge.msdk.mbjscommon.bridge.b.a(obj, e.getMessage());
                s.a(this.a, e.getMessage());
            }
        } catch (JSONException e2) {
            com.mbridge.msdk.mbjscommon.bridge.b.a(obj, "exception: " + e2.getLocalizedMessage());
            s.b(this.a, "cai", e2);
        } catch (Throwable th) {
            com.mbridge.msdk.mbjscommon.bridge.b.a(obj, "exception: " + th.getLocalizedMessage());
            s.b(this.a, "cai", th);
        }
    }

    public final void g(Object obj, String str) throws JSONException {
        s.a(this.a, "gial:" + str);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CommandMessage.CODE, com.mbridge.msdk.mbjscommon.bridge.b.b);
            JSONObject jSONObject2 = new JSONObject();
            List<String> list = com.mbridge.msdk.foundation.controller.a.b;
            JSONArray jSONArray = new JSONArray();
            if (list != null && list.size() > 0) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    jSONArray.put(list.get(i));
                }
            }
            jSONObject2.put("packageNameList", jSONArray);
            jSONObject.put("data", jSONObject2);
            h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (Exception e) {
            com.mbridge.msdk.mbjscommon.bridge.b.a(obj, e.getMessage());
            s.a(this.a, e.getMessage());
        } catch (Throwable th) {
            com.mbridge.msdk.mbjscommon.bridge.b.a(obj, th.getMessage());
            s.a(this.a, th.getMessage());
        }
    }

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
        s.d(this.a, CampaignEx.JSON_NATIVE_VIDEO_CLOSE);
        try {
            if (this.j != null) {
                this.j.a();
            }
        } catch (Throwable th) {
            s.b(this.a, CampaignEx.JSON_NATIVE_VIDEO_CLOSE, th);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.mraid.b
    public final void unload() {
        close();
    }

    @Override // com.mbridge.msdk.mbjscommon.mraid.b
    public final void useCustomClose(boolean z) {
        int i = z ? 2 : 1;
        try {
            if (this.j != null) {
                this.j.a(i);
            }
        } catch (Throwable th) {
            s.b(this.a, "useCustomClose", th);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.mraid.b
    public final void expand(String str, boolean z) {
        try {
            Bundle bundle = new Bundle();
            bundle.putString("url", str);
            bundle.putBoolean("shouldUseCustomClose", z);
            if (this.b == null || this.b.get() == null) {
                return;
            }
            if (this.k == null || !this.k.isShowing()) {
                this.k = new SplashExpandDialog(this.b.get(), bundle, this.j);
                this.k.setCampaignList(this.d, this.c);
                this.k.show();
                if (this.j != null) {
                    this.j.a(true);
                }
                com.mbridge.msdk.splash.e.a.a(this.d, (this.c == null || this.c.size() <= 0) ? null : this.c.get(0), str);
            }
        } catch (Throwable th) {
            s.b(this.a, "expand", th);
        }
    }

    public final void i(Object obj, String str) {
        s.a(this.a, "sendImpressions:" + str);
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            JSONArray jSONArray = new JSONArray(str);
            final ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                String string = jSONArray.getString(i);
                for (CampaignEx campaignEx : this.c) {
                    if (campaignEx.getId().equals(string)) {
                        com.mbridge.msdk.foundation.same.a.d.b(this.d, campaignEx, "splash");
                        arrayList.add(string);
                    }
                }
            }
            new Thread(new Runnable() { // from class: com.mbridge.msdk.splash.js.b.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        m mVarA = m.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            mVarA.b((String) it.next());
                        }
                    } catch (Exception unused) {
                        s.d(b.this.a, "campain can't insert db");
                    }
                }
            }).start();
        } catch (Throwable th) {
            s.b(this.a, "sendImpressions", th);
        }
    }

    public final void j(Object obj, String str) throws JSONException {
        s.a(this.a, "reportUrls:" + str);
        if (TextUtils.isEmpty(str)) {
            com.mbridge.msdk.mbjscommon.bridge.b.a(obj, "params is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                com.mbridge.msdk.click.b.a(com.mbridge.msdk.foundation.controller.a.f().j(), (CampaignEx) null, "", jSONObject.optString("url"), false, jSONObject.optInt(Message.TYPE) != 0);
            }
            h.a().a(obj, c.a(0));
        } catch (Throwable th) {
            s.b(this.a, "reportUrls", th);
        }
    }

    public final void k(Object obj, String str) {
        try {
            if (obj instanceof com.mbridge.msdk.mbjscommon.windvane.a) {
                h.a().a(((com.mbridge.msdk.mbjscommon.windvane.a) obj).a);
            }
        } catch (Throwable th) {
            s.b(this.a, "onJSBridgeConnect", th);
        }
    }

    public final void l(Object obj, String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(CommandMessage.CODE, 0);
            jSONObject.put("message", "Call pause count down success.");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("countdown", this.i);
            jSONObject.put("data", jSONObject2);
            h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (Exception e) {
            s.d(this.a, e.getMessage());
        }
        if (this.j != null) {
            this.j.a(1, -1);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m(java.lang.Object r3, java.lang.String r4) {
        /*
            r2 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            r1 = 0
            if (r0 != 0) goto L2c
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: java.lang.Exception -> L22
            r0.<init>(r4)     // Catch: java.lang.Exception -> L22
            java.lang.String r4 = "countdown"
            int r4 = r0.optInt(r4)     // Catch: java.lang.Exception -> L22
            com.mbridge.msdk.mbjscommon.windvane.h r0 = com.mbridge.msdk.mbjscommon.windvane.h.a()     // Catch: java.lang.Exception -> L1f
            java.lang.String r1 = com.mbridge.msdk.splash.js.c.a(r1)     // Catch: java.lang.Exception -> L1f
            r0.a(r3, r1)     // Catch: java.lang.Exception -> L1f
            r1 = r4
            goto L2c
        L1f:
            r3 = move-exception
            r1 = r4
            goto L23
        L22:
            r3 = move-exception
        L23:
            java.lang.String r4 = r2.a
            java.lang.String r3 = r3.getMessage()
            com.mbridge.msdk.foundation.tools.s.d(r4, r3)
        L2c:
            com.mbridge.msdk.splash.d.a r3 = r2.j
            if (r3 == 0) goto L36
            com.mbridge.msdk.splash.d.a r3 = r2.j
            r4 = 2
            r3.a(r4, r1)
        L36:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.splash.js.b.m(java.lang.Object, java.lang.String):void");
    }
}
