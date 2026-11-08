package com.bytedance.push.utils;

import android.net.Uri;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    public List<b> f4984a = new ArrayList();

    /* renamed from: b, reason: collision with root package name */
    public String f4985b;

    /* renamed from: c, reason: collision with root package name */
    public String f4986c;
    public String d;
    public String e;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        if (this.f4984a == null ? aVar.f4984a != null : !this.f4984a.equals(aVar.f4984a)) {
            return false;
        }
        if (this.f4985b == null ? aVar.f4985b != null : !this.f4985b.equals(aVar.f4985b)) {
            return false;
        }
        if (this.f4986c == null ? aVar.f4986c != null : !this.f4986c.equals(aVar.f4986c)) {
            return false;
        }
        if (this.d == null ? aVar.d == null : this.d.equals(aVar.d)) {
            return this.e == null ? aVar.e == null : this.e.equals(aVar.e);
        }
        return false;
    }

    public int hashCode() {
        return ((((((((this.f4984a != null ? this.f4984a.hashCode() : 0) * 31) + (this.f4985b != null ? this.f4985b.hashCode() : 0)) * 31) + (this.f4986c != null ? this.f4986c.hashCode() : 0)) * 31) + (this.d != null ? this.d.hashCode() : 0)) * 31) + (this.e != null ? this.e.hashCode() : 0);
    }

    public a(String str) {
        this.f4985b = str;
    }

    public String toString() {
        return "Component{name='" + this.f4985b + "', intentFilter=" + this.f4984a + ", processName='" + this.f4986c + "', permission='" + this.d + "', authorities='" + this.e + "'}";
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        List<String> f4988a;

        /* renamed from: b, reason: collision with root package name */
        List<String> f4989b;

        /* renamed from: c, reason: collision with root package name */
        Uri f4990c;
        String d;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            if (this.f4988a == null ? bVar.f4988a != null : !this.f4988a.equals(bVar.f4988a)) {
                return false;
            }
            if (this.f4989b == null ? bVar.f4989b != null : !this.f4989b.equals(bVar.f4989b)) {
                return false;
            }
            if (this.d == null ? bVar.d == null : this.d.equals(bVar.d)) {
                return this.f4990c != null ? this.f4990c.equals(bVar.f4990c) : bVar.f4990c == null;
            }
            return false;
        }

        public String toString() {
            return "IntentFilter{actions=" + this.f4988a + ", categories=" + this.f4989b + ", data=" + this.f4990c + ", mimetype=" + this.d + '}';
        }

        public int hashCode() {
            return ((((((this.f4988a != null ? this.f4988a.hashCode() : 0) * 31) + (this.f4989b != null ? this.f4989b.hashCode() : 0)) * 31) + (this.d != null ? this.d.hashCode() : 0)) * 31) + (this.f4990c != null ? this.f4990c.hashCode() : 0);
        }

        public b(List<String> list, List<String> list2, Uri uri) {
            this.f4988a = list;
            this.f4989b = list2;
            this.f4990c = uri;
        }

        public b(List<String> list, List<String> list2) {
            this.f4988a = list;
            this.f4989b = list2;
        }

        public b(List<String> list) {
            this(list, null);
        }
    }

    /* renamed from: com.bytedance.push.utils.a$a, reason: collision with other inner class name */
    public static class C0149a {

        /* renamed from: a, reason: collision with root package name */
        private a f4987a;

        public C0149a(String str) {
            this.f4987a = new a(str);
        }

        public C0149a a(String str) {
            this.f4987a.f4986c = str;
            return this;
        }

        public C0149a b(String str) {
            this.f4987a.d = str;
            return this;
        }

        public C0149a a(b bVar) {
            if (bVar == null) {
                return this;
            }
            this.f4987a.f4984a.add(bVar);
            return this;
        }

        public C0149a c(String str) {
            this.f4987a.e = str;
            return this;
        }

        public a a() {
            return this.f4987a;
        }

        public static C0149a d(String str) {
            return new C0149a(str);
        }
    }
}
