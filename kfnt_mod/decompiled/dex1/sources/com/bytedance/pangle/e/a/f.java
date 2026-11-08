package com.bytedance.pangle.e.a;

import androidx.core.internal.view.SupportMenu;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    int[] f4597a;

    /* renamed from: b, reason: collision with root package name */
    int[] f4598b;

    public final String a(int i) {
        if (i < 0 || this.f4597a == null || i >= this.f4597a.length) {
            return null;
        }
        int i2 = this.f4597a[i];
        int iA = a(this.f4598b, i2);
        StringBuilder sb = new StringBuilder(iA);
        while (iA != 0) {
            i2 += 2;
            sb.append((char) a(this.f4598b, i2));
            iA--;
        }
        return sb.toString();
    }

    public final int a(String str) {
        if (str == null) {
            return -1;
        }
        for (int i = 0; i != this.f4597a.length; i++) {
            int i2 = this.f4597a[i];
            int iA = a(this.f4598b, i2);
            if (iA == str.length()) {
                int i3 = i2;
                int i4 = 0;
                while (i4 != iA) {
                    i3 += 2;
                    if (str.charAt(i4) != a(this.f4598b, i3)) {
                        break;
                    }
                    i4++;
                }
                if (i4 == iA) {
                    return i;
                }
            }
        }
        return -1;
    }

    f() {
    }

    private static final int a(int[] iArr, int i) {
        int i2 = iArr[i / 4];
        return (i % 4) / 2 == 0 ? i2 & SupportMenu.USER_MASK : i2 >>> 16;
    }
}
