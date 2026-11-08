package com.mbridge.msdk.foundation.same.e;

/* compiled from: CommonTask.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class a implements Runnable {
    public static long id;
    public b mListener;
    public EnumC0111a mState = EnumC0111a.READY;

    /* compiled from: CommonTask.java */
    /* renamed from: com.mbridge.msdk.foundation.same.e.a$a, reason: collision with other inner class name */
    public enum EnumC0111a {
        READY,
        RUNNING,
        PAUSE,
        CANCEL,
        FINISH
    }

    /* compiled from: CommonTask.java */
    public interface b {
        void a(EnumC0111a enumC0111a);
    }

    public abstract void cancelTask();

    public abstract void pauseTask(boolean z);

    public abstract void runTask();

    @Override // java.lang.Runnable
    public final void run() {
        try {
            if (this.mState == EnumC0111a.READY) {
                setState(EnumC0111a.RUNNING);
                runTask();
                setState(EnumC0111a.FINISH);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public a() {
        id++;
    }

    public final long getId() {
        return id;
    }

    public final void cancel() {
        if (this.mState != EnumC0111a.CANCEL) {
            setState(EnumC0111a.CANCEL);
            cancelTask();
        }
    }

    public final void setPause(boolean z) {
        if (this.mState == EnumC0111a.PAUSE || this.mState == EnumC0111a.CANCEL || this.mState == EnumC0111a.FINISH) {
            return;
        }
        if (z) {
            setState(EnumC0111a.PAUSE);
        } else {
            setState(EnumC0111a.RUNNING);
        }
        pauseTask(z);
    }

    public EnumC0111a getState() {
        return this.mState;
    }

    private void setState(EnumC0111a enumC0111a) {
        this.mState = enumC0111a;
        if (this.mListener != null) {
            this.mListener.a(enumC0111a);
        }
    }

    public void setonStateChangeListener(b bVar) {
        this.mListener = bVar;
    }
}
