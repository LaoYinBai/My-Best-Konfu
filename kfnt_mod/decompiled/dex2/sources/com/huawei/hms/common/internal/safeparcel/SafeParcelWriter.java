package com.huawei.hms.common.internal.safeparcel;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class SafeParcelWriter {
    private SafeParcelWriter() {
    }

    private static void a(Parcel parcel, int i, int i2) {
        if (i2 >= 65535) {
            parcel.writeInt(i | (-65536));
            parcel.writeInt(i2);
        } else {
            parcel.writeInt(i | (i2 << 16));
        }
    }

    private static int a(Parcel parcel, int i) {
        parcel.writeInt(i | (-65536));
        parcel.writeInt(0);
        return parcel.dataPosition();
    }

    private static void b(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.setDataPosition(i - 4);
        parcel.writeInt(iDataPosition - i);
        parcel.setDataPosition(iDataPosition);
    }

    private static <P extends Parcelable> void a(Parcel parcel, P p, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(1);
        int iDataPosition2 = parcel.dataPosition();
        p.writeToParcel(parcel, i);
        int iDataPosition3 = parcel.dataPosition();
        parcel.setDataPosition(iDataPosition);
        parcel.writeInt(iDataPosition3 - iDataPosition2);
        parcel.setDataPosition(iDataPosition3);
    }

    public static int beginObjectHeader(Parcel parcel) {
        return a(parcel, 20293);
    }

    public static void finishObjectHeader(Parcel parcel, int i) {
        b(parcel, i);
    }

    public static void writeBoolean(Parcel parcel, int i, boolean z) {
        a(parcel, i, 4);
        if (z) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
        }
    }

    public static void writeBooleanObject(Parcel parcel, int i, Boolean bool, boolean z) {
        if (bool != null) {
            a(parcel, i, 4);
            parcel.writeInt(bool.booleanValue() ? 1 : 0);
        } else if (z) {
            a(parcel, i, 0);
        }
    }

    public static void writeByte(Parcel parcel, int i, byte b) {
        a(parcel, i, 4);
        parcel.writeInt(b);
    }

    public static void writeChar(Parcel parcel, int i, char c) {
        a(parcel, i, 4);
        parcel.writeInt(c);
    }

    public static void writeShort(Parcel parcel, int i, short s) {
        a(parcel, i, 4);
        parcel.writeInt(s);
    }

    public static void writeInt(Parcel parcel, int i, int i2) {
        a(parcel, i, 4);
        parcel.writeInt(i2);
    }

    public static void writeIntegerObject(Parcel parcel, int i, Integer num, boolean z) {
        if (num != null) {
            a(parcel, i, 4);
            parcel.writeInt(num.intValue());
        } else if (z) {
            a(parcel, i, 0);
        }
    }

    public static void writeLong(Parcel parcel, int i, long j) {
        a(parcel, i, 8);
        parcel.writeLong(j);
    }

    public static void writeLongObject(Parcel parcel, int i, Long l, boolean z) {
        if (l != null) {
            a(parcel, i, 8);
            parcel.writeLong(l.longValue());
        } else if (z) {
            a(parcel, i, 0);
        }
    }

    public static void writeBigInteger(Parcel parcel, int i, BigInteger bigInteger, boolean z) {
        if (bigInteger != null) {
            int iA = a(parcel, i);
            parcel.writeByteArray(bigInteger.toByteArray());
            b(parcel, iA);
        } else if (z) {
            a(parcel, i, 0);
        }
    }

    public static void writeFloat(Parcel parcel, int i, float f) {
        a(parcel, i, 4);
        parcel.writeFloat(f);
    }

    public static void writeFloatObject(Parcel parcel, int i, Float f, boolean z) {
        if (f != null) {
            a(parcel, i, 4);
            parcel.writeFloat(f.floatValue());
        } else if (z) {
            a(parcel, i, 0);
        }
    }

    public static void writeDouble(Parcel parcel, int i, double d) {
        a(parcel, i, 8);
        parcel.writeDouble(d);
    }

    public static void writeDoubleObject(Parcel parcel, int i, Double d, boolean z) {
        if (d != null) {
            a(parcel, i, 8);
            parcel.writeDouble(d.doubleValue());
        } else if (z) {
            a(parcel, i, 0);
        }
    }

    public static void writeBigDecimal(Parcel parcel, int i, BigDecimal bigDecimal, boolean z) {
        if (bigDecimal == null) {
            if (z) {
                a(parcel, i, 0);
            }
        } else {
            int iA = a(parcel, i);
            parcel.writeByteArray(bigDecimal.unscaledValue().toByteArray());
            parcel.writeInt(bigDecimal.scale());
            b(parcel, iA);
        }
    }

    public static void writeString(Parcel parcel, int i, String str, boolean z) {
        if (str != null) {
            int iA = a(parcel, i);
            parcel.writeString(str);
            b(parcel, iA);
        } else if (z) {
            a(parcel, i, 0);
        }
    }

    public static void writeIBinder(Parcel parcel, int i, IBinder iBinder, boolean z) {
        if (iBinder != null) {
            int iA = a(parcel, i);
            parcel.writeStrongBinder(iBinder);
            b(parcel, iA);
        } else if (z) {
            a(parcel, i, 0);
        }
    }

    public static void writeParcelable(Parcel parcel, int i, Parcelable parcelable, int i2, boolean z) {
        if (parcelable != null) {
            int iA = a(parcel, i);
            parcelable.writeToParcel(parcel, i2);
            b(parcel, iA);
        } else if (z) {
            a(parcel, i, 0);
        }
    }

    public static void writeBundle(Parcel parcel, int i, Bundle bundle, boolean z) {
        if (bundle != null) {
            int iA = a(parcel, i);
            parcel.writeBundle(bundle);
            b(parcel, iA);
        } else if (z) {
            a(parcel, i, 0);
        }
    }

    public static void writeByteArray(Parcel parcel, int i, byte[] bArr, boolean z) {
        if (bArr != null) {
            int iA = a(parcel, i);
            parcel.writeByteArray(bArr);
            b(parcel, iA);
        } else if (z) {
            a(parcel, i, 0);
        }
    }

    public static void writeByteArrayArray(Parcel parcel, int i, byte[][] bArr, boolean z) {
        if (bArr == null) {
            if (z) {
                a(parcel, i, 0);
                return;
            }
            return;
        }
        int iA = a(parcel, i);
        parcel.writeInt(bArr.length);
        for (byte[] bArr2 : bArr) {
            parcel.writeByteArray(bArr2);
        }
        b(parcel, iA);
    }

    public static void writeBooleanArray(Parcel parcel, int i, boolean[] zArr, boolean z) {
        if (zArr != null) {
            int iA = a(parcel, i);
            parcel.writeBooleanArray(zArr);
            b(parcel, iA);
        } else if (z) {
            a(parcel, i, 0);
        }
    }

    public static void writeCharArray(Parcel parcel, int i, char[] cArr, boolean z) {
        if (cArr != null) {
            int iA = a(parcel, i);
            parcel.writeCharArray(cArr);
            b(parcel, iA);
        } else if (z) {
            a(parcel, i, 0);
        }
    }

    public static void writeIntArray(Parcel parcel, int i, int[] iArr, boolean z) {
        if (iArr != null) {
            int iA = a(parcel, i);
            parcel.writeIntArray(iArr);
            b(parcel, iA);
        } else if (z) {
            a(parcel, i, 0);
        }
    }

    public static void writeLongArray(Parcel parcel, int i, long[] jArr, boolean z) {
        if (jArr != null) {
            int iA = a(parcel, i);
            parcel.writeLongArray(jArr);
            b(parcel, iA);
        } else if (z) {
            a(parcel, i, 0);
        }
    }

    public static void writeBigIntegerArray(Parcel parcel, int i, BigInteger[] bigIntegerArr, boolean z) {
        if (bigIntegerArr == null) {
            if (z) {
                a(parcel, i, 0);
                return;
            }
            return;
        }
        int iA = a(parcel, i);
        parcel.writeInt(bigIntegerArr.length);
        for (BigInteger bigInteger : bigIntegerArr) {
            parcel.writeByteArray(bigInteger.toByteArray());
        }
        b(parcel, iA);
    }

    public static void writeFloatArray(Parcel parcel, int i, float[] fArr, boolean z) {
        if (fArr != null) {
            int iA = a(parcel, i);
            parcel.writeFloatArray(fArr);
            b(parcel, iA);
        } else if (z) {
            a(parcel, i, 0);
        }
    }

    public static void writeDoubleArray(Parcel parcel, int i, double[] dArr, boolean z) {
        if (dArr != null) {
            int iA = a(parcel, i);
            parcel.writeDoubleArray(dArr);
            b(parcel, iA);
        } else if (z) {
            a(parcel, i, 0);
        }
    }

    public static void writeBigDecimalArray(Parcel parcel, int i, BigDecimal[] bigDecimalArr, boolean z) {
        if (bigDecimalArr == null) {
            if (z) {
                a(parcel, i, 0);
                return;
            }
            return;
        }
        int iA = a(parcel, i);
        int length = bigDecimalArr.length;
        parcel.writeInt(length);
        for (int i2 = 0; i2 < length; i2++) {
            parcel.writeByteArray(bigDecimalArr[i2].unscaledValue().toByteArray());
            parcel.writeInt(bigDecimalArr[i2].scale());
        }
        b(parcel, iA);
    }

    public static void writeStringArray(Parcel parcel, int i, String[] strArr, boolean z) {
        if (strArr != null) {
            int iA = a(parcel, i);
            parcel.writeStringArray(strArr);
            b(parcel, iA);
        } else if (z) {
            a(parcel, i, 0);
        }
    }

    public static void writeIBinderArray(Parcel parcel, int i, IBinder[] iBinderArr, boolean z) {
        if (iBinderArr != null) {
            int iA = a(parcel, i);
            parcel.writeBinderArray(iBinderArr);
            b(parcel, iA);
        } else if (z) {
            a(parcel, i, 0);
        }
    }

    public static void writeBooleanList(Parcel parcel, int i, List<Boolean> list, boolean z) {
        if (list == null) {
            if (z) {
                a(parcel, i, 0);
                return;
            }
            return;
        }
        int iA = a(parcel, i);
        int size = list.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            parcel.writeInt(list.get(i2).booleanValue() ? 1 : 0);
        }
        b(parcel, iA);
    }

    public static void writeIntegerList(Parcel parcel, int i, List<Integer> list, boolean z) {
        if (list == null) {
            if (z) {
                a(parcel, i, 0);
                return;
            }
            return;
        }
        int iA = a(parcel, i);
        int size = list.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            parcel.writeInt(list.get(i2).intValue());
        }
        b(parcel, iA);
    }

    public static void writeLongList(Parcel parcel, int i, List<Long> list, boolean z) {
        if (list == null) {
            if (z) {
                a(parcel, i, 0);
                return;
            }
            return;
        }
        int iA = a(parcel, i);
        int size = list.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            parcel.writeLong(list.get(i2).longValue());
        }
        b(parcel, iA);
    }

    public static void writeFloatList(Parcel parcel, int i, List<Float> list, boolean z) {
        if (list == null) {
            if (z) {
                a(parcel, i, 0);
                return;
            }
            return;
        }
        int iA = a(parcel, i);
        int size = list.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            parcel.writeFloat(list.get(i2).floatValue());
        }
        b(parcel, iA);
    }

    public static void writeDoubleList(Parcel parcel, int i, List<Double> list, boolean z) {
        if (list == null) {
            if (z) {
                a(parcel, i, 0);
                return;
            }
            return;
        }
        int iA = a(parcel, i);
        int size = list.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            parcel.writeDouble(list.get(i2).doubleValue());
        }
        b(parcel, iA);
    }

    public static void writeStringList(Parcel parcel, int i, List<String> list, boolean z) {
        if (list != null) {
            int iA = a(parcel, i);
            parcel.writeStringList(list);
            b(parcel, iA);
        } else if (z) {
            a(parcel, i, 0);
        }
    }

    public static void writeIBinderList(Parcel parcel, int i, List<IBinder> list, boolean z) {
        if (list != null) {
            int iA = a(parcel, i);
            parcel.writeBinderList(list);
            b(parcel, iA);
        } else if (z) {
            a(parcel, i, 0);
        }
    }

    public static <P extends Parcelable> void writeTypedArray(Parcel parcel, int i, P[] pArr, int i2, boolean z) {
        if (pArr == null) {
            if (z) {
                a(parcel, i, 0);
                return;
            }
            return;
        }
        int iA = a(parcel, i);
        parcel.writeInt(iA);
        int length = pArr.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (pArr[i3] != null) {
                a(parcel, pArr[i3], i2);
            } else {
                parcel.writeInt(0);
            }
        }
        b(parcel, iA);
    }

    public static <T extends Parcelable> void writeTypedList(Parcel parcel, int i, List<T> list, boolean z) {
        if (list == null) {
            if (z) {
                a(parcel, i, 0);
                return;
            }
            return;
        }
        int iA = a(parcel, i);
        int size = list.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            T t = list.get(i2);
            if (t != null) {
                a(parcel, t, 0);
            } else {
                parcel.writeInt(0);
            }
        }
        b(parcel, iA);
    }

    public static void writeParcel(Parcel parcel, int i, Parcel parcel2, boolean z) {
        if (parcel2 != null) {
            int iA = a(parcel, i);
            parcel.appendFrom(parcel2, 0, parcel2.dataSize());
            b(parcel, iA);
        } else if (z) {
            a(parcel, i, 0);
        }
    }

    public static void writeParcelArray(Parcel parcel, int i, Parcel[] parcelArr, boolean z) {
        if (parcelArr == null) {
            if (z) {
                a(parcel, i, 0);
                return;
            }
            return;
        }
        int iA = a(parcel, i);
        int length = parcelArr.length;
        parcel.writeInt(length);
        for (int i2 = 0; i2 < length; i2++) {
            if (parcelArr[i2] == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(parcelArr[i2].dataSize());
                parcel.appendFrom(parcelArr[i2], 0, parcelArr[i2].dataSize());
            }
        }
        b(parcel, iA);
    }

    public static void writeParcelList(Parcel parcel, int i, List<Parcel> list, boolean z) {
        if (list == null) {
            if (z) {
                a(parcel, i, 0);
                return;
            }
            return;
        }
        int iA = a(parcel, i);
        int size = list.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            Parcel parcel2 = list.get(i2);
            if (parcel2 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(parcel2.dataSize());
                parcel.appendFrom(parcel2, 0, parcel2.dataSize());
            }
        }
        b(parcel, iA);
    }

    public static void writeList(Parcel parcel, int i, List list, boolean z) {
        if (list != null) {
            int iA = a(parcel, i);
            parcel.writeList(list);
            b(parcel, iA);
        } else if (z) {
            a(parcel, i, 0);
        }
    }

    public static void writeSparseBooleanArray(Parcel parcel, int i, SparseBooleanArray sparseBooleanArray, boolean z) {
        if (sparseBooleanArray != null) {
            int iA = a(parcel, i);
            parcel.writeSparseBooleanArray(sparseBooleanArray);
            b(parcel, iA);
        } else if (z) {
            a(parcel, i, 0);
        }
    }

    public static void writeDoubleSparseArray(Parcel parcel, int i, SparseArray<Double> sparseArray, boolean z) {
        if (sparseArray == null) {
            if (z) {
                a(parcel, i, 0);
                return;
            }
            return;
        }
        int iA = a(parcel, i);
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            parcel.writeInt(sparseArray.keyAt(i2));
            parcel.writeDouble(sparseArray.valueAt(i2).doubleValue());
        }
        b(parcel, iA);
    }

    public static void writeFloatSparseArray(Parcel parcel, int i, SparseArray<Float> sparseArray, boolean z) {
        if (sparseArray == null) {
            if (z) {
                a(parcel, i, 0);
                return;
            }
            return;
        }
        int iA = a(parcel, i);
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            parcel.writeInt(sparseArray.keyAt(i2));
            parcel.writeFloat(sparseArray.valueAt(i2).floatValue());
        }
        b(parcel, iA);
    }

    public static void writeSparseIntArray(Parcel parcel, int i, SparseIntArray sparseIntArray, boolean z) {
        if (sparseIntArray == null) {
            if (z) {
                a(parcel, i, 0);
                return;
            }
            return;
        }
        int iA = a(parcel, i);
        int size = sparseIntArray.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            parcel.writeInt(sparseIntArray.keyAt(i2));
            parcel.writeInt(sparseIntArray.valueAt(i2));
        }
        b(parcel, iA);
    }

    public static void writeSparseLongArray(Parcel parcel, int i, SparseLongArray sparseLongArray, boolean z) {
        if (sparseLongArray == null) {
            if (z) {
                a(parcel, i, 0);
                return;
            }
            return;
        }
        int iA = a(parcel, i);
        int size = Build.VERSION.SDK_INT >= 18 ? sparseLongArray.size() : 0;
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            if (Build.VERSION.SDK_INT >= 18) {
                parcel.writeInt(sparseLongArray.keyAt(i2));
            }
            if (Build.VERSION.SDK_INT >= 18) {
                parcel.writeLong(sparseLongArray.valueAt(i2));
            }
        }
        b(parcel, iA);
    }

    public static void writeStringSparseArray(Parcel parcel, int i, SparseArray<String> sparseArray, boolean z) {
        if (sparseArray == null) {
            if (z) {
                a(parcel, i, 0);
                return;
            }
            return;
        }
        int iA = a(parcel, i);
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            parcel.writeInt(sparseArray.keyAt(i2));
            parcel.writeString(sparseArray.valueAt(i2));
        }
        b(parcel, iA);
    }

    public static void writeParcelSparseArray(Parcel parcel, int i, SparseArray<Parcel> sparseArray, boolean z) {
        if (sparseArray == null) {
            if (z) {
                a(parcel, i, 0);
                return;
            }
            return;
        }
        int iA = a(parcel, i);
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            parcel.writeInt(sparseArray.keyAt(i2));
            Parcel parcelValueAt = sparseArray.valueAt(i2);
            if (parcelValueAt == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(parcelValueAt.dataSize());
                parcel.appendFrom(parcelValueAt, 0, parcelValueAt.dataSize());
            }
        }
        b(parcel, iA);
    }

    public static <T extends Parcelable> void writeTypedSparseArray(Parcel parcel, int i, SparseArray<T> sparseArray, boolean z) {
        if (sparseArray == null) {
            if (z) {
                a(parcel, i, 0);
                return;
            }
            return;
        }
        int iA = a(parcel, i);
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            parcel.writeInt(sparseArray.keyAt(i2));
            T tValueAt = sparseArray.valueAt(i2);
            if (tValueAt != null) {
                a(parcel, tValueAt, 0);
            } else {
                parcel.writeInt(0);
            }
        }
        b(parcel, iA);
    }

    public static void writeIBinderSparseArray(Parcel parcel, int i, SparseArray<IBinder> sparseArray, boolean z) {
        if (sparseArray == null) {
            if (z) {
                a(parcel, i, 0);
                return;
            }
            return;
        }
        int iA = a(parcel, i);
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            parcel.writeInt(sparseArray.keyAt(i2));
            parcel.writeStrongBinder(sparseArray.valueAt(i2));
        }
        b(parcel, iA);
    }

    public static void writeByteArraySparseArray(Parcel parcel, int i, SparseArray<byte[]> sparseArray, boolean z) {
        if (sparseArray == null) {
            if (z) {
                a(parcel, i, 0);
                return;
            }
            return;
        }
        int iA = a(parcel, i);
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            parcel.writeInt(sparseArray.keyAt(i2));
            parcel.writeByteArray(sparseArray.valueAt(i2));
        }
        b(parcel, iA);
    }
}
