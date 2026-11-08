package com.bytedance.bdturing;

import android.os.Environment;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration;
import com.bytedance.sdk.openadsdk.api.plugin.PluginConstants;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e {
    private static final String A = "challenge_code";
    private static final String B = "os_version";
    private static final String C = "data";
    private static final String D = "msg";
    private static final String E = "name";
    private static final String F = "orientation";
    private static final String G = "result";
    private static final String H = "time";
    private static final String I = "event";
    private static final String J = "params";
    private static final String K = "common";
    private static TreeMap<String, String> L = new TreeMap<>();
    private static ReentrantLock M = new ReentrantLock();
    private static ReentrantLock N = new ReentrantLock();
    private static List<JSONObject> O = new LinkedList();
    private static LinkedBlockingQueue<String> P = new LinkedBlockingQueue<>(500);
    private static FileWriter Q = null;

    /* renamed from: a, reason: collision with root package name */
    public static final String f3792a = "turing_verify_pop";

    /* renamed from: b, reason: collision with root package name */
    public static final String f3793b = "turing_verify_pop_override";

    /* renamed from: c, reason: collision with root package name */
    public static final String f3794c = "turing_verify_close_fb_mask";
    public static final String d = "turing_verify_close_fb_close";
    public static final String e = "turing_verify_close_fb_feedback";
    public static final String f = "turing_verify_close_fb_system";
    public static final String g = "turing_verify_system_low";
    public static final String h = "turing_verify_webview_success";
    public static final String i = "turing_verify_webview_fail";
    public static final String j = "turing_verify_result";
    public static final String k = "turing_verify_bridge_2_sdk";
    public static final String l = "turing_verify_bridge_2_fe";
    public static final String m = "turing_verify_orientation";
    public static final String n = "turing_verify_orientation_change";
    public static final String o = "touch_event";
    public static final String p = "turing_verify_init_sdk";
    public static final String q = "turing_verify_fetch_config";
    public static final String r = "turing_verify_background";
    public static final int s = 500;
    public static final int t = 100;
    private static final String u = "EventReport";
    private static long v = 0;
    private static final String w = "code";
    private static final String x = "mode";
    private static final String y = "type";
    private static final String z = "duration";

    public static void a() {
        v = System.currentTimeMillis();
    }

    public static void a(long j2, float f2, float f3, int i2, float f4, float f5) throws JSONException, InterruptedException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("timestamp", j2);
            jSONObject.put(TTDownloadField.TT_FORCE, f2);
            jSONObject.put("majorRadius", f3);
            jSONObject.put("phase", i2);
            jSONObject.put("x", f4);
            jSONObject.put("y", f5);
            c(a(o, jSONObject).toString());
            try {
                N.lockInterruptibly();
                O.add(jSONObject);
                N.unlock();
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        } catch (JSONException e3) {
            g.a(e3);
        }
    }

    public static void b() throws InterruptedException {
        try {
            N.lockInterruptibly();
            O.clear();
            N.unlock();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void a(c cVar) {
        L.put("channel", cVar.i());
        L.put(TTBaseAdapterConfiguration.APP_NAME_EXTRA_KEY, cVar.d());
        L.put("app_version", cVar.e());
        L.put("aid", cVar.c());
        L.put(PluginConstants.KEY_SDK_VERSION, cVar.g());
        L.put("device_brand", cVar.j());
        L.put("device_model", cVar.k());
        L.put(B, cVar.l());
        L.put("os_name", cVar.m());
        L.put("locale", cVar.v());
        L.put("iid", cVar.r());
        L.put("user_id", cVar.t());
        L.put("did", cVar.s());
    }

    public static void a(String str, String str2, String str3) throws InterruptedException {
        try {
            M.lockInterruptibly();
            L.put("iid", str);
            L.put("user_id", str2);
            L.put("did", str3);
            M.unlock();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static JSONObject a(String str, JSONObject jSONObject) throws JSONException, InterruptedException {
        try {
            jSONObject.put("event", str);
            jSONObject.put("time", System.currentTimeMillis());
            M.lockInterruptibly();
            for (Map.Entry<String, String> entry : L.entrySet()) {
                jSONObject.put(entry.getKey(), entry.getValue());
            }
            M.unlock();
            return jSONObject;
        } catch (Exception e2) {
            g.a(e2);
            return null;
        }
    }

    public static void a(int i2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", i2);
            c(a(f3792a, jSONObject).toString());
        } catch (JSONException e2) {
            g.a(e2);
        }
    }

    public static void b(int i2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", i2);
            c(a(f3793b, jSONObject).toString());
        } catch (JSONException e2) {
            g.a(e2);
        }
    }

    public static void a(long j2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(z, j2);
            c(a(p, jSONObject).toString());
        } catch (JSONException e2) {
            g.a(e2);
        }
    }

    public static void b(long j2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(z, j2);
            c(a(r, jSONObject).toString());
        } catch (JSONException e2) {
            g.a(e2);
        }
    }

    public static void a(long j2, int i2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(z, j2);
            jSONObject.put("result", i2);
            c(a(q, jSONObject).toString());
        } catch (JSONException e2) {
            g.a(e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(java.lang.String r7) throws org.json.JSONException {
        /*
            int r0 = r7.hashCode()
            r1 = -847116780(0xffffffffcd820614, float:-2.7267955E8)
            if (r0 == r1) goto L37
            r1 = -512393751(0xffffffffe1757de9, float:-2.8303282E20)
            if (r0 == r1) goto L2d
            r1 = -27038896(0xfffffffffe636b50, float:-7.557299E37)
            if (r0 == r1) goto L23
            r1 = -20623981(0xfffffffffec54d93, float:-1.3113035E38)
            if (r0 == r1) goto L19
            goto L41
        L19:
            java.lang.String r0 = "turing_verify_close_fb_system"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L41
            r0 = 3
            goto L42
        L23:
            java.lang.String r0 = "turing_verify_close_fb_mask"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L41
            r0 = 2
            goto L42
        L2d:
            java.lang.String r0 = "turing_verify_close_fb_feedback"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L41
            r0 = 1
            goto L42
        L37:
            java.lang.String r0 = "turing_verify_close_fb_close"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L41
            r0 = 0
            goto L42
        L41:
            r0 = -1
        L42:
            switch(r0) {
                case 0: goto L46;
                case 1: goto L46;
                case 2: goto L46;
                case 3: goto L46;
                default: goto L45;
            }
        L45:
            return
        L46:
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = "duration"
            long r2 = java.lang.System.currentTimeMillis()     // Catch: org.json.JSONException -> L64
            long r4 = com.bytedance.bdturing.e.v     // Catch: org.json.JSONException -> L64
            r6 = 0
            long r2 = r2 - r4
            r0.put(r1, r2)     // Catch: org.json.JSONException -> L64
            org.json.JSONObject r7 = a(r7, r0)
            java.lang.String r7 = r7.toString()
            c(r7)
            return
        L64:
            r7 = move-exception
            com.bytedance.bdturing.g.a(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.bdturing.e.a(java.lang.String):void");
    }

    public static void a(int i2, int i3) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", i2);
            jSONObject.put(B, i3);
            c(a(g, jSONObject).toString());
        } catch (JSONException e2) {
            g.a(e2);
        }
    }

    public static void c(int i2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(z, System.currentTimeMillis() - v);
            jSONObject.put("type", i2);
            c(a(h, jSONObject).toString());
        } catch (JSONException e2) {
            g.a(e2);
        }
    }

    public static void a(int i2, int i3, String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(z, System.currentTimeMillis() - v);
            jSONObject.put("type", i2);
            jSONObject.put("code", i3);
            if (str != null) {
                jSONObject.put("msg", str);
            }
            c(a(i, jSONObject).toString());
        } catch (JSONException e2) {
            g.a(e2);
        }
    }

    public static void a(int i2, String str, int i3) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(z, System.currentTimeMillis() - v);
            jSONObject.put(x, str);
            jSONObject.put(A, i3);
            jSONObject.put("result", i2);
            c(a(j, jSONObject).toString());
        } catch (JSONException e2) {
            g.a(e2);
        }
    }

    public static void a(boolean z2, String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", str);
            c(a(z2 ? k : l, jSONObject).toString());
        } catch (JSONException e2) {
            g.a(e2);
        }
    }

    public static void d(int i2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(F, i2);
            c(a(m, jSONObject).toString());
        } catch (JSONException e2) {
            g.a(e2);
        }
    }

    public static void e(int i2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(F, i2);
            c(a(n, jSONObject).toString());
        } catch (JSONException e2) {
            g.a(e2);
        }
    }

    private static void a(List<String> list) {
        P.addAll(list);
    }

    private static void c(String str) {
        P.add(str);
        if (P.size() > 100) {
            j.a().a(3, (Object) null);
        }
    }

    private static String f() {
        return b(a(P));
    }

    private static String b(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("data", jSONArray);
            return com.bytedance.bdturing.c.i.a(1, com.bytedance.bdturing.c.i.l, "call", jSONObject, null);
        } catch (JSONException e2) {
            g.a(e2);
            return null;
        }
    }

    private static String c(com.bytedance.bdturing.c.i iVar) throws JSONException {
        a(false, com.bytedance.bdturing.c.h.f3784b);
        JSONArray jSONArrayA = a(P);
        if (jSONArrayA == null) {
            jSONArrayA = new JSONArray();
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("data", jSONArrayA);
            return com.bytedance.bdturing.c.i.a(1, com.bytedance.bdturing.c.i.i, jSONObject, iVar).toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static String d(com.bytedance.bdturing.c.i iVar) throws JSONException, InterruptedException {
        a(false, com.bytedance.bdturing.c.h.f3785c);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("os_name", DispatchConstants.ANDROID);
            N.lockInterruptibly();
            JSONArray jSONArray = new JSONArray((Collection) O);
            b();
            N.unlock();
            jSONObject.put("touch", jSONArray);
            return com.bytedance.bdturing.c.i.a(1, com.bytedance.bdturing.c.i.i, jSONObject, iVar).toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static JSONArray a(LinkedBlockingQueue<String> linkedBlockingQueue) {
        LinkedList linkedList = new LinkedList();
        if (linkedBlockingQueue.drainTo(linkedList, 500) == 0) {
            return null;
        }
        return new JSONArray((Collection) linkedList);
    }

    static void a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return;
        }
        d(b(jSONArray));
    }

    private static void d(String str) {
        if (str == null) {
            return;
        }
        com.bytedance.bdturing.b.b.a().a(str);
    }

    static void c() {
        a(a(P));
    }

    private static boolean a(String str, boolean z2) {
        if (str == null) {
            return false;
        }
        i iVarD = a.a().d();
        boolean zA = iVarD != null ? iVarD.a(str) : false;
        if (!zA) {
            if (z2) {
                a(com.bytedance.bdturing.c.i.a(str));
            } else {
                d(str);
            }
        }
        return zA;
    }

    public static boolean d() {
        return a(f(), false);
    }

    public static boolean a(com.bytedance.bdturing.c.i iVar) {
        return a(c(iVar), true);
    }

    public static boolean b(com.bytedance.bdturing.c.i iVar) throws JSONException, InterruptedException {
        String strD = d(iVar);
        i iVarD = a.a().d();
        if (iVarD != null) {
            return iVarD.a(strD);
        }
        return false;
    }

    public static boolean e() {
        String strD = com.bytedance.bdturing.b.b.a().d();
        if (strD == null) {
            return false;
        }
        return a(strD, false);
    }

    public static void b(String str) throws IOException {
        if (Q == null) {
            File file = new File(Environment.getExternalStorageDirectory(), "report.txt");
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            Q = new FileWriter(file, false);
        }
        Q.write(str);
        Q.write("\n");
    }
}
