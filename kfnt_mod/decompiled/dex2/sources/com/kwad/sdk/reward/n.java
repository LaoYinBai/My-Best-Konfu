package com.kwad.sdk.reward;

import androidx.annotation.NonNull;
import com.kwad.sdk.core.diskcache.a.c;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.utils.aa;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class n {
    public static boolean a(@NonNull AdTemplate adTemplate) {
        if (b(adTemplate)) {
            return true;
        }
        String strA = com.kwad.sdk.core.response.a.a.a(com.kwad.sdk.core.response.a.d.j(adTemplate));
        String strA2 = aa.a(strA);
        long jCurrentTimeMillis = System.currentTimeMillis();
        com.kwad.sdk.core.d.a.c("VideoCacheHelper", "start cache video key:" + strA2 + "--url:" + strA);
        c.a aVar = new c.a();
        boolean zA = com.kwad.sdk.core.diskcache.a.a.a().a(strA, aVar);
        com.kwad.sdk.core.d.a.c("VideoCacheHelper", "finish cache video key:" + strA2 + "--cache time:" + (System.currentTimeMillis() - jCurrentTimeMillis) + "--success:" + zA);
        if (zA) {
            com.kwad.sdk.core.report.d.a(adTemplate, 1);
        } else {
            com.kwad.sdk.core.report.d.a(adTemplate, 1, aVar.a);
        }
        return zA;
    }

    public static boolean a(@NonNull String str, String str2, c.a aVar) {
        String strA = aa.a(str2);
        long jCurrentTimeMillis = System.currentTimeMillis();
        com.kwad.sdk.core.d.a.c("VideoCacheHelper", "start cache video key:" + strA + "--url:" + str);
        boolean zA = com.kwad.sdk.core.diskcache.a.a.a().a(str, str2, aVar);
        com.kwad.sdk.core.d.a.c("VideoCacheHelper", "finish cache video key:" + strA + "--cache time:" + (System.currentTimeMillis() - jCurrentTimeMillis) + "--success:" + zA);
        return zA;
    }

    public static boolean b(@NonNull AdTemplate adTemplate) {
        File fileB = com.kwad.sdk.core.diskcache.a.a.a().b(com.kwad.sdk.core.response.a.a.a(com.kwad.sdk.core.response.a.d.j(adTemplate)));
        return fileB != null && fileB.exists();
    }
}
