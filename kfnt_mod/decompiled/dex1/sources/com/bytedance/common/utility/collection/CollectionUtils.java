package com.bytedance.common.utility.collection;

import com.bytedance.common.utility.reflect.JavaCalls;
import java.util.Collection;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CollectionUtils {
    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    public static void clear(Object obj) {
        JavaCalls.callMethod(obj, "clear", new Object[0]);
    }
}
