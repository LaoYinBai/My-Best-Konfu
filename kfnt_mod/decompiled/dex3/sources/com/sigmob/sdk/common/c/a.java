package com.sigmob.sdk.common.c;

import android.os.Process;
import android.text.TextUtils;
import com.sigmob.sdk.common.mta.PointCategory;
import com.sigmob.sdk.common.mta.PointEntityCrash;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a implements Thread.UncaughtExceptionHandler {
    private static a b;
    private Thread.UncaughtExceptionHandler a;

    public static a a() {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a();
                }
            }
        }
        return b;
    }

    private boolean a(final Throwable th) throws InterruptedException {
        if (th == null) {
            return false;
        }
        new Thread(new Runnable() { // from class: com.sigmob.sdk.common.c.a.1
            @Override // java.lang.Runnable
            public void run() {
                a.this.b(th);
            }
        }).start();
        try {
            Thread.sleep(1000L);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Throwable th) {
        try {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            while (true) {
                th = th.getCause();
                if (th == null) {
                    break;
                } else {
                    th.printStackTrace(printWriter);
                }
            }
            printWriter.close();
            String string = stringWriter.toString();
            if (TextUtils.isEmpty(string)) {
                return;
            }
            if (string.contains("com.sigmob") || string.contains("com.wind")) {
                PointEntityCrash.WindCrash(PointCategory.CRASH, string).sendServe();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    public void b() {
        this.a = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        try {
            a(th);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
        if (this.a != null) {
            this.a.uncaughtException(thread, th);
        } else {
            Process.killProcess(Process.myPid());
        }
    }
}
