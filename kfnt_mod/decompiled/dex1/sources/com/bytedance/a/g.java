package com.bytedance.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class g {

    /* renamed from: a, reason: collision with root package name */
    public HashSet<String> f3524a;

    /* renamed from: b, reason: collision with root package name */
    public HashMap<String, HashSet<String>> f3525b;

    public g(HashSet<String> hashSet, HashMap<String, HashSet<String>> map) {
        this.f3524a = hashSet;
        this.f3525b = map;
    }

    public static final g a(Context context, JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject;
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("sp_filter_name", 0).edit();
            editorEdit.clear().commit();
            if (jSONObject == null || !jSONObject.has("event_list") || (jSONObjectOptJSONObject = jSONObject.optJSONObject("event_list")) == null) {
                return null;
            }
            int iOptInt = jSONObjectOptJSONObject.optInt("is_block", 0);
            editorEdit.putInt("is_block", iOptInt);
            HashSet hashSet = new HashSet();
            JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("events");
            if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    String strOptString = jSONArrayOptJSONArray.optString(i);
                    if (!TextUtils.isEmpty(strOptString)) {
                        hashSet.add(strOptString);
                    }
                }
            }
            if (hashSet.size() > 0) {
                editorEdit.putStringSet("events", hashSet);
            }
            HashMap map = new HashMap();
            JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("params");
            if (jSONObjectOptJSONObject2 != null) {
                Iterator<String> itKeys = jSONObjectOptJSONObject2.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    if (!TextUtils.isEmpty(next)) {
                        HashSet hashSet2 = new HashSet();
                        JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject2.optJSONArray(next);
                        if (jSONArrayOptJSONArray2 != null && jSONArrayOptJSONArray2.length() > 0) {
                            for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                                String strOptString2 = jSONArrayOptJSONArray2.optString(i2);
                                if (!TextUtils.isEmpty(strOptString2)) {
                                    hashSet2.add(strOptString2);
                                }
                            }
                        }
                        if (hashSet2.size() > 0) {
                            map.put(next, hashSet2);
                        }
                    }
                }
            }
            if (map.size() > 0) {
                for (Map.Entry entry : map.entrySet()) {
                    editorEdit.putStringSet((String) entry.getKey(), (Set) entry.getValue());
                }
            }
            editorEdit.commit();
            return iOptInt > 0 ? new q(hashSet, map) : new l(hashSet, map);
        } catch (Throwable unused) {
            return null;
        }
    }

    public abstract boolean a(String str);

    public final boolean a(String str, String str2) {
        HashMap<String, HashSet<String>> map;
        HashSet<String> hashSet;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        HashSet<String> hashSet2 = this.f3524a;
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
            if (jSONObject != null && (map = this.f3525b) != null && map.size() > 0 && this.f3525b.containsKey(str) && (hashSet = this.f3525b.get(str)) != null && hashSet.size() > 0) {
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
