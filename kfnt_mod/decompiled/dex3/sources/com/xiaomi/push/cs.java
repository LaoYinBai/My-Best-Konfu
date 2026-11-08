package com.xiaomi.push;

import com.xiaomi.mipush.sdk.Constants;
import java.net.InetSocketAddress;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class cs {
    private int a;

    /* renamed from: a, reason: collision with other field name */
    private String f202a;

    public cs(String str, int i) {
        this.f202a = str;
        this.a = i;
    }

    public static cs a(String str, int i) throws NumberFormatException {
        String strSubstring;
        int iLastIndexOf = str.lastIndexOf(Constants.COLON_SEPARATOR);
        if (iLastIndexOf != -1) {
            strSubstring = str.substring(0, iLastIndexOf);
            try {
                int i2 = Integer.parseInt(str.substring(iLastIndexOf + 1));
                if (i2 > 0) {
                    i = i2;
                }
            } catch (NumberFormatException unused) {
            }
        } else {
            strSubstring = str;
        }
        return new cs(strSubstring, i);
    }

    /* renamed from: a, reason: collision with other method in class */
    public static InetSocketAddress m180a(String str, int i) throws NumberFormatException {
        cs csVarA = a(str, i);
        return new InetSocketAddress(csVarA.m181a(), csVarA.a());
    }

    public int a() {
        return this.a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public String m181a() {
        return this.f202a;
    }

    public String toString() {
        if (this.a <= 0) {
            return this.f202a;
        }
        return this.f202a + Constants.COLON_SEPARATOR + this.a;
    }
}
