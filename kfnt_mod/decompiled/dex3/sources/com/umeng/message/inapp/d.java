package com.umeng.message.inapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import com.sigmob.sdk.common.Constants;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.MsgConstant;
import com.umeng.message.UTrack;
import com.umeng.message.common.impl.json.JUtrack;
import com.umeng.message.common.inter.ITagManager;
import com.umeng.message.entity.UInAppMessage;
import com.umeng.message.proguard.h;
import java.io.File;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class d {
    private static final String a = "com.umeng.message.inapp.d";
    private static boolean c;

    @SuppressLint({"StaticFieldLeak"})
    private static d d;
    private Context b;

    private d(Context context) {
        this.b = context.getApplicationContext();
    }

    public static d a(Context context) {
        if (d == null) {
            synchronized (d.class) {
                if (d == null) {
                    d = new d(context.getApplicationContext());
                }
            }
        }
        return d;
    }

    void a(final IUmengInAppMessageCallback iUmengInAppMessageCallback) {
        c();
        com.umeng.message.common.d.a(new Runnable() { // from class: com.umeng.message.inapp.d.1
            @Override // java.lang.Runnable
            public void run() throws JSONException {
                UInAppMessage uInAppMessage;
                UMLog uMLog = UMConfigure.umDebugLog;
                UMLog.mutlInfo(d.a, 2, "get splash message begin");
                try {
                    JSONObject jSONObjectSendRequest = JUtrack.sendRequest(d.this.b(), MsgConstant.SPLASH_MSG_ENDPOINT);
                    if (jSONObjectSendRequest != null && TextUtils.equals(jSONObjectSendRequest.getString("success"), ITagManager.SUCCESS)) {
                        UMLog uMLog2 = UMConfigure.umDebugLog;
                        UMLog.mutlInfo(d.a, 2, "get splash message success" + jSONObjectSendRequest);
                        JSONObject jSONObject = jSONObjectSendRequest.getJSONObject("data");
                        InAppMessageManager.b = jSONObject.getInt("pduration") * 1000;
                        InAppMessageManager.c = jSONObject.getInt("sduration") * 1000;
                        iUmengInAppMessageCallback.onSplashMessage(new UInAppMessage(jSONObject.getJSONObject("launch")));
                        InAppMessageManager.getInstance(d.this.b).c();
                        return;
                    }
                    if (jSONObjectSendRequest != null && TextUtils.equals(jSONObjectSendRequest.getString("success"), "fail") && TextUtils.equals(jSONObjectSendRequest.getString("error"), "no message")) {
                        String strE = InAppMessageManager.getInstance(d.this.b).e();
                        if (TextUtils.isEmpty(strE)) {
                            return;
                        }
                        try {
                            uInAppMessage = new UInAppMessage(new JSONObject(strE));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            uInAppMessage = null;
                        }
                        if (uInAppMessage != null) {
                            InAppMessageManager.getInstance(d.this.b).a(new File(h.d(d.this.b, uInAppMessage.msg_id)));
                            InAppMessageManager.getInstance(d.this.b).a((UInAppMessage) null);
                            return;
                        }
                        return;
                    }
                    iUmengInAppMessageCallback.onSplashMessage(null);
                } catch (Exception e2) {
                    iUmengInAppMessageCallback.onSplashMessage(null);
                    e2.printStackTrace();
                }
            }
        });
    }

    void a(final String str, final IUmengInAppMessageCallback iUmengInAppMessageCallback) {
        c();
        com.umeng.message.common.d.a(new Runnable() { // from class: com.umeng.message.inapp.d.2
            @Override // java.lang.Runnable
            public void run() throws JSONException {
                UInAppMessage uInAppMessage;
                UMLog uMLog = UMConfigure.umDebugLog;
                UMLog.mutlInfo(d.a, 2, "get card message begin");
                try {
                    JSONObject jSONObjectB = d.this.b();
                    jSONObjectB.put(MsgConstant.INAPP_LABEL, str);
                    JSONObject jSONObjectSendRequest = JUtrack.sendRequest(jSONObjectB, MsgConstant.CARD_MSG_ENDPOINT);
                    if (jSONObjectSendRequest != null && TextUtils.equals(jSONObjectSendRequest.getString("success"), ITagManager.SUCCESS)) {
                        UMLog uMLog2 = UMConfigure.umDebugLog;
                        UMLog.mutlInfo(d.a, 2, "get card message success" + jSONObjectSendRequest);
                        JSONObject jSONObject = jSONObjectSendRequest.getJSONObject("data");
                        InAppMessageManager.b = jSONObject.getInt("pduration") * 1000;
                        InAppMessageManager.c = jSONObject.getInt("sduration") * 1000;
                        iUmengInAppMessageCallback.onCardMessage(new UInAppMessage(jSONObject.getJSONObject("card")));
                        InAppMessageManager.getInstance(d.this.b).a(jSONObjectB.optString(MsgConstant.INAPP_LABEL, ""));
                        return;
                    }
                    if (jSONObjectSendRequest != null && TextUtils.equals(jSONObjectSendRequest.getString("success"), "fail") && TextUtils.equals(jSONObjectSendRequest.getString("error"), "no message")) {
                        String strC = InAppMessageManager.getInstance(d.this.b).c(str);
                        if (TextUtils.isEmpty(strC)) {
                            return;
                        }
                        try {
                            uInAppMessage = new UInAppMessage(new JSONObject(strC));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            uInAppMessage = null;
                        }
                        if (uInAppMessage != null) {
                            InAppMessageManager.getInstance(d.this.b).a(new File(h.d(d.this.b, uInAppMessage.msg_id)));
                            InAppMessageManager.getInstance(d.this.b).a((UInAppMessage) null, str);
                            return;
                        }
                        return;
                    }
                    iUmengInAppMessageCallback.onCardMessage(null);
                } catch (Exception e2) {
                    iUmengInAppMessageCallback.onCardMessage(null);
                    e2.printStackTrace();
                }
            }
        });
    }

    void a(final String str, final int i, final int i2, final int i3, final int i4, final int i5, final int i6, final int i7, final int i8) {
        com.umeng.message.common.d.a(new Runnable() { // from class: com.umeng.message.inapp.d.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    UMLog uMLog = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(d.a, 2, "track in app msg begin");
                    JSONObject jSONObjectB = d.this.b(str, i, i2, i3, i4, i5, i6, i7, i8);
                    if (jSONObjectB == null || !TextUtils.equals(jSONObjectB.getString("success"), ITagManager.SUCCESS)) {
                        return;
                    }
                    UMLog uMLog2 = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(d.a, 2, "track in app msg success");
                } catch (Exception e) {
                    InAppMessageManager.getInstance(d.this.b).a(str, i, i2, i3, i4, i5, i6, i7, i8);
                    e.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("header", UTrack.getInstance(this.b).getHeader());
        if (InAppMessageManager.a) {
            jSONObject.put(MsgConstant.KEY_INAPP_PMODE, Constants.FAIL);
        } else {
            jSONObject.put(MsgConstant.KEY_INAPP_PMODE, "1");
        }
        return jSONObject;
    }

    private void c() {
        if (c) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 2, "sendInAppCacheLog已经在队列里，忽略该请求");
        } else {
            c = true;
            UMLog uMLog2 = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 2, "sendInAppCacheLog开始");
            com.umeng.message.common.d.a(new Runnable() { // from class: com.umeng.message.inapp.d.4
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        try {
                            Iterator<a> it = InAppMessageManager.getInstance(d.this.b).j().iterator();
                            while (it.hasNext()) {
                                a next = it.next();
                                JSONObject jSONObjectB = d.this.b(next.b, next.c, next.d, next.e, next.f, next.g, next.h, next.i, next.j);
                                if (jSONObjectB != null && TextUtils.equals(jSONObjectB.getString("success"), ITagManager.SUCCESS)) {
                                    InAppMessageManager.getInstance(d.this.b).h(next.b);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } finally {
                        boolean unused = d.c = false;
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject b(String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) throws Exception {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("header", UTrack.getInstance(this.b).getHeader());
        jSONObject.put("msg_id", str);
        jSONObject.put(MsgConstant.INAPP_MSG_TYPE, i);
        jSONObject.put(MsgConstant.INAPP_NUM_DISPLAY, i2);
        jSONObject.put(MsgConstant.INAPP_NUM_OPEN_FULL, i3);
        jSONObject.put(MsgConstant.INAPP_NUM_OPEN_TOP, i4);
        jSONObject.put(MsgConstant.INAPP_NUM_OPEN_BUTTOM, i5);
        jSONObject.put(MsgConstant.INAPP_NUM_CLOSE, i6);
        jSONObject.put(MsgConstant.INAPP_NUM_DURATION, i7);
        jSONObject.put(MsgConstant.INAPP_NUM_CUSTOM, i8);
        return JUtrack.sendRequest(jSONObject, MsgConstant.STATS_ENDPOINT);
    }
}
