package com.mbridge.msdk.video.bt.a;

import android.util.Base64;
import com.heytap.mcssdk.mode.CommandMessage;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbjscommon.windvane.h;
import com.mbridge.msdk.video.bt.module.MBridgeBTVideoView;
import java.util.LinkedHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: H5MediaPlayerManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static final String c = "a";
    private static LinkedHashMap<String, MBridgeBTVideoView> e = new LinkedHashMap<>();
    private String d = "handlerNativeResult";
    int a = 0;
    int b = 1;

    /* compiled from: H5MediaPlayerManager.java */
    /* renamed from: com.mbridge.msdk.video.bt.a.a$a, reason: collision with other inner class name */
    private static final class C0143a {
        private static a a = new a();
    }

    public static a a() {
        return C0143a.a;
    }

    public final void a(String str, MBridgeBTVideoView mBridgeBTVideoView) {
        e.put(str, mBridgeBTVideoView);
    }

    public final void a(String str) {
        e.remove(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00e2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(java.lang.Object r7, java.lang.String r8, org.json.JSONArray r9, int r10) {
        /*
            Method dump skipped, instructions count: 402
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.video.bt.a.a.a(java.lang.Object, java.lang.String, org.json.JSONArray, int):void");
    }

    private void a(int i, String str, Object obj) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CommandMessage.CODE, i);
            jSONObject.put("message", str);
            h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (JSONException e2) {
            s.a(c, e2.getMessage());
        } catch (Throwable th) {
            s.a(c, th.getMessage());
        }
    }
}
