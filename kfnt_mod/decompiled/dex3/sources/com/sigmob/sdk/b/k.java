package com.sigmob.sdk.b;

import com.sigmob.logger.SigmobLog;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class k {
    private final ExecutorService a = Executors.newSingleThreadExecutor();
    private final String b;
    private final int c;

    private class a implements Callable<Boolean> {
        private a() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean call() {
            return Boolean.valueOf(k.this.b());
        }
    }

    k(String str, int i) {
        this.b = (String) l.a(str);
        this.c = i;
    }

    private List<Proxy> a() {
        try {
            return ProxySelector.getDefault().select(new URI(c()));
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b() {
        h hVar = new h(c());
        try {
            byte[] bytes = "ping ok".getBytes();
            hVar.a(0L);
            byte[] bArr = new byte[bytes.length];
            hVar.a(bArr);
            boolean zEquals = Arrays.equals(bytes, bArr);
            SigmobLog.i("Ping response: `" + new String(bArr) + "`, pinged? " + zEquals);
            return zEquals;
        } catch (n e) {
            SigmobLog.e("Error reading ping response", e);
            return false;
        } finally {
            hVar.b();
        }
    }

    private String c() {
        return String.format(Locale.US, "http://%s:%d/%s", this.b, Integer.valueOf(this.c), "ping");
    }

    void a(Socket socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("HTTP/1.1 200 OK\n\n".getBytes());
        outputStream.write("ping ok".getBytes());
    }

    boolean a(int i, int i2) {
        l.a(i >= 1);
        l.a(i2 > 0);
        int i3 = i2;
        int i4 = 0;
        while (i4 < i) {
            try {
            } catch (InterruptedException | ExecutionException e) {
                SigmobLog.w("Error pinging server due to unexpected error", e);
            } catch (TimeoutException unused) {
                SigmobLog.w("Error pinging server (attempt: " + i4 + ", timeout: " + i3 + "). ");
            }
            if (((Boolean) this.a.submit(new a()).get(i3, TimeUnit.MILLISECONDS)).booleanValue()) {
                return true;
            }
            i4++;
            i3 *= 2;
        }
        String str = String.format(Locale.US, "Error pinging server (attempts: %d, max timeout: %d). If you see this message, please, report at https://github.com/danikula/AndroidVideoCache/issues/134. Default proxies are: %s", Integer.valueOf(i4), Integer.valueOf(i3 / 2), a());
        SigmobLog.e(str, new n(str));
        return false;
    }

    boolean a(String str) {
        return "ping".equals(str);
    }
}
