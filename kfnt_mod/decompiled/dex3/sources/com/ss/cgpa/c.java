package com.ss.cgpa;

import android.util.Log;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c extends com.ss.cgpa.a {
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private SelectionKey i;
    private SocketChannel j;
    private DataClientHelp k;
    private String l;
    private boolean m;
    private String n;

    private static class b {
        private static final c a = new c(100, 300, 36666);
    }

    private c(int i, int i2, int i3) {
        this(i, i2, i3, i3 + 10);
    }

    private c(int i, int i2, int i3, int i4) {
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = false;
        this.n = c.class.getName();
        b(i, i2);
        a(i3, i4);
        super.a(this.n);
    }

    private int e() {
        while (this.h < this.g) {
            try {
            } catch (Exception e) {
                Log.e(this.n, e.toString());
                g();
            }
            if (!f()) {
                return -1;
            }
            SocketChannel socketChannel = this.j;
            int i = this.h;
            this.h = i + 1;
            if (!socketChannel.connect(new InetSocketAddress(i))) {
                this.i = this.j.register(d(), 8);
                return 0;
            }
            if (i()) {
                return 1;
            }
        }
        return -1;
    }

    private boolean f() throws IOException {
        try {
            if (this.j == null) {
                SocketChannel socketChannelOpen = SocketChannel.open();
                this.j = socketChannelOpen;
                socketChannelOpen.configureBlocking(false);
            }
        } catch (Exception e) {
            Log.e(this.n, e.toString());
            g();
        }
        return this.j != null;
    }

    private void g() {
        SocketChannel socketChannel = this.j;
        if (socketChannel != null) {
            try {
                socketChannel.close();
            } catch (Exception e) {
                Log.e(this.n, e.toString());
            }
            this.j = null;
        }
    }

    public static final c h() {
        return b.a;
    }

    private boolean i() {
        try {
            if (this.j.finishConnect()) {
                DataClientHelp dataClientHelp = new DataClientHelp(this.l);
                this.i.interestOps(5);
                this.i.attach(dataClientHelp);
                this.k = dataClientHelp;
                return true;
            }
        } catch (Exception e) {
            Log.e(this.n, e.toString());
        }
        g();
        return false;
    }

    private boolean k() {
        this.h = this.f;
        while (true) {
            int iE = e();
            if (iE > 0) {
                return true;
            }
            if (iE < 0) {
                return false;
            }
            if (a(this.e) > 0) {
                Iterator<SelectionKey> it = c().iterator();
                while (it.hasNext()) {
                    SelectionKey next = it.next();
                    it.remove();
                    if (next.isConnectable() && i()) {
                        return true;
                    }
                    next.cancel();
                }
            }
            this.i.cancel();
            g();
        }
    }

    public void a(int i, int i2) {
        this.f = i;
        this.g = i2;
    }

    public void b(int i, int i2) {
        this.d = i;
        this.e = i2;
    }

    public void b(String str) throws IOException {
        c(str);
        j();
    }

    public void c(String str) {
        this.l = str;
    }

    public void j() throws IOException {
        if (this.m) {
            return;
        }
        this.m = true;
        if (this.l != null && a()) {
            if (!k()) {
                b();
                g();
                DataClientHelp dataClientHelp = this.k;
                if (dataClientHelp != null) {
                    dataClientHelp.close();
                    return;
                }
                return;
            }
            while (true) {
                int iA = a(this.d);
                if (iA == 0) {
                    if (this.k.procWriteCgpMsg() > 0) {
                        SelectionKey selectionKey = this.i;
                        selectionKey.interestOps(selectionKey.interestOps() | 4);
                    }
                } else {
                    if (iA < 0) {
                        break;
                    }
                    Iterator<SelectionKey> it = c().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            SelectionKey next = it.next();
                            it.remove();
                            if (next.isReadable() && !b(next)) {
                                b();
                                break;
                            } else if (next.isValid() && next.isWritable()) {
                                c(next);
                            }
                        }
                    }
                }
            }
            b();
            g();
            if (this.i != null) {
                this.i = null;
            }
            DataClientHelp dataClientHelp2 = this.k;
            if (dataClientHelp2 != null) {
                dataClientHelp2.close();
                this.k = null;
            }
        }
    }
}
