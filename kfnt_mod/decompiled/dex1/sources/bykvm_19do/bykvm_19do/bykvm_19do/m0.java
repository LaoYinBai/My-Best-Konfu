package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import android.os.Process;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class m0 {

    /* renamed from: a, reason: collision with root package name */
    private static String f1360a = null;

    /* renamed from: b, reason: collision with root package name */
    private static String f1361b = ":push";

    public static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static boolean b(Context context) {
        String strA = a(context);
        return strA != null && strA.endsWith(f1361b);
    }

    public static boolean a(JSONObject jSONObject, JSONObject jSONObject2) {
        return (jSONObject == null || jSONObject2 == null) ? a((Object) jSONObject, (Object) jSONObject2) : jSONObject.toString().equals(jSONObject2.toString());
    }

    public static JSONObject b(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        try {
            Iterator<String> itKeys = jSONObject2.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                jSONObject.put(next, jSONObject2.opt(next));
            }
        } catch (JSONException e) {
            l0.a(e);
        }
        return jSONObject;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002d, code lost:
    
        bykvm_19do.bykvm_19do.bykvm_19do.m0.f1360a = r1.processName;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(android.content.Context r3) {
        /*
            java.lang.String r0 = bykvm_19do.bykvm_19do.bykvm_19do.m0.f1360a
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L9
            return r0
        L9:
            int r0 = android.os.Process.myPid()     // Catch: java.lang.Exception -> L32
            java.lang.String r1 = "activity"
            java.lang.Object r3 = r3.getSystemService(r1)     // Catch: java.lang.Exception -> L32
            android.app.ActivityManager r3 = (android.app.ActivityManager) r3     // Catch: java.lang.Exception -> L32
            java.util.List r3 = r3.getRunningAppProcesses()     // Catch: java.lang.Exception -> L32
            java.util.Iterator r3 = r3.iterator()     // Catch: java.lang.Exception -> L32
        L1d:
            boolean r1 = r3.hasNext()     // Catch: java.lang.Exception -> L32
            if (r1 == 0) goto L36
            java.lang.Object r1 = r3.next()     // Catch: java.lang.Exception -> L32
            android.app.ActivityManager$RunningAppProcessInfo r1 = (android.app.ActivityManager.RunningAppProcessInfo) r1     // Catch: java.lang.Exception -> L32
            int r2 = r1.pid     // Catch: java.lang.Exception -> L32
            if (r2 != r0) goto L1d
            java.lang.String r3 = r1.processName     // Catch: java.lang.Exception -> L32
            bykvm_19do.bykvm_19do.bykvm_19do.m0.f1360a = r3     // Catch: java.lang.Exception -> L32
            goto L36
        L32:
            r3 = move-exception
            r3.printStackTrace()
        L36:
            java.lang.String r3 = a()
            bykvm_19do.bykvm_19do.bykvm_19do.m0.f1360a = r3
            boolean r3 = bykvm_19do.bykvm_19do.bykvm_19do.l0.f1356a
            if (r3 == 0) goto L57
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r0 = "getProcessName, "
            r3.append(r0)
            java.lang.String r0 = bykvm_19do.bykvm_19do.bykvm_19do.m0.f1360a
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            r0 = 0
            bykvm_19do.bykvm_19do.bykvm_19do.l0.a(r3, r0)
        L57:
            java.lang.String r3 = bykvm_19do.bykvm_19do.bykvm_19do.m0.f1360a
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_19do.m0.a(android.content.Context):java.lang.String");
    }

    private static String a() throws IOException {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + Process.myPid() + "/cmdline"), "iso-8859-1"));
        } catch (Throwable unused) {
            bufferedReader = null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            while (true) {
                int i = bufferedReader.read();
                if (i <= 0) {
                    break;
                }
                sb.append((char) i);
            }
            String string = sb.toString();
            try {
                bufferedReader.close();
            } catch (Exception unused2) {
            }
            return string;
        } catch (Throwable unused3) {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception unused4) {
                }
            }
            return null;
        }
    }

    public static String a(String str, HashMap<String, String> map, String str2) {
        StringBuilder sb = new StringBuilder(str);
        for (String str3 : map.keySet()) {
            String strA = a(str3, str2);
            String str4 = map.get(str3);
            String strA2 = str4 != null ? a(str4, str2) : "";
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(strA);
            sb.append("=");
            sb.append(strA2);
        }
        return sb.toString();
    }

    private static String a(String str, String str2) {
        if (str2 == null) {
            str2 = "ISO-8859-1";
        }
        try {
            return URLEncoder.encode(str, str2);
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }
}
