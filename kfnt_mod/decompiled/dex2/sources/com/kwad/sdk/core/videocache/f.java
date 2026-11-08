package com.kwad.sdk.core.videocache;

import android.content.Context;
import android.net.Uri;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f {
    private final Object a;
    private final ExecutorService b;
    private final Map<String, g> c;
    private final ServerSocket d;
    private final int e;
    private final Thread f;
    private final com.kwad.sdk.core.videocache.c g;

    public static final class a {
        private File a;
        private com.kwad.sdk.core.videocache.c.b d;
        private com.kwad.sdk.core.videocache.kwai.a c = new com.kwad.sdk.core.videocache.kwai.g(536870912);
        private com.kwad.sdk.core.videocache.kwai.c b = new com.kwad.sdk.core.videocache.kwai.f();
        private com.kwad.sdk.core.videocache.a.b e = new com.kwad.sdk.core.videocache.a.a();

        public a(Context context) {
            this.d = com.kwad.sdk.core.videocache.c.c.a(context);
            this.a = o.a(context);
        }

        private com.kwad.sdk.core.videocache.c b() {
            return new com.kwad.sdk.core.videocache.c(this.a, this.b, this.c, this.d, this.e);
        }

        public a a(long j) {
            this.c = new com.kwad.sdk.core.videocache.kwai.g(j);
            return this;
        }

        public f a() {
            return new f(b());
        }
    }

    private final class b implements Runnable {
        private final Socket b;

        public b(Socket socket) {
            this.b = socket;
        }

        @Override // java.lang.Runnable
        public void run() throws IOException {
            com.kwad.sdk.core.d.a.a("HttpProxyCacheServer", "schedule SocketProcessorRunnable run");
            f.this.a(this.b);
        }
    }

    private final class c implements Runnable {
        private final CountDownLatch b;

        public c(CountDownLatch countDownLatch) {
            this.b = countDownLatch;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.b.countDown();
                f.this.a();
            } catch (Throwable th) {
                com.kwad.sdk.core.d.a.a(th);
            }
        }
    }

    private f(com.kwad.sdk.core.videocache.c cVar) throws InterruptedException {
        this.a = new Object();
        this.b = com.kwad.sdk.core.i.b.l();
        this.c = new ConcurrentHashMap();
        this.g = (com.kwad.sdk.core.videocache.c) j.a(cVar);
        try {
            this.d = new ServerSocket(0, 8, InetAddress.getByName("127.0.0.1"));
            this.e = this.d.getLocalPort();
            i.a("127.0.0.1", this.e);
            CountDownLatch countDownLatch = new CountDownLatch(1);
            this.f = new Thread(new c(countDownLatch));
            this.f.start();
            countDownLatch.await();
        } catch (IOException | InterruptedException e) {
            this.b.shutdown();
            throw new IllegalStateException("Error starting local proxy server", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() throws IOException {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Socket socketAccept = this.d.accept();
                com.kwad.sdk.core.d.a.a("HttpProxyCacheServer", "Accept new socket " + socketAccept);
                this.b.submit(new b(socketAccept));
            } catch (IOException e) {
                a(new ProxyCacheException("Error during waiting connection", e));
                return;
            }
        }
    }

    private void a(File file) {
        try {
            this.g.c.a(file);
        } catch (IOException unused) {
            com.kwad.sdk.core.d.a.e("HttpProxyCacheServer", "Error touching file " + file);
        }
    }

    private void a(Throwable th) {
        com.kwad.sdk.core.d.a.e("HttpProxyCacheServer", "HttpProxyCacheServer error");
        com.kwad.sdk.core.d.a.b(th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [com.kwad.sdk.core.videocache.f] */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.net.Socket] */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v3, types: [java.net.Socket] */
    /* JADX WARN: Type inference failed for: r5v6, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    public void a(Socket socket) throws IOException {
        StringBuilder sb;
        try {
            try {
                d dVarA = d.a(socket.getInputStream());
                com.kwad.sdk.core.d.a.a("HttpProxyCacheServer", "Request to cache proxy:" + dVarA);
                f(l.c(dVarA.a)).a(dVarA, socket);
                b(socket);
                sb = new StringBuilder();
                socket = "HttpProxyCacheServer";
            } catch (ProxyCacheException | IOException e) {
                a(new ProxyCacheException("Error processing request", e));
                b(socket);
                sb = new StringBuilder();
                socket = "HttpProxyCacheServer";
            } catch (SocketException e2) {
                com.kwad.sdk.core.d.a.a("HttpProxyCacheServer", "Closing socket… Socket is closed by client.");
                e2.printStackTrace();
                b(socket);
                sb = new StringBuilder();
                socket = "HttpProxyCacheServer";
            }
            sb.append("Opened connections: ");
            sb.append(b());
            com.kwad.sdk.core.d.a.a((String) socket, sb.toString());
        } catch (Throwable th) {
            b(socket);
            com.kwad.sdk.core.d.a.a("HttpProxyCacheServer", "Opened connections: " + b());
            throw th;
        }
    }

    private int b() {
        int iB;
        synchronized (this.a) {
            iB = 0;
            Iterator<g> it = this.c.values().iterator();
            while (it.hasNext()) {
                iB += it.next().b();
            }
        }
        return iB;
    }

    private void b(Socket socket) throws IOException {
        c(socket);
        d(socket);
        e(socket);
    }

    private void c(Socket socket) throws IOException {
        try {
            if (socket.isInputShutdown()) {
                return;
            }
            socket.shutdownInput();
        } catch (SocketException unused) {
            com.kwad.sdk.core.d.a.a("HttpProxyCacheServer", "Releasing input stream… Socket is closed by client.");
        } catch (IOException e) {
            a(new ProxyCacheException("Error closing socket input stream", e));
        }
    }

    private void d(Socket socket) throws IOException {
        try {
            if (socket.isOutputShutdown()) {
                return;
            }
            socket.shutdownOutput();
        } catch (IOException unused) {
            com.kwad.sdk.core.d.a.d("HttpProxyCacheServer", "Failed to close socket on proxy side: {}. It seems client have already closed connection.");
        }
    }

    private String e(String str) {
        return String.format(Locale.US, "http://%s:%d/%s", "127.0.0.1", Integer.valueOf(this.e), l.b(str));
    }

    private void e(Socket socket) throws IOException {
        try {
            if (socket.isClosed()) {
                return;
            }
            socket.close();
        } catch (IOException e) {
            a(new ProxyCacheException("Error closing socket", e));
        }
    }

    private g f(String str) {
        g gVar;
        synchronized (this.a) {
            gVar = this.c.get(str);
            if (gVar == null) {
                gVar = new g(str, this.g);
                this.c.put(str, gVar);
            }
        }
        return gVar;
    }

    public String a(String str) {
        return a(str, true);
    }

    public String a(String str, boolean z) {
        if (!z || !d(str).exists()) {
            return e(str);
        }
        File fileD = d(str);
        a(fileD);
        return Uri.fromFile(fileD).toString();
    }

    public boolean a(String str, int i) throws Throwable {
        HttpURLConnection httpURLConnection;
        BufferedInputStream bufferedInputStream;
        com.kwad.sdk.core.d.a.a("HttpProxyCacheServer", "preloadSync preloadUrl " + str);
        if (b(str)) {
            return true;
        }
        long j = 0;
        BufferedInputStream bufferedInputStream2 = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(e(str)).openConnection();
            try {
                try {
                    com.kwad.sdk.core.network.m.a(httpURLConnection);
                    httpURLConnection.setRequestProperty("Accept-Language", "zh-CN");
                    httpURLConnection.setConnectTimeout(10000);
                    httpURLConnection.setReadTimeout(120000);
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setRequestProperty("Connection", "keep-alive");
                    httpURLConnection.setRequestProperty("Charset", C.UTF8_NAME);
                    httpURLConnection.setRequestProperty("User-Agent", com.kwad.sdk.core.network.k.c());
                    httpURLConnection.setRequestProperty("BrowserUa", com.kwad.sdk.core.network.k.d());
                    httpURLConnection.setRequestProperty("SystemUa", com.kwad.sdk.core.network.k.a());
                    bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                } catch (Exception e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e2) {
            e = e2;
            httpURLConnection = null;
        } catch (Throwable th2) {
            th = th2;
            httpURLConnection = null;
        }
        try {
            byte[] bArr = new byte[1024];
            try {
                do {
                    int i2 = bufferedInputStream.read(bArr);
                    if (i2 != -1) {
                        j += i2;
                    }
                    break;
                } while (j <= i);
                break;
                bufferedInputStream.close();
            } catch (IOException unused) {
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return true;
        } catch (Exception e3) {
            e = e3;
            bufferedInputStream2 = bufferedInputStream;
            com.kwad.sdk.core.d.a.b(e);
            com.kwad.sdk.core.d.a.a("HttpProxyCacheServer", "downloadUrlToStream file crash", e);
            if (bufferedInputStream2 != null) {
                try {
                    bufferedInputStream2.close();
                } catch (IOException unused2) {
                }
            }
            if (httpURLConnection == null) {
                return false;
            }
            httpURLConnection.disconnect();
            return false;
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream2 = bufferedInputStream;
            if (bufferedInputStream2 != null) {
                try {
                    bufferedInputStream2.close();
                } catch (IOException unused3) {
                }
            }
            if (httpURLConnection == null) {
                throw th;
            }
            httpURLConnection.disconnect();
            throw th;
        }
    }

    public boolean b(String str) {
        j.a(str, "Url can't be null!");
        return d(str).exists();
    }

    public boolean c(String str) {
        g gVar = this.c.get(str);
        if (gVar == null) {
            return false;
        }
        gVar.a();
        this.c.remove(str);
        return true;
    }

    public File d(String str) {
        return new File(this.g.a, this.g.b.a(str));
    }
}
