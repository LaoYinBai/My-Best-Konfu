package com.kwad.yoga;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class YogaNodeJNIFinalizer extends YogaNodeJNIBase {
    public YogaNodeJNIFinalizer() {
    }

    public YogaNodeJNIFinalizer(YogaConfig yogaConfig) {
        super(yogaConfig);
    }

    protected void finalize() throws Throwable {
        try {
            freeNatives();
        } finally {
            super.finalize();
        }
    }

    public void freeNatives() {
        if (this.mNativePointer != 0) {
            long j = this.mNativePointer;
            this.mNativePointer = 0L;
            YogaNative.jni_YGNodeFree(j);
        }
    }
}
