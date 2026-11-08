package anet.channel.strategy;

import android.text.TextUtils;
import anet.channel.util.ALog;
import java.net.InetAddress;
import java.util.Collections;
import java.util.LinkedList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class b implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f536a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ Object f537b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ a f538c;

    b(a aVar, String str, Object obj) {
        this.f538c = aVar;
        this.f536a = str;
        this.f537b = obj;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            try {
                String hostAddress = InetAddress.getByName(this.f536a).getHostAddress();
                LinkedList linkedList = new LinkedList();
                ConnProtocol connProtocol = StrategyTemplate.getInstance().getConnProtocol(this.f536a);
                if (connProtocol != null) {
                    linkedList.add(IPConnStrategy.a(hostAddress, !(connProtocol.protocol.equalsIgnoreCase("https") || !TextUtils.isEmpty(connProtocol.publicKey)) ? 80 : 443, connProtocol, 0, 0, 1, 45000));
                }
                linkedList.add(IPConnStrategy.a(hostAddress, 80, ConnProtocol.HTTP, 0, 0, 0, 0));
                linkedList.add(IPConnStrategy.a(hostAddress, 443, ConnProtocol.HTTPS, 0, 0, 0, 0));
                this.f538c.f534a.put(this.f536a, linkedList);
                if (ALog.isPrintLog(1)) {
                    ALog.d("awcn.LocalDnsStrategyTable", "resolve ip by local dns", null, com.alipay.sdk.cons.c.f, this.f536a, "ip", hostAddress, "list", linkedList);
                }
                synchronized (this.f538c.f535b) {
                    this.f538c.f535b.remove(this.f536a);
                }
                synchronized (this.f537b) {
                    this.f537b.notifyAll();
                }
            } catch (Exception unused) {
                if (ALog.isPrintLog(1)) {
                    ALog.d("awcn.LocalDnsStrategyTable", "resolve ip by local dns failed", null, com.alipay.sdk.cons.c.f, this.f536a);
                }
                this.f538c.f534a.put(this.f536a, Collections.EMPTY_LIST);
                synchronized (this.f538c.f535b) {
                    this.f538c.f535b.remove(this.f536a);
                    synchronized (this.f537b) {
                        this.f537b.notifyAll();
                    }
                }
            }
        } catch (Throwable th) {
            synchronized (this.f538c.f535b) {
                this.f538c.f535b.remove(this.f536a);
                synchronized (this.f537b) {
                    this.f537b.notifyAll();
                    throw th;
                }
            }
        }
    }
}
