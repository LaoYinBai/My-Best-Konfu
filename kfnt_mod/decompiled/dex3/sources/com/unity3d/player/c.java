package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class c {
    protected f b;
    protected String e;
    protected q a = null;
    protected Context c = null;
    protected String d = null;

    c(String str, f fVar) {
        this.b = null;
        this.e = "";
        this.e = str;
        this.b = fVar;
    }

    protected void reportError(String str) {
        if (this.b != null) {
            this.b.reportError(this.e + " Error [" + this.d + "]", str);
            return;
        }
        g.Log(6, this.e + " Error [" + this.d + "]: " + str);
    }

    protected void runOnUiThread(Runnable runnable) {
        if (this.c instanceof Activity) {
            ((Activity) this.c).runOnUiThread(runnable);
            return;
        }
        g.Log(5, "Not running " + this.e + " from an Activity; Ignoring execution request...");
    }

    protected boolean runOnUiThreadWithSync(final Runnable runnable) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            runnable.run();
            return true;
        }
        final Semaphore semaphore = new Semaphore(0);
        runOnUiThread(new Runnable() { // from class: com.unity3d.player.c.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    try {
                        runnable.run();
                    } catch (Exception e) {
                        c.this.reportError("Exception unloading Google VR on UI Thread. " + e.getLocalizedMessage());
                    }
                } finally {
                    semaphore.release();
                }
            }
        });
        try {
            if (semaphore.tryAcquire(4L, TimeUnit.SECONDS)) {
                return true;
            }
            reportError("Timeout waiting for vr state change!");
            return false;
        } catch (InterruptedException e) {
            reportError("Interrupted while trying to acquire sync lock. " + e.getLocalizedMessage());
            return false;
        }
    }
}
