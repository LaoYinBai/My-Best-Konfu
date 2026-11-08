package com.huawei.hms.common.internal;

import com.huawei.hms.api.Api;
import com.huawei.hms.api.Api.ApiOptions;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ConnectionManagerKey<TOption extends Api.ApiOptions> {
    private final Api<TOption> a;
    private final TOption b;
    private final boolean c;
    private final int d;
    private final String e;

    private ConnectionManagerKey(Api<TOption> api, TOption toption, String str) {
        this.c = false;
        this.a = api;
        this.b = toption;
        this.d = Objects.hashCode(this.a, this.b);
        this.e = str;
    }

    private ConnectionManagerKey(Api<TOption> api, String str) {
        this.c = true;
        this.a = api;
        this.b = null;
        this.d = System.identityHashCode(this);
        this.e = str;
    }

    public static <TOption extends Api.ApiOptions> ConnectionManagerKey<TOption> createConnectionManagerKey(Api<TOption> api, TOption toption, String str) {
        return new ConnectionManagerKey<>(api, toption, str);
    }

    public static <TOption extends Api.ApiOptions> ConnectionManagerKey<TOption> createConnectionManagerKey(Api<TOption> api, String str) {
        return new ConnectionManagerKey<>(api, str);
    }

    public final int hashCode() {
        return this.d;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ConnectionManagerKey)) {
            return false;
        }
        ConnectionManagerKey connectionManagerKey = (ConnectionManagerKey) obj;
        return this.c == connectionManagerKey.c && Objects.equal(this.a, connectionManagerKey.a) && Objects.equal(this.b, connectionManagerKey.b) && Objects.equal(this.e, connectionManagerKey.e);
    }
}
