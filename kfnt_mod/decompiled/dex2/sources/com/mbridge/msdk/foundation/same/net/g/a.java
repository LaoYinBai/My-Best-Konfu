package com.mbridge.msdk.foundation.same.net.g;

/* compiled from: CommonHttpConfig.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    public static String a(int i) {
        return i == 2 ? "Network error,I/O exception" : i == 3 ? "Network error,timeout exception" : i == 4 ? "Network unknown error" : i == -2 ? "Network is canceled" : i == 5 ? "Network error，https is not work,please check your phone time" : i == 6 ? "Network error,please check" : i == 7 ? "The server returns an exception" : i == 8 ? "Cast exception, return data can't be casted correctly" : "Network error,Load failed";
    }
}
