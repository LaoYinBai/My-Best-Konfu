package com.bytedance.push.e;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private List<a> f4784a;

    /* renamed from: b, reason: collision with root package name */
    private C0146b f4785b;

    public String a() throws JSONException {
        C0146b c0146b = this.f4785b;
        if (c0146b == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("is_mute", c0146b.f4788a ? 1 : 0);
            if (c0146b.f4788a) {
                jSONObject.put("start_time", c0146b.a().a());
                jSONObject.put("end_time", c0146b.b().a());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public String b() throws JSONException {
        ArrayList<a> arrayList = this.f4784a == null ? null : new ArrayList(this.f4784a);
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (a aVar : arrayList) {
            if (aVar != null) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("is_close", !aVar.f4787b ? 1 : 0);
                    jSONObject.put("name", aVar.f4786a);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                jSONArray.put(jSONObject);
            }
        }
        return jSONArray.toString();
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private String f4786a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f4787b;

        public String toString() {
            return "ChildSwitcher{tag='" + this.f4786a + "', isOpen=" + this.f4787b + '}';
        }
    }

    /* renamed from: com.bytedance.push.e.b$b, reason: collision with other inner class name */
    public static class C0146b {

        /* renamed from: a, reason: collision with root package name */
        private boolean f4788a;

        /* renamed from: b, reason: collision with root package name */
        private c f4789b;

        /* renamed from: c, reason: collision with root package name */
        private c f4790c;

        public c a() {
            return this.f4789b;
        }

        public c b() {
            return this.f4790c;
        }

        public String toString() {
            return "MuteConfig{isMute=" + this.f4788a + ", from=" + this.f4789b + ", to=" + this.f4790c + '}';
        }
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private int f4791a;

        /* renamed from: b, reason: collision with root package name */
        private int f4792b;

        String a() {
            return String.format(Locale.getDefault(), "%02d:%02d", Integer.valueOf(this.f4791a), Integer.valueOf(this.f4792b));
        }

        public String toString() {
            return a();
        }
    }
}
