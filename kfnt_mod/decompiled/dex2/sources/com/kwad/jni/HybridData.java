package com.kwad.jni;

import androidx.annotation.Keep;
import com.kwad.jni.DestructorThread;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class HybridData {

    @Keep
    private Destructor mDestructor = new Destructor(this);

    @Keep
    public static class Destructor extends DestructorThread.Destructor {

        @Keep
        private long mNativePointer;

        Destructor(Object obj) {
            super(obj);
        }

        static native void deleteNative(long j);

        @Override // com.kwad.jni.DestructorThread.Destructor
        protected final void destruct() {
            deleteNative(this.mNativePointer);
            this.mNativePointer = 0L;
        }
    }

    static {
        try {
            System.loadLibrary("kwad-fb");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isValid() {
        return this.mDestructor.mNativePointer != 0;
    }

    @Keep
    public synchronized void resetNative() {
        this.mDestructor.destruct();
    }
}
