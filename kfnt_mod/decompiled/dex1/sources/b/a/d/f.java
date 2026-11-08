package b.a.d;

import anet.channel.request.Request;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class f {
    public static boolean a(String str) {
        return str.equals("POST") || str.equals("PATCH") || str.equals(Request.Method.PUT) || str.equals(Request.Method.DELETE) || str.equals("MOVE");
    }

    public static boolean b(String str) {
        return str.equals("POST") || str.equals(Request.Method.PUT) || str.equals("PATCH") || str.equals("PROPPATCH") || str.equals("REPORT");
    }

    public static boolean c(String str) {
        return (str.equals("GET") || str.equals(Request.Method.HEAD)) ? false : true;
    }

    public static boolean d(String str) {
        return str.equals("PROPFIND");
    }

    public static boolean e(String str) {
        return !str.equals("PROPFIND");
    }

    private f() {
    }
}
