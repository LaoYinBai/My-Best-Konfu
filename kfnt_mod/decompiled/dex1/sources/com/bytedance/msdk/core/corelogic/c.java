package com.bytedance.msdk.core.corelogic;

import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {
    public static void a(List<Integer> list) {
        if (list != null) {
            if (list.size() <= 1) {
                return;
            }
            int[] iArr = new int[list.size()];
            for (int i = 0; i < iArr.length; i++) {
                iArr[i] = list.get(i).intValue();
            }
            for (int i2 = 1; i2 < iArr.length; i2++) {
                int i3 = iArr[i2];
                int i4 = i2;
                while (i4 > 0) {
                    int i5 = i4 - 1;
                    if (iArr[i5] > i3) {
                        iArr[i4] = iArr[i5];
                        i4--;
                    }
                }
                iArr[i4] = i3;
            }
            list.clear();
            for (int i6 : iArr) {
                list.add(Integer.valueOf(i6));
            }
        }
    }
}
