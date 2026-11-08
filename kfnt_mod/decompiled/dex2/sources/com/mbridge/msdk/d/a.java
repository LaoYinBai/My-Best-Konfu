package com.mbridge.msdk.d;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.mbridge.msdk.foundation.db.f;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.db.s;
import com.mbridge.msdk.foundation.entity.l;
import com.mbridge.msdk.reward.adapter.d;
import java.util.LinkedList;
import java.util.List;

/* compiled from: LoopTimer.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    private long a;
    private boolean b;
    private LinkedList<l> c;
    private LinkedList<l> d;
    private int e;
    private int f;
    private f g;
    private com.mbridge.msdk.videocommon.d.a h;
    private s i;
    private i j;
    private Handler k;

    /* compiled from: LoopTimer.java */
    /* renamed from: com.mbridge.msdk.d.a$a, reason: collision with other inner class name */
    static class C0107a {
        static a a = new a();
    }

    private a() {
        this.b = false;
        this.c = new LinkedList<>();
        this.d = new LinkedList<>();
        this.e = 0;
        this.f = 0;
        this.k = new Handler() { // from class: com.mbridge.msdk.d.a.1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                synchronized (a.this) {
                    switch (message.what) {
                        case 1:
                            if (!a.this.b) {
                                a.a(a.this, a.this.a);
                                sendMessageDelayed(obtainMessage(1), a.this.a);
                                break;
                            } else {
                                return;
                            }
                        case 2:
                            a.c(a.this);
                            break;
                    }
                }
            }
        };
    }

    private boolean a(l lVar) {
        if (lVar == null || TextUtils.isEmpty(lVar.a())) {
            return false;
        }
        String strA = lVar.a();
        boolean z = true;
        try {
            if (this.g == null) {
                return true;
            }
            int iA = this.g.a(strA, this.h != null ? this.h.d() : 0L);
            if (iA != -1) {
                if (iA == 1) {
                    return true;
                }
            } else if (!TextUtils.isEmpty(strA)) {
                if (this.c != null && this.c.contains(strA)) {
                    this.c.remove(strA);
                } else if (this.d != null && this.d.contains(strA)) {
                    this.d.remove(strA);
                }
                if (this.i != null) {
                    this.i.a(strA);
                }
            }
            try {
                this.k.sendMessage(this.k.obtainMessage(2));
                return false;
            } catch (Throwable th) {
                th = th;
                z = false;
                com.mbridge.msdk.foundation.tools.s.b("LoopTimer", th.getMessage(), th);
                return z;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private void a(String str, String str2, boolean z) {
        try {
            Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
            if (contextJ == null) {
                return;
            }
            final d dVar = new d(contextJ, str, str2);
            dVar.a(z);
            dVar.a(new com.mbridge.msdk.reward.adapter.b() { // from class: com.mbridge.msdk.d.a.2
                @Override // com.mbridge.msdk.reward.adapter.b
                public final void a() {
                }

                @Override // com.mbridge.msdk.reward.adapter.b
                public final void a(String str3) {
                    a.this.k.sendMessage(a.this.k.obtainMessage(2));
                    dVar.a((com.mbridge.msdk.reward.adapter.b) null);
                }

                @Override // com.mbridge.msdk.reward.adapter.b
                public final void b(String str3) {
                    a.this.k.sendMessage(a.this.k.obtainMessage(2));
                    dVar.a((com.mbridge.msdk.reward.adapter.b) null);
                }
            });
            dVar.a(1, 8000, false);
        } catch (Exception e) {
            com.mbridge.msdk.foundation.tools.s.b("LoopTimer", e.getMessage(), e);
        }
    }

    public final void a(String str, String str2) {
        if (this.c.contains(str2)) {
            return;
        }
        this.c.add(new l(str, str2, 94));
        if (this.i != null) {
            this.i.a(str, str2, 94);
        }
    }

    public final void b(String str, String str2) {
        if (this.d.contains(str2)) {
            return;
        }
        this.d.add(new l(str, str2, 287));
        if (this.i != null) {
            this.i.a(str, str2, 287);
        }
    }

    public final void a(long j) {
        if (this.j == null) {
            this.j = i.a(com.mbridge.msdk.foundation.controller.a.f().j());
        }
        if (this.i == null) {
            this.i = s.a(this.j);
        }
        List<l> listA = this.i.a(287);
        if (listA != null) {
            this.d.addAll(listA);
            for (l lVar : listA) {
                b(lVar.b(), lVar.a());
            }
        }
        List<l> listA2 = this.i.a(94);
        if (listA2 != null) {
            this.c.addAll(listA2);
            for (l lVar2 : listA2) {
                a(lVar2.b(), lVar2.a());
            }
        }
        if (this.g == null) {
            this.g = f.a(this.j);
        }
        if (this.h == null) {
            this.h = com.mbridge.msdk.videocommon.d.b.a().b();
        }
        this.a = j;
        this.b = false;
        this.k.sendMessageDelayed(this.k.obtainMessage(1), this.a);
    }

    static /* synthetic */ void a(a aVar, long j) {
        if (aVar.c == null || aVar.c.size() <= 0 || aVar.e == 0 || aVar.c.size() <= aVar.e) {
            if (aVar.d == null || aVar.d.size() <= 0 || aVar.f == 0 || aVar.d.size() == aVar.f) {
                aVar.f = 0;
                aVar.e = 0;
                aVar.k.sendMessage(aVar.k.obtainMessage(2));
            }
        }
    }

    static /* synthetic */ void c(a aVar) {
        try {
            if (aVar.c != null && aVar.c.size() > 0 && aVar.e < aVar.c.size()) {
                l lVar = aVar.c.get(aVar.e);
                aVar.e++;
                if (aVar.a(lVar)) {
                    aVar.a(lVar.b(), lVar.a(), false);
                }
            } else if (aVar.d != null && aVar.d.size() > 0 && aVar.f < aVar.d.size()) {
                l lVar2 = aVar.d.get(aVar.f);
                aVar.f++;
                if (aVar.a(lVar2)) {
                    aVar.a(lVar2.b(), lVar2.a(), true);
                }
            }
        } catch (Throwable th) {
            com.mbridge.msdk.foundation.tools.s.b("LoopTimer", th.getMessage(), th);
        }
    }
}
