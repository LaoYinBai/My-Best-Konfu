package com.tachikoma.core.component.recyclerview;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.kwad.yoga.YogaUnit;
import com.kwad.yoga.YogaValue;
import com.tachikoma.core.yoga.layout.YogaLayout;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class HeaderFooterViewHolder extends RecyclerView.ViewHolder {
    YogaValue mOriginHeight;
    YogaValue mOriginWidth;

    public HeaderFooterViewHolder(@NonNull View view, YogaValue yogaValue, YogaValue yogaValue2) {
        super(view);
        this.mOriginWidth = yogaValue;
        this.mOriginHeight = yogaValue2;
    }

    void maybeReset() {
        if (this.itemView instanceof YogaLayout) {
            if (this.mOriginWidth.unit == YogaUnit.AUTO) {
                ((YogaLayout) this.itemView).getYogaNode().setWidthAuto();
            }
            if (this.mOriginHeight.unit == YogaUnit.AUTO) {
                ((YogaLayout) this.itemView).getYogaNode().setHeightAuto();
            }
        }
    }
}
