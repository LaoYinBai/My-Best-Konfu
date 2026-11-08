package com.xiaomi.push;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ab {
    private static int a;

    /* renamed from: a, reason: collision with other field name */
    public static final String f103a;

    /* renamed from: a, reason: collision with other field name */
    public static final boolean f104a;
    public static final boolean b;
    public static final boolean c;
    public static final boolean d;
    public static boolean e;
    public static final boolean f;
    public static final boolean g;

    static {
        int i;
        f103a = ae.f105a ? "ONEBOX" : "@SHIP.TO.2A2FE0D7@";
        f104a = f103a.contains("2A2FE0D7");
        boolean z = false;
        b = f104a || "DEBUG".equalsIgnoreCase(f103a);
        c = "LOGABLE".equalsIgnoreCase(f103a);
        d = f103a.contains("YY");
        e = f103a.equalsIgnoreCase("TEST");
        f = "BETA".equalsIgnoreCase(f103a);
        if (f103a != null && f103a.startsWith("RC")) {
            z = true;
        }
        g = z;
        a = 1;
        if (f103a.equalsIgnoreCase("SANDBOX")) {
            i = 2;
        } else {
            if (!f103a.equalsIgnoreCase("ONEBOX")) {
                a = 1;
                return;
            }
            i = 3;
        }
        a = i;
    }

    public static int a() {
        return a;
    }

    public static void a(int i) {
        a = i;
    }

    /* renamed from: a, reason: collision with other method in class */
    public static boolean m90a() {
        return a == 2;
    }

    public static boolean b() {
        return a == 3;
    }
}
