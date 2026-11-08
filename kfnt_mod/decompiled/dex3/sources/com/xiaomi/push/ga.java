package com.xiaomi.push;

import java.io.PrintStream;
import java.io.PrintWriter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ga extends Exception {
    private gj a;

    /* renamed from: a, reason: collision with other field name */
    private gk f418a;

    /* renamed from: a, reason: collision with other field name */
    private Throwable f419a;

    public ga() {
        this.a = null;
        this.f418a = null;
        this.f419a = null;
    }

    public ga(gj gjVar) {
        this.a = null;
        this.f418a = null;
        this.f419a = null;
        this.a = gjVar;
    }

    public ga(String str) {
        super(str);
        this.a = null;
        this.f418a = null;
        this.f419a = null;
    }

    public ga(String str, Throwable th) {
        super(str);
        this.a = null;
        this.f418a = null;
        this.f419a = null;
        this.f419a = th;
    }

    public ga(Throwable th) {
        this.a = null;
        this.f418a = null;
        this.f419a = null;
        this.f419a = th;
    }

    public Throwable a() {
        return this.f419a;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        String message = super.getMessage();
        return (message != null || this.f418a == null) ? (message != null || this.a == null) ? message : this.a.toString() : this.f418a.toString();
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream printStream) {
        super.printStackTrace(printStream);
        if (this.f419a != null) {
            printStream.println("Nested Exception: ");
            this.f419a.printStackTrace(printStream);
        }
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        if (this.f419a != null) {
            printWriter.println("Nested Exception: ");
            this.f419a.printStackTrace(printWriter);
        }
    }

    @Override // java.lang.Throwable
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String message = super.getMessage();
        if (message != null) {
            sb.append(message);
            sb.append(": ");
        }
        if (this.f418a != null) {
            sb.append(this.f418a);
        }
        if (this.a != null) {
            sb.append(this.a);
        }
        if (this.f419a != null) {
            sb.append("\n  -- caused by: ");
            sb.append(this.f419a);
        }
        return sb.toString();
    }
}
