package com.bytedance.bdturing;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class j {

    /* renamed from: a, reason: collision with root package name */
    public static final int f3811a = 2;

    /* renamed from: b, reason: collision with root package name */
    public static final int f3812b = 3;

    /* renamed from: c, reason: collision with root package name */
    public static final int f3813c = 4;
    public static final int d = 5;
    public static final int e = 6;
    public static final int f = 7;
    public static final int g = 8;
    public static final int h = 9;
    public static final int i = 10;
    public static final int j = 11;
    private HandlerThread k;
    private b l;

    private static class a {

        /* renamed from: a, reason: collision with root package name */
        private static j f3814a = new j();

        private a() {
        }
    }

    private j() {
    }

    public static j a() {
        return a.f3814a;
    }

    public void b() {
        if (this.k == null) {
            synchronized (j.class) {
                if (this.k == null) {
                    this.k = new HandlerThread("TuringVerifyThread");
                    this.k.start();
                    this.l = new b(this.k.getLooper());
                }
            }
        }
    }

    public void a(int i2, Object obj) {
        if (this.l != null) {
            this.l.sendMessage(this.l.obtainMessage(i2, obj));
        }
    }

    public void a(int i2, int i3, int i4, Object obj) {
        if (this.l != null) {
            this.l.sendMessage(this.l.obtainMessage(i2, i3, i4, obj));
        }
    }

    public void a(int i2, Object obj, long j2) {
        if (this.l != null) {
            this.l.sendMessageDelayed(this.l.obtainMessage(i2, obj), j2);
        }
    }

    public void a(Runnable runnable) {
        if (this.l != null) {
            this.l.post(runnable);
        }
    }

    public void a(int i2) {
        if (this.l != null) {
            this.l.removeMessages(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Message message) {
        if (message.obj != null) {
            e.a((JSONArray) message.obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (e.e()) {
            a(7, null, 500L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Message message) {
        if (message.obj != null) {
            ((i) message.obj).b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Message message) {
        if (message.obj != null) {
            com.bytedance.bdturing.b.b.a().b((String) message.obj);
        }
    }

    private class b extends Handler {
        private b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) throws JSONException, InterruptedException {
            switch (message.what) {
                case 2:
                    com.bytedance.bdturing.a.a().e();
                    break;
                case 3:
                    e.d();
                    break;
                case 4:
                    e.a((com.bytedance.bdturing.c.i) message.obj);
                    break;
                case 5:
                    e.c();
                    break;
                case 6:
                    j.this.a(message);
                    break;
                case 7:
                    j.this.c();
                    break;
                case 8:
                    j.this.b(message);
                    break;
                case 9:
                    e.b((com.bytedance.bdturing.c.i) message.obj);
                    break;
                case 10:
                    j.this.c(message);
                    break;
                case 11:
                    e.b();
                    break;
            }
        }
    }
}
