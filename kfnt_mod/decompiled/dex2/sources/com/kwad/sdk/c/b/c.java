package com.kwad.sdk.c.b;

import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    private static List<String> a = new ArrayList(2);

    static {
        a.add("application/x-javascript");
        a.add("image/jpeg");
        a.add("image/tiff");
        a.add("text/css");
        a.add("text/html");
        a.add("image/gif");
        a.add("image/png");
        a.add("application/javascript");
    }

    public static boolean a(String str) {
        return a.contains(str);
    }
}
