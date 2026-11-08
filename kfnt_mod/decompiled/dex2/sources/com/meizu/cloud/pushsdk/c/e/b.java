package com.meizu.cloud.pushsdk.c.e;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.text.TextUtils;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.meizu.cloud.pushsdk.c.f.e;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private static final String a = "b";
    private final HashMap<String, String> b;
    private final HashMap<String, Object> c;
    private final HashMap<String, String> d;

    public static class a {
        private Context a = null;

        public a a(Context context) {
            this.a = context;
            return this;
        }

        public b a() {
            return new b(this);
        }
    }

    private b(a aVar) {
        this.b = new HashMap<>();
        this.c = new HashMap<>();
        this.d = new HashMap<>();
        d();
        e();
        f();
        g();
        if (aVar.a != null) {
            a(aVar.a);
        }
        com.meizu.cloud.pushsdk.c.f.c.c(a, "Subject created successfully.", new Object[0]);
    }

    private void a(String str, Object obj) {
        if (TextUtils.isEmpty(str) || obj == null || !(obj instanceof String) || ((String) obj).isEmpty()) {
            return;
        }
        this.c.put(str, obj);
    }

    private void a(String str, String str2) {
        if (str == null || str2 == null || str.isEmpty() || str2.isEmpty()) {
            return;
        }
        this.d.put(str, str2);
    }

    private void d() {
        a("ot", "android-" + Build.VERSION.RELEASE);
    }

    private void e() {
        a("ov", Build.DISPLAY);
    }

    private void f() {
        a("dm", Build.MODEL);
    }

    private void g() {
        a("df", Build.MANUFACTURER);
    }

    public Map<String, Object> a() {
        return this.c;
    }

    public void a(Context context) {
        b(context);
        c(context);
    }

    public Map<String, String> b() {
        return this.d;
    }

    public void b(Context context) {
        Location locationC = e.c(context);
        if (locationC == null) {
            com.meizu.cloud.pushsdk.c.f.c.a(a, "Location information not available.", new Object[0]);
            return;
        }
        a("la", Double.valueOf(locationC.getLatitude()));
        a("lt", Double.valueOf(locationC.getLongitude()));
        a(CampaignEx.JSON_KEY_AD_AL, Double.valueOf(locationC.getAltitude()));
        a("lla", Float.valueOf(locationC.getAccuracy()));
        a("speed", Float.valueOf(locationC.getSpeed()));
        a("br", Float.valueOf(locationC.getBearing()));
    }

    public Map<String, String> c() {
        return this.b;
    }

    public void c(Context context) {
        String strB = e.b(context);
        if (strB != null) {
            a("ca", strB);
        }
    }
}
