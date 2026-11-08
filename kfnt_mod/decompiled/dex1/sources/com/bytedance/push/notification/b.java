package com.bytedance.push.notification;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.bytedance.push.notification.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b implements Handler.Callback, c {

    /* renamed from: a, reason: collision with root package name */
    private final com.bytedance.push.b.a f4879a;

    /* renamed from: b, reason: collision with root package name */
    private Handler f4880b;

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        return false;
    }

    b(com.bytedance.push.b.a aVar) {
        this.f4879a = aVar;
    }

    public void a(final com.bytedance.push.b.c cVar, final a.InterfaceC0147a interfaceC0147a) {
        if (this.f4880b == null) {
            this.f4880b = new Handler(Looper.getMainLooper(), this);
        }
        com.bytedance.common.push.utility.b.b.a(new Runnable() { // from class: com.bytedance.push.notification.b.1
            @Override // java.lang.Runnable
            public void run() {
                final Bitmap bitmapA;
                try {
                    bitmapA = b.this.f4879a.a(cVar);
                } catch (Throwable th) {
                    th.printStackTrace();
                    bitmapA = null;
                }
                b.this.f4880b.post(new Runnable() { // from class: com.bytedance.push.notification.b.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (bitmapA != null && !bitmapA.isRecycled()) {
                            interfaceC0147a.a(bitmapA);
                        } else {
                            interfaceC0147a.a();
                        }
                    }
                });
            }
        });
    }

    @Override // com.bytedance.push.b.a
    public Bitmap a(com.bytedance.push.b.c cVar) {
        return this.f4879a.a(cVar);
    }
}
