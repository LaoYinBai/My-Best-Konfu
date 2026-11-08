package com.bytedance.a;

import java.util.HashMap;
import java.util.HashSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class l extends g {
    public l(HashSet<String> hashSet, HashMap<String, HashSet<String>> map) {
        super(hashSet, map);
    }

    @Override // com.bytedance.a.g
    public boolean a(String str) {
        return !this.f3524a.contains(str);
    }

    @Override // com.bytedance.a.g
    public boolean a(HashSet<String> hashSet, String str) {
        return !hashSet.contains(str);
    }
}
