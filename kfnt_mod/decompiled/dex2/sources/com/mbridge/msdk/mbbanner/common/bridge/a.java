package com.mbridge.msdk.mbbanner.common.bridge;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import com.heytap.mcssdk.mode.CommandMessage;
import com.heytap.mcssdk.mode.Message;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.b.d;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.db.m;
import com.mbridge.msdk.foundation.db.t;
import com.mbridge.msdk.foundation.download.download.H5DownLoadManager;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.p;
import com.mbridge.msdk.foundation.tools.c;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.v;
import com.mbridge.msdk.mbjscommon.bridge.b;
import com.mbridge.msdk.mbjscommon.windvane.h;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: BannerJSBridgeImpl.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a extends com.mbridge.msdk.mbjscommon.bridge.a {
    private WeakReference<Context> b;
    private List<CampaignEx> c;
    private String d;
    private String e;
    private int f;
    private com.mbridge.msdk.mbbanner.common.b.a g;
    private BannerExpandDialog h;
    private boolean i = false;

    public a(Context context, String str, String str2) {
        this.d = str;
        this.e = str2;
        this.b = new WeakReference<>(context);
    }

    public final void a(com.mbridge.msdk.mbbanner.common.b.a aVar) {
        if (aVar != null) {
            this.g = aVar;
        }
    }

    public final void a(List<CampaignEx> list) {
        this.c = list;
    }

    public final void a(int i) {
        this.f = i;
    }

    public final void a() {
        if (this.g != null) {
            this.g = null;
        }
        if (this.h != null) {
            this.h = null;
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.a, com.mbridge.msdk.mbjscommon.bridge.c
    public final void c(Object obj, String str) {
        if (obj != null) {
            try {
                int iOptInt = new JSONObject(str).optInt("isReady", 1);
                h.a().a(obj, b.a(0));
                if (this.g != null) {
                    this.g.b(iOptInt);
                }
            } catch (Throwable th) {
                s.b("BannerJSBridgeImpl", "readyStatus", th);
            }
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.a, com.mbridge.msdk.mbjscommon.bridge.c
    public final void a(Object obj, String str) {
        s.d("BannerJSBridgeImpl", "BANNER INIT INVOKE");
        try {
            JSONObject jSONObject = new JSONObject();
            c cVar = new c(com.mbridge.msdk.foundation.controller.a.f().j());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("dev_close_state", this.f);
            jSONObject.put("sdkSetting", jSONObject2);
            jSONObject.put("device", cVar.a());
            jSONObject.put("campaignList", CampaignEx.parseCamplistToJson(this.c));
            d dVarF = com.mbridge.msdk.b.b.a().f(com.mbridge.msdk.foundation.controller.a.f().k(), this.e);
            if (dVarF == null) {
                dVarF = d.d(this.e);
            }
            if (!TextUtils.isEmpty(this.d)) {
                dVarF.h(this.d);
            }
            jSONObject.put("unitSetting", dVarF.v());
            String strC = com.mbridge.msdk.b.b.a().c(com.mbridge.msdk.foundation.controller.a.f().k());
            if (!TextUtils.isEmpty(strC)) {
                jSONObject.put("appSetting", new JSONObject(strC));
            }
            h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (Throwable th) {
            s.b("BannerJSBridgeImpl", "init", th);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.a, com.mbridge.msdk.mbjscommon.bridge.c
    public final void b(Object obj, String str) {
        s.d("BannerJSBridgeImpl", CampaignEx.JSON_NATIVE_VIDEO_CLICK);
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
            if (this.g != null) {
                this.g.a(campaignEx);
            }
        } catch (Throwable th) {
            s.b("BannerJSBridgeImpl", CampaignEx.JSON_NATIVE_VIDEO_CLICK, th);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.a, com.mbridge.msdk.mbjscommon.bridge.c
    public final void d(Object obj, String str) {
        s.d("BannerJSBridgeImpl", "toggleCloseBtn");
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            int iOptInt = new JSONObject(str).optInt("state");
            if (this.g != null) {
                this.g.a(iOptInt);
            }
        } catch (Throwable th) {
            s.b("BannerJSBridgeImpl", "toggleCloseBtn", th);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.a, com.mbridge.msdk.mbjscommon.bridge.c
    public final void e(Object obj, String str) {
        s.d("BannerJSBridgeImpl", "triggerCloseBtn");
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            String strOptString = new JSONObject(str).optString("state");
            if (this.g != null) {
                this.g.a(strOptString);
            }
            h.a().a(obj, b.a(0));
        } catch (Throwable th) {
            s.b("BannerJSBridgeImpl", "triggerCloseBtn", th);
            h.a().a(obj, b.a(-1));
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.a, com.mbridge.msdk.mbjscommon.bridge.c
    public final void i(Object obj, String str) {
        s.a("BannerJSBridgeImpl", "sendImpressions:" + str);
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
                        com.mbridge.msdk.foundation.same.a.d.b(this.e, campaignEx, "banner");
                        arrayList.add(string);
                    }
                }
            }
            new Thread(new Runnable() { // from class: com.mbridge.msdk.mbbanner.common.bridge.a.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        m mVarA = m.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            mVarA.b((String) it.next());
                        }
                    } catch (Exception unused) {
                        s.d("BannerJSBridgeImpl", "campain can't insert db");
                    }
                }
            }).start();
        } catch (Throwable th) {
            s.b("BannerJSBridgeImpl", "sendImpressions", th);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.mraid.b
    public final void open(String str) {
        s.d("BannerJSBridgeImpl", "open");
        try {
            s.d("BannerJSBridgeImpl", str);
            if (this.c.size() > 1) {
                com.mbridge.msdk.foundation.controller.a.f().j().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                str = null;
            }
            if (this.g != null) {
                this.g.a(true, str);
            }
        } catch (Throwable th) {
            s.b("BannerJSBridgeImpl", "open", th);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.mraid.b
    public final void close() {
        s.d("BannerJSBridgeImpl", CampaignEx.JSON_NATIVE_VIDEO_CLOSE);
        try {
            if (this.g != null) {
                this.g.a();
            }
        } catch (Throwable th) {
            s.b("BannerJSBridgeImpl", CampaignEx.JSON_NATIVE_VIDEO_CLOSE, th);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.mraid.b
    public final void unload() {
        close();
    }

    private CampaignEx b() {
        if (this.c == null || this.c.size() <= 0) {
            return null;
        }
        return this.c.get(0);
    }

    @Override // com.mbridge.msdk.mbjscommon.mraid.b
    public final void useCustomClose(boolean z) {
        int i = z ? 2 : 1;
        try {
            if (this.g != null) {
                this.g.a(i);
            }
        } catch (Throwable th) {
            s.b("BannerJSBridgeImpl", "useCustomClose", th);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.mraid.b
    public final void expand(String str, boolean z) {
        Context context;
        try {
            String bannerUrl = "";
            if (b() != null) {
                if (TextUtils.isEmpty(b().getBannerHtml())) {
                    bannerUrl = b().getBannerUrl();
                } else {
                    bannerUrl = "file:////" + b().getBannerHtml();
                }
            }
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(str)) {
                bannerUrl = str;
            }
            bundle.putString("url", bannerUrl);
            bundle.putBoolean("shouldUseCustomClose", z);
            if (this.b != null && (context = this.b.get()) != null) {
                if (this.h != null && this.h.isShowing()) {
                    return;
                }
                this.h = new BannerExpandDialog(context, bundle, this.g);
                this.h.setCampaignList(this.e, this.c);
                this.h.show();
            }
            if (this.g != null) {
                this.g.a(true);
            }
            com.mbridge.msdk.mbbanner.common.d.a.a(this.e, b(), str);
        } catch (Throwable th) {
            s.b("BannerJSBridgeImpl", "expand", th);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.bridge.a, com.mbridge.msdk.mbjscommon.bridge.c
    public final void j(Object obj, String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            b.a(obj, "params is empty");
            return;
        }
        try {
            a(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.a("BannerJSBridgeImpl", th.getMessage());
        }
    }

    private static void a(Object obj, JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        if (jSONObject == null) {
            try {
                jSONObject2.put(CommandMessage.CODE, 1);
                jSONObject2.put("message", "params is null");
                h.a().a(obj, Base64.encodeToString(jSONObject2.toString().getBytes(), 2));
                return;
            } catch (JSONException e) {
                s.a("BannerJSBridgeImpl", e.getMessage());
                return;
            }
        }
        try {
            try {
                jSONObject2.put(CommandMessage.CODE, 0);
                jSONObject2.put("message", "");
                JSONArray jSONArray = jSONObject.getJSONArray("resource");
                if (jSONArray != null && jSONArray.length() > 0) {
                    JSONArray jSONArray2 = new JSONArray();
                    int length = jSONArray.length();
                    for (int i = 0; i < length; i++) {
                        JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                        String strOptString = jSONObject3.optString("ref", "");
                        int i2 = jSONObject3.getInt(Message.TYPE);
                        JSONObject jSONObject4 = new JSONObject();
                        if (i2 == 1 && !TextUtils.isEmpty(strOptString)) {
                            JSONObject jSONObject5 = new JSONObject();
                            p pVarA = t.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j())).a(strOptString);
                            if (pVarA != null) {
                                s.a("BannerJSBridgeImpl", "VideoBean not null");
                                jSONObject5.put(Message.TYPE, 1);
                                jSONObject5.put("videoDataLength", pVarA.c());
                                String strE = pVarA.e();
                                if (TextUtils.isEmpty(strE)) {
                                    s.a("BannerJSBridgeImpl", "VideoPath null");
                                    jSONObject5.put(FileDownloadModel.PATH, "");
                                    jSONObject5.put("path4Web", "");
                                } else {
                                    s.a("BannerJSBridgeImpl", "VideoPath not null");
                                    jSONObject5.put(FileDownloadModel.PATH, strE);
                                    jSONObject5.put("path4Web", strE);
                                }
                                if (pVarA.d() == 5) {
                                    jSONObject5.put("downloaded", 1);
                                } else {
                                    jSONObject5.put("downloaded", 0);
                                }
                                jSONObject4.put(strOptString, jSONObject5);
                                jSONArray2.put(jSONObject4);
                            } else {
                                s.a("BannerJSBridgeImpl", "VideoBean null");
                            }
                        } else if (i2 == 2 && !TextUtils.isEmpty(strOptString)) {
                            JSONObject jSONObject6 = new JSONObject();
                            jSONObject6.put(Message.TYPE, 2);
                            jSONObject6.put(FileDownloadModel.PATH, H5DownLoadManager.getInstance().getResAddress(strOptString) == null ? "" : H5DownLoadManager.getInstance().getH5ResAddress(strOptString));
                            jSONObject4.put(strOptString, jSONObject6);
                            jSONArray2.put(jSONObject4);
                        } else if (i2 == 3 && !TextUtils.isEmpty(strOptString)) {
                            String str = "";
                            try {
                                File file = new File(strOptString);
                                if (file.exists() && file.isFile() && file.canRead()) {
                                    s.a("BannerJSBridgeImpl", "getFileInfo Mraid file " + strOptString);
                                    str = "file:////" + strOptString;
                                }
                            } catch (Throwable th) {
                                if (MBridgeConstans.DEBUG) {
                                    th.printStackTrace();
                                }
                            }
                            JSONObject jSONObject7 = new JSONObject();
                            jSONObject7.put(Message.TYPE, 3);
                            jSONObject7.put(FileDownloadModel.PATH, str);
                            jSONObject4.put(strOptString, jSONObject7);
                            jSONArray2.put(jSONObject4);
                        } else if (i2 == 4 && !TextUtils.isEmpty(strOptString)) {
                            JSONObject jSONObject8 = new JSONObject();
                            jSONObject8.put(Message.TYPE, 4);
                            jSONObject8.put(FileDownloadModel.PATH, v.a(strOptString) == null ? "" : v.a(strOptString));
                            jSONObject4.put(strOptString, jSONObject8);
                            jSONArray2.put(jSONObject4);
                        }
                    }
                    jSONObject2.put("resource", jSONArray2);
                    h.a().a(obj, Base64.encodeToString(jSONObject2.toString().getBytes(), 2));
                    return;
                }
                try {
                    jSONObject2.put(CommandMessage.CODE, 1);
                    jSONObject2.put("message", "resource is null");
                    h.a().a(obj, Base64.encodeToString(jSONObject2.toString().getBytes(), 2));
                    return;
                } catch (JSONException e2) {
                    s.a("BannerJSBridgeImpl", e2.getMessage());
                    return;
                }
            } catch (Throwable th2) {
                jSONObject2.put(CommandMessage.CODE, 1);
                jSONObject2.put("message", th2.getLocalizedMessage());
                h.a().a(obj, Base64.encodeToString(jSONObject2.toString().getBytes(), 2));
                return;
            }
            jSONObject2.put(CommandMessage.CODE, 1);
            jSONObject2.put("message", th2.getLocalizedMessage());
            h.a().a(obj, Base64.encodeToString(jSONObject2.toString().getBytes(), 2));
            return;
        } catch (JSONException e3) {
            s.a("BannerJSBridgeImpl", e3.getMessage());
            return;
        }
    }
}
