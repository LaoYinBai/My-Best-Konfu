package com.mbridge.msdk.video.js.bridge;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.heytap.mcssdk.mode.CommandMessage;
import com.heytap.mcssdk.mode.Message;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.mbridge.msdk.click.c;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.mbjscommon.bridge.b;
import com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView;
import com.mbridge.msdk.mbjscommon.windvane.a;
import com.mbridge.msdk.mbjscommon.windvane.h;
import com.mbridge.msdk.video.js.a.j;
import com.mbridge.msdk.video.js.factory.IJSFactory;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class BaseRewardJsH5 implements IRewardBridge {
    protected IJSFactory a;

    /* JADX WARN: Multi-variable type inference failed */
    public void initialize(Context context, WindVaneWebView windVaneWebView) {
        if (context instanceof IJSFactory) {
            this.a = (IJSFactory) context;
        }
    }

    public void initialize(Object obj, WindVaneWebView windVaneWebView) {
        if (obj instanceof IJSFactory) {
            this.a = (IJSFactory) obj;
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void getEndScreenInfo(Object obj, String str) {
        String strEncodeToString;
        try {
            if (this.a != null) {
                String strA = this.a.getIJSRewardVideoV1().a();
                if (!TextUtils.isEmpty(strA)) {
                    strEncodeToString = Base64.encodeToString(strA.getBytes(), 2);
                    s.a("JS-Reward-Brigde", "getEndScreenInfo success");
                } else {
                    strEncodeToString = "";
                    s.a("JS-Reward-Brigde", "getEndScreenInfo failed");
                }
                h.a().a(obj, strEncodeToString);
            }
        } catch (Throwable th) {
            s.b("JS-Reward-Brigde", "getEndScreenInfo", th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void install(Object obj, String str) {
        j jVar;
        Context context;
        try {
            if (this.a != null) {
                s.a("JS-Reward-Brigde", "install:" + str);
                if (obj != null) {
                    a aVar = (a) obj;
                    if ((aVar.a.getObject() instanceof j) && (jVar = (j) aVar.a.getObject()) != null && (context = aVar.a.getContext()) != null && context != context.getApplicationContext()) {
                        jVar.a(context);
                    }
                }
                if (this.a.getJSContainerModule().endCardShowing()) {
                    this.a.getJSCommon().click(3, str);
                } else {
                    this.a.getJSCommon().click(1, str);
                }
            }
        } catch (Throwable th) {
            s.b("JS-Reward-Brigde", "install", th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void openURL(Object obj, String str) {
        WindVaneWebView windVaneWebView;
        s.d("JS-Reward-Brigde", "openURL:" + str);
        Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (contextJ == null) {
            try {
                if ((obj instanceof a) && (windVaneWebView = ((a) obj).a) != null) {
                    contextJ = windVaneWebView.getContext();
                }
            } catch (Exception e) {
                s.d("JS-Reward-Brigde", e.getMessage());
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
                c.a(contextJ, strOptString);
            } else if (iOptInt == 2) {
                c.b(contextJ, strOptString);
            }
        } catch (JSONException e2) {
            s.d("JS-Reward-Brigde", e2.getMessage());
        } catch (Throwable th) {
            s.d("JS-Reward-Brigde", th.getMessage());
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void cai(Object obj, String str) throws JSONException {
        s.a("JS-Reward-Brigde", "cai:" + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            String strOptString = new JSONObject(str).optString("packageName");
            if (TextUtils.isEmpty(strOptString)) {
                b.a(obj, "packageName is empty");
            }
            int i = w.c(com.mbridge.msdk.foundation.controller.a.f().j(), strOptString) ? 1 : 2;
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(CommandMessage.CODE, b.b);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("result", i);
                jSONObject.put("data", jSONObject2);
                h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
            } catch (Exception e) {
                b.a(obj, e.getMessage());
                s.a("JS-Reward-Brigde", e.getMessage());
            }
        } catch (JSONException e2) {
            b.a(obj, "exception: " + e2.getLocalizedMessage());
            s.b("JS-Reward-Brigde", "cai", e2);
        } catch (Throwable th) {
            b.a(obj, "exception: " + th.getLocalizedMessage());
            s.b("JS-Reward-Brigde", "cai", th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void gial(Object obj, String str) throws JSONException {
        s.a("JS-Reward-Brigde", "gial:" + str);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CommandMessage.CODE, b.b);
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
            b.a(obj, e.getMessage());
            s.a("JS-Reward-Brigde", e.getMessage());
        } catch (Throwable th) {
            b.a(obj, th.getMessage());
            s.a("JS-Reward-Brigde", th.getMessage());
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void notifyCloseBtn(Object obj, String str) {
        try {
            if (this.a == null || TextUtils.isEmpty(str)) {
                return;
            }
            int iOptInt = new JSONObject(str).optInt("state");
            s.a("JS-Reward-Brigde", "notifyCloseBtn,state:" + str);
            this.a.getIJSRewardVideoV1().notifyCloseBtn(iOptInt);
        } catch (Throwable th) {
            s.b("JS-Reward-Brigde", "notifyCloseBtn", th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void toggleCloseBtn(Object obj, String str) {
        try {
            if (this.a == null || TextUtils.isEmpty(str)) {
                return;
            }
            int iOptInt = new JSONObject(str).optInt("state");
            s.a("JS-Reward-Brigde", "toggleCloseBtn,state:" + str);
            this.a.getIJSRewardVideoV1().toggleCloseBtn(iOptInt);
        } catch (Throwable th) {
            s.b("JS-Reward-Brigde", "toggleCloseBtn", th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void triggerCloseBtn(Object obj, String str) {
        try {
            if (this.a == null || TextUtils.isEmpty(str)) {
                return;
            }
            h.a().a(obj, a(0));
            this.a.getIJSRewardVideoV1().a(new JSONObject(str).optString("state"));
            s.a("JS-Reward-Brigde", "triggerCloseBtn,state:" + str);
        } catch (Throwable th) {
            s.b("JS-Reward-Brigde", "triggerCloseBtn", th);
            h.a().a(obj, a(-1));
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void setOrientation(Object obj, String str) {
        try {
            if (this.a == null || TextUtils.isEmpty(str)) {
                return;
            }
            String strOptString = new JSONObject(str).optString("state");
            s.a("JS-Reward-Brigde", "setOrientation,state:" + str);
            this.a.getIJSRewardVideoV1().b(strOptString);
        } catch (Throwable th) {
            s.b("JS-Reward-Brigde", "setOrientation", th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void handlerPlayableException(Object obj, String str) {
        try {
            if (this.a == null || TextUtils.isEmpty(str)) {
                return;
            }
            String strOptString = new JSONObject(str).optString(RemoteMessageConst.MessageBody.MSG);
            s.a("JS-Reward-Brigde", "handlerPlayableException,msg:" + str);
            this.a.getIJSRewardVideoV1().c(strOptString);
        } catch (Throwable th) {
            s.b("JS-Reward-Brigde", "setOrientation", th);
        }
    }

    private String a(int i) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CommandMessage.CODE, i);
            String string = jSONObject.toString();
            return !TextUtils.isEmpty(string) ? Base64.encodeToString(string.getBytes(), 2) : "";
        } catch (Throwable unused) {
            s.d("JS-Reward-Brigde", "code to string is error");
            return "";
        }
    }
}
