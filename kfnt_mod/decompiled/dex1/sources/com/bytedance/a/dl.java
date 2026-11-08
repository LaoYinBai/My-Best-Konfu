package com.bytedance.a;

import com.bytedance.applog.AppLog;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class dl extends cw {
    public final String f;
    public int g;

    public dl(dg dgVar, String str) {
        super(dgVar);
        this.g = 0;
        this.f = str;
    }

    @Override // com.bytedance.a.cw
    public boolean a() {
        return true;
    }

    @Override // com.bytedance.a.cw
    public long b() {
        return 1000L;
    }

    @Override // com.bytedance.a.cw
    public long[] c() {
        return new long[]{1000};
    }

    @Override // com.bytedance.a.cw
    public boolean d() {
        this.g = bf.a((JSONObject) null, this.f) ? 0 : this.g + 1;
        if (this.g > 3) {
            AppLog.setRangersEventVerifyEnable(false, this.f);
        }
        return true;
    }

    @Override // com.bytedance.a.cw
    public String e() {
        return "RangersEventVerify";
    }
}
