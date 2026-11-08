package com.ss.union.game.sdk.common.util.logger;

import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import com.ss.union.game.sdk.common.util.DateUtils;
import com.ss.union.game.sdk.common.util.FileUtils;
import com.ss.union.game.sdk.common.util.logger.b;
import com.umeng.message.proguard.l;
import com.xiaomi.mipush.sdk.Constants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class PrettyFormatDiskStrategy implements d {
    private static final int a = 4000;
    private static final int b = 5;
    private static final char c = 9484;
    private static final char d = 9492;
    private static final char e = 9500;
    private static final char f = 9474;
    private static final String g = "────────────────────────────────────────────────────────";
    private static final String h = "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄";
    private static final String i = "┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────";
    private static final String j = "└────────────────────────────────────────────────────────────────────────────────────────────────────────────────";
    private static final String k = "├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄";
    private final int l;
    private final int m;
    private final a n;
    private final f o;
    private final f p;
    private final StringBuilder q;
    private final String r;

    enum a {
        ALL,
        ONLY_MAIN_THREAD,
        EXPECT_MAIN_THREAD,
        NONE
    }

    private PrettyFormatDiskStrategy(Builder builder) {
        this.l = builder.a;
        this.m = builder.b;
        this.n = builder.c;
        this.o = builder.d;
        this.p = builder.e;
        this.q = new StringBuilder();
        this.r = builder.f;
    }

    public static Builder a() {
        return new Builder();
    }

    @Override // com.ss.union.game.sdk.common.util.logger.d
    public void a(int i2, String str, String str2) {
        if (str2 == null) {
            str2 = "";
        }
        if (b() && this.q.length() > 0) {
            this.q.delete(0, this.q.length());
        }
        String strB = b(str);
        a(i2, strB);
        a(i2, strB, this.l);
        byte[] bytes = str2.getBytes();
        int length = bytes.length;
        if (length <= a) {
            if (this.l > 0) {
                c(i2, strB);
            }
            b(i2, strB, str2);
            b(i2, strB);
        } else {
            if (this.l > 0) {
                c(i2, strB);
            }
            for (int i3 = 0; i3 < length; i3 += a) {
                b(i2, strB, new String(bytes, i3, Math.min(length - i3, a)));
            }
            b(i2, strB);
        }
        if (b()) {
            this.p.a(0, strB, this.q.toString());
        }
    }

    private void a(int i2, String str) {
        c(i2, str, i);
    }

    private void a(int i2, String str, int i3) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        boolean z = false;
        if (a.ALL == this.n || (a.ONLY_MAIN_THREAD != this.n ? !(a.EXPECT_MAIN_THREAD != this.n || Looper.getMainLooper() == Looper.myLooper()) : Looper.getMainLooper() == Looper.myLooper())) {
            z = true;
        }
        if (z) {
            c(i2, str, "│ Thread: " + Thread.currentThread().getName());
            c(i2, str);
        }
        if (i3 <= 0) {
            return;
        }
        String str2 = "";
        int iA = a(stackTrace) + this.m;
        if (i3 + iA > stackTrace.length) {
            i3 = (stackTrace.length - iA) - 1;
        }
        while (i3 > 0) {
            int i4 = i3 + iA;
            if (i4 < stackTrace.length) {
                str2 = str2 + "   ";
                c(i2, str, f + ' ' + str2 + a(stackTrace[i4].getClassName()) + "." + stackTrace[i4].getMethodName() + "  (" + stackTrace[i4].getFileName() + Constants.COLON_SEPARATOR + stackTrace[i4].getLineNumber() + l.t);
            }
            i3--;
        }
    }

    private void b(int i2, String str) {
        c(i2, str, j);
    }

    private void c(int i2, String str) {
        c(i2, str, k);
    }

    private void b(int i2, String str, String str2) {
        if (str2 == null) {
            str2 = "";
        }
        for (String str3 : str2.split(System.getProperty("line.separator"))) {
            c(i2, str, "│ " + str3);
        }
    }

    private void c(int i2, String str, String str2) {
        if (str2 == null || str2.length() <= 0) {
            return;
        }
        this.o.a(i2, str, str2);
        if (b()) {
            this.q.append("\n");
            this.q.append(DateUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss.SSS"));
            this.q.append(" ");
            this.q.append(j.a(i2));
            this.q.append("/");
            this.q.append(str);
            this.q.append(": ");
            this.q.append(str2);
        }
    }

    private String a(String str) {
        return (str == null || str.length() <= 0) ? "" : str.substring(str.lastIndexOf(".") + 1);
    }

    private int a(StackTraceElement[] stackTraceElementArr) {
        if (stackTraceElementArr != null) {
            for (int i2 = 5; i2 < stackTraceElementArr.length; i2++) {
                String className = stackTraceElementArr[i2].getClassName();
                if (!className.equals(i.class.getName()) && !className.equals(h.class.getName())) {
                    return i2 - 1;
                }
            }
        }
        return -1;
    }

    private String b(String str) {
        if (str != null && str.length() > 0 && !j.a(this.r, str)) {
            if (TextUtils.isEmpty(this.r)) {
                return str;
            }
            return this.r + Constants.ACCEPT_TIME_SEPARATOR_SERVER + str;
        }
        return this.r;
    }

    private boolean b() {
        return this.p != null;
    }

    public static class Builder {
        private static final int g = 512000;
        int a;
        int b;
        a c;
        f d;
        f e;
        String f;

        private Builder() {
            this.a = 3;
            this.b = 0;
            this.c = a.NONE;
            this.f = "LG_LOGGER";
        }

        public Builder methodCount(int i) {
            this.a = i;
            return this;
        }

        public Builder methodOffset(int i) {
            this.b = i;
            return this;
        }

        public Builder showThreadInfo(a aVar) {
            this.c = aVar;
            return this;
        }

        public Builder logStrategy(f fVar) {
            this.d = fVar;
            return this;
        }

        public Builder openDisk(boolean z) {
            if (z) {
                if (this.e == null) {
                    String absolutePath = FileUtils.getOutDirPrivate("logger").getAbsolutePath();
                    HandlerThread handlerThread = new HandlerThread("AndroidFileLogger");
                    handlerThread.start();
                    this.e = new b(new b.a(handlerThread.getLooper(), absolutePath, g));
                }
            } else {
                this.e = null;
            }
            return this;
        }

        public Builder tag(String str) {
            this.f = str;
            return this;
        }

        public PrettyFormatDiskStrategy build() {
            if (this.d == null) {
                this.d = new g();
            }
            return new PrettyFormatDiskStrategy(this);
        }
    }
}
