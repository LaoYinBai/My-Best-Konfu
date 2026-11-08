package com.ss.union.game.sdk.common.net.dns;

import com.bytedance.sdk.adnet.game.ok3.okhttp3.Dns;
import java.net.InetAddress;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class Ok3HttpDNS implements Dns {
    private String a;

    public Ok3HttpDNS(String str) {
        this.a = str;
    }

    public List<InetAddress> lookup(String str) {
        return VDNSUtils.a(this.a, str);
    }
}
