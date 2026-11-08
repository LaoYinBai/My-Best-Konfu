package com.bytedance.b.a.a.e;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d {

    public interface a<A, B> {
        boolean a(A a2, B b2);
    }

    public static boolean a(List<?> list) {
        return list == null || list.size() == 0;
    }

    public static String[] a(Set<String> set) {
        String[] strArr = new String[set.size()];
        Iterator<String> it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            strArr[i] = it.next();
            i++;
        }
        return strArr;
    }

    public static String a(Collection collection, String str) {
        if (collection == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Object obj : collection) {
            if (z) {
                z = false;
            } else {
                sb.append(str);
            }
            sb.append(String.valueOf(obj));
        }
        return sb.toString();
    }

    public static <L, O> boolean a(Collection<L> collection, O o, a<? super L, O> aVar) {
        Iterator<L> it = collection.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (aVar.a(it.next(), o)) {
                it.remove();
                z = true;
            }
        }
        return z;
    }
}
