package com.ss.android.token;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class TTTokenHeader {
    private final String name;
    private final String value;

    public TTTokenHeader(String str, String str2) {
        this.name = str;
        this.value = str2;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TTTokenHeader tTTokenHeader = (TTTokenHeader) obj;
        if (this.name == null ? tTTokenHeader.name == null : this.name.equals(tTTokenHeader.name)) {
            return this.value == null ? tTTokenHeader.value == null : this.value.equals(tTTokenHeader.value);
        }
        return false;
    }

    public int hashCode() {
        return ((this.name != null ? this.name.hashCode() : 0) * 31) + (this.value != null ? this.value.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name != null ? this.name : "");
        sb.append(": ");
        sb.append(this.value != null ? this.value : "");
        return sb.toString();
    }
}
