package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.b.a.d;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00070\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\n0\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\u0005\u001a\u001a\u0010\f\u001a\u00020\r*\b\u0012\u0004\u0012\u00020\u00030\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a\u001a\u0010\u0010\u001a\u00020\u0011*\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a\u001a\u0010\u0013\u001a\u00020\u0014*\b\u0012\u0004\u0012\u00020\u00070\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a\u001a\u0010\u0016\u001a\u00020\u0017*\b\u0012\u0004\u0012\u00020\n0\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, d2 = {"sum", "Lkotlin/UInt;", "", "Lkotlin/UByte;", "sumOfUByte", "(Ljava/lang/Iterable;)I", "sumOfUInt", "Lkotlin/ULong;", "sumOfULong", "(Ljava/lang/Iterable;)J", "Lkotlin/UShort;", "sumOfUShort", "toUByteArray", "Lkotlin/UByteArray;", "", "(Ljava/util/Collection;)[B", "toUIntArray", "Lkotlin/UIntArray;", "(Ljava/util/Collection;)[I", "toULongArray", "Lkotlin/ULongArray;", "(Ljava/util/Collection;)[J", "toUShortArray", "Lkotlin/UShortArray;", "(Ljava/util/Collection;)[S", "kotlin-stdlib"}, k = 5, mv = {1, 1, 15}, xi = 1, xs = "kotlin/collections/UCollectionsKt")
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class UCollectionsKt___UCollectionsKt {
    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @d
    public static final byte[] toUByteArray(@d Collection<UByte> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "$this$toUByteArray");
        byte[] bArrM715constructorimpl = UByteArray.m715constructorimpl(collection.size());
        Iterator<UByte> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            UByteArray.m726setVurrAj0(bArrM715constructorimpl, i, it.next().getData());
            i++;
        }
        return bArrM715constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @d
    public static final int[] toUIntArray(@d Collection<UInt> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "$this$toUIntArray");
        int[] iArrM784constructorimpl = UIntArray.m784constructorimpl(collection.size());
        Iterator<UInt> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            UIntArray.m795setVXSXFK8(iArrM784constructorimpl, i, it.next().getData());
            i++;
        }
        return iArrM784constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @d
    public static final long[] toULongArray(@d Collection<ULong> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "$this$toULongArray");
        long[] jArrM853constructorimpl = ULongArray.m853constructorimpl(collection.size());
        Iterator<ULong> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            ULongArray.m864setk8EXiF4(jArrM853constructorimpl, i, it.next().getData());
            i++;
        }
        return jArrM853constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @d
    public static final short[] toUShortArray(@d Collection<UShort> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "$this$toUShortArray");
        short[] sArrM948constructorimpl = UShortArray.m948constructorimpl(collection.size());
        Iterator<UShort> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            UShortArray.m959set01HTLdE(sArrM948constructorimpl, i, it.next().getData());
            i++;
        }
        return sArrM948constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @JvmName(name = "sumOfUInt")
    public static final int sumOfUInt(@d Iterable<UInt> iterable) {
        Intrinsics.checkParameterIsNotNull(iterable, "$this$sum");
        Iterator<UInt> it = iterable.iterator();
        int iM739constructorimpl = 0;
        while (it.hasNext()) {
            iM739constructorimpl = UInt.m739constructorimpl(iM739constructorimpl + it.next().getData());
        }
        return iM739constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @JvmName(name = "sumOfULong")
    public static final long sumOfULong(@d Iterable<ULong> iterable) {
        Intrinsics.checkParameterIsNotNull(iterable, "$this$sum");
        Iterator<ULong> it = iterable.iterator();
        long jM808constructorimpl = 0;
        while (it.hasNext()) {
            jM808constructorimpl = ULong.m808constructorimpl(jM808constructorimpl + it.next().getData());
        }
        return jM808constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @JvmName(name = "sumOfUByte")
    public static final int sumOfUByte(@d Iterable<UByte> iterable) {
        Intrinsics.checkParameterIsNotNull(iterable, "$this$sum");
        Iterator<UByte> it = iterable.iterator();
        int iM739constructorimpl = 0;
        while (it.hasNext()) {
            iM739constructorimpl = UInt.m739constructorimpl(iM739constructorimpl + UInt.m739constructorimpl(it.next().getData() & UByte.MAX_VALUE));
        }
        return iM739constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @JvmName(name = "sumOfUShort")
    public static final int sumOfUShort(@d Iterable<UShort> iterable) {
        Intrinsics.checkParameterIsNotNull(iterable, "$this$sum");
        Iterator<UShort> it = iterable.iterator();
        int iM739constructorimpl = 0;
        while (it.hasNext()) {
            iM739constructorimpl = UInt.m739constructorimpl(iM739constructorimpl + UInt.m739constructorimpl(it.next().getData() & UShort.MAX_VALUE));
        }
        return iM739constructorimpl;
    }
}
