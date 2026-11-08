package anet.channel.util;

import com.appsflyer.share.Constants;
import java.net.Inet6Address;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    public int f611a;

    /* renamed from: b, reason: collision with root package name */
    public Inet6Address f612b;

    public f(Inet6Address inet6Address, int i) {
        this.f611a = i;
        this.f612b = inet6Address;
    }

    public String toString() {
        return this.f612b.getHostAddress() + Constants.URL_PATH_DELIMITER + this.f611a;
    }
}
