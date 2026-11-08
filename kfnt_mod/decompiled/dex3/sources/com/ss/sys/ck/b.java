package com.ss.sys.ck;

import c.a.a.a.a.ad;
import c.a.a.a.a.at;
import c.a.a.a.a.bg;
import c.a.a.a.a.bm;
import c.a.a.a.a.i;
import com.tachikoma.core.component.anim.AnimationProperty;
import com.taobao.accs.common.Constants;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    public static String a = "SCCheckLog";
    public static String b = at.b() + "/view";
    public static float c = -1.0f;
    public static HashMap<Integer, C0046b> d = new HashMap<>();
    public static boolean e = false;

    static class a extends Thread {
        a(String str) {
            super(str);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            int i = 0;
            while (i < 10) {
                if (!b.e && i < 10) {
                    b.a();
                    i++;
                }
                i++;
                bm.a(i * 3000);
                if (b.e) {
                    return;
                }
            }
        }
    }

    /* renamed from: com.ss.sys.ck.b$b, reason: collision with other inner class name */
    public static class C0046b {
        public Integer a;
        public float b;
        public int c;

        C0046b() {
        }

        C0046b(Integer num, float f, int i) {
            this.a = num;
            this.b = f;
            this.c = i;
        }
    }

    public static void a() {
        SCCheckUtils sCCheckUtils = SCCheckUtils.getInstance();
        if (sCCheckUtils != null) {
            String str = "?aid=" + sCCheckUtils.appID + "&lang=" + sCCheckUtils.language + "&app_name=" + sCCheckUtils.appName + "&iid=" + sCCheckUtils.iid + "&vc=" + sCCheckUtils.versionCode + "&did=" + sCCheckUtils.deviceID + "&ch=" + sCCheckUtils.channel + "&os=0";
            try {
                new ad(sCCheckUtils.ctx, sCCheckUtils.session, str).a();
            } catch (Throwable unused) {
                new i(sCCheckUtils.ctx, sCCheckUtils.session, str).a(2, 2, (byte[]) null);
            }
        }
    }

    public static boolean a(String str) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONArray jSONArray = jSONObject.getJSONArray("challenge_setting");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                C0046b c0046b = new C0046b();
                c0046b.a = Integer.valueOf(jSONObject2.getInt(Constants.KEY_MODE));
                c0046b.b = (float) jSONObject2.getDouble("percent");
                c0046b.c = jSONObject2.getInt("min_width");
                d.put(c0046b.a, c0046b);
            }
            b = jSONObject.getString("h5_url");
            c = (float) jSONObject.getDouble(AnimationProperty.OPACITY);
            bg.c("[+] set ck settings");
            e = true;
            return true;
        } catch (Exception unused) {
            b = at.b() + "/view";
            return false;
        }
    }

    public static String b() {
        return b;
    }

    public static void c() {
        C0046b c0046b = new C0046b(Integer.valueOf(com.ss.union.game.sdk.account.b.h), 1.1533333f, 200);
        C0046b c0046b2 = new C0046b(3058, 1.1533333f, 200);
        C0046b c0046b3 = new C0046b(10031, 1.1533333f, 200);
        C0046b c0046b4 = new C0046b(Integer.valueOf(com.ss.union.game.sdk.account.b.g), 0.9533333f, 200);
        C0046b c0046b5 = new C0046b(3059, 0.9533333f, 200);
        C0046b c0046b6 = new C0046b(10030, 0.9533333f, 200);
        d.put(c0046b.a, c0046b);
        d.put(c0046b2.a, c0046b2);
        d.put(c0046b3.a, c0046b3);
        d.put(c0046b4.a, c0046b4);
        d.put(c0046b5.a, c0046b5);
        d.put(c0046b6.a, c0046b6);
    }

    public static void d() {
        new a("CZL-11").start();
    }
}
