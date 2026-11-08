package com.mbridge.msdk.mbjscommon.windvane;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MvBridge extends AbsMbridgeDownload {
    @Override // com.mbridge.msdk.mbjscommon.windvane.AbsMbridgeDownload
    public void sendNoticeAndCallBackClick(Object obj, String str) {
    }

    public void getTitle(Object obj, String str) throws JSONException {
        String str2;
        a aVar = (a) obj;
        WindVaneWebView windVaneWebView = aVar.a;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("a", "hello succ");
            e.a().a(obj, jSONObject.toString());
            e.a();
            String string = jSONObject.toString();
            if (aVar instanceof a) {
                a aVar2 = aVar;
                if (TextUtils.isEmpty(string)) {
                    str2 = String.format("javascript:window.MvBridge.fireEvent('%s', '');", "receiveMessage");
                } else {
                    str2 = String.format("javascript:window.MvBridge.fireEvent('%s','%s');", "receiveMessage", k.c(string));
                }
                if (aVar2.a != null) {
                    try {
                        aVar2.a.loadUrl(str2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    public void getDeviceInfo(Object obj, String str) throws JSONException {
        WindVaneWebView windVaneWebView = ((a) obj).a;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("a", "hello fail");
            e.a();
            String string = jSONObject.toString();
            if (obj instanceof a) {
                a aVar = (a) obj;
                if (TextUtils.isEmpty(string)) {
                    String.format("javascript:window.MvBridge.onFailure(%s,'');", aVar.g);
                } else {
                    string = k.c(string);
                }
                String str2 = String.format("javascript:window.MvBridge.onFailure(%s,'%s');", aVar.g, string);
                if (aVar.a != null) {
                    try {
                        aVar.a.loadUrl(str2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    public void openWindow(Object obj, String str) throws JSONException {
        WindVaneWebView windVaneWebView = ((a) obj).a;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("a", "hello succ");
            e.a().a(obj, jSONObject.toString());
        } catch (Exception unused) {
        }
    }
}
