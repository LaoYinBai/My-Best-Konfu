package com.mbridge.msdk.video.bt.a;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.heytap.mcssdk.mode.CommandMessage;
import com.heytap.mcssdk.mode.Message;
import com.kwad.sdk.core.config.item.TipsConfigItem;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.db.m;
import com.mbridge.msdk.foundation.db.t;
import com.mbridge.msdk.foundation.download.download.H5DownLoadManager;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.RewardPlus;
import com.mbridge.msdk.foundation.entity.p;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.v;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView;
import com.mbridge.msdk.mbjscommon.windvane.h;
import com.mbridge.msdk.playercommon.exoplayer2.text.ttml.TtmlNode;
import com.mbridge.msdk.video.bt.module.BTBaseView;
import com.mbridge.msdk.video.bt.module.MBTempContainer;
import com.mbridge.msdk.video.bt.module.MBridgeBTContainer;
import com.mbridge.msdk.video.bt.module.MBridgeBTLayout;
import com.mbridge.msdk.video.bt.module.MBridgeBTNativeEC;
import com.mbridge.msdk.video.bt.module.MBridgeBTVideoView;
import com.mbridge.msdk.video.bt.module.MBridgeBTWebView;
import com.mbridge.msdk.video.js.a.j;
import com.mbridge.msdk.videocommon.b.d;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: OperateViews.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class c {
    private static ConcurrentHashMap<String, LinkedHashMap<String, View>> a = new ConcurrentHashMap<>();
    private static LinkedHashMap<String, String> b = new LinkedHashMap<>();
    private static LinkedHashMap<String, CampaignEx> c = new LinkedHashMap<>();
    private static LinkedHashMap<String, com.mbridge.msdk.videocommon.d.c> d = new LinkedHashMap<>();
    private static LinkedHashMap<String, String> e = new LinkedHashMap<>();
    private static LinkedHashMap<String, Integer> f = new LinkedHashMap<>();
    private static LinkedHashMap<String, Activity> g = new LinkedHashMap<>();
    private static volatile int h = 10000;
    private static int i = 0;
    private static int j = 1;
    private static String k = "";
    private static int l;
    private static int m;
    private static int n;
    private static int o;
    private static int p;

    private c() {
    }

    /* compiled from: OperateViews.java */
    private static class a {
        private static c a = new c();
    }

    public static c a() {
        return a.a;
    }

    public final String b() {
        h++;
        return String.valueOf(h);
    }

    public final CampaignEx a(String str) {
        if (c.containsKey(str)) {
            return c.get(str);
        }
        return null;
    }

    private String i(String str) {
        return b.containsKey(str) ? b.get(str) : "";
    }

    public final void a(String str, String str2) {
        b.put(str, str2);
    }

    public final com.mbridge.msdk.videocommon.d.c b(String str) {
        if (d.containsKey(str)) {
            return d.get(str);
        }
        return null;
    }

    public final String c(String str) {
        return e.containsKey(str) ? e.get(str) : "";
    }

    public final void a(String str, Activity activity) {
        g.put(str, activity);
    }

    public final Activity d(String str) {
        if (g.containsKey(str)) {
            return g.get(str);
        }
        return null;
    }

    public final void e(String str) {
        g.remove(str);
    }

    public final void a(String str, int i2) {
        f.put(str, Integer.valueOf(i2));
    }

    public final int f(String str) {
        if (f.containsKey(str)) {
            return f.get(str).intValue();
        }
        return 2;
    }

    public final void g(String str) {
        if (e.containsKey(str)) {
            e.remove(str);
        }
        if (d.containsKey(str)) {
            d.remove(str);
        }
        if (c.containsKey(str)) {
            c.remove(str);
        }
        if (b.containsKey(str)) {
            b.remove(str);
        }
    }

    public final void h(String str) {
        if (f.containsKey(str)) {
            f.remove(str);
        }
    }

    public final synchronized LinkedHashMap<String, View> b(String str, String str2) {
        if (a.containsKey(str + "_" + str2)) {
            return a.get(str + "_" + str2);
        }
        LinkedHashMap<String, View> linkedHashMap = new LinkedHashMap<>();
        a.put(str + "_" + str2, linkedHashMap);
        return linkedHashMap;
    }

    private void c(Object obj, String str) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CommandMessage.CODE, j);
            jSONObject.put("message", str);
            jSONObject.put("data", new JSONObject());
            h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (Exception e2) {
            s.a("OperateViews", e2.getMessage());
        }
    }

    public final void a(WebView webView, String str) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CommandMessage.CODE, j);
            jSONObject.put("message", str);
            jSONObject.put("data", new JSONObject());
            h.a().a(webView, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (Exception e2) {
            s.a("OperateViews", e2.getMessage());
        }
    }

    public final void a(Object obj, String str) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CommandMessage.CODE, i);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("id", str);
            jSONObject.put("data", jSONObject2);
            h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (Exception e2) {
            c(obj, e2.getMessage());
            s.a("OperateViews", e2.getMessage());
        }
    }

    private void a(Object obj, String str, String str2) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CommandMessage.CODE, i);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("id", str2);
            jSONObject.put("data", jSONObject2);
            h.a().a(obj, str, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (Exception e2) {
            c(obj, e2.getMessage());
            s.a("OperateViews", e2.getMessage());
        }
    }

    public final void a(WebView webView, String str, String str2) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CommandMessage.CODE, i);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("id", str2);
            jSONObject.put("data", jSONObject2);
            h.a().a(webView, str, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (Exception e2) {
            a(webView, e2.getMessage());
            s.a("OperateViews", e2.getMessage());
        }
    }

    public final void a(final Object obj, JSONObject jSONObject) throws JSONException {
        final Rect rect;
        final int i2;
        final int iOptInt;
        try {
            final String strOptString = jSONObject.optString("unitId");
            if (TextUtils.isEmpty(strOptString)) {
                c(obj, "unitId is empty");
                return;
            }
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
            int iOptInt2 = jSONObjectOptJSONObject.optInt("delay", 0);
            final String strOptString2 = jSONObjectOptJSONObject.optString("fileURL");
            final String strOptString3 = jSONObjectOptJSONObject.optString("filePath");
            final String strOptString4 = jSONObjectOptJSONObject.optString("html");
            JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("rect");
            Rect rect2 = new Rect(-999, -999, -999, -999);
            if (jSONObjectOptJSONObject2 != null) {
                Rect rect3 = new Rect(jSONObjectOptJSONObject2.optInt(TtmlNode.LEFT, -999), jSONObjectOptJSONObject2.optInt("top", -999), jSONObjectOptJSONObject2.optInt(TtmlNode.RIGHT, -999), jSONObjectOptJSONObject2.optInt(TipsConfigItem.TipConfigData.BOTTOM, -999));
                int iOptInt3 = jSONObjectOptJSONObject2.optInt("width");
                iOptInt = jSONObjectOptJSONObject2.optInt("height");
                rect = rect3;
                i2 = iOptInt3;
            } else {
                rect = rect2;
                i2 = 0;
                iOptInt = 0;
            }
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.mbridge.msdk.video.bt.a.c.1
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    WindVaneWebView windVaneWebView;
                    String rid = "";
                    com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                    if (aVar.a != null) {
                        WindVaneWebView windVaneWebView2 = aVar.a;
                        windVaneWebView = windVaneWebView2;
                        rid = windVaneWebView2.getRid();
                    } else {
                        windVaneWebView = null;
                    }
                    LinkedHashMap<String, View> linkedHashMapB = c.this.b(strOptString, rid);
                    String strB = c.this.b();
                    c.b.put(strB, rid);
                    MBridgeBTWebView mBridgeBTWebView = new MBridgeBTWebView(com.mbridge.msdk.foundation.controller.a.f().j());
                    linkedHashMapB.put(strB, mBridgeBTWebView);
                    mBridgeBTWebView.setInstanceId(strB);
                    mBridgeBTWebView.setUnitId(strOptString);
                    mBridgeBTWebView.setFileURL(strOptString2);
                    mBridgeBTWebView.setFilePath(strOptString3);
                    mBridgeBTWebView.setHtml(strOptString4);
                    mBridgeBTWebView.setRect(rect);
                    mBridgeBTWebView.setWebViewRid(rid);
                    mBridgeBTWebView.setCreateWebView(windVaneWebView);
                    if (i2 > 0 || iOptInt > 0) {
                        mBridgeBTWebView.setLayout(i2, iOptInt);
                    }
                    mBridgeBTWebView.preload();
                    c.this.a(obj, strB);
                    s.a("OperateViews", "createWebview instanceId = " + strB);
                }
            }, iOptInt2);
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "createWebview failed：" + th.getMessage());
        }
    }

    public final void b(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            String strOptString = jSONObject.optString("unitId");
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
            if (jSONObjectOptJSONObject != null && !TextUtils.isEmpty(strOptString)) {
                String rid = "";
                CampaignEx campaignWithBackData = CampaignEx.parseCampaignWithBackData(jSONObjectOptJSONObject.optJSONObject("campaign"));
                if (campaignWithBackData != null && !TextUtils.isEmpty(strOptString)) {
                    campaignWithBackData.setCampaignUnitId(strOptString);
                    rid = campaignWithBackData.getRequestId();
                }
                int iOptInt = jSONObjectOptJSONObject.optInt("show_time", 0);
                int iOptInt2 = jSONObjectOptJSONObject.optInt("show_mute", 0);
                int iOptInt3 = jSONObjectOptJSONObject.optInt("show_close", 0);
                int iOptInt4 = jSONObjectOptJSONObject.optInt("orientation", 1);
                MBridgeBTVideoView mBridgeBTVideoView = new MBridgeBTVideoView(com.mbridge.msdk.foundation.controller.a.f().j());
                mBridgeBTVideoView.setCampaign(campaignWithBackData);
                mBridgeBTVideoView.setUnitId(strOptString);
                mBridgeBTVideoView.setShowMute(iOptInt2);
                mBridgeBTVideoView.setShowTime(iOptInt);
                mBridgeBTVideoView.setShowClose(iOptInt3);
                mBridgeBTVideoView.setOrientation(iOptInt4);
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                if (aVar.a != null) {
                    WindVaneWebView windVaneWebView = aVar.a;
                    rid = windVaneWebView.getRid();
                    mBridgeBTVideoView.setCreateWebView(windVaneWebView);
                }
                LinkedHashMap<String, View> linkedHashMapB = b(strOptString, rid);
                String strB = b();
                b.put(strB, rid);
                mBridgeBTVideoView.setInstanceId(strB);
                linkedHashMapB.put(strB, mBridgeBTVideoView);
                mBridgeBTVideoView.preLoadData();
                if (campaignWithBackData == null) {
                    c(obj, "campaign is null");
                    s.a("OperateViews", "createPlayerView failed");
                } else {
                    a(obj, strB);
                    s.a("OperateViews", "createPlayerView instanceId = " + strB);
                }
                com.mbridge.msdk.video.bt.a.a.a().a(strB, mBridgeBTVideoView);
                return;
            }
            c(obj, "unitId or data is empty");
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "createPlayerView failed：" + th.getMessage());
        }
    }

    public final void c(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            String strOptString = jSONObject.optString("unitId");
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
            if (jSONObjectOptJSONObject != null && !TextUtils.isEmpty(strOptString)) {
                int iOptInt = jSONObjectOptJSONObject.optInt(CampaignEx.JSON_NATIVE_VIDEO_MUTE);
                String rid = "";
                CampaignEx campaignWithBackData = CampaignEx.parseCampaignWithBackData(jSONObjectOptJSONObject.optJSONObject("campaign"));
                if (campaignWithBackData != null && !TextUtils.isEmpty(strOptString)) {
                    campaignWithBackData.setCampaignUnitId(strOptString);
                    rid = campaignWithBackData.getRequestId();
                }
                com.mbridge.msdk.videocommon.d.c cVarA = com.mbridge.msdk.videocommon.d.c.a(jSONObjectOptJSONObject.optJSONObject("unitSetting"));
                if (cVarA != null) {
                    cVarA.a(strOptString);
                }
                String strOptString2 = jSONObjectOptJSONObject.optString("userId");
                if (TextUtils.isEmpty(rid)) {
                    com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                    if (aVar.a != null) {
                        rid = aVar.a.getRid();
                    }
                }
                LinkedHashMap<String, View> linkedHashMapB = b(strOptString, rid);
                String strB = b();
                b.put(strB, rid);
                MBTempContainer mBTempContainer = new MBTempContainer(com.mbridge.msdk.foundation.controller.a.f().j());
                mBTempContainer.setInstanceId(strB);
                mBTempContainer.setUnitId(strOptString);
                mBTempContainer.setCampaign(campaignWithBackData);
                mBTempContainer.setRewardUnitSetting(cVarA);
                mBTempContainer.setBigOffer(true);
                if (!TextUtils.isEmpty(strOptString2)) {
                    mBTempContainer.setUserId(strOptString2);
                }
                JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("reward");
                if (jSONObjectOptJSONObject2 != null) {
                    String strOptString3 = jSONObjectOptJSONObject2.optString("id");
                    d dVar = new d(jSONObjectOptJSONObject2.optString("name"), jSONObjectOptJSONObject2.optInt(RewardPlus.AMOUNT));
                    if (!TextUtils.isEmpty(strOptString3)) {
                        mBTempContainer.setRewardId(strOptString3);
                    }
                    if (!TextUtils.isEmpty(dVar.a())) {
                        mBTempContainer.setReward(dVar);
                    }
                }
                String strOptString4 = jSONObjectOptJSONObject.optString(PushConstants.EXTRA);
                if (!TextUtils.isEmpty(strOptString4)) {
                    mBTempContainer.setDeveloperExtraData(strOptString4);
                }
                mBTempContainer.setMute(iOptInt);
                linkedHashMapB.put(strB, mBTempContainer);
                e.put(strB, strOptString);
                c.put(strB, campaignWithBackData);
                d.put(strB, cVarA);
                a(obj, strB);
                s.a("OperateViews", "createSubPlayTemplateView instanceId = " + strB);
                return;
            }
            c(obj, "unitId or data is empty");
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "createSubPlayTemplateView failed：" + th.getMessage());
        }
    }

    public final void d(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("id");
            if (jSONObject.optJSONObject("data") != null && !TextUtils.isEmpty(strOptString)) {
                String strI = i(strOptString2);
                if (TextUtils.isEmpty(strI)) {
                    com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                    if (aVar.a != null) {
                        strI = aVar.a.getRid();
                    }
                }
                LinkedHashMap<String, View> linkedHashMapB = b(strOptString, strI);
                if (linkedHashMapB != null && linkedHashMapB.containsKey(strOptString2)) {
                    View view = linkedHashMapB.get(strOptString2);
                    linkedHashMapB.remove(strOptString2);
                    if (view != null && view.getParent() != null) {
                        ViewGroup viewGroup = (ViewGroup) view.getParent();
                        if (viewGroup != null) {
                            viewGroup.removeView(view);
                        }
                        if (view instanceof ViewGroup) {
                            ViewGroup viewGroup2 = (ViewGroup) view;
                            if (viewGroup2.getChildCount() > 0) {
                                int childCount = viewGroup2.getChildCount();
                                for (int i2 = 0; i2 < childCount; i2++) {
                                    View childAt = viewGroup2.getChildAt(i2);
                                    if (childAt instanceof MBridgeBTWebView) {
                                        linkedHashMapB.remove(((MBridgeBTWebView) childAt).getInstanceId());
                                        ((MBridgeBTWebView) childAt).onDestory();
                                    } else if (childAt instanceof MBridgeBTVideoView) {
                                        linkedHashMapB.remove(((MBridgeBTVideoView) childAt).getInstanceId());
                                        ((MBridgeBTVideoView) childAt).onDestory();
                                    } else if (childAt instanceof MBTempContainer) {
                                        linkedHashMapB.remove(((MBTempContainer) childAt).getInstanceId());
                                        ((MBTempContainer) childAt).onDestroy();
                                    }
                                }
                            }
                        }
                    }
                    if (view instanceof MBTempContainer) {
                        ((MBTempContainer) view).onDestroy();
                    }
                    if (view instanceof MBridgeBTWebView) {
                        ((MBridgeBTWebView) view).onDestory();
                    }
                    if (view instanceof MBridgeBTVideoView) {
                        ((MBridgeBTVideoView) view).onDestory();
                    }
                    a(obj, strOptString2);
                    a(obj, "onComponentDestroy", strOptString2);
                    s.a("OperateViews", "destroyComponent instanceId = " + strOptString2);
                    return;
                }
                c(obj, "unitId or instanceId not exist");
                s.a("OperateViews", "destroyComponent failed");
                return;
            }
            c(obj, "unidId or data is empty");
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "destroyComponent failed");
        }
    }

    public final void e(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            jSONObject.optString("unitId");
            String strOptString = jSONObject.optString("id");
            if (jSONObject.optJSONObject("data") == null) {
                c(obj, "data is empty");
            } else {
                a(obj, strOptString);
            }
        } catch (Throwable th) {
            c(obj, th.getMessage());
        }
    }

    public final void f(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("id");
            String strI = i(strOptString2);
            if (TextUtils.isEmpty(strI)) {
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                if (aVar.a != null) {
                    strI = aVar.a.getRid();
                }
            }
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
            if (jSONObjectOptJSONObject == null) {
                c(obj, "data is empty");
                return;
            }
            JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("rect");
            if (jSONObjectOptJSONObject2 == null) {
                c(obj, "rect not exist");
                return;
            }
            LinkedHashMap<String, View> linkedHashMapB = b(strOptString, strI);
            Rect rect = new Rect(jSONObjectOptJSONObject2.optInt(TtmlNode.LEFT, -999), jSONObjectOptJSONObject2.optInt("top", -999), jSONObjectOptJSONObject2.optInt(TtmlNode.RIGHT, -999), jSONObjectOptJSONObject2.optInt(TipsConfigItem.TipConfigData.BOTTOM, -999));
            int iOptInt = jSONObjectOptJSONObject2.optInt("width");
            int iOptInt2 = jSONObjectOptJSONObject2.optInt("height");
            if (linkedHashMapB.containsKey(strOptString2)) {
                View view = linkedHashMapB.get(strOptString2);
                if (view != null) {
                    if (view.getParent() != null) {
                        view.setLayoutParams(a(view.getLayoutParams(), rect, iOptInt, iOptInt2));
                        view.requestLayout();
                    } else {
                        if (view instanceof MBridgeBTWebView) {
                            ((MBridgeBTWebView) view).setRect(rect);
                            ((MBridgeBTWebView) view).setLayout(iOptInt, iOptInt2);
                        }
                        if (view instanceof MBridgeBTVideoView) {
                            ((MBridgeBTVideoView) view).setRect(rect);
                            ((MBridgeBTVideoView) view).setLayout(iOptInt, iOptInt2);
                        }
                    }
                    a(obj, strOptString2);
                    a(obj, "onViewRectChanged", strOptString2);
                    s.a("OperateViews", "setViewRect instanceId = " + strOptString2);
                    return;
                }
                c(obj, "view is null");
                s.a("OperateViews", "setViewRect failed: view is null");
                return;
            }
            c(obj, "instanceId not exist");
            s.a("OperateViews", "setViewRect failed: instanceId not exist");
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "setViewRect failed: " + th.getMessage());
        }
    }

    private ViewGroup.LayoutParams a(ViewGroup.LayoutParams layoutParams, Rect rect, int i2, int i3) {
        if (rect == null) {
            rect = new Rect(-999, -999, -999, -999);
        }
        Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
        if (contextJ == null) {
            return layoutParams;
        }
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
            if (rect.left != -999) {
                layoutParams2.leftMargin = w.b(contextJ, rect.left);
            }
            if (rect.top != -999) {
                layoutParams2.topMargin = w.b(contextJ, rect.top);
            }
            if (rect.right != -999) {
                layoutParams2.rightMargin = w.b(contextJ, rect.right);
            }
            if (rect.bottom != -999) {
                layoutParams2.bottomMargin = w.b(contextJ, rect.bottom);
            }
            if (i2 > 0) {
                layoutParams2.width = w.b(contextJ, i2);
            }
            if (i3 > 0) {
                layoutParams2.height = w.b(contextJ, i3);
            }
            return layoutParams2;
        }
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -1);
            if (rect.left != -999) {
                layoutParams3.leftMargin = w.b(contextJ, rect.left);
            }
            if (rect.top != -999) {
                layoutParams3.topMargin = w.b(contextJ, rect.top);
            }
            if (rect.right != -999) {
                layoutParams3.rightMargin = w.b(contextJ, rect.right);
            }
            if (rect.bottom != -999) {
                layoutParams3.bottomMargin = w.b(contextJ, rect.bottom);
            }
            if (i2 > 0) {
                layoutParams3.width = w.b(contextJ, i2);
            }
            if (i3 > 0) {
                layoutParams3.height = w.b(contextJ, i3);
            }
            return layoutParams3;
        }
        if (!(layoutParams instanceof LinearLayout.LayoutParams)) {
            return layoutParams;
        }
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -1);
        if (rect.left != -999) {
            layoutParams4.leftMargin = w.b(contextJ, rect.left);
        }
        if (rect.top != -999) {
            layoutParams4.topMargin = w.b(contextJ, rect.top);
        }
        if (rect.right != -999) {
            layoutParams4.rightMargin = w.b(contextJ, rect.right);
        }
        if (rect.bottom != -999) {
            layoutParams4.bottomMargin = w.b(contextJ, rect.bottom);
        }
        if (i2 > 0) {
            layoutParams4.width = w.b(contextJ, i2);
        }
        if (i3 > 0) {
            layoutParams4.height = w.b(contextJ, i3);
        }
        return layoutParams4;
    }

    public final void g(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("id");
            String strI = i(strOptString2);
            if (TextUtils.isEmpty(strI)) {
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                if (aVar.a != null) {
                    strI = aVar.a.getRid();
                }
            }
            if (jSONObject.optJSONObject("data") == null) {
                c(obj, "data is empty");
                return;
            }
            LinkedHashMap<String, View> linkedHashMapB = b(strOptString, strI);
            if (linkedHashMapB.containsKey(strOptString2)) {
                View view = linkedHashMapB.get(strOptString2);
                if (view != null && view.getParent() != null) {
                    ViewGroup viewGroup = (ViewGroup) view.getParent();
                    if (viewGroup != null) {
                        viewGroup.removeView(view);
                        a(obj, strOptString2);
                        a(obj, "onRemoveFromView", strOptString2);
                        s.a("OperateViews", "removeFromSuperView instanceId = " + strOptString2);
                        return;
                    }
                    c(obj, "parent is null");
                    s.a("OperateViews", "removeFromSuperView failed: parent is null instanceId = " + strOptString2);
                    return;
                }
                c(obj, "view is null");
                s.a("OperateViews", "removeFromSuperView failed: view is null instanceId = " + strOptString2);
                return;
            }
            c(obj, "params not enough");
            s.a("OperateViews", "removeFromSuperView failed: params not enough instanceId = " + strOptString2);
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "removeFromSuperView failed: " + th.getMessage());
        }
    }

    public final void h(Object obj, JSONObject jSONObject) throws JSONException {
        int iOptInt;
        try {
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("id");
            String strI = i(strOptString2);
            if (TextUtils.isEmpty(strI)) {
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                if (aVar.a != null) {
                    strI = aVar.a.getRid();
                }
            }
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
            if (jSONObjectOptJSONObject == null) {
                c(obj, "data is empty");
                return;
            }
            String strOptString3 = jSONObjectOptJSONObject.optString("id");
            LinkedHashMap<String, View> linkedHashMapB = b(strOptString, strI);
            if (linkedHashMapB.containsKey(strOptString3) && linkedHashMapB.containsKey(strOptString2)) {
                ViewGroup viewGroup = (ViewGroup) linkedHashMapB.get(strOptString2);
                View view = linkedHashMapB.get(strOptString3);
                if (viewGroup != null && view != null) {
                    JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("rect");
                    int iOptInt2 = 0;
                    if (view instanceof MBTempContainer) {
                        s.d("OperateViews", "OperateViews setNotchString = " + String.format("%1$s-%2$s-%3$s-%4$s-%5$s", Integer.valueOf(l), Integer.valueOf(m), Integer.valueOf(n), Integer.valueOf(o), Integer.valueOf(p)));
                        ((MBTempContainer) view).setNotchPadding(l, m, n, o, p);
                        Iterator<View> it = linkedHashMapB.values().iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            View next = it.next();
                            if (next instanceof MBridgeBTContainer) {
                                ((MBridgeBTContainer) next).appendSubView((MBridgeBTContainer) next, (MBTempContainer) view, jSONObjectOptJSONObject2);
                                break;
                            }
                        }
                    } else {
                        Rect rect = null;
                        if (jSONObjectOptJSONObject2 == null) {
                            if (view instanceof BTBaseView) {
                                rect = ((BTBaseView) view).getRect();
                                iOptInt2 = ((BTBaseView) view).getViewWidth();
                                iOptInt = ((BTBaseView) view).getViewHeight();
                            } else {
                                iOptInt = 0;
                            }
                        } else {
                            Rect rect2 = new Rect(jSONObjectOptJSONObject2.optInt(TtmlNode.LEFT, -999), jSONObjectOptJSONObject2.optInt("top", -999), jSONObjectOptJSONObject2.optInt(TtmlNode.RIGHT, -999), jSONObjectOptJSONObject2.optInt(TipsConfigItem.TipConfigData.BOTTOM, -999));
                            iOptInt2 = jSONObjectOptJSONObject2.optInt("width");
                            iOptInt = jSONObjectOptJSONObject2.optInt("height");
                            rect = rect2;
                        }
                        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
                        if (viewGroup instanceof FrameLayout) {
                            layoutParams = new FrameLayout.LayoutParams(-1, -1);
                        } else if (viewGroup instanceof RelativeLayout) {
                            layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                        } else if (viewGroup instanceof LinearLayout) {
                            layoutParams = new LinearLayout.LayoutParams(-1, -1);
                        }
                        viewGroup.addView(view, a(layoutParams, rect, iOptInt2, iOptInt));
                    }
                    a(obj, strOptString2);
                    a(obj, "onAppendView", strOptString2);
                    s.a("OperateViews", "appendSubView parentId = " + strOptString2 + " childId = " + strOptString3);
                    return;
                }
                c(obj, "view is not exist");
                return;
            }
            c(obj, "instanceId is not exist");
            s.a("OperateViews", "appendSubView failed: instanceId is not exist");
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "appendSubView failed: " + th.getMessage());
        }
    }

    public final void i(Object obj, JSONObject jSONObject) throws JSONException {
        int iOptInt;
        try {
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("id");
            String strI = i(strOptString2);
            if (TextUtils.isEmpty(strI)) {
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                if (aVar.a != null) {
                    strI = aVar.a.getRid();
                }
            }
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
            if (jSONObjectOptJSONObject != null && !TextUtils.isEmpty(strOptString)) {
                String strOptString3 = jSONObjectOptJSONObject.optString("id");
                LinkedHashMap<String, View> linkedHashMapB = b(strOptString, strI);
                if (linkedHashMapB.containsKey(strOptString2) && linkedHashMapB.containsKey(strOptString3)) {
                    ViewGroup viewGroup = (ViewGroup) linkedHashMapB.get(strOptString3);
                    View view = linkedHashMapB.get(strOptString2);
                    if (viewGroup != null && view != null) {
                        JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("rect");
                        if (view instanceof MBTempContainer) {
                            Iterator<View> it = linkedHashMapB.values().iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                View next = it.next();
                                if (next instanceof MBridgeBTContainer) {
                                    ((MBridgeBTContainer) next).appendSubView((MBridgeBTContainer) next, (MBTempContainer) view, jSONObjectOptJSONObject2);
                                    break;
                                }
                            }
                        } else {
                            Rect rect = null;
                            int iOptInt2 = 0;
                            if (jSONObjectOptJSONObject2 == null) {
                                if (view instanceof BTBaseView) {
                                    rect = ((BTBaseView) view).getRect();
                                    iOptInt2 = ((BTBaseView) view).getViewWidth();
                                    iOptInt = ((BTBaseView) view).getViewHeight();
                                } else {
                                    iOptInt = 0;
                                }
                            } else {
                                Rect rect2 = new Rect(jSONObjectOptJSONObject2.optInt(TtmlNode.LEFT, -999), jSONObjectOptJSONObject2.optInt("top", -999), jSONObjectOptJSONObject2.optInt(TtmlNode.RIGHT, -999), jSONObjectOptJSONObject2.optInt(TipsConfigItem.TipConfigData.BOTTOM, -999));
                                iOptInt2 = jSONObjectOptJSONObject2.optInt("width");
                                iOptInt = jSONObjectOptJSONObject2.optInt("height");
                                rect = rect2;
                            }
                            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
                            if (viewGroup instanceof FrameLayout) {
                                layoutParams = new FrameLayout.LayoutParams(-1, -1);
                            } else if (viewGroup instanceof RelativeLayout) {
                                layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                            } else if (viewGroup instanceof LinearLayout) {
                                layoutParams = new LinearLayout.LayoutParams(-1, -1);
                            }
                            viewGroup.addView(view, a(layoutParams, rect, iOptInt2, iOptInt));
                        }
                        a(obj, strOptString2);
                        a(obj, "onAppendViewTo", strOptString2);
                        s.a("OperateViews", "appendViewTo parentId = " + strOptString3 + " childId = " + strOptString2);
                        return;
                    }
                    c(obj, "view is not exist");
                    return;
                }
                c(obj, "instanceId is not exist");
                s.a("OperateViews", "appendViewTo failed: instanceId is not exist");
                return;
            }
            c(obj, "unitId or data is empty");
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "appendViewTo failed: " + th.getMessage());
        }
    }

    public final void j(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("id");
            String strI = i(strOptString2);
            if (TextUtils.isEmpty(strI)) {
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                if (aVar.a != null) {
                    strI = aVar.a.getRid();
                }
            }
            if (jSONObject.optJSONObject("data") == null) {
                c(obj, "data is empty");
                return;
            }
            LinkedHashMap<String, View> linkedHashMapB = b(strOptString, strI);
            if (linkedHashMapB.containsKey(strOptString2)) {
                View view = linkedHashMapB.get(strOptString2);
                if (view != null && view.getParent() != null) {
                    ViewGroup viewGroup = (ViewGroup) view.getParent();
                    if (viewGroup != null) {
                        viewGroup.bringChildToFront(view);
                        a(obj, strOptString2);
                        a(obj, "onBringViewToFront", strOptString2);
                        s.a("OperateViews", "bringViewToFront instanceId = " + strOptString2);
                        return;
                    }
                    c(obj, "parent is null");
                    s.a("OperateViews", "bringViewToFront failed: parent is null");
                    return;
                }
                c(obj, "view is null");
                s.a("OperateViews", "bringViewToFront failed: view is null");
                return;
            }
            c(obj, "instanceId is not exist");
            s.a("OperateViews", "bringViewToFront failed: instanceId is not exist");
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "bringViewToFront failed: " + th.getMessage());
        }
    }

    public final void k(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("id");
            String strI = i(strOptString2);
            if (TextUtils.isEmpty(strI)) {
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                if (aVar.a != null) {
                    strI = aVar.a.getRid();
                }
            }
            if (jSONObject.optJSONObject("data") == null) {
                c(obj, "data is empty");
                return;
            }
            LinkedHashMap<String, View> linkedHashMapB = b(strOptString, strI);
            if (linkedHashMapB.containsKey(strOptString2)) {
                View view = linkedHashMapB.get(strOptString2);
                if (view != null) {
                    view.setVisibility(8);
                    a(obj, strOptString2);
                    a(obj, "onHideView", strOptString2);
                    s.a("OperateViews", "hideView instanceId = " + strOptString2);
                    return;
                }
                c(obj, "view not exist");
                s.a("OperateViews", "hideView failed: view not exist");
                return;
            }
            c(obj, "instanceId not exist");
            s.a("OperateViews", "hideView failed: instanceId not exist");
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "hideView failed: + " + th.getMessage());
        }
    }

    public final void l(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("id");
            String strI = i(strOptString2);
            if (TextUtils.isEmpty(strI)) {
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                if (aVar.a != null) {
                    strI = aVar.a.getRid();
                }
            }
            if (jSONObject.optJSONObject("data") == null) {
                c(obj, "data is empty");
                return;
            }
            LinkedHashMap<String, View> linkedHashMapB = b(strOptString, strI);
            if (linkedHashMapB.containsKey(strOptString2)) {
                View view = linkedHashMapB.get(strOptString2);
                if (view != null) {
                    view.setVisibility(0);
                    a(obj, strOptString2);
                    a(obj, "onShowView", strOptString2);
                    s.a("OperateViews", "showView instanceId = " + strOptString2);
                    return;
                }
                c(obj, "view not exist");
                s.a("OperateViews", "showView failed: view not exist instanceId = " + strOptString2);
                return;
            }
            c(obj, "instanceId not exist");
            s.a("OperateViews", "showView failed: instanceId not exist instanceId = " + strOptString2);
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "showView failed: " + th.getMessage());
        }
    }

    public final void m(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("id");
            String strI = i(strOptString2);
            if (TextUtils.isEmpty(strI)) {
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                if (aVar.a != null) {
                    strI = aVar.a.getRid();
                }
            }
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
            if (jSONObjectOptJSONObject == null) {
                c(obj, "data is empty");
                return;
            }
            String strOptString3 = jSONObjectOptJSONObject.optString("color");
            if (TextUtils.isEmpty(strOptString3)) {
                c(obj, "color is not exist");
                return;
            }
            LinkedHashMap<String, View> linkedHashMapB = b(strOptString, strI);
            if (linkedHashMapB.containsKey(strOptString2)) {
                View view = linkedHashMapB.get(strOptString2);
                if (view != null) {
                    a(obj, strOptString2);
                    view.setBackgroundColor(Color.parseColor(strOptString3));
                    a(obj, "onViewBgColorChanged", strOptString2);
                    s.a("OperateViews", "setViewBgColor instanceId = " + strOptString2);
                    return;
                }
                c(obj, "view not exist");
                s.a("OperateViews", "setViewBgColor failed: view not exist instanceId = " + strOptString2);
                return;
            }
            c(obj, "instanceId not exist");
            s.a("OperateViews", "setViewBgColor failed: instanceId not exist instanceId = " + strOptString2);
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "setViewBgColor failed: " + th.getMessage());
        }
    }

    public final void n(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("id");
            String strI = i(strOptString2);
            if (TextUtils.isEmpty(strI)) {
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                if (aVar.a != null) {
                    strI = aVar.a.getRid();
                }
            }
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
            if (jSONObjectOptJSONObject == null) {
                c(obj, "data is empty");
                return;
            }
            double dOptDouble = jSONObjectOptJSONObject.optDouble("alpha", 1.0d);
            LinkedHashMap<String, View> linkedHashMapB = b(strOptString, strI);
            if (linkedHashMapB.containsKey(strOptString2)) {
                View view = linkedHashMapB.get(strOptString2);
                if (view != null) {
                    view.setAlpha((float) dOptDouble);
                    a(obj, strOptString2);
                    a(obj, "onViewAlphaChanged", strOptString2);
                    s.a("OperateViews", "setViewAlpha instanceId = " + strOptString2);
                    return;
                }
                c(obj, "view not exist");
                s.a("OperateViews", "setViewAlpha failed: view not exist instanceId = " + strOptString2);
                return;
            }
            c(obj, "instanceId not exist");
            s.a("OperateViews", "setViewAlpha failed: instanceId not exist instanceId = " + strOptString2);
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "setViewAlpha failed: " + th.getMessage());
        }
    }

    public final void o(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("id");
            String strI = i(strOptString2);
            if (TextUtils.isEmpty(strI)) {
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                if (aVar.a != null) {
                    strI = aVar.a.getRid();
                }
            }
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
            if (jSONObjectOptJSONObject == null) {
                c(obj, "data is empty");
                return;
            }
            double dOptDouble = jSONObjectOptJSONObject.optDouble("vertical", 1.0d);
            double dOptDouble2 = jSONObjectOptJSONObject.optDouble("horizon", 1.0d);
            LinkedHashMap<String, View> linkedHashMapB = b(strOptString, strI);
            if (linkedHashMapB.containsKey(strOptString2)) {
                View view = linkedHashMapB.get(strOptString2);
                if (view != null) {
                    view.setScaleX((float) dOptDouble2);
                    view.setScaleY((float) dOptDouble);
                    a(obj, strOptString2);
                    a(obj, "onViewScaleChanged", strOptString2);
                    s.a("OperateViews", "setViewScale instanceId = " + strOptString2);
                    return;
                }
                c(obj, "view not exist");
                s.a("OperateViews", "setViewScale failed: view not exist instanceId = " + strOptString2);
                return;
            }
            c(obj, "instanceId not exist");
            s.a("OperateViews", "setViewScale failed: instanceId not exist instanceId = " + strOptString2);
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "setViewScale failed: " + th.getMessage());
        }
    }

    public final void p(Object obj, JSONObject jSONObject) throws JSONException {
        int iOptInt;
        try {
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("id");
            String strI = i(strOptString2);
            if (TextUtils.isEmpty(strI)) {
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                if (aVar.a != null) {
                    strI = aVar.a.getRid();
                }
            }
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
            if (jSONObjectOptJSONObject == null) {
                c(obj, "data is empty");
                return;
            }
            String strOptString3 = jSONObjectOptJSONObject.optString("targetComponentId");
            JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("rect");
            LinkedHashMap<String, View> linkedHashMapB = b(strOptString, strI);
            if (linkedHashMapB.containsKey(strOptString2) && linkedHashMapB.containsKey(strOptString3)) {
                View view = linkedHashMapB.get(strOptString2);
                View view2 = linkedHashMapB.get(strOptString3);
                if (view2 != null && view2.getParent() != null) {
                    ViewGroup viewGroup = (ViewGroup) view2.getParent();
                    int iIndexOfChild = viewGroup.indexOfChild(view2);
                    Rect rect = null;
                    int iOptInt2 = 0;
                    if (jSONObjectOptJSONObject2 == null) {
                        if (view instanceof BTBaseView) {
                            rect = ((BTBaseView) view).getRect();
                            iOptInt2 = ((BTBaseView) view).getViewWidth();
                            iOptInt = ((BTBaseView) view).getViewHeight();
                        } else {
                            iOptInt = 0;
                        }
                    } else {
                        Rect rect2 = new Rect(jSONObjectOptJSONObject2.optInt(TtmlNode.LEFT, -999), jSONObjectOptJSONObject2.optInt("top", -999), jSONObjectOptJSONObject2.optInt(TtmlNode.RIGHT, -999), jSONObjectOptJSONObject2.optInt(TipsConfigItem.TipConfigData.BOTTOM, -999));
                        iOptInt2 = jSONObjectOptJSONObject2.optInt("width");
                        iOptInt = jSONObjectOptJSONObject2.optInt("height");
                        rect = rect2;
                    }
                    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
                    if (viewGroup instanceof FrameLayout) {
                        layoutParams = new FrameLayout.LayoutParams(-1, -1);
                    } else if (viewGroup instanceof RelativeLayout) {
                        layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                    } else if (viewGroup instanceof LinearLayout) {
                        layoutParams = new LinearLayout.LayoutParams(-1, -1);
                    }
                    viewGroup.addView(view, iIndexOfChild + 1, a(layoutParams, rect, iOptInt2, iOptInt));
                    a(obj, strOptString2);
                    a(obj, "onInsertViewAbove", strOptString2);
                    s.a("OperateViews", "insertViewAbove instanceId = " + strOptString2 + " brotherId = " + strOptString3);
                    return;
                }
                c(obj, "view not exist");
                s.a("OperateViews", "insertViewAbove failed: view not exist instanceId = " + strOptString2);
                return;
            }
            c(obj, "instanceId not exist");
            s.a("OperateViews", "insertViewAbove failed: instanceId not exist instanceId = " + strOptString2);
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "insertViewAbove failed: " + th.getMessage());
        }
    }

    public final void q(Object obj, JSONObject jSONObject) throws JSONException {
        int iOptInt;
        try {
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("id");
            String strI = i(strOptString2);
            if (TextUtils.isEmpty(strI)) {
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                if (aVar.a != null) {
                    strI = aVar.a.getRid();
                }
            }
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
            if (jSONObjectOptJSONObject == null) {
                c(obj, "data is empty");
                return;
            }
            String strOptString3 = jSONObjectOptJSONObject.optString("targetComponentId");
            JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("rect");
            LinkedHashMap<String, View> linkedHashMapB = b(strOptString, strI);
            if (linkedHashMapB.containsKey(strOptString2) && linkedHashMapB.containsKey(strOptString3)) {
                View view = linkedHashMapB.get(strOptString2);
                View view2 = linkedHashMapB.get(strOptString3);
                if (view2 != null && view2.getParent() != null) {
                    ViewGroup viewGroup = (ViewGroup) view2.getParent();
                    int iIndexOfChild = viewGroup.indexOfChild(view2);
                    Rect rect = null;
                    int iOptInt2 = 0;
                    if (jSONObjectOptJSONObject2 == null) {
                        if (view instanceof BTBaseView) {
                            rect = ((BTBaseView) view).getRect();
                            iOptInt2 = ((BTBaseView) view).getViewWidth();
                            iOptInt = ((BTBaseView) view).getViewHeight();
                        } else {
                            iOptInt = 0;
                        }
                    } else {
                        Rect rect2 = new Rect(jSONObjectOptJSONObject2.optInt(TtmlNode.LEFT, -999), jSONObjectOptJSONObject2.optInt("top", -999), jSONObjectOptJSONObject2.optInt(TtmlNode.RIGHT, -999), jSONObjectOptJSONObject2.optInt(TipsConfigItem.TipConfigData.BOTTOM, -999));
                        iOptInt2 = jSONObjectOptJSONObject2.optInt("width");
                        iOptInt = jSONObjectOptJSONObject2.optInt("height");
                        rect = rect2;
                    }
                    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
                    if (viewGroup instanceof FrameLayout) {
                        layoutParams = new FrameLayout.LayoutParams(-1, -1);
                    } else if (viewGroup instanceof RelativeLayout) {
                        layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                    } else if (viewGroup instanceof LinearLayout) {
                        layoutParams = new LinearLayout.LayoutParams(-1, -1);
                    }
                    viewGroup.addView(view, iIndexOfChild, a(layoutParams, rect, iOptInt2, iOptInt));
                    a(obj, strOptString2);
                    a(obj, "onInsertViewBelow", strOptString2);
                    s.a("OperateViews", "insertViewBelow instanceId = " + strOptString2);
                    return;
                }
                c(obj, "view not exist");
                s.a("OperateViews", "insertViewBelow failed: view not exist instanceId = " + strOptString2);
                return;
            }
            c(obj, "instanceId not exist");
            s.a("OperateViews", "insertViewBelow failed: instanceId not exist instanceId = " + strOptString2);
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "insertViewBelow failed: " + th.getMessage());
        }
    }

    public final void r(Object obj, JSONObject jSONObject) throws JSONException {
        Rect rect;
        int iOptInt;
        int iOptInt2;
        Object obj2 = obj;
        try {
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("id");
            String strI = i(strOptString2);
            if (TextUtils.isEmpty(strI)) {
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj2;
                if (aVar.a != null) {
                    strI = aVar.a.getRid();
                }
            }
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
            if (jSONObjectOptJSONObject == null) {
                c(obj2, "data is empty");
                return;
            }
            JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("content");
            if (jSONObjectOptJSONObject2 == null) {
                c(obj2, "content is empty");
                return;
            }
            String strOptString3 = jSONObjectOptJSONObject2.optString("fileURL");
            String strOptString4 = jSONObjectOptJSONObject2.optString("filePath");
            String strOptString5 = jSONObjectOptJSONObject2.optString("html");
            if (TextUtils.isEmpty(strOptString3) && TextUtils.isEmpty(strOptString4) && TextUtils.isEmpty(strOptString5)) {
                c(obj2, "url is empty");
                return;
            }
            JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject2.optJSONArray("campaigns");
            ArrayList arrayList = new ArrayList();
            if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                int length = jSONArrayOptJSONArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    CampaignEx campaignWithBackData = CampaignEx.parseCampaignWithBackData(jSONArrayOptJSONArray.getJSONObject(i2));
                    if (campaignWithBackData != null) {
                        campaignWithBackData.setCampaignUnitId(strOptString);
                        arrayList.add(campaignWithBackData);
                    }
                }
            }
            String strOptString6 = jSONObjectOptJSONObject2.optString("unitId");
            com.mbridge.msdk.videocommon.d.c cVarA = com.mbridge.msdk.videocommon.d.c.a(jSONObjectOptJSONObject2.optJSONObject("unitSetting"));
            if (cVarA != null) {
                cVarA.a(strOptString6);
            }
            JSONObject jSONObjectOptJSONObject3 = jSONObject.optJSONObject("rect");
            Rect rect2 = new Rect(-999, -999, -999, -999);
            if (jSONObjectOptJSONObject3 != null) {
                try {
                    rect = new Rect(jSONObjectOptJSONObject3.optInt(TtmlNode.LEFT, -999), jSONObjectOptJSONObject3.optInt("top", -999), jSONObjectOptJSONObject3.optInt(TtmlNode.RIGHT, -999), jSONObjectOptJSONObject3.optInt(TipsConfigItem.TipConfigData.BOTTOM, -999));
                    iOptInt = jSONObjectOptJSONObject3.optInt("width");
                    iOptInt2 = jSONObjectOptJSONObject3.optInt("height");
                } catch (Throwable th) {
                    th = th;
                    obj2 = obj;
                    c(obj2, th.getMessage());
                    s.a("OperateViews", "webviewLoad failed: " + th.getMessage());
                    return;
                }
            } else {
                rect = rect2;
                iOptInt2 = 0;
                iOptInt = 0;
            }
            int iOptInt3 = jSONObjectOptJSONObject2.optInt("refreshCache", 0);
            LinkedHashMap<String, View> linkedHashMapB = b(strOptString, strI);
            if (linkedHashMapB.containsKey(strOptString2)) {
                View view = linkedHashMapB.get(strOptString2);
                if (view instanceof MBridgeBTWebView) {
                    MBridgeBTWebView mBridgeBTWebView = (MBridgeBTWebView) view;
                    mBridgeBTWebView.setHtml(strOptString5);
                    mBridgeBTWebView.setFilePath(strOptString4);
                    mBridgeBTWebView.setFileURL(strOptString3);
                    mBridgeBTWebView.setRect(rect);
                    mBridgeBTWebView.setLayout(iOptInt, iOptInt2);
                    mBridgeBTWebView.setCampaigns(arrayList);
                    mBridgeBTWebView.setRewardUnitSetting(cVarA);
                    mBridgeBTWebView.webviewLoad(iOptInt3);
                    a(obj, strOptString2);
                    s.a("OperateViews", "webviewLoad instanceId = " + strOptString2);
                    return;
                }
                c(obj, "view not exist");
                s.a("OperateViews", "webviewLoad failed: view not exist instanceId = " + strOptString2);
                return;
            }
            c(obj, "instanceId not exist");
            s.a("OperateViews", "webviewLoad failed: instanceId not exist instanceId = " + strOptString2);
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public final void s(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("id");
            String strI = i(strOptString2);
            if (TextUtils.isEmpty(strI)) {
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                if (aVar.a != null) {
                    strI = aVar.a.getRid();
                }
            }
            if (jSONObject.optJSONObject("data") == null) {
                c(obj, "data is empty");
                return;
            }
            LinkedHashMap<String, View> linkedHashMapB = b(strOptString, strI);
            if (linkedHashMapB.containsKey(strOptString2)) {
                View view = linkedHashMapB.get(strOptString2);
                if (view instanceof MBridgeBTWebView) {
                    if (((MBridgeBTWebView) view).webviewReload()) {
                        a(obj, strOptString2);
                        s.a("OperateViews", "webviewReload instanceId = " + strOptString2);
                        return;
                    }
                    c(obj, "reload failed");
                    s.a("OperateViews", "webviewReload failed reload failed instanceId = " + strOptString2);
                    return;
                }
                c(obj, "view not exist");
                s.a("OperateViews", "webviewReload failed view not exist instanceId = " + strOptString2);
                return;
            }
            c(obj, "instanceId not exist");
            s.a("OperateViews", "webviewReload failed instanceId not exist instanceId = " + strOptString2);
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "webviewReload failed: " + th.getMessage());
        }
    }

    public final void t(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("id");
            String strI = i(strOptString2);
            if (TextUtils.isEmpty(strI)) {
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                if (aVar.a != null) {
                    strI = aVar.a.getRid();
                }
            }
            if (jSONObject.optJSONObject("data") == null) {
                c(obj, "data is empty");
                return;
            }
            LinkedHashMap<String, View> linkedHashMapB = b(strOptString, strI);
            if (linkedHashMapB.containsKey(strOptString2)) {
                View view = linkedHashMapB.get(strOptString2);
                if (view instanceof MBridgeBTWebView) {
                    if (((MBridgeBTWebView) view).webviewGoBack()) {
                        a(obj, strOptString2);
                        s.a("OperateViews", "webviewGoBack instanceId = " + strOptString2);
                        return;
                    }
                    c(obj, "webviewGoBack failed");
                    s.a("OperateViews", "webviewGoBack failed instanceId = " + strOptString2);
                    return;
                }
                c(obj, "view not exist");
                s.a("OperateViews", "webviewGoBack failed view not exist instanceId = " + strOptString2);
                return;
            }
            c(obj, "instanceId not exist");
            s.a("OperateViews", "webviewGoBack failed instanceId not exist instanceId = " + strOptString2);
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "webviewGoBack failed: " + th.getMessage());
        }
    }

    public final void u(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("id");
            String strI = i(strOptString2);
            if (TextUtils.isEmpty(strI)) {
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                if (aVar.a != null) {
                    strI = aVar.a.getRid();
                }
            }
            if (jSONObject.optJSONObject("data") == null) {
                c(obj, "data is empty");
                return;
            }
            LinkedHashMap<String, View> linkedHashMapB = b(strOptString, strI);
            if (linkedHashMapB.containsKey(strOptString2)) {
                View view = linkedHashMapB.get(strOptString2);
                if (view instanceof MBridgeBTWebView) {
                    if (((MBridgeBTWebView) view).webviewGoForward()) {
                        a(obj, strOptString2);
                        s.a("OperateViews", "webviewGoForward instanceId = " + strOptString2);
                        return;
                    }
                    c(obj, "webviewGoForward failed");
                    s.a("OperateViews", "webviewGoForward failed instanceId = " + strOptString2);
                    return;
                }
                c(obj, "view not exist");
                s.a("OperateViews", "webviewGoForward failed view not exist instanceId = " + strOptString2);
                return;
            }
            c(obj, "instanceId not exist");
            s.a("OperateViews", "webviewGoForward failed instanceId not exist instanceId = " + strOptString2);
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "webviewGoForward failed: " + th.getMessage());
        }
    }

    public final void v(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("id");
            String strI = i(strOptString2);
            if (TextUtils.isEmpty(strI)) {
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                if (aVar.a != null) {
                    strI = aVar.a.getRid();
                }
            }
            if (jSONObject.optJSONObject("data") == null) {
                c(obj, "data is empty");
                return;
            }
            LinkedHashMap<String, View> linkedHashMapB = b(strOptString, strI);
            if (linkedHashMapB.containsKey(strOptString2)) {
                View view = linkedHashMapB.get(strOptString2);
                if (view instanceof MBridgeBTVideoView) {
                    ((MBridgeBTVideoView) view).play();
                    a(obj, strOptString2);
                    s.a("OperateViews", "playerPlay success");
                    return;
                } else {
                    c(obj, "instanceId is not player");
                    s.a("OperateViews", "playerPlay failed instanceId is not player instanceId = " + strOptString2);
                    return;
                }
            }
            c(obj, "instanceId not exit");
            s.a("OperateViews", "playerPlay failed instanceId not exit instanceId = " + strOptString2);
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "playerPlay failed: " + th.getMessage());
        }
    }

    public final void w(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("id");
            String strI = i(strOptString2);
            if (TextUtils.isEmpty(strI)) {
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                if (aVar.a != null) {
                    strI = aVar.a.getRid();
                }
            }
            if (jSONObject.optJSONObject("data") == null) {
                c(obj, "data is empty");
                return;
            }
            LinkedHashMap<String, View> linkedHashMapB = b(strOptString, strI);
            if (linkedHashMapB.containsKey(strOptString2)) {
                View view = linkedHashMapB.get(strOptString2);
                if (view instanceof MBridgeBTVideoView) {
                    ((MBridgeBTVideoView) view).pause();
                    a(obj, strOptString2);
                    s.a("OperateViews", "playerPause success");
                    return;
                } else {
                    c(obj, "instanceId is not player");
                    s.a("OperateViews", "playerPause failed instanceId is not player instanceId = " + strOptString2);
                    return;
                }
            }
            c(obj, "instanceId not exit");
            s.a("OperateViews", "playerPause failed instanceId not exit instanceId = " + strOptString2);
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "playerPause failed: " + th.getMessage());
        }
    }

    public final void x(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("id");
            String strI = i(strOptString2);
            if (TextUtils.isEmpty(strI)) {
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                if (aVar.a != null) {
                    strI = aVar.a.getRid();
                }
            }
            if (jSONObject.optJSONObject("data") == null) {
                c(obj, "data is empty");
                return;
            }
            LinkedHashMap<String, View> linkedHashMapB = b(strOptString, strI);
            if (linkedHashMapB.containsKey(strOptString2)) {
                View view = linkedHashMapB.get(strOptString2);
                if (view instanceof MBridgeBTVideoView) {
                    ((MBridgeBTVideoView) view).resume();
                    a(obj, strOptString2);
                    s.a("OperateViews", "playerResume success");
                    return;
                } else {
                    c(obj, "instanceId is not player");
                    s.a("OperateViews", "playerResume failed instanceId is not player instanceId = " + strOptString2);
                    return;
                }
            }
            c(obj, "instanceId not exit");
            s.a("OperateViews", "playerResume failed instanceId not exit instanceId = " + strOptString2);
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "playerResume failed: " + th.getMessage());
        }
    }

    public final void y(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("id");
            String strI = i(strOptString2);
            if (TextUtils.isEmpty(strI)) {
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                if (aVar.a != null) {
                    strI = aVar.a.getRid();
                }
            }
            if (jSONObject.optJSONObject("data") == null) {
                c(obj, "data is empty");
                return;
            }
            LinkedHashMap<String, View> linkedHashMapB = b(strOptString, strI);
            if (linkedHashMapB.containsKey(strOptString2)) {
                View view = linkedHashMapB.get(strOptString2);
                if (view instanceof MBridgeBTVideoView) {
                    ((MBridgeBTVideoView) view).stop();
                    a(obj, strOptString2);
                    s.a("OperateViews", "playerStop success");
                    return;
                } else {
                    c(obj, "instanceId is not player");
                    s.a("OperateViews", "playerStop failed instanceId is not player instanceId = " + strOptString2);
                    return;
                }
            }
            c(obj, "instanceId not exit");
            s.a("OperateViews", "playerStop failed instanceId not exit instanceId = " + strOptString2);
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "playerStop failed: " + th.getMessage());
        }
    }

    public final void z(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            jSONObject.optString("unitId");
            if (jSONObject.optJSONObject("data") == null) {
                c(obj, "data is empty");
            }
        } catch (Throwable th) {
            c(obj, th.getMessage());
        }
    }

    public final void A(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("id");
            String strI = i(strOptString2);
            if (TextUtils.isEmpty(strI)) {
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                if (aVar.a != null) {
                    strI = aVar.a.getRid();
                }
            }
            if (jSONObject.optJSONObject("data") == null) {
                c(obj, "data is empty");
                return;
            }
            LinkedHashMap<String, View> linkedHashMapB = b(strOptString, strI);
            if (linkedHashMapB.containsKey(strOptString2)) {
                View view = linkedHashMapB.get(strOptString2);
                if (view instanceof MBridgeBTVideoView) {
                    if (((MBridgeBTVideoView) view).playMute()) {
                        a(obj, strOptString2);
                        s.a("OperateViews", "playerMute success");
                        return;
                    } else {
                        c(obj, "set mute failed");
                        s.a("OperateViews", "playerMute failed set mute failed");
                        return;
                    }
                }
                c(obj, "instanceId is not player");
                s.a("OperateViews", "playerMute failed: instanceId is not player");
                return;
            }
            c(obj, "instanceId not exist");
            s.a("OperateViews", "playerMute failed: instanceId is not exist");
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "playerMute failed: " + th.getMessage());
        }
    }

    public final void B(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("id");
            String strI = i(strOptString2);
            if (TextUtils.isEmpty(strI)) {
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                if (aVar.a != null) {
                    strI = aVar.a.getRid();
                }
            }
            if (jSONObject.optJSONObject("data") == null) {
                c(obj, "data is empty");
                return;
            }
            LinkedHashMap<String, View> linkedHashMapB = b(strOptString, strI);
            if (linkedHashMapB.containsKey(strOptString2)) {
                View view = linkedHashMapB.get(strOptString2);
                if (view instanceof MBridgeBTVideoView) {
                    if (((MBridgeBTVideoView) view).playUnMute()) {
                        a(obj, strOptString2);
                        a(obj, "onUnmute", strOptString2);
                        s.a("OperateViews", "playerUnmute successed");
                        return;
                    } else {
                        a(obj, "set unmute failed");
                        s.a("OperateViews", "playerUnmute failed: set unmute failed");
                        return;
                    }
                }
                c(obj, "instanceId is not player");
                s.a("OperateViews", "playerUnmute failed: instanceId is not player");
                return;
            }
            c(obj, "instanceId not exit");
            s.a("OperateViews", "playerUnmute failed: instanceId not exit");
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "playerUnmute failed: " + th.getMessage());
        }
    }

    public final void C(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("id");
            String strI = i(strOptString2);
            if (TextUtils.isEmpty(strI)) {
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                if (aVar.a != null) {
                    strI = aVar.a.getRid();
                }
            }
            if (jSONObject.optJSONObject("data") == null) {
                c(obj, "data is empty");
                return;
            }
            LinkedHashMap<String, View> linkedHashMapB = b(strOptString, strI);
            if (linkedHashMapB.containsKey(strOptString2)) {
                View view = linkedHashMapB.get(strOptString2);
                if (view instanceof MBridgeBTVideoView) {
                    int mute = ((MBridgeBTVideoView) view).getMute();
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(CommandMessage.CODE, i);
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("id", strOptString2);
                    jSONObject3.put(CampaignEx.JSON_NATIVE_VIDEO_MUTE, mute);
                    jSONObject2.put("data", jSONObject3);
                    h.a().a(obj, Base64.encodeToString(jSONObject2.toString().getBytes(), 2));
                    s.a("OperateViews", "playerGetMuteState successed mute = " + mute);
                    return;
                }
                c(obj, "instanceId is not player");
                s.a("OperateViews", "playerGetMuteState failed instanceId is not player");
                return;
            }
            c(obj, "instanceId not exist");
            s.a("OperateViews", "playerGetMuteState failed instanceId not exist");
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "playerGetMuteState failed: " + th.getMessage());
        }
    }

    public final void D(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            jSONObject.optString("unitId");
            if (jSONObject.optJSONObject("data") == null) {
                c(obj, "data is empty");
            }
        } catch (Throwable th) {
            c(obj, th.getMessage());
        }
    }

    public final void E(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            jSONObject.optString("unitId");
            jSONObject.optString("id");
            if (jSONObject.optJSONObject("data") == null) {
                c(obj, "data is empty");
            } else {
                c(obj, "android mediaPlayer not support setScaleType");
            }
        } catch (Throwable th) {
            c(obj, th.getMessage());
        }
    }

    public final void F(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("id");
            String strI = i(strOptString2);
            if (TextUtils.isEmpty(strI)) {
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                if (aVar.a != null) {
                    strI = aVar.a.getRid();
                }
            }
            if (jSONObject.optJSONObject("data") == null) {
                c(obj, "data is empty");
                return;
            }
            LinkedHashMap<String, View> linkedHashMapB = b(strOptString, strI);
            if (linkedHashMapB.containsKey(strOptString2)) {
                View view = linkedHashMapB.get(strOptString2);
                if (view instanceof MBTempContainer) {
                    ((MBTempContainer) view).preload();
                    a(obj, strOptString2);
                    return;
                } else {
                    c(obj, "view not exist");
                    return;
                }
            }
            c(obj, "instanceId not exist");
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "preloadSubPlayTemplateView failed: " + th.getMessage());
        }
    }

    public final void G(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("id");
            String strI = i(strOptString2);
            if (TextUtils.isEmpty(strI)) {
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                if (aVar.a != null) {
                    strI = aVar.a.getRid();
                }
            }
            if (jSONObject.optJSONObject("data") == null) {
                c(obj, "data is empty");
                return;
            }
            LinkedHashMap<String, View> linkedHashMapB = b(strOptString, strI);
            MBridgeBTContainer mBridgeBTContainer = null;
            if (linkedHashMapB.size() > 0) {
                for (View view : linkedHashMapB.values()) {
                    if (view instanceof MBridgeBTContainer) {
                        mBridgeBTContainer = (MBridgeBTContainer) view;
                    } else if (view instanceof MBridgeBTWebView) {
                        ((MBridgeBTWebView) view).onDestory();
                    } else if (view instanceof MBridgeBTVideoView) {
                        com.mbridge.msdk.video.bt.a.a.a().a(((MBridgeBTVideoView) view).getInstanceId());
                        ((MBridgeBTVideoView) view).onDestory();
                    } else if (view instanceof MBTempContainer) {
                        ((MBTempContainer) view).onDestroy();
                    }
                }
                if (mBridgeBTContainer != null) {
                    mBridgeBTContainer.onAdClose();
                    a.remove(linkedHashMapB);
                    linkedHashMapB.clear();
                    a.remove(strOptString + "_" + strI);
                    a(obj, strOptString2);
                    return;
                }
                c(obj, "not found MBridgeBTContainer");
                s.a("OperateViews", "closeAd successed");
                return;
            }
            c(obj, "unitId or instanceId not exist");
            s.a("OperateViews", "closeAd failed: unitId or instanceId not exist unitId = " + strOptString);
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "closeAd failed: " + th.getMessage());
        }
    }

    public final void H(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
            String rid = aVar.a != null ? aVar.a.getRid() : "";
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("eventName");
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
            LinkedHashMap<String, View> linkedHashMapB = b(strOptString, rid);
            if (linkedHashMapB != null && linkedHashMapB.size() > 0) {
                for (View view : linkedHashMapB.values()) {
                    if (view instanceof MBridgeBTWebView) {
                        ((MBridgeBTWebView) view).broadcast(strOptString2, jSONObjectOptJSONObject);
                    }
                    if (view instanceof MBridgeBTLayout) {
                        ((MBridgeBTLayout) view).broadcast(strOptString2, jSONObjectOptJSONObject);
                    }
                }
                a(obj, "");
                return;
            }
            c(obj, "unitId not exist");
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "broadcast failed: " + th.getMessage());
        }
    }

    public final void I(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
            String rid = aVar.a != null ? aVar.a.getRid() : "";
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("id");
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
            String strOptString3 = jSONObjectOptJSONObject.optString("userId");
            boolean zOptBoolean = jSONObjectOptJSONObject.optBoolean("expired");
            JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("reward");
            String strOptString4 = jSONObjectOptJSONObject.optString(PushConstants.EXTRA);
            d dVar = null;
            String strOptString5 = "";
            if (jSONObjectOptJSONObject2 != null) {
                dVar = new d(jSONObjectOptJSONObject2.optString("name"), jSONObjectOptJSONObject2.optInt(RewardPlus.AMOUNT));
                strOptString5 = jSONObjectOptJSONObject2.optString("id");
            }
            LinkedHashMap<String, View> linkedHashMapB = b(strOptString, rid);
            if (linkedHashMapB.size() > 0 && e.containsKey(strOptString2)) {
                View view = linkedHashMapB.get(strOptString2);
                if (view instanceof MBTempContainer) {
                    MBTempContainer mBTempContainer = (MBTempContainer) view;
                    mBTempContainer.setReward(dVar);
                    mBTempContainer.setUserId(strOptString3);
                    mBTempContainer.setRewardId(strOptString5);
                    mBTempContainer.setCampaignExpired(zOptBoolean);
                    if (!TextUtils.isEmpty(strOptString4)) {
                        mBTempContainer.setDeveloperExtraData(strOptString4);
                    }
                    a(obj, strOptString2);
                    s.a("OperateViews", "setSubPlayTemplateInfo success instanceId = " + strOptString2);
                    return;
                }
                c(obj, "instanceId not exist");
                s.a("OperateViews", "setSubPlayTemplateInfo failed: instanceId not exist instanceId = " + strOptString2);
                return;
            }
            c(obj, "unitId not exist");
            s.a("OperateViews", "setSubPlayTemplateInfo failed: unitId not exist instanceId = " + strOptString2);
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "setSubPlayTemplateInfo failed: " + th.getMessage());
        }
    }

    public final void J(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
            String rid = aVar.a != null ? aVar.a.getRid() : "";
            String strOptString = jSONObject.optString("unitId");
            String strOptString2 = jSONObject.optString("id");
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
            String strOptString3 = jSONObjectOptJSONObject.optString("eventName");
            JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("eventData");
            if (jSONObjectOptJSONObject2 == null) {
                jSONObjectOptJSONObject2 = new JSONObject();
            }
            LinkedHashMap<String, View> linkedHashMapB = b(strOptString, rid);
            if (linkedHashMapB.size() > 0) {
                View view = linkedHashMapB.get(strOptString2);
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    int childCount = viewGroup.getChildCount();
                    for (int i2 = 0; i2 < childCount; i2++) {
                        View childAt = viewGroup.getChildAt(i2);
                        if (childAt instanceof WindVaneWebView) {
                            h.a().a((WebView) childAt, strOptString3, Base64.encodeToString(jSONObjectOptJSONObject2.toString().getBytes(), 2));
                            a(obj, strOptString2);
                            s.a("OperateViews", "webviewFireEvent instanceId = " + strOptString2);
                            return;
                        }
                    }
                }
                c(obj, "instanceId not exist");
                s.a("OperateViews", "webviewFireEvent failed: instanceId not exist instanceId = " + strOptString2);
                return;
            }
            c(obj, "unitId not exist");
            s.a("OperateViews", "webviewFireEvent failed: unitId not exist");
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "webviewFireEvent failed: " + th.getMessage());
        }
    }

    public final void K(Object obj, JSONObject jSONObject) throws JSONException {
        try {
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
            if (jSONObjectOptJSONObject == null) {
                c(obj, "data is empty");
                return;
            }
            final CampaignEx campaignWithBackData = CampaignEx.parseCampaignWithBackData(jSONObjectOptJSONObject);
            if (campaignWithBackData == null) {
                c(obj, "data camapign is empty");
            } else {
                new Thread(new Runnable() { // from class: com.mbridge.msdk.video.bt.a.c.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            m mVarA = m.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
                            if (mVarA != null) {
                                if (!mVarA.a(campaignWithBackData.getId())) {
                                    com.mbridge.msdk.foundation.entity.i iVar = new com.mbridge.msdk.foundation.entity.i();
                                    iVar.a(campaignWithBackData.getId());
                                    iVar.a(campaignWithBackData.getFca());
                                    iVar.b(campaignWithBackData.getFcb());
                                    iVar.d(0);
                                    iVar.c(1);
                                    iVar.a(System.currentTimeMillis());
                                    mVarA.a(iVar);
                                } else {
                                    mVarA.b(campaignWithBackData.getId());
                                }
                            }
                            c.a(c.this, campaignWithBackData.getCampaignUnitId(), campaignWithBackData);
                        } catch (Throwable th) {
                            s.b("OperateViews", th.getMessage(), th);
                        }
                    }
                }).start();
                a(obj, "");
            }
        } catch (Throwable th) {
            c(obj, th.getMessage());
        }
    }

    public final void L(Object obj, JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        if (jSONObject == null) {
            try {
                jSONObject2.put(CommandMessage.CODE, 1);
                jSONObject2.put("message", "params is null");
                h.a().a(obj, Base64.encodeToString(jSONObject2.toString().getBytes(), 2));
                return;
            } catch (JSONException e2) {
                s.a("OperateViews", e2.getMessage());
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
                    for (int i2 = 0; i2 < length; i2++) {
                        JSONObject jSONObject3 = jSONArray.getJSONObject(i2);
                        String strOptString = jSONObject3.optString("ref", "");
                        int i3 = jSONObject3.getInt(Message.TYPE);
                        JSONObject jSONObject4 = new JSONObject();
                        if (i3 == 1 && !TextUtils.isEmpty(strOptString)) {
                            JSONObject jSONObject5 = new JSONObject();
                            p pVarA = t.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j())).a(strOptString);
                            if (pVarA != null) {
                                s.a("OperateViews", "VideoBean not null");
                                jSONObject5.put(Message.TYPE, 1);
                                jSONObject5.put("videoDataLength", pVarA.c());
                                String strE = pVarA.e();
                                if (TextUtils.isEmpty(strE)) {
                                    s.a("OperateViews", "VideoPath null");
                                    jSONObject5.put(FileDownloadModel.PATH, "");
                                    jSONObject5.put("path4Web", "");
                                } else {
                                    s.a("OperateViews", "VideoPath not null");
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
                                s.a("OperateViews", "VideoBean null");
                            }
                        } else if (i3 == 2 && !TextUtils.isEmpty(strOptString)) {
                            JSONObject jSONObject6 = new JSONObject();
                            jSONObject6.put(Message.TYPE, 2);
                            jSONObject6.put(FileDownloadModel.PATH, H5DownLoadManager.getInstance().getH5ResAddress(strOptString) == null ? "" : H5DownLoadManager.getInstance().getH5ResAddress(strOptString));
                            jSONObject4.put(strOptString, jSONObject6);
                            jSONArray2.put(jSONObject4);
                        } else if (i3 == 3 && !TextUtils.isEmpty(strOptString)) {
                            String str = "";
                            try {
                                File file = new File(strOptString);
                                if (file.exists() && file.isFile() && file.canRead()) {
                                    s.a("OperateViews", "getFileInfo Mraid file " + strOptString);
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
                        } else if (i3 == 4 && !TextUtils.isEmpty(strOptString)) {
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
                } catch (JSONException e3) {
                    s.a("OperateViews", e3.getMessage());
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
        } catch (JSONException e4) {
            s.a("OperateViews", e4.getMessage());
            return;
        }
    }

    public final void b(Object obj, String str) {
        s.a("OperateViews", "reportUrls:" + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                com.mbridge.msdk.click.b.a(com.mbridge.msdk.foundation.controller.a.f().j(), (CampaignEx) null, "", jSONObject.optString("url"), false, jSONObject.optInt(Message.TYPE) != 0);
            }
            a(obj, "");
        } catch (Throwable th) {
            s.b("OperateViews", "reportUrls", th);
        }
    }

    public final void M(Object obj, JSONObject jSONObject) throws JSONException {
        s.a("OperateViews", "createNativeEC:" + jSONObject);
        try {
            String strOptString = jSONObject.optString("unitId");
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
            if (jSONObjectOptJSONObject != null && !TextUtils.isEmpty(strOptString)) {
                String rid = "";
                CampaignEx campaignWithBackData = CampaignEx.parseCampaignWithBackData(jSONObjectOptJSONObject.optJSONObject("campaign"));
                if (campaignWithBackData != null && !TextUtils.isEmpty(strOptString)) {
                    campaignWithBackData.setCampaignUnitId(strOptString);
                    rid = campaignWithBackData.getRequestId();
                }
                com.mbridge.msdk.videocommon.d.c cVarA = com.mbridge.msdk.videocommon.d.c.a(jSONObjectOptJSONObject.optJSONObject("unitSetting"));
                if (cVarA != null) {
                    cVarA.a(strOptString);
                }
                MBridgeBTNativeEC mBridgeBTNativeEC = new MBridgeBTNativeEC(com.mbridge.msdk.foundation.controller.a.f().j());
                mBridgeBTNativeEC.setCampaign(campaignWithBackData);
                j jVar = new j(null, campaignWithBackData);
                jVar.a(strOptString);
                mBridgeBTNativeEC.setJSCommon(jVar);
                mBridgeBTNativeEC.setUnitId(strOptString);
                mBridgeBTNativeEC.setRewardUnitSetting(cVarA);
                com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                if (aVar.a != null) {
                    WindVaneWebView windVaneWebView = aVar.a;
                    rid = windVaneWebView.getRid();
                    mBridgeBTNativeEC.setCreateWebView(windVaneWebView);
                }
                LinkedHashMap<String, View> linkedHashMapB = b(strOptString, rid);
                String strB = b();
                b.put(strB, rid);
                mBridgeBTNativeEC.setInstanceId(strB);
                linkedHashMapB.put(strB, mBridgeBTNativeEC);
                mBridgeBTNativeEC.preLoadData();
                if (campaignWithBackData == null) {
                    c(obj, "campaign is null");
                    s.a("OperateViews", "createNativeEC failed");
                    return;
                }
                a(obj, strB);
                s.a("OperateViews", "createNativeEC instanceId = " + strB);
                return;
            }
            c(obj, "unitId or data is empty");
        } catch (Throwable th) {
            c(obj, th.getMessage());
            s.a("OperateViews", "createNativeEC failed：" + th.getMessage());
        }
    }

    public final void a(int i2, int i3, int i4, int i5, int i6) {
        s.a("OperateViews", "OperateViews setNotchString = " + String.format("%1$s-%2$s-%3$s-%4$s-%5$s", Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6)));
        k = com.mbridge.msdk.foundation.tools.i.a(i2, i3, i4, i5, i6);
        l = i2;
        m = i3;
        n = i4;
        o = i5;
        p = i6;
    }

    public final void a(String str, String str2, String str3) {
        try {
            LinkedHashMap<String, View> linkedHashMapB = a.a.b(str, str2);
            if (linkedHashMapB == null || linkedHashMapB.size() <= 0) {
                return;
            }
            for (View view : linkedHashMapB.values()) {
                if (view instanceof MBTempContainer) {
                    ((MBTempContainer) view).notifyEvent(str3);
                } else if (view instanceof MBridgeBTWebView) {
                    ((MBridgeBTWebView) view).notifyEvent(str3);
                } else if (view instanceof MBridgeBTLayout) {
                    ((MBridgeBTLayout) view).notifyEvent(str3);
                }
            }
        } catch (Throwable th) {
            s.a("OperateViews", th.getMessage());
        }
    }

    static /* synthetic */ void a(c cVar, String str, CampaignEx campaignEx) {
        if (com.mbridge.msdk.foundation.same.a.d.d == null || TextUtils.isEmpty(campaignEx.getId())) {
            return;
        }
        com.mbridge.msdk.foundation.same.a.d.b(str, campaignEx, "reward");
    }
}
