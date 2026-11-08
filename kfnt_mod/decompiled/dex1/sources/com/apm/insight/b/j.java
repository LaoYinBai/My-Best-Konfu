package com.apm.insight.b;

import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.util.Printer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class j {

    /* renamed from: a, reason: collision with root package name */
    private static int f2810a = 5;

    /* renamed from: b, reason: collision with root package name */
    private static b f2811b;

    /* renamed from: c, reason: collision with root package name */
    private static a f2812c;
    private static boolean d;
    private static Printer e;

    public interface a {
        void a(long j);
    }

    static class b implements Printer {

        /* renamed from: a, reason: collision with root package name */
        List<Printer> f2813a = new ArrayList();

        /* renamed from: b, reason: collision with root package name */
        List<Printer> f2814b = new ArrayList();

        /* renamed from: c, reason: collision with root package name */
        List<Printer> f2815c = new ArrayList();
        boolean d = false;
        boolean e = false;

        b() {
        }

        @Override // android.util.Printer
        public void println(String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            long jCurrentTimeMillis = j.f2812c != null ? System.currentTimeMillis() : 0L;
            if (str.charAt(0) == '>' && this.e) {
                for (Printer printer : this.f2815c) {
                    if (!this.f2813a.contains(printer)) {
                        this.f2813a.add(printer);
                    }
                }
                this.f2815c.clear();
                this.e = false;
            }
            if (this.f2813a.size() > j.f2810a) {
                Log.e("LooperPrinterUtils", "wrapper contains too many printer,please check if the useless printer have been removed");
            }
            for (Printer printer2 : this.f2813a) {
                if (printer2 != null) {
                    printer2.println(str);
                }
            }
            if (str.charAt(0) == '<' && this.d) {
                for (Printer printer3 : this.f2814b) {
                    this.f2813a.remove(printer3);
                    this.f2815c.remove(printer3);
                }
                this.f2814b.clear();
                this.d = false;
            }
            if (j.f2812c == null || jCurrentTimeMillis <= 0) {
                return;
            }
            j.f2812c.a(System.currentTimeMillis() - jCurrentTimeMillis);
        }
    }

    public static void a() {
        if (d) {
            return;
        }
        d = true;
        f2811b = new b();
        e = d();
        if (e != null) {
            f2811b.f2813a.add(e);
        }
        Looper.getMainLooper().setMessageLogging(f2811b);
    }

    public static void a(Printer printer) {
        if (printer == null || f2811b.f2815c.contains(printer)) {
            return;
        }
        f2811b.f2815c.add(printer);
        f2811b.e = true;
    }

    private static Printer d() throws NoSuchFieldException {
        try {
            Field declaredField = Class.forName("android.os.Looper").getDeclaredField("mLogging");
            declaredField.setAccessible(true);
            return (Printer) declaredField.get(Looper.getMainLooper());
        } catch (Exception unused) {
            return null;
        }
    }
}
