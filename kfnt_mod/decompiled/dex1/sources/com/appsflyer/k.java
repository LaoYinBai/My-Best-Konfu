package com.appsflyer;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class k {

    /* renamed from: ˋ, reason: contains not printable characters */
    private boolean f228;

    /* renamed from: ˏ, reason: contains not printable characters */
    private b f229;

    /* renamed from: ॱ, reason: contains not printable characters */
    private String f230;

    k(b bVar, String str, boolean z) {
        this.f229 = bVar;
        this.f230 = str;
        this.f228 = z;
    }

    /* renamed from: ॱ, reason: contains not printable characters */
    final String m185() {
        return this.f230;
    }

    /* renamed from: ˏ, reason: contains not printable characters */
    final boolean m184() {
        return this.f228;
    }

    public final String toString() {
        return String.format("%s,%s", this.f230, Boolean.valueOf(this.f228));
    }

    enum b {
        GOOGLE(0),
        AMAZON(1);


        /* renamed from: ˋ, reason: contains not printable characters */
        private int f234;

        b(int i) {
            this.f234 = i;
        }

        @Override // java.lang.Enum
        public final String toString() {
            return String.valueOf(this.f234);
        }
    }
}
