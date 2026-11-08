package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private int f1856a;

    /* renamed from: b, reason: collision with root package name */
    private int f1857b;

    /* renamed from: c, reason: collision with root package name */
    private String f1858c;
    private String d;
    private int e;
    private int f;
    private int g;

    public String a() throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("reason", this.f1856a);
            jSONObject.put("fill_error_code", this.f1857b);
            jSONObject.put("fill_error_msg", this.f1858c);
            jSONObject.put("mediation_rit", this.d);
            jSONObject.put("load_sort", this.e);
            jSONObject.put("show_sort", this.f);
            jSONObject.put("has_shown", this.g);
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "{\"name\": \"json err\"}";
        }
    }

    public void b(int i) {
        this.g = i;
    }

    public void c(int i) {
        this.e = i;
    }

    public void d(int i) {
        this.f1856a = i;
    }

    public void e(int i) {
        this.f = i;
    }

    public void b(String str) {
        this.d = str;
    }

    public void a(int i) {
        this.f1857b = i;
    }

    public void a(String str) {
        this.f1858c = str;
    }
}
