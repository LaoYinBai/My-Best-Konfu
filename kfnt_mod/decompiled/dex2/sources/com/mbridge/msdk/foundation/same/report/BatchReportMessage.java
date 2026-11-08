package com.mbridge.msdk.foundation.same.report;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class BatchReportMessage implements Parcelable {
    public static final Parcelable.Creator<BatchReportMessage> CREATOR = new Parcelable.Creator<BatchReportMessage>() { // from class: com.mbridge.msdk.foundation.same.report.BatchReportMessage.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ BatchReportMessage[] newArray(int i) {
            return new BatchReportMessage[i];
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ BatchReportMessage createFromParcel(Parcel parcel) {
            return new BatchReportMessage(parcel);
        }
    };
    private String a;
    private String b;
    private long c;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BatchReportMessage(String str, String str2, long j) {
        this.a = str;
        this.b = str2;
        this.c = j;
    }

    protected BatchReportMessage(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readLong();
    }

    public String getUuid() {
        return this.a;
    }

    public void setUuid(String str) {
        this.a = str;
    }

    public String getReportMessage() {
        return this.b;
    }

    public void setReportMessage(String str) {
        this.b = str;
    }

    public long getTimestamp() {
        return this.c;
    }

    public void setTimestamp(long j) {
        this.c = j;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeLong(this.c);
    }
}
