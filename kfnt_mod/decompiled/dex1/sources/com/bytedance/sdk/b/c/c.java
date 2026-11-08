package com.bytedance.sdk.b.c;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c implements com.bytedance.sdk.b.b.b {

    /* renamed from: a, reason: collision with root package name */
    public static final String f5139a = "MainProcessDNSManager";

    /* renamed from: b, reason: collision with root package name */
    private com.bytedance.sdk.b.e.a.a f5140b;

    /* renamed from: c, reason: collision with root package name */
    private a f5141c;
    private b d;

    @Override // com.bytedance.sdk.b.b.b
    public com.bytedance.sdk.b.b.b a(com.bytedance.sdk.b.e.a.a aVar) {
        this.f5140b = aVar;
        this.f5141c = new a(aVar);
        this.d = new b(aVar);
        this.d.a(new com.bytedance.sdk.b.b.a() { // from class: com.bytedance.sdk.b.c.c.1
            @Override // com.bytedance.sdk.b.b.a
            public void a(String str, com.bytedance.sdk.b.a.a aVar2) {
                if (c.this.f5141c != null) {
                    c.this.f5141c.a(str, aVar2);
                }
            }
        });
        String[] strArrD = aVar.d();
        if (strArrD != null && strArrD.length > 0) {
            for (String str : strArrD) {
                this.d.a(str);
            }
        }
        return this;
    }

    @Override // com.bytedance.sdk.b.b.b
    public List<InetAddress> a(String str) {
        if (this.f5141c != null) {
            com.bytedance.sdk.b.a.a aVarA = this.f5141c.a(str);
            if (aVarA != null && aVarA.f5130c != null && aVarA.f5130c.size() > 0) {
                if (aVarA.a()) {
                    ArrayList arrayList = new ArrayList(aVarA.f5130c);
                    try {
                        InetAddress[] allByName = InetAddress.getAllByName(str);
                        if (allByName != null && allByName.length > 0) {
                            for (InetAddress inetAddress : allByName) {
                                arrayList.add(inetAddress);
                            }
                        }
                    } catch (Throwable unused) {
                    }
                    if (this.d != null) {
                        this.d.a(str);
                    }
                    return arrayList;
                }
                return aVarA.f5130c;
            }
            if (this.d != null) {
                this.d.a(str);
            }
        }
        return new ArrayList();
    }

    @Override // com.bytedance.sdk.b.b.b
    public void a(String[] strArr) {
        if (this.d == null || strArr == null || strArr.length <= 0) {
            return;
        }
        for (String str : strArr) {
            this.d.a(str);
        }
    }
}
