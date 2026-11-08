package com.bytedance.a;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.bytedance.a.da;
import com.bytedance.a.dk;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class cv extends dy<dk> {

    public class a implements da.b<dk, String> {
        public a(cv cvVar) {
        }

        @Override // com.bytedance.a.da.b
        public dk a(IBinder iBinder) {
            return dk.a.a(iBinder);
        }

        @Override // com.bytedance.a.da.b
        public String a(dk dkVar) {
            return ((dk.a.C0098a) dkVar).a();
        }
    }

    public cv() {
        super("com.samsung.android.deviceidservice");
    }

    @Override // com.bytedance.a.dy
    public da.b<dk, String> a() {
        return new a(this);
    }

    @Override // com.bytedance.a.dy
    public Intent b(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.samsung.android.deviceidservice", "com.samsung.android.deviceidservice.DeviceIdService");
        return intent;
    }
}
