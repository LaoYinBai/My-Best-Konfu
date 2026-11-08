package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g extends Handler {

    /* renamed from: a, reason: collision with root package name */
    private final WeakReference<a> f1456a;

    public interface a {
        void handleMsg(Message message);
    }

    public g(Looper looper, a aVar) {
        super(looper);
        this.f1456a = new WeakReference<>(aVar);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        a aVar = this.f1456a.get();
        if (aVar == null || message == null) {
            return;
        }
        aVar.handleMsg(message);
    }
}
