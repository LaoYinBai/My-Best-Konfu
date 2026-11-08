package com.mbridge.msdk.foundation.same.net;

import android.text.TextUtils;
import android.util.Log;
import com.mbridge.msdk.playercommon.exoplayer2.DefaultLoadControl;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: SocketManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class m {
    private static final String a = "m";
    private static String b = com.mbridge.msdk.foundation.tools.n.b("DkPtYdQTLkfAW+xUhoPwLkPTHkJBDkM/Yr5T");
    private static int c = 9377;
    private static volatile AtomicInteger d = new AtomicInteger(1);
    private ExecutorService e;

    public final void a(String str) {
        b = str;
    }

    public final void a(int i) {
        c = i;
    }

    private m() {
        this.e = Executors.newCachedThreadPool();
    }

    public static m a() {
        return b.a;
    }

    /* compiled from: SocketManager.java */
    private static final class b {
        private static m a = new m();
    }

    public final synchronized void a(String str, e eVar) {
        this.e.execute(new a(str, eVar));
    }

    /* compiled from: SocketManager.java */
    private class a implements Runnable {
        private String b;
        private ByteBuffer c;
        private OutputStream d;
        private boolean e;
        private e f;
        private Socket g;

        a(String str, e eVar) {
            this.e = false;
            this.b = str;
            this.e = false;
            this.f = eVar;
        }

        @Override // java.lang.Runnable
        public final void run() throws IOException {
            Log.d(m.a, "SendService: msg = " + this.b);
            try {
                try {
                    this.g = new Socket(m.b, m.c);
                    this.g.setSoTimeout(DefaultLoadControl.DEFAULT_MIN_BUFFER_MS);
                    Log.d(m.a, "SendService: new socket.isConnected = " + this.g.isConnected());
                    this.d = this.g.getOutputStream();
                    this.c = ByteBuffer.wrap(new byte[8]);
                    this.c.order(ByteOrder.BIG_ENDIAN);
                    this.c.put((byte) 1);
                    if (TextUtils.isEmpty(this.b)) {
                        this.c.put((byte) 1);
                    } else {
                        this.c.put(this.e ? (byte) 3 : (byte) 2);
                    }
                    this.c.putShort((short) m.d.getAndIncrement());
                    if (TextUtils.isEmpty(this.b)) {
                        this.c.putInt(0);
                        Log.d(m.a, Arrays.toString(this.c.array()));
                        this.d.write(this.c.array());
                    } else {
                        this.c.putInt(this.b.getBytes().length);
                        this.d.write(this.c.array());
                        Log.d(m.a, "msg.getBytes().length = " + this.b.getBytes().length + " " + Arrays.toString(this.c.array()));
                        this.d.write(this.b.getBytes());
                    }
                    this.d.flush();
                    InputStream inputStream = this.g.getInputStream();
                    byte[] bArr = new byte[8];
                    inputStream.read(bArr, 0, bArr.length);
                    Log.d(m.a, "resp header:" + Arrays.toString(bArr));
                    this.c = ByteBuffer.wrap(bArr);
                    this.c.order(ByteOrder.BIG_ENDIAN);
                    int i = this.c.getInt(4);
                    byte[] bArr2 = new byte[i];
                    inputStream.read(bArr2, 0, i);
                    Log.d(m.a, "SendService succeed data:" + Arrays.toString(bArr2));
                    if (i >= 1 && bArr2[0] == 1) {
                        if (this.f != null) {
                            this.f.a(k.a(null, new com.mbridge.msdk.foundation.same.net.f.c(200, null, null)));
                        }
                    } else if (this.f != null) {
                        this.f.a(new com.mbridge.msdk.foundation.same.net.a.a(2, new com.mbridge.msdk.foundation.same.net.f.c(404, null, null)));
                    }
                    this.g.close();
                    this.c = null;
                    inputStream.close();
                    this.d.close();
                    if (this.g != null) {
                        try {
                            this.g.close();
                        } catch (IOException e) {
                            e = e;
                            e.printStackTrace();
                            this.f = null;
                        }
                    }
                } catch (Throwable th) {
                    if (this.g != null) {
                        try {
                            this.g.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    this.f = null;
                    throw th;
                }
            } catch (Throwable th2) {
                Log.d(m.a, "SendService exception: " + th2.getMessage());
                if (this.f != null) {
                    this.f.a(new com.mbridge.msdk.foundation.same.net.a.a(2, new com.mbridge.msdk.foundation.same.net.f.c(404, null, null)));
                }
                if (this.g != null) {
                    try {
                        this.g.close();
                    } catch (IOException e3) {
                        e = e3;
                        e.printStackTrace();
                        this.f = null;
                    }
                }
            }
            this.f = null;
        }
    }
}
