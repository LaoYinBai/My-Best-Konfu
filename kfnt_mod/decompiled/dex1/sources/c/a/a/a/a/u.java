package c.a.a.a.a;

import android.app.Application;
import android.content.Context;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import com.ss.sys.ck.SCCheckUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class u {

    /* renamed from: a, reason: collision with root package name */
    public static Context f2119a = null;

    /* renamed from: b, reason: collision with root package name */
    public static int f2120b = 25200;

    /* renamed from: c, reason: collision with root package name */
    public static int f2121c = 100;
    private static o d;

    public static class a extends Thread {

        /* renamed from: a, reason: collision with root package name */
        private String f2122a;

        public a(String str) {
            this.f2122a = str;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            u.a(this.f2122a);
            u.a((Context) null);
        }
    }

    public u() {
        d = o.a(a());
    }

    private static long a(JSONObject jSONObject) throws JSONException {
        long jCurrentTimeMillis;
        try {
            jCurrentTimeMillis = jSONObject.getLong(com.bytedance.b.a.a.a.h);
        } catch (Throwable unused) {
            jCurrentTimeMillis = 0;
        }
        if (jCurrentTimeMillis == 0) {
            jCurrentTimeMillis = System.currentTimeMillis() / 1000;
            try {
                jSONObject.put(com.bytedance.b.a.a.a.h, jCurrentTimeMillis);
            } catch (JSONException unused2) {
            }
        }
        return jCurrentTimeMillis;
    }

    public static Context a() {
        Context context = f2119a;
        if (context != null) {
            return context;
        }
        try {
            Context applicationContext = ((Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, new Object[0])).getApplicationContext();
            f2119a = applicationContext;
            return applicationContext;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static void a(Context context) {
        if (context == null && (context = a()) == null) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        long jCurrentTimeMillis2 = (System.currentTimeMillis() / 1000) - f2120b;
        String strB = b().b(jCurrentTimeMillis2, com.bytedance.bdturing.b.b.f3763c, f2121c);
        if (strB.equals("")) {
            return;
        }
        byte[] bytes = strB.getBytes();
        try {
            new aa(context).a(bytes);
        } catch (Throwable unused) {
            new r(context, SCCheckUtils.getInstance().getSession()).a(1, 1, bytes);
        }
        r.f2114b = jCurrentTimeMillis;
        r.f2113a = jCurrentTimeMillis2;
    }

    public static void a(String str) {
        if (str == null || str.equals("")) {
            return;
        }
        try {
            Object objNextValue = new JSONTokener(str).nextValue();
            if (objNextValue instanceof JSONObject) {
                b((JSONObject) objNextValue);
                return;
            }
            if (objNextValue instanceof JSONArray) {
                JSONArray jSONArray = (JSONArray) objNextValue;
                for (int i = 0; i < jSONArray.length(); i++) {
                    b((JSONObject) jSONArray.get(i));
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static boolean a(long j, String str) {
        try {
            b().a(j, str);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static long b(String str) {
        try {
            return Long.parseLong(str);
        } catch (Throwable unused) {
            return 0L;
        }
    }

    public static o b() {
        synchronized (u.class) {
            if (d == null) {
                d = o.a(a());
            }
        }
        return d;
    }

    private static boolean b(JSONObject jSONObject) {
        try {
            long jA = a(jSONObject);
            jSONObject.put("did", b(SCCheckUtils.getInstance().getDid()));
            jSONObject.put("iid", b(SCCheckUtils.getInstance().getIid()));
            jSONObject.put("aid", b(SCCheckUtils.getInstance().getAid()));
            jSONObject.put("ip", ag.b());
            jSONObject.put(IWeiboService.ResponseConstants.UID, 0L);
            jSONObject.put("version_code", b(ag.a()));
            jSONObject.put("os", OnekeyLoginConstants.CU_RESULT_SUCCESS);
            jSONObject.put("channel", SCCheckUtils.getInstance().getChannel());
            String string = jSONObject.toString();
            b().a(jA + "_" + o.a(), jA, string);
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }
}
