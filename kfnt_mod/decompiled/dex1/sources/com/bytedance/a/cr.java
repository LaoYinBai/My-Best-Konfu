package com.bytedance.a;

import android.content.Context;
import com.bytedance.applog.Level;
import com.bytedance.applog.UriConfig;
import com.bytedance.common.utility.date.DateDef;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class cr extends cw {
    public cr(dg dgVar) {
        super(dgVar);
    }

    @Override // com.bytedance.a.cw
    public boolean a() {
        return true;
    }

    @Override // com.bytedance.a.cw
    public long b() {
        return DateDef.HOUR;
    }

    @Override // com.bytedance.a.cw
    public long[] c() {
        return dq.f;
    }

    @Override // com.bytedance.a.cw
    public boolean d() {
        boolean zA;
        be beVar = this.f3480a.i;
        if (beVar.i() != 0) {
            UriConfig uriConfigC = this.f3480a.c();
            JSONObject jSONObjectA = beVar.a();
            if (jSONObjectA != null) {
                zA = bf.a(bk.a((Context) this.f3480a.d, beVar.a(), uriConfigC.getActiveUri(), true, Level.L0), jSONObjectA);
            } else {
                de.a("U SHALL NOT PASS!", null);
                zA = false;
            }
        } else {
            zA = false;
        }
        if (zA) {
            this.e = true;
        }
        return zA;
    }

    @Override // com.bytedance.a.cw
    public String e() {
        return "activator";
    }
}
