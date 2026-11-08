package com.mbridge.msdk.video.js.bridge;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import android.webkit.WebView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.heytap.mcssdk.mode.CommandMessage;
import com.heytap.mcssdk.mode.Message;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.CampaignUnit;
import com.mbridge.msdk.foundation.entity.DomainCampaignEx;
import com.mbridge.msdk.foundation.entity.d;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.mbjscommon.windvane.AbsMbridgeDownload;
import com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView;
import com.mbridge.msdk.mbjscommon.windvane.a;
import com.mbridge.msdk.mbjscommon.windvane.h;
import com.mbridge.msdk.video.bt.module.a.b;
import com.mbridge.msdk.video.js.a.j;
import com.mbridge.msdk.video.js.c;
import com.mbridge.msdk.video.js.factory.IJSFactory;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class BaseVideoBridge extends AbsMbridgeDownload implements IVideoBridge {
    protected IJSFactory a;

    private String a(int i) {
        switch (i) {
            case 1:
                return "sdk_info";
            case 2:
                return MBridgeConstans.PROPERTIES_UNIT_ID;
            case 3:
                return "appSetting";
            case 4:
                return "unitSetting";
            case 5:
                return "device";
            case 6:
                return "sdkSetting";
            default:
                return "";
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.windvane.j
    public void initialize(Object obj, WindVaneWebView windVaneWebView) {
        super.initialize(obj, windVaneWebView);
        if (obj instanceof IJSFactory) {
            this.a = (IJSFactory) obj;
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void init(Object obj, String str) {
        s.b("JS-Video-Brigde", "init");
        try {
            int i = 1;
            if (this.a != null) {
                String strD = this.a.getJSCommon().d();
                if (!TextUtils.isEmpty(strD)) {
                    strD = Base64.encodeToString(strD.getBytes(), 2);
                }
                h.a().a(obj, strD);
                this.a.getJSCommon().b(true);
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                JSONObject jSONObject = new JSONObject(str);
                int iOptInt = jSONObject.optInt("showTransparent");
                int iOptInt2 = jSONObject.optInt(CampaignEx.JSON_NATIVE_VIDEO_MUTE);
                int iOptInt3 = jSONObject.optInt("closeType");
                int iOptInt4 = jSONObject.optInt("orientationType");
                int iOptInt5 = jSONObject.optInt("h5cbp");
                int iOptInt6 = jSONObject.optInt("webfront");
                int iOptInt7 = jSONObject.optInt("showAlertRole");
                this.a.getJSCommon().a(iOptInt == 1);
                this.a.getJSCommon().b(iOptInt2);
                this.a.getJSCommon().c(iOptInt3);
                this.a.getJSCommon().d(iOptInt4);
                this.a.getJSCommon().e(iOptInt5);
                this.a.getJSCommon().f(iOptInt6);
                c jSCommon = this.a.getJSCommon();
                if (iOptInt7 == 0) {
                    iOptInt7 = 1;
                }
                jSCommon.i(iOptInt7);
                return;
            }
            if (obj != null) {
                a aVar = (a) obj;
                if (aVar.a.getObject() instanceof j) {
                    j jVar = (j) aVar.a.getObject();
                    String strD2 = jVar.d();
                    if (!TextUtils.isEmpty(str)) {
                        JSONObject jSONObject2 = new JSONObject(str);
                        int iOptInt8 = jSONObject2.optInt("showTransparent");
                        int iOptInt9 = jSONObject2.optInt(CampaignEx.JSON_NATIVE_VIDEO_MUTE);
                        int iOptInt10 = jSONObject2.optInt("closeType");
                        int iOptInt11 = jSONObject2.optInt("orientationType");
                        int iOptInt12 = jSONObject2.optInt("h5cbp");
                        int iOptInt13 = jSONObject2.optInt("webfront");
                        int iOptInt14 = jSONObject2.optInt("showAlertRole");
                        jVar.a(iOptInt8 == 1);
                        jVar.b(iOptInt9);
                        jVar.c(iOptInt10);
                        jVar.d(iOptInt11);
                        jVar.e(iOptInt12);
                        jVar.f(iOptInt13);
                        if (iOptInt14 != 0) {
                            i = iOptInt14;
                        }
                        jVar.i(i);
                        s.b("JS-Video-Brigde", "init jsCommon.setIsShowingTransparent = " + iOptInt8);
                    }
                    h.a().a(obj, Base64.encodeToString(strD2.getBytes(), 2));
                }
            }
        } catch (Throwable th) {
            s.b("JS-Video-Brigde", "init error", th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void click(final Object obj, String str) {
        String strOptString;
        int iOptInt;
        j jVar;
        Context context;
        s.b("JS-Video-Brigde", CampaignEx.JSON_NATIVE_VIDEO_CLICK);
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                iOptInt = jSONObject.optInt(Message.TYPE);
                strOptString = jSONObject.optString("pt");
            } catch (JSONException e) {
                e.printStackTrace();
                strOptString = "";
                iOptInt = 1;
            }
            b bVar = new b() { // from class: com.mbridge.msdk.video.js.bridge.BaseVideoBridge.1
                @Override // com.mbridge.msdk.video.bt.module.a.b
                public final void a() {
                    if (obj != null) {
                        a aVar = (a) obj;
                        s.a("========", "============onInstallAlertShow");
                        h.a().a((WebView) aVar.a, "onInstallAlertShow", "");
                        BaseVideoBridge.this.a.getJSVideoModule().videoOperate(5);
                    }
                }

                @Override // com.mbridge.msdk.video.bt.module.a.b
                public final void b() {
                    if (obj != null) {
                        a aVar = (a) obj;
                        s.a("========", "============onInstallAlertHide");
                        h.a().a((WebView) aVar.a, "onInstallAlertHide", "");
                        BaseVideoBridge.this.a.getJSVideoModule().videoOperate(4);
                    }
                }
            };
            if (this.a != null) {
                if (obj != null) {
                    a aVar = (a) obj;
                    if (aVar.a != null && aVar.a.getContext() != null && (context = aVar.a.getContext()) != null && context != context.getApplicationContext()) {
                        this.a.getJSCommon().a(context);
                    }
                }
                this.a.getJSCommon().a(bVar);
                this.a.getJSCommon().click(iOptInt, strOptString);
                return;
            }
            if (obj != null) {
                a aVar2 = (a) obj;
                if (!(aVar2.a.getObject() instanceof j) || (jVar = (j) aVar2.a.getObject()) == null) {
                    return;
                }
                Context context2 = aVar2.a.getContext();
                if (context2 != null && context2 != context2.getApplicationContext()) {
                    jVar.a(context2);
                }
                jVar.a(bVar);
                jVar.click(iOptInt, strOptString);
            }
        } catch (Throwable th) {
            s.b("JS-Video-Brigde", "click error", th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void statistics(Object obj, String str) {
        s.b("JS-Video-Brigde", "statistics,params:" + str);
        try {
            if (this.a == null || TextUtils.isEmpty(str)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(str);
            this.a.getJSCommon().a(jSONObject.optInt(Message.TYPE), jSONObject.optString("data"));
        } catch (Throwable th) {
            s.b("JS-Video-Brigde", "statistics error", th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void triggerCloseBtn(Object obj, String str) {
        s.b("JS-Video-Brigde", "triggerCloseBtn");
        try {
            if (this.a == null || TextUtils.isEmpty(str) || !new JSONObject(str).optString("state").equals(CampaignEx.JSON_NATIVE_VIDEO_CLICK)) {
                return;
            }
            this.a.getJSVideoModule().closeVideoOperate(1, -1);
            h.a().a(obj, b(0));
        } catch (Throwable th) {
            s.b("JS-Video-Brigde", "triggerCloseBtn error", th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void showVideoLocation(Object obj, String str) {
        try {
            if (this.a == null || TextUtils.isEmpty(str)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(str);
            int iOptInt = jSONObject.optInt("margin_top", 0);
            int iOptInt2 = jSONObject.optInt("margin_left", 0);
            int iOptInt3 = jSONObject.optInt("view_width", 0);
            int iOptInt4 = jSONObject.optInt("view_height", 0);
            int iOptInt5 = jSONObject.optInt("radius", 0);
            int iOptInt6 = jSONObject.optInt("border_top", 0);
            int iOptInt7 = jSONObject.optInt("border_left", 0);
            int iOptInt8 = jSONObject.optInt("border_width", 0);
            int iOptInt9 = jSONObject.optInt("border_height", 0);
            s.b("JS-Video-Brigde", "showVideoLocation,margin_top:" + iOptInt + ",marginLeft:" + iOptInt2 + ",viewWidth:" + iOptInt3 + ",viewHeight:" + iOptInt4 + ",radius:" + iOptInt5 + ",borderTop: " + iOptInt6 + ",borderLeft: " + iOptInt7 + ",borderWidth: " + iOptInt8 + ",borderHeight: " + iOptInt9);
            this.a.getJSVideoModule().showVideoLocation(iOptInt, iOptInt2, iOptInt3, iOptInt4, iOptInt5, iOptInt6, iOptInt7, iOptInt8, iOptInt9);
            this.a.getJSCommon().g();
        } catch (Throwable th) {
            s.b("JS-Video-Brigde", "showVideoLocation error", th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void soundOperate(Object obj, String str) {
        try {
            if (this.a == null || TextUtils.isEmpty(str)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(str);
            int iOptInt = jSONObject.optInt(CampaignEx.JSON_NATIVE_VIDEO_MUTE);
            int iOptInt2 = jSONObject.optInt("view_visible");
            String strOptString = jSONObject.optString("pt", "");
            s.b("JS-Video-Brigde", "soundOperate,mute:" + iOptInt + ",viewVisible:" + iOptInt2 + ",pt:" + strOptString);
            if (TextUtils.isEmpty(strOptString)) {
                this.a.getJSVideoModule().soundOperate(iOptInt, iOptInt2);
            } else {
                this.a.getJSVideoModule().soundOperate(iOptInt, iOptInt2, strOptString);
            }
            h.a().a(obj, b(0));
        } catch (Throwable th) {
            s.b("JS-Video-Brigde", "soundOperate error", th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void videoOperate(Object obj, String str) {
        try {
            if (this.a == null || TextUtils.isEmpty(str)) {
                return;
            }
            int iOptInt = new JSONObject(str).optInt("pause_or_resume");
            s.b("JS-Video-Brigde", "videoOperate,pauseOrResume:" + iOptInt);
            this.a.getJSVideoModule().videoOperate(iOptInt);
            h.a().a(obj, b(0));
        } catch (Throwable th) {
            s.b("JS-Video-Brigde", "videoOperate error", th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void closeVideoOperte(Object obj, String str) {
        try {
            if (this.a == null || TextUtils.isEmpty(str)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(str);
            int iOptInt = jSONObject.optInt(CampaignEx.JSON_NATIVE_VIDEO_CLOSE);
            int iOptInt2 = jSONObject.optInt("view_visible");
            s.b("JS-Video-Brigde", "closeVideoOperte,close:" + iOptInt + ",viewVisible:" + iOptInt2);
            this.a.getJSVideoModule().closeVideoOperate(iOptInt, iOptInt2);
            h.a().a(obj, b(0));
        } catch (Throwable th) {
            s.b("JS-Video-Brigde", "closeOperte error", th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void progressOperate(Object obj, String str) {
        try {
            if (this.a == null || TextUtils.isEmpty(str)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(str);
            int iOptInt = jSONObject.optInt("progress");
            int iOptInt2 = jSONObject.optInt("view_visible");
            s.b("JS-Video-Brigde", "progressOperate,progress:" + iOptInt + ",viewVisible:" + iOptInt2);
            this.a.getJSVideoModule().progressOperate(iOptInt, iOptInt2);
            h.a().a(obj, b(0));
        } catch (Throwable th) {
            s.b("JS-Video-Brigde", "progressOperate error", th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void getCurrentProgress(Object obj, String str) {
        try {
            if (this.a != null) {
                String currentProgress = this.a.getJSVideoModule().getCurrentProgress();
                s.b("JS-Video-Brigde", "getCurrentProgress:" + currentProgress);
                if (!TextUtils.isEmpty(currentProgress)) {
                    currentProgress = Base64.encodeToString(currentProgress.getBytes(), 2);
                }
                h.a().a(obj, currentProgress);
            }
        } catch (Throwable th) {
            s.b("JS-Video-Brigde", "getCurrentProgress error", th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void showVideoClickView(Object obj, String str) {
        try {
            if (this.a == null || TextUtils.isEmpty(str)) {
                return;
            }
            int iOptInt = new JSONObject(str).optInt(Message.TYPE);
            s.b("JS-Video-Brigde", "showVideoClickView,type:" + iOptInt);
            this.a.getJSContainerModule().showVideoClickView(iOptInt);
        } catch (Throwable th) {
            s.b("JS-Video-Brigde", "showVideoClickView error", th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void setScaleFitXY(Object obj, String str) {
        try {
            if (this.a == null || TextUtils.isEmpty(str)) {
                return;
            }
            int iOptInt = new JSONObject(str).optInt("fitxy");
            s.b("JS-Video-Brigde", "setScaleFitXY,type:" + iOptInt);
            this.a.getJSVideoModule().setScaleFitXY(iOptInt);
        } catch (Throwable th) {
            s.b("JS-Video-Brigde", "showVideoClickView error", th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void notifyCloseBtn(Object obj, String str) {
        try {
            if (this.a == null || TextUtils.isEmpty(str)) {
                return;
            }
            int iOptInt = new JSONObject(str).optInt("state");
            s.b("JS-Video-Brigde", "notifyCloseBtn,result:" + iOptInt);
            this.a.getJSVideoModule().notifyCloseBtn(iOptInt);
        } catch (Throwable th) {
            s.b("JS-Video-Brigde", "notifyCloseBtn", th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void openURL(Object obj, String str) {
        s.d("JS-Video-Brigde", "openURL:" + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("url");
            int iOptInt = jSONObject.optInt(Message.TYPE);
            if (iOptInt == 1) {
                com.mbridge.msdk.click.c.a(this.mContext, strOptString);
            } else if (iOptInt == 2) {
                com.mbridge.msdk.click.c.b(this.mContext, strOptString);
            }
        } catch (JSONException e) {
            s.d("JS-Video-Brigde", e.getMessage());
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", th.getMessage());
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void cai(Object obj, String str) throws JSONException {
        s.a("JS-Video-Brigde", "cai:" + str);
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
                s.a("JS-Video-Brigde", e.getMessage());
            }
        } catch (JSONException e2) {
            com.mbridge.msdk.mbjscommon.bridge.b.a(obj, "exception: " + e2.getLocalizedMessage());
            s.b("JS-Video-Brigde", "cai", e2);
        } catch (Throwable th) {
            com.mbridge.msdk.mbjscommon.bridge.b.a(obj, "exception: " + th.getLocalizedMessage());
            s.b("JS-Video-Brigde", "cai", th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void gial(Object obj, String str) throws JSONException {
        s.a("JS-Video-Brigde", "gial:" + str);
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
            s.a("JS-Video-Brigde", e.getMessage());
        } catch (Throwable th) {
            com.mbridge.msdk.mbjscommon.bridge.b.a(obj, th.getMessage());
            s.a("JS-Video-Brigde", th.getMessage());
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void showAlertView(Object obj, String str) {
        s.b("JS-Video-Brigde", "showAlertView");
        try {
            if (this.a == null || TextUtils.isEmpty(str)) {
                return;
            }
            this.a.getJSVideoModule().showIVRewardAlertView(str);
            h.a().a(obj, "showAlertView", "");
        } catch (Throwable th) {
            s.b("JS-Video-Brigde", "showAlertView", th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void toggleCloseBtn(Object obj, String str) {
        try {
            if (this.a == null || TextUtils.isEmpty(str)) {
                return;
            }
            int iOptInt = new JSONObject(str).optInt("state");
            s.b("JS-Video-Brigde", "toggleCloseBtn,result:" + iOptInt);
            int i = 2;
            if (iOptInt != 1) {
                i = iOptInt == 2 ? 1 : 0;
            }
            this.a.getJSVideoModule().closeVideoOperate(0, i);
        } catch (Throwable th) {
            s.b("JS-Video-Brigde", "toggleCloseBtn", th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void handlerH5Exception(Object obj, String str) {
        try {
            if (this.a == null || TextUtils.isEmpty(str)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(str);
            s.b("JS-Video-Brigde", "handlerH5Exception,params:" + str);
            this.a.getJSCommon().handlerH5Exception(jSONObject.optInt(CommandMessage.CODE, -999), jSONObject.optString("message", "h5 error"));
        } catch (Throwable th) {
            s.b("JS-Video-Brigde", "handlerH5Exception", th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void isSystemResume(Object obj, String str) {
        try {
            if (this.a != null) {
                s.b("JS-Video-Brigde", "isSystemResume,params:" + str);
                h.a().a(obj, b(this.a.getActivityProxy().h()));
            }
        } catch (Throwable th) {
            s.b("JS-Video-Brigde", "isSystemResume", th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void readyStatus(Object obj, String str) {
        if (obj != null) {
            try {
                a aVar = (a) obj;
                int iOptInt = new JSONObject(str).optInt("isReady", 1);
                if (aVar.a.getObject() instanceof j) {
                    ((j) aVar.a.getObject()).j(iOptInt);
                }
                if (aVar.a != null) {
                    WindVaneWebView windVaneWebView = aVar.a;
                    if (windVaneWebView.getWebViewListener() != null) {
                        windVaneWebView.getWebViewListener().a(windVaneWebView, iOptInt);
                    }
                }
            } catch (Throwable th) {
                s.b("JS-Video-Brigde", "readyStatus", th);
            }
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void playVideoFinishOperate(Object obj, String str) {
        try {
            if (TextUtils.isEmpty(str) || this.a == null || TextUtils.isEmpty(str)) {
                return;
            }
            int iOptInt = new JSONObject(str).optInt(Message.TYPE);
            s.b("JS-Video-Brigde", "playVideoFinishOperate,type: " + iOptInt);
            this.a.getJSCommon().g(iOptInt);
        } catch (Throwable th) {
            s.b("JS-Video-Brigde", "playVideoFinishOperate error", th);
        }
    }

    private String b(int i) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CommandMessage.CODE, i);
            String string = jSONObject.toString();
            return !TextUtils.isEmpty(string) ? Base64.encodeToString(string.getBytes(), 2) : "";
        } catch (Throwable unused) {
            s.d("JS-Video-Brigde", "code to string is error");
            return "";
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void reactDeveloper(Object obj, String str) {
        s.a("JS-Video-Brigde", "reactDeveloper");
        try {
            if (this.a != null && !TextUtils.isEmpty(str)) {
                this.a.getJSBTModule().reactDeveloper(obj, str);
            } else {
                h.a().b(obj, b(1));
            }
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "reactDeveloper error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void reportUrls(Object obj, String str) {
        s.a("JS-Video-Brigde", "reportUrls");
        try {
            if (!TextUtils.isEmpty(str)) {
                com.mbridge.msdk.video.bt.a.c.a().b(obj, str);
            } else {
                h.a().b(obj, b(1));
            }
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "reportUrls error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void getFileInfo(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().L(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.b("JS-Video-Brigde", "getFileInfo error", th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void getSDKInfo(Object obj, String str) {
        s.b("JS-Video-Brigde", "getSDKInfo");
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONArray jSONArray = new JSONObject(str).getJSONArray(Message.TYPE);
                JSONObject jSONObject = new JSONObject();
                int i = 0;
                if (this.a != null) {
                    while (i < jSONArray.length()) {
                        int i2 = jSONArray.getInt(i);
                        jSONObject.put(a(i2), this.a.getJSCommon().h(i2));
                        i++;
                    }
                } else if (obj != null) {
                    while (i < jSONArray.length()) {
                        int i3 = jSONArray.getInt(i);
                        a aVar = (a) obj;
                        if (aVar.a.getObject() instanceof j) {
                            jSONObject.put(a(i3), ((j) aVar.a.getObject()).h(i3));
                        }
                        i++;
                    }
                }
                h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
                return;
            }
            h.a().b(obj, "params is null");
        } catch (Throwable th) {
            s.b("JS-Video-Brigde", "getSDKInfo error", th);
            h.a().b(obj, "exception");
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void loadads(Object obj, String str) {
        String str2;
        String str3;
        int i;
        int iOptInt;
        s.b("JS-Video-Brigde", "loadads");
        try {
            if (TextUtils.isEmpty(str)) {
                str2 = "";
                str3 = "";
                i = 1;
                iOptInt = 1;
            } else {
                JSONObject jSONObject = new JSONObject(str);
                String strOptString = jSONObject.optString(MBridgeConstans.PLACEMENT_ID);
                String strOptString2 = jSONObject.optString("unitId");
                int iOptInt2 = jSONObject.optInt(Message.TYPE, 1);
                if (iOptInt2 > 2) {
                    iOptInt2 = 1;
                }
                str2 = strOptString2;
                iOptInt = jSONObject.optInt("adtype", 1);
                str3 = strOptString;
                i = iOptInt2;
            }
            if (TextUtils.isEmpty(str2)) {
                h.a().a(obj, b(1));
                return;
            }
            if (obj != null) {
                a aVar = (a) obj;
                if (aVar.a instanceof WindVaneWebView) {
                    WindVaneWebView windVaneWebView = aVar.a;
                    if (windVaneWebView.getWebViewListener() != null) {
                        ((com.mbridge.msdk.mbjscommon.c.a) windVaneWebView.getWebViewListener()).a(windVaneWebView, str3, str2, i, iOptInt);
                    }
                }
            }
            h.a().a(obj, b(0));
        } catch (Throwable th) {
            s.b("JS-Video-Brigde", "loadads error", th);
            h.a().a(obj, b(1));
        }
    }

    private void a(Object obj, String str) throws JSONException {
        try {
            if (TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(CommandMessage.CODE, 1);
                jSONObject.put("message", "params is null");
                h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
            }
        } catch (Exception e) {
            s.a("JS-Video-Brigde", e.getMessage());
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void createWebview(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().a(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "createWebview error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void createPlayerView(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().b(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "createPlayerView error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void createSubPlayTemplateView(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().c(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "createSubPlayTemplateView error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void destroyComponent(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().d(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "destroyComponent error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void getComponentOptions(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().e(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "getComponentOptions error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void setViewRect(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().f(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "setViewRect error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void removeFromSuperView(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().g(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "removeFromSuperView error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void appendSubView(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().h(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "appendSubView error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void appendViewTo(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().i(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "appendViewTo error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void bringViewToFront(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().j(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "bringViewToFront error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void hideView(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().k(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "hideView error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void showView(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().l(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "showView error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void setViewBgColor(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().m(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "setViewBgColor error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void setViewAlpha(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().n(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "setViewAlpha error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void setViewScale(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().o(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "setViewScale error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void insertViewAbove(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().p(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "insertViewAbove error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void insertViewBelow(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().q(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "insertViewBelow error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void webviewLoad(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().r(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "webviewLoad error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void webviewReload(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().s(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "webviewReload error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void webviewGoBack(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().t(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "webviewGoBack error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void webviewGoForward(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().u(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "webviewGoForward error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void playerPlay(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().v(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "playerPlay error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void playerPause(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().w(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "playerPause error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void playerResume(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().x(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "playerResume error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void playerStop(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().y(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "playerStop error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void playerUpdateFrame(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().z(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "playerUpdateFrame error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void playerMute(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().A(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "playerMute error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void playerUnmute(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().B(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "playerUnmute error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void playerGetMuteState(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().C(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "playerGetMuteState error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void playerSetSource(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().D(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "playerSetSource error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void playerSetRenderType(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().E(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "playerSetRenderType error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void preloadSubPlayTemplateView(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            if (this.a != null) {
                com.mbridge.msdk.video.bt.a.c.a().F(obj, new JSONObject(str));
            } else {
                a aVar = (a) obj;
                if (aVar.a instanceof WindVaneWebView) {
                    WindVaneWebView windVaneWebView = aVar.a;
                    if (windVaneWebView.getWebViewListener() != null) {
                        ((com.mbridge.msdk.mbjscommon.c.a) windVaneWebView.getWebViewListener()).a(obj, str);
                        s.a("JS-Video-Brigde", "preloadSubPlayTemplateView: RVWebViewListener");
                    } else {
                        s.a("JS-Video-Brigde", "preloadSubPlayTemplateView: failed");
                    }
                }
            }
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "preloadSubPlayTemplateView error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void closeAd(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().G(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "closeAd error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void broadcast(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().H(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "broadcast error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void closeWeb(Object obj, String str) {
        s.d("JS-Video-Brigde", Message.TYPE + str);
        try {
            if (TextUtils.isEmpty(str) || this.a == null || TextUtils.isEmpty(str)) {
                return;
            }
            int iOptInt = new JSONObject(str).optInt("status");
            this.a.getJSContainerModule().hideAlertWebview();
            this.a.getJSVideoModule().hideAlertView(iOptInt);
        } catch (Throwable th) {
            s.b("JS-Video-Brigde", "closeWeb", th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void ivRewardAdsWithoutVideo(Object obj, String str) {
        s.d("JS-Video-Brigde", "ivRewardAdsWithoutVideo ： params" + str);
        try {
            if (TextUtils.isEmpty(str) || this.a == null || TextUtils.isEmpty(str)) {
                return;
            }
            this.a.getJSContainerModule().ivRewardAdsWithoutVideo(str);
        } catch (Throwable th) {
            s.b("JS-Video-Brigde", "ivRewardAdsWithoutVideo", th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void setSubPlayTemplateInfo(Object obj, String str) throws JSONException {
        s.d("JS-Video-Brigde", "setSubPlayTemplateInfo : " + str);
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().I(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "setSubPlayTemplateInfo error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void webviewFireEvent(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().J(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "setSubPlayTemplateInfo error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void increaseOfferFrequence(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().K(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "increaseOfferFrequence error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void handleNativeObject(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.b.a().a(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "handleNativeObject error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void loadingResourceStatus(Object obj, String str) throws JSONException {
        a(obj, str);
        if (obj != null) {
            try {
                a aVar = (a) obj;
                int iOptInt = new JSONObject(str).optInt("isReady", 1);
                if (aVar.a != null) {
                    WindVaneWebView windVaneWebView = aVar.a;
                    if (windVaneWebView.getWebViewListener() != null) {
                        windVaneWebView.getWebViewListener().c(windVaneWebView, iOptInt);
                    }
                }
            } catch (Throwable th) {
                s.d("JS-Video-Brigde", "loadingResourceStatus error " + th);
            }
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void createNativeEC(Object obj, String str) throws JSONException {
        a(obj, str);
        try {
            com.mbridge.msdk.video.bt.a.c.a().M(obj, new JSONObject(str));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "createNativeEC error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void setCacheItem(Object obj, String str) {
        String str2 = "";
        int i = 0;
        try {
            SharedPreferences sharedPreferences = com.mbridge.msdk.foundation.controller.a.f().j().getSharedPreferences("MBridgeH5CacheSP", 0);
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString(DomainCampaignEx.LOOPBACK_KEY);
                String string2 = jSONObject.getString("value");
                if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                    sharedPreferences.edit().putString(string, string2).apply();
                }
                str2 = "Save Success";
                i = 1;
            }
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "setCacheItem error " + th);
            str2 = "Save Error, reason is : " + th.getMessage();
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(CommandMessage.CODE, i ^ 1);
            jSONObject2.put("message", str2);
            if (obj != null && i != 0) {
                h.a().a(obj, Base64.encodeToString(jSONObject2.toString().getBytes(), 2));
            } else {
                h.a().b(obj, Base64.encodeToString(jSONObject2.toString().getBytes(), 2));
            }
        } catch (Throwable unused) {
            s.d("JS-Video-Brigde", "setCacheItem error ");
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void removeCacheItem(Object obj, String str) {
        String str2 = "";
        int i = 0;
        try {
            SharedPreferences sharedPreferences = com.mbridge.msdk.foundation.controller.a.f().j().getSharedPreferences("MBridgeH5CacheSP", 0);
            if (!TextUtils.isEmpty(str)) {
                String string = new JSONObject(str).getString(DomainCampaignEx.LOOPBACK_KEY);
                if (!TextUtils.isEmpty(string)) {
                    sharedPreferences.edit().remove(string).apply();
                }
                str2 = "Delete Success";
                i = 1;
            }
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "removeCacheItem error " + th);
            str2 = "Delete Error, reason is : " + th.getMessage();
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CommandMessage.CODE, i ^ 1);
            jSONObject.put("message", str2);
            if (obj != null && i != 0) {
                h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
            } else {
                h.a().b(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
            }
        } catch (Throwable unused) {
            s.d("JS-Video-Brigde", "removeCacheItem error ");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x007f A[Catch: Throwable -> 0x00a3, TryCatch #2 {Throwable -> 0x00a3, blocks: (B:15:0x006e, B:17:0x007f, B:20:0x008e, B:18:0x0085), top: B:29:0x006e }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0085 A[Catch: Throwable -> 0x00a3, TryCatch #2 {Throwable -> 0x00a3, blocks: (B:15:0x006e, B:17:0x007f, B:20:0x008e, B:18:0x0085), top: B:29:0x006e }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x008e A[Catch: Throwable -> 0x00a3, TRY_LEAVE, TryCatch #2 {Throwable -> 0x00a3, blocks: (B:15:0x006e, B:17:0x007f, B:20:0x008e, B:18:0x0085), top: B:29:0x006e }] */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void getAllCache(java.lang.Object r6, java.lang.String r7) {
        /*
            r5 = this;
            r7 = 0
            r0 = 0
            com.mbridge.msdk.foundation.controller.a r1 = com.mbridge.msdk.foundation.controller.a.f()     // Catch: java.lang.Throwable -> L40
            android.content.Context r1 = r1.j()     // Catch: java.lang.Throwable -> L40
            java.lang.String r2 = "MBridgeH5CacheSP"
            android.content.SharedPreferences r1 = r1.getSharedPreferences(r2, r7)     // Catch: java.lang.Throwable -> L40
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L40
            r2.<init>()     // Catch: java.lang.Throwable -> L40
            java.util.Map r0 = r1.getAll()     // Catch: java.lang.Throwable -> L3e
            java.util.Set r0 = r0.entrySet()     // Catch: java.lang.Throwable -> L3e
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L3e
        L21:
            boolean r1 = r0.hasNext()     // Catch: java.lang.Throwable -> L3e
            if (r1 == 0) goto L3b
            java.lang.Object r1 = r0.next()     // Catch: java.lang.Throwable -> L3e
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch: java.lang.Throwable -> L3e
            java.lang.Object r3 = r1.getKey()     // Catch: java.lang.Throwable -> L3e
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Throwable -> L3e
            java.lang.Object r1 = r1.getValue()     // Catch: java.lang.Throwable -> L3e
            r2.put(r3, r1)     // Catch: java.lang.Throwable -> L3e
            goto L21
        L3b:
            java.lang.String r0 = "getAllCache Success"
            goto L6e
        L3e:
            r0 = move-exception
            goto L43
        L40:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L43:
            java.lang.String r1 = "JS-Video-Brigde"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "getAllCache error "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            com.mbridge.msdk.foundation.tools.s.d(r1, r3)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "getAllCache Error, reason is : "
            r1.append(r3)
            java.lang.String r0 = r0.getMessage()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
        L6e:
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: java.lang.Throwable -> La3
            r1.<init>()     // Catch: java.lang.Throwable -> La3
            java.lang.String r3 = "code"
            r1.put(r3, r7)     // Catch: java.lang.Throwable -> La3
            java.lang.String r7 = "message"
            r1.put(r7, r0)     // Catch: java.lang.Throwable -> La3
            if (r2 == 0) goto L85
            java.lang.String r7 = "data"
            r1.put(r7, r2)     // Catch: java.lang.Throwable -> La3
            goto L8c
        L85:
            java.lang.String r7 = "data"
            java.lang.String r0 = "{}"
            r1.put(r7, r0)     // Catch: java.lang.Throwable -> La3
        L8c:
            if (r6 == 0) goto Lba
            com.mbridge.msdk.mbjscommon.windvane.h r7 = com.mbridge.msdk.mbjscommon.windvane.h.a()     // Catch: java.lang.Throwable -> La3
            java.lang.String r0 = r1.toString()     // Catch: java.lang.Throwable -> La3
            byte[] r0 = r0.getBytes()     // Catch: java.lang.Throwable -> La3
            r1 = 2
            java.lang.String r0 = android.util.Base64.encodeToString(r0, r1)     // Catch: java.lang.Throwable -> La3
            r7.a(r6, r0)     // Catch: java.lang.Throwable -> La3
            goto Lba
        La3:
            r6 = move-exception
            java.lang.String r7 = "JS-Video-Brigde"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "getAllCache error "
            r0.append(r1)
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            com.mbridge.msdk.foundation.tools.s.d(r7, r6)
        Lba:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.video.js.bridge.BaseVideoBridge.getAllCache(java.lang.Object, java.lang.String):void");
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void clearAllCache(Object obj, String str) {
        try {
            com.mbridge.msdk.foundation.controller.a.f().j().getSharedPreferences("MBridgeH5CacheSP", 0).edit().clear().apply();
            if (obj != null) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(CommandMessage.CODE, 0);
                jSONObject.put("message", "Success");
                h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
            }
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "getAllCache error " + th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void getCutout(Object obj, String str) {
        try {
            String strJ = this.a.getJSCommon().j();
            s.d("JS-Video-Brigde", strJ);
            if (obj != null && !TextUtils.isEmpty(strJ)) {
                h.a().a(obj, Base64.encodeToString(strJ.getBytes(), 2));
            } else {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(CommandMessage.CODE, 1);
                jSONObject.put("message", "No notch data, plz try again later.");
                h.a().b(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
            }
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "getCutout error : " + th.getMessage());
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void getAppSetting(Object obj, String str) {
        JSONObject jSONObject;
        try {
            String strOptString = new JSONObject(str).optString(HiAnalyticsConstant.HaKey.BI_KEY_APPID, "");
            JSONObject jSONObject2 = new JSONObject();
            if (!TextUtils.isEmpty(strOptString)) {
                String strC = com.mbridge.msdk.b.b.a().c(strOptString);
                if (TextUtils.isEmpty(strC)) {
                    jSONObject = new JSONObject(com.mbridge.msdk.b.b.a().b().az());
                } else {
                    jSONObject = new JSONObject(strC);
                    jSONObject.put("isDefault", 0);
                }
                if (obj != null) {
                    jSONObject2.put(CommandMessage.CODE, 0);
                    jSONObject2.put("message", "Success");
                    jSONObject2.put("data", jSONObject);
                } else {
                    jSONObject2.put(CommandMessage.CODE, 1);
                    jSONObject2.put("message", "Get App Setting error, plz try again later.");
                }
            } else {
                jSONObject2.put(CommandMessage.CODE, 1);
                jSONObject2.put("message", "Get App Setting error, because must give a appId.");
            }
            h.a().a(obj, Base64.encodeToString(jSONObject2.toString().getBytes(), 2));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "getAppSetting error : " + th.getMessage());
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void getRewardSetting(Object obj, String str) {
        try {
            JSONObject jSONObjectJ = com.mbridge.msdk.videocommon.d.b.a().b().j();
            JSONObject jSONObject = new JSONObject();
            if (obj != null && jSONObjectJ != null) {
                jSONObject.put(CommandMessage.CODE, 0);
                jSONObject.put("message", "Success");
                jSONObject.put("data", jSONObjectJ);
            } else {
                jSONObject.put(CommandMessage.CODE, 1);
                jSONObject.put("message", "Get Reward Setting error, plz try again later.");
            }
            h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "getRewardSetting error : " + th.getMessage());
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void getRewardUnitSetting(Object obj, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString(HiAnalyticsConstant.HaKey.BI_KEY_APPID, "");
            String strOptString2 = jSONObject.optString("unitid", "");
            JSONObject jSONObject2 = new JSONObject();
            if (!TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2)) {
                JSONObject jSONObjectG = com.mbridge.msdk.videocommon.d.b.a().a(strOptString, strOptString2).G();
                if (obj != null && jSONObjectG != null) {
                    jSONObject2.put(CommandMessage.CODE, 0);
                    jSONObject2.put("message", "Success");
                    jSONObject2.put("data", jSONObjectG);
                } else {
                    jSONObject2.put(CommandMessage.CODE, 1);
                    jSONObject2.put("message", "Get Reward Unit Setting error, plz try again later.");
                }
            } else {
                jSONObject2.put(CommandMessage.CODE, 1);
                jSONObject2.put("message", "Get reward unit Setting error, because must give appId and unitId.");
            }
            h.a().a(obj, Base64.encodeToString(jSONObject2.toString().getBytes(), 2));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "getRewardUnitSetting error : " + th.getMessage());
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void getUnitSetting(Object obj, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CommandMessage.CODE, 1);
            jSONObject.put("message", "Get Unit Setting error, RV/IV can not support this method.");
            h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "getUnitSetting error : " + th.getMessage());
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.windvane.AbsMbridgeDownload
    public void sendNoticeAndCallBackClick(Object obj, String str) {
        click(obj, "{ \"type\":1,\"pt\":" + str + "}");
    }

    @Override // com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void getEncryptPrice(Object obj, String str) {
        String str2;
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            int i = 1;
            if (TextUtils.isEmpty(str)) {
                str2 = "params is null";
            } else {
                JSONObject jSONObject3 = new JSONObject(str);
                String strOptString = jSONObject3.optString("unitid", "");
                String strOptString2 = jSONObject3.optString("requestId", "");
                if (TextUtils.isEmpty(strOptString) || TextUtils.isEmpty(strOptString2)) {
                    str2 = "params parsing exception";
                } else {
                    d dVarB = com.mbridge.msdk.foundation.same.a.d.b(strOptString, strOptString2);
                    if (dVarB == null || dVarB.d() != 1) {
                        str2 = "not replaced";
                    } else {
                        jSONObject2.put("encrypt_p", dVarB.b());
                        jSONObject2.put(CampaignUnit.KEY_IRLFA, 1);
                        str2 = FirebaseAnalytics.Param.SUCCESS;
                        i = 0;
                    }
                }
            }
            jSONObject.put(CommandMessage.CODE, i);
            jSONObject.put("message", str2);
            jSONObject.put("data", jSONObject2);
            h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (Throwable th) {
            s.d("JS-Video-Brigde", "getEncryptPrice error : " + th.getMessage());
        }
    }
}
