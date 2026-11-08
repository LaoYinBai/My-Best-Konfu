package com.bytedance.pangle.servermanager;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class a implements Parcelable {
    public static final Parcelable.Creator<a> CREATOR = new Parcelable.Creator<a>() { // from class: com.bytedance.pangle.servermanager.a.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ a[] newArray(int i) {
            return new a[i];
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ a createFromParcel(Parcel parcel) {
            return new a(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    final IBinder f4682a;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    a(Parcel parcel) {
        this.f4682a = parcel.readStrongBinder();
    }

    a(IBinder iBinder) {
        this.f4682a = iBinder;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.f4682a);
    }
}
