package com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
import android.os.Build;
import androidx.annotation.RequiresApi;
import com.ss.union.game.sdk.core.glide.util.Util;
import com.umeng.message.proguard.l;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

@RequiresApi(19)
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SizeConfigStrategy implements d {
    private static final int a = 8;
    private static final Bitmap.Config[] b;
    private static final Bitmap.Config[] c;
    private static final Bitmap.Config[] d;
    private static final Bitmap.Config[] e;
    private static final Bitmap.Config[] f;
    private final b g = new b();
    private final c<a, Bitmap> h = new c<>();
    private final Map<Bitmap.Config, NavigableMap<Integer, Integer>> i = new HashMap();

    static {
        Bitmap.Config[] configArr = {Bitmap.Config.ARGB_8888, null};
        if (Build.VERSION.SDK_INT >= 26) {
            configArr = (Bitmap.Config[]) Arrays.copyOf(configArr, configArr.length + 1);
            configArr[configArr.length - 1] = Bitmap.Config.RGBA_F16;
        }
        b = configArr;
        c = b;
        d = new Bitmap.Config[]{Bitmap.Config.RGB_565};
        e = new Bitmap.Config[]{Bitmap.Config.ARGB_4444};
        f = new Bitmap.Config[]{Bitmap.Config.ALPHA_8};
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.d
    public void put(Bitmap bitmap) {
        a aVarA = this.g.a(Util.getBitmapByteSize(bitmap), bitmap.getConfig());
        this.h.a(aVarA, bitmap);
        NavigableMap<Integer, Integer> navigableMapA = a(bitmap.getConfig());
        Integer num = (Integer) navigableMapA.get(Integer.valueOf(aVarA.a));
        navigableMapA.put(Integer.valueOf(aVarA.a), Integer.valueOf(num != null ? 1 + num.intValue() : 1));
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.d
    public Bitmap get(int i, int i2, Bitmap.Config config) {
        a aVarB = b(Util.getBitmapByteSize(i, i2, config), config);
        Bitmap bitmapA = this.h.a((c<a, Bitmap>) aVarB);
        if (bitmapA != null) {
            a(Integer.valueOf(aVarB.a), bitmapA);
            bitmapA.reconfigure(i, i2, config);
        }
        return bitmapA;
    }

    private a b(int i, Bitmap.Config config) {
        a aVarA = this.g.a(i, config);
        for (Bitmap.Config config2 : b(config)) {
            Integer numCeilingKey = a(config2).ceilingKey(Integer.valueOf(i));
            if (numCeilingKey != null && numCeilingKey.intValue() <= i * 8) {
                if (numCeilingKey.intValue() == i) {
                    if (config2 == null) {
                        if (config == null) {
                            return aVarA;
                        }
                    } else if (config2.equals(config)) {
                        return aVarA;
                    }
                }
                this.g.offer(aVarA);
                return this.g.a(numCeilingKey.intValue(), config2);
            }
        }
        return aVarA;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.d
    public Bitmap removeLast() {
        Bitmap bitmapA = this.h.a();
        if (bitmapA != null) {
            a(Integer.valueOf(Util.getBitmapByteSize(bitmapA)), bitmapA);
        }
        return bitmapA;
    }

    private void a(Integer num, Bitmap bitmap) {
        NavigableMap<Integer, Integer> navigableMapA = a(bitmap.getConfig());
        Integer num2 = (Integer) navigableMapA.get(num);
        if (num2 == null) {
            throw new NullPointerException("Tried to decrement empty size, size: " + num + ", removed: " + logBitmap(bitmap) + ", this: " + this);
        }
        if (num2.intValue() == 1) {
            navigableMapA.remove(num);
        } else {
            navigableMapA.put(num, Integer.valueOf(num2.intValue() - 1));
        }
    }

    private NavigableMap<Integer, Integer> a(Bitmap.Config config) {
        NavigableMap<Integer, Integer> navigableMap = this.i.get(config);
        if (navigableMap != null) {
            return navigableMap;
        }
        TreeMap treeMap = new TreeMap();
        this.i.put(config, treeMap);
        return treeMap;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.d
    public String logBitmap(Bitmap bitmap) {
        return a(Util.getBitmapByteSize(bitmap), bitmap.getConfig());
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.d
    public String logBitmap(int i, int i2, Bitmap.Config config) {
        return a(Util.getBitmapByteSize(i, i2, config), config);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.d
    public int getSize(Bitmap bitmap) {
        return Util.getBitmapByteSize(bitmap);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SizeConfigStrategy{groupedMap=");
        sb.append(this.h);
        sb.append(", sortedSizes=(");
        for (Map.Entry<Bitmap.Config, NavigableMap<Integer, Integer>> entry : this.i.entrySet()) {
            sb.append(entry.getKey());
            sb.append('[');
            sb.append(entry.getValue());
            sb.append("], ");
        }
        if (!this.i.isEmpty()) {
            sb.replace(sb.length() - 2, sb.length(), "");
        }
        sb.append(")}");
        return sb.toString();
    }

    static class b extends BaseKeyPool<a> {
        b() {
        }

        public a a(int i, Bitmap.Config config) {
            a aVarC = c();
            aVarC.a(i, config);
            return aVarC;
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
        private Bitmap.Config c;

        public a(b bVar) {
            this.b = bVar;
        }

        a(b bVar, int i, Bitmap.Config config) {
            this(bVar);
            a(i, config);
        }

        public void a(int i, Bitmap.Config config) {
            this.a = i;
            this.c = config;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.e
        public void a() {
            this.b.offer(this);
        }

        public String toString() {
            return SizeConfigStrategy.a(this.a, this.c);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.a == aVar.a && Util.bothNullOrEqual(this.c, aVar.c);
        }

        public int hashCode() {
            return (this.a * 31) + (this.c != null ? this.c.hashCode() : 0);
        }
    }

    static String a(int i, Bitmap.Config config) {
        return "[" + i + "](" + config + l.t;
    }

    private static Bitmap.Config[] b(Bitmap.Config config) {
        if (Build.VERSION.SDK_INT >= 26 && Bitmap.Config.RGBA_F16.equals(config)) {
            return c;
        }
        switch (AnonymousClass1.a[config.ordinal()]) {
            case 1:
                return b;
            case 2:
                return d;
            case 3:
                return e;
            case 4:
                return f;
            default:
                return new Bitmap.Config[]{config};
        }
    }

    /* renamed from: com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.SizeConfigStrategy$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[Bitmap.Config.values().length];

        static {
            try {
                a[Bitmap.Config.ARGB_8888.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[Bitmap.Config.RGB_565.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[Bitmap.Config.ARGB_4444.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[Bitmap.Config.ALPHA_8.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }
}
