package anet.channel.entity;

import anet.channel.strategy.IConnStrategy;
import com.bytedance.msdk.api.AdError;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    public final IConnStrategy f429a;

    /* renamed from: b, reason: collision with root package name */
    public int f430b = 0;

    /* renamed from: c, reason: collision with root package name */
    public int f431c = 0;
    private String d;
    private String e;

    public a(String str, String str2, IConnStrategy iConnStrategy) {
        this.f429a = iConnStrategy;
        this.d = str;
        this.e = str2;
    }

    public String a() {
        if (this.f429a != null) {
            return this.f429a.getIp();
        }
        return null;
    }

    public int b() {
        if (this.f429a != null) {
            return this.f429a.getPort();
        }
        return 0;
    }

    public ConnType c() {
        if (this.f429a != null) {
            return ConnType.valueOf(this.f429a.getProtocol());
        }
        return ConnType.HTTP;
    }

    public int d() {
        return (this.f429a == null || this.f429a.getConnectionTimeout() == 0) ? AdError.ERROR_CODE_AD_LOAD_SUCCESS : this.f429a.getConnectionTimeout();
    }

    public int e() {
        return (this.f429a == null || this.f429a.getReadTimeout() == 0) ? AdError.ERROR_CODE_AD_LOAD_SUCCESS : this.f429a.getReadTimeout();
    }

    public String f() {
        return this.d;
    }

    public int g() {
        if (this.f429a != null) {
            return this.f429a.getHeartbeat();
        }
        return 45000;
    }

    public String h() {
        return this.e;
    }

    public String toString() {
        return "ConnInfo [ip=" + a() + ",port=" + b() + ",type=" + c() + ",hb" + g() + "]";
    }
}
