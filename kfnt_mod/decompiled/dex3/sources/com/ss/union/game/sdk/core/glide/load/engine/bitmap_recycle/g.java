package com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
import androidx.annotation.RequiresApi;
import com.ss.union.game.sdk.core.glide.util.Util;
import java.util.NavigableMap;

@RequiresApi(19)
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class g implements d {
    private static final int a = 8;
    private final b b = new b();
    private final c<a, Bitmap> c = new c<>();
    private final NavigableMap<Integer, Integer> d = new f();

    g() {
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.d
    public void put(Bitmap bitmap) {
        a aVarA = this.b.a(Util.getBitmapByteSize(bitmap));
        this.c.a(aVarA, bitmap);
        Integer num = (Integer) this.d.get(Integer.valueOf(aVarA.a));
        this.d.put(Integer.valueOf(aVarA.a), Integer.valueOf(num != null ? 1 + num.intValue() : 1));
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.d
    public Bitmap get(int i, int i2, Bitmap.Config config) {
        int bitmapByteSize = Util.getBitmapByteSize(i, i2, config);
        a aVarA = this.b.a(bitmapByteSize);
        Integer numCeilingKey = this.d.ceilingKey(Integer.valueOf(bitmapByteSize));
        if (numCeilingKey != null && numCeilingKey.intValue() != bitmapByteSize && numCeilingKey.intValue() <= bitmapByteSize * 8) {
            this.b.offer(aVarA);
            aVarA = this.b.a(numCeilingKey.intValue());
        }
        Bitmap bitmapA = this.c.a((c<a, Bitmap>) aVarA);
        if (bitmapA != null) {
            bitmapA.reconfigure(i, i2, config);
            a(numCeilingKey);
        }
        return bitmapA;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.d
    public Bitmap removeLast() {
        Bitmap bitmapA = this.c.a();
        if (bitmapA != null) {
            a(Integer.valueOf(Util.getBitmapByteSize(bitmapA)));
        }
        return bitmapA;
    }

    private void a(Integer num) {
        Integer num2 = (Integer) this.d.get(num);
        if (num2.intValue() == 1) {
            this.d.remove(num);
        } else {
            this.d.put(num, Integer.valueOf(num2.intValue() - 1));
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.d
    public String logBitmap(Bitmap bitmap) {
        return a(bitmap);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.d
    public String logBitmap(int i, int i2, Bitmap.Config config) {
        return a(Util.getBitmapByteSize(i, i2, config));
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.d
    public int getSize(Bitmap bitmap) {
        return Util.getBitmapByteSize(bitmap);
    }

    public String toString() {
        return "SizeStrategy:\n  " + this.c + "\n  SortedSizes" + this.d;
    }

    private static String a(Bitmap bitmap) {
        return a(Util.getBitmapByteSize(bitmap));
    }

    static String a(int i) {
        return "[" + i + "]";
    }

    static class b extends BaseKeyPool<a> {
        b() {
        }

        public a a(int i) {
            a aVar = (a) super.c();
            aVar.a(i);
            return aVar;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BaseKeyPool
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a b() {
            return new a(this);
        }
    }

    static final class a implements e {
        int a;
        private final b b;

        a(b bVar) {
            this.b = bVar;
        }

        public void a(int i) {
            this.a = i;
        }

        public boolean equals(Object obj) {
            return (obj instanceof a) && this.a == ((a) obj).a;
        }

        public int hashCode() {
            return this.a;
        }

        public String toString() {
            return g.a(this.a);
        }

        @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.e
        public void a() {
            this.b.offer(this);
        }
    }
}
