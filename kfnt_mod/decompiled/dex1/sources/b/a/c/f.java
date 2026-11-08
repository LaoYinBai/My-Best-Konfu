package b.a.c;

import b.ag;
import b.r;
import b.v;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    private final b.a f805a;

    /* renamed from: b, reason: collision with root package name */
    private final d f806b;

    /* renamed from: c, reason: collision with root package name */
    private final b.e f807c;
    private final r d;
    private int f;
    private List<Proxy> e = Collections.emptyList();
    private List<InetSocketAddress> g = Collections.emptyList();
    private final List<ag> h = new ArrayList();

    public f(b.a aVar, d dVar, b.e eVar, r rVar) {
        this.f805a = aVar;
        this.f806b = dVar;
        this.f807c = eVar;
        this.d = rVar;
        a(aVar.a(), aVar.h());
    }

    public boolean a() {
        return c() || !this.h.isEmpty();
    }

    public a b() throws IOException {
        if (!a()) {
            throw new NoSuchElementException();
        }
        ArrayList arrayList = new ArrayList();
        while (c()) {
            Proxy proxyD = d();
            int size = this.g.size();
            for (int i = 0; i < size; i++) {
                ag agVar = new ag(this.f805a, proxyD, this.g.get(i));
                if (this.f806b.c(agVar)) {
                    this.h.add(agVar);
                } else {
                    arrayList.add(agVar);
                }
            }
            if (!arrayList.isEmpty()) {
                break;
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.addAll(this.h);
            this.h.clear();
        }
        return new a(arrayList);
    }

    public void a(ag agVar, IOException iOException) {
        if (agVar.b().type() != Proxy.Type.DIRECT && this.f805a.g() != null) {
            this.f805a.g().connectFailed(this.f805a.a().b(), agVar.b().address(), iOException);
        }
        this.f806b.a(agVar);
    }

    private void a(v vVar, Proxy proxy) {
        List<Proxy> listA;
        if (proxy != null) {
            this.e = Collections.singletonList(proxy);
        } else {
            List<Proxy> listSelect = this.f805a.g().select(vVar.b());
            if (listSelect != null && !listSelect.isEmpty()) {
                listA = b.a.c.a(listSelect);
            } else {
                listA = b.a.c.a(Proxy.NO_PROXY);
            }
            this.e = listA;
        }
        this.f = 0;
    }

    private boolean c() {
        return this.f < this.e.size();
    }

    private Proxy d() throws IOException {
        if (!c()) {
            throw new SocketException("No route to " + this.f805a.a().i() + "; exhausted proxy configurations: " + this.e);
        }
        List<Proxy> list = this.e;
        int i = this.f;
        this.f = i + 1;
        Proxy proxy = list.get(i);
        a(proxy);
        return proxy;
    }

    private void a(Proxy proxy) throws IOException {
        String strI;
        int iJ;
        this.g = new ArrayList();
        if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.SOCKS) {
            strI = this.f805a.a().i();
            iJ = this.f805a.a().j();
        } else {
            SocketAddress socketAddressAddress = proxy.address();
            if (!(socketAddressAddress instanceof InetSocketAddress)) {
                throw new IllegalArgumentException("Proxy.address() is not an InetSocketAddress: " + socketAddressAddress.getClass());
            }
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddressAddress;
            strI = a(inetSocketAddress);
            iJ = inetSocketAddress.getPort();
        }
        if (iJ < 1 || iJ > 65535) {
            throw new SocketException("No route to " + strI + ":" + iJ + "; port is out of range");
        }
        if (proxy.type() == Proxy.Type.SOCKS) {
            this.g.add(InetSocketAddress.createUnresolved(strI, iJ));
            return;
        }
        this.d.a(this.f807c, strI);
        List<InetAddress> listLookup = this.f805a.b().lookup(strI);
        if (listLookup.isEmpty()) {
            throw new UnknownHostException(this.f805a.b() + " returned no addresses for " + strI);
        }
        this.d.a(this.f807c, strI, listLookup);
        int size = listLookup.size();
        for (int i = 0; i < size; i++) {
            this.g.add(new InetSocketAddress(listLookup.get(i), iJ));
        }
    }

    static String a(InetSocketAddress inetSocketAddress) {
        InetAddress address = inetSocketAddress.getAddress();
        if (address == null) {
            return inetSocketAddress.getHostName();
        }
        return address.getHostAddress();
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private final List<ag> f808a;

        /* renamed from: b, reason: collision with root package name */
        private int f809b = 0;

        a(List<ag> list) {
            this.f808a = list;
        }

        public boolean a() {
            return this.f809b < this.f808a.size();
        }

        public ag b() {
            if (!a()) {
                throw new NoSuchElementException();
            }
            List<ag> list = this.f808a;
            int i = this.f809b;
            this.f809b = i + 1;
            return list.get(i);
        }

        public List<ag> c() {
            return new ArrayList(this.f808a);
        }
    }
}
