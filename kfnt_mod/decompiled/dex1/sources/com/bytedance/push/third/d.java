package com.bytedance.push.third;

import com.bytedance.push.utils.i;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d extends i<b> implements b {

    /* renamed from: a, reason: collision with root package name */
    private int f4973a;

    /* renamed from: b, reason: collision with root package name */
    private String f4974b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f4975c;
    private IPushAdapter d;
    private String e;
    private com.bytedance.push.third.a.b f;

    @Override // com.bytedance.push.third.b
    public boolean a() {
        return this.f4975c;
    }

    @Override // com.bytedance.push.third.b
    public IPushAdapter b() {
        return this.d;
    }

    public d(int i, String str, String str2, com.bytedance.push.third.a.b bVar) {
        this.f4973a = i;
        this.f4974b = str;
        this.e = str2;
        this.f = bVar;
    }

    @Override // com.bytedance.push.third.b
    public String c() {
        return this.f4974b;
    }

    @Override // com.bytedance.push.third.b
    public String d() {
        return this.e;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.push.utils.i
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public b b(Object... objArr) {
        if (com.bytedance.common.push.utility.i.a(this.f4974b)) {
            return this;
        }
        try {
            Object objNewInstance = Class.forName(this.f4974b).newInstance();
            if (objNewInstance instanceof IPushAdapter) {
                this.d = (IPushAdapter) objNewInstance;
                this.f4975c = this.f.a(this.d, this.f4973a);
            }
            com.bytedance.common.push.utility.a.d("PushManager", "load PushManagerImpl success: " + this.f4974b);
        } catch (Throwable th) {
            com.bytedance.common.push.utility.a.d("PushManager", "load PushManagerImpl exception: " + this.f4974b + " exception is:" + th);
        }
        return this;
    }
}
