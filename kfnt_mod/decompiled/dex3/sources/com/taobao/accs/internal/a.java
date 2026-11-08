package com.taobao.accs.internal;

import androidx.annotation.Keep;
import com.taobao.accs.AccsClientConfig;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.d;
import com.taobao.accs.data.Message;
import com.taobao.accs.net.j;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a implements d {
    private static final String a = "a";
    private com.taobao.accs.net.b b;

    public a(AccsClientConfig accsClientConfig) {
        AccsClientConfig.setAccsConfig(accsClientConfig.getConfigEnv(), accsClientConfig);
        this.b = b.a(GlobalClientInfo.a, accsClientConfig.getTag(), true);
    }

    public a(String str) {
        this.b = new j(GlobalClientInfo.a, 1, str);
    }

    @Override // com.taobao.accs.d
    public void a() {
        this.b.a();
    }

    @Override // com.taobao.accs.d
    public boolean a(String str) {
        return this.b.a(str);
    }

    @Override // com.taobao.accs.d
    public void b() {
        this.b.k();
    }

    @Override // com.taobao.accs.d
    public String c() {
        return this.b.i();
    }

    @Override // com.taobao.accs.d
    public void a(Message message, int i) {
        this.b.b(message, i);
    }

    @Override // com.taobao.accs.d
    public String b(String str) {
        return this.b.b(str);
    }

    @Override // com.taobao.accs.d
    public String d() {
        return this.b.i.getAppSecret();
    }

    @Override // com.taobao.accs.d
    public void c(String str) {
        this.b.a = str;
    }

    @Override // com.taobao.accs.d
    public void d(String str) {
        this.b.b = str;
    }

    @Override // com.taobao.accs.d
    public void a(Message message, boolean z) {
        this.b.b(message, z);
    }

    @Override // com.taobao.accs.d
    public void a(AccsClientConfig accsClientConfig) {
        if (this.b instanceof j) {
            ((j) this.b).a(accsClientConfig);
        }
    }

    @Override // com.taobao.accs.d
    public boolean e(String str) {
        return this.b.j().c(str);
    }

    @Override // com.taobao.accs.d
    public boolean f(String str) {
        return this.b.j().d(str);
    }

    @Override // com.taobao.accs.d
    public boolean a(String str, String str2) {
        return this.b.j().b(str, str2);
    }

    @Override // com.taobao.accs.d
    public void a(Message message) {
        this.b.a(message, true);
    }

    @Override // com.taobao.accs.d
    public void a(int i) {
        this.b.c(i);
    }

    @Override // com.taobao.accs.d
    public String e() {
        return this.b.i.getStoreId();
    }

    @Override // com.taobao.accs.d
    public boolean f() {
        return this.b.m();
    }
}
