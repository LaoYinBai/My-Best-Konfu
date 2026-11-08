package com.vivo.push.b;

import com.ss.android.socialbase.appdownloader.constants.InstallErrorCode;
import com.umeng.message.MsgConstant;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class z extends c {
    private ArrayList<String> c;

    @Override // com.vivo.push.b.c, com.vivo.push.g
    public final String toString() {
        return "TagCommand";
    }

    public z(boolean z, String str, ArrayList<String> arrayList) {
        super(z ? InstallErrorCode.ERROR_CANCELED : 2005, str);
        this.c = arrayList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.b.c, com.vivo.push.g
    public final void b(com.vivo.push.a aVar) {
        super.b(aVar);
        aVar.a(MsgConstant.KEY_TAGS, (Serializable) this.c);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.b.c, com.vivo.push.g
    public final void c(com.vivo.push.a aVar) {
        super.c(aVar);
        this.c = aVar.b(MsgConstant.KEY_TAGS);
    }
}
