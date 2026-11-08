package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import anet.channel.util.ALog;
import anet.channel.util.ErrorConstant;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class NetworkResponse implements Parcelable, anetwork.channel.f {
    public static final Parcelable.Creator<NetworkResponse> CREATOR = new k();

    /* renamed from: a, reason: collision with root package name */
    int f638a;

    /* renamed from: b, reason: collision with root package name */
    byte[] f639b;

    /* renamed from: c, reason: collision with root package name */
    private String f640c;
    private Map<String, List<String>> d;
    private Throwable e;
    private anetwork.channel.i.a f;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void a(int i) {
        this.f638a = i;
        this.f640c = ErrorConstant.getErrMsg(i);
    }

    public void a(byte[] bArr) {
        this.f639b = bArr;
    }

    public void a(Map<String, List<String>> map) {
        this.d = map;
    }

    public void a(String str) {
        this.f640c = str;
    }

    public String a() {
        return this.f640c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("NetworkResponse [");
        sb.append("statusCode=");
        sb.append(this.f638a);
        sb.append(", desc=");
        sb.append(this.f640c);
        sb.append(", connHeadFields=");
        sb.append(this.d);
        sb.append(", bytedata=");
        sb.append(this.f639b != null ? new String(this.f639b) : "");
        sb.append(", error=");
        sb.append(this.e);
        sb.append(", statisticData=");
        sb.append(this.f);
        sb.append("]");
        return sb.toString();
    }

    public NetworkResponse() {
    }

    public NetworkResponse(int i) {
        this.f638a = i;
        this.f640c = ErrorConstant.getErrMsg(i);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f638a);
        parcel.writeString(this.f640c);
        int length = this.f639b != null ? this.f639b.length : 0;
        parcel.writeInt(length);
        if (length > 0) {
            parcel.writeByteArray(this.f639b);
        }
        parcel.writeMap(this.d);
        if (this.f != null) {
            parcel.writeSerializable(this.f);
        }
    }

    public static NetworkResponse a(Parcel parcel) {
        NetworkResponse networkResponse = new NetworkResponse();
        try {
            networkResponse.f638a = parcel.readInt();
            networkResponse.f640c = parcel.readString();
            int i = parcel.readInt();
            if (i > 0) {
                networkResponse.f639b = new byte[i];
                parcel.readByteArray(networkResponse.f639b);
            }
            networkResponse.d = parcel.readHashMap(NetworkResponse.class.getClassLoader());
            try {
                networkResponse.f = (anetwork.channel.i.a) parcel.readSerializable();
            } catch (Throwable unused) {
                ALog.i("anet.NetworkResponse", "[readFromParcel] source.readSerializable() error", null, new Object[0]);
            }
        } catch (Exception e) {
            ALog.w("anet.NetworkResponse", "[readFromParcel]", null, e, new Object[0]);
        }
        return networkResponse;
    }

    public void a(anetwork.channel.i.a aVar) {
        this.f = aVar;
    }
}
