package com.tachikoma.core.component.recyclerview.pagelist;

import androidx.annotation.Nullable;
import com.kwad.v8.V8Object;
import com.sigmob.sdk.common.mta.PointCategory;
import com.tachikoma.core.component.recyclerview.export.ITKPageList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKPageList implements ITKPageList {

    @Nullable
    private final V8Object mAssociateV8Obj;

    public TKPageList(@Nullable V8Object v8Object) {
        this.mAssociateV8Obj = v8Object;
    }

    @Override // com.tachikoma.core.component.recyclerview.export.ITKPageList
    public boolean hasMore() {
        try {
            if (this.mAssociateV8Obj == null) {
                return false;
            }
            return ((Boolean) this.mAssociateV8Obj.executeJSFunction("hasMore")).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.tachikoma.core.component.recyclerview.export.ITKPageList
    public void load() {
        try {
            if (this.mAssociateV8Obj == null) {
                return;
            }
            this.mAssociateV8Obj.executeJSFunction(PointCategory.LOAD);
        } catch (Exception unused) {
        }
    }
}
