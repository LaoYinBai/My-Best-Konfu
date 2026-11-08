package com.tachikoma.core.component.recyclerview.export;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKWaterLayoutConfiguration {
    public int centerPadding;
    public int edgePadding;
    public int rowPadding;

    public void confirmDataValid() {
        if (this.edgePadding < 0) {
            this.edgePadding = 0;
        }
        if (this.centerPadding < 0) {
            this.centerPadding = 0;
        }
        if (this.rowPadding < 0) {
            this.rowPadding = 0;
        }
    }

    public boolean isValid() {
        return this.edgePadding > 0 || this.centerPadding > 0 || this.rowPadding > 0;
    }
}
