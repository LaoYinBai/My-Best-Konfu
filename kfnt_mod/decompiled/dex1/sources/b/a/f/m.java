package b.a.f;

import java.util.Arrays;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class m {

    /* renamed from: a, reason: collision with root package name */
    static final int f923a = 65535;

    /* renamed from: b, reason: collision with root package name */
    static final int f924b = 1;

    /* renamed from: c, reason: collision with root package name */
    static final int f925c = 2;
    static final int d = 4;
    static final int e = 5;
    static final int f = 6;
    static final int g = 7;
    static final int h = 10;
    private int i;
    private final int[] j = new int[10];

    void a() {
        this.i = 0;
        Arrays.fill(this.j, 0);
    }

    m a(int i, int i2) {
        if (i < 0 || i >= this.j.length) {
            return this;
        }
        this.i = (1 << i) | this.i;
        this.j[i] = i2;
        return this;
    }

    boolean a(int i) {
        return ((1 << i) & this.i) != 0;
    }

    int b(int i) {
        return this.j[i];
    }

    int b() {
        return Integer.bitCount(this.i);
    }

    int c() {
        if ((this.i & 2) != 0) {
            return this.j[1];
        }
        return -1;
    }

    boolean a(boolean z) {
        return ((this.i & 4) != 0 ? this.j[2] : z ? 1 : 0) == 1;
    }

    int c(int i) {
        return (this.i & 16) != 0 ? this.j[4] : i;
    }

    int d(int i) {
        return (this.i & 32) != 0 ? this.j[5] : i;
    }

    int e(int i) {
        return (this.i & 64) != 0 ? this.j[6] : i;
    }

    int d() {
        if ((this.i & 128) != 0) {
            return this.j[7];
        }
        return 65535;
    }

    void a(m mVar) {
        for (int i = 0; i < 10; i++) {
            if (mVar.a(i)) {
                a(i, mVar.b(i));
            }
        }
    }
}
