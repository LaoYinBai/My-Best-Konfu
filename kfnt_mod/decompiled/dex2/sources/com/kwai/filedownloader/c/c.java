package com.kwai.filedownloader.c;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import com.kwai.filedownloader.e.f;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c implements Parcelable {
    public static final Parcelable.Creator<c> CREATOR = new Parcelable.Creator<c>() { // from class: com.kwai.filedownloader.c.c.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public c createFromParcel(Parcel parcel) {
            return new c(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public c[] newArray(int i) {
            return new c[i];
        }
    };
    private int a;
    private String b;
    private String c;
    private boolean d;
    private String e;
    private final AtomicInteger f;
    private final AtomicLong g;
    private long h;
    private String i;
    private String j;
    private int k;
    private boolean l;

    public c() {
        this.g = new AtomicLong();
        this.f = new AtomicInteger();
    }

    protected c(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readByte() != 0;
        this.e = parcel.readString();
        this.f = new AtomicInteger(parcel.readByte());
        this.g = new AtomicLong(parcel.readLong());
        this.h = parcel.readLong();
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readInt();
        this.l = parcel.readByte() != 0;
    }

    public int a() {
        return this.a;
    }

    public void a(byte b) {
        this.f.set(b);
    }

    public void a(int i) {
        this.a = i;
    }

    public void a(long j) {
        this.g.set(j);
    }

    public void a(String str) {
        this.b = str;
    }

    public void a(String str, boolean z) {
        this.c = str;
        this.d = z;
    }

    public String b() {
        return this.b;
    }

    public void b(int i) {
        this.k = i;
    }

    public void b(long j) {
        this.g.addAndGet(j);
    }

    public void b(String str) {
        this.j = str;
    }

    public String c() {
        return this.c;
    }

    public void c(long j) {
        this.l = j > 2147483647L;
        this.h = j;
    }

    public void c(String str) {
        this.i = str;
    }

    public String d() {
        return f.a(c(), l(), m());
    }

    public void d(String str) {
        this.e = str;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String e() {
        if (d() == null) {
            return null;
        }
        return f.d(d());
    }

    public byte f() {
        return (byte) this.f.get();
    }

    public long g() {
        return this.g.get();
    }

    public long h() {
        return this.h;
    }

    public boolean i() {
        return this.h == -1;
    }

    public String j() {
        return this.j;
    }

    public String k() {
        return this.i;
    }

    public boolean l() {
        return this.d;
    }

    public String m() {
        return this.e;
    }

    public int n() {
        return this.k;
    }

    public void o() {
        this.k = 1;
    }

    public ContentValues p() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FileDownloadModel.ID, Integer.valueOf(a()));
        contentValues.put("url", b());
        contentValues.put(FileDownloadModel.PATH, c());
        contentValues.put("status", Byte.valueOf(f()));
        contentValues.put(FileDownloadModel.SOFAR, Long.valueOf(g()));
        contentValues.put(FileDownloadModel.TOTAL, Long.valueOf(h()));
        contentValues.put(FileDownloadModel.ERR_MSG, k());
        contentValues.put("etag", j());
        contentValues.put(FileDownloadModel.CONNECTION_COUNT, Integer.valueOf(n()));
        contentValues.put(FileDownloadModel.PATH_AS_DIRECTORY, Boolean.valueOf(l()));
        if (l() && m() != null) {
            contentValues.put(FileDownloadModel.FILENAME, m());
        }
        return contentValues;
    }

    public boolean q() {
        return this.l;
    }

    public String toString() {
        return f.a("id[%d], url[%s], path[%s], status[%d], sofar[%s], total[%d], etag[%s], %s", Integer.valueOf(this.a), this.b, this.c, Integer.valueOf(this.f.get()), this.g, Long.valueOf(this.h), this.j, super.toString());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeByte(this.d ? (byte) 1 : (byte) 0);
        parcel.writeString(this.e);
        parcel.writeByte((byte) this.f.get());
        parcel.writeLong(this.g.get());
        parcel.writeLong(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeInt(this.k);
        parcel.writeByte(this.l ? (byte) 1 : (byte) 0);
    }
}
