package com.tachikoma.core.component.recyclerview.export;

import android.content.Context;
import android.view.View;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.component.TKBase;
import java.util.List;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKRefreshAnimatableView extends TKBase<View> {
    public TKRefreshAnimatableView(Context context, List<Object> list) {
        super(context, list);
    }

    @Override // com.tachikoma.core.component.TKBase
    protected View createViewInstance(Context context) {
        return null;
    }

    public void startAnimation() {
    }

    public void stopAnimation() {
    }

    public void turnBack(int i) {
    }

    public void updateKeyFrameWithProgress(double d) {
    }
}
