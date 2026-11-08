package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class m implements Parcelable.Creator<ParcelableRequest> {
    m() {
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ParcelableRequest createFromParcel(Parcel parcel) {
        return ParcelableRequest.a(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ParcelableRequest[] newArray(int i) {
        return new ParcelableRequest[i];
    }
}
