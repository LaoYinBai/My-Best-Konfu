package com.kwad.sdk.glide.framesequence;

import android.graphics.Bitmap;
import androidx.annotation.Nullable;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class FrameSequence implements Serializable {
    private static final AtomicBoolean ISLOADED = new AtomicBoolean(false);
    private int mDefaultLoopCount;
    private int mFrameCount;
    private int mHeight;
    private long mNativeFrameSequence;
    private boolean mOpaque;
    private int mWidth;

    static class State implements Serializable {
        private long mNativeState;

        public State(long j) {
            this.mNativeState = j;
        }

        public void destroy() {
            if (this.mNativeState != 0) {
                FrameSequence.nativeDestroyState(this.mNativeState);
                this.mNativeState = 0L;
            }
        }

        public long getFrame(int i, Bitmap bitmap, int i2) {
            if (bitmap == null || bitmap.getConfig() != Bitmap.Config.ARGB_8888) {
                throw new IllegalArgumentException("Bitmap passed must be non-null and ARGB_8888");
            }
            if (this.mNativeState != 0) {
                return FrameSequence.nativeGetFrame(this.mNativeState, i, bitmap, i2);
            }
            throw new IllegalStateException("attempted to draw destroyed FrameSequenceState");
        }
    }

    static {
        try {
            System.loadLibrary("framesequencev2");
            ISLOADED.set(true);
        } catch (Throwable unused) {
            ISLOADED.set(false);
        }
    }

    public FrameSequence() {
    }

    private FrameSequence(long j, int i, int i2, boolean z, int i3, int i4) {
        this.mNativeFrameSequence = j;
        this.mWidth = i;
        this.mHeight = i2;
        this.mOpaque = z;
        this.mFrameCount = i3;
        this.mDefaultLoopCount = i4;
    }

    @Nullable
    public static FrameSequence decodeByteArray(byte[] bArr) {
        if (ISLOADED.get()) {
            return decodeByteArray(bArr, 0, bArr.length);
        }
        return null;
    }

    @Nullable
    public static FrameSequence decodeByteArray(byte[] bArr, int i, int i2) {
        if (!ISLOADED.get()) {
            return null;
        }
        if (bArr == null) {
            throw new IllegalArgumentException();
        }
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new IllegalArgumentException("invalid offset/length parameters");
        }
        return nativeDecodeByteArray(bArr, i, i2);
    }

    @Nullable
    public static FrameSequence decodeByteBuffer(ByteBuffer byteBuffer) {
        if (!ISLOADED.get()) {
            return null;
        }
        if (byteBuffer == null) {
            throw new IllegalArgumentException();
        }
        if (byteBuffer.isDirect()) {
            return nativeDecodeByteBuffer(byteBuffer, byteBuffer.position(), byteBuffer.remaining());
        }
        if (byteBuffer.hasArray()) {
            return decodeByteArray(byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining());
        }
        throw new IllegalArgumentException("Cannot have non-direct ByteBuffer with no byte array");
    }

    @Nullable
    public static FrameSequence decodeStream(InputStream inputStream) {
        if (!ISLOADED.get()) {
            return null;
        }
        if (inputStream != null) {
            return nativeDecodeStream(inputStream, new byte[16384]);
        }
        throw new IllegalArgumentException();
    }

    public static boolean isEnable() {
        return ISLOADED.get();
    }

    private static native long nativeCreateState(long j);

    private static native FrameSequence nativeDecodeByteArray(byte[] bArr, int i, int i2);

    private static native FrameSequence nativeDecodeByteBuffer(ByteBuffer byteBuffer, int i, int i2);

    private static native FrameSequence nativeDecodeStream(InputStream inputStream, byte[] bArr);

    private static native void nativeDestroyFrameSequence(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeDestroyState(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public static native long nativeGetFrame(long j, int i, Bitmap bitmap, int i2);

    State createState() {
        if (this.mNativeFrameSequence == 0) {
            throw new IllegalStateException("attempted to use incorrectly built FrameSequence");
        }
        long jNativeCreateState = nativeCreateState(this.mNativeFrameSequence);
        if (jNativeCreateState == 0) {
            return null;
        }
        return new State(jNativeCreateState);
    }

    public void destroy() {
        if (this.mNativeFrameSequence != 0) {
            nativeDestroyFrameSequence(this.mNativeFrameSequence);
        }
    }

    public int getDefaultLoopCount() {
        return this.mDefaultLoopCount;
    }

    public int getFrameCount() {
        return this.mFrameCount;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public boolean isOpaque() {
        return this.mOpaque;
    }
}
