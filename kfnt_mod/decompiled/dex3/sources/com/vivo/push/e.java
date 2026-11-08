package com.vivo.push;

import android.content.Context;
import com.vivo.push.b.n;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class e implements Runnable {
    protected Context a;
    int b;
    private g c;

    public abstract void a(g gVar);

    public e(g gVar) {
        this.b = -1;
        this.c = gVar;
        this.b = gVar.a;
        if (this.b < 0) {
            throw new IllegalArgumentException("PushTask need a > 0 task id.");
        }
        this.a = d.a().e;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.a != null && !(this.c instanceof n)) {
            com.vivo.push.util.n.a(this.a, "[执行指令]" + this.c);
        }
        a(this.c);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("{");
        sb.append(this.c == null ? "[null]" : this.c.toString());
        sb.append("}");
        return sb.toString();
    }
}
