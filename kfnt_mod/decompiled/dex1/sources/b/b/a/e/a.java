package b.b.a.e;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    public HashSet<String> f1060a;

    /* renamed from: b, reason: collision with root package name */
    public HashMap<String, HashSet<String>> f1061b;

    public a(HashSet<String> hashSet, HashMap<String, HashSet<String>> map) {
        this.f1060a = hashSet;
        this.f1061b = map;
    }

    public abstract boolean a(String str);

    public final boolean a(String str, String str2) {
        HashMap<String, HashSet<String>> map;
        HashSet<String> hashSet;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        HashSet<String> hashSet2 = this.f1060a;
        if (hashSet2 != null && hashSet2.size() > 0) {
            if (a(str)) {
                return false;
            }
            if (TextUtils.isEmpty(str2)) {
                return true;
            }
            JSONObject jSONObject = null;
            try {
                jSONObject = new JSONObject(str2);
            } catch (JSONException unused) {
            }
            if (jSONObject != null && (map = this.f1061b) != null && map.size() > 0 && this.f1061b.containsKey(str) && (hashSet = this.f1061b.get(str)) != null && hashSet.size() > 0) {
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    if (a(hashSet, itKeys.next())) {
                        try {
                            itKeys.remove();
                        } catch (Throwable unused2) {
                        }
                    }
                }
            }
        }
        return true;
    }

    public abstract boolean a(HashSet<String> hashSet, String str);
}
