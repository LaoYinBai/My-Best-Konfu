package com.mbridge.msdk.foundation.entity;

import java.util.Set;

/* compiled from: InstallApp.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class j {
    private String a;
    private String b;

    public j() {
    }

    public j(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public final String a() {
        return this.a;
    }

    public final void a(String str) {
        this.a = str;
    }

    public final String b() {
        return this.b;
    }

    public final void b(String str) {
        this.b = str;
    }

    public static String a(Set<j> set) {
        if (set == null) {
            return null;
        }
        try {
            if (set.size() <= 0) {
                return null;
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (j jVar : set) {
                stringBuffer.append("{\"campaignId\":");
                stringBuffer.append(jVar.a + ",");
                stringBuffer.append("\"packageName\":");
                stringBuffer.append(jVar.b + "},");
            }
            return "[{$native_info}]".replace("{$native_info}", stringBuffer.subSequence(0, stringBuffer.lastIndexOf(",")));
        } catch (Exception unused) {
            return null;
        }
    }

    public final int hashCode() {
        return (((this.a == null ? 0 : this.a.hashCode()) + 31) * 31) + (this.b != null ? this.b.hashCode() : 0);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        j jVar = (j) obj;
        if (this.a == null) {
            if (jVar.a != null) {
                return false;
            }
        } else if (!this.a.equals(jVar.a)) {
            return false;
        }
        if (this.b == null) {
            if (jVar.b != null) {
                return false;
            }
        } else if (!this.b.equals(jVar.b)) {
            return false;
        }
        return true;
    }
}
