package com.meizu.cloud.pushsdk.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.location.Location;
import android.os.Build;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.base.c;
import com.meizu.cloud.pushsdk.c.f.e;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private static final String a = "b";
    private final HashMap<String, String> b;
    private final HashMap<String, String> c;
    private final HashMap<String, Object> d;
    private final HashMap<String, Object> e;

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

    private b(a aVar) throws NoSuchMethodException, SecurityException {
        this.b = new HashMap<>();
        this.c = new HashMap<>();
        this.d = new HashMap<>();
        this.e = new HashMap<>();
        e();
        if (aVar.a != null) {
            a(aVar.a);
        }
        DebugLogger.i(a, "Subject created successfully.");
    }

    private void a(String str, Object obj) {
        if (TextUtils.isEmpty(str) || obj == null || !(obj instanceof String) || ((String) obj).isEmpty()) {
            return;
        }
        this.d.put(str, obj);
    }

    private void a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        this.c.put(str, str2);
    }

    private void b(String str, Object obj) {
        if (TextUtils.isEmpty(str) || obj == null || !(obj instanceof String) || ((String) obj).isEmpty()) {
            return;
        }
        this.e.put(str, obj);
    }

    private void c(Context context) {
        a("pn", (Object) context.getPackageName());
        a("pv", (Object) MzSystemUtils.getAppVersionName(context));
        a("pvc", Integer.valueOf(MzSystemUtils.getAppVersionCode(context)));
        a("st", Integer.valueOf(!TextUtils.isEmpty(MzSystemUtils.findReceiver(context, "com.meizu.ups.push.intent.MESSAGE", context.getPackageName())) ? 1 : 0));
    }

    private void d(Context context) {
        Location locationC = e.c(context);
        if (locationC == null) {
            DebugLogger.e(a, "Location information not available.");
            return;
        }
        b("lt", Double.valueOf(locationC.getLongitude()));
        b("at", Double.valueOf(locationC.getAltitude()));
        b("lat", Double.valueOf(locationC.getLatitude()));
    }

    private void e() {
        a("br", Build.BRAND);
        a("dc", Build.MODEL);
        a("ot", Build.VERSION.RELEASE);
        a("ov", Build.DISPLAY);
        a("ll", MzSystemUtils.getCurrentLanguage());
    }

    private void e(Context context) {
        a("op", MzSystemUtils.getOperator(context));
    }

    private void f(Context context) {
        a("ma", c.c(context));
        b("nt", MzSystemUtils.getNetWorkType(context));
        b("wb", MzSystemUtils.getBssId(context));
        b("wl", MzSystemUtils.getWifiList(context));
    }

    public Map<String, String> a() {
        return this.b;
    }

    public void a(int i, int i2) {
        this.c.put("ss", i + "." + i2);
    }

    public void a(Context context) throws NoSuchMethodException, SecurityException {
        d(context);
        e(context);
        b(context);
        f(context);
        c(context);
    }

    public Map<String, String> b() {
        return this.c;
    }

    @TargetApi(19)
    public void b(Context context) throws NoSuchMethodException, SecurityException {
        Display defaultDisplay;
        try {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager == null) {
                return;
            }
            defaultDisplay = windowManager.getDefaultDisplay();
            try {
                Point point = new Point();
                Display.class.getMethod("getSize", Point.class);
                defaultDisplay.getSize(point);
                a(point.x, point.y);
            } catch (Exception unused) {
                DebugLogger.e(a, "Display.getSize isn't available on older devices.");
                if (defaultDisplay != null) {
                    a(defaultDisplay.getWidth(), defaultDisplay.getHeight());
                } else {
                    DebugLogger.e(a, "error get display");
                }
            }
        } catch (Exception unused2) {
            defaultDisplay = null;
        }
    }

    public Map<String, Object> c() {
        return this.d;
    }

    public Map<String, Object> d() {
        return this.e;
    }
}
