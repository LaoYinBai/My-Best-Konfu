package com.vivo.push;

import android.content.Intent;
import android.os.Bundle;
import com.taobao.accs.common.Constants;
import com.vivo.push.util.n;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class g {
    public int a;
    public String b;

    public boolean a() {
        return false;
    }

    public abstract void b(a aVar);

    public abstract void c(a aVar);

    public g(int i) {
        this.a = -1;
        if (i < 0) {
            throw new IllegalArgumentException("PushCommand: the value of command must > 0.");
        }
        this.a = i;
    }

    public final void a(a aVar) {
        String strA = h.a(this.a);
        if (strA == null) {
            strA = "";
        }
        aVar.a("method", strA);
        d(aVar);
    }

    public final void a(Intent intent) {
        a aVarA = a.a(intent);
        if (aVarA == null) {
            n.b("PushCommand", "bundleWapper is null");
            return;
        }
        aVarA.a("method", this.a);
        d(aVarA);
        Bundle bundle = aVarA.a;
        if (bundle != null) {
            intent.putExtras(bundle);
        }
    }

    private final void d(a aVar) {
        aVar.a(Constants.KEY_COMMAND, this.a);
        aVar.a("client_pkgname", this.b);
        b(aVar);
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
