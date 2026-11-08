package com.yxcorp.kuaishou.addfp.android;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Orange {
    private static final int JNI_CONTROL_COMMAND_DIR_DETECT = 1114128;
    private static final int JNI_CONTROL_COMMAND_GK_DETECT = 1114132;
    private static final int JNI_CONTROL_COMMAND_MACS_DETECT = 1179653;
    private static final int JNI_CONTROL_COMMAND_PROPS_DETECT = 1114131;
    private static final int JNI_CONTROL_COMMAND_SERIAL_DETECT = 1179649;
    private static boolean sLibLoadFail = true;
    private static volatile Orange singleton;

    private Orange() {
        loadSoLib();
    }

    private static native String getClock(Context context, byte[] bArr, int i);

    public static Orange getInstance() {
        if (singleton == null) {
            synchronized (Orange.class) {
                if (singleton == null) {
                    singleton = new Orange();
                }
            }
        }
        return singleton;
    }

    private static native byte[] getMagic(Context context, byte[] bArr, int i);

    private boolean isReject() {
        return sLibLoadFail;
    }

    private static native Object jniCommand(int i, Object obj, Object obj2, Object obj3);

    private void loadSoLib() {
        try {
            System.loadLibrary("sgcore");
            sLibLoadFail = false;
        } catch (Throwable th) {
            sLibLoadFail = true;
            th.printStackTrace();
        }
    }

    public String gKpsd() {
        try {
            return isReject() ? "" : (String) jniCommand(JNI_CONTROL_COMMAND_GK_DETECT, null, null, null);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public String gMas() {
        try {
            return isReject() ? "" : (String) jniCommand(JNI_CONTROL_COMMAND_MACS_DETECT, null, null, null);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public String gProps() {
        try {
            return isReject() ? "" : (String) jniCommand(JNI_CONTROL_COMMAND_PROPS_DETECT, null, null, null);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public String gRdi() {
        try {
            return isReject() ? "" : (String) jniCommand(JNI_CONTROL_COMMAND_DIR_DETECT, null, null, null);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public String gSer() {
        try {
            return isReject() ? "" : (String) jniCommand(JNI_CONTROL_COMMAND_SERIAL_DETECT, null, null, null);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public synchronized String getClockWrapper(Context context, byte[] bArr, int i) {
        if (isReject()) {
            return "";
        }
        return getClock(context, bArr, i);
    }

    public synchronized byte[] getMagicWrapper(Context context, byte[] bArr, int i) {
        if (isReject()) {
            return null;
        }
        return getMagic(context, bArr, i);
    }
}
