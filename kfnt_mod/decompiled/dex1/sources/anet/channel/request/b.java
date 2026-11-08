package anet.channel.request;

import anet.channel.util.ALog;
import java.util.concurrent.Future;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b implements Cancelable {
    public static final b NULL = new b(null, null);

    /* renamed from: a, reason: collision with root package name */
    private final Future<?> f481a;

    /* renamed from: b, reason: collision with root package name */
    private final String f482b;

    public b(Future<?> future, String str) {
        this.f481a = future;
        this.f482b = str;
    }

    @Override // anet.channel.request.Cancelable
    public void cancel() {
        if (this.f481a != null) {
            ALog.i("awcn.FutureCancelable", "cancel request", this.f482b, new Object[0]);
            this.f481a.cancel(true);
        }
    }
}
