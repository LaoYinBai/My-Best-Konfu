package com.ss.union.game.sdk.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ArrayUtils {
    public static boolean isEmpty(List list) {
        return list == null || list.size() <= 0;
    }

    public static boolean isEmpty(Object[] objArr) {
        return objArr == null || objArr.length <= 0;
    }

    public static boolean isEmpty(short[] sArr) {
        return sArr == null || sArr.length <= 0;
    }

    public static boolean isEmpty(int[] iArr) {
        return iArr == null || iArr.length <= 0;
    }

    public static boolean isEmpty(long[] jArr) {
        return jArr == null || jArr.length <= 0;
    }

    public static boolean isEmpty(float[] fArr) {
        return fArr == null || fArr.length <= 0;
    }

    public static boolean isEmpty(double[] dArr) {
        return dArr == null || dArr.length <= 0;
    }

    public static boolean isEmpty(char[] cArr) {
        return cArr == null || cArr.length <= 0;
    }

    public static boolean isEmpty(boolean[] zArr) {
        return zArr == null || zArr.length <= 0;
    }

    public static boolean isEmpty(byte[] bArr) {
        return bArr == null || bArr.length <= 0;
    }

    public static <T> T[] list2Array(List<T> list) {
        if (isEmpty(list)) {
            return null;
        }
        Object[] objArr = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            objArr[i] = list.get(i);
        }
        return (T[]) objArr;
    }

    public static <T> List<T> array2List(T[] tArr) {
        if (isEmpty(tArr)) {
            return null;
        }
        return Arrays.asList(tArr);
    }

    public static List<Short> array2List(short[] sArr) {
        if (isEmpty(sArr)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < sArr.length; i++) {
            arrayList.set(i, Short.valueOf(sArr[i]));
        }
        return arrayList;
    }

    public static List<Integer> array2List(int[] iArr) {
        if (isEmpty(iArr)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < iArr.length; i++) {
            arrayList.set(i, Integer.valueOf(iArr[i]));
        }
        return arrayList;
    }

    public static List<Long> array2List(long[] jArr) {
        if (isEmpty(jArr)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jArr.length; i++) {
            arrayList.set(i, Long.valueOf(jArr[i]));
        }
        return arrayList;
    }

    public static List<Float> array2List(float[] fArr) {
        if (isEmpty(fArr)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < fArr.length; i++) {
            arrayList.set(i, Float.valueOf(fArr[i]));
        }
        return arrayList;
    }

    public static List<Double> array2List(double[] dArr) {
        if (isEmpty(dArr)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < dArr.length; i++) {
            arrayList.set(i, Double.valueOf(dArr[i]));
        }
        return arrayList;
    }

    public static List<Byte> array2List(byte[] bArr) {
        if (isEmpty(bArr)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < bArr.length; i++) {
            arrayList.set(i, Byte.valueOf(bArr[i]));
        }
        return arrayList;
    }

    public static List<Character> array2List(char[] cArr) {
        if (isEmpty(cArr)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cArr.length; i++) {
            arrayList.set(i, Character.valueOf(cArr[i]));
        }
        return arrayList;
    }

    public static List<Boolean> array2List(boolean[] zArr) {
        if (isEmpty(zArr)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < zArr.length; i++) {
            arrayList.set(i, Boolean.valueOf(zArr[i]));
        }
        return arrayList;
    }
}
