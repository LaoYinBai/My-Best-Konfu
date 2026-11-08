package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import anetwork.channel.c;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class DefaultProgressEvent implements Parcelable, c.b {
    public static final Parcelable.Creator<DefaultProgressEvent> CREATOR = new j();

    /* renamed from: a, reason: collision with root package name */
    int f635a;

    /* renamed from: b, reason: collision with root package name */
    int f636b;

    /* renamed from: c, reason: collision with root package name */
    int f637c;
    Object d;
    byte[] e;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DefaultProgressEvent() {
    }

    public DefaultProgressEvent(int i, int i2, int i3, byte[] bArr) {
        this.f635a = i;
        this.f636b = i2;
        this.f637c = i3;
        this.e = bArr;
    }

    public void a(Object obj) {
        this.d = obj;
    }

    public String toString() {
        return "DefaultProgressEvent [index=" + this.f635a + ", size=" + this.f636b + ", total=" + this.f637c + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f635a);
        parcel.writeInt(this.f636b);
        parcel.writeInt(this.f637c);
        parcel.writeInt(this.e != null ? this.e.length : 0);
        parcel.writeByteArray(this.e);
    }

    public static DefaultProgressEvent a(Parcel parcel) {
        DefaultProgressEvent defaultProgressEvent = new DefaultProgressEvent();
        try {
            defaultProgressEvent.f635a = parcel.readInt();
            defaultProgressEvent.f636b = parcel.readInt();
            defaultProgressEvent.f637c = parcel.readInt();
            int i = parcel.readInt();
            if (i > 0) {
                byte[] bArr = new byte[i];
                parcel.readByteArray(bArr);
                defaultProgressEvent.e = bArr;
            }
        } catch (Exception unused) {
        }
        return defaultProgressEvent;
    }
}
