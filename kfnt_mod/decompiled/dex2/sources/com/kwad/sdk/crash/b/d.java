package com.kwad.sdk.crash.b;

import android.content.Context;
import com.kwad.sdk.crash.model.message.JavaExceptionMessage;
import java.lang.Thread;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d implements Thread.UncaughtExceptionHandler {
    private volatile boolean a = false;
    private Thread.UncaughtExceptionHandler b = Thread.getDefaultUncaughtExceptionHandler();
    private Context c;

    public d(Context context) {
        this.c = context;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        try {
        } catch (Throwable unused) {
        }
        if (this.a) {
            com.kwad.sdk.core.d.a.a("ExceptionCollector", "SDK捕获 uncaughtException 正在处理 ex=" + th.getMessage());
            this.b.uncaughtException(thread, th);
            return;
        }
        this.a = true;
        com.kwad.sdk.core.d.a.a("ExceptionCollector", "thread=" + thread + " ex=" + th.getMessage());
        if (th != null && com.kwad.sdk.crash.a.a.a(th)) {
            c.d().a(th, new JavaExceptionMessage(), this.c);
        }
        this.b.uncaughtException(thread, th);
    }
}
