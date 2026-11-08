package org.android.spdy;

import java.util.Random;
import java.util.TreeSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public class SpdyBytePool {
    private TreeSet<SpdyByteArray> pool;
    private static Object lock = new Object();
    private static volatile SpdyBytePool gInstance = null;
    private static Random rand = new Random();
    private SpdyByteArray std = new SpdyByteArray();
    private long reused = 0;

    private SpdyBytePool() {
        this.pool = null;
        this.pool = new TreeSet<>();
    }

    public static SpdyBytePool getInstance() {
        if (gInstance == null) {
            synchronized (lock) {
                if (gInstance == null) {
                    gInstance = new SpdyBytePool();
                }
            }
        }
        return gInstance;
    }

    SpdyByteArray getSpdyByteArray(int i) {
        SpdyByteArray spdyByteArrayCeiling;
        synchronized (lock) {
            this.std.length = i;
            spdyByteArrayCeiling = this.pool.ceiling(this.std);
            if (spdyByteArrayCeiling == null) {
                spdyByteArrayCeiling = new SpdyByteArray(i);
            } else {
                this.pool.remove(spdyByteArrayCeiling);
                this.reused += i;
            }
        }
        spduLog.Logi("libeasy", "getSpdyByteArray: " + spdyByteArrayCeiling);
        spduLog.Logi("libeasy", "reused: " + this.reused);
        return spdyByteArrayCeiling;
    }

    void recycle(SpdyByteArray spdyByteArray) {
        synchronized (lock) {
            this.pool.add(spdyByteArray);
            while (this.pool.size() > 100) {
                if (rand.nextBoolean()) {
                    this.pool.pollFirst();
                } else {
                    this.pool.pollLast();
                }
            }
        }
    }
}
