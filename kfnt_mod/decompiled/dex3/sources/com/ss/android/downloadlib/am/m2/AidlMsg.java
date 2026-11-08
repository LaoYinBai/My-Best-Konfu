package com.ss.android.downloadlib.am.m2;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AidlMsg implements Parcelable {
    public static final Parcelable.Creator<AidlMsg> CREATOR = new Parcelable.Creator<AidlMsg>() { // from class: com.ss.android.downloadlib.am.m2.AidlMsg.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AidlMsg createFromParcel(Parcel parcel) {
            return new AidlMsg(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AidlMsg[] newArray(int i) {
            return new AidlMsg[i];
        }
    };
    public int action;
    public int code;
    public String data;
    public int hostVersionCode;
    public String sourceApk;
    public String sourceApkInfo;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "";
    }

    public AidlMsg() {
        this.data = "";
        this.sourceApk = "";
        this.sourceApkInfo = "";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.action);
        parcel.writeInt(this.code);
        parcel.writeString(this.data);
        parcel.writeString(this.sourceApk);
        parcel.writeString(this.sourceApkInfo);
        parcel.writeInt(this.hostVersionCode);
    }

    protected AidlMsg(Parcel parcel) {
        this.data = "";
        this.sourceApk = "";
        this.sourceApkInfo = "";
        this.action = parcel.readInt();
        this.code = parcel.readInt();
        this.data = parcel.readString();
        this.sourceApk = parcel.readString();
        this.sourceApkInfo = parcel.readString();
        this.hostVersionCode = parcel.readInt();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AidlMsg aidlMsg = (AidlMsg) obj;
        if (this.action != aidlMsg.action || this.code != aidlMsg.code) {
            return false;
        }
        if (this.data != null) {
            return this.data.equals(aidlMsg.data);
        }
        return aidlMsg.data == null;
    }

    public int hashCode() {
        return (((this.action * 31) + this.code) * 31) + (this.data != null ? this.data.hashCode() : 0);
    }
}
