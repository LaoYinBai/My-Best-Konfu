package com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle;

import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.e;
import com.ss.union.game.sdk.core.glide.util.Util;
import java.util.Queue;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class BaseKeyPool<T extends e> {
    private static final int a = 20;
    private final Queue<T> b = Util.createQueue(20);

    abstract T b();

    T c() {
        T tPoll = this.b.poll();
        return tPoll == null ? (T) b() : tPoll;
    }

    public void offer(T t) {
        if (this.b.size() < 20) {
            this.b.offer(t);
        }
    }
}
