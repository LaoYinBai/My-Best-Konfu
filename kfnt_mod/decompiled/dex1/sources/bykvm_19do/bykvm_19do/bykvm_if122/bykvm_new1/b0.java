package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1;

import androidx.annotation.NonNull;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b0 {
    public static <T> void a(List<T> list, @NonNull Comparator<T> comparator) {
        if (list == null || list.size() <= 1) {
            return;
        }
        try {
            Collections.sort(list, comparator);
        } catch (Throwable unused) {
            List listAsList = Arrays.asList(list.toArray());
            Collections.sort(listAsList, comparator);
            list.clear();
            list.addAll(listAsList);
        }
    }

    public static <T extends Comparable<? super T>> void a(List<T> list) {
        if (list == null || list.size() <= 1) {
            return;
        }
        try {
            Collections.sort(list);
        } catch (Throwable unused) {
            List listAsList = Arrays.asList(list.toArray());
            Collections.sort(listAsList);
            list.clear();
            list.addAll(listAsList);
        }
    }
}
