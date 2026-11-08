package com.kwai.sodler.lib.ext;

import com.kwai.sodler.lib.g;
import com.kwai.sodler.lib.h;
import com.kwai.sodler.lib.kwai.a;
import com.kwai.sodler.lib.kwai.f;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public interface b<P extends com.kwai.sodler.lib.kwai.a, R extends f<P>> {

    public static class a implements b<g, h> {
        @Override // com.kwai.sodler.lib.ext.b
        public void a(h hVar) {
        }

        @Override // com.kwai.sodler.lib.ext.b
        public void a(h hVar, PluginError pluginError) {
        }

        @Override // com.kwai.sodler.lib.ext.b
        public void a(h hVar, g gVar) {
        }

        @Override // com.kwai.sodler.lib.ext.b
        public void b(h hVar) {
        }

        @Override // com.kwai.sodler.lib.ext.b
        public void c(h hVar) {
        }

        @Override // com.kwai.sodler.lib.ext.b
        public void d(h hVar) {
        }
    }

    void a(R r);

    void a(R r, PluginError pluginError);

    void a(R r, P p);

    void b(R r);

    void c(R r);

    void d(R r);
}
