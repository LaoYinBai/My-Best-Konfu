package com.kwai.filedownloader.kwai;

import com.kwai.filedownloader.e.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d {
    public static b a(Map<String, List<String>> map, b bVar, List<String> list) throws IllegalAccessException {
        int iE = bVar.e();
        String strA = bVar.a("Location");
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (a(iE)) {
            if (strA == null) {
                throw new IllegalAccessException(f.a("receive %d (redirect) but the location is null with response [%s]", Integer.valueOf(iE), bVar.c()));
            }
            if (com.kwai.filedownloader.e.d.a) {
                com.kwai.filedownloader.e.d.c(d.class, "redirect to %s with %d, %s", strA, Integer.valueOf(iE), arrayList);
            }
            bVar.f();
            bVar = a(map, strA);
            arrayList.add(strA);
            bVar.d();
            iE = bVar.e();
            strA = bVar.a("Location");
            i++;
            if (i >= 10) {
                throw new IllegalAccessException(f.a("redirect too many times! %s", arrayList));
            }
        }
        if (list != null) {
            list.addAll(arrayList);
        }
        return bVar;
    }

    private static b a(Map<String, List<String>> map, String str) {
        b bVarA = com.kwai.filedownloader.download.b.a().a(str);
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            if (value != null) {
                Iterator<String> it = value.iterator();
                while (it.hasNext()) {
                    bVarA.a(key, it.next());
                }
            }
        }
        return bVarA;
    }

    private static boolean a(int i) {
        return i == 301 || i == 302 || i == 303 || i == 300 || i == 307 || i == 308;
    }
}
