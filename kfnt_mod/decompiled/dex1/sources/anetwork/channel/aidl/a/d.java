package anetwork.channel.aidl.a;

import android.os.RemoteException;
import anet.channel.bytes.ByteArray;
import anetwork.channel.aidl.f;
import com.bytedance.sdk.adnet.game.http.cb.NetCallback;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d extends f.a {

    /* renamed from: c, reason: collision with root package name */
    private static final ByteArray f656c = ByteArray.create(0);
    private int f;
    private int g;
    private int h;
    private final AtomicBoolean d = new AtomicBoolean(false);
    private LinkedList<ByteArray> e = new LinkedList<>();
    private int i = NetCallback.ERROR_CODE_REQUEST_SUCCESS;
    private String j = "";

    /* renamed from: a, reason: collision with root package name */
    final ReentrantLock f657a = new ReentrantLock();

    /* renamed from: b, reason: collision with root package name */
    final Condition f658b = this.f657a.newCondition();

    public void a(ByteArray byteArray) {
        if (this.d.get()) {
            return;
        }
        this.f657a.lock();
        try {
            this.e.add(byteArray);
            this.f658b.signal();
        } finally {
            this.f657a.unlock();
        }
    }

    public void a() {
        a(f656c);
    }

    private void f() {
        this.f657a.lock();
        try {
            this.e.set(this.f, f656c).recycle();
        } finally {
            this.f657a.unlock();
        }
    }

    @Override // anetwork.channel.aidl.f
    public int b() throws RemoteException {
        if (this.d.get()) {
            throw new RuntimeException("Stream is closed");
        }
        this.f657a.lock();
        try {
            int dataLength = 0;
            if (this.f == this.e.size()) {
                return 0;
            }
            ListIterator<ByteArray> listIterator = this.e.listIterator(this.f);
            while (listIterator.hasNext()) {
                dataLength += listIterator.next().getDataLength();
            }
            return dataLength - this.g;
        } finally {
            this.f657a.unlock();
        }
    }

    @Override // anetwork.channel.aidl.f
    public void c() throws RemoteException {
        if (this.d.compareAndSet(false, true)) {
            this.f657a.lock();
            try {
                Iterator<ByteArray> it = this.e.iterator();
                while (it.hasNext()) {
                    ByteArray next = it.next();
                    if (next != f656c) {
                        next.recycle();
                    }
                }
                this.e.clear();
                this.e = null;
                this.f = -1;
                this.g = -1;
                this.h = 0;
            } finally {
                this.f657a.unlock();
            }
        }
    }

    @Override // anetwork.channel.aidl.f
    public int d() throws RemoteException {
        byte b2;
        if (this.d.get()) {
            throw new RuntimeException("Stream is closed");
        }
        this.f657a.lock();
        while (true) {
            try {
                try {
                    if (this.f == this.e.size() && !this.f658b.await(this.i, TimeUnit.MILLISECONDS)) {
                        c();
                        throw new RuntimeException("await timeout.");
                    }
                    ByteArray byteArray = this.e.get(this.f);
                    if (byteArray == f656c) {
                        b2 = -1;
                        break;
                    }
                    if (this.g < byteArray.getDataLength()) {
                        b2 = byteArray.getBuffer()[this.g];
                        this.g++;
                        break;
                    }
                    f();
                    this.f++;
                    this.g = 0;
                } catch (InterruptedException unused) {
                    c();
                    throw new RuntimeException("await interrupt");
                }
            } finally {
                this.f657a.unlock();
            }
        }
        return b2;
    }

    @Override // anetwork.channel.aidl.f
    public int a(byte[] bArr, int i, int i2) throws RemoteException {
        int i3;
        if (this.d.get()) {
            throw new RuntimeException("Stream is closed");
        }
        if (bArr == null) {
            throw new NullPointerException();
        }
        if (i < 0 || i2 < 0 || (i3 = i2 + i) > bArr.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.f657a.lock();
        int i4 = i;
        while (i4 < i3) {
            try {
                try {
                    if (this.f == this.e.size() && !this.f658b.await(this.i, TimeUnit.MILLISECONDS)) {
                        c();
                        throw new RuntimeException("await timeout.");
                    }
                    ByteArray byteArray = this.e.get(this.f);
                    if (byteArray == f656c) {
                        break;
                    }
                    int dataLength = byteArray.getDataLength() - this.g;
                    int i5 = i3 - i4;
                    if (dataLength < i5) {
                        System.arraycopy(byteArray.getBuffer(), this.g, bArr, i4, dataLength);
                        i4 += dataLength;
                        f();
                        this.f++;
                        this.g = 0;
                    } else {
                        System.arraycopy(byteArray.getBuffer(), this.g, bArr, i4, i5);
                        this.g += i5;
                        i4 += i5;
                    }
                } catch (InterruptedException unused) {
                    c();
                    throw new RuntimeException("await interrupt");
                }
            } catch (Throwable th) {
                this.f657a.unlock();
                throw th;
            }
        }
        this.f657a.unlock();
        int i6 = i4 - i;
        if (i6 > 0) {
            return i6;
        }
        return -1;
    }

    @Override // anetwork.channel.aidl.f
    public int a(byte[] bArr) throws RemoteException {
        return a(bArr, 0, bArr.length);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x002f, code lost:
    
        r1 = r1 + (r2 - r5.g);
        f();
        r5.f++;
        r5.g = 0;
     */
    @Override // anetwork.channel.aidl.f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long a(int r6) throws android.os.RemoteException {
        /*
            r5 = this;
            java.util.concurrent.locks.ReentrantLock r0 = r5.f657a
            r0.lock()
            r0 = 0
            r1 = 0
        L7:
            if (r1 >= r6) goto L4f
            int r2 = r5.f     // Catch: java.lang.Throwable -> L48
            java.util.LinkedList<anet.channel.bytes.ByteArray> r3 = r5.e     // Catch: java.lang.Throwable -> L48
            int r3 = r3.size()     // Catch: java.lang.Throwable -> L48
            if (r2 != r3) goto L14
            goto L4f
        L14:
            java.util.LinkedList<anet.channel.bytes.ByteArray> r2 = r5.e     // Catch: java.lang.Throwable -> L48
            int r3 = r5.f     // Catch: java.lang.Throwable -> L48
            java.lang.Object r2 = r2.get(r3)     // Catch: java.lang.Throwable -> L48
            anet.channel.bytes.ByteArray r2 = (anet.channel.bytes.ByteArray) r2     // Catch: java.lang.Throwable -> L48
            anet.channel.bytes.ByteArray r3 = anetwork.channel.aidl.a.d.f656c     // Catch: java.lang.Throwable -> L48
            if (r2 != r3) goto L23
            goto L4f
        L23:
            int r2 = r2.getDataLength()     // Catch: java.lang.Throwable -> L48
            int r3 = r5.g     // Catch: java.lang.Throwable -> L48
            int r3 = r2 - r3
            int r4 = r6 - r1
            if (r3 >= r4) goto L3f
            int r6 = r5.g     // Catch: java.lang.Throwable -> L48
            int r2 = r2 - r6
            int r1 = r1 + r2
            r5.f()     // Catch: java.lang.Throwable -> L48
            int r6 = r5.f     // Catch: java.lang.Throwable -> L48
            int r6 = r6 + 1
            r5.f = r6     // Catch: java.lang.Throwable -> L48
            r5.g = r0     // Catch: java.lang.Throwable -> L48
            goto L4f
        L3f:
            int r1 = r5.g     // Catch: java.lang.Throwable -> L48
            int r2 = r6 - r6
            int r1 = r1 + r2
            r5.g = r1     // Catch: java.lang.Throwable -> L48
            r1 = r6
            goto L7
        L48:
            r6 = move-exception
            java.util.concurrent.locks.ReentrantLock r0 = r5.f657a
            r0.unlock()
            throw r6
        L4f:
            java.util.concurrent.locks.ReentrantLock r6 = r5.f657a
            r6.unlock()
            long r0 = (long) r1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: anetwork.channel.aidl.a.d.a(int):long");
    }

    @Override // anetwork.channel.aidl.f
    public int e() throws RemoteException {
        return this.h;
    }

    public void a(anetwork.channel.entity.h hVar, int i) {
        this.h = i;
        this.j = hVar.e;
        this.i = hVar.d;
    }
}
