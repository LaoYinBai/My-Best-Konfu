package com.sigmob.a.a;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a implements com.sigmob.a.a {
    private String a;
    private int b;
    private int c;
    private int d;
    private long e;
    private long f;
    private byte g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private int m;
    private int n;
    private boolean o;
    private long p;
    private File q;

    private a() {
        this.i = "ustar\u0000";
        this.j = "00";
        this.a = "";
        this.h = "";
        String property = System.getProperty("user.name", "");
        property = property.length() > 31 ? property.substring(0, 31) : property;
        this.c = 0;
        this.d = 0;
        this.k = property;
        this.l = "";
        this.q = null;
    }

    public a(byte[] bArr) {
        this();
        a(bArr);
    }

    private static String a(String str, boolean z) {
        int iIndexOf;
        String lowerCase = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
        if (lowerCase != null) {
            if (lowerCase.startsWith("windows")) {
                if (str.length() > 2) {
                    char cCharAt = str.charAt(0);
                    if (str.charAt(1) == ':' && ((cCharAt >= 'a' && cCharAt <= 'z') || (cCharAt >= 'A' && cCharAt <= 'Z'))) {
                        str = str.substring(2);
                    }
                }
            } else if (lowerCase.indexOf("netware") > -1 && (iIndexOf = str.indexOf(58)) != -1) {
                str = str.substring(iIndexOf + 1);
            }
        }
        String strReplace = str.replace(File.separatorChar, '/');
        while (!z && strReplace.startsWith("/")) {
            strReplace = strReplace.substring(1);
        }
        return strReplace;
    }

    private int b(byte[] bArr) {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr, 257, 6);
        if (byteBufferWrap.compareTo(ByteBuffer.wrap("ustar ".getBytes())) == 0) {
            return 2;
        }
        return byteBufferWrap.compareTo(ByteBuffer.wrap("ustar\u0000".getBytes())) == 0 ? 3 : 0;
    }

    public String a() {
        return this.a.toString();
    }

    public void a(int i) {
        this.c = i;
    }

    public void a(long j) {
        if (j <= 8589934591L && j >= 0) {
            this.e = j;
            return;
        }
        throw new IllegalArgumentException("Size is out of range: " + j);
    }

    public void a(String str) {
        this.a = a(str, false);
    }

    public void a(byte[] bArr) {
        this.a = e.b(bArr, 0, 100);
        this.b = (int) e.a(bArr, 100, 8);
        this.c = (int) e.a(bArr, 108, 8);
        this.d = (int) e.a(bArr, 116, 8);
        this.e = e.a(bArr, 124, 12);
        this.f = e.a(bArr, 136, 12);
        this.g = bArr[156];
        this.h = e.b(bArr, 157, 100);
        this.i = e.b(bArr, 257, 6);
        this.j = e.b(bArr, 263, 2);
        this.k = e.b(bArr, 265, 32);
        this.l = e.b(bArr, 297, 32);
        this.m = (int) e.a(bArr, 329, 8);
        this.n = (int) e.a(bArr, 337, 8);
        if (b(bArr) == 2) {
            this.o = e.a(bArr, 482);
            this.p = e.a(bArr, 483, 12);
            return;
        }
        String strB = e.b(bArr, 345, 155);
        if (g() && !this.a.endsWith("/")) {
            this.a += "/";
        }
        if (strB.length() > 0) {
            this.a = strB + "/" + this.a;
        }
    }

    public boolean a(a aVar) {
        return a().equals(aVar.a());
    }

    public long b() {
        return this.e;
    }

    public void b(int i) {
        this.d = i;
    }

    public void b(String str) {
        this.h = str;
    }

    public void c(String str) {
        this.k = str;
    }

    public boolean c() {
        return this.o;
    }

    public void d(String str) {
        this.l = str;
    }

    public boolean d() {
        return this.g == 83;
    }

    public boolean e() {
        return this.g == 76 && this.a.toString().equals("././@LongLink");
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return a((a) obj);
    }

    public boolean f() {
        return this.g == 120 || this.g == 88;
    }

    public boolean g() {
        if (this.q != null) {
            return this.q.isDirectory();
        }
        if (this.g == 53) {
            return true;
        }
        return a().endsWith("/");
    }

    public int hashCode() {
        return a().hashCode();
    }
}
