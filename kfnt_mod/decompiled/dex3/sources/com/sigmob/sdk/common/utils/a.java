package com.sigmob.sdk.common.utils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.UUID;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a implements Serializable {
    final Calendar a = Calendar.getInstance();
    public final String b;
    final String c;
    public final boolean d;

    a(String str, String str2, boolean z, long j) {
        this.b = str;
        this.c = str2;
        this.d = z;
        this.a.setTimeInMillis(j);
    }

    public static a a() {
        return new a(null, b(), false, (Calendar.getInstance().getTimeInMillis() - 86400000) - 1);
    }

    static String b() {
        return UUID.randomUUID().toString();
    }

    boolean c() {
        return Calendar.getInstance().getTimeInMillis() - this.a.getTimeInMillis() >= 86400000;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.d == aVar.d && this.b.equals(aVar.b) && this.c.equals(aVar.c);
    }

    public int hashCode() {
        return (((this.b.hashCode() * 31) + this.c.hashCode()) * 31) + (this.d ? 1 : 0);
    }

    public String toString() {
        return "AdvertisingId{mLastRotation=" + this.a + ", mAdvertisingId='" + this.b + "', mSigmobId='" + this.c + "', mDoNotTrack=" + this.d + '}';
    }
}
