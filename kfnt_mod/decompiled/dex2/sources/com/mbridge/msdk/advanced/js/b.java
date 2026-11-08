package com.mbridge.msdk.advanced.js;

import android.text.TextUtils;
import android.util.Base64;
import android.webkit.WebView;
import com.heytap.mcssdk.mode.CommandMessage;
import com.heytap.mcssdk.mode.Message;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.db.t;
import com.mbridge.msdk.foundation.download.download.H5DownLoadManager;
import com.mbridge.msdk.foundation.entity.p;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.v;
import com.mbridge.msdk.mbjscommon.windvane.h;
import java.io.File;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeAdvancedJsUtils.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b {
    private static String a = "NativeAdvancedJsUtils";

    public static void a(Object obj, JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        if (jSONObject == null) {
            try {
                jSONObject2.put(CommandMessage.CODE, 1);
                jSONObject2.put("message", "params is null");
                h.a().a(obj, Base64.encodeToString(jSONObject2.toString().getBytes(), 2));
                return;
            } catch (JSONException e) {
                s.a(a, e.getMessage());
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
                                s.a(a, "VideoBean not null");
                                jSONObject5.put(Message.TYPE, 1);
                                jSONObject5.put("videoDataLength", pVarA.c());
                                String strE = pVarA.e();
                                if (TextUtils.isEmpty(strE)) {
                                    s.a(a, "VideoPath null");
                                    jSONObject5.put(FileDownloadModel.PATH, "");
                                    jSONObject5.put("path4Web", "");
                                } else {
                                    s.a(a, "VideoPath not null");
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
                                s.a(a, "VideoBean null");
                            }
                        } else if (i2 == 2 && !TextUtils.isEmpty(strOptString)) {
                            JSONObject jSONObject6 = new JSONObject();
                            jSONObject6.put(Message.TYPE, 2);
                            jSONObject6.put(FileDownloadModel.PATH, H5DownLoadManager.getInstance().getH5ResAddress(strOptString) == null ? "" : H5DownLoadManager.getInstance().getH5ResAddress(strOptString));
                            jSONObject4.put(strOptString, jSONObject6);
                            jSONArray2.put(jSONObject4);
                        } else if (i2 == 3 && !TextUtils.isEmpty(strOptString)) {
                            String str = "";
                            try {
                                File file = new File(strOptString);
                                if (file.exists() && file.isFile() && file.canRead()) {
                                    s.a(a, "getFileInfo Mraid file " + strOptString);
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
                    s.a(a, e2.getMessage());
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
            s.a(a, e3.getMessage());
            return;
        }
    }

    public static void a(WebView webView, String str, String str2) {
        h.a().a(webView, str, str2);
    }

    public static void a(WebView webView, String str, String str2, Object obj) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("sq", 1);
            jSONObject.put("action", str);
            if (!TextUtils.isEmpty(str2)) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(str2, obj);
                jSONObject.put(CommandMessage.PARAMS, jSONObject2);
            }
            if (TextUtils.isEmpty(str2) && obj != null) {
                jSONObject.put(CommandMessage.PARAMS, obj);
            }
            h.a().a(webView, "thirdPartyCalled", Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void a(WebView webView) {
        s.d(a, "fireOnJSBridgeConnected");
        h.a().a(webView);
    }

    public static void a(Object obj) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CommandMessage.CODE, com.mbridge.msdk.mbjscommon.bridge.b.b);
            h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (Exception e) {
            s.a(a, e.getMessage());
        }
    }
}
