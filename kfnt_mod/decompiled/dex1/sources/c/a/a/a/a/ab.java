package c.a.a.a.a;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class ab {
    static String a(String str, Map<String, Object> map) {
        if (str == null || map == null || map.size() <= 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> next = it.next();
            sb.append(URLEncoder.encode(next.getKey(), "UTF-8"));
            sb.append("=");
            sb.append(URLEncoder.encode(next.getValue().toString(), "UTF-8"));
            if (it.hasNext()) {
                sb.append("&");
            }
        }
        return str + "?" + sb.toString();
    }

    static byte[] a(Map<String, Object> map) throws JSONException {
        if (map == null || map.size() <= 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            jSONObject.put(entry.getKey(), entry.getValue());
        }
        return jSONObject.toString().getBytes();
    }

    @NonNull
    static Map<String, String> b(@Nullable Map<String, Object> map) {
        if (map == null) {
            return new HashMap();
        }
        HashMap map2 = new HashMap(map.size());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            map2.put(entry.getKey(), entry.getValue().toString());
        }
        return map2;
    }
}
