package com.sigmob.volley;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class i {
    public final int a;
    public final byte[] b;
    public final Map<String, String> c;
    public final List<e> d;
    public final boolean e;
    public final long f;

    private i(int i, byte[] bArr, Map<String, String> map, List<e> list, boolean z, long j) {
        this.a = i;
        this.b = bArr;
        this.c = map;
        this.d = list == null ? null : Collections.unmodifiableList(list);
        this.e = z;
        this.f = j;
    }

    public i(int i, byte[] bArr, boolean z, long j, List<e> list) {
        this(i, bArr, a(list), list, z, j);
    }

    private static Map<String, String> a(List<e> list) {
        if (list == null) {
            return null;
        }
        if (list.isEmpty()) {
            return Collections.emptyMap();
        }
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (e eVar : list) {
            treeMap.put(eVar.a(), eVar.b());
        }
        return treeMap;
    }
}
