package com.bytedance.pangle.b;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: b, reason: collision with root package name */
    public static String f4523b = "request_finish";

    /* renamed from: c, reason: collision with root package name */
    public static String f4524c = "download_start";
    public static String d = "download_finish";
    public static String e = "install_start";
    public static String f = "install_finish";
    public static String g = "load_start";
    public static String h = "load_finish";
    public static String i = "zeus_error";
    private static volatile b j;

    /* renamed from: a, reason: collision with root package name */
    public final List<com.bytedance.pangle.b.a> f4525a = new ArrayList();

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public static int f4526a = 11000;

        /* renamed from: b, reason: collision with root package name */
        public static int f4527b = 12000;

        /* renamed from: c, reason: collision with root package name */
        public static int f4528c = 12001;
        public static int d = 20000;
        public static int e = 21000;
        public static int f = 21001;
        public static int g = 21002;
        public static int h = 22000;
        public static int i = 22001;
        public static int j = 22002;
        public static int k = 22999;
        public static int l = 30000;
        public static int m = 31000;
        public static int n = 32000;
        public static int o = 32001;
        public static int p = 32002;
        public static int q = 32003;
        public static int r = 32004;
        public static int s = 32005;
        public static int t = 32006;
        public static int u = 32007;
        public static int v = 32999;
        public static int w = 40000;
        public static int x = 41000;
        public static int y = 42000;
    }

    public static b a() {
        if (j == null) {
            synchronized (b.class) {
                j = new b();
            }
        }
        return j;
    }

    private b() {
    }

    public final void a(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        synchronized (this.f4525a) {
            Iterator<com.bytedance.pangle.b.a> it = this.f4525a.iterator();
            while (it.hasNext()) {
                try {
                    it.next().a(str, jSONObject, jSONObject2, jSONObject3);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }
}
