package com.kwai.filedownloader.message;

import android.os.Parcel;
import android.os.Parcelable;
import com.kwai.filedownloader.message.d;
import com.kwai.filedownloader.message.h;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class MessageSnapshot implements Parcelable, c {
    public static final Parcelable.Creator<MessageSnapshot> CREATOR = new Parcelable.Creator<MessageSnapshot>() { // from class: com.kwai.filedownloader.message.MessageSnapshot.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public MessageSnapshot createFromParcel(Parcel parcel) {
            MessageSnapshot jVar;
            boolean z = parcel.readByte() == 1;
            byte b2 = parcel.readByte();
            switch (b2) {
                case -4:
                    if (!z) {
                        jVar = new h.j(parcel);
                        break;
                    } else {
                        jVar = new d.j(parcel);
                        break;
                    }
                case -3:
                    if (!z) {
                        jVar = new h.b(parcel);
                        break;
                    } else {
                        jVar = new d.b(parcel);
                        break;
                    }
                case -2:
                case 0:
                case 4:
                default:
                    jVar = null;
                    break;
                case -1:
                    if (!z) {
                        jVar = new h.d(parcel);
                        break;
                    } else {
                        jVar = new d.C0097d(parcel);
                        break;
                    }
                case 1:
                    if (!z) {
                        jVar = new h.f(parcel);
                        break;
                    } else {
                        jVar = new d.f(parcel);
                        break;
                    }
                case 2:
                    if (!z) {
                        jVar = new h.c(parcel);
                        break;
                    } else {
                        jVar = new d.c(parcel);
                        break;
                    }
                case 3:
                    if (!z) {
                        jVar = new h.g(parcel);
                        break;
                    } else {
                        jVar = new d.g(parcel);
                        break;
                    }
                case 5:
                    if (!z) {
                        jVar = new h.C0098h(parcel);
                        break;
                    } else {
                        jVar = new d.h(parcel);
                        break;
                    }
                case 6:
                    jVar = new b(parcel);
                    break;
            }
            if (jVar != null) {
                jVar.a = z;
                return jVar;
            }
            throw new IllegalStateException("Can't restore the snapshot because unknown status: " + ((int) b2));
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public MessageSnapshot[] newArray(int i) {
            return new MessageSnapshot[i];
        }
    };
    protected boolean a;
    private final int b;

    public static class NoFieldException extends IllegalStateException {
        NoFieldException(String str, MessageSnapshot messageSnapshot) {
            super(com.kwai.filedownloader.e.f.a("There isn't a field for '%s' in this message %d %d %s", str, Integer.valueOf(messageSnapshot.m()), Byte.valueOf(messageSnapshot.b()), messageSnapshot.getClass().getName()));
        }
    }

    public interface a {
        MessageSnapshot l();
    }

    public static class b extends MessageSnapshot {
        b(int i) {
            super(i);
        }

        b(Parcel parcel) {
            super(parcel);
        }

        @Override // com.kwai.filedownloader.message.c
        public byte b() {
            return (byte) 6;
        }
    }

    MessageSnapshot(int i) {
        this.b = i;
    }

    MessageSnapshot(Parcel parcel) {
        this.b = parcel.readInt();
    }

    public int a() {
        throw new NoFieldException("getSmallSofarBytes", this);
    }

    public int c() {
        throw new NoFieldException("getSmallTotalBytes", this);
    }

    public long d() {
        throw new NoFieldException("getLargeTotalBytes", this);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean e() {
        throw new NoFieldException("isReusedDownloadedFile", this);
    }

    public String f() {
        throw new NoFieldException("getFileName", this);
    }

    public boolean g() {
        throw new NoFieldException("isResuming", this);
    }

    public String h() {
        throw new NoFieldException("getEtag", this);
    }

    public long i() {
        throw new NoFieldException("getLargeSofarBytes", this);
    }

    public Throwable j() {
        throw new NoFieldException("getThrowable", this);
    }

    public int k() {
        throw new NoFieldException("getRetryingTimes", this);
    }

    public int m() {
        return this.b;
    }

    public boolean n() {
        return this.a;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.a ? (byte) 1 : (byte) 0);
        parcel.writeByte(b());
        parcel.writeInt(this.b);
    }
}
