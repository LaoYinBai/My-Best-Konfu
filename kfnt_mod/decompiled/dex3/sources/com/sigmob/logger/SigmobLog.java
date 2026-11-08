package com.sigmob.logger;

import android.text.TextUtils;
import android.util.Log;
import com.sigmob.sdk.common.Constants;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SigmobLog {
    private static final Logger a = Logger.getLogger("com.sigmob");
    private static final SigmobLogHandler b = new SigmobLogHandler();

    private static final class SigmobLogHandler extends Handler {
        private static final Map<Level, Integer> a = new HashMap(7);

        static {
            a.put(Level.FINEST, 2);
            a.put(Level.FINER, 2);
            a.put(Level.FINE, 2);
            a.put(Level.CONFIG, 3);
            a.put(Level.INFO, 3);
            a.put(Level.WARNING, 5);
            a.put(Level.SEVERE, 6);
        }

        private SigmobLogHandler() {
        }

        @Override // java.util.logging.Handler
        public void close() {
        }

        @Override // java.util.logging.Handler
        public void flush() {
        }

        @Override // java.util.logging.Handler
        public void publish(LogRecord logRecord) {
            if (isLoggable(logRecord)) {
                int iIntValue = a.containsKey(logRecord.getLevel()) ? a.get(logRecord.getLevel()).intValue() : 2;
                String str = logRecord.getMessage() + "\n";
                Throwable thrown = logRecord.getThrown();
                if (thrown != null) {
                    str = str + Log.getStackTraceString(thrown);
                }
                Log.println(iIntValue, Constants.SDK_FOLDER, str);
            }
        }
    }

    static {
        a.setUseParentHandlers(false);
        a.setLevel(Level.ALL);
        b.setLevel(Level.FINE);
        LogManager.getLogManager().addLogger(a);
        a(a, b);
    }

    private SigmobLog() {
    }

    private static void a(String str, Throwable th) {
        a.log(Level.FINEST, str, th);
    }

    private static void a(Logger logger, Handler handler) throws SecurityException {
        for (Handler handler2 : logger.getHandlers()) {
            if (handler2.equals(handler)) {
                return;
            }
        }
        logger.addHandler(handler);
    }

    public static void addHandler(Handler handler) throws SecurityException {
        a(a, handler);
    }

    private static void b(String str, Throwable th) {
        a.log(Level.INFO, str, th);
    }

    public static void c(String str) {
        a(str, (Throwable) null);
    }

    public static void d(String str) {
        d(str, null);
    }

    public static void d(String str, Throwable th) {
        a.log(Level.CONFIG, str, th);
    }

    public static void dd(String str, String str2) {
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0) {
            return;
        }
        if (str2.length() > 3072) {
            while (str2.length() > 3072) {
                String strSubstring = str2.substring(0, 3072);
                str2 = str2.replace(strSubstring, "");
                Log.d(str, strSubstring);
            }
        }
        Log.d(str, str2);
    }

    public static void e(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        e(str, null);
    }

    public static void e(String str, Throwable th) {
        a.log(Level.SEVERE, str, th);
    }

    public static void i(String str) {
        b(str, null);
    }

    public static void setSdkHandlerLevel(Level level) {
        b.setLevel(level);
    }

    public static void v(String str) {
        v(str, null);
    }

    public static void v(String str, Throwable th) {
        a.log(Level.FINE, str, th);
    }

    public static void w(String str) {
        w(str, null);
    }

    public static void w(String str, Throwable th) {
        a.log(Level.WARNING, str, th);
    }
}
