package com.vivo.push.c;

import com.vivo.push.cache.ClientConfigManagerImpl;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class c extends com.vivo.push.e {
    public c(com.vivo.push.g gVar) {
        super(gVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(com.vivo.push.g gVar) {
        com.vivo.push.util.n.a(ClientConfigManagerImpl.getInstance(this.a).isDebug());
    }
}
