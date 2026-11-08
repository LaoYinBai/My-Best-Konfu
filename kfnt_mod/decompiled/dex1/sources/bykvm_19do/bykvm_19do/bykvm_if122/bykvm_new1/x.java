package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1;

import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class x {
    public static <T> boolean a(List<T> list) {
        return list == null || list.size() == 0;
    }

    public static <T> boolean b(List<T> list) {
        return list == null || list.size() == 0 || list.get(0) == null;
    }
}
