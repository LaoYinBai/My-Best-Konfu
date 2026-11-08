package com.ss.sys.ck;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c {
    private static Map<String, HashMap<String, Method>> a = new HashMap();

    private static HashMap<String, Method> a(Class cls) throws SecurityException {
        String name;
        Class<?>[] parameterTypes;
        HashMap<String, Method> map = new HashMap<>();
        for (Method method : cls.getDeclaredMethods()) {
            if (method.getModifiers() == 9 && (name = method.getName()) != null && (parameterTypes = method.getParameterTypes()) != null && parameterTypes.length == 3 && parameterTypes[0] == WebView.class && parameterTypes[1] == JSONObject.class && parameterTypes[2] == d.class) {
                map.put(name, method);
            }
        }
        return map;
    }

    public static void a(String str, Class<?> cls) {
        if (a.containsKey(str)) {
            return;
        }
        try {
            a.put(str, a(cls));
        } catch (Exception unused) {
        }
    }

    public static boolean a(WebView webView, String str) {
        HashMap<String, Method> map;
        String strReplace = "";
        String host = "";
        String query = "{}";
        String str2 = "";
        if (!TextUtils.isEmpty(str) && str.startsWith("jsbridge")) {
            try {
                Uri uri = Uri.parse(str);
                host = uri.getHost();
                query = uri.getQuery();
                str2 = uri.getPort() + "";
                String path = uri.getPath();
                if (!TextUtils.isEmpty(path)) {
                    strReplace = path.replace("/", "");
                }
            } catch (Throwable unused) {
                return false;
            }
        }
        if (a.containsKey(host) && (map = a.get(host)) != null && map.size() != 0 && map.containsKey(strReplace)) {
            Method method = map.get(strReplace);
            if (method == null || webView == null) {
                Log.e("CZL", "handler is NULL");
            } else {
                try {
                    method.invoke(null, webView, new JSONObject(query), new d(webView, str2));
                    return true;
                } catch (Throwable th) {
                    Log.e("CZL", "invoke encounter ERROR!");
                    th.printStackTrace();
                }
            }
        }
        return false;
    }
}
