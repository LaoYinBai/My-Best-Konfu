package com.kwai.sodler.lib;

import com.kwai.sodler.lib.kwai.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b implements com.kwai.sodler.lib.kwai.b {
    private b.a a;

    @Override // com.kwai.sodler.lib.kwai.b
    public void a(com.kwai.sodler.lib.kwai.f fVar) {
        if (this.a != null) {
            fVar.a(this.a.a(fVar.i()));
        } else {
            a.e("Sodler.PluginConfigUpdater", "config reqester not set");
        }
    }
}
