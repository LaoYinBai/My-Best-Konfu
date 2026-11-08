package anetwork.channel.entity;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class a implements Parcelable.Creator<BodyHandlerEntry> {
    a() {
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public BodyHandlerEntry createFromParcel(Parcel parcel) {
        BodyHandlerEntry bodyHandlerEntry = new BodyHandlerEntry(null);
        bodyHandlerEntry.f684a = anetwork.channel.aidl.a.b.a(parcel.readStrongBinder());
        return bodyHandlerEntry;
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public BodyHandlerEntry[] newArray(int i) {
        return new BodyHandlerEntry[i];
    }
}
