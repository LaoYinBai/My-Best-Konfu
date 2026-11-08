package com.tachikoma.core.component.progressbar;

import android.content.Context;
import com.tachikoma.core.component.IFactory;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class KTProgressBarViewFactory implements IFactory<KTProgressBarView> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tachikoma.core.component.IFactory
    public KTProgressBarView newInstance(Context context, List<Object> list) {
        return new KTProgressBarView(context, list);
    }

    @Override // com.tachikoma.core.component.IFactory
    public /* bridge */ /* synthetic */ KTProgressBarView newInstance(Context context, List list) {
        return newInstance(context, (List<Object>) list);
    }
}
