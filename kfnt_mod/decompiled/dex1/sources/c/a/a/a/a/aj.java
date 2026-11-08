package c.a.a.a.a;

import android.content.Context;
import android.util.Base64;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class aj {

    /* renamed from: a, reason: collision with root package name */
    static int f2060a = 504;

    /* renamed from: b, reason: collision with root package name */
    static boolean f2061b = false;

    /* renamed from: c, reason: collision with root package name */
    static long f2062c = 0;
    static String d = "";

    public static String a() {
        String str;
        String str2;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("os", "Android");
            jSONObject.put("version", "1.0.3");
            if (f2061b) {
                str = "token_id";
                str2 = d;
            } else {
                str = "token_id";
                str2 = "";
            }
            jSONObject.put(str, str2);
            jSONObject.put("code", f2060a);
            return Base64.encodeToString(jSONObject.toString().getBytes(), 2);
        } catch (Throwable unused) {
            bg.a("Call onEvent Error");
            return "";
        }
    }

    public static void a(Context context, long j, ap apVar) {
        int i = f2060a;
        if (i == 102 || i == 202 || i == 200) {
            return;
        }
        System.currentTimeMillis();
        f2061b = false;
        f2062c = j;
        f2060a = 102;
        Thread thread = new Thread(new am(context, apVar));
        thread.setName("CZL-3");
        thread.start();
    }
}
