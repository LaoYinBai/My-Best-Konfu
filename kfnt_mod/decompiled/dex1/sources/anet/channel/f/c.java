package anet.channel.f;

import androidx.core.view.InputDeviceCompat;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.session.TnetSpdySession;
import anet.channel.strategy.IConnStrategy;
import java.util.List;
import org.android.spdy.SpdyAgent;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class c implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ List f443a;

    c(List list) {
        this.f443a = list;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (a.f442c.compareAndSet(false, true)) {
            SpdyAgent.InitializeCerts();
        }
        IConnStrategy iConnStrategy = (IConnStrategy) this.f443a.get(0);
        TnetSpdySession tnetSpdySession = new TnetSpdySession(GlobalAppRuntimeInfo.getContext(), new anet.channel.entity.a("https://" + a.f440a, "QuicDetect" + a.e.getAndIncrement(), iConnStrategy));
        tnetSpdySession.registerEventcb(InputDeviceCompat.SOURCE_KEYBOARD, new d(this, iConnStrategy));
        tnetSpdySession.q.isCommitted = true;
        tnetSpdySession.connect();
    }
}
