package com.tachikoma.core.event.guesture;

import android.content.Context;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.component.anim.AnimationProperty;
import com.tachikoma.core.event.base.TKBaseEvent;
import java.util.HashMap;
import java.util.List;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKPinchEvent extends TKBaseEvent {
    public float scale;

    public TKPinchEvent(Context context, List<Object> list) {
        super(context, list);
    }

    @Override // com.tachikoma.core.event.base.TKBaseEvent, com.tachikoma.core.event.base.IBaseEvent
    public void configWithData(HashMap<String, Object> map) {
        super.configWithData(map);
        this.scale = ((Number) map.get(AnimationProperty.SCALE)).floatValue();
    }

    public void setScale(float f) {
        this.scale = f;
    }
}
