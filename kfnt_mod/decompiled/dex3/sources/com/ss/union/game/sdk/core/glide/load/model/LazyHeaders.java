package com.ss.union.game.sdk.core.glide.load.model;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class LazyHeaders implements Headers {
    private final Map<String, List<LazyHeaderFactory>> a;
    private volatile Map<String, String> b;

    LazyHeaders(Map<String, List<LazyHeaderFactory>> map) {
        this.a = Collections.unmodifiableMap(map);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.model.Headers
    public Map<String, String> getHeaders() {
        if (this.b == null) {
            synchronized (this) {
                if (this.b == null) {
                    this.b = Collections.unmodifiableMap(a());
                }
            }
        }
        return this.b;
    }

    private Map<String, String> a() {
        HashMap map = new HashMap();
        for (Map.Entry<String, List<LazyHeaderFactory>> entry : this.a.entrySet()) {
            String strA = a(entry.getValue());
            if (!TextUtils.isEmpty(strA)) {
                map.put(entry.getKey(), strA);
            }
        }
        return map;
    }

    private String a(List<LazyHeaderFactory> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            String strBuildHeader = list.get(i).buildHeader();
            if (!TextUtils.isEmpty(strBuildHeader)) {
                sb.append(strBuildHeader);
                if (i != list.size() - 1) {
                    sb.append(',');
                }
            }
        }
        return sb.toString();
    }

    public String toString() {
        return "LazyHeaders{headers=" + this.a + '}';
    }

    public boolean equals(Object obj) {
        if (obj instanceof LazyHeaders) {
            return this.a.equals(((LazyHeaders) obj).a);
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public static final class Builder {
        private static final String a = "User-Agent";
        private static final String b = a();
        private static final Map<String, List<LazyHeaderFactory>> c;
        private boolean d = true;
        private Map<String, List<LazyHeaderFactory>> e = c;
        private boolean f = true;

        static {
            HashMap map = new HashMap(2);
            if (!TextUtils.isEmpty(b)) {
                map.put("User-Agent", Collections.singletonList(new a(b)));
            }
            c = Collections.unmodifiableMap(map);
        }

        public Builder addHeader(String str, String str2) {
            return addHeader(str, new a(str2));
        }

        public Builder addHeader(String str, LazyHeaderFactory lazyHeaderFactory) {
            if (this.f && "User-Agent".equalsIgnoreCase(str)) {
                return setHeader(str, lazyHeaderFactory);
            }
            b();
            a(str).add(lazyHeaderFactory);
            return this;
        }

        public Builder setHeader(String str, String str2) {
            return setHeader(str, str2 == null ? null : new a(str2));
        }

        public Builder setHeader(String str, LazyHeaderFactory lazyHeaderFactory) {
            b();
            if (lazyHeaderFactory == null) {
                this.e.remove(str);
            } else {
                List<LazyHeaderFactory> listA = a(str);
                listA.clear();
                listA.add(lazyHeaderFactory);
            }
            if (this.f && "User-Agent".equalsIgnoreCase(str)) {
                this.f = false;
            }
            return this;
        }

        private List<LazyHeaderFactory> a(String str) {
            List<LazyHeaderFactory> list = this.e.get(str);
            if (list != null) {
                return list;
            }
            ArrayList arrayList = new ArrayList();
            this.e.put(str, arrayList);
            return arrayList;
        }

        private void b() {
            if (this.d) {
                this.d = false;
                this.e = c();
            }
        }

        public LazyHeaders build() {
            this.d = true;
            return new LazyHeaders(this.e);
        }

        private Map<String, List<LazyHeaderFactory>> c() {
            HashMap map = new HashMap(this.e.size());
            for (Map.Entry<String, List<LazyHeaderFactory>> entry : this.e.entrySet()) {
                map.put(entry.getKey(), new ArrayList(entry.getValue()));
            }
            return map;
        }

        static String a() {
            String property = System.getProperty("http.agent");
            if (TextUtils.isEmpty(property)) {
                return property;
            }
            int length = property.length();
            StringBuilder sb = new StringBuilder(property.length());
            for (int i = 0; i < length; i++) {
                char cCharAt = property.charAt(i);
                if ((cCharAt > 31 || cCharAt == '\t') && cCharAt < 127) {
                    sb.append(cCharAt);
                } else {
                    sb.append('?');
                }
            }
            return sb.toString();
        }
    }

    static final class a implements LazyHeaderFactory {
        private final String a;

        a(String str) {
            this.a = str;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.model.LazyHeaderFactory
        public String buildHeader() {
            return this.a;
        }

        public String toString() {
            return "StringHeaderFactory{value='" + this.a + "'}";
        }

        public boolean equals(Object obj) {
            if (obj instanceof a) {
                return this.a.equals(((a) obj).a);
            }
            return false;
        }

        public int hashCode() {
            return this.a.hashCode();
        }
    }
}
