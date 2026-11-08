package com.bytedance.msdk.adapter.util;

import android.os.Looper;
import java.util.IllegalFormatException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class Preconditions {
    public static final String EMPTY_ARGUMENTS = "";

    public static final class NoThrow {

        /* renamed from: a, reason: collision with root package name */
        private static volatile boolean f4242a = false;

        public static boolean checkArgument(boolean z) {
            return Preconditions.c(z, f4242a, "Illegal argument", "");
        }

        public static boolean checkNotNull(Object obj) {
            return Preconditions.b(obj, f4242a, "Object can not be null.", "");
        }

        public static boolean checkState(boolean z) {
            return Preconditions.d(z, f4242a, "Illegal state.", "");
        }

        public static boolean checkUiThread() {
            return Preconditions.b(f4242a, "This method must be called from the UI thread.", "");
        }

        public static void setStrictMode(boolean z) {
            f4242a = z;
        }

        public static boolean checkArgument(boolean z, String str) {
            return Preconditions.c(z, f4242a, str, "");
        }

        public static boolean checkNotNull(Object obj, String str) {
            return Preconditions.b(obj, f4242a, str, "");
        }

        public static boolean checkState(boolean z, String str) {
            return Preconditions.d(z, f4242a, str, "");
        }

        public static boolean checkUiThread(String str) {
            return Preconditions.b(f4242a, str, "");
        }

        public static boolean checkArgument(boolean z, String str, Object... objArr) {
            return Preconditions.c(z, f4242a, str, objArr);
        }

        public static boolean checkNotNull(Object obj, String str, Object... objArr) {
            return Preconditions.b(obj, f4242a, str, objArr);
        }

        public static boolean checkState(boolean z, String str, Object... objArr) {
            return Preconditions.d(z, f4242a, str, objArr);
        }

        public static boolean checkUiThread(String str, Object... objArr) {
            return Preconditions.b(false, str, objArr);
        }
    }

    private Preconditions() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean c(boolean z, boolean z2, String str, Object... objArr) {
        if (z) {
            return true;
        }
        String strA = a(str, objArr);
        if (z2) {
            throw new IllegalArgumentException(strA);
        }
        Logger.e("TTMediationSDK_ADAPTER", strA);
        return false;
    }

    public static void checkArgument(boolean z) {
        c(z, true, "Illegal argument.", "");
    }

    public static void checkNotNull(Object obj) {
        b(obj, true, "Object can not be null.", "");
    }

    public static void checkState(boolean z) {
        d(z, true, "Illegal state.", "");
    }

    public static void checkUiThread() {
        b(true, "This method must be called from the UI thread.", "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean d(boolean z, boolean z2, String str, Object... objArr) {
        if (z) {
            return true;
        }
        String strA = a(str, objArr);
        if (z2) {
            throw new IllegalStateException(strA);
        }
        Logger.e("TTMediationSDK_ADAPTER", strA);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(Object obj, boolean z, String str, Object... objArr) {
        if (obj != null) {
            return true;
        }
        String strA = a(str, objArr);
        if (z) {
            throw new NullPointerException(strA);
        }
        Logger.e("TTMediationSDK_ADAPTER", strA);
        return false;
    }

    public static void checkArgument(boolean z, String str) {
        c(z, true, str, "");
    }

    public static void checkNotNull(Object obj, String str) {
        b(obj, true, str, "");
    }

    public static void checkState(boolean z, String str) {
        d(z, true, str, "");
    }

    public static void checkUiThread(String str) {
        b(true, str, "");
    }

    public static void checkArgument(boolean z, String str, Object... objArr) {
        c(z, true, str, objArr);
    }

    public static void checkNotNull(Object obj, String str, Object... objArr) {
        b(obj, true, str, objArr);
    }

    public static void checkState(boolean z, String str, Object... objArr) {
        d(z, true, str, objArr);
    }

    public static void checkUiThread(String str, Object... objArr) {
        b(true, str, objArr);
    }

    private static String a(String str, Object... objArr) {
        String strValueOf = String.valueOf(str);
        try {
            return String.format(strValueOf, objArr);
        } catch (IllegalFormatException e) {
            Logger.e("TTMediationSDK_ADAPTER", "MoPub preconditions had a format exception: " + e.getMessage());
            return strValueOf;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(boolean z, String str, Object... objArr) {
        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            return true;
        }
        String strA = a(str, objArr);
        if (!z) {
            Logger.e("TTMediationSDK_ADAPTER", strA);
            return false;
        }
        throw new IllegalStateException(strA);
    }
}
