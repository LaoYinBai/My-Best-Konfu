package c.a.a.a.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class at {

    /* renamed from: a, reason: collision with root package name */
    public static int f2067a = 255;

    /* renamed from: b, reason: collision with root package name */
    public static String f2068b = "";

    /* renamed from: c, reason: collision with root package name */
    public static String f2069c = "";

    public static String a() {
        if (f2068b.length() > 0) {
            return f2068b;
        }
        int i = f2067a;
        return i != 0 ? i != 4 ? "" : "http://10.8.121.119:8888" : "https://xlog.snssdk.com";
    }

    public static synchronized void a(int i) {
        f2067a = i;
    }

    public static synchronized void a(int i, String str) {
        if (i == 1025) {
            f2068b = str;
        } else if (i == 1026) {
            f2069c = str;
        }
    }

    public static String b() {
        int i = f2067a;
        return i != 0 ? i != 4 ? "" : "http://10.8.121.119:6789" : "https://verify.snssdk.com";
    }

    public static synchronized void b(int i) {
    }

    public static String c() {
        if (f2069c.length() > 0) {
            return f2069c;
        }
        int i = f2067a;
        return i != 0 ? i != 4 ? "" : "http://10.2.197.212:8888" : "https://sdfp.snssdk.com";
    }
}
