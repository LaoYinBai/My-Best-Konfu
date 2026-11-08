package com.baidu.mobads.sdk.internal;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import anet.channel.util.HttpConstant;
import com.bytedance.sdk.adnet.game.http.cb.NetCallback;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ae {

    /* renamed from: a, reason: collision with root package name */
    public static final String f3097a = "OAdURLConnection";

    /* renamed from: b, reason: collision with root package name */
    public static final String f3098b = "POST";

    /* renamed from: c, reason: collision with root package name */
    public static final String f3099c = "GET";
    public static final String d = "application/json";
    public static final String e = "text/plain";
    private HttpURLConnection f;
    private bb g;
    private b h;
    private c i;
    private String j;
    private String k;
    private String l;
    private String m;
    private int n;
    private int o;
    private boolean p;
    private Uri.Builder q;

    public interface b {
        void a(String str, int i);

        void a(String str, String str2);
    }

    public interface c {
        void a(InputStream inputStream, String str);

        void a(String str, int i);
    }

    public ae(String str) {
        this(str, "GET");
    }

    public ae(String str, String str2) {
        this.g = bb.a();
        this.h = null;
        this.i = null;
        this.m = e;
        this.n = NetCallback.ERROR_CODE_REQUEST_SUCCESS;
        this.o = NetCallback.ERROR_CODE_REQUEST_SUCCESS;
        this.p = false;
        this.q = null;
        this.j = str;
        this.k = str2;
    }

    public String a() throws Throwable {
        e();
        if (this.f == null) {
            return null;
        }
        try {
            if (this.f.getResponseCode() / 100 != 2) {
                if (this.f != null) {
                    this.f.disconnect();
                }
                return null;
            }
            String strC = c();
            if (this.f != null) {
                this.f.disconnect();
            }
            return strC;
        } catch (Throwable unused) {
            if (this.f != null) {
                this.f.disconnect();
            }
            return null;
        }
    }

    public void b() {
        try {
            am.a().a((h) new a());
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() throws Throwable {
        if (TextUtils.isEmpty(this.j)) {
            return;
        }
        try {
            this.f = by.a().a(new URL(this.j));
            this.f.setConnectTimeout(this.n);
            if (Integer.parseInt(av.a((Context) null).c()) < 8) {
                System.setProperty("http.keepAlive", "false");
            }
            this.f.setRequestMethod(this.k);
            this.f.setUseCaches(this.p);
            if (!TextUtils.isEmpty(this.l)) {
                this.f.setRequestProperty("User-Agent", this.l);
            }
            this.f.setRequestProperty("Content-type", this.m);
            this.f.setRequestProperty("Connection", "keep-alive");
            this.f.setRequestProperty(HttpConstant.CACHE_CONTROL, "no-cache");
            if (this.k.equals("POST")) {
                this.f.setDoInput(true);
                this.f.setDoOutput(true);
                if (this.q != null) {
                    a(this.q.build().getEncodedQuery(), this.f);
                }
            }
        } catch (Exception e2) {
            if (this.h != null) {
                this.h.a("Net Create RuntimeError: " + e2.toString(), 0);
            }
            if (this.i != null) {
                this.i.a("Net Create RuntimeError: " + e2.toString(), 0);
            }
        }
    }

    private void a(String str, HttpURLConnection httpURLConnection) throws Throwable {
        OutputStream outputStream;
        BufferedWriter bufferedWriter = null;
        try {
            outputStream = httpURLConnection.getOutputStream();
            try {
                BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                try {
                    bufferedWriter2.write(str);
                    bufferedWriter2.flush();
                    bufferedWriter2.close();
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedWriter = bufferedWriter2;
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            outputStream = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        try {
            try {
                this.f.connect();
                this.g.a(f3097a, this.f.getRequestMethod() + " connect code :" + this.f.getResponseCode());
                int responseCode = this.f.getResponseCode();
                if (responseCode == 302 || responseCode == 301) {
                    this.f.setInstanceFollowRedirects(false);
                    this.f = a(this.f);
                    responseCode = this.f.getResponseCode();
                }
                if (responseCode / 100 != 2) {
                    if (this.h != null) {
                        this.h.a(this.f.getResponseMessage(), responseCode);
                    }
                    if (this.i != null) {
                        this.i.a(this.f.getResponseMessage(), responseCode);
                    }
                } else {
                    String strF = by.a().f(this.j);
                    if (this.h != null) {
                        this.h.a(c(), strF);
                    }
                    if (this.i != null) {
                        this.i.a(this.f.getInputStream(), strF);
                    }
                }
                if (this.f == null) {
                    return;
                }
            } catch (Exception e2) {
                if (this.h != null) {
                    this.h.a("Net Connect RuntimeError: " + e2.toString(), 0);
                }
                if (this.i != null) {
                    this.i.a("Net Connect RuntimeError: " + e2.toString(), 0);
                }
                if (this.f == null) {
                    return;
                }
            }
            this.f.disconnect();
        } catch (Throwable th) {
            if (this.f != null) {
                this.f.disconnect();
            }
            throw th;
        }
    }

    public String c() throws Throwable {
        InputStream inputStream;
        Throwable th;
        try {
            inputStream = this.f.getInputStream();
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[128];
                while (true) {
                    int i = inputStream.read(bArr);
                    if (i == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i);
                }
                byteArrayOutputStream.flush();
                String string = byteArrayOutputStream.toString();
                if (inputStream != null) {
                    inputStream.close();
                }
                return string;
            } catch (Throwable th2) {
                th = th2;
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            inputStream = null;
            th = th3;
        }
    }

    public void a(Map<String, String> map) {
        if (this.f != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                this.f.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
    }

    private HttpURLConnection a(HttpURLConnection httpURLConnection) throws IOException {
        while (true) {
            try {
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode != 302 && responseCode != 301) {
                    return httpURLConnection;
                }
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(httpURLConnection.getHeaderField("Location")).openConnection();
                try {
                    httpURLConnection2.setConnectTimeout(httpURLConnection2.getConnectTimeout());
                    httpURLConnection2.setInstanceFollowRedirects(false);
                    httpURLConnection2.setRequestProperty("Range", "bytes=0-");
                    httpURLConnection = httpURLConnection2;
                } catch (Exception unused) {
                    return httpURLConnection2;
                }
            } catch (Exception unused2) {
                return httpURLConnection;
            }
        }
    }

    public void d() throws IOException {
        if (this.f != null) {
            try {
                InputStream inputStream = this.f.getInputStream();
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e2) {
                Log.e(f3097a, e2.toString());
            }
        }
    }

    public void a(b bVar) {
        this.h = bVar;
    }

    public void a(c cVar) {
        this.i = cVar;
    }

    public void a(int i) {
        this.n = i;
    }

    public void b(int i) {
        this.o = i;
    }

    public void a(Uri.Builder builder) {
        this.q = builder;
    }

    public void a(String str) {
        this.m = str;
    }

    class a extends h {
        a() {
        }

        @Override // com.baidu.mobads.sdk.internal.h
        public Object i() throws Throwable {
            ae.this.e();
            ae.this.f();
            return null;
        }
    }
}
