package com.baidu.mobads.sdk.internal;

import android.os.Parcel;
import android.os.Parcelable;
import anet.channel.strategy.dispatch.DispatchConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class bf implements Parcelable {

    /* renamed from: a, reason: collision with root package name */
    public static final String f3157a = "MD5";

    /* renamed from: b, reason: collision with root package name */
    public static final Parcelable.Creator<bf> f3158b = new bg();

    /* renamed from: c, reason: collision with root package name */
    private JSONObject f3159c;
    private double d;
    private String e;
    private String f;
    private String g;
    private int h;
    private int i;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* synthetic */ bf(Parcel parcel, bg bgVar) {
        this(parcel);
    }

    public bf(String str) {
        try {
            this.f3159c = new JSONObject(str);
            this.d = this.f3159c.getDouble("version");
            this.e = this.f3159c.getString("url");
            this.f = this.f3159c.getString(DispatchConstants.SIGN);
            this.i = 1;
            this.g = "";
            this.h = 0;
        } catch (JSONException unused) {
            this.i = 0;
        }
        this.i = c() == null ? 0 : 1;
    }

    public bf(bf bfVar, String str, Boolean bool) {
        this.d = bfVar.b();
        this.e = bfVar.c();
        this.f = bfVar.d();
        this.i = bfVar.a().booleanValue() ? 1 : 0;
        this.g = str;
        this.h = bool.booleanValue() ? 1 : 0;
    }

    private bf(Parcel parcel) {
        this.f = parcel.readString();
        this.i = parcel.readInt();
        this.e = parcel.readString();
        this.d = parcel.readDouble();
        this.g = parcel.readString();
        this.h = parcel.readInt();
    }

    public Boolean a() {
        return Boolean.valueOf(this.i == 1);
    }

    public double b() {
        return this.d;
    }

    public String c() {
        return by.a().c(this.e);
    }

    public String d() {
        return this.f;
    }

    public String e() {
        return this.g;
    }

    public Boolean f() {
        return Boolean.valueOf(this.h == 1);
    }

    public String toString() {
        return this.f3159c.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f);
        parcel.writeInt(this.i);
        parcel.writeString(this.e);
        parcel.writeDouble(this.d);
        parcel.writeString(this.g);
        parcel.writeInt(this.h);
    }
}
