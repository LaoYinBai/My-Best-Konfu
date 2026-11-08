package b.b.a.e;

import java.util.HashMap;
import java.util.HashSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b extends a {
    public b(HashSet<String> hashSet, HashMap<String, HashSet<String>> map) {
        super(hashSet, map);
    }

    @Override // b.b.a.e.a
    public boolean a(String str) {
        return !this.f1060a.contains(str);
    }

    @Override // b.b.a.e.a
    public boolean a(HashSet<String> hashSet, String str) {
        return !hashSet.contains(str);
    }
}
