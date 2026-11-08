package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12;

import anet.channel.request.Request;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class f {
    public static boolean a(String str) {
        return str.equals("POST") || str.equals("PATCH") || str.equals(Request.Method.PUT) || str.equals(Request.Method.DELETE) || str.equals("MOVE");
    }

    public static boolean b(String str) {
        return e(str) || str.equals(Request.Method.OPTION) || str.equals(Request.Method.DELETE) || str.equals("PROPFIND") || str.equals("MKCOL") || str.equals("LOCK");
    }

    public static boolean c(String str) {
        return !str.equals("PROPFIND");
    }

    public static boolean d(String str) {
        return str.equals("PROPFIND");
    }

    public static boolean e(String str) {
        return str.equals("POST") || str.equals(Request.Method.PUT) || str.equals("PATCH") || str.equals("PROPPATCH") || str.equals("REPORT");
    }
}
