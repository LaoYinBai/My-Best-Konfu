package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12;

import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.e;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.i;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b<T extends i> {

    /* renamed from: a, reason: collision with root package name */
    private e<T> f1939a;

    /* renamed from: b, reason: collision with root package name */
    private Handler f1940b;

    /* renamed from: c, reason: collision with root package name */
    private AtomicBoolean f1941c = new AtomicBoolean(false);

    public b(String str, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.a<T> aVar, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_if122.a<T> aVar2, e.b bVar, e.a aVar3) {
        this.f1939a = new e<>(str, aVar, aVar2, bVar, aVar3);
    }

    public void a() {
        if (this.f1941c.get()) {
            Message messageObtainMessage = this.f1940b.obtainMessage();
            messageObtainMessage.what = 2;
            this.f1940b.sendMessage(messageObtainMessage);
        }
    }

    public synchronized void b() {
        AtomicBoolean atomicBoolean = this.f1941c;
        if ((atomicBoolean == null || !atomicBoolean.get()) && this.f1939a.getLooper() == null) {
            AtomicBoolean atomicBoolean2 = this.f1941c;
            if (atomicBoolean2 != null && !atomicBoolean2.get()) {
                this.f1939a.start();
                this.f1940b = new Handler(this.f1939a.getLooper(), this.f1939a);
                Message messageObtainMessage = this.f1940b.obtainMessage();
                messageObtainMessage.what = 5;
                this.f1940b.sendMessage(messageObtainMessage);
                this.f1941c.set(true);
            }
        }
    }

    public void a(@NonNull T t) {
        if (this.f1941c.get()) {
            Message messageObtainMessage = this.f1940b.obtainMessage();
            messageObtainMessage.what = 1;
            messageObtainMessage.obj = t;
            this.f1940b.sendMessage(messageObtainMessage);
        }
    }
}
