package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1;

import androidx.core.internal.view.SupportMenu;
import java.util.Arrays;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class n {

    /* renamed from: a, reason: collision with root package name */
    private int f1655a;

    /* renamed from: b, reason: collision with root package name */
    private final int[] f1656b = new int[10];

    void a() {
        this.f1655a = 0;
        Arrays.fill(this.f1656b, 0);
    }

    int b() {
        if ((this.f1655a & 2) != 0) {
            return this.f1656b[1];
        }
        return -1;
    }

    int c(int i) {
        return (this.f1655a & 32) != 0 ? this.f1656b[5] : i;
    }

    boolean d(int i) {
        return ((1 << i) & this.f1655a) != 0;
    }

    int b(int i) {
        return (this.f1655a & 16) != 0 ? this.f1656b[4] : i;
    }

    int c() {
        return (this.f1655a & 128) != 0 ? this.f1656b[7] : SupportMenu.USER_MASK;
    }

    int d() {
        return Integer.bitCount(this.f1655a);
    }

    n a(int i, int i2) {
        if (i >= 0) {
            int[] iArr = this.f1656b;
            if (i < iArr.length) {
                this.f1655a = (1 << i) | this.f1655a;
                iArr[i] = i2;
            }
        }
        return this;
    }

    int a(int i) {
        return this.f1656b[i];
    }

    void a(n nVar) {
        for (int i = 0; i < 10; i++) {
            if (nVar.d(i)) {
                a(i, nVar.a(i));
            }
        }
    }
}
