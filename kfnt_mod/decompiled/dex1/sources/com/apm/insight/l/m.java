package com.apm.insight.l;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class m {

    /* renamed from: a, reason: collision with root package name */
    final Writer f2956a;

    /* renamed from: b, reason: collision with root package name */
    private final List<a> f2957b = new ArrayList();

    enum a {
        EMPTY_ARRAY,
        NONEMPTY_ARRAY,
        EMPTY_OBJECT,
        DANGLING_KEY,
        NONEMPTY_OBJECT,
        NULL
    }

    public m(Writer writer) {
        this.f2956a = writer;
    }

    private void a(a aVar) {
        this.f2957b.set(this.f2957b.size() - 1, aVar);
    }

    private void a(JSONArray jSONArray) throws JSONException, IOException {
        a();
        for (int i = 0; i < jSONArray.length(); i++) {
            a(jSONArray.get(i));
        }
        b();
    }

    public static void a(JSONArray jSONArray, Writer writer) throws JSONException, IOException {
        new m(writer).a(jSONArray);
        writer.flush();
    }

    private void a(JSONObject jSONObject) throws JSONException {
        c();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            a(next).a(jSONObject.get(next));
        }
        d();
    }

    public static void a(JSONObject jSONObject, Writer writer) throws JSONException, IOException {
        new m(writer).a(jSONObject);
        writer.flush();
    }

    private void b(String str) throws IOException {
        Writer writer;
        String str2;
        this.f2956a.write("\"");
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\"' || cCharAt == '/' || cCharAt == '\\') {
                this.f2956a.write(92);
            } else {
                switch (cCharAt) {
                    case '\b':
                        writer = this.f2956a;
                        str2 = "\\b";
                        writer.write(str2);
                        break;
                    case '\t':
                        writer = this.f2956a;
                        str2 = "\\t";
                        writer.write(str2);
                        break;
                    case '\n':
                        writer = this.f2956a;
                        str2 = "\\n";
                        writer.write(str2);
                        break;
                    default:
                        switch (cCharAt) {
                            case '\f':
                                writer = this.f2956a;
                                str2 = "\\f";
                                writer.write(str2);
                                break;
                            case '\r':
                                writer = this.f2956a;
                                str2 = "\\r";
                                writer.write(str2);
                                break;
                            default:
                                if (cCharAt <= 31) {
                                    this.f2956a.write(String.format("\\u%04x", Integer.valueOf(cCharAt)));
                                    break;
                                }
                                break;
                        }
                }
            }
            this.f2956a.write(cCharAt);
        }
        this.f2956a.write("\"");
    }

    private a e() {
        return this.f2957b.get(this.f2957b.size() - 1);
    }

    private void f() throws JSONException, IOException {
        a aVarE = e();
        if (aVarE == a.NONEMPTY_OBJECT) {
            this.f2956a.write(44);
        } else if (aVarE != a.EMPTY_OBJECT) {
            throw new JSONException("Nesting problem");
        }
        a(a.DANGLING_KEY);
    }

    private void g() throws JSONException, IOException {
        a aVar;
        if (this.f2957b.isEmpty()) {
            return;
        }
        a aVarE = e();
        if (aVarE == a.EMPTY_ARRAY) {
            aVar = a.NONEMPTY_ARRAY;
        } else if (aVarE == a.NONEMPTY_ARRAY) {
            this.f2956a.write(44);
            return;
        } else if (aVarE != a.DANGLING_KEY) {
            if (aVarE != a.NULL) {
                throw new JSONException("Nesting problem");
            }
            return;
        } else {
            this.f2956a.write(":");
            aVar = a.NONEMPTY_OBJECT;
        }
        a(aVar);
    }

    public m a() {
        return a(a.EMPTY_ARRAY, "[");
    }

    m a(a aVar, a aVar2, String str) throws IOException {
        e();
        this.f2957b.remove(this.f2957b.size() - 1);
        this.f2956a.write(str);
        return this;
    }

    m a(a aVar, String str) throws JSONException, IOException {
        g();
        this.f2957b.add(aVar);
        this.f2956a.write(str);
        return this;
    }

    public m a(Object obj) throws JSONException, IOException {
        Writer writer;
        String strNumberToString;
        if (obj instanceof JSONArray) {
            a((JSONArray) obj);
            return this;
        }
        if (obj instanceof JSONObject) {
            a((JSONObject) obj);
            return this;
        }
        g();
        if (obj == null || obj == JSONObject.NULL) {
            this.f2956a.write("null");
        } else {
            if (obj instanceof Boolean) {
                writer = this.f2956a;
                strNumberToString = String.valueOf(obj);
            } else if (obj instanceof Number) {
                writer = this.f2956a;
                strNumberToString = JSONObject.numberToString((Number) obj);
            } else {
                b(obj.toString());
            }
            writer.write(strNumberToString);
        }
        return this;
    }

    public m a(String str) throws JSONException, IOException {
        f();
        b(str);
        return this;
    }

    public m b() {
        return a(a.EMPTY_ARRAY, a.NONEMPTY_ARRAY, "]");
    }

    public m c() {
        return a(a.EMPTY_OBJECT, "{");
    }

    public m d() {
        return a(a.EMPTY_OBJECT, a.NONEMPTY_OBJECT, com.alipay.sdk.util.f.d);
    }

    public String toString() {
        return "";
    }
}
