package com.xiaomi.push;

import com.sigmob.sdk.common.Constants;
import java.nio.ByteBuffer;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class fm extends fg {
    public fm() {
        a("PING", (String) null);
        a(Constants.FAIL);
        a(0);
    }

    @Override // com.xiaomi.push.fg
    /* renamed from: a */
    ByteBuffer mo313a(ByteBuffer byteBuffer) {
        return a().length == 0 ? byteBuffer : super.mo313a(byteBuffer);
    }

    @Override // com.xiaomi.push.fg
    public int c() {
        if (a().length == 0) {
            return 0;
        }
        return super.c();
    }
}
