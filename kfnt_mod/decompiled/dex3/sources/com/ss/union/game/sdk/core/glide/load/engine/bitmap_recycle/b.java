package com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
import com.ss.union.game.sdk.core.glide.util.Util;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class b implements d {
    private final C0079b a = new C0079b();
    private final c<a, Bitmap> b = new c<>();

    b() {
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.d
    public void put(Bitmap bitmap) {
        this.b.a(this.a.a(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig()), bitmap);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.d
    public Bitmap get(int i, int i2, Bitmap.Config config) {
        return this.b.a((c<a, Bitmap>) this.a.a(i, i2, config));
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.d
    public Bitmap removeLast() {
        return this.b.a();
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.d
    public String logBitmap(Bitmap bitmap) {
        return a(bitmap);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.d
    public String logBitmap(int i, int i2, Bitmap.Config config) {
        return a(i, i2, config);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.d
    public int getSize(Bitmap bitmap) {
        return Util.getBitmapByteSize(bitmap);
    }

    public String toString() {
        return "AttributeStrategy:\n  " + this.b;
    }

    private static String a(Bitmap bitmap) {
        return a(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
    }

    static String a(int i, int i2, Bitmap.Config config) {
        return "[" + i + "x" + i2 + "], " + config;
    }

    /* renamed from: com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.b$b, reason: collision with other inner class name */
    static class C0079b extends BaseKeyPool<a> {
        C0079b() {
        }

        a a(int i, int i2, Bitmap.Config config) {
            a aVarC = c();
            aVarC.a(i, i2, config);
            return aVarC;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BaseKeyPool
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a b() {
            return new a(this);
        }
    }

    static class a implements e {
        private final C0079b a;
        private int b;
        private int c;
        private Bitmap.Config d;

        public a(C0079b c0079b) {
            this.a = c0079b;
        }

        public void a(int i, int i2, Bitmap.Config config) {
            this.b = i;
            this.c = i2;
            this.d = config;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.b == aVar.b && this.c == aVar.c && this.d == aVar.d;
        }

        public int hashCode() {
            return (((this.b * 31) + this.c) * 31) + (this.d != null ? this.d.hashCode() : 0);
        }

        public String toString() {
            return b.a(this.b, this.c, this.d);
        }

        @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.e
        public void a() {
            this.a.offer(this);
        }
    }
}
