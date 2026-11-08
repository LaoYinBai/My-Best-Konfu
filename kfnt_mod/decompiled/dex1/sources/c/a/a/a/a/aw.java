package c.a.a.a.a;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import anet.channel.request.Request;
import anet.channel.util.HttpConstant;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import com.bytedance.sdk.adnet.game.http.cb.NetCallback;
import com.bytedance.sdk.b.d.a;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.util.Locale;
import javax.net.ssl.HostnameVerifier;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class aw {

    /* renamed from: a, reason: collision with root package name */
    private static HostnameVerifier f2071a = new ay();

    /* renamed from: b, reason: collision with root package name */
    private String f2072b;
    private int d;
    private int e;
    private byte[] f;
    private HandlerThread m;
    private Handler n;

    /* renamed from: c, reason: collision with root package name */
    public String f2073c = "";
    private int g = -1;
    private byte[] h = null;
    private int i = NetCallback.ERROR_CODE_REQUEST_SUCCESS;
    private int j = 1;
    private int k = 0;
    private boolean l = false;
    private HttpURLConnection o = null;

    class a implements Handler.Callback {
        a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (aw.this.k >= aw.this.j) {
                aw.this.l = false;
                aw.this.k = 0;
                aw.this.m.quit();
                return false;
            }
            if (!aw.this.a()) {
                aw.this.n.sendEmptyMessageDelayed(1, aw.this.i * 1);
                aw.g(aw.this);
                return false;
            }
            aw.this.l = false;
            aw.this.k = 0;
            aw.this.m.quit();
            return true;
        }
    }

    public aw(Context context, String str) {
        this.f2072b = (str == null || str.length() <= 0) ? "" : str;
    }

    private void a(int i) throws ProtocolException {
        String str;
        switch (i) {
            case 1:
                str = "POST";
                break;
            case 2:
            default:
                str = "GET";
                break;
            case 3:
                str = Request.Method.PUT;
                break;
            case 4:
                str = Request.Method.DELETE;
                break;
            case 5:
                str = Request.Method.HEAD;
                break;
            case 6:
                str = "TRACE";
                break;
        }
        this.o.setRequestMethod(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0080 A[Catch: all -> 0x0088, PHI: r2
  0x0080: PHI (r2v16 java.io.InputStream) = (r2v15 java.io.InputStream), (r2v21 java.io.InputStream) binds: [B:19:0x007e, B:16:0x007a] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TRY_LEAVE, TryCatch #1 {all -> 0x0088, blocks: (B:3:0x0002, B:5:0x0013, B:7:0x0021, B:8:0x002a, B:10:0x0046, B:12:0x004b, B:13:0x0061, B:20:0x0080), top: B:40:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0091 A[DONT_GENERATE, PHI: r0 r2
  0x0091: PHI (r0v4 boolean) = (r0v3 boolean), (r0v0 boolean) binds: [B:27:0x008f, B:22:0x0085] A[DONT_GENERATE, DONT_INLINE]
  0x0091: PHI (r2v2 java.net.HttpURLConnection) = (r2v1 java.net.HttpURLConnection), (r2v17 java.net.HttpURLConnection) binds: [B:27:0x008f, B:22:0x0085] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a() {
        /*
            r4 = this;
            r0 = 1
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch: java.lang.Throwable -> L88
            java.lang.String r3 = r4.f2073c     // Catch: java.lang.Throwable -> L88
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L88
            java.net.URLConnection r3 = r2.openConnection()     // Catch: java.lang.Throwable -> L88
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3     // Catch: java.lang.Throwable -> L88
            r4.o = r3     // Catch: java.lang.Throwable -> L88
            java.lang.String r3 = "https"
            java.lang.String r2 = r2.getProtocol()     // Catch: java.lang.Throwable -> L88
            java.lang.String r2 = r2.toLowerCase()     // Catch: java.lang.Throwable -> L88
            boolean r2 = r3.equals(r2)     // Catch: java.lang.Throwable -> L88
            if (r2 == 0) goto L2a
            java.net.HttpURLConnection r2 = r4.o     // Catch: java.lang.Throwable -> L88
            javax.net.ssl.HttpsURLConnection r2 = (javax.net.ssl.HttpsURLConnection) r2     // Catch: java.lang.Throwable -> L88
            javax.net.ssl.HostnameVerifier r3 = c.a.a.a.a.aw.f2071a     // Catch: java.lang.Throwable -> L88
            r2.setHostnameVerifier(r3)     // Catch: java.lang.Throwable -> L88
        L2a:
            java.net.HttpURLConnection r2 = r4.o     // Catch: java.lang.Throwable -> L88
            int r3 = r4.i     // Catch: java.lang.Throwable -> L88
            r2.setConnectTimeout(r3)     // Catch: java.lang.Throwable -> L88
            java.net.HttpURLConnection r2 = r4.o     // Catch: java.lang.Throwable -> L88
            int r3 = r4.i     // Catch: java.lang.Throwable -> L88
            r2.setReadTimeout(r3)     // Catch: java.lang.Throwable -> L88
            int r2 = r4.d     // Catch: java.lang.Throwable -> L88
            r4.a(r2)     // Catch: java.lang.Throwable -> L88
            int r2 = r4.e     // Catch: java.lang.Throwable -> L88
            r4.b(r2)     // Catch: java.lang.Throwable -> L88
            byte[] r2 = r4.f     // Catch: java.lang.Throwable -> L88
            if (r2 == 0) goto L61
            byte[] r2 = r4.f     // Catch: java.lang.Throwable -> L88
            int r2 = r2.length     // Catch: java.lang.Throwable -> L88
            if (r2 <= 0) goto L61
            java.net.HttpURLConnection r2 = r4.o     // Catch: java.lang.Throwable -> L88
            r2.setDoOutput(r0)     // Catch: java.lang.Throwable -> L88
            java.net.HttpURLConnection r2 = r4.o     // Catch: java.lang.Throwable -> L88
            java.io.OutputStream r2 = r2.getOutputStream()     // Catch: java.lang.Throwable -> L88
            byte[] r3 = r4.f     // Catch: java.lang.Throwable -> L88
            r2.write(r3)     // Catch: java.lang.Throwable -> L88
            r2.flush()     // Catch: java.lang.Throwable -> L88
            r2.close()     // Catch: java.lang.Throwable -> L88
        L61:
            java.net.HttpURLConnection r2 = r4.o     // Catch: java.lang.Throwable -> L88
            r2.connect()     // Catch: java.lang.Throwable -> L88
            java.net.HttpURLConnection r2 = r4.o     // Catch: java.lang.Throwable -> L7d
            int r2 = r2.getResponseCode()     // Catch: java.lang.Throwable -> L7d
            r4.g = r2     // Catch: java.lang.Throwable -> L7d
            java.net.HttpURLConnection r2 = r4.o     // Catch: java.lang.Throwable -> L7d
            java.io.InputStream r2 = r2.getInputStream()     // Catch: java.lang.Throwable -> L7d
            byte[] r3 = r4.a(r2)     // Catch: java.lang.Throwable -> L7e
            r4.h = r3     // Catch: java.lang.Throwable -> L7e
            if (r2 == 0) goto L83
            goto L80
        L7d:
            r2 = r1
        L7e:
            if (r2 == 0) goto L83
        L80:
            r2.close()     // Catch: java.lang.Throwable -> L88
        L83:
            java.net.HttpURLConnection r2 = r4.o
            if (r2 == 0) goto L96
            goto L91
        L88:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> La0
            r0 = 0
            java.net.HttpURLConnection r2 = r4.o
            if (r2 == 0) goto L96
        L91:
            r2.disconnect()
            r4.o = r1
        L96:
            if (r0 == 0) goto L9f
            int r1 = r4.g
            byte[] r2 = r4.h
            r4.a(r1, r2)
        L9f:
            return r0
        La0:
            r0 = move-exception
            java.net.HttpURLConnection r2 = r4.o
            if (r2 == 0) goto Laa
            r2.disconnect()
            r4.o = r1
        Laa:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.a.a.aw.a():boolean");
    }

    private byte[] a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr, 0, 1024);
            if (i <= 0) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
    }

    private void b(int i) {
        HttpURLConnection httpURLConnection;
        String str;
        StringBuilder sb;
        String str2;
        String str3 = i != 1 ? i != 2 ? "" : a.f.f5151a : com.bytedance.b.a.a.c.e.f3610c;
        if (str3.length() > 0) {
            this.o.addRequestProperty("Content-Type", str3);
        }
        this.o.addRequestProperty(HttpConstant.COOKIE, "sessionid=" + this.f2072b);
        try {
            String language = Locale.getDefault().getLanguage();
            if (language.equalsIgnoreCase("zh")) {
                httpURLConnection = this.o;
                str = "Accept-Language";
                sb = new StringBuilder();
                sb.append(Locale.getDefault().toString());
                sb.append(IWeiboService.Scope.EMPTY_SCOPE);
                sb.append(language);
                str2 = ";q=0.9";
            } else {
                httpURLConnection = this.o;
                str = "Accept-Language";
                sb = new StringBuilder();
                sb.append(Locale.getDefault().toString());
                sb.append(IWeiboService.Scope.EMPTY_SCOPE);
                sb.append(language);
                str2 = ";q=0.9,en-US;q=0.6,en;q=0.4";
            }
            sb.append(str2);
            httpURLConnection.addRequestProperty(str, sb.toString());
        } catch (Throwable unused) {
        }
    }

    private void b(int i, int i2, byte[] bArr) {
        this.d = i;
        this.e = i2;
        this.f = bArr;
    }

    static /* synthetic */ int g(aw awVar) {
        int i = awVar.k;
        awVar.k = i + 1;
        return i;
    }

    public synchronized void a(int i, int i2, byte[] bArr) {
        if (this.l) {
            return;
        }
        this.j = 1;
        b(i, i2, bArr);
        this.l = true;
        this.k = 0;
        HandlerThread handlerThread = new HandlerThread(TTLogUtil.TAG_EVENT_REQUEST);
        this.m = handlerThread;
        handlerThread.start();
        Handler handler = new Handler(this.m.getLooper(), new a());
        this.n = handler;
        handler.sendEmptyMessage(1);
    }

    public abstract boolean a(int i, byte[] bArr);
}
