package com.unity3d.player;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Camera2Wrapper implements d {
    private Context a;
    private a b = null;
    private final int c = 100;

    public Camera2Wrapper(Context context) {
        this.a = context;
        initCamera2Jni();
    }

    private static int a(float f) {
        return (int) Math.min(Math.max((f * 2000.0f) - 1000.0f, -900.0f), 900.0f);
    }

    private final native void initCamera2Jni();

    private final native void nativeFrameReady(Object obj, Object obj2, Object obj3, int i, int i2, int i3);

    private final native void nativeSurfaceTextureReady(Object obj);

    public final void a() {
        closeCamera2();
    }

    @Override // com.unity3d.player.d
    public final void a(Object obj) {
        nativeSurfaceTextureReady(obj);
    }

    @Override // com.unity3d.player.d
    public final void a(Object obj, Object obj2, Object obj3, int i, int i2, int i3) {
        nativeFrameReady(obj, obj2, obj3, i, i2, i3);
    }

    protected void closeCamera2() {
        if (this.b != null) {
            this.b.b();
        }
        this.b = null;
    }

    protected int getCamera2Count() {
        if (j.b) {
            return a.a(this.a);
        }
        return 0;
    }

    protected int[] getCamera2Resolutions(int i) {
        if (j.b) {
            return a.d(this.a, i);
        }
        return null;
    }

    protected int getCamera2SensorOrientation(int i) {
        if (j.b) {
            return a.a(this.a, i);
        }
        return 0;
    }

    protected Object getCameraFocusArea(float f, float f2) {
        int iA = a(f);
        int iA2 = a(1.0f - f2);
        return new Camera.Area(new Rect(iA - 100, iA2 - 100, iA + 100, iA2 + 100), 1000);
    }

    protected Rect getFrameSizeCamera2() {
        return this.b != null ? this.b.a() : new Rect();
    }

    protected boolean initializeCamera2(int i, int i2, int i3, int i4, int i5) {
        if (!j.b || this.b != null || UnityPlayer.currentActivity == null) {
            return false;
        }
        this.b = new a(this);
        return this.b.a(this.a, i, i2, i3, i4, i5);
    }

    protected boolean isCamera2AutoFocusPointSupported(int i) {
        if (j.b) {
            return a.c(this.a, i);
        }
        return false;
    }

    protected boolean isCamera2FrontFacing(int i) {
        if (j.b) {
            return a.b(this.a, i);
        }
        return false;
    }

    protected void pauseCamera2() {
        if (this.b != null) {
            this.b.d();
        }
    }

    protected boolean setAutoFocusPoint(float f, float f2) {
        if (!j.b || this.b == null) {
            return false;
        }
        return this.b.a(f, f2);
    }

    protected void startCamera2() throws CameraAccessException {
        if (this.b != null) {
            this.b.c();
        }
    }

    protected void stopCamera2() {
        if (this.b != null) {
            this.b.e();
        }
    }
}
