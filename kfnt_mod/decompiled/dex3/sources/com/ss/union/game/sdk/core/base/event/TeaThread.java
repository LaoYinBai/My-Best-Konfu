package com.ss.union.game.sdk.core.base.event;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TeaThread extends HandlerThread {
    private static final String a = "TeaThread";
    private static final int b = 1000;
    private static volatile TeaThread c;
    private final Object d;
    private final LinkedList<Runnable> e;
    private Handler f;
    private volatile boolean g;

    protected TeaThread() {
        super(a);
        this.d = new Object();
        this.e = new LinkedList<>();
        this.g = false;
    }

    protected TeaThread(String str) {
        super(str);
        this.d = new Object();
        this.e = new LinkedList<>();
        this.g = false;
    }

    public static TeaThread getInst() {
        if (c == null) {
            synchronized (TeaThread.class) {
                if (c == null) {
                    c = new TeaThread();
                    c.start();
                }
            }
        }
        return c;
    }

    public static TeaThread createNewThread(String str) {
        return new TeaThread(str);
    }

    @Override // android.os.HandlerThread
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        synchronized (this.d) {
            this.g = true;
            ArrayList arrayList = new ArrayList(this.e);
            this.e.clear();
            if (!arrayList.isEmpty()) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    post((Runnable) it.next());
                }
            }
        }
    }

    public void ensureTeaThreadLite(Runnable runnable) {
        ensureTeaThreadLiteDelay(runnable, 0L);
    }

    public void ensureTeaThreadLiteDelay(Runnable runnable, long j) {
        if (runnable == null) {
            return;
        }
        if (this.g) {
            postDelay(runnable, j);
            return;
        }
        synchronized (this.d) {
            if (this.g) {
                postDelay(runnable, j);
            } else {
                if (this.e.size() > 1000) {
                    this.e.poll();
                }
                this.e.add(runnable);
            }
        }
    }

    public void post(Runnable runnable) {
        if (runnable != null) {
            getTeaHandler().post(runnable);
        }
    }

    public void repost(Runnable runnable) {
        if (runnable != null) {
            removeCallbacks(runnable);
            post(runnable);
        }
    }

    public void repost(Runnable runnable, long j) {
        if (runnable != null) {
            removeCallbacks(runnable);
            postDelay(runnable, j);
        }
    }

    public void postDelay(Runnable runnable, long j) {
        if (runnable != null) {
            getTeaHandler().postDelayed(runnable, j);
        }
    }

    public void removeCallbacks(Runnable runnable) {
        getTeaHandler().removeCallbacks(runnable);
    }

    public void ensureTeaThread(Runnable runnable) {
        ensureTeaThreadLite(runnable);
    }

    public Handler getTeaHandler() {
        if (this.f == null) {
            synchronized (this) {
                if (this.f == null) {
                    this.f = new Handler(getLooper());
                }
            }
        }
        return this.f;
    }

    public Handler createTeaHandler() {
        return new Handler(getLooper());
    }
}
