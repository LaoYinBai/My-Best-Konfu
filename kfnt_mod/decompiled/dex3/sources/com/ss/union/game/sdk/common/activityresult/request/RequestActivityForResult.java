package com.ss.union.game.sdk.common.activityresult.request;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RequestActivityForResult implements Request {
    public static final Parcelable.Creator<RequestActivityForResult> CREATOR = new Parcelable.Creator<RequestActivityForResult>() { // from class: com.ss.union.game.sdk.common.activityresult.request.RequestActivityForResult.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public RequestActivityForResult createFromParcel(Parcel parcel) {
            return new RequestActivityForResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public RequestActivityForResult[] newArray(int i) {
            return new RequestActivityForResult[i];
        }
    };
    private final Intent a;

    @Nullable
    private Bundle b;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RequestActivityForResult(Intent intent, @Nullable Bundle bundle) {
        this.a = intent;
        this.b = bundle;
    }

    private RequestActivityForResult(Parcel parcel) {
        this((Intent) parcel.readParcelable(Intent.class.getClassLoader()), (Bundle) parcel.readParcelable(Bundle.class.getClassLoader()));
    }

    @NonNull
    public Intent getIntent() {
        return this.a;
    }

    @Override // com.ss.union.game.sdk.common.activityresult.request.Request
    public void execute(Fragment fragment, int i) throws Exception {
        fragment.startActivityForResult(this.a, i, this.b);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.a, i);
        parcel.writeParcelable(this.b, i);
    }
}
