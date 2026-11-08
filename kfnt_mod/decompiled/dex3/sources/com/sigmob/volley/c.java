package com.sigmob.volley;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c implements p {
    private final int a;
    private final float b;
    private final int c;
    private int d;
    private int e;

    public c() {
        this(10000, 2, 0.0f);
    }

    public c(int i, int i2, float f) {
        this.c = i;
        this.d = i;
        this.a = i2;
        this.b = f;
    }

    public c(int i, int i2, int i3, float f) {
        this.c = i;
        this.d = i2;
        this.a = i3;
        this.b = f;
    }

    @Override // com.sigmob.volley.p
    public int a() {
        return this.d;
    }

    @Override // com.sigmob.volley.p
    public void a(s sVar) throws s {
        this.e++;
        this.d += (int) (this.d * this.b);
        if (!d()) {
            throw sVar;
        }
    }

    @Override // com.sigmob.volley.p
    public int b() {
        return this.e;
    }

    @Override // com.sigmob.volley.p
    public int c() {
        return this.c;
    }

    protected boolean d() {
        return this.e <= this.a;
    }
}
