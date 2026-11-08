package com.umeng.commonsdk.proguard;

import com.umeng.commonsdk.proguard.am;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ac {
    private final ByteArrayOutputStream a;
    private final be b;
    private as c;

    public ac() {
        this(new am.a());
    }

    public ac(au auVar) {
        this.a = new ByteArrayOutputStream();
        this.b = new be(this.a);
        this.c = auVar.a(this.b);
    }

    public byte[] a(t tVar) throws z {
        this.a.reset();
        tVar.write(this.c);
        return this.a.toByteArray();
    }

    public String a(t tVar, String str) throws z {
        try {
            return new String(a(tVar), str);
        } catch (UnsupportedEncodingException unused) {
            throw new z("JVM DOES NOT SUPPORT ENCODING: " + str);
        }
    }

    public String b(t tVar) throws z {
        return new String(a(tVar));
    }
}
