package com.xiaomi.push;

import com.xiaomi.push.dd;
import com.xiaomi.push.dd.c;
import java.io.File;
import java.util.Date;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class de extends dd.b {
    final /* synthetic */ int a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ dd f230a;

    /* renamed from: a, reason: collision with other field name */
    File f231a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ String f232a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ Date f233a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ boolean f234a;
    final /* synthetic */ String b;

    /* renamed from: b, reason: collision with other field name */
    final /* synthetic */ Date f235b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    de(dd ddVar, int i, Date date, Date date2, String str, String str2, boolean z) {
        super();
        this.f230a = ddVar;
        this.a = i;
        this.f233a = date;
        this.f235b = date2;
        this.f232a = str;
        this.b = str2;
        this.f234a = z;
    }

    @Override // com.xiaomi.push.dd.b, com.xiaomi.push.al.b
    public void b() {
        if (aa.d()) {
            try {
                File file = new File(this.f230a.f223a.getExternalFilesDir(null) + "/.logcache");
                file.mkdirs();
                if (file.isDirectory()) {
                    dc dcVar = new dc();
                    dcVar.a(this.a);
                    this.f231a = dcVar.a(this.f230a.f223a, this.f233a, this.f235b, file);
                }
            } catch (NullPointerException unused) {
            }
        }
    }

    @Override // com.xiaomi.push.al.b
    /* renamed from: c */
    public void mo197c() {
        if (this.f231a != null && this.f231a.exists()) {
            this.f230a.f224a.add(this.f230a.new c(this.f232a, this.b, this.f231a, this.f234a));
        }
        this.f230a.a(0L);
    }
}
