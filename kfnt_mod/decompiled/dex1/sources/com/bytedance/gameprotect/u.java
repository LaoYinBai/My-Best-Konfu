package com.bytedance.gameprotect;

import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class u {

    /* renamed from: a, reason: collision with root package name */
    private final ArrayList f3890a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f3891b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f3892c;
    private int d;
    private long e;
    private int f;
    private int g;
    private int h;
    private long i;
    private boolean j;
    private int k;
    private long l;
    private int m;
    private boolean n;

    private class b {

        /* renamed from: a, reason: collision with root package name */
        private int f3893a;

        /* renamed from: b, reason: collision with root package name */
        private int f3894b;

        /* renamed from: c, reason: collision with root package name */
        private int f3895c;
        private int d;
        private int e;
        private long f;
        private float g;

        public b(u uVar, int i, int i2, int i3, int i4, long j, float f, int i5) {
            this.f3893a = i;
            this.f3894b = i2;
            this.f3895c = i3;
            this.d = i4;
            this.f = j;
            this.g = f;
            this.e = i5;
        }

        public int a() {
            return this.f3893a;
        }

        public int b() {
            return this.f3894b;
        }

        public long c() {
            return this.f;
        }

        public int d() {
            return this.e;
        }

        public float e() {
            return this.g;
        }

        public int f() {
            return this.f3895c;
        }

        public int g() {
            return this.d;
        }
    }

    private class c implements View.OnTouchListener {

        /* renamed from: a, reason: collision with root package name */
        private View.OnTouchListener f3896a;

        public c(View.OnTouchListener onTouchListener) {
            this.f3896a = onTouchListener;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (u.this.b(motionEvent)) {
                u.this.a(motionEvent);
            }
            View.OnTouchListener onTouchListener = this.f3896a;
            if (onTouchListener != null) {
                return onTouchListener.onTouch(view, motionEvent);
            }
            return false;
        }
    }

    private enum d {
        INSTANCE;


        /* renamed from: a, reason: collision with root package name */
        private final u f3900a = new u();

        d() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public u a() {
            return this.f3900a;
        }
    }

    private u() {
        this.f3890a = new ArrayList(100);
        this.f3891b = false;
        this.f3892c = false;
        this.d = 8;
        this.e = 1800000L;
        this.f = 100;
        this.g = 0;
        this.h = 0;
        this.i = 0L;
        this.j = true;
        this.k = 1;
        this.l = 0L;
        this.m = 0;
        this.n = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(MotionEvent motionEvent) {
        int i = this.m + 1;
        this.m = i;
        if (i == 1) {
            this.i = System.currentTimeMillis();
        }
        synchronized (this.f3890a) {
            this.f3890a.add(new b(this, motionEvent.getAction(), motionEvent.getDeviceId(), (int) motionEvent.getX(), (int) motionEvent.getY(), motionEvent.getEventTime(), motionEvent.getPressure(), motionEvent.getHistorySize()));
        }
        if (this.m >= this.f) {
            c(motionEvent);
        }
    }

    private void a(View view, String str, String str2, String str3) {
        try {
            Method declaredMethod = View.class.getDeclaredMethod(str2, new Class[0]);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(view, new Object[0]);
            Field declaredField = Class.forName(str).getDeclaredField(str3);
            declaredField.setAccessible(true);
            declaredField.set(objInvoke, new c((View.OnTouchListener) declaredField.get(objInvoke)));
        } catch (Throwable unused) {
        }
    }

    public static u b() {
        return d.INSTANCE.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 2 && !this.f3892c) {
            return false;
        }
        if (this.j && this.m < this.f) {
            return true;
        }
        if (this.j || this.k >= this.d || motionEvent.getEventTime() - this.l <= this.e) {
            return false;
        }
        this.j = true;
        this.k++;
        return true;
    }

    private void c(MotionEvent motionEvent) {
        this.j = false;
        this.l = motionEvent.getEventTime();
        this.m = 0;
        this.n = true;
    }

    public Object a() {
        String str;
        String str2;
        HashMap map = new HashMap();
        if (this.n) {
            this.n = false;
            str = "result";
            str2 = "ok";
        } else {
            str = "result";
            str2 = "unOk";
        }
        map.put(str, str2);
        return map;
    }

    public boolean a(String str, String str2, String str3, String str4, int i, int i2, int i3, boolean z) {
        if (this.f3891b) {
            return false;
        }
        this.f3891b = true;
        this.f3892c = z;
        if (i > 0) {
            this.d = i;
        }
        if (i2 > 0) {
            this.e = i2 * 60 * 1000;
        }
        if (i3 > 0 && i3 < 7) {
            this.f = i3 * 50;
        }
        DisplayMetrics displayMetricsC = com.bytedance.gameprotect.c.c();
        if (displayMetricsC != null) {
            this.g = displayMetricsC.widthPixels;
            this.h = displayMetricsC.heightPixels;
        }
        View viewA = com.bytedance.gameprotect.c.a(str);
        if (viewA == null) {
            return false;
        }
        a(viewA, str2, str3, str4);
        return true;
    }

    public Object c() {
        HashMap map = new HashMap();
        map.put("CollectTimes", String.valueOf(this.k));
        map.put("FirstEventTime", String.valueOf(this.i));
        map.put("WidthPixels", String.valueOf(this.g));
        map.put("HeightPixels", String.valueOf(this.h));
        return map;
    }

    public Object d() {
        HashMap map = new HashMap();
        synchronized (this.f3890a) {
            if (this.f3890a.size() > 0) {
                b bVar = (b) this.f3890a.get(0);
                map.put("Action", String.valueOf(bVar.a()));
                map.put("X", String.valueOf(bVar.f()));
                map.put("Y", String.valueOf(bVar.g()));
                map.put("HistorySize", String.valueOf(bVar.d()));
                map.put("EventTime", String.valueOf(bVar.c()));
                map.put("DeviceId", String.valueOf(bVar.b()));
                map.put("Pressure", String.valueOf(bVar.e()));
                this.f3890a.remove(0);
            }
        }
        return map;
    }
}
