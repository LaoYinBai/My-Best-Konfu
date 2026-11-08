package com.xiaomi.push;

import android.text.TextUtils;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.push.eb;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class fg {

    /* renamed from: a, reason: collision with other field name */
    int f367a;

    /* renamed from: a, reason: collision with other field name */
    private eb.a f368a;

    /* renamed from: a, reason: collision with other field name */
    String f369a;

    /* renamed from: a, reason: collision with other field name */
    private short f370a;

    /* renamed from: b, reason: collision with other field name */
    private byte[] f371b;
    private static String b = gr.a(5) + Constants.ACCEPT_TIME_SEPARATOR_SERVER;
    private static long a = 0;

    /* renamed from: a, reason: collision with other field name */
    private static final byte[] f366a = new byte[0];

    public fg() {
        this.f370a = (short) 2;
        this.f371b = f366a;
        this.f369a = null;
        this.f368a = new eb.a();
        this.f367a = 1;
    }

    fg(eb.a aVar, short s, byte[] bArr) {
        this.f370a = (short) 2;
        this.f371b = f366a;
        this.f369a = null;
        this.f368a = aVar;
        this.f370a = s;
        this.f371b = bArr;
        this.f367a = 2;
    }

    @Deprecated
    public static fg a(gg ggVar, String str) {
        int i;
        fg fgVar = new fg();
        try {
            i = Integer.parseInt(ggVar.k());
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.m22a("Blob parse chid err " + e.getMessage());
            i = 1;
        }
        fgVar.a(i);
        fgVar.a(ggVar.j());
        fgVar.c(ggVar.m());
        fgVar.b(ggVar.n());
        fgVar.a("XMLMSG", (String) null);
        try {
            fgVar.a(ggVar.mo354a().getBytes("utf8"), str);
            if (TextUtils.isEmpty(str)) {
                fgVar.a((short) 3);
            } else {
                fgVar.a((short) 2);
                fgVar.a("SECMSG", (String) null);
            }
        } catch (UnsupportedEncodingException e2) {
            com.xiaomi.channel.commonutils.logger.b.m22a("Blob setPayload err： " + e2.getMessage());
        }
        return fgVar;
    }

    static fg a(ByteBuffer byteBuffer) throws IOException {
        try {
            ByteBuffer byteBufferSlice = byteBuffer.slice();
            short s = byteBufferSlice.getShort(0);
            short s2 = byteBufferSlice.getShort(2);
            int i = byteBufferSlice.getInt(4);
            eb.a aVar = new eb.a();
            aVar.a(byteBufferSlice.array(), byteBufferSlice.arrayOffset() + 8, (int) s2);
            byte[] bArr = new byte[i];
            byteBufferSlice.position(s2 + 8);
            byteBufferSlice.get(bArr, 0, i);
            return new fg(aVar, s, bArr);
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.m22a("read Blob err :" + e.getMessage());
            throw new IOException("Malformed Input");
        }
    }

    public static synchronized String d() {
        StringBuilder sb;
        sb = new StringBuilder();
        sb.append(b);
        long j = a;
        a = 1 + j;
        sb.append(Long.toString(j));
        return sb.toString();
    }

    public int a() {
        return this.f368a.c();
    }

    /* renamed from: a, reason: collision with other method in class */
    public String m312a() {
        return this.f368a.m223c();
    }

    /* renamed from: a, reason: collision with other method in class */
    ByteBuffer mo313a(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            byteBuffer = ByteBuffer.allocate(c());
        }
        byteBuffer.putShort(this.f370a);
        byteBuffer.putShort((short) this.f368a.a());
        byteBuffer.putInt(this.f371b.length);
        int iPosition = byteBuffer.position();
        this.f368a.a(byteBuffer.array(), byteBuffer.arrayOffset() + iPosition, this.f368a.a());
        byteBuffer.position(iPosition + this.f368a.a());
        byteBuffer.put(this.f371b);
        return byteBuffer;
    }

    /* renamed from: a, reason: collision with other method in class */
    public short m314a() {
        return this.f370a;
    }

    public void a(int i) {
        this.f368a.a(i);
    }

    public void a(long j, String str, String str2) {
        if (j != 0) {
            this.f368a.a(j);
        }
        if (!TextUtils.isEmpty(str)) {
            this.f368a.a(str);
        }
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        this.f368a.b(str2);
    }

    public void a(String str) {
        this.f368a.e(str);
    }

    public void a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("command should not be empty");
        }
        this.f368a.c(str);
        this.f368a.m218a();
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        this.f368a.d(str2);
    }

    public void a(short s) {
        this.f370a = s;
    }

    public void a(byte[] bArr, String str) {
        if (TextUtils.isEmpty(str)) {
            this.f368a.c(0);
        } else {
            this.f368a.c(1);
            bArr = com.xiaomi.push.service.be.a(com.xiaomi.push.service.be.a(str, e()), bArr);
        }
        this.f371b = bArr;
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m315a() {
        return this.f368a.j();
    }

    /* renamed from: a, reason: collision with other method in class */
    public byte[] m316a() {
        return fh.a(this, this.f371b);
    }

    /* renamed from: a, reason: collision with other method in class */
    public byte[] m317a(String str) {
        byte[] bArrA;
        if (this.f368a.e() == 1) {
            bArrA = com.xiaomi.push.service.be.a(com.xiaomi.push.service.be.a(str, e()), this.f371b);
        } else {
            if (this.f368a.e() != 0) {
                com.xiaomi.channel.commonutils.logger.b.m22a("unknow cipher = " + this.f368a.e());
            }
            bArrA = this.f371b;
        }
        return fh.a(this, bArrA);
    }

    public int b() {
        return this.f368a.f();
    }

    /* renamed from: b, reason: collision with other method in class */
    public String m318b() {
        return this.f368a.m225d();
    }

    public void b(String str) {
        this.f369a = str;
    }

    public int c() {
        return this.f368a.b() + 8 + this.f371b.length;
    }

    /* renamed from: c, reason: collision with other method in class */
    public String m319c() {
        return this.f368a.m229f();
    }

    public void c(String str) throws NumberFormatException {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        int iIndexOf = str.indexOf("@");
        try {
            long j = Long.parseLong(str.substring(0, iIndexOf));
            int iIndexOf2 = str.indexOf("/", iIndexOf);
            String strSubstring = str.substring(iIndexOf + 1, iIndexOf2);
            String strSubstring2 = str.substring(iIndexOf2 + 1);
            this.f368a.a(j);
            this.f368a.a(strSubstring);
            this.f368a.b(strSubstring2);
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.m22a("Blob parse user err " + e.getMessage());
        }
    }

    public String e() {
        String strM227e = this.f368a.m227e();
        if ("ID_NOT_AVAILABLE".equals(strM227e)) {
            return null;
        }
        if (this.f368a.g()) {
            return strM227e;
        }
        String strD = d();
        this.f368a.e(strD);
        return strD;
    }

    public String f() {
        return this.f369a;
    }

    public String g() {
        if (!this.f368a.m222b()) {
            return null;
        }
        return Long.toString(this.f368a.m217a()) + "@" + this.f368a.m219a() + "/" + this.f368a.m221b();
    }

    public String toString() {
        return "Blob [chid=" + a() + "; Id=" + e() + "; cmd=" + m312a() + "; type=" + ((int) m314a()) + "; from=" + g() + " ]";
    }
}
