package com.vivo.push.model;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class a {
    public String a;
    public String b;

    public a(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public final int hashCode() {
        return (this.a == null ? 0 : this.a.hashCode()) + 31;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        if (this.a == null) {
            if (aVar.a != null) {
                return false;
            }
        } else if (!this.a.equals(aVar.a)) {
            return false;
        }
        return true;
    }

    public final String toString() {
        return "ConfigItem{mKey='" + this.a + "', mValue='" + this.b + "'}";
    }
}
