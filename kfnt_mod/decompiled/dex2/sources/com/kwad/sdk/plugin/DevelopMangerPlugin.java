package com.kwad.sdk.plugin;

import java.io.Serializable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public interface DevelopMangerPlugin extends d {

    public static class DevelopValue implements Serializable {
        private static final long serialVersionUID = 2793333073373146040L;
        Serializable mValue;

        public DevelopValue(Serializable serializable) {
            this.mValue = serializable;
        }

        public <T> T getValue() {
            if (this.mValue != null) {
                return (T) this.mValue;
            }
            return null;
        }
    }

    DevelopValue a(String str);
}
