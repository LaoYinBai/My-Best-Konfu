package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class k implements Parcelable.Creator<NetworkResponse> {
    k() {
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public NetworkResponse createFromParcel(Parcel parcel) {
        return NetworkResponse.a(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public NetworkResponse[] newArray(int i) {
        return new NetworkResponse[i];
    }
}
