package com.huawei.hms.support.api.entity.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.internal.Objects;
import com.huawei.hms.core.aidl.IMessageEntity;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class Scope implements Parcelable, IMessageEntity {
    public static final Parcelable.Creator<Scope> CREATOR = new Parcelable.Creator<Scope>() { // from class: com.huawei.hms.support.api.entity.auth.Scope.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Scope createFromParcel(Parcel parcel) {
            return new Scope(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Scope[] newArray(int i) {
            return new Scope[i];
        }
    };
    private String mScopeUri;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Scope() {
        this.mScopeUri = null;
    }

    public Scope(String str) {
        this.mScopeUri = str;
    }

    protected Scope(Parcel parcel) {
        this.mScopeUri = parcel.readString();
    }

    public String getScopeUri() {
        return this.mScopeUri;
    }

    @Deprecated
    public boolean equeals(Object obj) {
        return equals(obj);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Scope) {
            return Objects.equal(this.mScopeUri, ((Scope) obj).mScopeUri);
        }
        return false;
    }

    public final int hashCode() {
        return this.mScopeUri == null ? super.hashCode() : this.mScopeUri.hashCode();
    }

    public final String toString() {
        return this.mScopeUri;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mScopeUri);
    }
}
