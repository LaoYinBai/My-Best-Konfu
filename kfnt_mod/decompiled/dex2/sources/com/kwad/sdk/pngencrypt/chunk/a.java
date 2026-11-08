package com.kwad.sdk.pngencrypt.chunk;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements com.kwad.sdk.pngencrypt.g {
    boolean a;

    public a() {
        this(true);
    }

    public a(boolean z) {
        this.a = z;
    }

    @Override // com.kwad.sdk.pngencrypt.g
    public final PngChunk a(d dVar, com.kwad.sdk.pngencrypt.k kVar) {
        PngChunk pngChunkA = a(dVar.c, kVar);
        if (pngChunkA == null) {
            pngChunkA = c(dVar.c, kVar);
        }
        if (pngChunkA == null) {
            pngChunkA = b(dVar.c, kVar);
        }
        pngChunkA.b(dVar);
        if (this.a && dVar.d != null) {
            pngChunkA.a(dVar);
        }
        return pngChunkA;
    }

    protected final PngChunk a(String str, com.kwad.sdk.pngencrypt.k kVar) {
        if (str.equals("IDAT")) {
            return new o(kVar);
        }
        if (str.equals("IHDR")) {
            return new q(kVar);
        }
        if (str.equals("PLTE")) {
            return new v(kVar);
        }
        if (str.equals("IEND")) {
            return new p(kVar);
        }
        if (str.equals("tEXt")) {
            return new ab(kVar);
        }
        if (str.equals("iTXt")) {
            return new r(kVar);
        }
        if (str.equals("zTXt")) {
            return new ag(kVar);
        }
        if (str.equals("bKGD")) {
            return new h(kVar);
        }
        if (str.equals("gAMA")) {
            return new l(kVar);
        }
        if (str.equals("pHYs")) {
            return new u(kVar);
        }
        if (str.equals("iCCP")) {
            return new n(kVar);
        }
        if (str.equals("tIME")) {
            return new ac(kVar);
        }
        if (str.equals("tRNS")) {
            return new ad(kVar);
        }
        if (str.equals("cHRM")) {
            return new i(kVar);
        }
        if (str.equals("sBIT")) {
            return new w(kVar);
        }
        if (str.equals("sRGB")) {
            return new y(kVar);
        }
        if (str.equals("hIST")) {
            return new m(kVar);
        }
        if (str.equals("sPLT")) {
            return new x(kVar);
        }
        if (str.equals("fdAT")) {
            return new k(kVar);
        }
        if (str.equals("acTL")) {
            return new g(kVar);
        }
        if (str.equals("fcTL")) {
            return new j(kVar);
        }
        return null;
    }

    protected final PngChunk b(String str, com.kwad.sdk.pngencrypt.k kVar) {
        return new af(str, kVar);
    }

    protected PngChunk c(String str, com.kwad.sdk.pngencrypt.k kVar) {
        if (str.equals("oFFs")) {
            return new t(kVar);
        }
        if (str.equals("sTER")) {
            return new z(kVar);
        }
        return null;
    }
}
