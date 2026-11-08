package com.mbridge.msdk.video.bt.module;

import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import com.heytap.mcssdk.mode.CommandMessage;
import com.heytap.mcssdk.mode.Message;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.kwad.sdk.core.config.item.TipsConfigItem;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.db.f;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.RewardPlus;
import com.mbridge.msdk.foundation.tools.m;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView;
import com.mbridge.msdk.playercommon.exoplayer2.text.ttml.TtmlNode;
import com.mbridge.msdk.video.bt.module.a.c;
import com.mbridge.msdk.video.bt.module.b.h;
import com.mbridge.msdk.video.js.a.j;
import com.mbridge.msdk.video.js.b;
import com.mbridge.msdk.video.js.container.AbstractJSContainer;
import com.mbridge.msdk.videocommon.a;
import com.mbridge.msdk.videocommon.b.d;
import com.mbridge.msdk.videocommon.download.a;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBridgeBTContainer extends AbstractJSContainer implements b {
    private static final String a = "MBridgeBTContainer";
    private boolean A;
    private List<CampaignEx> B;
    private List<a> C;
    private com.mbridge.msdk.video.bt.module.a.a D;
    private h E;
    private c F;
    private String G;
    private String H;
    private boolean I;
    private int J;
    private boolean K;
    private int L;
    private String M;
    private int b;
    private int c;
    private FrameLayout d;
    private MBridgeBTLayout e;
    private WindVaneWebView f;
    private LayoutInflater g;
    private Context h;
    private boolean i;
    private boolean y;
    private String z;

    @Override // com.mbridge.msdk.video.js.d
    public void click(int i, String str) {
    }

    @Override // com.mbridge.msdk.video.js.d
    public void handlerH5Exception(int i, String str) {
    }

    public MBridgeBTContainer(Context context) {
        super(context);
        this.b = 0;
        this.c = 1;
        this.i = false;
        this.y = true;
        this.A = false;
        this.J = 1;
        init(context);
    }

    public MBridgeBTContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = 0;
        this.c = 1;
        this.i = false;
        this.y = true;
        this.A = false;
        this.J = 1;
        init(context);
    }

    public void init(Context context) {
        this.h = context;
        this.g = LayoutInflater.from(context);
    }

    public void onCreate() {
        WindVaneWebView windVaneWebViewA;
        try {
            int iFindLayout = findLayout("mbridge_bt_container");
            if (iFindLayout < 0) {
                a("mbridge_bt_container layout null");
                return;
            }
            this.d = (FrameLayout) this.g.inflate(iFindLayout, this);
            if (this.d == null) {
                a("ViewIds null");
                return;
            }
            String str = "";
            this.H = "";
            if (this.B != null && this.B.size() > 0) {
                CampaignEx campaignEx = this.B.get(0);
                String mof_template_url = campaignEx.getMof_template_url();
                this.H = campaignEx.getRequestId();
                str = mof_template_url;
            }
            a.C0146a c0146aA = com.mbridge.msdk.videocommon.a.a(this.k + "_" + this.H + "_" + str);
            if (c0146aA != null) {
                this.z = c0146aA.b();
                s.a(a, "get BT wraper.getTag = " + this.z);
                c0146aA.a("");
                windVaneWebViewA = c0146aA.a();
            } else {
                windVaneWebViewA = null;
            }
            this.f = windVaneWebViewA;
            com.mbridge.msdk.videocommon.a.b(this.k + "_" + this.H + "_" + str);
            if (this.f != null) {
                com.mbridge.msdk.video.js.factory.b bVar = new com.mbridge.msdk.video.js.factory.b(this.j, this, this.f);
                registerJsFactory(bVar);
                this.f.setApiManagerJSFactory(bVar);
                if (this.f.getParent() != null) {
                    a("preload template webview is null or load error");
                    return;
                }
                if (this.f.getObject() instanceof j) {
                    bVar.a((j) this.f.getObject());
                    if (this.f != null) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put(com.mbridge.msdk.foundation.same.a.r, w.d(getContext()));
                        try {
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("name", this.o.a());
                            jSONObject2.put(RewardPlus.AMOUNT, this.o.b());
                            jSONObject2.put("id", this.p);
                            jSONObject.put("userId", this.n);
                            jSONObject.put("reward", jSONObject2);
                            jSONObject.put("playVideoMute", this.q);
                            jSONObject.put(PushConstants.EXTRA, this.M);
                        } catch (JSONException e) {
                            s.a(a, e.getMessage());
                        } catch (Exception e2) {
                            s.a(a, e2.getMessage());
                        }
                        getJSNotifyProxy().a(jSONObject.toString());
                        getJSCommon().b(true);
                    }
                    ((com.mbridge.msdk.video.js.a.c) getJSCommon()).m.a();
                }
                this.f.setBackgroundColor(0);
                LinkedHashMap<String, View> linkedHashMapB = com.mbridge.msdk.video.bt.a.c.a().b(this.k, this.H);
                if (linkedHashMapB != null && linkedHashMapB.containsKey(this.z)) {
                    View view = linkedHashMapB.get(this.z);
                    if (view instanceof MBridgeBTLayout) {
                        this.e = (MBridgeBTLayout) view;
                        this.e.addView(this.f, 0, new FrameLayout.LayoutParams(-1, -1));
                        this.e.setTag(this.z);
                        linkedHashMapB.put(this.z, this.e);
                        for (View view2 : linkedHashMapB.values()) {
                            if (view2 instanceof MBridgeBTRootLayout) {
                                MBridgeBTRootLayout mBridgeBTRootLayout = (MBridgeBTRootLayout) view2;
                                this.G = mBridgeBTRootLayout.getInstanceId();
                                this.d.addView(mBridgeBTRootLayout, new FrameLayout.LayoutParams(-1, -1));
                            }
                        }
                        linkedHashMapB.remove(this.G);
                        linkedHashMapB.put(this.G, this);
                    }
                    com.mbridge.msdk.video.bt.a.c.a().a(this.k, this.q);
                    com.mbridge.msdk.video.bt.a.c.a().a(this.z, this.H);
                    com.mbridge.msdk.video.bt.a.c.a().a(this.G, this.H);
                    com.mbridge.msdk.video.bt.a.c.a().a(this.k + "_" + this.H, this.j);
                    try {
                        com.mbridge.msdk.foundation.same.f.b.a().execute(new Runnable() { // from class: com.mbridge.msdk.video.bt.module.MBridgeBTContainer.1
                            @Override // java.lang.Runnable
                            public final void run() {
                                if (MBridgeBTContainer.this.B == null || MBridgeBTContainer.this.B.size() <= 0) {
                                    return;
                                }
                                try {
                                    f.a(i.a(MBridgeBTContainer.this.getContext().getApplicationContext())).a(MBridgeBTContainer.this.k, MBridgeBTContainer.this.B);
                                    com.mbridge.msdk.videocommon.a.a.a().c(MBridgeBTContainer.this.l, ((CampaignEx) MBridgeBTContainer.this.B.get(0)).getAdType());
                                } catch (Exception unused) {
                                    s.a(MBridgeBTContainer.a, "remove campaign failed");
                                }
                            }
                        });
                    } catch (Throwable unused) {
                        s.a(a, "remove campaign failed");
                    }
                    if (this.B == null || this.B.size() <= 0) {
                        return;
                    }
                    a(this.m, this.B.get(0));
                    return;
                }
                a("big template webviewLayout is null");
                return;
            }
            a("big template webview is null");
        } catch (Throwable th) {
            a("onCreate exception " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.container.AbstractJSContainer
    protected final void a(String str) {
        if (this.E != null) {
            this.E.a(str);
        }
        super.a(str);
    }

    @Override // com.mbridge.msdk.video.js.container.AbstractJSContainer, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        try {
            LinkedHashMap<String, View> linkedHashMapB = com.mbridge.msdk.video.bt.a.c.a().b(this.k, this.H);
            if (linkedHashMapB == null || linkedHashMapB.size() <= 0) {
                return;
            }
            for (View view : linkedHashMapB.values()) {
                if (view instanceof MBTempContainer) {
                    ((MBTempContainer) view).onConfigurationChanged(configuration);
                } else if (view instanceof MBridgeBTWebView) {
                    ((MBridgeBTWebView) view).onConfigurationChanged(configuration);
                } else if (view instanceof MBridgeBTLayout) {
                    ((MBridgeBTLayout) view).onConfigurationChanged(configuration);
                }
            }
        } catch (Throwable th) {
            s.a(a, th.getMessage());
        }
    }

    @Override // com.mbridge.msdk.video.js.container.AbstractJSContainer
    public void onResume() {
        super.onResume();
        try {
            LinkedHashMap<String, View> linkedHashMapB = com.mbridge.msdk.video.bt.a.c.a().b(this.k, this.H);
            if (linkedHashMapB == null || linkedHashMapB.size() <= 0) {
                return;
            }
            for (View view : linkedHashMapB.values()) {
                if (view instanceof MBridgeBTVideoView) {
                    ((MBridgeBTVideoView) view).onResume();
                }
            }
        } catch (Throwable th) {
            s.a(a, th.getMessage());
        }
    }

    @Override // com.mbridge.msdk.video.js.container.AbstractJSContainer
    public void onStop() {
        super.onStop();
        try {
            LinkedHashMap<String, View> linkedHashMapB = com.mbridge.msdk.video.bt.a.c.a().b(this.k, this.H);
            if (linkedHashMapB == null || linkedHashMapB.size() <= 0) {
                return;
            }
            for (View view : linkedHashMapB.values()) {
                if (view instanceof MBridgeBTVideoView) {
                    ((MBridgeBTVideoView) view).onStop();
                }
            }
        } catch (Throwable th) {
            s.a(a, th.getMessage());
        }
    }

    public void onBackPressed() {
        try {
            LinkedHashMap<String, View> linkedHashMapB = com.mbridge.msdk.video.bt.a.c.a().b(this.k, this.H);
            if (linkedHashMapB == null || linkedHashMapB.size() <= 0) {
                return;
            }
            for (View view : linkedHashMapB.values()) {
                if (view instanceof MBTempContainer) {
                    ((MBTempContainer) view).onBackPressed();
                } else if (view instanceof MBridgeBTWebView) {
                    ((MBridgeBTWebView) view).onBackPressed();
                } else if (view instanceof MBridgeBTLayout) {
                    ((MBridgeBTLayout) view).onBackPressed();
                }
            }
        } catch (Throwable th) {
            s.a(a, th.getMessage());
        }
    }

    @Override // com.mbridge.msdk.video.js.container.AbstractJSContainer
    public void onPause() {
        super.onPause();
        try {
            LinkedHashMap<String, View> linkedHashMapB = com.mbridge.msdk.video.bt.a.c.a().b(this.k, this.H);
            if (linkedHashMapB == null || linkedHashMapB.size() <= 0) {
                return;
            }
            for (View view : linkedHashMapB.values()) {
                if (view instanceof MBridgeBTVideoView) {
                    ((MBridgeBTVideoView) view).onPause();
                }
            }
        } catch (Throwable th) {
            s.a(a, th.getMessage());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void onAdClose() {
        if (this.j != null) {
            this.j.finish();
        }
    }

    @Override // com.mbridge.msdk.video.js.container.AbstractJSContainer
    public void onDestroy() {
        if (this.A) {
            return;
        }
        this.A = true;
        super.onDestroy();
        com.mbridge.msdk.video.bt.a.c.a().e(this.k + "_" + this.H);
        try {
            if (this.f != null) {
                ViewGroup viewGroup = (ViewGroup) this.f.getParent();
                if (viewGroup != null) {
                    viewGroup.removeAllViews();
                }
                this.f.clearWebView();
                this.f.release();
            }
            if (this.F != null) {
                this.F = null;
            }
            if (this.D != null) {
                this.D = null;
            }
            if (this.h != null) {
                this.h = null;
            }
            if (this.B != null && this.B.size() > 0) {
                for (CampaignEx campaignEx : this.B) {
                    if (campaignEx != null && campaignEx.getRewardTemplateMode() != null) {
                        com.mbridge.msdk.videocommon.a.b(this.k + "_" + campaignEx.getRequestId() + "_" + campaignEx.getRewardTemplateMode().d());
                    }
                }
            }
            com.mbridge.msdk.video.bt.a.c.a().g(this.z);
            com.mbridge.msdk.video.bt.a.c.a().h(this.k);
            com.mbridge.msdk.video.bt.a.c.a().b(this.k, this.H).remove(this.z);
            com.mbridge.msdk.video.bt.a.c.a().b(this.k, this.H).remove(this.G);
            com.mbridge.msdk.video.bt.a.c.a().b(this.k, this.H).clear();
        } catch (Throwable th) {
            s.a(a, th.getMessage());
        }
    }

    @Override // com.mbridge.msdk.video.js.b
    public void reactDeveloper(Object obj, String str) {
        com.mbridge.msdk.videocommon.b.c cVarY;
        if (this.D != null && !TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                int iOptInt = jSONObject.optInt(Message.TYPE);
                int iOptInt2 = jSONObject.optInt("hit");
                String strOptString = jSONObject.optString("unitId", getUnitId());
                String strOptString2 = jSONObject.optString(MBridgeConstans.PLACEMENT_ID, getPlacementId());
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
                CampaignEx campaignEx = this.B.get(0);
                if (iOptInt == 1) {
                    boolean zOptBoolean = jSONObjectOptJSONObject.optBoolean("expired");
                    if (campaignEx != null) {
                        if (zOptBoolean) {
                            campaignEx.setSpareOfferFlag(1);
                        } else {
                            campaignEx.setSpareOfferFlag(0);
                        }
                    }
                    this.I = isNativeKilledCallback(campaignEx);
                }
                switch (iOptInt) {
                    case 1:
                        if (this.I) {
                            if (!a(1)) {
                                this.D.a();
                            }
                        } else if (iOptInt2 != this.J) {
                            this.D.a();
                        }
                        this.D.a(2, strOptString2, strOptString);
                        break;
                    case 2:
                        JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject(CampaignEx.JSON_NATIVE_VIDEO_ERROR);
                        String strOptString3 = jSONObjectOptJSONObject2 != null ? jSONObjectOptJSONObject2.optString(RemoteMessageConst.MessageBody.MSG) : "";
                        if (TextUtils.isEmpty(strOptString3)) {
                            strOptString3 = jSONObjectOptJSONObject.optString(CampaignEx.JSON_NATIVE_VIDEO_ERROR);
                        }
                        if (this.I) {
                            if (!a(1)) {
                                this.D.a(strOptString3);
                            }
                        } else if (iOptInt2 != this.J) {
                            this.D.a(strOptString3);
                        }
                        this.D.a(4, strOptString2, strOptString);
                        break;
                    case 3:
                        if (this.I) {
                            if (!a(2)) {
                                this.D.a(strOptString2, strOptString);
                            }
                        } else if (iOptInt2 != this.J) {
                            this.D.a(strOptString2, strOptString);
                        }
                        this.D.a(5, strOptString2, strOptString);
                        break;
                    case 4:
                        if (this.I) {
                            if (!a(3)) {
                                this.D.b(strOptString2, strOptString);
                            }
                        } else if (iOptInt2 != this.J) {
                            this.D.b(strOptString2, strOptString);
                        }
                        this.D.a(6, strOptString2, strOptString);
                        break;
                    case 5:
                        boolean zOptBoolean2 = jSONObject.optBoolean("isAutoClick");
                        if (this.I) {
                            if (!a(4) && !a(zOptBoolean2)) {
                                this.D.a(zOptBoolean2, strOptString2, strOptString);
                                break;
                            }
                        } else if (iOptInt2 != this.J) {
                            this.D.a(zOptBoolean2, strOptString2, strOptString);
                            break;
                        }
                        break;
                    case 6:
                        boolean z = jSONObjectOptJSONObject.optInt("convert") == 1;
                        JSONObject jSONObjectOptJSONObject3 = jSONObjectOptJSONObject.optJSONObject("reward");
                        CampaignEx campaignWithBackData = CampaignEx.parseCampaignWithBackData(jSONObjectOptJSONObject.optJSONObject("campaign"));
                        d dVarA = d.a(jSONObjectOptJSONObject3);
                        if (dVarA == null) {
                            dVarA = this.o;
                        }
                        String strOptString4 = jSONObjectOptJSONObject.optString(PushConstants.EXTRA);
                        if (!TextUtils.isEmpty(strOptString4)) {
                            this.M = strOptString4;
                        }
                        this.D.a(7, strOptString2, strOptString);
                        if (this.I) {
                            if (!a(5)) {
                                if (((this.m == null || (cVarY = this.m.y()) == null) ? 1 : cVarY.a()) == 0 && a(1)) {
                                    this.D.a("");
                                }
                                if (this.r && (this.t == com.mbridge.msdk.foundation.same.a.v || this.t == com.mbridge.msdk.foundation.same.a.w)) {
                                    this.D.a(this.K, this.L);
                                }
                                if (!z) {
                                    dVarA.a(0);
                                }
                                this.D.a(z, dVarA);
                                s.a(a, "sendToServerRewardInfo");
                                if (!this.r && z) {
                                    if (campaignWithBackData != null) {
                                        com.mbridge.msdk.video.module.b.a.a(campaignWithBackData, dVarA, strOptString, this.n, this.M);
                                        break;
                                    } else {
                                        com.mbridge.msdk.video.module.b.a.a(campaignEx, dVarA, strOptString, this.n, this.M);
                                        break;
                                    }
                                }
                            }
                        } else if (iOptInt2 != this.J) {
                            if (this.r && (this.t == com.mbridge.msdk.foundation.same.a.v || this.t == com.mbridge.msdk.foundation.same.a.w)) {
                                this.D.a(this.K, this.L);
                            }
                            if (!z) {
                                dVarA.a(0);
                            }
                            this.D.a(z, dVarA);
                            s.a(a, "sendToServerRewardInfo");
                            if (!this.r && z) {
                                if (campaignWithBackData != null) {
                                    com.mbridge.msdk.video.module.b.a.a(campaignWithBackData, dVarA, strOptString, this.n, this.M);
                                    break;
                                } else {
                                    com.mbridge.msdk.video.module.b.a.a(campaignEx, dVarA, strOptString, this.n, this.M);
                                    break;
                                }
                            }
                        }
                        break;
                }
                a(obj);
                return;
            } catch (JSONException e) {
                a(obj, e.getMessage());
                s.a(a, e.getMessage());
                return;
            }
        }
        a(obj, "listener is null");
    }

    public void appendSubView(MBridgeBTContainer mBridgeBTContainer, MBTempContainer mBTempContainer, JSONObject jSONObject) {
        try {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            if (jSONObject != null) {
                Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
                int iOptInt = jSONObject.optInt(TtmlNode.LEFT, -999);
                int iOptInt2 = jSONObject.optInt("top", -999);
                int iOptInt3 = jSONObject.optInt(TtmlNode.RIGHT, -999);
                int iOptInt4 = jSONObject.optInt(TipsConfigItem.TipConfigData.BOTTOM, -999);
                if (iOptInt != -999 && contextJ != null) {
                    layoutParams.leftMargin = w.b(contextJ, iOptInt);
                }
                if (iOptInt2 != -999 && contextJ != null) {
                    layoutParams.topMargin = w.b(contextJ, iOptInt2);
                }
                if (iOptInt3 != -999 && contextJ != null) {
                    layoutParams.rightMargin = w.b(contextJ, iOptInt3);
                }
                if (iOptInt4 != -999 && contextJ != null) {
                    layoutParams.bottomMargin = w.b(contextJ, iOptInt4);
                }
                int iOptInt5 = jSONObject.optInt("width");
                int iOptInt6 = jSONObject.optInt("height");
                if (iOptInt5 > 0) {
                    layoutParams.width = iOptInt5;
                }
                if (iOptInt6 > 0) {
                    layoutParams.height = iOptInt6;
                }
            }
            mBridgeBTContainer.addView(mBTempContainer, layoutParams);
            mBTempContainer.setActivity(this.j);
            mBTempContainer.setMute(this.q);
            mBTempContainer.setBidCampaign(this.i);
            mBTempContainer.setIV(this.r);
            mBTempContainer.setBigOffer(this.y);
            mBTempContainer.setIVRewardEnable(this.t, this.u, this.v);
            mBTempContainer.setShowRewardListener(this.E);
            mBTempContainer.setCampaignDownLoadTask(c(mBTempContainer.getCampaign()));
            if (this.F == null) {
                this.F = new c() { // from class: com.mbridge.msdk.video.bt.module.MBridgeBTContainer.2
                    @Override // com.mbridge.msdk.video.bt.module.a.c
                    public final void a(String str) throws JSONException {
                        if (MBridgeBTContainer.this.f != null) {
                            JSONObject jSONObject2 = new JSONObject();
                            try {
                                jSONObject2.put("id", str);
                                JSONObject jSONObject3 = new JSONObject();
                                jSONObject3.put(MBridgeConstans.PLACEMENT_ID, MBridgeBTContainer.this.l);
                                jSONObject3.put("unitId", MBridgeBTContainer.this.k);
                                jSONObject2.put("data", jSONObject3);
                                s.a(MBridgeBTContainer.a, " BT Call H5 onAdShow " + jSONObject2.toString());
                            } catch (JSONException e) {
                                s.a(MBridgeBTContainer.a, e.getMessage());
                            }
                            com.mbridge.msdk.mbjscommon.windvane.h.a().a((WebView) MBridgeBTContainer.this.f, "onSubPlayTemplateViewPlayStart", Base64.encodeToString(jSONObject2.toString().getBytes(), 2));
                        }
                    }

                    @Override // com.mbridge.msdk.video.bt.module.a.c
                    public final void a(String str, boolean z, d dVar) throws JSONException {
                        if (MBridgeBTContainer.this.f != null) {
                            JSONObject jSONObject2 = new JSONObject();
                            try {
                                jSONObject2.put("id", str);
                                JSONObject jSONObject3 = new JSONObject();
                                if (dVar != null) {
                                    JSONObject jSONObject4 = new JSONObject();
                                    jSONObject4.put("name", dVar.a());
                                    jSONObject4.put(RewardPlus.AMOUNT, dVar.b());
                                    jSONObject3.put("reward", jSONObject4);
                                }
                                jSONObject3.put("isComplete", z);
                                jSONObject2.put("data", jSONObject3);
                                s.a(MBridgeBTContainer.a, " BT Call H5 onAdClose " + jSONObject2.toString());
                            } catch (JSONException e) {
                                s.a(MBridgeBTContainer.a, e.getMessage());
                            }
                            com.mbridge.msdk.mbjscommon.windvane.h.a().a((WebView) MBridgeBTContainer.this.f, "onSubPlayTemplateViewCloseBtnClicked", Base64.encodeToString(jSONObject2.toString().getBytes(), 2));
                            com.mbridge.msdk.mbjscommon.windvane.h.a().a((WebView) MBridgeBTContainer.this.f, "onSubPlayTemplateViewDismissed", Base64.encodeToString(jSONObject2.toString().getBytes(), 2));
                        }
                    }

                    @Override // com.mbridge.msdk.video.bt.module.a.c
                    public final void a(boolean z, int i) {
                        MBridgeBTContainer.this.K = z;
                        MBridgeBTContainer.this.L = i;
                    }

                    @Override // com.mbridge.msdk.video.bt.module.a.c
                    public final void a(String str, String str2) throws JSONException {
                        if (MBridgeBTContainer.this.f != null) {
                            JSONObject jSONObject2 = new JSONObject();
                            try {
                                jSONObject2.put("id", str);
                                JSONObject jSONObject3 = new JSONObject();
                                jSONObject3.put(MBridgeConstans.PLACEMENT_ID, MBridgeBTContainer.this.l);
                                jSONObject3.put("unitId", MBridgeBTContainer.this.k);
                                jSONObject3.put(CampaignEx.JSON_NATIVE_VIDEO_ERROR, str2);
                                jSONObject2.put("data", jSONObject3);
                                s.a(MBridgeBTContainer.a, " BT Call H5 onShowFail " + jSONObject2.toString());
                            } catch (JSONException e) {
                                s.a(MBridgeBTContainer.a, e.getMessage());
                            }
                            com.mbridge.msdk.mbjscommon.windvane.h.a().a((WebView) MBridgeBTContainer.this.f, "onSubPlayTemplateViewPlayFailed", Base64.encodeToString(jSONObject2.toString().getBytes(), 2));
                        }
                    }

                    @Override // com.mbridge.msdk.video.bt.module.a.c
                    public final void a(String str, String str2, String str3) throws JSONException {
                        if (MBridgeBTContainer.this.f != null) {
                            JSONObject jSONObject2 = new JSONObject();
                            try {
                                jSONObject2.put("id", str);
                                JSONObject jSONObject3 = new JSONObject();
                                jSONObject3.put(MBridgeConstans.PLACEMENT_ID, str2);
                                jSONObject3.put("unitId", str3);
                                jSONObject2.put("data", jSONObject3);
                                s.a(MBridgeBTContainer.a, " BT Call H5 onVideoAdClicked " + jSONObject2.toString());
                            } catch (JSONException e) {
                                s.a(MBridgeBTContainer.a, e.getMessage());
                            }
                            com.mbridge.msdk.mbjscommon.windvane.h.a().a((WebView) MBridgeBTContainer.this.f, "onSubPlayTemplateViewClicked", Base64.encodeToString(jSONObject2.toString().getBytes(), 2));
                        }
                    }

                    @Override // com.mbridge.msdk.video.bt.module.a.c
                    public final void b(String str, String str2, String str3) throws JSONException {
                        if (MBridgeBTContainer.this.f != null) {
                            JSONObject jSONObject2 = new JSONObject();
                            try {
                                jSONObject2.put("id", str);
                                JSONObject jSONObject3 = new JSONObject();
                                jSONObject3.put("convert", true);
                                jSONObject3.put(MBridgeConstans.PLACEMENT_ID, str2);
                                jSONObject3.put("unitId", str3);
                                jSONObject2.put("data", jSONObject3);
                                s.a(MBridgeBTContainer.a, " BT Call H5 onVideoComplete " + jSONObject2.toString());
                            } catch (JSONException e) {
                                s.a(MBridgeBTContainer.a, e.getMessage());
                            }
                            com.mbridge.msdk.mbjscommon.windvane.h.a().a((WebView) MBridgeBTContainer.this.f, "onSubPlayTemplateViewPlayCompleted", Base64.encodeToString(jSONObject2.toString().getBytes(), 2));
                        }
                    }

                    @Override // com.mbridge.msdk.video.bt.module.a.c
                    public final void c(String str, String str2, String str3) throws JSONException {
                        if (MBridgeBTContainer.this.f != null) {
                            JSONObject jSONObject2 = new JSONObject();
                            try {
                                jSONObject2.put("id", str);
                                JSONObject jSONObject3 = new JSONObject();
                                jSONObject3.put(MBridgeConstans.PLACEMENT_ID, str2);
                                jSONObject3.put("unitId", str3);
                                jSONObject2.put("data", jSONObject3);
                                s.a(MBridgeBTContainer.a, " BT Call H5 onEndcardShow " + jSONObject2.toString());
                            } catch (JSONException e) {
                                s.a(MBridgeBTContainer.a, e.getMessage());
                            }
                            com.mbridge.msdk.mbjscommon.windvane.h.a().a((WebView) MBridgeBTContainer.this.f, "onSubPlayTemplateViewEndcardShowSuccess", Base64.encodeToString(jSONObject2.toString().getBytes(), 2));
                        }
                    }

                    @Override // com.mbridge.msdk.video.bt.module.a.c
                    public final void a(String str, int i, String str2, String str3) {
                        if (MBridgeBTContainer.this.E != null) {
                            MBridgeBTContainer.this.E.a(i, str2, str3);
                        }
                    }
                };
            }
            mBTempContainer.setMBridgeTempCallback(this.F);
            mBTempContainer.setH5Cbp(getJSCommon().a());
            mBTempContainer.setWebViewFront(getJSCommon().b());
            mBTempContainer.init(this.h);
            mBTempContainer.onCreate();
        } catch (Throwable th) {
            s.a(a, th.getMessage());
        }
    }

    private com.mbridge.msdk.videocommon.download.a c(CampaignEx campaignEx) {
        if (this.C == null || campaignEx == null) {
            return null;
        }
        for (com.mbridge.msdk.videocommon.download.a aVar : this.C) {
            if (aVar.g().getId().equals(campaignEx.getId())) {
                s.a(a, "tempContainer task initSuccess");
                return aVar;
            }
        }
        return null;
    }

    public void broadcast(String str, JSONObject jSONObject) throws JSONException {
        if (this.f != null) {
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(CommandMessage.CODE, this.b);
                jSONObject2.put("id", this.z);
                jSONObject2.put("eventName", str);
                jSONObject2.put("data", jSONObject);
                com.mbridge.msdk.mbjscommon.windvane.h.a().a((WebView) this.f, "broadcast", Base64.encodeToString(jSONObject2.toString().getBytes(), 2));
            } catch (Exception unused) {
                com.mbridge.msdk.video.bt.a.c.a().a((WebView) this.f, "broadcast", this.z);
            }
        }
    }

    public int findID(String str) {
        return m.a(getContext(), str, "id");
    }

    public int findLayout(String str) {
        return m.a(getContext(), str, TtmlNode.TAG_LAYOUT);
    }

    public void setBTContainerCallback(com.mbridge.msdk.video.bt.module.a.a aVar) {
        this.D = aVar;
    }

    public void setCampaigns(List<CampaignEx> list) {
        this.B = list;
    }

    public void setCampaignDownLoadTasks(List<com.mbridge.msdk.videocommon.download.a> list) {
        this.C = list;
    }

    public void setJSFactory(com.mbridge.msdk.video.js.factory.b bVar) {
        this.x = bVar;
    }

    public void setShowRewardVideoListener(h hVar) {
        this.E = hVar;
    }

    public boolean isNativeKilledCallback(CampaignEx campaignEx) {
        if (getJSCommon().a() != 1 && campaignEx != null) {
            if (campaignEx.getSpareOfferFlag() == 1) {
                if (this.m != null) {
                    if (this.m.C() == 1) {
                        campaignEx.setCbt(1);
                        return true;
                    }
                    campaignEx.setCbt(0);
                    return false;
                }
            } else {
                if (campaignEx.isBidCampaign()) {
                    campaignEx.setCbt(0);
                    return false;
                }
                int iD = this.m.d();
                campaignEx.setCbt(iD);
                return iD == 1;
            }
        }
        return false;
    }

    private boolean a(int i) {
        com.mbridge.msdk.videocommon.b.c cVarY;
        if (this.m != null && (cVarY = this.m.y()) != null) {
            if (cVarY.a() == 0) {
                return i <= 4;
            }
            List<Integer> listB = cVarY.b();
            if (listB != null) {
                return listB.contains(Integer.valueOf(i));
            }
        }
        return i <= 4;
    }

    private boolean a(boolean z) {
        try {
        } catch (Throwable th) {
            s.b(a, "", th);
        }
        if (this.m == null) {
            return false;
        }
        switch (this.m.A()) {
            case 1:
                return z;
            case 2:
                return z && c();
            case 3:
                return c();
            default:
                return false;
        }
    }

    private boolean c() {
        try {
            if (this.m == null) {
                return false;
            }
            double dB = this.m.B();
            if (dB == 1.0d) {
                return false;
            }
            return new Random().nextDouble() > dB;
        } catch (Throwable th) {
            s.b(a, "", th);
            return false;
        }
    }

    public void setNotchPadding(int i, int i2, int i3, int i4, int i5) {
        try {
            String strA = com.mbridge.msdk.foundation.tools.i.a(i, i2, i3, i4, i5);
            s.d(a, strA);
            if (this.f != null && (this.f.getObject() instanceof j) && !TextUtils.isEmpty(strA)) {
                ((j) this.f.getObject()).b(strA);
                com.mbridge.msdk.mbjscommon.windvane.h.a().a((WebView) this.f, "oncutoutfetched", Base64.encodeToString(strA.getBytes(), 0));
            }
            com.mbridge.msdk.video.bt.a.c.a().a(i, i2, i3, i4, i5);
            LinkedHashMap<String, View> linkedHashMapB = com.mbridge.msdk.video.bt.a.c.a().b(this.k, this.H);
            if (linkedHashMapB == null || linkedHashMapB.size() <= 0) {
                return;
            }
            for (View view : linkedHashMapB.values()) {
                if (view instanceof MBridgeBTVideoView) {
                    ((MBridgeBTVideoView) view).setNotchPadding(i2, i3, i4, i5);
                }
                if (view instanceof MBTempContainer) {
                    ((MBTempContainer) view).setNotchPadding(i, i2, i3, i4, i5);
                }
                if ((view instanceof WindVaneWebView) && !TextUtils.isEmpty(strA)) {
                    com.mbridge.msdk.mbjscommon.windvane.h.a().a(view, "oncutoutfetched", Base64.encodeToString(strA.getBytes(), 0));
                }
            }
        } catch (Throwable th) {
            s.a(a, th.getMessage());
        }
    }

    public void setDeveloperExtraData(String str) {
        this.M = str;
    }
}
