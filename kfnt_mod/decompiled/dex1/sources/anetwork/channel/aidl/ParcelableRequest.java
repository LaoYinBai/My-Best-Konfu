package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import anet.channel.request.BodyEntry;
import anet.channel.util.ALog;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ParcelableRequest implements Parcelable {
    public static final Parcelable.Creator<ParcelableRequest> CREATOR = new m();

    /* renamed from: a, reason: collision with root package name */
    public anetwork.channel.e f646a;

    /* renamed from: b, reason: collision with root package name */
    public BodyEntry f647b;

    /* renamed from: c, reason: collision with root package name */
    public int f648c;
    public String d;
    public String e;
    public boolean f;
    public String g;
    public Map<String, String> h = null;
    public Map<String, String> i = null;
    public int j;
    public int k;
    public String l;
    public String m;
    public Map<String, String> n;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (this.f646a == null) {
            return;
        }
        try {
            parcel.writeInt(this.f646a.c());
            parcel.writeString(this.d);
            parcel.writeString(this.f646a.d());
            parcel.writeInt(this.f646a.a() ? 1 : 0);
            parcel.writeString(this.f646a.b());
            parcel.writeInt(this.h == null ? 0 : 1);
            if (this.h != null) {
                parcel.writeMap(this.h);
            }
            parcel.writeInt(this.i == null ? 0 : 1);
            if (this.i != null) {
                parcel.writeMap(this.i);
            }
            parcel.writeParcelable(this.f647b, 0);
            parcel.writeInt(this.f646a.e());
            parcel.writeInt(this.f646a.f());
            parcel.writeString(this.f646a.g());
            parcel.writeString(this.f646a.h());
            Map<String, String> mapI = this.f646a.i();
            parcel.writeInt(mapI == null ? 0 : 1);
            if (mapI != null) {
                parcel.writeMap(mapI);
            }
        } catch (Throwable th) {
            ALog.w("anet.ParcelableRequest", "[writeToParcel]", null, th, new Object[0]);
        }
    }

    public static ParcelableRequest a(Parcel parcel) {
        ParcelableRequest parcelableRequest = new ParcelableRequest();
        try {
            parcelableRequest.f648c = parcel.readInt();
            parcelableRequest.d = parcel.readString();
            parcelableRequest.e = parcel.readString();
            boolean z = true;
            if (parcel.readInt() != 1) {
                z = false;
            }
            parcelableRequest.f = z;
            parcelableRequest.g = parcel.readString();
            if (parcel.readInt() != 0) {
                parcelableRequest.h = parcel.readHashMap(ParcelableRequest.class.getClassLoader());
            }
            if (parcel.readInt() != 0) {
                parcelableRequest.i = parcel.readHashMap(ParcelableRequest.class.getClassLoader());
            }
            parcelableRequest.f647b = (BodyEntry) parcel.readParcelable(ParcelableRequest.class.getClassLoader());
            parcelableRequest.j = parcel.readInt();
            parcelableRequest.k = parcel.readInt();
            parcelableRequest.l = parcel.readString();
            parcelableRequest.m = parcel.readString();
            if (parcel.readInt() != 0) {
                parcelableRequest.n = parcel.readHashMap(ParcelableRequest.class.getClassLoader());
            }
        } catch (Throwable th) {
            ALog.w("anet.ParcelableRequest", "[readFromParcel]", null, th, new Object[0]);
        }
        return parcelableRequest;
    }

    public String a(String str) {
        if (this.n == null) {
            return null;
        }
        return this.n.get(str);
    }
}
