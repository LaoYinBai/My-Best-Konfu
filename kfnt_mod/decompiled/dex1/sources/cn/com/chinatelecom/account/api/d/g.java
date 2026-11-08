package cn.com.chinatelecom.account.api.d;

import android.net.Network;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g {

    /* renamed from: a, reason: collision with root package name */
    public Network f2274a;

    /* renamed from: b, reason: collision with root package name */
    public int f2275b;

    /* renamed from: c, reason: collision with root package name */
    public String f2276c;
    public String d;
    public boolean e;
    public boolean f;
    public String g;
    public String h;
    public Map<String, String> i;
    private int j;
    private int k;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private int f2277a;

        /* renamed from: b, reason: collision with root package name */
        private int f2278b;

        /* renamed from: c, reason: collision with root package name */
        private Network f2279c;
        private int d;
        private String e;
        private String f;
        private boolean g;
        private boolean h;
        private String i;
        private String j;
        private Map<String, String> k;

        public a a(int i) {
            this.f2277a = i;
            return this;
        }

        public a a(Network network) {
            this.f2279c = network;
            return this;
        }

        public a a(String str) {
            this.e = str;
            return this;
        }

        public a a(Map<String, String> map) {
            this.k = map;
            return this;
        }

        public a a(boolean z) {
            this.g = z;
            return this;
        }

        public a a(boolean z, String str, String str2) {
            this.h = z;
            this.i = str;
            this.j = str2;
            return this;
        }

        public g a() {
            return new g(this);
        }

        public a b(int i) {
            this.f2278b = i;
            return this;
        }

        public a b(String str) {
            this.f = str;
            return this;
        }
    }

    public g(a aVar) {
        this.j = aVar.f2277a;
        this.k = aVar.f2278b;
        this.f2274a = aVar.f2279c;
        this.f2275b = aVar.d;
        this.f2276c = aVar.e;
        this.d = aVar.f;
        this.e = aVar.g;
        this.f = aVar.h;
        this.g = aVar.i;
        this.h = aVar.j;
        this.i = aVar.k;
    }

    public int a() {
        if (this.j > 0) {
            return this.j;
        }
        return 3000;
    }

    public int b() {
        if (this.k > 0) {
            return this.k;
        }
        return 3000;
    }
}
