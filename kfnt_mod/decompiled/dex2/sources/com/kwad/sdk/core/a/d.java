package com.kwad.sdk.core.a;

import androidx.annotation.NonNull;
import com.kwad.sdk.plugin.DevelopMangerPlugin;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d {
    private static i a;

    private static i a() {
        if (a != null) {
            return a;
        }
        DevelopMangerPlugin.DevelopValue developValueA = ((DevelopMangerPlugin) com.kwad.sdk.plugin.f.a(DevelopMangerPlugin.class)).a("KEY_CLOSE_AES");
        if (developValueA != null && ((Boolean) developValueA.getValue()).booleanValue()) {
            a = new g();
            return a;
        }
        com.kwad.sdk.plugin.a aVar = (com.kwad.sdk.plugin.a) com.kwad.sdk.plugin.f.a(com.kwad.sdk.plugin.a.class);
        a = (aVar == null || aVar.b() == null) ? new g() : aVar.b();
        return a;
    }

    public static String a(String str) {
        return a().a(str);
    }

    public static void a(String str, Map<String, String> map, String str2) {
        a().a(str, map, str2);
    }

    public static void a(@NonNull Map<String, String> map) {
        a().a(map);
    }

    public static String b(String str) {
        DevelopMangerPlugin.DevelopValue developValueA = ((DevelopMangerPlugin) com.kwad.sdk.plugin.f.a(DevelopMangerPlugin.class)).a("KEY_HOST_ENCRYPT_DISABLE");
        return (developValueA == null || !((Boolean) developValueA.getValue()).booleanValue()) ? a().b(str) : str;
    }
}
