package com.baidu.mobads.sdk.internal;

import android.content.Context;
import com.baidu.mobads.sdk.internal.p;
import com.bytedance.sdk.adnet.game.http.cb.NetCallback;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Observable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ad extends Observable implements p, Runnable {
    protected static final int i = 10240;
    protected static final int j = 10240;
    public static final String k = ".tmp";
    private static final String m = "FileDownloader";

    /* renamed from: a, reason: collision with root package name */
    protected Context f3094a;

    /* renamed from: b, reason: collision with root package name */
    protected URL f3095b;

    /* renamed from: c, reason: collision with root package name */
    protected String f3096c;
    protected String d;
    protected int e;
    protected p.a f;
    protected int g;
    protected int h;
    protected byte[] l;
    private boolean n;

    @Override // com.baidu.mobads.sdk.internal.p
    public void a(boolean z) {
    }

    @Override // com.baidu.mobads.sdk.internal.p
    @Deprecated
    public void b() {
    }

    @Override // com.baidu.mobads.sdk.internal.p
    @Deprecated
    public void c() {
    }

    @Override // com.baidu.mobads.sdk.internal.p
    @Deprecated
    public void d() {
    }

    @Override // com.baidu.mobads.sdk.internal.p
    @Deprecated
    public String f() {
        return null;
    }

    @Override // com.baidu.mobads.sdk.internal.p
    @Deprecated
    public String h() {
        return null;
    }

    @Override // com.baidu.mobads.sdk.internal.p
    @Deprecated
    public String i() {
        return null;
    }

    @Override // com.baidu.mobads.sdk.internal.p
    public void m() {
    }

    @Override // com.baidu.mobads.sdk.internal.p
    public boolean n() {
        return false;
    }

    public byte[] o() {
        return this.l;
    }

    public ad(Context context, URL url, String str, String str2, boolean z) {
        this.n = false;
        this.f3094a = context;
        this.f3095b = url;
        this.f3096c = str;
        this.n = z;
        if (str2 != null && str2.trim().length() > 0) {
            this.d = str2;
        } else {
            String file = url.getFile();
            this.d = file.substring(file.lastIndexOf(47) + 1);
        }
        this.e = -1;
        this.f = p.a.DOWNLOADING;
        this.g = 0;
        this.h = 0;
    }

    @Override // com.baidu.mobads.sdk.internal.p
    public void a() {
        a(p.a.DOWNLOADING);
        p();
    }

    @Override // com.baidu.mobads.sdk.internal.p
    public String e() {
        return this.f3095b.toString();
    }

    @Override // com.baidu.mobads.sdk.internal.p
    public int j() {
        return this.e;
    }

    @Override // com.baidu.mobads.sdk.internal.p
    public float k() {
        return Math.abs((this.g / this.e) * 100.0f);
    }

    @Override // com.baidu.mobads.sdk.internal.p
    public p.a l() {
        return this.f;
    }

    protected void a(p.a aVar) {
        this.f = aVar;
        q();
    }

    protected void p() {
        am.a().a(this);
    }

    protected void a(int i2, float f) {
        this.g += i2;
        q();
    }

    protected void q() {
        setChanged();
        notifyObservers();
    }

    @Override // com.baidu.mobads.sdk.internal.p
    public String g() {
        return this.f3096c + this.d;
    }

    private void s() {
        a(p.a.ERROR);
    }

    /* JADX WARN: Removed duplicated region for block: B:118:0x0187 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:122:0x016d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:124:0x01f8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01c4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01a1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:134:0x01de A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 536
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobads.sdk.internal.ad.run():void");
    }

    private HttpURLConnection a(HttpURLConnection httpURLConnection) throws IOException {
        while (true) {
            try {
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode != 302 && responseCode != 301) {
                    return httpURLConnection;
                }
                this.f3095b = new URL(httpURLConnection.getHeaderField("Location"));
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) this.f3095b.openConnection();
                try {
                    httpURLConnection2.setConnectTimeout(NetCallback.ERROR_CODE_REQUEST_SUCCESS);
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

    protected void r() {
        az.a(this.f3096c + this.d + k, this.f3096c + this.d);
    }
}
