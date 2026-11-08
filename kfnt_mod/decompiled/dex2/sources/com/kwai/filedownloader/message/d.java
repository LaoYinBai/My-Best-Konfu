package com.kwai.filedownloader.message;

import android.os.Parcel;
import com.google.android.gms.common.api.Api;
import com.kwai.filedownloader.message.MessageSnapshot;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class d extends MessageSnapshot {

    public static class a extends b implements com.kwai.filedownloader.message.b {
        a(int i, boolean z, long j) {
            super(i, z, j);
        }
    }

    public static class b extends d {
        private final boolean b;
        private final long c;

        b(int i, boolean z, long j) {
            super(i);
            this.b = z;
            this.c = j;
        }

        b(Parcel parcel) {
            super(parcel);
            this.b = parcel.readByte() != 0;
            this.c = parcel.readLong();
        }

        @Override // com.kwai.filedownloader.message.c
        public byte b() {
            return (byte) -3;
        }

        @Override // com.kwai.filedownloader.message.MessageSnapshot
        public long d() {
            return this.c;
        }

        @Override // com.kwai.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // com.kwai.filedownloader.message.MessageSnapshot
        public boolean e() {
            return this.b;
        }

        @Override // com.kwai.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte(this.b ? (byte) 1 : (byte) 0);
            parcel.writeLong(this.c);
        }
    }

    public static class c extends d {
        private final boolean b;
        private final long c;
        private final String d;
        private final String e;

        c(int i, boolean z, long j, String str, String str2) {
            super(i);
            this.b = z;
            this.c = j;
            this.d = str;
            this.e = str2;
        }

        c(Parcel parcel) {
            super(parcel);
            this.b = parcel.readByte() != 0;
            this.c = parcel.readLong();
            this.d = parcel.readString();
            this.e = parcel.readString();
        }

        @Override // com.kwai.filedownloader.message.c
        public byte b() {
            return (byte) 2;
        }

        @Override // com.kwai.filedownloader.message.MessageSnapshot
        public long d() {
            return this.c;
        }

        @Override // com.kwai.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // com.kwai.filedownloader.message.MessageSnapshot
        public String f() {
            return this.e;
        }

        @Override // com.kwai.filedownloader.message.MessageSnapshot
        public boolean g() {
            return this.b;
        }

        @Override // com.kwai.filedownloader.message.MessageSnapshot
        public String h() {
            return this.d;
        }

        @Override // com.kwai.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte(this.b ? (byte) 1 : (byte) 0);
            parcel.writeLong(this.c);
            parcel.writeString(this.d);
            parcel.writeString(this.e);
        }
    }

    /* renamed from: com.kwai.filedownloader.message.d$d, reason: collision with other inner class name */
    public static class C0097d extends d {
        private final long b;
        private final Throwable c;

        C0097d(int i, long j, Throwable th) {
            super(i);
            this.b = j;
            this.c = th;
        }

        C0097d(Parcel parcel) {
            super(parcel);
            this.b = parcel.readLong();
            this.c = (Throwable) parcel.readSerializable();
        }

        @Override // com.kwai.filedownloader.message.c
        public byte b() {
            return (byte) -1;
        }

        @Override // com.kwai.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // com.kwai.filedownloader.message.MessageSnapshot
        public long i() {
            return this.b;
        }

        @Override // com.kwai.filedownloader.message.MessageSnapshot
        public Throwable j() {
            return this.c;
        }

        @Override // com.kwai.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeLong(this.b);
            parcel.writeSerializable(this.c);
        }
    }

    public static class e extends f {
        e(int i, long j, long j2) {
            super(i, j, j2);
        }

        @Override // com.kwai.filedownloader.message.d.f, com.kwai.filedownloader.message.c
        public byte b() {
            return (byte) -2;
        }
    }

    public static class f extends d {
        private final long b;
        private final long c;

        f(int i, long j, long j2) {
            super(i);
            this.b = j;
            this.c = j2;
        }

        f(Parcel parcel) {
            super(parcel);
            this.b = parcel.readLong();
            this.c = parcel.readLong();
        }

        f(f fVar) {
            this(fVar.m(), fVar.i(), fVar.d());
        }

        public byte b() {
            return (byte) 1;
        }

        @Override // com.kwai.filedownloader.message.MessageSnapshot
        public long d() {
            return this.c;
        }

        @Override // com.kwai.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // com.kwai.filedownloader.message.MessageSnapshot
        public long i() {
            return this.b;
        }

        @Override // com.kwai.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeLong(this.b);
            parcel.writeLong(this.c);
        }
    }

    public static class g extends d {
        private final long b;

        g(int i, long j) {
            super(i);
            this.b = j;
        }

        g(Parcel parcel) {
            super(parcel);
            this.b = parcel.readLong();
        }

        @Override // com.kwai.filedownloader.message.c
        public byte b() {
            return (byte) 3;
        }

        @Override // com.kwai.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // com.kwai.filedownloader.message.MessageSnapshot
        public long i() {
            return this.b;
        }

        @Override // com.kwai.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeLong(this.b);
        }
    }

    public static class h extends C0097d {
        private final int b;

        h(int i, long j, Throwable th, int i2) {
            super(i, j, th);
            this.b = i2;
        }

        h(Parcel parcel) {
            super(parcel);
            this.b = parcel.readInt();
        }

        @Override // com.kwai.filedownloader.message.d.C0097d, com.kwai.filedownloader.message.c
        public byte b() {
            return (byte) 5;
        }

        @Override // com.kwai.filedownloader.message.d.C0097d, com.kwai.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // com.kwai.filedownloader.message.MessageSnapshot
        public int k() {
            return this.b;
        }

        @Override // com.kwai.filedownloader.message.d.C0097d, com.kwai.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.b);
        }
    }

    public static class i extends j implements com.kwai.filedownloader.message.b {
        i(int i, long j, long j2) {
            super(i, j, j2);
        }
    }

    public static class j extends f implements MessageSnapshot.a {
        j(int i, long j, long j2) {
            super(i, j, j2);
        }

        j(Parcel parcel) {
            super(parcel);
        }

        @Override // com.kwai.filedownloader.message.d.f, com.kwai.filedownloader.message.c
        public byte b() {
            return (byte) -4;
        }

        @Override // com.kwai.filedownloader.message.MessageSnapshot.a
        public MessageSnapshot l() {
            return new f(this);
        }
    }

    d(int i2) {
        super(i2);
        this.a = true;
    }

    d(Parcel parcel) {
        super(parcel);
    }

    @Override // com.kwai.filedownloader.message.MessageSnapshot
    public int a() {
        return i() > 2147483647L ? Api.BaseClientBuilder.API_PRIORITY_OTHER : (int) i();
    }

    @Override // com.kwai.filedownloader.message.MessageSnapshot
    public int c() {
        return d() > 2147483647L ? Api.BaseClientBuilder.API_PRIORITY_OTHER : (int) d();
    }
}
