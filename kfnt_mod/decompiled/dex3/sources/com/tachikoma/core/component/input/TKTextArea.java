package com.tachikoma.core.component.input;

import android.content.Context;
import androidx.annotation.Nullable;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import java.util.List;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKTextArea extends TKInput {
    public TKTextArea(Context context, @Nullable List<Object> list) {
        super(context, list);
    }

    @Override // com.tachikoma.core.component.input.TKInput
    protected boolean isSingleLine() {
        return false;
    }

    public void setTextLineClamp(int i) {
        this.mProperty.setMaxLines(i);
    }
}
