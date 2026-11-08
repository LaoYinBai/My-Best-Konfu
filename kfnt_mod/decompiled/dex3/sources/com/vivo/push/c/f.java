package com.vivo.push.c;

import android.content.SharedPreferences;
import com.vivo.push.util.w;
import com.vivo.push.util.x;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class f extends o {
    public f(com.vivo.push.g gVar) {
        super(gVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(com.vivo.push.g gVar) {
        com.vivo.push.util.n.d("OnClearCacheTask", "delete push info " + this.a.getPackageName());
        x xVarB = x.b(this.a);
        w wVar = new w();
        if (wVar.a(xVarB.a)) {
            SharedPreferences.Editor editorEdit = wVar.b.edit();
            if (editorEdit != null) {
                editorEdit.clear();
                com.vivo.push.util.a.a(editorEdit);
            }
            com.vivo.push.util.n.d(w.a, "system cache is cleared");
            com.vivo.push.util.n.d("SystemCache", "sp cache is cleared");
        }
    }
}
