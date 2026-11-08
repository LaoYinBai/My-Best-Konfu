package com.sigmob.sdk.common.utils;

import com.sigmob.logger.SigmobLog;
import java.util.IllegalFormatException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class m {

    public static final class a {
        private static volatile boolean a = true;

        public static String a() {
            try {
                StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];
                return stackTraceElement.getFileName() + ": Line " + stackTraceElement.getMethodName();
            } catch (Throwable unused) {
                return "";
            }
        }

        public static boolean a(Object obj) {
            return m.b(obj, a, (String) null, "");
        }

        public static boolean a(Object obj, String str) {
            return m.b(obj, a, str, "");
        }

        public static boolean a(boolean z) {
            return m.b(z, a, "Illegal argument", "");
        }

        public static boolean a(boolean z, String str) {
            return m.b(z, a, str, "");
        }
    }

    private static String a(String str, Object... objArr) {
        String strValueOf = String.valueOf(str);
        try {
            return String.format(strValueOf, objArr);
        } catch (IllegalFormatException e) {
            SigmobLog.e("Sigmob preconditions had a format exception: " + e.getMessage());
            return strValueOf;
        }
    }

    public static void a(Object obj) {
        b(obj, true, "Object can not be null.", "");
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
        SigmobLog.e(strA);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(boolean z, boolean z2, String str, Object... objArr) {
        if (z) {
            return true;
        }
        String strA = a(str, objArr);
        if (z2) {
            throw new IllegalArgumentException(strA);
        }
        SigmobLog.e(strA);
        return false;
    }
}
