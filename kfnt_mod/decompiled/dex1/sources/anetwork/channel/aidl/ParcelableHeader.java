package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import anet.channel.util.ALog;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ParcelableHeader implements Parcelable {
    public static Parcelable.Creator<ParcelableHeader> CREATOR = new l();

    /* renamed from: a, reason: collision with root package name */
    public int f644a;

    /* renamed from: b, reason: collision with root package name */
    public Map<String, List<String>> f645b;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ParcelableHeader(int i, Map<String, List<String>> map) {
        this.f645b = map;
        this.f644a = i;
    }

    ParcelableHeader() {
    }

    public Map<String, List<String>> a() {
        return this.f645b;
    }

    public int b() {
        return this.f644a;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (this.f645b != null) {
            parcel.writeInt(1);
            parcel.writeMap(this.f645b);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeInt(this.f644a);
    }

    static ParcelableHeader a(Parcel parcel) {
        ParcelableHeader parcelableHeader = new ParcelableHeader();
        try {
            if (parcel.readInt() == 1) {
                parcelableHeader.f645b = parcel.readHashMap(ParcelableHeader.class.getClassLoader());
            }
            parcelableHeader.f644a = parcel.readInt();
        } catch (Throwable th) {
            ALog.e("anet.ParcelableHeader", "[readFromParcel]", null, th, new Object[0]);
        }
        return parcelableHeader;
    }

    public String toString() {
        return "ParcelableResponseHeader [responseCode=" + this.f644a + ", header=" + this.f645b + "]";
    }
}
