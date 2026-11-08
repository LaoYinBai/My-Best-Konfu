package kotlin.collections;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.b.a.d;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a,\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0086\u0002¢\u0006\u0002\u0010\u0004\u001a4\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0006H\u0086\u0002¢\u0006\u0002\u0010\u0007\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0086\u0002\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\tH\u0086\u0002\u001a,\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0087\b¢\u0006\u0002\u0010\u0004\u001a,\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0086\u0002¢\u0006\u0002\u0010\u0004\u001a4\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0006H\u0086\u0002¢\u0006\u0002\u0010\u0007\u001a-\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0086\u0002\u001a-\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\tH\u0086\u0002\u001a,\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0087\b¢\u0006\u0002\u0010\u0004¨\u0006\r"}, d2 = {"minus", "", "T", "element", "(Ljava/util/Set;Ljava/lang/Object;)Ljava/util/Set;", "elements", "", "(Ljava/util/Set;[Ljava/lang/Object;)Ljava/util/Set;", "", "Lkotlin/sequences/Sequence;", "minusElement", "plus", "plusElement", "kotlin-stdlib"}, k = 5, mv = {1, 1, 15}, xi = 1, xs = "kotlin/collections/SetsKt")
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class SetsKt___SetsKt extends SetsKt__SetsKt {
    @d
    public static final <T> Set<T> minus(@d Set<? extends T> set, T t) {
        Intrinsics.checkParameterIsNotNull(set, "$this$minus");
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt.mapCapacity(set.size()));
        boolean z = false;
        for (T t2 : set) {
            boolean z2 = true;
            if (!z && Intrinsics.areEqual(t2, t)) {
                z = true;
                z2 = false;
            }
            if (z2) {
                linkedHashSet.add(t2);
            }
        }
        return linkedHashSet;
    }

    @d
    public static final <T> Set<T> minus(@d Set<? extends T> set, @d T[] tArr) {
        Intrinsics.checkParameterIsNotNull(set, "$this$minus");
        Intrinsics.checkParameterIsNotNull(tArr, "elements");
        LinkedHashSet linkedHashSet = new LinkedHashSet(set);
        CollectionsKt.removeAll(linkedHashSet, tArr);
        return linkedHashSet;
    }

    @d
    public static final <T> Set<T> minus(@d Set<? extends T> set, @d Iterable<? extends T> iterable) {
        Intrinsics.checkParameterIsNotNull(set, "$this$minus");
        Intrinsics.checkParameterIsNotNull(iterable, "elements");
        Set<? extends T> set2 = set;
        Collection<?> collectionConvertToSetForSetOperationWith = CollectionsKt.convertToSetForSetOperationWith(iterable, set2);
        if (collectionConvertToSetForSetOperationWith.isEmpty()) {
            return CollectionsKt.toSet(set2);
        }
        if (collectionConvertToSetForSetOperationWith instanceof Set) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (T t : set2) {
                if (!collectionConvertToSetForSetOperationWith.contains(t)) {
                    linkedHashSet.add(t);
                }
            }
            return linkedHashSet;
        }
        LinkedHashSet linkedHashSet2 = new LinkedHashSet(set);
        linkedHashSet2.removeAll(collectionConvertToSetForSetOperationWith);
        return linkedHashSet2;
    }

    @d
    public static final <T> Set<T> minus(@d Set<? extends T> set, @d Sequence<? extends T> sequence) {
        Intrinsics.checkParameterIsNotNull(set, "$this$minus");
        Intrinsics.checkParameterIsNotNull(sequence, "elements");
        LinkedHashSet linkedHashSet = new LinkedHashSet(set);
        CollectionsKt.removeAll(linkedHashSet, sequence);
        return linkedHashSet;
    }

    @InlineOnly
    private static final <T> Set<T> minusElement(@d Set<? extends T> set, T t) {
        return SetsKt.minus(set, t);
    }

    @d
    public static final <T> Set<T> plus(@d Set<? extends T> set, T t) {
        Intrinsics.checkParameterIsNotNull(set, "$this$plus");
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt.mapCapacity(set.size() + 1));
        linkedHashSet.addAll(set);
        linkedHashSet.add(t);
        return linkedHashSet;
    }

    @d
    public static final <T> Set<T> plus(@d Set<? extends T> set, @d T[] tArr) {
        Intrinsics.checkParameterIsNotNull(set, "$this$plus");
        Intrinsics.checkParameterIsNotNull(tArr, "elements");
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt.mapCapacity(set.size() + tArr.length));
        linkedHashSet.addAll(set);
        CollectionsKt.addAll(linkedHashSet, tArr);
        return linkedHashSet;
    }

    @d
    public static final <T> Set<T> plus(@d Set<? extends T> set, @d Iterable<? extends T> iterable) {
        int size;
        Intrinsics.checkParameterIsNotNull(set, "$this$plus");
        Intrinsics.checkParameterIsNotNull(iterable, "elements");
        Integer numCollectionSizeOrNull = CollectionsKt.collectionSizeOrNull(iterable);
        if (numCollectionSizeOrNull != null) {
            size = set.size() + numCollectionSizeOrNull.intValue();
        } else {
            size = set.size() * 2;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt.mapCapacity(size));
        linkedHashSet.addAll(set);
        CollectionsKt.addAll(linkedHashSet, iterable);
        return linkedHashSet;
    }

    @d
    public static final <T> Set<T> plus(@d Set<? extends T> set, @d Sequence<? extends T> sequence) {
        Intrinsics.checkParameterIsNotNull(set, "$this$plus");
        Intrinsics.checkParameterIsNotNull(sequence, "elements");
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt.mapCapacity(set.size() * 2));
        linkedHashSet.addAll(set);
        CollectionsKt.addAll(linkedHashSet, sequence);
        return linkedHashSet;
    }

    @InlineOnly
    private static final <T> Set<T> plusElement(@d Set<? extends T> set, T t) {
        return SetsKt.plus(set, t);
    }
}
