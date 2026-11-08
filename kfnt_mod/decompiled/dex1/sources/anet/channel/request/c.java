package anet.channel.request;

import anet.channel.util.ALog;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import org.android.spdy.SpdyErrorException;
import org.android.spdy.SpdySession;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c implements Cancelable {
    public static final c NULL = new c(null, 0, null);

    /* renamed from: a, reason: collision with root package name */
    private final int f483a;

    /* renamed from: b, reason: collision with root package name */
    private final SpdySession f484b;

    /* renamed from: c, reason: collision with root package name */
    private final String f485c;

    public c(SpdySession spdySession, int i, String str) {
        this.f484b = spdySession;
        this.f483a = i;
        this.f485c = str;
    }

    @Override // anet.channel.request.Cancelable
    public void cancel() {
        try {
            if (this.f484b == null || this.f483a == 0) {
                return;
            }
            ALog.i("awcn.TnetCancelable", "cancel tnet request", this.f485c, "streamId", Integer.valueOf(this.f483a));
            this.f484b.streamReset(this.f483a, 5);
        } catch (SpdyErrorException e) {
            ALog.e("awcn.TnetCancelable", "request cancel failed.", this.f485c, e, AccountMonitorConstants.CommonParameter.ERROR, Integer.valueOf(e.SpdyErrorGetCode()));
        }
    }
}
