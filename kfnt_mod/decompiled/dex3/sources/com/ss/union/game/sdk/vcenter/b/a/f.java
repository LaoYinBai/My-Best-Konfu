package com.ss.union.game.sdk.vcenter.b.a;

import android.content.Intent;
import android.os.RemoteException;
import android.util.Log;
import com.ss.union.game.sdk.vcenter.b.a.e;
import com.ss.union.game.sdk.vcenter.m;
import com.ss.union.game.sdk.vcenter.n;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class f extends n.a {
    private static final String d = "UniversalCmd";
    private Map<Integer, a> e = new HashMap();
    private a f = new d();
    private boolean g = true;
    private b h = new b() { // from class: com.ss.union.game.sdk.vcenter.b.a.f.1
        @Override // com.ss.union.game.sdk.vcenter.b.a.b
        public void a(String str, String str2) {
            Log.d(str, str2);
        }

        @Override // com.ss.union.game.sdk.vcenter.b.a.b
        public void b(String str, String str2) {
            Log.e(str, str2);
        }

        @Override // com.ss.union.game.sdk.vcenter.b.a.b
        public void a(String str, String str2, Throwable th) {
            Log.e(str, str2, th);
        }
    };

    public static e.b a() {
        return e.c.c.a((e.a) null);
    }

    public void b() {
        this.g = false;
    }

    public void a(b bVar) {
        if (bVar == null) {
            return;
        }
        this.h = bVar;
    }

    public void a(a aVar) {
        this.e.put(Integer.valueOf(aVar.a()), aVar);
    }

    a a(e.a aVar) {
        a aVar2 = this.e.get(Integer.valueOf(aVar.c()));
        if (aVar2 != null) {
            return aVar2;
        }
        if (this.g) {
            this.h.b(d, "失败执行命令: " + aVar.d() + ",return defaultHandler");
        }
        return this.f;
    }

    @Override // com.ss.union.game.sdk.vcenter.n
    public String a(String str, Intent intent) throws RemoteException {
        e.a aVarA = e.a.a(str);
        if (aVarA == null) {
            if (this.g) {
                this.h.b(d, "request == null;cmd = " + str);
            }
            return a().g();
        }
        return a(aVarA).a(aVarA, intent).g();
    }

    @Override // com.ss.union.game.sdk.vcenter.n
    public void b(String str, Intent intent) throws RemoteException {
        e.a aVarA = e.a.a(str);
        if (aVarA == null) {
            if (this.g) {
                this.h.b(d, "request == null;cmd = " + str);
                return;
            }
            return;
        }
        a(aVarA).a(aVarA, intent);
    }

    @Override // com.ss.union.game.sdk.vcenter.n
    public void a(String str, Intent intent, final m mVar) throws RemoteException {
        final e.a aVarA = e.a.a(str);
        if (aVarA == null) {
            if (this.g) {
                this.h.b(d, "request == null;cmd = " + str);
            }
            mVar.a(a().g());
            return;
        }
        a(aVarA).a(aVarA, intent, new c() { // from class: com.ss.union.game.sdk.vcenter.b.a.f.2
            @Override // com.ss.union.game.sdk.vcenter.b.a.c
            public void a(e.b bVar) {
                try {
                    mVar.a(aVarA.d());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
