package com.ss.union.game.sdk.core.glide.load.engine.prefill;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class b {
    private final Map<PreFillType, Integer> a;
    private final List<PreFillType> b;
    private int c;
    private int d;

    public b(Map<PreFillType, Integer> map) {
        this.a = map;
        this.b = new ArrayList(map.keySet());
        Iterator<Integer> it = map.values().iterator();
        while (it.hasNext()) {
            this.c += it.next().intValue();
        }
    }

    public PreFillType a() {
        PreFillType preFillType = this.b.get(this.d);
        Integer num = this.a.get(preFillType);
        if (num.intValue() == 1) {
            this.a.remove(preFillType);
            this.b.remove(this.d);
        } else {
            this.a.put(preFillType, Integer.valueOf(num.intValue() - 1));
        }
        this.c--;
        this.d = this.b.isEmpty() ? 0 : (this.d + 1) % this.b.size();
        return preFillType;
    }

    public int b() {
        return this.c;
    }

    public boolean c() {
        return this.c == 0;
    }
}
