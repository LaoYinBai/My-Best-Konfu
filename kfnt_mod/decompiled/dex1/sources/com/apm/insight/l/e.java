package com.apm.insight.l;

import androidx.annotation.NonNull;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e extends PrintWriter {

    /* renamed from: a, reason: collision with root package name */
    private MessageDigest f2952a;

    /* renamed from: b, reason: collision with root package name */
    private Charset f2953b;

    /* renamed from: c, reason: collision with root package name */
    private a f2954c;

    public static class a {
        public boolean a(String str) {
            return true;
        }
    }

    public e(@NonNull OutputStream outputStream, MessageDigest messageDigest, a aVar) {
        super(outputStream);
        this.f2952a = null;
        this.f2953b = null;
        this.f2954c = null;
        this.f2952a = messageDigest;
        this.f2954c = aVar;
        if (messageDigest != null) {
            this.f2953b = Charset.defaultCharset();
        }
    }

    @Override // java.io.PrintWriter, java.io.Writer
    public void write(int i) {
        super.write(i);
        if (this.f2952a != null) {
            this.f2952a.update((byte) i);
        }
    }

    @Override // java.io.PrintWriter, java.io.Writer
    public void write(@NonNull String str, int i, int i2) {
        super.write(str, i, i2);
        if (this.f2952a != null) {
            if (this.f2954c == null || this.f2954c.a(str)) {
                this.f2952a.update(this.f2953b.encode(CharBuffer.wrap(str, i, i2 + i)).array());
            }
        }
    }

    @Override // java.io.PrintWriter, java.io.Writer
    public void write(@NonNull char[] cArr, int i, int i2) {
        super.write(cArr, i, i2);
        if (this.f2952a != null) {
            this.f2952a.update(this.f2953b.encode(CharBuffer.wrap(cArr)).array());
        }
    }
}
