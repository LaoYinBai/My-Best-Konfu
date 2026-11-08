package com.kwad.sdk.core.j;

import android.os.Message;
import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import com.kwad.sdk.utils.ae;
import com.kwad.sdk.utils.bb;
import com.kwad.sdk.utils.bc;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class a implements c, bc.a {
    private Set<d> c;
    private final View d;
    private final int e;
    private final AtomicBoolean b = new AtomicBoolean(false);
    protected final bc a = new bc(this);

    public a(@NonNull View view, int i) {
        this.d = view;
        this.e = i;
    }

    private void a(boolean z) {
        if (this.c == null) {
            return;
        }
        for (d dVar : this.c) {
            if (dVar != null) {
                if (z) {
                    dVar.e();
                } else {
                    dVar.f();
                }
            }
        }
    }

    public final void a() {
        this.a.removeMessages(666);
        this.a.sendEmptyMessage(666);
    }

    @Override // com.kwad.sdk.utils.bc.a
    public void a(Message message) {
        if (message.what == 666) {
            if (c()) {
                e();
            } else {
                f();
            }
            this.a.sendEmptyMessageDelayed(666, 500L);
        }
    }

    @Override // com.kwad.sdk.core.j.c
    @MainThread
    public final void a(d dVar) {
        ae.a();
        if (dVar == null) {
            return;
        }
        if (c()) {
            dVar.e();
        } else {
            dVar.f();
        }
        if (this.c == null) {
            this.c = new HashSet();
        }
        this.c.add(dVar);
    }

    public final void b() {
        this.a.removeCallbacksAndMessages(null);
    }

    @Override // com.kwad.sdk.core.j.c
    @MainThread
    public final void b(d dVar) {
        ae.a();
        if (dVar == null || this.c == null) {
            return;
        }
        this.c.remove(dVar);
    }

    public abstract boolean c();

    public final boolean d() {
        return bb.a(this.d, this.e, false);
    }

    protected final void e() {
        if (this.b.getAndSet(true)) {
            return;
        }
        a(true);
    }

    protected final void f() {
        if (this.b.getAndSet(false)) {
            a(false);
        }
    }

    @CallSuper
    public void g() {
        b();
        if (this.c != null) {
            this.c.clear();
        }
    }
}
