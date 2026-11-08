package com.bytedance.b.a.c;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f {
    public static JSONObject a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        LinkedList linkedList = new LinkedList();
        Iterator<String> itKeys = jSONObject.keys();
        if (itKeys == null) {
            return null;
        }
        while (itKeys.hasNext()) {
            linkedList.add(itKeys.next());
        }
        try {
            return new JSONObject(jSONObject, (String[]) linkedList.toArray(new String[0]));
        } catch (Exception e) {
            e.printStackTrace();
            return jSONObject;
        }
    }

    public static JSONObject b(JSONObject jSONObject) throws JSONException {
        Iterator<String> itKeys;
        if (jSONObject == null || (itKeys = jSONObject.keys()) == null) {
            return null;
        }
        JSONObject jSONObject2 = new JSONObject();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            jSONObject2.put(next, jSONObject.opt(next));
        }
        return jSONObject2;
    }

    public static JSONObject a(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        Iterator<String> itKeys;
        if (jSONObject == null || jSONObject2 == null || (itKeys = jSONObject2.keys()) == null) {
            return null;
        }
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (!jSONObject2.isNull(next)) {
                jSONObject.put(next, jSONObject2.opt(next));
            }
        }
        return jSONObject;
    }

    public static void b(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        if (jSONObject == null || jSONObject2 == null || jSONObject2.length() <= 0) {
            return;
        }
        try {
            Iterator<String> itKeys = jSONObject2.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                jSONObject.put(next, jSONObject2.get(next));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static boolean c(JSONObject jSONObject) {
        return jSONObject == null || jSONObject.length() == 0;
    }

    public static boolean a(JSONArray jSONArray) {
        return jSONArray == null || jSONArray.length() == 0;
    }

    public static boolean d(JSONObject jSONObject) {
        return jSONObject != null && jSONObject.length() > 0;
    }

    public static boolean b(JSONArray jSONArray) {
        return jSONArray != null && jSONArray.length() > 0;
    }

    public static int e(JSONObject jSONObject) {
        try {
            b bVar = new b();
            a.a(jSONObject, bVar);
            return bVar.f3685a;
        } catch (Throwable unused) {
            return 0;
        }
    }

    private static class b extends Writer {

        /* renamed from: a, reason: collision with root package name */
        public int f3685a;

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() throws IOException {
        }

        private b() {
            this.f3685a = 0;
        }

        @Override // java.io.Writer
        public void write(@NonNull char[] cArr, int i, int i2) throws IOException {
            this.f3685a += i2;
        }

        @Override // java.io.Writer
        public void write(int i) throws IOException {
            this.f3685a++;
        }

        @Override // java.io.Writer
        public void write(@NonNull String str) throws IOException {
            this.f3685a += str.length();
        }

        @Override // java.io.Writer
        public void write(@NonNull char[] cArr) throws IOException {
            this.f3685a += cArr.length;
        }

        @Override // java.io.Writer
        public void write(@NonNull String str, int i, int i2) throws IOException {
            this.f3685a += i2;
        }

        @Override // java.io.Writer, java.lang.Appendable
        public Writer append(char c2) throws IOException {
            this.f3685a++;
            return this;
        }

        @Override // java.io.Writer, java.lang.Appendable
        public Writer append(CharSequence charSequence) throws IOException {
            this.f3685a += charSequence.length();
            return this;
        }

        @Override // java.io.Writer, java.lang.Appendable
        public Writer append(CharSequence charSequence, int i, int i2) throws IOException {
            this.f3685a += i2 - i;
            return this;
        }
    }

    private static class a {

        /* renamed from: a, reason: collision with root package name */
        final Writer f3680a;

        /* renamed from: b, reason: collision with root package name */
        private final List<EnumC0105a> f3681b = new ArrayList();

        /* renamed from: com.bytedance.b.a.c.f$a$a, reason: collision with other inner class name */
        enum EnumC0105a {
            EMPTY_ARRAY,
            NONEMPTY_ARRAY,
            EMPTY_OBJECT,
            DANGLING_KEY,
            NONEMPTY_OBJECT,
            NULL
        }

        public String toString() {
            return "";
        }

        public a(Writer writer) {
            this.f3680a = writer;
        }

        public a a() throws JSONException, IOException {
            return a(EnumC0105a.EMPTY_ARRAY, "[");
        }

        public a b() throws JSONException, IOException {
            return a(EnumC0105a.EMPTY_ARRAY, EnumC0105a.NONEMPTY_ARRAY, "]");
        }

        public a c() throws JSONException, IOException {
            return a(EnumC0105a.EMPTY_OBJECT, "{");
        }

        public a d() throws JSONException, IOException {
            return a(EnumC0105a.EMPTY_OBJECT, EnumC0105a.NONEMPTY_OBJECT, com.alipay.sdk.util.f.d);
        }

        a a(EnumC0105a enumC0105a, String str) throws JSONException, IOException {
            g();
            this.f3681b.add(enumC0105a);
            this.f3680a.write(str);
            return this;
        }

        a a(EnumC0105a enumC0105a, EnumC0105a enumC0105a2, String str) throws JSONException, IOException {
            e();
            this.f3681b.remove(this.f3681b.size() - 1);
            this.f3680a.write(str);
            return this;
        }

        private EnumC0105a e() throws JSONException {
            return this.f3681b.get(this.f3681b.size() - 1);
        }

        private void a(EnumC0105a enumC0105a) {
            this.f3681b.set(this.f3681b.size() - 1, enumC0105a);
        }

        public a a(Object obj) throws JSONException, IOException {
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
                this.f3680a.write("null");
            } else if (obj instanceof Boolean) {
                this.f3680a.write(String.valueOf(obj));
            } else if (obj instanceof Number) {
                this.f3680a.write(JSONObject.numberToString((Number) obj));
            } else {
                b(obj.toString());
            }
            return this;
        }

        public a a(boolean z) throws JSONException, IOException {
            g();
            this.f3680a.write(String.valueOf(z));
            return this;
        }

        public a a(double d) throws JSONException, IOException {
            g();
            this.f3680a.write(JSONObject.numberToString(Double.valueOf(d)));
            return this;
        }

        public a a(long j) throws JSONException, IOException {
            g();
            this.f3680a.write(String.valueOf(j));
            return this;
        }

        private void b(String str) throws IOException {
            this.f3680a.write("\"");
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char cCharAt = str.charAt(i);
                if (cCharAt == '\"' || cCharAt == '/' || cCharAt == '\\') {
                    this.f3680a.write(92);
                    this.f3680a.write(cCharAt);
                } else {
                    switch (cCharAt) {
                        case '\b':
                            this.f3680a.write("\\b");
                            break;
                        case '\t':
                            this.f3680a.write("\\t");
                            break;
                        case '\n':
                            this.f3680a.write("\\n");
                            break;
                        default:
                            switch (cCharAt) {
                                case '\f':
                                    this.f3680a.write("\\f");
                                    break;
                                case '\r':
                                    this.f3680a.write("\\r");
                                    break;
                                default:
                                    if (cCharAt <= 31) {
                                        this.f3680a.write(String.format("\\u%04x", Integer.valueOf(cCharAt)));
                                        break;
                                    } else {
                                        this.f3680a.write(cCharAt);
                                        break;
                                    }
                            }
                    }
                }
            }
            this.f3680a.write("\"");
        }

        public a a(String str) throws JSONException, IOException {
            f();
            b(str);
            return this;
        }

        private void f() throws JSONException, IOException {
            EnumC0105a enumC0105aE = e();
            if (enumC0105aE == EnumC0105a.NONEMPTY_OBJECT) {
                this.f3680a.write(44);
            } else if (enumC0105aE != EnumC0105a.EMPTY_OBJECT) {
                throw new JSONException("Nesting problem");
            }
            a(EnumC0105a.DANGLING_KEY);
        }

        private void g() throws JSONException, IOException {
            if (this.f3681b.isEmpty()) {
                return;
            }
            EnumC0105a enumC0105aE = e();
            if (enumC0105aE == EnumC0105a.EMPTY_ARRAY) {
                a(EnumC0105a.NONEMPTY_ARRAY);
                return;
            }
            if (enumC0105aE == EnumC0105a.NONEMPTY_ARRAY) {
                this.f3680a.write(44);
            } else if (enumC0105aE == EnumC0105a.DANGLING_KEY) {
                this.f3680a.write(":");
                a(EnumC0105a.NONEMPTY_OBJECT);
            } else if (enumC0105aE != EnumC0105a.NULL) {
                throw new JSONException("Nesting problem");
            }
        }

        public static void a(JSONObject jSONObject, Writer writer) throws Throwable {
            new a(writer).a(jSONObject);
            writer.flush();
        }

        public static void a(JSONArray jSONArray, Writer writer) throws Throwable {
            new a(writer).a(jSONArray);
            writer.flush();
        }

        private void a(JSONObject jSONObject) throws JSONException, IOException {
            c();
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                a(next).a(jSONObject.get(next));
            }
            d();
        }

        private void a(JSONArray jSONArray) throws JSONException, IOException {
            a();
            for (int i = 0; i < jSONArray.length(); i++) {
                a(jSONArray.get(i));
            }
            b();
        }
    }

    @Nullable
    public static JSONObject a(JSONObject jSONObject, String str, String str2) {
        JSONObject jSONObjectOptJSONObject;
        if (jSONObject == null || (jSONObjectOptJSONObject = jSONObject.optJSONObject(str)) == null) {
            return null;
        }
        return jSONObjectOptJSONObject.optJSONObject(str2);
    }

    @Nullable
    public static JSONObject a(JSONObject jSONObject, String str, String str2, String str3) {
        JSONObject jSONObjectA = a(jSONObject, str, str2);
        if (jSONObjectA == null) {
            return null;
        }
        return jSONObjectA.optJSONObject(str3);
    }

    public static int b(JSONObject jSONObject, String str, String str2, String str3) {
        JSONObject jSONObjectA = a(jSONObject, str, str2);
        if (jSONObjectA == null) {
            return 0;
        }
        return jSONObjectA.optInt(str3, 0);
    }

    public static boolean a(JSONObject jSONObject, String str, String str2, boolean z) {
        JSONObject jSONObjectOptJSONObject;
        return (jSONObject == null || (jSONObjectOptJSONObject = jSONObject.optJSONObject(str)) == null) ? z : jSONObjectOptJSONObject.optBoolean(str2, z);
    }
}
