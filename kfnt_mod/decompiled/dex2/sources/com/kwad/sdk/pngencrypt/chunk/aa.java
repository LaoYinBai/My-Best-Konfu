package com.kwad.sdk.pngencrypt.chunk;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class aa extends PngChunk {
    protected aa(String str, com.kwad.sdk.pngencrypt.k kVar) {
        super(str, kVar);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        aa aaVar = (aa) obj;
        if (this.a == null) {
            if (aaVar.a != null) {
                return false;
            }
        } else if (!this.a.equals(aaVar.a)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return 31 + (this.a == null ? 0 : this.a.hashCode());
    }
}
