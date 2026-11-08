package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* compiled from: BUGLY */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class y {
    public static boolean a = true;
    private static SimpleDateFormat b = null;
    private static int c = 5120;
    private static StringBuilder d;
    private static StringBuilder e;
    private static boolean f;
    private static a g;
    private static String h;
    private static String i;
    private static Context j;
    private static String k;
    private static boolean l;
    private static int m;
    private static final Object n = new Object();

    static /* synthetic */ boolean a(boolean z) {
        f = false;
        return false;
    }

    static {
        try {
            b = new SimpleDateFormat("MM-dd HH:mm:ss");
        } catch (Throwable unused) {
        }
    }

    private static boolean b(String str, String str2, String str3) {
        try {
            com.tencent.bugly.crashreport.common.info.a aVarB = com.tencent.bugly.crashreport.common.info.a.b();
            if (aVarB == null || aVarB.D == null) {
                return false;
            }
            return aVarB.D.appendLogToNative(str, str2, str3);
        } catch (Throwable th) {
            if (x.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    public static synchronized void a(Context context) {
        if (l || context == null || !a) {
            return;
        }
        try {
            e = new StringBuilder(0);
            d = new StringBuilder(0);
            j = context;
            com.tencent.bugly.crashreport.common.info.a aVarA = com.tencent.bugly.crashreport.common.info.a.a(context);
            h = aVarA.d;
            aVarA.getClass();
            i = "";
            k = j.getFilesDir().getPath() + "/buglylog_" + h + "_" + i + ".txt";
            m = Process.myPid();
        } catch (Throwable unused) {
        }
        l = true;
    }

    public static void a(int i2) {
        synchronized (n) {
            c = i2;
            if (i2 < 0) {
                c = 0;
            } else if (i2 > 10240) {
                c = 10240;
            }
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (th == null) {
            return;
        }
        String message = th.getMessage();
        if (message == null) {
            message = "";
        }
        a(str, str2, message + '\n' + z.b(th));
    }

    public static synchronized void a(String str, String str2, String str3) {
        if (l && a) {
            b(str, str2, str3);
            long jMyTid = Process.myTid();
            d.setLength(0);
            if (str3.length() > 30720) {
                str3 = str3.substring(str3.length() - 30720, str3.length() - 1);
            }
            Date date = new Date();
            String string = b != null ? b.format(date) : date.toString();
            StringBuilder sb = d;
            sb.append(string);
            sb.append(" ");
            sb.append(m);
            sb.append(" ");
            sb.append(jMyTid);
            sb.append(" ");
            sb.append(str);
            sb.append(" ");
            sb.append(str2);
            sb.append(": ");
            sb.append(str3);
            sb.append("\u0001\r\n");
            final String string2 = d.toString();
            synchronized (n) {
                e.append(string2);
                if (e.length() <= c) {
                    return;
                }
                if (f) {
                    return;
                }
                f = true;
                w.a().a(new Runnable() { // from class: com.tencent.bugly.proguard.y.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        synchronized (y.n) {
                            try {
                                if (y.g == null) {
                                    a unused = y.g = new a(y.k);
                                } else if (y.g.b == null || y.g.b.length() + y.e.length() > y.g.e) {
                                    y.g.a();
                                }
                                if (y.g.a) {
                                    y.g.a(y.e.toString());
                                    y.e.setLength(0);
                                } else {
                                    y.e.setLength(0);
                                    y.e.append(string2);
                                }
                                y.a(false);
                            } catch (Throwable unused2) {
                            }
                        }
                    }
                });
            }
        }
    }

    public static byte[] a() {
        if (!a) {
            return null;
        }
        synchronized (n) {
            try {
                File file = (g == null || !g.a) ? null : g.b;
                if (e.length() == 0 && file == null) {
                    return null;
                }
                return z.a(file, e.toString(), "BuglyLog.txt");
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    /* compiled from: BUGLY */
    public static class a {
        private boolean a;
        private File b;
        private String c;
        private long d;
        private long e = 30720;

        public a(String str) {
            if (str == null || str.equals("")) {
                return;
            }
            this.c = str;
            this.a = a();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean a() {
            try {
                this.b = new File(this.c);
                if (this.b.exists() && !this.b.delete()) {
                    this.a = false;
                    return false;
                }
                if (this.b.createNewFile()) {
                    return true;
                }
                this.a = false;
                return false;
            } catch (Throwable unused) {
                this.a = false;
                return true;
            }
        }

        public final boolean a(String str) throws Throwable {
            FileOutputStream fileOutputStream;
            if (!this.a) {
                return false;
            }
            FileOutputStream fileOutputStream2 = null;
            try {
                try {
                    fileOutputStream = new FileOutputStream(this.b, true);
                } catch (Throwable unused) {
                }
            } catch (Throwable th) {
                th = th;
                fileOutputStream = fileOutputStream2;
            }
            try {
                fileOutputStream.write(str.getBytes("UTF-8"));
                fileOutputStream.flush();
                fileOutputStream.close();
                this.d += r10.length;
                try {
                    fileOutputStream.close();
                } catch (IOException unused2) {
                }
                return true;
            } catch (Throwable th2) {
                th = th2;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused3) {
                    }
                }
                throw th;
            }
        }
    }
}
