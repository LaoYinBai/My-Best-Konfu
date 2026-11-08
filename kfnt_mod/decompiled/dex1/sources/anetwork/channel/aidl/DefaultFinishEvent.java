package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import anet.channel.statist.RequestStatistic;
import anet.channel.util.ErrorConstant;
import anetwork.channel.c;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class DefaultFinishEvent implements Parcelable, c.a {
    public static final Parcelable.Creator<DefaultFinishEvent> CREATOR = new i();

    /* renamed from: a, reason: collision with root package name */
    Object f632a;

    /* renamed from: b, reason: collision with root package name */
    int f633b;

    /* renamed from: c, reason: collision with root package name */
    String f634c;
    anetwork.channel.i.a d;
    public final RequestStatistic e;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void a(Object obj) {
        this.f632a = obj;
    }

    @Override // anetwork.channel.c.a
    public int a() {
        return this.f633b;
    }

    @Override // anetwork.channel.c.a
    public String b() {
        return this.f634c;
    }

    @Override // anetwork.channel.c.a
    public anetwork.channel.i.a c() {
        return this.d;
    }

    public DefaultFinishEvent(int i) {
        this(i, null, null);
    }

    public DefaultFinishEvent(int i, String str, RequestStatistic requestStatistic) {
        this.d = new anetwork.channel.i.a();
        this.f633b = i;
        this.f634c = str == null ? ErrorConstant.getErrMsg(i) : str;
        this.e = requestStatistic;
    }

    public String toString() {
        return "DefaultFinishEvent [code=" + this.f633b + ", desc=" + this.f634c + ", context=" + this.f632a + ", statisticData=" + this.d + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f633b);
        parcel.writeString(this.f634c);
        if (this.d != null) {
            parcel.writeSerializable(this.d);
        }
    }

    static DefaultFinishEvent a(Parcel parcel) {
        DefaultFinishEvent defaultFinishEvent = new DefaultFinishEvent(0);
        try {
            defaultFinishEvent.f633b = parcel.readInt();
            defaultFinishEvent.f634c = parcel.readString();
            defaultFinishEvent.d = (anetwork.channel.i.a) parcel.readSerializable();
        } catch (Throwable unused) {
        }
        return defaultFinishEvent;
    }
}
