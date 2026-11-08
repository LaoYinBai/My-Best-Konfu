package com.bytedance.sdk.adnet.game.http.cookie;

import com.bytedance.sdk.adnet.game.ok3.okhttp3.Cookie;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    public String f5037a;

    /* renamed from: b, reason: collision with root package name */
    public String f5038b;

    /* renamed from: c, reason: collision with root package name */
    public String f5039c;
    private transient Cookie d;
    private transient Cookie e;

    public c(String str, Cookie cookie) {
        this.d = cookie;
        this.f5037a = str;
        this.f5038b = cookie.name();
        this.f5039c = cookie.domain();
    }

    public static Cookie a(String str) {
        return a(b(str));
    }

    public static Cookie a(byte[] bArr) {
        try {
            return ((c) new ObjectInputStream(new ByteArrayInputStream(bArr)).readObject()).a();
        } catch (Exception unused) {
            return null;
        }
    }

    public static String a(String str, Cookie cookie) {
        if (cookie == null) {
            return null;
        }
        return b(b(str, cookie));
    }

    private static String b(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b2 : bArr) {
            int i = b2 & 255;
            if (i < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(i));
        }
        return sb.toString().toUpperCase(Locale.US);
    }

    private static byte[] b(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public static byte[] b(String str, Cookie cookie) throws IOException {
        c cVar = new c(str, cookie);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(byteArrayOutputStream).writeObject(cVar);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            return null;
        }
    }

    public Cookie a() {
        return this.e != null ? this.e : this.d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        c cVar = (c) obj;
        if (this.f5037a == null ? cVar.f5037a != null : !this.f5037a.equals(cVar.f5037a)) {
            return false;
        }
        if (this.f5038b == null ? cVar.f5038b == null : this.f5038b.equals(cVar.f5038b)) {
            return this.f5039c != null ? this.f5039c.equals(cVar.f5039c) : cVar.f5039c == null;
        }
        return false;
    }

    public int hashCode() {
        return ((((this.f5037a != null ? this.f5037a.hashCode() : 0) * 31) + (this.f5038b != null ? this.f5038b.hashCode() : 0)) * 31) + (this.f5039c != null ? this.f5039c.hashCode() : 0);
    }
}
