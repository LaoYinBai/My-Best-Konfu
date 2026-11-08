package com.mbridge.msdk.widget.custom.baseview;

/* compiled from: StateManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class e {
    private boolean a;
    private int b;

    public e(MBCircularProgressButton mBCircularProgressButton) {
        this.a = mBCircularProgressButton.isEnabled();
        this.b = mBCircularProgressButton.getProgress();
    }

    public final void a(MBCircularProgressButton mBCircularProgressButton) {
        this.b = mBCircularProgressButton.getProgress();
    }

    public final void b(MBCircularProgressButton mBCircularProgressButton) {
        if (mBCircularProgressButton.getProgress() != this.b) {
            mBCircularProgressButton.setProgress(mBCircularProgressButton.getProgress());
        } else if (mBCircularProgressButton.isEnabled() != this.a) {
            mBCircularProgressButton.setEnabled(mBCircularProgressButton.isEnabled());
        }
    }
}
